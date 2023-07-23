import { Field, Int, ObjectType } from '@nestjs/graphql'
import { GenderType } from '@prisma/client'

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
}
