import { defineStore } from 'pinia'
import { ref } from 'vue'

export const useStatusStore = defineStore('status', () => {
  const backGroundImageUrl = ref<string>('')

  const state = { backGroundImageUrl }
  const action = {}

  return { ...state, ...action }
})
