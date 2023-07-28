import { InputType, Field } from '@nestjs/graphql'

@InputType()
export class FindFilterFoodInput {
  @Field(() => Number, { nullable: false })
  readonly categoryId: number
}

@InputType()
export class CreateFoodInput {
  @Field(() => String, { nullable: false })
  readonly name: string

  @Field(() => Number, { nullable: false })
  readonly categoryId: number
}
