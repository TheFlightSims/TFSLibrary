/*    */ package org.apache.commons.codec.binary;
/*    */ 
/*    */ import java.io.InputStream;
/*    */ 
/*    */ public class Base64InputStream extends BaseNCodecInputStream {
/*    */   public Base64InputStream(InputStream in) {
/* 52 */     this(in, false);
/*    */   }
/*    */   
/*    */   public Base64InputStream(InputStream in, boolean doEncode) {
/* 65 */     super(in, new Base64(false), doEncode);
/*    */   }
/*    */   
/*    */   public Base64InputStream(InputStream in, boolean doEncode, int lineLength, byte[] lineSeparator) {
/* 86 */     super(in, new Base64(lineLength, lineSeparator), doEncode);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\codec\binary\Base64InputStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */