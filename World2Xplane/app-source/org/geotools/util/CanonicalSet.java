/*     */ package org.geotools.util;
/*     */ 
/*     */ public class CanonicalSet<E> extends WeakHashSet<E> {
/*     */   public CanonicalSet() {}
/*     */   
/*     */   protected CanonicalSet(Class<E> type) {
/*  68 */     super(type);
/*     */   }
/*     */   
/*     */   public static <E> CanonicalSet<E> newInstance(Class<E> type) {
/*  81 */     return new CanonicalSet<E>(type);
/*     */   }
/*     */   
/*     */   public synchronized <T extends E> T get(T object) {
/*  97 */     return (T)intern(object, 0);
/*     */   }
/*     */   
/*     */   public synchronized <T extends E> T unique(T object) {
/* 123 */     return (T)intern(object, 2);
/*     */   }
/*     */   
/*     */   public synchronized void uniques(E[] objects) {
/* 143 */     for (int i = 0; i < objects.length; i++)
/* 144 */       objects[i] = (E)intern(objects[i], 2); 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotool\\util\CanonicalSet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */