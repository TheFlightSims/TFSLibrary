/*     */ package org.apache.commons.beanutils;
/*     */ 
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import org.apache.commons.collections.Closure;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ 
/*     */ public class BeanPropertyValueChangeClosure implements Closure {
/*  85 */   private final Log log = LogFactory.getLog(getClass());
/*     */   
/*     */   private String propertyName;
/*     */   
/*     */   private Object propertyValue;
/*     */   
/*     */   private boolean ignoreNull;
/*     */   
/*     */   public BeanPropertyValueChangeClosure(String propertyName, Object propertyValue) {
/* 121 */     this(propertyName, propertyValue, false);
/*     */   }
/*     */   
/*     */   public BeanPropertyValueChangeClosure(String propertyName, Object propertyValue, boolean ignoreNull) {
/* 140 */     if (propertyName != null && propertyName.length() > 0) {
/* 141 */       this.propertyName = propertyName;
/* 142 */       this.propertyValue = propertyValue;
/* 143 */       this.ignoreNull = ignoreNull;
/*     */     } else {
/* 145 */       throw new IllegalArgumentException("propertyName cannot be null or empty");
/*     */     } 
/*     */   }
/*     */   
/*     */   public void execute(Object object) {
/*     */     try {
/* 166 */       PropertyUtils.setProperty(object, this.propertyName, this.propertyValue);
/* 167 */     } catch (IllegalArgumentException e) {
/* 168 */       String errorMsg = "Unable to execute Closure. Null value encountered in property path...";
/* 170 */       if (this.ignoreNull) {
/* 171 */         this.log.warn("WARNING: Unable to execute Closure. Null value encountered in property path..." + e);
/*     */       } else {
/* 173 */         IllegalArgumentException iae = new IllegalArgumentException("Unable to execute Closure. Null value encountered in property path...");
/* 174 */         if (!BeanUtils.initCause(iae, e))
/* 175 */           this.log.error("Unable to execute Closure. Null value encountered in property path...", e); 
/* 177 */         throw iae;
/*     */       } 
/* 179 */     } catch (IllegalAccessException e) {
/* 180 */       String errorMsg = "Unable to access the property provided.";
/* 181 */       IllegalArgumentException iae = new IllegalArgumentException("Unable to access the property provided.");
/* 182 */       if (!BeanUtils.initCause(iae, e))
/* 183 */         this.log.error("Unable to access the property provided.", e); 
/* 185 */       throw iae;
/* 186 */     } catch (InvocationTargetException e) {
/* 187 */       String errorMsg = "Exception occurred in property's getter";
/* 188 */       IllegalArgumentException iae = new IllegalArgumentException("Exception occurred in property's getter");
/* 189 */       if (!BeanUtils.initCause(iae, e))
/* 190 */         this.log.error("Exception occurred in property's getter", e); 
/* 192 */       throw iae;
/* 193 */     } catch (NoSuchMethodException e) {
/* 194 */       String errorMsg = "Property not found";
/* 195 */       IllegalArgumentException iae = new IllegalArgumentException("Property not found");
/* 196 */       if (!BeanUtils.initCause(iae, e))
/* 197 */         this.log.error("Property not found", e); 
/* 199 */       throw iae;
/*     */     } 
/*     */   }
/*     */   
/*     */   public String getPropertyName() {
/* 209 */     return this.propertyName;
/*     */   }
/*     */   
/*     */   public Object getPropertyValue() {
/* 220 */     return this.propertyValue;
/*     */   }
/*     */   
/*     */   public boolean isIgnoreNull() {
/* 237 */     return this.ignoreNull;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\beanutils\BeanPropertyValueChangeClosure.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */