# Test Plan

**Author**: Sylvester Witkowski - switkowski3

| Version | Description     |
| --------|:---------------:|
| V1      | Initial version |
| V2      | Add partial results |

## 1 Testing Strategy

### 1.1 Overall strategy

**Activities**: System testing will be used in this testing process to make sure all the different parts of the system work together as expected.
**Who**: Sylvester Witkowski

### 1.2 Test Selection
**Technique**: For the system testing, black-box model based (FSM) techniques will be used in this testing process.

### 1.3 Adequacy Criterion
For the system testing, to assess the quality we have to make sure that the test suites cover all the requirements and all the tests succeed (pass).

### 1.4 Bug Tracking
**Bug Tracking**: Emails will be used to track the bugs.
**Enhancement Tracking**: Emails will be used to track the enhancement requests.

### 1.5 Technology
For the system testing, manual testing will be performed.

## 2 Test Cases

| Test                                               | Steps                                     | Expected result                  | Actual result                  | Status         | Note                           |
| ---------------------------------------------------|:-----------------------------------------:|:--------------------------------:|:------------------------------:|:--------------:|:------------------------------:|
| Verify that manager can add customer.              | 1) start the app                          | customer should be created       |customer was created            |  PASS          |  "printCard called" in log     | 
|                                                    | 2) click "MANAGER" button                 | customer should have name        |customer  has name              |                |                                |
|                                                    | 3) click "ADD CUSTOMER" button            | customer should have email       |customer has email              |                |                                |
|                                                    | 4) Enter value for all fields             | customer should have unique HexID|customer has unique HexID       |                |                                |
|                                                    | 5) click "SUBMIT" button                  | card is printed for customer     |card is printed for customer    |                |                                |
|                                                    | 6) click "EDIT CUSTOMER INFO " button     |                                  |                                |                |                                |
|                                                    | 7) select customer that was created from the drop down   |                   |                                |                |                                |
|                                                    |                                           |                                  |                                |                |                                |
| ---------------------------------------------------|-------------------------------------------|----------------------------------|--------------------------------|----------------|--------------------------------|
| Verify that manager can not add same customer.     | 1) start the app                          | error                            |                                |                |                                | 
|                                                    | 2) click "MANAGER" button                 |                                  |                                |                |                                |
|                                                    | 3) click "ADD CUSTOMER" button            |                                  |                                |                |                                |
|                                                    | 4) Enter same customer value for all fields|                                 |                                |                |                                |
|                                                    | 5) click "SUBMIT" button                  |                                  |                                |                |                                |
|                                                    |                                           |                                  |                                |                |                                |
|                                                    |                                           |                                  |                                |                |                                |
| ---------------------------------------------------|-------------------------------------------|----------------------------------|--------------------------------|----------------|--------------------------------|
| Verify that manager can print customer cards       | 1) start the app                          | Cards should be printed          |card is printed for customer    |  PASS          | "printCard called" in log      | 
|                                                    | 2) click "MANAGER" button                 |                                  |                                |                |                                |
|                                                    | 3) click "PRINT CUSTOMER CARD" button     |                                  |                                |                |                                |
|                                                    | 4) select customer from the drop down     |                                  |                                |                |                                |
|                                                    | 5) click "PRINT" button                   |                                  |                                |                |                                |
|                                                    |                                           |                                  |                                |                |                                |
| ---------------------------------------------------|-------------------------------------------|----------------------------------|--------------------------------|----------------|--------------------------------|
| Verify that manager can edit customer              | 1) start the app                          | customer data should be modified | customer data was modified     |   PASS         |                                | 
|                                                    | 2) click "MANAGER" button                 |                                  |                                |                |                                |
|                                                    | 3) click "EDIT CUSTOMER INFO " button     |                                  |                                |                |                                |
|                                                    | 4) select customer from the drop down     |                                  |                                |                |                                |
|                                                    | 5) modify values in all fields            |                                  |                                |                |                                |
|                                                    | 6) click "SUBMIT" button                  |                                  |                                |                |                                |
|                                                    | 7) click "EDIT CUSTOMER INFO " button     |                                  |                                |                |                                |
|                                                    | 8) select customer that was modified from the drop down  |                   |                                |                |                                |
|                                                    |                                           |                                  |                                |                |                                |
| ---------------------------------------------------|-------------------------------------------|----------------------------------|--------------------------------|----------------|--------------------------------|
| Verify that customer card can be read by videocam  | 1) scan card                              | card should be read              |                                |                |                                | 
|                                                    |                                           | it should encode customer ID     |                                |                |                                |
|                                                    |                                           |                                  |                                |                |                                |
|                                                    |                                           |                                  |                                |                |                                |
| ---------------------------------------------------|-------------------------------------------|----------------------------------|--------------------------------|----------------|--------------------------------|
| Verify that customer can request lane              | 1) launch "Lane request"                  | lane should be assigned to playes|                                |                |                                | 
|                                                    | 2) scan customer card                     | lane should be on screen         |                                |                |                                |
|                                                    | 3) provide number of players              |                                  |                                |                |                                |
|                                                    | 4) scan all players cards                 |                                  |                                |                |                                |
|                                                    |                                           |                                  |                                |                |                                |
| ---------------------------------------------------|-------------------------------------------|----------------------------------|--------------------------------|----------------|--------------------------------|
| Verify that customer can not request lane          | 1) launch "Lane request"                  | error                            |                                |                |                                | 
| when they can not provide the card                 | 2) scan wrong customer card               |                                  |                                |                |                                |
|                                                    | 3)                                        |                                  |                                |                |                                |
|                                                    | 4)                                        |                                  |                                |                |                                |
|                                                    |                                           |                                  |                                |                |                                |
| ---------------------------------------------------|-------------------------------------------|----------------------------------|--------------------------------|----------------|--------------------------------|
| Verify that customer can checkout lane             | 1) launch "Checkout"                      | score should be saved for palyers|                                |                |                                | 
| with save score option                             | 2) provide lane number                    | current date should be saved with scores    |                                |                |                                |
| and split bill > 1                                 | 3) select save score option               | bill should be splitted into the number that was enterd                                 |                                |                |                                |
|                                                    | 4) enter score for players                | any number of cards should be accepted                                 |                                |                |                                |
|                                                    | 5) enter number >1 for split bill         | cost should be correct           |                                |                |                                |
|                                                    | 6) swipe credit card                      | credit card scanner should read name, account number, expiration date and security code                                 |                                |                |                                |
|                                                    |                                           | total cost is saved for customer |                                |                |                                |
| ---------------------------------------------------|-------------------------------------------|----------------------------------|--------------------------------|----------------|--------------------------------|
| Verify that customer can checkout lane             | 1) launch "Checkout"                      | score should not be saved for palyers|                                |                |                                | 
| without save score option                          | 2) provide lane number                    | bill should not be split         |                                |                |                                |
| and split bill = 1                                 | 3) do not save score                      | credit card should be accepted   |                                |                |                                |
|                                                    | 4) enter 1 for split bill                 | cost should be correct           |                                |                |                                |
|                                                    | 5) swipe credit card                      | credit card scanner should read name, account number, expiration date and security code           |                                |                |                                |
|                                                    |                                           | total cost is saved for customer |                                |                |                                |
|                                                    |                                           |                                  |                                |                |                                |
| ---------------------------------------------------|-------------------------------------------|----------------------------------|--------------------------------|----------------|--------------------------------|
| Verify that customer can have VIP status           | 1) make sure that total cost is >=500     | customer should have VIP status  |                                |                |                                |
| when >= $500 is spent in a single calendar year    | 2) launch "Lane request"                  | cost should be correct - includes %10          |                                |                |                                |
| and 10% discount is given                          | 3) provide number of players              |                                  |                                |                |                                |
|                                                    | 4) scan all players cards                 |                                  |                                |                |                                |
|                                                    | 5) launch "Checkout"                      |                                  |                                |                |                                |
|                                                    | 6) provide lane number                    |                                  |                                |                |                                |
|                                                    | 7) do not save score                      |                                  |                                |                |                                |
|                                                    | 8) enter 1 for split bill                 |                                  |                                |                |                                |
|                                                    | 9) swipe credit card                      |                                  |                                |                |                                |
| ---------------------------------------------------|-------------------------------------------|----------------------------------|--------------------------------|----------------|--------------------------------|
| Verify that customer can not checkout lane         | 1) launch "Checkout"                      | error                            |                                |                |                                | 
| with wrong name line                               | 2) provide wrong lane number              |                                  |                                |                |                                |
|                                                    |                                           |                                  |                                |                |                                |
|                                                    |                                           |                                  |                                |                |                                |
|                                                    |                                           |                                  |                                |                |                                |
|                                                    |                                           |                                  |                                |                |                                |
|                                                    |                                           |                                  |                                |                |                                |
| ---------------------------------------------------|-------------------------------------------|----------------------------------|--------------------------------|----------------|--------------------------------|



