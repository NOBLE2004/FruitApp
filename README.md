# Android MVP Base Architecture for Enterprise Mobile Application using Architectural Components

# Highlights

1. MVP Architectural pattern
2. Offline Support sql Room
3. Unit test demonstration using JUnit and Mockito
4. Kotlin



The application has been built with **offline support**. It has been designed using **Android Architecture components** with **Room** for offline data caching. The application is built in such a way that whenvever there is a service call, the result will be stored in local database.

The whole application is built based on the MVP architectural pattern.

# Application Architecture

The main advantage of using MVP, the itractor will connects to db / network and fetch the data , and it will pass to presenter where business 
logic is writter , Then presenter will update view accordingly



# Programming Practices Followed
a) Android Architectural Components <br/>
b) Dagger 2 for Dependency Injection <br/>
c) MVP <br/>
d) Retrofit with Okhttp <br/>
e) Room for data caching <br/>
f) JUnit and Mockito for Unit testing <br/>
d) RX Java <br/>


# How to build ?

Open terminal and type the below command to generate debug build <br/>

``` ./gradlew assembleDebug ```

Open terminal and type the below command to generate release build <br/>

``` ./gradlew assembleRelease ```

## Test cases

see android test and test packages

## Suggestions
we must use pagination since the data rows are larger