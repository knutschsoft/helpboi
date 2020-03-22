<template>
    <div>
        <v-breadcrumbs
            :items="breadcrumbItems"
            divider=">"
        />
    </div>
</template>

<script>

    export default {
        name: 'Tasks',
        components: {},
        data: () => ({
            breadcrumbItems: [
                {
                    to: {path: '/aufgaben'},
                    text: 'Organisation',
                    disabled: false,
                    exact: false,
                },
                {
                    to: 'details',
                    text: 'Aufgaben',
                    disabled: true,
                },
            ]
        })
    }
</script>
