# IOBuilders POC

### Sergio Alba Alcalde

---

The project is divided into 3 main packages:

- `org.picobankapi.poc.core`: Common dependencies and classes
- `org.picobankapi.poc.user`: User components in charge of creating, and getting the users.
- `org.picobankapi.poc.wallet`: Wallet components of the application, in charge of creating and operating the wallets of the users.

## Dependencies

- JDK 17
- Maven

## Run

In terminal: `mvn spring-boot:run`

## API

Runs on `localhost:8080` by default.

**H2 UI**

Can be found under: `/h2-ui`

- User: `iobuilders`
- Pwd: `hexagonal`

### Users

#### Register user

**POST** `"/user"`

- Schema:

```
{
    "username": string,
    "password": string,
    "alias": string,
}
```

- Operations

  - Creates a new user.
  - Creates a new wallet for the user with 0.00 in balance.

- Responses:
  - **200** - Successfully created user
  - **400** - Bad request
  - **409** - User with that username already exists

#### Getting users

**GET** `"/user"`

- Responses:
  - **200** - List of users [can be empty]:
  ```
  [
      {
          "id": number,
          "username": string,
          "password": string,
          "alias": string,
      },
  ]
  ```

#### Getting a user by username

**GET** `"/user/username/{username}"`

- Responses:
  - **200** - User data:
  ```
  {
      "id": number,
      "username": string,
      "password": string,
      "alias": string,
  }
  ```
  - **404** - User not found.

### Wallet

#### Get balance

**GET** `"/wallet/{id}"`

- Responses:
  - **200** - Wallet balance, for example:
  ```
  110.00
  ```
  - **404** - Wallet not found.

#### Deposit

**POST** `"/wallet/{id}/deposit"`

- Body:
  ```
  {
      "amount": number
  }
  ```
- Operations:

  - Source wallet balance increased by amount.
  - Deposit gets registered as movement.

- Responses:
  - **200** - Operation Ok.
  - **400** - Bad request.
  - **404** - Wallet not found.

#### Withdraw

**POST** `"/wallet/{id}/withdraw"`

- Body:

  ```
  {
      "amount": number
  }
  ```

- Operations:

  - Source wallet balance decreased by amount.
  - Withdrawal gets registered as movement.

- Responses:
  - **200** - Operation Ok.
  - **400** - Bad request.
  - **404** - Wallet not found.

#### Transfer

**POST** `"/wallet/transfer"`

- Body:
  ```
  {
      "sourceId": number,
      "targetId": number,
      "amount": number
  }
  ```
- Operations:

  - Withdrawal and deposit get registered as movements.

- Responses:
  - **200** - Operation Ok.
  - **400** - Bad request.
  - **404** - Wallet(s) not found.

#### Get Movements

**GET** `"/movements/{id}"`

- Responses:
  - **200** - Wallet movement data:
  ```
  [
      {
          "id": number,
          "source": null | Wallet,
          "target": null | Wallet,
          "amount": number,
          "type": one of "DEPOSIT" | "WITHDRAWAL" | "TRANSFER"
      }
  ]
  ```
  - **400** - Bad request.
  - **404** - Wallet not found.

## Next Steps

- Split the `wallet` and `user` endpoints into two separate microservices.
  - MQ to transfer events
- Implement the [Command / Handler](https://refactoring.guru/es/design-patterns/command) pattern for each use case.
  - Every event is sent decoupled from the handler.
- Implement OAuth2 / JWT for route authentication / authorization.
  - Protect all wallet routes so only the wallet holder can access them.
  - Have a login route and login handler w/ middleware to check for authorization token.
- Allow for one user to register a new wallet.
  - Make the `create` function on `WalletService` interface available on the `WalletControllerREST` controller.
- Deeper and more through testing.
- Salt and hash user passwords so they are never stored in clear text.
  - More on [BCrypt](https://docs.spring.io/spring-security/site/docs/current/api/org/springframework/security/crypto/bcrypt/BCrypt.html).
- Implement an NoSQL / SQL database adapter for real persistance infrastructure.
- Implement CI/CD pipelines.
- Deployment.
