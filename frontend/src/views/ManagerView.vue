<template>
  <div class="manager-view">
    <LoginRegisterForm :userType="'Manager'" />
  </div>
</template>

<script lang="ts">
import ManagerLoginRegisterView from "@/views/ManagerLoginRegisterView.vue";

export default {
  components: { ManagerLoginRegisterView },
};
</script>
