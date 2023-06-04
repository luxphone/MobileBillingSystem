<template>
  <div>
    <h2>欢迎访问后台</h2>
    <el-card class="user-card" :body-style="{ padding: '20px' }">
      <div class="user-info">
        <h3>{{ username }}</h3>
        <p>{{ role }}</p>
      </div>
    </el-card>
  </div>
</template>

<script>
export default {
  name: "OverView",
  data() {
    return {
      username: '',
      role: '',
    }
  },
  methods: {

  },
  created() {
    this.username = sessionStorage.getItem("operatorName")
    this.role = sessionStorage.getItem("operatorIsAdmin") === 'Y' ? '管理员' : '操作员'
  }
}
</script>

<style lang="less" scoped>
h2 {
  text-align: center;
}

.user-card {
  width: 70%;
  margin: 0 auto;
  display: flex;
  justify-content: center;
  align-items: center;
}

.user-info {
  text-align: center;
}
</style>