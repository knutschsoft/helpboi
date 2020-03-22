import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import vuetify from './plugins/vuetify';
import Storage from 'vue-web-storage';

Vue.config.productionTip = false

Vue.use(Storage, {
  prefix: 'helpboi-store-',
  drivers: ['local', 'session'],
});

import "@/assets/global.css"

new Vue({
  router,
  store,
  vuetify,
  render: h => h(App)
}).$mount('#app')
