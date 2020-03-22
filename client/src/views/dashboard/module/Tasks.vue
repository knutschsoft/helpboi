<template>
    <v-container fluid>
        <v-breadcrumbs
            large
            :items="breadcrumbItems"
            divider=">"
        />

        <v-toolbar flat color="transparent">
            <v-icon class="mr-5">mdi-calendar-today</v-icon>
            <v-toolbar-title class="headline">Aufgaben</v-toolbar-title>
            <v-spacer/>
        </v-toolbar>

        <v-card class="elevation-12 mt-5">
            <v-card-text>
                <v-data-table
                    :headers="headers"
                    :items="tasks"
                    :items-per-page="20"
                    :search="tasksSearch"
                    multi-sort
                    class="clickable"
                >
                    <template v-slot:top>
                        <v-toolbar flat>
                            <v-toolbar-title class="font-weight-light">Filter:</v-toolbar-title>
                            <v-text-field class="ml-4" v-model="tasksSearch"
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
        name: 'Tasks',
        components: {},
        data: () => ({
            tasksSearch: null,
            breadcrumbItems: [
                {
                    to: {path: '/aufgaben'},
                    text: 'Aufgaben',
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
                {text: 'Patient', value: 'patientId'},
                {text: 'Aufgabe', value: 'content'},
                {text: 'Status', value: 'state'},
                {text: 'Bearbeiter', value: 'agentId'},
                {text: 'Bis', value: 'activeTo'},
                {text: 'Überfällig', value: 'overdue'},
            ],
        }),
        computed: {
            isLoading() {
                return this.$store.getters["task/isLoading"];
            },
            hasError() {
                return this.$store.getters["task/hasError"];
            },
            error() {
                return this.$store.getters["task/error"];
            },
            hasTasks() {
                return this.$store.getters["task/hasTasks"];
            },
            tasks() {
                return this.$store.getters["task/tasks"];
            },
            currentUser() {
                return this.$store.getters['security/currentUser'];
            },
        },
        created() {
            let user = this.$store.getters["security/currentUser"];
            if (user.organisationId) {
                this.$store.dispatch("task/findAll");
                // this.$store.dispatch("organisation/find", user.organisationId);
                // this.$store.dispatch("organisation/findOrganisationPatients", user.organisationId);
            } else {
                this.$router.push({path: "/organisation"});
            }
        },
    }
</script>
