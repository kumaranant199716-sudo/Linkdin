# LinkedIn Job Application Automation

This project automates the process of applying to jobs on LinkedIn using Selenium WebDriver and TestNG. It's designed to search for SDET (Software Development Engineer in Test) positions with the "Easy Apply" filter and submit applications automatically.

## Prerequisites

- Java 11 or higher
- Maven 3.6.0 or higher
- Chrome browser installed (for ChromeDriver)
- LinkedIn account credentials

## Setup

1. Clone the repository
2. Update the `src/test/resources/config.properties` file with your LinkedIn credentials:
   ```
   linkedin.username=your_email@example.com
   linkedin.password=your_password
   ```
3. (Optional) Adjust other settings in the config file as needed

## Project Structure

```
src/
├── main/java/com/linkedin/automation/
│   ├── pages/           # Page Object Model classes
│   ├── utils/           # Utility classes
│   └── listeners/       # TestNG listeners
├── test/java/com/linkedin/automation/tests/  # Test classes
└── test/resources/      # Configuration files
```

## Running the Tests

To run the tests, use the following Maven command:

```bash
mvn clean test
```

Or run the `testng.xml` file directly from your IDE.

## Features

- Automates LinkedIn login
- Searches for SDET positions
- Applies "Easy Apply" filter
- Handles job application forms
- Takes screenshots on test failure
- Configurable number of applications

## Notes

- Use this tool responsibly and don't spam applications
- LinkedIn might detect and block automated activities, so use with caution
- The script includes delays to mimic human behavior
- Always verify your applications in the LinkedIn dashboard after running the tests

## Dependencies

- Selenium WebDriver
- TestNG
- WebDriverManager
- Apache POI (for future Excel integration)
- Extent Reports (for reporting)
- Log4j2 (for logging)

## License
