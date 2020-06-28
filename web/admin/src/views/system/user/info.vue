<template>
  <div class="app-container">
    <div class="filter-container">
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="用户名称" prop="username">
          <el-input
            v-model.trim="form.username"
            placeholder="请输入用户名称"
            clearable
            autocomplete="off"
          />
        </el-form-item>
        <el-form-item label="用户昵称" prop="nickname">
          <el-input
            v-model.trim="form.nickname"
            placeholder="请输入用户昵称"
            clearable
            autocomplete="off"
          />
        </el-form-item>
        <el-form-item v-if="!this.userId" label="密码" prop="password">
          <el-input
            v-model="form.password"
            type="password"
            placeholder="请输入密码"
            clearable
            autocomplete="off"
          />
        </el-form-item>
        <el-form-item v-if="!this.userId" label="确认密码" prop="checkPassword">
          <el-input
            v-model="form.checkPassword"
            type="password"
            placeholder="请输入确认密码"
            clearable
            autocomplete="off"
          />
        </el-form-item>
        <el-form-item label="手机号" prop="phone">
          <el-input v-model.trim="form.phone" placeholder="请输入手机号" clearable autocomplete="off" />
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input v-model.trim="form.email" placeholder="请输入邮箱" clearable autocomplete="off" />
        </el-form-item>
        <el-form-item label="性别" prop="sex">
          <el-radio-group v-model="form.sex">
            <el-radio
              v-for="(item, index) in sexList"
              :key="index"
              :label="item.value"
            >{{ item.name }}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="生日" prop="birthday">
          <el-date-picker
            v-model="form.birthday"
            type="datetime"
            placeholder="请选择生日"
            value-format="yyyy-MM-dd HH:mm:ss"
            style="width: 100%"
          />
        </el-form-item>
        <el-form-item label="地区" prop="area" style="text-align: left;">
          <v-distpicker
            :province="form.province"
            :city="form.city"
            :area="form.area"
            class="threeSlect"
            @province="getProvince"
            @city="getCity"
            @area="getArea"
          />
        </el-form-item>
        <el-form-item label="地址" prop="address">
          <el-input v-model.trim="form.address" placeholder="请输入地址" clearable autocomplete="off" />
        </el-form-item>
        <el-form-item label="角色" prop="roleId">
          <el-select v-model="form.roleId" placeholder="请选择角色" clearable autocomplete="off" style="width: 100%">
            <el-option v-for="(item, index) in roleList" :key="index" :label="item.roleName" :value="item.id" />
          </el-select>
        </el-form-item>
        <el-form-item label="公司" prop="compId">
          <el-select
            v-model="form.compId"
            placeholder="请选择公司"
            clearable
            autocomplete="off"
            style="width: 100%"
            @change="changeCompSelect"
          >
            <el-option
              v-for="(item, index) in companyList"
              :key="index"
              :label="item.fullName"
              :value="item.id"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="部门" prop="deptId">
          <span>
            <treeselect
              v-model="form.deptId"
              :clearable="clearable"
              :always-open="alwaysOpen"
              :options="treeDeptData"
              placeholder="请选择部门"
            />
          </span>
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
import VDistpicker from 'v-distpicker'
import Treeselect from '@riophae/vue-treeselect'
import '@riophae/vue-treeselect/dist/vue-treeselect.css'
import { checkUser, userInfo, saveUser, updateUser } from '@/api/user'
import { dictByType } from '@/api/dict'
import { roleSelectList } from '@/api/role'
import { companySelectList } from '@/api/company'
import { deptTree } from '@/api/dept'
import store from '@/store'
import { validUsername } from '@/utils/validate'

