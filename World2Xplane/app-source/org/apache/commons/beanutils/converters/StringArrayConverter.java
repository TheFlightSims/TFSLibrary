/*     */ package org.apache.commons.beanutils.converters;
/*     */ 
/*     */ import java.util.List;
/*     */ import org.apache.commons.beanutils.ConversionException;
/*     */ 
/*     */ public final class StringArrayConverter extends AbstractArrayConverter {
/*     */   public StringArrayConverter() {
/*  53 */     this.defaultValue = null;
/*  54 */     this.useDefault = false;
/*     */   }
/*     */   
/*     */   public StringArrayConverter(Object defaultValue) {
/*  67 */     this.defaultValue = defaultValue;
/*  68 */     this.useDefault = true;
/*     */   }
/*     */   
/*  79 */   private static final String[] MODEL = new String[0];
/*     */   
/*  84 */   private static final int[] INT_MODEL = new int[0];
/*     */   
/*     */   public Object convert(Class type, Object value) {
/* 126 */     if (value == null) {
/* 127 */       if (this.useDefault)
/* 128 */         return this.defaultValue; 
/* 130 */       throw new ConversionException("No value specified");
/*     */     } 
/* 135 */     if (MODEL.getClass() == value.getClass())
/* 136 */       return value; 
/* 140 */     if (INT_MODEL.getClass() == value.getClass()) {
/* 142 */       int[] values = (int[])value;
/* 143 */       String[] results = new String[values.length];
/* 144 */       for (int i = 0; i < values.length; i++)
/* 146 */         results[i] = Integer.toString(values[i]); 
/* 149 */       return results;
/*     */     } 
/*     */     try {
/* 155 */       List list = parseElements(value.toString());
/* 156 */       String[] results = new String[list.size()];
/* 157 */       for (int i = 0; i < results.length; i++)
/* 158 */         results[i] = list.get(i); 
/* 160 */       return results;
/* 161 */     } catch (Exception e) {
/* 162 */       if (this.useDefault)
/* 163 */         return this.defaultValue; 
/* 165 */       throw new ConversionException(value.toString(), e);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\beanutils\converters\StringArrayConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */