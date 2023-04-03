/*    */ package org.geotools.factory;
/*    */ 
/*    */ import org.geotools.resources.i18n.Errors;
/*    */ 
/*    */ public class RecursiveSearchException extends FactoryRegistryException {
/*    */   private static final long serialVersionUID = -2028654588882874110L;
/*    */   
/*    */   public RecursiveSearchException(Class<?> category) {
/* 52 */     super(Errors.format(163, category));
/*    */   }
/*    */   
/*    */   public RecursiveSearchException(String message) {
/* 59 */     super(message);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\factory\RecursiveSearchException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */