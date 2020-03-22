<template>
    <v-app>
        <v-navigation-drawer
                v-model="drawer"
                app
                dark
                permanent
                :expand-on-hover="isExpandOnHover"
        >
            <v-list dense>
                <v-list-item
                        v-if="isAuthenticated"
                >
                    <v-list-item-content>
                        <v-list-item-title>{{ hello }}</v-list-item-title>
                    </v-list-item-content>
                </v-list-item>
                <v-list-item
                    v-if="user.organisationId"
                    link
                    to="/patientenkartei"
                >
                    <v-list-item-action>
                        <v-icon>mdi-account-box</v-icon>
                    </v-list-item-action>
                    <v-list-item-content>
                        <v-list-item-title>Patientenkartei</v-list-item-title>
                    </v-list-item-content>
                </v-list-item>
                <v-list-item
                    v-if="user.admin && user.organisationId"
                    link
                    to="/aufgaben"
                >
                    <v-list-item-action>
                        <v-icon>mdi-calendar-today</v-icon>
                    </v-list-item-action>
                    <v-list-item-content>
                        <v-list-item-title>Aufgaben</v-list-item-title>
                    </v-list-item-content>
                </v-list-item>
                <v-list-item
                    v-if="user.admin && user.organisationId"
                    link
                    to="/organisation"
                >
                    <v-list-item-action>
                        <v-icon>mdi-bank</v-icon>
                    </v-list-item-action>
                    <v-list-item-content>
                        <v-list-item-title>Organisation verwalten</v-list-item-title>
                    </v-list-item-content>
                </v-list-item>
                <v-list-item
                    v-else-if="!user.organisationId"
                    link
                    to="/organisation/erstellen"
                >
                    <v-list-item-action>
                        <v-icon>mdi-bank-plus</v-icon>
                    </v-list-item-action>
                    <v-list-item-content>
                        <v-list-item-title>Organisation erstellen</v-list-item-title>
                    </v-list-item-content>
                </v-list-item>
                <v-list-item
                        v-if="isAuthenticated"
                        link
                        to="/abmelden"
                >
                    <v-list-item-action>
                        <v-icon>mdi-location-exit</v-icon>
                    </v-list-item-action>
                    <v-list-item-content>
                        <v-list-item-title>Abmelden</v-list-item-title>
                    </v-list-item-content>
                </v-list-item>
            </v-list>
        </v-navigation-drawer>

        <v-content>
            <router-view></router-view>
        </v-content>

    </v-app>
</template>

<script>
    export default {
        name: 'Dashboard',

        components: {
        },

        data: () => ({
            drawer: true, // Display side menu by default
            isExpandOnHover: false,
            user: false,
        }),
        computed: {
            isAuthenticated() {
                return this.$store.getters['security/isAuthenticated'];
            },
            hello() {
                return `Hallo ${this.user.firstname}!:)`;
            },
            currentUser() {
                return this.$store.getters['security/currentUser'];
            },
        },
        created() {
            this.user = this.$store.getters['security/currentUser'];
        },
    };
</script>
