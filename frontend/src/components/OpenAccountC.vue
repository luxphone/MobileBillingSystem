<template>
  <div>
    <h2>新增用户-3</h2>
    <el-form :model="userForm">
      <el-form-item label="手机号码">
        <el-input v-model="userForm.mobileNumber" placeholder="手机号码" style="width: auto"></el-input>
      </el-form-item>
      <el-form-item label="漫游状态">
        <el-select v-model="userForm.roamingStatus" placeholder="请选择漫游状态">
          <el-option label="省内漫游" value="P"></el-option>
          <el-option label="国内漫游" value="D"></el-option>
          <el-option label="国际漫游" value="I"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="通话级别">
        <el-select v-model="userForm.comLevel" placeholder="请选择通话级别">
          <el-option label="本地通话" value="L"></el-option>
          <el-option label="国内长途" value="D"></el-option>
          <el-option label="国际长途" value="I"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="客户id">
        <el-input v-model="userForm.customerId" disabled style="width: auto"></el-input>
      </el-form-item>
      <el-form-item label="账号">
        <el-input v-model="userForm.accountId" placeholder="请输入账号" style="width: auto"></el-input>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="clear">全部重写</el-button>
      <el-button type="primary" @click="addUser">下一步</el-button>
    </div>
  </div>
</template>
<script>
export default {
  name: "OpenAccountC",
  data() {
    return {
      userForm: {
        customerId: '',
        accountId: '',
        mobileNumber: '',
        roamingStatus: '',
        comLevel: ''
      },
    }
  },
  methods: {
    addUser() {
      this.$axios.postForm("/user/add", this.userForm).then(response => {
        if (response.data.code === 200) {
          this.$message.success(response.data.msg);
          let params = {
            accountId: this.userForm.accountId
          }
          this.$axios.get("/account/query", {params: params}).then(response => {
            if (response.data.data == null) {
              sessionStorage.setItem("accountId", this.userForm.accountId);
              this.$router.push("/addAccount");
            } else {
              sessionStorage.setItem("account", JSON.stringify(response.data.data));
              this.$router.push("/mergeAccount");
            }
          })
        }
      })
    },
    clear() {
      this.userForm = {}
    },
  },
  created() {
    this.userForm.customerId = sessionStorage.getItem("customerId");
  }
}
</script>

<style scoped lang="less">

</style>