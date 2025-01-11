<template>
  <div class="home-view">
    <h1>Welcome to the Reservation System</h1>
    <p>Are you a client or a manager?</p>
    <div class="buttons">
      <router-link to="/client-login"><button>Client</button></router-link>
      <router-link to="/manager-login"><button>Manager</button></router-link>
    </div>
  </div>
</template>

<script lang="ts">
export default {
  name: 'HomeView',
};
</script>

<style scoped>
.home-view {
  text-align: center;
  padding: 20px;
}
button {
  width: auto;
  align-self: center;
  padding: 12px;
  background-color: #007BFF;
  color: white;
  border: none;
  border-radius: 8px;
  font-size: 1.2rem;
  cursor: pointer;
  transition: all 0.3s ease;
}
button:hover {
  background-color: #0056b3;
  transform: translateY(-2px);
}

.buttons {
  display: flex;
  justify-content: center;
  gap: 10px;
}
</style>
