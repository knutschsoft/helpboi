export default {
    apiUrl: process.env.NODE_ENV === 'production'
        ? 'https://api.helpboi.projektmotor.de'
        : 'http://localhost:8080',
}
