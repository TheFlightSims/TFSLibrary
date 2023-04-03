/*     */ package com.sun.media.jai.util;
/*     */ 
/*     */ import java.awt.image.DataBuffer;
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.lang.reflect.Method;
/*     */ 
/*     */ public final class DataBufferUtils {
/*  29 */   private static final String[] FLOAT_CLASS_NAMES = new String[] { "java.awt.image.DataBufferFloat", "javax.media.jai.DataBufferFloat", "com.sun.media.jai.codecimpl.util.DataBufferFloat" };
/*     */   
/*  38 */   private static final String[] DOUBLE_CLASS_NAMES = new String[] { "java.awt.image.DataBufferDouble", "javax.media.jai.DataBufferDouble", "com.sun.media.jai.codecimpl.util.DataBufferDouble" };
/*     */   
/*  47 */   private static Class floatClass = null;
/*     */   
/*  48 */   private static Class doubleClass = null;
/*     */   
/*     */   static Class array$$F;
/*     */   
/*     */   static Class array$I;
/*     */   
/*     */   static Class array$F;
/*     */   
/*     */   static Class array$$D;
/*     */   
/*     */   static Class array$D;
/*     */   
/*     */   static {
/*  54 */     floatClass = getDataBufferClass(4);
/*  55 */     doubleClass = getDataBufferClass(5);
/*     */   }
/*     */   
/*     */   private static final Class getDataBufferClass(int dataType) {
/*  66 */     String[] classNames = null;
/*  67 */     switch (dataType) {
/*     */       case 4:
/*  69 */         classNames = FLOAT_CLASS_NAMES;
/*     */         break;
/*     */       case 5:
/*  72 */         classNames = DOUBLE_CLASS_NAMES;
/*     */         break;
/*     */       default:
/*  75 */         throw new IllegalArgumentException("dataType == " + dataType + "!");
/*     */     } 
/*  79 */     Class dataBufferClass = null;
/*  82 */     for (int i = 0; i < classNames.length; i++) {
/*     */       try {
/*  85 */         dataBufferClass = Class.forName(classNames[i]);
/*  88 */         if (dataBufferClass != null)
/*     */           break; 
/*  91 */       } catch (ClassNotFoundException e) {}
/*     */     } 
/*  97 */     if (dataBufferClass == null)
/*  98 */       throw new RuntimeException(JaiI18N.getString("DataBufferUtils0") + " " + ((dataType == 4) ? "DataBufferFloat" : "DataBufferDouble")); 
/* 104 */     return dataBufferClass;
/*     */   }
/*     */   
/*     */   private static final DataBuffer constructDataBuffer(int dataType, Class[] paramTypes, Object[] paramValues) {
/* 115 */     Class dbClass = null;
/* 116 */     switch (dataType) {
/*     */       case 4:
/* 118 */         dbClass = floatClass;
/*     */         break;
/*     */       case 5:
/* 121 */         dbClass = doubleClass;
/*     */         break;
/*     */       default:
/* 124 */         throw new IllegalArgumentException("dataType == " + dataType + "!");
/*     */     } 
/* 127 */     DataBuffer dataBuffer = null;
/*     */     try {
/* 129 */       Constructor constructor = dbClass.getConstructor(paramTypes);
/* 130 */       dataBuffer = constructor.newInstance(paramValues);
/* 131 */     } catch (Exception e) {
/* 132 */       throw new RuntimeException(JaiI18N.getString("DataBufferUtils1"));
/*     */     } 
/* 135 */     return dataBuffer;
/*     */   }
/*     */   
/*     */   private static final Object invokeDataBufferMethod(DataBuffer dataBuffer, String methodName, Class[] paramTypes, Object[] paramValues) {
/* 146 */     if (dataBuffer == null)
/* 147 */       throw new IllegalArgumentException("dataBuffer == null!"); 
/* 150 */     Class dbClass = dataBuffer.getClass();
/* 152 */     Object returnValue = null;
/*     */     try {
/* 154 */       Method method = dbClass.getMethod(methodName, paramTypes);
/* 155 */       returnValue = method.invoke(dataBuffer, paramValues);
/* 156 */     } catch (Exception e) {
/* 157 */       throw new RuntimeException(JaiI18N.getString("DataBufferUtils2") + " \"" + methodName + "\".");
/*     */     } 
/* 161 */     return returnValue;
/*     */   }
/*     */   
/*     */   public static final DataBuffer createDataBufferFloat(float[][] dataArray, int size) {
/* 166 */     return constructDataBuffer(4, new Class[] { (array$$F == null) ? (array$$F = class$("[[F")) : array$$F, int.class }, new Object[] { dataArray, new Integer(size) });
/*     */   }
/*     */   
/*     */   static Class class$(String x0) {
/*     */     try {
/* 167 */       return Class.forName(x0);
/*     */     } catch (ClassNotFoundException x1) {
/* 167 */       throw new NoClassDefFoundError(x1.getMessage());
/*     */     } 
/*     */   }
/*     */   
/*     */   public static final DataBuffer createDataBufferFloat(float[][] dataArray, int size, int[] offsets) {
/* 176 */     return constructDataBuffer(4, new Class[] { (array$$F == null) ? (array$$F = class$("[[F")) : array$$F, int.class, (array$I == null) ? (array$I = class$("[I")) : array$I }, new Object[] { dataArray, new Integer(size), offsets });
/*     */   }
/*     */   
/*     */   public static final DataBuffer createDataBufferFloat(float[] dataArray, int size) {
/* 187 */     return constructDataBuffer(4, new Class[] { (array$F == null) ? (array$F = class$("[F")) : array$F, int.class }, new Object[] { dataArray, new Integer(size) });
/*     */   }
/*     */   
/*     */   public static final DataBuffer createDataBufferFloat(float[] dataArray, int size, int offset) {
/* 197 */     return constructDataBuffer(4, new Class[] { (array$F == null) ? (array$F = class$("[F")) : array$F, int.class, int.class }, new Object[] { dataArray, new Integer(size), new Integer(offset) });
/*     */   }
/*     */   
/*     */   public static final DataBuffer createDataBufferFloat(int size) {
/* 207 */     return constructDataBuffer(4, new Class[] { int.class }, new Object[] { new Integer(size) });
/*     */   }
/*     */   
/*     */   public static final DataBuffer createDataBufferFloat(int size, int numBanks) {
/* 214 */     return constructDataBuffer(4, new Class[] { int.class, int.class }, new Object[] { new Integer(size), new Integer(numBanks) });
/*     */   }
/*     */   
/*     */   public static final float[][] getBankDataFloat(DataBuffer dataBuffer) {
/* 222 */     return (float[][])invokeDataBufferMethod(dataBuffer, "getBankData", null, null);
/*     */   }
/*     */   
/*     */   public static final float[] getDataFloat(DataBuffer dataBuffer) {
/* 229 */     return (float[])invokeDataBufferMethod(dataBuffer, "getData", null, null);
/*     */   }
/*     */   
/*     */   public static final float[] getDataFloat(DataBuffer dataBuffer, int bank) {
/* 237 */     return (float[])invokeDataBufferMethod(dataBuffer, "getData", new Class[] { int.class }, new Object[] { new Integer(bank) });
/*     */   }
/*     */   
/*     */   public static final DataBuffer createDataBufferDouble(double[][] dataArray, int size) {
/* 245 */     return constructDataBuffer(5, new Class[] { (array$$D == null) ? (array$$D = class$("[[D")) : array$$D, int.class }, new Object[] { dataArray, new Integer(size) });
/*     */   }
/*     */   
/*     */   public static final DataBuffer createDataBufferDouble(double[][] dataArray, int size, int[] offsets) {
/* 255 */     return constructDataBuffer(5, new Class[] { (array$$D == null) ? (array$$D = class$("[[D")) : array$$D, int.class, (array$I == null) ? (array$I = class$("[I")) : array$I }, new Object[] { dataArray, new Integer(size), offsets });
/*     */   }
/*     */   
/*     */   public static final DataBuffer createDataBufferDouble(double[] dataArray, int size) {
/* 266 */     return constructDataBuffer(5, new Class[] { (array$D == null) ? (array$D = class$("[D")) : array$D, int.class }, new Object[] { dataArray, new Integer(size) });
/*     */   }
/*     */   
/*     */   public static final DataBuffer createDataBufferDouble(double[] dataArray, int size, int offset) {
/* 276 */     return constructDataBuffer(5, new Class[] { (array$D == null) ? (array$D = class$("[D")) : array$D, int.class, int.class }, new Object[] { dataArray, new Integer(size), new Integer(offset) });
/*     */   }
/*     */   
/*     */   public static final DataBuffer createDataBufferDouble(int size) {
/* 286 */     return constructDataBuffer(5, new Class[] { int.class }, new Object[] { new Integer(size) });
/*     */   }
/*     */   
/*     */   public static final DataBuffer createDataBufferDouble(int size, int numBanks) {
/* 293 */     return constructDataBuffer(5, new Class[] { int.class, int.class }, new Object[] { new Integer(size), new Integer(numBanks) });
/*     */   }
/*     */   
/*     */   public static final double[][] getBankDataDouble(DataBuffer dataBuffer) {
/* 301 */     return (double[][])invokeDataBufferMethod(dataBuffer, "getBankData", null, null);
/*     */   }
/*     */   
/*     */   public static final double[] getDataDouble(DataBuffer dataBuffer) {
/* 308 */     return (double[])invokeDataBufferMethod(dataBuffer, "getData", null, null);
/*     */   }
/*     */   
/*     */   public static final double[] getDataDouble(DataBuffer dataBuffer, int bank) {
/* 316 */     return (double[])invokeDataBufferMethod(dataBuffer, "getData", new Class[] { int.class }, new Object[] { new Integer(bank) });
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\ja\\util\DataBufferUtils.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */