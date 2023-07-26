import { Module } from '@nestjs/common'
import { FoodService } from './food.service'
import { FoodResolver } from './food.resolver'

@Module({
  providers: [FoodService, FoodResolver]
})
export class FoodModule {}
