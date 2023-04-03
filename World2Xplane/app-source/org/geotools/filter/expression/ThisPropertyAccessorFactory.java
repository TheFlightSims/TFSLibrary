/*    */ package org.geotools.filter.expression;
/*    */ 
/*    */ import org.geotools.factory.Hints;
/*    */ import org.opengis.feature.Attribute;
/*    */ 
/*    */ public class ThisPropertyAccessorFactory implements PropertyAccessorFactory {
/* 13 */   static final ThisPropertyAccessor THIS_ACCESSOR = new ThisPropertyAccessor();
/*    */   
/*    */   public PropertyAccessor createPropertyAccessor(Class type, String xpath, Class target, Hints hints) {
/* 17 */     if (".".equals(xpath))
/* 18 */       return THIS_ACCESSOR; 
/* 20 */     return null;
/*    */   }
/*    */   
/*    */   static class ThisPropertyAccessor implements PropertyAccessor {
/*    */     public boolean canHandle(Object object, String xpath, Class target) {
/* 26 */       return ".".equals(xpath);
/*    */     }
/*    */     
/*    */     public Object get(Object object, String xpath, Class target) throws IllegalArgumentException {
/* 31 */       if (object instanceof Attribute)
/* 32 */         return ((Attribute)object).getValue(); 
/* 34 */       return object;
/*    */     }
/*    */     
/*    */     public void set(Object object, String xpath, Object value, Class target) throws IllegalArgumentException {
/* 39 */       throw new IllegalArgumentException("Can't change the value itself");
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\expression\ThisPropertyAccessorFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */