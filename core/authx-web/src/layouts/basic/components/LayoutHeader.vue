<template>
  <div>
    <Header>
      <Menu mode="horizontal" theme="dark">
        <div class="layout-logo">
          <MenuItem name="home" to="/">
            <Avatar
              size="45"
              src="/static/images/logo.png">
            </Avatar>
          </MenuItem>
        </div>
        <div class="layout-nav">
          <div v-if="isLogined">
            <Dropdown placement="bottom-end">
              <a href="javascript:void(0)">
                <Avatar style="background-color: #87d068">
                  {{ userInfo.name }}
                </Avatar>
              </a>
              <template #list>
                <DropdownMenu>
                  <DropdownItem @click="handlerSignOut">
                    <font-awesome-icon :icon="['fas', 'sign-out']"/> 退出
                  </DropdownItem>
                </DropdownMenu>
              </template>
            </Dropdown>
          </div>
          <div v-else>
            <MenuItem name="auth_login" to="/auth/login">
              <font-awesome-icon :icon="['fas', 'right-to-bracket']"/>
              登录
            </MenuItem>
            <MenuItem name="auth_login" to="/auth/register">
              <font-awesome-icon :icon="['fas', 'user-plus']"/>
              注册
            </MenuItem>
          </div>
        </div>
      </Menu>
    </Header>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue'
import AuthService from '@/services/user/AuthService'
import UserService from '@/services/user/UserService'
import { UserEntity } from '@/entity/UserEntity'
import SupportUtils from '@/utils/SupportUtils'
import router from '@/router'

export default defineComponent({
  name: 'FastDocLayoutHeader',
  created () {
    this.handlerInitialize()
  },
  data () {
    return {
      isLogined: false,
      userInfo: { name: '' }
    }
  },
  methods: {
    handlerInitialize () {
      const auth: string = AuthService.getAuth()
      const username: string = AuthService.getAuthUserName()
      if (auth) {
        this.isLogined = true
        UserService.getInfoByUserName(username)
          .then(response => {
            if (response.code === 2000) {
              this.userInfo = response.data
            } else {
              this.userInfo.name = username
            }
          })
      }
    },
    handlerSignOut () {
      localStorage.removeItem(SupportUtils.token)
      router.push('/auth/login')
    }
  }
})
</script>

<style scoped>
.layout {
  border: 1px solid #d7dde4;
  background: #f5f7f9;
  position: relative;
  border-radius: 4px;
  overflow: hidden;
}

.layout-logo {
  border-radius: 3px;
  float: left;
  position: relative;
  left: 20px;
}

.layout-nav {
  float: right;
  margin: 0 20px 0 auto;
}
</style>
