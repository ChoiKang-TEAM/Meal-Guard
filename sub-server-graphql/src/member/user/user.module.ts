import { Module } from '@nestjs/common'
import { UserService } from './user.service'
import { UserResolver } from './user.resolver'
import { PrismaModule } from 'src/shared/prisma/prisma.module'

@Module({
  providers: [UserService, UserResolver],
  imports: [PrismaModule],
  exports: [UserService]
})
export class UserModule {}
