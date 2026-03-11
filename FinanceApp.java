import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Scanner;

public class FinanceApp {

    public static void main(String[] args) {
        FinanceManager manager = new FinanceManager();
        manager.setTransactions(FileService.loadFromFile());

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Aplikacja Finansowa ---");
            System.out.println("1. Dodaj przychód");
            System.out.println("2. Dodaj wydatek");
            System.out.println("3. Pokaż saldo");
            System.out.println("4. Podsumowanie miesięczne");
            System.out.println("5. Filtrowanie po kategorii");
            System.out.println("6. Zapisz i wyjdź");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1, 2 -> {
                    System.out.print("Kwota: ");
                    double amount = scanner.nextDouble();

                    System.out.println("Kategoria:");
                    for (Category c : Category.values()) {
                        System.out.println(c);
                    }
                    Category category = Category.valueOf(scanner.next());

                    TransactionType type = (choice == 1)
                            ? TransactionType.INCOME
                            : TransactionType.EXPENSE;

                    manager.addTransaction(new Transaction(
                            amount,
                            category,
                            LocalDate.now(),
                            type
                    ));
                }
                case 3 -> {
                    System.out.println("Saldo: " + manager.getBalance());
                }
                case 4 -> {
                    System.out.print("Podaj miesiąc (YYYY-MM): ");
                    YearMonth month = YearMonth.parse(scanner.next());
                    System.out.println("Saldo: " + manager.getMonthlyBalance(month));
                }
                case 5 -> {
                    System.out.print("Podaj kategorię: ");
                    Category cat = Category.valueOf(scanner.next());
                    manager.getTransactionsByCategory(cat)
                            .forEach(System.out::println);
                }
                case 6 -> {
                    FileService.saveToFile(manager.getAllTransactions());
                    System.out.println("Zapisano. Koniec programu.");
                    return;
                }
            }
        }
    }
}