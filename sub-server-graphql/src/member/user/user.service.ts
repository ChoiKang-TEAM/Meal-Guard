import { Injectable, Inject } from '@nestjs/common'
import { CrudService } from 'src/shared/interfaces/factory.interface'
import { User } from './model/user.model'
import { PrismaService } from 'src/shared/prisma/prisma.service'
import { WINSTON_MODULE_PROVIDER } from 'nest-winston'
import { Logger } from 'winston'
import * as bcrypt from 'bcrypt'
import { FindMemberUserByUserId, SignUpMemberUserInput } from './dto/user.input'
import { v4 as uuidv4 } from 'uuid'
import axios from 'axios'
import { PrismaClient } from '@prisma/client'

@Injectable()
export class UserService implements CrudService<User> {
  constructor(private readonly prisma: PrismaService, @Inject(WINSTON_MODULE_PROVIDER) private readonly logger: Logger) {}
  async create(dto: SignUpMemberUserInput): Promise<boolean> {
    try {
      const saltRound = 10
      const salt = await bcrypt.genSalt(saltRound)
      const hasshedPassword = await bcrypt.hash(dto.password, salt)
      const userSeq = uuidv4()

      await this.prisma.$transaction(async (tx: PrismaClient) => {
        await tx.user.create({
          data: {
            userId: dto.userId,
            password: hasshedPassword,
            name: dto.name,
            userSeq: userSeq,
            age: dto.age,
            gender: dto.gender,
            preferredFoods: {
              create: {
                foodId: 1
              }
            }
          }
        })
        // const url = 'https://20c1-124-111-225-247.ngrok-free.app/user/create'
        // await axios.post(url, userSeq)
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
      const result = await this.prisma.user.findUnique({
        where: {
          userId: dto.userId
        },
        include: {
          preferredFoods: {
            include: {
              food: {
                include: {
                  category: true
                }
              }
            }
          }
        }
      })

      return result
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
