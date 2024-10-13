# MOROCCO Players Gallery

## Description
This Android application showcases a gallery of MOROCCO players. It features an animated splash screen, a list of players with filtering capabilities. The app demonstrates various Android development concepts including animations, RecyclerView usage, menu creation, data filtering, and image handling.

## Features
- Animated splash screen with logo animations
- List of Real Madrid players using RecyclerView
- Circular player images using CircleImageView
- Image loading with Glide library
- Search functionality to filter players
- Sharing option for the application

## Prerequisites
- Android Studio Arctic Fox (2020.3.1) or newer
- Android SDK (API 21+)
- JDK 8+

## Dependencies
This project uses several key dependencies:

1. RecyclerView:
   - Implementation: androidx.recyclerview:recyclerview:1.3.2
   - Purpose: Efficient display of scrollable lists
   - Advantages: 
     - Better performance for long lists
     - Easy implementation of various list layouts
     - Built-in view holder pattern

2. Glide:
   - Implementation: com.github.bumptech.glide:glide:4.16.0
   - Purpose: Efficient image loading and caching
   - Advantages:
     - Smooth scrolling performance
     - Automatic memory and disk caching
     - Easy image transformations

3. CircleImageView:
   - Implementation: de.hdodenhof:circleimageview:3.1.0
   - Purpose: Display circular images
   - Advantages:
     - Simple way to create circular image views
     - Customizable border width and color

## Project Structure
- adapter: Contains custom adapters for RecyclerView
- beans: Data model classes
- dao: Data Access Object interfaces
- service: Business logic and data handling services

## Setup and Installation
1. Clone the repository:
https://github.com/AdamBessam/TP-RECYCLEVIEW-MOBILE
2. Open the project in Android Studio.
3. Sync the project with Gradle files.
4. Run the application on an emulator or physical device.

## Implementation Details

### Splash Screen (SplashActivity)
The splash screen features multiple animations on the logo:

java
logo = findViewById(R.id.logo);
logo.animate().rotation(360f).setDuration(2000);
logo.animate().scaleX(0.5f).scaleY(0.5f).setDuration(3000);
logo.animate().translationYBy(1000f).setDuration(2000);
logo.animate().alpha(0f).setDuration(6000);

rotation(360f): Rotates the logo 360 degrees
scaleX(0.5f).scaleY(0.5f): Scales the logo to half its size
translationYBy(1000f): Moves the logo 1000 pixels down
alpha(0f): Fades out the logo
### Adapter Implementation
The custom adapter extends RecyclerView.Adapter and implements filtering:
java
public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.ViewHolder> implements Filterable {
    // Implementation details
}

# Video  Demonstration

https://github.com/user-attachments/assets/23077ee3-6972-453c-bc9d-67dd37f447df












## Contributors
- BESSAM Adam ([GitHub](https://github.com/AdamBessam))



