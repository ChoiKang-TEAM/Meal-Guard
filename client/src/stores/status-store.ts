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

  const groupArrayIntoObjects = <T>(arr: T[]): { data: T[] }[] => {
    const groupedObjects = Array.from(
      { length: Math.ceil(arr.length / 3) },
      (_, index) => ({
        data: arr.slice(index * 3, index * 3 + 3),
      })
    )
    return groupedObjects
  }

  const state = { backGroundImageUrl }
  const action = { getAgeGroup, groupArrayIntoObjects }

  return { ...state, ...action }
})
