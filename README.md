
# Email-Based Authentication

This project demonstrates a simple yet secure email-based authentication method in Java. The application generates a one-time verification code, sends it to the user’s email, and verifies the code input by the user. This provides a basic form of two-factor authentication (2FA) using Java's Jakarta Mail API.

## Features
- Generates a random 6-digit verification code.
- Sends the verification code to the user's email.
- Verifies the user by matching the code input with the generated code.

## Requirements
- **Java Development Kit (JDK)** version 11 or higher.
- **Gradle** for dependency management.

## Setup Instructions

### 1. Clone the Repository
Clone the project repository or download the source files directly.

### 2. Configure SMTP Credentials
Update the following constants in the `Main.java` file:
- `SMTP_SERVER`: Your SMTP server address (e.g., `smtp.gmail.com`).
- `USERNAME`: Your email address.
- `PASSWORD`: Your email password or an application-specific password (recommended).

> **Important**: For Gmail, you may need to use an **App Password** if Two-Factor Authentication (2FA) is enabled. Refer to [Google's App Passwords](https://support.google.com/accounts/answer/185833) documentation.

### 3. Build and Run the Application Using Gradle
1. Run the application with Gradle:
   ```bash
   ./gradlew run
   ```
2. Follow the prompts:
    - Enter your email address when prompted.
    - Check your email for the verification code.
    - Enter the code to complete authentication.

## Gradle Configuration
The project uses Gradle for dependency management. Relevant sections of the `build.gradle` file:

```gradle
plugins {
    id 'java'
}

group = 'com.indritvaka'
version = '1.0-SNAPSHOT'

repositories {
    mavenCentral()
}

dependencies {
    testImplementation platform('org.junit:junit-bom:5.10.0')
    testImplementation 'org.junit.jupiter:junit-jupiter'
    implementation 'org.eclipse.angus:angus-mail:2.0.1'
}

test {
    useJUnitPlatform()
}
```

This configuration includes:
- **JUnit** for testing.
- **Angus Mail** (`org.eclipse.angus:angus-mail:2.0.1`) for email handling, replacing the previous Jakarta Mail package.

## How It Works

1. **Generate Code**: The application generates a random 6-digit code.
2. **Send Code**: The code is sent to the specified email using the configured SMTP server.
3. **Code Verification**: The user is prompted to enter the code they received. If it matches, authentication is successful.

## Security Considerations
- **Store Passwords Securely**: Never hard-code sensitive information like passwords directly in source code. Instead, consider using environment variables or configuration files.
- **Use App Passwords**: When using email services like Gmail with 2FA, use an app-specific password instead of your actual account password.
- **Validate Email Input**: Basic email validation is implemented, but consider more robust validation for production applications.

---

