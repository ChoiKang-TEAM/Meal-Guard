import { Args, Context, Mutation, Resolver, Query } from '@nestjs/graphql'
import { UserService } from './user.service'
import { FindMemberUserByUserId, SignUpMemberUserInput } from './dto/user.input'
import { User } from './model/user.model'
import { UseGuards } from '@nestjs/common'
import { JwtAuthGuard } from 'src/shared/guard/jwt-auth.guard'
import { BadWordsPipe } from 'src/shared/pipes/language-filter.pipe'

@Resolver()
export class UserResolver {
  constructor(private readonly userService: UserService) {}

  @Mutation(() => Boolean)
  async signUpMemberUser(@Args('signUpMemberUserInput', BadWordsPipe) signUpMemberUserInput: SignUpMemberUserInput, @Context() context: { req: Request }): Promise<boolean> {
    return await this.userService.create(signUpMemberUserInput)
  }

  @UseGuards(JwtAuthGuard)
  @Query(() => User)
  async findUniqueMemberUser(@Args('findMemberUserByUserId') findMemberUserByUserId: FindMemberUserByUserId): Promise<User> {
    return await this.userService.findUnique(findMemberUserByUserId)
  }
}
