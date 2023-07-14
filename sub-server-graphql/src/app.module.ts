import { Module } from '@nestjs/common'
import { ApolloDriver, ApolloDriverConfig } from '@nestjs/apollo'
import { GraphQLModule } from '@nestjs/graphql'
import { AppService } from './app.service'
import { SearchImgModule } from './shared/public-api/search/search-img.module'
import { PrismaModule } from './shared/prisma/prisma.module'
import { join } from 'path'
import { UserModule } from './member/user/user.module'
import { WinstonModule } from 'nest-winston'

@Module({
  imports: [
    GraphQLModule.forRoot<ApolloDriverConfig>({
      driver: ApolloDriver,
      autoSchemaFile: join(process.cwd(), 'src/schema.gql')
    }),
    WinstonModule.forRoot({}),
    SearchImgModule,
    PrismaModule,
    UserModule
  ],
  controllers: [],
  providers: [AppService]
})
export class AppModule {}
