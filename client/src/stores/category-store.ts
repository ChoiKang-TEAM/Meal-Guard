import { defineStore } from 'pinia'
import { CategoryFindModel } from 'src/common/models'
import { FIND_ALL_CATEGORY } from 'src/graphql/category'
import { useQuery } from 'villus'
import { ref } from 'vue'

export const useCategoryStore = defineStore('category', () => {
  const categoryData = ref<CategoryFindModel[]>([])
  const findAllCategory = async () => {
    try {
      const { data } = await useQuery({
        query: FIND_ALL_CATEGORY,
        cachePolicy: 'network-only',
      })
      categoryData.value = data?.value?.findAllCategory ?? []
    } catch (e) {
      console.error(e)
    }
  }
  const state = { categoryData }
  const action = { findAllCategory }

  return { ...state, ...action }
})
