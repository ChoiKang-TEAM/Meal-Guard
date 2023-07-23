import { InputType, Field } from '@nestjs/graphql'
import { GenderType } from '@prisma/client'

@InputType()
export class LoginMemberUserInput {
  @Field(() => String, { nullable: false })
  readonly userId: string

  @Field(() => String, { nullable: false })
  readonly password: string
}

@InputType()
export class FindMemberUserByUserId {
  @Field(() => String, { nullable: false })
  readonly userId: string
}

@InputType()
export class SignUpMemberUserInput {
  @Field(() => String, { nullable: false })
  readonly userId: string

  @Field(() => String, { nullable: false })
  readonly password: string

  @Field(() => String, { nullable: true })
  readonly name?: string

  @Field(() => GenderType, { nullable: false })
  readonly gender: GenderType

  @Field(() => Number, { nullable: false })
  readonly age: number
}

@InputType()
export class UpdateMemberUserInput {
  @Field(() => String, { nullable: false })
  readonly userId: string

  @Field(() => String, { nullable: true })
  readonly password?: string
}

@InputType()
export class DeleteMemberUserInput {
  @Field(() => String, { nullable: false })
  readonly userId: string
}
