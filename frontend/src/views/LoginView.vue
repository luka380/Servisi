<template>
  <div class="login-container">
    <form @submit.prevent="login" class="login-form">
      <h2>Login</h2>

      <div class="form-group">
        <label for="username">Username</label>
        <input
          id="username"
          v-model="username"
          type="text"
          placeholder="Enter your username"
        />
        <span v-if="usernameerror" class="error">{{ usernameerror }}</span>
      </div>

      <div class="form-group">
        <label for="password">Password</label>
        <input
          id="password"
          v-model="password"
          type="password"
          placeholder="Enter your password"
        />
        <span v-if="passworderror" class="error">{{ passworderror }}</span>
      </div>

      <button type="submit" class="btn">Login</button>
      <span v-if="errorMessage" class="error">{{ errorMessage }}</span>
    </form>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { handleLogin } from '@/API_Commands'
import { ResponseCode } from '@/types'

const router = useRouter()

const username = ref<string>('')
const password = ref<string>('')

const usernameerror = ref<string>()
const passworderror = ref<string>()

const errorMessage = ref<string>()

const validateForm = () => {
  usernameerror.value = username.value.length < 2 || username.value.length > 32
    ? 'Username must be between 2 and 32 characters.'
    : ''
  passworderror.value = password.value.length < 8 || password.value.length > 128
    ? 'Password must be between 8 and 128 characters.'
    : ''

  return !usernameerror.value && !passworderror.value
}

async function login() {
  if (!validateForm())
    return

  const response = await handleLogin(username.value, password.value)
  if (response !== ResponseCode.GOOD) {
    alert(response.toString())
    return errorMessage.value = 'An error occurred while trying to log in.'
  }

  alert('Login successful!')
  await router.push('/Home')
}
</script>

<style scoped>
.login-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-color: #f5f5f5;
}

.login-form {
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
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

.btn:hover {
  background-color: #0056b3;
}
</style>
