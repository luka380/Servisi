<template>
  <div class="reservations-view">
    <h1>Reservations</h1>
    <ul class="reservations-list">
      <li
        v-for="reservation in reservations"
        :key="reservation.id"
        class="reservation-item"
      >
        <h3>Reservation for {{ reservation.guestName }}</h3>
        <p>Table: {{ reservation.tableName }}</p>
        <p>Date: {{ reservation.date }}</p>
        <button @click="deleteReservation(reservation.id)">Delete</button>
      </li>
    </ul>
    <form @submit.prevent="createReservation">
      <h3>Create Reservation</h3>
      <input
        v-model="newReservation.guestName"
        placeholder="Guest Name"
        required
      />
      <select v-model="newReservation.table" required>
        <option v-for="table in tables" :key="table.id" :value="table.id">
          {{ table.tableName }}
        </option>
      </select>
      <input v-model="newReservation.date" type="date" required />
      <button type="submit">Create</button>
    </form>
  </div>
</template>

<script setup lang="ts">
const reservations: []
const tables: []
const newReservation: {
  guestName: ''
  table: ''
  date: ''
}

function createReservation() {}

function deleteReservation(id: number) {}
</script>

<style scoped>
.reservations-view {
  padding: 20px;
}

.reservations-list {
  list-style-type: none;
  padding: 0;
}

.reservation-item {
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
