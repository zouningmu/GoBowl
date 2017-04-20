# Use Case Model

**Author**: Team 20

| Version | Description     |
| --------|:---------------:|
| V1      | Initial version |

## 1 Use Case Diagrams

### Diagram 1
![alt tag](https://www.anony.ws/i/2016/07/01/ManagerDiagram1.png)

### Diagram 2
![alt tag](https://www.anony.ws/i/2016/07/01/ManagerDiagram2.png)

### Diagram 3
![alt tag](https://www.anony.ws/i/2016/07/01/ManagerDiagram3.png)

### Diagram 4
![alt tag](https://www.anony.ws/i/2016/07/01/CustomerDiagram1.png)

### Diagram 5
![alt tag](https://www.anony.ws/i/2016/07/01/CustomerDiagram2.png)

## 2 Use Case Descriptions

### Use case 1 - Print Customer Card

- *Requirements*: The manager must be able to select a customer in the GoBowl system and print the corresponding customer card.

- *Pre-conditions*: The customer associated with the card must be in the GoBowl system. 

- *Post-conditions*: The proper customer card must be printed containing a QR code that can be read using a videocam attached to the system and that encodes the customer’s unique ID.

- *Scenario 1*:
	1. The manager accesses the GoBowl system
	2. The manager selects a desired customer
	3. The manager chooses to print the customer's card
	4. The customer card is printed with the proper QR code

- *Scenario 2*:
	1. The manager accesses the GoBowl system
	2. The manager searches for a desired customer
	3. GoBowl displays that the desired customer is not in the system

### Use case 2 - Add Customer

- *Requirements*: The manager must be able to add a new customer to the GoBowl system, fill in all required information, and the customer card must be printed.

- *Pre-conditions*: The customer must not be in the system. 

- *Post-conditions*: The new customer is added to the GoBowl system and the associated customer card is printed.

- *Scenario 1*:
	1. The manager accesses the GoBowl system
	2. The manager chooses to add a new customer
	3. The manager supplies the customer's name and email
	4. GoBowl generates a new ID
	5. GoBowl prints the new customer's card

- *Scenario 2*:
	1. The manager accesses the GoBowl system
	2. The manager chooses to add a new customer
	3. The manager supplies the customer's name and email
	4. An error is displayed stating the customer is already in the system

### Use case 3 - Edit Customer Info

- *Requirements*: The manager must be able to edit customer information in the GoBowl system.

- *Pre-conditions*: The customer must be in the GoBowl system. 

- *Post-conditions*: The customer information is permanently changed to the newly supplied information.

- *Scenario 1*:
	1. The manager accesses the GoBowl system
	2. The manager selects a desired customer
	3. The manager chooses to edit customer information
	4. The manager alters the desired information (name, email, scores, VIP status, etc)
	5. The manager saves the customer information

- *Scenario 2*:
	1. The manager accesses GoBowl system
	2. The manager searches for a desired customer
	3. GoBowl displays that the customer is not in the system

### Use case 4 - Request Lane

- *Requirements*: A customer must be able to request a lane, specify the number of players, and be assigned a lane.

- *Pre-conditions*: The customer and all players must be in the GoBowl system. 

- *Post-conditions*: The customer and all associated players must be assigned to a lane and the lane is no longer available to other customers.

- *Scenario 1*:
	1. The customer requests a lane
	2. GoBowl scans the requesting customer's card
	3. The customer provides the number of players
	4. GoBowl scans all other customer cards
	5. GoBowl assigns a lane to the players
	6. GoBowl shows the lane number on screen

- *Scenario 2*:
	1. The customer requests a lane
	2. The customer fails or is unable to provide their customer card
	3. GoBowl displays an error to the customer

- *Scenario 3*:
	1. The customer requests a lane
	2. GoBowl scans the requesting customer's card
	3. The customer enters the number of players
	4. GoBowl fails or is unable to scan the required number of customer cards 
	5. GoBowl displays an error to the customer

### Use case 5 - Checkout Lane

- *Requirements*: A customer must be able to checkout a previously assigned lane, split the bill among any number of the players, and the players may pay using any credit card.
- *Pre-conditions*: The customer must be assigned to a lane.
- *Post-conditions*: The customer and all players are no longer assigned to the lane, and any number of players are able to split and pay the bill using any credit cards.
- *Scenario 1*:
	1. The customer selects to checkout a lane
	2. The customer supplies a lane number they have been assigned
	3. GoBowl asks if the customer would like to save a score
	4. The customer selects 'yes' and continues to step 5, or the customer selects 'no' and continues with step 9
	5. The customer selects a player
	6. The customer enters the score to be saved
	7. GoBowl saves the date and score for the selected player
	8. Steps 3 - 7 are repeated until the customer selects 'no'
	9. GoBowl asks how many ways to split the bill
	10. The customer enters the number of ways to split the bill
	11. The players swipe as many cards as stated
	12. GoBowl charges the correct amount to each card

- *Scenario 2*:
	1. The customer selects to checkout a lane
	2. The customer supplies a lane number they have not been assigned
	3. GoBowl displays an error

- *Scenario 3*:
	1. The customer selects to checkout a lane
	2. The customer supplies a lane number they have been assigned
	3. GoBowl asks if the customer would like to save a score
	4. The customer selects 'yes' and continues to step 5, or the customer selects 'no' and continues with step 9
	5. The customer selects a player
	6. The customer enters the score to be saved
	7. GoBowl saves the date and score for the selected player
	8. Steps 3 - 7 are repeated until the customer selects 'no'
	9. GoBowl asks how many ways to split the bill
	10. The customer enters an incorrect number of ways to split the bill (less than one or greater than the number of players)
	11. GoBowl displays an error requesting the customer input a valid number