import { InputType, Field } from '@nestjs/graphql'

@InputType()
export class FindFilterFoodInput {
  @Field(() => Number, { nullable: false })
  readonly categoryId: number
}
