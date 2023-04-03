/*    */ package org.apache.commons.beanutils;
/*    */ 
/*    */ import java.lang.reflect.InvocationTargetException;
/*    */ 
/*    */ public class ConvertingWrapDynaBean extends WrapDynaBean {
/*    */   public ConvertingWrapDynaBean(Object instance) {
/* 48 */     super(instance);
/*    */   }
/*    */   
/*    */   public void set(String name, Object value) {
/*    */     try {
/* 67 */       BeanUtils.copyProperty(this.instance, name, value);
/* 68 */     } catch (InvocationTargetException ite) {
/* 69 */       Throwable cause = ite.getTargetException();
/* 70 */       throw new IllegalArgumentException("Error setting property '" + name + "' nested exception - " + cause);
/* 73 */     } catch (Throwable t) {
/* 74 */       IllegalArgumentException iae = new IllegalArgumentException("Error setting property '" + name + "', exception - " + t);
/* 77 */       BeanUtils.initCause(iae, t);
/* 78 */       throw iae;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\beanutils\ConvertingWrapDynaBean.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */