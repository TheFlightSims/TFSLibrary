/*     */ package scala.collection.parallel;
/*     */ 
/*     */ import scala.Function1;
/*     */ import scala.Function2;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.immutable.List;
/*     */ import scala.collection.immutable.Nil$;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ public abstract class AugmentedSeqIterator$class {
/*     */   public static void $init$(AugmentedSeqIterator $this) {}
/*     */   
/*     */   public static int prefixLength(AugmentedSeqIterator $this, Function1 pred) {
/* 298 */     int total = 0;
/* 299 */     boolean loop = true;
/* 300 */     while ($this.hasNext() && loop) {
/* 301 */       if (BoxesRunTime.unboxToBoolean(pred.apply($this.next()))) {
/* 301 */         total++;
/*     */         continue;
/*     */       } 
/* 302 */       loop = false;
/*     */     } 
/* 304 */     return total;
/*     */   }
/*     */   
/*     */   public static int indexWhere(AugmentedSeqIterator $this, Function1 pred) {
/* 308 */     int i = 0;
/* 309 */     boolean loop = true;
/* 310 */     while ($this.hasNext() && loop) {
/* 311 */       if (BoxesRunTime.unboxToBoolean(pred.apply($this.next()))) {
/* 311 */         loop = false;
/*     */         continue;
/*     */       } 
/* 312 */       i++;
/*     */     } 
/* 314 */     return loop ? -1 : i;
/*     */   }
/*     */   
/*     */   public static int lastIndexWhere(AugmentedSeqIterator $this, Function1 pred) {
/* 318 */     int pos = -1;
/* 319 */     int i = 0;
/* 320 */     while ($this.hasNext()) {
/* 321 */       if (BoxesRunTime.unboxToBoolean(pred.apply($this.next())))
/* 321 */         pos = i; 
/* 322 */       i++;
/*     */     } 
/* 324 */     return pos;
/*     */   }
/*     */   
/*     */   public static boolean corresponds(AugmentedSeqIterator $this, Function2 corr, Iterator that) {
/* 328 */     while ($this.hasNext() && that.hasNext()) {
/* 329 */       if (!BoxesRunTime.unboxToBoolean(corr.apply($this.next(), that.next())))
/* 329 */         return false; 
/*     */     } 
/* 331 */     return ($this.hasNext() == that.hasNext());
/*     */   }
/*     */   
/*     */   public static Combiner reverse2combiner(AugmentedSeqIterator $this, Combiner cb) {
/* 337 */     if ($this.isRemainingCheap())
/* 337 */       cb.sizeHint($this.remaining()); 
/* 338 */     Nil$ nil$ = Nil$.MODULE$;
/*     */     List list;
/* 339 */     for (; $this.hasNext(); list = nil$.$colon$colon($this.next()));
/*     */     while (true) {
/* 340 */       Nil$ nil$1 = Nil$.MODULE$;
/* 340 */       if (list == null) {
/* 340 */         if (nil$1 == null)
/* 344 */           return cb; 
/*     */       } else if (list.equals(nil$1)) {
/* 344 */         return cb;
/*     */       } 
/*     */       cb.$plus$eq(list.head());
/*     */       list = (List)list.tail();
/*     */     } 
/*     */   }
/*     */   
/*     */   public static Combiner reverseMap2combiner(AugmentedSeqIterator $this, Function1 f, Combiner cb) {
/* 349 */     if ($this.isRemainingCheap())
/* 349 */       cb.sizeHint($this.remaining()); 
/* 350 */     Nil$ nil$ = Nil$.MODULE$;
/*     */     List list;
/* 351 */     for (; $this.hasNext(); list = nil$.$colon$colon(f.apply($this.next())));
/*     */     while (true) {
/* 352 */       Nil$ nil$1 = Nil$.MODULE$;
/* 352 */       if (list == null) {
/* 352 */         if (nil$1 == null)
/* 356 */           return cb; 
/*     */       } else if (list.equals(nil$1)) {
/* 356 */         return cb;
/*     */       } 
/*     */       cb.$plus$eq(list.head());
/*     */       list = (List)list.tail();
/*     */     } 
/*     */   }
/*     */   
/*     */   public static Combiner updated2combiner(AugmentedSeqIterator $this, int index, Object elem, Combiner cb) {
/* 361 */     if ($this.isRemainingCheap())
/* 361 */       cb.sizeHint($this.remaining()); 
/* 362 */     int j = 0;
/* 363 */     while ($this.hasNext()) {
/* 365 */       cb.$plus$eq(elem);
/* 366 */       (j == index) ? $this.next() : 
/* 367 */         cb.$plus$eq($this.next());
/* 368 */       j++;
/*     */     } 
/* 370 */     return cb;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\AugmentedSeqIterator$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */