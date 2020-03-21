<template>
    <div>
        <UserCreate />
        <UserList
            :users="users"
        />
    </div>
</template>

<script>
    import UserCreate from '@/components/UserCreate';
    import UserList from '@/components/UserList';

    export default {
        name: 'Users',
        components: {
            UserCreate,
            UserList,
        },
        computed: {
            isLoading() {
                return this.$store.getters["user/isLoading"];
            },
            hasError() {
                return this.$store.getters["user/hasError"];
            },
            error() {
                return this.$store.getters["user/error"];
            },
            hasUsers() {
                return this.$store.getters["user/hasUsers"];
            },
            users() {
                return this.$store.getters["user/users"];
            },
        },
        created() {
            this.$store.dispatch("user/findAll");
        },
    }
</script>
