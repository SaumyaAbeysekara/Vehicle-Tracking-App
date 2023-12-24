# 🚗 Vehicle Tracking App

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Screenshots](#screenshots)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
  - [Configuration](#configuration)
- [Usage](#usage)
- [Acknowledgements](#acknowledgements)

## Overview

The Vehicle Tracking App is a comprehensive solution designed for managing and tracking vehicles using Android Studio, Java, Firebase, and ThingSpeak. The app provides a secure login system, allows users to register and manage vehicles, assign vehicles to drivers, and track real-time locations using GPS devices and ThingSpeak integration.

## Features

- **🔐 User Authentication**: Ensure secure access with a login system to manage user accounts.
- **🚗 Vehicle Registration**: Register and manage vehicle details such as make, model, and registration number.
- **👤 Driver Assignment**: Assign registered vehicles to specific drivers, automatically updating available and locked vehicle lists.
- **🌐 Real-time Data**: Utilize ThingSpeak to track real-time location data obtained from GPS devices attached to vehicles.
- **🗺️ Interactive Map**: View vehicle locations on Google Maps directly from the app.
- **🚦 Vehicle Status**: Easily distinguish between available and locked vehicles based on driver assignments.
- **🔓 Unlock Vehicles**: Allow users to unlock vehicles once assigned tasks are completed.

## Screenshots

<details>
  <summary>Sign In Screen</summary>
  <img src="screenshots/sign_in.jpeg" alt="Sign In Screen" width="300"/>
</details>

<details>
  <summary>Home Page</summary>
  <img src="screenshots/home_page.jpeg" alt="Home Page" width="300"/>
</details>

<details>
  <summary>Vehicle Managing</summary>
  <img src="screenshots/manage_vehicles.jpeg" alt="Vehicle Managing" width="300"/>
</details>

<details>
  <summary>Register Page</summary>
  <img src="screenshots/register.jpeg" alt="Register Page" width="300"/>
</details>

<details>
  <summary>Locked Vehicles</summary>
  <img src="screenshots/locked_vehicles.jpeg" alt="Locked Vehicles" width="300"/>
</details>

<details>
  <summary>Unlock Vehicles</summary>
  <img src="screenshots/unlock_vehicles.jpeg" alt="Unlock Vehicles" width="300"/>
</details>

<details>
  <summary>Real-time Map</summary>
  <img src="screenshots/map.jpeg" alt="Real-time Map" width="300"/>
</details>

## Getting Started

### Prerequisites

Ensure you have the following installed:

- **Android Studio**
- **Firebase account**
- **ThingSpeak account**

### Installation

1. Clone the repository:

    ```bash
    git clone https://github.com/SaumyaAbeysekara/vehicle-tracking-app.git
    cd vehicle-tracking-app
    ```

2. Open the project in Android Studio.

### Configuration

1. Set up Firebase:
   - Create a new Firebase project.
   - Connect your app to the Firebase project by adding the configuration file (`google-services.json`) to the app module.

2. Configure ThingSpeak:
   - Create a ThingSpeak channel to store location data.
   - Update the app to use the ThingSpeak API to fetch real-time location data.

## Usage

1. Launch the app and log in with your credentials.

2. Navigate to the home page to register vehicles.

3. Assign vehicles to drivers, and observe the dynamic update of available and locked vehicle lists.

4. Use the interactive map to view real-time vehicle locations.

5. Unlock vehicles once assigned tasks are completed.

## Acknowledgements

- [Firebase](https://firebase.google.com/)
- [ThingSpeak](https://thingspeak.com/)
