# BBS

BBS is a web application using Java/Spring for backend and Javascript/React for frontend.

## How To Run

```
1) Run docker locally
$ docker-compose up

2) Access http://localhost:3000 on browser of your choice
```

## User Stories

User can signup, and login.

```
POST http://localhost:8080/api/v1/auth/signup
{
    "username: "username",
    "email": "email",
    "password": "password"
}

POST http://localhost:8080/api/v1/auth/login
{
    "username: "username",
    "password": "password"
}
```

User can get/update its profile.

```
GET http://localhost:8080/api/v1/users/{id}

PUT http://localhost:8080/api/v1/users/{id}
{
    "username": "username"
}
```

User can create/get/modify/delete forms.

```
GET http://localhost:8080/api/v1/forms

GET http://localhost:8080/api/v1/forms/{id}

POST http://localhost:8080/api/v1/forms
{
    "title": "title",
    "description": "description"
}

PUT http://localhost:8080/api/v1/forms/{id}
{
    "title": "title"
}

DELETE http://localhost:8080/api/v1/forms/{id}
```

User can create/get/add/modify/delete comments on forms.

```
GET http://localhost:8080/api/v1/comments/{id}

POST http://localhost:8080/api/v1/forms/{formId}/comments
{
    "text": "text"
}

PUT http://localhost:8080/api/v1/comments/{id}
{
    "text": "text"
}

DELETE http://localhost:8080/api/v1/comments/{id}
```
