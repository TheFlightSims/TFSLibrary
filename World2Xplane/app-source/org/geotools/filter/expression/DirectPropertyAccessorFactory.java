/*    */ package org.geotools.filter.expression;
/*    */ 
/*    */ import org.geotools.factory.Hints;
/*    */ import org.opengis.feature.Property;
/*    */ import org.opengis.feature.type.Name;
/*    */ 
/*    */ public class DirectPropertyAccessorFactory implements PropertyAccessorFactory {
/* 34 */   static PropertyAccessor DIRECT = new DirectPropertyAccessor();
/*    */   
/*    */   public PropertyAccessor createPropertyAccessor(Class type, String xpath, Class target, Hints hints) {
/* 38 */     return DIRECT;
/*    */   }
/*    */   
/*    */   static class DirectPropertyAccessor implements PropertyAccessor {
/*    */     public boolean canHandle(Object object, String xpath, Class target) {
/* 56 */       if (object instanceof Property) {
/* 57 */         Property property = (Property)object;
/* 58 */         Name name = property.getName();
/* 59 */         if (name != null)
/* 60 */           return name.getLocalPart().equals(xpath); 
/* 64 */         return false;
/*    */       } 
/* 67 */       return false;
/*    */     }
/*    */     
/*    */     public Object get(Object object, String xpath, Class target) throws IllegalArgumentException {
/* 72 */       return ((Property)object).getValue();
/*    */     }
/*    */     
/*    */     public void set(Object object, String xpath, Object value, Class target) throws IllegalArgumentException {
/* 77 */       ((Property)object).setValue(value);
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\expression\DirectPropertyAccessorFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */