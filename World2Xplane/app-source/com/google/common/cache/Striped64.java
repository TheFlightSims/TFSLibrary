/*     */ package com.google.common.cache;
/*     */ 
/*     */ import java.lang.reflect.Field;
/*     */ import java.security.AccessController;
/*     */ import java.security.PrivilegedActionException;
/*     */ import java.security.PrivilegedExceptionAction;
/*     */ import java.util.Random;
/*     */ import sun.misc.Unsafe;
/*     */ 
/*     */ abstract class Striped64 extends Number {
/*     */   static final class Cell {
/*     */     volatile long p0;
/*     */     
/*     */     volatile long p1;
/*     */     
/*     */     volatile long p2;
/*     */     
/*     */     volatile long p3;
/*     */     
/*     */     volatile long p4;
/*     */     
/*     */     volatile long p5;
/*     */     
/*     */     volatile long p6;
/*     */     
/*     */     volatile long value;
/*     */     
/*     */     volatile long q0;
/*     */     
/*     */     volatile long q1;
/*     */     
/*     */     volatile long q2;
/*     */     
/*     */     volatile long q3;
/*     */     
/*     */     volatile long q4;
/*     */     
/*     */     volatile long q5;
/*     */     
/*     */     volatile long q6;
/*     */     
/*     */     private static final Unsafe UNSAFE;
/*     */     
/*     */     private static final long valueOffset;
/*     */     
/*     */     Cell(long x) {
/*  97 */       this.value = x;
/*     */     }
/*     */     
/*     */     final boolean cas(long cmp, long val) {
/* 100 */       return UNSAFE.compareAndSwapLong(this, valueOffset, cmp, val);
/*     */     }
/*     */     
/*     */     static {
/*     */       try {
/* 108 */         UNSAFE = Striped64.getUnsafe();
/* 109 */         Class<?> ak = Cell.class;
/* 110 */         valueOffset = UNSAFE.objectFieldOffset(ak.getDeclaredField("value"));
/* 112 */       } catch (Exception e) {
/* 113 */         throw new Error(e);
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   static final class HashCode {
/* 124 */     static final Random rng = new Random();
/*     */     
/*     */     int code;
/*     */     
/*     */     HashCode() {
/* 127 */       int h = rng.nextInt();
/* 128 */       this.code = (h == 0) ? 1 : h;
/*     */     }
/*     */   }
/*     */   
/*     */   static final class ThreadHashCode extends ThreadLocal<HashCode> {
/*     */     public Striped64.HashCode initialValue() {
/* 136 */       return new Striped64.HashCode();
/*     */     }
/*     */   }
/*     */   
/* 145 */   static final ThreadHashCode threadHashCode = new ThreadHashCode();
/*     */   
/* 148 */   static final int NCPU = Runtime.getRuntime().availableProcessors();
/*     */   
/*     */   volatile transient Cell[] cells;
/*     */   
/*     */   volatile transient long base;
/*     */   
/*     */   volatile transient int busy;
/*     */   
/*     */   private static final Unsafe UNSAFE;
/*     */   
/*     */   private static final long baseOffset;
/*     */   
/*     */   private static final long busyOffset;
/*     */   
/*     */   final boolean casBase(long cmp, long val) {
/* 176 */     return UNSAFE.compareAndSwapLong(this, baseOffset, cmp, val);
/*     */   }
/*     */   
/*     */   final boolean casBusy() {
/* 183 */     return UNSAFE.compareAndSwapInt(this, busyOffset, 0, 1);
/*     */   }
/*     */   
/*     */   final void retryUpdate(long x, HashCode hc, boolean wasUncontended) {
/* 209 */     int h = hc.code;
/* 210 */     boolean collide = false;
/*     */     while (true) {
/*     */       Cell[] as;
/*     */       int n;
/* 213 */       if ((as = this.cells) != null && (n = as.length) > 0) {
/*     */         Cell a;
/* 214 */         if ((a = as[n - 1 & h]) == null) {
/* 215 */           if (this.busy == 0) {
/* 216 */             Cell r = new Cell(x);
/* 217 */             if (this.busy == 0 && casBusy()) {
/* 218 */               boolean created = false;
/*     */               try {
/*     */                 Cell[] rs;
/*     */                 int m;
/*     */                 int j;
/* 221 */                 if ((rs = this.cells) != null && (m = rs.length) > 0 && rs[j = m - 1 & h] == null) {
/* 224 */                   rs[j] = r;
/* 225 */                   created = true;
/*     */                 } 
/*     */               } finally {
/* 228 */                 this.busy = 0;
/*     */               } 
/* 230 */               if (created)
/*     */                 break; 
/*     */               continue;
/*     */             } 
/*     */           } 
/* 235 */           collide = false;
/* 237 */         } else if (!wasUncontended) {
/* 238 */           wasUncontended = true;
/*     */         } else {
/*     */           long l;
/* 239 */           if (a.cas(l = a.value, fn(l, x)))
/*     */             break; 
/* 241 */           if (n >= NCPU || this.cells != as) {
/* 242 */             collide = false;
/* 243 */           } else if (!collide) {
/* 244 */             collide = true;
/* 245 */           } else if (this.busy == 0 && casBusy()) {
/*     */             try {
/* 247 */               if (this.cells == as) {
/* 248 */                 Cell[] rs = new Cell[n << 1];
/* 249 */                 for (int i = 0; i < n; i++)
/* 250 */                   rs[i] = as[i]; 
/* 251 */                 this.cells = rs;
/*     */               } 
/*     */             } finally {
/* 254 */               this.busy = 0;
/*     */             } 
/* 256 */             collide = false;
/*     */             continue;
/*     */           } 
/*     */         } 
/* 259 */         h ^= h << 13;
/* 260 */         h ^= h >>> 17;
/* 261 */         h ^= h << 5;
/*     */         continue;
/*     */       } 
/* 263 */       if (this.busy == 0 && this.cells == as && casBusy()) {
/* 264 */         boolean init = false;
/*     */         try {
/* 266 */           if (this.cells == as) {
/* 267 */             Cell[] rs = new Cell[2];
/* 268 */             rs[h & 0x1] = new Cell(x);
/* 269 */             this.cells = rs;
/* 270 */             init = true;
/*     */           } 
/*     */         } finally {
/* 273 */           this.busy = 0;
/*     */         } 
/* 275 */         if (init)
/*     */           break; 
/*     */         continue;
/*     */       } 
/*     */       long v;
/* 278 */       if (casBase(v = this.base, fn(v, x)))
/*     */         break; 
/*     */     } 
/* 281 */     hc.code = h;
/*     */   }
/*     */   
/*     */   final void internalReset(long initialValue) {
/* 288 */     Cell[] as = this.cells;
/* 289 */     this.base = initialValue;
/* 290 */     if (as != null) {
/* 291 */       int n = as.length;
/* 292 */       for (int i = 0; i < n; i++) {
/* 293 */         Cell a = as[i];
/* 294 */         if (a != null)
/* 295 */           a.value = initialValue; 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   static {
/*     */     try {
/* 306 */       UNSAFE = getUnsafe();
/* 307 */       Class<?> sk = Striped64.class;
/* 308 */       baseOffset = UNSAFE.objectFieldOffset(sk.getDeclaredField("base"));
/* 310 */       busyOffset = UNSAFE.objectFieldOffset(sk.getDeclaredField("busy"));
/* 312 */     } catch (Exception e) {
/* 313 */       throw new Error(e);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static Unsafe getUnsafe() {
/*     */     try {
/* 326 */       return Unsafe.getUnsafe();
/* 327 */     } catch (SecurityException tryReflectionInstead) {
/*     */       try {
/* 329 */         return AccessController.<Unsafe>doPrivileged(new PrivilegedExceptionAction<Unsafe>() {
/*     */               public Unsafe run() throws Exception {
/* 332 */                 Class<Unsafe> k = Unsafe.class;
/* 333 */                 for (Field f : k.getDeclaredFields()) {
/* 334 */                   f.setAccessible(true);
/* 335 */                   Object x = f.get(null);
/* 336 */                   if (k.isInstance(x))
/* 337 */                     return k.cast(x); 
/*     */                 } 
/* 339 */                 throw new NoSuchFieldError("the Unsafe");
/*     */               }
/*     */             });
/* 341 */       } catch (PrivilegedActionException e) {
/* 342 */         throw new RuntimeException("Could not initialize intrinsics", e.getCause());
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   abstract long fn(long paramLong1, long paramLong2);
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\google\common\cache\Striped64.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */