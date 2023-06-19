import { ErrorValidationEntity } from '@/entity/ErrorValidationEntity'
import { Message } from 'view-ui-plus'

export class MessageUtils {
  handlerError (error: ErrorValidationEntity) {
    const message: string | undefined = error.error
      ?.map(value => value.field + ' : ' + value.message)
      .join('\n')
    Message.error(message)
  }
}

export default new MessageUtils()
