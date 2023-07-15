import { Module } from '@nestjs/common'
import { ApolloDriver, ApolloDriverConfig } from '@nestjs/apollo'
import { GraphQLModule } from '@nestjs/graphql'
import { AppService } from './app.service'
import { SearchImgModule } from './shared/public-api/search/search-img.module'
import { PrismaModule } from './shared/prisma/prisma.module'
import { join } from 'path'
import { UserModule } from './member/user/user.module'
import { WinstonModule } from 'nest-winston'
import { transports, format } from 'winston'
import { AuthModule } from './shared/auth/auth.module'

@Module({
  imports: [
    GraphQLModule.forRoot<ApolloDriverConfig>({
      driver: ApolloDriver,
      autoSchemaFile: join(process.cwd(), 'src/schema.gql')
    }),
    WinstonModule.forRoot({
      level: 'info', // 로그 레벨을 설정합니다. (예: 'info', 'debug', 'error' 등)
      format: format.combine(format.timestamp(), format.json()),
      transports: [
        new transports.Console(), // 콘솔에 로그를 출력합니다.
        new transports.File({ filename: 'combined.log' }) // 파일에 로그를 출력합니다.
      ]
    }),
    SearchImgModule,
    PrismaModule,
    UserModule,
    AuthModule
  ],
  controllers: [],
  providers: [AppService]
})
export class AppModule {}
