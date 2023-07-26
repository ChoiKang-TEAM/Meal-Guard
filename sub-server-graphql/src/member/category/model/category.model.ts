import { Field, Int, ObjectType } from '@nestjs/graphql'
import { Food } from 'src/member/food/model/food.model'

@ObjectType()
export class Category {
  @Field(() => Int)
  readonly id: number

  @Field(() => String)
  readonly type: string

  @Field(() => String)
  readonly name: string

  @Field(() => [Food], { nullable: true })
  readonly foods?: Food[]
}
