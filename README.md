# Recruitment Service

Designed to be user-friendly and efficient, aiming to improve how recruitment is managed for small organizations

## Features

A RESTful APIs for managing recruitment processes.
1. Employer API: For headhunters to manage job postings and employer details
2. Seeker API: For candidates to manage their profiles and apply for jobs
3. Jobs API: For headhunter to manage job listings
4. Resume API: For candidates to create, upload or manage resumes
5. Auth API: For handling authentication and authorization processes

## Installation

- [Java 17 or higher](https://www.oracle.com/java/technologies/downloads/)
- [Docker & Docker Compose](https://docs.docker.com/get-started/get-docker/)
- [Maven](https://maven.apache.org/download.cgi)

## Running the application

1. Clone the repository:

```bash
git clone https://github.com/your-username/recruitment-service-api.git
cd recruitment-service-api
```

2. Build the application:

```bash
./mvnw clean install
```

3. Start the services using Docker Compose

```bash
docker-compose up -d
```

4. Access the APIs:

- The APIs will be available at [http://localhost:8080](http://localhost:8080)
- Swagger documentation: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
