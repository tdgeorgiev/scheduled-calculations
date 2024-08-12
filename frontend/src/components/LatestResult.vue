<template>
  <div class="result-container">
    <h1 class="title">Latest Calculation Result</h1>
    <div class="result-details">
      <p><strong>ID:</strong> {{ result.id }}</p>
      <p><strong>Schedule Period ID:</strong> {{ result.schedulePeriodId }}</p>
      <p><strong>Created At:</strong> {{ result.timestamp }}</p>
      <p><strong>Result:</strong> {{ result.result }}</p>
    </div>
    <div class="all-results">
      <el-button class="custom-button" type="primary" @click="goBack">Go Back to All Results</el-button>
    </div>
  </div>
</template>

<script>
import { ref, onMounted, onBeforeUnmount } from 'vue';
import { useRouter  } from 'vue-router';

import calculationService from '../services/calculationService';

export default {
  setup() {
    const result = ref({});
    const router = useRouter();
    let intervalId = null;

    const fetchLatestResult = async () => {
      try {
        const response = await calculationService.getLatestCalculationResult();
        result.value = response.data;
      } catch (error) {
        console.error('Failed to fetch latest result:', error);
      }
    };

    const refreshResult = () => {
      fetchLatestResult();
    };

    const goBack = () => {
      router.push({ name: 'calculation-results' });
    };

    const startPolling = () => {
      fetchLatestResult();
      intervalId = setInterval(fetchLatestResult, 5000);
    };

    const stopPolling = () => {
      if (intervalId) {
        clearInterval(intervalId);
      }
    };

    onMounted(() => {
      startPolling();
    });

    onBeforeUnmount(() => {
      stopPolling();
    });

    return {
      result,
      refreshResult,
      goBack
    };
  },
};
</script>

<style scoped>

.all-results {
  margin-top: 40px;
}
</style>