import { defineStore } from 'pinia'
import { api } from 'boot/axios'
import { ref } from 'vue'

export const useFoodStore = defineStore('food', () => {
  const temp = ref()
  const rotateData = ref()
  const randomFoodData = ref()

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

  const state = { rotateData, randomFoodData }
  const action = { getCurrentWeather, getRandomFoodByWeather }

  return { ...state, ...action }
})
