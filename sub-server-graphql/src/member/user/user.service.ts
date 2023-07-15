import { Injectable, Inject } from '@nestjs/common'
import { CrudService } from 'src/shared/interfaces/factory.interface'
import { User } from './model/user.model'
import { PrismaService } from 'src/shared/prisma/prisma.service'
import { WINSTON_MODULE_PROVIDER } from 'nest-winston'
import { Logger } from 'winston'
import * as bcrypt from 'bcrypt'
import { FindMemberUserByUserId, SignUpMemberUserInput } from './dto/user.input'

@Injectable()
export class UserService implements CrudService<User> {
  constructor(private readonly prisma: PrismaService, @Inject(WINSTON_MODULE_PROVIDER) private readonly logger: Logger) {}
  async create(dto: SignUpMemberUserInput): Promise<boolean> {
    try {
      const saltRound = 10
      const salt = await bcrypt.genSalt(saltRound)
      const hasshedPassword = await bcrypt.hash(dto.password, salt)

      await this.prisma.user.create({
        data: {
          userId: dto.userId,
          password: hasshedPassword,
          name: dto.name
        }
      })
      return true
    } catch (e) {
      this.logger.error(e)
      return false
    }
  }
  delete(dto: User): Promise<boolean> {
    throw new Error('Method not implemented.')
  }
  update(dto: User): Promise<boolean> {
    throw new Error('Method not implemented.')
  }
  async findUnique(dto: FindMemberUserByUserId): Promise<User> {
    try {
      return await this.prisma.user.findUnique({
        where: {
          userId: dto.userId
        }
      })
    } catch (e) {
      this.logger.error(e)
    }
  }
  findAll(): Promise<User[]> {
    throw new Error('Method not implemented.')
  }
  findByFilter(dto: User): Promise<User[]> {
    throw new Error('Method not implemented.')
  }
}
