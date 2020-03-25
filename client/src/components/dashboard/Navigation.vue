<template>
    <v-navigation-drawer
        v-model="drawer"
        :mini-variant="mini"
        app
        dark
        color="primary"
        permanent
    >
        <template v-slot:prepend>
            <v-list nav two-line class="my-1">
                <v-list-item>
                    <v-list-item-avatar>
                        <v-avatar size="42">
                            <v-img :src="require('../../assets/logo.png')"></v-img>
                        </v-avatar>
                    </v-list-item-avatar>
                    <v-list-item-content>
                        <v-list-item-title class="headline">
                            Helpboi
                        </v-list-item-title>
                        <v-list-item-subtitle>
                        </v-list-item-subtitle>
                    </v-list-item-content>
                </v-list-item>
                <v-divider />
                <v-list-item to="/abmelden" two-line>
                    <v-list-item-action>
                        <v-icon>mdi-logout</v-icon>
                    </v-list-item-action>
                    <v-list-item-content>
                        <v-list-item-title>{{ hello }}</v-list-item-title>
                        <v-list-item-subtitle>Abmelden</v-list-item-subtitle>
                    </v-list-item-content>
                </v-list-item>
                <v-divider />
            </v-list>
        </template>
        <v-list nav>
            <v-list-item
                v-if="user.organisationId"
                link
                to="/"
            >
                <v-list-item-action>
                    <v-icon>mdi-account-box</v-icon>
                </v-list-item-action>
                <v-list-item-content>
                    <v-list-item-title>Patientenkartei</v-list-item-title>
                </v-list-item-content>
            </v-list-item>
            <v-list-item
                v-if="user.organisationId"
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
                    <v-list-item-title>Organisation</v-list-item-title>
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
        </v-list>
        <template v-slot:append>
            <v-divider />
            <v-list shaped>
                <v-list-item @click="mini = !mini">
                    <v-list-item-icon>
                        <v-icon v-if="!mini">mdi-chevron-double-left</v-icon>
                        <v-icon v-if="mini">mdi-chevron-double-right</v-icon>
                    </v-list-item-icon>
                    <v-list-item-content>
                        <v-list-item-subtitle>Sidebar verkleinern</v-list-item-subtitle>
                    </v-list-item-content>
                </v-list-item>
            </v-list>
        </template>
    </v-navigation-drawer>
</template>

<script>
    export default {
        name: "Navigation",

        data: () => ({
            drawer: true,
            mini: false,
            user: false,
        }),
        computed: {
            isAuthenticated() {
                return this.$store.getters['security/isAuthenticated'];
            },
            hello() {
                return `Hallo ${this.user.firstname}!`;
            },
            currentUser() {
                return this.$store.getters['security/currentUser'];
            },
        },
        created() {
            this.user = this.$store.getters['security/currentUser'];
        },
    }
</script>