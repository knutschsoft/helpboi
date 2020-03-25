<template>
    <v-app>
        <Navigation />
        <v-content class="grey lighten-3">
            <div class="ma-3">
                <router-view />
            </div>
        </v-content>
    </v-app>
</template>

<script>
    import Navigation from "../../components/Navigation";

    export default {
        name: 'Dashboard',
        components: {
            Navigation
        }
    };
</script>
