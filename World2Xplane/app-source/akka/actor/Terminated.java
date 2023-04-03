/*    */ package akka.actor;
/*    */ 
/*    */ import scala.Product;
/*    */ import scala.collection.Iterator;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ import scala.runtime.ScalaRunTime$;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\005\005d\001B\001\003\001\036\021!\002V3s[&t\027\r^3e\025\t\031A!A\003bGR|'OC\001\006\003\021\t7n[1\004\001M1\001\001\003\b\023+a\001\"!\003\007\016\003)Q\021aC\001\006g\016\fG.Y\005\003\033)\021a!\0218z%\0264\007CA\b\021\033\005\021\021BA\t\003\005M\tU\017^8SK\016,\027N^3e\033\026\0348/Y4f!\ty1#\003\002\025\005\ty\001k\\:tS\nd\027\020S1s[\032,H\016\005\002\n-%\021qC\003\002\b!J|G-^2u!\tI\021$\003\002\033\025\ta1+\032:jC2L'0\0312mK\"A1\001\001BK\002\023\005A$F\001\036!\tya$\003\002 \005\tA\021i\031;peJ+g\r\003\005\"\001\tE\t\025!\003\036\003\031\t7\r^8sA!\022\001e\t\t\003I\035j\021!\n\006\003M)\tQAY3b]NL!\001K\023\003\031\t+\027M\034)s_B,'\017^=\t\013)\002A\021A\026\002\021\035,G/Q2u_J$\022!\b\005\t[\001\021)\031!C\001]\005\021R\r_5ti\026t7-Z\"p]\032L'/\\3e+\005y\003CA\0051\023\t\t$BA\004C_>dW-\0318\t\021M\002!\021!Q\001\n=\n1#\032=jgR,gnY3D_:4\027N]7fI\002B#AM\022\t\013Y\002A\021A\034\002+\035,G/\022=jgR,gnY3D_:4\027N]7fIR\tq\006\003\005:\001\t\025\r\021\"\001/\003E\tG\r\032:fgN$VM]7j]\006$X\r\032\005\tw\001\021\t\021)A\005_\005\021\022\r\0323sKN\034H+\032:nS:\fG/\0323!Q\tQ4\005C\003?\001\021\005q'\001\013hKR\fE\r\032:fgN$VM]7j]\006$X\r\032\005\007\001\002!\t\001B!\002\rqJg.\033;?)\t\021e\tF\002D\t\026\003\"a\004\001\t\0135z\004\031A\030\t\013ez\004\031A\030\t\013\ry\004\031A\017\t\017!\003\021\021!C\001\023\006!1m\0349z)\tQU\nF\002D\0272CQ!L$A\002=BQ!O$A\002=BqaA$\021\002\003\007Q\004C\004P\001E\005I\021\001)\002\035\r|\007/\037\023eK\032\fW\017\034;%cU\t\021K\013\002\036%.\n1\013\005\002U36\tQK\003\002W/\006IQO\\2iK\016\\W\r\032\006\0031*\t!\"\0318o_R\fG/[8o\023\tQVKA\tv]\016DWmY6fIZ\013'/[1oG\026Dq\001\030\001\002\002\023\005S,A\007qe>$Wo\031;Qe\0264\027\016_\013\002=B\021q\fZ\007\002A*\021\021MY\001\005Y\006twMC\001d\003\021Q\027M^1\n\005\025\004'AB*ue&tw\rC\004h\001\005\005I\021\0015\002\031A\024x\016Z;di\006\023\030\016^=\026\003%\004\"!\0036\n\005-T!aA%oi\"9Q\016AA\001\n\003q\027A\0049s_\022,8\r^#mK6,g\016\036\013\003_J\004\"!\0039\n\005ET!aA!os\"91\017\\A\001\002\004I\027a\001=%c!9Q\017AA\001\n\0032\030a\0049s_\022,8\r^%uKJ\fGo\034:\026\003]\0042\001_>p\033\005I(B\001>\013\003)\031w\016\0347fGRLwN\\\005\003yf\024\001\"\023;fe\006$xN\035\005\b}\002\t\t\021\"\001\000\003!\031\027M\\#rk\006dGcA\030\002\002!91/`A\001\002\004y\007\"CA\003\001\005\005I\021IA\004\003!A\027m\0355D_\022,G#A5\t\023\005-\001!!A\005B\0055\021\001\003;p'R\024\030N\\4\025\003yC\021\"!\005\001\003\003%\t%a\005\002\r\025\fX/\0317t)\ry\023Q\003\005\tg\006=\021\021!a\001_\"*\001!!\007\002 A\031\021\"a\007\n\007\005u!B\001\tTKJL\027\r\034,feNLwN\\+J\tz\t\021aB\005\002$\t\t\t\021#\001\002&\005QA+\032:nS:\fG/\0323\021\007=\t9C\002\005\002\005\005\005\t\022AA\025'\021\t9\003\003\r\t\017\001\0139\003\"\001\002.Q\021\021Q\005\005\013\003\027\t9#!A\005F\0055\001BCA\032\003O\t\t\021\"!\0026\005)\021\r\0359msR!\021qGA!)\025\031\025\021HA\037\021\031i\023\021\007a\001_!\032\021\021H\022\t\re\n\t\0041\0010Q\r\tid\t\005\007\007\005E\002\031A\017)\007\005\0053\005\003\006\002H\005\035\022\021!CA\003\023\nq!\0368baBd\027\020\006\003\002L\005E\003\003B\005\002NuI1!a\024\013\005\031y\005\017^5p]\"I\0211KA#\003\003\005\raQ\001\004q\022\002\004BCA,\003O\t\t\021\"\003\002Z\005Y!/Z1e%\026\034x\016\034<f)\t\tY\006E\002`\003;J1!a\030a\005\031y%M[3di\002")
/*    */ public class Terminated implements AutoReceivedMessage, PossiblyHarmful, Product {
/*    */   public static final long serialVersionUID = 1L;
/*    */   
/*    */   private final ActorRef actor;
/*    */   
/*    */   private final boolean existenceConfirmed;
/*    */   
/*    */   private final boolean addressTerminated;
/*    */   
/*    */   public ActorRef actor() {
/* 96 */     return this.actor;
/*    */   }
/*    */   
/*    */   public ActorRef getActor() {
/* 96 */     return actor();
/*    */   }
/*    */   
/*    */   public Terminated copy(ActorRef actor, boolean existenceConfirmed, boolean addressTerminated) {
/* 96 */     return new Terminated(actor, 
/* 97 */         existenceConfirmed, 
/* 98 */         addressTerminated);
/*    */   }
/*    */   
/*    */   public ActorRef copy$default$1() {
/*    */     return actor();
/*    */   }
/*    */   
/*    */   public String productPrefix() {
/*    */     return "Terminated";
/*    */   }
/*    */   
/*    */   public int productArity() {
/*    */     return 1;
/*    */   }
/*    */   
/*    */   public Object productElement(int x$1) {
/*    */     int i = x$1;
/*    */     switch (i) {
/*    */       default:
/*    */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*    */       case 0:
/*    */         break;
/*    */     } 
/*    */     return actor();
/*    */   }
/*    */   
/*    */   public Iterator<Object> productIterator() {
/*    */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*    */   }
/*    */   
/*    */   public boolean canEqual(Object x$1) {
/*    */     return x$1 instanceof Terminated;
/*    */   }
/*    */   
/*    */   public int hashCode() {
/*    */     return ScalaRunTime$.MODULE$._hashCode(this);
/*    */   }
/*    */   
/*    */   public String toString() {
/*    */     return ScalaRunTime$.MODULE$._toString(this);
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
/*    */     //   8: instanceof akka/actor/Terminated
/*    */     //   11: ifeq -> 19
/*    */     //   14: iconst_1
/*    */     //   15: istore_3
/*    */     //   16: goto -> 21
/*    */     //   19: iconst_0
/*    */     //   20: istore_3
/*    */     //   21: iload_3
/*    */     //   22: ifeq -> 84
/*    */     //   25: aload_1
/*    */     //   26: checkcast akka/actor/Terminated
/*    */     //   29: astore #4
/*    */     //   31: aload_0
/*    */     //   32: invokevirtual actor : ()Lakka/actor/ActorRef;
/*    */     //   35: aload #4
/*    */     //   37: invokevirtual actor : ()Lakka/actor/ActorRef;
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
/*    */     //   #96	-> 0
/*    */     //   #63	-> 14
/*    */     //   #96	-> 21
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	86	0	this	Lakka/actor/Terminated;
/*    */     //   0	86	1	x$1	Ljava/lang/Object;
/*    */   }
/*    */   
/*    */   public Terminated(ActorRef actor, boolean existenceConfirmed, boolean addressTerminated) {
/*    */     Product.class.$init$(this);
/*    */   }
/*    */   
/*    */   public boolean existenceConfirmed() {
/*    */     return this.existenceConfirmed;
/*    */   }
/*    */   
/*    */   public boolean getExistenceConfirmed() {
/*    */     return existenceConfirmed();
/*    */   }
/*    */   
/*    */   public boolean addressTerminated() {
/* 98 */     return this.addressTerminated;
/*    */   }
/*    */   
/*    */   public boolean getAddressTerminated() {
/* 98 */     return addressTerminated();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\Terminated.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */