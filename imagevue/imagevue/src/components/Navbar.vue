<template>

  <div>
    <b-nav >
      <b-nav class=" navbar  navbar-inverse ">
        <b-nav-item v-on:click="home">Home</b-nav-item>
        <b-nav-item v-on:click="blog">Blog</b-nav-item>
        <b-nav-item v-on:click="technicalstandard">TechnicalStandard</b-nav-item>
        <b-nav-item v-on:click="video">Video</b-nav-item>
        <b-nav-item v-on:click="article">Article</b-nav-item>


        <b-nav-item-dropdown
          id="my-nav-dropdown"
          text="User"
          toggle-class="nav-link-custom"
          right
        >
          <b-dropdown-item>UserProfile</b-dropdown-item>
          <b-dropdown-item>Login</b-dropdown-item>
          <b-dropdown-item @click="logout">Logout</b-dropdown-item>
        </b-nav-item-dropdown>
      </b-nav>
    </b-nav>
  </div>
</template>
<script>
export default {
  data() {
    console.log("-------")
    return {
      msg: {}
    }
  },
  methods: {
    logout(){
      alert('确定退出吗')
      // 清除token
      localStorage.removeItem('token')
      // 回到注册登陆页面
      this.$router.push('/');
    },
    home(){
      this.$router.push('/homepage/home');
    },
    blog(){
      this.$router.push('/homepage/blog');
    },
    technicalstandard() {
      this.$router.push('/homepage/technicalstandard');
    },
    video() {
      this.$router.push('/homepage/video');
    },
    article() {
      //直接跳转
      this.$router.push('/homepage/article');
      //带参数跳转
      //this.$router.push({path:'/testDemo',query:{setid:123456}});
      //this.$router.push({name:'testDemo',params:{setid:111222}});
    },
  }
}
</script>
<style scoped>
</style>
