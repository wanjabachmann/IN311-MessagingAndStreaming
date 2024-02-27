# Blog Validation

This project conatins an example of a blog backend and a validation service.
It processes the blog content and determines whether they are valid or not.
The project uses Kafka as streaming plattform

## Start the project in dev Mode

```PowerShell
cd .\blog-backend\
.\mvnw compile quarkus:dev

cd .\text-validator\
.\mvnw compile quarkus:dev
```

## API

[http://localhost:8080/q/swagger-ui/#/](http://localhost:8080/q/swagger-ui/#/)

## Kafka Toplics

[http://localhost:8080/q/dev-ui/io.quarkus.quarkus-kafka-client/topics](http://localhost:8080/q/dev-ui/io.quarkus.quarkus-kafka-client/topics)

## Example Valid

POST → content contains a 0

```JSON
{
"title": "Title of the Blog",
"content": "Is this blog valid? 0"
}
```

```JSON
{
"content": "Is this blog valid? 0",
"id": 1,
"title": "Title of the Blog",
"valid": false
}
```

## Example Not Valid

POST → content **doesn't** contain a 0

```JSON
{
"title": "Title",
"content": "Is this blog valid?"
}
```

```JSON
{
"content": "Is this blog valid?",
"id": 2,
"title": "Title",
"valid": true,
"validationDate": "2024-02-27"
}
```
