import { createRouter, createWebHistory } from 'vue-router'
import ScheduleView from '@/views/ScheduleView.vue';
import TableTypesView from '@/views/TableTypesView.vue';
import TablesView from '@/views/TablesView.vue';
import ReservationsView from '@/views/ReservationsView.vue';
import SettingsView from '@/views/SettingsView.vue';
import ZoneView from '@/views/ZoneView.vue';
import RestaurantDetailsView from '@/views/RestaurantDetailsView.vue';

const routes = [
  { path: '/schedule', name: 'Schedule', component: ScheduleView },
  { path: '/table-types', name: 'TableTypes', component: TableTypesView },
  { path: '/tables', name: 'Tables', component: TablesView },
  { path: '/reservations', name: 'Reservations', component: ReservationsView },
  { path: '/settings', name: 'Settings', component: SettingsView },
  { path: '/zones', name: 'Zones', component: ZoneView },
  { path: '/restaurant-details', name: 'RestaurantDetails', component: RestaurantDetailsView },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
