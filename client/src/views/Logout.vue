<template>
    <div></div>
</template>

<script>
    import axios from "axios";

    export default {
        name: "Logout",
        created() {
            this.$localStorage.set('payload', null);
            this.$localStorage.set('isAuthenticated', false);
            this.$localStorage.set('user', null);
            axios.defaults.headers.common = {};
            this.$store.commit('reset');
            this.$router.push({path: "/login"});
        },
    }
</script>
