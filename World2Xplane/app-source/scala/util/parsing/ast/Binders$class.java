/*     */ package scala.util.parsing.ast;
/*     */ 
/*     */ import scala.Function1;
/*     */ import scala.collection.TraversableLike;
/*     */ import scala.collection.generic.CanBuildFrom;
/*     */ import scala.collection.immutable.List;
/*     */ import scala.collection.immutable.List$;
/*     */ import scala.collection.immutable.Nil$;
/*     */ 
/*     */ public abstract class Binders$class {
/*     */   public static void $init$(Binders $this) {}
/*     */   
/*     */   public static Mappable.Mappable UnderBinderIsMappable(Binders $this, Binders.UnderBinder ub, Function1 evidence$8, Function1 evidence$9) {
/* 263 */     return new Binders$$anon$7($this, ub, evidence$8, evidence$9);
/*     */   }
/*     */   
/*     */   public static Mappable.Mappable ScopeIsMappable(Binders $this, Binders.Scope scope, Function1 evidence$10) {
/* 268 */     return new Binders$$anon$8($this, scope, evidence$10);
/*     */   }
/*     */   
/*     */   public static Mappable.Mappable NameElementIsMappable(Binders $this, AbstractSyntax.NameElement self) {
/* 275 */     return new Binders$$anon$9($this, self);
/*     */   }
/*     */   
/*     */   public static Binders.UnderBinder sequence(Binders $this, List orig, Function1 evidence$13) {
/* 298 */     Binders$$anonfun$sequence$1 binders$$anonfun$sequence$1 = new Binders$$anonfun$sequence$1($this, evidence$13);
/* 298 */     Nil$ nil$ = Nil$.MODULE$;
/* 298 */     Binders.UnderBinder$ underBinder$1 = $this.UnderBinder();
/* 298 */     Binders.Scope<AbstractSyntax.NameElement> scope1 = new Binders.Scope<AbstractSyntax.NameElement>(underBinder$1.$outer);
/* 298 */     Binders.UnderBinder$ underBinder$2 = underBinder$1.$outer.UnderBinder();
/* 299 */     Binders$$anonfun$sequence$3 binders$$anonfun$sequence$3 = new Binders$$anonfun$sequence$3($this, evidence$13);
/* 299 */     Object object = orig.map((Function1)new Binders$$anonfun$sequence$2($this), (CanBuildFrom)List$.MODULE$.ReusableCBF());
/* 299 */     Binders.Scope<AbstractSyntax.NameElement> scope2 = ((Binders.UnderBinder)orig.apply(0)).scope();
/* 299 */     Binders.UnderBinder$ underBinder$3 = $this.UnderBinder();
/* 299 */     return orig.isEmpty() ? new Binders.UnderBinder<AbstractSyntax.NameElement, Nil$>(underBinder$2.$outer, scope1, nil$, (Function1<Nil$, Mappable.Mappable<Nil$>>)binders$$anonfun$sequence$1) : new Binders.UnderBinder<AbstractSyntax.NameElement, Object>(underBinder$3.$outer, scope2, object, (Function1<Object, Mappable.Mappable<Object>>)binders$$anonfun$sequence$3);
/*     */   }
/*     */   
/*     */   public static List unsequence(Binders $this, Binders.UnderBinder orig, Function1 evidence$14) {
/* 303 */     return (List)((TraversableLike)orig.scala$util$parsing$ast$Binders$$element()).map((Function1)new Binders$$anonfun$unsequence$1($this, orig, evidence$14), List$.MODULE$.canBuildFrom());
/*     */   }
/*     */   
/*     */   public static Binders.ReturnAndDo return_(Binders $this, Object result) {
/* 338 */     return new Binders$$anon$11($this, result);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\parsing\ast\Binders$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */