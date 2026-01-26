
Each epic explicitly maps to:

**Web Layer → Controller Layer → Service Layer → Repository Layer → Model Classes**

---

# 📌 Digital Wallet Management System

### Epic-Level Feature List

---

## **Epic 1: User Management & Profile Context**

**Description:**
Manage application users and maintain contextual identity across all operations.

**Layer Mapping:**

* **Web Layer:** User selection UI (username-based context)
* **Controller Layer:** `UserController`
* **Service Layer:** `UserService`
* **Repository Layer:** `UserRepository`
* **Model Classes:** `User`

**Key Capabilities:**

* Create and view users
* Activate / deactivate user
* Fetch user details by username
* Maintain user context across requests

---

## **Epic 2: Wallet Creation & Ownership**

**Description:**
Ensure every user has exactly one wallet linked to their identity.

**Layer Mapping:**

* **Web Layer:** Wallet overview screen
* **Controller Layer:** `WalletController`
* **Service Layer:** `WalletService`
* **Repository Layer:** `WalletRepository`
* **Model Classes:** `Wallet`, `User`

**Key Capabilities:**

* Auto-create wallet for user
* Fetch wallet by user
* Prevent multiple wallets per user
* Wallet status management

---

## **Epic 3: Credit Money to Wallet**

**Description:**
Add funds to a user wallet through controlled credit transactions.

**Layer Mapping:**

* **Web Layer:** Credit money form
* **Controller Layer:** `TransactionController`
* **Service Layer:** `TransactionService`
* **Repository Layer:** `TransactionRepository`
* **Model Classes:** `Transaction`, `Wallet`, `Category`

**Key Capabilities:**

* Credit wallet balance
* Create CREDIT transaction record
* Validate category type
* Generate reference ID

---

## **Epic 4: Debit Money from Wallet**

**Description:**
Deduct funds from wallet while enforcing balance validation.

**Layer Mapping:**

* **Web Layer:** Debit money form
* **Controller Layer:** `TransactionController`
* **Service Layer:** `TransactionService`
* **Repository Layer:** `TransactionRepository`
* **Model Classes:** `WalletTransaction`, `Wallet`

**Key Capabilities:**

* Check available balance
* Prevent overdraft
* Create DEBIT transaction
* Atomic balance update

---

## **Epic 5: Transaction History & Audit Trail**

**Description:**
Provide complete transaction visibility for wallets.

**Layer Mapping:**

* **Web Layer:** Transaction list UI
* **Controller Layer:** `TransactionController`
* **Service Layer:** `TransactionService`
* **Repository Layer:** `TransactionRepository`
* **Model Classes:** `WalletTransaction`

**Key Capabilities:**

* List transactions by wallet
* Sort by date
* Filter by type (CREDIT/DEBIT)
* Pagination support

---

## **Epic 6: Expense & Income Categorization**

**Description:**
Classify transactions under standardized categories.

**Layer Mapping:**

* **Web Layer:** Category management UI
* **Controller Layer:** `CategoryController`
* **Service Layer:** `CategoryService`
* **Repository Layer:** `CategoryRepository`
* **Model Classes:** `Category`

**Key Capabilities:**

* Create expense/income categories
* Activate / deactivate categories
* Validate category usage
* Prevent deletion if in use

---

## **Epic 7: Monthly Wallet Summary**

**Description:**
Generate monthly financial summaries for wallets.

**Layer Mapping:**

* **Web Layer:** Monthly summary dashboard
* **Controller Layer:** `ReportController`
* **Service Layer:** `ReportService`
* **Repository Layer:** `TransactionRepository`
* **Model Classes:** `WalletTransaction`

**Key Capabilities:**

* Monthly credit total
* Monthly debit total
* Net balance calculation
* Category-wise summary

---

## **Epic 8: Wallet Balance Consistency & Integrity**

**Description:**
Ensure wallet balance always reflects transaction history.

**Layer Mapping:**

* **Web Layer:** Balance display
* **Controller Layer:** `WalletController`
* **Service Layer:** `WalletService`
* **Repository Layer:** `WalletRepository`
* **Model Classes:** `Wallet`, `WalletTransaction`

**Key Capabilities:**

* Balance recalculation
* Transaction-based updates only
* Consistency checks
* Recovery logic (future extension)

---

## **Epic 9: Validation & Exception Handling**

**Description:**
Standardize validations and error responses.

**Layer Mapping:**

* **Web Layer:** Error messages display
* **Controller Layer:** `@ControllerAdvice`
* **Service Layer:** Business validations
* **Repository Layer:** Data integrity constraints
* **Model Classes:** DTOs, Custom Exceptions

**Key Capabilities:**

* Input validation
* Business rule enforcement
* Meaningful error responses
* Centralized exception handling

---

## **Epic 10: Testing & Quality Assurance**

**Description:**
Validate business logic and data integrity using automated tests.

**Layer Mapping:**

* **Service Layer:** Unit tests
* **Repository Layer:** JPA tests
* **Controller Layer:** API tests
* **Model Classes:** Entity validations

**Key Capabilities:**

* Service layer unit tests
* Mockito-based mocking
* Repository integration tests
* Transaction scenario testing

---

# 🧠 Why This Feature List Works Well

✔ Clearly shows **layered architecture**
✔ Easy to explain **responsibility separation**
✔ Maps directly to **package structure**
✔ Perfect for **README, assessments, demos**
✔ Scales naturally for future features

---

