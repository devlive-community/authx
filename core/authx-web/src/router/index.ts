import { createRouter, createWebHashHistory, RouteRecordRaw } from 'vue-router'
import LayoutContainer from '@/layouts/basic/Layout.vue'

const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    name: 'home',
    component: LayoutContainer
  },
  {
    path: '/auth',
    component: LayoutContainer,
    children: [
      {
        name: 'login',
        path: 'login',
        component: () => import('@/views/common/auth/AuthLogin.vue')
      }
    ]
  },
  {
    path: '/about',
    name: 'about',
    component: () => import(/* webpackChunkName: "about" */ '@/views/AboutView.vue')
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
