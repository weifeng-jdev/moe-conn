<script>
import NavBar from "./components/NavBar.vue";
import Carousel from "./components/Carousel.vue";

export default {
  components: {Carousel, NavBar}
}
</script>

<template>
  <div class="common-layout">
    <el-container>
      <el-header>
        <nav-bar></nav-bar>
      </el-header>
      <el-main>
        <carousel></carousel>
      </el-main>
      <el-footer>Footer</el-footer>
    </el-container>
  </div>
</template>

<style scoped>

</style>
