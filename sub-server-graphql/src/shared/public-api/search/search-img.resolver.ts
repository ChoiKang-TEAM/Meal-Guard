import { Args, Query, Resolver } from '@nestjs/graphql'
import { SearchImgService } from './search-img.service'
import { DocumentByDaumBlog } from './model/search.model'

@Resolver()
export class SearchImgResolver {
  constructor(private readonly searchImgService: SearchImgService) {}

  @Query(() => String)
  async sampleQuery(@Args('keyword') keyword: string): Promise<string> {
    return this.searchImgService.sampleQuery(keyword)
  }

  @Query(() => [DocumentByDaumBlog])
  async getDaumBlogApi(@Args('keyword') keyword: string): Promise<DocumentByDaumBlog[]> {
    return this.searchImgService.getDaumBlogApi(keyword)
  }
}
