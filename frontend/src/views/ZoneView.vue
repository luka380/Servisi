<template>
  <div class="zones-view">
    <h1>Zones</h1>
    <ul class="zones-list">
      <li v-for="zone in zones" :key="zone.id" class="zone-item">
        <h3>{{ zone.name }}</h3>
        <p>{{ zone.description }}</p>
        <p>Location: {{ zone.location }}</p>
        <p>Smoking: {{ zone.isSmoking ? 'Yes' : 'No' }}</p>
        <button @click="deleteZone(zone.id)">Delete</button>
      </li>
    </ul>
    <form @submit.prevent="createZone">
      <h3>Create Zone</h3>
      <input v-model="newZone.name" placeholder="Name" required />
      <input v-model="newZone.description" placeholder="Description" />
      <input v-model="newZone.location" placeholder="Location" required />
      <label>
        Smoking Allowed:
        <input type="checkbox" v-model="newZone.isSmoking" />
      </label>
      <button type="submit">Create</button>
    </form>
  </div>
</template>

<script setup lang="ts">
const zones: []
let newZone: {
  name: ''
  description: ''
  location: ''
  isSmoking: false
}

function createZone() {

}

function deleteZone(id: number) {

}
</script>

<style scoped>
.zones-view {
  padding: 20px;
}

.zones-list {
  list-style-type: none;
  padding: 0;
}

.zone-item {
  border: 1px solid #ddd;
  padding: 10px;
  margin-bottom: 10px;
  border-radius: 5px;
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
