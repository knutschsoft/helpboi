<template>
    <v-container fluid>
        <v-breadcrumbs
            large
            :items="breadcrumbItems"
            divider=">"
        />
        <v-row>
            <v-col
                cols="12"
                sm="10"
                md="8"
            >
                <v-card class="elevation-12">
                    <v-card-text>
                        Du hast noch keine Organisation.
                        <br>
                        Um loszulegen musst du zuerst eine erstellen.
                        <br>
                    </v-card-text>
                </v-card>
                <v-card class="elevation-12">
                    <v-toolbar
                        dark
                        flat
                    >
                        <v-toolbar-title>Organisation erstellen</v-toolbar-title>
                    </v-toolbar>
                    <v-card-text>
                        <v-form>
                            <v-text-field
                                v-model="name"
                                label="Der Name deiner Organisation"
                                name="name"
                                type="text"
                            />
                            <v-text-field
                                v-model="zipcode"
                                label="Postleizahl"
                                name="zipcode"
                                type="text"
                            />
                            <v-text-field
                                v-model="city"
                                label="Ort"
                                name="city"
                                type="text"
                            />
                            <v-text-field
                                v-model="address"
                                label="Straße und Straßennummer"
                                name="address"
                                type="text"
                            />
                        </v-form>
                    </v-card-text>
                    <v-card-actions>
                        <v-spacer />
                        <v-btn
                            class="px-4 ml-4"
                            dark
                            @click="createOrganisation()"
                        >
                            Organisation erstellen
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
        name: 'Organisation',
        components: {},
        data: () => ({
            breadcrumbItems: [
                {
                    to: {path: '/'},
                    text: 'Organisation',
                    disabled: false,
                    exact: false,
                },
                {
                    to: 'create',
                    text: 'Eine neue Organisation erstellen',
                    disabled: true,
                },
            ],
            name: null,
            zipcode: null,
            city: null,
            address: null,
        }),
        computed: {
            isLoading() {
                return this.$store.getters["organisation/isLoading"];
            },
            hasError() {
                return this.$store.getters["organisation/hasError"];
            },
            error() {
                return this.$store.getters["organisation/error"];
            },
            hasOrganisation() {
                return this.$store.getters["organisation/hasOrganisation"];
            },
            // currentUser() {
            //     return this.$store.getters['security/currentUser'];
            // },
        },
        methods: {
            currentUser() {
                return this.$store.getters['security/currentUser'];
            },
            async createOrganisation() {
                let user = this.currentUser();

                const organisation = await this.$store.dispatch(
                    "organisation/create",
                    [
                        this.$data.name,
                        this.$data.zipcode,
                        this.$data.city,
                        this.$data.address,
                        user.id,
                    ]
                );
                if (organisation !== null) {
                    user.organisationId = organisation.id;
                    user.admin = true;

                    this.$localStorage.set('user', user);
                    this.$store.dispatch("security/updateCurrentUser", user);

                    this.$router.push({path: "/organisation"});
                }
            }
        },
        created() {
            let user = this.currentUser();
            if (user && user.organisationId) {
                this.$router.push({path: "/organisation"});
            }
        },
    }
</script>
