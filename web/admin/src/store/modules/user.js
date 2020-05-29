import { login, logout, getInfo } from '@/api/user'
import { getToken, setToken, removeToken } from '@/utils/auth'
// import router, { resetRouter } from '@/router'
import { resetRouter } from '@/router'
import { Message } from 'element-ui'

const state = {
  token: getToken(),
  userId: '',
  name: '',
  avatar: '',
  compId: '',
  deptId: '',
  introduction: '',
  roles: []
}

const mutations = {
  SET_TOKEN: (state, token) => {
    state.token = token
  },
  SET_USER: (state, userId) => {
    state.userId = userId
  },
  SET_INTRODUCTION: (state, introduction) => {
    state.introduction = introduction
  },
  SET_NAME: (state, name) => {
    state.name = name
  },
  SET_DEPT: (state, deptId) => {
    state.deptId = deptId
  },
  SET_COMP: (state, compId) => {
    state.compId = compId
  },
  SET_AVATAR: (state, avatar) => {
    state.avatar = avatar
  },
  SET_ROLES: (state, roles) => {
    state.roles = roles
  }
}

const actions = {
  // user login TODO
  login({ commit }, userInfo) {
    const { username, password, code, randomStr } = userInfo
    return new Promise((resolve, reject) => {
      login({ username: username.trim(), password: password ,code: code, randomStr: randomStr}).then(response => {
        // const { data } = response
        const data = response
        if(data.code == 200){
          commit('SET_TOKEN', data.data)
          // commit('SET_TOKEN', data.token)
          // setToken(data.token)
          setToken(data.data)
          resolve()
        }else{
          Message.error(data.message)
        }
      }).catch(error => {
        reject(error)
        Message.error('登录失败')
      })
    })
  },

  // get user info
  getInfo({ commit, state }) {
    return new Promise((resolve, reject) => {
      getInfo(state.token).then(response => {
        const { data } = response
        if (!data) {
          reject('Verification failed, please Login again.')
        }
        // const { roles, name, avatar, introduction } = data
        const { roles, sysUser } = data
        // roles must be a non-empty array
        if (!roles || roles.length <= 0) {
          reject('getInfo: roles must be a non-null array!')
        }
        commit('SET_ROLES', roles)
        commit('SET_USER',sysUser.id)
        commit('SET_NAME', sysUser.nickname)
        commit('SET_AVATAR', sysUser.headImage)
        if(sysUser.deptId){
          commit('SET_DEPT',sysUser.deptId)
        }
        if(sysUser.compId){
          commit('SET_COMP',sysUser.compId)
        }
        // commit('SET_NAME', name)
        // commit('SET_AVATAR', avatar)
        // commit('SET_INTRODUCTION', introduction)
        resolve(data)
      }).catch(error => {
        reject(error)
      })
    })
  },

  // user logout
  logout({ commit, state, dispatch }) {
    return new Promise((resolve, reject) => {
      logout(state.token).then(() => {
        commit('SET_TOKEN', '')
        commit('SET_ROLES', [])
        removeToken()
        resetRouter()

        // reset visited views and cached views
        // to fixed https://github.com/PanJiaChen/vue-element-admin/issues/2485
        dispatch('tagsView/delAllViews', null, { root: true })

        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },

  // remove token
  resetToken({ commit }) {
    return new Promise(resolve => {
      commit('SET_TOKEN', '')
      commit('SET_ROLES', [])
      removeToken()
      resolve()
    })
  },

  // dynamically modify permissions
  // changeRoles({ commit, dispatch }, role) {
  //   return new Promise(async resolve => {
  //     const token = role + '-token'

  //     commit('SET_TOKEN', token)
  //     setToken(token)

  //     const { roles } = await dispatch('getInfo')

  //     resetRouter()

  //     // generate accessible routes map based on roles
  //     const accessRoutes = await dispatch('permission/generateRoutes', roles, { root: true })

  //     // dynamically add accessible routes
  //     router.addRoutes(accessRoutes)

  //     // reset visited views and cached views
  //     dispatch('tagsView/delAllViews', null, { root: true })

  //     resolve()
  //   })
  // }

  // 动态修改权限
  changeRoles({ commit }, role) {
    return new Promise(resolve => {
      commit('SET_TOKEN', role)
      setToken(role)
      getInfo(role).then(response => {
        const { data } = response
        const { roles, sysUser } = data
        commit('SET_ROLES', roles)
        commit('SET_NAME', sysUser.nickname)
        commit('SET_AVATAR', sysUser.headImage)
        // commit('SET_ROLES', data.roles)
        // commit('SET_NAME', data.name)
        // commit('SET_AVATAR', data.avatar)
        // commit('SET_INTRODUCTION', data.introduction)
        resolve()
      })
    })
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}
