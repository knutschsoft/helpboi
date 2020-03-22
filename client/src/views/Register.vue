<template>
    <v-container>
        <v-row class="text-center">
            <v-col cols="12">
                <v-alert
                    v-if="hasError"
                    error
                >
                    {{ error }}
                </v-alert>
                <v-text-field
                    v-model="firstName"
                    type="text"
                    placeholder="Vorname"
                    class="form-control"
                />
                <v-text-field
                    v-model="lastName"
                    type="text"
                    placeholder="Nachname"
                    class="form-control"
                />
                <v-text-field
                    v-model="phone"
                    type="text"
                    placeholder="Telefonnummer"
                    class="form-control"
                />
                <v-text-field
                    v-model="email"
                    type="text"
                    placeholder="E-Mail"
                    class="form-control"
                />
                <v-text-field
                    v-model="password"
                    type="text"
                    placeholder="Passwort"
                    class="form-control"
                />
                <v-btn
                    :disabled="firstName.length === 0 || lastName.length === 0 || email.length === 0 || password.length === 0 || phone.length === 0 || isLoading"
                    type="button"
                    class="btn btn-primary"
                    @click="createUser()"
                >
                    Registrieren und Einloggen
                </v-btn>
            </v-col>
        </v-row>
    </v-container>
</template>

<script>
    export default {
        name: "Register",
        props: {
        },
        data() {
            return {
                firstName: "",
                lastName: "",
                email: "",
                password: "",
                phone: '',
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
                        this.$data.firstName,
                        this.$data.lastName,
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
        watch: {
        },
    };
</script>
