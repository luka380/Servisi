import { createRouter, createWebHistory } from 'vue-router'
import ScheduleView from '@/views/ScheduleView.vue';
import TableTypesView from '@/views/TableTypeView.vue';
import TablesView from '@/views/TableView.vue';
import ReservationsView from '@/views/ReservationsView.vue';
import SettingsView from '@/views/SettingsView.vue';
import ZoneView from '@/views/ZoneView.vue';
import RestaurantDetailsView from '@/views/RestaurantDetailsView.vue';
import HomeView from '@/views/HomeView.vue';
import ClientLoginRegisterView from '@/views/ClientLoginRegisterView.vue'
import ManagerLoginRegisterView from '@/views/ManagerLoginRegisterView.vue'
import ClientView from '@/views/ClientView.vue'
import ManagerView from '@/views/ManagerView.vue'

const routes = [
  { path: '/', name: 'Home', component: HomeView },
  { path: '/client-login', name: 'ClientLogin', component: ClientLoginRegisterView },
  { path: '/manager-login', name: 'ManagerLogin', component: ManagerLoginRegisterView },
  { path: '/client', name: 'ClientView', component: ClientView },
  { path: '/manager', name: 'ManagerView', component: ManagerView },
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
