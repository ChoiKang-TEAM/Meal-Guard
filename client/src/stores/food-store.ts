import { defineStore } from 'pinia'
import { api } from 'boot/axios'
import { ref } from 'vue'
import { useNaverImgStore } from './naver-img-store'

export const useFoodStore = defineStore('food', () => {
  const temp = ref()
  const rotateData = ref()
  const randomFoodData = ref()
  const recipeData = ref()
  const recipeTotalPage = ref<number>(0)
  const naverImgStore = useNaverImgStore()
  const { searchUrl } = naverImgStore

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

  // TODO: 좋아요 기능
  const favoriteApi = async () => {
    const result = await api.post(`/recipes/${3367}/favorite`)
    console.log(result)
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
    const result = await api.get('/recipes', {
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
    // recipeData.value = await Promise.all(
    //   result?.data?.data.map(async (v: any) => {
    //     const foodUrlImage = await searchUrl(v.name)
    //     return { ...v, foodUrlImage }
    //   })
    // )

    recipeData.value = result?.data?.data

    recipeTotalPage.value = result?.data?.pageInfo?.totalPages
  }

  const getNaverBlogByFoodName = async (food: string) => {
    try {
      const result = await api.get('/naverblog', {
        headers: {
          'Content-Type': 'application / json',
          'ngrok-skip-browser-warning': '69420',
        },
        params: {
          food: food,
        },
      })
      const test = await Promise.all(
        result?.data.map(async (v: any) => {
          const foodUrlImage = await searchUrl(v.title)
          return { ...v, foodUrlImage }
        })
      )
      console.log(test)
    } catch (e) {
      console.error(e)
    }
  }

  const state = {
    rotateData,
    randomFoodData,
    recipeData,
    recipeTotalPage,
  }

  const action = {
    getCurrentWeather,
    getRandomFoodByWeather,
    getRecipeData,
    favoriteApi,
    getNaverBlogByFoodName,
  }

  return { ...state, ...action }
})
