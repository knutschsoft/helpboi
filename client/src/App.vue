<template>
    <router-view/>
</template>

<script>
    import axios from 'axios';

    export default {
        name: 'App',
        computed: {
            isAuthenticated() {
                return this.$store.getters['security/isAuthenticated'];
            },
        },
        created() {
            axios.interceptors.response.use(undefined, (err) => {
                return new Promise(() => {
                    if (err.response.status === 401) {
                        this.$router.push({path: "/login"})
                    }
                    throw err;
                });
            });
        },
    };
</script>
