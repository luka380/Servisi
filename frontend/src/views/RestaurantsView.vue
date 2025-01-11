<script setup lang="ts">
import type { Restaurant } from '@/types'

const restaurants = ref<Restaurant[]>({});

function goToRestaurantDetails(id: number) {
  this.$router.push({ name: 'RestaurantDetails', params: { id } })
}
</script>

<template>
  <div class="restaurant-view">
    <h1>Restaurants</h1>
    <table class="restaurant-table">
      <thead>
        <tr>
          <th>Name</th>
          <th>Kitchen Type</th>
        </tr>
      </thead>
      <tbody>
        <tr
          v-for="restaurant in restaurants"
          :key="restaurant.id"
          @click="goToRestaurantDetails(restaurant.id)"
        >
          <td>{{ restaurant.name }}</td>
          <td>{{ restaurant.kitchenType }}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<style scoped>
.restaurant-view {
  padding: 20px;
}

.restaurant-table {
  width: 100%;
  border-collapse: collapse;
  margin-top: 20px;
}

.restaurant-table th,
.restaurant-table td {
  border: 1px solid #ddd;
  padding: 10px;
  text-align: left;
}

.restaurant-table tr:hover {
  background-color: #f1f1f1;
  cursor: pointer;
}
</style>
