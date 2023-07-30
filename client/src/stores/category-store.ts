import { defineStore } from 'pinia'
import { FIND_ALL_CATEGORY } from 'src/graphql/category'
import { useQuery } from 'villus'

export const useCategoryStore = defineStore('category', () => {
  const findAllCategory = async () => {
    try {
      const { data } = await useQuery({
        query: FIND_ALL_CATEGORY,
      })
      console.log(data?.value)
    } catch (e) {}
  }
  const state = {}
  const action = { findAllCategory }

  return { ...state, ...action }
})
