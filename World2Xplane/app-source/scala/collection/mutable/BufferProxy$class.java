/*     */ package scala.collection.mutable;
/*     */ 
/*     */ import scala.collection.Iterable;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.Traversable;
/*     */ import scala.collection.TraversableOnce;
/*     */ import scala.collection.script.Message;
/*     */ 
/*     */ public abstract class BufferProxy$class {
/*     */   public static void $init$(BufferProxy $this) {}
/*     */   
/*     */   public static int length(BufferProxy $this) {
/*  35 */     return $this.self().length();
/*     */   }
/*     */   
/*     */   public static Iterator iterator(BufferProxy $this) {
/*  37 */     return $this.self().iterator();
/*     */   }
/*     */   
/*     */   public static Object apply(BufferProxy $this, int n) {
/*  39 */     return $this.self().apply(n);
/*     */   }
/*     */   
/*     */   public static BufferProxy $plus$eq(BufferProxy<Object> $this, Object elem) {
/*  45 */     $this.self().$plus$eq(elem);
/*  45 */     return $this;
/*     */   }
/*     */   
/*     */   public static Seq readOnly(BufferProxy $this) {
/*  47 */     return $this.self().readOnly();
/*     */   }
/*     */   
/*     */   public static BufferProxy $plus$plus$eq(BufferProxy $this, TraversableOnce xs) {
/*  54 */     $this.self().$plus$plus$eq(xs);
/*  54 */     return $this;
/*     */   }
/*     */   
/*     */   public static void append(BufferProxy $this, Seq elems) {
/*  60 */     $this.self().$plus$plus$eq((TraversableOnce)elems);
/*     */   }
/*     */   
/*     */   public static void appendAll(BufferProxy $this, TraversableOnce xs) {
/*  66 */     $this.self().appendAll(xs);
/*     */   }
/*     */   
/*     */   public static BufferProxy $plus$eq$colon(BufferProxy<Object> $this, Object elem) {
/*  74 */     $this.self().$plus$eq$colon(elem);
/*  74 */     return $this;
/*     */   }
/*     */   
/*     */   public static BufferProxy $plus$plus$eq$colon(BufferProxy $this, TraversableOnce xs) {
/*  76 */     $this.self().$plus$plus$eq$colon(xs);
/*  76 */     return $this;
/*     */   }
/*     */   
/*     */   public static void prepend(BufferProxy $this, Seq elems) {
/*  82 */     $this.self().prependAll((TraversableOnce)elems);
/*     */   }
/*     */   
/*     */   public static void prependAll(BufferProxy $this, TraversableOnce xs) {
/*  89 */     $this.self().prependAll(xs);
/*     */   }
/*     */   
/*     */   public static void insert(BufferProxy $this, int n, Seq elems) {
/*  98 */     $this.self().insertAll(n, (Traversable)elems);
/*     */   }
/*     */   
/*     */   public static void insertAll(BufferProxy $this, int n, Iterable iter) {
/* 108 */     $this.self().insertAll(n, (Traversable)iter);
/*     */   }
/*     */   
/*     */   public static void insertAll(BufferProxy $this, int n, Traversable iter) {
/* 112 */     $this.self().insertAll(n, iter);
/*     */   }
/*     */   
/*     */   public static void update(BufferProxy<Object> $this, int n, Object newelem) {
/* 120 */     $this.self().update(n, newelem);
/*     */   }
/*     */   
/*     */   public static Object remove(BufferProxy $this, int n) {
/* 126 */     return $this.self().remove(n);
/*     */   }
/*     */   
/*     */   public static void clear(BufferProxy $this) {
/* 130 */     $this.self().clear();
/*     */   }
/*     */   
/*     */   public static void $less$less(BufferProxy $this, Message cmd) {
/* 136 */     $this.self().$less$less(cmd);
/*     */   }
/*     */   
/*     */   public static Buffer clone(BufferProxy<A> $this) {
/* 142 */     return new BufferProxy$$anon$1($this);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\BufferProxy$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */