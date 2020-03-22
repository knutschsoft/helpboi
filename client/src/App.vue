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
                    link
                    to="/"
                >
                    <v-list-item-action>
                        <v-icon>mdi-home</v-icon>
                    </v-list-item-action>
                    <v-list-item-content>
                        <v-list-item-title>Home</v-list-item-title>
                    </v-list-item-content>
                </v-list-item>
                <v-list-item
                    link
                    to="/users"
                >
                    <v-list-item-action>
                        <v-icon>mdi-account-box</v-icon>
                    </v-list-item-action>
                    <v-list-item-content>
                        <v-list-item-title>Patientenkartei</v-list-item-title>
                    </v-list-item-content>
                </v-list-item>
                <v-list-item
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
    import axios from "axios";

    export default {
        name: 'App',

        components: {
        },

        data: () => ({
            drawer: true, // Display side menu by default
            isExpandOnHover: false,
            user: null,
        }),
        computed: {
            isAuthenticated() {
                return this.$store.getters['security/isAuthenticated'];
            },
            hello() {
                return `Hallo ${this.user.firstname}!:)`;
            },
        },
        method: {
        },
        created() {
            let isAuthenticated = this.$localStorage.get('isAuthenticated', false);
            let user = this.$localStorage.get('user', null);
            let payload = this.$localStorage.get('payload', null);
            this.$data.user = user;

            if (payload) {
                let basicAuth = 'Basic ' + btoa(payload.email + ':' + payload.password);
                axios.defaults.headers.common = {'Authorization': basicAuth}
            }

            payload = { isAuthenticated: isAuthenticated, user: user };
            this.$store.dispatch("security/onRefresh", payload);

            axios.interceptors.response.use(undefined, (err) => {
                return new Promise(() => {
                    if (err.response.status === 401) {
                        this.$router.push({path: "/login"})
                    }
                    throw err;
                });
            });
        },
    };
</script>
