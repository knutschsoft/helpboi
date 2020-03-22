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
                            <v-btn icon class="hidden-xs-only" @click="$router.go(-1)">
                                <v-icon>mdi-arrow-left</v-icon>
                            </v-btn>
                            <v-toolbar-title>Noch keinen Account?</v-toolbar-title>
                        </v-toolbar>
                        <v-card-text>
                            <v-alert
                                v-if="hasError"
                                dense
                                text
                                type="error"
                            >
                                {{ error }}
                            </v-alert>
                            <v-form @keyup.native.enter="createUser">
                                <v-text-field
                                    v-model="firstname"
                                    type="text"
                                    placeholder="Vorname"
                                    prepend-icon="mdi-account-details"
                                />
                                <v-text-field
                                    v-model="lastname"
                                    type="text"
                                    placeholder="Nachname"
                                    prepend-icon="mdi-account-details-outline"
                                />
                                <v-text-field
                                    v-model="phone"
                                    type="text"
                                    placeholder="Telefonnummer"
                                    prepend-icon="mdi-phone"
                                />
                                <v-text-field
                                    v-model="email"
                                    type="text"
                                    placeholder="E-Mail"
                                    prepend-icon="mdi-email"
                                />
                                <v-text-field
                                    v-model="password"
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
                                <v-btn
                                    block
                                    depressed
                                    x-large
                                    text
                                    :disabled="firstname.length === 0 || lastname.length === 0 || email.length === 0 || password.length === 0 || phone.length === 0 || isLoading"
                                    class="px-4 ml-4" @click="createUser()">
                                    Jetzt anmelden
                                </v-btn>
                            </v-toolbar>
                        </v-card-actions>
                    </v-card>
                </v-row>
            </v-container>
        </v-content>
        <Footer />
    </v-app>
</template>

<script>
    import Footer from "../components/Footer";

    export default {
        name: "Register",
        components: {
            Footer
        },

        data() {
            return {
                firstname: "",
                lastname: "",
                email: "",
                password: "",
                phone: '',

                showPassword: false,
            };
        },
        computed: {
            isLoading() {
                return this.$store.getters["user/isLoading"];
            },
            hasError() {
                return this.$store.getters["user/hasError"];
            },
            error() {
                return this.$store.getters["user/error"];
            },
            hasUsers() {
                return this.$store.getters["user/hasUsers"];
            },
            users() {
                return this.$store.getters["user/users"];
            }
        },
        created() {
        },
        methods: {
            async createUser() {
                const result = await this.$store.dispatch(
                    "user/create",
                    [
                        this.$data.firstname,
                        this.$data.lastname,
                        this.$data.email,
                        this.$data.password,
                        this.$data.phone,
                    ]
                );
                if (result !== null) {
                    let payload = {email: this.$data.email, password: this.$data.password};

                    let user = await this.$store.dispatch("security/login", payload);
                    if (!this.$store.getters["security/hasError"]) {
                        this.$localStorage.set('isAuthenticated', true);
                        this.$localStorage.set('user', user);
                        this.$localStorage.set('payload', payload);

                        this.$router.push({path: "/"});
                    }
                }
            }
        },
        watch: {},
    };
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