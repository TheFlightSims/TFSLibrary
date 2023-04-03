/*     */ package scala.runtime;
/*     */ 
/*     */ import scala.Function2;
/*     */ import scala.Serializable;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.IterableLike;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.TraversableLike;
/*     */ import scala.collection.TraversableOnce;
/*     */ import scala.collection.generic.CanBuildFrom;
/*     */ import scala.collection.mutable.Builder;
/*     */ 
/*     */ public final class Tuple2Zipped$ {
/*     */   public static final Tuple2Zipped$ MODULE$;
/*     */   
/*     */   public final <El1, Repr1, El2, Repr2> int hashCode$extension(Tuple2 $this) {
/*  30 */     return $this.hashCode();
/*     */   }
/*     */   
/*     */   public final <El1, Repr1, El2, Repr2> boolean equals$extension(Tuple2 $this, Object x$1) {
/*     */     // Byte code:
/*     */     //   0: aload_2
/*     */     //   1: instanceof scala/runtime/Tuple2Zipped
/*     */     //   4: ifeq -> 12
/*     */     //   7: iconst_1
/*     */     //   8: istore_3
/*     */     //   9: goto -> 14
/*     */     //   12: iconst_0
/*     */     //   13: istore_3
/*     */     //   14: iload_3
/*     */     //   15: ifeq -> 69
/*     */     //   18: aload_2
/*     */     //   19: ifnonnull -> 26
/*     */     //   22: aconst_null
/*     */     //   23: goto -> 33
/*     */     //   26: aload_2
/*     */     //   27: checkcast scala/runtime/Tuple2Zipped
/*     */     //   30: invokevirtual colls : ()Lscala/Tuple2;
/*     */     //   33: astore #4
/*     */     //   35: aload_1
/*     */     //   36: dup
/*     */     //   37: ifnonnull -> 49
/*     */     //   40: pop
/*     */     //   41: aload #4
/*     */     //   43: ifnull -> 57
/*     */     //   46: goto -> 61
/*     */     //   49: aload #4
/*     */     //   51: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   54: ifeq -> 61
/*     */     //   57: iconst_1
/*     */     //   58: goto -> 62
/*     */     //   61: iconst_0
/*     */     //   62: ifeq -> 69
/*     */     //   65: iconst_1
/*     */     //   66: goto -> 70
/*     */     //   69: iconst_0
/*     */     //   70: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #30	-> 0
/*     */     //   #236	-> 7
/*     */     //   #30	-> 14
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	71	0	this	Lscala/runtime/Tuple2Zipped$;
/*     */     //   0	71	1	$this	Lscala/Tuple2;
/*     */     //   0	71	2	x$1	Ljava/lang/Object;
/*     */   }
/*     */   
/*     */   public final <B, To, El1, Repr1, El2, Repr2> To map$extension(Tuple2 $this, Function2 f, CanBuildFrom cbf) {
/*  33 */     Object object = new Object();
/*     */     try {
/*  34 */       Builder b = cbf.apply(((TraversableLike)$this._1()).repr());
/*  35 */       b.sizeHint((TraversableLike)$this._1());
/*  36 */       Iterator elems2 = ((IterableLike)$this._2()).iterator();
/*  36 */       ((TraversableLike)$this
/*     */         
/*  38 */         ._1()).foreach(new Tuple2Zipped$$anonfun$map$extension$1(b, elems2, f, object));
/*     */     } catch (NonLocalReturnControl nonLocalReturnControl1) {
/*     */       NonLocalReturnControl<?> nonLocalReturnControl;
/*     */       if ((nonLocalReturnControl = null).key() == object)
/*     */         return (To)nonLocalReturnControl.value(); 
/*     */     } 
/*  45 */     return (To)b.result();
/*     */   }
/*     */   
/*     */   public static class Tuple2Zipped$$anonfun$map$extension$1 extends AbstractFunction1<El1, Builder<B, To>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Builder b$1;
/*     */     
/*     */     private final Iterator elems2$1;
/*     */     
/*     */     private final Function2 f$1;
/*     */     
/*     */     private final Object nonLocalReturnKey1$1;
/*     */     
/*     */     public Tuple2Zipped$$anonfun$map$extension$1(Builder b$1, Iterator elems2$1, Function2 f$1, Object nonLocalReturnKey1$1) {}
/*     */     
/*     */     public final Builder<B, To> apply(Object el1) {
/*     */       if (this.elems2$1.hasNext())
/*     */         return this.b$1.$plus$eq(this.f$1.apply(el1, this.elems2$1.next())); 
/*     */       throw new NonLocalReturnControl(this.nonLocalReturnKey1$1, this.b$1.result());
/*     */     }
/*     */   }
/*     */   
/*     */   public final <B, To, El1, Repr1, El2, Repr2> To flatMap$extension(Tuple2 $this, Function2 f, CanBuildFrom cbf) {
/*  48 */     Object object = new Object();
/*     */     try {
/*  49 */       Builder b = cbf.apply(((TraversableLike)$this._1()).repr());
/*  50 */       Iterator elems2 = ((IterableLike)$this._2()).iterator();
/*  50 */       ((TraversableLike)$this
/*     */         
/*  52 */         ._1()).foreach(new Tuple2Zipped$$anonfun$flatMap$extension$1(b, elems2, f, object));
/*     */     } catch (NonLocalReturnControl nonLocalReturnControl1) {
/*     */       NonLocalReturnControl<?> nonLocalReturnControl;
/*     */       if ((nonLocalReturnControl = null).key() == object)
/*     */         return (To)nonLocalReturnControl.value(); 
/*     */     } 
/*  59 */     return (To)b.result();
/*     */   }
/*     */   
/*     */   public static class Tuple2Zipped$$anonfun$flatMap$extension$1 extends AbstractFunction1<El1, Builder<B, To>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Builder b$2;
/*     */     
/*     */     private final Iterator elems2$2;
/*     */     
/*     */     private final Function2 f$2;
/*     */     
/*     */     private final Object nonLocalReturnKey2$1;
/*     */     
/*     */     public Tuple2Zipped$$anonfun$flatMap$extension$1(Builder b$2, Iterator elems2$2, Function2 f$2, Object nonLocalReturnKey2$1) {}
/*     */     
/*     */     public final Builder<B, To> apply(Object el1) {
/*     */       if (this.elems2$2.hasNext())
/*     */         return (Builder<B, To>)this.b$2.$plus$plus$eq((TraversableOnce)this.f$2.apply(el1, this.elems2$2.next())); 
/*     */       throw new NonLocalReturnControl(this.nonLocalReturnKey2$1, this.b$2.result());
/*     */     }
/*     */   }
/*     */   
/*     */   public final <To1, To2, El1, Repr1, El2, Repr2> Tuple2<To1, To2> filter$extension(Tuple2 $this, Function2 f, CanBuildFrom cbf1, CanBuildFrom cbf2) {
/*  62 */     Object object = new Object();
/*     */     try {
/*  63 */       Builder b1 = cbf1.apply(((TraversableLike)$this._1()).repr());
/*  64 */       Builder b2 = cbf2.apply(((TraversableLike)$this._2()).repr());
/*  65 */       Iterator elems2 = ((IterableLike)$this._2()).iterator();
/*  65 */       ((TraversableLike)$this
/*     */         
/*  67 */         ._1()).foreach(new Tuple2Zipped$$anonfun$filter$extension$1(b1, b2, elems2, f, object));
/*     */     } catch (NonLocalReturnControl nonLocalReturnControl1) {
/*     */       NonLocalReturnControl<?> nonLocalReturnControl;
/*     */       if ((nonLocalReturnControl = null).key() == object)
/*     */         return (Tuple2<To1, To2>)nonLocalReturnControl.value(); 
/*     */     } 
/*  78 */     return new Tuple2(b1.result(), b2.result());
/*     */   }
/*     */   
/*     */   public static class Tuple2Zipped$$anonfun$filter$extension$1 extends AbstractFunction1<El1, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Builder b1$1;
/*     */     
/*     */     private final Builder b2$1;
/*     */     
/*     */     private final Iterator elems2$3;
/*     */     
/*     */     private final Function2 f$3;
/*     */     
/*     */     private final Object nonLocalReturnKey3$1;
/*     */     
/*     */     public Tuple2Zipped$$anonfun$filter$extension$1(Builder b1$1, Builder b2$1, Iterator elems2$3, Function2 f$3, Object nonLocalReturnKey3$1) {}
/*     */     
/*     */     public final Object apply(Object el1) {
/*     */       if (this.elems2$3.hasNext()) {
/*     */         Object el2 = this.elems2$3.next();
/*     */         this.b1$1.$plus$eq(el1);
/*     */         return BoxesRunTime.unboxToBoolean(this.f$3.apply(el1, el2)) ? this.b2$1.$plus$eq(el2) : BoxedUnit.UNIT;
/*     */       } 
/*     */       throw new NonLocalReturnControl(this.nonLocalReturnKey3$1, new Tuple2(this.b1$1.result(), this.b2$1.result()));
/*     */     }
/*     */   }
/*     */   
/*     */   public final <El1, Repr1, El2, Repr2> boolean exists$extension(Tuple2 $this, Function2 f) {
/*  81 */     Object object = new Object();
/*     */     try {
/*  82 */       Iterator elems2 = ((IterableLike)$this._2()).iterator();
/*  82 */       ((TraversableLike)$this
/*     */         
/*  84 */         ._1()).foreach(new Tuple2Zipped$$anonfun$exists$extension$1(elems2, f, object));
/*     */     } catch (NonLocalReturnControl nonLocalReturnControl1) {
/*     */       NonLocalReturnControl<?> nonLocalReturnControl;
/*     */       if ((nonLocalReturnControl = null).key() == object)
/*     */         return nonLocalReturnControl.value$mcZ$sp(); 
/*     */     } 
/*     */     return false;
/*     */   }
/*     */   
/*     */   public static class Tuple2Zipped$$anonfun$exists$extension$1 extends AbstractFunction1<El1, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Iterator elems2$4;
/*     */     
/*     */     private final Function2 f$4;
/*     */     
/*     */     private final Object nonLocalReturnKey4$1;
/*     */     
/*     */     public Tuple2Zipped$$anonfun$exists$extension$1(Iterator elems2$4, Function2 f$4, Object nonLocalReturnKey4$1) {}
/*     */     
/*     */     public final void apply(Object el1) {
/*  85 */       if (this.elems2$4.hasNext()) {
/*  86 */         if (BoxesRunTime.unboxToBoolean(this.f$4.apply(el1, this.elems2$4.next())))
/*  87 */           throw new NonLocalReturnControl$mcZ$sp(this.nonLocalReturnKey4$1, true); 
/*     */         return;
/*     */       } 
/*  89 */       throw new NonLocalReturnControl$mcZ$sp(this.nonLocalReturnKey4$1, false);
/*     */     }
/*     */   }
/*     */   
/*     */   public final <El1, Repr1, El2, Repr2> boolean forall$extension(Tuple2<TraversableLike<?, ?>, IterableLike<?, ?>> $this, Function2 f) {
/*  95 */     return !exists$extension($this, new Tuple2Zipped$$anonfun$forall$extension$1(f));
/*     */   }
/*     */   
/*     */   public static class Tuple2Zipped$$anonfun$forall$extension$1 extends AbstractFunction2<El1, El2, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Function2 f$5;
/*     */     
/*     */     public final boolean apply(Object x, Object y) {
/*  95 */       return !BoxesRunTime.unboxToBoolean(this.f$5.apply(x, y));
/*     */     }
/*     */     
/*     */     public Tuple2Zipped$$anonfun$forall$extension$1(Function2 f$5) {}
/*     */   }
/*     */   
/*     */   public final <U, El1, Repr1, El2, Repr2> void foreach$extension(Tuple2 $this, Function2 f) {
/*  97 */     Object object = new Object();
/*     */     try {
/*  98 */       Iterator elems2 = ((IterableLike)$this._2()).iterator();
/*  98 */       ((TraversableLike)$this
/*     */         
/* 100 */         ._1()).foreach(new Tuple2Zipped$$anonfun$foreach$extension$1(elems2, f, object));
/*     */     } catch (NonLocalReturnControl nonLocalReturnControl1) {
/*     */       NonLocalReturnControl<?> nonLocalReturnControl;
/*     */       if ((nonLocalReturnControl = null).key() == object) {
/*     */         nonLocalReturnControl.value$mcV$sp();
/*     */         return;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static class Tuple2Zipped$$anonfun$foreach$extension$1 extends AbstractFunction1<El1, U> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Iterator elems2$5;
/*     */     
/*     */     private final Function2 f$6;
/*     */     
/*     */     private final Object nonLocalReturnKey5$1;
/*     */     
/*     */     public Tuple2Zipped$$anonfun$foreach$extension$1(Iterator elems2$5, Function2 f$6, Object nonLocalReturnKey5$1) {}
/*     */     
/*     */     public final U apply(Object el1) {
/* 101 */       if (this.elems2$5.hasNext())
/* 101 */         return 
/* 102 */           (U)this.f$6.apply(el1, this.elems2$5.next()); 
/* 104 */       throw new NonLocalReturnControl$mcV$sp(this.nonLocalReturnKey5$1, BoxedUnit.UNIT);
/*     */     }
/*     */   }
/*     */   
/*     */   private Tuple2Zipped$() {
/* 109 */     MODULE$ = this;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\runtime\Tuple2Zipped$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */