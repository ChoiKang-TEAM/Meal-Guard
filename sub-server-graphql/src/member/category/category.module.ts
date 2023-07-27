import { Module } from '@nestjs/common'
import { CategoryService } from './category.service'
import { CategoryResolver } from './category.resolver'
import { PrismaModule } from 'src/shared/prisma/prisma.module'

@Module({
  providers: [CategoryService, CategoryResolver],
  imports: [PrismaModule]
})
export class CategoryModule {}
