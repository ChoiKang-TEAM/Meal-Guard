import { Module } from '@nestjs/common'
import { UserService } from './user.service'
import { UserResolver } from './user.resolver'
import { PrismaModule } from 'src/shared/prisma/prisma.module'
import { registerEnumType } from '@nestjs/graphql'
import { GenderType } from '@prisma/client'

@Module({
  providers: [UserService, UserResolver],
  imports: [PrismaModule],
  exports: [UserService]
})
export class UserModule {
  constructor() {
    registerEnumType(GenderType, {
      name: 'GenderType' // GraphQL 스키마에서 사용될 Enum 이름
    })
  }
}
