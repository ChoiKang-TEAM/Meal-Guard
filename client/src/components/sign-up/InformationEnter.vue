<script lang="ts">
import { useQuasar } from 'quasar'
import { SignUpMemberUserInput } from 'src/common/models'
import { useAuthStore } from 'src/stores/auth-store'
import { defineComponent, ref } from 'vue'

export default defineComponent({
  setup() {
    const $q = useQuasar()
    const authStore = useAuthStore()
    const { signUpMemberUser } = authStore
    const userId = ref<string>('')
    const password = ref<string>('')
    const userList = ref<any>()
    const isPwd = ref<boolean>(true)

    const memberSingUp = async () => {
      try {
        const signUpMemberUserInput: SignUpMemberUserInput = {
          userId: userId.value,
          password: password.value,
        }
        const { error } = await signUpMemberUser({
          signUpMemberUserInput: signUpMemberUserInput,
        })
        if (error) {
        } else return $q
      } catch (e) {
        console.error(e)
      }
    }

    const state = {
      userList,
      userId,
      password,
      isPwd,
    }
    const action = { memberSingUp }
    return {
      ...state,
      ...action,
    }
  },
})
</script>

<template>
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
        label="가입하기"
      />
    </q-card-section>
  </q-form>
</template>
