<template>
    <v-container fluid>
        <v-breadcrumbs
            large
            :items="breadcrumbItems"
            divider=">"
        />

        <v-toolbar flat color="transparent">
            <v-btn icon @click="$router.go(-1)">
                <v-icon>mdi-arrow-left</v-icon>
            </v-btn>
            <v-toolbar-title class="headline">
                {{ patient.firstname }}
                {{ patient.lastname }}
            </v-toolbar-title>
            <v-spacer/>
            <v-btn text color="secondary">
                <v-icon left>mdi-note-text-outline</v-icon>
                Eintrag hinzufügen
            </v-btn>
            <v-divider inset vertical/>
            <v-menu open-on-hover>
                <template v-slot:activator="{ on }">
                    <v-btn text color="secondary" class="mx-2" v-on="on">
                        <v-icon left>mdi-medical-bag</v-icon>
                        Maßnahme hinzufügen
                    </v-btn>
                </template>
                <v-list>
                    <template v-for="actionType in actions">
                        <v-list-item @click="createAction(actionType)" :key="actionType">
                            <v-list-item-icon>
                                <v-icon>mdi-plus</v-icon>
                            </v-list-item-icon>
                            <v-list-item-title>{{actionType}}</v-list-item-title>
                        </v-list-item>
                        <v-divider :key="actionType"/>
                    </template>
                    <v-list-item @click="createInfo()">
                        <v-list-item-icon>
                            <v-icon>mdi-plus</v-icon>
                        </v-list-item-icon>
                        <v-list-item-title>Info hinzufügen</v-list-item-title>
                    </v-list-item>
                    <v-divider/>
                    <v-list-item @click="createTask()">
                        <v-list-item-icon>
                            <v-icon>mdi-plus</v-icon>
                        </v-list-item-icon>
                        <v-list-item-title>Aufgabe hinzufügen</v-list-item-title>
                    </v-list-item>
                </v-list>
            </v-menu>
        </v-toolbar>

        <v-card class="elevation-12 mt-5">
            <v-banner single-line dark class="mb-5">
                <span class="title font-weight-light">Status:</span>
                <div>
                    <v-chip class="ma-2" :color="hasSymptoms(patient) ? 'red' : 'green'" text-color="white">
                        <v-avatar left>
                            <v-icon>{{ hasSymptoms(patient) ? 'mdi-skull' : 'mdi-check-bold' }}</v-icon>
                        </v-avatar>
                        Symptome
                    </v-chip>
                    <v-chip class="ma-2" :color="isSeriouslyIll(patient) ? 'red' : 'green'" text-color="white">
                        <v-avatar left>
                            <v-icon>{{ isSeriouslyIll(patient) ? 'mdi-skull' : 'mdi-check-bold' }}</v-icon>
                        </v-avatar>
                        Schwerkrank
                    </v-chip>
                    <v-chip class="ma-2" :color="isQuarantine(patient) ? 'red' : 'green'" text-color="white">
                        <v-avatar left>
                            <v-icon>{{ isQuarantine(patient) ? 'mdi-skull' : 'mdi-check-bold' }}</v-icon>
                        </v-avatar>
                        Quarantäne
                    </v-chip>
                    <v-chip class="ma-2" :color="isTest(patient) ? 'red' : 'green'" text-color="white">
                        <v-avatar left>
                            <v-icon>{{ isTest(patient) ? 'mdi-skull' : 'mdi-check-bold' }}</v-icon>
                        </v-avatar>
                        Test
                    </v-chip>
                </div>
            </v-banner>

            <v-card-text>

                <v-row>
                    <v-col>
                        <h3 class="mb-4">Personendaten</h3>
                        <v-text-field :value="patient.firstname + ' ' + patient.lastname" filled label="Geschlecht"
                                      readonly/>
                        <v-text-field :value="getGender(patient)" filled label="Geschlecht" readonly/>
                        <v-text-field :value="patient.address + ' ' + patient.zipcode + ' ' + patient.city" filled
                                      label="Anschrift" readonly/>
                        <v-text-field :value="patient.dateOfBirth" filled label="Geburtsdatum" readonly/>
                        <v-text-field v-model="patient.phone" filled label="Telefon" readonly
                                      append-icon="mdi-phone-forward"/>
                        <v-text-field v-model="patient.email" filled label="Email" readonly
                                      append-icon="mdi-email-edit"/>

                        <h3 class="mb-4">Vorerkrankungen</h3>
                        <v-textarea outlined value="Darmkrebs, HIV"/>
                    </v-col>
                    <v-col>
                        <v-tabs show-arrows background-color="transparent">
                            <v-tab>Verlauf</v-tab>
                            <v-tab-item>
                                <v-timeline dense>
                                    <v-timeline-item
                                        v-for="history in patient.histories"
                                        :key="history.id"
                                        color="grey"
                                        small
                                        fill-dot
                                    >
                                        <v-card color="grey lighten-3" elevation="0">
                                            <v-row class="pa-3">
                                                <v-col cols="4">
                                                    <strong>{{ history.createdAt }}</strong>
                                                </v-col>
                                                <v-col>
                                                    <strong>{{ getHistoryTypeLabel(history) }}</strong>
                                                    <div class="caption">{{ history.content }}</div>
                                                </v-col>
                                            </v-row>
                                        </v-card>
                                    </v-timeline-item>
                                </v-timeline>
                            </v-tab-item>
                            <v-tab>Fragenkatalog</v-tab>
                            <v-tab-item>
                            </v-tab-item>
                        </v-tabs>
                    </v-col>
                </v-row>
            </v-card-text>
        </v-card>
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
            async createInfo() {
                let patient = await this.$store.dispatch(
                    "organisation/addHistoryToPatient",
                    [
                        this.patientId,
                        "INFO",
                        `Zähneputzen, pullern und ab ins Bett!`
                    ]
                );

                if (patient) {
                    this.patient = patient;
                }
            },
            async createTask() {
                let activeTo = "2020-03-22T21:16:00Z";
                let content = "Nachsorge Anruf";

                let patient = await this.$store.dispatch(
                    "organisation/addHistoryToPatient",
                    [
                        this.patientId,
                        "TASK",
                        `Aufgabe hinzugefügt: ${content} bis ${activeTo}`
                    ]
                );

                if (patient) {
                    this.patient = patient;

                    await this.$store.dispatch(
                        "task/create",
                        [
                            this.patientId,
                            content,
                            activeTo
                        ]
                    );
                }
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
            getGender(patient) {
                switch (patient.gender) {
                    case 'MALE':
                        return "Männlich";
                    case 'FEMALE':
                        return "Weblich";
                    case 'DIVERS':
                        return "Divers";
                }
                return '';
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
