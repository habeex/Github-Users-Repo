
---

# GitHub Repository and User Info Search App

This project is an Android application built using Jetpack Compose, designed to search for GitHub repositories and retrieve user information. The app leverages modern Android development practices, including Clean Architecture, Paging 3, Dagger Hilt, and Retrofit, to ensure a robust, maintainable, and performant codebase.

## Project Goal

The primary goal of this project is to build an Android application for searching GitHub repositories and retrieving user information using Jetpack Compose.

### Screenshots
<img src="https://github.com/habeex/Github-Users-Repo/assets/22020160/14741ed7-5373-429c-aa27-e0b5bc005fa9" width="150"/> <img src="https://github.com/habeex/Github-Users-Repo/assets/22020160/27035e79-2e44-468b-9370-01f0c8a42b65" width="150"/> <img src="https://github.com/habeex/Github-Users-Repo/assets/22020160/b85c5bcc-d4e1-4a24-9565-a0582a8a9f05" width="150"/> <img src="https://github.com/habeex/Github-Users-Repo/assets/22020160/90f3f4c6-f738-4883-befb-6b5ea98ae4ec" width="150"/> <img src="https://github.com/habeex/Github-Users-Repo/assets/22020160/1edde5e4-7963-4578-95f5-83687f882ace" width="150"/> 

<img src="https://github.com/habeex/Github-Users-Repo/assets/22020160/b15f0f23-b791-4055-b09b-81da188a58ab" width="150"/> <img src="https://github.com/habeex/Github-Users-Repo/assets/22020160/124dce55-475d-4a9f-909e-8e9b0c772a0f" width="150"/> <img src="https://github.com/habeex/Github-Users-Repo/assets/22020160/b5fb9803-fe27-4301-a5c8-69c3726889b0" width="150"/> <img src="https://github.com/habeex/Github-Users-Repo/assets/22020160/b9588e8a-a2dd-4f64-aa8b-6d1f25e88996" width="150"/> 


## Features

- Search for GitHub repositories.
- Retrieve and display GitHub user information.
- Handle network errors gracefully, including 404 and 500 response codes, as well as IOExceptions using custom Error handler.
- Implement paging to efficiently handle large sets of data.
- Ensure high code quality and adherence to coding conventions.
- Focus on app performance and responsiveness.
- Unit test implementation to ensure code reliability and correctness.

## Architecture

This project follows the Clean Architecture - MVVM principle, which ensures a clear separation of concerns and makes the codebase more modular, testable, and maintainable.

- **Presentation Layer**: Implemented using Jetpack Compose for building the UI and ViewModel for managing the states.
- **Domain Layer**: Contains business logic and use cases.
- **Data Layer**: Manages data sources, including remote APIs and connectivity manager.

## Tech Stack

- **Jetpack Compose**: Modern toolkit for building native Android UI.
- **Clean Architecture**: A layered architecture approach for a clean and maintainable codebase.
- **Paging 3**: Efficiently loads large sets of data.
- **Dagger Hilt**: Dependency injection library to manage dependencies.
- **Retrofit**: Type-safe HTTP client for Android to handle network requests.
- **MockK**: For unit testing

## Code Quality and Conventions

- Follow Kotlin coding conventions and best practices.
- Use meaningful and descriptive names for variables, functions, and classes.
- Write clear and concise comments and documentation.
- Maintain a consistent code style throughout the project.

## App Performance

- Optimize UI rendering with Jetpack Compose.
- Use Paging 3 to efficiently load large data sets and reduce memory usage.
- Handle network errors properly and provide meaningful feedback to users.

## Unit Tests

Unit tests are implemented to ensure the reliability and correctness of the codebase. The tests cover various components and use cases within the application.

## Note

The GitHub API does not return users' full information on the search endpoint (`https://api.github.com/search/users`). User details such as name, email, location, and bio were simulated/mocked for the purpose of this app.

### GitHub API Search Users Response Sample
```json
{
  "total_count": 1639,
  "incomplete_results": false,
  "items": [
    {
       "login": "habeex",
       "id": 22020160,
       "node_id": "MDQ6VXNlcjIyMDIwMTYw",
       "avatar_url": "https://avatars.githubusercontent.com/u/22020160?v=4",
       "gravatar_id": "",
       "url": "https://api.github.com/users/habeex",
       "html_url": "https://github.com/habeex",
       "followers_url": "https://api.github.com/users/habeex/followers",
       "following_url": "https://api.github.com/users/habeex/following{/other_user}",
       "gists_url": "https://api.github.com/users/habeex/gists{/gist_id}",
       "starred_url": "https://api.github.com/users/habeex/starred{/owner}{/repo}",
       "subscriptions_url": "https://api.github.com/users/habeex/subscriptions",
       "organizations_url": "https://api.github.com/users/habeex/orgs",
       "repos_url": "https://api.github.com/users/habeex/repos",
       "events_url": "https://api.github.com/users/habeex/events{/privacy}",
       "received_events_url": "https://api.github.com/users/habeex/received_events",
       "type": "User",
       "site_admin": false,
       "score": 1.0
    }
  ]
}
```

---
