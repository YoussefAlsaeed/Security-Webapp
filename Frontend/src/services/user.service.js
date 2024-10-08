import axios from 'axios';
import authHeader from './auth-header';

const API_URL = 'http://localhost:8080/api/';
class UserService {


  getUserBoard() {
    return axios.get(API_URL + 'user', { headers: authHeader() });
  }

  getAdminBoard() {
    return axios.get(API_URL + 'admin', { headers: authHeader() });
  }
  assignAdminRole(userId) {
    return axios.post(API_URL + 'admin/assignAdmin', { userId }, { headers: authHeader() });
  }
  getUsersList() {
    return axios.get(API_URL + 'admin/getUsers', { headers: authHeader() });
  }

  getUserLists(userId) {
    return axios.request({
      url: `${API_URL}user/lists?userId=${userId}`,
      method: 'GET',
      headers: authHeader()
    });
  }

  createList(userId, title) {
    return axios.post(API_URL + 'user/createList', { userId, title }, { headers: authHeader() });
  }
  createTask(listId, title, status) {
    return axios.post(API_URL + 'user/createTask', { listId, title, status }, { headers: authHeader() });
  }
  deleteTask(taskId) {
    return axios.delete(API_URL + 'user/deleteTask', {
      headers: authHeader(),
      data: { taskId }
    });
  }
  deleteList(listId) {
    return axios.delete(API_URL + 'user/deleteList', {
      headers: authHeader(),
      data: { listId }
    });
  }
}
export default new UserService();
