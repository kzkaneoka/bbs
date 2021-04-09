# BBS

BBS is a web application using Java/Spring for backend and Javascript/React for frontend.

## API Endpoints

```sh
# /auth
POST /auth/signup
POST /auth/login

# /users
GET /users
GET /users/{id}
POST /users/{id}
PUT /users/{id}
DELETE /users
DELETE /users/{id}

# /forms
GET /forms
GET /forms/{id}
POST /forms
PUT /forms/{id}
DELETE /forms
DELETE /forms/{id}

# /comments
GET /comments
GET /forms/{formId}/comments
GET /comments/{id}
POST /forms/{formId}/comments
PUT /forms/{formId}/comments/{id}
DELETE /forms/{formId}/comments/{id}
DELETE /comments
```

## How To Run

```sh
$ docker-compose up
```
