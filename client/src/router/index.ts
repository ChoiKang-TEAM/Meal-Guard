import { route } from 'quasar/wrappers'
import {
  createMemoryHistory,
  createRouter,
  createWebHashHistory,
  createWebHistory,
} from 'vue-router'

import routes from './routes'
import { useMemberUserStore } from 'src/stores/member-user-store'
import { storeToRefs } from 'pinia'

/*
 * If not building with SSR mode, you can
 * directly export the Router instantiation;
 *
 * The function below can be async too; either use
 * async/await or return a Promise which resolves
 * with the Router instance.
 */

export default route(function (/* { store, ssrContext } */) {
  const createHistory = process.env.SERVER
    ? createMemoryHistory
    : process.env.VUE_ROUTER_MODE === 'history'
    ? createWebHistory
    : createWebHashHistory

  const Router = createRouter({
    scrollBehavior: () => ({ left: 0, top: 0 }),
    routes,

    // Leave this as is and make changes in quasar.conf.js instead!
    // quasar.conf.js -> build -> vueRouterMode
    // quasar.conf.js -> build -> publicPath
    history: createHistory(process.env.VUE_ROUTER_BASE),
  })

  Router.beforeEach((to, from, next) => {
    const memberUserStore = useMemberUserStore()
    const exceptionPathList: string[] = ['/login', '/sign-up']
    const { resetToken } = memberUserStore
    const { token } = storeToRefs(memberUserStore)
    if (exceptionPathList.includes(to.path)) {
      resetToken()
      return next()
    }

    // 토큰이 있는지 확인
    if (token.value) {
      // 토큰이 있는 경우 정상적으로 페이지로 이동
      next()
    } else {
      // 토큰이 없는 경우 로그인 페이지로 리다이렉트 또는 접근 거부 처리
      next('/login') // 로그인 페이지로 리다이렉트
      // 또는
      // next(false) // 접근 거부
    }
  })

  return Router
})
