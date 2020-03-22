<template>
    <v-container fluid>
        <v-breadcrumbs
            large
            :items="breadcrumbItems"
            divider=">"
        />

        <v-toolbar flat color="transparent">
            <v-btn icon>
                <v-icon>mdi-bank</v-icon>
            </v-btn>
            <v-toolbar-title class="headline">Organisation verwalten</v-toolbar-title>
        </v-toolbar>

        <v-tabs show-arrows background-color="transparent">
            <v-tab>Bezeichnung</v-tab>
            <v-tab-item>
                <v-card
                    v-if="hasOrganisation"
                    class="elevation-12 mt-2"
                >
                    <v-card-text>
                        <v-text-field v-model="organisation.name" filled label="Organisation Name" readonly/>
                        <v-text-field v-model="organisation.zipcode" filled label="Postleizahl" readonly/>
                        <v-text-field v-model="organisation.city" filled label="Stadt" readonly/>
                        <v-text-field v-model="organisation.address" filled label="Adresse" readonly/>
                    </v-card-text>
                </v-card>
            </v-tab-item>
            <v-tab>Mitarbeiter / Agents</v-tab>
            <v-tab-item>
                <v-card
                    v-if="hasOrganisation"
                    class="elevation-12 mt-2"
                >
                    <v-card-text>
                        <v-data-table
                            :headers="headers"
                            :items="organisationUsers"
                            :items-per-page="10"
                            class="elevation-1"
                        ></v-data-table>
                    </v-card-text>
                </v-card>
            </v-tab-item>
        </v-tabs>
    </v-container>
</template>

<script>

    export default {
        name: 'Organisation',
        components: {},
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
            organisationUsers() {
                return this.$store.getters['organisation/organisationUsers'];
            },
        },
        data: () => ({
            breadcrumbItems: [
                {
                    to: {path: '/organisation'},
                    text: 'Organisation',
                    disabled: false,
                    exact: false,
                },
                {
                    to: 'details',
                    text: 'Details',
                    disabled: true,
                },
            ],
            headers: [
                { text: 'Vorname', value: 'firstname' },
                { text: 'Nachname', value: 'lastname' },
                { text: 'E-Mail', value: 'email' },
                { text: 'Telefon', value: 'phone' },
            ],
        }),
        created() {
            let user = this.$store.getters["security/currentUser"];
            if (user.organisationId) {
                this.$store.dispatch("organisation/find", user.organisationId);
                this.$store.dispatch("organisation/findOrganisationUsers", user.organisationId);
            } else {
                this.$router.push({path: "/organisation/erstellen"});
            }
        }
    }
</script>
