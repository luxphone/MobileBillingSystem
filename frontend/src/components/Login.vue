<template>
  <div class="container">
    <div class="login-container">
      <el-card class="login-card">
        <h1 class="login-title">中国移动通信</h1>
        <el-form ref="loginForm" :model="loginForm" label-width="80px">
          <el-form-item label="用户名" prop="username">
            <el-input v-model="loginForm.username" placeholder="请输入用户名"></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="password">
            <el-input type="password" v-model="loginForm.password" placeholder="请输入密码"></el-input>
          </el-form-item>
          <el-form-item label="验证码" prop="code">
            <el-input v-model="loginForm.code" placeholder="请输入验证码"></el-input>
            <el-button type="text" @click="refreshLoginCode"><img :src="loginImgUrl" alt="..."/>&nbsp;看不清楚，换一张
            </el-button>
          </el-form-item>
          <el-form-item>
            <el-button plain @click="login">登录</el-button>
          </el-form-item>
        </el-form>
      </el-card>
    </div>
  </div>
</template>

<script>
export default {
  name: "Login",
  data() {
    return {
      loginForm: {
        username: '',
        password: '',
        code: ''
      },
      loginImgUrl: process.env.VUE_APP_BACKEND_URL + "/operator/getLoginCode?t=" + new Date().valueOf()
    };
  },
  methods: {
    login() {
      this.$axios.postForm("/operator/login", this.loginForm).then(response => {
        if (response.data.code == 200) {
          let token = response.data.data
          localStorage.setItem("token", token)
          this.$router.push("/home")
        }
      })
    },
    refreshLoginCode() {
      this.loginImgUrl = process.env.VUE_APP_BACKEND_URL + "/operator/getLoginCode?t=" + new Date().valueOf()
    },
  },
  beforeCreate() {
    document.querySelector('body').setAttribute('style', 'margin:0;padding:0;')
  },
}
</script>

<style lang="less" scoped>
.container {
  background-color: cadetblue;
  height: 100vh;
}

.login-container {
  max-width: 400px;
  margin: auto;
  padding: 80px;
}

.login-title {
  text-align: center;
  margin-bottom: 20px;
}

</style>
