/*     */ package org.apache.commons.beanutils;
/*     */ 
/*     */ public class ConvertUtils {
/*     */   public static boolean getDefaultBoolean() {
/*  47 */     return ConvertUtilsBean.getInstance().getDefaultBoolean();
/*     */   }
/*     */   
/*     */   public static void setDefaultBoolean(boolean newDefaultBoolean) {
/*  57 */     ConvertUtilsBean.getInstance().setDefaultBoolean(newDefaultBoolean);
/*     */   }
/*     */   
/*     */   public static byte getDefaultByte() {
/*  68 */     return ConvertUtilsBean.getInstance().getDefaultByte();
/*     */   }
/*     */   
/*     */   public static void setDefaultByte(byte newDefaultByte) {
/*  78 */     ConvertUtilsBean.getInstance().setDefaultByte(newDefaultByte);
/*     */   }
/*     */   
/*     */   public static char getDefaultCharacter() {
/*  89 */     return ConvertUtilsBean.getInstance().getDefaultCharacter();
/*     */   }
/*     */   
/*     */   public static void setDefaultCharacter(char newDefaultCharacter) {
/*  99 */     ConvertUtilsBean.getInstance().setDefaultCharacter(newDefaultCharacter);
/*     */   }
/*     */   
/*     */   public static double getDefaultDouble() {
/* 110 */     return ConvertUtilsBean.getInstance().getDefaultDouble();
/*     */   }
/*     */   
/*     */   public static void setDefaultDouble(double newDefaultDouble) {
/* 120 */     ConvertUtilsBean.getInstance().setDefaultDouble(newDefaultDouble);
/*     */   }
/*     */   
/*     */   public static float getDefaultFloat() {
/* 131 */     return ConvertUtilsBean.getInstance().getDefaultFloat();
/*     */   }
/*     */   
/*     */   public static void setDefaultFloat(float newDefaultFloat) {
/* 141 */     ConvertUtilsBean.getInstance().setDefaultFloat(newDefaultFloat);
/*     */   }
/*     */   
/*     */   public static int getDefaultInteger() {
/* 152 */     return ConvertUtilsBean.getInstance().getDefaultInteger();
/*     */   }
/*     */   
/*     */   public static void setDefaultInteger(int newDefaultInteger) {
/* 162 */     ConvertUtilsBean.getInstance().setDefaultInteger(newDefaultInteger);
/*     */   }
/*     */   
/*     */   public static long getDefaultLong() {
/* 173 */     return ConvertUtilsBean.getInstance().getDefaultLong();
/*     */   }
/*     */   
/*     */   public static void setDefaultLong(long newDefaultLong) {
/* 183 */     ConvertUtilsBean.getInstance().setDefaultLong(newDefaultLong);
/*     */   }
/*     */   
/*     */   public static short getDefaultShort() {
/* 194 */     return ConvertUtilsBean.getInstance().getDefaultShort();
/*     */   }
/*     */   
/*     */   public static void setDefaultShort(short newDefaultShort) {
/* 204 */     ConvertUtilsBean.getInstance().setDefaultShort(newDefaultShort);
/*     */   }
/*     */   
/*     */   public static String convert(Object value) {
/* 222 */     return ConvertUtilsBean.getInstance().convert(value);
/*     */   }
/*     */   
/*     */   public static Object convert(String value, Class clazz) {
/* 241 */     return ConvertUtilsBean.getInstance().convert(value, clazz);
/*     */   }
/*     */   
/*     */   public static Object convert(String[] values, Class clazz) {
/* 260 */     return ConvertUtilsBean.getInstance().convert(values, clazz);
/*     */   }
/*     */   
/*     */   public static Object convert(Object value, Class targetType) {
/* 276 */     return ConvertUtilsBean.getInstance().convert(value, targetType);
/*     */   }
/*     */   
/*     */   public static void deregister() {
/* 290 */     ConvertUtilsBean.getInstance().deregister();
/*     */   }
/*     */   
/*     */   public static void deregister(Class clazz) {
/* 306 */     ConvertUtilsBean.getInstance().deregister(clazz);
/*     */   }
/*     */   
/*     */   public static Converter lookup(Class clazz) {
/* 324 */     return ConvertUtilsBean.getInstance().lookup(clazz);
/*     */   }
/*     */   
/*     */   public static Converter lookup(Class sourceType, Class targetType) {
/* 339 */     return ConvertUtilsBean.getInstance().lookup(sourceType, targetType);
/*     */   }
/*     */   
/*     */   public static void register(Converter converter, Class clazz) {
/* 356 */     ConvertUtilsBean.getInstance().register(converter, clazz);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\beanutils\ConvertUtils.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */