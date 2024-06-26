<template>
  <!-- 全国门禁详情 -->
  <div class="device-data-preview">
    <!-- 左侧全国设备状态 -->
    <div class="device-state left-layout">
      <div class="online-rate">
        <p class="content-title">招生计划文理科比例</p>
        <div class="device-chart">
          <dv-active-ring-chart :config="deviceSateConfig" style="width:100%; height:200px" />
        </div>
        <div class="footer">
          <ul>
            <li v-for="item in doorStates" :key="item.id" :class="[{'d-online': item.id === 'd-online'}, {'d-off': item.id === 'd-off'}]">
              <div class="number-scroll">
                <span :class="[{'flop-figure': !isNaN(ls)}, {'flop-comma': isNaN(ls)}]" v-for="(ls, index) in item.valueArr" :key="item.id + index">
                  <i v-if="!isNaN(ls)">0123456789</i>
                  <span v-else>{{ls}}</span>
                </span>
              </div>
              <div>{{item.name}}</div>
            </li>
          </ul>
        </div>
      </div>
      <!-- 数量排行 -->
      <div ref="scrollTable" class="quantity-ranking">
        <p class="content-title">招生最多(最火)专业Top10</p>
        <ScrollTable class="scroll-table" :config="deviceConfig" />
      </div>
    </div>
    <!-- 右侧设备占比 -->
    <div class="data-proportion right-layout">
      <div class="real-time-Warning" :style="{'height': $parent.fullscreen ? height : '315px'}">
        <p class="content-title">招生最少(最冷门)专业Top10</p>
        <ScrollTable v-if="isShow" class="scroll-table" :config="deviceWarningConfig" />
      </div>
      <div class="proportion-type" :style="{'height': $parent.fullscreen ? height : '250px'}">
        <p class="content-title">理科热门招生专业</p>
        <div>
          <div id="proportionType" style="height: 230px"></div>
        </div>
      </div>
      <div class="model-type" :style="{'height': $parent.fullscreen ? height : '250px'}">
        <p class="content-title">文科热门招生专业</p>
        <div>
          <div id="modelType" style="height: 230px"></div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { deviceStateData, deviceSortData, deviceWarningData, deviceTypeData, deviceAdvertiseModelData } from '../../../utils/jsonData'
