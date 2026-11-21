# Local Eats Discovery

> **Good food. Great prices. Right near you.**

Local Eats Discovery is an Android application designed to help users discover affordable meals and local restaurants around Southport, Gold Coast, Queensland.

The application provides a simple and user-friendly way to search for meals, explore restaurants, apply filters, save meals, find deals, and view restaurant promotional options.

This project was developed as an academic Android application using **Kotlin**, **Jetpack Compose**, and **Material 3** in **Android Studio**.

---

## 1. Project Overview

Local Eats Discovery focuses on connecting users with affordable local food options in one easy-to-use mobile application.

The application provides two main areas of value:

### For customers

Users can:

- Search for meals and restaurants
- Browse local food options
- Select a location
- Filter meals by cuisine
- Set a maximum price
- Sort search results
- View meal details
- Save meals for later
- Use Meal Match
- Explore local deals
- Explore restaurants

### For restaurants

Restaurants can:

- Explore partnership opportunities
- Submit a partnership enquiry
- View Local Spotlight promotional options
- Review the promotional price and duration
- Complete a demo payment process
- Receive a demo invoice

---

## 2. Project Purpose

The main purpose of Local Eats Discovery is to make finding affordable local food easier.

Instead of searching through multiple platforms, users can use one application to discover meals based on their location, preferred cuisine, and budget.

The project also demonstrates how a mobile application can provide useful promotional features for local restaurants.

---

## 3. Key Features

The application includes the following main features:

- User login
- Create Account interface
- Forgot Password interface
- Session management
- Logout
- Food and restaurant search
- Location selection
- Cuisine filtering
- Maximum price filtering
- Sorting
- Meal Match
- Deals
- Restaurant discovery
- Saved Meals
- Meal details
- Restaurant partnership enquiry
- Local Spotlight promotion
- Demo payment
- Demo invoice
- About section
- Contact section
- Settings
- Navigation drawer

---

## 4. Technology Used

| Technology | Purpose |
|---|---|
| Kotlin | Main programming language |
| Jetpack Compose | User interface development |
| Material 3 | UI components and design |
| Android Studio | Application development |
| Gradle | Project build system |
| SharedPreferences | Login session storage |
| Git | Version control |
| GitHub | Source code repository |

---

## 5. Development Environment

The project was developed and tested using:

- Android Studio
- Kotlin
- Android SDK
- Gradle
- Android Emulator / Android physical device

The application can be launched directly from Android Studio on a connected Android device or emulator.

---

# 6. User Authentication and Session Management

The application contains a login system to control access to the main application.

The authentication flow starts from `LoginActivity`.

After a successful login, the application opens `MainActivity`.

The application uses `SessionManager` to store the login state using Android `SharedPreferences`.

### SessionManager

The main session management file is:

```text
app
└── src
    └── main
        └── java
            └── com.example.localeatsdiscovery
                └── auth
                    └── SessionManager.kt
```

The `SessionManager` contains three main functions:

### `setLoggedIn()`

Stores the current login status.

When the user signs in successfully:

```text
logged_in = true
```

When the user logs out:

```text
logged_in = false
```

### `isLoggedIn()`

Checks whether the user currently has an active login session.

If the stored value is `true`, the application allows the user to continue to the main screen.

If the stored value is `false`, the Login screen is shown.

### `logout()`

Changes the login status to:

```text
logged_in = false
```

The user is then returned to the Login screen.

### Login Flow

```text
                 Start Application
                        |
                        v
                 LoginActivity
                        |
                        v
                Check Login Session
                   /           \
                  /             \
             Logged In       Not Logged In
                 |                 |
                 v                 v
           MainActivity        Login Screen
```

### Logout Flow

```text
             MainActivity
                  |
                  v
                Logout
                  |
                  v
        SessionManager.logout()
                  |
                  v
          logged_in = false
                  |
                  v
           LoginActivity
```

The logout process also clears the previous activity stack so that the user cannot return to the Home screen using the Android Back button.

---

# 7. Project Structure

The project is organised into separate areas for application logic, authentication, UI theme files, and resources.

The main project structure is:

```text
LocalEatsDiscovery
│
├── app
│   └── src
│       └── main
│           ├── java
│           │   └── com.example.localeatsdiscovery
│           │       │
│           │       ├── auth
│           │       │   ├── SessionManager.kt
│           │       │   └── LogoutManager.kt
│           │       │
│           │       ├── login
│           │       │   └── LoginActivity.kt
│           │       │
│           │       ├── ui
│           │       │   └── theme
│           │       │       ├── Color.kt
│           │       │       ├── Theme.kt
│           │       │       └── Type.kt
│           │       │
│           │       └── MainActivity.kt
│           │
│           └── res
│               ├── drawable
│               ├── mipmap
│               └── values
│
├── build.gradle.kts
├── settings.gradle.kts
├── gradle.properties
├── gradlew
├── gradlew.bat
├── .gitignore
└── README.md
```

---

# 8. Login Screen

The Login screen is the first screen of the application when there is no active session.

The screen provides:

- Username field
- Password field
- Password visibility control
- Sign In button
- Forgot Password option
- Sign in with Google interface
- Create Account option

The Login screen is implemented using:

```text
LoginActivity.kt
```

After a successful sign-in, the application navigates to:

```text
MainActivity
```

---

# 9. Main Home Screen

The Home screen is the main discovery area of Local Eats Discovery.

It provides quick access to the main food discovery functions.

The Home screen includes:

- Local Eats Discovery branding
- Navigation menu
- Search field
- Location field
- Cuisine filter
- Sorting option
- Maximum price filter
- Meal Match
- Deals
- Restaurant discovery
- Saved Meals access

The main application screen is implemented in:

```text
MainActivity.kt
```

The user interface is built using Jetpack Compose.

---

# 10. Search and Food Filtering

The application allows users to search for meals and restaurants.

The search feature is designed to help users quickly find relevant food options.

Users can also apply filters to narrow down their results.

### Cuisine Filter

Users can select a cuisine category to find meals that match their food preferences.

### Maximum Price

Users can set a maximum price so that the application can focus on meals within their budget.

### Sorting

Users can change the sorting option to organise the available results.

### Clear Filters

Users can also clear the selected filters and return to the default discovery results.

---

# 11. Meal Details

Users can select a meal to view more information.

The Meal Details screen provides information related to the selected meal, such as:

- Meal name
- Meal description
- Price
- Restaurant information
- Available actions

This allows users to understand the meal before deciding what to do next.

---

# 12. Saved Meals

Local Eats Discovery includes a **Saved Meals** feature.

Users can save meals that they are interested in.

Saved meals can then be accessed later without searching for the meal again.

This feature is useful for users who want to keep a list of meals they may want to try later.

---

# 13. Meal Match

The application includes a **Meal Match** feature.

Meal Match is designed to help users find meals that suit their preferences and budget.

The feature supports the main purpose of Local Eats Discovery by reducing the time required to search through different food options.

---

# 14. Deals

The **Deals** section helps users discover affordable food offers and local deals.

This feature supports the application's focus on finding good food at reasonable prices.

Users can access Deals directly from the Home screen.

---

# 15. Restaurant Discovery

The application includes a dedicated restaurant discovery section.

Users can explore local restaurants and access restaurant-related information.

The restaurant section provides access to:

- Restaurant discovery
- Restaurant information
- Partnership enquiry
- Promotional options

This feature also supports the business side of the Local Eats Discovery concept.

---

# 16. Restaurant Partnership Enquiry

Local Eats Discovery provides a **Restaurant Partnership Enquiry** option.

This feature allows restaurants to express interest in working with Local Eats Discovery.

The enquiry interface is part of the academic prototype and demonstrates how restaurants could communicate their interest in partnership.

---

# 17. Local Spotlight Promotion

The application includes a restaurant promotional feature called:

**Local Spotlight**

The Local Spotlight option provides a simple promotional package for restaurants.

### Promotion Details

| Item | Details |
|---|---|
| Promotion | Local Spotlight |
| Duration | 30 days |
| Price | A$39 |
| Payment | Demo payment |
| Purpose | Academic prototype |

The promotion flow is:

```text
Restaurants
    |
    v
View Promotional Options
    |
    v
Local Spotlight
    |
    v
Review Promotion
    |
    v
Demo Payment
    |
    v
Invoice
```

---

# 18. Demo Payment and Invoice

The Local Spotlight promotion includes a demonstration payment process.

The payment screen provides fields for example payment information, including:

- Cardholder name
- Card number
- Expiry date
- CVV

The payment process is **not connected to a real payment gateway**.

It is included only to demonstrate how a future restaurant promotion payment flow could work.

After completing the demo payment, the application generates a demo invoice.

The invoice includes information such as:

- Invoice number
- Payment date
- Promotion name
- Promotion duration
- Amount
- Payment status

The demo invoice displays the payment status as:

```text
PAID
```

### Important

This is an academic prototype.

No real money is charged and no real payment is processed through the application.

---

# 19. Navigation

The application uses navigation within the main application to move between different features.

Important areas include:

```text
Discover
Restaurants
Saved Meals
Settings
About
Contact
Promotion
Payment
Invoice
```

The application also uses a navigation drawer to provide quick access to important sections.

The navigation design keeps the application simple and easy to use.

---

# 20. Settings, About and Contact

## Settings

The Settings section provides application-related options and information.

It gives users a dedicated area for application settings and preferences.

## About

The About section provides information about the Local Eats Discovery application and its purpose.

## Contact

The Contact section provides contact-related information for the application.

These sections help provide a complete application experience.

---

# 21. Testing and Build

The application was tested during development using Android Studio and an Android device.

Important application areas were tested, including:

- Login
- Logout
- Login session
- Main Home screen
- Search
- Cuisine filtering
- Price filtering
- Sorting
- Meal Match
- Deals
- Saved Meals
- Restaurant section
- Partnership enquiry
- Local Spotlight
- Demo payment
- Invoice
- Navigation
- Settings
- About
- Contact

The project also builds successfully using Gradle.

### Build

The project can be built from Android Studio using:

```text
Build → Build Project
```

A debug APK can also be generated using:

```text
Build → Generate App Bundles or APKs
```

For command-line builds, the following Gradle command can be used on Windows:

```text
gradlew.bat assembleDebug
```

---

# 22. Project Links

### Website

https://localeatdiscovery.onrender.com/discover

### Facebook

https://www.facebook.com/localeatsdiscovery

---

# 23. Application Information

| Item | Details |
|---|---|
| Application Name | Local Eats Discovery |
| Platform | Android |
| Language | Kotlin |
| UI Framework | Jetpack Compose |
| Design System | Material 3 |
| Development Tool | Android Studio |
| Main Activity | MainActivity |
| Login Activity | LoginActivity |
| Session Manager | SessionManager |
| Target Area | Southport, Gold Coast, Queensland |
| Promotion | Local Spotlight |
| Promotion Price | A$39 |
| Promotion Duration | 30 days |

---

# 24. Academic Prototype Notice

Local Eats Discovery was developed as an academic project and prototype.

Some features, including restaurant promotions, demo payments and invoice generation, are designed to demonstrate the possible functionality of a future production application.

The payment feature does not process real financial transactions.

The application focuses on demonstrating the user experience, application structure, Android development skills and functional features of the Local Eats Discovery concept.

---

# Conclusion

Local Eats Discovery brings food discovery and local restaurant features together in one Android application.

The application allows users to search for meals, apply filters, find food within their budget, save meals, explore deals and restaurants, and use the Meal Match feature.

For restaurants, the application provides partnership and promotional features, including the Local Spotlight demonstration flow.

The project demonstrates practical use of Kotlin, Jetpack Compose, Material 3, Android Studio, SharedPreferences, navigation, form handling and application state management.

Overall, the project provides a complete academic prototype focused on making local food discovery simple, affordable and convenient.

> **Local Eats Discovery — Good food. Great prices. Right near you.**