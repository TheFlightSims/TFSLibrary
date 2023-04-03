/*     */ package scala.collection.mutable;
/*     */ 
/*     */ import scala.Serializable;
/*     */ import scala.collection.GenTraversable;
/*     */ import scala.collection.generic.CanBuildFrom;
/*     */ import scala.collection.generic.MutableSetFactory;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxedUnit;
/*     */ 
/*     */ public final class HashSet$ extends MutableSetFactory<HashSet> implements Serializable {
/*     */   public static final HashSet$ MODULE$;
/*     */   
/*     */   public class HashSet$$anonfun$readObject$1 extends AbstractFunction1<A, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final void apply(Object x) {}
/*     */     
/*     */     public HashSet$$anonfun$readObject$1(HashSet $outer) {}
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 106 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private HashSet$() {
/* 106 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public <A> CanBuildFrom<HashSet<?>, A, HashSet<A>> canBuildFrom() {
/* 107 */     return setCanBuildFrom();
/*     */   }
/*     */   
/*     */   public <A> HashSet<A> empty() {
/* 108 */     return new HashSet<A>();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\HashSet$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */