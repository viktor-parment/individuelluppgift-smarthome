# SmartHome System

A console-based system for managing smart home devices, built as an individual deep-dive assignment in Java.

## Description

The system lets a user administer different types of connected devices (lamps, cameras, radiators) through a common interface. Devices can be added, removed, searched for, listed, activated and deactivated. The solution is designed to be easily extended with new device types in the future.

## Tech stack

- **Language:** Java 25
- **Build system:** Gradle
- **Test framework:** JUnit 5
- **Mocking:** Mockito

## Project structure

```
src/main/java/se/chasacademy/fjax26/smarthome/
├── Main.java            Entry point and demonstration
├── model/                SmartDevice (abstract), Lamp, Camera, Radiator, Activatable (interface)
├── repository/           DeviceRepository (interface), InMemoryDeviceRepository
├── service/              SmartDeviceService, ReportService
└── exception/            DeviceNotFoundException, DeviceStateException, DuplicateDeviceIdException

src/test/java/se/chasacademy/fjax26/smarthome/
├── model/                Tests for SmartDevice, Lamp, Camera, Radiator
├── repository/           Tests for InMemoryDeviceRepository
└── service/              Tests for SmartDeviceService, ReportService
```

## How to run the program

### Option 1: Via Gradle

```bash
./gradlew run
```

### Option 2: Build and run an executable JAR

```bash
./gradlew build
java -jar build/libs/individuelluppgift-smarthome-1.0-SNAPSHOT.jar
```

The program's `main` method (in `Main.java`) runs a demonstration showing:
- Creation of multiple devices
- Activation and deactivation
- Searching for a device
- Listing all devices
- Status reporting (number of active/inactive devices)
- Examples of error handling (duplicate id, device not found)

## How to run the tests

```bash
./gradlew test
```

A test report is generated at `build/reports/tests/test/index.html` and can be opened in a browser for a detailed breakdown.

The project includes tests across all layers:
- **Model layer:** constructor validation, activation/deactivation, boundary cases for each device type
- **Repository layer:** CRUD operations, duplicate protection, encapsulation protection (unmodifiable list)
- **Service layer:** business logic and validation, with the repository mocked via Mockito

## Design decisions in brief

- **Inheritance** is used for the device types (`Lamp`, `Camera`, `Radiator extends SmartDevice`), since a genuine is-a relationship exists between them.
- **Composition** Composition is used for time handling (SmartDevice has a Clock), which makes time-dependent logic testable without depending on the system's real clock. The same pattern is used in the service layer: SmartDeviceService and ReportService each have a DeviceRepository (injected through the constructor) rather than being one, which lets each service delegate storage concerns to the repository and be tested with a mocked repository.
- **Interfaces** (`DeviceRepository`, `Activatable`) are used to reduce coupling between layers and to enable mocking in tests (Dependency Inversion Principle).
- **Single Responsibility Principle** runs through the layering: the repository handles storage, `SmartDeviceService` handles adding/removing devices, and `ReportService` handles reporting and statistics.
- **Encapsulation:** `InMemoryDeviceRepository.getDeviceList()` returns an unmodifiable copy of its internal list, preventing callers from mutating the repository's state directly.

## AI usage

AI tools (Claude) were used as support during development, for code review, debugging, and design discussions. All code has been reviewed and understood by the student.

**Example of a corrected AI suggestion:** A suggested test used `assertThrows(RuntimeException.class, ...)` to verify that the returned device list is unmodifiable. This was corrected to the more specific `assertThrows(UnsupportedOperationException.class, ...)`, since the broader exception type would have made the test less reliable — it could have passed even if a completely different, unintended bug caused some other kind of `RuntimeException`.

