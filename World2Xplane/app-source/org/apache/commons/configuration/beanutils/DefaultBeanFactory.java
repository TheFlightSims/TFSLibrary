/*     */ package org.apache.commons.configuration.beanutils;
/*     */ 
/*     */ public class DefaultBeanFactory implements BeanFactory {
/*  42 */   public static final DefaultBeanFactory INSTANCE = new DefaultBeanFactory();
/*     */   
/*     */   public Object createBean(Class beanClass, BeanDeclaration data, Object parameter) throws Exception {
/*  61 */     Object result = createBeanInstance(beanClass, data);
/*  62 */     initBeanInstance(result, data);
/*  63 */     return result;
/*     */   }
/*     */   
/*     */   public Class getDefaultBeanClass() {
/*  74 */     return null;
/*     */   }
/*     */   
/*     */   protected Object createBeanInstance(Class beanClass, BeanDeclaration data) throws Exception {
/*  90 */     return beanClass.newInstance();
/*     */   }
/*     */   
/*     */   protected void initBeanInstance(Object bean, BeanDeclaration data) throws Exception {
/* 106 */     BeanHelper.initBean(bean, data);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\configuration\beanutils\DefaultBeanFactory.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */