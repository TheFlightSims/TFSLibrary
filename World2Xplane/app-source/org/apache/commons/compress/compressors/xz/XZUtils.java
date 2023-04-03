/*    */ package org.apache.commons.compress.compressors.xz;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import org.apache.commons.compress.compressors.FileNameUtil;
/*    */ 
/*    */ public class XZUtils {
/*    */   private static final FileNameUtil fileNameUtil;
/*    */   
/*    */   static {
/* 35 */     Map<String, String> uncompressSuffix = new HashMap<String, String>();
/* 36 */     uncompressSuffix.put(".txz", ".tar");
/* 37 */     uncompressSuffix.put(".xz", "");
/* 38 */     uncompressSuffix.put("-xz", "");
/* 39 */     fileNameUtil = new FileNameUtil(uncompressSuffix, ".xz");
/*    */   }
/*    */   
/*    */   public static boolean isCompressedFilename(String filename) {
/* 54 */     return fileNameUtil.isCompressedFilename(filename);
/*    */   }
/*    */   
/*    */   public static String getUncompressedFilename(String filename) {
/* 71 */     return fileNameUtil.getUncompressedFilename(filename);
/*    */   }
/*    */   
/*    */   public static String getCompressedFilename(String filename) {
/* 86 */     return fileNameUtil.getCompressedFilename(filename);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\compress\compressors\xz\XZUtils.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */