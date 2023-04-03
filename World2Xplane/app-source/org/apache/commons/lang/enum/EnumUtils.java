/*     */ package org.apache.commons.lang.enum;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class EnumUtils {
/*     */   public static Enum getEnum(Class enumClass, String name) {
/*  56 */     return Enum.getEnum(enumClass, name);
/*     */   }
/*     */   
/*     */   public static ValuedEnum getEnum(Class enumClass, int value) {
/*  68 */     return (ValuedEnum)ValuedEnum.getEnum(enumClass, value);
/*     */   }
/*     */   
/*     */   public static Map getEnumMap(Class enumClass) {
/*  85 */     return Enum.getEnumMap(enumClass);
/*     */   }
/*     */   
/*     */   public static List getEnumList(Class enumClass) {
/* 105 */     return Enum.getEnumList(enumClass);
/*     */   }
/*     */   
/*     */   public static Iterator iterator(Class enumClass) {
/* 125 */     return Enum.getEnumList(enumClass).iterator();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\lang\enum\EnumUtils.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */