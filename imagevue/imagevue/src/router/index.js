// 该文件专门用于创建整个应用容器的路由器

import VueRouter from 'vue-router'

// 引入组件
import About from "../components/About";
import Home from "../components/Home";
import Signup from "../components/Signup";

// 创建并暴露一个路由器
export default new VueRouter({
  routes: [
    {
      path: '/about',
      component: About
    },
    {
      path: '/home',
      component: Home
    },
    {
      path: '/',
      component: Signup
    },
  ]
})

