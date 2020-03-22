<template>
    <div>
        <v-breadcrumbs
            :items="breadcrumbItems"
            divider=">"
        />

        <v-card
            v-if="hasOrganisation"
        >
            <v-card-title>Bezeichnung</v-card-title>
            <v-card-text>
                <p>
                    {{ organisation.name }}
                </p>
                <p>
                    {{ organisation.zipcode }}
                    {{ organisation.city }}
                </p>
                <p>
                    {{ organisation.address }}
                </p>
            </v-card-text>
        </v-card>

        <v-card
            v-if="hasOrganisation"
        >
            <v-card-title>Mitarbeiter / Agents</v-card-title>
            <v-card-text>
                <v-data-table
                    :headers="headers"
                    :items="organisationUsers"
                    :items-per-page="10"
                    class="elevation-1"
                ></v-data-table>
            </v-card-text>
        </v-card>
    </div>
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
            this.$store.dispatch("organisation/find", user.organisationId);
            this.$store.dispatch("organisation/findOrganisationUsers", user.organisationId);
        }
    }
</script>
