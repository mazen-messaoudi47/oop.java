import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * الفئة الرئيسية لتشغيل تطبيق المكتبة
 * تحتوي على واجهة المستخدم النصية
 */
public class MainApp {
    private static LibraryService library = new LibraryService();
    private static Scanner scanner = new Scanner(System.in);
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static void main(String[] args) {
        System.out.println("نظام إدارة المكتبة الرقمية");
        System.out.println("=========================");

        boolean running = true;
        while (running) {
            printMainMenu();
            int choice = readIntInput("اختر خياراً: ");

            switch (choice) {
                case 1: addBookMenu(); break;
                case 2: addBorrowerMenu(); break;
                case 3: borrowBookMenu(); break;
                case 4: returnBookMenu(); break;
                case 5: searchBooksMenu(); break;
                case 6: searchBorrowersMenu(); break;
                case 7: displayBorrowedBooksMenu(); break;
                case 8: displayAllBooksMenu(); break;
                case 9: displayAllBorrowersMenu(); break;
                case 0: running = false; break;
                default: System.out.println("خيار غير صحيح، حاول مرة أخرى");
            }
        }

        System.out.println("شكراً لاستخدامك نظام إدارة المكتبة. إلى اللقاء!");
    }

    // --- طرق عرض القوائم ---
    
    private static void printMainMenu() {
        System.out.println("\nالقائمة الرئيسية:");
        System.out.println("1. إضافة كتاب جديد");
        System.out.println("2. إضافة مستعير جديد");
        System.out.println("3. إعارة كتاب");
        System.out.println("4. إرجاع كتاب");
        System.out.println("5. البحث عن كتب");
        System.out.println("6. البحث عن مستعيرين");
        System.out.println("7. عرض الكتب المعارة");
        System.out.println("8. عرض جميع الكتب");
        System.out.println("9. عرض جميع المستعيرين");
        System.out.println("0. خروج");
    }

    // --- طرق معالجة الإدخال ---
    
