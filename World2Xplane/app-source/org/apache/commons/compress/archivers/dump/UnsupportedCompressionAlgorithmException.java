/*    */ package org.apache.commons.compress.archivers.dump;
/*    */ 
/*    */ public class UnsupportedCompressionAlgorithmException extends DumpArchiveException {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   public UnsupportedCompressionAlgorithmException() {
/* 31 */     super("this file uses an unsupported compression algorithm.");
/*    */   }
/*    */   
/*    */   public UnsupportedCompressionAlgorithmException(String alg) {
/* 35 */     super("this file uses an unsupported compression algorithm: " + alg + ".");
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\compress\archivers\dump\UnsupportedCompressionAlgorithmException.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */