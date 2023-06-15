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
          <MenuItem name="auth_login" to="/auth/login">
            <font-awesome-icon :icon="['fas', 'user-plus']"/>
            登录
          </MenuItem>
        </div>
      </Menu>
    </Header>
  </div>
</template>

<script lang="ts">
export default {
  name: 'FastDocLayoutHeader'
}
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
