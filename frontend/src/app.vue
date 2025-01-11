<template>
  <div id="app">
    <header>
      <nav>
        <h1>Restaurant Reservation System</h1>
        <div class="navbar-links">
          <router-link to="/">Home</router-link>
        </div>

      </nav>
    </header>
    <main>
      <router-view />
    </main>
  </div>
</template>

<script>
export default {
  name: 'App',
};
</script>

<style>
#app {
  display: flex;
  flex-direction: column;
  min-height: 100vh;
  font-family: "American Typewriter";
}
header {
  background-color: #9ac8f1;
  color: white;
  padding: 10px 20px;
}
header nav {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
header nav h1 {
  margin: 0;
  font-size: 24px;
}
header nav a {
  color: white;
  text-decoration: none;
  margin-left: 20px;
}
header nav a:hover {
  text-decoration: underline;
}
main {
  flex: 1;
  padding: 20px;
}

.navbar-links a {
  color: white;
  text-decoration: none;
  padding: 0.5rem 1.5rem;
  border-radius: 4px;
  transition: background-color 0.3s ease;
  font-family: "Pixelify Sans", sans-serif;
  font-optical-sizing: auto;
  font-weight: bold;
  font-style: normal;
  background: none;
  border: none;
  cursor: pointer;
  font-size: 1rem;
  line-height: 1.2;
  display: inline-flex;
  align-items: center;
}

.navbar-links a:hover {
  background-color: rgba(255, 255, 255, 0.2); /* Optional hover effect */
}


</style>
