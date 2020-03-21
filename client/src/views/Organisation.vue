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
        name: 'Home',
        components: {},
        data: () => ({
            breadcrumbItems: [
                {
                    to: {path: '/'},
                    text: 'Organisation',
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
