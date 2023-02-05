# LIST-NG SHARED

### Definition
Serves as a centralized configuration of all microservices. This project ensures:
- Same versions cross modules
  - Modules
  - Dependencies
- Mutual configurations
  - PostgreSQL DB
- Mutual interfaces and implementations for REST-full services

### Usage
Define `listng-shared` as a parent module for a microservice module as shown:
```
    <parent>
        <groupId>sk.fmfi.listng</groupId>
        <artifactId>listng-shared</artifactId>
        <version>1.0.0</version>
    </parent>
```
