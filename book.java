import java.util.Date;

/**
 * فئة مجردة تمثل كتاباً في المكتبة
 * تطبق واجهة Searchable للبحث
 */
public abstract class Book implements Searchable {
    // خصائص الكتاب (مغلقة للتعديل المباشر - مبدأ التغليف)
    private String title;       // عنوان الكتاب
    private String author;      // مؤلف الكتاب
    private String isbn;        // رقم ISBN الفريد
    private boolean isBorrowed; // حالة الإعارة (true إذا كان معاراً)
    private Date publishDate;   // تاريخ النشر

    /**
     * منشئ فئة الكتاب
     * @param title عنوان الكتاب
     * @param author مؤلف الكتاب
     * @param isbn رقم ISBN
     * @param publishDate تاريخ النشر
     */
    public Book(String title, String author, String isbn, Date publishDate) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publishDate = publishDate;
        this.isBorrowed = false; // عند الإنشاء، الكتاب غير معار
    }

    // --- طرق الوصول إلى الخصائص (Getters) ---
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getIsbn() { return isbn; }
    public boolean isBorrowed() { return isBorrowed; }
    public Date getPublishDate() { return publishDate; }

    // --- طرق تعديل الخصائص (Setters) ---
    public void setBorrowed(boolean borrowed) { 
        this.isBorrowed = borrowed; 
    }

    /**
     * تنفيذ واجهة Searchable
     * للبحث في خصائص الكتاب
     */
    @Override
    public boolean matches(String query) {
        // البحث في العنوان، المؤلف ورقم ISBN
        return title.toLowerCase().contains(query.toLowerCase()) || 
               author.toLowerCase().contains(query.toLowerCase()) || 
               isbn.contains(query);
    }

    /**
     * طريقة مجردة يجب تنفيذها في الفئات الفرعية
     * @return نوع الكتاب (ورقي/إلكتروني)
     */
    public abstract String getBookType();

    /**
     * تمثيل نصي للكتاب
     */
    @Override
    public String toString() {
        return String.format("%s [%s] by %s - %s - Published: %s", 
            title, isbn, author, 
            isBorrowed ? "معار" : "متاح",
            publishDate.toString());
    }
}
