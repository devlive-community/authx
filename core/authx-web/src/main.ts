import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import './style.css'
import ViewUIPlus from 'view-ui-plus'
import 'view-ui-plus/dist/styles/viewuiplus.css'
import { createIcons } from '@/fontawesome'

const app = createApp(App)
// 注册 fontawesome
createIcons(app)
// 注册 ElementPlus
app.use(ViewUIPlus)
app.use(router)
app.mount('#app')
