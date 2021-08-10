<template>
  <!-- 所有的内容要被根节点包含起来 -->
  <div class="cotn_principal">
    <!--导航栏-->
    <Navbar/>
    <br>

    <!--内容区-->
    <div class="container">
      <router-view></router-view>
    </div>

    <!--底部栏-->
    <Footer/>
  </div>
</template>

<script>

import Navbar from "../components/Navbar";
import Footer from "../components/Footer";

export default {
  components: {Footer, Navbar},
  methods: {}
}
</script>

<style lang="css" scoped>
</style>
