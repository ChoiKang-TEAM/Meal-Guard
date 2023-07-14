import { Args, Query, Resolver } from '@nestjs/graphql'
import { SearchImgService } from './search-img.service'

@Resolver()
export class SearchImgResolver {
  constructor(private readonly searchImgService: SearchImgService) {}

  @Query(() => String)
  async sampleQuery(@Args('keyword') keyword: string): Promise<string> {
    return this.searchImgService.sampleQuery(keyword)
  }
}
