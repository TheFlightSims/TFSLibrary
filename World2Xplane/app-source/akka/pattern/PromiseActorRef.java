/*     */ package akka.pattern;
/*     */ 
/*     */ import akka.actor.ActorKilledException;
/*     */ import akka.actor.ActorPath;
/*     */ import akka.actor.ActorRef;
/*     */ import akka.actor.ActorRefProvider;
/*     */ import akka.actor.Cancellable;
/*     */ import akka.actor.InternalActorRef;
/*     */ import akka.actor.LocalRef;
/*     */ import akka.actor.MinimalActorRef;
/*     */ import akka.dispatch.sysmsg.DeathWatchNotification;
/*     */ import akka.dispatch.sysmsg.SystemMessage;
/*     */ import akka.util.Timeout;
/*     */ import akka.util.Unsafe;
/*     */ import java.io.ObjectStreamException;
/*     */ import scala.Function1;
/*     */ import scala.Option;
/*     */ import scala.Product;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.StringContext;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.immutable.Set;
/*     */ import scala.concurrent.ExecutionContext;
/*     */ import scala.concurrent.Promise;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.util.Failure;
/*     */ import scala.util.Try;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\t5g!B\001\003\005\0211!a\004)s_6L7/Z!di>\024(+\0324\013\005\r!\021a\0029biR,'O\034\006\002\013\005!\021m[6b'\r\001q!\004\t\003\021-i\021!\003\006\003\025\021\tQ!Y2u_JL!\001D\005\003!%sG/\032:oC2\f5\r^8s%\0264\007C\001\005\017\023\ty\021BA\bNS:LW.\0317BGR|'OU3g\021!\t\002A!b\001\n\003\031\022\001\0039s_ZLG-\032:\004\001U\tA\003\005\002\t+%\021a#\003\002\021\003\016$xN\035*fMB\023xN^5eKJD\001\002\007\001\003\002\003\006I\001F\001\naJ|g/\0333fe\002B\001B\007\001\003\006\004%\taG\001\007e\026\034X\017\034;\026\003q\0012!\b\022%\033\005q\"BA\020!\003)\031wN\\2veJ,g\016\036\006\002C\005)1oY1mC&\0211E\b\002\b!J|W.[:f!\t)c%D\001!\023\t9\003EA\002B]fD\001\"\013\001\003\002\003\006I\001H\001\be\026\034X\017\034;!\021\025Y\003\001\"\003-\003\031a\024N\\5u}Q\031Qf\f\031\021\0059\002Q\"\001\002\t\013EQ\003\031\001\013\t\013iQ\003\031\001\017\t\023I\002\001\031!A!B\023\031\024!G0ti\006$X\rR8O_R\034\025\r\0347NK\022K'/Z2uYf\004\"!\n\033\n\005U\002#AB!osJ+g\r\013\0022oA\021Q\005O\005\003s\001\022\001B^8mCRLG.\032\005\007w\001\001\013\025\002\037\002;};\030\r^2iK\022\024\025\020R8O_R\034\025\r\0347NK\022K'/Z2uYf\0042!\020!D\035\t)c(\003\002@A\0051\001K]3eK\032L!!\021\"\003\007M+GO\003\002@AA\021\001\002R\005\003\013&\021\001\"Q2u_J\024VM\032\025\003u]Ba\001\023\001!\n\023I\025!C<bi\016DW\r\032\"z+\005a\004FA$L!\t)C*\003\002NA\t1\021N\0347j]\026Daa\024\001!\n\023\001\026aD;qI\006$XmV1uG\",GMQ=\025\007E#f\013\005\002&%&\0211\013\t\002\b\005>|G.Z1o\021\025)f\n1\001=\0031yG\016Z,bi\016DW\r\032\"z\021\0259f\n1\001=\0031qWm^,bi\016DW\r\032\"zQ\tq5\n\003\004[\001\001&iaW\001\013C\022$w+\031;dQ\026\024HCA)]\021\025i\026\f1\001D\003\0359\030\r^2iKJD#!W0\021\005\001\034W\"A1\013\005\t\004\023AC1o]>$\030\r^5p]&\021A-\031\002\bi\006LGN]3d\021\0311\007\001)C\007O\006Q!/Z7XCR\034\007.\032:\025\005!\\\007CA\023j\023\tQ\007E\001\003V]&$\b\"B/f\001\004\031\005FA3`\021\031q\007\001)C\007_\006i1\r\\3be^\013Go\0315feN$\022\001\020\025\003[~CaA\035\001!\n\023\031\030!B:uCR,W#A\032)\005E\\\005B\002<\001A\023%q/A\006va\022\fG/Z*uCR,GcA)yu\")\0210\036a\001g\005Aq\016\0343Ti\006$X\rC\003|k\002\0071'\001\005oK^\034F/\031;fQ\t)8\n\003\004\001\001&Ia`\001\tg\026$8\013^1uKR\031\001.!\001\t\013ml\b\031A\032)\005u\\\005bBA\004\001\021\005\023\021B\001\nO\026$\b+\031:f]R,\022a\002\005\b\003\033\001A\021AA\b\003\025Jg\016^3s]\006d7)\0317mS:<G\013\033:fC\022,\0050Z2vi&|gnQ8oi\026DH/\006\002\002\022A\031Q$a\005\n\007\005UaD\001\tFq\026\034W\017^5p]\016{g\016^3yi\"9\021\021\004\001\005\002\005m\021\001\0029bi\",\"!!\b\021\007!\ty\"C\002\002\"%\021\021\"Q2u_J\004\026\r\0365)\007\005]q\fC\004\002(\001!\t%!\013\002\013\021\022\027M\\4\025\t\005-\022\021\007\013\004Q\0065\002\"CA\030\003K\001\n\021q\001D\003\031\031XM\0343fe\"9\0211GA\023\001\004!\023aB7fgN\fw-\032\005\b\003o\001A\021IA\035\003E\031XM\0343TsN$X-\\'fgN\fw-\032\013\004Q\006m\002\002CA\032\003k\001\r!!\020\021\t\005}\022\021J\007\003\003\003RA!a\021\002F\00511/_:ng\036T1!a\022\005\003!!\027n\0359bi\016D\027\002BA&\003\003\022QbU=ti\026lW*Z:tC\036,\007bBA(\001\021\005\023\021K\001\rSN$VM]7j]\006$X\rZ\013\002#\"B\021QJA+\0037\ny\006E\002&\003/J1!!\027!\005)!W\r\035:fG\006$X\rZ\021\003\003;\na'V:fA\r|g\016^3yi::\030\r^2iQ\005\034Go\034:*A\005tG\r\t:fG\026Lg/\032\021UKJl\027N\\1uK\022D\023m\031;pe&\n#!!\031\002\007Ir#\007C\004\002f\001!\t%a\032\002\tM$x\016\035\013\002Q\"\032\0211M0\t\023\0055\004!%A\005B\005=\024a\004\023cC:<G\005Z3gCVdG\017\n\032\025\t\005E\024\021\021\026\004\007\006M4FAA;!\021\t9(! \016\005\005e$bAA>C\006IQO\\2iK\016\\W\rZ\005\005\003\nIHA\tv]\016DWmY6fIZ\013'/[1oG\026Dq!a\r\002l\001\007Ae\002\005\002\006\nA\t\001BAD\003=\001&o\\7jg\026\f5\r^8s%\0264\007c\001\030\002\n\0329\021A\001E\001\t\005-5#BAEg\0055\005cA\023\002\020&\031\021\021\023\021\003\031M+'/[1mSj\f'\r\\3\t\017-\nI\t\"\001\002\026R\021\021qQ\004\t\0033\013I\t##\002\034\006Y!+Z4jgR,'/\0338h!\021\ti*a(\016\005\005%e\001CAQ\003\023CI)a)\003\027I+w-[:uKJLgnZ\n\b\003?\033\024QUAG!\r)\023qU\005\004\003S\003#a\002)s_\022,8\r\036\005\bW\005}E\021AAW)\t\tY\n\003\006\0022\006}\025\021!C!\003g\013Q\002\035:pIV\034G\017\025:fM&DXCAA[!\021\t9,!1\016\005\005e&\002BA^\003{\013A\001\\1oO*\021\021qX\001\005U\0064\030-\003\003\002D\006e&AB*ue&tw\r\003\006\002H\006}\025\021!C\001\003\023\fA\002\035:pIV\034G/\021:jif,\"!a3\021\007\025\ni-C\002\002P\002\0221!\0238u\021)\t\031.a(\002\002\023\005\021Q[\001\017aJ|G-^2u\0132,W.\0328u)\r!\023q\033\005\013\0033\f\t.!AA\002\005-\027a\001=%c!Q\021Q\\AP\003\003%\t%a8\002\037A\024x\016Z;di&#XM]1u_J,\"!!9\021\013\005\r\030\021\036\023\016\005\005\025(bAAtA\005Q1m\0347mK\016$\030n\0348\n\t\005-\030Q\035\002\t\023R,'/\031;pe\"Q\021q^AP\003\003%\t!!=\002\021\r\fg.R9vC2$2!UAz\021%\tI.!<\002\002\003\007A\005\003\006\002x\006}\025\021!C!\003s\f\001\002[1tQ\016{G-\032\013\003\003\027D!\"!@\002 \006\005I\021IA\000\003!!xn\025;sS:<GCAA[\021)\021\031!a(\002\002\023%!QA\001\fe\026\fGMU3t_24X\r\006\002\003\bA!\021q\027B\005\023\021\021Y!!/\003\r=\023'.Z2u\017!\021y!!#\t\n\nE\021aB*u_B\004X\r\032\t\005\003;\023\031B\002\005\003\026\005%\005\022\022B\f\005\035\031Fo\0349qK\022\034rAa\0054\003K\013i\tC\004,\005'!\tAa\007\025\005\tE\001BCAY\005'\t\t\021\"\021\0024\"Q\021q\031B\n\003\003%\t!!3\t\025\005M'1CA\001\n\003\021\031\003F\002%\005KA!\"!7\003\"\005\005\t\031AAf\021)\tiNa\005\002\002\023\005\023q\034\005\013\003_\024\031\"!A\005\002\t-BcA)\003.!I\021\021\034B\025\003\003\005\r\001\n\005\013\003o\024\031\"!A\005B\005e\bBCA\005'\t\t\021\"\021\002\000\"Q!1\001B\n\003\003%IA!\002\007\017\t]\022\021\022#\003:\ty1\013^8qa\026$w+\033;i!\006$\bnE\004\0036M\n)+!$\t\027\005e!Q\007BK\002\023\005\0211\004\005\f\005\021)D!E!\002\023\ti\"A\003qCRD\007\005C\004,\005k!\tAa\021\025\t\t\025#q\t\t\005\003;\023)\004\003\005\002\032\t\005\003\031AA\017\021)\021YE!\016\002\002\023\005!QJ\001\005G>\004\030\020\006\003\003F\t=\003BCA\r\005\023\002\n\0211\001\002\036!Q!1\013B\033#\003%\tA!\026\002\035\r|\007/\037\023eK\032\fW\017\034;%cU\021!q\013\026\005\003;\t\031\b\003\006\0022\nU\022\021!C!\003gC!\"a2\0036\005\005I\021AAe\021)\t\031N!\016\002\002\023\005!q\f\013\004I\t\005\004BCAm\005;\n\t\0211\001\002L\"Q\021Q\034B\033\003\003%\t%a8\t\025\005=(QGA\001\n\003\0219\007F\002R\005SB\021\"!7\003f\005\005\t\031\001\023\t\025\005](QGA\001\n\003\nI\020\003\006\002~\nU\022\021!C!\003D!B!\035\0036\005\005I\021\tB:\003\031)\027/^1mgR\031\021K!\036\t\023\005e'qNA\001\002\004!sA\003B=\003\023\013\t\021#\003\003|\005y1\013^8qa\026$w+\033;i!\006$\b\016\005\003\002\036\nudA\003B\034\003\023\013\t\021#\003\003\000M1!Q\020BA\003\033\003\002Ba!\003\n\006u!QI\007\003\005\013S1Aa\"!\003\035\021XO\034;j[\026LAAa#\003\006\n\t\022IY:ue\006\034GOR;oGRLwN\\\031\t\017-\022i\b\"\001\003\020R\021!1\020\005\013\003{\024i(!A\005F\005}\bB\003BK\005{\n\t\021\"!\003\030\006)\021\r\0359msR!!Q\tBM\021!\tIBa%A\002\005u\001B\003BO\005{\n\t\021\"!\003 \0069QO\\1qa2LH\003\002BQ\005O\003R!\nBR\003;I1A!*!\005\031y\005\017^5p]\"Q!\021\026BN\003\003\005\rA!\022\002\007a$\003\007\003\006\003\004\tu\024\021!C\005\005\013A\001B!&\002\n\022\005!q\026\013\b[\tE&1\027Bb\021\031\t\"Q\026a\001)!A!Q\027BW\001\004\0219,A\004uS6,w.\036;\021\t\te&qX\007\003\005wS1A!0\005\003\021)H/\0337\n\t\t\005'1\030\002\b)&lWm\\;u\021!\021)M!,A\002\t\035\027A\003;be\036,GOT1nKB\031QH!3\n\007\005\r'\t\003\006\003\004\005%\025\021!C\005\005\013\001")
/*     */ public final class PromiseActorRef extends InternalActorRef implements MinimalActorRef {
/*     */   private final ActorRefProvider provider;
/*     */   
/*     */   private final Promise<Object> result;
/*     */   
/*     */   private volatile Object _stateDoNotCallMeDirectly;
/*     */   
/*     */   private volatile Set<ActorRef> _watchedByDoNotCallMeDirectly;
/*     */   
/*     */   public InternalActorRef getChild(Iterator names) {
/* 174 */     return MinimalActorRef.class.getChild(this, names);
/*     */   }
/*     */   
/*     */   public void start() {
/* 174 */     MinimalActorRef.class.start(this);
/*     */   }
/*     */   
/*     */   public void suspend() {
/* 174 */     MinimalActorRef.class.suspend(this);
/*     */   }
/*     */   
/*     */   public void resume(Throwable causedByFailure) {
/* 174 */     MinimalActorRef.class.resume(this, causedByFailure);
/*     */   }
/*     */   
/*     */   public void restart(Throwable cause) {
/* 174 */     MinimalActorRef.class.restart(this, cause);
/*     */   }
/*     */   
/*     */   public Object writeReplace() throws ObjectStreamException {
/* 174 */     return MinimalActorRef.class.writeReplace(this);
/*     */   }
/*     */   
/*     */   public final boolean isLocal() {
/* 174 */     return LocalRef.class.isLocal((LocalRef)this);
/*     */   }
/*     */   
/*     */   public ActorRefProvider provider() {
/* 174 */     return this.provider;
/*     */   }
/*     */   
/*     */   public Promise<Object> result() {
/* 174 */     return this.result;
/*     */   }
/*     */   
/*     */   public PromiseActorRef(ActorRefProvider provider, Promise<Object> result) {
/* 174 */     LocalRef.class.$init$((LocalRef)this);
/* 174 */     MinimalActorRef.class.$init$(this);
/* 196 */     this._watchedByDoNotCallMeDirectly = akka.actor.ActorCell$.MODULE$.emptyActorRefSet();
/*     */   }
/*     */   
/*     */   private Set<ActorRef> watchedBy() {
/* 199 */     return (Set<ActorRef>)Unsafe.instance.getObjectVolatile(this, AbstractPromiseActorRef.watchedByOffset);
/*     */   }
/*     */   
/*     */   private boolean updateWatchedBy(Set oldWatchedBy, Set newWatchedBy) {
/* 203 */     return Unsafe.instance.compareAndSwapObject(this, AbstractPromiseActorRef.watchedByOffset, oldWatchedBy, newWatchedBy);
/*     */   }
/*     */   
/*     */   private final boolean addWatcher(ActorRef watcher) {
/*     */     while (true) {
/* 206 */       Set<ActorRef> set = watchedBy();
/* 207 */       if (set == null) {
/* 207 */         boolean bool = false;
/*     */       } else {
/* 208 */         if (updateWatchedBy(set, (Set<ActorRef>)set.$plus(watcher)))
/*     */           return true; 
/* 208 */         watcher = watcher;
/*     */         continue;
/*     */       } 
/*     */       return SYNTHETIC_LOCAL_VARIABLE_4;
/*     */     } 
/*     */   }
/*     */   
/*     */   private final void remWatcher(ActorRef watcher) {
/*     */     while (true) {
/* 212 */       Set<ActorRef> set = watchedBy();
/* 213 */       if (set == null) {
/* 213 */         BoxedUnit boxedUnit = BoxedUnit.UNIT;
/* 214 */       } else if (updateWatchedBy(set, (Set<ActorRef>)set.$minus(watcher))) {
/* 214 */         BoxedUnit boxedUnit = BoxedUnit.UNIT;
/*     */       } else {
/* 214 */         watcher = watcher;
/*     */         continue;
/*     */       } 
/*     */       return;
/*     */     } 
/*     */   }
/*     */   
/*     */   private final Set<ActorRef> clearWatchers() {
/*     */     Set<ActorRef> set;
/*     */     while (true) {
/* 218 */       Set<ActorRef> set1 = watchedBy();
/* 219 */       if (set1 == null) {
/* 219 */         set = akka.actor.ActorCell$.MODULE$.emptyActorRefSet();
/*     */         break;
/*     */       } 
/* 220 */       null;
/* 220 */       if (updateWatchedBy(set1, null)) {
/* 220 */         set = set1;
/*     */         break;
/*     */       } 
/*     */     } 
/*     */     return set;
/*     */   }
/*     */   
/*     */   private Object state() {
/* 224 */     return Unsafe.instance.getObjectVolatile(this, AbstractPromiseActorRef.stateOffset);
/*     */   }
/*     */   
/*     */   private boolean updateState(Object oldState, Object newState) {
/* 228 */     return Unsafe.instance.compareAndSwapObject(this, AbstractPromiseActorRef.stateOffset, oldState, newState);
/*     */   }
/*     */   
/*     */   private void setState(Object newState) {
/* 231 */     Unsafe.instance.putObjectVolatile(this, AbstractPromiseActorRef.stateOffset, newState);
/*     */   }
/*     */   
/*     */   public InternalActorRef getParent() {
/* 233 */     return provider().tempContainer();
/*     */   }
/*     */   
/*     */   public ExecutionContext internalCallingThreadExecutionContext() {
/* 236 */     return provider().guardian().underlying().systemImpl().internalCallingThreadExecutionContext();
/*     */   }
/*     */   
/*     */   public ActorPath path() {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: invokespecial state : ()Ljava/lang/Object;
/*     */     //   4: astore_2
/*     */     //   5: aload_2
/*     */     //   6: ifnonnull -> 62
/*     */     //   9: aload_0
/*     */     //   10: aconst_null
/*     */     //   11: pop
/*     */     //   12: aconst_null
/*     */     //   13: getstatic akka/pattern/PromiseActorRef$Registering$.MODULE$ : Lakka/pattern/PromiseActorRef$Registering$;
/*     */     //   16: invokespecial updateState : (Ljava/lang/Object;Ljava/lang/Object;)Z
/*     */     //   19: ifeq -> 0
/*     */     //   22: aconst_null
/*     */     //   23: pop
/*     */     //   24: aconst_null
/*     */     //   25: astore #4
/*     */     //   27: aload_0
/*     */     //   28: invokevirtual provider : ()Lakka/actor/ActorRefProvider;
/*     */     //   31: invokeinterface tempPath : ()Lakka/actor/ActorPath;
/*     */     //   36: astore #4
/*     */     //   38: aload_0
/*     */     //   39: invokevirtual provider : ()Lakka/actor/ActorRefProvider;
/*     */     //   42: aload_0
/*     */     //   43: aload #4
/*     */     //   45: invokeinterface registerTempActor : (Lakka/actor/InternalActorRef;Lakka/actor/ActorPath;)V
/*     */     //   50: aload #4
/*     */     //   52: aload_0
/*     */     //   53: aload #4
/*     */     //   55: invokespecial setState : (Ljava/lang/Object;)V
/*     */     //   58: astore_3
/*     */     //   59: goto -> 104
/*     */     //   62: aload_2
/*     */     //   63: instanceof akka/actor/ActorPath
/*     */     //   66: ifeq -> 81
/*     */     //   69: aload_2
/*     */     //   70: checkcast akka/actor/ActorPath
/*     */     //   73: astore #6
/*     */     //   75: aload #6
/*     */     //   77: astore_3
/*     */     //   78: goto -> 104
/*     */     //   81: aload_2
/*     */     //   82: instanceof akka/pattern/PromiseActorRef$StoppedWithPath
/*     */     //   85: ifeq -> 106
/*     */     //   88: aload_2
/*     */     //   89: checkcast akka/pattern/PromiseActorRef$StoppedWithPath
/*     */     //   92: astore #7
/*     */     //   94: aload #7
/*     */     //   96: invokevirtual path : ()Lakka/actor/ActorPath;
/*     */     //   99: astore #8
/*     */     //   101: aload #8
/*     */     //   103: astore_3
/*     */     //   104: aload_3
/*     */     //   105: areturn
/*     */     //   106: getstatic akka/pattern/PromiseActorRef$Stopped$.MODULE$ : Lakka/pattern/PromiseActorRef$Stopped$;
/*     */     //   109: aload_2
/*     */     //   110: astore #9
/*     */     //   112: dup
/*     */     //   113: ifnonnull -> 125
/*     */     //   116: pop
/*     */     //   117: aload #9
/*     */     //   119: ifnull -> 133
/*     */     //   122: goto -> 160
/*     */     //   125: aload #9
/*     */     //   127: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   130: ifeq -> 160
/*     */     //   133: aload_0
/*     */     //   134: getstatic akka/pattern/PromiseActorRef$Stopped$.MODULE$ : Lakka/pattern/PromiseActorRef$Stopped$;
/*     */     //   137: new akka/pattern/PromiseActorRef$StoppedWithPath
/*     */     //   140: dup
/*     */     //   141: aload_0
/*     */     //   142: invokevirtual provider : ()Lakka/actor/ActorRefProvider;
/*     */     //   145: invokeinterface tempPath : ()Lakka/actor/ActorPath;
/*     */     //   150: invokespecial <init> : (Lakka/actor/ActorPath;)V
/*     */     //   153: invokespecial updateState : (Ljava/lang/Object;Ljava/lang/Object;)Z
/*     */     //   156: pop
/*     */     //   157: goto -> 0
/*     */     //   160: getstatic akka/pattern/PromiseActorRef$Registering$.MODULE$ : Lakka/pattern/PromiseActorRef$Registering$;
/*     */     //   163: aload_2
/*     */     //   164: astore #10
/*     */     //   166: dup
/*     */     //   167: ifnonnull -> 179
/*     */     //   170: pop
/*     */     //   171: aload #10
/*     */     //   173: ifnull -> 0
/*     */     //   176: goto -> 187
/*     */     //   179: aload #10
/*     */     //   181: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   184: ifne -> 0
/*     */     //   187: new scala/MatchError
/*     */     //   190: dup
/*     */     //   191: aload_2
/*     */     //   192: invokespecial <init> : (Ljava/lang/Object;)V
/*     */     //   195: athrow
/*     */     //   196: astore #5
/*     */     //   198: aload_0
/*     */     //   199: aload #4
/*     */     //   201: invokespecial setState : (Ljava/lang/Object;)V
/*     */     //   204: aload #5
/*     */     //   206: athrow
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #244	-> 0
/*     */     //   #245	-> 5
/*     */     //   #246	-> 9
/*     */     //   #247	-> 22
/*     */     //   #249	-> 27
/*     */     //   #250	-> 38
/*     */     //   #251	-> 50
/*     */     //   #252	-> 52
/*     */     //   #246	-> 58
/*     */     //   #254	-> 62
/*     */     //   #255	-> 81
/*     */     //   #244	-> 104
/*     */     //   #256	-> 106
/*     */     //   #258	-> 133
/*     */     //   #260	-> 160
/*     */     //   #244	-> 187
/*     */     //   #252	-> 196
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	207	0	this	Lakka/pattern/PromiseActorRef;
/*     */     //   27	180	4	p	Lakka/actor/ActorPath;
/*     */     //   101	106	8	p	Lakka/actor/ActorPath;
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   27	52	196	finally
/*     */   }
/*     */   
/*     */   public void $bang(Object message, ActorRef sender) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: invokespecial state : ()Ljava/lang/Object;
/*     */     //   4: astore_3
/*     */     //   5: getstatic akka/pattern/PromiseActorRef$Stopped$.MODULE$ : Lakka/pattern/PromiseActorRef$Stopped$;
/*     */     //   8: aload_3
/*     */     //   9: astore #4
/*     */     //   11: dup
/*     */     //   12: ifnonnull -> 24
/*     */     //   15: pop
/*     */     //   16: aload #4
/*     */     //   18: ifnull -> 32
/*     */     //   21: goto -> 38
/*     */     //   24: aload #4
/*     */     //   26: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   29: ifeq -> 38
/*     */     //   32: iconst_1
/*     */     //   33: istore #5
/*     */     //   35: goto -> 54
/*     */     //   38: aload_3
/*     */     //   39: instanceof akka/pattern/PromiseActorRef$StoppedWithPath
/*     */     //   42: ifeq -> 51
/*     */     //   45: iconst_1
/*     */     //   46: istore #5
/*     */     //   48: goto -> 54
/*     */     //   51: iconst_0
/*     */     //   52: istore #5
/*     */     //   54: iload #5
/*     */     //   56: ifeq -> 89
/*     */     //   59: getstatic akka/actor/package$.MODULE$ : Lakka/actor/package$;
/*     */     //   62: aload_0
/*     */     //   63: invokevirtual provider : ()Lakka/actor/ActorRefProvider;
/*     */     //   66: invokeinterface deadLetters : ()Lakka/actor/ActorRef;
/*     */     //   71: invokevirtual actorRef2Scala : (Lakka/actor/ActorRef;)Lakka/actor/ScalaActorRef;
/*     */     //   74: aload_1
/*     */     //   75: aload_2
/*     */     //   76: invokeinterface $bang : (Ljava/lang/Object;Lakka/actor/ActorRef;)V
/*     */     //   81: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */     //   84: astore #6
/*     */     //   86: goto -> 237
/*     */     //   89: aload_1
/*     */     //   90: ifnonnull -> 104
/*     */     //   93: new akka/actor/InvalidMessageException
/*     */     //   96: dup
/*     */     //   97: ldc_w 'Message is null'
/*     */     //   100: invokespecial <init> : (Ljava/lang/String;)V
/*     */     //   103: athrow
/*     */     //   104: aload_0
/*     */     //   105: invokevirtual result : ()Lscala/concurrent/Promise;
/*     */     //   108: aload_1
/*     */     //   109: astore #7
/*     */     //   111: aload #7
/*     */     //   113: instanceof akka/actor/Status$Success
/*     */     //   116: ifeq -> 147
/*     */     //   119: aload #7
/*     */     //   121: checkcast akka/actor/Status$Success
/*     */     //   124: astore #8
/*     */     //   126: aload #8
/*     */     //   128: invokevirtual status : ()Ljava/lang/Object;
/*     */     //   131: astore #9
/*     */     //   133: new scala/util/Success
/*     */     //   136: dup
/*     */     //   137: aload #9
/*     */     //   139: invokespecial <init> : (Ljava/lang/Object;)V
/*     */     //   142: astore #10
/*     */     //   144: goto -> 194
/*     */     //   147: aload #7
/*     */     //   149: instanceof akka/actor/Status$Failure
/*     */     //   152: ifeq -> 183
/*     */     //   155: aload #7
/*     */     //   157: checkcast akka/actor/Status$Failure
/*     */     //   160: astore #11
/*     */     //   162: aload #11
/*     */     //   164: invokevirtual cause : ()Ljava/lang/Throwable;
/*     */     //   167: astore #12
/*     */     //   169: new scala/util/Failure
/*     */     //   172: dup
/*     */     //   173: aload #12
/*     */     //   175: invokespecial <init> : (Ljava/lang/Throwable;)V
/*     */     //   178: astore #10
/*     */     //   180: goto -> 194
/*     */     //   183: new scala/util/Success
/*     */     //   186: dup
/*     */     //   187: aload #7
/*     */     //   189: invokespecial <init> : (Ljava/lang/Object;)V
/*     */     //   192: astore #10
/*     */     //   194: aload #10
/*     */     //   196: invokeinterface tryComplete : (Lscala/util/Try;)Z
/*     */     //   201: ifeq -> 210
/*     */     //   204: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */     //   207: goto -> 235
/*     */     //   210: getstatic akka/actor/package$.MODULE$ : Lakka/actor/package$;
/*     */     //   213: aload_0
/*     */     //   214: invokevirtual provider : ()Lakka/actor/ActorRefProvider;
/*     */     //   217: invokeinterface deadLetters : ()Lakka/actor/ActorRef;
/*     */     //   222: invokevirtual actorRef2Scala : (Lakka/actor/ActorRef;)Lakka/actor/ScalaActorRef;
/*     */     //   225: aload_1
/*     */     //   226: aload_2
/*     */     //   227: invokeinterface $bang : (Ljava/lang/Object;Lakka/actor/ActorRef;)V
/*     */     //   232: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */     //   235: astore #6
/*     */     //   237: return
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #263	-> 0
/*     */     //   #264	-> 5
/*     */     //   #266	-> 89
/*     */     //   #267	-> 104
/*     */     //   #268	-> 108
/*     */     //   #269	-> 111
/*     */     //   #270	-> 147
/*     */     //   #271	-> 183
/*     */     //   #268	-> 194
/*     */     //   #267	-> 196
/*     */     //   #272	-> 210
/*     */     //   #265	-> 235
/*     */     //   #263	-> 237
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	238	0	this	Lakka/pattern/PromiseActorRef;
/*     */     //   0	238	1	message	Ljava/lang/Object;
/*     */     //   0	238	2	sender	Lakka/actor/ActorRef;
/*     */     //   133	105	9	r	Ljava/lang/Object;
/*     */     //   169	69	12	f	Ljava/lang/Throwable;
/*     */   }
/*     */   
/*     */   public ActorRef $bang$default$2(Object message) {
/* 263 */     return akka.actor.Actor$.MODULE$.noSender();
/*     */   }
/*     */   
/*     */   public void sendSystemMessage(SystemMessage message) {
/*     */     // Byte code:
/*     */     //   0: aload_1
/*     */     //   1: astore_2
/*     */     //   2: aload_2
/*     */     //   3: instanceof akka/dispatch/sysmsg/Terminate
/*     */     //   6: ifeq -> 20
/*     */     //   9: aload_0
/*     */     //   10: invokevirtual stop : ()V
/*     */     //   13: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */     //   16: astore_3
/*     */     //   17: goto -> 415
/*     */     //   20: aload_2
/*     */     //   21: instanceof akka/dispatch/sysmsg/DeathWatchNotification
/*     */     //   24: ifeq -> 92
/*     */     //   27: aload_2
/*     */     //   28: checkcast akka/dispatch/sysmsg/DeathWatchNotification
/*     */     //   31: astore #4
/*     */     //   33: aload #4
/*     */     //   35: invokevirtual actor : ()Lakka/actor/ActorRef;
/*     */     //   38: astore #5
/*     */     //   40: aload #4
/*     */     //   42: invokevirtual existenceConfirmed : ()Z
/*     */     //   45: istore #6
/*     */     //   47: aload #4
/*     */     //   49: invokevirtual addressTerminated : ()Z
/*     */     //   52: istore #7
/*     */     //   54: new akka/actor/Terminated
/*     */     //   57: dup
/*     */     //   58: aload #5
/*     */     //   60: iload #6
/*     */     //   62: iload #7
/*     */     //   64: invokespecial <init> : (Lakka/actor/ActorRef;ZZ)V
/*     */     //   67: astore #8
/*     */     //   69: aload_0
/*     */     //   70: aload #8
/*     */     //   72: invokevirtual $bang$default$2 : (Ljava/lang/Object;)Lakka/actor/ActorRef;
/*     */     //   75: astore #9
/*     */     //   77: aload_0
/*     */     //   78: aload #8
/*     */     //   80: aload #9
/*     */     //   82: invokevirtual $bang : (Ljava/lang/Object;Lakka/actor/ActorRef;)V
/*     */     //   85: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */     //   88: astore_3
/*     */     //   89: goto -> 415
/*     */     //   92: aload_2
/*     */     //   93: instanceof akka/dispatch/sysmsg/Watch
/*     */     //   96: ifeq -> 264
/*     */     //   99: aload_2
/*     */     //   100: checkcast akka/dispatch/sysmsg/Watch
/*     */     //   103: astore #10
/*     */     //   105: aload #10
/*     */     //   107: invokevirtual watchee : ()Lakka/actor/InternalActorRef;
/*     */     //   110: astore #11
/*     */     //   112: aload #10
/*     */     //   114: invokevirtual watcher : ()Lakka/actor/InternalActorRef;
/*     */     //   117: astore #12
/*     */     //   119: aload #11
/*     */     //   121: aload_0
/*     */     //   122: astore #13
/*     */     //   124: dup
/*     */     //   125: ifnonnull -> 137
/*     */     //   128: pop
/*     */     //   129: aload #13
/*     */     //   131: ifnull -> 145
/*     */     //   134: goto -> 208
/*     */     //   137: aload #13
/*     */     //   139: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   142: ifeq -> 208
/*     */     //   145: aload #12
/*     */     //   147: aload_0
/*     */     //   148: astore #14
/*     */     //   150: dup
/*     */     //   151: ifnonnull -> 163
/*     */     //   154: pop
/*     */     //   155: aload #14
/*     */     //   157: ifnull -> 208
/*     */     //   160: goto -> 171
/*     */     //   163: aload #14
/*     */     //   165: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   168: ifne -> 208
/*     */     //   171: aload_0
/*     */     //   172: aload #12
/*     */     //   174: invokespecial addWatcher : (Lakka/actor/ActorRef;)Z
/*     */     //   177: ifeq -> 186
/*     */     //   180: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */     //   183: goto -> 260
/*     */     //   186: aload #12
/*     */     //   188: new akka/dispatch/sysmsg/DeathWatchNotification
/*     */     //   191: dup
/*     */     //   192: aload #11
/*     */     //   194: iconst_1
/*     */     //   195: iconst_0
/*     */     //   196: invokespecial <init> : (Lakka/actor/ActorRef;ZZ)V
/*     */     //   199: invokevirtual sendSystemMessage : (Lakka/dispatch/sysmsg/SystemMessage;)V
/*     */     //   202: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */     //   205: goto -> 260
/*     */     //   208: getstatic java/lang/System.err : Ljava/io/PrintStream;
/*     */     //   211: new scala/collection/immutable/StringOps
/*     */     //   214: dup
/*     */     //   215: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */     //   218: ldc_w 'BUG: illegal Watch(%s,%s) for %s'
/*     */     //   221: invokevirtual augmentString : (Ljava/lang/String;)Ljava/lang/String;
/*     */     //   224: invokespecial <init> : (Ljava/lang/String;)V
/*     */     //   227: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */     //   230: iconst_3
/*     */     //   231: anewarray java/lang/Object
/*     */     //   234: dup
/*     */     //   235: iconst_0
/*     */     //   236: aload #11
/*     */     //   238: aastore
/*     */     //   239: dup
/*     */     //   240: iconst_1
/*     */     //   241: aload #12
/*     */     //   243: aastore
/*     */     //   244: dup
/*     */     //   245: iconst_2
/*     */     //   246: aload_0
/*     */     //   247: aastore
/*     */     //   248: invokevirtual genericWrapArray : (Ljava/lang/Object;)Lscala/collection/mutable/WrappedArray;
/*     */     //   251: invokevirtual format : (Lscala/collection/Seq;)Ljava/lang/String;
/*     */     //   254: invokevirtual println : (Ljava/lang/String;)V
/*     */     //   257: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */     //   260: astore_3
/*     */     //   261: goto -> 415
/*     */     //   264: aload_2
/*     */     //   265: instanceof akka/dispatch/sysmsg/Unwatch
/*     */     //   268: ifeq -> 411
/*     */     //   271: aload_2
/*     */     //   272: checkcast akka/dispatch/sysmsg/Unwatch
/*     */     //   275: astore #15
/*     */     //   277: aload #15
/*     */     //   279: invokevirtual watchee : ()Lakka/actor/ActorRef;
/*     */     //   282: astore #16
/*     */     //   284: aload #15
/*     */     //   286: invokevirtual watcher : ()Lakka/actor/ActorRef;
/*     */     //   289: astore #17
/*     */     //   291: aload #16
/*     */     //   293: aload_0
/*     */     //   294: astore #18
/*     */     //   296: dup
/*     */     //   297: ifnonnull -> 309
/*     */     //   300: pop
/*     */     //   301: aload #18
/*     */     //   303: ifnull -> 317
/*     */     //   306: goto -> 355
/*     */     //   309: aload #18
/*     */     //   311: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   314: ifeq -> 355
/*     */     //   317: aload #17
/*     */     //   319: aload_0
/*     */     //   320: astore #19
/*     */     //   322: dup
/*     */     //   323: ifnonnull -> 335
/*     */     //   326: pop
/*     */     //   327: aload #19
/*     */     //   329: ifnull -> 355
/*     */     //   332: goto -> 343
/*     */     //   335: aload #19
/*     */     //   337: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   340: ifne -> 355
/*     */     //   343: aload_0
/*     */     //   344: aload #17
/*     */     //   346: invokespecial remWatcher : (Lakka/actor/ActorRef;)V
/*     */     //   349: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */     //   352: goto -> 407
/*     */     //   355: getstatic java/lang/System.err : Ljava/io/PrintStream;
/*     */     //   358: new scala/collection/immutable/StringOps
/*     */     //   361: dup
/*     */     //   362: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */     //   365: ldc_w 'BUG: illegal Unwatch(%s,%s) for %s'
/*     */     //   368: invokevirtual augmentString : (Ljava/lang/String;)Ljava/lang/String;
/*     */     //   371: invokespecial <init> : (Ljava/lang/String;)V
/*     */     //   374: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */     //   377: iconst_3
/*     */     //   378: anewarray java/lang/Object
/*     */     //   381: dup
/*     */     //   382: iconst_0
/*     */     //   383: aload #16
/*     */     //   385: aastore
/*     */     //   386: dup
/*     */     //   387: iconst_1
/*     */     //   388: aload #17
/*     */     //   390: aastore
/*     */     //   391: dup
/*     */     //   392: iconst_2
/*     */     //   393: aload_0
/*     */     //   394: aastore
/*     */     //   395: invokevirtual genericWrapArray : (Ljava/lang/Object;)Lscala/collection/mutable/WrappedArray;
/*     */     //   398: invokevirtual format : (Lscala/collection/Seq;)Ljava/lang/String;
/*     */     //   401: invokevirtual println : (Ljava/lang/String;)V
/*     */     //   404: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */     //   407: astore_3
/*     */     //   408: goto -> 415
/*     */     //   411: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */     //   414: astore_3
/*     */     //   415: return
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #275	-> 0
/*     */     //   #276	-> 2
/*     */     //   #277	-> 20
/*     */     //   #278	-> 92
/*     */     //   #279	-> 119
/*     */     //   #280	-> 171
/*     */     //   #282	-> 186
/*     */     //   #283	-> 208
/*     */     //   #279	-> 260
/*     */     //   #284	-> 264
/*     */     //   #285	-> 291
/*     */     //   #286	-> 355
/*     */     //   #285	-> 407
/*     */     //   #287	-> 411
/*     */     //   #275	-> 415
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	416	0	this	Lakka/pattern/PromiseActorRef;
/*     */     //   0	416	1	message	Lakka/dispatch/sysmsg/SystemMessage;
/*     */     //   40	376	5	a	Lakka/actor/ActorRef;
/*     */     //   47	369	6	ec	Z
/*     */     //   54	362	7	at	Z
/*     */     //   69	19	8	x$4	Lakka/actor/Terminated;
/*     */     //   77	11	9	x$5	Lakka/actor/ActorRef;
/*     */     //   112	304	11	watchee	Lakka/actor/InternalActorRef;
/*     */     //   119	297	12	watcher	Lakka/actor/InternalActorRef;
/*     */     //   284	132	16	watchee	Lakka/actor/ActorRef;
/*     */     //   291	125	17	watcher	Lakka/actor/ActorRef;
/*     */   }
/*     */   
/*     */   public boolean isTerminated() {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: invokespecial state : ()Ljava/lang/Object;
/*     */     //   4: astore_1
/*     */     //   5: getstatic akka/pattern/PromiseActorRef$Stopped$.MODULE$ : Lakka/pattern/PromiseActorRef$Stopped$;
/*     */     //   8: aload_1
/*     */     //   9: astore_2
/*     */     //   10: dup
/*     */     //   11: ifnonnull -> 22
/*     */     //   14: pop
/*     */     //   15: aload_2
/*     */     //   16: ifnull -> 29
/*     */     //   19: goto -> 34
/*     */     //   22: aload_2
/*     */     //   23: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   26: ifeq -> 34
/*     */     //   29: iconst_1
/*     */     //   30: istore_3
/*     */     //   31: goto -> 48
/*     */     //   34: aload_1
/*     */     //   35: instanceof akka/pattern/PromiseActorRef$StoppedWithPath
/*     */     //   38: ifeq -> 46
/*     */     //   41: iconst_1
/*     */     //   42: istore_3
/*     */     //   43: goto -> 48
/*     */     //   46: iconst_0
/*     */     //   47: istore_3
/*     */     //   48: iload_3
/*     */     //   49: ifeq -> 58
/*     */     //   52: iconst_1
/*     */     //   53: istore #4
/*     */     //   55: goto -> 61
/*     */     //   58: iconst_0
/*     */     //   59: istore #4
/*     */     //   61: iload #4
/*     */     //   63: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #290	-> 0
/*     */     //   #291	-> 5
/*     */     //   #292	-> 58
/*     */     //   #290	-> 61
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	64	0	this	Lakka/pattern/PromiseActorRef;
/*     */   }
/*     */   
/*     */   private final void ensureCompleted$1() {
/* 298 */     result().tryComplete((Try)new Failure((Throwable)new ActorKilledException("Stopped")));
/* 299 */     Set<ActorRef> watchers = clearWatchers();
/* 300 */     if (!watchers.isEmpty())
/* 301 */       watchers.foreach((Function1)new PromiseActorRef$$anonfun$ensureCompleted$1$1(this)); 
/*     */   }
/*     */   
/*     */   public class PromiseActorRef$$anonfun$ensureCompleted$1$1 extends AbstractFunction1<ActorRef, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public PromiseActorRef$$anonfun$ensureCompleted$1$1(PromiseActorRef $outer) {}
/*     */     
/*     */     public final void apply(ActorRef watcher) {
/* 303 */       ((InternalActorRef)watcher)
/* 304 */         .sendSystemMessage((SystemMessage)new DeathWatchNotification(watcher, true, false));
/*     */     }
/*     */   }
/*     */   
/*     */   public void stop() {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: invokespecial state : ()Ljava/lang/Object;
/*     */     //   4: astore_2
/*     */     //   5: aload_2
/*     */     //   6: ifnonnull -> 33
/*     */     //   9: aload_0
/*     */     //   10: aconst_null
/*     */     //   11: pop
/*     */     //   12: aconst_null
/*     */     //   13: getstatic akka/pattern/PromiseActorRef$Stopped$.MODULE$ : Lakka/pattern/PromiseActorRef$Stopped$;
/*     */     //   16: invokespecial updateState : (Ljava/lang/Object;Ljava/lang/Object;)Z
/*     */     //   19: ifeq -> 0
/*     */     //   22: aload_0
/*     */     //   23: invokespecial ensureCompleted$1 : ()V
/*     */     //   26: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */     //   29: astore_3
/*     */     //   30: goto -> 144
/*     */     //   33: aload_2
/*     */     //   34: instanceof akka/actor/ActorPath
/*     */     //   37: ifeq -> 86
/*     */     //   40: aload_2
/*     */     //   41: checkcast akka/actor/ActorPath
/*     */     //   44: astore #4
/*     */     //   46: aload_0
/*     */     //   47: aload #4
/*     */     //   49: new akka/pattern/PromiseActorRef$StoppedWithPath
/*     */     //   52: dup
/*     */     //   53: aload #4
/*     */     //   55: invokespecial <init> : (Lakka/actor/ActorPath;)V
/*     */     //   58: invokespecial updateState : (Ljava/lang/Object;Ljava/lang/Object;)Z
/*     */     //   61: ifeq -> 0
/*     */     //   64: aload_0
/*     */     //   65: invokespecial ensureCompleted$1 : ()V
/*     */     //   68: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */     //   71: aload_0
/*     */     //   72: invokevirtual provider : ()Lakka/actor/ActorRefProvider;
/*     */     //   75: aload #4
/*     */     //   77: invokeinterface unregisterTempActor : (Lakka/actor/ActorPath;)V
/*     */     //   82: astore_3
/*     */     //   83: goto -> 144
/*     */     //   86: getstatic akka/pattern/PromiseActorRef$Stopped$.MODULE$ : Lakka/pattern/PromiseActorRef$Stopped$;
/*     */     //   89: aload_2
/*     */     //   90: astore #6
/*     */     //   92: dup
/*     */     //   93: ifnonnull -> 105
/*     */     //   96: pop
/*     */     //   97: aload #6
/*     */     //   99: ifnull -> 113
/*     */     //   102: goto -> 119
/*     */     //   105: aload #6
/*     */     //   107: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   110: ifeq -> 119
/*     */     //   113: iconst_1
/*     */     //   114: istore #7
/*     */     //   116: goto -> 135
/*     */     //   119: aload_2
/*     */     //   120: instanceof akka/pattern/PromiseActorRef$StoppedWithPath
/*     */     //   123: ifeq -> 132
/*     */     //   126: iconst_1
/*     */     //   127: istore #7
/*     */     //   129: goto -> 135
/*     */     //   132: iconst_0
/*     */     //   133: istore #7
/*     */     //   135: iload #7
/*     */     //   137: ifeq -> 149
/*     */     //   140: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */     //   143: astore_3
/*     */     //   144: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */     //   147: pop
/*     */     //   148: return
/*     */     //   149: getstatic akka/pattern/PromiseActorRef$Registering$.MODULE$ : Lakka/pattern/PromiseActorRef$Registering$;
/*     */     //   152: aload_2
/*     */     //   153: astore #8
/*     */     //   155: dup
/*     */     //   156: ifnonnull -> 168
/*     */     //   159: pop
/*     */     //   160: aload #8
/*     */     //   162: ifnull -> 0
/*     */     //   165: goto -> 176
/*     */     //   168: aload #8
/*     */     //   170: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   173: ifne -> 0
/*     */     //   176: new scala/MatchError
/*     */     //   179: dup
/*     */     //   180: aload_2
/*     */     //   181: invokespecial <init> : (Ljava/lang/Object;)V
/*     */     //   184: athrow
/*     */     //   185: astore #5
/*     */     //   187: aload_0
/*     */     //   188: invokevirtual provider : ()Lakka/actor/ActorRefProvider;
/*     */     //   191: aload #4
/*     */     //   193: invokeinterface unregisterTempActor : (Lakka/actor/ActorPath;)V
/*     */     //   198: aload #5
/*     */     //   200: athrow
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #308	-> 0
/*     */     //   #309	-> 5
/*     */     //   #310	-> 9
/*     */     //   #311	-> 33
/*     */     //   #312	-> 46
/*     */     //   #313	-> 86
/*     */     //   #308	-> 144
/*     */     //   #314	-> 149
/*     */     //   #308	-> 176
/*     */     //   #312	-> 185
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	201	0	this	Lakka/pattern/PromiseActorRef;
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   64	71	185	finally
/*     */   }
/*     */   
/*     */   public static PromiseActorRef apply(ActorRefProvider paramActorRefProvider, Timeout paramTimeout, String paramString) {
/*     */     return PromiseActorRef$.MODULE$.apply(paramActorRefProvider, paramTimeout, paramString);
/*     */   }
/*     */   
/*     */   public static class Registering$ implements Product, Serializable {
/*     */     public static final Registering$ MODULE$;
/*     */     
/*     */     public String productPrefix() {
/* 323 */       return "Registering";
/*     */     }
/*     */     
/*     */     public int productArity() {
/* 323 */       return 0;
/*     */     }
/*     */     
/*     */     public Object productElement(int x$1) {
/* 323 */       int i = x$1;
/* 323 */       throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */     }
/*     */     
/*     */     public Iterator<Object> productIterator() {
/* 323 */       return scala.runtime.ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */     }
/*     */     
/*     */     public boolean canEqual(Object x$1) {
/* 323 */       return x$1 instanceof Registering$;
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 323 */       return -465409473;
/*     */     }
/*     */     
/*     */     public String toString() {
/* 323 */       return "Registering";
/*     */     }
/*     */     
/*     */     private Object readResolve() {
/* 323 */       return MODULE$;
/*     */     }
/*     */     
/*     */     public Registering$() {
/* 324 */       MODULE$ = this;
/* 324 */       Product.class.$init$(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Stopped$ implements Product, Serializable {
/*     */     public static final Stopped$ MODULE$;
/*     */     
/*     */     public String productPrefix() {
/* 324 */       return "Stopped";
/*     */     }
/*     */     
/*     */     public int productArity() {
/* 324 */       return 0;
/*     */     }
/*     */     
/*     */     public Object productElement(int x$1) {
/* 324 */       int i = x$1;
/* 324 */       throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */     }
/*     */     
/*     */     public Iterator<Object> productIterator() {
/* 324 */       return scala.runtime.ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */     }
/*     */     
/*     */     public boolean canEqual(Object x$1) {
/* 324 */       return x$1 instanceof Stopped$;
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 324 */       return -219666003;
/*     */     }
/*     */     
/*     */     public String toString() {
/* 324 */       return "Stopped";
/*     */     }
/*     */     
/*     */     private Object readResolve() {
/* 324 */       return MODULE$;
/*     */     }
/*     */     
/*     */     public Stopped$() {
/* 325 */       MODULE$ = this;
/* 325 */       Product.class.$init$(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class StoppedWithPath implements Product, Serializable {
/*     */     private final ActorPath path;
/*     */     
/*     */     public ActorPath path() {
/* 325 */       return this.path;
/*     */     }
/*     */     
/*     */     public StoppedWithPath copy(ActorPath path) {
/* 325 */       return new StoppedWithPath(path);
/*     */     }
/*     */     
/*     */     public ActorPath copy$default$1() {
/* 325 */       return path();
/*     */     }
/*     */     
/*     */     public String productPrefix() {
/* 325 */       return "StoppedWithPath";
/*     */     }
/*     */     
/*     */     public int productArity() {
/* 325 */       return 1;
/*     */     }
/*     */     
/*     */     public Object productElement(int x$1) {
/* 325 */       int i = x$1;
/* 325 */       switch (i) {
/*     */         default:
/* 325 */           throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */         case 0:
/*     */           break;
/*     */       } 
/* 325 */       return path();
/*     */     }
/*     */     
/*     */     public Iterator<Object> productIterator() {
/* 325 */       return scala.runtime.ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */     }
/*     */     
/*     */     public boolean canEqual(Object x$1) {
/* 325 */       return x$1 instanceof StoppedWithPath;
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 325 */       return scala.runtime.ScalaRunTime$.MODULE$._hashCode(this);
/*     */     }
/*     */     
/*     */     public String toString() {
/* 325 */       return scala.runtime.ScalaRunTime$.MODULE$._toString(this);
/*     */     }
/*     */     
/*     */     public boolean equals(Object x$1) {
/*     */       // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: if_acmpeq -> 80
/*     */       //   5: aload_1
/*     */       //   6: astore_2
/*     */       //   7: aload_2
/*     */       //   8: instanceof akka/pattern/PromiseActorRef$StoppedWithPath
/*     */       //   11: ifeq -> 19
/*     */       //   14: iconst_1
/*     */       //   15: istore_3
/*     */       //   16: goto -> 21
/*     */       //   19: iconst_0
/*     */       //   20: istore_3
/*     */       //   21: iload_3
/*     */       //   22: ifeq -> 84
/*     */       //   25: aload_1
/*     */       //   26: checkcast akka/pattern/PromiseActorRef$StoppedWithPath
/*     */       //   29: astore #4
/*     */       //   31: aload_0
/*     */       //   32: invokevirtual path : ()Lakka/actor/ActorPath;
/*     */       //   35: aload #4
/*     */       //   37: invokevirtual path : ()Lakka/actor/ActorPath;
/*     */       //   40: astore #5
/*     */       //   42: dup
/*     */       //   43: ifnonnull -> 55
/*     */       //   46: pop
/*     */       //   47: aload #5
/*     */       //   49: ifnull -> 63
/*     */       //   52: goto -> 76
/*     */       //   55: aload #5
/*     */       //   57: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   60: ifeq -> 76
/*     */       //   63: aload #4
/*     */       //   65: aload_0
/*     */       //   66: invokevirtual canEqual : (Ljava/lang/Object;)Z
/*     */       //   69: ifeq -> 76
/*     */       //   72: iconst_1
/*     */       //   73: goto -> 77
/*     */       //   76: iconst_0
/*     */       //   77: ifeq -> 84
/*     */       //   80: iconst_1
/*     */       //   81: goto -> 85
/*     */       //   84: iconst_0
/*     */       //   85: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #325	-> 0
/*     */       //   #63	-> 14
/*     */       //   #325	-> 21
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	86	0	this	Lakka/pattern/PromiseActorRef$StoppedWithPath;
/*     */       //   0	86	1	x$1	Ljava/lang/Object;
/*     */     }
/*     */     
/*     */     public StoppedWithPath(ActorPath path) {
/* 325 */       Product.class.$init$(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class StoppedWithPath$ extends AbstractFunction1<ActorPath, StoppedWithPath> implements Serializable {
/*     */     public static final StoppedWithPath$ MODULE$;
/*     */     
/*     */     public final String toString() {
/* 325 */       return "StoppedWithPath";
/*     */     }
/*     */     
/*     */     public PromiseActorRef.StoppedWithPath apply(ActorPath path) {
/* 325 */       return new PromiseActorRef.StoppedWithPath(path);
/*     */     }
/*     */     
/*     */     public Option<ActorPath> unapply(PromiseActorRef.StoppedWithPath x$0) {
/* 325 */       return (x$0 == null) ? (Option<ActorPath>)scala.None$.MODULE$ : (Option<ActorPath>)new Some(x$0.path());
/*     */     }
/*     */     
/*     */     private Object readResolve() {
/* 325 */       return MODULE$;
/*     */     }
/*     */     
/*     */     public StoppedWithPath$() {
/* 325 */       MODULE$ = this;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class PromiseActorRef$$anonfun$1 extends AbstractFunction0.mcV.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Timeout timeout$1;
/*     */     
/*     */     private final String targetName$1;
/*     */     
/*     */     private final Promise result$1;
/*     */     
/*     */     public final void apply() {
/* 333 */       apply$mcV$sp();
/*     */     }
/*     */     
/*     */     public void apply$mcV$sp() {
/* 333 */       (new String[3])[0] = "Ask timed out on [";
/* 333 */       (new String[3])[1] = "] after [";
/* 333 */       (new String[3])[2] = " ms]";
/* 333 */       this.result$1.tryComplete((Try)new Failure(new AskTimeoutException((new StringContext((Seq)scala.Predef$.MODULE$.wrapRefArray((Object[])new String[3]))).s((Seq)scala.Predef$.MODULE$.genericWrapArray(new Object[] { this.targetName$1, BoxesRunTime.boxToLong(this.timeout$1.duration().toMillis()) })))));
/*     */     }
/*     */     
/*     */     public PromiseActorRef$$anonfun$1(Timeout timeout$1, String targetName$1, Promise result$1) {}
/*     */   }
/*     */   
/*     */   public static class PromiseActorRef$$anonfun$apply$1 extends AbstractFunction1<Try<Object>, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final PromiseActorRef a$1;
/*     */     
/*     */     private final Cancellable f$1;
/*     */     
/*     */     public final void apply(Try x$1) {
/*     */       try {
/* 335 */         this.a$1.stop();
/*     */         return;
/*     */       } finally {
/* 335 */         this.f$1.cancel();
/*     */       } 
/*     */     }
/*     */     
/*     */     public PromiseActorRef$$anonfun$apply$1(PromiseActorRef a$1, Cancellable f$1) {}
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\pattern\PromiseActorRef.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */