/*     */ package scala.runtime;
/*     */ 
/*     */ import scala.Function3;
/*     */ import scala.Serializable;
/*     */ import scala.Tuple3;
/*     */ import scala.collection.IterableLike;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.TraversableLike;
/*     */ import scala.collection.TraversableOnce;
/*     */ import scala.collection.generic.CanBuildFrom;
/*     */ import scala.collection.mutable.Builder;
/*     */ 
/*     */ public final class Tuple3Zipped$ {
/*     */   public static final Tuple3Zipped$ MODULE$;
/*     */   
/*     */   public final <El1, Repr1, El2, Repr2, El3, Repr3> int hashCode$extension(Tuple3 $this) {
/*  27 */     return $this.hashCode();
/*     */   }
/*     */   
/*     */   public final <El1, Repr1, El2, Repr2, El3, Repr3> boolean equals$extension(Tuple3 $this, Object x$1) {
/*     */     // Byte code:
/*     */     //   0: aload_2
/*     */     //   1: instanceof scala/runtime/Tuple3Zipped
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
/*     */     //   27: checkcast scala/runtime/Tuple3Zipped
/*     */     //   30: invokevirtual colls : ()Lscala/Tuple3;
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
/*     */     //   #27	-> 0
/*     */     //   #236	-> 7
/*     */     //   #27	-> 14
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	71	0	this	Lscala/runtime/Tuple3Zipped$;
/*     */     //   0	71	1	$this	Lscala/Tuple3;
/*     */     //   0	71	2	x$1	Ljava/lang/Object;
/*     */   }
/*     */   
/*     */   public final <B, To, El1, Repr1, El2, Repr2, El3, Repr3> To map$extension(Tuple3 $this, Function3 f, CanBuildFrom cbf) {
/*  30 */     Object object = new Object();
/*     */     try {
/*  31 */       Builder b = cbf.apply(((TraversableLike)$this._1()).repr());
/*  32 */       Iterator elems2 = ((IterableLike)$this._2()).iterator();
/*  33 */       Iterator elems3 = ((IterableLike)$this._3()).iterator();
/*  33 */       ((TraversableLike)$this
/*     */         
/*  35 */         ._1()).foreach(new Tuple3Zipped$$anonfun$map$extension$1(b, elems2, elems3, f, object));
/*     */     } catch (NonLocalReturnControl nonLocalReturnControl1) {
/*     */       NonLocalReturnControl<?> nonLocalReturnControl;
/*     */       if ((nonLocalReturnControl = null).key() == object)
/*     */         return (To)nonLocalReturnControl.value(); 
/*     */     } 
/*  41 */     return (To)b.result();
/*     */   }
/*     */   
/*     */   public static class Tuple3Zipped$$anonfun$map$extension$1 extends AbstractFunction1<El1, Builder<B, To>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Builder b$1;
/*     */     
/*     */     private final Iterator elems2$1;
/*     */     
/*     */     private final Iterator elems3$1;
/*     */     
/*     */     private final Function3 f$1;
/*     */     
/*     */     private final Object nonLocalReturnKey1$1;
/*     */     
/*     */     public Tuple3Zipped$$anonfun$map$extension$1(Builder b$1, Iterator elems2$1, Iterator elems3$1, Function3 f$1, Object nonLocalReturnKey1$1) {}
/*     */     
/*     */     public final Builder<B, To> apply(Object el1) {
/*     */       if (this.elems2$1.hasNext() && this.elems3$1.hasNext())
/*     */         return this.b$1.$plus$eq(this.f$1.apply(el1, this.elems2$1.next(), this.elems3$1.next())); 
/*     */       throw new NonLocalReturnControl(this.nonLocalReturnKey1$1, this.b$1.result());
/*     */     }
/*     */   }
/*     */   
/*     */   public final <B, To, El1, Repr1, El2, Repr2, El3, Repr3> To flatMap$extension(Tuple3 $this, Function3 f, CanBuildFrom cbf) {
/*  44 */     Object object = new Object();
/*     */     try {
/*  45 */       Builder b = cbf.apply(((TraversableLike)$this._1()).repr());
/*  46 */       Iterator elems2 = ((IterableLike)$this._2()).iterator();
/*  47 */       Iterator elems3 = ((IterableLike)$this._3()).iterator();
/*  47 */       ((TraversableLike)$this
/*     */         
/*  49 */         ._1()).foreach(new Tuple3Zipped$$anonfun$flatMap$extension$1(b, elems2, elems3, f, object));
/*     */     } catch (NonLocalReturnControl nonLocalReturnControl1) {
/*     */       NonLocalReturnControl<?> nonLocalReturnControl;
/*     */       if ((nonLocalReturnControl = null).key() == object)
/*     */         return (To)nonLocalReturnControl.value(); 
/*     */     } 
/*  55 */     return (To)b.result();
/*     */   }
/*     */   
/*     */   public static class Tuple3Zipped$$anonfun$flatMap$extension$1 extends AbstractFunction1<El1, Builder<B, To>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Builder b$2;
/*     */     
/*     */     private final Iterator elems2$2;
/*     */     
/*     */     private final Iterator elems3$2;
/*     */     
/*     */     private final Function3 f$2;
/*     */     
/*     */     private final Object nonLocalReturnKey2$1;
/*     */     
/*     */     public Tuple3Zipped$$anonfun$flatMap$extension$1(Builder b$2, Iterator elems2$2, Iterator elems3$2, Function3 f$2, Object nonLocalReturnKey2$1) {}
/*     */     
/*     */     public final Builder<B, To> apply(Object el1) {
/*     */       if (this.elems2$2.hasNext() && this.elems3$2.hasNext())
/*     */         return (Builder<B, To>)this.b$2.$plus$plus$eq((TraversableOnce)this.f$2.apply(el1, this.elems2$2.next(), this.elems3$2.next())); 
/*     */       throw new NonLocalReturnControl(this.nonLocalReturnKey2$1, this.b$2.result());
/*     */     }
/*     */   }
/*     */   
/*     */   public final <To1, To2, To3, El1, Repr1, El2, Repr2, El3, Repr3> Tuple3<To1, To2, To3> filter$extension(Tuple3 $this, Function3 f, CanBuildFrom cbf1, CanBuildFrom cbf2, CanBuildFrom cbf3) {
/*  58 */     Object object = new Object();
/*     */     try {
/*  62 */       Builder b1 = cbf1.apply(((TraversableLike)$this._1()).repr());
/*  63 */       Builder b2 = cbf2.apply(((TraversableLike)$this._2()).repr());
/*  64 */       Builder b3 = cbf3.apply(((TraversableLike)$this._3()).repr());
/*  65 */       Iterator elems2 = ((IterableLike)$this._2()).iterator();
/*  66 */       Iterator elems3 = ((IterableLike)$this._3()).iterator();
/*  66 */       ((TraversableLike)$this
/*     */         
/*  69 */         ._1()).foreach(new Tuple3Zipped$$anonfun$filter$extension$1(b1, b2, b3, elems2, elems3, f, object));
/*     */     } catch (NonLocalReturnControl nonLocalReturnControl1) {
/*     */       NonLocalReturnControl<?> nonLocalReturnControl;
/*     */       if ((nonLocalReturnControl = null).key() == object)
/*     */         return (Tuple3<To1, To2, To3>)nonLocalReturnControl.value(); 
/*     */     } 
/*  83 */     return scala$runtime$Tuple3Zipped$$result$1(b1, b2, b3);
/*     */   }
/*     */   
/*     */   public final Tuple3 scala$runtime$Tuple3Zipped$$result$1(Builder b1$1, Builder b2$1, Builder b3$1) {
/*     */     return new Tuple3(b1$1.result(), b2$1.result(), b3$1.result());
/*     */   }
/*     */   
/*     */   public static class Tuple3Zipped$$anonfun$filter$extension$1 extends AbstractFunction1<El1, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Builder b1$1;
/*     */     
/*     */     private final Builder b2$1;
/*     */     
/*     */     private final Builder b3$1;
/*     */     
/*     */     private final Iterator elems2$3;
/*     */     
/*     */     private final Iterator elems3$3;
/*     */     
/*     */     private final Function3 f$3;
/*     */     
/*     */     private final Object nonLocalReturnKey3$1;
/*     */     
/*     */     public Tuple3Zipped$$anonfun$filter$extension$1(Builder b1$1, Builder b2$1, Builder b3$1, Iterator elems2$3, Iterator elems3$3, Function3 f$3, Object nonLocalReturnKey3$1) {}
/*     */     
/*     */     public final Object apply(Object el1) {
/*     */       if (this.elems2$3.hasNext() && this.elems3$3.hasNext()) {
/*     */         Object el2 = this.elems2$3.next();
/*     */         Object el3 = this.elems3$3.next();
/*     */         this.b1$1.$plus$eq(el1);
/*     */         this.b2$1.$plus$eq(el2);
/*     */         return BoxesRunTime.unboxToBoolean(this.f$3.apply(el1, el2, el3)) ? this.b3$1.$plus$eq(el3) : BoxedUnit.UNIT;
/*     */       } 
/*     */       throw new NonLocalReturnControl(this.nonLocalReturnKey3$1, Tuple3Zipped$.MODULE$.scala$runtime$Tuple3Zipped$$result$1(this.b1$1, this.b2$1, this.b3$1));
/*     */     }
/*     */   }
/*     */   
/*     */   public final <El1, Repr1, El2, Repr2, El3, Repr3> boolean exists$extension(Tuple3 $this, Function3 f) {
/*  86 */     Object object = new Object();
/*     */     try {
/*  87 */       Iterator elems2 = ((IterableLike)$this._2()).iterator();
/*  88 */       Iterator elems3 = ((IterableLike)$this._3()).iterator();
/*  88 */       ((TraversableLike)$this
/*     */         
/*  90 */         ._1()).foreach(new Tuple3Zipped$$anonfun$exists$extension$1(elems2, elems3, f, object));
/*     */     } catch (NonLocalReturnControl nonLocalReturnControl1) {
/*     */       NonLocalReturnControl<?> nonLocalReturnControl;
/*     */       if ((nonLocalReturnControl = null).key() == object)
/*     */         return nonLocalReturnControl.value$mcZ$sp(); 
/*     */     } 
/*     */     return false;
/*     */   }
/*     */   
/*     */   public static class Tuple3Zipped$$anonfun$exists$extension$1 extends AbstractFunction1<El1, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Iterator elems2$4;
/*     */     
/*     */     private final Iterator elems3$4;
/*     */     
/*     */     private final Function3 f$4;
/*     */     
/*     */     private final Object nonLocalReturnKey4$1;
/*     */     
/*     */     public Tuple3Zipped$$anonfun$exists$extension$1(Iterator elems2$4, Iterator elems3$4, Function3 f$4, Object nonLocalReturnKey4$1) {}
/*     */     
/*     */     public final void apply(Object el1) {
/*  91 */       if (this.elems2$4.hasNext() && this.elems3$4.hasNext()) {
/*  92 */         if (BoxesRunTime.unboxToBoolean(this.f$4.apply(el1, this.elems2$4.next(), this.elems3$4.next())))
/*  93 */           throw new NonLocalReturnControl$mcZ$sp(this.nonLocalReturnKey4$1, true); 
/*     */         return;
/*     */       } 
/*  95 */       throw new NonLocalReturnControl$mcZ$sp(this.nonLocalReturnKey4$1, false);
/*     */     }
/*     */   }
/*     */   
/*     */   public final <El1, Repr1, El2, Repr2, El3, Repr3> boolean forall$extension(Tuple3<TraversableLike<?, ?>, IterableLike<?, ?>, IterableLike<?, ?>> $this, Function3 f) {
/* 101 */     return !exists$extension($this, new Tuple3Zipped$$anonfun$forall$extension$1(f));
/*     */   }
/*     */   
/*     */   public static class Tuple3Zipped$$anonfun$forall$extension$1 extends AbstractFunction3<El1, El2, El3, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Function3 f$5;
/*     */     
/*     */     public final boolean apply(Object x, Object y, Object z) {
/* 101 */       return !BoxesRunTime.unboxToBoolean(this.f$5.apply(x, y, z));
/*     */     }
/*     */     
/*     */     public Tuple3Zipped$$anonfun$forall$extension$1(Function3 f$5) {}
/*     */   }
/*     */   
/*     */   public final <U, El1, Repr1, El2, Repr2, El3, Repr3> void foreach$extension(Tuple3 $this, Function3 f) {
/* 103 */     Object object = new Object();
/*     */     try {
/* 104 */       Iterator elems2 = ((IterableLike)$this._2()).iterator();
/* 105 */       Iterator elems3 = ((IterableLike)$this._3()).iterator();
/* 105 */       ((TraversableLike)$this
/*     */         
/* 107 */         ._1()).foreach(new Tuple3Zipped$$anonfun$foreach$extension$1(elems2, elems3, f, object));
/*     */     } catch (NonLocalReturnControl nonLocalReturnControl1) {
/*     */       NonLocalReturnControl<?> nonLocalReturnControl;
/*     */       if ((nonLocalReturnControl = null).key() == object) {
/*     */         nonLocalReturnControl.value$mcV$sp();
/*     */         return;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static class Tuple3Zipped$$anonfun$foreach$extension$1 extends AbstractFunction1<El1, U> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Iterator elems2$5;
/*     */     
/*     */     private final Iterator elems3$5;
/*     */     
/*     */     private final Function3 f$6;
/*     */     
/*     */     private final Object nonLocalReturnKey5$1;
/*     */     
/*     */     public Tuple3Zipped$$anonfun$foreach$extension$1(Iterator elems2$5, Iterator elems3$5, Function3 f$6, Object nonLocalReturnKey5$1) {}
/*     */     
/*     */     public final U apply(Object el1) {
/* 108 */       if (this.elems2$5.hasNext() && this.elems3$5.hasNext())
/* 108 */         return 
/* 109 */           (U)this.f$6.apply(el1, this.elems2$5.next(), this.elems3$5.next()); 
/* 111 */       throw new NonLocalReturnControl$mcV$sp(this.nonLocalReturnKey5$1, BoxedUnit.UNIT);
/*     */     }
/*     */   }
/*     */   
/*     */   private Tuple3Zipped$() {
/* 116 */     MODULE$ = this;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\runtime\Tuple3Zipped$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */