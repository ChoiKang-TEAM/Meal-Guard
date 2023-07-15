import { Injectable } from '@nestjs/common'
import axios from 'axios'

@Injectable()
export class SearchImgService {
  async sampleQuery(keyword: string): Promise<string> {
    const Access_Key = process.env.UNSPLASH_ACCESS_KEY
    const url = `https://openapi.naver.com/v1/search/image`
    try {
      const { data } = await axios.get(url, {
        headers: {
          'Content-Type': 'application/json',
          'X-Naver-Client-Id': 'TojrHxgNpXmUA8f78mOU',
          'X-Naver-Client-Secret': 'CDe8KaDUJw'
        },
        params: {
          query: keyword,
          filter: 'small',
          display: 1
        }
      })
      // const data = await (await fetch(url, {
      //   headers:}

      // })).json()
      console.log(data?.items?.[0].thumbnail)

      return data?.items?.[0].thumbnail
    } catch (e) {
      console.error(e)
    }
  }
}
