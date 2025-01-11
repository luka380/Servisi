<template>
  <div class="settings-view">
    <h1>Settings</h1>
    <form @submit.prevent="updateSettings">
      <h3>Update Restaurant Settings</h3>
      <input v-model="settings.name" placeholder="Restaurant Name" required />
      <input v-model="settings.address" placeholder="Address" required />
      <input v-model="settings.phone" placeholder="Phone Number" required />
      <button type="submit">Update</button>
    </form>
  </div>
</template>

<script setup lang="ts">
const settings: {
  name: ''
  address: ''
  phone: ''
}

function updateSettings() {}
</script>

<style scoped>
.settings-view {
  padding: 20px;
}

form {
  margin-top: 20px;
  display: flex;
  flex-direction: column;
  gap: 10px;
}

input,
button {
  padding: 10px;
  font-size: 16px;
}
</style>
