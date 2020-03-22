<template>
    <v-container>
        <v-row class="text-center">
            <v-col cols="12">
                <v-card>
                    <v-card-title>
                        {{ patient.firstname }}
                        {{ patient.lastname }}
                    </v-card-title>
                    <v-card-text>
                        Personendaten
                        <br>
                        Name: {{ patient.firstname }} {{ patient.lastname }}
                        <v-icon
                            v-if="isMale(patient)"
                        >
                            mdi-male
                        </v-icon>
                        <v-icon
                            color="red"
                            v-else-if="isFemale(patient)"
                        >
                            mdi-female
                        </v-icon>
                        <v-icon
                            v-else-if="isDivers(patient)"
                        >
                            mdi-male-female
                        </v-icon>
                        <br>
                        Anschrift: {{ patient.address }} {{ patient.zipcode }} {{ patient.city }}
                        <br>
                        Geburtsdatum: {{ patient.dateOfBirth }}
                        <br>
                        Telefon: {{ patient.dateOfBirth }}
                        <br>
                        E-Mail: <a :href="`mailto: ${patient.email}`">{{ patient.email }}</a>
                    </v-card-text>
                    <v-card-title>
                        Vorerkrankungen
                    </v-card-title>
                    <v-card-text>
                        <v-textarea>
                            Darmkrebs, HIV
                        </v-textarea>
                    </v-card-text>
                    <v-card-text>
                        Status
                        <br>
                        Symptome
                        <v-icon
                            v-if="hasSymptoms(patient)"
                            color="red"
                        >
                            mdi-skull-crossbones
                        </v-icon>
                        <v-icon
                            v-else
                            color="green"
                        >
                            mdi-check-outline
                        </v-icon>
                        <br>
                        Schwerkrank
                        <v-icon
                            v-if="isSeriouslyIll(patient)"
                            color="red"
                        >
                            mdi-skull-crossbones
                        </v-icon>
                        <v-icon
                            v-else
                            color="green"
                        >
                            mdi-check-outline
                        </v-icon>
                        <br>
                        Quarantäne
                        <v-icon
                            v-if="isQuarantine(patient)"
                            color="red"
                        >
                            mdi-skull-crossbones
                        </v-icon>
                        <v-icon
                            v-else
                            color="green"
                        >
                            mdi-check-outline
                        </v-icon>
                        <br>
                        Test
                        <v-icon
                            v-if="isTest(patient)"
                            color="red"
                        >
                            mdi-skull-crossbones-outline
                        </v-icon>
                        <v-icon
                            v-else
                            color="green"
                        >
                            mdi-check-outline
                        </v-icon>
                        <br>
                    </v-card-text>
                    <v-card-text>
                    </v-card-text>
                </v-card>

                <v-tabs show-arrows background-color="transparent">
                    <v-tab>Verlauf</v-tab>
                    <v-tab-item>
                        <v-timeline>
                            <v-timeline-item
                                v-for="history in patient.histories"
                                :key="history.id"
                            >
                                {{ getHistoryTypeLabel(history) }}
                                {{ history.content }}
                                {{ history.createdAt }}
                            </v-timeline-item>
                            <v-timeline-item class="text-right">timeline item</v-timeline-item>
                            <v-timeline-item>timeline item</v-timeline-item>
                        </v-timeline>
                    </v-tab-item>
                    <v-tab>Fragenkatalog</v-tab>
                    <v-tab-item>
                    </v-tab-item>
                </v-tabs>
            </v-col>
        </v-row>
    </v-container>
</template>

<script>
    export default {
        name: 'Patient',
        props: {
            patientId: {
                required: true
            }
        },
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
                return this.$store.getters['organisation/organisationPatients'];
            },
            patient() {
                let patients = this.$store.getters['organisation/organisationPatients'];
                let that = this;
                let myPatient = false;
                patients.forEach(function (patient) {
                    if (parseInt(patient.id) === parseInt(that.patientId)) {
                        myPatient = patient;
                    }
                });

                return myPatient;
            }
        },
        data: () => ({
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
                { text: 'Vorname', value: 'firstname' },
                { text: 'Nachname', value: 'lastname' },
                { text: 'Geschlecht', value: 'gender' },
                { text: 'Status', value: 'status' },
                { text: 'Telefon', value: 'phone' },
                { text: 'PLZ', value: 'zipcode' },
                { text: 'Ort', value: 'city' },
                { text: 'Adresse', value: 'address' },
                { text: 'Vermerk', value: 'notes' },
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

            // this.patient = patientId
        },
        methods: {
            async createPatient() {
                let user = this.$store.getters["security/currentUser"];

                await this.$store.dispatch(
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
            },
            isMale(patient) {
                return patient.gender === 'MALE';
            },
            isFemale(patient) {
                return patient.gender === 'FEMALE';
            },
            isDivers(patient) {
                return patient.gender === 'DIVERS';
            },
            hasSymptoms(patient) {
                return patient.gender !== 'DIVERS';
            },
            isSeriouslyIll(patient) {
                return patient.gender === 'DIVERS';
            },
            isQuarantine(patient) {
                return patient.gender === 'DIVERS';
            },
            isTest(patient) {
                return patient.gender !== 'DIVERS';
            },
            getHistoryTypeLabel(history) {
                switch (history.type) {
                    case 'ACTION':
                        return 'Aktion';
                    case 'INFO':
                        return 'Info';
                    case 'TASK':
                        return 'Aufgabe';
                }

                return '';
            },
        },
    }
</script>
