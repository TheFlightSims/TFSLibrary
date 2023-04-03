/*    */ package org.apache.commons.compress.compressors.bzip2;
/*    */ 
/*    */ import java.util.LinkedHashMap;
/*    */ import java.util.Map;
/*    */ import org.apache.commons.compress.compressors.FileNameUtil;
/*    */ 
/*    */ public abstract class BZip2Utils {
/*    */   private static final FileNameUtil fileNameUtil;
/*    */   
/*    */   static {
/* 35 */     Map<String, String> uncompressSuffix = new LinkedHashMap<String, String>();
/* 39 */     uncompressSuffix.put(".tar.bz2", ".tar");
/* 40 */     uncompressSuffix.put(".tbz2", ".tar");
/* 41 */     uncompressSuffix.put(".tbz", ".tar");
/* 42 */     uncompressSuffix.put(".bz2", "");
/* 43 */     uncompressSuffix.put(".bz", "");
/* 44 */     fileNameUtil = new FileNameUtil(uncompressSuffix, ".bz2");
/*    */   }
/*    */   
/*    */   public static boolean isCompressedFilename(String filename) {
/* 59 */     return fileNameUtil.isCompressedFilename(filename);
/*    */   }
/*    */   
/*    */   public static String getUncompressedFilename(String filename) {
/* 76 */     return fileNameUtil.getUncompressedFilename(filename);
/*    */   }
/*    */   
/*    */   public static String getCompressedFilename(String filename) {
/* 90 */     return fileNameUtil.getCompressedFilename(filename);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\compress\compressors\bzip2\BZip2Utils.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */