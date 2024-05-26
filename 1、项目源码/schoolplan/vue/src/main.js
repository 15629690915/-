import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import './assets/global.css'
import request from "@/utils/request";
import * as echarts from 'echarts';
import './utils/mixins'

// main.js全局注册
import mavonEditor from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'
import {
  loading,
  borderBox8,
  decoration3,
  decoration5,
  decoration10,
  activeRingChart,
} from '@jiaminghi/data-view'
// use
Vue.use(loading)
Vue.use(borderBox8)
Vue.use(decoration3)
Vue.use(decoration5)
Vue.use(decoration10)
Vue.use(activeRingChart)
Vue.use(mavonEditor)

Vue.config.productionTip = false
Vue.prototype.$echarts = echarts

Vue.use(ElementUI, { size: "mini" });

Vue.prototype.request=request

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
