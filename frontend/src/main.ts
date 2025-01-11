import { createApp } from 'vue';
import App from '/Users/marija/Servisi/frontend/src/app.vue';
import router from './router';
import { createPinia } from 'pinia';

const app = createApp(App);
app.use(createPinia());
app.use(router);
app.mount('#app');
