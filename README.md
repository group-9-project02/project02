README.md
Title: SoundCritic

Description: SoundCritic is a JavaFX desktop application that allows music fans to search for albums and tracks using the Spotify Web API and write personal reviews for them. Users create an account and log in to access the app. Once authenticated, they can search Spotify’s catalog, browse album tracklists, and save star ratings along with written reviews to a local SQLite database. All reviews are stored and tied to the logged-in user, making it a personal music journal backed by Spotify’s data. 

Use Cases: 

1: User Login
	Main Flow: User launches app and sees the Login screen. Enters username and password, then click the "Login” button. 
	Alternate Flow: User sees Login screen and clicks ‘Register New Account’. User enters a desired username and password then clicks ‘Register’ button. 

2: Search for an album
	Main Flow: User enters an album name into a search field and is shown album names matching the search field. The user can then select the desired album and go to the albums page.

3. Write the album review
	Main Flow: When viewing an album page, the user can select an option to write a review for the album. This review will include a rating system and will provide the user with a text box to form a written review of the album.

4. View personal review history
	Main Flow: User clicks on review history button and is brought to a page displaying all past user reviews.
	Alternate Flow: User clicks on profile button, from profile user clicks on past reviews button.

5. Edit an existing review
	Main Flow: User can click on an icon from the existing review screen to edit their review from, and be able to type in their edit. Then would be able to save, and see that new response.
	Alternate Flow: When viewing an album directly, the user can see any review they have previously made and select an option to edit the review

6. Delete a review
	Main Flow: When viewing the list of all their reviews, a user can select to delete/remove a review that they have previously made to be deleted.
	Alternate Flow: When viewing an album directly, the user can see any review they have previously made and select an option to delete it

Entity Relationship Diagram:
![Entity Relationship Diagram](https://github.com/user-attachments/assets/5545a1dc-448c-40ae-8d0a-756673a8e6c1)

Testing Plan: All but save User login and User registration assume a user has been created.

User Login: Manually put user information into our database. Test validation function by pulling user information and matching it against a string that is different from the password. Then test against a string that matches the password. 
	
Album Search: First make sure an album is stored in the database. Have the tests utilize a search function that scans the database for a matching album name. In one test, make sure the album string doesn't match and in another make sure it does. 
	
Create Album review: First store an album in the database. Next pull from the database and make sure the review portion of the stored album is blank. Next utilize the function that adds and stores a review by passing in a non empty string. Finally, pull the same album from the database and make sure the review portion matches the aforementioned review string.

View Personal review: Attempt to use function that pulls review history and make sure it returns nothing. Next populate the database with a list of albums and reviews assigned to the created user. Test the function and make sure reviews match the inputted album/review information.
	
Edit Album review: Populate database with one album and an album review. Create a string that will be used to update/edit the existing review. Pull from database and validate that db string doesn't match our created string. Then utilize our function that updates reviews. Next pull the same album from the database and make sure it matches our updated review string.
	
Delete Album review: Populate database with an album and an album review. Pull album information from database and make sure review portion is populated. Next utilize function to delete a review for the specified album. Now again, pull album from database and make sure the review portion is empty. 

User registration: First attempt to login with user information not present in the database and validate that it doesn't work. Next user the function that registers a user then utilize the login function to make sure a user has been properly registered. 

Scene Mockups:
![Scene Mockups](https://github.com/user-attachments/assets/65a38159-4b46-40ff-b212-e7c12b4ec80b)

GitHub Link: https://github.com/group-9-project02/project02


