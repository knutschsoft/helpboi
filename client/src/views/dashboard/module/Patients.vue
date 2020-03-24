<template>
    <v-container fluid>
        <v-breadcrumbs
            large
            :items="breadcrumbItems"
            divider=">"
        />

        <v-toolbar flat color="transparent">
            <v-icon class="mr-5">mdi-account-box</v-icon>
            <v-toolbar-title class="headline">Patientenkartei</v-toolbar-title>
            <v-spacer/>
            <v-btn text color="secondary" @click="createPatient()">
                <v-icon left>mdi-plus</v-icon>
                Patient hinzufügen
            </v-btn>
        </v-toolbar>

        <v-card class="elevation-12 mt-5">
            <v-card-text>
                <v-data-table
                    :headers="headers"
                    :items="organisationPatients"
                    :items-per-page="20"
                    :search="organisationPatientsSearch"
                    @click:row="editPatient"
                    multi-sort
                    class="clickable"
                >
                    <template v-slot:top>
                        <v-toolbar flat>
                            <v-toolbar-title class="font-weight-light">Filter:</v-toolbar-title>
                            <v-text-field class="ml-4" v-model="organisationPatientsSearch"
                                          append-icon="mdi-magnify" label="Suche"
                                          single-line hide-details style="max-width: 500px;"/>
                        </v-toolbar>
                    </template>
                    <template v-slot:no-data>
                        Keine Einträge gefunden
                    </template>
                </v-data-table>
            </v-card-text>
        </v-card>
    </v-container>
</template>

<script>
    export default {
        name: 'Patients',

        computed: {
            isLoading() {
                return this.$store.getters["organisation/isLoading"];
            },
            hasError() {
                return this.$store.getters["organisation/hasError"];
            },
            error() {
                return this.$store.getters["organisation/error"];
            },
            hasOrganisation() {
                return this.$store.getters["organisation/hasOrganisation"];
            },
            organisation() {
                return this.$store.getters["organisation/organisation"];
            },
            currentUser() {
                return this.$store.getters['security/currentUser'];
            },
            organisationPatients() {
                let patients = this.$store.getters['organisation/organisationPatients'];
                patients.forEach((patient) => patient.gender = this.getGender(patient));
                patients.forEach((patient) => patient.status = this.getStatus(patient));
                return patients;
            },
        },
        data: () => ({
            organisationPatientsSearch: null,

            breadcrumbItems: [
                {
                    to: {path: '/'},
                    text: 'Organisation',
                    disabled: false,
                    exact: false,
                },
                {
                    to: 'patientenkartei',
                    text: 'Patientenkartei',
                    disabled: true,
                },
            ],
            headers: [
                {text: 'Vorname', value: 'firstname'},
                {text: 'Nachname', value: 'lastname'},
                {text: 'Geschlecht', value: 'gender'},
                {text: 'Status', value: 'status'},
                {text: 'Telefon', value: 'phone'},
                {text: 'PLZ', value: 'zipcode'},
                {text: 'Ort', value: 'city'},
                {text: 'Adresse', value: 'address'},
                {text: 'Vermerk', value: 'notes'},
            ],
        }),
        created() {
            let user = this.$store.getters["security/currentUser"];
            if (user.organisationId) {
                this.$store.dispatch("organisation/find", user.organisationId);
                this.$store.dispatch("organisation/findOrganisationPatients", user.organisationId);
            } else {
                this.$router.push({path: "/organisation"});
            }
        },
        methods: {
            editPatient(patient) {
                this.$router.push("/patientenkartei/" + patient.id);
            },
            async createPatient() {
                let user = this.$store.getters["security/currentUser"];

                let patient = await this.$store.dispatch(
                    "organisation/createOrganisationPatient",
                    [
                        user.organisationId,
                        this.$data.firstname,
                        this.$data.lastname,
                        this.$data.gender,
                        this.$data.phone,
                        this.$data.dateOfBirth,
                        this.$data.zipcode,
                        this.$data.city,
                        this.$data.address,
                        this.$data.status,
                        this.$data.notes,
                    ]
                );

                if (patient) {
                    await this.$store.dispatch(
                        "organisation/addHistoryToPatient",
                        [
                            patient.id,
                            "INFO",
                            "Patient erstellt"
                        ]
                    );
                }
            },
            getGender(patient) {
                switch (patient.gender) {
                    case 'MALE':
                        return "Männlich";
                    case 'FEMALE':
                        return "Weiblich";
                    case 'DIVERS':
                        return "Divers";
                }
                return '';
            },
            getStatus(patient) {
                switch (patient.status) {
                    case 'REGISTERED':
                        return "Erfasst (Symptome / Risikogebiet / Kontakt mit infizierten) ohne Maßnahmen";
                    case 'TEST_VISIT_HOSPITAL':
                        return "Test & Arzt / Krankenhaus aufsuchen";
                    case 'TEST_IN_QUARANTINE':
                        return "Test & Quarantäne";
                    case 'TEST_POSITIVE_IN_QUARANTINE':
                        return "Testergebnis Positiv & Quarantäne";
                    case 'VOLUNTARY_QUARANTINE':
                        return "Empfehlung freiwillige Quarantäne / ggf. Test";
                }
                return '';
            },
        },
    }
</script>
