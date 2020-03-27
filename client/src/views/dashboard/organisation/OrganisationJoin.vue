<template>
    <v-container fluid>
        <v-breadcrumbs
            large
            :items="breadcrumbItems"
            divider=">"
        />

        <v-toolbar flat color="transparent">
            <v-icon class="mr-5">mdi-bank-plus</v-icon>
            <v-toolbar-title class="headline">Organisation beitreten</v-toolbar-title>
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
                Jemmand hat Dich zu seiner Organisation eingeladen. Möchtest Du dieser beitreten?
            </span>
            </v-banner>

            <v-card-text>
                <v-form v-if="hasOrganisation">
                    <v-text-field
                        readonly
                        v-model="organisation.name"
                        label="Name der Organisation"
                        name="name"
                        type="text"
                    />
                    <v-text-field
                        readonly
                        v-model="organisation.zipcode"
                        label="Postleizahl"
                        name="zipcode"
                        type="text"
                    />
                    <v-text-field
                        readonly
                        v-model="organisation.city"
                        label="Ort"
                        name="city"
                        type="text"
                    />
                    <v-text-field
                        readonly
                        v-model="organisation.address"
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
                    @click="joinOrganisation()"
                >
                    Organisation beitreten
                </v-btn>
                <v-spacer/>
            </v-card-actions>
        </v-card>
    </v-container>
</template>

<script>
    export default {
        name: "OrganisationJoin",

        data: () => ({
            breadcrumbItems: [
                {
                    to: {path: '/'},
                    text: 'Organisation',
                    disabled: false,
                    exact: false,
                },
                {
                    to: 'join',
                    text: 'Organisation beitreten',
                    disabled: true,
                },
            ]
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
            organisation() {
                return this.$store.getters["organisation/organisation"];
            },
            currentUser() {
                return this.$store.getters['security/currentUser'];
            },
        },

        mounted() {
            if (this.currentUser
                && this.currentUser.organisationId) {
                this.$router.push({path: "/organisation"});
            } else {
                const organisationId = this.$route.params.organisationId;
                this.$store.dispatch("organisation/find", organisationId);
            }
        },

        methods: {
            async joinOrganisation() {
                const organisationId = this.$route.params.organisationId;
                const user = await this.$store.dispatch("user/assignToOrganisation",
                    [this.currentUser.id, organisationId]);
                if (user) {
                    await this.$store.dispatch("security/updateCurrentUser", user);
                }
                // It's easier to start with a fresh state
                location.reload();
            }
        }
    }
</script>
