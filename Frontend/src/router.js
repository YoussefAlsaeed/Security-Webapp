import Vue from 'vue';
import Router from 'vue-router';
import Login from './views/Login.vue';
import Register from './views/Register.vue';
import ToDoList from './views/ToDoList.vue';
Vue.use(Router);

export const router = new Router({
  mode: 'history',
  routes: [
    {
      path: '/login',
      component: Login
    },
    {
      path: '/register',
      component: Register
    },
    {
      path: '/profile',
      name: 'profile',
      component: () => import('./views/Profile.vue')
    },
    {
      path: '/admin',
      name: 'admin',
      component: () => import('./views/BoardAdmin.vue')
    },
    {
      path: '/user',
      name: 'user',
      component: () => import('./views/BoardUser.vue')
    },
    {
      path: '/todolist/:id',
      name: 'ToDoList',
      component: ToDoList, 
      props: true 
    },
  ]
});

