import { Field, Int, ObjectType } from '@nestjs/graphql'
import { GenderType } from '@prisma/client'
import { UserFoodMapping } from 'src/shared/models/user-food-mapping.model'

@ObjectType()
export class User {
  @Field(() => Int)
  readonly id: number

  @Field(() => String)
  readonly userId: string

  @Field(() => String)
  readonly password: string

  @Field(() => String)
  readonly userSeq: string

  @Field(() => GenderType)
  readonly gender: GenderType

  @Field(() => Number)
  readonly age: number

  @Field(() => [UserFoodMapping])
  readonly preferredFoods: UserFoodMapping[]
}
