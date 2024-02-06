https://docs.google.com/document/d/1NxvKVQ4NdH4BmzlOHZ-VGoc2AZB3EKfRoDZvZLwuzOQ/edit?urp=gmail_link#heading=h.gmf6mr73qdp5

https://docs.github.com/en/rest/repos/repos?apiVersion=2022-11-28
# Problem Statement
Your task is to create a simple Android app that fetches a list of GitHub repositories using the GitHub API and displays them in a RecyclerView.
The app should have two screens:

# Screen 1: Repository List
- Show a list of GitHub repositories.
- Each item in the list should show the repository name, description, and the number of stars.
- The list should be sorted by the number of stars in descending order.
- When the user taps on an item, it should take them to the repository details screen.

# Screen 2: Repository Details
- Show the details of the selected repository.
- The details should include the repository name, description, the number of stars, the number of forks, and the primary language used in the repository.
- You should also display a button that takes the user to the GitHub page of the repository.

# Requirements
Use Kotlin for the app development.
Use MVVM or MVP architecture.
Use Retrofit to fetch data from the GitHub API.
Use Glide or Picasso to load images.
Use RecyclerView for the repository list.
Use ConstraintLayout for the layout.
Write unit tests for the repository list and repository details view models.
Implement error handling and display appropriate error messages to the user.
Make sure the app is optimized for performance and can handle large datasets.
The app should be able to run on Android 5.0 and above.
Evaluation Criteria
Clean and organized code.
Performance optimization.
Error handling and resilience.
Good understanding of the Android framework and architecture patterns.
Ability to work without a QA team and deliver quality code.
Ability to write unit tests and ensure code quality.
Familiarity with continuous delivery and shipping code regularly.
Bonus Points
Implement pagination to handle large datasets.
Use Kotlin Coroutines to handle threading and background tasks.
Implement a local cache to store the fetched data and avoid unnecessary API calls.
Add UI animations and transitions to enhance the user experience.
