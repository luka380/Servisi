<template>
  <div>
    <!-- Navbar -->
    <nav class="navbar">
      <div class="navbar-logo">
        <h2>Client Dashboard</h2>
      </div>
      <div class="navbar-links">
        <button @click="showNotifications">Notifications</button>
        <button @click="viewReservations">Current Reservations</button>
        <button @click="openSearch">Search</button>
        <button @click="viewRewards">Rewards</button>
        <button @click="openSettings">Settings</button>
      </div>
    </nav>

    <!-- Main Content -->
    <div class="content">
      <h1>Welcome to Your Dashboard!</h1>
      <div v-if="currentView === 'notifications'">Notifications Content</div>
      <div v-if="currentView === 'reservations'">Reservations Content</div>
      <div v-if="currentView === 'search'">Search Content</div>
      <div v-if="currentView === 'rewards'">Rewards Content</div>
      <div v-if="currentView === 'settings'">Settings Content</div>
    </div>
  </div>
</template>

<script lang="ts">
export default {
  name: 'ClientView',
  data() {
    return {
      currentView: '',
    };
  },
  methods: {
    showNotifications() {
      this.currentView = 'notifications';
      alert("Show Notifications");
    },
    viewReservations() {
      this.currentView = 'reservations';
      alert("View Current Reservations");
    },
    openSearch() {
      this.currentView = 'search';
      alert("Open Search");
    },
    viewRewards() {
      this.currentView = 'rewards';
      alert("View Rewards");
    },
    openSettings() {
      this.currentView = 'settings';
      alert("Open Settings");
    }
  }
}
</script>

<style scoped>
/* Navbar Styles */
.navbar {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 15px;
  background-color: #007bff;
  color: white;
}

.navbar-logo h2 {
  margin: 0;
}

.navbar-links button {
  background-color: transparent;
  color: white;
  border: 1px solid white;
  padding: 10px 20px;
  margin-left: 10px;
  cursor: pointer;
  border-radius: 5px;
  transition: background-color 0.3s;
}

.navbar-links button:hover {
  background-color: #0056b3;
}

.navbar-links button:focus {
  outline: none;
}

/* Content Styles */
.content {
  padding: 20px;
  text-align: center;
}

h1 {
  font-size: 24px;
  color: #333;
}
</style>
