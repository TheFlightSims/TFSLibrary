/*     */ package org.apache.commons.beanutils;
/*     */ 
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import org.apache.commons.collections.Predicate;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ 
/*     */ public class BeanPredicate implements Predicate {
/*  33 */   private final Log log = LogFactory.getLog(getClass());
/*     */   
/*     */   private String propertyName;
/*     */   
/*     */   private Predicate predicate;
/*     */   
/*     */   public BeanPredicate(String propertyName, Predicate predicate) {
/*  49 */     this.propertyName = propertyName;
/*  50 */     this.predicate = predicate;
/*     */   }
/*     */   
/*     */   public boolean evaluate(Object object) {
/*  63 */     boolean evaluation = false;
/*     */     try {
/*  66 */       Object propValue = PropertyUtils.getProperty(object, this.propertyName);
/*  67 */       evaluation = this.predicate.evaluate(propValue);
/*  68 */     } catch (IllegalArgumentException e) {
/*  69 */       String errorMsg = "Problem during evaluation.";
/*  70 */       this.log.error("ERROR: Problem during evaluation.", e);
/*  71 */       throw e;
/*  72 */     } catch (IllegalAccessException e) {
/*  73 */       String errorMsg = "Unable to access the property provided.";
/*  74 */       this.log.error("Unable to access the property provided.", e);
/*  75 */       throw new IllegalArgumentException("Unable to access the property provided.");
/*  76 */     } catch (InvocationTargetException e) {
/*  77 */       String errorMsg = "Exception occurred in property's getter";
/*  78 */       this.log.error("Exception occurred in property's getter", e);
/*  79 */       throw new IllegalArgumentException("Exception occurred in property's getter");
/*  80 */     } catch (NoSuchMethodException e) {
/*  81 */       String errorMsg = "Property not found.";
/*  82 */       this.log.error("Property not found.", e);
/*  83 */       throw new IllegalArgumentException("Property not found.");
/*     */     } 
/*  86 */     return evaluation;
/*     */   }
/*     */   
/*     */   public String getPropertyName() {
/*  95 */     return this.propertyName;
/*     */   }
/*     */   
/*     */   public void setPropertyName(String propertyName) {
/* 104 */     this.propertyName = propertyName;
/*     */   }
/*     */   
/*     */   public Predicate getPredicate() {
/* 113 */     return this.predicate;
/*     */   }
/*     */   
/*     */   public void setPredicate(Predicate predicate) {
/* 122 */     this.predicate = predicate;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\beanutils\BeanPredicate.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */