<template>
  <div class="action">
    <el-button class="custom-button" type="primary" @click="goToLatestResult">Go to Latest Result</el-button>
  </div>

  <div class="table-container">
    <el-table :data="tableData" style="width: 100%">
      <el-table-column
          prop="schedulePeriodId"
          label="Schedule Period ID"
          width="180">
      </el-table-column>
      <el-table-column
          prop="id"
          label="ID"
          width="180">
      </el-table-column>
      <el-table-column
          prop="timestamp"
          label="Created At"
          width="180">
      </el-table-column>
      <el-table-column
          prop="result"
          label="Result"
          width="180">
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import { ref, onMounted, onBeforeUnmount } from 'vue';
import { ElTable, ElTableColumn, ElButton } from 'element-plus';
import { useRouter } from 'vue-router';
import calculationService from '../services/calculationService';

export default {
  components: {
    ElTable,
    ElTableColumn,
    ElButton
  },
  setup() {
    const tableData = ref([]);
    const router = useRouter();
    let intervalId = null;

    const fetchData = async () => {
      try {
        const response = await calculationService.getAllCalculationResults();
        tableData.value = response.data;
      } catch (error) {
        console.error('Failed to fetch data:', error);
      }
    };

    const startPolling = () => {

      fetchData();
      intervalId = setInterval(fetchData, 5000); // Poll every 5 seconds
    };

    const stopPolling = () => {
      if (intervalId) {
        clearInterval(intervalId);
      }
    };

    const goToLatestResult = async () => {
      try {
        await router.push({ name: 'latest'});
      } catch (error) {
        console.error('Failed to fetch latest result:', error);
      }
    };

    onMounted(() => {
      startPolling();
    });

    onBeforeUnmount(() => {
      stopPolling();
    });

    return {
      tableData,
      goToLatestResult
    };
  },
};
</script>

<style scoped>
.action {
  display: flex;
  justify-content: space-between;
  align-items: flex-start;
  margin-bottom: 20px;
}

.table-container {
  margin-top: 20px;
}

:deep(.el-table__body .cell) {
  background-color: #ffffff;
  color: #666;
  border-bottom: 1px solid #ddd;
  text-align: center;
  padding: 10px;
}

:deep(.el-table__body tr:hover) {
  background-color: #f5f5f5;
}
</style>

