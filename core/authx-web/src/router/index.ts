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
      },
      {
        name: 'register',
        path: 'register',
        component: () => import('@/views/common/auth/AuthRegister.vue')
      }
    ]
  },
  {
    path: '/admin',
    component: LayoutContainer,
    children: [
      {
        name: 'users',
        path: 'users',
        component: () => import('@/views/user/UserView.vue')
      },
      {
        name: 'roles',
        path: 'roles',
        component: () => import('@/views/role/RoleView.vue')
      },
      {
        name: 'methods',
        path: 'methods',
        component: () => import('@/views/method/MethodView.vue')
      }
    ]
  },
  {
    path: '/common',
    component: LayoutContainer,
    children: [
      {
        name: '403',
        path: '403',
        component: () => import('@/views/common/code/PageForbidden.vue')
      },
      {
        name: '404',
        path: '404',
        component: () => import('@/views/common/code/PageNotFound.vue')
      }
    ]
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

// 路由转换前增加特殊编码，404，403 等页面处理
router.beforeEach((to, from, next) => {
  if (to.matched.length === 0) {
    next({ name: '404' })
  } else {
    next()
  }
})

export default router
