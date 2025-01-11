<template>
  <div class="schedule-view">
    <h1>Schedule</h1>
    <div class="calendar-and-templates">
      <div class="calendar">Calendar Component</div>
      <div class="templates">
        <h2>Schedule Templates</h2>
        <button v-for="template in templates" :key="template.id">
          {{ template.name }}
        </button>
      </div>
    </div>
    <button @click="createSchedule">Create Schedule</button>
  </div>
</template>

<script setup lang="ts">

const templates: []

function createSchedule() {
      this.$router.push({ name: 'CreateSchedule' });
}

</script>

<style scoped>
.schedule-view {
  padding: 20px;
}

.calendar-and-templates {
  display: flex;
  justify-content: space-between;
}

.calendar {
  width: 60%;
  border: 1px solid #ddd;
  padding: 20px;
  border-radius: 5px;
}

.templates {
  width: 35%;
}
</style>
