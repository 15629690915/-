<template>
  <div class="LoginBg">
    <div id="logo">高 校 招 生 计 划 分 析 可 视 化 系 统</div>
    <div id="lowerlogo">专注于辅助学生、家长、高校的报名，招生分析系统</div>
    <div class="login-register">
      <div class="contain">
        <div class="big-box" :class="{active:isLogin}">

          <div class="big-contain" key="bigContainLogin" v-if="isLogin">
            <div class="btitle">登   录</div>
            <div class="login_header">
            </div>
            <el-tabs v-model="activeName" @tab-click="handleClick" style="margin-top: 5px;">
              <el-tab-pane label="账户登录" name="first" >
                <div class="bigform">
                  <el-form :model="user" ref="userForm">
                    <el-input size="medium" style="margin: 10px 0;" prefix-icon="el-icon-user" v-model="user.username" placeholder="请输入用户名" clearable></el-input>
                    <span class="errTips" v-if="emailError">* 用户名输入有误 *</span>
                    <el-input size="medium" style="margin: 10px 0; margin-top: 10px" prefix-icon="el-icon-lock" v-model="user.password" placeholder="请输入密码" show-password clearable></el-input>
                    <span class="errTips" v-if="emailError">* 密码填写错误 *</span>
                  </el-form>

                  <div style="margin-top: 20px">
                    <el-checkbox v-model="checked" @change="checkadmission">
                      我已阅读并同意 <el-link href="/agreement" type="primary" style="margin-bottom: 3px">《隐私协议》和《用户使用协议》</el-link>
                    </el-checkbox>
                  </div>
                </div>

                <div style="margin-top: 30px">
                  <el-button round type="primary" style="position:relative; margin-left: 30%" size="big" @click="login" :disabled="!checked" >登&nbsp &nbsp录</el-button>
                  <el-button round type="success" style="position:relative; margin-left: 30px" size="big" @click="regretpwd">忘记密码</el-button>
                </div>
              </el-tab-pane>

              <el-tab-pane label="邮箱登录" name="second" >
                <div class="bigform">
                  <el-form :model="user" ref="userForm">
                    <el-input size="medium" style="margin: 10px 0;" prefix-icon="el-icon-message" v-model="user2.email" placeholder="请输入邮箱地址" clearable></el-input>
                    <span class="errTips" v-if="emailError">* 邮箱输入有误 *</span>
                    <el-input size="medium" style="margin: 10px 0; margin-top: 10px" prefix-icon="el-icon-lock" v-model="user2.password" placeholder="请输入密码" show-password clearable></el-input>
                    <span class="errTips" v-if="emailError">* 密码填写错误 *</span>
                  </el-form>

                  <div style="margin-top: 20px">
                    <el-checkbox v-model="checked" @change="checkadmission">
                      我已阅读并同意 <el-link href="/agreement" type="primary" style="margin-bottom: 3px">《隐私协议》和《用户使用协议》</el-link>
                    </el-checkbox>
                  </div>
                </div>

                <div style="margin-top: 30px">
                  <el-button round type="primary" style="position:relative; margin-left: 30%" size="big" @click="login" :disabled="!checked" >登&nbsp &nbsp录</el-button>
                  <el-button round type="success" style="position:relative; margin-left: 30px" size="big" @click="regretpwd">忘记密码</el-button>
                </div>
              </el-tab-pane>
            </el-tabs>
          </div>

          <div class="big-contain" key="bigContainRegister" v-else>
            <div class="btitle" style="margin-top: 20px">创建账户</div>
            <el-form :model="user2" :rules="rules" ref="user2Form" style="width: 465.63px; margin-top: 20px">
              <el-form-item prop="username">
                <el-input size="medium" style="margin: 10px 0;" prefix-icon="el-icon-user" v-model="user2.username" placeholder="请输入用户名" clearable></el-input>
              </el-form-item>
              <span class="errTips" v-if="existed">* 用户名已经存在！ *</span>
              <el-form-item prop="email">
                <el-input size="medium" style="margin: 10px 0;" prefix-icon="el-icon-message" v-model="user2.email" placeholder="请输入邮箱地址" clearable></el-input>
              </el-form-item>
              <span class="errTips" v-if="emailError">* 邮箱输入有误 *</span>
              <el-form-item prop="phone">
                <el-input size="medium" style="margin: 10px 0;" prefix-icon="el-icon-phone" v-model="user2.phone" placeholder="请输入手机号码" clearable></el-input>
              </el-form-item>
              <el-form-item prop="password">
                <el-input size="medium" style="margin: 10px 0;" prefix-icon="el-icon-lock" v-model="user2.password" placeholder="请输入密码" clearable show-password></el-input>
              </el-form-item>
              <el-form-item prop="confirmpassword">
                <el-input size="medium" style="margin: 10px 0;" prefix-icon="el-icon-lock" v-model="user2.confirmpassword" placeholder="请输入密码" clearable show-password></el-input>
              </el-form-item>
            </el-form>
            <div style="margin-top: 0px;">
              <el-checkbox v-model="checked" @change="checkadmission">
                我已阅读并同意 <el-link href="/agreement" type="primary" style="margin-bottom: 3px">《隐私协议》和《用户使用协议》</el-link>
              </el-checkbox>
            </div>
            <div style="margin-top: 10px">
              <el-button round type="primary" style="position:relative; margin-left: 10%" size="big" @click="register" :disabled="!checked" >注&nbsp &nbsp册</el-button>
            </div>
          </div>

        </div>

        <div class="small-box" :class="{active:isLogin}">
          <div class="small-contain" key="smallContainRegister" v-if="isLogin">
            <div class="stitle">你好，朋友!</div>
            <p class="scontent">开始注册，和我们一起旅行</p>
            <button class="sbutton" @click="changeType">注册</button>
          </div>

          <div class="small-contain" key="smallContainLogin" v-else>
            <div class="stitle">欢迎回来!</div>
            <p class="scontent">与我们保持联系，请登录你的账户</p>
            <button class="sbutton" @click="changeType">登录</button>
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
        confirmpassword:"",
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

    login() {
      this.$refs['userForm'].validate((valid) => {
        if (valid) {  // 表单校验合法
          this.request.post("/user/login", this.user).then(res => {
            if (res.code === '200') {
              localStorage.setItem("user", JSON.stringify(res.data))  // 存储用户信息到浏览器
              localStorage.setItem("menus", JSON.stringify(res.data.menus))  // 存储用户信息到浏览器
              // 动态设置当前用户的路由
              setRoutes()
              this.$message.success("登录成功")
              this.$router.push("/")
            } else {
              this.$message.error(res.msg)
            }
          })
        }
      });
    },

    loginEmail() {
      this.request.post("/user/loginEmail", this.user).then(res => {
        if (res.code === '200') {
          localStorage.setItem("user", JSON.stringify(res.data))  // 存储用户信息到浏览器
          localStorage.setItem("menus", JSON.stringify(res.data.menus))  // 存储用户信息到浏览器
          // 动态设置当前用户的路由
          setRoutes()
          this.$message.success("登录成功")
          this.$router.push("/")
        } else {
          this.$message.error(res.msg)
        }
      })
    },

    regretpwd() {
      console.log("111")
      this.$router.push('/regretpwd')
    },

    register() {
      this.$refs['user2Form'].validate((valid) => {
        if (valid) {  // 表单校验合法
          if (this.user2.password !== this.user2.confirmpassword) {
            this.$message.error("两次输入的密码不一致")
            return false
          }
          this.request.post("/user/register", this.user2).then(res => {
            if(res.code === '200') {
              this.$message.success("注册成功")
              this.isLogin = !this.isLogin
            } else {
              this.$message.error(res.msg)
            }
          })
        }
      });
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
  background: linear-gradient(135deg, #FCF5AAFF, #9AF8DAFF);
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

<!--<template>-->
<!--  <div class="wrapper">-->
<!--    <div-->
<!--        style="margin: 200px auto; background-color: #fff; width: 350px; height: 300px; padding: 20px; border-radius: 10px">-->
<!--      <div style="margin: 20px 0; text-align: center; font-size: 24px"><b>登 录</b></div>-->
<!--      <el-form :model="user" :rules="rules" ref="userForm">-->
<!--        <el-form-item prop="username">-->
<!--          <el-input size="medium" style="margin: 10px 0" prefix-icon="el-icon-user" v-model="user.username"></el-input>-->
<!--        </el-form-item>-->
<!--        <el-form-item prop="password">-->
<!--          <el-input size="medium" style="margin: 10px 0" prefix-icon="el-icon-lock" show-password-->
<!--                    v-model="user.password"></el-input>-->
<!--        </el-form-item>-->
<!--        <el-form-item style="margin: 10px 0; text-align: right">-->
<!--          <el-button type="warning" size="small" autocomplete="off" @click="$router.push('/register')">注册</el-button>-->
<!--          <el-button type="primary" size="small" autocomplete="off" @click="login">登录</el-button>-->
<!--        </el-form-item>-->
<!--      </el-form>-->
<!--    </div>-->
<!--  </div>-->
<!--</template>-->

<!--<script>-->
<!--import {setRoutes} from "@/router";-->

<!--export default {-->
<!--  name: "Login",-->
<!--  data() {-->
<!--    return {-->
<!--      user: {},-->
<!--      rules: {-->
<!--        username: [-->
<!--          {required: true, message: '请输入用户名', trigger: 'blur'},-->
<!--          {min: 3, max: 10, message: '长度在 3 到 5 个字符', trigger: 'blur'}-->
<!--        ],-->
<!--        password: [-->
<!--          {required: true, message: '请输入密码', trigger: 'blur'},-->
<!--          {min: 1, max: 20, message: '长度在 1 到 20 个字符', trigger: 'blur'}-->
<!--        ],-->
<!--      }-->
<!--    }-->
<!--  },-->
<!--  methods: {-->
<!--    login() {-->
<!--      this.$refs['userForm'].validate((valid) => {-->
<!--        if (valid) {  // 表单校验合法-->
<!--          this.request.post("/user/login", this.user).then(res => {-->
<!--            if (res.code === '200') {-->
<!--              localStorage.setItem("user", JSON.stringify(res.data))  // 存储用户信息到浏览器-->
<!--              localStorage.setItem("menus", JSON.stringify(res.data.menus))  // 存储用户信息到浏览器-->
<!--              // 动态设置当前用户的路由-->
<!--              setRoutes()-->
<!--              this.$message.success("登录成功")-->

<!--              if (res.data.role === 'ROLE_STUDENT') {-->
<!--                this.$router.push("/front/home")-->
<!--              } else {-->
<!--                this.$router.push("/")-->
<!--              }-->
<!--            } else {-->
<!--              this.$message.error(res.msg)-->
<!--            }-->
<!--          })-->
<!--        }-->
<!--      });-->
<!--    }-->
<!--  }-->
<!--}-->
<!--</script>-->

<!--<style>-->
<!--.wrapper {-->
<!--  height: 100vh;-->
<!--  background-image: linear-gradient(to bottom right, #FC466B, #3F5EFB);-->
<!--  overflow: hidden;-->
<!--}-->
<!--</style>-->
