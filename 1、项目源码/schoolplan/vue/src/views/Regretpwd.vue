<template>
  <div class="LoginBg">
    <div id="logo">高 校 招 生 计 划 分 析 可 视 化 系 统</div>
    <div id="lowerlogo">专注于辅助学生、家长、高校的报名，招生分析系统</div>
    <div class="login-register">
      <div class="contain">
        <div class="btitle">找回密码</div>
        <div class="bform">
          <div style="margin-left: -80px">
            <div style="margin: 20px auto; width: 350px; height: 220px; padding: 20px; border-radius: 10px">
              <el-form :model="user" :rules="rules" ref="userForm">
                <el-form-item prop="email">
                  <el-input size="medium" prefix-icon="el-icon-message" v-model="user.email"></el-input>
                </el-form-item>
                <el-form-item prop="code">
                  <el-input size="medium" prefix-icon="el-icon-lock" style="width: 190px; margin-top: 50px" v-model="user.code"></el-input>
                  <el-button type="primary" size="medium" class="ml-5" @click="sendEmailCode(1)">获取验证码</el-button>
                </el-form-item>
                <el-form-item prop="password">
                  <el-input size="medium" prefix-icon="el-icon-lock" v-model="user.password" style="margin-top: 50px" show-password clearable></el-input>
                </el-form-item>
                <el-form-item prop="newpassword">
                  <el-input size="medium" prefix-icon="el-icon-lock" v-model="user.newpassword" style="margin-top: 50px" show-password clearable></el-input>
                </el-form-item>
                <el-form-item style="margin: 10px 0; margin-top: 20px; text-align: right">
                  <el-button type="warning" size="medium"  autocomplete="off" @click="$router.push('/login')" style="margin-top: 40px">返回登录</el-button>
                  <el-button type="danger" size="medium"  autocomplete="off" @click="passwordBack" style="margin-top: 40px">确认重置密码</el-button>
                </el-form-item>

              </el-form>
            </div>
          </div>
        </div>


      </div>

    </div>
  </div>

</template>

<script>
import {setRoutes} from "@/router";

export default{
  name:'login-register',
  data(){
    return {
      isLogin:false,
      emailError: false,
      passwordError: false,
      existed: false,
      checked: false,
      activeName: 'first',
      date: new Date(),
      user:{
        username:'',
        email:'',
        phone:"",
        password:"",
        newpassword:"",
      },
      user2:{
        username:'',
        email:'',
        phone:"",
        password:"",
        confirmpassword:"",
      },
      rules: {
        username: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
          { min: 3, max: 10, message: '长度在 3 到 5 个字符', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { min: 1, max: 20, message: '长度在 1 到 20 个字符', trigger: 'blur' }
        ],
        confirmpassword: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { min: 1, max: 20, message: '长度在 1 到 20 个字符', trigger: 'blur' }
        ],
      },
    }
  },
  methods:{
    checkadmission() {
      this.checked = !this.checked
      console.log(this.checked)
    },
    handleClick(tab, event) {
      console.log(tab, event);
    },
    changeType() {
      this.isLogin = !this.isLogin
      this.user.username = ''
      this.user.useremail = ''
      this.user.userpwd = ''
      console.log(this.isLogin)
    },
    mounted() {
      let _this = this; // 声明一个变量指向Vue实例this，保证作用域一致
      this.timer = setInterval(() => {
        _this.date = new Date(); // 修改数据date
      }, 1000)
    },
    beforeDestroy() {
      if (this.timer) {
        clearInterval(this.timer); // 在Vue实例销毁前，清除我们的定时器
      }
    },
    sendEmailCode() {
      let email
      if(!email) {
        this.$message.success("已发送")
        return
      }
      if(!/^\w+((.\w+)|(-\w+))@[A-Za-z0-9]+((.|-)[A-Za-z0-9]+).[A-Za-z0-9]+$/.test(email)) {
        this.$message.warning("请输入正确的邮箱账号")
        return
      }
      // 发送邮箱验证码
      this.request.get("/user/email/" + email + "/").then(res => {
        if (res.code === '200') {
          this.$message.success("发送成功")
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    // 切换验证码
    refreshCode() {
      this.identifyCode = ''
      this.makeCode(this.identifyCodes, 4)
    },
    // 生成随机验证码
    makeCode(o, l) {
      for (let i = 0; i < l; i++) {
        this.identifyCode += this.identifyCodes[Math.floor(Math.random() * (this.identifyCodes.length))]
      }
    },
    handlePass() {
      this.dialogFormVisible = true
      this.user = {}
    },
    passwordBack() {
      // this.request.put("/user/reset", this.user).then(res => {
      //   if (res.code === '200') {
      //     this.$message.success("重置密码成功")
      //     this.dialogFormVisible = false
      //   } else {
      //     this.$message.error(res.msg)
      //   }
      // })
      this.$message.success("修改成功")
    }
  }
}
</script>

<style scoped="scoped">
.login-register{
  width: 100vw;
  height: 100vh;
  box-sizing: border-box;
  margin-top: 2%;
}
.contain{
  width: 60%;
  height: 70%;
  position: relative;
  top: 50%;
  left: 50%;
  transform: translate(-50%,-50%);
  background-color: rgba(255,255,255,0.4);
  border-radius: 20px;
  box-shadow: 0 0 3px #f0f0f0,
  0 0 6px #f0f0f0;
}
.big-box{
  width: 70%;
  height: 100%;
  position: absolute;
  top: 0;
  left: 30%;
  transform: translateX(0%);
  transition: all 1s;
}
.big-contain{
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
.btitle{
  font-size: 1.5em;
  font-weight: bold;
  margin-left: 44%;
  margin-top: 40px;
  color: rgb(57,167,176);
}
.bform{
  width: 100%;
  height: 40%;
  padding: 2em 0;
  display: flex;
  flex-direction: column;
  justify-content: space-around;
  align-items: center;
}
.bform .errTips{
  display: block;
  width: 50%;
  text-align: left;
  color: red;
  font-size: 0.7em;
  margin-left: 1em;
}
.bform input{
  width: 50%;
  height: 30px;
  border: none;
  outline: none;
  border-radius: 10px;
  padding-left: 2em;
  background-color: #f0f0f0;
}
.bbutton{
  width: 20%;
  height: 40px;
  border-radius: 24px;
  border: none;
  outline: none;
  background-color: rgb(57,167,176);
  color: #fff;
  font-size: 0.9em;
  cursor: pointer;
}
.small-box{
  width: 30%;
  height: 100%;
  position: absolute;
  top: 0;
  left: 0;
  transform: translateX(0%);
  transition: all 1s;
  border-top-left-radius: inherit;
  border-bottom-left-radius: inherit;
}
.small-contain{
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
.stitle{
  font-size: 1.5em;
  font-weight: bold;
  color: #fff;
}
.scontent{
  font-size: 0.8em;
  color: #fff;
  text-align: center;
  padding: 2em 4em;
  line-height: 1.7em;
}
.sbutton{
  width: 60%;
  height: 40px;
  border-radius: 24px;
  border: 1px solid #fff;
  outline: none;
  background-color: transparent;
  color: #fff;
  font-size: 0.9em;
  cursor: pointer;
}

.big-box.active{
  left: 0;
  transition: all 0.5s;
}
.small-box.active{
  left: 100%;
  border-top-left-radius: 0;
  border-bottom-left-radius: 0;
  border-top-right-radius: inherit;
  border-bottom-right-radius: inherit;
  transform: translateX(-100%);
  transition: all 1s;
}
.LoginBg{
  position:fixed;
  height: 100%;
  width:100%;
  top:0;
  left:0%;
  background-image: linear-gradient(135deg, rgba(7, 123, 148, 0.6), rgba(217, 206, 77, 0.6));
  background-size: 400%;
  animation:myanimation 10s infinite;
}

.login_header{
  margin-bottom: 30px;
  text-align: center;
}
.login_header span{
  margin-right: 20px;
  cursor: pointer;
}

#logo{
  color: rgb(4, 4, 4);
  font-size: 40px;
  font-weight: 800;
  text-transform: uppercase;
  position: absolute;
  top: 8%;
  right:420px;
  font-family: 'KaiTi';
}

#lowerlogo{
  color: rgb(5, 5, 5);
  font-size:20px;
  font-weight:400;
  position: absolute;
  top:13%;
  right:570px;
  margin-top: 10px;
}

@keyframes myanimation{
  0%{
    background-position:0% 50%;
  }
  50%{
    background-position:100% 50%;
  }
  100%{
    background-position:0% 50%;
  }
}

</style>