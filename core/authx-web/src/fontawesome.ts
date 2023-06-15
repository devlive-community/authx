import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'
import { library } from '@fortawesome/fontawesome-svg-core'
import { faHome, faRightToBracket, faUserPlus, faUser } from '@fortawesome/free-solid-svg-icons'

const createIcons = (app: any) => {
  library.add(faHome, faRightToBracket, faUserPlus, faUser)
  app.component('font-awesome-icon', FontAwesomeIcon)
}

export {
  createIcons
}
