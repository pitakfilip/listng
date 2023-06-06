# L.I.S.T. New Generation (listng)
Bachelor Thesis Project
By **Filip Piták**,
for **FMPH Comenius University Bratislava**, 
2022/2023

## Required Technologies
- OpenJDK 17.0.2+
- NodeJs 16.17+
- NPM (bundled with NodeJs)
- Angular 15.2.0+
- Consul executable (1.14.4)
- Maven

## Project Structure
- [GUI directory](/GUI) contains FE and its REST application, which provides FE with data and operations by being the middleman, executin the communication between other services.
- [MCS directory](/Services) containing all the microservices, which have an isolated context, ensuring we have responsibilities logically divided.
- [Infrastructure directory](/Infrastructure) contains [infrastructure project](/Infrastructure/listng-infrastructure) holding all common functionality for MCSs and [setup scripts](/Infrastructure/setup) containing all needed scripts to initialize DB, download and install projects, start and other.
- [Development directory](_development) contains pseudo-projects with appropriate modules imported, used as a single project to open, develop and run multiple modules at a same time.
  
## Run instructions (local)
- Download [AMD64 executable file](https://developer.hashicorp.com/consul/downloads) for Consul `version 1.14.4` and place into [consul module resources](/Services/listng-consul-develop/src/main/resources). Within `application.properties` make value for `consul.executable.filename` is correct.
- Download and install NodeJs with NPM (node package manager). Used version `v16.17.0`, however using the newest LTS version `v18.16.0` should be compatible.
- Download and install Angular CLI via `npm install -g @angular/cli`.
- Verify Angular installation with `ng version`, which should show the installed version (any version of Angular 15 is acceptable).
- Within the [service remote properties](/Services/listng-consul-develop/src/main/resources/configuration-properties) verify the PostgreSQL datasource credentials
- Maven build projects using [bash build script](/Infrastructure/setup/scripts/build.sh), which builds everything in order.
- Start applications in order:
  - ListNG Consul
  - Domain applications (User, Course, ...)
  - REST
  - Angular web application
- Webapp runs on [localhost:4200](http://localhost:4200/)
