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
# Следующая версия ресторанного приложения

Привет! В этом репозитории вы найдете мое приложение для эмуляции ресторанного бизнеса. Как один из моих первых проектов, я выделил несколько областей для улучшения в следующей версии этого приложения:

## Внесенные изменения:

### 1. Архитектура
   - Переорганизовать всё приложение, используя архитектуру MVVM или MVM. В настоящее время в приложении отсутствует четкий архитектурный паттерн.
   - Рассмотреть возможность объединения функционала базы данных в один класс для улучшенной организации и эффективности.

### 2. Дизайн
   - Переработать некоторые аспекты пользовательского интерфейса для более современного и единообразного вида.
   - Пересмотреть и, возможно, изменить общую архитектуру приложения для улучшения удобства использования.

### 3. Базы данных
   - Перераспределить базы данных в единую структуру. Текущее разделение DAO может быть необязательным.
   - Рассмотреть возможность создания единого, всестороннего класса базы данных для улучшенного управления.

### 4. Паттерны
   - Реализовать как MVVM, так и еще один паттерн (MVE или MVI). MVVM необходим для представления базы данных, в то время как дополнительный паттерн поможет правильно предоставлять намерения пользователя.

### 5. Редизайн
   - Провести всесторонний редизайн с особым вниманием к разделу новостей на экране продаж.

### 6. Интеграция API
   - Добавить два крупных API в проект:
      - Платежное API (рассмотрите Yookassa или Stripe).
      - Картографическое API (рассмотрите Google Maps или Mapbox).

### 7. Технологическая интеграция
   - Понять и реализовать Dagger Hilt для внедрения зависимостей. Овладеть его возможностями для более эффективной организации кода.

### Экраны:

#### 1. Экран Меню (MenuScreen)
   - Экран для заказа блюд. Нажатие на кнопку "Добавить" добавит выбранное блюдо в заказ в базе данных.

   ![Скриншот Экрана Меню](/images/MenuScreen.jpg)

#### 2. Экран Профиля (ProfileScreen)
   - Экран для персональных предпочтений, в настоящее время показывает рекламу и новые функции из базы данных. Эти функции в настоящее время открывают Экран Новостей.

   ![Скриншот Экрана Профиля](/images/ProfileScreen.jpg)

#### 3. Экран Новостей (NewsScreen)
   - Представление для новостей, рекламы или функций.

   ![Скриншот Экрана Новостей](/images/NewsScreen.jpg)

#### 4. Экран Корзины (BinScreen)
   - Экран для управления заказами. Здесь вы можете удалить товары из заказа, добавить больше товаров, добавить десерты или купить весь заказ.

   ![Скриншот Экрана Корзины](/images/BinScreen.jpg)

Не стесняйтесь вносить свой вклад, предлагать улучшения или изучать эволюцию этого ресторанного приложения в следующей версии!


Акселератор:
Команда Restaurant assistant
Борисенко
Ux дизайнер
Сделал экран меню и помогал в реализации к нему блока даты и был спикером
Акимов
Backend
Реализовал бд, её заполение, взаимодействие с блоком дизайна
Жохов
Backend
Помогал в реализации экрана с акциями
Реализовал блок view model (блок изменения данных и их передачу через экраны)
Карпенко анастасия
Ux designer
Сделала экраны Onsale, trashbin

---

