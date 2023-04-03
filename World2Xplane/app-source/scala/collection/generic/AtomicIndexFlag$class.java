/*     */ package scala.collection.generic;
/*     */ 
/*     */ import java.util.concurrent.atomic.AtomicInteger;
/*     */ 
/*     */ public abstract class AtomicIndexFlag$class {
/*     */   public static void $init$(AtomicIndexFlag $this) {
/* 134 */     $this.scala$collection$generic$AtomicIndexFlag$_setter_$scala$collection$generic$AtomicIndexFlag$$intflag_$eq(new AtomicInteger(-1));
/*     */   }
/*     */   
/*     */   public static int indexFlag(AtomicIndexFlag $this) {
/* 135 */     return $this.scala$collection$generic$AtomicIndexFlag$$intflag().get();
/*     */   }
/*     */   
/*     */   public static void setIndexFlag(AtomicIndexFlag $this, int f) {
/* 136 */     $this.scala$collection$generic$AtomicIndexFlag$$intflag().set(f);
/*     */   }
/*     */   
/*     */   public static void setIndexFlagIfGreater(AtomicIndexFlag $this, int f) {
/* 138 */     boolean loop = true;
/*     */     do {
/* 139 */       int old = $this.scala$collection$generic$AtomicIndexFlag$$intflag().get();
/* 141 */       if (f <= old) {
/* 141 */         loop = false;
/* 142 */       } else if ($this.scala$collection$generic$AtomicIndexFlag$$intflag().compareAndSet(old, f)) {
/* 142 */         loop = false;
/*     */       } 
/* 143 */     } while (loop);
/*     */   }
/*     */   
/*     */   public static void setIndexFlagIfLesser(AtomicIndexFlag $this, int f) {
/* 146 */     boolean loop = true;
/*     */     do {
/* 147 */       int old = $this.scala$collection$generic$AtomicIndexFlag$$intflag().get();
/* 149 */       if (f >= old) {
/* 149 */         loop = false;
/* 150 */       } else if ($this.scala$collection$generic$AtomicIndexFlag$$intflag().compareAndSet(old, f)) {
/* 150 */         loop = false;
/*     */       } 
/* 151 */     } while (loop);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\generic\AtomicIndexFlag$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */