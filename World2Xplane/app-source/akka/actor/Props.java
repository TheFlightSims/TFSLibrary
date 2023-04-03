/*     */ package akka.actor;
/*     */ 
/*     */ import akka.japi.Creator;
/*     */ import akka.routing.RouterConfig;
/*     */ import com.typesafe.config.Config;
/*     */ import java.lang.reflect.Type;
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.Option;
/*     */ import scala.PartialFunction;
/*     */ import scala.Product;
/*     */ import scala.Serializable;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.immutable.Seq;
/*     */ import scala.reflect.ClassTag;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ import scala.runtime.AbstractPartialFunction;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.Nothing$;
/*     */ import scala.runtime.ScalaRunTime$;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\rer!B\001\003\021\0039\021!\002)s_B\034(BA\002\005\003\025\t7\r^8s\025\005)\021\001B1lW\006\034\001\001\005\002\t\0235\t!AB\003\013\005!\0051BA\003Qe>\0048oE\002\n\031I\001\"!\004\t\016\0039Q\021aD\001\006g\016\fG.Y\005\003#9\021a!\0218z%\0264\007CA\007\024\023\t!bB\001\007TKJL\027\r\\5{C\ndW\rC\003\027\023\021\005q#\001\004=S:LGO\020\013\002\017!9\021$\003b\001\n\013Q\022A\0043fM\006,H\016^\"sK\006$xN]\013\0027A\031Q\002\b\020\n\005uq!!\003$v]\016$\030n\03481!\tAq$\003\002!\005\t)\021i\031;pe\"1!%\003Q\001\016m\tq\002Z3gCVdGo\021:fCR|'\017\t\005\bI%\021\r\021\"\002&\003I!WMZ1vYR\024v.\036;fIB\023x\016]:\026\003\031\002\"a\n\026\016\003!R!!\013\003\002\017I|W\017^5oO&\0211\006\013\002\r%>,H/\032:D_:4\027n\032\005\007[%\001\013Q\002\024\002'\021,g-Y;miJ{W\017^3e!J|\007o\035\021\t\017=J!\031!C\003a\005iA-\0324bk2$H)\0329m_f,\022!\r\t\003\021IJ!a\r\002\003\r\021+\007\017\\8z\021\031)\024\002)A\007c\005qA-\0324bk2$H)\0329m_f\004\003bB\034\n\005\004%)\001O\001\006K6\004H/_\013\002sA\021\001B\017\004\005\025\t\0215h\005\003;\031q\022\002CA\007>\023\tqdBA\004Qe>$Wo\031;\t\021\001S$Q3A\005\002A\na\001Z3qY>L\b\002\003\";\005#\005\013\021B\031\002\017\021,\007\017\\8zA!AAI\017BK\002\023\005Q)A\003dY\006T(0F\001Ga\t9\005\013E\002I\027:s!!D%\n\005)s\021A\002)sK\022,g-\003\002M\033\n)1\t\\1tg*\021!J\004\t\003\037Bc\001\001B\005R%\006\005\t\021!B\0011\n\031q\fJ\033\t\021MS$\021#Q\001\nQ\013aa\0317buj\004\003GA+X!\rA5J\026\t\003\037^#\021\"\025*\002\002\003\005)\021\001-\022\005ec\006CA\007[\023\tYfBA\004O_RD\027N\\4\021\0055i\026B\0010\017\005\r\te.\037\005\tAj\022)\032!C\001C\006!\021M]4t+\005\021\007cA2i96\tAM\003\002fM\006I\021.\\7vi\006\024G.\032\006\003O:\t!bY8mY\026\034G/[8o\023\tIGMA\002TKFD\001b\033\036\003\022\003\006IAY\001\006CJ<7\017\t\005\006-i\"\t!\034\013\005s9|G\017C\003AY\002\007\021\007C\003EY\002\007\001\017\r\002rgB\031\001j\023:\021\005=\033H!C)p\003\003\005\tQ!\001Y\021\025\001G\0161\001c\021%1(\b1A\001B\003&q/A\005`aJ|G-^2feB\021\001\002_\005\003s\n\021Q#\0238eSJ,7\r^!di>\024\bK]8ek\016,'\017\013\002vwB\021Q\002`\005\003{:\021\021\002\036:b]NLWM\034;\t\025}T\004\031!A!B\023\t\t!A\t`G\006\034\007.\0323BGR|'o\0217bgN\004D!a\001\002\bA!\001jSA\003!\ry\025q\001\003\f\003\023q\030\021!A\001\006\003\tYAA\002`IY\n\"!\027\020)\005y\\\b\002CA\tu\001&I!a\005\002\021A\024x\016Z;dKJ,\022a\036\005\t\003/Q\004\025\"\003\002\032\005\0012-Y2iK\022\f5\r^8s\0072\f7o]\013\003\0037\001D!!\b\002\"A!\001jSA\020!\ry\025\021\005\003\r\003G\t)\"!A\001\002\013\005\0211\002\002\004?\022:\004bBA\024u\021\005\021\021F\001\013I&\034\b/\031;dQ\026\024XCAA\026!\rA\025QF\005\004\003_i%AB*ue&tw\rC\004\0024i\"\t!!\013\002\0175\f\027\016\0342pq\"1\021q\007\036\005\002\025\nAB]8vi\026\0248i\0348gS\036Dq!a\017;\t\003\ti$\001\bxSRDG)[:qCR\034\007.\032:\025\007e\ny\004\003\005\002B\005e\002\031AA\026\003\005!\007bBA#u\021\005\021qI\001\fo&$\b.T1jY\n|\007\020F\002:\003\023B\001\"a\023\002D\001\007\0211F\001\002[\"9\021q\n\036\005\002\005E\023AC<ji\"\024v.\036;feR\031\021(a\025\t\017\005U\023Q\na\001M\005\t!\017C\004\002Zi\"\t!a\027\002\025]LG\017\033#fa2|\027\020F\002:\003;Bq!!\021\002X\001\007\021\007C\004\002bi\"\t!a\031\002\025\005\034Go\034:DY\006\0348\017\006\002\002fA\"\021qMA6!\021A5*!\033\021\007=\013Y\007\002\007\002n\005}\023\021!A\001\006\003\tYAA\002`IaB\001\"!\035;\t\003!\0211O\001\t]\026<\030i\031;peR\ta\004C\005\002xi\n\t\021\"\001\002z\005!1m\0349z)\035I\0241PA?\003B\001\002QA;!\003\005\r!\r\005\t\t\006U\004\023!a\001a\"A\001-!\036\021\002\003\007!\rC\005\002\004j\n\n\021\"\001\002\006\006q1m\0349zI\021,g-Y;mi\022\nTCAADU\r\t\024\021R\026\003\003\027\003B!!$\002\0306\021\021q\022\006\005\003#\013\031*A\005v]\016DWmY6fI*\031\021Q\023\b\002\025\005tgn\034;bi&|g.\003\003\002\032\006=%!E;oG\",7m[3e-\006\024\030.\0318dK\"I\021Q\024\036\022\002\023\005\021qT\001\017G>\004\030\020\n3fM\006,H\016\036\0233+\t\t\t\013\r\003\002$\006U&\006BAS\003\023\003b!a*\0022\006MVBAAU\025\021\tY+!,\002\t1\fgn\032\006\003\003_\013AA[1wC&\031A*!+\021\007=\013)\f\002\006R\0037\013\t\021!A\003\002aC\021\"!/;#\003%\t!a/\002\035\r|\007/\037\023eK\032\fW\017\034;%gU\021\021Q\030\026\004E\006%\005\"CAau\005\005I\021IAb\0035\001(o\0343vGR\004&/\0324jqV\021\021Q\031\t\005\003O\0139-\003\003\0020\005%\006\"CAfu\005\005I\021AAg\0031\001(o\0343vGR\f%/\033;z+\t\ty\rE\002\016\003#L1!a5\017\005\rIe\016\036\005\n\003/T\024\021!C\001\0033\fa\002\035:pIV\034G/\0227f[\026tG\017F\002]\0037D!\"!8\002V\006\005\t\031AAh\003\rAH%\r\005\n\003CT\024\021!C!\003G\fq\002\035:pIV\034G/\023;fe\006$xN]\013\003\003K\004R!a:\002jrk\021AZ\005\004\003W4'\001C%uKJ\fGo\034:\t\023\005=((!A\005\002\005E\030\001C2b]\026\013X/\0317\025\t\005M\030\021 \t\004\033\005U\030bAA|\035\t9!i\\8mK\006t\007\"CAo\003[\f\t\0211\001]\021%\tiPOA\001\n\003\ny0\001\005iCND7i\0343f)\t\ty\rC\005\003\004i\n\t\021\"\021\003\006\005AAo\\*ue&tw\r\006\002\002F\"I!\021\002\036\002\002\023\005#1B\001\007KF,\030\r\\:\025\t\005M(Q\002\005\n\003;\0249!!AA\002qCSA\017B\t\005/\0012!\004B\n\023\r\021)B\004\002\021'\026\024\030.\0317WKJ\034\030n\0348V\023\022s\022A\001\005\b\0057I\001\025!\004:\003\031)W\016\035;zA!A!qD\005C\002\023\025\001(A\004eK\032\fW\017\034;\t\017\t\r\022\002)A\007s\005AA-\0324bk2$\bEB\004\003(%\001AA!\013\003\025\025k\007\017^=BGR|'o\005\003\003&1q\002b\002\f\003&\021\005!Q\006\013\003\005_\001BA!\r\003&5\t\021\002\003\005\0036\t\025B\021\001B\034\003\035\021XmY3jm\026,\"A!\017\017\t\tm\"\021\t\b\004\021\tu\022b\001B \005\005)\021i\031;pe&!!1\tB#\0035)W\016\035;z\005\026D\027M^5pe*\031!q\b\002\t\017\t%\023\002\"\001\003L\005)\021\r\0359msV!!Q\nB2)\t\021y\005F\002:\005#B!Ba\025\003H\005\005\t9\001B+\003))g/\0333f]\016,G%\r\t\007\005/\022iF!\031\016\005\te#b\001B.\035\0059!/\0324mK\016$\030\002\002B0\0053\022\001b\0217bgN$\026m\032\t\004\037\n\rD\001\003B3\005\017\022\r!a\003\003\003QCqA!\023\n\t\003\021I'\006\003\003l\t]D\003\002B7\005s\"2!\017B8\021)\021\tHa\032\002\002\003\017!1O\001\013KZLG-\0328dK\022\022\004C\002B,\005;\022)\bE\002P\005o\"\001B!\032\003h\t\007\0211\002\005\n\005w\0229\007\"a\001\005{\nqa\031:fCR|'\017E\003\016\005\022)(C\002\003\002:\021\001\002\0202z]\006lWM\020\005\b\005\013KA\021\002BD\003\035i7\016\025:paN$R!\017BE\005/C\001Ba#\003\004\002\007!QR\001\rG2\f7o](g\003\016$xN\035\031\005\005\037\023\031\n\005\003I\027\nE\005cA(\003\024\022Y!Q\023BE\003\003\005\tQ!\001Y\005\ryF%\r\005\b\0053\023\031\t1\001\034\003\021\031Go\034:\t\017\t%\023\002\"\001\003\036R)\021Ha(\003,\"9AIa'A\002\t\005\006\007\002BR\005O\003B\001S&\003&B\031qJa*\005\027\t%&qTA\001\002\003\025\t\001\027\002\004?\022\022\004b\0021\003\034\002\007!Q\026\t\005\033\t=F,C\002\0032:\021!\002\020:fa\026\fG/\0323?\021\035\021),\003C\001\005o\013aa\031:fCR,G#B\035\003:\n\025\007b\002#\0034\002\007!1\030\031\005\005{\023\t\r\005\003I\027\n}\006cA(\003B\022Y!1\031B]\003\003\005\tQ!\001Y\005\ryFe\r\005\bA\nM\006\031\001Bd!\021i!q\026\007)\t\tM&1\032\t\005\005\033\024y-\004\002\002\024&!!\021[AJ\005\0351\030M]1sONDqA!.\n\t\003\021).\006\003\003X\n%HcA\035\003Z\"A!1\020Bj\001\004\021Y\016\005\004\003^\n\r(q]\007\003\005?T1A!9\005\003\021Q\027\r]5\n\t\t\025(q\034\002\b\007J,\027\r^8s!\ry%\021\036\003\t\005K\022\031N1\001\002\f!9!QW\005\005\002\t5X\003\002Bx\005o$R!\017By\005sD\001\"!\031\003l\002\007!1\037\t\005\021.\023)\020E\002P\005o$\001B!\032\003l\n\007\0211\002\005\t\005w\022Y\0171\001\003|B1!Q\034Br\005kD\021B!\023\n\003\003%\tIa@\025\017e\032\taa\001\004\016!1\001I!@A\002EBq\001\022B\001\004\031)\001\r\003\004\b\r-\001\003\002%L\007\023\0012aTB\006\t)\t61AA\001\002\003\025\t\001\027\005\007A\nu\b\031\0012\t\023\rE\021\"!A\005\002\016M\021aB;oCB\004H.\037\013\005\007+\031I\003\r\003\004\030\r\035\002#B\007\004\032\ru\021bAB\016\035\t1q\n\035;j_:\004r!DB\020c\r\r\"-C\002\004\"9\021a\001V;qY\026\034\004CBAT\003c\033)\003E\002P\007O!!\"UB\b\003\003\005\tQ!\001Y\021%\031Yca\004\002\002\003\007\021(A\002yIAB\021ba\f\n\003\003%Ia!\r\002\027I,\027\r\032*fg>dg/\032\013\003\007g\001B!a*\0046%!1qGAU\005\031y%M[3di\002")
/*     */ public final class Props implements Product, Serializable {
/*     */   public static final long serialVersionUID = 2L;
/*     */   
/*     */   private final Deploy deploy;
/*     */   
/*     */   private final Class<?> clazz;
/*     */   
/*     */   private final Seq<Object> args;
/*     */   
/*     */   private transient IndirectActorProducer _producer;
/*     */   
/*     */   private transient Class<? extends Actor> _cachedActorClass;
/*     */   
/*     */   public static class $anonfun$2 extends AbstractFunction0<Nothing$> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Nothing$ apply() {
/*  34 */       throw new UnsupportedOperationException("No actor creator specified!");
/*     */     }
/*     */   }
/*     */   
/*     */   public static class EmptyActor implements Actor {
/*     */     private final ActorContext context;
/*     */     
/*     */     private final ActorRef self;
/*     */     
/*     */     public ActorContext context() {
/*  61 */       return this.context;
/*     */     }
/*     */     
/*     */     public final ActorRef self() {
/*  61 */       return this.self;
/*     */     }
/*     */     
/*     */     public void akka$actor$Actor$_setter_$context_$eq(ActorContext x$1) {
/*  61 */       this.context = x$1;
/*     */     }
/*     */     
/*     */     public final void akka$actor$Actor$_setter_$self_$eq(ActorRef x$1) {
/*  61 */       this.self = x$1;
/*     */     }
/*     */     
/*     */     public final ActorRef sender() {
/*  61 */       return Actor$class.sender(this);
/*     */     }
/*     */     
/*     */     public void aroundReceive(PartialFunction receive, Object msg) {
/*  61 */       Actor$class.aroundReceive(this, receive, msg);
/*     */     }
/*     */     
/*     */     public void aroundPreStart() {
/*  61 */       Actor$class.aroundPreStart(this);
/*     */     }
/*     */     
/*     */     public void aroundPostStop() {
/*  61 */       Actor$class.aroundPostStop(this);
/*     */     }
/*     */     
/*     */     public void aroundPreRestart(Throwable reason, Option message) {
/*  61 */       Actor$class.aroundPreRestart(this, reason, message);
/*     */     }
/*     */     
/*     */     public void aroundPostRestart(Throwable reason) {
/*  61 */       Actor$class.aroundPostRestart(this, reason);
/*     */     }
/*     */     
/*     */     public SupervisorStrategy supervisorStrategy() {
/*  61 */       return Actor$class.supervisorStrategy(this);
/*     */     }
/*     */     
/*     */     public void preStart() throws Exception {
/*  61 */       Actor$class.preStart(this);
/*     */     }
/*     */     
/*     */     public void postStop() throws Exception {
/*  61 */       Actor$class.postStop(this);
/*     */     }
/*     */     
/*     */     public void preRestart(Throwable reason, Option message) throws Exception {
/*  61 */       Actor$class.preRestart(this, reason, message);
/*     */     }
/*     */     
/*     */     public void postRestart(Throwable reason) throws Exception {
/*  61 */       Actor$class.postRestart(this, reason);
/*     */     }
/*     */     
/*     */     public void unhandled(Object message) {
/*  61 */       Actor$class.unhandled(this, message);
/*     */     }
/*     */     
/*     */     public EmptyActor() {
/*  61 */       Actor$class.$init$(this);
/*     */     }
/*     */     
/*     */     public Actor.emptyBehavior$ receive() {
/*  62 */       return Actor.emptyBehavior$.MODULE$;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Props$$anonfun$1 extends AbstractPartialFunction<Type, Class<?>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Class ac$1;
/*     */     
/*     */     public final <A1 extends Type, B1> B1 applyOrElse(Type x1, Function1 default) {
/* 120 */       Type type = x1;
/* 120 */       if (type instanceof Class) {
/* 120 */         Class<?> clazz = (Class)type;
/* 120 */         if (this.ac$1.isAssignableFrom(clazz)) {
/* 120 */           Class clazz1 = this.ac$1;
/* 120 */           if (clazz == null) {
/* 120 */             if (clazz1 != null)
/* 120 */               return (B1)clazz; 
/* 120 */           } else if (!clazz.equals(clazz1)) {
/* 120 */             return (B1)clazz;
/*     */           } 
/*     */         } 
/*     */       } 
/* 120 */       return (B1)default.apply(x1);
/*     */     }
/*     */     
/*     */     public final boolean isDefinedAt(Type x1) {
/* 120 */       Type type = x1;
/* 120 */       if (type instanceof Class) {
/* 120 */         Class<?> clazz = (Class)type;
/* 120 */         if (this.ac$1.isAssignableFrom(clazz)) {
/* 120 */           Class clazz1 = this.ac$1;
/* 120 */           if (clazz == null) {
/* 120 */             if (clazz1 != null)
/* 120 */               return true; 
/* 120 */           } else if (!clazz.equals(clazz1)) {
/* 120 */             return true;
/*     */           } 
/*     */         } 
/*     */       } 
/* 120 */       return false;
/*     */     }
/*     */     
/*     */     public Props$$anonfun$1(Class ac$1) {}
/*     */   }
/*     */   
/*     */   public static class Props$$anonfun$3 extends AbstractFunction0<Class<Actor>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Class ac$1;
/*     */     
/*     */     public final Class<Actor> apply() {
/* 120 */       return this.ac$1;
/*     */     }
/*     */     
/*     */     public Props$$anonfun$3(Class ac$1) {}
/*     */   }
/*     */   
/*     */   public Deploy deploy() {
/* 161 */     return this.deploy;
/*     */   }
/*     */   
/*     */   public Class<?> clazz() {
/* 161 */     return this.clazz;
/*     */   }
/*     */   
/*     */   public Seq<Object> args() {
/* 161 */     return this.args;
/*     */   }
/*     */   
/*     */   public Props copy(Deploy deploy, Class<?> clazz, Seq<Object> args) {
/* 161 */     return new Props(deploy, clazz, args);
/*     */   }
/*     */   
/*     */   public Deploy copy$default$1() {
/* 161 */     return deploy();
/*     */   }
/*     */   
/*     */   public Class<?> copy$default$2() {
/* 161 */     return clazz();
/*     */   }
/*     */   
/*     */   public Seq<Object> copy$default$3() {
/* 161 */     return args();
/*     */   }
/*     */   
/*     */   public String productPrefix() {
/* 161 */     return "Props";
/*     */   }
/*     */   
/*     */   public int productArity() {
/* 161 */     return 3;
/*     */   }
/*     */   
/*     */   public Object productElement(int x$1) {
/* 161 */     int i = x$1;
/* 161 */     switch (i) {
/*     */       default:
/* 161 */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */       case 2:
/*     */       
/*     */       case 1:
/*     */       
/*     */       case 0:
/*     */         break;
/*     */     } 
/* 161 */     return deploy();
/*     */   }
/*     */   
/*     */   public Iterator<Object> productIterator() {
/* 161 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */   }
/*     */   
/*     */   public boolean canEqual(Object x$1) {
/* 161 */     return x$1 instanceof Props;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 161 */     return ScalaRunTime$.MODULE$._hashCode(this);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 161 */     return ScalaRunTime$.MODULE$._toString(this);
/*     */   }
/*     */   
/*     */   public boolean equals(Object x$1) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: aload_1
/*     */     //   2: if_acmpeq -> 135
/*     */     //   5: aload_1
/*     */     //   6: astore_2
/*     */     //   7: aload_2
/*     */     //   8: instanceof akka/actor/Props
/*     */     //   11: ifeq -> 19
/*     */     //   14: iconst_1
/*     */     //   15: istore_3
/*     */     //   16: goto -> 21
/*     */     //   19: iconst_0
/*     */     //   20: istore_3
/*     */     //   21: iload_3
/*     */     //   22: ifeq -> 139
/*     */     //   25: aload_1
/*     */     //   26: checkcast akka/actor/Props
/*     */     //   29: astore #4
/*     */     //   31: aload_0
/*     */     //   32: invokevirtual deploy : ()Lakka/actor/Deploy;
/*     */     //   35: aload #4
/*     */     //   37: invokevirtual deploy : ()Lakka/actor/Deploy;
/*     */     //   40: astore #5
/*     */     //   42: dup
/*     */     //   43: ifnonnull -> 55
/*     */     //   46: pop
/*     */     //   47: aload #5
/*     */     //   49: ifnull -> 63
/*     */     //   52: goto -> 131
/*     */     //   55: aload #5
/*     */     //   57: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   60: ifeq -> 131
/*     */     //   63: aload_0
/*     */     //   64: invokevirtual clazz : ()Ljava/lang/Class;
/*     */     //   67: aload #4
/*     */     //   69: invokevirtual clazz : ()Ljava/lang/Class;
/*     */     //   72: astore #6
/*     */     //   74: dup
/*     */     //   75: ifnonnull -> 87
/*     */     //   78: pop
/*     */     //   79: aload #6
/*     */     //   81: ifnull -> 95
/*     */     //   84: goto -> 131
/*     */     //   87: aload #6
/*     */     //   89: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   92: ifeq -> 131
/*     */     //   95: aload_0
/*     */     //   96: invokevirtual args : ()Lscala/collection/immutable/Seq;
/*     */     //   99: aload #4
/*     */     //   101: invokevirtual args : ()Lscala/collection/immutable/Seq;
/*     */     //   104: astore #7
/*     */     //   106: dup
/*     */     //   107: ifnonnull -> 119
/*     */     //   110: pop
/*     */     //   111: aload #7
/*     */     //   113: ifnull -> 127
/*     */     //   116: goto -> 131
/*     */     //   119: aload #7
/*     */     //   121: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   124: ifeq -> 131
/*     */     //   127: iconst_1
/*     */     //   128: goto -> 132
/*     */     //   131: iconst_0
/*     */     //   132: ifeq -> 139
/*     */     //   135: iconst_1
/*     */     //   136: goto -> 140
/*     */     //   139: iconst_0
/*     */     //   140: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #161	-> 0
/*     */     //   #63	-> 14
/*     */     //   #161	-> 21
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	141	0	this	Lakka/actor/Props;
/*     */     //   0	141	1	x$1	Ljava/lang/Object;
/*     */   }
/*     */   
/*     */   public Props(Deploy deploy, Class<?> clazz, Seq<Object> args) {
/* 161 */     Product.class.$init$(this);
/* 186 */     producer();
/*     */   }
/*     */   
/*     */   private IndirectActorProducer producer() {
/*     */     if (this._producer == null)
/*     */       this._producer = IndirectActorProducer$.MODULE$.apply(clazz(), args()); 
/*     */     return this._producer;
/*     */   }
/*     */   
/*     */   private Class<? extends Actor> cachedActorClass() {
/*     */     if (this._cachedActorClass == null)
/*     */       this._cachedActorClass = producer().actorClass(); 
/*     */     return this._cachedActorClass;
/*     */   }
/*     */   
/*     */   public String dispatcher() {
/* 192 */     String str1 = deploy().dispatcher();
/* 193 */     String str2 = str1;
/* 193 */     if ("" == null) {
/* 193 */       "";
/* 193 */       if (str2 != null)
/* 194 */         String str = str1; 
/*     */     } else {
/*     */       if ("".equals(str2))
/*     */         return "akka.actor.default-dispatcher"; 
/* 194 */       String str = str1;
/*     */     } 
/*     */     return "akka.actor.default-dispatcher";
/*     */   }
/*     */   
/*     */   public String mailbox() {
/* 201 */     String str1 = deploy().mailbox();
/* 202 */     String str2 = str1;
/* 202 */     if ("" == null) {
/* 202 */       "";
/* 202 */       if (str2 != null)
/* 203 */         String str = str1; 
/*     */     } else {
/*     */       if ("".equals(str2))
/*     */         return "akka.actor.default-mailbox"; 
/* 203 */       String str = str1;
/*     */     } 
/*     */     return "akka.actor.default-mailbox";
/*     */   }
/*     */   
/*     */   public RouterConfig routerConfig() {
/* 210 */     return deploy().routerConfig();
/*     */   }
/*     */   
/*     */   public Props withDispatcher(String d) {
/* 215 */     String x$5 = d, x$6 = deploy().copy$default$1();
/* 215 */     Config x$7 = deploy().copy$default$2();
/* 215 */     RouterConfig x$8 = deploy().copy$default$3();
/* 215 */     Scope x$9 = deploy().copy$default$4();
/* 215 */     String x$10 = deploy().copy$default$6();
/* 215 */     return copy(deploy().copy(x$6, x$7, x$8, x$9, x$5, x$10), copy$default$2(), copy$default$3());
/*     */   }
/*     */   
/*     */   public Props withMailbox(String m) {
/* 220 */     String x$11 = m, x$12 = deploy().copy$default$1();
/* 220 */     Config x$13 = deploy().copy$default$2();
/* 220 */     RouterConfig x$14 = deploy().copy$default$3();
/* 220 */     Scope x$15 = deploy().copy$default$4();
/* 220 */     String x$16 = deploy().copy$default$5();
/* 220 */     return copy(deploy().copy(x$12, x$13, x$14, x$15, x$16, x$11), copy$default$2(), copy$default$3());
/*     */   }
/*     */   
/*     */   public Props withRouter(RouterConfig r) {
/* 225 */     RouterConfig x$17 = r;
/* 225 */     String x$18 = deploy().copy$default$1();
/* 225 */     Config x$19 = deploy().copy$default$2();
/* 225 */     Scope x$20 = deploy().copy$default$4();
/* 225 */     String x$21 = deploy().copy$default$5(), x$22 = deploy().copy$default$6();
/* 225 */     return copy(deploy().copy(x$18, x$19, x$17, x$20, x$21, x$22), copy$default$2(), copy$default$3());
/*     */   }
/*     */   
/*     */   public Props withDeploy(Deploy d) {
/* 230 */     return copy(d.withFallback(deploy()), copy$default$2(), copy$default$3());
/*     */   }
/*     */   
/*     */   public Class<? extends Actor> actorClass() {
/* 239 */     return cachedActorClass();
/*     */   }
/*     */   
/*     */   public Actor newActor() {
/* 249 */     return producer().produce();
/*     */   }
/*     */   
/*     */   public static Props create(Class<?> paramClass, Object... paramVarArgs) {
/*     */     return Props$.MODULE$.create(paramClass, paramVarArgs);
/*     */   }
/*     */   
/*     */   public static <T extends Actor> Props create(Class<T> paramClass, Creator<T> paramCreator) {
/*     */     return Props$.MODULE$.create(paramClass, paramCreator);
/*     */   }
/*     */   
/*     */   public static <T extends Actor> Props create(Creator<T> paramCreator) {
/*     */     return Props$.MODULE$.create(paramCreator);
/*     */   }
/*     */   
/*     */   public static Props create(Class<?> paramClass, Seq<Object> paramSeq) {
/*     */     return Props$.MODULE$.create(paramClass, paramSeq);
/*     */   }
/*     */   
/*     */   public static Props apply(Class<?> paramClass, Seq<Object> paramSeq) {
/*     */     return Props$.MODULE$.apply(paramClass, paramSeq);
/*     */   }
/*     */   
/*     */   public static <T extends Actor> Props apply(Function0<T> paramFunction0, ClassTag<T> paramClassTag) {
/*     */     return Props$.MODULE$.apply(paramFunction0, paramClassTag);
/*     */   }
/*     */   
/*     */   public static <T extends Actor> Props apply(ClassTag<T> paramClassTag) {
/*     */     return Props$.MODULE$.apply(paramClassTag);
/*     */   }
/*     */   
/*     */   public static Props default() {
/*     */     return Props$.MODULE$.default();
/*     */   }
/*     */   
/*     */   public static Props empty() {
/*     */     return Props$.MODULE$.empty();
/*     */   }
/*     */   
/*     */   public static Deploy defaultDeploy() {
/*     */     return Props$.MODULE$.defaultDeploy();
/*     */   }
/*     */   
/*     */   public static RouterConfig defaultRoutedProps() {
/*     */     return Props$.MODULE$.defaultRoutedProps();
/*     */   }
/*     */   
/*     */   public static Function0<Actor> defaultCreator() {
/*     */     return Props$.MODULE$.defaultCreator();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\Props.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */