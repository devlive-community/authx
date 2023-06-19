<template>
  <div class="login_container">
    <div class="login_box">
      <div class="avatar_box">
        <Avatar
          size="80"
          src="/static/images/logo.png">
        </Avatar>
      </div>
      <div class="login_form">
        <Login @on-submit="handlerSubmit">
          <UserName name="username"/>
          <Password name="password"/>
          <div class="login_btn">
            <Space>
              <Button size="large" to="/auth/register">注册</Button>
              <Submit/>
            </Space>
          </div>
        </Login>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import AuthService from '@/services/user/AuthService'
import { Message } from 'view-ui-plus'
import SupportUtils from '@/utils/SupportUtils'
import router from '@/router'

export default {
  created () {
    localStorage.removeItem(SupportUtils.token)
  },
  methods: {
    handlerSubmit (valid: any, { username, password }: any) {
      if (valid) {
        AuthService.doAuth(username, password)
          .then(response => {
            if (response?.data.code === 2000) {
              AuthService.saveAuth(response?.data?.data)
              router.push('/')
            }
          })
          .catch(error => {
            const message = error?.response ? error?.response?.data.message : error.message
            Message.error(message)
          })
      }
    }
  }
}
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
  //border: 1px solid #eee; border-radius: 50%; padding: 10px;
  //box-shadow: 0 0 1px #ddd; position: absolute; left: 50%;
    transform: translate(-50%, -50%);
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
