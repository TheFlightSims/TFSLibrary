/*    */ package scala;
/*    */ 
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ import scala.runtime.BoxedUnit;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ import scala.runtime.Nothing$;
/*    */ import scala.runtime.ObjectRef;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\005\rr!B\001\003\021\003)\021!\003*fgB|g\016Z3s\025\005\031\021!B:dC2\f7\001\001\t\003\r\035i\021A\001\004\006\021\tA\t!\003\002\n%\026\034\bo\0348eKJ\0342a\002\006\016!\t11\"\003\002\r\005\t1\021I\\=SK\032\004\"A\002\b\n\005=\021!\001D*fe&\fG.\033>bE2,\007\"B\t\b\t\003\021\022A\002\037j]&$h\bF\001\006\021\025!r\001\"\001\026\003!\031wN\\:uC:$XC\001\fb)\t9\"\rE\002\0071\0014Q\001\003\002\002\002e)\"A\007\021\024\007aQQ\002C\003\0221\021\005A\004F\001\036!\r1\001D\b\t\003?\001b\001\001\002\004\"1\021\025\rA\t\002\002\003F\0211E\n\t\003\r\021J!!\n\002\003\0179{G\017[5oOB\021aaJ\005\003Q\t\0211!\0218z\021\025Q\003D\"\001,\003\035\021Xm\0359p]\022$\"\001L\030\021\005\031i\023B\001\030\003\005\021)f.\033;\t\013AJ\003\031A\031\002\003-\004BA\002\032\037Y%\0211G\001\002\n\rVt7\r^5p]FBQ!\016\r\005\002Y\nqAZ8sK\006\034\007\016\006\002-o!)\001\007\016a\001c!)\021\b\007C\001u\005\031Q.\0319\026\005mrDC\001\037A!\r1\001$\020\t\003?y\"Qa\020\035C\002\t\022\021A\021\005\006\003b\002\rAQ\001\002MB!aA\r\020>\021\025!\005\004\"\001F\003\0351G.\031;NCB,\"AR%\025\005\035S\005c\001\004\031\021B\021q$\023\003\006\r\023\rA\t\005\006\003\016\003\ra\023\t\005\rIrr\tC\003N1\021\005a*\001\004gS2$XM\035\013\003;=CQ\001\025'A\002E\013\021\001\035\t\005\rIr\"\013\005\002\007'&\021AK\001\002\b\005>|G.Z1o\021\0251\006\004\"\021X\003!!xn\025;sS:<G#\001-\021\005esV\"\001.\013\005mc\026\001\0027b]\036T\021!X\001\005U\0064\030-\003\002`5\n11\013\036:j]\036\004\"aH1\005\013\005\032\"\031\001\022\t\013\r\034\002\031\0011\002\003aDQ!Z\004\005\002\031\fA!\032=fGV\021q\r\034\013\003%\"Daa\0313\005\002\004I\007c\001\004kY%\0211N\001\002\ty\tLh.Y7f}\021)\021\005\032b\001E!)an\002C\001_\006\031!/\0368\026\005A,HCA9w!\r1!\017^\005\003g\n\021aa\0249uS>t\007CA\020v\t\025\tSN1\001#\021\0259X\0161\001y\003\005\021\bc\001\004\031i\")!p\002C\001w\006!An\\8q+\ra\030\021\001\013\003{z\0042A\002\r$\021\0259\030\0201\001\000!\r1\001\004\f\003\006Ce\024\rA\t\005\b\003\0139A\021AA\004\003%awn\0349XQ&dW-\006\003\002\n\005UA\003BA\006\003\037!2a`A\007\021\0319\0301\001a\001\"I\021\021CA\002\t\003\007\0211C\001\005G>tG\rE\002\007UJ#a!IA\002\005\004\021\003\"CA\r\017\005\005I\021BA\016\003-\021X-\0313SKN|GN^3\025\005\005u\001cA-\002 %\031\021\021\005.\003\r=\023'.Z2u\001")
/*    */ public abstract class Responder<A> implements Serializable {
/*    */   public static <A> Responder<BoxedUnit> loopWhile(Function0<Object> paramFunction0, Responder<BoxedUnit> paramResponder) {
/*    */     return Responder$.MODULE$.loopWhile(paramFunction0, paramResponder);
/*    */   }
/*    */   
/*    */   public static <A> Responder<Nothing$> loop(Responder<BoxedUnit> paramResponder) {
/*    */     return Responder$.MODULE$.loop(paramResponder);
/*    */   }
/*    */   
/*    */   public static <A> Option<A> run(Responder<A> paramResponder) {
/*    */     return Responder$.MODULE$.run(paramResponder);
/*    */   }
/*    */   
/*    */   public static <A> boolean exec(Function0<BoxedUnit> paramFunction0) {
/*    */     return Responder$.MODULE$.exec(paramFunction0);
/*    */   }
/*    */   
/*    */   public static <A> Responder<A> constant(A paramA) {
/*    */     return Responder$.MODULE$.constant(paramA);
/*    */   }
/*    */   
/*    */   public abstract void respond(Function1<A, BoxedUnit> paramFunction1);
/*    */   
/*    */   public static class Responder$$anon$1 extends Responder<A> {
/*    */     private final Object x$1;
/*    */     
/*    */     public Responder$$anon$1(Object x$1) {}
/*    */     
/*    */     public void respond(Function1 k) {
/* 26 */       k.apply(this.x$1);
/*    */     }
/*    */   }
/*    */   
/*    */   public static class Responder$$anonfun$run$1 extends AbstractFunction1<A, BoxedUnit> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     private final ObjectRef result$1;
/*    */     
/*    */     public final void apply(Object x) {
/* 38 */       this.result$1.elem = new Some(x);
/*    */     }
/*    */     
/*    */     public Responder$$anonfun$run$1(ObjectRef result$1) {}
/*    */   }
/*    */   
/*    */   public static class Responder$$anonfun$loop$1 extends AbstractFunction1<BoxedUnit, Responder<Nothing$>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     private final Responder r$1;
/*    */     
/*    */     public final Responder<Nothing$> apply(BoxedUnit _) {
/* 43 */       return Responder$.MODULE$.loop(this.r$1).map((Function1<Nothing$, Nothing$>)new Responder$$anonfun$loop$1$$anonfun$apply$1(this));
/*    */     }
/*    */     
/*    */     public Responder$$anonfun$loop$1(Responder r$1) {}
/*    */     
/*    */     public class Responder$$anonfun$loop$1$$anonfun$apply$1 extends AbstractFunction1<Nothing$, Nothing$> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       public final Nothing$ apply(Nothing$ y) {
/* 43 */         return y;
/*    */       }
/*    */       
/*    */       public Responder$$anonfun$loop$1$$anonfun$apply$1(Responder$$anonfun$loop$1 $outer) {}
/*    */     }
/*    */   }
/*    */   
/*    */   public static class Responder$$anonfun$loopWhile$1 extends AbstractFunction1<BoxedUnit, Responder<BoxedUnit>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     private final Function0 cond$1;
/*    */     
/*    */     private final Responder r$2;
/*    */     
/*    */     public final Responder<BoxedUnit> apply(BoxedUnit _) {
/* 46 */       return Responder$.MODULE$.loopWhile(this.cond$1, this.r$2).map((Function1<BoxedUnit, BoxedUnit>)new Responder$$anonfun$loopWhile$1$$anonfun$apply$2(this));
/*    */     }
/*    */     
/*    */     public Responder$$anonfun$loopWhile$1(Function0 cond$1, Responder r$2) {}
/*    */     
/*    */     public class Responder$$anonfun$loopWhile$1$$anonfun$apply$2 extends AbstractFunction1<BoxedUnit, BoxedUnit> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       public final void apply(BoxedUnit y) {}
/*    */       
/*    */       public Responder$$anonfun$loopWhile$1$$anonfun$apply$2(Responder$$anonfun$loopWhile$1 $outer) {}
/*    */     }
/*    */   }
/*    */   
/*    */   public void foreach(Function1<A, BoxedUnit> k) {
/* 65 */     respond(k);
/*    */   }
/*    */   
/*    */   public <B> Responder<B> map(Function1 f) {
/* 67 */     return new Responder$$anon$3(this, (Responder<A>)f);
/*    */   }
/*    */   
/*    */   public class Responder$$anon$3 extends Responder<B> {
/*    */     public final Function1 f$2;
/*    */     
/*    */     public Responder$$anon$3(Responder $outer, Function1 f$2) {}
/*    */     
/*    */     public void respond(Function1 k) {
/* 69 */       this.$outer.respond((Function1)new Responder$$anon$3$$anonfun$respond$1(this, ($anon$3)k));
/*    */     }
/*    */     
/*    */     public class Responder$$anon$3$$anonfun$respond$1 extends AbstractFunction1<A, BoxedUnit> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       private final Function1 k$2;
/*    */       
/*    */       public final void apply(Object x) {
/* 69 */         this.k$2.apply(this.$outer.f$2.apply(x));
/*    */       }
/*    */       
/*    */       public Responder$$anon$3$$anonfun$respond$1(Responder$$anon$3 $outer, Function1 k$2) {}
/*    */     }
/*    */   }
/*    */   
/*    */   public <B> Responder<B> flatMap(Function1 f) {
/* 73 */     return new Responder$$anon$2(this, (Responder<A>)f);
/*    */   }
/*    */   
/*    */   public class Responder$$anon$2 extends Responder<B> {
/*    */     public final Function1 f$1;
/*    */     
/*    */     public Responder$$anon$2(Responder $outer, Function1 f$1) {}
/*    */     
/*    */     public void respond(Function1 k) {
/* 75 */       this.$outer.respond((Function1)new Responder$$anon$2$$anonfun$respond$2(this, ($anon$2)k));
/*    */     }
/*    */     
/*    */     public class Responder$$anon$2$$anonfun$respond$2 extends AbstractFunction1<A, BoxedUnit> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       private final Function1 k$1;
/*    */       
/*    */       public final void apply(Object x) {
/* 75 */         ((Responder)this.$outer.f$1.apply(x)).respond(this.k$1);
/*    */       }
/*    */       
/*    */       public Responder$$anon$2$$anonfun$respond$2(Responder$$anon$2 $outer, Function1 k$1) {}
/*    */     }
/*    */   }
/*    */   
/*    */   public Responder<A> filter(Function1 p) {
/* 79 */     return new Responder$$anon$4(this, (Responder<A>)p);
/*    */   }
/*    */   
/*    */   public class Responder$$anon$4 extends Responder<A> {
/*    */     public final Function1 p$1;
/*    */     
/*    */     public Responder$$anon$4(Responder $outer, Function1 p$1) {}
/*    */     
/*    */     public void respond(Function1 k) {
/* 81 */       this.$outer.respond((Function1)new Responder$$anon$4$$anonfun$respond$3(this, ($anon$4)k));
/*    */     }
/*    */     
/*    */     public class Responder$$anon$4$$anonfun$respond$3 extends AbstractFunction1<A, BoxedUnit> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       private final Function1 k$3;
/*    */       
/*    */       public final void apply(Object x) {
/* 81 */         if (BoxesRunTime.unboxToBoolean(this.$outer.p$1.apply(x)))
/* 81 */           this.k$3.apply(x); 
/*    */       }
/*    */       
/*    */       public Responder$$anon$4$$anonfun$respond$3(Responder$$anon$4 $outer, Function1 k$3) {}
/*    */     }
/*    */   }
/*    */   
/*    */   public String toString() {
/* 85 */     return "Responder";
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\Responder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */