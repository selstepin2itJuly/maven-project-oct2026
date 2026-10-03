#############################
ROLE
Act as a Senior QA Engineer.

CONTEXT
Application: [https://opensource-demo.orangehrmlive.com/]
Feature: [Directory]
Environment: [QA]

REQUIREMENTS
[REQUIREMENTS]

TASK
[TASK]

CONSTRAINTS
- Do not invent requirements.
- Separate facts from assumptions.
- Consider positive, negative, and boundary cases.
- Follow the project's technology and coding standards.

OUTPUT
Return:
[EXACT FORMAT]
###########################

#Prompt Specificity and Progressive Disclosure
#Do not start every task with a giant prompt. Give the AI enough information to complete the current step, review the result, and then move to the next step.
Example: requirement to Selenium automation
Step 1
Analyze the requirement and list the testable business rules.

Step 2
Generate positive, negative, and boundary test scenarios from the confirmed rules.

Step 3
Convert approved scenarios into detailed manual test cases.

Step 4
Identify which test cases are suitable for Selenium automation and explain why.

Step 5
Create Java + Selenium + TestNG automation using Page Object Model. create code for testcases. Use same structure as other testcases and use valid credentials. Keep the code in Java programing.

Step 6
Review the generated code for synchronization, locator stability, assertions,
maintainability, and duplication.

Step 7
Create the Maven command needed to execute the selected TestNG suite.

Step 8
Suggest a Git workflow for committing the automation changes.
###############################
