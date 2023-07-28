import { Field, Int, ObjectType } from '@nestjs/graphql'
import { Category } from 'src/member/category/model/category.model'
import { UserFoodMapping } from 'src/shared/models/user-food-mapping.model'

@ObjectType()
export class Food {
  @Field(() => Int)
  readonly id: number

  @Field(() => Int)
  readonly categoryId: number

  @Field(() => String)
  readonly name: string

  @Field(() => Category)
  readonly category?: Category

  @Field(() => Date)
  readonly createdAt: Date

  @Field(() => Date)
  readonly updatedAt: Date

  @Field(() => [UserFoodMapping], { nullable: true })
  readonly users?: UserFoodMapping[]
}
