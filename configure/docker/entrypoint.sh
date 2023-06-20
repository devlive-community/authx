#!/bin/bash

set -e

# 启动 MySQL 服务
service mysql start
# 删除预留数据
#service mysql restart

# 等待 MySQL 服务启动
RET=1
while [[ RET -ne 0 ]]; do
    echo "Waiting for MySQL to start..."
    sleep 5
    mysql -uroot -p12345678 -e "status" > /dev/null 2>&1
    RET=$?
done

echo "MySQL is up and running!"

# 最后再运行主服务
exec sh ./bin/debug.sh
