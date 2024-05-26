<template>
  <div class="block">
    <div >
      <span class="demonstration">选择需要预测的高校：</span>
      <div style="margin-top: 20px">
        <el-input
            placeholder="仅限985/211高校"
            v-model="school"
            clearable
            style="font-size: 14px"
        ></el-input>
      </div>
      <div style="margin-top: 20px">
        <span class="demonstration">请输入你的专业方向：</span>
      </div>
      <div style="margin-top: 20px">
        <el-input
            placeholder="请输入文科(1)/理科(2)"
            v-model="subtype"
            clearable
            style="font-size: 14px"
        ></el-input>
      </div>
      <div style="margin-top: 20px">
        <span class="demonstration">请输入你的排位：</span>
      </div>
      <div style="margin-top: 20px">
        <el-input
            placeholder="请输入位次"
            v-model="rank"
            clearable
            style="font-size: 14px"
        ></el-input>
      </div>
      <div>
        <el-button>查询</el-button>
      </div>
    </div>
    <div style="display: flex">
      <span>您的录取概率约为： </span><span>50%</span>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      school:"",
      subtype:"",
      rank:"",
    }
  },
  methods: {
    handleChange(value) {
      console.log(value);
    }
  }
}
</script>

<style >
.el-input--mini .el-input__inner{
  width:190px;
  height:60px
}
</style>
