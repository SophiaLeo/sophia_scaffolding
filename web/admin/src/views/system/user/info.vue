<template>
  <div class="app-container">
    <div class="filter-container">
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="用户名称" prop="username">
          <el-input v-if="!this.userId" v-model.trim="form.username" placeholder="请输入用户名称" clearable autocomplete="off"/>
          <el-input v-if="this.userId" readonly v-model.trim="form.username" placeholder="请输入用户名称" clearable autocomplete="off"/>
        </el-form-item>
        <el-form-item label="用户昵称" prop="nickname">
          <el-input v-model.trim="form.nickname" placeholder="请输入用户昵称" clearable autocomplete="off"/>
        </el-form-item>
        <el-form-item v-if="!this.userId" label="密码" prop="password">
          <el-input type="password" v-model="form.password" placeholder="请输入密码" clearable autocomplete="off"/>
        </el-form-item>
        <el-form-item v-if="!this.userId" label="确认密码" prop="checkPassword">
          <el-input type="password" v-model="form.checkPassword" placeholder="请输入确认密码" clearable autocomplete="off"/>
        </el-form-item>
        <el-form-item label="手机号" prop="phone">
          <el-input v-model.trim="form.phone" placeholder="请输入手机号" clearable autocomplete="off"/>
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input v-model.trim="form.email" placeholder="请输入邮箱" clearable autocomplete="off"/>
        </el-form-item>
        <el-form-item label="年龄" prop="age">
          <el-input v-model.number="form.age" placeholder="请输入年龄" clearable autocomplete="off"/>
        </el-form-item>
         <el-form-item label="性别" prop="sex">
          <el-radio-group v-model="form.sex">
            <el-radio v-for="(item, index) in sexList" :key="index" :label="item.value">{{ item.name }}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="生日" prop="birthday" >
          <el-date-picker type="datetime" placeholder="请选择生日" v-model="form.birthday" value-format="yyyy-MM-dd HH:mm:ss" style="width: 100%"/>
        </el-form-item>
        <el-form-item label="地区" prop="area" style="text-align: left;">
          <v-distpicker
            :province="form.province"
            :city="form.city"
            :area="form.area"
            class="threeSlect"
            @province="getProvince"
            @city="getCity"
            @area="getArea"/>
        </el-form-item>
        <el-form-item label="地址" prop="address">
          <el-input v-model.trim="form.address" placeholder="请输入地址" clearable autocomplete="off"/>
        </el-form-item>
        <!-- <el-form-item label="角色" prop="roleId">
          <el-select v-model="form.roleId" placeholder="请选择角色" clearable autocomplete="off" style="width: 100%">
            <el-option v-for="(item, index) in roleList" :key="index" :label="item.roleName" :value="item.id" />
          </el-select>
        </el-form-item> -->
        <el-form-item label="公司" prop="compId">
          <el-select v-model="form.compId" placeholder="请选择公司" clearable autocomplete="off" style="width: 100%">
            <el-option v-for="(item, index) in companyList" :key="index" :label="item.fullName" :value="item.id" />
          </el-select>
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
import { userList, deleteUser, deleteBatch, updateStatus } from '@/api/user'
import { dictByType } from '@/api/dict'
import { roleSelectList } from '@/api/role'
import { companySelectList } from '@/api/company'
import store from '@/store'

export default {
  name: 'UserInfo',
  components: { VDistpicker },
  data() {
    var validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'));
      } else {
        if (this.form.checkPassword !== '') {
          this.$refs.form.validateField('checkPassword');
        }
        callback();
      }
    };
    var validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'));
      } else if (value !== this.form.password) {
        callback(new Error('两次输入密码不一致!'));
      } else {
        callback();
      }
    };
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
    };
    return {
      loading: true,
      userId: this.$route.query.id,
      form: {
        province: '省',
        city: '市',
        area: '区'
      },
      rules: {
        username: [{ required: true, message: '请输入用户名称', trigger: 'blur' }],
        nickname: [{ required: true, message: '请输入用户昵称', trigger: 'blur' }],
        password: [{ validator: validatePass, trigger: 'blur' }],
        checkPassword: [{ validator: validatePass2, trigger: 'blur' }],
        age: [{ required: true, message: '请输入年龄', trigger: 'blur' }],
        area: [{ required: true, validator: check, trigger: 'change' }]
      },
      roleList: [],
      companyList: [],
      sexList: []
    }
  },
  created() {
    // this.queryRole()
    this.queryCompany()
    this.queryDictByType()
  },
  methods: {
    init(){
      //编辑时回显
    },
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          alert('submit!')
        } else {
          console.log('error submit!!')
          return false
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields()
    },
    cancelForm(formName) {
      this.$refs[formName].resetFields()
      this.$router.push({name: 'User'})
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
    queryDictByType() {
      let type = 'sex'
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
.threeSlect select{
  float: left;
  width: 31%;
  margin-right: 2%;
}
</style>