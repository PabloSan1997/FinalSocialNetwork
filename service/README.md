# Server My Socail Networks
 
This part describes the backend development.

## Tecnhologies

- **Libraries and Frameworks:** Spring boot, Spring security, Spring web, Jsonwebtoken.
- **Language**: Java

## Requests and Responses

### Register

Request path

```http
GET /api/user/register
```
Authenticate: No

Request header

```JSON
{
  "Content-Type": "application/json"
}
```
Request body

```JSON
{
  "username":"string",
  "nickname":"string",
  "password":"string",
  "description":"string",
  "urlPerfil":"string"
}
```

Response Body

```JSON
{
  "username": "string",
  "jwtoken": "string"
}
```

### Login

Request path

```http
GET /api/user/login
```
Authenticate: No

Request header

```JSON
{
  "Content-Type": "application/json"
}
```
Request body

```JSON
{
  "username":"string",
  "password":"string"
}
```

Response Body

```JSON
{
  "username": "string",
  "jwtoken": "string"
}
```


