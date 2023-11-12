# RestaurantApp Next Version

Hello! In this repository, you can find my restaurant business emulation app. As one of my first projects, I've identified several areas for improvement in the next version of this app:

## Changes to Implement:

### 1. Architecture
   - Reorganize the entire app using MVVM or MVM architecture. Currently, the app lacks a clear architectural pattern.
   - Consider consolidating the database functionality into a single database class for improved organization and efficiency.

### 2. Design
   - Redesign certain aspects of the user interface for a more modern and cohesive look.
   - Review and potentially modify the overall app architecture to enhance usability.

### 3. Databases
   - Rearrange databases into a unified structure. The current separation of DAOs may not be necessary.
   - Consider creating a single, comprehensive database class for improved management.

### 4. Patterns
   - Implement both MVVM and another pattern (MVE or MVI). MVVM is essential for representing the database, while the additional pattern will help in correctly providing user intents.

### 5. Redesign
   - Perform a comprehensive redesign, with specific attention to areas such as the news section on the sales screen.

### 6. API Integration
   - Add two major APIs to the project:
      - Payment API (consider Yookassa or Stripe).
      - Map API (consider Google Maps or Mapbox).

### 7. Technology Integration
   - Understand and implement Dagger Hilt for dependency injection. Gain proficiency in leveraging its capabilities for better code organization.

### Screens:

#### 1. MenuScreen
   - A screen for ordering items. Pressing the add button will add the selected meal to the order in the database.

   ![MenuScreen Screenshot](/images/MenuScreen.jpg)

#### 2. ProfileScreen
   - A screen for personal preferences, currently showing ads and new features from the database. These features currently open the NewsScreen.

   ![ProfileScreen Screenshot](/images/ProfileScreen.jpg)

#### 3. NewsScreen
   - A view for news, ads, or features.

   ![NewsScreen Screenshot](/images/NewsScreen.jpg)

#### 4. BinScreen
   - A screen for managing orders. Here, you can delete items from your order, add more items, add desserts, or buy the entire order.

   ![BinScreen Screenshot](/images/BinScreen.jpg)

Feel free to contribute, suggest improvements, or explore the evolution of this restaurant app in its next version!

---
