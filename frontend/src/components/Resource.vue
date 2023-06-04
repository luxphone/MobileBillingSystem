<template>
  <div>
    <h2>新增号码</h2>
    <el-form :model="resourceForm">
      <el-form-item label="号码类型" prop="mobileType">
        <el-radio-group v-model="resourceForm.mobileType">
          <el-radio label="SIM"></el-radio>
          <el-radio label="UIM"></el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="指定号段">
        <el-col :span="4">
          <el-input v-model="resourceForm.mobileNumberLeft"></el-input>
        </el-col>
        <el-col :span="1" STYLE="text-align: center">
          到
        </el-col>
        <el-col :span="4">
          <el-input v-model="resourceForm.mobileNumberRight"></el-input>
        </el-col>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button type="primary" @click="addResource">提交</el-button>
      <el-button type="primary" @click="resetForm">全部重写</el-button>
    </div>
  </div>
</template>

<script>
export default {
  name: "Resource",
  data() {
    return {
      resourceForm: {},
    }
  },
  methods: {
    addResource() {
      this.$axios.postForm("/mobiles/add", this.resourceForm).then(response => {
        if (response.data.code == 200) {
          this.$message.success(response.data.msg)
        }
      })
    },
    resetForm() {
      this.resourceForm = {}
    }
  },
  created() {
  }
}
</script>

<style scoped  lang="less">

</style>