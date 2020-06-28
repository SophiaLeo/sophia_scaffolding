<template>
  <div class="app-container">
    <div class="filter-container">
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="角色名称" prop="roleName">
          <el-input v-model.trim="form.roleName" placeholder="请输入角色名称" clearable autocomplete="off" />
        </el-form-item>
        <el-form-item label="角色编号" prop="roleCode">
          <el-input v-model.trim="form.roleCode" placeholder="请输入角色编号" clearable autocomplete="off" />
        </el-form-item>
        <el-form-item label="描述" prop="description">
          <el-input v-model.trim="form.description" placeholder="请输入描述" clearable autocomplete="off" />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm('form')">确定</el-button>
          <el-button type="info" @click="cancelForm('form')">取消</el-button>
          <el-button type="warning" @click="resetForm('form')">重置</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>

import { roleInfo, saveRole, updateRole } from '@/api/role'
import store from '@/store'

export default {
  name: 'RoleInfo',
  data() {
    return {
      loading: true,
      roleId: this.$route.query.id,
      form: {
        roleType: 1
      },
      rules: {
        roleName: [{ required: true, message: '角色名称不能为空', trigger: ['change', 'blur'] }],
        roleCode: [{ required: true, message: '角色编号不能为空', trigger: ['change', 'blur'] }]
      }
    }
  },
  created() {
    this.init()
  },
  methods: {
    init() {
      // 编辑时回显
      if (this.roleId) {
        roleInfo(this.roleId).then(res => {
          this.form = res.data
        })
      }
    },
    submitForm(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          // 编辑
          if (this.roleId) {
            updateRole(this.form).then(response => {
              if (response.code == 200) {
                this.$message.success(response.message)
                this.$router.push({ name: 'Role' })
              } else {
                this.$message.error(response.message)
              }
            })
          } else {
            // 新增
            saveRole(this.form).then(response => {
              if (response.code == 200) {
                this.$message.success(response.message)
                this.$router.push({ name: 'Role' })
              } else {
                this.$message.error(response.message)
              }
            })
          }
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    resetForm(formName) {
      this.$refs[formName].resetFields()
    },
    cancelForm(formName) {
      this.$refs[formName].resetFields()
      this.$router.push({ name: 'Role' })
    }
  }
}
</script>
