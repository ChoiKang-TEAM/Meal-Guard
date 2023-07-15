import { Args, Mutation, Resolver } from '@nestjs/graphql'
import { UserService } from './user.service'
import { SignUpMemberUserInput } from './dto/user.input'

@Resolver()
export class UserResolver {
  constructor(private readonly userService: UserService) {}

  @Mutation(() => Boolean)
  async signUpMemberUser(@Args('signUpMemberUserInput') signUpMemberUserInput: SignUpMemberUserInput): Promise<boolean> {
    return await this.userService.create(signUpMemberUserInput)
  }
}
