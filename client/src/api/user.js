import axios from "axios";
import config from '@/config';

export default {
    findAll() {
        return axios.get(`${config.apiUrl}/users/1`);
    },
    create(firstname, lastname, email, password, phone) {
        return axios.post(`${config.apiUrl}/users`, {
            "firstname": firstname,
            "lastname": lastname,
            "email": email,
            "password": password,
            "phone": phone
        });
    },
    assignToOrganisation(id, organisationId) {
        return axios.post(`${config.apiUrl}/users/${id}/organisations`, {
            "organisationId": organisationId
        });
    }
};
