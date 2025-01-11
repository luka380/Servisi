<template>
  <div class="restaurant-details">
    <h1>Restaurant Details</h1>
    <div class="buttons">
      <button @click="goToView('Zones')">Zones</button>
      <button @click="goToView('Schedule')">Schedule</button>
      <button @click="goToView('TableTypes')">Table Types</button>
      <button @click="goToView('Tables')">Tables</button>
      <button @click="goToView('Reservations')">Reservations</button>
      <button @click="goToView('Settings')">Settings</button>
    </div>
  </div>
</template>

<script setup lang="ts">
function goToView(view: string) {
      this.$router.push({ name: view });
    }
</script>

<style scoped>
.restaurant-details {
  padding: 20px;
}

.buttons {
  display: flex;
  gap: 10px;
}

button {
  padding: 10px 20px;
  border: none;
  border-radius: 5px;
  background-color: #007BFF;
  color: white;
  cursor: pointer;
  transition: background-color 0.3s;
}

button:hover {
  background-color: #0056b3;
}
</style>
