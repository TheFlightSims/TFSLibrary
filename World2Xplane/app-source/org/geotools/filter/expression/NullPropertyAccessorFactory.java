/*    */ package org.geotools.filter.expression;
/*    */ 
/*    */ import org.geotools.factory.Hints;
/*    */ 
/*    */ public class NullPropertyAccessorFactory implements PropertyAccessorFactory {
/* 35 */   private static PropertyAccessor NULLPA = new NullPropertyAccessor();
/*    */   
/*    */   public PropertyAccessor createPropertyAccessor(Class type, String xpath, Class target, Hints hints) {
/* 39 */     return NULLPA;
/*    */   }
/*    */   
/*    */   static class NullPropertyAccessor implements PropertyAccessor {
/*    */     public boolean canHandle(Object object, String xpath, Class target) {
/* 53 */       return "Expression/NIL".equals(xpath);
/*    */     }
/*    */     
/*    */     public Object get(Object object, String xpath, Class target) throws IllegalArgumentException {
/* 59 */       return null;
/*    */     }
/*    */     
/*    */     public void set(Object object, String xpath, Object value, Class target) throws IllegalArgumentException {
/* 64 */       throw new IllegalArgumentException("Cannot assign a value to Expression/NIL.");
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\expression\NullPropertyAccessorFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */