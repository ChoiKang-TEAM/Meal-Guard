import { InputType, Field } from '@nestjs/graphql'
import { GenderType } from '@prisma/client'

@InputType()
export class LoginMemberUserInput {
  @Field(() => String, { nullable: false })
  readonly userId: string

  @Field(() => String, { nullable: false })
  readonly password: string
}
