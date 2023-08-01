<script lang="ts">
import { useQuasar } from 'quasar'
import { LoginMemberUserInput } from 'src/common/models'
import { defineComponent, ref } from 'vue'
import { useRouter } from 'vue-router'
import { useAuthStore } from 'src/stores/auth-store'
import { useMemberUserStore } from 'src/stores/member-user-store'

export default defineComponent({
  setup() {
    const $q = useQuasar()
    const router = useRouter()
    const authStore = useAuthStore()
    const memberUserStore = useMemberUserStore()
    const { loginMemberUser } = authStore
    const { setToken } = memberUserStore
    const userId = ref<string>('')
    const password = ref<string>('')
    const isPwd = ref<boolean>(true)

    const memberLogin = async () => {
      try {
        const loginMemberUserInput: LoginMemberUserInput = {
          userId: userId.value,
          password: password.value,
        }
        const token = await loginMemberUser({
          loginMemberUserInput: loginMemberUserInput,
        })
        if (token?.error)
          return $q.dialog({
            title: '알림',
            message: '아이디와 비밀번호를 확인해주세요.',
            color: 'negative',
          })
        setToken(token?.data?.loginMemberUser)
        router.replace({ name: 'Home' })
      } catch (e) {
        console.error(e)
      }
    }

    const goSignUpPage = () => {
      router.replace({ name: 'SignUp' })
    }

    const state = { userId, password, isPwd }
    const action = { memberLogin, goSignUpPage }
    return {
      ...state,
      ...action,
    }
  },
})
</script>
<template>
  <q-layout view="hHh lpR fFf">
    <q-header elevated class="bg-orange text-white">
      <q-toolbar>
        <q-toolbar-title>
          <q-avatar size="24px">
            <img src="src/assets/images/login-icon.png" />
          </q-avatar>
          MEAL-GUARD
        </q-toolbar-title>
      </q-toolbar>
    </q-header>

    <div class="q-pa-md q-gutter-md d-flex justify-center align-center vh-100">
      <q-card class="card-container">
        <q-card-section>
          <h2 class="text-h6 text-black">로그인</h2>
        </q-card-section>

        <q-form @submit="memberLogin">
          <q-card-section>
            <q-input
              rounded
              outlined
              v-model="userId"
              label="아이디"
              type="text"
              placeholder="아이디를 입력해주세요."
            />
          </q-card-section>
          <q-card-section>
            <q-input
              rounded
              outlined
              v-model="password"
              label="비밀번호"
              :type="isPwd ? 'password' : 'text'"
              placeholder="비밀번호를 입력해주세요."
              autocomplete="off"
            >
              <template v-slot:append
                ><q-icon
                  :name="isPwd ? 'visibility_off' : 'visibility'"
                  class="cursor-pointer"
                  @click="isPwd = !isPwd"
                />
              </template>
            </q-input>
          </q-card-section>

          <q-card-section>
            <q-btn
              rounded
              outline
              class="w-100"
              color="primary"
              type="submit"
              label="로그인"
            />
          </q-card-section>
          <q-card-section> <q-separator inset /> </q-card-section>
          <q-card-actions>
            <div class="w-100">
              <q-btn flat class="w-50" color="orange" label="비밀번호 찾기" />
              <q-btn
                flat
                class="w-50"
                color="orange"
                @click="goSignUpPage"
                label="회원 가입"
              />
            </div>
          </q-card-actions>
        </q-form>
      </q-card>
    </div>
  </q-layout>
</template>

<style scoped>
.d-flex {
  display: flex;
}

.justify-center {
  justify-content: center;
}

.align-center {
  align-items: center;
}

.vh-100 {
  height: 100vh;
}

.card-container {
  min-width: 600px;
  margin: auto;
}
</style>
