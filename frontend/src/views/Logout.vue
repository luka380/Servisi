<script setup lang="ts">

import { ref } from 'vue'
import { useAuthStore } from '@/stores/auth'
import { useRouter } from 'vue-router'

const authStore = useAuthStore()
const router = useRouter()
const message = ref<string>("Logging out ...")

setTimeout(() => {}, 1000)
if(authStore.isAuthenticated()) {
  authStore.logout()
  message.value = "Logged out"
}
else{
  message.value = "You are not logged in"
}
setTimeout(() => {}, 1000)
message.value = "Redirecting ..."
router.push('/Home')
</script>

<template>
  <div class="centered-text" >
    {{ message }}
  </div>
</template>

<style scoped>
.centered-text {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  font-size: 2rem;
  font-weight: bold;
  color: #333;
}
</style>
