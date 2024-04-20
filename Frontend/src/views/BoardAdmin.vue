<template>
  <div class="container mt-5">
    <header class="jumbotron">
      <h3>Admin Dashboard</h3>
    </header>
    <table class="table table-hover">
      <thead class="thead-light">
        <tr>
          <th scope="col">Username</th>
          <th scope="col">Email</th>
          <th scope="col">Actions</th>
        </tr>
      </thead>
      <tbody v-if="users.length > 0">
        <tr v-for="user in users" :key="user.id">
          <td class="text-center">{{ user.username }}</td>
          <td class="text-center">{{ user.email }}</td>
          <td class="text-center">
            <button v-if="user.role !== 'admin'" class="btn btn-sm btn-pink btn-block" @click="makeAdmin(user)">Make Admin</button>
          </td>
        </tr>
      </tbody>
      <div v-else class="alert alert-info">
        No users found.
      </div>
    </table>
  </div>
</template>


<script>
import UserService from '../services/user.service';

export default {
  name: 'Admin',
  data() {
    return {
      users: []
    };
  },
  methods: {
    makeAdmin(user) {
      UserService.assignAdminRole(user.id)
        .then(() => {
          user.role = 'admin';
          alert('User role updated successfully');
        })
        .catch(error => {
          console.error("Failed to update user role:", error); // eslint-disable-line no-console
        });
    },
    fetchUsers() {
      UserService.getUsersList()
        .then(response => {
          this.users = response.data;
        })
        .catch(error => {
          console.error("Failed to fetch users:", error);
        });
    }
  },
  mounted() {
    this.fetchUsers();
  }
};
</script>

<style scoped>
.btn-pink {
  background-color: rgb(0, 0, 0);
  color: white;
  width: 100px;
}
.btn-pink:hover {
  background-color: pink;
  color: black;
  font-weight: 500;
}
th{
  text-align: center
}
td{
  text-align: center;
}
.btn-block{
  display: inline;
}
</style>
