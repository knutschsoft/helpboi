<template>
    <v-app>
        <v-content>
            <v-container
                class="fill-height grey lighten-4 background"
                fluid
            >
                <v-row
                    align="center"
                    justify="center"
                >
                    <v-card class="elevation-10" width="400">
                        <v-toolbar flat>
                            <v-spacer/>
                            <v-tooltip bottom>
                                <template v-slot:activator="{ on }">
                                    <v-btn :disabled="isLoading" icon large v-on="on">
                                        <v-icon>mdi-lock-reset</v-icon>
                                    </v-btn>
                                </template>
                                <span>
                                    <b>Passwort vergessen?!</b><br>
                                    Hoppla! Du würdest diese Funktion gerne sehen?<br>Dann gib uns Feedback und Unterstützung
                                    <v-icon color="white">mdi-emoticon-outline</v-icon>
                                </span>
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
                        <v-img :src="require('../assets/logo2.png')" class="ma-5" height="250" contain></v-img>
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
                                    prepend-icon="mdi-email"
                                    type="text"
                                />

                                <v-text-field
                                    required
                                    v-model="password"
                                    id="password"
                                    name="password"
                                    placeholder="Passwort"
                                    prepend-icon="mdi-lock"
                                    :append-icon="showPassword ? 'mdi-eye-off-outline' : 'mdi-eye-outline'"
                                    @click:append="() => (showPassword = !showPassword)"
                                    :type="showPassword ? 'text' : 'password'"
                                />
                            </v-form>
                        </v-card-text>
                        <v-card-actions>
                            <v-toolbar color="primary" dark flat>
                                <v-btn block depressed x-large text
                                       :disabled="email.length === 0 || password.length === 0 || isLoading"
                                       @click="performLogin()">
                                    Jetzt anmelden
                                </v-btn>
                            </v-toolbar>
                        </v-card-actions>
                    </v-card>
                </v-row>
            </v-container>
        </v-content>
        <Footer/>
    </v-app>
</template>

<script>
    import Footer from "../components/Footer";

    export default {
        name: "Login",
        components: {
            Footer
        },

        data() {
            return {
                email: "",
                password: "",

                showPassword: false,
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
                if (user) {
                    let onRefreshPayload = {
                        payload: payload,
                        user: user,
                        isAuthenticated: true,
                    };
                    this.$store.dispatch('security/onRefresh', onRefreshPayload);

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

<style scoped>
    .background {
        background-image: url('../assets/ringe.png');
        background-repeat: no-repeat;
        background-attachment: fixed;
        background-position: 50px 50px;
        background-size: 20%;
    }
</style>
