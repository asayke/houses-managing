# houses-managing
1. Регистрация нового пользователя - /api/registration/, post-request с json-телом :
```json
{
  "username":"yourusername",
  "name":"yourname",
  "age":0,
  "password":"yourpassword"
}
```
2. Логин - /api/login/, post-request с json-телом :
```json
{
  "username":"yourusername",
  "password":"yourpassword"
}
```
В ответ получаем token, далее, чтобы сделать запрос от имени пользователя достаточно добавить http-header "Authorization", значение которого представляет собой Bearer_token.
