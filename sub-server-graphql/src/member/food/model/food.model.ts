import { Field, Int, ObjectType } from '@nestjs/graphql'
import { Category } from 'src/member/category/model/category.model'

@ObjectType()
export class Food {
  @Field(() => Int)
  readonly id: number

  @Field(() => Int)
  readonly categoryId: number

  @Field(() => String)
  readonly name: string

  @Field(() => Category)
  readonly category: Category
}
