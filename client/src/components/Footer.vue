<template>
    <v-footer fixed app padless color="grey lighten-2">
        <v-row justify="center" no-gutters>
            <v-btn v-for="(link) in overallLinks" :key="link.text" :to="link.route" text rounded class="my-2">
                {{ link.text }}
            </v-btn>
            <v-col class="grey lighten-4 py-4 text-center" cols="12">
                &copy; {{ new Date().getFullYear() }} — <strong>Helpboi</strong>
            </v-col>
        </v-row>
    </v-footer>
</template>

<script>
    export default {
        name: "Footer",

        data: () =>
            ({
                overallLinks: [
                    {text: 'Datenschutz', route: '/'},
                    {text: 'Über uns', route: '/'},
                    {text: 'Impressum', route: '/'},
                    {text: 'Nutzungsbedingungen', route: '/'}
                ]
            })
    }
</script>