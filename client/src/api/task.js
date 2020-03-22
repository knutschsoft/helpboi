import axios from "axios";
import config from '@/config';

export default {
    create(patientId, content, activeTo) {
        return axios.post(`${config.apiUrl}/tasks`, {
            patientId: patientId,
            content: content,
            activeTo: activeTo,
        });
    },
    findAll() {
        return axios.get(`${config.apiUrl}/tasks`);
    },
};
