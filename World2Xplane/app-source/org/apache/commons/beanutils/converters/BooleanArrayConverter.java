/*     */ package org.apache.commons.beanutils.converters;
/*     */ 
/*     */ import java.util.List;
/*     */ import org.apache.commons.beanutils.ConversionException;
/*     */ 
/*     */ public final class BooleanArrayConverter extends AbstractArrayConverter {
/*     */   public BooleanArrayConverter() {
/*  59 */     this.booleanConverter = DEFAULT_CONVERTER;
/*     */   }
/*     */   
/*     */   public BooleanArrayConverter(Object defaultValue) {
/*  75 */     super(defaultValue);
/*  76 */     this.booleanConverter = DEFAULT_CONVERTER;
/*     */   }
/*     */   
/*     */   public BooleanArrayConverter(BooleanConverter converter, Object defaultValue) {
/* 100 */     super(defaultValue);
/* 101 */     this.booleanConverter = converter;
/*     */   }
/*     */   
/* 112 */   public static final Class MODEL = (new boolean[0]).getClass();
/*     */   
/* 119 */   private static final BooleanConverter DEFAULT_CONVERTER = new BooleanConverter();
/*     */   
/*     */   protected final BooleanConverter booleanConverter;
/*     */   
/*     */   public Object convert(Class type, Object value) {
/* 185 */     if (value == null) {
/* 186 */       if (this.useDefault)
/* 187 */         return this.defaultValue; 
/* 189 */       throw new ConversionException("No value specified");
/*     */     } 
/* 194 */     if (MODEL == value.getClass())
/* 195 */       return value; 
/* 202 */     if (strings.getClass() == value.getClass())
/*     */       try {
/* 204 */         String[] values = (String[])value;
/* 205 */         boolean[] results = new boolean[values.length];
/* 206 */         for (int i = 0; i < values.length; i++) {
/* 207 */           String stringValue = values[i];
/* 208 */           Object result = this.booleanConverter.convert(Boolean.class, stringValue);
/* 209 */           results[i] = ((Boolean)result).booleanValue();
/*     */         } 
/* 211 */         return results;
/* 212 */       } catch (Exception e) {
/* 213 */         if (this.useDefault)
/* 214 */           return this.defaultValue; 
/* 216 */         throw new ConversionException(value.toString(), e);
/*     */       }  
/*     */     try {
/* 226 */       List list = parseElements(value.toString());
/* 227 */       boolean[] results = new boolean[list.size()];
/* 228 */       for (int i = 0; i < results.length; i++) {
/* 229 */         String stringValue = list.get(i);
/* 230 */         Object result = this.booleanConverter.convert(Boolean.class, stringValue);
/* 231 */         results[i] = ((Boolean)result).booleanValue();
/*     */       } 
/* 233 */       return results;
/* 234 */     } catch (Exception e) {
/* 235 */       if (this.useDefault)
/* 236 */         return this.defaultValue; 
/* 238 */       throw new ConversionException(value.toString(), e);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\beanutils\converters\BooleanArrayConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */