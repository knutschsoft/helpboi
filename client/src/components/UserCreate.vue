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
                    v-model="firstname"
                    type="text"
                    placeholder="Vorname"
                    class="form-control"
                />
                <v-text-field
                    v-model="lastname"
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
                    :disabled="firstname.length === 0 || lastname.length === 0 || email.length === 0 || password.length === 0 || phone.length === 0 || isLoading"
                    type="button"
                    class="btn btn-primary"
                    @click="createUser()"
                >
                    Nutzer anlegen
                </v-btn>
            </v-col>
        </v-row>
    </v-container>
</template>

<script>
    export default {
        name: "UserCreate",
        props: {
        },
        data() {
            return {
                firstname: "Robb",
                lastname: "Stark",
                email: "robb@got.de",
                password: "EchtStark",
                phone: '0160 123 123 123',
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
            // this.$store.dispatch("user/findAll");
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
                    this.$data.firstname = "";
                    this.$data.lastname = "";
                    this.$data.email = "";
                    this.$data.password = "";
                    this.$data.phone = "";
                }
            }
        },
        watch: {
        },
    };
</script>
