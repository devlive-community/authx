<template>
  <div class="login_container">
    <div class="login_box">
      <div class="avatar_box">
        <!--        <img src="../assets/logo.png" alt="" />-->
      </div>
      <el-form label-width="60px" class="login_form">
        <el-form-item label="用户名">
          <el-input placeholder="请输入用户名">
          </el-input>
        </el-form-item>
        <el-form-item label="密 码">
          <el-input placeholder="请输入用户密码">
          </el-input>
        </el-form-item>
        <el-row justify="end">
          <el-form-item class="login_btn">
            <el-button type="primary">登录</el-button>
            <el-button type="info">注册</el-button>
          </el-form-item>
        </el-row>
      </el-form>
    </div>
  </div>
</template>

<script>
</script>
<style scoped>
.login_container {
  height: 90vh;
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
    background-color: #fff;

    img {
      width: 100%;
      height: 100%;
      border-radius: 50%;
      background-color: #eee;
    }
  }
}

.login_form {
  position: absolute;
  bottom: 0;
  width: 100%;
  padding: 0 20px;
  box-sizing: border-box;
}

.login_btn {
  display: flex;
  justify-content: flex-end;
}
</style>
