### USER API DOCUMENTATION
## Register User
Endpont : POST /api/users
Request Body :
```json
{
  "username" : "haris",
  "password" : "haris",
  "name" : "haris priantury"
}
```
Response Success :
```json
{
    "data": "haris priantury success registered ",
    "errors": null
}
```

## Login User
Endpoint : POST /api/auth/login
Request Body : 
```json
{
    "username" : "haris",
    "password" : "haris"
}
```

Response Success :
```json
{
    "data": {
        "username": "haris",
        "token": "79187353-d494-4d10-8a38-207e9527ede5",
        "tokenExpiredAt": 1698520862628
    },
    "errors": null
}
```

Response Failed :
```json
{
    "data": null,
    "errors": "401 UNAUTHORIZED"
}
```

## Get User
Endpoint : GET /api/users/current
Request Header : "X-TOKEN-API" 

Response Succes :
```json
{
    "data": {
        "name": "priantury",
        "username": "haris"
    },
    "errors": null
}
```
Response Failed :
```json
{
    "data": null,
    "errors": "401 UNAUTHORIZED \"unauthorized\""
}
```

## Update User
Endpoint : PATCH /api/users/current

Request Header : "X-TOKEN-API" 

Request Body :
```json
{
    "name" : "priantury",
    "password" : "priantury",
    "username" : "priantury"
}
```
Response Success :
```json
{
    "data": {
        "name": "haris",
        "username": null
    },
    "errors": null
}
```
Response Failed :
```json
{
    "data": null,
    "errors": "401 UNAUTHORIZED \"gagal update unauthorized\""
}
```