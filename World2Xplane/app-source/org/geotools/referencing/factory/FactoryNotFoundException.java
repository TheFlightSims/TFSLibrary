/*    */ package org.geotools.referencing.factory;
/*    */ 
/*    */ import org.opengis.referencing.FactoryException;
/*    */ 
/*    */ public class FactoryNotFoundException extends FactoryException {
/*    */   private static final long serialVersionUID = -661925454228937249L;
/*    */   
/*    */   public FactoryNotFoundException() {}
/*    */   
/*    */   public FactoryNotFoundException(String message) {
/* 53 */     super(message);
/*    */   }
/*    */   
/*    */   public FactoryNotFoundException(Exception cause) {
/* 65 */     super(cause.getLocalizedMessage(), cause);
/*    */   }
/*    */   
/*    */   public FactoryNotFoundException(String message, Throwable cause) {
/* 79 */     super(message, cause);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\factory\FactoryNotFoundException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */