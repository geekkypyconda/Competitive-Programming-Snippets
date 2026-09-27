import java.util.*;

// -------------------- Exceptions --------------------
class BookNotFoundException extends Exception {
    public BookNotFoundException(String msg) { super(msg); }
}

class MemberNotFoundException extends Exception {
    public MemberNotFoundException(String msg) { super(msg); }
}

class CheckoutException extends Exception {

    public CheckoutException(String msg) {
        super(msg);
    }
}

// -------------------- Book --------------------
class Book {
    String isbn, title, author;
    boolean isAvailable;

    public Book(String isbn, String title, String author) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;

        this.isAvailable = true;
    }
}

// -------------------- BorrowRecord --------------------
class BorrowRecord {
    Book book;
    int checkoutDay;
    int dueDay;
    Integer returnDay;

    public BorrowRecord(Book book, int currentDay) {
        this.book = book;
        this.checkoutDay = currentDay;
        this.dueDay = currentDay + 14;
        this.returnDay = null;
    }
}

// -------------------- Member --------------------
class Member {
    String memberId, name;
    List<BorrowRecord> borrowedBooks;
    List<BorrowRecord> borrowingHistory;
    double fineBalance;

    public Member(String id, String name) {
        this.memberId = id;
        this.name = name;
        this.fineBalance = 0.0;

        this.borrowedBooks = new ArrayList<>();
        this.borrowingHistory = new ArrayList<>();
    }
}

// -------------------- Library --------------------
class Library {
    Map<String, Book> books = new HashMap<>();
    Map<String, Member> members = new HashMap<>();
    int currentDay = 0;

    public void nextDay(int days) {
        currentDay += days;
    }

    public void addBook(Book b) {
        books.put(b.isbn, b);
    }

    public void registerMember(Member m) {
        members.put(m.memberId, m);
    }

    public void checkoutBook(String memberId, String isbn)
            throws BookNotFoundException, MemberNotFoundException, CheckoutException {

        if (!books.containsKey(isbn)) throw new BookNotFoundException("Book not found");
        if (!members.containsKey(memberId)) throw new MemberNotFoundException("Member not found");

        Book book = books.get(isbn);
        Member m = members.get(memberId);


        if (!book.isAvailable) throw new CheckoutException("Book not available");
        if (m.borrowedBooks.size() >= 3) throw new CheckoutException("Max 3 books allowed");
        if (m.fineBalance > 10) throw new CheckoutException("Fine > $10");


        BorrowRecord r = new BorrowRecord(book, currentDay);
        m.borrowedBooks.add(r);
        m.borrowingHistory.add(r);
        book.isAvailable = false;

        System.out.println("Member: " + m.name + ", Checked out, Book: " + book.title + " on day " + currentDay);
    }

    public void returnBook(String memberId, String isbn)
            throws BookNotFoundException, MemberNotFoundException {

        if (!books.containsKey(isbn)) throw new BookNotFoundException("Book not found");
        if (!members.containsKey(memberId)) throw new MemberNotFoundException("Member not found");

        Member m = members.get(memberId);
        Book book = books.get(isbn);

        BorrowRecord found = null;
        for (BorrowRecord r : m.borrowedBooks) {
            if (r.book.isbn.equals(isbn)) {
                found = r;
                break;
            }
        }

        if (found == null) {
            System.out.println("Book not borrowed by member");
            return;
        }

        found.returnDay = currentDay;

        int overdue = currentDay - found.dueDay;
        if (overdue > 0) {
            double fine = overdue * 0.5;
            m.fineBalance += fine;
            System.out.println("Fine incurred: $" + fine);
        }

        m.borrowedBooks.remove(found);
        book.isAvailable = true;

        System.out.println("Member: " + m.name + ", Returned, Book: " + book.title + " on day " + currentDay);
    }

    public double calculateFine(String memberId)
            throws MemberNotFoundException {

        if (!members.containsKey(memberId))
            throw new MemberNotFoundException("Member not found");

        return members.get(memberId).fineBalance;
    }

    // FIXED: now returns list
    public List<Book> getAvailableBooks() {
        List<Book> res = new ArrayList<>();
        for (Book b : books.values()) {
            if (b.isAvailable) res.add(b);
        }
        return res;
    }

    // FIXED: now returns list
    public void getMemberBorrowingHistory(String memberId)
            throws MemberNotFoundException {

        if (!members.containsKey(memberId)) {
            throw new MemberNotFoundException("Member not found");
        }

        Member m = members.get(memberId);

        System.out.println("\n========== BORROWING HISTORY ==========");
        System.out.println("Member ID   : " + m.memberId);
        System.out.println("Member Name : " + m.name);
        System.out.println("Current Day : " + currentDay);
        System.out.println("----------------------------------------");

        if (m.borrowingHistory.isEmpty()) {
            System.out.println("No borrowing history found.");
            return;
        }

        System.out.printf("%-15s %-10s %-10s %-10s %-12s %-10s\n",
                "Book", "Checkout", "Due", "Return", "Status", "Fine");

        System.out.println("---------------------------------------------------------------------");

        for (BorrowRecord r : m.borrowingHistory) {

            String status;
            int fineDays = 0;

            if (r.returnDay == null) {
                if (currentDay > r.dueDay) {
                    status = "OVERDUE";
                    fineDays = currentDay - r.dueDay;
                } else {
                    status = "ACTIVE";
                }
            } else {
                if (r.returnDay > r.dueDay) {
                    status = "LATE";
                    fineDays = r.returnDay - r.dueDay;
                } else {
                    status = "RETURNED";
                }
            }

            double fine = fineDays * 0.5;

            System.out.printf("%-15s %-10d %-10d %-10s %-12s $%-10.2f\n",
                    r.book.title,
                    r.checkoutDay,
                    r.dueDay,
                    (r.returnDay == null ? "-" : r.returnDay),
                    status,
                    fine
            );
        }

        System.out.println("=========================================\n");
    }
}

// -------------------- Main --------------------
public class LibrarySystem {

    public static void runTests() {
        Library lib = new Library();

        // Add Books
        lib.addBook(new Book("101", "Java", "James"));
        lib.addBook(new Book("102", "DSA", "Mark"));
        lib.addBook(new Book("103", "OS", "Silb"));
        lib.addBook(new Book("104", "CN", "Tan"));

        // Register Members
        lib.registerMember(new Member("M1", "Alice"));
        lib.registerMember(new Member("M2", "Bob"));

        try {
            System.out.println("\n--- Test 1: Normal Checkout ---");
            lib.checkoutBook("M1", "101");
            lib.checkoutBook("M1", "102");

            System.out.println("\n--- Test 2: Member Not Found ---");
            try { lib.checkoutBook("X", "101"); }
            catch (Exception e) { System.out.println(e.getMessage()); }

            System.out.println("\n--- Test 3: Book Not Found ---");
            try { lib.checkoutBook("M1", "999"); }
            catch (Exception e) { System.out.println(e.getMessage()); }

            System.out.println("\n--- Test 4: Borrow Limit ---");
            lib.checkoutBook("M1", "103");
            try { lib.checkoutBook("M1", "104"); }
            catch (Exception e) { System.out.println(e.getMessage()); }

            System.out.println("\n--- Test 5: Book Already Borrowed ---");
            try { lib.checkoutBook("M2", "101"); }
            catch (Exception e) { System.out.println(e.getMessage()); }

            System.out.println("\n--- Test 6: Return Book ---");
            lib.returnBook("M1", "101");

            System.out.println("\n--- Test 7: Return Book Not Borrowed ---");
            lib.returnBook("M1", "101");

            System.out.println("\n--- Test 8: Available Books ---");
            for (Book b : lib.getAvailableBooks()) {
                System.out.println(b.title);
            }

            System.out.println("\n--- Test 9: Overdue Fine ---");
            lib.nextDay(20); // simulate time
            lib.returnBook("M1", "102");

            System.out.println("\n--- Test 10: Fine Check ---");
            System.out.println("Fine: $" + lib.calculateFine("M1"));

            System.out.println("\n--- Test 11: Borrow Blocked Due to Fine ---");
            lib.members.get("M1").fineBalance = 11;
            try { lib.checkoutBook("M1", "104"); }
            catch (Exception e) { System.out.println(e.getMessage()); }

            System.out.println("\n--- Test 12: Boundary Fine = 10 ---");
            lib.members.get("M1").fineBalance = 10;
            lib.checkoutBook("M1", "104");

            System.out.println("\n--- Test 13: Borrowing History ---");
            lib.getMemberBorrowingHistory("M1");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void runComplexScenarioTests() {

        Library lib = new Library();

        // ---------------- Books ----------------
        lib.addBook(new Book("B1", "Java", "James"));
        lib.addBook(new Book("B2", "DSA", "Mark"));
        lib.addBook(new Book("B3", "OS", "Silb"));
        lib.addBook(new Book("B4", "CN", "Tan"));
        lib.addBook(new Book("B5", "DBMS", "Navathe"));
        lib.addBook(new Book("B6", "AI", "Russell"));

        // ---------------- Members ----------------
        lib.registerMember(new Member("M1", "Alice"));
        lib.registerMember(new Member("M2", "Bob"));
        lib.registerMember(new Member("M3", "Charlie")); // idle
        lib.registerMember(new Member("M4", "David"));

        try {
            System.out.println("\n========= COMPLEX TEST SCENARIO =========");

            // Day 0
            System.out.println("\n--- Day 0: Initial Borrow ---");
            lib.checkoutBook("M1", "B1");
            lib.checkoutBook("M1", "B2");

            lib.checkoutBook("M2", "B3");

            lib.checkoutBook("M4", "B4");
            lib.checkoutBook("M4", "B5");

            // ---------------- Advance Time ----------------
            lib.nextDay(10);
            System.out.println("\n--- Day 10 ---");

            // On-time return
            lib.returnBook("M1", "B1"); // before due

            // M2 borrows another
            lib.checkoutBook("M2", "B6");

            // ---------------- Advance Time ----------------
            lib.nextDay(10); // now day 20
            System.out.println("\n--- Day 20 ---");

            // Late return
            lib.returnBook("M1", "B2"); // overdue

            // M2 returns one late, one still active
            lib.returnBook("M2", "B3"); // overdue

            // M4 does nothing → becomes overdue later

            // ---------------- Advance Time ----------------
            lib.nextDay(10); // day 30
            System.out.println("\n--- Day 30 ---");

            // M4 returns both late
            lib.returnBook("M4", "B4");
            lib.returnBook("M4", "B5");

            try{
                lib.checkoutBook("m10","B11");
            } catch (CheckoutException | MemberNotFoundException | BookNotFoundException E){
                System.out.println(E.getMessage());
            }

            try{
                lib.checkoutBook("M4", "B3");
            } catch (Exception e){
                System.out.println(e.getMessage());
            }


            // M2 still holding B6 → overdue but not returned

            // ---------------- Fine Blocking ----------------
            System.out.println("\n--- Fine Blocking Test ---");
            lib.members.get("M4").fineBalance = 12; // force block

            try {
                lib.checkoutBook("M4", "B1");
            } catch (Exception e) {
                System.out.println("Expected Block: " + e.getMessage());
            }

            // ---------------- Idle Member ----------------
            System.out.println("\n--- Idle Member (M3) ---");
            lib.getMemberBorrowingHistory("M3");

            // ---------------- Final State ----------------
            System.out.println("\n--- Final Histories ---");

            lib.getMemberBorrowingHistory("M1");
            lib.getMemberBorrowingHistory("M2");
            lib.getMemberBorrowingHistory("M4");

            System.out.println("\n--- Available Books ---");
            for (Book b : lib.getAvailableBooks()) {
                System.out.println(b.title);
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        runTests();

        runComplexScenarioTests();
    }
}