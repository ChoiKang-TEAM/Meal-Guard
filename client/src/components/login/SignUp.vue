<script lang="ts">
import { useQuasar } from 'quasar'
import { SignUpMemberUserInput } from 'src/common/models'
import { useAuthStore } from 'src/stores/auth-store'
import { useCategoryStore } from 'src/stores/category-store'
import { defineComponent, onMounted, ref } from 'vue'

export default defineComponent({
  setup() {
    const $q = useQuasar()
    const authStore = useAuthStore()
    const categoryStore = useCategoryStore()
    const { signUpMemberUser } = authStore
    const { findAllCategory } = categoryStore
    const userId = ref<string>('')
    const password = ref<string>('')
    const userList = ref<any>()
    const isPwd = ref<boolean>(true)

    onMounted(() => {
      findAllCategory()
    })

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

    const state = { userList, userId, password, isPwd, step: ref(1) }
    const action = { memberSingUp }
    return {
      ...state,
      ...action,
    }
  },
})
</script>
<template>
  <div class="q-pa-md">
    <q-stepper v-model="step" vertical color="primary" animated>
      <q-step
        :name="1"
        title="Select campaign settings"
        icon="settings"
        :done="step > 1"
      >
        For each ad campaign that you create, you can control how much you're
        willing to spend on clicks and conversions, which networks and
        geographical locations you want your ads to show on, and more.

        <q-stepper-navigation>
          <q-btn @click="step = 2" color="primary" label="Continue" />
        </q-stepper-navigation>
      </q-step>

      <q-step
        :name="2"
        title="Create an ad group"
        caption="Optional"
        icon="create_new_folder"
        :done="step > 2"
      >
        An ad group contains one or more ads which target a shared set of
        keywords.

        <q-stepper-navigation>
          <q-btn @click="step = 4" color="primary" label="Continue" />
          <q-btn
            flat
            @click="step = 1"
            color="primary"
            label="Back"
            class="q-ml-sm"
          />
        </q-stepper-navigation>
      </q-step>

      <q-step :name="3" title="Ad template" icon="assignment" disable>
        This step won't show up because it is disabled.
      </q-step>

      <q-step :name="4" title="Create an ad" icon="add_comment">
        Try out different ad text to see what brings in the most customers, and
        learn how to enhance your ads using features like ad extensions. If you
        run into any problems with your ads, find out how to tell if they're
        running and how to resolve approval issues.

        <q-stepper-navigation>
          <q-btn color="primary" label="Finish" />
          <q-btn
            flat
            @click="step = 2"
            color="primary"
            label="Back"
            class="q-ml-sm"
          />
        </q-stepper-navigation>
      </q-step>
    </q-stepper>
  </div>

  <q-card class="card-container">
    <q-card-section>
      <h2 class="text-h6 text-black">
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
