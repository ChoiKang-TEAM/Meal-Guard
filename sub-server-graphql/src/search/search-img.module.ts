import { Module } from '@nestjs/common'
import { SearchImgService } from './search-img.service'
import { SearchImgResolver } from './search-img.resolver'

@Module({
  providers: [SearchImgService, SearchImgResolver]
})
export class SearchImgModule {}
