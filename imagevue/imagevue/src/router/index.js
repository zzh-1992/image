// 该文件专门用于创建整个应用容器的路由器
import VueRouter from 'vue-router'

// 引入组件
import HomePage from "../layout/HomePage";
import Signup from "../layout/Signup";

import Home from "../components/Home";
import Blog from "../components/Blog";
import TechnicalStandard from "../components/TechnicalStandard";
import Video from "../components/Video";
import Article from "../components/Article";

// 创建并暴露一个路由器
const router =  new VueRouter({
  routes: [
    {
      path: '/homepage',
      redirect: '/homepage/home',
      component: HomePage, children: [
        {
          path: 'home',
          component: Home
        },
        {
          path: 'blog',
          component: Blog
        },
        {
          path: 'technicalstandard',
          component: TechnicalStandard
        },
        {
          path: 'video',
          component: Video
        },
        {
          path: 'article',
          component: Article
        },
      ]
    },
    {
      path: '/',
      component: Signup
    },
  ]
})

// 全局前置守卫
router.beforeEach(function (to, from, next){
  if(to.path === '/'){
    // 注册登陆页面直接放行
    next();
  }else {
    const token = localStorage.getItem('token')
    // 后台页面
    if(token){
      // 有登陆或注册成功，后端返回token，允许跳转后台
      next();
    }else {
      // 无token，回到注册登陆页面
      next('/');
    }
  }
})

export default router;

