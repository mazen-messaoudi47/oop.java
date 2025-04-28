import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * فئة الخدمة الرئيسية للمكتبة
 * تحتوي على المنطق الأساسي لإدارة المكتبة
 */
public class LibraryService {
    // قوائم لتخزين البيانات
    private List<Book> books;                   // جميع الكتب
    private List<Borrower> borrowers;           // جميع المستعيرين
    private List<BorrowingProcess> borrowings;  // جميع عمليات الإعارة

    /**
     * منشئ فئة الخدمة
     */
    public LibraryService() {
        this.books = new ArrayList<>();
        this.borrowers = new ArrayList<>();
        this.borrowings = new ArrayList<>();
    }

    // --- عمليات إضافة العناصر ---
    
    /**
     * إضافة كتاب جديد
     * @param book الكتاب المطلوب إضافته
     */
    public void addBook(Book book) {
        books.add(book);
    }

    /**
     * إضافة مستعير جديد
     * @param borrower المستعير المطلوب إضافته
     */
    public void addBorrower(Borrower borrower) {
        borrowers.add(borrower);
    }

    // --- عمليات البحث ---
    
    /**
     * البحث عن كتاب
     * @param query نص البحث
     * @return قائمة بالكتب المطابقة
     */
    public List<Book> searchBooks(String query) {
        List<Book> results = new ArrayList<>();
        for (Book book : books) {
            if (book.matches(query)) {
                results.add(book);
            }
        }
        return results;
    }

    /**
     * البحث عن مستعير
     * @param query نص البحث
     * @return قائمة بالمستعيرين المطابقين
     */
    public List<Borrower> searchBorrowers(String query) {
        List<Borrower> results = new ArrayList<>();
        for (Borrower borrower : borrowers) {
            if (borrower.matches(query)) {
                results.add(borrower);
            }
        }
        return results;
    }

    // --- عمليات الإعارة والإرجاع ---
    
    /**
     * إعارة كتاب
     * @param book الكتاب المطلوب إعارته
     * @param borrower المستعير
     * @param borrowDate تاريخ الإعارة
     * @return true إذا نجحت العملية، false إذا فشلت (الكتاب معار مسبقاً)
     */
    public boolean borrowBook(Book book, Borrower borrower, Date borrowDate) {
        if (book.isBorrowed()) {
            return false; // الكتاب معار مسبقاً
        }
        
        borrower.borrowBook(book);
        BorrowingProcess process = new BorrowingProcess(book, borrower, borrowDate);
        borrowings.add(process);
        return true;
    }

    /**
     * إرجاع كتاب
     * @param book الكتاب المطلوب إرجاعه
     * @param borrower المستعير
     * @param returnDate تاريخ الإرجاع
     * @return true إذا نجحت العملية، false إذا فشلت (الكتاب غير معار لهذا المستعير)
     */
    public boolean returnBook(Book book, Borrower borrower, Date returnDate) {
        for (BorrowingProcess process : borrowings) {
            if (process.getBook().equals(book) && 
                process.getBorrower().equals(borrower) && 
                process.getReturnDate() == null) {
                
                borrower.returnBook(book);
                process.setReturnDate(returnDate);
                return true;
            }
        }
        return false; // لم يتم العثور على عملية إعارة مطابقة
    }

    // --- طرق الحصول على القوائم الكاملة ---
    
    public List<Book> getAllBooks() { return books; }
    public List<Borrower> getAllBorrowers() { return borrowers; }
    public List<BorrowingProcess> getAllBorrowings() { return borrowings; }

    /**
     * الحصول على الكتب المعارة حالياً
     * @return قائمة بالكتب المعارة
     */
    public List<Book> getBorrowedBooks() {
        List<Book> borrowed = new ArrayList<>();
        for (Book book : books) {
            if (book.isBorrowed()) {
                borrowed.add(book);
            }
        }
        return borrowed;
    }

    /**
     * الحصول على الكتب المتاحة للإعارة
     * @return قائمة بالكتب المتاحة
     */
    public List<Book> getAvailableBooks() {
        List<Book> available = new ArrayList<>();
        for (Book book : books) {
            if (!book.isBorrowed()) {
                available.add(book);
            }
        }
return available;
    }
}
