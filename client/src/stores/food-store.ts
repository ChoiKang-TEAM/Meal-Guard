import { defineStore } from 'pinia'
import { api } from 'boot/axios'
import { ref } from 'vue'
import { useGoogleImgSotre } from './google-img-store'

export const useFoodStore = defineStore('food', () => {
  const temp = ref()
  const rotateData = ref()
  const randomFoodData = ref()
  const recipeData = ref()
  const recipeTotalPage = ref<number>(0)
  const store = useGoogleImgSotre()
  const { searchUrl } = store

  const getCurrentWeather = async (
    currentLongitude = 126.9312417,
    currentLatitude = 37.59996944
  ) => {
    const { data } = await api.get('/api/weather', {
      headers: {
        'Content-Type': 'application/json',
        'ngrok-skip-browser-warning': '69420',
      },
      params: {
        currentLongitude: currentLongitude,
        currentLatitude: currentLatitude,
      },
    })
    temp.value = data?.weather?.temp
    rotateData.value = data
    await getRandomFoodByWeather(temp.value)
  }

  const getRandomFoodByWeather = async (temp: number) => {
    const result = await api.get('/foods/temp', {
      headers: {
        'Content-Type': 'application / json',
        'ngrok-skip-browser-warning': '69420',
      },
      params: {
        temp: temp,
      },
    })
    randomFoodData.value = result
  }

  const getRecipeData = async (dto: {
    page: number
    size: number
    name: string | null
  }) => {
    const result = await api.get('/recipes/search', {
      headers: {
        'Content-Type': 'application / json',
        'ngrok-skip-browser-warning': '69420',
      },
      params: {
        size: dto.size,
        page: dto.page,
        name: dto.name,
      },
    })
    recipeData.value = await Promise.all(
      result?.data?.data.map(async (v: any) => {
        const foodUrlImage = await searchUrl(v.name)
        return { ...v, foodUrlImage }
      })
    )

    recipeTotalPage.value = result?.data?.pageInfo?.totalPages
  }

  const state = { rotateData, randomFoodData, recipeData, recipeTotalPage }
  const action = { getCurrentWeather, getRandomFoodByWeather, getRecipeData }

  return { ...state, ...action }
})
