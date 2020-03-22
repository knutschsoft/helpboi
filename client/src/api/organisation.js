import axios from "axios";
import config from '@/config';

export default {
    find(organisationId) {
        return axios.get(`${config.apiUrl}/organisations/${organisationId}`);
    },
    findOrganisationUsers(organisationId) {
        return axios.get(`${config.apiUrl}/organisations/${organisationId}/users`);
    },
    findOrganisationPatients(organisationId) {
        return axios.get(`${config.apiUrl}/organisations/${organisationId}/patients`);
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
