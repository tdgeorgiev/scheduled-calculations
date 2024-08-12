import { createRouter, createWebHistory } from 'vue-router';
import CalculationResults from '../components/CalculationResults.vue';
import LatestResult from '../components/LatestResult.vue';

const routes = [
    { path: '/calculation-results', name: 'calculation-results', component: CalculationResults },
    { path: '/latest', name: 'latest', component: LatestResult },
    { path: '/', redirect: '/calculation-results' }
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

export default router;