    private static int readIntInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.println("الرجاء إدخال رقم صحيح!");
            scanner.next();
            System.out.print(prompt);
        }
        int input = scanner.nextInt();
        scanner.nextLine(); // تنظيف buffer
        return input;
    }

    private static Date readDateInput(String prompt) {
        System.out.print(prompt + " (yyyy-MM-dd): ");
        while (true) {
            try {
                return dateFormat.parse(scanner.nextLine());
            } catch (ParseException e) {
                System.out.print("تاريخ غير صحيح، حاول مرة أخرى (yyyy-MM-dd): ");
            }
        }
    }

    // --- طرق القوائم الفرعية ---
    
    private static void addBookMenu() {
        System.out.println("\nإضافة كتاب جديد:");
        
        System.out.print("نوع الكتاب (1- ورقي / 2- إلكتروني): ");
        int type = readIntInput("");
        
        System.out.print("العنوان: ");
        String title = scanner.nextLine();
        
        System.out.print("المؤلف: ");
        String author = scanner.nextLine();
        
        System.out.print("رقم ISBN: ");
        String isbn = scanner.nextLine();
        
        Date publishDate = readDateInput("تاريخ النشر");

        if (type == 1) {
            // كتاب ورقي
            int pages = readIntInput("عدد الصفحات: ");
            System.out.print("الطبعة: ");
            String edition = scanner.nextLine();
            
            PaperBook book = new PaperBook(title, author, isbn, publishDate, pages, edition);
            library.addBook(book);
            System.out.println("تمت إضافة الكتاب الورقي بنجاح!");
        } else {
            // كتاب إلكتروني
            System.out.print("صيغة الملف: ");
            String format = scanner.nextLine();
            
            double size = readIntInput("حجم الملف (MB): ");
System.out.print("رابط التحميل: ");
            String link = scanner.nextLine();
            
            EBook book = new EBook(title, author, isbn, publishDate, format, size, link);
            library.addBook(book);
            System.out.println("تمت إضافة الكتاب الإلكتروني بنجاح!");
        }
    }

    private static void addBorrowerMenu() {
        System.out.println("\nإضافة مستعير جديد:");
        
        System.out.print("رقم الهوية: ");
        String id = scanner.nextLine();
        
        System.out.print("الاسم: ");
        String name = scanner.nextLine();
        
        System.out.print("البريد الإلكتروني: ");
        String email = scanner.nextLine();
        
        System.out.print("رقم الهاتف: ");
        String phone = scanner.nextLine();
        
        Borrower borrower = new Borrower(id, name, email, phone);
        library.addBorrower(borrower);
        System.out.println("تمت إضافة المستعير بنجاح!");
    }

    private static void borrowBookMenu() {
        System.out.println("\nإعارة كتاب:");
        
        List<Book> availableBooks = library.getAvailableBooks();
        if (availableBooks.isEmpty()) {
            System.out.println("لا توجد كتب متاحة للإعارة حالياً.");
            return;
        }
        
        System.out.println("الكتب المتاحة:");
        for (int i = 0; i < availableBooks.size(); i++) {
            System.out.printf("%d. %s\n", i+1, availableBooks.get(i));
        }
        
        int bookIndex = readIntInput("اختر رقم الكتاب: ") - 1;
        if (bookIndex < 0 || bookIndex >= availableBooks.size()) {
            System.out.println("اختيار غير صحيح!");
            return;
        }
        
        List<Borrower> borrowers = library.getAllBorrowers();
        if (borrowers.isEmpty()) {
            System.out.println("لا يوجد مستعيرون مسجلون.");
            return;
        }
        
        System.out.println("المستعيرون:");
        for (int i = 0; i < borrowers.size(); i++) {
            System.out.printf("%d. %s\n", i+1, borrowers.get(i));
        }
        
        int borrowerIndex = readIntInput("اختر رقم المستعير: ") - 1;
        if (borrowerIndex < 0 || borrowerIndex >= borrowers.size()) {
            System.out.println("اختيار غير صحيح!");
            return;
        }
        
        Book selectedBook = availableBooks.get(bookIndex);
        Borrower selectedBorrower = borrowers.get(borrowerIndex);
        Date borrowDate = new Date(); // التاريخ الحالي
        
        boolean success = library.borrowBook(selectedBook, selectedBorrower, borrowDate);
        if (success) {
            System.out.println("تمت إعارة الكتاب بنجاح!");
        } else {
            System.out.println("فشلت عملية الإعارة. الكتاب قد يكون معاراً بالفعل.");
        }
    }

    private static void returnBookMenu() {
        System.out.println("\nإرجاع كتاب:");
        
        List<Borrower> borrowersWithBooks = new ArrayList<>();
        for (Borrower b : library.getAllBorrowers()) {
            if (!b.getBorrowedBooks().isEmpty()) {
                borrowersWithBooks.add(b);
            }
        }
        
        if (borrowersWithBooks.isEmpty()) {
            System.out.println("لا يوجد مستعيرون لديهم كتب معارة حالياً.");
            return;
        }
        
        System.out.println("المستعيرون الذين لديهم كتب معارة:");
        for (int i = 0; i < borrowersWithBooks.size(); i++) {
            System.out.printf("%d. %s\n", i+1, borrowersWithBooks.get(i));
        }
        
        int borrowerIndex = readIntInput("اختر رقم المستعير: ") - 1;
        if (borrowerIndex < 0 || borrowerIndex >= borrowersWithBooks.size()) {
            System.out.println("اختيار غير صحيح!");
            return;
        }
        
        Borrower selectedBorrower = borrowersWithBooks.get(borrowerIndex);
        List<Book> borrowedBooks = selectedBorrower.getBorrowedBooks();
        
        System.out.println("الكتب المعارة لهذا المستعير:");
        for (int i = 0; i < borrowedBooks.size(); i++) {
            System.out.printf("%d. %s\n", i+1, borrowedBooks.get(i));
        }
