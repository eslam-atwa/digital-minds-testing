# Digital Minds Testing

Comprehensive testing suite for the [Digital Minds website](https://digitall-minds.com/) including automated Selenium tests and performance testing.

##  Project Structure

```
digital-minds-testing/
├── automation_testing/    # Selenium automated tests
│   ├── src/
│   │   └── test/java/
│   │       ├── BaseTest.java
│   │       └── HomeAbout_DigitalMindsTest.java
│   └── pom.xml
├── performance-tests/     # Performance testing scripts
└── README.md
```

##  Automation Tests

### What's Tested
- **Home Page**: Navigation, hero section, images, footer
- **About Page**: Content structure, images, footer

### Prerequisites
- Java JDK 8+
- Maven 3.6+
- Chrome Browser

### Setup & Run

1. **Clone the repository**
```bash
git clone https://github.com/eslam-atwa/digital-minds-testing.git
cd digital-minds-testing/automation_testing
```

2. **Run tests**
```bash
mvn clean test
```

### Test Reports
After running tests, find detailed reports in:
- Console output (real-time)
- `target/surefire-reports/` (HTML/XML reports)

##  Technologies Used

- **Selenium WebDriver 4.21.0** - Browser automation
- **TestNG 7.10.2** - Testing framework
- **WebDriverManager 5.7.0** - Automatic driver management
- **Maven** - Build & dependency management

## 🔧 Configuration

Tests use:
- **Base URL**: `https://digitall-minds.com`
- **Browser**: Chrome (headless option available)
- **Implicit Wait**: 2 seconds

##  Known Issues

- Some images may load slowly on first run
- Footer detection uses multiple selectors for flexibility

##  Contributors

- [Eslam Atwa](https://github.com/eslam-atwa)



---

**Website Under Test**: [digitall-minds.com](https://digitall-minds.com/)
