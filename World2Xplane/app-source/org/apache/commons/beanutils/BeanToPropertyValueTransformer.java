/*     */ package org.apache.commons.beanutils;
/*     */ 
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import org.apache.commons.collections.Transformer;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ 
/*     */ public class BeanToPropertyValueTransformer implements Transformer {
/*  75 */   private final Log log = LogFactory.getLog(getClass());
/*     */   
/*     */   private String propertyName;
/*     */   
/*     */   private boolean ignoreNull;
/*     */   
/*     */   public BeanToPropertyValueTransformer(String propertyName) {
/* 105 */     this(propertyName, false);
/*     */   }
/*     */   
/*     */   public BeanToPropertyValueTransformer(String propertyName, boolean ignoreNull) {
/* 123 */     if (propertyName != null && propertyName.length() > 0) {
/* 124 */       this.propertyName = propertyName;
/* 125 */       this.ignoreNull = ignoreNull;
/*     */     } else {
/* 127 */       throw new IllegalArgumentException("propertyName cannot be null or empty");
/*     */     } 
/*     */   }
/*     */   
/*     */   public Object transform(Object object) {
/* 150 */     Object propertyValue = null;
/*     */     try {
/* 153 */       propertyValue = PropertyUtils.getProperty(object, this.propertyName);
/* 154 */     } catch (IllegalArgumentException e) {
/* 155 */       String errorMsg = "Problem during transformation. Null value encountered in property path...";
/* 157 */       if (this.ignoreNull) {
/* 158 */         this.log.warn("WARNING: Problem during transformation. Null value encountered in property path..." + e);
/*     */       } else {
/* 160 */         IllegalArgumentException iae = new IllegalArgumentException("Problem during transformation. Null value encountered in property path...");
/* 161 */         if (!BeanUtils.initCause(iae, e))
/* 162 */           this.log.error("Problem during transformation. Null value encountered in property path...", e); 
/* 164 */         throw iae;
/*     */       } 
/* 166 */     } catch (IllegalAccessException e) {
/* 167 */       String errorMsg = "Unable to access the property provided.";
/* 168 */       IllegalArgumentException iae = new IllegalArgumentException("Unable to access the property provided.");
/* 169 */       if (!BeanUtils.initCause(iae, e))
/* 170 */         this.log.error("Unable to access the property provided.", e); 
/* 172 */       throw iae;
/* 173 */     } catch (InvocationTargetException e) {
/* 174 */       String errorMsg = "Exception occurred in property's getter";
/* 175 */       IllegalArgumentException iae = new IllegalArgumentException("Exception occurred in property's getter");
/* 176 */       if (!BeanUtils.initCause(iae, e))
/* 177 */         this.log.error("Exception occurred in property's getter", e); 
/* 179 */       throw iae;
/* 180 */     } catch (NoSuchMethodException e) {
/* 181 */       String errorMsg = "No property found for name [" + this.propertyName + "]";
/* 183 */       IllegalArgumentException iae = new IllegalArgumentException(errorMsg);
/* 184 */       if (!BeanUtils.initCause(iae, e))
/* 185 */         this.log.error(errorMsg, e); 
/* 187 */       throw iae;
/*     */     } 
/* 190 */     return propertyValue;
/*     */   }
/*     */   
/*     */   public String getPropertyName() {
/* 199 */     return this.propertyName;
/*     */   }
/*     */   
/*     */   public boolean isIgnoreNull() {
/* 216 */     return this.ignoreNull;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\beanutils\BeanToPropertyValueTransformer.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */