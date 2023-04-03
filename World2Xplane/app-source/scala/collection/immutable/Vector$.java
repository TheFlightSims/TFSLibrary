/*     */ package scala.collection.immutable;
/*     */ 
/*     */ import scala.Serializable;
/*     */ import scala.collection.AbstractIterator;
/*     */ import scala.collection.GenTraversable;
/*     */ import scala.collection.generic.CanBuildFrom;
/*     */ import scala.collection.generic.GenTraversableFactory;
/*     */ import scala.collection.generic.SeqFactory;
/*     */ import scala.collection.mutable.Builder;
/*     */ 
/*     */ public final class Vector$ extends SeqFactory<Vector> implements Serializable {
/*     */   public static final Vector$ MODULE$;
/*     */   
/*     */   private final GenTraversableFactory<Vector>.GenericCanBuildFrom<scala.runtime.Nothing$> VectorReusableCBF;
/*     */   
/*     */   private GenTraversableFactory<Vector>.GenericCanBuildFrom<scala.runtime.Nothing$> ReusableCBF;
/*     */   
/*     */   private final Vector<scala.runtime.Nothing$> NIL;
/*     */   
/*     */   private volatile boolean bitmap$0;
/*     */   
/*     */   private Object readResolve() {
/*  21 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private Vector$() {
/*  21 */     MODULE$ = this;
/*  27 */     this.VectorReusableCBF = new Vector.VectorReusableCBF();
/*  34 */     this.NIL = new Vector<scala.runtime.Nothing$>(0, 0, 0);
/*     */   }
/*     */   
/*     */   private GenTraversableFactory<Vector>.GenericCanBuildFrom<scala.runtime.Nothing$> VectorReusableCBF() {
/*     */     return this.VectorReusableCBF;
/*     */   }
/*     */   
/*     */   private GenTraversableFactory.GenericCanBuildFrom ReusableCBF$lzycompute() {
/*     */     synchronized (this) {
/*     */       if (!this.bitmap$0) {
/*     */         this.ReusableCBF = scala.collection.IndexedSeq$.MODULE$.ReusableCBF();
/*     */         this.bitmap$0 = true;
/*     */       } 
/*     */       /* monitor exit ThisExpression{ObjectType{scala/collection/immutable/Vector$}} */
/*     */       return this.ReusableCBF;
/*     */     } 
/*     */   }
/*     */   
/*     */   public GenTraversableFactory<Vector>.GenericCanBuildFrom<scala.runtime.Nothing$> ReusableCBF() {
/*     */     return this.bitmap$0 ? this.ReusableCBF : ReusableCBF$lzycompute();
/*     */   }
/*     */   
/*     */   public <A> Builder<A, Vector<A>> newBuilder() {
/*     */     return new VectorBuilder<A>();
/*     */   }
/*     */   
/*     */   public <A> CanBuildFrom<Vector<?>, A, Vector<A>> canBuildFrom() {
/*     */     return (CanBuildFrom)ReusableCBF();
/*     */   }
/*     */   
/*     */   public Vector<scala.runtime.Nothing$> NIL() {
/*  34 */     return this.NIL;
/*     */   }
/*     */   
/*     */   public <A> Vector<A> empty() {
/*  35 */     return (Vector)NIL();
/*     */   }
/*     */   
/*     */   public class Vector$$anon$1 extends AbstractIterator<A> {
/*     */     private int i;
/*     */     
/*     */     public Vector$$anon$1(Vector $outer) {
/* 110 */       this.i = $outer.length();
/*     */     }
/*     */     
/*     */     private int i() {
/* 110 */       return this.i;
/*     */     }
/*     */     
/*     */     private void i_$eq(int x$1) {
/* 110 */       this.i = x$1;
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/* 111 */       return (0 < i());
/*     */     }
/*     */     
/*     */     public A next() {
/* 114 */       i_$eq(i() - 1);
/* 115 */       return (0 < i()) ? this.$outer.apply(i()) : 
/* 116 */         (A)scala.collection.Iterator$.MODULE$.empty().next();
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\immutable\Vector$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */