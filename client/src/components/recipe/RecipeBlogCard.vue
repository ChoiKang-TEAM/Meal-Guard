<script lang="ts">
import { defineComponent, ref } from 'vue'

export default defineComponent({
  setup() {
    const pageIndex = ref<number>(1)

    const state = {}
    const action = {}
    return {
      ...state,
      ...action,
    }
  },
})
</script>

<template>
  <q-card>dd </q-card>
</template>
