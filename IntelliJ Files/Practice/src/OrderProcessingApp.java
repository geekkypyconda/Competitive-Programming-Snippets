import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

class Order {
    String orderId;
    String customerName;
    String productName;
    int quantity;
    double unitPrice;
    LocalDate orderDate;

    double lineTotal;
    double discount;
    double netTotal;

    public Order(String orderId, String customerName, String productName,
                 int quantity, double unitPrice, LocalDate orderDate) {

        this.orderId = orderId;
        this.customerName = customerName;
        this.productName = productName;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.orderDate = orderDate;

        calculateTotals();
    }

    private void calculateTotals() {
        lineTotal = quantity * unitPrice;

        if (lineTotal > 500) {
            discount = 0.10 * lineTotal;
        } else {
            discount = 0;
        }

        netTotal = lineTotal - discount;
    }
}

class CustomerSummary {
    String customerName;
    int orderCount = 0;
    int totalItems = 0;
    double grossTotal = 0;
    double discountTotal = 0;
    double netTotal = 0;

    public CustomerSummary(String name) {
        this.customerName = name;
    }

    public void addOrder(Order o) {
        orderCount++;
        totalItems += o.quantity;
        grossTotal += o.lineTotal;
        discountTotal += o.discount;
        netTotal += o.netTotal;
    }
}

public class OrderProcessingApp {

    public static void processOrders(String inputFile, String outputFile, String errorFile) {

        Map<String, CustomerSummary> summaryMap = new HashMap<>();
        Set<String> seenOrderIds = new HashSet<>();

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile));
             BufferedWriter errorWriter = new BufferedWriter(new FileWriter(errorFile))) {

            String line;
            boolean isEmpty = true;

            while ((line = br.readLine()) != null) {
                isEmpty = false;

                // Skip empty lines & comments
                if (line.trim().isEmpty() || line.startsWith("#")) continue;

                try {
                    Order order = processLine(line);

                    //  Duplicate OrderID check
                    if (seenOrderIds.contains(order.orderId)) {
                        throw new Exception("Duplicate OrderID");
                    }
                    seenOrderIds.add(order.orderId);

                    // Add to summary
                    summaryMap
                            .computeIfAbsent(order.customerName, CustomerSummary::new)
                            .addOrder(order);

                } catch (Exception e) {
                    errorWriter.write("ERROR: " + line + " -> " + e.getMessage());
                    errorWriter.newLine();
                }
            }

            if (isEmpty) {
                System.out.println("Input file is empty.");
                return;
            }

            writeOutput(summaryMap, outputFile);

            System.out.println("Processing completed for: " + inputFile);

        } catch (IOException e) {
            System.out.println("File error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        System.out.println("Working Directory: " + System.getProperty("user.dir"));
        String inputFile = "src/input.txt";
        String outputFile = "src/output.txt";
        String errorFile = "src/error.txt";

        processOrders(inputFile, outputFile, errorFile);
        System.out.println("Processing done for Normal cases, please check error.txt and output.txt");

        String inputFile2 = "src/input2.txt";
        String outputFile2 = "src/output2.txt";
        String errorFile2= "src/error2.txt";

        processOrders(inputFile2, outputFile2, errorFile2);
        System.out.println("Processing done for Difficult cases, please check error2.txt and output2.txt");

        String inputFile3 = "src/input3.txt";
        String outputFile3 = "src/output3.txt";
        String errorFile3= "src/error3.txt";

        processOrders(inputFile3, outputFile3, errorFile3);
        System.out.println("Processing done for edge cases, please check error3.txt and output3.txt");
    }

    private static Order processLine(String line) throws Exception {

        String[] parts = line.split("\\|");

        if (parts.length != 6) {
            throw new Exception("Invalid number of fields");
        }

        // Trim all fields
        for (int i = 0; i < parts.length; i++) {
            parts[i] = parts[i].trim();
        }

        // ---------------- OrderID Validation ----------------
        String orderId = parts[0];

        if (!orderId.startsWith("ORD")) {
            throw new Exception("Invalid OrderID format (must start with ORD)");
        }

        String numberPart = orderId.substring(3);

        if (numberPart.isEmpty()) {
            throw new Exception("Invalid OrderID format (missing number)");
        }

        for (int i = 0; i < numberPart.length(); i++) {
            if (!Character.isDigit(numberPart.charAt(i))) {
                throw new Exception("Invalid OrderID format (non-digit found)");
            }
        }

        // ---------------- Customer Name ----------------
        String customerName = parts[1].trim();

        // Normalize multiple spaces → single space
        customerName = customerName.replaceAll("\\s+", " ");

        if (customerName.isEmpty()) {
            throw new Exception("Empty customer name");
        }

        // ---------------- Product Name ----------------
        String productName = parts[2];
        if (productName.isEmpty()) {
            throw new Exception("Empty product name");
        }

        // ---------------- Quantity ----------------
        int quantity;
        try {
            quantity = Integer.parseInt(parts[3]);
            if (quantity == 0) {
                throw new Exception("Zero quantity");
            } else if(quantity < 0){
                throw new Exception("Negative quantity");
            } else if(quantity > 1000){
                throw new Exception("Selected quantity not available");
            }
        } catch (NumberFormatException e) {
            throw new Exception("Invalid quantity");
        }

        // ---------------- Unit Price ----------------
        double unitPrice;
        try {
            unitPrice = Double.parseDouble(parts[4]);
            if (unitPrice < 0) {
                throw new Exception("Negative unit price");
            }
        } catch (NumberFormatException e) {
            throw new Exception("Invalid unit price");
        }

        // ---------------- Date ----------------
        LocalDate orderDate;
        try {
            orderDate = LocalDate.parse(parts[5]);
        } catch (Exception e) {
            throw new Exception("Invalid date format");
        }

        return new Order(orderId, customerName, productName, quantity, unitPrice, orderDate);
    }

    private static void writeOutput(Map<String, CustomerSummary> map, String outputFile) {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {

            String header = String.format(
                    "%-15s %-10s %-12s %-15s %-15s %-15s",
                    "Customer", "Orders", "Items", "Gross Total", "Discount", "Net Total"
            );

            bw.write(header);
            bw.newLine();
            bw.write("-------------------------------------------------------------------------------------");
            bw.newLine();

            double grandGross = 0, grandDiscount = 0, grandNet = 0;

            for (CustomerSummary cs : map.values()) {

                bw.write(String.format(
                        "%-15s %-10d %-12d %-15.2f %-15.2f %-15.2f",
                        cs.customerName,
                        cs.orderCount,
                        cs.totalItems,
                        cs.grossTotal,
                        cs.discountTotal,
                        cs.netTotal
                ));
                bw.newLine();

                grandGross += cs.grossTotal;
                grandDiscount += cs.discountTotal;
                grandNet += cs.netTotal;
            }

            bw.write("-------------------------------------------------------------------------------------");
            bw.newLine();

            bw.write(String.format(
                    "%-15s %-10s %-12s %-15.2f %-15.2f %-15.2f",
                    "GRAND TOTAL", "", "",
                    grandGross,
                    grandDiscount,
                    grandNet
            ));

        } catch (IOException e) {
            System.out.println("Error writing output: " + e.getMessage());
        }
    }
}