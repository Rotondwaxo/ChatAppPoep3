# ChatApp POE – Part 3 (Proof of Portfolio)

## Project Overview

This is the final implementation of the **PROG5121 Programming 1A** Proof of Evidence.  
The application is a console‑based chat system that includes:

- **User registration & login** with strict validation (username, password, South African cell number)
- **Messaging system** – users can send, disregard, or store messages
- **Message attributes** – unique 10‑digit ID, auto‑incremented number, hash (first two digits of ID : message number : first word + last word, all caps)
- **JSON storage** – messages are appended to `messages.json`
- **Data management using parallel arrays**:
  - Sent messages
  - Disregarded messages
  - Stored messages (loaded from JSON file)
  - Message hashes
  - Message IDs
- **Report and search features**:
  - Display report of all sent messages (hash, recipient, message)
  - Find the longest message
  - Search message by ID
  - Search all messages by recipient
  - Delete a message using its hash
- **Automated unit tests** (JUnit 5) covering all validation, messaging, and array operations
- **Git version control** with feature branches and **GitHub Actions** (CI) that run tests on every push

---

## Technologies Used

- Java 17
- Maven (build & dependency management)
- JUnit 5 (unit testing)
- Git & GitHub (version control)
- GitHub Actions (continuous integration)
- JSON (local file storage)

---

## Project Structure
