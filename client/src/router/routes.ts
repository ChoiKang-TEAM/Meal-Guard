import { RouteRecordRaw } from 'vue-router'

const routes: RouteRecordRaw[] = [
  {
    path: '/',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      {
        name: 'Home',
        path: '',
        component: () => import('pages/Home.vue'),
        meta: { isBgImage: false },
      },
      {
        path: 'member-user',
        component: () => import('pages/member-user/MemberUser.vue'),
        children: [
          {
            name: 'ChatMessenger',
            path: 'chat-messenger',
            component: () => import('pages/member-user/ChatMessenger.vue'),
          },
        ],
      },
    ],
  },
  {
    name: 'Recipe',
    path: '/recipe',
    component: () => import('pages/recipe/Recipe.vue'),
    children: [
      {
        name: 'RecipeDetail',
        path: 'detail/:id',
        component: () => import('pages/recipe/RecipeDetail.vue'),
      },
    ],
  },

  {
    name: 'Login',
    path: '/login',
    component: () => import('pages/Login.vue'),
  },

  // Always leave this as last one,
  // but you can also remove it
  {
    path: '/:catchAll(.*)*',
    component: () => import('pages/ErrorNotFound.vue'),
  },
]

export default routes
