# Step 2: Test Scenarios

## Business Rule 1: View Directory List
- Positive: Authorized user logs in and successfully views the full directory list.
- Negative: Unauthorized user attempts to view the directory list and is denied access.
- Boundary: Directory list is empty; authorized user sees an appropriate empty state message.

## Business Rule 2: Search Directory
- Positive: User searches for an existing directory name and receives correct results.
- Negative: User searches for a non-existent directory name and receives a "no results found" message.
- Boundary: User searches with a single character or maximum allowed length for directory name.

## Business Rule 3: Unauthorized Access
- Positive: Unauthorized user tries to access directory details and receives an access denied message.
- Negative: Authorized user accesses directory details and is able to view them.
- Boundary: User with expired session/token tries to access directory details and is redirected to login or shown an error.
