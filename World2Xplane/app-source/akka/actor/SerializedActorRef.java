/*     */ package akka.actor;
/*     */ 
/*     */ import akka.serialization.JavaSerializer$;
/*     */ import akka.serialization.Serialization$;
/*     */ import java.io.ObjectStreamException;
/*     */ import scala.Product;
/*     */ import scala.Serializable;
/*     */ import scala.collection.Iterator;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.ScalaRunTime$;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005mc!B\001\003\001\0221!AE*fe&\fG.\033>fI\006\033Go\034:SK\032T!a\001\003\002\013\005\034Go\034:\013\003\025\tA!Y6lCN!\001aB\007\021!\tA1\"D\001\n\025\005Q\021!B:dC2\f\027B\001\007\n\005\031\te.\037*fMB\021\001BD\005\003\037%\021q\001\025:pIV\034G\017\005\002\t#%\021!#\003\002\r'\026\024\030.\0317ju\006\024G.\032\005\t)\001\021)\032!C\001-\005!\001/\031;i\007\001)\022a\006\t\0031mq!\001C\r\n\005iI\021A\002)sK\022,g-\003\002\035;\t11\013\036:j]\036T!AG\005\t\021}\001!\021#Q\001\n]\tQ\001]1uQ\002BQ!\t\001\005\n\t\na\001P5oSRtDCA\022&!\t!\003!D\001\003\021\025!\002\0051\001\030\021\025\t\003\001\"\001()\t\031\003\006C\003*M\001\007!&\001\005bGR|'OU3g!\t!3&\003\002-\005\tA\021i\031;peJ+g\rC\003/\001\021\005q&A\006sK\006$'+Z:pYZ,G#A\004)\0075\n4\tE\002\teQJ!aM\005\003\rQD'o\\<t!\t)d\007\004\001\005\013]\002!\031\001\035\003\003Q\013\"!\017\037\021\005!Q\024BA\036\n\005\035qu\016\0365j]\036\004\"!\020!\017\005!q\024BA \n\003\035\001\030mY6bO\026L!!\021\"\003\023QC'o\\<bE2,'BA \nG\005!\005CA#K\033\0051%BA$I\003\tIwNC\001J\003\021Q\027M^1\n\005-3%!F(cU\026\034Go\025;sK\006lW\t_2faRLwN\034\005\b\033\002\t\t\021\"\001O\003\021\031w\016]=\025\005\rz\005b\002\013M!\003\005\ra\006\005\b#\002\t\n\021\"\001S\0039\031w\016]=%I\0264\027-\0367uIE*\022a\025\026\003/Q[\023!\026\t\003-nk\021a\026\006\0031f\013\021\"\0368dQ\026\0347.\0323\013\005iK\021AC1o]>$\030\r^5p]&\021Al\026\002\022k:\034\007.Z2lK\0224\026M]5b]\016,\007b\0020\001\003\003%\teX\001\016aJ|G-^2u!J,g-\033=\026\003\001\004\"!\0313\016\003\tT!a\031%\002\t1\fgnZ\005\0039\tDqA\032\001\002\002\023\005q-\001\007qe>$Wo\031;Be&$\0300F\001i!\tA\021.\003\002k\023\t\031\021J\034;\t\0171\004\021\021!C\001[\006q\001O]8ek\016$X\t\\3nK:$HC\0018r!\tAq.\003\002q\023\t\031\021I\\=\t\017I\\\027\021!a\001Q\006\031\001\020J\031\t\017Q\004\021\021!C!k\006y\001O]8ek\016$\030\n^3sCR|'/F\001w!\r9(P\\\007\002q*\021\0210C\001\013G>dG.Z2uS>t\027BA>y\005!IE/\032:bi>\024\bbB?\001\003\003%\tA`\001\tG\006tW)];bYR\031q0!\002\021\007!\t\t!C\002\002\004%\021qAQ8pY\026\fg\016C\004sy\006\005\t\031\0018\t\023\005%\001!!A\005B\005-\021\001\0035bg\"\034u\016Z3\025\003!D\021\"a\004\001\003\003%\t%!\005\002\021Q|7\013\036:j]\036$\022\001\031\005\n\003+\001\021\021!C!\003/\ta!Z9vC2\034HcA@\002\032!A!/a\005\002\002\003\007a\016K\003\001\003;\t\031\003E\002\t\003?I1!!\t\n\005A\031VM]5bYZ+'o]5p]VKEIH\001\002\017!\t9C\001E\001\t\005%\022AE*fe&\fG.\033>fI\006\033Go\034:SK\032\0042\001JA\026\r\035\t!\001#\001\005\003[\031B!a\013\b!!9\021%a\013\005\002\005EBCAA\025\021!\t)$a\013\005\002\005]\022!B1qa2LHcA\022\002:!1\021&a\rA\002)B!\"!\016\002,\005\005I\021QA\037)\r\031\023q\b\005\007)\005m\002\031A\f\t\025\005\r\0231FA\001\n\003\013)%A\004v]\006\004\b\017\\=\025\t\005\035\023Q\n\t\005\021\005%s#C\002\002L%\021aa\0249uS>t\007\"CA(\003\003\n\t\0211\001$\003\rAH\005\r\005\n]\005-\022\021!C\005\003'\"\"!!\026\021\007\005\f9&C\002\002Z\t\024aa\0242kK\016$\b")
/*     */ public class SerializedActorRef implements Product, Serializable {
/*     */   public static final long serialVersionUID = 1L;
/*     */   
/*     */   private final String path;
/*     */   
/*     */   public String path() {
/* 397 */     return this.path;
/*     */   }
/*     */   
/*     */   public SerializedActorRef copy(String path) {
/* 397 */     return new SerializedActorRef(path);
/*     */   }
/*     */   
/*     */   public String copy$default$1() {
/* 397 */     return path();
/*     */   }
/*     */   
/*     */   public String productPrefix() {
/* 397 */     return "SerializedActorRef";
/*     */   }
/*     */   
/*     */   public int productArity() {
/* 397 */     return 1;
/*     */   }
/*     */   
/*     */   public Object productElement(int x$1) {
/* 397 */     int i = x$1;
/* 397 */     switch (i) {
/*     */       default:
/* 397 */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */       case 0:
/*     */         break;
/*     */     } 
/* 397 */     return path();
/*     */   }
/*     */   
/*     */   public Iterator<Object> productIterator() {
/* 397 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */   }
/*     */   
/*     */   public boolean canEqual(Object x$1) {
/* 397 */     return x$1 instanceof SerializedActorRef;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 397 */     return ScalaRunTime$.MODULE$._hashCode(this);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 397 */     return ScalaRunTime$.MODULE$._toString(this);
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
/*     */     //   8: instanceof akka/actor/SerializedActorRef
/*     */     //   11: ifeq -> 19
/*     */     //   14: iconst_1
/*     */     //   15: istore_3
/*     */     //   16: goto -> 21
/*     */     //   19: iconst_0
/*     */     //   20: istore_3
/*     */     //   21: iload_3
/*     */     //   22: ifeq -> 84
/*     */     //   25: aload_1
/*     */     //   26: checkcast akka/actor/SerializedActorRef
/*     */     //   29: astore #4
/*     */     //   31: aload_0
/*     */     //   32: invokevirtual path : ()Ljava/lang/String;
/*     */     //   35: aload #4
/*     */     //   37: invokevirtual path : ()Ljava/lang/String;
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
/*     */     //   #397	-> 0
/*     */     //   #63	-> 14
/*     */     //   #397	-> 21
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	86	0	this	Lakka/actor/SerializedActorRef;
/*     */     //   0	86	1	x$1	Ljava/lang/Object;
/*     */   }
/*     */   
/*     */   public SerializedActorRef(String path) {
/* 397 */     Product.class.$init$(this);
/*     */   }
/*     */   
/*     */   public SerializedActorRef(ActorRef actorRef) {
/* 401 */     this(Serialization$.MODULE$.serializedActorPath(actorRef));
/*     */   }
/*     */   
/*     */   public Object readResolve() throws ObjectStreamException {
/* 405 */     ExtendedActorSystem extendedActorSystem = (ExtendedActorSystem)JavaSerializer$.MODULE$.currentSystem().value();
/* 406 */     if (extendedActorSystem == null)
/* 407 */       throw new IllegalStateException(
/* 408 */           "Trying to deserialize a serialized ActorRef without an ActorSystem in scope. Use 'akka.serialization.Serialization.currentSystem.withValue(system) { ... }'"); 
/* 411 */     return extendedActorSystem.provider().resolveActorRef(path());
/*     */   }
/*     */   
/*     */   public static SerializedActorRef apply(ActorRef paramActorRef) {
/*     */     return SerializedActorRef$.MODULE$.apply(paramActorRef);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\SerializedActorRef.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */