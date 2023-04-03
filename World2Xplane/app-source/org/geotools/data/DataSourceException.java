/*    */ package org.geotools.data;
/*    */ 
/*    */ import java.io.IOException;
/*    */ 
/*    */ public class DataSourceException extends IOException {
/*    */   private static final long serialVersionUID = -602847953059978370L;
/*    */   
/*    */   public DataSourceException(String msg) {
/* 41 */     super(msg);
/*    */   }
/*    */   
/*    */   public DataSourceException(Throwable cause) {
/* 50 */     super(cause.getMessage());
/* 51 */     initCause(cause);
/*    */   }
/*    */   
/*    */   public DataSourceException(String msg, Throwable cause) {
/* 61 */     super(msg);
/* 62 */     initCause(cause);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\DataSourceException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */