let username = 'root';
let password = 'root';
var basicAuth = 'Basic ' + btoa(username + ':' + password);

export default {
    apiUrl: 'http://localhost:8080',
    axiosGetAuth: {
        auth: {
            username: username,
            password: password,
        },
    },
    axiosPostAuth: {
        headers: { 'Authorization': + basicAuth }
    }
}
