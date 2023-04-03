/*     */ package org.geotools.data;
/*     */ 
/*     */ public class DefaultFeatureLockFactory extends FeatureLockFactory {
/*  40 */   static long count = 0L;
/*     */   
/*     */   protected FeatureLock createLock(String name, long duration) {
/*  43 */     long number = nextIdNumber(duration);
/*  45 */     return new DefaultFeatureLock(name + "_" + Long.toString(number, 16), duration);
/*     */   }
/*     */   
/*     */   static FeatureLock createTestLock(String name, long duration) {
/*  58 */     return new DefaultFeatureLock(name, duration);
/*     */   }
/*     */   
/*     */   static void seedIdNumber(long seed) {
/*  69 */     count = seed;
/*     */   }
/*     */   
/*     */   static long nextIdNumber(long duration) {
/*  92 */     long now = System.currentTimeMillis();
/*  93 */     long time = now + duration;
/*  97 */     long number = ++count;
/*  98 */     number ^= time;
/* 100 */     StringBuffer reverse = new StringBuffer(asBits(now));
/* 101 */     now = Long.parseLong(reverse.reverse().toString(), 2);
/* 103 */     number ^= now;
/* 105 */     return number;
/*     */   }
/*     */   
/*     */   private static String asBits(long number) {
/* 119 */     long yum = number;
/* 120 */     StringBuffer buf = new StringBuffer(32);
/* 122 */     for (int i = 0; i < 63; i++) {
/* 123 */       buf.append(yum & 0x1L);
/* 124 */       yum >>= 1L;
/*     */     } 
/* 127 */     buf.reverse();
/* 129 */     return buf.toString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\DefaultFeatureLockFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */