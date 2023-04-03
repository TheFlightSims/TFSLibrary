/*     */ package com.google.common.base;
/*     */ 
/*     */ import com.google.common.annotations.GwtCompatible;
/*     */ import java.util.Arrays;
/*     */ import javax.annotation.CheckReturnValue;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ @GwtCompatible
/*     */ public final class Objects {
/*     */   @CheckReturnValue
/*     */   public static boolean equal(@Nullable Object a, @Nullable Object b) {
/*  57 */     return (a == b || (a != null && a.equals(b)));
/*     */   }
/*     */   
/*     */   public static int hashCode(@Nullable Object... objects) {
/*  78 */     return Arrays.hashCode(objects);
/*     */   }
/*     */   
/*     */   public static ToStringHelper toStringHelper(Object self) {
/* 121 */     return new ToStringHelper(simpleName(self.getClass()));
/*     */   }
/*     */   
/*     */   public static ToStringHelper toStringHelper(Class<?> clazz) {
/* 135 */     return new ToStringHelper(simpleName(clazz));
/*     */   }
/*     */   
/*     */   public static ToStringHelper toStringHelper(String className) {
/* 147 */     return new ToStringHelper(className);
/*     */   }
/*     */   
/*     */   private static String simpleName(Class<?> clazz) {
/* 155 */     String name = clazz.getName();
/* 159 */     name = name.replaceAll("\\$[0-9]+", "\\$");
/* 162 */     int start = name.lastIndexOf('$');
/* 166 */     if (start == -1)
/* 167 */       start = name.lastIndexOf('.'); 
/* 169 */     return name.substring(start + 1);
/*     */   }
/*     */   
/*     */   public static <T> T firstNonNull(@Nullable T first, @Nullable T second) {
/* 190 */     return (first != null) ? first : Preconditions.<T>checkNotNull(second);
/*     */   }
/*     */   
/*     */   public static final class ToStringHelper {
/*     */     private final String className;
/*     */     
/* 201 */     private ValueHolder holderHead = new ValueHolder();
/*     */     
/* 202 */     private ValueHolder holderTail = this.holderHead;
/*     */     
/*     */     private boolean omitNullValues = false;
/*     */     
/*     */     private ToStringHelper(String className) {
/* 209 */       this.className = Preconditions.<String>checkNotNull(className);
/*     */     }
/*     */     
/*     */     public ToStringHelper omitNullValues() {
/* 220 */       this.omitNullValues = true;
/* 221 */       return this;
/*     */     }
/*     */     
/*     */     public ToStringHelper add(String name, @Nullable Object value) {
/* 231 */       return addHolder(name, value);
/*     */     }
/*     */     
/*     */     public ToStringHelper add(String name, boolean value) {
/* 241 */       return addHolder(name, String.valueOf(value));
/*     */     }
/*     */     
/*     */     public ToStringHelper add(String name, char value) {
/* 251 */       return addHolder(name, String.valueOf(value));
/*     */     }
/*     */     
/*     */     public ToStringHelper add(String name, double value) {
/* 261 */       return addHolder(name, String.valueOf(value));
/*     */     }
/*     */     
/*     */     public ToStringHelper add(String name, float value) {
/* 271 */       return addHolder(name, String.valueOf(value));
/*     */     }
/*     */     
/*     */     public ToStringHelper add(String name, int value) {
/* 281 */       return addHolder(name, String.valueOf(value));
/*     */     }
/*     */     
/*     */     public ToStringHelper add(String name, long value) {
/* 291 */       return addHolder(name, String.valueOf(value));
/*     */     }
/*     */     
/*     */     public ToStringHelper addValue(@Nullable Object value) {
/* 301 */       return addHolder(value);
/*     */     }
/*     */     
/*     */     public ToStringHelper addValue(boolean value) {
/* 313 */       return addHolder(String.valueOf(value));
/*     */     }
/*     */     
/*     */     public ToStringHelper addValue(char value) {
/* 325 */       return addHolder(String.valueOf(value));
/*     */     }
/*     */     
/*     */     public ToStringHelper addValue(double value) {
/* 337 */       return addHolder(String.valueOf(value));
/*     */     }
/*     */     
/*     */     public ToStringHelper addValue(float value) {
/* 349 */       return addHolder(String.valueOf(value));
/*     */     }
/*     */     
/*     */     public ToStringHelper addValue(int value) {
/* 361 */       return addHolder(String.valueOf(value));
/*     */     }
/*     */     
/*     */     public ToStringHelper addValue(long value) {
/* 373 */       return addHolder(String.valueOf(value));
/*     */     }
/*     */     
/*     */     public String toString() {
/* 388 */       boolean omitNullValuesSnapshot = this.omitNullValues;
/* 389 */       String nextSeparator = "";
/* 390 */       StringBuilder builder = (new StringBuilder(32)).append(this.className).append('{');
/* 392 */       for (ValueHolder valueHolder = this.holderHead.next; valueHolder != null; 
/* 393 */         valueHolder = valueHolder.next) {
/* 394 */         if (!omitNullValuesSnapshot || valueHolder.value != null) {
/* 395 */           builder.append(nextSeparator);
/* 396 */           nextSeparator = ", ";
/* 398 */           if (valueHolder.name != null)
/* 399 */             builder.append(valueHolder.name).append('='); 
/* 401 */           builder.append(valueHolder.value);
/*     */         } 
/*     */       } 
/* 404 */       return builder.append('}').toString();
/*     */     }
/*     */     
/*     */     private ValueHolder addHolder() {
/* 408 */       ValueHolder valueHolder = new ValueHolder();
/* 409 */       this.holderTail = this.holderTail.next = valueHolder;
/* 410 */       return valueHolder;
/*     */     }
/*     */     
/*     */     private ToStringHelper addHolder(@Nullable Object value) {
/* 414 */       ValueHolder valueHolder = addHolder();
/* 415 */       valueHolder.value = value;
/* 416 */       return this;
/*     */     }
/*     */     
/*     */     private ToStringHelper addHolder(String name, @Nullable Object value) {
/* 420 */       ValueHolder valueHolder = addHolder();
/* 421 */       valueHolder.value = value;
/* 422 */       valueHolder.name = Preconditions.<String>checkNotNull(name);
/* 423 */       return this;
/*     */     }
/*     */     
/*     */     private static final class ValueHolder {
/*     */       String name;
/*     */       
/*     */       Object value;
/*     */       
/*     */       ValueHolder next;
/*     */       
/*     */       private ValueHolder() {}
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\google\common\base\Objects.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */