# java-vuejs-template

# Startup Environment

Depending on the requirements of teh development environment you 
can spin up the required services from the `docker-compose.yml` file.

`docker-compose down`
`docker-compose up -d --build [flyway|api|client]`

# API

#### Links

* https://docs.micronaut.io/latest/guide/index.html  
* https://micronaut-projects.github.io/micronaut-data

### Other 

Endpoint: `https://localhost:8080`  
Documentation: `https://localhost:8080/documentation.html`  
Credentials: `root@root.com:root`

# Client

### Other 

Endpoint: `http://localhost:8085`  

### Project setup
```
npm install
```

#### Compiles and hot-reloads for development
```
npm run serve
```

#### Compiles and minifies for production
```
npm run build
```

#### Lints and fixes files
```
npm run lint
```

#### Customize configuration
See [Configuration Reference](https://cli.vuejs.org/config/).
