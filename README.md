# Gruppuppgift: Hotel Booking System
### Mål
Ni ska bygga ett **objektorienterat bokningssystem** för ett hotell som använder en **MySQL-databas (`hotel_db`)**.

Systemet ska låta användaren:
* registrera kunder
* visa och hantera rum
* skapa, visa och avboka bokningar
* använda **PreparedStatements** för all databasåtkomst
* strukturera koden med **DAO-mönster och modellklasser**

Ni ska jobba i grupper om 3–5 personer och bygga systemet.

### Del 1 – Grundläggande krav
#### Kunder
1. ~~Lägg till ny kund (`addCustomer`)~~
2. ~~Visa alla kunder (`getAllCustomers`)~~
3. ~~Sök kund via e-post (`findCustomerByEmail`)~~
4. ~~Uppdatera kundens stad (`updateCustomerCity`)~~
5. Ta bort kund (`deleteCustomer`)

#### Rum
6. Lägg till nya rum (`addRoom`)
7. Visa alla rum (`getAllRooms`)
8. Visa endast lediga rum (`getAvailableRooms`)
9. Uppdatera priset för ett rum (`updateRoomPrice`)
10. Ändra rumstyp (`updateRoomType`)

#### Bokningar
11. Skapa en ny bokning (`bookRoom`)
  * Kontrollera att rummet är ledigt (`available = true`)
  * När bokningen sparas → sätt `available = false`
12. Visa alla bokningar (`getAllBookings`)
13. Visa bokningar för en viss kund (`getBookingsByCustomerEmail`)
* 14. Avboka rum (`cancelBooking`)
  * När bokningen tas bort → sätt `available = true`

### Del 2 – Utökade krav
När grunden fungerar ska gruppen lägga till mer avancerade funktioner:
#### Rapporter
15. Visa antal bokningar per kund
16. Visa genomsnittspris på bokade rum
17. Visa vilka rum som är lediga en viss period
18. Visa alla kunder som aldrig bokat

#### Transaktioner
När man bokar ett rum:
* Lägg till en rad i `bookings`
* Uppdatera `rooms.available = false`
* Gör båda i **samma transaktion**
