# Server My Socail Networks
 
This part describes the backend development.

## Tecnhologies

- **Libraries and Frameworks:** Spring boot, Spring security, Spring web, Jsonwebtoken, Bcrypt.
- **Language**: Java

## Requests and Responses

### Register

This request creates a new user with his roles, hash password and responses with a jsonwebtoken

Request path

```http
POST /api/user/register
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

This request authenticates the user with their username and password to respond with a jsonwebtoken. The token is register in the database with the time it was generated.

Request path

```http
POST /api/user/login
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


### Logout

In the database there is a table called `login_register`, this table has a column called `enabled`. This request changes the "enabled" value "true" to "false" to block the token making the request.

Request path

```http
POST /api/user/logout
```
Authenticate: Yes

Request header

```JSON
{
  "Authentication": "Bearer token"
}
```
