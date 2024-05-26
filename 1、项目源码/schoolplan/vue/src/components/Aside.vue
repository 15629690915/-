<template>
  <el-menu :default-openeds="opens" style="min-height: 100%; overflow-x: hidden; background: linear-gradient(135deg, #FCF5AAFF, #9AF8DAFF)"
           text-color="#006000"
           background-color="#CEFFCE"
           active-text-color="#00EC00"
           :collapse-transition="false"
           :collapse="isCollapse"
           router
  >
    <div style="height: 60px; line-height: 60px; text-align: center">
      <img src="../assets/HubuLogo.png" alt="" style="width: 20px; position: relative; top: 5px;">
      <b style="color: #006000; margin-left: 5px" v-show="logoTextShow">高校招生计划分析系统</b>
    </div>
    <div v-for="item in menus" :key="item.id">
      <div v-if="item.path">
        <el-menu-item :index="item.path">
          <i :class="item.icon"></i>
          <span slot="title">{{ item.name }}</span>
        </el-menu-item>
      </div>
      <div v-else>
        <el-submenu :index="item.id + ''">
          <template slot="title">
            <i :class="item.icon"></i>
            <span slot="title">{{ item.name }}</span>
          </template>
          <div  v-for="subItem in item.children" :key="subItem.id">
            <el-menu-item :index="subItem.path">
              <i :class="subItem.icon"></i>
              <span slot="title">{{ subItem.name }}</span>
            </el-menu-item>
          </div>
        </el-submenu>
      </div>
    </div>
  </el-menu>
</template>

<script>
export default {
  name: "Aside",
  props: {
    isCollapse: Boolean,
    logoTextShow: Boolean
  },
  data() {
    return {
      menus: localStorage.getItem("menus") ? JSON.parse(localStorage.getItem("menus")) : [],
      opens: localStorage.getItem("menus") ? JSON.parse(localStorage.getItem("menus")).map(v => v.id + '') : []
    }
  },
}
</script>

<style>
.el-menu-item.is-active {
  background: linear-gradient(135deg, #FCF5AAFF, #9AF8DAFF) !important;
  /*background: linear-gradient(135deg, #da0447, #5494f1) !important;*/
}
.el-menu-item:hover {
  background: linear-gradient(135deg, #FCF5AAFF, #9AF8DAFF) !important;
  /*background: linear-gradient(135deg, #da0447, #5494f1) !important;*/
}

.el-submenu__title:hover {
  background: linear-gradient(135deg, #FCF5AAFF, #9AF8DAFF) !important;
  /*background: linear-gradient(135deg, #da0447, #5494f1) !important;*/
}
/*解决收缩菜单文字不消失问题*/
.el-menu--collapse span {
  visibility: hidden;
}
</style>
