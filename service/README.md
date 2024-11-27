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

In the database there is a table called `login_register`, this table has a column called `enabled`. This request changes the `enabled` value "true" to "false" to block the token making the request.

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

### User info and get friend info

This request gets the own user or friend info.

Request paths

- User info

```http
GET /api/user/userInfo
```

- Friend info

```http
GET /api/user/otherUser?username={username}
```

Authenticate: yes

Request header

```JSON
{
  "Authentication": "Bearer token"
}
```

Response Body

```JSON
{
  "id": "number",
  "username": "string",
  "nickname": "string",
  "description": "string",
  "urlPerfil": "string",
  "following": "number",
  "followers": "number"
}
```

### Followers and Followings

This requests get followers and followins of a user.

Request paths

- Followers

```http
GET /api/user/follow/find/followers/{idUser}?page={page}&size={size}
```

- Followings

```http
GET /api/user/follow/find/followings/{idUSer}?page={number}&size={number}
```

Authenticate: yes

Request header

```JSON
{
  "Authentication": "Bearer token"
}
```

Response Body

```JSON
{
  "users": [
    {
      "username": "string",
      "nickname": "string",
      "userInfo": {
        "id": "number",
        "urlPerfil": "string"
      }
    }
  ]
}
```

### Count follows

This requests get followers and followins numbers

Request paths


```http
GET /api/user/follow/count/{idUser}
```


Authenticate: yes

Request header

```JSON
{
  "Authentication": "Bearer token"
}
```

Response Body

```JSON
{
  "following": "number",
  "followers": "number"
}
```

### Know if it is following

This requests responses with a boolean to know if a user is following other user. The main user is from the token and the secon user is from
dynamic URL 

Request paths

```http
GET /api/user/follow/followUser?username={string}
```

Authenticate: yes

Request header

```JSON
{
  "Authentication": "Bearer token"
}
```

Response Body

```JSON
{
  "followingThisUser": "boolean"
}
```

### Search friend

This request is to search for people.

Request paths

```http
GET /api/user/search/{username or nickname}
```

Authenticate: yes

Request header

```JSON
{
  "Authentication": "Bearer token"
}
```

Response Body

```JSON
{
  "usernames": [
    {
      "username": "string",
      "nickname": "string",
      "userInfo": {
        "id": "number",
        "urlPerfil": "string"
      }
    }
  ],
  "nicknames": [
    {
      "username": "string",
      "nickname": "string",
      "userInfo": {
        "id": "number",
        "urlPerfil": "string"
      }
    }
  ]
}
```

### Generate follow

This request creates a new follow

Request paths

```http
GET /api/user/follow/{idUser}
```

Authenticate: yes

Request header

```JSON
{
  "Authentication": "Bearer token"
}
```

Response Body

```JSON
{
  "followingThisUser": true
}
```