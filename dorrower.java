import java.util.ArrayList;
import java.util.List;

/**
 * فئة تمثل مستعيراً للكتب
 * تطبق واجهة Searchable للبحث
 */
public class Borrower implements Searchable {
    private String id;          // رقم تعريف المستعير
    private String name;        // اسم المستعير
    private String email;       // البريد الإلكتروني
    private String phone;       // رقم الهاتف
    private List<Book> borrowedBooks; // قائمة الكتب المعارة

    /**
     * منشئ فئة المستعير
     */
    public Borrower(String id, String name, String email, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.borrowedBooks = new ArrayList<>();
    }

    // --- طرق الوصول إلى الخصائص ---
    public String getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public List<Book> getBorrowedBooks() { return borrowedBooks; }

    /**
     * إضافة كتاب إلى قائمة الكتب المعارة
     * @param book الكتاب المطلوب إضافته
     */
    public void borrowBook(Book book) {
        if (!book.isBorrowed()) {
            borrowedBooks.add(book);
            book.setBorrowed(true);
        }
    }

    /**
     * إزالة كتاب من قائمة الكتب المعارة
     * @param book الكتاب المطلوب إزالته
     * @return true إذا تمت الإزالة بنجاح، false إذا لم يكن الكتاب معاراً
     */
    public boolean returnBook(Book book) {
        boolean removed = borrowedBooks.remove(book);
        if (removed) {
            book.setBorrowed(false);
        }
        return removed;
    }

    /**
     * تنفيذ واجهة Searchable
     * للبحث في خصائص المستعير
     */
    @Override
    public boolean matches(String query) {
        return name.toLowerCase().contains(query.toLowerCase()) || 
               id.contains(query) || 
               email.contains(query) || 
               phone.contains(query);
    }

    /**
     * تمثيل نصي للمستعير
     */
    @Override
    public String toString() {
        return String.format("%s (ID: %s) - %s - %s - الكتب المعارة: %d", 
            name, id, email, phone, borrowedBooks.size());
    }
}
