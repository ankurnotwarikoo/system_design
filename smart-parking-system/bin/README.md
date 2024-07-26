# Smart Parking System

This project implements a backend system for a smart parking lot using standard Java with in-memory data storage. It handles vehicle entry and exit management, parking space allocation, and fee calculation.

## Project Structure

```
smart-parking-system/
├── src/
│   └── com/
│       └── smartparking/
│           ├── model/
│           │   ├── ParkingSpot.java
│           │   ├── Vehicle.java
│           │   └── ParkingTransaction.java
│           ├── service/
│           │   ├── ParkingService.java
│           │   ├── FeeCalculationService.java
│           │   └── SpotAllocationService.java
│           ├── util/
│           │   └── ConcurrencyUtil.java
│           └── SmartParkingSystem.java
└── README.md
```

## Key Components

1. **Model**: Represents the core entities in the system.
2. **Service**: Contains the business logic for parking operations and manages in-memory data.
3. **Util**: Provides utility functions, including concurrency handling.

## Design Patterns Used

1. **Singleton**: Used for the `SmartParkingSystem` class to ensure a single instance of the system.
2. **Strategy**: Implemented in the fee calculation logic to allow for different fee calculation strategies.
3. **Observer**: Implemented to update real-time availability of parking spots.

## Getting Started

1. Clone the repository
2. Navigate to the project directory
3. Compile the Java files: `javac -d bin src/com/smartparking/**/*.java`
4. Run the main class: `java -cp bin com.smartparking.SmartParkingSystem`

## Contributing

Please read CONTRIBUTING.md for details on our code of conduct, and the process for submitting pull requests.

## License

This project is licensed under the MIT License - see the LICENSE.md file for details.
