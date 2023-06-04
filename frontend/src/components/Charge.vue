<template>
  <div>
    <div class="left-content">
      <h2>收费细项</h2>
      <el-form :model="chargeForm">
        <el-form-item label="收费项目">
          <el-select v-model="chargeForm.chargeCode" placeholder="收费项目" style="width: auto">
            <el-option label="开户费" value="A"></el-option>
            <el-option label="漫游费" value="B"></el-option>
            <el-option label="押金" value="C"></el-option>
            <el-option label="入网费" value="D"></el-option>
            <el-option label="选号费" value="E"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="收费金额">
          <el-input v-model="chargeForm.charge" placeholder="请输入收费金额" style="width: auto"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="editCharge">提交</el-button>
        <el-button @click="clear">重置</el-button>
      </div>
      <h2>业务收费</h2>
      <el-form :model="chargeRuleForm">
        <el-form-item label="业务">
          <el-select v-model="chargeRuleForm.funcId" placeholder="选择业务" style="width: auto"
                     @change="initChargeRuleForm">
            <el-option v-for="item in chargeFuncList" :label="item==='O'?'开户业务':''"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="收费项目">
          <el-checkbox-group v-model="chargeRuleForm.checkList">
            <el-checkbox label="A">开户费</el-checkbox>
            <el-checkbox label="B">漫游费</el-checkbox>
            <el-checkbox label="C">押金</el-checkbox>
            <el-checkbox label="D">入网费</el-checkbox>
            <el-checkbox label="E">选号费</el-checkbox>
          </el-checkbox-group>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="editChargeRule">提交</el-button>
        <el-button @click="clearRule">重置</el-button>
      </div>
    </div>
    <div class="right-content">
      <h2>业务收费表</h2>
      <el-table
          :data="chargeList"
          stripe
          style="width: 100%">
        <el-table-column
            label="#"
            type="index"
            width="50">
        </el-table-column>
        <el-table-column
            prop="chargeCode"
            label="收费项目"
            width="180">
          <template v-slot="scope">
            <el-tag v-if="scope.row.chargeCode ==='A' ">开户费</el-tag>
            <el-tag v-if="scope.row.chargeCode ==='B' ">漫游费</el-tag>
            <el-tag v-if="scope.row.chargeCode ==='C' ">押金</el-tag>
            <el-tag v-if="scope.row.chargeCode ==='D' ">入网费</el-tag>
            <el-tag v-if="scope.row.chargeCode ==='E' ">选号费</el-tag>
          </template>
        </el-table-column>
        <el-table-column
            prop="charge"
            label="费用">
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
export default {
  name: "Resource",
  data() {
    return {
      chargeForm: {},
      chargeRuleForm: {
        funcId: '',
        checkList: [],
      },
      chargeFuncList: [],
      chargeList: []
    }
  },
  methods: {
    editCharge() {
      this.$axios.postForm("/charge/update", this.chargeForm).then(response => {
        if (response.data.code === 200) {
          this.$message.success(response.data.msg)
          this.initChargeList()
        }
      })
    },
    clear() {
      this.chargeForm = {}
    },
    clearRule() {
      this.chargeRuleForm.funcId = ''
      this.chargeRuleForm.checkList = []
    },
    editChargeRule() {
      let chargeCodeList = []
      this.chargeRuleForm.checkList.forEach(item => {
        chargeCodeList.push(item)
      })
      let params={
        funcId: 'O',
        chargeCodeList: chargeCodeList
      }
      this.$axios.postForm("/chargeRule/update", params).then(response=>{
        if (response.data.code === 200) {
          this.$message.success(response.data.msg)
        }
      })
    },
    initChargeFuncList() {
      this.$axios.get("/chargeRule/list").then(response => {
        if (response.data.code === 200) {
          this.chargeFuncList = response.data.data
        }
      })
    },
    initChargeRuleForm() {
      let params = {
        funcId: 'O'
      }
      this.$axios.get("/chargeRule/query", {params: params}).then(response => {
        for (let i = 0; i < response.data.data.length; i++) {
          this.chargeRuleForm.checkList.push(response.data.data[i])
        }
      })
    },
    initChargeList() {
      this.$axios.get("/charge/list").then(response => {
        if (response.data.code === 200) {
          this.chargeList = response.data.data
        }
      })
    },
  },
  created() {
    this.initChargeList()
    this.initChargeFuncList()
  }
}
</script>

<style scoped lang="less">
.left-content {
  width: 50%;
  float: left;
}

.right-content {
  width: 50%;
  float: right;
}
</style>