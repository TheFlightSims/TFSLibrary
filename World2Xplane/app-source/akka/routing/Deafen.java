/*    */ package akka.routing;
/*    */ 
/*    */ import akka.actor.ActorRef;
/*    */ import scala.Function1;
/*    */ import scala.Product;
/*    */ import scala.Serializable;
/*    */ import scala.collection.Iterator;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ import scala.runtime.ScalaRunTime$;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\005ea\001B\001\003\001\036\021a\001R3bM\026t'BA\002\005\003\035\021x.\036;j]\036T\021!B\001\005C.\\\027m\001\001\024\013\001AaBE\013\021\005%aQ\"\001\006\013\003-\tQa]2bY\006L!!\004\006\003\r\005s\027PU3g!\ty\001#D\001\003\023\t\t\"AA\bMSN$XM\\3s\033\026\0348/Y4f!\tI1#\003\002\025\025\t9\001K]8ek\016$\bCA\005\027\023\t9\"B\001\007TKJL\027\r\\5{C\ndW\r\003\005\032\001\tU\r\021\"\001\033\003!a\027n\035;f]\026\024X#A\016\021\005qyR\"A\017\013\005y!\021!B1di>\024\030B\001\021\036\005!\t5\r^8s%\0264\007\002\003\022\001\005#\005\013\021B\016\002\0231L7\017^3oKJ\004\003\"\002\023\001\t\003)\023A\002\037j]&$h\b\006\002'OA\021q\002\001\005\0063\r\002\ra\007\005\bS\001\t\t\021\"\001+\003\021\031w\016]=\025\005\031Z\003bB\r)!\003\005\ra\007\005\b[\001\t\n\021\"\001/\0039\031w\016]=%I\0264\027-\0367uIE*\022a\f\026\0037AZ\023!\r\t\003e]j\021a\r\006\003iU\n\021\"\0368dQ\026\0347.\0323\013\005YR\021AC1o]>$\030\r^5p]&\021\001h\r\002\022k:\034\007.Z2lK\0224\026M]5b]\016,\007b\002\036\001\003\003%\teO\001\016aJ|G-^2u!J,g-\033=\026\003q\002\"!\020\"\016\003yR!a\020!\002\t1\fgn\032\006\002\003\006!!.\031<b\023\t\031eH\001\004TiJLgn\032\005\b\013\002\t\t\021\"\001G\0031\001(o\0343vGR\f%/\033;z+\0059\005CA\005I\023\tI%BA\002J]RDqa\023\001\002\002\023\005A*\001\bqe>$Wo\031;FY\026lWM\034;\025\0055\003\006CA\005O\023\ty%BA\002B]fDq!\025&\002\002\003\007q)A\002yIEBqa\025\001\002\002\023\005C+A\bqe>$Wo\031;Ji\026\024\030\r^8s+\005)\006c\001,Z\0336\tqK\003\002Y\025\005Q1m\0347mK\016$\030n\0348\n\005i;&\001C%uKJ\fGo\034:\t\017q\003\021\021!C\001;\006A1-\0318FcV\fG\016\006\002_CB\021\021bX\005\003A*\021qAQ8pY\026\fg\016C\004R7\006\005\t\031A'\t\017\r\004\021\021!C!I\006A\001.Y:i\007>$W\rF\001H\021\0351\007!!A\005B\035\f\001\002^8TiJLgn\032\013\002y!9\021\016AA\001\n\003R\027AB3rk\006d7\017\006\002_W\"9\021\013[A\001\002\004iuaB7\003\003\003E\tA\\\001\007\t\026\fg-\0328\021\005=ygaB\001\003\003\003E\t\001]\n\004_F,\002\003\002:v7\031j\021a\035\006\003i*\tqA];oi&lW-\003\002wg\n\t\022IY:ue\006\034GOR;oGRLwN\\\031\t\013\021zG\021\001=\025\0039DqAZ8\002\002\023\025s\rC\004|_\006\005I\021\021?\002\013\005\004\b\017\\=\025\005\031j\b\"B\r{\001\004Y\002\002C@p\003\003%\t)!\001\002\017Ut\027\r\0359msR!\0211AA\005!\021I\021QA\016\n\007\005\035!B\001\004PaRLwN\034\005\t\003\027q\030\021!a\001M\005\031\001\020\n\031\t\023\005=q.!A\005\n\005E\021a\003:fC\022\024Vm]8mm\026$\"!a\005\021\007u\n)\"C\002\002\030y\022aa\0242kK\016$\b")
/*    */ public class Deafen implements ListenerMessage, Product, Serializable {
/*    */   private final ActorRef listener;
/*    */   
/*    */   public static <A> Function1<ActorRef, A> andThen(Function1<Deafen, A> paramFunction1) {
/*    */     return Deafen$.MODULE$.andThen(paramFunction1);
/*    */   }
/*    */   
/*    */   public static <A> Function1<A, Deafen> compose(Function1<A, ActorRef> paramFunction1) {
/*    */     return Deafen$.MODULE$.compose(paramFunction1);
/*    */   }
/*    */   
/*    */   public ActorRef listener() {
/* 12 */     return this.listener;
/*    */   }
/*    */   
/*    */   public Deafen copy(ActorRef listener) {
/* 12 */     return new Deafen(listener);
/*    */   }
/*    */   
/*    */   public ActorRef copy$default$1() {
/* 12 */     return listener();
/*    */   }
/*    */   
/*    */   public String productPrefix() {
/* 12 */     return "Deafen";
/*    */   }
/*    */   
/*    */   public int productArity() {
/* 12 */     return 1;
/*    */   }
/*    */   
/*    */   public Object productElement(int x$1) {
/* 12 */     int i = x$1;
/* 12 */     switch (i) {
/*    */       default:
/* 12 */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*    */       case 0:
/*    */         break;
/*    */     } 
/* 12 */     return listener();
/*    */   }
/*    */   
/*    */   public Iterator<Object> productIterator() {
/* 12 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*    */   }
/*    */   
/*    */   public boolean canEqual(Object x$1) {
/* 12 */     return x$1 instanceof Deafen;
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 12 */     return ScalaRunTime$.MODULE$._hashCode(this);
/*    */   }
/*    */   
/*    */   public String toString() {
/* 12 */     return ScalaRunTime$.MODULE$._toString(this);
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
/*    */     //   8: instanceof akka/routing/Deafen
/*    */     //   11: ifeq -> 19
/*    */     //   14: iconst_1
/*    */     //   15: istore_3
/*    */     //   16: goto -> 21
/*    */     //   19: iconst_0
/*    */     //   20: istore_3
/*    */     //   21: iload_3
/*    */     //   22: ifeq -> 84
/*    */     //   25: aload_1
/*    */     //   26: checkcast akka/routing/Deafen
/*    */     //   29: astore #4
/*    */     //   31: aload_0
/*    */     //   32: invokevirtual listener : ()Lakka/actor/ActorRef;
/*    */     //   35: aload #4
/*    */     //   37: invokevirtual listener : ()Lakka/actor/ActorRef;
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
/*    */     //   #12	-> 0
/*    */     //   #63	-> 14
/*    */     //   #12	-> 21
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	86	0	this	Lakka/routing/Deafen;
/*    */     //   0	86	1	x$1	Ljava/lang/Object;
/*    */   }
/*    */   
/*    */   public Deafen(ActorRef listener) {
/* 12 */     Product.class.$init$(this);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\routing\Deafen.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */