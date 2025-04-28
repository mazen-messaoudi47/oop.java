/**
 * فئة تمثل كتاباً ورقيًا
 * ترث من الفئة الأساسية Book
 */
public class PaperBook extends Book {
    private int pageCount;  // عدد الصفحات
    private String edition; // الإصدار (الطبعة)

    /**
     * منشئ فئة الكتاب الورقي
     */
    public PaperBook(String title, String author, String isbn, 
                    Date publishDate, int pageCount, String edition) {
        // استدعاء منشئ الفئة الأساسية
        super(title, author, isbn, publishDate);
        this.pageCount = pageCount;
        this.edition = edition;
    }

    // --- طرق الوصول إلى الخصائص ---
    public int getPageCount() { return pageCount; }
    public String getEdition() { return edition; }

    /**
     * تنفيذ الطريقة المجردة من الفئة الأساسية
     * @return نوع الكتاب
     */
    @Override
    public String getBookType() {
        return "كتاب ورقي";
    }

    /**
     * تمثيل نصي للكتاب الورقي
     */
    @Override
    public String toString() {
        return super.toString() + String.format(" - %s - %d صفحة - الطبعة %s", 
            getBookType(), pageCount, edition);
    }
}
