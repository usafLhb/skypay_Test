"# skypay_Test" 

1. Is it recommended to put all the functions in the same service?
No, it is generally not recommended.

Explanation:

Separation of concerns makes the codebase easier to maintain, read, and test.

Ideally, we would have separate service classes to manage users, rooms, and bookings.

A single service can become too large and difficult to manage as the application grows in size and complexity.

2. The setRoom(...) function must not impact existing bookings. Is there another way? What is your recommendation?
Alternative:
Allow room updates by creating a new version of the room (versioning), or make room instances immutable and create a new room with a new ID when changes are needed.

Recommendation:

Never modify room data that is already associated with past bookings (immutability ensures historical data integrity).

If updates are needed, it's better to create a new room instance with a new identifier or keep a modification history.

This approach preserves the accuracy and traceability of historical booking data
