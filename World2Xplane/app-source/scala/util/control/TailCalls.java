/*     */ package scala.util.control;
/*     */ 
/*     */ import scala.Function0;
/*     */ import scala.MatchError;
/*     */ import scala.Option;
/*     */ import scala.Product;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.collection.Iterator;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\t=q!B\001\003\021\003I\021!\003+bS2\034\025\r\0347t\025\t\031A!A\004d_:$(o\0347\013\005\0251\021\001B;uS2T\021aB\001\006g\016\fG.Y\002\001!\tQ1\"D\001\003\r\025a!\001#\001\016\005%!\026-\0337DC2d7o\005\002\f\035A\021q\002E\007\002\r%\021\021C\002\002\007\003:L(+\0324\t\013MYA\021\001\013\002\rqJg.\033;?)\005Ia!\002\f\f\003\0039\"a\002+bS2\024VmY\013\0031}\031\"!\006\b\t\013M)B\021\001\016\025\003m\0012\001H\013\036\033\005Y\001C\001\020 \031\001!a\001I\013\005\006\004\t#!A!\022\005\t*\003CA\b$\023\t!cAA\004O_RD\027N\\4\021\005=1\023BA\024\007\005\r\te.\037\005\006SU!\tAK\001\007e\026\034X\017\034;\026\003u1A\001L\006I[\t!1)\0317m+\tq\023g\005\003,_I*\004c\001\017\026aA\021a$\r\003\006A-\022\r!\t\t\003\037MJ!\001\016\004\003\017A\023x\016Z;diB\021qBN\005\003o\031\021AbU3sS\006d\027N_1cY\026D\001\"O\026\003\026\004%\tAO\001\005e\026\034H/F\001<!\ryAhL\005\003{\031\021\021BR;oGRLwN\034\031\t\021}Z#\021#Q\001\nm\nQA]3ti\002BQaE\026\005\002\005#\"AQ\"\021\007qY\003\007C\003:\001\002\0071\bC\004FW\005\005I\021\001$\002\t\r|\007/_\013\003\017*#\"\001S&\021\007qY\023\n\005\002\037\025\022)\001\005\022b\001C!9\021\b\022I\001\002\004a\005cA\b=\033B\031A$F%\t\017=[\023\023!C\001!\006q1m\0349zI\021,g-Y;mi\022\nTCA)]+\005\021&FA\036TW\005!\006CA+[\033\0051&BA,Y\003%)hn\0315fG.,GM\003\002Z\r\005Q\021M\0348pi\006$\030n\0348\n\005m3&!E;oG\",7m[3e-\006\024\030.\0318dK\022)\001E\024b\001C!9alKA\001\n\003z\026!\0049s_\022,8\r\036)sK\032L\0070F\001a!\t\tg-D\001c\025\t\031G-\001\003mC:<'\"A3\002\t)\fg/Y\005\003O\n\024aa\025;sS:<\007bB5,\003\003%\tA[\001\raJ|G-^2u\003JLG/_\013\002WB\021q\002\\\005\003[\032\0211!\0238u\021\035y7&!A\005\002A\fa\002\035:pIV\034G/\0227f[\026tG\017\006\002&c\"9!O\\A\001\002\004Y\027a\001=%c!9AoKA\001\n\003*\030a\0049s_\022,8\r^%uKJ\fGo\034:\026\003Y\0042a\036>&\033\005A(BA=\007\003)\031w\016\0347fGRLwN\\\005\003wb\024\001\"\023;fe\006$xN\035\005\b{.\n\t\021\"\001\003!\031\027M\\#rk\006dGcA@\002\006A\031q\"!\001\n\007\005\raAA\004C_>dW-\0318\t\017Id\030\021!a\001K!I\021\021B\026\002\002\023\005\0231B\001\tQ\006\034\bnQ8eKR\t1\016C\005\002\020-\n\t\021\"\021\002\022\005AAo\\*ue&tw\rF\001a\021%\t)bKA\001\n\003\n9\"\001\004fcV\fGn\035\013\004\006e\001\002\003:\002\024\005\005\t\031A\023\b\023\005u1\"!A\t\022\005}\021\001B\"bY2\0042\001HA\021\r!a3\"!A\t\022\005\r2\003BA\021\035UBqaEA\021\t\003\t9\003\006\002\002 !Q\021qBA\021\003\003%)%!\005\t\025\0055\022\021EA\001\n\003\013y#A\003baBd\0270\006\003\0022\005]B\003BA\032\003s\001B\001H\026\0026A\031a$a\016\005\r\001\nYC1\001\"\021\035I\0241\006a\001\003w\001Ba\004\037\002>A!A$FA\033\021)\t\t%!\t\002\002\023\005\0251I\001\bk:\f\007\017\0357z+\021\t)%a\025\025\t\005\035\023Q\013\t\006\037\005%\023QJ\005\004\003\0272!AB(qi&|g\016\005\003\020y\005=\003\003\002\017\026\003#\0022AHA*\t\031\001\023q\bb\001C!Q\021qKA \003\003\005\r!!\027\002\007a$\003\007\005\003\035W\005E\003BCA/\003C\t\t\021\"\003\002`\005Y!/Z1e%\026\034x\016\034<f)\t\t\t\007E\002b\003GJ1!!\032c\005\031y%M[3di\0321\021\021N\006I\003W\022A\001R8oKV!\021QNA:'\031\t9'a\0343kA!A$FA9!\rq\0221\017\003\007A\005\035$\031A\021\t\025%\n9G!f\001\n\003\n9(\006\002\002r!Y\0211PA4\005#\005\013\021BA9\003\035\021Xm];mi\002BqaEA4\t\003\ty\b\006\003\002\002\006\r\005#\002\017\002h\005E\004bB\025\002~\001\007\021\021\017\005\n\013\006\035\024\021!C\001\003\017+B!!#\002\020R!\0211RAI!\025a\022qMAG!\rq\022q\022\003\007A\005\025%\031A\021\t\023%\n)\t%AA\002\0055\005\"C(\002hE\005I\021AAK+\021\t9*a'\026\005\005e%fAA9'\0221\001%a%C\002\005B\001BXA4\003\003%\te\030\005\tS\006\035\024\021!C\001U\"Iq.a\032\002\002\023\005\0211\025\013\004K\005\025\006\002\003:\002\"\006\005\t\031A6\t\021Q\f9'!A\005BUD\021\"`A4\003\003%\t!a+\025\007}\fi\013\003\005s\003S\013\t\0211\001&\021)\tI!a\032\002\002\023\005\0231\002\005\013\003\037\t9'!A\005B\005E\001BCA\013\003O\n\t\021\"\021\0026R\031q0a.\t\021I\f\031,!AA\002\025:\021\"a/\f\003\003E\t\"!0\002\t\021{g.\032\t\0049\005}f!CA5\027\005\005\t\022CAa'\021\tyLD\033\t\017M\ty\f\"\001\002FR\021\021Q\030\005\013\003\037\ty,!A\005F\005E\001BCA\027\003\013\t\021\"!\002LV!\021QZAj)\021\ty-!6\021\013q\t9'!5\021\007y\t\031\016\002\004!\003\023\024\r!\t\005\bS\005%\007\031AAi\021)\t\t%a0\002\002\023\005\025\021\\\013\005\0037\f\t\017\006\003\002^\006\r\b#B\b\002J\005}\007c\001\020\002b\0221\001%a6C\002\005B!\"a\026\002X\006\005\t\031AAs!\025a\022qMAp\021)\ti&a0\002\002\023%\021q\f\005\b\003W\\A\021AAw\003!!\030-\0337dC2dW\003BAx\003k$B!!=\002xB!A$FAz!\rq\022Q\037\003\007A\005%(\031A\021\t\021e\nI\017\"a\001\003s\004RaDA~\003cL1!!@\007\005!a$-\0378b[\026t\004b\002B\001\027\021\005!1A\001\005I>tW-\006\003\003\006\t-A\003\002B\004\005\033\001B\001H\013\003\nA\031aDa\003\005\r\001\nyP1\001\"\021\035I\023q a\001\005\023\001")
/*     */ public final class TailCalls {
/*     */   public static <A> TailRec<A> done(A paramA) {
/*     */     return TailCalls$.MODULE$.done(paramA);
/*     */   }
/*     */   
/*     */   public static <A> TailRec<A> tailcall(Function0<TailRec<A>> paramFunction0) {
/*     */     return TailCalls$.MODULE$.tailcall(paramFunction0);
/*     */   }
/*     */   
/*     */   public static abstract class TailRec<A> {
/*     */     private final Object loop$1(TailRec body) {
/*  36 */       while (body instanceof TailCalls.Call) {
/*  36 */         TailCalls.Call<A> call = (TailCalls.Call)body;
/*  37 */         body = (TailRec)call.rest().apply();
/*     */       } 
/*  38 */       if (body instanceof TailCalls.Done)
/*  38 */         TailCalls.Done done = (TailCalls.Done)body; 
/*     */       throw new MatchError(body);
/*     */     }
/*     */     
/*     */     public A result() {
/*  40 */       return (A)loop$1(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Call<A> extends TailRec<A> implements Product, Serializable {
/*     */     private final Function0<TailCalls.TailRec<A>> rest;
/*     */     
/*     */     public Function0<TailCalls.TailRec<A>> rest() {
/*  45 */       return this.rest;
/*     */     }
/*     */     
/*     */     public <A> Call<A> copy(Function0<TailCalls.TailRec<A>> rest) {
/*  45 */       return new Call(rest);
/*     */     }
/*     */     
/*     */     public <A> Function0<TailCalls.TailRec<A>> copy$default$1() {
/*  45 */       return rest();
/*     */     }
/*     */     
/*     */     public String productPrefix() {
/*  45 */       return "Call";
/*     */     }
/*     */     
/*     */     public int productArity() {
/*  45 */       return 1;
/*     */     }
/*     */     
/*     */     public Object productElement(int x$1) {
/*  45 */       switch (x$1) {
/*     */         default:
/*  45 */           throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */         case 0:
/*     */           break;
/*     */       } 
/*  45 */       return rest();
/*     */     }
/*     */     
/*     */     public Iterator<Object> productIterator() {
/*  45 */       return scala.runtime.ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */     }
/*     */     
/*     */     public boolean canEqual(Object x$1) {
/*  45 */       return x$1 instanceof Call;
/*     */     }
/*     */     
/*     */     public int hashCode() {
/*  45 */       return scala.runtime.ScalaRunTime$.MODULE$._hashCode(this);
/*     */     }
/*     */     
/*     */     public String toString() {
/*  45 */       return scala.runtime.ScalaRunTime$.MODULE$._toString(this);
/*     */     }
/*     */     
/*     */     public boolean equals(Object x$1) {
/*     */       // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: if_acmpeq -> 75
/*     */       //   5: aload_1
/*     */       //   6: instanceof scala/util/control/TailCalls$Call
/*     */       //   9: ifeq -> 17
/*     */       //   12: iconst_1
/*     */       //   13: istore_2
/*     */       //   14: goto -> 19
/*     */       //   17: iconst_0
/*     */       //   18: istore_2
/*     */       //   19: iload_2
/*     */       //   20: ifeq -> 79
/*     */       //   23: aload_1
/*     */       //   24: checkcast scala/util/control/TailCalls$Call
/*     */       //   27: astore #4
/*     */       //   29: aload_0
/*     */       //   30: invokevirtual rest : ()Lscala/Function0;
/*     */       //   33: aload #4
/*     */       //   35: invokevirtual rest : ()Lscala/Function0;
/*     */       //   38: astore_3
/*     */       //   39: dup
/*     */       //   40: ifnonnull -> 51
/*     */       //   43: pop
/*     */       //   44: aload_3
/*     */       //   45: ifnull -> 58
/*     */       //   48: goto -> 71
/*     */       //   51: aload_3
/*     */       //   52: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   55: ifeq -> 71
/*     */       //   58: aload #4
/*     */       //   60: aload_0
/*     */       //   61: invokevirtual canEqual : (Ljava/lang/Object;)Z
/*     */       //   64: ifeq -> 71
/*     */       //   67: iconst_1
/*     */       //   68: goto -> 72
/*     */       //   71: iconst_0
/*     */       //   72: ifeq -> 79
/*     */       //   75: iconst_1
/*     */       //   76: goto -> 80
/*     */       //   79: iconst_0
/*     */       //   80: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #45	-> 0
/*     */       //   #236	-> 12
/*     */       //   #45	-> 19
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	81	0	this	Lscala/util/control/TailCalls$Call;
/*     */       //   0	81	1	x$1	Ljava/lang/Object;
/*     */     }
/*     */     
/*     */     public Call(Function0<TailCalls.TailRec<A>> rest) {
/*  45 */       Product.class.$init$(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Call$ implements Serializable {
/*     */     public static final Call$ MODULE$;
/*     */     
/*     */     public final String toString() {
/*  45 */       return "Call";
/*     */     }
/*     */     
/*     */     public <A> TailCalls.Call<A> apply(Function0<TailCalls.TailRec<A>> rest) {
/*  45 */       return new TailCalls.Call<A>(rest);
/*     */     }
/*     */     
/*     */     public <A> Option<Function0<TailCalls.TailRec<A>>> unapply(TailCalls.Call x$0) {
/*  45 */       return (x$0 == null) ? (Option<Function0<TailCalls.TailRec<A>>>)scala.None$.MODULE$ : (Option<Function0<TailCalls.TailRec<A>>>)new Some(x$0.rest());
/*     */     }
/*     */     
/*     */     private Object readResolve() {
/*  45 */       return MODULE$;
/*     */     }
/*     */     
/*     */     public Call$() {
/*  45 */       MODULE$ = this;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Done<A> extends TailRec<A> implements Product, Serializable {
/*     */     private final A result;
/*     */     
/*     */     public A result() {
/*  49 */       return this.result;
/*     */     }
/*     */     
/*     */     public <A> Done<A> copy(Object result) {
/*  49 */       return new Done((A)result);
/*     */     }
/*     */     
/*     */     public <A> A copy$default$1() {
/*  49 */       return result();
/*     */     }
/*     */     
/*     */     public String productPrefix() {
/*  49 */       return "Done";
/*     */     }
/*     */     
/*     */     public int productArity() {
/*  49 */       return 1;
/*     */     }
/*     */     
/*     */     public Object productElement(int x$1) {
/*  49 */       switch (x$1) {
/*     */         default:
/*  49 */           throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */         case 0:
/*     */           break;
/*     */       } 
/*  49 */       return result();
/*     */     }
/*     */     
/*     */     public Iterator<Object> productIterator() {
/*  49 */       return scala.runtime.ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */     }
/*     */     
/*     */     public boolean canEqual(Object x$1) {
/*  49 */       return x$1 instanceof Done;
/*     */     }
/*     */     
/*     */     public int hashCode() {
/*  49 */       return scala.runtime.ScalaRunTime$.MODULE$._hashCode(this);
/*     */     }
/*     */     
/*     */     public String toString() {
/*  49 */       return scala.runtime.ScalaRunTime$.MODULE$._toString(this);
/*     */     }
/*     */     
/*     */     public boolean equals(Object x$1) {
/*  49 */       if (this != x$1) {
/*     */         boolean bool;
/*  49 */         if (x$1 instanceof Done) {
/* 236 */           bool = true;
/*     */         } else {
/* 236 */           bool = false;
/*     */         } 
/*     */         if (bool) {
/*     */           Done<Object> done = (Done)x$1;
/*     */           A a2 = (A)done.result();
/*     */           A a1;
/*     */           if (((((a1 = result()) == a2) ? true : ((a1 == null) ? false : ((a1 instanceof Number) ? BoxesRunTime.equalsNumObject((Number)a1, a2) : ((a1 instanceof Character) ? BoxesRunTime.equalsCharObject((Character)a1, a2) : a1.equals(a2))))) && done.canEqual(this)));
/*     */         } 
/*     */         return false;
/*     */       } 
/*     */     }
/*     */     
/*     */     public Done(Object result) {
/*     */       Product.class.$init$(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Done$ implements Serializable {
/*     */     public static final Done$ MODULE$;
/*     */     
/*     */     public final String toString() {
/*     */       return "Done";
/*     */     }
/*     */     
/*     */     public <A> TailCalls.Done<A> apply(Object result) {
/*     */       return new TailCalls.Done<A>((A)result);
/*     */     }
/*     */     
/*     */     public <A> Option<A> unapply(TailCalls.Done x$0) {
/*     */       return (x$0 == null) ? (Option<A>)scala.None$.MODULE$ : (Option<A>)new Some(x$0.result());
/*     */     }
/*     */     
/*     */     private Object readResolve() {
/*     */       return MODULE$;
/*     */     }
/*     */     
/*     */     public Done$() {
/*     */       MODULE$ = this;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\control\TailCalls.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */