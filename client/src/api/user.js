import axios from "axios";
import config from '@/config';

export default {
    findAll() {
        return axios.get(`${config.apiUrl}/users/1`, config.axiosGetAuth);
    },
    create(firstName, lastName, email, password, phone) {
        return axios.post(`${config.apiUrl}/users`, {
            "firstname": firstName,
            "lastname": lastName,
            "email": email,
            "password": password,
            "phone": phone
        }, config.axiosGetAuth);
    },
};
