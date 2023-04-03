/*     */ package akka.actor;
/*     */ 
/*     */ import scala.Function1;
/*     */ import scala.Product;
/*     */ import scala.Serializable;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.ScalaRunTime$;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005\035b\001B\001\003\001\036\021Q\"Q2u_Jtu\016\036$pk:$'BA\002\005\003\025\t7\r^8s\025\005)\021\001B1lW\006\034\001a\005\003\001\021YQ\002CA\005\024\035\tQ\001C\004\002\f\0355\tAB\003\002\016\r\0051AH]8pizJ\021aD\001\006g\016\fG.Y\005\003#I\tq\001]1dW\006<WMC\001\020\023\t!RC\001\tSk:$\030.\\3Fq\016,\007\017^5p]*\021\021C\005\t\003/ai\021AE\005\0033I\021q\001\025:pIV\034G\017\005\002\0307%\021AD\005\002\r'\026\024\030.\0317ju\006\024G.\032\005\t=\001\021)\032!C\001?\005I1/\0327fGRLwN\\\013\002AA\021\021EI\007\002\005%\0211E\001\002\017\003\016$xN]*fY\026\034G/[8o\021!)\003A!E!\002\023\001\023AC:fY\026\034G/[8oA!)q\005\001C\001Q\0051A(\0338jiz\"\"!\013\026\021\005\005\002\001\"\002\020'\001\004\001\003b\002\027\001\003\003%\t!L\001\005G>\004\030\020\006\002*]!9ad\013I\001\002\004\001\003b\002\031\001#\003%\t!M\001\017G>\004\030\020\n3fM\006,H\016\036\0232+\005\021$F\001\0214W\005!\004CA\033;\033\0051$BA\0349\003%)hn\0315fG.,GM\003\002:%\005Q\021M\0348pi\006$\030n\0348\n\005m2$!E;oG\",7m[3e-\006\024\030.\0318dK\"9Q\bAA\001\n\003r\024!\0049s_\022,8\r\036)sK\032L\0070F\001@!\t\001U)D\001B\025\t\0215)\001\003mC:<'\"\001#\002\t)\fg/Y\005\003\r\006\023aa\025;sS:<\007b\002%\001\003\003%\t!S\001\raJ|G-^2u\003JLG/_\013\002\025B\021qcS\005\003\031J\0211!\0238u\021\035q\005!!A\005\002=\013a\002\035:pIV\034G/\0227f[\026tG\017\006\002Q'B\021q#U\005\003%J\0211!\0218z\021\035!V*!AA\002)\0131\001\037\0232\021\0351\006!!A\005B]\013q\002\035:pIV\034G/\023;fe\006$xN]\013\0021B\031\021\f\030)\016\003iS!a\027\n\002\025\r|G\016\\3di&|g.\003\002^5\nA\021\n^3sCR|'\017C\004`\001\005\005I\021\0011\002\021\r\fg.R9vC2$\"!\0313\021\005]\021\027BA2\023\005\035\021un\0347fC:Dq\001\0260\002\002\003\007\001\013C\004g\001\005\005I\021I4\002\021!\f7\017[\"pI\026$\022A\023\005\bS\002\t\t\021\"\021k\003\031)\027/^1mgR\021\021m\033\005\b)\"\f\t\0211\001QQ\r\001Q\016\035\t\003/9L!a\034\n\003!M+'/[1m-\026\0248/[8o+&#e$A\001\b\017I\024\021\021!E\001g\006i\021i\031;pe:{GOR8v]\022\004\"!\t;\007\017\005\021\021\021!E\001kN\031AO\036\016\021\t]T\b%K\007\002q*\021\021PE\001\beVtG/[7f\023\tY\bPA\tBEN$(/Y2u\rVt7\r^5p]FBQa\n;\005\002u$\022a\035\005\tR\f\t\021\"\022\002\002\005AAo\\*ue&tw\rF\001@\021%\t)\001^A\001\n\003\0139!A\003baBd\027\020F\002*\003\023AaAHA\002\001\004\001\003\"CA\007i\006\005I\021QA\b\003\035)h.\0319qYf$B!!\005\002\030A!q#a\005!\023\r\t)B\005\002\007\037B$\030n\0348\t\023\005e\0211BA\001\002\004I\023a\001=%a!I\021Q\004;\002\002\023%\021qD\001\fe\026\fGMU3t_24X\r\006\002\002\"A\031\001)a\t\n\007\005\025\022I\001\004PE*,7\r\036")
/*     */ public class ActorNotFound extends RuntimeException implements Product, Serializable {
/*     */   public static final long serialVersionUID = 1L;
/*     */   
/*     */   private final ActorSelection selection;
/*     */   
/*     */   public static <A> Function1<ActorSelection, A> andThen(Function1<ActorNotFound, A> paramFunction1) {
/*     */     return ActorNotFound$.MODULE$.andThen(paramFunction1);
/*     */   }
/*     */   
/*     */   public static <A> Function1<A, ActorNotFound> compose(Function1<A, ActorSelection> paramFunction1) {
/*     */     return ActorNotFound$.MODULE$.compose(paramFunction1);
/*     */   }
/*     */   
/*     */   public ActorSelection selection() {
/* 287 */     return this.selection;
/*     */   }
/*     */   
/*     */   public ActorNotFound copy(ActorSelection selection) {
/* 287 */     return new ActorNotFound(selection);
/*     */   }
/*     */   
/*     */   public ActorSelection copy$default$1() {
/* 287 */     return selection();
/*     */   }
/*     */   
/*     */   public String productPrefix() {
/* 287 */     return "ActorNotFound";
/*     */   }
/*     */   
/*     */   public int productArity() {
/* 287 */     return 1;
/*     */   }
/*     */   
/*     */   public Object productElement(int x$1) {
/* 287 */     int i = x$1;
/* 287 */     switch (i) {
/*     */       default:
/* 287 */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */       case 0:
/*     */         break;
/*     */     } 
/* 287 */     return selection();
/*     */   }
/*     */   
/*     */   public Iterator<Object> productIterator() {
/* 287 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */   }
/*     */   
/*     */   public boolean canEqual(Object x$1) {
/* 287 */     return x$1 instanceof ActorNotFound;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 287 */     return ScalaRunTime$.MODULE$._hashCode(this);
/*     */   }
/*     */   
/*     */   public boolean equals(Object x$1) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: aload_1
/*     */     //   2: if_acmpeq -> 80
/*     */     //   5: aload_1
/*     */     //   6: astore_2
/*     */     //   7: aload_2
/*     */     //   8: instanceof akka/actor/ActorNotFound
/*     */     //   11: ifeq -> 19
/*     */     //   14: iconst_1
/*     */     //   15: istore_3
/*     */     //   16: goto -> 21
/*     */     //   19: iconst_0
/*     */     //   20: istore_3
/*     */     //   21: iload_3
/*     */     //   22: ifeq -> 84
/*     */     //   25: aload_1
/*     */     //   26: checkcast akka/actor/ActorNotFound
/*     */     //   29: astore #4
/*     */     //   31: aload_0
/*     */     //   32: invokevirtual selection : ()Lakka/actor/ActorSelection;
/*     */     //   35: aload #4
/*     */     //   37: invokevirtual selection : ()Lakka/actor/ActorSelection;
/*     */     //   40: astore #5
/*     */     //   42: dup
/*     */     //   43: ifnonnull -> 55
/*     */     //   46: pop
/*     */     //   47: aload #5
/*     */     //   49: ifnull -> 63
/*     */     //   52: goto -> 76
/*     */     //   55: aload #5
/*     */     //   57: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   60: ifeq -> 76
/*     */     //   63: aload #4
/*     */     //   65: aload_0
/*     */     //   66: invokevirtual canEqual : (Ljava/lang/Object;)Z
/*     */     //   69: ifeq -> 76
/*     */     //   72: iconst_1
/*     */     //   73: goto -> 77
/*     */     //   76: iconst_0
/*     */     //   77: ifeq -> 84
/*     */     //   80: iconst_1
/*     */     //   81: goto -> 85
/*     */     //   84: iconst_0
/*     */     //   85: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #287	-> 0
/*     */     //   #63	-> 14
/*     */     //   #287	-> 21
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	86	0	this	Lakka/actor/ActorNotFound;
/*     */     //   0	86	1	x$1	Ljava/lang/Object;
/*     */   }
/*     */   
/*     */   public ActorNotFound(ActorSelection selection) {
/* 287 */     super((new StringBuilder()).append("Actor not found for: ").append(selection).toString());
/* 287 */     Product.class.$init$(this);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\ActorNotFound.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */