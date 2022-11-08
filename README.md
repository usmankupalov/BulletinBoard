# BulletinBoard

## Description
- News service where any registered user can share news. Written news is displayed in the general list of news after approval by the Administrator.
The administrator can approve or refuse the publication of the news.

## Details :
- The service provides 4 web pages
- Authorization page
- Registration page
- Form for sending/editing/viewing news
- News list news (main page)

- Page 1 requires login and password
- Page 2 requires a unique Login, password, email and age
- Access to pages 3 and 4 is possible only for authorized users
- Page 3 is a text field for entering the text of the news and functional buttons "Submit" and "Cancel". The functionality of page 3 depends on the role of the user (Administrator or Simple User) and on the current action being performed (Creating a new message, editing an existing one).
- Page 4 displays a list of approved news and personal news for a simple user and all news (both approved and new) for the Administrator, in the form of a table with the full text of the news, the author's login and mail, the date the news was created, the date of approval by the administrator.
- The user has an edit button for personal (created by him) news. If already approved news is edited (or whose news is rejected), then the status of such news becomes new again and requires approval by the Administrator.
- For new news, the Administrator has the “Approve” and “Reject” buttons that change the status of the news. When approved, the news is visible to all users.

## Functional requirements
- The administrator is created when the service is first initialized.
- Correct custom errors should be displayed.

## Technical requirements :
- jdk11
- maven
- spring boot
- spring security (jwtFilter, token) 
- liquibase
- sql db (postgresql)
