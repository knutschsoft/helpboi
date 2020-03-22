<template>
    <div>
        <v-breadcrumbs
            large
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
