# sample-app

Spring OAuth 2.0 Resource Server with Access Token and Imgur apis to upload, delete and get user images.

# System requirement
- Java 17

# prerequisite
- local kafka setup
- In this application we are using Imgur APIs to upload and delete the images.
  - Required one Imgur client id that need to update in application.properties -> **imgur.clientId**
  - For more information about imgur apis use following link
      - https://apidocs.imgur.com/
  - Please make sure while creating cient it should be Anonymouse usage without user authorization

![image](https://github.com/ankit5858/sample-app/assets/11443617/99c853fb-a8bd-4cd2-97a5-b1f113def2e5)


# Getting Start
run the spring boot application and use attached postman collection.
