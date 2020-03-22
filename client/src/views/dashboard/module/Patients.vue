<template>
    <v-container>
        <v-row class="text-center">
            <v-col cols="12">
                <v-card>
                    <v-card-title>
                        Patientenkartei
                    </v-card-title>
                    <v-card-text>
                        <v-text-field
                            placeholder="Patient suchen"
                        ></v-text-field>
                    </v-card-text>
                </v-card>

                <v-card>
                    <v-card-title>Patienten</v-card-title>
                    <v-card-text>
                        <v-data-table
                            :headers="headers"
                            :items="organisationPatients"
                            :items-per-page="20"
                            class="elevation-1"
                        ></v-data-table>
                    </v-card-text>
                </v-card>
            </v-col>
        </v-row>
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
                return this.$store.getters['organisation/organisationPatients'];
            },
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
        }
    }
</script>
