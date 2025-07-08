# Fawry Project

## Key Features

### 1. **Polymorphism in `Product`**
The `Product` class is an abstract base class that is extended by multiple subclasses:
- `ShippableProduct`
- `ExpirableProduct`
- `ExpirableShippableProduct`

Each subclass provides specific implementations for methods like `isAvailable`, `getWeight`, and `getShippingFees`. This allows the system to handle different types of products uniformly while leveraging their unique behaviors.

### 2. **Instance Checking in `printShipment` and `calcPrices`**
The `Cart` class uses `instanceof` to check whether a product implements the `ShippableProductInterface`. This is used in methods like:
- `printShipment`: Collects and prints details of shippable products.
- `calcPrices`: Calculates shipping fees for shippable products.

### 3. **Error Handling with `try-catch`**
The system uses `try-catch` blocks to handle errors like:
- **RuntimeException**:
  - Thrown when a product is not found or balance is not sufficient.

### 4. **Dynamic Behavior with Interfaces**
The `ShippableProductInterface` defines methods like `getWeight` and `getShippingFees`. Classes implementing this interface can be processed uniformly, enabling dynamic behavior.