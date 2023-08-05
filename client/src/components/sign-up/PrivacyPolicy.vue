<script lang="ts">
import { useQuasar } from 'quasar'
import { useAuthStore } from 'src/stores/auth-store'
import { computed, defineComponent, ref } from 'vue'
import { PRIVACY_POLICY_LIST } from 'src/common/constants'

export default defineComponent({
  setup() {
    const $q = useQuasar()
    const authStore = useAuthStore()

    const isAllChecked = ref<boolean>(false)
    const selected = ref<string[]>([])

    const updateIsAllChecked = () => {
      isAllChecked.value = selected.value.length === PRIVACY_POLICY_LIST.length
    }

    // selected 배열의 값이 변경되면 isAllChecked도 자동으로 업데이트됩니다.
    const selectAllItems = () => {
      selected.value = isAllChecked.value
        ? PRIVACY_POLICY_LIST.map((item) => item.val)
        : []
    }

    const state = { selected, isAllChecked, PRIVACY_POLICY_LIST }
    const action = { selectAllItems, updateIsAllChecked }
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
          />
        </q-item-section>
        <q-item-section>
          <q-item-label>전체 동의</q-item-label>
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
            :error="true"
          />
        </q-item-section>
        <q-item-section>
          <q-item-label>{{ data.title }}</q-item-label>
        </q-item-section>
      </q-item>
    </q-list>
  </q-card-section>
</template>
