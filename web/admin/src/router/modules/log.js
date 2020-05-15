/** When your routing table is too long, you can split it into small modules**/

import Layout from '@/layout'

const logRouter = {
  path: '/log',
  component: Layout,
  // redirect: 'noRedirect',
  redirect: '/log/index',
  name: 'Log',
  meta: {
    title: 'log',
    icon: 'log'
  },
  children: [
    {
      path: '/log/index',
      component: () => import('@/views/log/index'),
      name: 'Logger',
      meta: { title: 'logger', icon: 'log', noCache: true }
    }
  ]
}

export default logRouter
