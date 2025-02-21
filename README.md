# DreamHotel
[![MIT License](https://img.shields.io/badge/License-MIT-green.svg)](https://choosealicense.com/licenses/mit/)

> [!IMPORTANT]  
> This is project for studies 

<p align="center">

 <img src="https://github.com/user-attachments/assets/c3ad90ef-2a5d-4710-a19c-2eb3abee92f8" alt="Sublime's custom image"/>
</p>




#  DreamHotel

DreamHotel is a hotel reservation system built with Spring Boot and PostgreSQL. It allows users to browse and book rooms, select meal options, and manage reservations.
The platform supports role-based access, with users able to book rooms and view their reservations also edit them by changing the date, but no later than 1 month from the original date and no earlier than the original date , 
admins able to manage room availability, reservations, and rooms.
The system includes meal selections (non or breakfast or breakfast and dinner), and administrators can upload images for room categories and create new one if needed. It ensures secure login through Spring Security and manages data using Hibernate.
![Zrzut ekranu 2025-02-20 220510](https://github.com/user-attachments/assets/6c23e429-62d4-41fd-9f7d-f37bd256be65)

![Zrzut ekranu 2025-02-21 212812](https://github.com/user-attachments/assets/30c56694-d17b-4512-a9af-284256e30a39)

Below are the key features and functionalities included in DreamHotel:



## Technology Stack 🛠
**Backend:** Java Spring Boot for business logic and database management.

**Frontend:** Thymeleaf templates integrated with Spring MVC for rendering dynamic content.

**Database:** PostgreSQL

**Security:** Spring Security for handling authentication and role-based access control.




## FAQ 💬


#### How to log in as Admin?
```bash
 login: admin
```
```bash
password: admin
```


#### How to log in as Guest?

```bash
 login: user
```
```bash
password: user
```

> [!CAUTION]
> The upload limit is 5 MB for files such as trip images.





 ## Roadmap 🧠

DreamHotel is a constantly evolving platform designed to meet user needs for flexible trip booking, while providing a streamlined experience for both travelers and administrators. With upcoming features like 

 - ~~About us section,~~ ✅
 - ~~Contact section~~ ✅
 - Cancel reservation ,
 - User Managment,
 - Payment status and payment system
 - and improved UI/UX,


DreamHotel aims to be the go-to open srouce platform for all Hotels.

## Updates

> [!NOTE]
> UPDATE 21.02.2025 : fixed auth added sections about us and contact  with option to  customize them. deleted some old code or not used class but still repo is full of not the clenest code at least in my opinion. 

## Screenshots

Check dates for avalible rooms 
![Zrzut ekranu 2025-02-20 220839](https://github.com/user-attachments/assets/999a2adf-7719-4535-8571-00db98244434)
Room detail and reservation 
![Zrzut ekranu 2025-02-20 220929](https://github.com/user-attachments/assets/d45dcabe-f614-427a-9177-12abb8de8ad3)
Editing room category list 
![Zrzut ekranu 2025-02-20 221513](https://github.com/user-attachments/assets/21138f54-47f2-4531-be0b-46d5efb5d155)
Edit specific room 
![Zrzut ekranu 2025-02-20 22152131252](https://github.com/user-attachments/assets/f4fd1f49-3cf7-4e3b-8fe0-96854b03622f)

See all reservation 
![Zrzut ekranu 2025-02-20 221640](https://github.com/user-attachments/assets/85206519-f043-493e-bfdc-b7e18a8fa865)

Change dates 
![Zrzut ekranu 2025-02-20 221704](https://github.com/user-attachments/assets/89434bbd-a51a-46cf-8593-d763e3b6b7a5)

See your reservation
![Zrzut ekranu 2025-02-20 221739](https://github.com/user-attachments/assets/f1c8e2bd-5b4d-49e8-9159-9e254788378d)

Admin panel ( 21.02.2025 ) 
![Zrzut ekranu 2025-02-21 212516](https://github.com/user-attachments/assets/8b29b227-2f4c-49b2-aa9a-6af540cecd74)

Edit contact section ( forgot to put edit for email 🤷‍♂️)
![image](https://github.com/user-attachments/assets/df10c947-6e3b-43ab-b86e-bb967aa5d877)

Edit about us 
![Zrzut ekranu 2025-02-21 212732](https://github.com/user-attachments/assets/ac46eef0-9eb6-4d9f-ab45-928be807e024)

