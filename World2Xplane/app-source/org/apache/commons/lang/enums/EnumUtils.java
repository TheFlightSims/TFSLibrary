/*     */ package org.apache.commons.lang.enums;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class EnumUtils {
/*     */   public static Enum getEnum(Class enumClass, String name) {
/*  52 */     return Enum.getEnum(enumClass, name);
/*     */   }
/*     */   
/*     */   public static ValuedEnum getEnum(Class enumClass, int value) {
/*  64 */     return (ValuedEnum)ValuedEnum.getEnum(enumClass, value);
/*     */   }
/*     */   
/*     */   public static Map getEnumMap(Class enumClass) {
/*  81 */     return Enum.getEnumMap(enumClass);
/*     */   }
/*     */   
/*     */   public static List getEnumList(Class enumClass) {
/* 101 */     return Enum.getEnumList(enumClass);
/*     */   }
/*     */   
/*     */   public static Iterator iterator(Class enumClass) {
/* 121 */     return Enum.getEnumList(enumClass).iterator();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\lang\enums\EnumUtils.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */