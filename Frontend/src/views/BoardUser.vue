<template>
  <div class="todo-lists-card card">
    <div class="card-header">
      <h2>All To-Do Lists</h2>
    </div>
    <div class="card-body">
      <ul v-if="toDoLists && toDoLists.length">
        <li
          v-for="toDoList in toDoLists"
          :key="toDoList.id"
          class="list-item"
        >
        <router-link :to="{ name: 'ToDoList', params: { id: toDoList.id, tasks: toDoList.tasks } }">
            {{ toDoList.title }}
          </router-link>
          <button @click="deleteList(toDoList.id)" class="delete-button">Delete</button>
        </li>
      </ul>
      <div v-else class="no-lists-message">
        <p>No to-do lists available.</p>
      </div>
    </div>
    <div class="card-footer">
      <h1>Create New To-Do List</h1>
      <form @submit.prevent="createToDoList" class="new-list-form">
        <label for="title">Title:</label>
        <input type="text" v-model="newToDoList.title" required class="list-title-input">
        <button type="submit" class="create-button">Create</button>
      </form>
    </div>
  </div>
</template>

<script>
import UserService from '@/services/user.service'; 

export default {
  name: 'ToDoLists',
  computed: {
    currentUser() {
      return this.$store.state.auth.user;
    },
    userId() {
      return this.currentUser.id; 
    }
  },
  data() {
    return {
      newToDoList: {
        title: '',
      },
      toDoLists: [],
    };
  },
  methods: {
    async createToDoList() {
      const response = await UserService.createList(this.userId, this.newToDoList.title);
      this.toDoLists.push(response.data);
      this.newToDoList.title = '';
    },
    async deleteList(listId) {
      await UserService.deleteList(listId);
      this.toDoLists = this.toDoLists.filter(list => list.id !== listId);
    },
  },
  async created() {
    const response = await UserService.getUserLists(this.userId);
    this.toDoLists = response.data;
  },
};
</script>

<style scoped>
.card {
  height: 1000px;
  background-color: #fff;
  border: 1px solid #ddd;
  border-radius: 4px;
  box-shadow: 0 1px 3px rgba(0, 0, 0, 0.1);
  padding: 1rem;
  margin-bottom: 1rem;
}
.card-header {
  border-bottom: 1px solid #eee;
  padding-bottom: 0.75rem;
  margin-bottom: 0.75rem;
}
.card-body,
.card-footer {
  display: flex;
  flex-direction: column;
  gap: 1rem;
}
.todo-lists-card {
  max-width: 600px;
  margin: 0 auto;
}
.card-header h2 {
  font-size: 1.25rem;
  margin: 0;
}
.list-item {
  list-style: none;
  padding: 0.5rem;
  border-bottom: 3px solid #eee;
  cursor: pointer;
  transition: background-color 0.2s ease-in-out;
}
.list-item:hover {
  background-color: #f5f5f5;
}
.no-lists-message {
  text-align: center;
  color: #aaa;
}
.new-list-form {
  display: flex;
  flex-direction: column;
  gap: 0.5rem;
}
.list-title-input{
  padding: 0.5rem;
  border: 1px solid #ddd;
  border-radius: 4px;
}
.list-title-input {
  flex: 1;
}
.delete-button {
  background: none;
  border: none;
  color: #f00; 
  cursor: pointer;
}

.delete-button:hover {
  color: #c00; 
}
