import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class LoanRepaymentCalculator {

    // Repayment frequency constants
    protected static final int MONTHLY = 1;
    protected static final int BI_MONTHLY = 2;
    protected static final int WEEKLY = 4;

    // Function to calculate the loan repayment details
    public static LoanRepaymentDetails calculateLoanRepayment(double loanAmount, int loanTermMonths, double interestRatePerYear, int repaymentFrequency) {
        // Convert the annual interest rate to monthly interest rate
        double interestRate;
        double repaymentAmount;

        if(loanAmount < 0){
            throw new IllegalArgumentException("Invalid loan amount");
        }

        // Calculate the repayment amount based on repayment frequency
        switch (repaymentFrequency) {
            case MONTHLY:
                interestRate = interestRatePerYear / (12 * 100);
                repaymentAmount = calculateMonthlyRepayment(loanAmount, interestRate, loanTermMonths);
                break;
            case BI_MONTHLY:
                interestRate = interestRatePerYear / (6 * 100);
                repaymentAmount = calculateBiMonthlyRepayment(loanAmount, interestRate, loanTermMonths);
                break;
            case WEEKLY:
                interestRate = interestRatePerYear / (48 * 100);
                repaymentAmount = calculateWeeklyRepayment(loanAmount, interestRate, loanTermMonths);
                System.out.println(repaymentAmount);
                break;
            default:
                throw new IllegalArgumentException("Invalid repayment frequency.");
        }

        double totalInterest = 0.0;
        double remainingBalance = loanAmount;
        int numRepayments;

        // Determine the number of repayments based on the repayment frequency
        switch (repaymentFrequency) {
            case BI_MONTHLY:
                numRepayments = loanTermMonths / 2;
                break;
            case WEEKLY:
                numRepayments = loanTermMonths * 4;
                break;
            default:
                numRepayments = loanTermMonths;
                break;
        }

        List<RepaymentEntry> repaymentSchedule = new ArrayList<>();

        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        for (int i = 1; i <= numRepayments; i++) {
            double interestAmount = remainingBalance * interestRate;
            double principalAmount = repaymentAmount - interestAmount;

            // Adjust the last repayment to avoid overpaying
            if (i == numRepayments) {
                principalAmount = remainingBalance;
                repaymentAmount = interestAmount + principalAmount;
            }

            totalInterest += interestAmount;
            remainingBalance -= principalAmount;

            // Handle the case where the remaining balance goes below zero
            if (remainingBalance < 0) {
                remainingBalance = 0;
                principalAmount = repaymentAmount - interestAmount;
            }

            // Store the repayment details in the repayment schedule
            RepaymentEntry entry = new RepaymentEntry(i, repaymentAmount, principalAmount, interestAmount, remainingBalance);
            repaymentSchedule.add(entry);

            // If the remaining balance is zero, break the loop
            if (remainingBalance == 0) {
                break;
            }
        }

        double totalRepayment = loanAmount + totalInterest;

        System.out.println("Repayment Schedule:");
        System.out.println("--------------------------------------------------------------------------");
        System.out.println("|  No. " + getFrequencyString(repaymentFrequency) + "  |  Repayment  |  Principal  |  Interest  |  Remaining Balance  |");
        System.out.println("--------------------------------------------------------------------------");

        for (RepaymentEntry entry : repaymentSchedule) {
            System.out.printf("|  %-12d |   %-9s |   %-9s |   %-7s |   %-18s |%n",
                    entry.getNumber(),
                    decimalFormat.format(entry.getRepaymentAmount()),
                    decimalFormat.format(entry.getPrincipalAmount()),
                    decimalFormat.format(entry.getInterestAmount()),
                    decimalFormat.format(entry.getRemainingBalance()));
        }

        System.out.println("--------------------------------------------------------------------------");
        System.out.println("Total Interest: Ksh " + decimalFormat.format(totalInterest));
        System.out.println("Total Repayment: Ksh " + decimalFormat.format(totalRepayment));

        return new LoanRepaymentDetails(totalInterest, totalRepayment);
    }

    // Helper method to get the string representation of the repayment frequency
    private static String getFrequencyString(int repaymentFrequency) {
        switch (repaymentFrequency) {
            case MONTHLY:
                return "Monthly";
            case BI_MONTHLY:
                return "Bi-Monthly";
            case WEEKLY:
                return "Weekly";
            default:
                return "Unknown";
        }
    }

    // Helper method to calculate monthly repayment
    private static double calculateMonthlyRepayment(double loanAmount, double monthlyInterestRate, int loanTermMonths) {
        return loanAmount * (monthlyInterestRate / (1 - Math.pow(1 + monthlyInterestRate, -loanTermMonths)));
    }

    private static double calculateBiMonthlyRepayment(double loanAmount, double interestRate, int loanTermMonths) {
        return calculateMonthlyRepayment(loanAmount, interestRate, loanTermMonths / 2);
    }

    // Helper method to calculate weekly repayment
    private static double calculateWeeklyRepayment(double loanAmount, double interestRate, int loanTermMonths) {
        return loanAmount * (interestRate / (1 - Math.pow(1 + interestRate, -loanTermMonths * 4)));
    }

    // Data class to store the loan repayment details
    public static class LoanRepaymentDetails {
        private final double totalInterest;
        private final double totalRepayment;

        public LoanRepaymentDetails(double totalInterest, double totalRepayment) {
            this.totalInterest = totalInterest;
            this.totalRepayment = totalRepayment;
        }

        // Getters for the loan repayment details
        public double getTotalInterest() {
            return totalInterest;
        }

        public double getTotalRepayment() {
            return totalRepayment;
        }
    }

    // Data class to store individual repayment details
    public static class RepaymentEntry {
        private final int number;
        private final double repaymentAmount;
        private final double principalAmount;
        private final double interestAmount;
        private final double remainingBalance;

        public RepaymentEntry(int number, double repaymentAmount, double principalAmount, double interestAmount, double remainingBalance) {
            this.number = number;
            this.repaymentAmount = repaymentAmount;
            this.principalAmount = principalAmount;
            this.interestAmount = interestAmount;
            this.remainingBalance = remainingBalance;
        }

        public int getNumber() {
            return number;
        }

        public double getRepaymentAmount() {
            return repaymentAmount;
        }

        public double getPrincipalAmount() {
            return principalAmount;
        }

        public double getInterestAmount() {
            return interestAmount;
        }

        public double getRemainingBalance() {
            return remainingBalance;
        }
    }

    public static void main(String[] args) {
        // Example usage
        double loanAmount = 5000;
        int loanTermMonths = 4;
        double interestRatePerYear = 10;
        int repaymentFrequency = MONTHLY;

        calculateLoanRepayment(loanAmount, loanTermMonths, interestRatePerYear, repaymentFrequency);
    }
}
