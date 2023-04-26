# L.I.S.T. New Generation (listng)
Bachelor Thesis Project
By **Filip Piták**,
for **FMPH Comenius University Bratislava**, 
2022/2023

## Technologies
- OpenJDK 17.0.2+
- NodeJS (Angular 15)
- Maven

## Project Structure
- [GUI directory](/GUI) contains FE and its REST application, which provides FE with data and operations by being the middleman, executin the communication between other services.
- [MCS directory](/Services) containing all the microservices, which have an isolated context, ensuring we have responsibilities logically divided.
- [Infrastructure directory](/Infrastructure) has the [base domain (listng-core)](/Infrastructure/listng-core) Java project, containing any needed functionality, [infrastructure project](/Infrastructure/listng-infrastructure) holding all common functionality for MCSs and [setup scripts](/Infrastructure/setup) containing all needed scripts to initialize DB, download and install projects, start and other.
- [Development directory](_development) contains pseudo-projects with appropriate modules imported, used as a single project to open, develop and run multiple modules at a same time.
  
## Run instructions
- TODO
