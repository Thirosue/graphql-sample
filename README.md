# graphql-sample

> GraphQL Sample use Apollo Clinent and SpringBoot Server

## Required

* Docker
* yarn
* JDK11
* Chrome

## Build

* front

```
$ cd /path/to/directory/apollo-graphql-client-sample
$ yarn && yarn build
```

* Server

```
$ cd /path/to/directory/spring-boot-graphql-sample
$ ./gradlew bootJar
```

## Run 

```
$ cd /path/to/directory/
$ docker-compose up
```