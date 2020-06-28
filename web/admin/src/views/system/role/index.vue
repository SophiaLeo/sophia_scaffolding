<template>
  <div class="app-container">
    <div class="filter-container">
      <el-input v-model.trim="listQuery.roleName" placeholder="请输入角色名称" clearable style="width: 200px;" class="filter-item" @keyup.enter.native="handleFilter" />
      <div class="button-group">
        <el-button type="warning" icon="el-icon-plus" @click="addRole">添加</el-button>
        <el-button type="primary" icon="el-icon-search" @click="getList">查询</el-button>
        <el-button type="danger" icon="el-icon-delete" @click="batchDelete(multipleSelection)">批量删除</el-button>
      </div>
    </div>

    <el-table
      ref="multipleTable"
      v-loading="listLoading"
      :data="list"
      border
      fit
      highlight-current-row
      style="width: 100%;"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" align="center" width="55" />
      <el-table-column width="55" label="序号" align="center">
        <template slot-scope="scope">
          <span>{{ scope.$index + 1 }}</span>
        </template>
      </el-table-column>
      <el-table-column :show-overflow-tooltip="true" label="角色名称" prop="roleName" align="center">
        <template slot-scope="{row}">
          <span>{{ row.roleName }}</span>
        </template>
      </el-table-column>
      <el-table-column :show-overflow-tooltip="true" label="角色编号" prop="roleCode" align="center">
        <template slot-scope="{row}">
          <span>{{ row.roleCode }}</span>
        </template>
      </el-table-column>
      <el-table-column :show-overflow-tooltip="true" label="描述" prop="description" align="center">
        <template slot-scope="{row}">
          <span>{{ row.description }}</span>
        </template>
      </el-table-column>
      <el-table-column :show-overflow-tooltip="true" label="角色类型" prop="roleType" align="center">
        <template slot-scope="{row}">
          <el-tag v-if="row.roleType === 0" type="success" size="medium">前台</el-tag>
          <el-tag v-if="row.roleType === 1" size="medium">后台</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="status" label="操作" align="center" fixed="right" width="220px">
        <template slot-scope="{row}">
          <el-button type="success" size="mini" icon="el-icon-edit" @click="updateRole(row.id)">编辑</el-button>
          <el-button type="danger" size="mini" icon="el-icon-delete" @click="deleteRole(row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-pagination
      :total="total"
      :page-size="pageSize"
      :current-page="currentPage"
      :page-sizes="[10, 20, 30, 40]"
      background
      layout=" prev, pager, next, sizes, total"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
    />
  </div>
</template>

<script>
import { roleList, deleteRole, deleteBatch, roleSelectList } from '@/api/role'
import store from '@/store'

export default {
  name: 'Role',
  data() {
    return {
      list: null,
      total: 0,
      currentPage: 1,
      pageSize: 10,
      listLoading: true,
      listQuery: {
        roleName: '',
        currentPage: 1,
        pageSize: 10
      },
      multipleSelection: [],
      ids: []
    }
  },
  created() {
    this.getList()
  },
  methods: {
    getList() {
      this.listQuery.pageSize = this.pageSize
      this.listQuery.currentPage = this.currentPage
      this.listLoading = true
      roleList(this.listQuery).then(response => {
        if (response.code == 200) {
          this.list = response.data.records
          this.total = response.data.total
          this.pageSize = response.data.size
          this.currentPage = response.data.current
          this.listLoading = false
        } else {
          this.list = []
          this.total = 0
          this.pageSize = 10
          this.currentPage = 1
          this.listLoading = false
        }
      })
    },
    handleSizeChange(pageSize) {
      this.pageSize = pageSize
      this.getList()
    },
    handleCurrentChange(currentPage) {
      this.currentPage = currentPage
      this.getList()
    },
    handleFilter() {
      this.currentPage = 1
      this.getList()
    },
    // 获取批量删除选中的数据行
    handleSelectionChange(val) {
      this.multipleSelection = val
    },
    addRole() {
      this.$router.push({ name: 'RoleSave' })
    },
    updateRole(id) {
      this.$router.push({ name: 'RoleUpdate', query: { id: id }})
    },
    deleteRole(id) {
      this.$confirm('确认删除?')
        .then(_ => {
          deleteRole(id).then(response => {
            if (response.code == 200) {
              this.$message.success(response.message)
              this.getList()
            } else {
              this.$message.error(response.message)
            }
          }
          )
        })
        .catch(_ => { })
    },
    batchDelete(rows) {
      rows.forEach(element => {
        this.ids.push(element.id)
      })
      this.$confirm('确认批量删除?')
        .then(_ => {
          deleteBatch(this.ids).then(response => {
            if (response.code === 200) {
              this.$message.success(response.message)
              this.getList()
              this.$refs.multipleTable.clearSelection()
              this.ids = []
            } else {
              this.$message.error(response.message)
            }
          }
          )
        }).catch(_ => {
        })
    }
  }
}
</script>

<style scoped>
.button-group {
  float: right;
  margin-bottom: 10px;
}
</style>
