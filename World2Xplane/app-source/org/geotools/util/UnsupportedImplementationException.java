/*    */ package org.geotools.util;
/*    */ 
/*    */ import org.geotools.resources.i18n.Errors;
/*    */ 
/*    */ public class UnsupportedImplementationException extends UnsupportedOperationException {
/*    */   private static final long serialVersionUID = -649050339146622730L;
/*    */   
/*    */   public UnsupportedImplementationException(String message) {
/* 48 */     super(message);
/*    */   }
/*    */   
/*    */   public UnsupportedImplementationException(Class<?> classe) {
/* 57 */     super(Errors.format(187, classe));
/*    */   }
/*    */   
/*    */   public UnsupportedImplementationException(Class<?> classe, Exception cause) {
/* 68 */     super(Errors.format(187, classe), cause);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotool\\util\UnsupportedImplementationException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */