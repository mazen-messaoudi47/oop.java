/**
 * واجهة قابلة للبحث
 * تحدد سلوك البحث المشترك بين الكتب والمستعيرين
 */
public interface Searchable {
    /**
     * يتحقق إذا كان العنصر يطابق استعلام البحث
     * @param query نص البحث
     * @return true إذا كان هناك تطابق، false إذا لم يكن هناك تطابق
     */
    boolean matches(String query);
}
