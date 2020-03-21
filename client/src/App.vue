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
                    to="/tasks"
                >
                    <v-list-item-action>
                        <v-icon>mdi-calendar-today</v-icon>
                    </v-list-item-action>
                    <v-list-item-content>
                        <v-list-item-title>Aufgaben</v-list-item-title>
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
        name: 'App',

        components: {
        },

        data: () => ({
            drawer: true, // Display side menu by default
            isExpandOnHover: false,
        }),
    };
</script>
