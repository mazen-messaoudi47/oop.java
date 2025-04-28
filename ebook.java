/**
 * فئة تمثل كتاباً إلكترونياً
 * ترث من الفئة الأساسية Book
 */
public class EBook extends Book {
    private String fileFormat;  // صيغة الملف (PDF, EPUB...)
    private double fileSize;    // حجم الملف بالميجابايت
    private String downloadLink; // رابط التحميل

    /**
     * منشئ فئة الكتاب الإلكتروني
     */
    public EBook(String title, String author, String isbn, 
                Date publishDate, String fileFormat, 
                double fileSize, String downloadLink) {
        super(title, author, isbn, publishDate);
        this.fileFormat = fileFormat;
        this.fileSize = fileSize;
        this.downloadLink = downloadLink;
    }

    // --- طرق الوصول إلى الخصائص ---
    public String getFileFormat() { return fileFormat; }
    public double getFileSize() { return fileSize; }
    public String getDownloadLink() { return downloadLink; }

    /**
     * تنفيذ الطريقة المجردة من الفئة الأساسية
     * @return نوع الكتاب
     */
    @Override
    public String getBookType() {
        return "كتاب إلكتروني";
    }

    /**
     * تمثيل نصي للكتاب الإلكتروني
     */
    @Override
    public String toString() {
        return super.toString() + String.format(" - %s - %.2f MB - %s", 
            getBookType(), fileSize, downloadLink);
    }
}
