<template>
  <div style="background-color:#ffffee; height: 100%; width: 100%; position: absolute">
    <img src="src/assets/agreement.png" alt="" style="margin-left: 600px">
    <div style="margin-left: 600px; font-size: 20px; margin-top: 50px">《隐私协议》和《用户使用协议》</div>
    <div style="margin-top: 50px; margin-left: 350px; font-size: 45px"> 只要不干坏事不恶意攻击服务器就是符合协议！ </div>
    <el-button type="primary" style="margin-left: 720px; margin-top: 50px" size="x-big" @click="$router.go(-1)">返回</el-button>

  </div>
</template>

<script>
export default {
  name: "Agreement"
}
</script>

<style scoped>

</style>