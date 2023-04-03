/*     */ package com.google.common.base;
/*     */ 
/*     */ import com.google.common.annotations.Beta;
/*     */ import com.google.common.annotations.GwtCompatible;
/*     */ import com.google.common.annotations.GwtIncompatible;
/*     */ import java.io.Serializable;
/*     */ import java.lang.ref.WeakReference;
/*     */ import java.lang.reflect.Field;
/*     */ import java.util.EnumSet;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.WeakHashMap;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ @GwtCompatible(emulated = true)
/*     */ @Beta
/*     */ public final class Enums {
/*     */   @GwtIncompatible("reflection")
/*     */   public static Field getField(Enum<?> enumValue) {
/*  57 */     Class<?> clazz = enumValue.getDeclaringClass();
/*     */     try {
/*  59 */       return clazz.getDeclaredField(enumValue.name());
/*  60 */     } catch (NoSuchFieldException impossible) {
/*  61 */       throw new AssertionError(impossible);
/*     */     } 
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public static <T extends Enum<T>> Function<String, T> valueOfFunction(Class<T> enumClass) {
/*  80 */     return new ValueOfFunction<T>(enumClass);
/*     */   }
/*     */   
/*     */   private static final class ValueOfFunction<T extends Enum<T>> implements Function<String, T>, Serializable {
/*     */     private final Class<T> enumClass;
/*     */     
/*     */     private static final long serialVersionUID = 0L;
/*     */     
/*     */     private ValueOfFunction(Class<T> enumClass) {
/*  93 */       this.enumClass = Preconditions.<Class<T>>checkNotNull(enumClass);
/*     */     }
/*     */     
/*     */     public T apply(String value) {
/*     */       try {
/*  99 */         return Enum.valueOf(this.enumClass, value);
/* 100 */       } catch (IllegalArgumentException e) {
/* 101 */         return null;
/*     */       } 
/*     */     }
/*     */     
/*     */     public boolean equals(@Nullable Object obj) {
/* 106 */       return (obj instanceof ValueOfFunction && this.enumClass.equals(((ValueOfFunction)obj).enumClass));
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 110 */       return this.enumClass.hashCode();
/*     */     }
/*     */     
/*     */     public String toString() {
/* 114 */       return "Enums.valueOf(" + this.enumClass + ")";
/*     */     }
/*     */   }
/*     */   
/*     */   public static <T extends Enum<T>> Optional<T> getIfPresent(Class<T> enumClass, String value) {
/* 130 */     Preconditions.checkNotNull(enumClass);
/* 131 */     Preconditions.checkNotNull(value);
/* 132 */     return Platform.getEnumIfPresent(enumClass, value);
/*     */   }
/*     */   
/*     */   @GwtIncompatible("java.lang.ref.WeakReference")
/* 137 */   private static final Map<Class<? extends Enum<?>>, Map<String, WeakReference<? extends Enum<?>>>> enumConstantCache = new WeakHashMap<Class<? extends Enum<?>>, Map<String, WeakReference<? extends Enum<?>>>>();
/*     */   
/*     */   @GwtIncompatible("java.lang.ref.WeakReference")
/*     */   private static <T extends Enum<T>> Map<String, WeakReference<? extends Enum<?>>> populateCache(Class<T> enumClass) {
/* 143 */     Map<String, WeakReference<? extends Enum<?>>> result = new HashMap<String, WeakReference<? extends Enum<?>>>();
/* 145 */     for (Enum<?> enum_ : EnumSet.<T>allOf(enumClass))
/* 146 */       result.put(enum_.name(), new WeakReference<Enum<?>>(enum_)); 
/* 148 */     enumConstantCache.put(enumClass, result);
/* 149 */     return result;
/*     */   }
/*     */   
/*     */   @GwtIncompatible("java.lang.ref.WeakReference")
/*     */   static <T extends Enum<T>> Map<String, WeakReference<? extends Enum<?>>> getEnumConstants(Class<T> enumClass) {
/* 155 */     synchronized (enumConstantCache) {
/* 156 */       Map<String, WeakReference<? extends Enum<?>>> constants = enumConstantCache.get(enumClass);
/* 158 */       if (constants == null)
/* 159 */         constants = populateCache(enumClass); 
/* 161 */       return constants;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static <T extends Enum<T>> Converter<String, T> stringConverter(Class<T> enumClass) {
/* 174 */     return new StringConverter<T>(enumClass);
/*     */   }
/*     */   
/*     */   private static final class StringConverter<T extends Enum<T>> extends Converter<String, T> implements Serializable {
/*     */     private final Class<T> enumClass;
/*     */     
/*     */     private static final long serialVersionUID = 0L;
/*     */     
/*     */     StringConverter(Class<T> enumClass) {
/* 183 */       this.enumClass = Preconditions.<Class<T>>checkNotNull(enumClass);
/*     */     }
/*     */     
/*     */     protected T doForward(String value) {
/* 188 */       return Enum.valueOf(this.enumClass, value);
/*     */     }
/*     */     
/*     */     protected String doBackward(T enumValue) {
/* 193 */       return enumValue.name();
/*     */     }
/*     */     
/*     */     public boolean equals(@Nullable Object object) {
/* 198 */       if (object instanceof StringConverter) {
/* 199 */         StringConverter<?> that = (StringConverter)object;
/* 200 */         return this.enumClass.equals(that.enumClass);
/*     */       } 
/* 202 */       return false;
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 207 */       return this.enumClass.hashCode();
/*     */     }
/*     */     
/*     */     public String toString() {
/* 212 */       return "Enums.stringConverter(" + this.enumClass.getName() + ".class)";
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\google\common\base\Enums.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */