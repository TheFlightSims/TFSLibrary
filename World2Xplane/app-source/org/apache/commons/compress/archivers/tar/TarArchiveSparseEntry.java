/*    */ package org.apache.commons.compress.archivers.tar;
/*    */ 
/*    */ import java.io.IOException;
/*    */ 
/*    */ public class TarArchiveSparseEntry implements TarConstants {
/*    */   private boolean isExtended;
/*    */   
/*    */   public TarArchiveSparseEntry(byte[] headerBuf) throws IOException {
/* 55 */     int offset = 0;
/* 56 */     offset += 504;
/* 57 */     this.isExtended = TarUtils.parseBoolean(headerBuf, offset);
/*    */   }
/*    */   
/*    */   public boolean isExtended() {
/* 61 */     return this.isExtended;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\compress\archivers\tar\TarArchiveSparseEntry.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */