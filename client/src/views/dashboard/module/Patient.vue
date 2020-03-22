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
                        <v-btn
                            v-for="actionType in actions"
                            :key="actionType"
                            @click="createAction(actionType)"
                        >
                            {{ actionType }}
                        </v-btn>
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
        },
        data: () => ({
            patient: false,
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
            actions: [
                'Freiwillige Quarantäne',
                'Quarantäne',
                'Test',
                'Quarantäne & Test',
                'Zum Arzt/KH',
            ],
        }),
        async created() {
            let user = this.$store.getters["security/currentUser"];
            if (user.organisationId) {
                this.$store.dispatch("organisation/find", user.organisationId);
                let patients = await this.$store.dispatch("organisation/findOrganisationPatients", user.organisationId);

                let that = this;
                patients.forEach(function (patient) {
                    if (parseInt(patient.id) === parseInt(that.patientId)) {
                        that.patient = patient;
                    }
                });
            } else {
                this.$router.push({path: "/organisation"});
            }
        },
        methods: {
            async createAction(actionType) {
                let patient = await this.$store.dispatch(
                    "organisation/addHistoryToPatient",
                    [
                        this.patientId,
                        "TASK",
                        `Maßnahme hinzugefügt: ${actionType}`
                    ]
                );

                if (patient) {
                    this.patient = patient;
                }
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
