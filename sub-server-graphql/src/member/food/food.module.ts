import { Module } from '@nestjs/common'
import { FoodService } from './food.service'
import { FoodResolver } from './food.resolver'
import { PrismaModule } from 'src/shared/prisma/prisma.module'

@Module({
  imports: [PrismaModule],
  providers: [FoodService, FoodResolver]
})
export class FoodModule {}
