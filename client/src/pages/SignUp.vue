<script lang="ts">
import { SignUpSteps } from 'src/common/models'
import { useCategoryStore } from 'src/stores/category-store'
import { defineComponent, onMounted, ref } from 'vue'
import { SIGN_UP_QSTEP_LIST } from 'src/common/constants'
import HeaderLayout from 'src/layouts/HeaderLayout.vue'
import InformationEnter from 'src/components/sign-up/InformationEnter.vue'

export default defineComponent({
  components: { HeaderLayout, InformationEnter },
  setup() {
    const categoryStore = useCategoryStore()

    const { findAllCategory } = categoryStore
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

    const state = {
      userList,
      userId,
      password,
      isPwd,
      qStepList,
      step: ref(1),
    }
    const action = {}
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
            v-model="step"
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
              :done="step > index + 1"
            >
              <information-enter></information-enter>

              <q-stepper-navigation>
                <q-btn
                  :disable="data.require"
                  :color="data.require ? 'grey' : 'amber'"
                  @click="step = index + 2"
                  label="Continue"
                />
                <q-btn
                  v-if="index > 0"
                  flat
                  @click="step = index"
                  color="primary"
                  label="Back"
                  class="q-ml-sm"
                />
              </q-stepper-navigation>
            </q-step>
          </q-stepper>
        </q-card-section>
      </q-card>
    </header-layout>
  </q-layout>
</template>

<style scoped></style>
