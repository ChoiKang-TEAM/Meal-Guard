import { Field, Int, ObjectType } from '@nestjs/graphql'
import { Food } from 'src/member/food/model/food.model'
import { User } from 'src/member/user/model/user.model'

@ObjectType()
export class UserFoodMapping {
  @Field(() => Int)
  readonly id: number

  @Field(() => Int)
  readonly userId: number

  @Field(() => Int)
  readonly foodId: number

  @Field(() => User, { nullable: true })
  readonly user?: User

  @Field(() => Food)
  readonly food: Food
}
