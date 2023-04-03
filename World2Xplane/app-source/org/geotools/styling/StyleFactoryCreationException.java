/*    */ package org.geotools.styling;
/*    */ 
/*    */ public class StyleFactoryCreationException extends Exception {
/*    */   private static final long serialVersionUID = -1809128211848169317L;
/*    */   
/*    */   public StyleFactoryCreationException() {}
/*    */   
/*    */   public StyleFactoryCreationException(String msg) {
/* 49 */     super(msg);
/*    */   }
/*    */   
/*    */   public StyleFactoryCreationException(Exception e) {
/* 53 */     super(e);
/*    */   }
/*    */   
/*    */   public StyleFactoryCreationException(String msg, Exception e) {
/* 57 */     super(msg, e);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\styling\StyleFactoryCreationException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */