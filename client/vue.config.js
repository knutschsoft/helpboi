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
        ? '/' // there seems to be no need to change it to https://helpboi.projektmotor.de
        : '/'
}
