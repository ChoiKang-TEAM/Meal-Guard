<script lang="ts">
import { useQuasar } from 'quasar'
import { SignUpMemberUserInput } from 'src/common/models'
import { useAuthStore } from 'src/stores/auth-store'
import { defineComponent, ref } from 'vue'

export default defineComponent({
  setup() {
    const $q = useQuasar()
    const store = useAuthStore()
    const { signUpMemberUser } = store
    const userId = ref<string>('')
    const password = ref<string>('')
    const userList = ref<any>()

    const memberSingUp = async () => {
      try {
        const signUpMemberUserInput: SignUpMemberUserInput = {
          userId: userId.value,
          password: password.value,
        }
        const { data, error } = await signUpMemberUser({
          signUpMemberUserInput: signUpMemberUserInput,
        })
        if (error) {
        } else return $q
      } catch (e) {
        console.error(e)
      }
    }

    const state = { userList, userId, password }
    const action = { memberSingUp }
    return {
      ...state,
      ...action,
    }
  },
})
</script>
<template>
  <q-card class="card-container">
    <q-card-section>
      <h2 class="text-h6">
        회원가입
        <q-btn rounded flat icon="close" v-close-popup style="float: right" />
      </h2>
    </q-card-section>

    <q-form @submit="memberSingUp">
      <q-card-section>
        <q-input rounded outlined v-model="userId" label="아이디" type="text" />
      </q-card-section>
      <q-card-section>
        <q-input
          rounded
          outlined
          v-model="password"
          label="비밀번호"
          type="password"
        />
      </q-card-section>

      <q-card-section>
        <q-btn
          rounded
          outline
          class="w-100"
          color="primary"
          type="submit"
          label="가입하기"
        />
      </q-card-section>
      <q-card-section> <q-separator inset /> </q-card-section>
      <q-card-actions>
        <div class="w-100">
          <q-btn flat class="w-100" color="orange" label="닫기" v-close-popup />
        </div>
      </q-card-actions>

      <!-- <q-card-section>
              <q-btn rounded outline color="primary" label="회원가입" />
            </q-card-section> -->
    </q-form>
  </q-card>
</template>

<style scoped></style>
