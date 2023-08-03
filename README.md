# quiz-generator
generate your quiz (using microservices architecture)

## What I used 🍃 

 java 17,
 maven as a build tool
 spring boot as my framework (3.1.1 v)
 spring mvc
 spring data JPA

 ## features 🍃   
 
 1:🌻 get all questions with the right answers (but user will request to quiz-service so this method is not allowed but you, as a developer can do this) 
 2:🌻 get questions by category (allowed only for developers not the client)
 
 3:🌻 add a question (for developers)
 
 4:🌻 get questions by difficulty level(for dev)

 5:🌻 update a question by id (allowed for developers)
 
 6:🌻 user can create a quiz (with category, a title and also number of questions)
 
 7:🌻 user can create quiz by difficulty level (level, a title, num of questions)
 
 8:🌻 get quiz by its id (we only return the questions with options)
 
 9:🌻 get quiz by its name (we only return the questions with options)
 
10:🌻 at the end user will submit his answers and we return his score