export default {
  name: 'UserInfo',
  components: { VDistpicker, Treeselect },
  data() {
    var validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'))
      } else if (value.length < 6) {
        callback(new Error('密码不能小于6位'))
      } else {
        if (this.form.checkPassword !== '') {
          this.$refs.form.validateField('checkPassword')
        }
        callback()
      }
    }
    var validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'))
      } else if (value !== this.form.password) {
        callback(new Error('两次输入密码不一致!'))
      } else if (value.length < 6) {
        callback(new Error('密码不能小于6位'))
      } else {
        callback()
      }
    }
    var validateUserName = (rule, value, callback) => {
      if (value == '') {
        callback(new Error('请输入用户名称'))
      } else if (!validUsername(value)) {
        callback(new Error('用户名称要求字母、数字、“_”、“.”、“@”的字串 长度为5-20个字符'))
      } else {
        const params = {
          id: this.userId,
          str: value,
          type: 1
        }
        checkUser(params).then(response => {
          if (response.code === 200) {
            callback()
          } else {
            callback(new Error(response.message))
          }
        })
      }
    }
    var validateNickName = (rule, value, callback) => {
      if (value == '') {
        callback(new Error('请输入用户昵称'))
      } else {
        const params = {
          id: this.userId,
          str: value,
          type: 2
        }
        checkUser(params).then(response => {
          if (response.code === 200) {
            callback()
          } else {
            callback(new Error(response.message))
          }
        })
      }
    }
    var validatePhone = (rule, value, callback) => {
      const reg = /^(13[0-9]|14[5|7]|15[0|1|2|3|4|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}$/
      if (!value) {
        callback(new Error('请输入手机号码'))
      } else if (!reg.test(value)) {
        callback(new Error('手机格式不正确'))
      } else {
        const params = {
          id: this.userId,
          str: value,
          type: 3
        }
        checkUser(params).then(response => {
          if (response.code === 200) {
            callback()
          } else {
            callback(new Error(response.message))
          }
        })
      }
    }
    var validateEmail = (rule, value, callback) => {
      const regEmail = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/
      if (!value) {
        callback()
      } else if (!regEmail.test(value)) {
        callback(new Error('邮箱格式不正确'))
      } else {
        callback()
      }
    }
    var check = (rule, value, callback) => {
      value = this.form.area
      if (this.form.province === '省') {
        callback(new Error('请选择省'))
      } else if (this.form.city === '市') {
        callback(new Error('请选择市'))
      } else if (value === '区') {
        callback(new Error('请选择区'))
      } else {
        callback()
      }
    }
    return {
      loading: true,
      userId: this.$route.query.id,
      form: {
        province: '省',
        city: '市',
        area: '区'
      },
      rules: {
        username: [{ required: true, validator: validateUserName, trigger: ['blur'] }],
        nickname: [{ required: true, validator: validateNickName, trigger: ['blur'] }],
        password: [{ validator: validatePass, trigger: ['change', 'blur'] }],
        checkPassword: [{ validator: validatePass2, trigger: ['change', 'blur'] }],
        phone: [{ required: true, validator: validatePhone, trigger: ['change', 'blur'] }],
        email: [{ validator: validateEmail, trigger: ['change', 'blur'] }],
        area: [{ required: true, validator: check, trigger: ['change'] }]
      },
      alwaysOpen: false,
      clearable: true,
      treeDeptData: [],
      roleList: [],
      companyList: [],
      sexList: []
    }
  },
  created() {
    this.queryRole()
    this.queryCompany()
    this.queryDictByType()
    this.init()
  },
  methods: {
    init() {
      // 编辑时回显
      if (this.userId) {
        userInfo(this.userId).then(res => {
          this.form = res.data
          if (res.data.deptId) {
            deptTree(res.data.compId).then(response => {
              this.treeDeptData = response.data
            })
          }
        })
      }
    },
    submitForm(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          // 编辑
          if (this.userId) {
            updateUser(this.form).then(response => {
              if (response.code == 200) {
                this.$message.success(response.message)
                this.$router.push({ name: 'User' })
              } else {
                this.$message.error(response.message)
              }
            })
          } else {
            // 新增
            saveUser(this.form).then(response => {
              if (response.code == 200) {
                this.$message.success(response.message)
                this.$router.push({ name: 'User' })
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
      this.$router.push({ name: 'User' })
    },
    getProvince(data) {
      this.form.province = data.value
    },
    getCity(data) {
      this.form.city = data.value
    },
    getArea(data) {
      this.form.area = data.value
    },
    changeCompSelect(compId) {
      if (compId) {
        deptTree(compId).then(res => {
          this.form.deptId = null
          this.treeDeptData = res.data
        })
      }
    },
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
    queryCompany() {
      companySelectList().then(res => {
        this.companyList = res.data
      })
    }
  }
}
</script>
<style scoped>
.threeSlect select {
  float: left;
  width: 31%;
  margin-right: 2%;
}
</style>
