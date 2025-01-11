<template>
  <div class="login-container">
    <h1 v-if="!isRegistering && !isResetting">Client Login</h1>
    <h1 v-else-if="isRegistering">Client Register</h1>
    <h1 v-else>Password Reset</h1>

    <form v-if="!isResetting" @submit.prevent="isRegistering ? handleRegister : handleLogin">
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
        <button type="button" @click="startPasswordReset" v-if="!isRegistering">Forgot Password?</button>
      </div>
    </form>

    <form v-else @submit.prevent="handlePasswordReset">
      <div class="input-group" v-if="!resetStepTwo">
        <label for="reset-username">Username:</label>
        <input
            id="reset-username"
            v-model="resetUsername"
            type="text"
            required
            placeholder="Enter your username"
        />
        <button type="button" @click="sendResetCode">Send Reset Code</button>
      </div>

      <div v-else>
        <div class="input-group">
          <label for="reset-code">Verification Code:</label>
          <input
              id="reset-code"
              v-model="resetCode"
              type="text"
              required
              placeholder="Enter the verification code"
          />
        </div>
        <div class="input-group">
          <label for="reset-password">New Password:</label>
          <input
              id="reset-password"
              v-model="resetPassword"
              type="password"
              required
              minlength="8"
              placeholder="Enter your new password"
          />
        </div>
        <button type="submit">Reset Password</button>
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
  name: "Login",
  setup() {
    const username = ref("");
    const password = ref("");
    const email = ref("");
    const firstName = ref("");
    const lastName = ref("");
    const isRegistering = ref(false);
    const isResetting = ref(false);
    const resetStepTwo = ref(false);
    const resetUsername = ref("");
    const resetCode = ref("");
    const resetPassword = ref("");
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

        router.push("/client");
      } catch (err) {
        error.value = err.response?.data || "Failed to login. Please try again.";
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
        router.push("/manager");
      } catch (err) {
        error.value = err.response?.data || "Failed to register. Please try again.";
      }
    };

    const toggleForm = () => {
      isRegistering.value = !isRegistering.value;
      isResetting.value = false;
      error.value = "";
    };

    const startPasswordReset = () => {
      isResetting.value = true;
      resetStepTwo.value = false;
      error.value = "";
    };

    const sendResetCode = async () => {
      try {
        await axios.post("/auth/Client/SendPassReset", { username: resetUsername.value });
        resetStepTwo.value = true;
        error.value = "Reset code sent. Check your email.";
      } catch (err) {
        error.value = err.response?.data || "Failed to send reset code.";
      }
    };

    const handlePasswordReset = async () => {
      try {
        await axios.post(`/auth/Client/PassReset/${resetCode.value}`, {
          password: resetPassword.value,
        });
        alert("Password reset successful!");
        isResetting.value = false;
        resetStepTwo.value = false;
        resetUsername.value = "";
        resetCode.value = "";
        resetPassword.value = "";
        error.value = "";
      } catch (err) {
        error.value = err.response?.data || "Failed to reset password.";
      }
    };

    return {
      username,
      password,
      email,
      firstName,
      lastName,
      isRegistering,
      isResetting,
      resetStepTwo,
      resetUsername,
      resetCode,
      resetPassword,
      error,
      handleLogin,
      handleRegister,
      toggleForm,
      startPasswordReset,
      sendResetCode,
      handlePasswordReset,
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
  justify-content: center; /* Align buttons centrally */
  gap: 10px;
}
</style>
