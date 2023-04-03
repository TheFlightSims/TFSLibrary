/*    */ package org.codehaus.stax2.typed;
/*    */ 
/*    */ public final class Base64Variants {
/*    */   static final String STD_BASE64_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
/*    */   
/* 36 */   public static final Base64Variant MIME = new Base64Variant("MIME", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/", true, '=', 76);
/*    */   
/* 47 */   public static final Base64Variant MIME_NO_LINEFEEDS = new Base64Variant(MIME, "MIME-NO-LINEFEEDS", 2147483647);
/*    */   
/* 54 */   public static final Base64Variant PEM = new Base64Variant(MIME, "PEM", true, '=', 64);
/*    */   
/*    */   public static final Base64Variant MODIFIED_FOR_URL;
/*    */   
/*    */   static {
/* 69 */     StringBuffer sb = new StringBuffer("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/");
/* 71 */     sb.setCharAt(sb.indexOf("+"), '-');
/* 72 */     sb.setCharAt(sb.indexOf("/"), '_');
/* 76 */     MODIFIED_FOR_URL = new Base64Variant("MODIFIED-FOR-URL", sb.toString(), false, false, 2147483647);
/*    */   }
/*    */   
/*    */   public static Base64Variant getDefaultVariant() {
/* 84 */     return MIME;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\codehaus\stax2\typed\Base64Variants.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */