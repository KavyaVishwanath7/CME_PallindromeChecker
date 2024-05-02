# PalindromeChecker
PalindromeChecker is a simple web service, designed using the Spring-Boot framework, to determine whether a given string is a palindrome or not. It follows a microservice architecture with main components: Controller, Service, and Repository. This documentation provides an overview of each component and how they interact with the application.

API : /api/v1/test/checkPalindrome


## Technical features
* PalindromeCheckerAPIController
 
  Non-Blocking api using WebFlux
  RestController
  Accessible from any domain
* PalindromeService
 
  checkPalindrome method to verify palindrome or not
  Cache load on application startup
  ConcurrentHashMap for cache
  Interacts with FileRepository for writing and reading Json file
* PalindromFileRepository
  
  Read and write Json file, calls methods from StorageUtilities
* PalindromeDBRepository
  
  Extends JPARepository, ready for transaction with DB(used Oracle)
* PalindromeObject
  
  Object model to store userName, textValue & isPalindrome
  Custom Validation(annotation) on textValue field
* CustomExceptionHandlng for printing custom error message from validation
* Logging info, debug and error messages using Slf4j
* Junit test cases covering all use-cases for checkPalindrome api calls


## How to run PalindromeChecker
* After importing as maven project or copying to a folder run below command

   mvn clean install
* From IDE: Navigate to src/main/java/com/cme/palindrome/PalindromeCheckerApplication.java and run the application
* From the command prompt, navigate to project folder and run below command 

  mvn spring-boot:run

PalindromeChecker by default runs on 8080 port

url : http://localhost:8080/api/v1/test/checkPalindrome

input body:
{
    "userName": "user",
    "textValue": "deed"
}


## Testing
* Valid Input
![image](https://github.com/KavyaVishwanath7/PalindromeChecker/assets/168433126/c0778286-42ea-4fc8-bd97-3512f933ee18)

* Valid Input
![image](https://github.com/KavyaVishwanath7/PalindromeChecker/assets/168433126/f8492ef7-d265-4e1c-9009-c6ca4b308d91)

* Invalid Input
![image](https://github.com/KavyaVishwanath7/PalindromeChecker/assets/168433126/d11c3bfd-abab-40a4-a8fb-2f1fbe20cd15)

* Junit Tests
![image](https://github.com/KavyaVishwanath7/PalindromeChecker/assets/168433126/c699213b-513e-49d8-b7b3-8085e756c092)  
  
