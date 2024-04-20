<template>
  <div class="profile-container">
    <header class="profile-header">
      <h3 class="profile-title">
        <strong>{{currentUser.username}}</strong> Profile
      </h3>
    </header>
    <div class="profile-details">
      <p class="profile-email">
        <strong>Email:</strong> {{currentUser.email}}
      </p>
      <div class="profile-authorities">
        <strong>Your role:</strong>
        <ul>
          <li v-for="(role, index) in currentUser.roles" :key="index" class="profile-role">{{role}}</li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Profile',
  computed: {
    currentUser() {
      return this.$store.state.auth.user;
    }
  },
  mounted() {
    if (!this.currentUser) {
      this.$router.push('/login');
    }
  }
};
</script>

<style scoped>
.profile-container {
  max-width: 600px;
  margin: 0 auto;
  padding: 20px;
  background-color: #f9f9f9;
  border-radius: 8px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.profile-header {
  text-align: center;
  margin-bottom: 20px;
}

.profile-title {
  font-size: 24px;
  color: #333;
}

.profile-details {
  background-color: #fff;
  padding: 20px;
  border-radius: 8px;
}

.profile-email {
  margin-bottom: 10px;
}

.profile-authorities {
  margin-top: 20px;
}

.profile-authorities ul {
  list-style-type: none;
  padding: 0;
}

.profile-role {
  margin-bottom: 5px;
  color: #666;
}
</style>
