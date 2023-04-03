/*    */ package org.apache.commons.compress.archivers.zip;
/*    */ 
/*    */ public class UnicodePathExtraField extends AbstractUnicodeExtraField {
/* 40 */   public static final ZipShort UPATH_ID = new ZipShort(28789);
/*    */   
/*    */   public UnicodePathExtraField() {}
/*    */   
/*    */   public UnicodePathExtraField(String text, byte[] bytes, int off, int len) {
/* 56 */     super(text, bytes, off, len);
/*    */   }
/*    */   
/*    */   public UnicodePathExtraField(String name, byte[] bytes) {
/* 67 */     super(name, bytes);
/*    */   }
/*    */   
/*    */   public ZipShort getHeaderId() {
/* 72 */     return UPATH_ID;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\compress\archivers\zip\UnicodePathExtraField.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */