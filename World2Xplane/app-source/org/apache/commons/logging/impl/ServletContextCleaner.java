/*     */ package org.apache.commons.logging.impl;
/*     */ 
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import javax.servlet.ServletContextEvent;
/*     */ import javax.servlet.ServletContextListener;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ 
/*     */ public class ServletContextCleaner implements ServletContextListener {
/*  54 */   private Class[] RELEASE_SIGNATURE = new Class[] { ClassLoader.class };
/*     */   
/*     */   public void contextDestroyed(ServletContextEvent sce) {
/*  62 */     ClassLoader tccl = Thread.currentThread().getContextClassLoader();
/*  64 */     Object[] params = new Object[1];
/*  65 */     params[0] = tccl;
/*  97 */     ClassLoader loader = tccl;
/*  98 */     while (loader != null) {
/*     */       try {
/* 103 */         Class logFactoryClass = loader.loadClass("org.apache.commons.logging.LogFactory");
/* 104 */         Method releaseMethod = logFactoryClass.getMethod("release", this.RELEASE_SIGNATURE);
/* 105 */         releaseMethod.invoke(null, params);
/* 106 */         loader = logFactoryClass.getClassLoader().getParent();
/* 107 */       } catch (ClassNotFoundException ex) {
/* 110 */         loader = null;
/* 111 */       } catch (NoSuchMethodException ex) {
/* 113 */         System.err.println("LogFactory instance found which does not support release method!");
/* 114 */         loader = null;
/* 115 */       } catch (IllegalAccessException ex) {
/* 117 */         System.err.println("LogFactory instance found which is not accessable!");
/* 118 */         loader = null;
/* 119 */       } catch (InvocationTargetException ex) {
/* 121 */         System.err.println("LogFactory instance release method failed!");
/* 122 */         loader = null;
/*     */       } 
/*     */     } 
/* 129 */     LogFactory.release(tccl);
/*     */   }
/*     */   
/*     */   public void contextInitialized(ServletContextEvent sce) {}
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\logging\impl\ServletContextCleaner.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */