import { Resolver, Query, Args, Mutation } from '@nestjs/graphql'
import { FoodService } from './food.service'
import { Food } from './model/food.model'
import { CreateFoodInput, FindFilterFoodInput } from './dto/food.input'
import { BadWordsPipe } from 'src/shared/pipes/language-filter.pipe'

@Resolver()
export class FoodResolver {
  constructor(private readonly foodService: FoodService) {}

  @Query(() => [Food])
  async findFoodByFilter(@Args('findFilterFoodInput') findFilterFoodInput: FindFilterFoodInput): Promise<Food[]> {
    return this.foodService.findByFilter(findFilterFoodInput)
  }

  @Mutation(() => Boolean)
  async createFood(@Args('createFoodInput', BadWordsPipe) createFoodInput: CreateFoodInput): Promise<boolean> {
    console.log(1)
    return this.foodService.create(createFoodInput)
  }
}
