# L.I.S.T. New Generation (listng)
Bachelor Thesis Project
By **Filip Piták**,
for **FMPH Comenius University Bratislava**, 
2022/2023

## Technologies
- Adoptium OpenJDK (Temurin) 17.0.2+
- NodeJS (Angular)
- Maven

## Project Structure
- Domain logic of entities within ***listng-core***
  - Separated from REST logic in order to separate the domain logic from the service logic, enabling modularity and the possibility to modify scopes without affecting others
- Service templates and configurations within ***listng-infrastructure***
  - A library easy to plug into a new maven project as a dependency, giving a new module all the needed configurations and information to run and register with the existing modules
  - Serves as a centralized storage of configs, service ports, prepared classes for service communication and custom JMS
  
## Run instructions
- TODO