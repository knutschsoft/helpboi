import axios from "axios";
import config from '@/config';

export default {
    findAll(organisationId) {
        return axios.get(`${config.apiUrl}/patients/${organisationId}`);
    },
    create(name, zipcode, city, address, userId) {
        return axios.post(`${config.apiUrl}/organisations`, {
            "name": name,
            "zipcode": zipcode,
            "city": city,
            "address": address,
            "userId": userId,
        });
    },
};
