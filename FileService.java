import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FileService {

    private static final String FILE_NAME = "transactions.csv";

    public static void saveToFile(List<Transaction> transactions) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Transaction t : transactions) {
                writer.println(
                        t.getAmount() + "," +
                        t.getCategory() + "," +
                        t.getDate() + "," +
                        t.getType()
                );
            }
        } catch (IOException e) {
            System.out.println("Błąd zapisu do pliku.");
        }
    }

    public static List<Transaction> loadFromFile() {
        List<Transaction> transactions = new ArrayList<>();
        File file = new File(FILE_NAME);

        if (!file.exists()) return transactions;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                double amount = Double.parseDouble(data[0]);
                Category category = Category.valueOf(data[1]);
                LocalDate date = LocalDate.parse(data[2]);
                TransactionType type = TransactionType.valueOf(data[3]);

                transactions.add(new Transaction(amount, category, date, type));
            }
        } catch (Exception e) {
            System.out.println("Błąd odczytu pliku.");
        }
        return transactions;
    }
}
