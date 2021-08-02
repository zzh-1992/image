
<template>
  <div>
    <h2 >this is about </h2>

  </div>
</template>
<script>
export default {
  name:"About",
  beforeDestroy() {
    console.log("About 组件即将被销毁")
  },
  mounted() {
    console.log("About 组件挂载完毕")
  },
}
</script>
<style scoped>
</style>
