/*     */ package org.apache.commons.beanutils.converters;
/*     */ 
/*     */ import java.util.List;
/*     */ import org.apache.commons.beanutils.ConversionException;
/*     */ 
/*     */ public final class IntegerArrayConverter extends AbstractArrayConverter {
/*     */   public IntegerArrayConverter() {
/*  50 */     this.defaultValue = null;
/*  51 */     this.useDefault = false;
/*     */   }
/*     */   
/*     */   public IntegerArrayConverter(Object defaultValue) {
/*  64 */     this.defaultValue = defaultValue;
/*  65 */     this.useDefault = true;
/*     */   }
/*     */   
/*  76 */   private static final int[] MODEL = new int[0];
/*     */   
/*     */   public Object convert(Class type, Object value) {
/*  96 */     if (value == null) {
/*  97 */       if (this.useDefault)
/*  98 */         return this.defaultValue; 
/* 100 */       throw new ConversionException("No value specified");
/*     */     } 
/* 105 */     if (MODEL.getClass() == value.getClass())
/* 106 */       return value; 
/* 110 */     if (strings.getClass() == value.getClass())
/*     */       try {
/* 112 */         String[] values = (String[])value;
/* 113 */         int[] results = new int[values.length];
/* 114 */         for (int i = 0; i < values.length; i++)
/* 115 */           results[i] = Integer.parseInt(values[i]); 
/* 117 */         return results;
/* 118 */       } catch (Exception e) {
/* 119 */         if (this.useDefault)
/* 120 */           return this.defaultValue; 
/* 122 */         throw new ConversionException(value.toString(), e);
/*     */       }  
/*     */     try {
/* 130 */       List list = parseElements(value.toString());
/* 131 */       int[] results = new int[list.size()];
/* 132 */       for (int i = 0; i < results.length; i++)
/* 133 */         results[i] = Integer.parseInt((String)list.get(i)); 
/* 135 */       return results;
/* 136 */     } catch (Exception e) {
/* 137 */       if (this.useDefault)
/* 138 */         return this.defaultValue; 
/* 140 */       throw new ConversionException(value.toString(), e);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\beanutils\converters\IntegerArrayConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */