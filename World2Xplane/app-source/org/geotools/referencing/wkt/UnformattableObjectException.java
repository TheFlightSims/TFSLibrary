/*     */ package org.geotools.referencing.wkt;
/*     */ 
/*     */ import java.lang.reflect.Modifier;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ 
/*     */ public class UnformattableObjectException extends UnsupportedOperationException {
/*     */   private final Class unformattable;
/*     */   
/*     */   public UnformattableObjectException(String message) {
/*  57 */     super(message);
/*  58 */     this.unformattable = Object.class;
/*     */   }
/*     */   
/*     */   public UnformattableObjectException(String message, Class unformattable) {
/*  70 */     super(message);
/*  71 */     this.unformattable = unformattable;
/*     */   }
/*     */   
/*     */   public Class getUnformattableClass() {
/*  86 */     return this.unformattable;
/*     */   }
/*     */   
/*     */   public String getMessage() {
/*  94 */     String message = super.getMessage();
/*  95 */     if (message == null) {
/*  96 */       Class c = this.unformattable;
/*  97 */       while (!Modifier.isPublic(c.getModifiers())) {
/*  98 */         Class candidate = c.getSuperclass();
/*  99 */         if (candidate == null)
/*     */           break; 
/* 102 */         c = candidate;
/*     */       } 
/* 104 */       return Errors.format(83, c);
/*     */     } 
/* 106 */     return message;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\wkt\UnformattableObjectException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */