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
            let isAuthenticated = this.$localStorage.get('isAuthenticated', false);
            let user = this.$localStorage.get('user', null);
            let payload = this.$localStorage.get('payload', null);

            if (payload) {
                let basicAuth = 'Basic ' + btoa(payload.email + ':' + payload.password);
                axios.defaults.headers.common = {'Authorization': basicAuth}
            }

            payload = { isAuthenticated: isAuthenticated, user: user };
            this.$store.dispatch("security/onRefresh", payload);

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
