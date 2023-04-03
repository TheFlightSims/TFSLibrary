/*    */ package org.postgresql.fastpath;
/*    */ 
/*    */ import java.sql.SQLException;
/*    */ import org.postgresql.core.ParameterList;
/*    */ 
/*    */ public class FastpathArg {
/*    */   private final byte[] bytes;
/*    */   
/*    */   private final int bytesStart;
/*    */   
/*    */   private final int bytesLength;
/*    */   
/*    */   public FastpathArg(int value) {
/* 39 */     this.bytes = new byte[4];
/* 40 */     this.bytes[3] = (byte)value;
/* 41 */     this.bytes[2] = (byte)(value >> 8);
/* 42 */     this.bytes[1] = (byte)(value >> 16);
/* 43 */     this.bytes[0] = (byte)(value >> 24);
/* 44 */     this.bytesStart = 0;
/* 45 */     this.bytesLength = 4;
/*    */   }
/*    */   
/*    */   public FastpathArg(byte[] bytes) {
/* 54 */     this(bytes, 0, bytes.length);
/*    */   }
/*    */   
/*    */   public FastpathArg(byte[] buf, int off, int len) {
/* 65 */     this.bytes = buf;
/* 66 */     this.bytesStart = off;
/* 67 */     this.bytesLength = len;
/*    */   }
/*    */   
/*    */   public FastpathArg(String s) {
/* 76 */     this(s.getBytes());
/*    */   }
/*    */   
/*    */   void populateParameter(ParameterList params, int index) throws SQLException {
/* 80 */     if (this.bytes == null) {
/* 81 */       params.setNull(index, 0);
/*    */     } else {
/* 83 */       params.setBytea(index, this.bytes, this.bytesStart, this.bytesLength);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\fastpath\FastpathArg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */