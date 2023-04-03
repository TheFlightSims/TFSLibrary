/*     */ package scala.collection.mutable;
/*     */ 
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.Option;
/*     */ import scala.collection.IterableLike;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.Nothing$;
/*     */ 
/*     */ public abstract class DoubleLinkedListLike$class {
/*     */   public static void $init$(DoubleLinkedListLike $this) {}
/*     */   
/*     */   public static Seq append(DoubleLinkedListLike $this, Seq that) {
/*  70 */     $this.next_$eq(that);
/*  71 */     if (that.nonEmpty())
/*  71 */       ((DoubleLinkedListLike)that).prev_$eq($this.repr()); 
/*  73 */     ((LinkedListLike)$this.next()).isEmpty() ? BoxedUnit.UNIT : ((DoubleLinkedListLike)$this.next()).append(that);
/*  75 */     return $this.isEmpty() ? that : (Seq)$this.repr();
/*     */   }
/*     */   
/*     */   public static void insert(DoubleLinkedListLike $this, Seq that) {
/*  80 */     $this.scala$collection$mutable$DoubleLinkedListLike$$super$insert(that);
/*  81 */     if (that.nonEmpty())
/*  81 */       ((DoubleLinkedListLike)that).prev_$eq($this.repr()); 
/*     */   }
/*     */   
/*     */   public static void remove(DoubleLinkedListLike $this) {
/*  95 */     if ($this.nonEmpty()) {
/*  96 */       ((DoubleLinkedListLike)$this.next()).prev_$eq($this.prev());
/*  97 */       if ($this.prev() != null)
/*  97 */         ((LinkedListLike)$this.prev()).next_$eq($this.next()); 
/*     */     } 
/*     */   }
/*     */   
/*     */   private static Object atLocation(DoubleLinkedListLike $this, int n, Function1 f, Function0 onOutOfBounds) {
/* 101 */     Seq loc = (Seq)$this.repr();
/* 102 */     int left = n;
/* 103 */     while (left > 0) {
/* 104 */       loc = (Seq)((LinkedListLike)loc).next();
/* 105 */       left--;
/* 106 */       ((LinkedListLike)loc).isEmpty() ? onOutOfBounds.apply() : BoxedUnit.UNIT;
/*     */     } 
/* 108 */     return $this.isEmpty() ? onOutOfBounds.apply() : f.apply(loc);
/*     */   }
/*     */   
/*     */   public static Nothing$ scala$collection$mutable$DoubleLinkedListLike$$outofbounds(DoubleLinkedListLike $this, int n) {
/* 111 */     throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(n).toString());
/*     */   }
/*     */   
/*     */   public static Seq drop(DoubleLinkedListLike $this, int n) {
/* 113 */     return (Seq)IterableLike.class.drop((IterableLike)$this, n);
/*     */   }
/*     */   
/*     */   public static Seq tail(DoubleLinkedListLike $this) {
/* 114 */     return (Seq)$this.drop(1);
/*     */   }
/*     */   
/*     */   public static Object apply(DoubleLinkedListLike<A, This> $this, int n) {
/* 115 */     return atLocation($this, n, (Function1)new DoubleLinkedListLike$$anonfun$apply$1($this), (Function0)new DoubleLinkedListLike$$anonfun$apply$2($this, n));
/*     */   }
/*     */   
/*     */   public static void update(DoubleLinkedListLike $this, int n, Object x) {
/* 116 */     atLocation($this, n, (Function1)new DoubleLinkedListLike$$anonfun$update$1($this, (DoubleLinkedListLike<A, This>)x), (Function0)new DoubleLinkedListLike$$anonfun$update$2($this, n));
/*     */   }
/*     */   
/*     */   public static Option get(DoubleLinkedListLike<A, This> $this, int n) {
/* 117 */     return (Option)atLocation($this, n, (Function1)new DoubleLinkedListLike$$anonfun$get$1($this), (Function0)new DoubleLinkedListLike$$anonfun$get$2($this));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\DoubleLinkedListLike$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */