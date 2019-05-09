#!/bin/sh
## java env
export JAVA_HOME=/usr/local/jdk/jdk1.8.0_101
export JRE_HOME=${JAVA_HOME}/jre

APPLICATION_NAME=bootstack
JAR_NAME=${APPLICATION_NAME}.jar
RUN_PID=${APPLICATION_NAME}.pid

# Use instructions to prompt for input parameters
usage() {
    echo "Usage: sh bootstack.sh [start|stop|restart|status]"
    exit 1
}

# Check if the program is running
running(){
  pid=`ps -ef|grep ${JAR_NAME}|grep -v grep|awk '{print $2}' `
  # If there is no return 1, there is a return 0
  if [[ -z "${pid}" ]]; then
   return 1
  else
    return 0
  fi
}

# start
start(){
  running
  if [[ $? -eq "0" ]]; then
    echo ">>> ${JAR_NAME} is already running PID=${pid} <<<"
  else
    nohup ${JRE_HOME}/bin/java -Xms256m -Xmx512m -jar ${JAR_NAME} >/dev/null 2>&1 &
    echo $! > ${RUN_PID}
    echo ">>> start ${JAR_NAME} successed PID=$! <<<"
   fi
  }

# stop
stop(){
  pid_file=$(cat ${RUN_PID})
  echo ">>> api PID = ${pid_file} begin kill ${pid_file} <<<"
  kill ${pid_file}
  rm -rf ${RUN_PID}
  sleep 2
  running
  if [[ $? -eq "0" ]]; then
    echo ">>> api 2 PID = ${pid} begin kill -9 ${pid}  <<<"
    kill -9  ${pid}
    sleep 2
    echo ">>> ${JAR_NAME} process stopped <<<"
  else
    echo ">>> ${JAR_NAME} is not running <<<"
  fi
}

# status
status(){
  running
  if [[ $? -eq "0" ]]; then
    echo ">>> ${JAR_NAME} is running PID is ${pid} <<<"
  else
    echo ">>> ${JAR_NAME} is not running <<<"
  fi
}

# restart
restart(){
  stop
  start
}

# According to the input parameters, select the corresponding method to execute, no input will execute the instructions
case "$1" in
  "start")
    start
    ;;
  "stop")
    stop
    ;;
  "status")
    status
    ;;
  "restart")
    restart
    ;;
  *)
    usage
    ;;
esac
exit 0