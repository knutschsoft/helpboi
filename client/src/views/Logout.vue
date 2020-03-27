<template>
    <div></div>
</template>

<script>
    export default {
        name: "Logout",
        created() {
            this.$store.commit('reset');
            let onRefreshPayload = {
                user: null,
                isAuthenticated: false,
                payload: null,
            };
            this.$store.dispatch('security/onRefresh', onRefreshPayload);
            this.$router.push({path: "/login"});
        },
    }
</script>
