/*    */ package com.google.common.base;
/*    */ 
/*    */ import java.util.Collections;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ public final class Defaults {
/*    */   private static final Map<Class<?>, Object> DEFAULTS;
/*    */   
/*    */   static {
/* 38 */     Map<Class<?>, Object> map = new HashMap<Class<?>, Object>();
/* 39 */     put(map, boolean.class, Boolean.valueOf(false));
/* 40 */     put(map, char.class, Character.valueOf(false));
/* 41 */     put(map, byte.class, Byte.valueOf((byte)0));
/* 42 */     put(map, short.class, Short.valueOf((short)0));
/* 43 */     put(map, int.class, Integer.valueOf(0));
/* 44 */     put(map, long.class, Long.valueOf(0L));
/* 45 */     put(map, float.class, Float.valueOf(0.0F));
/* 46 */     put(map, double.class, Double.valueOf(0.0D));
/* 47 */     DEFAULTS = Collections.unmodifiableMap(map);
/*    */   }
/*    */   
/*    */   private static <T> void put(Map<Class<?>, Object> map, Class<T> type, T value) {
/* 51 */     map.put(type, value);
/*    */   }
/*    */   
/*    */   public static <T> T defaultValue(Class<T> type) {
/* 62 */     T t = (T)DEFAULTS.get(Preconditions.checkNotNull(type));
/* 63 */     return t;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\google\common\base\Defaults.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */