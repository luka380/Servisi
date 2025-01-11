<template>
  <div class="tables-view">
    <h1>Tables</h1>
    <ul class="tables-list">
      <li v-for="table in tables" :key="table.id" class="table-item">
        <h3>{{ table.tableName }}</h3>
        <button @click="deleteTable(table.id)">Delete</button>
      </li>
    </ul>
    <form @submit.prevent="createTable">
      <h3>Create Table</h3>
      <input v-model="newTable.tableName" placeholder="Table Name" required />
      <select v-model="newTable.tableType" required>
        <option v-for="type in tableTypes" :key="type.id" :value="type.id">
          {{ type.name }}
        </option>
      </select>
      <select v-model="newTable.zone" required>
        <option v-for="zone in zones" :key="zone.id" :value="zone.id">
          {{ zone.name }}
        </option>
      </select>
      <button type="submit">Create</button>
    </form>
  </div>
</template>

<script setup lang="ts">
const tables: []
const tableTypes: []
const zones: []
const newTable: {
  tableName: ''
  tableType: ''
  zone: ''
}

function createTable() {}

function deleteTable(id: number) {}
</script>

<style scoped>
.tables-view {
  padding: 20px;
}

.tables-list {
  list-style-type: none;
  padding: 0;
}

.table-item {
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
button,
select {
  padding: 10px;
  font-size: 16px;
}
</style>
