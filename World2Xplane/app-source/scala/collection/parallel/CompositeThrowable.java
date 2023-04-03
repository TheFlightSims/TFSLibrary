/*     */ package scala.collection.parallel;
/*     */ 
/*     */ import scala.Array$;
/*     */ import scala.Function1;
/*     */ import scala.Predef$;
/*     */ import scala.Product;
/*     */ import scala.Serializable;
/*     */ import scala.collection.GenTraversableOnce;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.Set;
/*     */ import scala.collection.Set$;
/*     */ import scala.collection.TraversableOnce;
/*     */ import scala.collection.immutable.StringOps;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.reflect.ClassTag$;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.ScalaRunTime$;
/*     */ import scala.runtime.StringAdd$;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005UqaB\001\003\003\003E\t!C\001\023\007>l\007o\\:ji\026$\006N]8xC\ndWM\003\002\004\t\005A\001/\031:bY2,GN\003\002\006\r\005Q1m\0347mK\016$\030n\0348\013\003\035\tQa]2bY\006\034\001\001\005\002\013\0275\t!AB\004\r\005\005\005\t\022A\007\003%\r{W\016]8tSR,G\013\033:po\006\024G.Z\n\004\0279I\003\003B\b\023)\001j\021\001\005\006\003#\031\tqA];oi&lW-\003\002\024!\t\t\022IY:ue\006\034GOR;oGRLwN\\\031\021\007U1\002$D\001\005\023\t9BAA\002TKR\004\"!G\017\017\005iYR\"\001\004\n\005q1\021a\0029bG.\fw-Z\005\003=}\021\021\002\0265s_^\f'\r\\3\013\005q1\001C\001\006\"\r\021a!A\021\022\024\t\005\032c%\013\t\0033\021J!!J\020\003\023\025C8-\0329uS>t\007C\001\016(\023\tAcAA\004Qe>$Wo\031;\021\005iQ\023BA\026\007\0051\031VM]5bY&T\030M\0317f\021!i\023E!f\001\n\003q\023A\003;ie><\030M\0317fgV\tA\003\003\0051C\tE\t\025!\003\025\003-!\bN]8xC\ndWm\035\021\t\013I\nC\021A\032\002\rqJg.\033;?)\t\001C\007C\003.c\001\007A\003C\0047C\005\005I\021A\034\002\t\r|\007/\037\013\003AaBq!L\033\021\002\003\007A\003C\004;CE\005I\021A\036\002\035\r|\007/\037\023eK\032\fW\017\034;%cU\tAH\013\002\025{-\na\b\005\002@\t6\t\001I\003\002B\005\006IQO\\2iK\016\\W\r\032\006\003\007\032\t!\"\0318o_R\fG/[8o\023\t)\005IA\tv]\016DWmY6fIZ\013'/[1oG\026DqaR\021\002\002\023\005\003*A\007qe>$Wo\031;Qe\0264\027\016_\013\002\023B\021!jT\007\002\027*\021A*T\001\005Y\006twMC\001O\003\021Q\027M^1\n\005A[%AB*ue&tw\rC\004SC\005\005I\021A*\002\031A\024x\016Z;di\006\023\030\016^=\026\003Q\003\"AG+\n\005Y3!aA%oi\"9\001,IA\001\n\003I\026A\0049s_\022,8\r^#mK6,g\016\036\013\0035v\003\"AG.\n\005q3!aA!os\"9alVA\001\002\004!\026a\001=%c!9\001-IA\001\n\003\n\027a\0049s_\022,8\r^%uKJ\fGo\034:\026\003\t\0042!F2[\023\t!GA\001\005Ji\026\024\030\r^8s\021\0351\027%!A\005\002\035\f\001bY1o\013F,\030\r\034\013\003Q.\004\"AG5\n\005)4!a\002\"p_2,\027M\034\005\b=\026\f\t\0211\001[\021\035i\027%!A\005B9\f\001\002[1tQ\016{G-\032\013\002)\"9\001/IA\001\n\003\n\030AB3rk\006d7\017\006\002ie\"9al\\A\001\002\004Q\006\"\002\032\f\t\003!H#A\005\t\017Y\\\021\021!C#o\006AAo\\*ue&tw\rF\001J\021\035I8\"!A\005\002j\fQ!\0319qYf$\"\001I>\t\0135B\b\031\001\013\t\017u\\\021\021!CA}\0069QO\\1qa2LHcA@\002\006A!!$!\001\025\023\r\t\031A\002\002\007\037B$\030n\0348\t\021\005\035A0!AA\002\001\n1\001\037\0231\021%\tYaCA\001\n\023\ti!A\006sK\006$'+Z:pYZ,GCAA\b!\rQ\025\021C\005\004\003'Y%AB(cU\026\034G\017")
/*     */ public final class CompositeThrowable extends Exception implements Product, Serializable {
/*     */   private final Set<Throwable> throwables;
/*     */   
/*     */   public CompositeThrowable copy(Set<Throwable> throwables) {
/* 138 */     return new CompositeThrowable(
/* 139 */         throwables);
/*     */   }
/*     */   
/*     */   public String productPrefix() {
/*     */     return "CompositeThrowable";
/*     */   }
/*     */   
/*     */   public int productArity() {
/*     */     return 1;
/*     */   }
/*     */   
/*     */   public Object productElement(int x$1) {
/*     */     switch (x$1) {
/*     */       default:
/*     */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */       case 0:
/*     */         break;
/*     */     } 
/*     */     return throwables();
/*     */   }
/*     */   
/*     */   public Iterator<Object> productIterator() {
/*     */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */   }
/*     */   
/*     */   public boolean canEqual(Object x$1) {
/*     */     return x$1 instanceof CompositeThrowable;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/*     */     return ScalaRunTime$.MODULE$._hashCode(this);
/*     */   }
/*     */   
/*     */   public boolean equals(Object x$1) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: aload_1
/*     */     //   2: if_acmpeq -> 67
/*     */     //   5: aload_1
/*     */     //   6: instanceof scala/collection/parallel/CompositeThrowable
/*     */     //   9: ifeq -> 17
/*     */     //   12: iconst_1
/*     */     //   13: istore_2
/*     */     //   14: goto -> 19
/*     */     //   17: iconst_0
/*     */     //   18: istore_2
/*     */     //   19: iload_2
/*     */     //   20: ifeq -> 71
/*     */     //   23: aload_1
/*     */     //   24: checkcast scala/collection/parallel/CompositeThrowable
/*     */     //   27: astore_3
/*     */     //   28: aload_0
/*     */     //   29: invokevirtual throwables : ()Lscala/collection/Set;
/*     */     //   32: aload_3
/*     */     //   33: invokevirtual throwables : ()Lscala/collection/Set;
/*     */     //   36: astore #4
/*     */     //   38: dup
/*     */     //   39: ifnonnull -> 51
/*     */     //   42: pop
/*     */     //   43: aload #4
/*     */     //   45: ifnull -> 59
/*     */     //   48: goto -> 63
/*     */     //   51: aload #4
/*     */     //   53: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   56: ifeq -> 63
/*     */     //   59: iconst_1
/*     */     //   60: goto -> 64
/*     */     //   63: iconst_0
/*     */     //   64: ifeq -> 71
/*     */     //   67: iconst_1
/*     */     //   68: goto -> 72
/*     */     //   71: iconst_0
/*     */     //   72: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #138	-> 0
/*     */     //   #236	-> 12
/*     */     //   #138	-> 19
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	73	0	this	Lscala/collection/parallel/CompositeThrowable;
/*     */     //   0	73	1	x$1	Ljava/lang/Object;
/*     */   }
/*     */   
/*     */   public Set<Throwable> throwables() {
/* 139 */     return this.throwables;
/*     */   }
/*     */   
/*     */   public Set<Throwable> copy$default$1() {
/* 139 */     return throwables();
/*     */   }
/*     */   
/*     */   public CompositeThrowable(Set<Throwable> throwables) {
/* 141 */     super((new StringBuilder()).append("Multiple exceptions thrown during a parallel computation: ")
/* 142 */         .append(((TraversableOnce)throwables.map((Function1)new CompositeThrowable$$anonfun$$init$$1(), Set$.MODULE$.canBuildFrom())).mkString("\n\n")).toString());
/*     */     Product.class.$init$(this);
/*     */   }
/*     */   
/*     */   public static <A> Function1<Set<Throwable>, A> andThen(Function1<CompositeThrowable, A> paramFunction1) {
/*     */     return CompositeThrowable$.MODULE$.andThen(paramFunction1);
/*     */   }
/*     */   
/*     */   public static <A> Function1<A, CompositeThrowable> compose(Function1<A, Set<Throwable>> paramFunction1) {
/*     */     return CompositeThrowable$.MODULE$.compose(paramFunction1);
/*     */   }
/*     */   
/*     */   public class CompositeThrowable$$anonfun$$init$$1 extends AbstractFunction1<Throwable, String> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final String apply(Throwable t) {
/* 142 */       Predef$ predef$ = Predef$.MODULE$;
/* 142 */       return (new StringBuilder()).append(StringAdd$.MODULE$.$plus$extension(Predef$.MODULE$.any2stringadd(t), "\n")).append(Predef$.MODULE$.genericArrayOps(Predef$.MODULE$.refArrayOps((Object[])Predef$.MODULE$.refArrayOps((Object[])t.getStackTrace()).take(10)).$plus$plus((GenTraversableOnce)new StringOps("..."), Array$.MODULE$.canBuildFrom(ClassTag$.MODULE$.Any()))).mkString("\n")).toString();
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\CompositeThrowable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */