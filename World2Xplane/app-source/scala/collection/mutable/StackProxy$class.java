/*     */ package scala.collection.mutable;
/*     */ 
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.TraversableOnce;
/*     */ import scala.collection.immutable.List;
/*     */ 
/*     */ public abstract class StackProxy$class {
/*     */   public static void $init$(StackProxy $this) {}
/*     */   
/*     */   public static Object apply(StackProxy $this, int n) {
/*  29 */     return $this.self().apply(n);
/*     */   }
/*     */   
/*     */   public static int length(StackProxy $this) {
/*  33 */     return $this.self().length();
/*     */   }
/*     */   
/*     */   public static boolean isEmpty(StackProxy $this) {
/*  39 */     return $this.self().isEmpty();
/*     */   }
/*     */   
/*     */   public static StackProxy $plus$eq(StackProxy<Object> $this, Object elem) {
/*  46 */     $this.self().push(elem);
/*  47 */     return $this;
/*     */   }
/*     */   
/*     */   public static StackProxy pushAll(StackProxy $this, TraversableOnce xs) {
/*  50 */     $this.self().pushAll(xs);
/*  50 */     return $this;
/*     */   }
/*     */   
/*     */   public static StackProxy push(StackProxy<Object> $this, Object elem1, Object elem2, Seq elems) {
/*  53 */     $this.self().push(elem1).push(elem2).pushAll((TraversableOnce)elems);
/*  54 */     return $this;
/*     */   }
/*     */   
/*     */   public static StackProxy push(StackProxy<Object> $this, Object elem) {
/*  58 */     $this.self().push(elem);
/*  59 */     return $this;
/*     */   }
/*     */   
/*     */   public static Object top(StackProxy $this) {
/*  68 */     return $this.self().top();
/*     */   }
/*     */   
/*     */   public static Object pop(StackProxy $this) {
/*  72 */     return $this.self().pop();
/*     */   }
/*     */   
/*     */   public static void clear(StackProxy $this) {
/*  78 */     $this.self().clear();
/*     */   }
/*     */   
/*     */   public static Iterator iterator(StackProxy $this) {
/*  88 */     return $this.self().iterator();
/*     */   }
/*     */   
/*     */   public static List toList(StackProxy $this) {
/*  94 */     return $this.self().toList();
/*     */   }
/*     */   
/*     */   public static Stack clone(StackProxy<A> $this) {
/* 100 */     return new StackProxy$$anon$1($this);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\StackProxy$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */