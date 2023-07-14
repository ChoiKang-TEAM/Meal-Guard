import { Injectable, Inject } from '@nestjs/common'
import { CrudService } from 'src/shared/interfaces/factory.interface'
import { User } from './model/user.model'
import { PrismaService } from 'src/shared/prisma/prisma.service'
import { WINSTON_MODULE_PROVIDER } from 'nest-winston'
import { Logger } from 'winston'
import * as bcrypt from 'bcrypt'

@Injectable()
export class UserService implements CrudService<User> {
  constructor(private readonly prisma: PrismaService, @Inject(WINSTON_MODULE_PROVIDER) private readonly logger: Logger) {}
  async create(dto: User): Promise<boolean> {
    try {
      const saltRound = 10
      const salt = await bcrypt.genSalt(saltRound)
      const hasshedPassword = await bcrypt.hash(dto.password, salt)

      await this.prisma.user.create({
        data: {
          userId: dto.userId,
          password: hasshedPassword
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
  findUnique(dto: User): Promise<User> {
    throw new Error('Method not implemented.')
  }
  findAll(): Promise<User[]> {
    throw new Error('Method not implemented.')
  }
  findByFilter(dto: User): Promise<User[]> {
    throw new Error('Method not implemented.')
  }
}
