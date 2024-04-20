<template>
  <div id="app">
    <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
      <div class="container">
        <router-link to="/" class="navbar-brand">TO-DO LIST</router-link>

        <div class="collapse navbar-collapse">
          <ul class="navbar-nav me-auto">
            <li v-if="showAdminBoard" class="nav-item">
              <router-link to="/admin" class="nav-link">Admin</router-link>
            </li>
            <li class="nav-item" v-if="showUserBoard && currentUser">
              <router-link to="/user" class="nav-link">User</router-link>
            </li>
          </ul>

          <ul class="navbar-nav ml-auto">
            <li v-if="!currentUser" class="nav-item">
              <router-link to="/register" class="nav-link">
                <i class="fas fa-user-plus"></i> Sign Up
              </router-link>
            </li>
            <li v-if="!currentUser" class="nav-item">
              <router-link to="/login" class="nav-link">
                <i class="fas fa-sign-in-alt"></i> Login
              </router-link>
            </li>
            <li v-if="currentUser" class="nav-item">
              <router-link to="/profile" class="nav-link">
                <i class="fas fa-user"></i> {{ currentUser.username }}
              </router-link>
            </li>
            <li v-if="currentUser" class="nav-item">
              <a href="#" class="nav-link" @click.prevent="logOut">
                <i class="fas fa-sign-out-alt"></i> Log Out
              </a>
            </li>
          </ul>
        </div>
      </div>
    </nav>

    <div class="container">
      <router-view></router-view>
    </div>
  </div>
</template>

<script>
export default {
  computed: {
    currentUser() {
      return this.$store.state.auth.user;
    },
    showAdminBoard() {
      return this.currentUser && this.currentUser.roles && this.currentUser.roles.includes('ROLE_ADMIN');
    },
    showUserBoard() {
      return this.currentUser && this.currentUser.roles && this.currentUser.roles.includes('ROLE_USER');
    }
  },
  methods: {
    logOut() {
      this.$store.dispatch('auth/logout');
      this.$router.push('/login');
    }
  }
};
</script>

<style scoped>
.navbar {
  border-bottom: 1px solid rgba(255, 255, 255, 0.1);
}

.navbar-brand {
  font-weight: bold;
}

.nav-link {
  color: #fff;
}

.nav-link:hover {
  color: rgba(255, 255, 255, 0.75);
}

.navbar-nav .nav-item {
  margin-right: 10px;
}

.fa-user-plus, .fa-sign-in-alt, .fa-user, .fa-sign-out-alt {
  margin-right: 5px;
}
</style>
