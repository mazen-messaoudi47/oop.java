import java.util.Date;

/**
 * فئة تمثل عملية إعارة كتاب
 */
public class BorrowingProcess {
    private Book book;          // الكتاب المعار
    private Borrower borrower;  // المستعير
    private Date borrowDate;    // تاريخ الإعارة
    private Date returnDate;    // تاريخ الإرجاع (null إذا لم يتم الإرجاع بعد)

    /**
     * منشئ فئة عملية الإعارة
     */
    public BorrowingProcess(Book book, Borrower borrower, Date borrowDate) {
        this.book = book;
        this.borrower = borrower;
        this.borrowDate = borrowDate;
        this.returnDate = null; // عند الإنشاء، لم يتم الإرجاع بعد
    }

    // --- طرق الوصول إلى الخصائص ---
    public Book getBook() { return book; }
    public Borrower getBorrower() { return borrower; }
    public Date getBorrowDate() { return borrowDate; }
    public Date getReturnDate() { return returnDate; }

    /**
     * تسجيل تاريخ إرجاع الكتاب
     * @param returnDate تاريخ الإرجاع
     */
    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    /**
     * حساب مدة الإعارة بالأيام
     * @return عدد أيام الإعارة (0 إذا لم يتم الإرجاع بعد)
     */
    public long getBorrowingDuration() {
        if (returnDate == null) return 0;
        long diff = returnDate.getTime() - borrowDate.getTime();
        return diff / (1000 * 60 * 60 * 24); // تحويل الملي ثانية إلى أيام
    }

    /**
     * تمثيل نصي لعملية الإعارة
     */
    @Override
    public String toString() {
        return String.format("%s قام بإعارة %s في %s%s", 
            borrower.getName(), book.getTitle(), borrowDate,
            returnDate != null ? " وأرجعه في " + returnDate : "");
    }
}
