import { Field, Int, ObjectType } from '@nestjs/graphql'

@ObjectType()
export class User {
  @Field(() => Int)
  readonly id: number

  @Field(() => String)
  readonly userId: string

  @Field(() => String)
  readonly password: string
}
