/*     */ package scala.util.continuations;
/*     */ 
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.Function2;
/*     */ import scala.PartialFunction;
/*     */ import scala.Serializable;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.AbstractFunction2;
/*     */ import scala.runtime.Nothing$;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\0055b\001B\001\003\005%\021abQ8oiJ|GnQ8oi\026DHO\003\002\004\t\005i1m\0348uS:,\030\r^5p]NT!!\002\004\002\tU$\030\016\034\006\002\017\005)1oY1mC\016\001Q\003\002\006\036O]\0322\001A\006\020!\taQ\"D\001\007\023\tqaA\001\004B]f\024VM\032\t\003\031AI!!\005\004\003\031M+'/[1mSj\f'\r\\3\t\021M\001!Q1A\005\002Q\t1AZ;o+\005)\002#\002\007\0271%2\024BA\f\007\005%1UO\\2uS>t'\007\005\003\r3m1\023B\001\016\007\005%1UO\\2uS>t\027\007\005\002\035;1\001AA\002\020\001\t\013\007qDA\001B#\t\0013\005\005\002\rC%\021!E\002\002\b\035>$\b.\0338h!\taA%\003\002&\r\t\031\021I\\=\021\005q9CA\002\025\001\021\013\007qDA\001C!\021a\021D\013\024\021\005-\032dB\001\0272\035\ti\003'D\001/\025\ty\003\"\001\004=e>|GOP\005\002\017%\021!GB\001\ba\006\0347.Y4f\023\t!TGA\005Fq\016,\007\017^5p]*\021!G\002\t\0039]\"a\001\017\001\005\006\004y\"!A\"\t\021i\002!\021!Q\001\nU\tAAZ;oA!AA\b\001BC\002\023\005Q(A\001y+\005Y\002\002C \001\005\003\005\013\021B\016\002\005a\004\003\"B!\001\t\003\021\025A\002\037j]&$h\bF\002D\013\032\003R\001\022\001\034MYj\021A\001\005\006'\001\003\r!\006\005\006y\001\003\ra\007\005\006\021\002!)!S\001\004[\006\004XC\001&N)\tYu\nE\003E\00113c\007\005\002\035\033\022)aj\022b\001?\t\021\021)\r\005\006!\036\003\r!U\001\002MB!A\"G\016MQ\t95\013\005\002\r)&\021QK\002\002\t]>Lg\016\\5oK\")q\013\001C\0031\0069a\r\\1u\033\006\004X\003B-]=\022$\"A\0271\021\013\021\0031,\030\034\021\005qaF!\002(W\005\004y\002C\001\017_\t\025yfK1\001 \005\t\021\025\007C\003Q-\002\007\021\r\005\003\r3m\021\007#\002#\0017v\033\007C\001\017e\t\025)gK1\001g\005\t\031\025'\005\002!M!\022ak\025\005\006S\002!)A[\001\bM>\024X-Y2i)\t14\016C\003QQ\002\007\001\004C\003n\001\021\005a.A\006g_J,\027m\0315Gk2dGc\001\034pa\")\001\013\034a\0011!)\021\017\034a\001S\005\tq\rC\003t\001\021\025A/A\005jgR\023\030N^5bYV\tQ\017\005\002\rm&\021qO\002\002\b\005>|G.Z1o\021\025I\b\001\"\002>\003=9W\r\036+sSZL\027\r\034,bYV,\007\"B>\001\t\013a\030\001\0044mCRl\025\r]\"bi\016DWcB?\002\002\005\035\0211\002\013\004}\006=\001c\002#\001\006\025\021\021\002\t\0049\005\005AA\002({\005\004\t\031!\005\002\034GA\031A$a\002\005\013}S(\031\0014\021\007q\tY\001\002\004fu\n\007\021QB\t\004m\005\025\001bBA\tu\002\007\0211C\001\003a\032\004R\001DA\013UyL1!a\006\007\005=\001\026M\035;jC24UO\\2uS>t\007bBA\016\001\021\025\021QD\001\013[\006\004h)\0338bY2LHcA\"\002 !9\001+!\007A\002\005\005\002#\002\007\002$\005\035\022bAA\023\r\tIa)\0368di&|g\016\r\t\004\031\005%\022bAA\026\r\t!QK\\5u\001")
/*     */ public final class ControlContext<A, B, C> implements Serializable {
/*     */   private final Function2<Function1<A, B>, Function1<Exception, B>, C> fun;
/*     */   
/*     */   private final A x;
/*     */   
/*     */   public Function2<Function1<A, B>, Function1<Exception, B>, C> fun() {
/*  83 */     return this.fun;
/*     */   }
/*     */   
/*     */   public A x() {
/*  83 */     return this.x;
/*     */   }
/*     */   
/*     */   public ControlContext(Function2<Function1<A, B>, Function1<Exception, B>, C> fun, Object x) {}
/*     */   
/*     */   public final <A1> ControlContext<A1, B, C> map(Function1 f) {
/* 102 */     if (fun() == null) {
/*     */       try {
/*     */       
/* 103 */       } catch (Exception exception) {}
/*     */     } else {
/*     */     
/*     */     } 
/* 110 */     return new ControlContext((Function2<Function1<A, B>, Function1<Exception, B>, C>)new ControlContext$$anonfun$map$2(this, (ControlContext<A, B, C>)f), null);
/*     */   }
/*     */   
/*     */   public class ControlContext$$anonfun$map$1 extends AbstractFunction2<Function1<A1, B>, Function1<Exception, B>, C> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Exception ex$1;
/*     */     
/*     */     public final C apply(Function1 k, Function1 thr) {
/*     */       return (C)thr.apply(this.ex$1);
/*     */     }
/*     */     
/*     */     public ControlContext$$anonfun$map$1(ControlContext $outer, Exception ex$1) {}
/*     */   }
/*     */   
/*     */   public class ControlContext$$anonfun$map$2 extends AbstractFunction2<Function1<A1, B>, Function1<Exception, B>, C> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Function1 f$1;
/*     */     
/*     */     public ControlContext$$anonfun$map$2(ControlContext $outer, Function1 f$1) {}
/*     */     
/*     */     public final C apply(Function1 k, Function1 thr) {
/* 111 */       return (C)this.$outer.fun().apply(new ControlContext$$anonfun$map$2$$anonfun$apply$1(this, k, ($anonfun$map$2)thr), 
/*     */           
/* 121 */           thr);
/*     */     }
/*     */     
/*     */     public class ControlContext$$anonfun$map$2$$anonfun$apply$1 extends AbstractFunction1<A, B> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final Function1 k$1;
/*     */       
/*     */       private final Function1 thr$1;
/*     */       
/*     */       public ControlContext$$anonfun$map$2$$anonfun$apply$1(ControlContext$$anonfun$map$2 $outer, Function1 k$1, Function1 thr$1) {}
/*     */       
/*     */       public final B apply(Object x) {
/*     */         boolean done = false;
/*     */         try {
/*     */           Object res = this.$outer.f$1.apply(x);
/*     */           done = true;
/*     */         } finally {
/*     */           Exception exception = null;
/*     */         } 
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public final <A1, B1, C1 extends B> ControlContext<A1, B1, C> flatMap(Function1 f) {
/* 141 */     if (fun() == null) {
/*     */       try {
/*     */       
/* 142 */       } catch (Exception exception) {}
/*     */     } else {
/*     */     
/*     */     } 
/* 149 */     return new ControlContext((Function2<Function1<A, B>, Function1<Exception, B>, C>)new ControlContext$$anonfun$flatMap$2(this, (ControlContext<A, B, C>)f), null);
/*     */   }
/*     */   
/*     */   public class ControlContext$$anonfun$flatMap$1 extends AbstractFunction2<Function1<A1, B1>, Function1<Exception, B1>, C> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Exception ex$2;
/*     */     
/*     */     public final C apply(Function1 k, Function1 thr) {
/*     */       return (C)thr.apply(this.ex$2);
/*     */     }
/*     */     
/*     */     public ControlContext$$anonfun$flatMap$1(ControlContext $outer, Exception ex$2) {}
/*     */   }
/*     */   
/*     */   public class ControlContext$$anonfun$flatMap$2 extends AbstractFunction2<Function1<A1, B1>, Function1<Exception, B1>, C> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Function1 f$2;
/*     */     
/*     */     public ControlContext$$anonfun$flatMap$2(ControlContext $outer, Function1 f$2) {}
/*     */     
/*     */     public final C apply(Function1 k, Function1 thr) {
/* 150 */       return (C)this.$outer.fun().apply(new ControlContext$$anonfun$flatMap$2$$anonfun$apply$2(this, k, ($anonfun$flatMap$2)thr), 
/*     */           
/* 161 */           thr);
/*     */     }
/*     */     
/*     */     public class ControlContext$$anonfun$flatMap$2$$anonfun$apply$2 extends AbstractFunction1<A, B> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final Function1 k$2;
/*     */       
/*     */       private final Function1 thr$2;
/*     */       
/*     */       public ControlContext$$anonfun$flatMap$2$$anonfun$apply$2(ControlContext$$anonfun$flatMap$2 $outer, Function1 k$2, Function1 thr$2) {}
/*     */       
/*     */       public final B apply(Object x) {
/*     */         boolean done = false;
/*     */         try {
/*     */           ControlContext ctxR = (ControlContext)this.$outer.f$2.apply(x);
/*     */           done = true;
/*     */           Object res = ctxR.foreachFull(this.k$2, this.thr$2);
/*     */         } finally {
/*     */           Exception exception = null;
/*     */         } 
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public final C foreach(Function1<A, B> f) {
/* 170 */     return foreachFull(f, (Function1<Exception, B>)new ControlContext$$anonfun$foreach$1(this));
/*     */   }
/*     */   
/*     */   public class ControlContext$$anonfun$foreach$1 extends AbstractFunction1<Exception, Nothing$> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Nothing$ apply(Exception x$1) {
/* 170 */       throw x$1;
/*     */     }
/*     */     
/*     */     public ControlContext$$anonfun$foreach$1(ControlContext $outer) {}
/*     */   }
/*     */   
/*     */   public C foreachFull(Function1 f, Function1 g) {
/* 173 */     return (fun() == null) ? 
/* 174 */       (C)f.apply(x()) : 
/*     */       
/* 176 */       (C)fun().apply(f, g);
/*     */   }
/*     */   
/*     */   public final boolean isTrivial() {
/* 180 */     return (fun() == null);
/*     */   }
/*     */   
/*     */   public final A getTrivialValue() {
/* 182 */     return x();
/*     */   }
/*     */   
/*     */   public final <A1, B1 extends B, C1 extends B1> ControlContext<A1, B1, C1> flatMapCatch(PartialFunction pf) {
/* 190 */     ControlContext$$anonfun$1 controlContext$$anonfun$1 = new ControlContext$$anonfun$1(this, (ControlContext<A, B, C>)pf);
/* 208 */     return (fun() == null) ? this : new ControlContext((Function2<Function1<A, B>, Function1<Exception, B>, C>)controlContext$$anonfun$1, null);
/*     */   }
/*     */   
/*     */   public class ControlContext$$anonfun$1 extends AbstractFunction2<Function1<A1, B1>, Function1<Exception, B1>, C> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final PartialFunction pf$1;
/*     */     
/*     */     public ControlContext$$anonfun$1(ControlContext $outer, PartialFunction pf$1) {}
/*     */     
/*     */     public final C apply(Function1 ret1, Function1 thr1) {
/*     */       ControlContext$$anonfun$1$$anonfun$2 controlContext$$anonfun$1$$anonfun$2 = new ControlContext$$anonfun$1$$anonfun$2(this, ret1, ($anonfun$1)thr1);
/*     */       return (C)this.$outer.fun().apply(ret1, controlContext$$anonfun$1$$anonfun$2);
/*     */     }
/*     */     
/*     */     public class ControlContext$$anonfun$1$$anonfun$2 extends AbstractFunction1<Exception, B1> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final Function1 ret1$1;
/*     */       
/*     */       private final Function1 thr1$1;
/*     */       
/*     */       public ControlContext$$anonfun$1$$anonfun$2(ControlContext$$anonfun$1 $outer, Function1 ret1$1, Function1 thr1$1) {}
/*     */       
/*     */       public final B1 apply(Exception t) {
/*     */         boolean captureExceptions = true;
/*     */         try {
/*     */           ControlContext cc1 = (ControlContext)this.$outer.pf$1.apply(t);
/*     */           captureExceptions = false;
/*     */           captureExceptions = false;
/*     */         } finally {
/*     */           Exception exception = null;
/*     */         } 
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public final ControlContext<A, B, C> mapFinally(Function0 f) {
/* 213 */     if (fun() == null) {
/*     */       try {
/* 215 */         f.apply$mcV$sp();
/*     */       } catch (Exception exception) {}
/*     */     } else {
/* 222 */       ControlContext$$anonfun$3 controlContext$$anonfun$3 = new ControlContext$$anonfun$3(this, (ControlContext<A, B, C>)f);
/*     */     } 
/* 245 */     return new ControlContext((Function2<Function1<A, B>, Function1<Exception, B>, C>)controlContext$$anonfun$3, null);
/*     */   }
/*     */   
/*     */   public class ControlContext$$anonfun$mapFinally$1 extends AbstractFunction2<Function1<A, B>, Function1<Exception, B>, C> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Exception ex$3;
/*     */     
/*     */     public final C apply(Function1 k, Function1 thr) {
/*     */       return (C)thr.apply(this.ex$3);
/*     */     }
/*     */     
/*     */     public ControlContext$$anonfun$mapFinally$1(ControlContext $outer, Exception ex$3) {}
/*     */   }
/*     */   
/*     */   public class ControlContext$$anonfun$3 extends AbstractFunction2<Function1<A, B>, Function1<Exception, B>, C> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Function0 f$3;
/*     */     
/*     */     public ControlContext$$anonfun$3(ControlContext $outer, Function0 f$3) {}
/*     */     
/*     */     public final C apply(Function1 ret1, Function1 thr1) {
/*     */       ControlContext$$anonfun$3$$anonfun$4 controlContext$$anonfun$3$$anonfun$4 = new ControlContext$$anonfun$3$$anonfun$4(this, ret1, ($anonfun$3)thr1);
/*     */       return (C)this.$outer.fun().apply(controlContext$$anonfun$3$$anonfun$4, thr1);
/*     */     }
/*     */     
/*     */     public class ControlContext$$anonfun$3$$anonfun$4 extends AbstractFunction1<A, B> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final Function1 ret1$2;
/*     */       
/*     */       private final Function1 thr1$2;
/*     */       
/*     */       public ControlContext$$anonfun$3$$anonfun$4(ControlContext$$anonfun$3 $outer, Function1 ret1$2, Function1 thr1$2) {}
/*     */       
/*     */       public final B apply(Object x) {
/*     */         boolean captureExceptions = true;
/*     */         try {
/*     */           this.$outer.f$3.apply$mcV$sp();
/*     */           captureExceptions = false;
/*     */         } finally {
/*     */           Exception exception = null;
/*     */         } 
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\continuations\ControlContext.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */