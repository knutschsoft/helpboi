<template>
    <v-container fluid>
        <v-breadcrumbs
            large
            :items="breadcrumbItems"
            divider=">"
        />
    </v-container>
</template>

<script>

    export default {
        name: 'Tasks',
        components: {},
        data: () => ({
            breadcrumbItems: [
                {
                    to: {path: '/aufgaben'},
                    text: 'Aufgaben',
                    disabled: false,
                    exact: false,
                },
                {
                    to: 'details',
                    text: 'Details',
                    disabled: true,
                },
            ]
        })
    }
</script>
