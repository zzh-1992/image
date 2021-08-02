// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.

import './style/style.css'
import './style/logincss.css'


import VueResource from 'vue-resource'
// 引入vue
import Vue from 'vue'
// 引入App
import App from "./App.vue";
import VueRouter from "vue-router";

// 引入路由
import router from './router'

Vue.use(VueResource);
// 应用插件
Vue.use(VueRouter);

// 关闭Vue的生产提示
Vue.config.productionTip = false

// 创建Vue
new Vue({
  el: '#app',
  render: h => h(App),
  router: router
})

