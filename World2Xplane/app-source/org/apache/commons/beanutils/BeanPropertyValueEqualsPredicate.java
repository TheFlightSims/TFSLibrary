/*     */ package org.apache.commons.beanutils;
/*     */ 
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import org.apache.commons.collections.Predicate;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ 
/*     */ public class BeanPropertyValueEqualsPredicate implements Predicate {
/* 114 */   private final Log log = LogFactory.getLog(getClass());
/*     */   
/*     */   private String propertyName;
/*     */   
/*     */   private Object propertyValue;
/*     */   
/*     */   private boolean ignoreNull;
/*     */   
/*     */   public BeanPropertyValueEqualsPredicate(String propertyName, Object propertyValue) {
/* 151 */     this(propertyName, propertyValue, false);
/*     */   }
/*     */   
/*     */   public BeanPropertyValueEqualsPredicate(String propertyName, Object propertyValue, boolean ignoreNull) {
/* 168 */     if (propertyName != null && propertyName.length() > 0) {
/* 169 */       this.propertyName = propertyName;
/* 170 */       this.propertyValue = propertyValue;
/* 171 */       this.ignoreNull = ignoreNull;
/*     */     } else {
/* 173 */       throw new IllegalArgumentException("propertyName cannot be null or empty");
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean evaluate(Object object) {
/* 197 */     boolean evaluation = false;
/*     */     try {
/* 200 */       evaluation = evaluateValue(this.propertyValue, PropertyUtils.getProperty(object, this.propertyName));
/* 202 */     } catch (IllegalArgumentException e) {
/* 203 */       String errorMsg = "Problem during evaluation. Null value encountered in property path...";
/* 205 */       if (this.ignoreNull) {
/* 206 */         this.log.warn("WARNING: Problem during evaluation. Null value encountered in property path..." + e);
/*     */       } else {
/* 208 */         IllegalArgumentException iae = new IllegalArgumentException("Problem during evaluation. Null value encountered in property path...");
/* 209 */         if (!BeanUtils.initCause(iae, e))
/* 210 */           this.log.error("Problem during evaluation. Null value encountered in property path...", e); 
/* 212 */         throw iae;
/*     */       } 
/* 214 */     } catch (IllegalAccessException e) {
/* 215 */       String errorMsg = "Unable to access the property provided.";
/* 216 */       IllegalArgumentException iae = new IllegalArgumentException("Unable to access the property provided.");
/* 217 */       if (!BeanUtils.initCause(iae, e))
/* 218 */         this.log.error("Unable to access the property provided.", e); 
/* 220 */       throw iae;
/* 221 */     } catch (InvocationTargetException e) {
/* 222 */       String errorMsg = "Exception occurred in property's getter";
/* 223 */       IllegalArgumentException iae = new IllegalArgumentException("Exception occurred in property's getter");
/* 224 */       if (!BeanUtils.initCause(iae, e))
/* 225 */         this.log.error("Exception occurred in property's getter", e); 
/* 227 */       throw iae;
/* 228 */     } catch (NoSuchMethodException e) {
/* 229 */       String errorMsg = "Property not found.";
/* 230 */       IllegalArgumentException iae = new IllegalArgumentException("Property not found.");
/* 231 */       if (!BeanUtils.initCause(iae, e))
/* 232 */         this.log.error("Property not found.", e); 
/* 234 */       throw iae;
/*     */     } 
/* 237 */     return evaluation;
/*     */   }
/*     */   
/*     */   protected boolean evaluateValue(Object expected, Object actual) {
/* 249 */     return (expected == actual || (expected != null && expected.equals(actual)));
/*     */   }
/*     */   
/*     */   public String getPropertyName() {
/* 260 */     return this.propertyName;
/*     */   }
/*     */   
/*     */   public Object getPropertyValue() {
/* 271 */     return this.propertyValue;
/*     */   }
/*     */   
/*     */   public boolean isIgnoreNull() {
/* 288 */     return this.ignoreNull;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\beanutils\BeanPropertyValueEqualsPredicate.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */