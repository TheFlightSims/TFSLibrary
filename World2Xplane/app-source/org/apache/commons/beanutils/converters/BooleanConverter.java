/*     */ package org.apache.commons.beanutils.converters;
/*     */ 
/*     */ import org.apache.commons.beanutils.ConversionException;
/*     */ 
/*     */ public final class BooleanConverter extends AbstractConverter {
/*     */   public BooleanConverter(Object defaultValue) {
/*  85 */     if (defaultValue != NO_DEFAULT)
/*  86 */       setDefaultValue(defaultValue); 
/*     */   }
/*     */   
/*     */   public BooleanConverter(String[] trueStrings, String[] falseStrings) {
/* 109 */     this.trueStrings = copyStrings(trueStrings);
/* 110 */     this.falseStrings = copyStrings(falseStrings);
/*     */   }
/*     */   
/*     */   public BooleanConverter(String[] trueStrings, String[] falseStrings, Object defaultValue) {
/* 139 */     this.trueStrings = copyStrings(trueStrings);
/* 140 */     this.falseStrings = copyStrings(falseStrings);
/* 141 */     if (defaultValue != NO_DEFAULT)
/* 142 */       setDefaultValue(defaultValue); 
/*     */   }
/*     */   
/* 157 */   public static final Object NO_DEFAULT = new Object();
/*     */   
/* 165 */   private String[] trueStrings = new String[] { "true", "yes", "y", "on", "1" };
/*     */   
/* 170 */   private String[] falseStrings = new String[] { "false", "no", "n", "off", "0" };
/*     */   
/*     */   protected Class getDefaultType() {
/* 181 */     return Boolean.class;
/*     */   }
/*     */   
/*     */   protected Object convertToType(Class type, Object value) throws Throwable {
/* 209 */     String stringValue = value.toString().toLowerCase();
/*     */     int i;
/* 211 */     for (i = 0; i < this.trueStrings.length; i++) {
/* 212 */       if (this.trueStrings[i].equals(stringValue))
/* 213 */         return Boolean.TRUE; 
/*     */     } 
/* 217 */     for (i = 0; i < this.falseStrings.length; i++) {
/* 218 */       if (this.falseStrings[i].equals(stringValue))
/* 219 */         return Boolean.FALSE; 
/*     */     } 
/* 223 */     throw new ConversionException("Can't convert value '" + value + "' to a Boolean");
/*     */   }
/*     */   
/*     */   private static String[] copyStrings(String[] src) {
/* 235 */     String[] dst = new String[src.length];
/* 236 */     for (int i = 0; i < src.length; i++)
/* 237 */       dst[i] = src[i].toLowerCase(); 
/* 239 */     return dst;
/*     */   }
/*     */   
/*     */   public BooleanConverter() {}
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\beanutils\converters\BooleanConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */