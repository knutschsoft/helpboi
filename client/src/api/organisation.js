import axios from "axios";
import config from '@/config';
import faker from 'faker';
faker.locale = "de";

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
        firstname = faker.name.firstName();
        lastname = faker.name.lastName();
        gender = faker.helpers.randomize(['DIVERS', 'MALE', 'FEMALE']);
        phone = faker.phone.phoneNumber();
        dateOfBirth = faker.date.past();//'1983-03-22T15:27:07Z';
        zipcode = faker.address.zipCode();
        city = faker.address.city();
        address = faker.address.streetName();
        status = faker.helpers.randomize(['REGISTERED', 'TEST_IN_QUARANTINE', 'VOLUNTARY_QUARANTINE', 'TEST_POSITIVE_IN_QUARANTINE', 'TEST_VISIT_HOSPITAL']);
        notes = faker.lorem.sentence();

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
