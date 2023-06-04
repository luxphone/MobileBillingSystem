<template>
  <div>
    <h2>新增用户-2</h2>
    <el-form :model="customerForm">
      <el-form-item label="证件类型">
        <el-input v-if="customerForm.idType==='D'" value="居民身份证" disabled style="width: auto"></el-input>
        <el-input v-if="customerForm.idType==='A'" value="军官证" disabled style="width: auto"></el-input>
        <el-input v-if="customerForm.idType==='P'" value="护照" disabled style="width: auto"></el-input>
      </el-form-item>
      <el-form-item label="证件号码">
        <el-input v-model="customerForm.idNumber" disabled placeholder="证件号码" style="width: auto"></el-input>
      </el-form-item>
      <el-form-item label="姓名">
        <el-input v-model="customerForm.customerName" placeholder="请输入姓名" style="width: auto"></el-input>
      </el-form-item>
      <el-form-item label="性别">
        <el-radio-group v-model="customerForm.customerSex" placeholder="请选择性别">
          <el-radio label="M">男</el-radio>
          <el-radio label="F">女</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="生日">
        <el-date-picker
            v-model="customerForm.customerBirthday"
            type="date"
            placeholder="选择日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="联系地址">
        <el-input v-model="customerForm.customerAddress" placeholder="请输入联系地址" style="width: auto"></el-input>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button type="primary" @click="addCustomer">下一步</el-button>
      <el-button @click="resetForm">重置</el-button>
    </div>
  </div>
</template>
<script>
export default {
  name: "OpenAccountB",
  data() {
    return {
      customerForm: {},
    }
  },
  methods: {
    addCustomer() {
      this.$axios.postForm("/customer/add", this.customerForm).then(response => {
        if (response.data.code === 200) {
          this.$message.success(response.data.msg)
          sessionStorage.setItem("customerId", response.data.data)
          this.$router.push("/openAccountC")
        }
      })
    },
    resetForm() {
      this.customerForm = {}
    },
  },
  created() {
    this.customerForm = JSON.parse(sessionStorage.getItem("customerForm"))
  }
}
</script>

<style scoped lang="less">

</style>