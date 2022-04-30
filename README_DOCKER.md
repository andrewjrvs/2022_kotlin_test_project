# 2022_kotlin_test_project within DOCKER

the application has been built and tested as a docker image as well. 

*System Requirements*

- Containerization software (Like Docker Desktop, or Rancher Desktop)

_NOTE_: these indictions are written for the docker / dockerd commands.

## Build

from the base application path run the docker build command

```
$ docker build . -t local/2022-kotlin-test-project
```

## Running

### H2 - In memory database (default)

No changes needed; but please note, you will NOT be able to utilize the h2-console within the container. 

```
$ docker run -p 8080:8080 local/2022-kotlin-test-project
```

### Relational Database

Create a local env file name `RDB.env`

```env
# RDB.env
DB_CONNECTIONSTRING=YOUR CONNECTION STRING HERE
DB_USER=YOUR DB USER HERE
DB_PASSWORD=YOUR DB USER PASSWORD HERE
SPRING_PROFILES_ACTIVE=mysql
```

You can then run your docker image with the following content

```batch
 $ docker run -d -p 8080:8080 --env-file ./RDB.env  local/2022-kotlin-test-project
```

## TESTING

Follow the same testing technique when running locally see [Testing](./README.md###Manual)
