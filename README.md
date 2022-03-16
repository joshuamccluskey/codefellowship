# codefellowship

## CodeFellowship

### Contributors Joshua McCluskey

### Implementation

- Please adjust postgres url, username, password to access your local database on the following link [Postgres Properties](src/main/resources/application.properties)
- Build and run app from root codefellowship/ on IDE or using the following in the command line/ terminal
- See link and run from the file [Run App from this file in IDE](src/main/java/com/joshuamccluskey/codefellowship/CodefellowshipApplication.java)
- Link to browser [See App in Browser](http://localhost:8080/)
- For integration testing, run command Line/terminal  : `./gradlew test`
- For build, input in command Line/terminal : `./gradle build`
- To run app, input in command line/terminal: `./gradle bootRun`


### Overview
Build an app that allows users to log into CodeFellowship.
- Sit has a  login page, link to signup page, link to login and logout
- An ApplicationUser should have a username, password (will be hashed using BCrypt), firstName, lastName, dateOfBirth, bio, and any other fields you think are useful.
- The site should allow users to create an ApplicationUser on the “sign up” page.

Files:

[ApplicationUserController](src/main/java/com/joshuamccluskey/codefellowship/controller/ApplicationUserController.java)
[ApplicationUser](src/main/java/com/joshuamccluskey/codefellowship/model/ApplicationUser.java)
[ApplicationUserRepository](src/main/java/com/joshuamccluskey/codefellowship/repository/ApplicationUserRepository.java)
[WebSecurityConfig](src/main/java/com/joshuamccluskey/codefellowship/security/WebSecurityConfig.java)
[UserDetailsServiceImpl](src/main/java/com/joshuamccluskey/codefellowship/service/UserDetailsServiceImpl.java)

Work Time 5 Hours

### Sources:
Stock Photo : https://stock.adobe.com/search?k=%22default+profile+picture%22&asset_id=64675209


## CodeFellowship Day 2 Authorization

### Contributors Joshua McCluskey

### Overview
- Build an app that allows users to log into CodeFellowship.

- Upon logging in, users should be taken to a /myprofile route that displays their information.
- This should include a default profile picture, which is the same for every user, and their basic information from ApplicationUser.
- The site should have a page which allows viewing the data about a single ApplicationUser, at a route like /users/{id}.
- This should include a default profile picture, which is the same for every user, and their basic information.
- Continue to ensure that your homepage, login, and registration routes are accessible to non-logged in users. All other routes should be limited to logged-in users.
- Add a Post entity to your app.
- A Post has a body and a createdAt timestamp.
- A logged-in user should be able to create a Post, and a post should belong to the user that created it.
- Hint: This is a relationship between two pieces of data
- A user’s posts should be visible on their profile page.
- The site should use reusable templates for its information. (At a minimum, it should have one Thymeleaf fragment that is used on multiple pages.)
- The site should have a non-whitelabel error handling page that lets the user know, at minimum, the error code and a brief message about what went wrong.

### Stretch Goals

- A logged-in user should be able to edit their profile. (Double stretch: allow them to change their passwords if they enter their current password.)
- An “admin” user should be able to edit anyone’s profile.

Work Time 4 hours

## CodeFellowship Day 3 Authorization

### Contributors Joshua McCluskey

### Overview
- Ensure that users can’t perform SQL injection or HTML injection with their posts.
- Allow users to follow other users. Following a user means that their posts show up in the logged-in user’s feed, where they can see what all of their followed users have posted recently.
    - Ensure there is some way (like a users index page) that a user can discover other users on the service.
    - On a user profile page that does NOT belong to the currently logged-in user, display a “Follow” button. When a user clicks that follow button, the logged-in user is now following the viewed-profile-page user.
        - note: this will require a self-join on ApplicationUsers.
    - A user can visit a url (like /feed) to view all of the posts from the users that they follow.
        - Each post should have a link to the user profile of the user who wrote the post.

#### Old feature tasks that are still required
- A splash page with basic information about the site
- The ability for users to register for new accounts and log in.
- The ability for logged-in users to create posts.
- The ability to see a user’s posts, along with their profile information and a default profile picture, on their profile page.
- A pleasing design throughout the site.
- Thymeleaf templates & fragments used appropriately to keep view code DRY.
- Smooth error handling with appropriate responses to bad requests.
- Integration testing on (at minimum) the splash page, login, and sign up routes.


### Stretch Goals

- When users create posts, allow them to specify whether or not a post is “public”. Public posts show up in everyone’s feeds.
- Add the ability for users to comment on posts.

Work Time 3 hours

### Refactor 

- Made sure login worked and all features were working
- Worktime 3 Hours
- 
### Refactor

- Account Info persists;Login Success; Profile page complete; Users Page Complete: Post created: Styling;
- Worktime 5 Hours
