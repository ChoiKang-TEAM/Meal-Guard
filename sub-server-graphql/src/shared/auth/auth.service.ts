import { Injectable, UnauthorizedException } from '@nestjs/common'
import { JwtService } from '@nestjs/jwt'
import { FindMemberUserByUserId, LoginMemberUserInput } from 'src/member/user/dto/user.input'
import { User } from 'src/member/user/model/user.model'
import { UserService } from 'src/member/user/user.service'
import * as bcrypt from 'bcrypt'

@Injectable()
export class AuthService {
  constructor(private readonly jwtService: JwtService, private readonly userService: UserService) {}

  async createToken(user: User): Promise<string> {
    const payload = { id: user.id, userId: user.userId, userSeq: user.userSeq, gender: user.gender, age: user.age }
    return this.jwtService.sign(payload)
  }

  async validateUser(loginMemberUserInput: LoginMemberUserInput): Promise<User> {
    const userIdDto: FindMemberUserByUserId = {
      userId: loginMemberUserInput.userId
    }
    const user = await this.userService.findUnique(userIdDto)
    if (!user) {
      throw new UnauthorizedException('Invalid userId')
    }
    const isValidPassword = await bcrypt.compare(loginMemberUserInput.password, user.password)
    if (!isValidPassword) {
      throw new UnauthorizedException('Invalid password')
    }
    return user
  }

  async verifyToken(token: string): Promise<any> {
    return this.jwtService.verify(token)
  }
}
