<template>
  <div class="container">
    <div class="task">
      <div class="title">
        <h1>To Do List</h1>
      </div>
      <div class="form">
        <input type="text" placeholder="New Task" v-model="newTask" @keyup.enter="addTask" />
        <button @click="addTask"><i class="fas fa-plus"></i></button>
      </div>
      <div class="taskItems">
        <ul>
          <li v-for="(task, index) in tasks" :key="task.id">
            <div class="task-content">
              <!-- <button @click.self="completeTask(task)" :class="className(task)"> -->
                <i class="far fa-circle"></i> {{ task.title }}
              <!-- </button> -->

              <button @click="deleteTask(task.id)" class="delete-button">  <i class="far fa-trash-alt"></i>Delete</button>
            </div>
          </li>
        </ul>
      </div>
      <div class="pendingTasks">
        <span>Pending Tasks: {{ incomplete }}</span>
      </div>
    </div>
  </div>
</template>


<script>
import UserService from '@/services/user.service'; 

export default {
  name: 'Task',
  data() {
    return {
      tasks: [],
      newTask: "",
      listId: this.$route.params.id, 
    };
  },
  computed: {
    incomplete() {
      return this.tasks.filter(task => !task.completed).length;
    },
  },
  methods: {
    async addTask() {
      if (this.newTask.trim()) {
        const response = await UserService.createTask(this.listId, this.newTask, 'NOT_STARTED');
        this.tasks.push(response.data);
        this.newTask = "";
      }
    },
    completeTask(task) {
      task.completed = !task.completed;
    },
    async deleteTask(taskId) {
      try {
        await UserService.deleteTask(taskId);
        this.tasks = this.tasks.filter(task => task.id !== taskId);
      } catch (error) {
        console.error("Error deleting task:", error);
      }
    },
    clearCompleted() {
      this.tasks = this.tasks.filter(task => !task.completed);
    },
    clearAll() {
      this.tasks = [];
    },
    className(task) {
      return task.completed ? 'toggle toggle-completed' : 'toggle';
    },
  },
  created() {
    if (this.$route.params.tasks !== undefined) {
      this.tasks = this.$route.params.tasks;
    }
  }
};
</script>

<style>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

.taskItems li {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  margin: 0 0 20px;
}


body {
  font: 15px/1.4 "Poppins", sans-serif;
  background: #4d5050;
  color: #333;
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

.container {
  max-width: 480px;
  padding: 0 15px;
}

input[type="text"] {
  width: 100%;
  height: 50px;
  font: 15px/1.4 "Poppins", sans-serif;
  padding: 15px;
  background: #f3f3f3;
  color: #333;
  border: 2px solid black;
  border-radius: 10px;
  transition: border 0.3s linear;
  margin-bottom: 20px;
}

input[type="text"]:focus {
  outline: none;
  border: 1px solid #4ec5c1;
}

button {
  width: 200px;
  cursor: pointer;
  font: 15px/1.4 "Poppins", sans-serif;
  transition: all 0.3s linear;
  color: #4ec5c1;
  background-color: rgb(67, 67, 67);
  border-radius: 50px;
}

button:hover {
  background-color: #4ec5c1;
  color: black;
  font-weight: 600;
}

button:focus {
  outline: none;
}

p {
  font-weight: 400;
}

h1 {
  font-size: 22px;
}

ul {
  list-style: none;
  margin: 0;
  padding: 0;
}

.task {
  background: #fff;
  border-radius: 25px;
  padding: 30px;
  box-shadow: 0px 0px 40px 0px rgba(0, 0, 0, 0.1);
}

.title {
  text-align: center;
  margin: 0 0 20px;
}

.form {
  position: relative;
  margin: 0 0 30px;
}

.form button {
  background: none;
  border: none;
  color: #4ec5c1;
  font-size: 18px;
  position: absolute;
  top: 50%;
  right: 20px;
  transform: translateY(-50%);
}

.delete-button {
  background: none;
  border: none;
  color: #f00;
  margin-left: 150px;
  cursor: pointer;
}

.delete-button:hover {
  color: #c00;
}

.clearBtns {
  display: flex;
  justify-content: space-between;
  margin: 0 0 20px;
}

.clearBtns button {
  width: 100%;
  background: #4ec5c1;
  color: #fff;
  border: none;
  border-radius: 10px;
  padding: 10px;
  margin: 0 5px;
}

.clearBtns button:hover {
  background: #333;
}

.pendingTasks {
  padding: 0 6px;
}

.taskItems {
  padding: 0 10px;
}

.taskItems li {
  display: flex;
  justify-content: space-between;
  margin: 0 0 20px;
}

.taskItems button {
  background: none;
  border: none;
}

.taskItems button:hover {
  color: #4ec5c1;
}

.taskItems .toggle i {
  margin: 0 10px 0 0;
  font-size: 14px;
}

.toggle-completed {
  text-decoration: line-through;
}
</style>