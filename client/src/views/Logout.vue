<template>
    <div></div>
</template>

<script>
    export default {
        name: "Logout",
        created() {
            this.$localStorage.set('payload', null);
            this.$localStorage.set('isAuthenticated', false);
            this.$localStorage.set('user', null);
            let payload = { isAuthenticated: false, user: null };
            this.$store.dispatch("security/onRefresh", payload);
            this.$router.push({path: "/login"});
        },
    }
</script>
