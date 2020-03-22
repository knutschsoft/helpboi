import axios from "axios";
import config from '@/config';

export default {
    login(email, password) {
        return axios.post(`${config.apiUrl}/auth`, {
            email: email,
            password: password,
        });
    },
};
