# Step 3: Manual Test Cases

## Test Case 1: View Directory List as Authorized User
- Precondition: User is logged in with appropriate permissions.
- Steps:
  1. Navigate to the Directories section.
  2. Observe the list of directories displayed.
- Expected Result: The user sees the complete list of directories.

## Test Case 2: Search for Directory by Name (Positive and Boundary)
- Precondition: User is logged in with appropriate permissions.
- Steps:
  1. Navigate to the Directories section.
  2. Enter an existing directory name in the search field.
  3. Click the search button.
- Expected Result: The directory matching the search term is displayed.

- Steps (Boundary):
  1. Enter a single character or the maximum allowed length in the search field.
  2. Click the search button.
- Expected Result: The application returns correct results or a "no results found" message as appropriate.

## Test Case 3: Unauthorized Access to Directory Details
- Precondition: User is not logged in or lacks required permissions.
- Steps:
  1. Attempt to access the details page of any directory.
- Expected Result: The user receives an access denied message or is redirected to the login page.
