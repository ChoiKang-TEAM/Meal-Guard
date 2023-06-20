import { Injectable } from '@nestjs/common'

@Injectable()
export class SearchImgService {
  async sampleQuery(keyword: string): Promise<string> {
    const Access_Key = process.env.UNSPLASH_ACCESS_KEY
    const url = `https://openapi.naver.com/v1/search/image.json?query=${keyword}&display=1`
    try {
      const data = await fetch(url, {
        headers: {
          'X-Naver-Client-Id': 'TojrHxgNpXmUA8f78mOU',
          'X-Naver-Client-Secret': 'CDe8KaDUJw'
        }
      })
      // const data = await (await fetch(url, {
      //   headers:}

      // })).json()
      const result = await data.json()

      return result?.items?.[0]?.link
    } catch (e) {
      console.error(e)
    }
  }
}
