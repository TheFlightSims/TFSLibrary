/*     */ package org.apache.commons.beanutils.converters;
/*     */ 
/*     */ import java.util.List;
/*     */ import org.apache.commons.beanutils.ConversionException;
/*     */ 
/*     */ public final class DoubleArrayConverter extends AbstractArrayConverter {
/*     */   public DoubleArrayConverter() {
/*  50 */     this.defaultValue = null;
/*  51 */     this.useDefault = false;
/*     */   }
/*     */   
/*     */   public DoubleArrayConverter(Object defaultValue) {
/*  64 */     this.defaultValue = defaultValue;
/*  65 */     this.useDefault = true;
/*     */   }
/*     */   
/*  76 */   private static final double[] MODEL = new double[0];
/*     */   
/*     */   public Object convert(Class type, Object value) {
/*  95 */     if (value == null) {
/*  96 */       if (this.useDefault)
/*  97 */         return this.defaultValue; 
/*  99 */       throw new ConversionException("No value specified");
/*     */     } 
/* 104 */     if (MODEL.getClass() == value.getClass())
/* 105 */       return value; 
/* 109 */     if (strings.getClass() == value.getClass())
/*     */       try {
/* 111 */         String[] values = (String[])value;
/* 112 */         double[] results = new double[values.length];
/* 113 */         for (int i = 0; i < values.length; i++)
/* 114 */           results[i] = Double.parseDouble(values[i]); 
/* 116 */         return results;
/* 117 */       } catch (Exception e) {
/* 118 */         if (this.useDefault)
/* 119 */           return this.defaultValue; 
/* 121 */         throw new ConversionException(value.toString(), e);
/*     */       }  
/*     */     try {
/* 129 */       List list = parseElements(value.toString());
/* 130 */       double[] results = new double[list.size()];
/* 131 */       for (int i = 0; i < results.length; i++)
/* 132 */         results[i] = Double.parseDouble((String)list.get(i)); 
/* 134 */       return results;
/* 135 */     } catch (Exception e) {
/* 136 */       if (this.useDefault)
/* 137 */         return this.defaultValue; 
/* 139 */       throw new ConversionException(value.toString(), e);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\beanutils\converters\DoubleArrayConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */