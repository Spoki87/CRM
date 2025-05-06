
# CRM

---

## 🔧 Tech Stack

- Java 17  
- Spring Boot 3
- Spring Security
- Hibernate / JPA  
- PostgreSQL
- Lombok  
- Redis
- Docker
- JUnit + Mockito
- Swagger / OpenAPI 3

---

## 🚀 Getting Started

DATABASE:

`DB_URL`
`DB_USERNAME`
`DB_PASSWORD`

REDIS:

`REDIS_HOST`
`REDIS_PORT`


### Clone and Run the Project

```bash
git clone git@github.com:Spoki87/crm.git
cd crm
mvn clean package 
docker compose up
```

When the program is launched, a user with super admin privileges is automatically added.

`Username: SUPERADMIN`
`email: example@example.pl`
`Password: root`

## 🛡️ Security

The application uses session-based authentication with JSESSIONID tokens managed by Spring Security. This allows secure user login and session persistence without relying on stateless JWT tokens.
- Based on Spring Security's session mechanism
- Protected endpoints require authentication via session cookie

## 🗂️ Project Structure

```
com.reservation
├── api 
├── config                        
├── exception
  ├── business
  ├── domain
  ├── handler              
├── hotel          
├── model
├── module
  ├── lead
  ├── contact
  ├── hotel
  ├── company
  ├── opportunity
  ├── activity
    ├── call
    ├── meeting
    ├── task     
├── user
  ├──appuser
  ├──security          
```

## Example API Endpoints


| Endpoint                         | Method | Description                     |
|----------------------------------|--------|---------------------------------|
| `/api/auth/login`            | POST   | User authentication               |
| `/api/user`               | POST   | Add new user   |
| `/api/leads`                     | GET   | All Leads           |



**Swagger UI available at:**  
[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
## Author

- [@Patryk Pawlak](https://www.github.com/Spoki87)

