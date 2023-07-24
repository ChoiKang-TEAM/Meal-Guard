import { defineStore } from 'pinia'
import { GET_DAUM_BLOG_API, SAMPLE_QUERY } from 'src/graphql/google-img-search'
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

  const searchKewordByDaumBlog = async (keyword: string) => {
    const { data } = await useQuery({
      query: GET_DAUM_BLOG_API,
      variables: { keyword: keyword },
      cachePolicy: 'network-only',
    })
    return data?.value?.getDaumBlogApi
  }
  const state = {}
  const action = { searchUrl, searchKewordByDaumBlog }

  return { ...state, ...action }
})
