<template>
  <div>
    <Layout>
      <Affix>
        <FastDocLayoutHeader :is-logined="isLogined" :user-info="userInfo">
        </FastDocLayoutHeader>
      </Affix>
      <Layout :style="{padding: '0', height: '650px', background: '#fff'}">
        <Sider hide-trigger :style="{background: '#fff'}">
          <FastDocLayoutAside :menus="menus">
          </FastDocLayoutAside>
        </Sider>
        <Layout :style="{background: '#fff', padding: '10px'}">
          <FastDocLayoutContent>
          </FastDocLayoutContent>
        </Layout>
      </Layout>
      <FastDocLayoutFooter>
      </FastDocLayoutFooter>
    </Layout>
  </div>
</template>

<script lang="ts">
import FastDocLayoutHeader from '@/layouts/basic/components/LayoutHeader.vue'
import FastDocLayoutContent from '@/layouts/basic/components/LayoutContent.vue'
import FastDocLayoutFooter from '@/layouts/basic/components/LayoutFooter.vue'
import AuthService from '@/services/AuthService'
import { defineComponent } from 'vue'
import UserService from '@/services/UserService'
import MenuService from '@/services/MenuService'
import { MenuEntity } from '@/entity/MenuEntity'
import FastDocLayoutAside from '@/layouts/basic/components/LayoutAside.vue'

export default defineComponent({
  name: 'FastDocLayoutContainer',
  components: {
    FastDocLayoutAside,
    FastDocLayoutHeader,
    FastDocLayoutContent,
    FastDocLayoutFooter
  },
  created () {
    this.handlerInitialize()
  },
  data () {
    return {
      isLogined: false,
      userInfo: { name: '', id: 0 },
      menus: Array<MenuEntity>()
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
              this.handlerInitializeMenus()
            } else {
              this.userInfo.name = username
            }
          })
      }
    },
    handlerInitializeMenus () {
      MenuService.getMenusByUser(this.userInfo.id)
        .then(response => {
          this.menus = response.data
        })
    }
  }
})
</script>
