<template>
  <div>
    <div style="margin: 10px 0">
      <el-input style="width: 200px" placeholder="请输入学校名称" suffix-icon="el-icon-search" v-model="school"></el-input>
<!--      <el-input style="width: 200px" placeholder="请输入学科选择" suffix-icon="el-icon-search" class="ml-5" v-model="subType"></el-input>-->
      <el-select v-model="subType" placeholder="请选择" class="ml-5">
        <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value">
        </el-option>
      </el-select>
      <el-input style="width: 200px" placeholder="请输入省份名称" suffix-icon="el-icon-search" class="ml-5" v-model="city"></el-input>
      <el-input style="width: 200px" placeholder="请输入专业大类" suffix-icon="el-icon-search" class="ml-5" v-model="profess"></el-input>
      <el-button class="ml-5" type="primary" @click="load">搜索</el-button>
      <el-button type="warning" @click="reset">重置</el-button>
    </div>

    <div style="margin: 10px 0">
      <el-button type="primary" @click="exp" class="ml-5">导出 <i class="el-icon-top"></i></el-button>
    </div>

    <el-table :data="tableData" border stripe :header-cell-class-name="'headerBg'"  @selection-change="handleSelectionChange">
      <el-table-column prop="sid" label="学校编号" width="80"></el-table-column>
      <el-table-column prop="school" label="学校名称" width="140"></el-table-column>
      <el-table-column prop="cityCode" label="省份代号"></el-table-column>
      <el-table-column prop="city" label="省份名称"></el-table-column>
      <el-table-column prop="subType" label="文理科/1(文科) 2(理科)"></el-table-column>
      <el-table-column prop="majorCode" label="专业代码"></el-table-column>
      <el-table-column prop="profess" label="专业名称"></el-table-column>
      <el-table-column prop="plan" label="计划招生数"></el-table-column>
<!--      <el-table-column label="操作"  width="500" align="center">-->
<!--        <template slot-scope="scope" v-if="user.role !== 'ROLE_ROOT'">-->
<!--          &lt;!&ndash;          <el-button type="primary" @click="lookCourse(scope.row.courses)" v-if="scope.row.role === 'ROLE_ADMIN'">查看教授课程 <i class="el-icon-document"></i></el-button>&ndash;&gt;-->
<!--          &lt;!&ndash;          <el-button type="warning" @click="lookStuCourse(scope.row.stuCourses)" v-if="scope.row.role === 'ROLE_USER'">查看已选课程 <i class="el-icon-document"></i></el-button>&ndash;&gt;-->
<!--          <el-button type="success" @click="handleEdit(scope.row)" v-if="user.role !== 'ROLE_ROOT'">编辑 <i class="el-icon-edit"></i></el-button>-->
<!--          <el-popconfirm-->
<!--              class="ml-5"-->
<!--              confirm-button-text='确定'-->
<!--              cancel-button-text='我再想想'-->
<!--              icon="el-icon-info"-->
<!--              icon-color="red"-->
<!--              title="您确定删除吗？"-->
<!--              @confirm="del(user.id)"-->
<!--          >-->
<!--            <el-button type="danger" slot="reference" v-if="user.role !=='ROLE_ROOT'">删除 <i class="el-icon-remove-outline"></i></el-button>-->
<!--          </el-popconfirm>-->
<!--        </template>-->
<!--      </el-table-column>-->
    </el-table>
    <div style="padding: 10px 0">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="pageNum"
          :page-sizes="[2, 5, 10, 20]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
      </el-pagination>
    </div>

    <el-dialog title="用户信息" :visible.sync="dialogFormVisible" width="30%" >
      <el-form label-width="80px" size="small">
        <el-form-item label="用户名">
          <el-input v-model="form.username" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="角色">
          <el-select clearable v-model="form.role" placeholder="请选择角色" style="width: 100%">
            <el-option v-for="item in roles" :key="item.name" :label="item.name" :value="item.flag"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="昵称">
          <el-input v-model="form.nickname" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="邮箱">
          <el-input v-model="form.email" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="电话">
          <el-input v-model="form.phone" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="地址">
          <el-input v-model="form.address" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="save">确 定</el-button>
      </div>
    </el-dialog>


  </div>
</template>

<script>
import {serverIp} from "../../public/config";

export default {
  name: "User",
  data() {
    return {
      serverIp: serverIp,
      tableData: [],
      total: 0,
      pageNum: 1,
      pageSize: 10,
      sid: "",
      school: "",
      city_code: "",
      city:"",
      profess:"",
      subType:"",
      major_code:"",
      plan:"",
      options: [{
        value: '1',
        label: '1(文科)'
      }, {
        value: '2',
        label: '2(理科)'
      }],
      form: {},
      dialogFormVisible: false,
      multipleSelection: [],
      roles: [],
      vis: false,
      stuVis: false,
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
    }
  },

  methods: {
    reset() {
      this.sid= "",
      this.school= "",
      this.city_code= "",
      this.city= "",
      this.profess= "",
      this.subType= "",
      this.major_code= "",
      this.plan= ""
    },
    exp() {
      window.open(`http://${serverIp}:9090/school/export`)
    },
    created() {
      this.load()
    },
    load() {
      this.request.get("/school/page", {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          sid: this.sid,
          school: this.school ,
          city_code: this.city_code,
          city: this.city,
          profess: this.profess,
          subType: this.subType,
          major_code:this.major_code,
          plan: this.plan,
        }
      }).then(res => {
        this.tableData = res.data.records
        this.total = res.data.total
      })
    },
    save() {
      this.request.post("/school", this.form).then(res => {
        if (res.code === '200') {
          this.$message.success("保存成功")
          this.dialogFormVisible = false
          this.load()
        } else {
          this.$message.error("保存失败")
        }
      })
    },
    handleAdd() {
      this.dialogFormVisible = true
      this.form = {}
    },
    handleEdit(row) {
      this.form = JSON.parse(JSON.stringify(row))
      this.dialogFormVisible = true
    },
    del(id) {
      this.request.delete("/school/" + id).then(res => {
        if (res.code === '200') {
          this.$message.success("删除成功")
          this.load()
        } else {
          this.$message.error("删除失败")
        }
      })
    },
    handleSelectionChange(val) {
      console.log(val)
      this.multipleSelection = val
    },
    handleSizeChange(pageSize) {
      console.log(pageSize)
      this.pageSize = pageSize
      this.load()
    },
    handleCurrentChange(pageNum) {
      console.log(pageNum)
      this.pageNum = pageNum
      this.load()
    },
  }
}
</script>


<style>
.headerBg {
  background: #eee!important;
}
</style>
