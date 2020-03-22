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
            let payload = { isAuthenticated: false, user: null };
            axios.defaults.headers.common = {'Authorization': ''};
            this.$store.dispatch("security/onRefresh", payload);
            this.$router.push({path: "/login"});
        },
    }
</script>
