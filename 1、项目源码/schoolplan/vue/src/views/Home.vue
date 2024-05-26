<template>
  <div style="color: #666;font-size: 14px;">
    <div style="padding-bottom: 20px">
      <b>欢迎你！{{ user.nickname }}</b>
    </div>
    <el-card>
      关于我们 &emsp;|| &emsp;项目团队
      <el-divider />
      <div style="display: flex">
        <div style=" height: 203px; width: 400px; display: inline-block">
          <img src="../assets/aboutus.png" alt="" style="width: 150px; height: 200px; position: relative; ">
        </div>
        <div style="width: 400px; margin-top: 30px">
          <div style="
            font-size: 1.5em;
            font-weight: bold;
            color: rgba(109,183,35,0.4);">
            &emsp14;&emsp14;&emsp14;&emsp14;&emsp14;&emsp14;&emsp14;&emsp14;&emsp14;&emsp14;&emsp14;&emsp14;加入我们！
          </div>
          <div>&emsp;&emsp;&emsp13;我们是湖北大学G-05小组</div>
          <div>感谢学校老师和软通动力老师的指导!</div>
          <div style="margin-top: 10px">团队成员：王一帆，余汉林，刘培霖，童年</div>
          <div>夏乐军，吴璟昕，王恩睿，陈品源</div>
        </div>
        <div>
          <div class="block" style=" margin-left: 40%; width: 400px; margin-top: 70px">
            <span class="demonstration" >给我们的系统评价一下吧！</span>
            <el-rate v-model="value2" :colors="colors">
            </el-rate>
          </div>
        </div>
      </div>

    </el-card>
    <div style="height: 1px; background: #ddd; margin: 20px 0"/>

    <el-row :gutter="30">
      <el-col :span="12">
        <div style="padding: 20px 0; font-size: 20px">本系统志于解决</div>
        <div>
          如果您觉得本系统对您有用，请在左上方点击五星好评，下面是有关链接：
        </div>
        <div class="m-10"><el-button type="primary"><i class="el-icon-s-custom"></i>如果有改进的建议，请联系QQ：1669349808</el-button></div>
        <div class="m-10">
          <el-button type="primary"><i class="el-icon-link"></i> <a style="color: #fff" href="https://gitee.com/kennywang01/schoolplan">Gitee源码地址</a></el-button>
        </div>
      </el-col>
      <el-col :span="12">
        <div style="padding: 20px 0; font-size: 20px">技术栈</div>
        <el-row>
          <el-col :span="6" style="line-height: 30px">
            <div><b>后端</b></div>
            <div>SpringBoot</div>
            <div>Hutool</div>
            <div>Poi</div>
            <div>Lombok</div>
            <div>Mybatis/Mybatis-plus</div>
          </el-col>
          <el-col :span="6" style="line-height: 30px">
            <b>前端</b>
            <div>Vue</div>
            <div>Vue-Router</div>
            <div>VueX</div>
            <div>ElementUI</div>
            <div>Axios</div>
          </el-col>
          <el-col :span="6" style="line-height: 30px">
            <b>数据库/数据处理</b>
            <div>MySQL</div>
            <div>Hadoop</div>
            <div>MapReduce</div>
          </el-col>
          <el-col :span="6" style="line-height: 30px">
            <b>部署/测试/运维</b>
            <div>Nginx</div>
            <div>Jemter</div>
            <div>Jenkins</div>
          </el-col>
        </el-row>
      </el-col>
    </el-row>

  </div>
</template>

<script>
export default {
  name: "Home",
  data() {
    return {
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
      value2: null,
      colors: ['#99A9BF', '#F7BA2A', '#FF9900']  // 等同于 { 2: '#99A9BF', 4: { value: '#F7BA2A', excluded: true }, 5: '#FF9900' }
    }
  }
}
</script>

<style>


</style>