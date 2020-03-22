module.exports = {
    "transpileDependencies": [
        "vuetify"
    ],
    devServer: {
        disableHostCheck: true
    },
    configureWebpack: config => {
        if (process.env.NODE_ENV === 'production') {
            // mutate config for production...
        } else {
            // mutate for development...
        }
    },
    publicPath: process.env.NODE_ENV === 'production'
        ? 'https://helpboi.projektmotor.de/'
        : '/'
}
