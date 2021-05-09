# Cards Application

## Problem statement

A bank wants to launch 2 new credit card products, C1 and C2. To receive applications for the products the bank will collect the following detail about the _applicant_ in a web-form or mobile app, which the bank provides:

1. Name
2. Address
3. Email id

The submission of this form will be received by a Tradeledger API and sent over to a Thirdparty service for eligibility decisioning. The Thirdparty provides a RESTful api which responds with one of the following eligibilities:

1. C1
2. C2
3. Both C1 and C2
4. Neither C1 nor C2

The Tradeledger APIs should interface with the Thirdparty, sending an eligibility request and updating the user with the result of their application.

## Exercise Overview

### FE

1. Create React App (CRA)

### BE

1. A Microservices with Spring Boot (2.2.3.RELEASE+)
2. Build Automation Tool - Gradle (6.0.1+)

## Completing the exercise

### Submission

1. Fork the repo to your own repository or setup provided _.zip_ project into your own repository.
2. Complete the exercise.
3. Add [TL-Recruitment](https://github.com/TL-Recruitment) Github user to read your repository and/or provide a link to your repo with the completed task

### Duration

You should spend around ~1.5hrs on this task. You may spend longer if you wish, but ensure you think about which are the most critical pieces to complete first.

## How to Run

Open the project with IntelliJ or Eclipse or any other IDE of your choice

### FE
1. Go To terminal.
2. cd client/cards -- Browse to Directory i.e. client/cards
3. npm i -- to install the app
4. npm test -- to test the app
5. npm start -- to run the app

### BE
1. Go To terminal.
2. cd server/cards --- Browse to Directory i.e. server/cards
3. ./gradlew build -- to build the solution and third party
4. ./gradlew test -- to unit/integration test the solution and third party
5. ./gradlew bootRun -- to run the solution and third party applications simultaneously
6. ./gradlew clean -- to clean the solution and third party

or alternatively you can use IDE's builtin plugin for Gradle for these Gradle goals

_Note: Gradle version should be 6.0.1+_

## Task

1. For BE, in /server, build a synchronous Tradeledger API to orchestrate the journey from the application submission to retrieval of response. The API should call the provided Thirdparty endpoint over HTTP.
2. For FE, in /client, implement a call to the API you've just built and display the results using the provided components.

## Stub structure

### FE
Within `/client/cards` the FE application is a simple CRA. You are provided with a simple form with inputs for name, email and address. You are required to implement the code to call the BE service and display the result. 
Feel free to include any additional packages to implement, test and style the application.

### BE
BE stub is provided in `/server/cards/tl-api`, this is setup to run a spring bootrun service on `localhost:8080`. 
There is also the third party service which is setup as a separate subproject within gradle in `/server/cards/thirdparty`, 
this exposes a third party service endpoint on `localhost:3317`.

The structure of the `com.tradeledger.cards` structure is just a suggestion, feel free to modify or structure
as you think is logical, makes sense and extendable in the future.

### Thirdparty contract:

_Request_

```
POST /eligibility/check
```

```json
{
  "name": "String",
  "email": "String",
  "address": "String"
}
```

_Response_

```json
{
  "eligibleCards": "Array of C1, C2, BOTH or None"
}
```

## Considerations (only for Solution Design section):

1. The bank believes that the products would be extremely popular and around 1 million requests will be received in the first hour, tailing off from there
2. The Thirdparty might take up to 10secs to respond to the request
3. The processed applications and results need to be stored for up to 7 years for audit purposes
