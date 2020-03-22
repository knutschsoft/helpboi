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
        name: 'Organisation',
        components: {},
        data: () => ({
            breadcrumbItems: [
                {
                    to: {path: '/organisation'},
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
