<template>
  <div class="login-container">
    <h1 v-if="!isRegistering">Manager Login</h1>
    <h1 v-else>Manager Register</h1>
    <form @submit.prevent="isRegistering ? handleRegister : handleLogin">
      <div class="input-group">
        <label for="username">Username:</label>
        <input
            id="username"
            v-model="username"
            type="text"
            required
            minlength="2"
            maxlength="32"
            placeholder="Enter your username"
        />
      </div>
      <div class="input-group" v-if="isRegistering">
        <label for="email">Email:</label>
        <input
            id="email"
            v-model="email"
            type="email"
            required
            placeholder="Enter your email"
        />
      </div>
      <div class="input-group" v-if="isRegistering">
        <label for="firstName">First Name:</label>
        <input
            id="firstName"
            v-model="firstName"
            type="text"
            required
            placeholder="Enter your first name"
        />
      </div>
      <div class="input-group" v-if="isRegistering">
        <label for="lastName">Last Name:</label>
        <input
            id="lastName"
            v-model="lastName"
            type="text"
            required
            placeholder="Enter your last name"
        />
      </div>
      <div class="input-group">
        <label for="password">Password:</label>
        <input
            id="password"
            v-model="password"
            type="password"
            required
            minlength="8"
            maxlength="128"
            placeholder="Enter your password"
        />
      </div>
      <div class="button-group">
        <button type="submit">{{ isRegistering ? 'Register' : 'Login' }}</button>
        <button type="button" @click="toggleForm">{{ isRegistering ? 'Back to Login' : 'Register' }}</button>
      </div>
    </form>
    <p v-if="error" class="error">{{ error }}</p>
  </div>
</template>

<script lang="ts">
import { defineComponent, ref } from "vue";
import axios from "axios";
import { useRouter } from "vue-router";

export default defineComponent({
  name: "LoginView",
  setup() {
    const username = ref("");
    const password = ref("");
    const email = ref("");
    const firstName = ref("");
    const lastName = ref("");
    const isRegistering = ref(false);
    const error = ref("");

    const router = useRouter();

    const handleLogin = async () => {
      try {
        const response = await axios.post("/auth/Client/Login", {
          username: username.value,
          password: password.value,
        });
        const token = response.data;
        localStorage.setItem("token", token);
        error.value = "";

        router.push("/");
      } catch (err) {
        if (axios.isAxiosError(err)) {
          error.value = err.response?.data || "Failed to login. Please try again.";
        } else {
          error.value = "An unknown error occurred.";
        }
      }
    };


    const handleRegister = async () => {
      try {
        const response = await axios.post("/auth/Client/Register", {
          username: username.value,
          password: password.value,
          email: email.value,
          firstName: firstName.value,
          lastName: lastName.value,
        });

        alert(response.data);
        error.value = "";
        toggleForm();
      } catch (err) {
        if (axios.isAxiosError(err)) {
          error.value = err.response?.data || "Failed to register. Please try again.";
        } else {
          error.value = "An unknown error occurred. Please try again.";
        }
      }
    };


    const toggleForm = () => {
      isRegistering.value = !isRegistering.value;
      error.value = "";
    };

    return {
      username,
      password,
      email,
      firstName,
      lastName,
      isRegistering,
      error,
      handleLogin,
      handleRegister,
      toggleForm,
    };
  },
});
</script>

<style scoped>

.login-container {
  max-width: 400px;
  padding: 40px;
  margin: auto;
  align-items: center;
  align-self: center;
  border-radius: 12px;
  background-color: #f0f8ff;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
}

h1 {
  font-optical-sizing: auto;
  font-weight: normal;
  font-style: normal;
  font-size: 4rem;
  text-align: center;
  color: #007BFF;
  margin-bottom: 20px;
}

.input-group {
  margin-bottom: 20px;
}

input {
  width: 90%;
  padding: 12px;
  font-size: 1rem;
  border: 1px solid #007BFF;
  border-radius: 8px;
  background-color: #fff;
  outline: none;
  transition: all 0.3s ease;
}

input:focus {
  border-color: #0056b3;
  box-shadow: 0 0 5px rgba(0, 123, 255, 0.6);
}

button {
  width: 45%;
  align-self: center;
  padding: 12px;
  background-color: #007BFF;
  color: white;
  border: none;
  border-radius: 8px;
  font-size: 1.2rem;
  cursor: pointer;
  transition: all 0.3s ease;
}
button:hover {
  background-color: #0056b3;
  transform: translateY(-2px);
}

.error {
  color: red;
  font-size: 0.9rem;
  margin-top: 15px;
  text-align: center;
}

input, button {
  transition: all 0.3s ease;
}
.button-group {
  display: flex;
  justify-content: center;
  gap: 10px;
}
</style>
