import org.junit.jupiter.api.Test;

import static java.lang.Double.NaN;
import static org.junit.jupiter.api.Assertions.*;

public class LoanRepaymentCalculatorTest {

    // Test Case 1 - Monthly Repayment, No Interest (Interest Rate = 0)
    @Test
    public void testMonthlyRepayment_NoInterest() {
        double loanAmount = 10000;
        int loanTermMonths = 36;
        double interestRatePerYear = 0;
        int repaymentFrequency = LoanRepaymentCalculator.MONTHLY;

        LoanRepaymentCalculator.LoanRepaymentDetails result =
                LoanRepaymentCalculator.calculateLoanRepayment(loanAmount, loanTermMonths, interestRatePerYear, repaymentFrequency);

        assertEquals(NaN, result.getTotalInterest(), 0.001);
        assertEquals(NaN, result.getTotalRepayment(), 0.001);
        // Add more assertions for the repayment schedule if needed
    }

    // Test Case 2 - Bi-Monthly Repayment, High Interest (Interest Rate > Loan Amount)
    @Test
    public void testBiMonthlyRepayment_HighInterest() {
        double loanAmount = 5000;
        int loanTermMonths = 24;
        double interestRatePerYear = 10;
        int repaymentFrequency = LoanRepaymentCalculator.BI_MONTHLY;

        LoanRepaymentCalculator.LoanRepaymentDetails result =
                LoanRepaymentCalculator.calculateLoanRepayment(loanAmount, loanTermMonths, interestRatePerYear, repaymentFrequency);

        assertEquals(558.0703538248008, result.getTotalInterest(), 0.001);
        assertEquals(5558.0703538248008, result.getTotalRepayment(), 0.001);
        // Add more assertions for the repayment schedule if needed
    }

    // Test Case 3 - Weekly Repayment, Long Loan Term
    @Test
    public void testWeeklyRepayment_LongLoanTerm() {
        double loanAmount = 20000;
        int loanTermMonths = 60;
        double interestRatePerYear = 6.5;
        int repaymentFrequency = LoanRepaymentCalculator.WEEKLY;

        LoanRepaymentCalculator.LoanRepaymentDetails result =
                LoanRepaymentCalculator.calculateLoanRepayment(loanAmount, loanTermMonths, interestRatePerYear, repaymentFrequency);

        assertEquals(3439.1526882648996, result.getTotalInterest(), 0.01);
        assertEquals(23439.1526882648996, result.getTotalRepayment(), 0.01);
        // Add more assertions for the repayment schedule if needed
    }

    // Test Case 4 - Monthly Repayment, High Interest (Interest Rate > Loan Amount)
    @Test
    public void testMonthlyRepayment_HighInterest() {
        double loanAmount = 2000;
        int loanTermMonths = 12;
        double interestRatePerYear = 20;
        int repaymentFrequency = LoanRepaymentCalculator.MONTHLY;

        LoanRepaymentCalculator.LoanRepaymentDetails result =
                LoanRepaymentCalculator.calculateLoanRepayment(loanAmount, loanTermMonths, interestRatePerYear, repaymentFrequency);

        assertEquals(223.22814152992032, result.getTotalInterest(), 0.001);
        assertEquals(2223.22814152992032, result.getTotalRepayment(), 0.001);
        // Add more assertions for the repayment schedule if needed
    }

    // Test Case 5 - Bi-Monthly Repayment, Moderate Interest
    @Test
    public void testBiMonthlyRepayment_ModerateInterest() {
        double loanAmount = 7500;
        int loanTermMonths = 18;
        double interestRatePerYear = 8.5;
        int repaymentFrequency = LoanRepaymentCalculator.BI_MONTHLY;

        LoanRepaymentCalculator.LoanRepaymentDetails result =
                LoanRepaymentCalculator.calculateLoanRepayment(loanAmount, loanTermMonths, interestRatePerYear, repaymentFrequency);

        assertEquals(541.211613407042, result.getTotalInterest(), 0.01);
        assertEquals(8041.211613407042, result.getTotalRepayment(), 0.01);
        // Add more assertions for the repayment schedule if needed
    }

    // Test Case 6 - Weekly Repayment, Low Interest (Interest Rate < 1)
    @Test
    public void testWeeklyRepayment_LowInterest() {
        double loanAmount = 12000;
        int loanTermMonths = 48;
        double interestRatePerYear = 0.5;
        int repaymentFrequency = LoanRepaymentCalculator.WEEKLY;

        LoanRepaymentCalculator.LoanRepaymentDetails result =
                LoanRepaymentCalculator.calculateLoanRepayment(loanAmount, loanTermMonths, interestRatePerYear, repaymentFrequency);

        assertEquals(121.02496565165, result.getTotalInterest(), 0.001);
        assertEquals(12121.02496565165, result.getTotalRepayment(), 0.001);
        // Add more assertions for the repayment schedule if needed
    }

    // Test Case 7 - Monthly Repayment, Negative Loan Amount (Invalid Scenario)
    @Test
    public void testMonthlyRepayment_NegativeLoanAmount() {
        double loanAmount = -3000;
        int loanTermMonths = 6;
        double interestRatePerYear = 7;
        int repaymentFrequency = LoanRepaymentCalculator.MONTHLY;

        // Verify that the calculator throws an IllegalArgumentException for negative loan amount
        assertThrows(IllegalArgumentException.class,
                () -> LoanRepaymentCalculator.calculateLoanRepayment(loanAmount, loanTermMonths, interestRatePerYear, repaymentFrequency));
    }
    @Test
    public void testMonthlyRepayment_ZeroLoanAmount() {
        double loanAmount = 0;
        int loanTermMonths = 24;
        double interestRatePerYear = 5.5;
        int repaymentFrequency = LoanRepaymentCalculator.MONTHLY;

        LoanRepaymentCalculator.LoanRepaymentDetails result =
                LoanRepaymentCalculator.calculateLoanRepayment(loanAmount, loanTermMonths, interestRatePerYear, repaymentFrequency);

        assertEquals(0, result.getTotalInterest(), 0.001);
        assertEquals(0, result.getTotalRepayment(), 0.001);
        // Add more assertions for the repayment schedule if needed
    }

    // Test Case 9 - Weekly Repayment, Zero Loan Term
    @Test
    public void testWeeklyRepayment_ZeroLoanTerm() {
        double loanAmount = 5000;
        int loanTermMonths = 0;
        double interestRatePerYear = 7;
        int repaymentFrequency = LoanRepaymentCalculator.WEEKLY;

        LoanRepaymentCalculator.LoanRepaymentDetails result =
                LoanRepaymentCalculator.calculateLoanRepayment(loanAmount, loanTermMonths, interestRatePerYear, repaymentFrequency);

        assertEquals(0, result.getTotalInterest(), 0.001);
        assertEquals(5000, result.getTotalRepayment(), 0.001);
        // Add more assertions for the repayment schedule if needed
    }

    // Test Case 10 - Bi-Monthly Repayment, High Loan Amount
    @Test
    public void testBiMonthlyRepayment_HighLoanAmount() {
        double loanAmount = 500000;
        int loanTermMonths = 48;
        double interestRatePerYear = 3.5;
        int repaymentFrequency = LoanRepaymentCalculator.BI_MONTHLY;

        LoanRepaymentCalculator.LoanRepaymentDetails result =
                LoanRepaymentCalculator.calculateLoanRepayment(loanAmount, loanTermMonths, interestRatePerYear, repaymentFrequency);

        assertEquals(37270.94923774526, result.getTotalInterest(), 0.01);
        assertEquals(537270.94923774526, result.getTotalRepayment(), 0.01);
        // Add more assertions for the repayment schedule if needed
    }

    // Test Case 11 - Monthly Repayment, Low Interest Rate
    @Test
    public void testMonthlyRepayment_LowInterestRate() {
        double loanAmount = 10000;
        int loanTermMonths = 60;
        double interestRatePerYear = 1.5;
        int repaymentFrequency = LoanRepaymentCalculator.MONTHLY;

        LoanRepaymentCalculator.LoanRepaymentDetails result =
                LoanRepaymentCalculator.calculateLoanRepayment(loanAmount, loanTermMonths, interestRatePerYear, repaymentFrequency);

        assertEquals(385.93283290974995, result.getTotalInterest(), 0.01);
        assertEquals(10385.93283290974995, result.getTotalRepayment(), 0.01);
        // Add more assertions for the repayment schedule if needed
    }

    // Test Case 12 - Bi-Monthly Repayment, Long Loan Term
    @Test
    public void testBiMonthlyRepayment_LongLoanTerm() {
        double loanAmount = 30000;
        int loanTermMonths = 120;
        double interestRatePerYear = 6;
        int repaymentFrequency = LoanRepaymentCalculator.BI_MONTHLY;

        LoanRepaymentCalculator.LoanRepaymentDetails result =
                LoanRepaymentCalculator.calculateLoanRepayment(loanAmount, loanTermMonths, interestRatePerYear, repaymentFrequency);

        assertEquals(10040.005832823215, result.getTotalInterest(), 0.01);
        assertEquals(40040.005832823215, result.getTotalRepayment(), 0.01);
        // Add more assertions for the repayment schedule if needed
    }

}
