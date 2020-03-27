<template>
    <v-container fluid>
        <v-breadcrumbs
            large
            :items="breadcrumbItems"
            divider=">"
        />

        <v-toolbar flat color="transparent">
            <v-icon class="mr-5">mdi-bank-plus</v-icon>
            <v-toolbar-title class="headline">Organisation erstellen</v-toolbar-title>
        </v-toolbar>

        <v-card class="elevation-12 mt-5">
            <v-banner single-line dark>
                <v-icon
                    slot="icon"
                    color="warning"
                    size="36"
                >
                    mdi-alert-outline
                </v-icon>
                <span class="title font-weight-light">
                Du hast noch keine Organisation. Um loszulegen musst Du zuerst eine erstellen.
            </span>
            </v-banner>

            <v-card-text>
                <v-alert
                    v-if="hasError"
                    dense
                    text
                    type="error"
                >
                    {{ error }}
                </v-alert>
                <v-form>
                    <v-text-field
                        v-model="name"
                        label="Name deiner Organisation"
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
                <v-btn
                    x-large
                    depressed
                    color="primary"
                    @click="createOrganisation()"
                >
                    Organisation erstellen
                </v-btn>
                <v-spacer/>
            </v-card-actions>
        </v-card>
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

                    await this.$store.dispatch("security/updateCurrentUser", user);

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
