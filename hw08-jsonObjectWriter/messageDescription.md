
* authUserRequest - запрос от GUI к БД на авторизацию пользователя
* addUserRequest - запрос от GUI к БД на добавление пользователя
* delUserRequest - запрос от GUI к БД на удаление пользователя
* authUserResponse - ответ от БД в GUI на запрос "авторизация пользователя"
* addUserResponse - ответ от БД в GUI на запрос "добавление пользователя"
* delUserResponse - ответ от БД в GUI на запрос "удаление пользователя"

#### authUserRequest
```json
{
  "type" : "authUserRequest",
  "login" : "...",
  "password" : "..."
}
``` 

#### addUserRequest
```json
{
  "type" : "authUserRequest",
  "login" : "...",
  "password" : "..."
}
``` 

#### delUserRequest
```json
{
  "type" : "authUserRequest",
  "login" : "..."
}
``` 

#### authUserResponse - admin, success 
```json
{
  "type" : "authUserResponse",
  "status" : "admin-success",
  "users" : [
    {
      "id" : 1,
      "login" : "...",
      "password" : "...",
      "admin" : true
    },
    {
      "id" : 2,
      "login" : "...",
      "password" : "...",
      "admin" : false
    }   
  ]
}
```
Поле 'users' содержит информацию о пользователях в БД

#### authUserResponse - user, success
```json
{
  "type" : "authUserResponse",
  "status" : "user-success",
  "login" : "..."
}
```

#### authUserResponse - wrong login or/and password
```json
{
  "type" : "authUserResponse",
  "status" : "wrong-login-password"
}
```

#### addUserResponse 
```json
{
  "type" : "addUserResponse",
  "status" : "...",
  "users" : []
}
```
##### status
* User ??? was added
* Wrong login/password
* User ??? already exists

##### users
* Array of json-objects with users data

#### delUserResponse 
```json
{
  "type" : "delUserResponse",
  "status" : "...",
  "users" : []
}
```
##### status
* User ??? was deleted
* Wrong login, it is empty
* User ??? doesn't exist

##### users
* Array of json-objects with users data