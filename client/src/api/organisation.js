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
    createPatient(
        organisationId,
        firstname,
        lastname,
        gender,
        phone,
        dateOfBirth,
        zipcode,
        city,
        address,
        status,
        notes
    ) {
        return axios.post(`${config.apiUrl}/patients`, {
            organisationId: organisationId,
            firstname: firstname,
            lastname: lastname,
            gender: gender,
            phone: phone,
            dateOfBirth: dateOfBirth,
            zipcode: zipcode,
            city: city,
            address: address,
            status: status,
            notes: notes,
        });
    },
};
