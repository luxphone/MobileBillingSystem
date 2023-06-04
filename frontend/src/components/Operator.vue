<script>
export default {
  name: "Operator",
  data() {
    return {
      operatorList: [],
      total: 0,
      size: 5,
      current: 1,
      searchForm: {},
      addFormVisible: false,
      addForm: {},
      editFormVisible: false,
      editForm: {},

    }
  },
  methods: {
    initOperatorList() {
      let params = {
        current: this.current,
        size: this.size
      }
      this.$axios.get("/operator/list", {params: params}).then(response => {
        if (response.data.code === 200) {
          this.operatorList = response.data.data.records
          this.total = response.data.data.total
          this.current = response.data.data.current
          this.size = response.data.data.size
        }
      })
    },
    search() {
      let params = {
        current: this.current,
        size: this.size,
        ...this.searchForm
      }
      this.$axios.postForm("/operator/search", params).then(response => {
        if (response.data.code === 200) {
          this.operatorList = response.data.data.records
          this.total = response.data.data.total
          this.current = response.data.data.current
          this.size = response.data.data.size
        }
      })
    },
    closeForm() {
      this.addFormVisible = false
      this.addForm = {}
      this.editFormVisible = false
      this.editForm = {}
    },
    handleSizeChange(size) {
      this.size = size
      this.initOperatorList()
    },
    handleCurrentChange(current) {
      this.current = current
      this.initOperatorList()
    },
    confirmAdd() {
      this.$axios.postForm("/operator/add", this.addForm).then(response => {
        if (response.data.code === 200) {
          this.addFormVisible = false
          this.$message.success(response.data.msg)
          this.initOperatorList()
        }
      })
    },
    showEditForm(row) {
      this.editFormVisible = true
      this.editForm = row
    },
    confirmEdit() {
      this.$axios.postForm("/operator/edit", this.editForm).then(response => {
        if (response.data.code === 200) {
          this.editFormVisible = false
          this.$message.success(response.data.msg)
          this.initOperatorList()
        }
      })
    },
    deleteOperator(operatorId) {
      this.$confirm('确定要删除该操作员吗', '警告', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$axios.delete("/operator/delete", {params: {operatorId: operatorId}}).then(response => {
          if (response.data.code === 200) {
            this.$message.success(response.data.msg)
            this.initOperatorList()
          }
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '取消删除'
        })
      })
    }
  },
  created() {
    this.initOperatorList()
  }
}
</script>

<template>
  <div>
    <el-button type="primary" @click="addFormVisible=true">新增操作员</el-button>
    <br>
    <br>
    <el-form :inline="true" :model="searchForm" class="demo-form-inline">
      <el-form-item label="操作员姓名">
        <el-input v-model="searchForm.operatorName"></el-input>
      </el-form-item>
      <el-form-item label="操作员编号">
        <el-input v-model="searchForm.operatorId"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="search">立即查询</el-button>
      </el-form-item>
    </el-form>
    <el-table
        :data="operatorList"
        style="width: 100%">
      <el-table-column
          prop="operatorId"
          label="ID"
          width="180">
      </el-table-column>
      <el-table-column
          prop="operatorName"
          label="姓名"
          width="180">
      </el-table-column>
      <el-table-column
          prop="operatorPwd"
          label="密码"
          width="180">
      </el-table-column>
      <el-table-column
          prop="isAdmin"
          label="是否管理员"
          width="180">
        <template v-slot="scope">
          <el-tag :type="scope.row.isAdmin === 'Y' ? 'success' : 'danger'" isable-transitions>
            {{ scope.row.isAdmin === 'Y' ? '是' : '否' }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作">
        <template v-slot="scope">
          <el-button type="primary" size="mini" @click="showEditForm(scope.row)">修改</el-button>
          <el-button type="danger" size="mini" @click="deleteOperator(scope.row.operatorId)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-dialog title="新增操作员" :visible.sync="addFormVisible" @close="closeForm" :close-on-click-modal="false">
      <el-form :model="addForm" ref="ruleForm" label-width="100px" class="demo-ruleForm">
        <el-form-item label="Id" prop="operatorId">
          <el-input v-model="addForm.operatorId"></el-input>
        </el-form-item>
        <el-form-item label="姓名" prop="operatorName">
          <el-input v-model="addForm.operatorName"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="operatorPwd">
          <el-input v-model="addForm.operatorPwd"></el-input>
        </el-form-item>
        <el-form-item label="设为管理员" prop="isAdmin">
          <el-radio-group v-model="addForm.isAdmin">
            <el-radio label="Y">是</el-radio>
            <el-radio label="N">否</el-radio>
          </el-radio-group>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="closeForm">取 消</el-button>
        <el-button type="primary" @click="confirmAdd">确 定</el-button>
      </div>
    </el-dialog>
    <el-dialog title="修改操作员" :visible.sync="editFormVisible" @close="closeForm" :close-on-click-modal="false">
      <el-form :model="editForm" ref="ruleForm" label-width="100px" class="demo-ruleForm">
        <el-form-item label="Id" prop="operatorId">
          <el-input v-model="editForm.operatorId"></el-input>
        </el-form-item>
        <el-form-item label="姓名" prop="operatorName">
          <el-input v-model="editForm.operatorName"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="operatorPwd">
          <el-input v-model="editForm.operatorPwd"></el-input>
        </el-form-item>
        <el-form-item label="设为管理员" prop="isAdmin">
          <el-radio-group v-model="editForm.isAdmin">
            <el-radio label="Y">是</el-radio>
            <el-radio label="N">否</el-radio>
          </el-radio-group>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="closeForm">取 消</el-button>
        <el-button type="primary" @click="confirmEdit">确 定</el-button>
      </div>
    </el-dialog>
    <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="current"
        :page-sizes="[5, 10, 15, 20]"
        :page-size="size"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total">
    </el-pagination>
  </div>
</template>

<style scoped lang="less">

</style>