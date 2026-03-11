import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

public class FinanceManager {

    private List<Transaction> transactions = new ArrayList<>();

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public double getTotalIncome() {
        return transactions.stream()
                .filter(t -> t.getType() == TransactionType.INCOME)
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    public double getTotalExpense() {
        return transactions.stream()
                .filter(t -> t.getType() == TransactionType.EXPENSE)
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    public double getBalance() {
        return getTotalIncome() - getTotalExpense();
    }

    public List<Transaction> getTransactionsByCategory(Category category) {
        return transactions.stream()
                .filter(t -> t.getCategory() == category)
                .toList();
    }

    public double getMonthlyBalance(YearMonth month) {
        return transactions.stream()
                .filter(t -> YearMonth.from(t.getDate()).equals(month))
                .mapToDouble(t ->
                        t.getType() == TransactionType.INCOME ? t.getAmount() : -t.getAmount()
                )
                .sum();
    }

    public List<Transaction> getAllTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> loaded) {
        this.transactions = loaded;
    }
}
