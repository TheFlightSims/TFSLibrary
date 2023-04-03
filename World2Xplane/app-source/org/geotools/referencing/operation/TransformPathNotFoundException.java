/*    */ package org.geotools.referencing.operation;
/*    */ 
/*    */ import org.opengis.referencing.FactoryException;
/*    */ import org.opengis.referencing.operation.TransformException;
/*    */ 
/*    */ public class TransformPathNotFoundException extends TransformException {
/*    */   private static final long serialVersionUID = 5072333160296464925L;
/*    */   
/*    */   public TransformPathNotFoundException() {}
/*    */   
/*    */   public TransformPathNotFoundException(FactoryException cause) {
/* 74 */     super(cause.getLocalizedMessage(), (Throwable)cause);
/*    */   }
/*    */   
/*    */   public TransformPathNotFoundException(String message) {
/* 84 */     super(message);
/*    */   }
/*    */   
/*    */   public TransformPathNotFoundException(String message, FactoryException cause) {
/* 96 */     super(message, (Throwable)cause);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\operation\TransformPathNotFoundException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */