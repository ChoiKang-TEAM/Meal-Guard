import { defineStore } from 'pinia'
import { CategoryFindModel } from 'src/common/models'
import { FIND_ALL_CATEGORY } from 'src/graphql/category'
import { useQuery } from 'villus'
import { ref } from 'vue'

export const useCategoryStore = defineStore('category', () => {
  const categoryTab = ref<string[]>([])
  const findAllCategory = async () => {
    try {
      const { data } = await useQuery({
        query: FIND_ALL_CATEGORY,
      })
      const items: CategoryFindModel[] = data?.value?.findAllCategory ?? []
      for (const item of items) {
        categoryTab.value.push(item.type)
      }
    } catch (e) {
      console.error(e)
    }
  }
  const state = { categoryTab }
  const action = { findAllCategory }

  return { ...state, ...action }
})
