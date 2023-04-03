/*    */ package org.geotools.metadata.sql;
/*    */ 
/*    */ public class MetadataException extends RuntimeException {
/*    */   private static final long serialVersionUID = -7156617726114815455L;
/*    */   
/*    */   public MetadataException(String message) {
/* 45 */     super(message);
/*    */   }
/*    */   
/*    */   public MetadataException(String message, Exception cause) {
/* 55 */     super(message, cause);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\metadata\sql\MetadataException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */