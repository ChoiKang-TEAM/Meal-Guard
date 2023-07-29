import { Resolver, Query, Args } from '@nestjs/graphql'
import { FoodService } from './food.service'
import { Food } from './model/food.model'
import { FindFilterFoodInput } from './dto/food.input'

@Resolver()
export class FoodResolver {
  constructor(private readonly foodService: FoodService) {}

  @Query(() => [Food])
  async findFoodByFilter(@Args('findFilterFoodInput') findFilterFoodInput: FindFilterFoodInput): Promise<Food[]> {
    return this.foodService.findByFilter(findFilterFoodInput)
  }
}
