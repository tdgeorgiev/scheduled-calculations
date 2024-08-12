import axios from 'axios';

const API_URL = '/api/calculation-results';

export default {
    getAllCalculationResults() {
        return axios.get(API_URL);
    },

    getLatestCalculationResult() {
        return axios.get(`${API_URL}/latest`);
    },
};
