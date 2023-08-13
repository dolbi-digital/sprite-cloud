1. For UI I've chosen tests to check names and descriptions of chapters
   and Authorization cases that always could be relevant.
2. For API just CRUD operations for some Pet.

How to run the tests locally using Jenkins:
1. Tests could be run by maven command:
mvn clean test
2. Test results are stored in "target/allure-results" folder. 
3. To make allure html report please execute:
allure serve target/allure-results
4. To install jenkins run docker command:
docker run -p 8080:8080 -v "$PWD/jenkins:/var/jenkins_home" jenkins/jenkins:lts
Or:
brew install jenkins-lts
brew services start jenkins-lts
5. Just create a freestyle project with checking out code from
https://github.com/dolbi-digital/sprite-cloud
6. Add maven clean test command:
Job Configuration -> Build Steps -> Invoke top-level Maven targets 
-> Goals (clean test)
7. Install Allure plugin abd add report settings:
Post-build Actions -> Allure Report -> Path (target/allure-results)
8. Launch the job and report should be attached to the build page.