import { defineStore } from 'pinia'
import { SAMPLE_QUERY } from 'src/graphql/google-img-search'
import { useQuery } from 'villus'

export const useNaverImgStore = defineStore('naver-img', () => {
  const searchUrl = async (keyword: string) => {
    const { data } = await useQuery({
      query: SAMPLE_QUERY,
      variables: { keyword: keyword },
      cachePolicy: 'network-only',
    })
    return data?.value?.sampleQuery
  }
  const state = {}
  const action = { searchUrl }

  return { ...state, ...action }
})