// 自定义排名轮播
import ScrollTable from '@/components/ScrollTable/src/index'
export default {
  name: 'DeviceDataPreview',
  components: { ScrollTable },
  props: {
    riseImage: {
      type: String,
    },
    declineImage: {
      type: String,
    },
    height: {
      type: String,
      default: ''
    },
    fullscreen: {
      type: Boolean,
      default: false
    }
  },
  data () {
    return {
      timer: null,
      onlineTimer: null,
      isShow: true,
      pointTimer: true,
      allUserNumCount: '',
      onLineDoorControl: '',
      offLineDoorControl: '',
      bubbleData: {
        cityCode: '',
        cityName: '',
        proportion: '',
        communityCount: '',
        onlineCount: '',
        offlineCount: ''
      },
      doorStates: [],
      cityInfoList: [],
      deviceSateConfig: {},
      deviceConfig: {},
      deviceWarningConfig: {}
    }
  },
  watch: {
    height () {
      this.initDeviceConfig()
      this.initDeviceWarning()
    }
  },
  mounted () {
    this.initDeviceSateConfig()
    this.initDeviceConfig()
    this.initDeviceProportionType()
    this.initDeviceModelType()
    this.initDeviceWarning()
  },
  methods: {
    // 设备状态
    initDeviceSateConfig () {
      if (deviceStateData.code !== 0) return
      clearInterval(this.onlineTimer)
      this.onlineTimer = null
      this.doorStates = []
      this.doorStates.push({
        id: 'd-online',
        name: '文科',
        value: this.$parent.formatter(deviceStateData.data.onLineDoorControl),
        valueArr: this.$parent.formatter(deviceStateData.data.onLineDoorControl).split('')
      }, {
        id: 'd-off',
        name: '理科',
        value: this.$parent.formatter(deviceStateData.data.offLineDoorControl),
        valueArr: this.$parent.formatter(deviceStateData.data.offLineDoorControl).split('')
      })
      this.$parent.timedRefresh(this.doorStates, 'device')
      let data = [{
        name: '文科比率',
        value: deviceStateData.data.onLineDoorControl
      }, {
        name: '理科比率',
        value: deviceStateData.data.offLineDoorControl
      }]
      this.deviceSateConfig = {
        radius: '60%',
        activeRadius: '65%',
        color: ['#41A5FF', '#FF6A00'],
        data: data,
        activeTimeGap: 3500,
        digitalFlopStyle: {
          fontSize: 22,
        }
      }
    },
    // 设备排行
    initDeviceConfig () {
      const rowNum = Math.floor((this.$refs.scrollTable.offsetHeight - 50) / 42)
      if (deviceSortData.code !== 0) return
      if (!deviceSortData.data.cityDoorControlVOList || !deviceSortData.data.cityDoorControlVOList.length || deviceSortData.data.cityDoorControlVOList.length < 1) return
      let deviceConfigData = []
      deviceSortData.data.cityDoorControlVOList.sort((a, b) => {
        return b.iotCount - a.iotCount
      })
      deviceSortData.data.cityDoorControlVOList.forEach((t, index) => {
        let className = ''
        index < 3 ? className = 'indexY' : className = 'indexG'
        deviceConfigData.push(
          [`<span class="index ${className}">${index + 1}</span>`, `<span style="color:rgba(255, 255, 255, 0.7);font-size: 13px">${t.cityName}</span>`,
            `<span style="color:#ffffff; font-size: 16px">${this.$parent.formatter(t.iotCount)}</span>`]
        )
      })
      this.deviceConfig = {
        header: ['排行', '专业名称', '计划招生人数'],
        data: deviceConfigData,
        rowNum: rowNum,
        waitTime: 2000,
        indexHeader: '排行',
        headerBGC: 'rgba(0,0,0,0)',
        columnWidth: [90, 150, 110]
      }
    },
    // 实时预警
    initDeviceWarning () {
      if (deviceWarningData.code !== 0) return
      let deviceWarningConfigData = []
      deviceWarningData.data.realTimeWarningVOList && deviceWarningData.data.realTimeWarningVOList.length && deviceWarningData.data.realTimeWarningVOList.length > 0 && deviceWarningData.data.realTimeWarningVOList.forEach((t, index) => {
        if (index < 15) {
          deviceWarningConfigData.push(
            [ `<span style="color:#4A90E2;">${t.communtiyName}</span>`, `<span style="color:#ccc;">${t.doorControlName} </span>`, `<span style="color:#ccc;">${t.realTime} </span>`]
          )
        }
      })
      this.deviceWarningConfig = {
        header: [ '排行', '专业', '计划招生人数'],
        data: deviceWarningConfigData,
        rowNum: 6,
        waitTime: 2000,
        indexHeader: '排行',
        headerBGC: 'rgba(0,0,0,0)',
        columnWidth: [100, 150, 120,]
      }
    },
    // 类型占比
    initDeviceProportionType () {
      if (deviceTypeData.code !== 0) return
      let optionData = [
        {value: 59552, name: '土木工程'},
        {value: 56182, name: '电气工程及其自动化'},
        {value: 52613, name: '计算机科学与技术'},
        {value: 51284, name: '临床医学'},
        {value: 50670, name: '机械设计制造及其自动化'},
        {value: 39354, name: '工程造价'},
        {value: 37009, name: '电子信息工程'},
        {value: 36201, name: '软件工程'},
        {value: 34155, name: '自动化'},
        {value: 33665, name: '电子商务'},
        {value: 2631566, name: '其他专业'}
      ]
      let myChart = this.$echarts.init(document.getElementById('proportionType'))
      let option = {
        tooltip: {
          trigger: 'item',
          backgroundColor: 'rgba(74, 144, 226, 0.84)',
          formatter: (params) => {
            return `<div>${params.seriesName} <br> ${params.data.name}：${this.$parent.formatter(params.data.value)} (${params.percent}%)</div>`
          }
        },
        legend: {
          orient: 'vertical',
          left: 310,
          bottom: 20,
          textStyle: {
            color: '#999',
            padding: [3, 0, 0, 3]
          },
          itemWidth: 10,
          itemHeight: 10,
          selectedMode: false,
          icon: 'circle',
          data: ['土木工程', '电气工程及其自动化', '计算机科学与技术', '临床医学', '机械设计制造及其自动化', '工程造价', '电子信息工程', '软件工程','自动化','电子商务' ]
        },
        series: [
          {
            name: '理科热门专业及比例',
            type: 'pie',
            radius: '55%',
            left: '-30%',
            top: -60,
            center: ['50%', '60%'],
            itemStyle:{
              borderWidth:2,
              borderColor:'rgba(16,16,21,0.4)',
              normal : {
                label : {
                  show : false
                },
                labelLine : {
                  show : false
                }
              }
            },
            color: ['#5AD8A6', '#5D7092', '#F6BD16', '#E8684A', '#6DC8EC', '#9270CA'],
            data: optionData
          }
        ]
      }
      myChart.setOption(option)
      this.$LoopShowTooltip(myChart, option, {loopSeries: true, interval: 4000})
    },
    // 人脸广告机
    initDeviceModelType () {
      if (deviceAdvertiseModelData.code !== 0) return
      let optionData = [
        {value:  43872|| 0, name: '市场营销'},
        {value:  42636|| 0, name: '财务管理'},
        {value:  40490|| 0, name: '会计学'},
        {value:  40411|| 0, name: '会计'},
        {value:  37905|| 0, name: '法学'},
        {value:  37161|| 0, name: '英语'},
        {value:  35269|| 0, name: '国际经济与贸易'},
        {value:  32679|| 0, name: '物流管理'},
        {value:  31681|| 0, name: '电子商务'},
        {value:  31270|| 0, name: '旅游管理'},
        {value:  1157979|| 0, name: '其他专业'}
      ]
      let myChart = this.$echarts.init(document.getElementById('modelType'))
      let option = {
        tooltip: {
          trigger: 'item',
          backgroundColor: 'rgba(74, 144, 226, 0.84)',
          formatter: (params) => {
            return `<div>${params.seriesName} <br> ${params.data.name}：${this.$parent.formatter(params.data.value)} (${params.percent}%)</div>`
          }
        },
        legend: {
          orient: 'vertical',
          left: 310,
          bottom: 20,
          textStyle: {
            color: '#999',
            padding: [2, 0, 0, 3]
          },
          itemWidth: 10,
          itemHeight: 10,
          icon: 'circle',
          selectedMode: false,
          data: ['市场营销', '财务管理', '会计学', '会计', '法学', '英语', '国际经济与贸易', '物流管理', '电子商务', '旅游管理', '其他专业']
        },
        series: [
          {
            name: '文科热门专业及其比例',
            type: 'pie',
            left: '-30%',
            radius: ['50%', '70%'],
            avoidLabelOverlap: false,
            label: {
              show: false,
              position: 'center'
            },
            labelLine: {
              show: true
            },
            itemStyle:{
              borderWidth:2,
              borderColor:'rgba(16,16,21,0.4)',
            },
            emphasis: {
              label: {
                color: '#4a90e2',
                show: true,
                fontSize: '14',
                lineHeight: 22,
                formatter: '{d}% \n {b}'
              },
            },
            color: ['#5B8FF9', '#5AD8A6', '#5D7092', '#F6BD16', '#E8684A', '#6DC8EC', '#9270CA'],
            data: optionData
          }
        ]
      }
      myChart.setOption(option)
      this.$LoopShowTooltip(myChart, option, {loopSeries: true, interval: 4000})
    }
  }
}
</script>
