/*    */ package akka.routing;
/*    */ 
/*    */ import akka.actor.ActorRef;
/*    */ import scala.Function1;
/*    */ import scala.Product;
/*    */ import scala.Serializable;
/*    */ import scala.collection.Iterator;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.BoxedUnit;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ import scala.runtime.ScalaRunTime$;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\005\025b\001B\001\003\001\036\021QbV5uQ2K7\017^3oKJ\034(BA\002\005\003\035\021x.\036;j]\036T\021!B\001\005C.\\\027m\001\001\024\013\001AaBE\013\021\005%aQ\"\001\006\013\003-\tQa]2bY\006L!!\004\006\003\r\005s\027PU3g!\ty\001#D\001\003\023\t\t\"AA\bMSN$XM\\3s\033\026\0348/Y4f!\tI1#\003\002\025\025\t9\001K]8ek\016$\bCA\005\027\023\t9\"B\001\007TKJL\027\r\\5{C\ndW\r\003\005\032\001\tU\r\021\"\001\033\003\0051W#A\016\021\t%ab\004J\005\003;)\021\021BR;oGRLwN\\\031\021\005}\021S\"\001\021\013\005\005\"\021!B1di>\024\030BA\022!\005!\t5\r^8s%\0264\007CA\005&\023\t1#B\001\003V]&$\b\002\003\025\001\005#\005\013\021B\016\002\005\031\004\003\"\002\026\001\t\003Y\023A\002\037j]&$h\b\006\002-[A\021q\002\001\005\0063%\002\ra\007\005\b_\001\t\t\021\"\0011\003\021\031w\016]=\025\0051\n\004bB\r/!\003\005\ra\007\005\bg\001\t\n\021\"\0015\0039\031w\016]=%I\0264\027-\0367uIE*\022!\016\026\0037YZ\023a\016\t\003quj\021!\017\006\003um\n\021\"\0368dQ\026\0347.\0323\013\005qR\021AC1o]>$\030\r^5p]&\021a(\017\002\022k:\034\007.Z2lK\0224\026M]5b]\016,\007b\002!\001\003\003%\t%Q\001\016aJ|G-^2u!J,g-\033=\026\003\t\003\"a\021%\016\003\021S!!\022$\002\t1\fgn\032\006\002\017\006!!.\031<b\023\tIEI\001\004TiJLgn\032\005\b\027\002\t\t\021\"\001M\0031\001(o\0343vGR\f%/\033;z+\005i\005CA\005O\023\ty%BA\002J]RDq!\025\001\002\002\023\005!+\001\bqe>$Wo\031;FY\026lWM\034;\025\005M3\006CA\005U\023\t)&BA\002B]fDqa\026)\002\002\003\007Q*A\002yIEBq!\027\001\002\002\023\005#,A\bqe>$Wo\031;Ji\026\024\030\r^8s+\005Y\006c\001/`'6\tQL\003\002_\025\005Q1m\0347mK\016$\030n\0348\n\005\001l&\001C%uKJ\fGo\034:\t\017\t\004\021\021!C\001G\006A1-\0318FcV\fG\016\006\002eOB\021\021\"Z\005\003M*\021qAQ8pY\026\fg\016C\004XC\006\005\t\031A*\t\017%\004\021\021!C!U\006A\001.Y:i\007>$W\rF\001N\021\035a\007!!A\005B5\f\001\002^8TiJLgn\032\013\002\005\"9q\016AA\001\n\003\002\030AB3rk\006d7\017\006\002ec\"9qK\\A\001\002\004\031vaB:\003\003\003E\t\001^\001\016/&$\b\016T5ti\026tWM]:\021\005=)haB\001\003\003\003E\tA^\n\004k^,\002\003\002=|71j\021!\037\006\003u*\tqA];oi&lW-\003\002}s\n\t\022IY:ue\006\034GOR;oGRLwN\\\031\t\013)*H\021\001@\025\003QDq\001\\;\002\002\023\025S\016C\005\002\004U\f\t\021\"!\002\006\005)\021\r\0359msR\031A&a\002\t\re\t\t\0011\001\034\021%\tY!^A\001\n\003\013i!A\004v]\006\004\b\017\\=\025\t\005=\021Q\003\t\005\023\005E1$C\002\002\024)\021aa\0249uS>t\007\"CA\f\003\023\t\t\0211\001-\003\rAH\005\r\005\n\0037)\030\021!C\005\003;\t1B]3bIJ+7o\0347wKR\021\021q\004\t\004\007\006\005\022bAA\022\t\n1qJ\0316fGR\004")
/*    */ public class WithListeners implements ListenerMessage, Product, Serializable {
/*    */   private final Function1<ActorRef, BoxedUnit> f;
/*    */   
/*    */   public static <A> Function1<Function1<ActorRef, BoxedUnit>, A> andThen(Function1<WithListeners, A> paramFunction1) {
/*    */     return WithListeners$.MODULE$.andThen(paramFunction1);
/*    */   }
/*    */   
/*    */   public static <A> Function1<A, WithListeners> compose(Function1<A, Function1<ActorRef, BoxedUnit>> paramFunction1) {
/*    */     return WithListeners$.MODULE$.compose(paramFunction1);
/*    */   }
/*    */   
/*    */   public Function1<ActorRef, BoxedUnit> f() {
/* 13 */     return this.f;
/*    */   }
/*    */   
/*    */   public WithListeners copy(Function1<ActorRef, BoxedUnit> f) {
/* 13 */     return new WithListeners(f);
/*    */   }
/*    */   
/*    */   public Function1<ActorRef, BoxedUnit> copy$default$1() {
/* 13 */     return f();
/*    */   }
/*    */   
/*    */   public String productPrefix() {
/* 13 */     return "WithListeners";
/*    */   }
/*    */   
/*    */   public int productArity() {
/* 13 */     return 1;
/*    */   }
/*    */   
/*    */   public Object productElement(int x$1) {
/* 13 */     int i = x$1;
/* 13 */     switch (i) {
/*    */       default:
/* 13 */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*    */       case 0:
/*    */         break;
/*    */     } 
/* 13 */     return f();
/*    */   }
/*    */   
/*    */   public Iterator<Object> productIterator() {
/* 13 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*    */   }
/*    */   
/*    */   public boolean canEqual(Object x$1) {
/* 13 */     return x$1 instanceof WithListeners;
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 13 */     return ScalaRunTime$.MODULE$._hashCode(this);
/*    */   }
/*    */   
/*    */   public String toString() {
/* 13 */     return ScalaRunTime$.MODULE$._toString(this);
/*    */   }
/*    */   
/*    */   public boolean equals(Object x$1) {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: if_acmpeq -> 80
/*    */     //   5: aload_1
/*    */     //   6: astore_2
/*    */     //   7: aload_2
/*    */     //   8: instanceof akka/routing/WithListeners
/*    */     //   11: ifeq -> 19
/*    */     //   14: iconst_1
/*    */     //   15: istore_3
/*    */     //   16: goto -> 21
/*    */     //   19: iconst_0
/*    */     //   20: istore_3
/*    */     //   21: iload_3
/*    */     //   22: ifeq -> 84
/*    */     //   25: aload_1
/*    */     //   26: checkcast akka/routing/WithListeners
/*    */     //   29: astore #4
/*    */     //   31: aload_0
/*    */     //   32: invokevirtual f : ()Lscala/Function1;
/*    */     //   35: aload #4
/*    */     //   37: invokevirtual f : ()Lscala/Function1;
/*    */     //   40: astore #5
/*    */     //   42: dup
/*    */     //   43: ifnonnull -> 55
/*    */     //   46: pop
/*    */     //   47: aload #5
/*    */     //   49: ifnull -> 63
/*    */     //   52: goto -> 76
/*    */     //   55: aload #5
/*    */     //   57: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   60: ifeq -> 76
/*    */     //   63: aload #4
/*    */     //   65: aload_0
/*    */     //   66: invokevirtual canEqual : (Ljava/lang/Object;)Z
/*    */     //   69: ifeq -> 76
/*    */     //   72: iconst_1
/*    */     //   73: goto -> 77
/*    */     //   76: iconst_0
/*    */     //   77: ifeq -> 84
/*    */     //   80: iconst_1
/*    */     //   81: goto -> 85
/*    */     //   84: iconst_0
/*    */     //   85: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #13	-> 0
/*    */     //   #63	-> 14
/*    */     //   #13	-> 21
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	86	0	this	Lakka/routing/WithListeners;
/*    */     //   0	86	1	x$1	Ljava/lang/Object;
/*    */   }
/*    */   
/*    */   public WithListeners(Function1<ActorRef, BoxedUnit> f) {
/* 13 */     Product.class.$init$(this);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\routing\WithListeners.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */