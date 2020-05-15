<template>
  <div class="app-container">
    <div class="filter-container">
      <el-input v-model.trim="listQuery.userName" placeholder="请输入访问用户" clearable style="width: 200px;" class="filter-item" @keyup.enter.native="handleFilter" />
      <el-input v-model.trim="listQuery.methodName" placeholder="请输入方法名称" clearable style="width: 200px;" class="filter-item" @keyup.enter.native="handleFilter" />
      <el-select v-model="listQuery.method" placeholder="请选择请求方式" clearable style="width: 200px" class="filter-item">
        <el-option v-for="(item, index) in methodList" :key="index" :label="item.name" :value="item.name" />
      </el-select>
      <el-date-picker v-model="listQuery.queryTime" type="daterange" range-separator="至" start-placeholder="访问开始日期" end-placeholder="访问结束日期" class="filter-item" style="width: 400px"/>
      <div class="button-group">
        <el-button type="primary" icon="el-icon-search" @click="getList">查询</el-button>
        <el-button type="danger" icon="el-icon-delete" @click="batchDelete(multipleSelection)">批量删除</el-button>
      </div>
    </div>

    <el-table
      v-loading="listLoading"
      ref="multipleTable"
      :data="list"
      border
      fit
      highlight-current-row
      style="width: 100%;"
      @selection-change="handleSelectionChange"
    >
    <el-table-column type="selection" align="center" width="55"/>
    <el-table-column width="55" label="序号" align="center">
        <template slot-scope="scope">
          <span>{{ scope.$index + 1 }}</span>
        </template>
      </el-table-column>
      <el-table-column :show-overflow-tooltip="true" label="访问用户" prop="userName" width="150px" align="center">
        <template slot-scope="{row}">
          <span>{{ row.userName }}</span>
        </template>
      </el-table-column>
      <el-table-column :show-overflow-tooltip="true" label="IP" prop="ip" width="150px" align="center">
        <template slot-scope="{row}">
          <span>{{ row.ip }}</span>
        </template>
      </el-table-column>
      <el-table-column :show-overflow-tooltip="true" label="接口" prop="uri" width="150px" align="center">
        <template slot-scope="{row}">
          <span>{{ row.uri }}</span>
        </template>
      </el-table-column>
      <el-table-column :show-overflow-tooltip="true" label="参数" prop="params" width="150px" align="center">
        <template slot-scope="{row}">
          <span>{{ row.params }}</span>
        </template>
      </el-table-column>
      <el-table-column :show-overflow-tooltip="true" label="请求方式" prop="method" width="150px" align="center">
        <template slot-scope="{row}">
         <el-tag>{{ row.method }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column :show-overflow-tooltip="true" abel="类名" prop="className" width="150px" align="center">
        <template slot-scope="{row}">
          <span>{{ row.className }}</span>
        </template>
      </el-table-column>
      <el-table-column :show-overflow-tooltip="true" label="方法名" prop="methodName" width="150px" align="center">
        <template slot-scope="{row}">
          <span>{{ row.methodName }}</span>
        </template>
      </el-table-column>
      <el-table-column :show-overflow-tooltip="true" label="访问时间" prop="createTime" width="150px" align="center">
        <template slot-scope="{row}">
          <span>{{ row.createTime }}</span>
        </template>
      </el-table-column> 
      <el-table-column prop="status" label="操作" align="center" fixed="right" width="100">
        <template slot-scope="{row}">
          <el-button type="danger" size="mini" icon="el-icon-delete" @click="deleteLog(row.id)">删除</el-button>
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
      @current-change="handleCurrentChange"/>
  </div>
</template>

<script>
import { logList, deleteLog, deleteBatch } from '@/api/log'
import { dictByType } from '@/api/dict'

export default {
  name: 'Log',
  data() {
    return {
      list: null,
      total: 0,
      currentPage: 1,
      pageSize: 10,
      listLoading: true,
      listQuery: {
        userName: '',
        methodName: '',
        method: '',
        startTime: 0,
        endTime: 0,
        currentPage: 1,
        pageSize: 10
      },
      multipleSelection: [],
      ids: [],
      methodList: []
    }
  },
  created() {
    this.queryDictByType()
    this.getList()
  },
  methods: {    
    queryDictByType() {
      let type = 'method'
      dictByType(type).then(res => {
         this.methodList = res.data
      })
    },
    getList() {
      this.listQuery.pageSize = this.pageSize
      this.listQuery.currentPage = this.currentPage
      if (this.listQuery.queryTime != null && this.listQuery.queryTime.length > 0) {
        this.listQuery.startTime = Number(this.listQuery.queryTime[0])
        this.listQuery.endTime = Number(this.listQuery.queryTime[1])
      } else {
        this.listQuery.startTime = null
        this.listQuery.endTime = null
      }
      this.listLoading = true
      logList(this.listQuery).then(response => {
        if(response.code == 200){
          this.list = response.data.records
          this.total = response.data.total
          this.pageSize = response.data.size
          this.currentPage = response.data.current
          this.listLoading = false   
        }else{
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
    deleteLog(id){
      this.$confirm('确认删除?')
        .then(_ => {
         deleteLog(id).then(response => {
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
    batchDelete(rows){
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