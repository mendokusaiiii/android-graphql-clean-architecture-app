# Graphql AnimeApp Documentation

## Introduction
AnimeApp is a sample Android application built to demonstrate the use of modern Android development tools and practices. It allows users to browse popular animes from the AniList API, view detailed information, and manage a list of favorite animes, which are stored locally for offline access.

---

## Architecture
The app follows the **MVVM (Model-View-ViewModel)** architecture pattern, combined with **Clean Architecture** principles to ensure a clear separation of concerns. The architecture is divided into three main layers:

- **Data Layer**: 
  - Handles API calls using **Apollo GraphQL** to fetch data from the AniList API.
  - Manages local storage with **Room** for caching animes and favorites.
- **Domain Layer**: 
  - Contains use cases that encapsulate the app's business logic, such as fetching popular animes or managing the favorites list.
- **Presentation Layer**: 
  - Manages the UI using **Jetpack Compose**.
  - Uses **ViewModels** to handle state and interact with the domain layer.

Dependency injection is provided by **Hilt**, ensuring modular and testable code.

---

## Features
- **Browse Popular Animes**: View a list of currently popular animes fetched from the AniList API.
- **Anime Details**: See detailed information about a selected anime, including synopsis, cover image, and more.
- **Favorite Animes**: Add or remove animes from a favorites list, persisted locally.
- **Offline Support**: Access cached popular animes and favorites without an internet connection.

---

## Setup and Installation

### Requirements
- **Android Studio**: Arctic Fox or later
- **Kotlin**: Version 1.5 or later
- **Android SDK**: API level 24 or higher

### Steps
1. **Clone the repository**
2. **Open the project** in Android Studio.
3. **Ensure dependencies are installed**: Verify that the latest Android Studio and required SDKs are set up.
4. **Run the app**: Build and launch the app on an emulator or physical device.

**Note**: The GraphQL schema is included in the repository. If you need to update it, run the following command:
```bash
./gradlew downloadApolloSchema
```

---

## Usage
The app is designed to be intuitive and straightforward. Below are the main screens and their functionalities:

- **Home Screen**: 
  - Displays a list of popular animes.
  - Tap an anime to view its details.
- **Details Screen**: 
  - Shows detailed information about the selected anime.
  - Includes options to add or remove the anime from favorites.
- **Favorites Screen**: 
  - Lists all favorited animes.
  - Accessible even when offline due to local caching.

---

## Contributing
Contributions are welcome! To contribute:
- Submit **issues** or **pull requests** on the GitHub repository.
- Follow the project's coding standards.
- Add tests for any new features or changes.

---

## License
This project is licensed under the **MIT License**. See the `LICENSE` file in the repository for details. If no license file exists, consider adding one to clarify usage and distribution terms.

---

## Technologies Used
The following tools and libraries power AnimeApp:
- **Kotlin**: Programming language
- **Jetpack Compose**: Modern UI toolkit
- **Apollo GraphQL**: For API communication with AniList
- **Hilt**: Dependency injection framework
- **Room**: Local database for persistence
- **MVVM & Clean Architecture**: Architectural patterns
- **Coil**: Image loading library

---

## Additional Notes
AnimeApp is designed as a learning tool. Feel free to explore the code to understand how modern Android components—such as Jetpack Compose, Hilt, and Room—work together. Each module is structured to be modular and easy to follow.

For further assistance or questions, refer to the GitHub repository or reach out through the issue tracker.
