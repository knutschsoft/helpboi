import axios from "axios";
import config from '@/config';

export default {
    findAll() {
        return axios.get(`${config.apiUrl}/symptoms`);
    },
};
