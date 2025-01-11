<script setup lang="ts">
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { handleRegister } from '@/API_Commands'
import { ResponseCode } from '@/types'

const router = useRouter()

const form = ref({
  username: '',
  password: '',
  confirmPassword: ''
})

const errors = ref({
  username: '',
  password: '',
  confirmPassword: ''
})

const errorMessage = ref<string>('')

const validateForm = () => {
  errors.value.username = form.value.username.length < 2 || form.value.username.length > 32
    ? 'Username must be between 2 and 32 characters.'
    : ''
  errors.value.password = form.value.password.length < 8 || form.value.password.length > 128
    ? 'Password must be between 8 and 128 characters.'
    : ''
  errors.value.confirmPassword =
    form.value.password !== form.value.confirmPassword
      ? 'Passwords do not match.'
      : ''

  return (
    !errors.value.username && !errors.value.password && !errors.value.confirmPassword
  )
}

async function register() {
  if (!validateForm())
    return
  const response = await handleRegister(form.value.username, form.value.password)
  if (response !== ResponseCode.GOOD) {
    alert(response.toString())
    return errorMessage.value = 'An error occurred while trying to log in.'
  }

  alert('Register successful!')
  await router.push('/Login')
}
</script>

<template>
  <div class="register-container">
    <form @submit.prevent="register" class="register-form">
      <h2>Register</h2>

      <div class="form-group">
        <label for="username">Username</label>
        <input
          id="username"
          v-model="form.username"
          type="text"
          placeholder="Enter your username"
        />
        <span v-if="errors.username" class="error">{{ errors.username }}</span>
      </div>

      <div class="form-group">
        <label for="password">Password</label>
        <input
          id="password"
          v-model="form.password"
          type="password"
          placeholder="Enter your password"
        />
        <span v-if="errors.password" class="error">{{ errors.password }}</span>
      </div>

      <div class="form-group">
        <label for="confirmPassword">Confirm Password</label>
        <input
          id="confirmPassword"
          v-model="form.confirmPassword"
          type="password"
          placeholder="Confirm your password"
        />
        <span v-if="errors.confirmPassword" class="error">{{ errors.confirmPassword }}</span>
      </div>

      <button type="submit" class="btn">Register</button>
      <span v-if="errorMessage" class="error">{{ errorMessage }}</span>
    </form>
  </div>
</template>

<style scoped>
.register-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-color: #f5f5f5;
}

.register-form {
  width: 300px;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 8px;
  background-color: #fff;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.form-group {
  margin-bottom: 15px;
}

label {
  display: block;
  margin-bottom: 5px;
  font-weight: bold;
}

input {
  width: 100%;
  padding: 8px;
  border: 1px solid #ccc;
  border-radius: 4px;
}

.error {
  color: red;
  font-size: 12px;
}

.btn {
  width: 100%;
  padding: 10px;
  background-color: #28a745;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

.btn:hover {
  background-color: #218838;
}
</style>
