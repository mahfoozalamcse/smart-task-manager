# Smart Task Manager

Full-stack task management application built using React.js and Spring Boot.

## Features

- JWT Authentication
- Role-Based Access
- Task CRUD Operations
- Swagger Documentation
- Protected Routes
- Multi-user System
- Docker Support

## Tech Stack

### Backend
- Java 21
- Spring Boot
- Spring Security
- JWT
- MySQL
- Swagger

### Frontend
- React.js
- Vite
- Tailwind CSS
- Axios

## Project Structure

```bash
backend/
frontend/
docker-compose.yml
```

## Run Backend

```bash
cd backend
mvnw spring-boot:run
```

## Run Frontend

```bash
cd frontend
npm install
npm run dev
```

## Docker Run

```bash
docker compose up --build
```

## API Documentation

```bash
http://localhost:8080/swagger-ui/index.html
```
