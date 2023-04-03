/*     */ package scala.collection.immutable;
/*     */ 
/*     */ import scala.Function1;
/*     */ import scala.Function2;
/*     */ import scala.MatchError;
/*     */ import scala.Serializable;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.GenTraversable;
/*     */ import scala.collection.Iterable;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.SeqLike;
/*     */ import scala.collection.generic.CanBuildFrom;
/*     */ import scala.collection.generic.SeqFactory;
/*     */ import scala.collection.mutable.Builder;
/*     */ import scala.collection.mutable.ListBuffer;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.AbstractFunction2;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.util.Either;
/*     */ import scala.util.Left;
/*     */ import scala.util.Right;
/*     */ 
/*     */ public final class List$ extends SeqFactory<List> {
/*     */   public static final List$ MODULE$;
/*     */   
/*     */   public class List$$anonfun$foldRight$1 extends AbstractFunction2<B, A, B> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Function2 op$1;
/*     */     
/*     */     public final B apply(Object right, Object left) {
/* 306 */       return (B)this.op$1.apply(left, right);
/*     */     }
/*     */     
/*     */     public List$$anonfun$foldRight$1(List $outer, Function2 op$1) {}
/*     */   }
/*     */   
/*     */   public class List$$anonfun$toStream$1 extends AbstractFunction0<Stream<A>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Stream<A> apply() {
/* 312 */       return ((List<A>)this.$outer.tail()).toStream();
/*     */     }
/*     */     
/*     */     public List$$anonfun$toStream$1(List $outer) {}
/*     */   }
/*     */   
/*     */   private List$() {
/* 388 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public <A> CanBuildFrom<List<?>, A, List<A>> canBuildFrom() {
/* 394 */     return (CanBuildFrom<List<?>, A, List<A>>)ReusableCBF();
/*     */   }
/*     */   
/*     */   public <A> Builder<A, List<A>> newBuilder() {
/* 396 */     return (Builder<A, List<A>>)new ListBuffer();
/*     */   }
/*     */   
/*     */   public <A> List<A> empty() {
/* 398 */     return Nil$.MODULE$;
/*     */   }
/*     */   
/*     */   public <A> List<A> apply(Seq xs) {
/* 400 */     return xs.toList();
/*     */   }
/*     */   
/*     */   public List<Object> range(int start, int end, Function1 step) {
/* 415 */     boolean up = (step.apply$mcII$sp(start) > start);
/* 416 */     boolean down = (step.apply$mcII$sp(start) < start);
/* 417 */     ListBuffer b = new ListBuffer();
/* 418 */     int i = start;
/*     */     while (true) {
/* 419 */       if ((up && i >= end) || (down && i <= end))
/* 426 */         return b.toList(); 
/*     */       b.$plus$eq(BoxesRunTime.boxToInteger(i));
/*     */       int next = step.apply$mcII$sp(i);
/*     */       if (i == next)
/*     */         throw new IllegalArgumentException((new StringBuilder()).append("the step function did not make any progress on ").append(BoxesRunTime.boxToInteger(i)).toString()); 
/*     */       i = next;
/*     */     } 
/*     */   }
/*     */   
/*     */   public <A> List<A> make(int n, Object elem) {
/* 437 */     ListBuffer b = new ListBuffer();
/* 438 */     int i = 0;
/* 439 */     while (i < n) {
/* 440 */       b.$plus$eq(elem);
/* 441 */       i++;
/*     */     } 
/* 443 */     return b.toList();
/*     */   }
/*     */   
/*     */   public <A> List<A> flatten(List<List> xss) {
/* 453 */     ListBuffer b = new ListBuffer();
/* 454 */     List<List> these1 = xss;
/*     */     label12: while (true) {
/* 454 */       if (these1.isEmpty())
/* 461 */         return b.toList(); 
/*     */       List xc1 = these1.head();
/*     */       while (true) {
/*     */         if (xc1.isEmpty()) {
/*     */           these1 = (List<List>)these1.tail();
/*     */           continue label12;
/*     */         } 
/*     */         b.$plus$eq(xc1.head());
/*     */         xc1 = (List)xc1.tail();
/*     */       } 
/*     */       break;
/*     */     } 
/*     */   }
/*     */   
/*     */   public <A, B> Tuple2<List<A>, List<B>> unzip(List<Tuple2> xs) {
/* 471 */     ListBuffer b1 = new ListBuffer();
/* 472 */     ListBuffer b2 = new ListBuffer();
/* 473 */     List<Tuple2> xc = xs;
/*     */     while (true) {
/* 474 */       if (xc.isEmpty())
/* 479 */         return new Tuple2(b1.toList(), b2.toList()); 
/*     */       b1.$plus$eq(((Tuple2)xc.head())._1());
/*     */       b2.$plus$eq(((Tuple2)xc.head())._2());
/*     */       xc = (List<Tuple2>)xc.tail();
/*     */     } 
/*     */   }
/*     */   
/*     */   public <A, B> Tuple2<List<A>, List<B>> unzip(Iterable xs) {
/* 489 */     return (Tuple2<List<A>, List<B>>)xs.foldRight(new Tuple2(Nil$.MODULE$, Nil$.MODULE$), (Function2)new List$$anonfun$unzip$1());
/*     */   }
/*     */   
/*     */   public static class List$$anonfun$unzip$1 extends AbstractFunction2<Tuple2<A, B>, Tuple2<List<A>, List<B>>, Tuple2<List<A>, List<B>>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Tuple2<List<A>, List<B>> apply(Tuple2 x0$1, Tuple2 x1$1) {
/* 489 */       Tuple2 tuple2 = new Tuple2(x0$1, x1$1);
/* 489 */       if (tuple2 != null && tuple2._1() != null && tuple2._2() != null) {
/* 490 */         Object object1 = ((Tuple2)tuple2._1())._1(), object2 = ((Tuple2)tuple2._1())._2();
/* 490 */         return new Tuple2(((List)((Tuple2)tuple2._2())._1()).$colon$colon(object1), ((List)((Tuple2)tuple2._2())._2()).$colon$colon(object2));
/*     */       } 
/*     */       throw new MatchError(tuple2);
/*     */     }
/*     */   }
/*     */   
/*     */   public <A, B> List<A> lefts(Iterable es) {
/* 498 */     return (List<A>)es.foldRight(Nil$.MODULE$, (Function2)new List$$anonfun$lefts$1());
/*     */   }
/*     */   
/*     */   public static class List$$anonfun$lefts$1 extends AbstractFunction2<Either<A, B>, List<A>, List<A>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final List<A> apply(Either e, List<A> as) {
/* 498 */       if (e instanceof Left) {
/* 498 */         Left left = (Left)e;
/* 499 */         Object object = left.a();
/* 499 */         List list = as.$colon$colon(object);
/*     */       } else {
/* 500 */         if (e instanceof Right)
/* 500 */           return as; 
/*     */         throw new MatchError(e);
/*     */       } 
/*     */       return (List<A>)SYNTHETIC_LOCAL_VARIABLE_5;
/*     */     }
/*     */   }
/*     */   
/*     */   public <A, B> List<B> rights(Iterable es) {
/* 508 */     return (List<B>)es.foldRight(Nil$.MODULE$, (Function2)new List$$anonfun$rights$1());
/*     */   }
/*     */   
/*     */   public static class List$$anonfun$rights$1 extends AbstractFunction2<Either<A, B>, List<B>, List<B>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final List<B> apply(Either e, List bs) {
/*     */       List<B> list;
/* 508 */       if (e instanceof Left) {
/* 508 */         list = bs;
/*     */       } else {
/* 510 */         if (e instanceof Right) {
/* 510 */           Right right = (Right)e;
/* 510 */           Object object = right.b();
/* 510 */           list = bs.$colon$colon(object);
/*     */         } 
/*     */         throw new MatchError(e);
/*     */       } 
/*     */       return list;
/*     */     }
/*     */   }
/*     */   
/*     */   public <A, B> Tuple2<List<A>, List<B>> separate(Iterable es) {
/* 520 */     return (Tuple2<List<A>, List<B>>)es.foldRight(new Tuple2(Nil$.MODULE$, Nil$.MODULE$), (Function2)new List$$anonfun$separate$1());
/*     */   }
/*     */   
/*     */   public static class List$$anonfun$separate$1 extends AbstractFunction2<Either<A, B>, Tuple2<List<A>, List<B>>, Tuple2<List<A>, List<B>>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Tuple2<List<A>, List<B>> apply(Either x0$2, Tuple2 x1$2) {
/* 520 */       Tuple2 tuple2 = new Tuple2(x0$2, x1$2);
/* 520 */       if (tuple2 != null && tuple2
/* 521 */         ._1() instanceof Left) {
/* 521 */         Left left = (Left)tuple2._1();
/* 521 */         if (tuple2._2() != null) {
/* 521 */           Object object = left.a();
/* 521 */           return new Tuple2(((List)((Tuple2)tuple2._2())._1()).$colon$colon(object), ((Tuple2)tuple2._2())._2());
/*     */         } 
/*     */       } 
/* 522 */       if (tuple2 != null && tuple2._1() instanceof Right) {
/* 522 */         Right right = (Right)tuple2._1();
/* 522 */         if (tuple2._2() != null);
/*     */       } 
/*     */       throw new MatchError(tuple2);
/*     */     }
/*     */   }
/*     */   
/*     */   public <A> List<A> fromIterator(Iterator it) {
/* 532 */     return it.toList();
/*     */   }
/*     */   
/*     */   public <A> List<A> fromArray(Object arr) {
/* 541 */     return fromArray(arr, 0, scala.runtime.ScalaRunTime$.MODULE$.array_length(arr));
/*     */   }
/*     */   
/*     */   public <A> List<A> fromArray(Object arr, int start, int len) {
/* 553 */     List<A> res = Nil$.MODULE$;
/* 554 */     int i = start + len;
/* 555 */     while (i > start) {
/* 556 */       i--;
/* 557 */       Object object = scala.runtime.ScalaRunTime$.MODULE$.array_apply(arr, i);
/* 557 */       res = res.$colon$colon(object);
/*     */     } 
/* 559 */     return res;
/*     */   }
/*     */   
/*     */   public <A, B, C> List<C> map2(List xs, List ys, Function2 f) {
/* 572 */     ListBuffer b = new ListBuffer();
/* 573 */     List xc = xs;
/* 574 */     List yc = ys;
/*     */     while (true) {
/* 575 */       if (xc.isEmpty() || yc.isEmpty())
/* 580 */         return b.toList(); 
/*     */       b.$plus$eq(f.apply(xc.head(), yc.head()));
/*     */       xc = (List)xc.tail();
/*     */       yc = (List)yc.tail();
/*     */     } 
/*     */   }
/*     */   
/*     */   public <A, B> boolean forall2(List xs, List ys, Function2 f) {
/* 595 */     List xc = xs;
/* 596 */     List yc = ys;
/*     */     while (true) {
/* 597 */       if (xc.isEmpty() || yc.isEmpty())
/* 602 */         return true; 
/*     */       if (BoxesRunTime.unboxToBoolean(f.apply(xc.head(), yc.head()))) {
/*     */         xc = (List)xc.tail();
/*     */         yc = (List)yc.tail();
/*     */         continue;
/*     */       } 
/*     */       return false;
/*     */     } 
/*     */   }
/*     */   
/*     */   public <A, B> boolean exists2(List xs, List ys, Function2 f) {
/* 617 */     List xc = xs;
/* 618 */     List yc = ys;
/*     */     while (true) {
/* 619 */       if (xc.isEmpty() || yc.isEmpty())
/* 624 */         return false; 
/*     */       if (BoxesRunTime.unboxToBoolean(f.apply(xc.head(), yc.head())))
/*     */         return true; 
/*     */       xc = (List)xc.tail();
/*     */       yc = (List)yc.tail();
/*     */     } 
/*     */   }
/*     */   
/*     */   public <A> List<List<A>> transpose(List<SeqLike> xss) {
/* 635 */     ListBuffer buf = new ListBuffer();
/* 636 */     List<SeqLike> yss = xss;
/*     */     while (true) {
/* 637 */       if (((SeqLike)yss.head()).isEmpty())
/* 641 */         return buf.toList(); 
/*     */       buf.$plus$eq(yss.map((Function1)new List$$anonfun$transpose$1(), canBuildFrom()));
/*     */       yss = (List<SeqLike>)yss.map((Function1)new List$$anonfun$transpose$2(), canBuildFrom());
/*     */     } 
/*     */   }
/*     */   
/*     */   public static class List$$anonfun$transpose$1 extends AbstractFunction1<List<A>, A> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final A apply(List<A> x$12) {
/*     */       return x$12.head();
/*     */     }
/*     */   }
/*     */   
/*     */   public static class List$$anonfun$transpose$2 extends AbstractFunction1<List<A>, List<A>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final List<A> apply(List x$13) {
/*     */       return (List<A>)x$13.tail();
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\immutable\List$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */