# Hearthstone Card Info App

## Mobile Challenge for a company

Study application made to take advantage of the best programming practices using hearthstone public api.
Shows a list of Hearthstone Game's cards that contains all their stats. (name, flavor, cardSet, class, faction, rarity, attack, cost, health, etc.)

[APK](https://github.com/rafaelmfer/Hearthstone-Card-Info-App/blob/main/apk/app-debug.apk?raw=true)
|| [VIDEO](https://github.com/rafaelmfer/Hearthstone-Card-Info-App/blob/main/github_assets/videos/Screen_Recording_Hearthstone%20Card%20Info.mp4?raw=true)

<table>
    <thead>
        <tr>
            <th>BASE</th>
            <th>Architecture</th>
            <th>IU</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>AppCompat</td>
            <td>ViewBinding</td>
            <td>Material Components</td>
        </tr>
        <tr>
            <td>Android KTX</td>
            <td>Lifecycles</td>
        </tr>
        <tr>
            <td>Android Arch</td>
            <td>LiveData</td>
        </tr>
        <tr>
            <td></td>
            <td>ViewModel</td>
        </tr>
    </tbody>
</table>


**Screens**
<table>  
    <th>Main Light</th>
    <th>Card Details Light</th>
    <tr>
        <td>
            <img src="https://github.com/rafaelmfer/Hearthstone-Card-Info-App/blob/main/github_assets/images/Home%20Light.png"/>
        </td>
        <td>
            <img src="https://github.com/rafaelmfer/Hearthstone-Card-Info-App/blob/main/github_assets/images/Card%20Details%20Light.png"/>
        </td>
    </tr>
</table>

<table>  
    <th>Main Dark</th>
    <th>Card Details Dark</th>
    <tr>
        <td>
            <img src="https://github.com/rafaelmfer/Hearthstone-Card-Info-App/blob/main/github_assets/images/Home%20Dark.png"/>
        </td>
        <td>
            <img src="https://github.com/rafaelmfer/Hearthstone-Card-Info-App/blob/main/github_assets/images/Card%20Details%20Dark.png"/>
        </td>
    </tr>
</table>

## Base project

- **Dependency injection:**
  With Koin, a practical dependency injection library, the code will not be coupled and it'll still
  be easy to resolve automatically the dependencies on the runtime and mock them during the tests.

- **Coroutines:**
  With coroutines it is possible to perform asynchronous tasks without changing the code flow of the
  application. Simplifies code by abstracting all the complexity of using threads.

## Tests

- **Unit Tests**:

<table>
    <th>MainViewModelTest</th>
    <th>CardDetailsViewModelTest</th>
    <tr>
        <td>
            <img src="https://github.com/rafaelmfer/Hearthstone-Card-Info-App/blob/main/github_assets/images/MainViewModelTest.png"/>
        </td>
        <td>
            <img src="https://github.com/rafaelmfer/Hearthstone-Card-Info-App/blob/main/github_assets/images/CardDetailsViewModelTest.png"/>
        </td>
    </tr>
</table>

- **Instrumented Tests**:

<table>
    <th>MainActivityTest</th>
    <tr>
        <td>
            <img src="https://github.com/rafaelmfer/Hearthstone-Card-Info-App/blob/main/github_assets/images/MainActivityTest.png"/>
        </td>
    </tr>
</table>

## Quick start

1. Clone the repository with `git clone https://github.com/rafaelmfer/Hearthstone-Card-Info-App.git`
2. Create a free account on the [Hearthstone API](https://rapidapi.com/omgvamp/api/hearthstone) to get your API KEY
3. Go to the app's `build.gradle` file and put your API KEY in the variable `def API_KEY = "INPUT_YOUR_API_KEY_HERE"`
4. Run the application and be happy

## CODE

- **IDE - Android Studio Electric Eel | 2022.1.1 Patch 1**

- **Gradle 7.4.1**

- **Kotlin 1.8.0**

- **AAC Android Architecture Components** *using guide Google JetPack*

- **MVVM Architecture** *for apply SOLID*

- **ViewBinding** *bind view*

- **Retrofit** *for make the communication to API*

- **Coroutines** *for asynchronous calls and operations*

- **ViewModel** *for interact view with business rules*

- **JUnit / Espresso** *for unit and instrumented tests*

## API

Hearthstone's API Documentation: https://rapidapi.com/omgvamp/api/hearthstone

## DESIGN

**Material Components**

https://github.com/material-components

- RecyclerView
- MaterialButton