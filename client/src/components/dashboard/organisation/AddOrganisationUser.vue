<template>
    <v-dialog v-model="visible" scrollable max-width="700">
        <template v-slot:activator="{ on }">
            <v-btn text color="secondary" v-on="on" class="mx-2">
                <v-icon left>mdi-plus</v-icon>
                Mitarbeiter Hinzufügen
            </v-btn>
        </template>
        <v-card>
            <v-toolbar flat dark color="primary">
                <v-toolbar-title>Mitarbeiter hinzufügen</v-toolbar-title>
                <v-spacer/>
                <v-btn icon dar depressed @click="close">
                    <v-icon>mdi-close</v-icon>
                </v-btn>
            </v-toolbar>
            <v-card-text class="mt-5">
                <v-banner two-line class="mb-5">
                    <v-icon slot="icon" size="36">
                        mdi-share-variant
                    </v-icon>
                    Verteilen Sie diesen Link an andere Benutzer, damit diese Ihrer Organisation beitreten können.
                    Diese sollten einen Account besitzen und eingeloggt sein.
                </v-banner>
                <v-text-field v-model="organisationLink" ref="organisationLink" hide-details filled readonly/>
            </v-card-text>
            <v-divider/>
            <v-card-actions>
                <v-btn color="primary" @click="copy">Copy Link</v-btn>
                <v-btn text @click="close">Schließen</v-btn>
            </v-card-actions>
        </v-card>
    </v-dialog>
</template>

<script>
    export default {
        name: "AddOrganisationUser",

        data() {
            return {
                visible: false,

                errorShow: false,
                errorText: ""
            }
        },

        computed: {
            hasOrganisation() {
                return this.$store.getters["organisation/hasOrganisation"];
            },
            organisation() {
                return this.$store.getters["organisation/organisation"];
            },
            organisationLink() {
                if (this.hasOrganisation) {
                    return window.location.origin + "/organisation/" + this.organisation.id + "/beitreten/" + this.generateToken(32);
                }
                return 'Keine Organisation gefunden';
            }
        },

        methods: {
            generateToken(n) {
                const chars = 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789';
                let token = '';
                for (let i = 0; i < n; i++) {
                    token += chars[Math.floor(Math.random() * chars.length)];
                }
                return token;
            },
            copy() {
                let organisationLink = this.$refs.organisationLink
                    .$el.querySelector('input');
                organisationLink.select();
                document.execCommand("copy");
                this.close();
            },
            close() {
                this.errorShow = false;
                this.visible = false;
            }
        }
    }
</script>