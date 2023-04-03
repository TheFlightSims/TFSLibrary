/*     */ package scala.collection;
/*     */ 
/*     */ import scala.collection.generic.CanBuildFrom;
/*     */ import scala.collection.generic.GenTraversableFactory;
/*     */ import scala.collection.generic.TraversableFactory;
/*     */ import scala.collection.immutable.Traversable$;
/*     */ import scala.collection.mutable.Builder;
/*     */ import scala.util.control.Breaks;
/*     */ 
/*     */ public final class Traversable$ extends GenTraversableFactory<Traversable> implements TraversableFactory<Traversable> {
/*     */   public static final Traversable$ MODULE$;
/*     */   
/*     */   private final Breaks breaks;
/*     */   
/*     */   private Traversable$() {
/*  93 */     MODULE$ = this;
/*  96 */     this.breaks = new Breaks();
/*     */   }
/*     */   
/*     */   public Breaks breaks() {
/*  96 */     return this.breaks;
/*     */   }
/*     */   
/*     */   public <A> CanBuildFrom<Traversable<?>, A, Traversable<A>> canBuildFrom() {
/*  99 */     return (CanBuildFrom<Traversable<?>, A, Traversable<A>>)ReusableCBF();
/*     */   }
/*     */   
/*     */   public <A> Builder<A, Traversable<A>> newBuilder() {
/* 101 */     return Traversable$.MODULE$.newBuilder();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\Traversable$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */