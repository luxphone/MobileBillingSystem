<template>
  <div>
    <h2>新增用户-1</h2>
    <el-form :model="customerForm">
      <el-form-item label="证件类型">
        <el-select v-model="customerForm.idType" placeholder="证件类型">
          <el-option label="居民身份证" value="D"></el-option>
          <el-option label="军官证" value="A"></el-option>
          <el-option label="护照" value="P"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="证件号码">
        <el-input v-model="customerForm.idNumber" placeholder="证件号码" style="width: auto"></el-input>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button type="primary" @click="addCustomer">下一步</el-button>
      <el-button @click="resetForm">重置</el-button>
    </div>
    <h4>请正确填写证件号码</h4>
    <h4>1.身份证15位或18位</h4>
    <h4>2.护照8位或9位</h4>
    <h4>3.军官证7位</h4>
  </div>
</template>
<script>
export default {
  name: "OpenAccount",
  data() {
    return {
      customerForm: {
        idType: '',
        idNumber: ''
      },
    }
  },
  methods: {
    addCustomer() {
      sessionStorage.setItem("customerForm", JSON.stringify(this.customerForm))
      this.$router.push("/openAccountB")
    },
    resetForm() {
      this.customerForm = {}
    },
  },
  created() {
  }
}
</script>

<style scoped lang="less">

</style>