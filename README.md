## Loan Repayment Calculator

This is a Java implementation of a loan repayment calculator. The calculator takes input parameters such as loan amount, loan term (in months), interest rate (per year), and repayment frequency (monthly, bi-monthly, or weekly) to calculate the total interest to be paid over the loan term, the total amount to be repaid over the loan term, and a repayment schedule breakdown, including the principal and interest amounts, and the remaining balance after each repayment.

### Prerequisites
- Java Development Kit (JDK) 8 or later installed on your machine.

### How to Build
1. Clone the repository to your local machine or download the source code as a ZIP file and extract it.
2. Navigate to the project directory containing the source code.

### How to Run
You can run the loan repayment calculator by executing the `LoanRepaymentCalculator` class with the appropriate input parameters.

In the `main` method of the `LoanRepaymentCalculator` class, you can set the following input parameters:
- `loanAmount`: The total loan amount.
- `loanTermMonths`: The loan term in months.
- `interestRatePerYear`: The annual interest rate for the loan.
- `repaymentFrequency`: The repayment frequency, which can be one of the following constants:
    - `MONTHLY`: For monthly repayments.
    - `BI_MONTHLY`: For bi-monthly (every two months) repayments.
    - `WEEKLY`: For weekly repayments.

After setting the input parameters, run the `main` method, and the calculator will display the results in the console, including the total interest and total repayment amount.

### How to Run the Unit Tests
To run the unit tests for the loan repayment calculator, follow these steps:
1. Ensure that you have JUnit set up in your development environment.
2. Open the `LoanRepaymentCalculatorTest` class in the `src` directory.
3. Run the test cases individually or collectively using your IDE's test runner.

### Test Coverage
The unit tests in the `LoanRepaymentCalculatorTest` class cover various scenarios, including no interest, high interest, long loan term, negative loan amount (invalid scenario), and different repayment frequencies. These tests ensure the correctness of the loan repayment calculator and validate its performance for different loan scenarios.

### Additional Information
- The interest is calculated based on the outstanding balance of the loan, and repayments are applied first to the interest and then to the principal.
- The calculator assumes that the interest is compounded monthly. If you need to consider different compounding periods, you can modify the calculation of `monthlyInterestRate` in the `LoanRepaymentCalculator` class.
- The code has been optimized for performance by calculating the repayment schedule on-the-fly and directly printing the results to the console.
- The `LoanRepaymentCalculator` class contains nested classes `LoanRepaymentDetails` to store loan repayment summary.
- The calculator uses `java.text.DecimalFormat` to format the monetary values in the output for better readability.
- The `calculateLoanRepayment` method handles different repayment frequencies and calculates the repayment amount accordingly.
- The code is well-organized, follows best practices for software development, and includes appropriate comments for better understanding.

### License
This project is open-source and licensed under the MIT License. Feel free to use and modify it as per the terms of the license.