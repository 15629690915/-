<template>
  <div>
    <el-row :gutter="10" style="margin-bottom: 60px">
      <el-col :span="6">
        <el-card style="color: #409EFF">
          <div><i class="el-icon-user-solid" />统计高校计划招生总人数</div>
          <div style="padding: 10px 0; text-align: center; font-weight: bold">
            574978人
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card style="color: #F56C6C">
          <div><i class="el-icon-set-up" />统计招生学科</div>
          <div style="padding: 10px 0; text-align: center; font-weight: bold">
            1153种
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card style="color: #67C23A">
          <div><i class="el-icon-bank-card" />统计高校</div>
          <div style="padding: 10px 0; text-align: center; font-weight: bold">
            1755所
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card style="color: #E6A23C">
          <div><i class="el-icon-s-shop" />985/211类高校</div>
          <div style="padding: 10px 0; text-align: center; font-weight: bold">
            116所
          </div>
        </el-card>
      </el-col>
    </el-row>
    <el-row style="display: flex">
      <el-col :span="12">
        <div id="lineChart" style="width: 400px; height: 450px"></div>
      </el-col>
      <el-col >
        <el-button style=" margin-left: 40%; font-size: 25px; margin-top: 20%" icon="el-icon-s-platform" circle type="goon"
                   @click="$router.push('/bigScreen')" >
        </el-button>
        <div style="height: 20%" >
          <i class="el-icon-thumb" style="font-size: 25px; margin-left: 45%; margin-top: 7%"></i>
        </div>
        <span class="Pycharm_color" style="margin-left: 13%; margin-top: 35%; font-size: 25px">点击大屏查看更多可视化内容</span>

      </el-col>
      <el-col :span="10">
        <div id="Pie" style="width: 500px; height: 400px"></div>
      </el-col>
    </el-row>

  </div>
</template>

<script>
import * as echarts from 'echarts'

export default {
  name: "Pics",
  data() {
    return {
    }
  },
  methods: {
    drawLineChart(id){
      this.$echarts.init(document.getElementById(id)).dispose(); //初始化echarts之前先手动销毁之前的echarts图，防止显示错乱
      let lineChart = this.$echarts.init(document.getElementById(id)); //初始化echarts
// 添加配置项
      lineChart.setOption(
          {
            tooltip: {
              trigger: 'axis',
              axisPointer: {
                type: 'cross',
                crossStyle: {
                  color: '#999'
                }
              }
            },
            toolbox: {
              feature: {
                magicType: { show: true, type: ['line', 'bar'] },
                restore: { show: true },
                saveAsImage: { show: true }
              }
            },
            legend: {
              data: ['录取人数', '报考人数', '录取率'],
              left: "left"
            },
            xAxis: [
              {
                type: 'category',
                data: ['2016', '2017', '2018', '2019', '2020'],
                axisPointer: {
                  type: 'shadow'
                }
              }
            ],
            yAxis: [
              {
                type: 'value',
                name: '人数',
                min: 600,
                max: 1071,
                interval: 100,
                axisLabel: {
                  formatter: '{value} 万人'
                }
              },
              {
                type: 'value',
                name: '录取率',
                min: 60,
                max: 90.34,
                interval: 5,
                axisLabel: {
                  formatter: '{value} %'
                }
              }
            ],
            series: [
              {
                name: '录取人数',
                type: 'bar',
                tooltip: {
                  valueFormatter: function (value) {
                    return value + ' 万人';
                  }
                },
                data: [
                  705, 700, 791, 820, 967.5
                ]
              },
              {
                name: '报考人数',
                type: 'bar',
                tooltip: {
                  valueFormatter: function (value) {
                    return value + ' 万人';
                  }
                },
                data: [
                  940, 940, 975, 1031, 1071
                ]
              },
              {
                name: '录取率',
                type: 'line',
                yAxisIndex: 1,
                tooltip: {
                  valueFormatter: function (value) {
                    return value + ' %';
                  }
                },
                data: [75, 74.46, 81.13, 79.53, 90.34]
              }
            ]
          },
      );
    },
    drawPie(id) {
      this.$echarts.init(document.getElementById(id)).dispose(); //初始化echarts之前先手动销毁之前的echarts图，防止显示错乱
      let Pie = this.$echarts.init(document.getElementById(id)); //初始化echarts
// 添加配置项
      Pie.setOption(
          {
            title: {
              text: '统计高校中985/211高校的占比',
              left: 'center'
            },
            tooltip: {
              trigger: 'item'
            },
            legend: {
              orient: 'vertical',
              left: 'left'
            },
            series: [
              {
                name: 'Access From',
                type: 'pie',
                radius: '50%',
                data: [
                  {value: 39, name: '985高校'},
                  {value: 112, name: '211高校'},
                  {value: 1604, name: '其他高校'},
                ],
                emphasis: {
                  itemStyle: {
                    shadowBlur: 10,
                    shadowOffsetX: 0,
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                  }
                }
              }
            ]
          },
      );
    },
  },
    mounted() {
      this.drawLineChart('lineChart')
      this.drawPie('Pie')
    }
}
</script>

<style scoped>
.el-button--goon {
  color: #FFF;
  background-color: #1E90FF;
  border-color: #1E90FF;
  height: 70px;
  width: 70px;
  line-height: 34px;
  padding: 0 20px;
}

</style>
