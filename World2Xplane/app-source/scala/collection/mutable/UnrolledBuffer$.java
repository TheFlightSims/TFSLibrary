/*     */ package scala.collection.mutable;
/*     */ 
/*     */ import java.io.ObjectOutputStream;
/*     */ import scala.Serializable;
/*     */ import scala.collection.AbstractIterator;
/*     */ import scala.collection.generic.CanBuildFrom;
/*     */ import scala.collection.generic.ClassTagTraversableFactory;
/*     */ import scala.reflect.ClassTag;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxedUnit;
/*     */ 
/*     */ public final class UnrolledBuffer$ extends ClassTagTraversableFactory<UnrolledBuffer> implements Serializable {
/*     */   public static final UnrolledBuffer$ MODULE$;
/*     */   
/*     */   private final int waterline;
/*     */   
/*     */   private final int waterlineDelim;
/*     */   
/*     */   private final int unrolledlength;
/*     */   
/*     */   public class UnrolledBuffer$$anon$1 extends AbstractIterator<T> {
/*     */     private int pos;
/*     */     
/*     */     private UnrolledBuffer.Unrolled<T> node;
/*     */     
/*     */     public UnrolledBuffer$$anon$1(UnrolledBuffer<T> $outer) {
/* 109 */       this.pos = -1;
/* 110 */       this.node = $outer.scala$collection$mutable$UnrolledBuffer$$headptr();
/* 111 */       scan();
/*     */     }
/*     */     
/*     */     private int pos() {
/*     */       return this.pos;
/*     */     }
/*     */     
/*     */     private void pos_$eq(int x$1) {
/*     */       this.pos = x$1;
/*     */     }
/*     */     
/*     */     private UnrolledBuffer.Unrolled<T> node() {
/*     */       return this.node;
/*     */     }
/*     */     
/*     */     private void node_$eq(UnrolledBuffer.Unrolled<T> x$1) {
/*     */       this.node = x$1;
/*     */     }
/*     */     
/*     */     private void scan() {
/* 114 */       pos_$eq(pos() + 1);
/* 115 */       while (pos() >= node().size()) {
/* 116 */         pos_$eq(0);
/* 117 */         node_$eq(node().next());
/* 118 */         if (node() == null)
/*     */           return; 
/*     */       } 
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/* 121 */       return (node() != null);
/*     */     }
/*     */     
/*     */     public T next() {
/* 123 */       Object r = scala.runtime.ScalaRunTime$.MODULE$.array_apply(node().array(), pos());
/* 124 */       scan();
/* 125 */       return hasNext() ? (T)r : 
/* 126 */         (T)scala.collection.Iterator$.MODULE$.empty().next();
/*     */     }
/*     */   }
/*     */   
/*     */   public class UnrolledBuffer$$anonfun$writeObject$1 extends AbstractFunction1<T, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final ObjectOutputStream out$1;
/*     */     
/*     */     public final void apply(Object elem) {
/* 165 */       this.out$1.writeObject(elem);
/*     */     }
/*     */     
/*     */     public UnrolledBuffer$$anonfun$writeObject$1(UnrolledBuffer $outer, ObjectOutputStream out$1) {}
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 189 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private UnrolledBuffer$() {
/* 189 */     MODULE$ = this;
/* 195 */     this.waterline = 50;
/* 196 */     this.waterlineDelim = 100;
/* 197 */     this.unrolledlength = 32;
/*     */   }
/*     */   
/*     */   public <T> CanBuildFrom<UnrolledBuffer<?>, T, UnrolledBuffer<T>> canBuildFrom(ClassTag t) {
/*     */     return (CanBuildFrom<UnrolledBuffer<?>, T, UnrolledBuffer<T>>)new ClassTagTraversableFactory.GenericCanBuildFrom(this, t);
/*     */   }
/*     */   
/*     */   public <T> Builder<T, UnrolledBuffer<T>> newBuilder(ClassTag<T> t) {
/*     */     return new UnrolledBuffer<T>(t);
/*     */   }
/*     */   
/*     */   public int waterline() {
/*     */     return this.waterline;
/*     */   }
/*     */   
/*     */   public int waterlineDelim() {
/*     */     return this.waterlineDelim;
/*     */   }
/*     */   
/*     */   public int unrolledlength() {
/* 197 */     return this.unrolledlength;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\UnrolledBuffer$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */