export class UserEntity {
  username = ''
  password = ''
  // 转换 JWT
  token?: string | undefined
  type?: string | undefined
  id?: number
}
