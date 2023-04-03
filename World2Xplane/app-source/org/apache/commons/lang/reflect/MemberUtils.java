/*     */ package org.apache.commons.lang.reflect;
/*     */ 
/*     */ import java.lang.reflect.AccessibleObject;
/*     */ import java.lang.reflect.Member;
/*     */ import java.lang.reflect.Method;
/*     */ import java.lang.reflect.Modifier;
/*     */ import org.apache.commons.lang.ArrayUtils;
/*     */ import org.apache.commons.lang.ClassUtils;
/*     */ import org.apache.commons.lang.SystemUtils;
/*     */ 
/*     */ abstract class MemberUtils {
/*     */   private static final int ACCESS_TEST = 7;
/*     */   
/*     */   private static final Method IS_SYNTHETIC;
/*     */   
/*     */   static {
/*  46 */     Method isSynthetic = null;
/*  47 */     if (SystemUtils.isJavaVersionAtLeast(1.5F))
/*     */       try {
/*  50 */         isSynthetic = Member.class.getMethod("isSynthetic", ArrayUtils.EMPTY_CLASS_ARRAY);
/*  52 */       } catch (Exception e) {} 
/*  55 */     IS_SYNTHETIC = isSynthetic;
/*     */   }
/*     */   
/*  59 */   private static final Class[] ORDERED_PRIMITIVE_TYPES = new Class[] { byte.class, short.class, char.class, int.class, long.class, float.class, double.class };
/*     */   
/*     */   static void setAccessibleWorkaround(AccessibleObject o) {
/*  75 */     if (o == null || o.isAccessible())
/*     */       return; 
/*  78 */     Member m = (Member)o;
/*  79 */     if (Modifier.isPublic(m.getModifiers()) && isPackageAccess(m.getDeclaringClass().getModifiers()))
/*     */       try {
/*  82 */         o.setAccessible(true);
/*  83 */       } catch (SecurityException e) {} 
/*     */   }
/*     */   
/*     */   static boolean isPackageAccess(int modifiers) {
/*  95 */     return ((modifiers & 0x7) == 0);
/*     */   }
/*     */   
/*     */   static boolean isAccessible(Member m) {
/* 104 */     return (m != null && Modifier.isPublic(m.getModifiers()) && !isSynthetic(m));
/*     */   }
/*     */   
/*     */   static boolean isSynthetic(Member m) {
/* 113 */     if (IS_SYNTHETIC != null)
/*     */       try {
/* 115 */         return ((Boolean)IS_SYNTHETIC.invoke(m, null)).booleanValue();
/* 116 */       } catch (Exception e) {} 
/* 119 */     return false;
/*     */   }
/*     */   
/*     */   static int compareParameterTypes(Class[] left, Class[] right, Class[] actual) {
/* 135 */     float leftCost = getTotalTransformationCost(actual, left);
/* 136 */     float rightCost = getTotalTransformationCost(actual, right);
/* 137 */     return (leftCost < rightCost) ? -1 : ((rightCost < leftCost) ? 1 : 0);
/*     */   }
/*     */   
/*     */   private static float getTotalTransformationCost(Class[] srcArgs, Class[] destArgs) {
/* 148 */     float totalCost = 0.0F;
/* 149 */     for (int i = 0; i < srcArgs.length; i++) {
/* 151 */       Class srcClass = srcArgs[i];
/* 152 */       Class destClass = destArgs[i];
/* 153 */       totalCost += getObjectTransformationCost(srcClass, destClass);
/*     */     } 
/* 155 */     return totalCost;
/*     */   }
/*     */   
/*     */   private static float getObjectTransformationCost(Class srcClass, Class destClass) {
/* 167 */     if (destClass.isPrimitive())
/* 168 */       return getPrimitivePromotionCost(srcClass, destClass); 
/* 170 */     float cost = 0.0F;
/* 171 */     while (srcClass != null && !destClass.equals(srcClass)) {
/* 172 */       if (destClass.isInterface() && ClassUtils.isAssignable(srcClass, destClass)) {
/* 178 */         cost += 0.25F;
/*     */         break;
/*     */       } 
/* 181 */       cost++;
/* 182 */       srcClass = srcClass.getSuperclass();
/*     */     } 
/* 188 */     if (srcClass == null)
/* 189 */       cost += 1.5F; 
/* 191 */     return cost;
/*     */   }
/*     */   
/*     */   private static float getPrimitivePromotionCost(Class srcClass, Class destClass) {
/* 202 */     float cost = 0.0F;
/* 203 */     Class cls = srcClass;
/* 204 */     if (!cls.isPrimitive()) {
/* 206 */       cost += 0.1F;
/* 207 */       cls = ClassUtils.wrapperToPrimitive(cls);
/*     */     } 
/* 209 */     for (int i = 0; cls != destClass && i < ORDERED_PRIMITIVE_TYPES.length; i++) {
/* 210 */       if (cls == ORDERED_PRIMITIVE_TYPES[i]) {
/* 211 */         cost += 0.1F;
/* 212 */         if (i < ORDERED_PRIMITIVE_TYPES.length - 1)
/* 213 */           cls = ORDERED_PRIMITIVE_TYPES[i + 1]; 
/*     */       } 
/*     */     } 
/* 217 */     return cost;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\lang\reflect\MemberUtils.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */