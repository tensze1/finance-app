import java.time.LocalDate;

public class Transaction {
    private double amount;
    private Category category;
    private LocalDate date;
    private TransactionType type;

    public Transaction(double amount, Category category, LocalDate date, TransactionType type) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Kwota musi być większa od zera.");
        }
        this.amount = amount;
        this.category = category;
        this.date = date;
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public Category getCategory() {
        return category;
    }

    public LocalDate getDate() {
        return date;
    }

    public TransactionType getType() {
        return type;
    }

    @Override
    public String toString() {
        return date + " | " + type + " | " + category + " | " + amount;
    }
}
