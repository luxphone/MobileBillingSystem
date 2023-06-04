<template>
  <div>
    <h2>合账</h2>
    <el-form :model="accountForm">
      <el-form-item label="账号">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <el-input v-model="accountForm.accountId" disabled style="width: 30%"></el-input>
      </el-form-item>
      <el-form-item label="姓名">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <el-input v-model="accountForm.contactPerson" style="width: 30%"></el-input>
      </el-form-item>
      <el-form-item label="联系地址">
        <el-input v-model="accountForm.contactAddress" style="width: 30%"></el-input>
      </el-form-item>
      <el-form-item label="开户金额">
        <el-input v-model="accountForm.accountBalance" style="width: 30%"></el-input>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button type="primary" @click="addAccount">下一步</el-button>
      <el-button @click="resetForm">重置</el-button>
    </div>
  </div>
</template>
<script>

export default {
  name: "AddAccount",
  data() {
    return {
      charge: 0,
      accountForm: {
        accountId: "",
        contactPerson: "",
        contactAddress: "",
        accountBalance: "",
      },
    }
  },
  methods: {
    addAccount() {
      this.accountForm.accountBalance -= this.charge;
      if (this.accountForm.accountBalance < 0) {
        this.$message.error("余额不足")
        setTimeout(() => {
          location.reload();
        }, 1000);
      } else {
        this.$axios.postForm("/account/add", this.accountForm).then(response => {
          if (response.data.code === 200) {
            this.$message.success(response.data.msg)
            this.$router.push("/home")
          }
        });
      }
    },
    queryCharge() {
      this.$axios.get("/charge/totalFee").then(response => {
        if (response.data.code === 200) {
          this.charge = response.data.data
        }
      })
    },
    resetForm() {
      this.accountForm = {}
    },
  },
  created() {
    this.accountForm.accountId = sessionStorage.getItem("accountId");
    this.queryCharge();
  }
}
</script>

<style scoped lang="less">

</style>