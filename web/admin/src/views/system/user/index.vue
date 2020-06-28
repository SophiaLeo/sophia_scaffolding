<template>
  <div class="app-container">
    <div class="filter-container">
      <el-input v-model.trim="listQuery.username" placeholder="请输入用户名称" clearable style="width: 200px;" class="filter-item" @keyup.enter.native="handleFilter" />
      <el-input v-model.trim="listQuery.nickname" placeholder="请输入用户昵称" clearable style="width: 200px;" class="filter-item" @keyup.enter.native="handleFilter" />
      <el-select v-model="listQuery.roleId" placeholder="请选择角色" clearable style="width: 200px" class="filter-item">
        <el-option v-for="(item, index) in roleList" :key="index" :label="item.roleName" :value="item.id" />
      </el-select>
      <el-date-picker v-model="listQuery.queryTime" type="daterange" range-separator="至" start-placeholder="开始日期" end-placeholder="结束日期" value-format="yyyy-MM-dd HH:mm:ss" class="filter-item" style="width: 400px" />
      <div class="button-group">
        <el-button type="warning" icon="el-icon-plus" @click="addUser">添加</el-button>
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
      <el-table-column :show-overflow-tooltip="true" label="用户名称" prop="username" width="150px" align="center">
        <template slot-scope="{row}">
          <span>{{ row.username }}</span>
        </template>
      </el-table-column>
      <el-table-column :show-overflow-tooltip="true" label="用户昵称" prop="nickname" width="150px" align="center">
        <template slot-scope="{row}">
          <span>{{ row.nickname }}</span>
        </template>
      </el-table-column>
      <el-table-column :show-overflow-tooltip="true" label="用户角色" prop="roleName" width="150px" align="center">
        <template slot-scope="{row}">
          <span>{{ row.roleName }}</span>
        </template>
      </el-table-column>
      <el-table-column :show-overflow-tooltip="true" label="头像" prop="headImage" width="150px" align="center">
        <template slot-scope="{row}">
          <img :src="row.headImage" class="imgShow">
        </template>
      </el-table-column>
      <el-table-column :show-overflow-tooltip="true" label="公司" prop="compName" width="150px" align="center">
        <template slot-scope="{row}">
          <span>{{ row.compName }}</span>
        </template>
      </el-table-column>
      <el-table-column :show-overflow-tooltip="true" label="部门" prop="deptName" width="150px" align="center">
        <template slot-scope="{row}">
          <span>{{ row.deptName }}</span>
        </template>
      </el-table-column>
      <el-table-column :show-overflow-tooltip="true" label="年龄" prop="age" width="80px" align="center">
        <template slot-scope="{row}">
          <span>{{ row.age }}</span>
        </template>
      </el-table-column>
      <el-table-column :show-overflow-tooltip="true" label="生日" prop="birthday" width="150px" align="center">
        <template slot-scope="{row}">
          <span>{{ row.birthday }}</span>
        </template>
      </el-table-column>
      <el-table-column :show-overflow-tooltip="true" label="性别" prop="sex" width="80px" align="center">
        <template slot-scope="scope">
          <div v-for="(item, index) in sexList" :key="index">
            <el-tag v-if="scope.row.sex === item.value ">{{ item.name }}</el-tag>
          </div>
        </template>
      </el-table-column>
      <el-table-column :show-overflow-tooltip="true" label="手机号" prop="phone" width="150px" align="center">
        <template slot-scope="{row}">
          <span>{{ row.phone }}</span>
        </template>
      </el-table-column>
      <el-table-column :show-overflow-tooltip="true" label="邮箱" prop="email" width="150px" align="center">
        <template slot-scope="{row}">
          <span>{{ row.email }}</span>
        </template>
      </el-table-column>
      <el-table-column :show-overflow-tooltip="true" label="省" prop="province" width="150px" align="center">
        <template slot-scope="{row}">
          <span>{{ row.province }}</span>
        </template>
      </el-table-column>
      <el-table-column :show-overflow-tooltip="true" label="市" prop="city" width="150px" align="center">
        <template slot-scope="{row}">
          <span>{{ row.city }}</span>
        </template>
      </el-table-column>
      <el-table-column :show-overflow-tooltip="true" label="区" prop="area" width="150px" align="center">
        <template slot-scope="{row}">
          <span>{{ row.area }}</span>
        </template>
      </el-table-column>
      <el-table-column :show-overflow-tooltip="true" label="地址" prop="address" width="150px" align="center">
        <template slot-scope="{row}">
          <span>{{ row.address }}</span>
        </template>
      </el-table-column>
      <el-table-column :show-overflow-tooltip="true" label="最后登录时间" prop="lastLoginTime" width="150px" align="center">
        <template slot-scope="{row}">
          <span>{{ row.lastLoginTime }}</span>
        </template>
      </el-table-column>
      <el-table-column :show-overflow-tooltip="true" label="最后登录IP" prop="lastLoginIp" width="150px" align="center">
        <template slot-scope="{row}">
          <span>{{ row.lastLoginIp }}</span>
        </template>
      </el-table-column>
      <el-table-column fixed="right" prop="blStatus" label="用户启用" width="150" align="center">
        <template slot-scope="{row}">
          <el-switch v-model="row.blStatus" style="display: block" class="textbalck" active-text="启用" inactive-text="禁用" active-color="#13ce66" inactive-color="#ff4949" @change="changeStatus(row)" />
        </template>
      </el-table-column>
      <el-table-column prop="status" label="操作" align="center" fixed="right" width="300px">
        <template slot-scope="{row}">
          <el-button type="warning" size="mini" icon="el-icon-refresh" @click="resetPassword(row.id)">重置密码</el-button>
          <el-button type="success" size="mini" icon="el-icon-edit" @click="updateUser(row.id)">编辑</el-button>
          <el-button type="danger" size="mini" icon="el-icon-delete" @click="deleteUser(row.id)">删除</el-button>
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
import { userList, deleteUser, deleteBatch, updateStatus, reset } from '@/api/user'
import { dictByType } from '@/api/dict'
import { roleSelectList } from '@/api/role'
import store from '@/store'

export default {
  name: 'User',
  data() {
    return {
      list: null,
      total: 0,
      currentPage: 1,
      pageSize: 10,
      listLoading: true,
      listQuery: {
        username: '',
        nickname: '',
        roleId: '',
        startTime: 0,
        endTime: 0,
        currentPage: 1,
        pageSize: 10
      },
      multipleSelection: [],
      ids: [],
      sexList: [],
      compList: [],
      deptList: [],
      roleList: []
    }
  },
  created() {
    this.queryRole()
    this.queryDictByType()
    this.getList()
  },
  methods: {
    queryDictByType() {
      const type = 'sex'
      dictByType(type).then(res => {
        this.sexList = res.data
      })
    },
    queryRole() {
      roleSelectList(store.getters.userId).then(res => {
        this.roleList = res.data
      })
    },
    getList() {
      this.listQuery.pageSize = this.pageSize
      this.listQuery.currentPage = this.currentPage
      if (this.listQuery.queryTime != null && this.listQuery.queryTime.length > 0) {
        this.listQuery.startTime = this.listQuery.queryTime[0]
        this.listQuery.endTime = this.listQuery.queryTime[1]
      } else {
        this.listQuery.startTime = null
        this.listQuery.endTime = null
      }
      this.listLoading = true
      userList(this.listQuery).then(response => {
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
    addUser() {
      this.$router.push({ name: 'UserSave' })
    },
    updateUser(id) {
      this.$router.push({ name: 'UserUpdate', query: { id: id }})
    },
    changeStatus(row) {
      let status
      if (row.blStatus) {
        status = 1
      } else {
        status = 0
      }
      const params = {
        id: row.id,
        status: status
      }
      updateStatus(params).then(response => {
        if (response.code === 200) {
          this.$message.success(response.message)
          this.getList()
        } else {
          this.$message.error(response.message)
        }
      })
    },
    resetPassword(id) {
      const params = {
        id: id
      }
      reset(params).then(response => {
        if (response.code === 200) {
          this.$message.success(response.message)
        } else {
          this.$message.error(response.message)
        }
      })
    },
    deleteUser(id) {
      this.$confirm('确认删除?')
        .then(_ => {
          deleteUser(id).then(response => {
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
.imgShow {
  width: auto;
  height: auto;
  max-width: 100%;
  max-height: 100%;
}
</style>
