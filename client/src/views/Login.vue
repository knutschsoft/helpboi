<template>
    <v-container
        class="fill-height"
        fluid
    >
        <v-row
            align="center"
            justify="center"
        >
            <v-col
                cols="12"
                sm="10"
                md="8"
            >
                <v-card class="elevation-12">
                    <v-toolbar
                        dark
                        flat
                    >
                        <v-toolbar-title>Anmeldung</v-toolbar-title>
                    </v-toolbar>
                    <v-card-text>
                        <v-form>
                            <v-text-field
                                v-model="email"
                                label="E-Mail-Adresse"
                                name="login"
                                prepend-icon="mdi-account"
                                type="text"
                            />

                            <v-text-field
                                v-model="password"
                                id="password"
                                label="Passwort"
                                name="password"
                                prepend-icon="mdi-lock"
                                type="password"
                            />
                        </v-form>
                    </v-card-text>
                    <v-card-actions>
                        <v-spacer />
                        <v-btn
                            :disabled="isLoading"
                            text
                            class="mr-4 px-4 font-weight-regular"
                            to="/register"
                        >
                            noch kein Account?
                        </v-btn>
                        <v-btn
                            :disabled="email.length === 0 || password.length === 0 || isLoading"
                            class="px-4 ml-4"
                            dark
                            @click="performLogin()"
                        >
                            Jetzt anmelden
                        </v-btn>
                    </v-card-actions>
                    <v-divider></v-divider>
                    <v-alert
                        v-if="hasError"
                        class="warning"
                    >
                        {{ error }}
                    </v-alert>
                </v-card>
            </v-col>
        </v-row>
    </v-container>
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
