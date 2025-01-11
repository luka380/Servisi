<template>
  <div class="table-types-view">
    <h1>Table Types</h1>
    <ul class="table-types-list">
      <li v-for="type in tableTypes" :key="type.id" class="table-type-item">
        <h3>{{ type.name }}</h3>
        <p>Seating Capacity: {{ type.seatingCapacity }}</p>
        <button @click="deleteTableType(type.id)">Delete</button>
      </li>
    </ul>
    <form @submit.prevent="createTableType">
      <h3>Create Table Type</h3>
      <input v-model="newTableType.name" placeholder="Name" required />
      <input
        v-model="newTableType.seatingCapacity"
        type="number"
        placeholder="Seating Capacity"
        required
      />
      <button type="submit">Create</button>
    </form>
  </div>
</template>

<script setup lang="ts">
const tableTypes: []
const newTableType: {
  name: ''
  seatingCapacity: 0
}

function createTableType() {

}

function deleteTableType(id: number) {

}
</script>

<style scoped>
.table-types-view {
  padding: 20px;
}

.table-types-list {
  list-style-type: none;
  padding: 0;
}

.table-type-item {
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
