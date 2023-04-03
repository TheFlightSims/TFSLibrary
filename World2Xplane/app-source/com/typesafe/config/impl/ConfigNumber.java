/*     */ package com.typesafe.config.impl;
/*     */ 
/*     */ import com.typesafe.config.ConfigException;
/*     */ import com.typesafe.config.ConfigOrigin;
/*     */ import java.io.ObjectStreamException;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ abstract class ConfigNumber extends AbstractConfigValue implements Serializable {
/*     */   private static final long serialVersionUID = 2L;
/*     */   
/*     */   protected final String originalText;
/*     */   
/*     */   protected ConfigNumber(ConfigOrigin origin, String originalText) {
/*  23 */     super(origin);
/*  24 */     this.originalText = originalText;
/*     */   }
/*     */   
/*     */   String transformToString() {
/*  32 */     return this.originalText;
/*     */   }
/*     */   
/*     */   int intValueRangeChecked(String path) {
/*  36 */     long l = longValue();
/*  37 */     if (l < -2147483648L || l > 2147483647L)
/*  38 */       throw new ConfigException.WrongType(origin(), path, "32-bit integer", "out-of-range value " + l); 
/*  41 */     return (int)l;
/*     */   }
/*     */   
/*     */   private boolean isWhole() {
/*  49 */     long asLong = longValue();
/*  50 */     return (asLong == doubleValue());
/*     */   }
/*     */   
/*     */   protected boolean canEqual(Object other) {
/*  55 */     return other instanceof ConfigNumber;
/*     */   }
/*     */   
/*     */   public boolean equals(Object other) {
/*  61 */     if (other instanceof ConfigNumber && canEqual(other)) {
/*  62 */       ConfigNumber n = (ConfigNumber)other;
/*  63 */       if (isWhole())
/*  64 */         return (n.isWhole() && longValue() == n.longValue()); 
/*  66 */       return (!n.isWhole() && doubleValue() == n.doubleValue());
/*     */     } 
/*  69 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/*     */     long asLong;
/*  80 */     if (isWhole()) {
/*  81 */       asLong = longValue();
/*     */     } else {
/*  83 */       asLong = Double.doubleToLongBits(doubleValue());
/*     */     } 
/*  85 */     return (int)(asLong ^ asLong >>> 32L);
/*     */   }
/*     */   
/*     */   static ConfigNumber newNumber(ConfigOrigin origin, long number, String originalText) {
/*  90 */     if (number <= 2147483647L && number >= -2147483648L)
/*  91 */       return new ConfigInt(origin, (int)number, originalText); 
/*  93 */     return new ConfigLong(origin, number, originalText);
/*     */   }
/*     */   
/*     */   static ConfigNumber newNumber(ConfigOrigin origin, double number, String originalText) {
/*  98 */     long asLong = (long)number;
/*  99 */     if (asLong == number)
/* 100 */       return newNumber(origin, asLong, originalText); 
/* 102 */     return new ConfigDouble(origin, number, originalText);
/*     */   }
/*     */   
/*     */   private Object writeReplace() throws ObjectStreamException {
/* 108 */     return new SerializedConfigValue(this);
/*     */   }
/*     */   
/*     */   public abstract Number unwrapped();
/*     */   
/*     */   protected abstract long longValue();
/*     */   
/*     */   protected abstract double doubleValue();
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\typesafe\config\impl\ConfigNumber.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */