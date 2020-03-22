export default {
    apiUrl: process.env.NODE_ENV === 'production'
        ? 'https://helpboi.projektmotor.de'
        : 'http://localhost:8080',
}
