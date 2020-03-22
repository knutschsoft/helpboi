<template>
    <v-app>
        <v-content>
            <v-container
                class="fill-height grey lighten-4"
                fluid
            >
                <v-row
                    align="center"
                    justify="center"
                >
                    <v-card class="elevation-10" width="400">
                        <v-toolbar flat>
                            <v-toolbar-title>Helpboi</v-toolbar-title>
                            <v-spacer/>
                            <v-tooltip bottom>
                                <template v-slot:activator="{ on }">
                                    <v-btn :disabled="isLoading" icon large v-on="on">
                                        <v-icon>mdi-lock-reset</v-icon>
                                    </v-btn>
                                </template>
                                <span>Passwort vergessen</span>
                            </v-tooltip>
                            <v-tooltip bottom>
                                <template v-slot:activator="{ on }">
                                    <v-btn :disabled="isLoading" icon large v-on="on" to="/register">
                                        <v-icon>mdi-account-plus</v-icon>
                                    </v-btn>
                                </template>
                                <span>Erstelle Account</span>
                            </v-tooltip>
                        </v-toolbar>
                        <v-img :src="require('../assets/logo.png')" class="ma-12" height="200" contain></v-img>
                        <v-card-text>
                            <v-alert
                                v-if="hasError"
                                dense
                                text
                                type="error"
                            >
                                {{ error }}
                            </v-alert>
                            <v-form @keyup.native.enter="performLogin">
                                <v-text-field
                                    required
                                    v-model="email"
                                    name="login"
                                    placeholder="E-Mail-Adresse"
                                    prepend-icon="mdi-account"
                                    type="text"
                                />

                                <v-text-field
                                    required
                                    v-model="password"
                                    id="password"
                                    name="password"
                                    placeholder="Passwort"
                                    prepend-icon="mdi-lock"
                                    type="password"
                                />
                            </v-form>
                        </v-card-text>
                        <v-toolbar color="primary" dark flat>
                            <v-btn block depressed x-large text
                                :disabled="email.length === 0 || password.length === 0 || isLoading"
                                class="px-4 ml-4" @click="performLogin()">
                                Jetzt anmelden
                            </v-btn>
                        </v-toolbar>
                    </v-card>
                </v-row>
            </v-container>
        </v-content>
    </v-app>
</template>

<script>
    export default {
        name: "Login",
        data() {
            return {
                email: "",
                password: ""
            };
        },
        computed: {
            isLoading() {
                return this.$store.getters["security/isLoading"];
            },
            hasError() {
                return this.$store.getters["security/hasError"];
            },
            error() {
                return this.$store.getters["security/error"];
            }
        },
        created() {
            let redirect = this.$route.query.redirect;

            if (this.$store.getters["security/isAuthenticated"]) {
                if (typeof redirect !== "undefined") {
                    this.$router.push({path: redirect});
                } else {
                    this.$router.push({path: "/"});
                }
            }
        },
        methods: {
            async performLogin() {
                let payload = {email: this.$data.email, password: this.$data.password},
                    redirect = this.$route.query.redirect;

                let user = await this.$store.dispatch("security/login", payload);
                if (!this.$store.getters["security/hasError"]) {
                    this.$localStorage.set('isAuthenticated', true);
                    this.$localStorage.set('user', user);
                    this.$localStorage.set('payload', payload);

                    if (typeof redirect !== "undefined") {
                        this.$router.push({path: redirect});
                    } else if (user.organisationId) {
                        this.$router.push({path: "/patientenkartei"});
                    } else {
                        this.$router.push({path: "/organisation/create"});
                    }
                }
            }
        }
    }
</script>
