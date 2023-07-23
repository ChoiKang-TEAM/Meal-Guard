import { defineStore } from 'pinia'
import { ref } from 'vue'
import { AgeType } from 'src/common/models'

export const useStatusStore = defineStore('status', () => {
  const backGroundImageUrl = ref<string>('')

  const getAgeGroup = (age: number): AgeType => {
    if (age <= 18) return 'TEENAGER'
    else if (age <= 29) return 'YOUTH'
    else if (age <= 49) return 'MIDDLEAGE'
    else return 'SENIOR'
  }

  const state = { backGroundImageUrl }
  const action = { getAgeGroup }

  return { ...state, ...action }
})
