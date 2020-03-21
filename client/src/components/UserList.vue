<template>
    <v-container>
        <v-row class="text-center">
            <v-col
                cols="12"
                v-for="user in users"
                :key="user.id"
            >

                {{ user.id }}<br>
                {{ user.firstname }}<br>
                {{ user.lastname }}<br>
                {{ user.email }}<br>
                {{ user.phone }}<br>
            </v-col>
        </v-row>
    </v-container>
</template>

<script>
    export default {
        name: 'UserList',
        props: {
            users: {
                required: true,
                type: Array,
            }
        },
        data: () => ({
        }),
    }
</script>
