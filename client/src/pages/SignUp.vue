<script lang="ts">
import { SignUpSteps } from 'src/common/models'
import { useCategoryStore } from 'src/stores/category-store'
import { defineComponent, onMounted, ref } from 'vue'
import { SIGN_UP_QSTEP_LIST } from 'src/common/constants'
import HeaderLayout from 'src/layouts/HeaderLayout.vue'
import InformationEnter from 'src/components/sign-up/InformationEnter.vue'
import PrivacyPolicy from 'src/components/sign-up/PrivacyPolicy.vue'
import IdentityVerify from 'src/components/sign-up/IdentityVerify.vue'
import { useAuthStore } from 'src/stores/auth-store'
import { storeToRefs } from 'pinia'

export default defineComponent({
  components: { HeaderLayout, InformationEnter, PrivacyPolicy, IdentityVerify },
  setup() {
    const categoryStore = useCategoryStore()
    const authStore = useAuthStore()
    const { findAllCategory } = categoryStore
    const { formSteps, formStepNumber } = storeToRefs(authStore)
    const userId = ref<string>('')
    const password = ref<string>('')
    const userList = ref<any>()
    const isPwd = ref<boolean>(true)
    const qStepList = ref<SignUpSteps[]>([])

    onMounted(() => {
      findAllCategory()
      console.log(SIGN_UP_QSTEP_LIST)
      qStepList.value = JSON.parse(JSON.stringify(SIGN_UP_QSTEP_LIST))
    })

    const isStepValid = (stepNumber: number) => {
      return formSteps.value[`step-${stepNumber}`]
    }

    const state = {
      userList,
      userId,
      password,
      isPwd,
      qStepList,
      formStepNumber,
    }
    const action = { isStepValid }
    return {
      ...state,
      ...action,
    }
  },
})
</script>
<template>
  <q-layout view="lHh Lpr lFf">
    <header-layout>
      <template #expand>
        <q-toolbar-title class="toolbar-title">
          <div class="text-center">MEAL-GUARD</div>
        </q-toolbar-title></template
      >
      <q-card flat class="card-container">
        <q-card-section>
          <h2 class="text-h6 text-black">회원가입</h2>
          <q-stepper
            v-model="formStepNumber"
            done-color="amber-5"
            active-color="amber-5"
            inactive-color="grey"
            animated
          >
            <q-step
              v-for="(data, index) in qStepList"
              :key="index"
              :name="index + 1"
              :title="data.title"
              :icon="data.icon"
              :caption="data.caption ?? ''"
              :done="formStepNumber > index + 1"
            >
              <privacy-policy v-if="index === 0" />
              <identity-verify v-if="index === 1" />
              <information-enter v-if="index === 2" />

              <!-- <q-stepper-navigation>
                <q-btn
                  flat
                  :class="isStepValid(index + 1) ? 'bg-teal' : 'bg-grey'"
                  @click="step = index + 2"
                  label="다음으로"
                  :disable="!isStepValid(index + 1)"
                />
                <q-btn
                  v-if="index > 0"
                  flat
                  @click="step = index"
                  color="primary"
                  label="Back"
                  class="q-ml-sm"
                />
              </q-stepper-navigation> -->
            </q-step>
          </q-stepper>
        </q-card-section>
      </q-card>
    </header-layout>
  </q-layout>
</template>

<style scoped></style>
