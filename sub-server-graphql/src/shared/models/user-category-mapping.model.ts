import { Field, Int, ObjectType } from '@nestjs/graphql'
import { Category } from 'src/member/category/model/category.model'
import { User } from 'src/member/user/model/user.model'

@ObjectType()
export class UserCategoryMapping {
  @Field(() => Int)
  readonly id: number

  @Field(() => Int)
  readonly userId: number

  @Field(() => Int)
  readonly categoryId: number

  @Field(() => User, { nullable: true })
  readonly user?: User

  @Field(() => Category)
  readonly category: Category
}
