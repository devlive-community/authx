<template>
  <div class="login_container">
    <div class="login_box">
      <div class="avatar_box">
        <Avatar
          style="width: 75px; height: 80px;"
          src="/static/images/logo.png">
        </Avatar>
      </div>
      <div class="login_form">
        <Login>
          <UserName name="username"/>
          <Password name="password"/>
          <div class="login_btn">
            <Submit/>
          </div>
        </Login>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
</script>

<style scoped>
.login_container {
  height: 75vh;
}

.login_box {
  width: 450px;
  height: 250px;
  background-color: #ecf5ff;
  border-radius: 20px;
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);

  .avatar_box {
    height: 100px;
    width: 100px;
    border: 1px solid #eee;
    border-radius: 50%;
    padding: 10px;
    box-shadow: 0 0 10px #ddd;
    position: absolute;
    left: 50%;
    transform: translate(-50%, -50%);
    background-color: #91e1b9;
  }
}

.login_form {
  position: absolute;
  bottom: 0;
  width: 100%;
  padding: 20px;
  box-sizing: border-box;
}

.login_btn {
  display: flex;
  justify-content: flex-end;
}
</style>
