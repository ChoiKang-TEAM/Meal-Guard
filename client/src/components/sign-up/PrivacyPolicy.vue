<script lang="ts">
import { useQuasar } from 'quasar'
import { useAuthStore } from 'src/stores/auth-store'
import { defineComponent, onMounted, ref } from 'vue'
import { PRIVACY_POLICY_LIST } from 'src/common/constants'
import { useField } from 'vee-validate'
import * as yup from 'yup'
import { storeToRefs } from 'pinia'

export default defineComponent({
  setup() {
    const authStore = useAuthStore()
    const { signUpInputData } = authStore
    const { formStepNumber } = storeToRefs(authStore)
    const isAllChecked = ref<boolean>(false)

    onMounted(() => {
      updateIsAllChecked()
    })

    const validatePolicyList = PRIVACY_POLICY_LIST.filter(
      (val) => val.required
    ).map((val) => val.val)

    const {
      value: selected,
      meta,
      errorMessage,
    } = useField<string[]>(
      'selected',
      yup
        .array()
        .of(yup.string())
        .test('has-policies', '필수 항목을 체크해주세요.', (value: any) =>
          validatePolicyList.every((policy: string) => value.includes(policy))
        )
        .required(),
      { initialValue: signUpInputData.get('privacy-policy') ?? [] }
    )

    const updateIsAllChecked = () => {
      isAllChecked.value = selected.value.length === PRIVACY_POLICY_LIST.length
    }

    const selectAllItems = () => {
      selected.value = isAllChecked.value
        ? PRIVACY_POLICY_LIST.map((item) => item.val)
        : []
    }

    const goNextStep = () => {
      signUpInputData.set('privacy-policy', [...selected.value])
      formStepNumber.value += 1
    }

    const state = {
      selected,
      isAllChecked,
      PRIVACY_POLICY_LIST,
      meta,
      errorMessage,
    }
    const action = { selectAllItems, updateIsAllChecked, goNextStep }
    return {
      ...state,
      ...action,
    }
  },
})
</script>

<template>
  <q-card-section class="text-black"> 개인 정보 방침 </q-card-section>
  <q-card-section class="text-black">
    <q-list>
      <q-item tag="label" v-ripple>
        <q-item-section avatar>
          <q-checkbox
            v-model="isAllChecked"
            color="positive"
            checked-icon="task_alt"
            unchecked-icon="highlight_off"
            @click="selectAllItems()"
            label="전체 동의"
          />
        </q-item-section>
      </q-item>

      <q-item
        v-for="(data, index) in PRIVACY_POLICY_LIST"
        :key="index"
        tag="label"
        v-ripple
      >
        <q-item-section avatar>
          <q-checkbox
            v-model="selected"
            :val="data.val"
            color="positive"
            checked-icon="task_alt"
            unchecked-icon="highlight_off"
            @click="updateIsAllChecked()"
            :class="data.required ? 'essential-checkbox' : 'optional-checkbox'"
            :label="data.title"
          />
        </q-item-section>
      </q-item>
      <span class="text-red">{{ errorMessage }}</span>
    </q-list>
  </q-card-section>
  <q-card-actions>
    <q-btn
      flat
      :disable="!meta.valid"
      label="다음으로"
      :class="meta.valid ? 'bg-teal' : 'bg-grey'"
      @click="goNextStep"
    />
  </q-card-actions>
</template>
