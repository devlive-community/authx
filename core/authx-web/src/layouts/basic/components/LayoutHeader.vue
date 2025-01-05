<template>
  <div>
    <ShadcnLayoutHeader>
      <ShadcnMenu direction="horizontal">
        <div class="layout-logo">
          <ShadcnMenuItem name="home" to="/">
            <ShadcnAvatar src="/static/images/logo.png"/>
          </ShadcnMenuItem>
        </div>
        <div class="layout-nav">
          <div v-if="isLogined">
            <ShadcnDropdown placement="bottom-end">
              <a href="javascript:void(0)">
                <Avatar style="background-color: #87d068">
                  {{ userInfo.name }}
                </Avatar>
              </a>
              <template #list>
                <DropdownMenu>
                  <DropdownItem @click="logout">
                    <font-awesome-icon :icon="['fas', 'sign-out']"/>
                    退出
                  </DropdownItem>
                </DropdownMenu>
              </template>
            </ShadcnDropdown>
          </div>
          <div v-else>
            <ShadcnMenuItem name="auth_login" to="/auth/login">
              <font-awesome-icon :icon="['fas', 'right-to-bracket']"/>
              登录
            </ShadcnMenuItem>
            <ShadcnMenuItem name="auth_login" to="/auth/register">
              <font-awesome-icon :icon="['fas', 'user-plus']"/>
              注册
            </ShadcnMenuItem>
          </div>
        </div>
      </ShadcnMenu>
    </ShadcnLayoutHeader>
  </div>
</template>

<script setup lang="ts">
import router from '@/router'
import AuthService from '@/services/auth'

interface Props
{
  isLogined: boolean
  userInfo: any
}

withDefaults(defineProps<Props>(), {
  isLogined: false,
  userInfo: null
})

const logout = () => {
  AuthService.logout()
  router.push('/auth/login')
}
</script>
