<template>
    <router-view />
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
        data: () => ({
        }),
        created() {
            axios.interceptors.response.use(
                function (response) {
                    // Any status code that lie within the range of 2xx cause this function to trigger
                    // Do something with response data
                    return response;
                }, function (error) {
                    let reason = error;
                    if (error.response && error.response.data && error.response.data.message) {
                        reason = error.response.data.message;
                    }
                    // if (error.response.status === 401) {
                        // that.$router.push({path: "/abmelden"})
                        // } else if (err.response.status === 500) {
                        // this.showError = true;
                    // }

                return Promise.reject(reason);
            });
        },
    };
</script>
