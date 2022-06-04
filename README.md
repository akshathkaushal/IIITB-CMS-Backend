![CircleCI](https://circleci.com/gh/akshathkaushal/IIITB-CMS-Backend.svg?style=svg&circle-token=82792b994f6b5a5e5baf538ed6d2f2b50ea144b7)
![Maven](https://github.com/akshathkaushal/IIITB-CMS-Backend/actions/workflows/mvn-verify.yml/badge.svg)
![Lint](https://github.com/akshathkaushal/IIITB-CMS-Backend/actions/workflows/linter.yml/badge.svg)

# IIITB CMS Backend
Contains backend code for IIITB CMS

### Running the project
#### Clean the target directory
`mvn clean`
#### Install the dependencies
`mvn install`
#### Compile the Java source classes
`mvn compiler:compile`
#### Compile the test classes
`mvn compiler:testCompile`
#### Build the project and package
`mvn build package`

***

### API Documentation
Check the API docs at [http://localhost:8090/swagger-ui.html#/](http://localhost:8090/swagger-ui.html#/) after running the project.

***
### Mailbox
As of now, I have used a pseudo mail box, `mailtrap.io` for account validation after signup. Credentials for that need to be changed in the `application.properties` file.

***

# For IIITBSoC
Queries welcome at [akshath.kaushal@iiitb.ac.in](mailto:akshath.kaushal@iiitb.ac.in)    
Check the front end repository [here](https://github.com/akshathkaushal/IIITB-CMS-Frontend)
