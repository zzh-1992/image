<template>

  <b-navbar type="dark" variant="dark">
    <b-navbar-nav>
      <b-nav-item v-on:click="home">Home</b-nav-item>
      <b-nav-item v-on:click="blog">Blog</b-nav-item>
      <b-nav-item v-on:click="technicalstandard">TechnicalStandard</b-nav-item>
      <b-nav-item v-on:click="video">Video</b-nav-item>
      <b-nav-item v-on:click="userList">UserList</b-nav-item>
      <b-nav-item v-on:click="article">Article</b-nav-item>

      <b-nav-item v-on:click="example">Example</b-nav-item>

      <b-nav-item-dropdown
        id="my-nav-dropdown"
        text="User"
        toggle-class="nav-link-custom" style="background: transparent"
        right
      >
        <b-dropdown-item @click="userProfile">UserProfile</b-dropdown-item>
        <b-dropdown-item>Login</b-dropdown-item>
        <b-dropdown-item @click="logout">Logout</b-dropdown-item>
      </b-nav-item-dropdown>
    </b-navbar-nav>
  </b-navbar>
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
    logout() {
      // 清除本地缓存
      localStorage.clear()
      // 回到注册登陆页面
      this.$router.push('/');
    },
    home() {
      this.$router.push('/homepage/home');
    },
    userList() {
      this.$router.push('/homepage/userList');
    },
    blog() {
      this.$router.push('/homepage/blog');
    },
    technicalstandard() {
      this.$router.push('/homepage/technicalstandard');
    },
    video() {
      this.$router.push('/homepage/video');
    },// userProfile
    article() {
      //直接跳转
      this.$router.push('/homepage/article');
      //带参数跳转
      //this.$router.push({path:'/testDemo',query:{setid:123456}});
      //this.$router.push({name:'testDemo',params:{setid:111222}});
    },
    example() {
      this.$router.push('/homepage/example');
    },

    userProfile() {
      this.$router.push('/homepage/userProfile');
    }
  }
}
</script>
<style scoped>

body {
  min-height: 115vh;
  min-height: -webkit-fill-available;
}

html {
  height: -webkit-fill-available;
}

main {
  display: flex;
  flex-wrap: nowrap;
  height: 100vh;
  height: -webkit-fill-available;
  max-height: 100vh;
  overflow-x: auto;
  overflow-y: hidden;
}

.nav-flush .nav-link {
  border-radius: 0;
}

.btn-toggle-nav a {
  display: inline-flex;
  padding: .1875rem .5rem;
  margin-top: .125rem;
  margin-left: 1.25rem;
  text-decoration: none;
}

.btn-toggle-nav a:hover,
.btn-toggle-nav a:focus {
  background-color: #d2f4ea;
}
</style>
