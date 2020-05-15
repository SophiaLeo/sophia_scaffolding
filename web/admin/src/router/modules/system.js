/** When your routing table is too long, you can split it into small modules**/

import Layout from '@/layout'

const systemRouter = {
  path: '/system',
  component: Layout,
  // redirect: 'noRedirect',
  redirect: '/system/user',
  name: 'System',
  meta: {
    title: 'system',
    icon: 'system'
  },
  children: [
    {
      path: '/system/user',
      component: () => import('@/views/system/user/index'),
      name: 'User',
      meta: { title: 'user', icon: 'user', noCache: true }
    },
    {
      path: '/system/user/save',
      component: () => import('@/views/system/user/info'),
      name: 'UserSave',
      hidden: true,
      meta: { title: 'userSave', icon: 'user', noCache: true }
    },
    {
      path: '/system/user/update',
      component: () => import('@/views/system/user/info'),
      name: 'UserUpdate',
      hidden: true,
      meta: { title: 'userUpdate', icon: 'user', noCache: true }
    }
  ]
}

export default systemRouter
