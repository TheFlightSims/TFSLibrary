/*     */ package scala.collection.mutable;
/*     */ 
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import scala.Serializable;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.AbstractIterator;
/*     */ import scala.collection.GenMap;
/*     */ import scala.collection.Map;
/*     */ import scala.collection.generic.CanBuildFrom;
/*     */ import scala.collection.generic.GenMapFactory;
/*     */ import scala.collection.generic.MutableMapFactory;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxedUnit;
/*     */ 
/*     */ public final class LinkedHashMap$ extends MutableMapFactory<LinkedHashMap> implements Serializable {
/*     */   public static final LinkedHashMap$ MODULE$;
/*     */   
/*     */   private Object readResolve() {
/*  20 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private LinkedHashMap$() {
/*  20 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public <A, B> CanBuildFrom<LinkedHashMap<?, ?>, Tuple2<A, B>, LinkedHashMap<A, B>> canBuildFrom() {
/*  21 */     return (CanBuildFrom<LinkedHashMap<?, ?>, Tuple2<A, B>, LinkedHashMap<A, B>>)new GenMapFactory.MapCanBuildFrom((GenMapFactory)this);
/*     */   }
/*     */   
/*     */   public <A, B> LinkedHashMap<A, B> empty() {
/*  22 */     return new LinkedHashMap<A, B>();
/*     */   }
/*     */   
/*     */   public class LinkedHashMap$$anon$1 extends AbstractIterator<Tuple2<A, B>> {
/*     */     private LinkedEntry<A, B> cur;
/*     */     
/*     */     public LinkedHashMap$$anon$1(LinkedHashMap<A, B> $outer) {
/*  91 */       this.cur = $outer.firstEntry();
/*     */     }
/*     */     
/*     */     private LinkedEntry<A, B> cur() {
/*  91 */       return this.cur;
/*     */     }
/*     */     
/*     */     private void cur_$eq(LinkedEntry<A, B> x$1) {
/*  91 */       this.cur = x$1;
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/*  92 */       return (cur() != null);
/*     */     }
/*     */     
/*     */     public Tuple2<A, B> next() {
/*  94 */       Tuple2<A, B> res = new Tuple2(cur().key(), cur().value());
/*  94 */       cur_$eq(cur().later());
/*  94 */       return hasNext() ? res : 
/*  95 */         (Tuple2<A, B>)scala.collection.Iterator$.MODULE$.empty().next();
/*     */     }
/*     */   }
/*     */   
/*     */   public class LinkedHashMap$$anon$2 extends AbstractIterator<A> {
/*     */     private LinkedEntry<A, B> cur;
/*     */     
/*     */     public LinkedHashMap$$anon$2(LinkedHashMap<A, B> $outer) {
/* 117 */       this.cur = $outer.firstEntry();
/*     */     }
/*     */     
/*     */     private LinkedEntry<A, B> cur() {
/* 117 */       return this.cur;
/*     */     }
/*     */     
/*     */     private void cur_$eq(LinkedEntry<A, B> x$1) {
/* 117 */       this.cur = x$1;
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/* 118 */       return (cur() != null);
/*     */     }
/*     */     
/*     */     public A next() {
/* 120 */       Object res = cur().key();
/* 120 */       cur_$eq(cur().later());
/* 120 */       return hasNext() ? (A)res : 
/* 121 */         (A)scala.collection.Iterator$.MODULE$.empty().next();
/*     */     }
/*     */   }
/*     */   
/*     */   public class LinkedHashMap$$anon$3 extends AbstractIterator<B> {
/*     */     private LinkedEntry<A, B> cur;
/*     */     
/*     */     public LinkedHashMap$$anon$3(LinkedHashMap<A, B> $outer) {
/* 125 */       this.cur = $outer.firstEntry();
/*     */     }
/*     */     
/*     */     private LinkedEntry<A, B> cur() {
/* 125 */       return this.cur;
/*     */     }
/*     */     
/*     */     private void cur_$eq(LinkedEntry<A, B> x$1) {
/* 125 */       this.cur = x$1;
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/* 126 */       return (cur() != null);
/*     */     }
/*     */     
/*     */     public B next() {
/* 128 */       Object res = cur().value();
/* 128 */       cur_$eq(cur().later());
/* 128 */       return hasNext() ? (B)res : 
/* 129 */         (B)scala.collection.Iterator$.MODULE$.empty().next();
/*     */     }
/*     */   }
/*     */   
/*     */   public class LinkedHashMap$$anonfun$writeObject$1 extends AbstractFunction1<LinkedEntry<A, B>, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final ObjectOutputStream out$1;
/*     */     
/*     */     public LinkedHashMap$$anonfun$writeObject$1(LinkedHashMap $outer, ObjectOutputStream out$1) {}
/*     */     
/*     */     public final void apply(LinkedEntry entry) {
/* 163 */       this.out$1.writeObject(entry.key());
/* 164 */       this.out$1.writeObject(entry.value());
/*     */     }
/*     */   }
/*     */   
/*     */   public class LinkedHashMap$$anonfun$readObject$1 extends AbstractFunction0<LinkedEntry<A, B>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final ObjectInputStream in$1;
/*     */     
/*     */     public final LinkedEntry<A, B> apply() {
/* 171 */       return this.$outer.createNewEntry((A)this.in$1.readObject(), this.in$1.readObject());
/*     */     }
/*     */     
/*     */     public LinkedHashMap$$anonfun$readObject$1(LinkedHashMap $outer, ObjectInputStream in$1) {}
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\LinkedHashMap$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */