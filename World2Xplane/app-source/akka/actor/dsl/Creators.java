/*     */ package akka.actor.dsl;
/*     */ 
/*     */ import akka.actor.Actor;
/*     */ import akka.actor.Actor$emptyBehavior$;
/*     */ import akka.actor.ActorRef;
/*     */ import akka.actor.ActorRefFactory;
/*     */ import akka.actor.AllForOneStrategy$;
/*     */ import akka.actor.OneForOneStrategy$;
/*     */ import akka.actor.Stash;
/*     */ import akka.actor.SupervisorStrategy;
/*     */ import akka.actor.SupervisorStrategy$Escalate$;
/*     */ import akka.actor.SupervisorStrategy$Restart$;
/*     */ import akka.actor.SupervisorStrategy$Resume$;
/*     */ import akka.actor.SupervisorStrategy$Stop$;
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.Function2;
/*     */ import scala.Option;
/*     */ import scala.PartialFunction;
/*     */ import scala.reflect.ClassTag;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.BoxedUnit;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\tec!C\001\003!\003\r\t!\003B*\005!\031%/Z1u_J\034(BA\002\005\003\r!7\017\034\006\003\013\031\tQ!Y2u_JT\021aB\001\005C.\\\027m\001\001\024\005\001Q\001CA\006\017\033\005a!\"A\007\002\013M\034\027\r\\1\n\005=a!AB!osJ+g\rC\003\022\001\021\005!#\001\004%S:LG\017\n\013\002'A\0211\002F\005\003+1\021A!\0268ji\0329q\003\001I\001\004\003A\"aA!diN\031aCC\r\021\005iYR\"\001\003\n\005q!!!B!di>\024\b\"B\t\027\t\003\021\002BB\020\027A\003&\001%A\006qe\026\034F/\031:u\rVt\007cA\006\"'%\021!\005\004\002\n\rVt7\r^5p]BBa\001\n\f!B\023\001\023a\0039pgR\034Fo\0349Gk:DaA\n\f!B\0239\023!\0049sKJ+7\017^1si\032+h\016E\003\fQ)24#\003\002*\031\tIa)\0368di&|gN\r\t\003WMr!\001L\031\017\0055\002T\"\001\030\013\005=B\021A\002\037s_>$h(C\001\016\023\t\021D\"A\004qC\016\\\027mZ3\n\005Q*$!\003+ie><\030M\0317f\025\t\021D\002E\002\foeJ!\001\017\007\003\r=\003H/[8o!\tY!(\003\002<\031\t\031\021I\\=\t\ru2\002\025)\003?\0039\001xn\035;SKN$\030M\035;Gk:\004BaC +'%\021\001\t\004\002\n\rVt7\r^5p]FBaA\021\f!B\023\031\025\001C:ue\006$XmZ=\021\005i!\025BA#\005\005I\031V\017]3sm&\034xN]*ue\006$XmZ=\t\013\0353B\021\001%\002#=sWMR8s\037:,7\013\036:bi\026<\0270F\001J\035\tQeJ\004\002L\033:\021Q\006T\005\002\017%\021QAB\005\003\017\022AQ\001\025\f\005\002E\013\021#\0217m\r>\024xJ\\3TiJ\fG/Z4z+\005\021fB\001&T\023\t\001F\001C\003V-\021\005a+\001\003Ti>\004X#A,\017\005a[fB\001\016Z\023\tQF!\001\nTkB,'O^5t_J\034FO]1uK\036L\030BA+]\025\tQF\001C\003_-\021\005q,A\004SKN$\030M\035;\026\003\001t!\001W1\n\005yc\006\"B2\027\t\003!\027A\002*fgVlW-F\001f\035\tAf-\003\002d9\")\001N\006C\001S\006AQi]2bY\006$X-F\001k\035\tA6.\003\002i9\")QN\006C\001]\006i!-Z2p[\026\034F/Y2lK\022$\"aE8\t\013Ad\007\031A9\002\003I\004\"A]:\016\003YI!\001^\016\003\017I+7-Z5wK\")aO\006C\001o\0061!-Z2p[\026$\"a\005=\t\013A,\b\031A9\t\013i4B\021\001\n\002\021Ut'-Z2p[\026DQ\001 \f\005\002u\fQb];qKJ4\030n]3XSRDGCA\n\021\025y8\0201\001D\003\005\031\bbBA\002-\021\005\021QA\001\ro\",gn\025;beRLgn\032\013\004'\005\035\001\"CA\005\003\003!\t\031AA\006\003\021\021w\016Z=\021\t-\tiaE\005\004\003\037a!\001\003\037cs:\fW.\032 \t\017\005Ma\003\"\001\002\026\005Yq\017[3o\r\006LG.\0338h)\r\031\022q\003\005\b\003\023\t\t\0021\001(\021\035\tYB\006C\001\003;\tQb\0365f]J+7\017^1si\026$GcA\n\002 !9\021\021BA\r\001\004q\004bBA\022-\021\005\021QE\001\ro\",gn\025;paBLgn\032\013\004'\005\035\002\"CA\005\003C!\t\031AA\006\021\031\tYC\006C!%\005A\001O]3Ti\006\024H\017C\004\0020Y!\t%!\r\002\025A\024XMU3ti\006\024H\017F\003\024\003g\t9\004C\004\0026\0055\002\031\001\026\002\013\r\fWo]3\t\017\005e\022Q\006a\001m\005\031Qn]4\t\017\005ub\003\"\021\002@\005Y\001o\\:u%\026\034H/\031:u)\r\031\022\021\t\005\b\003k\tY\0041\001+\021\031\t)E\006C!%\005A\001o\\:u'R|\007\017C\004\002JY!\t%a\023\002%M,\b/\032:wSN|'o\025;sCR,w-_\013\002\007\"9\021q\n\f\005B\005E\023a\002:fG\026Lg/Z\013\002c\"Y\021Q\013\f\002\002\003%IAEA,\0039\031X\017]3sIA\024Xm\025;beRL1!a\013\034\0211\tYFFA\001\002\023%\021QLA4\003A\031X\017]3sIA\024XMU3ti\006\024H\017F\003\024\003?\n\031\007C\004\002b\005e\003\031\001\026\002\rI,\027m]8o\021\035\t)'!\027A\002Y\nq!\\3tg\006<W-C\002\0020mAA\"a\033\027\003\003\005I\021BA7\003c\n\021c];qKJ$\003o\\:u%\026\034H/\031:u)\r\031\022q\016\005\b\003C\nI\0071\001+\023\r\tid\007\005\f\003k2\022\021!A\005\nI\t9(\001\btkB,'\017\n9pgR\034Fo\0349\n\007\005\0253\004\003\007\002|Y\t\t\021!C\005\003\027\ni(\001\rtkB,'\017J:va\026\024h/[:peN#(/\031;fOfL1!!\023\034\r%\t\t\t\001I\001$\003\t\031I\001\007BGR<\026\016\0365Ti\006\034\bnE\004\002\000)\t))!#\021\007\005\035e#D\001\001!\rQ\0221R\005\004\003\033#!!B*uCND\007bBAI\001\021%\0211S\001\b[.\004&o\0349t)\031\t)*a'\002@B\031!$a&\n\007\005eEAA\003Qe>\0048\017\003\005\002\036\006=\005\031AAP\0031\031G.Y:t\037\032\f5\r^8sa\021\t\t+a-\021\r\005\r\026\021VAX\035\rY\021QU\005\004\003Oc\021A\002)sK\022,g-\003\003\002,\0065&!B\"mCN\034(bAAT\031A!\021\021WAZ\031\001!A\"!.\002\034\006\005\t\021!B\001\003o\0231a\030\0232#\r\tI,\017\t\004\027\005m\026bAA_\031\t9aj\034;iS:<\007\002CAa\003\037\003\r!a1\002\t\r$xN\035\t\004\027\005J\002BB\003\001\t\003\t9-\006\003\002J\006\025H\003BAf\003k$b!!4\002T\006-\bc\001\016\002P&\031\021\021\033\003\003\021\005\033Go\034:SK\032D!\"!6\002F\006\005\t9AAl\003))g/\0333f]\016,G%\r\t\007\0033\fy.a9\016\005\005m'bAAo\031\0059!/\0324mK\016$\030\002BAq\0037\024\001b\0217bgN$\026m\032\t\005\003c\013)\017\002\005\002h\006\025'\031AAu\005\005!\026cAA]3!A\021Q^Ac\001\b\ty/A\004gC\016$xN]=\021\007i\t\t0C\002\002t\022\021q\"Q2u_J\024VM\032$bGR|'/\037\005\n\003\003\f)\r\"a\001\003o\004RaCA\007\003GDa!\002\001\005\002\005mX\003BA\005\027!B!a@\003\024Q!!\021\001B\b)\031\tiMa\001\003\016!Q!QAA}\003\003\005\035Aa\002\002\025\0254\030\016Z3oG\026$#\007\005\004\002Z\006}'\021\002\t\005\003c\023Y\001\002\005\002h\006e(\031AAu\021!\ti/!?A\004\005=\b\"CAa\003s$\t\031\001B\t!\025Y\021Q\002B\005\021!\021)\"!?A\002\t]\021\001\0028b[\026\004B!a)\003\032%!!1DAW\005\031\031FO]5oO\"1Q\001\001C\001\005?)BA!\t\0030Q1!1\005B\033\005o!BA!\n\0032Q!\021Q\032B\024\021)\021IC!\b\002\002\003\017!1F\001\013KZLG-\0328dK\022\032\004CBAm\003?\024i\003\005\003\0022\n=B\001CAt\005;\021\r!!;\t\023\005\005'Q\004CA\002\tM\002#B\006\002\016\t5\002\002CAw\005;\001\r!a<\t\021\tU!Q\004a\001\005/Aa!\002\001\005\002\tmR\003\002B\037\005\027\"BAa\020\003RQ!!\021\tB')\021\tiMa\021\t\025\t\025#\021HA\001\002\b\0219%\001\006fm&$WM\\2fIQ\002b!!7\002`\n%\003\003BAY\005\027\"\001\"a:\003:\t\007\021\021\036\005\n\003\003\024I\004\"a\001\005\037\002RaCA\007\005\023B\001\"!<\003:\001\007\021q\036\b\0045\tU\023b\001B,\t\005A\021i\031;pe\022\033F\n")
/*     */ public interface Creators {
/*     */   <T extends Actor> ActorRef actor(Function0<T> paramFunction0, ClassTag<T> paramClassTag, ActorRefFactory paramActorRefFactory);
/*     */   
/*     */   <T extends Actor> ActorRef actor(String paramString, Function0<T> paramFunction0, ClassTag<T> paramClassTag, ActorRefFactory paramActorRefFactory);
/*     */   
/*     */   <T extends Actor> ActorRef actor(ActorRefFactory paramActorRefFactory, String paramString, Function0<T> paramFunction0, ClassTag<T> paramClassTag);
/*     */   
/*     */   <T extends Actor> ActorRef actor(ActorRefFactory paramActorRefFactory, Function0<T> paramFunction0, ClassTag<T> paramClassTag);
/*     */   
/*     */   public interface Act extends Actor {
/*     */     Function0 akka$actor$dsl$Creators$Act$$preStartFun();
/*     */     
/*     */     void akka$actor$dsl$Creators$Act$$preStartFun_$eq(Function0 param1Function0);
/*     */     
/*     */     Function0 akka$actor$dsl$Creators$Act$$postStopFun();
/*     */     
/*     */     void akka$actor$dsl$Creators$Act$$postStopFun_$eq(Function0 param1Function0);
/*     */     
/*     */     Function2 akka$actor$dsl$Creators$Act$$preRestartFun();
/*     */     
/*     */     void akka$actor$dsl$Creators$Act$$preRestartFun_$eq(Function2 param1Function2);
/*     */     
/*     */     Function1 akka$actor$dsl$Creators$Act$$postRestartFun();
/*     */     
/*     */     void akka$actor$dsl$Creators$Act$$postRestartFun_$eq(Function1 param1Function1);
/*     */     
/*     */     SupervisorStrategy akka$actor$dsl$Creators$Act$$strategy();
/*     */     
/*     */     void akka$actor$dsl$Creators$Act$$strategy_$eq(SupervisorStrategy param1SupervisorStrategy);
/*     */     
/*     */     void akka$actor$dsl$Creators$Act$$super$preStart();
/*     */     
/*     */     void akka$actor$dsl$Creators$Act$$super$preRestart(Throwable param1Throwable, Option<Object> param1Option);
/*     */     
/*     */     void akka$actor$dsl$Creators$Act$$super$postRestart(Throwable param1Throwable);
/*     */     
/*     */     void akka$actor$dsl$Creators$Act$$super$postStop();
/*     */     
/*     */     SupervisorStrategy akka$actor$dsl$Creators$Act$$super$supervisorStrategy();
/*     */     
/*     */     OneForOneStrategy$ OneForOneStrategy();
/*     */     
/*     */     AllForOneStrategy$ AllForOneStrategy();
/*     */     
/*     */     SupervisorStrategy$Stop$ Stop();
/*     */     
/*     */     SupervisorStrategy$Restart$ Restart();
/*     */     
/*     */     SupervisorStrategy$Resume$ Resume();
/*     */     
/*     */     SupervisorStrategy$Escalate$ Escalate();
/*     */     
/*     */     void becomeStacked(PartialFunction<Object, BoxedUnit> param1PartialFunction);
/*     */     
/*     */     void become(PartialFunction<Object, BoxedUnit> param1PartialFunction);
/*     */     
/*     */     void unbecome();
/*     */     
/*     */     void superviseWith(SupervisorStrategy param1SupervisorStrategy);
/*     */     
/*     */     void whenStarting(Function0<BoxedUnit> param1Function0);
/*     */     
/*     */     void whenFailing(Function2<Throwable, Option<Object>, BoxedUnit> param1Function2);
/*     */     
/*     */     void whenRestarted(Function1<Throwable, BoxedUnit> param1Function1);
/*     */     
/*     */     void whenStopping(Function0<BoxedUnit> param1Function0);
/*     */     
/*     */     void preStart();
/*     */     
/*     */     void preRestart(Throwable param1Throwable, Option<Object> param1Option);
/*     */     
/*     */     void postRestart(Throwable param1Throwable);
/*     */     
/*     */     void postStop();
/*     */     
/*     */     SupervisorStrategy supervisorStrategy();
/*     */     
/*     */     PartialFunction<Object, BoxedUnit> receive();
/*     */   }
/*     */   
/*     */   public abstract class Act$class {
/*     */     public static void $init$(Creators.Act $this) {
/*  44 */       null;
/*  44 */       $this.akka$actor$dsl$Creators$Act$$preStartFun_$eq(null);
/*  45 */       null;
/*  45 */       $this.akka$actor$dsl$Creators$Act$$postStopFun_$eq(null);
/*  46 */       null;
/*  46 */       $this.akka$actor$dsl$Creators$Act$$preRestartFun_$eq(null);
/*  47 */       null;
/*  47 */       $this.akka$actor$dsl$Creators$Act$$postRestartFun_$eq(null);
/*  48 */       null;
/*  48 */       $this.akka$actor$dsl$Creators$Act$$strategy_$eq(null);
/*     */     }
/*     */     
/*     */     public static OneForOneStrategy$ OneForOneStrategy(Creators.Act $this) {
/*  53 */       return OneForOneStrategy$.MODULE$;
/*     */     }
/*     */     
/*     */     public static AllForOneStrategy$ AllForOneStrategy(Creators.Act $this) {
/*  58 */       return AllForOneStrategy$.MODULE$;
/*     */     }
/*     */     
/*     */     public static SupervisorStrategy$Stop$ Stop(Creators.Act $this) {
/*  63 */       return SupervisorStrategy$Stop$.MODULE$;
/*     */     }
/*     */     
/*     */     public static SupervisorStrategy$Restart$ Restart(Creators.Act $this) {
/*  68 */       return SupervisorStrategy$Restart$.MODULE$;
/*     */     }
/*     */     
/*     */     public static SupervisorStrategy$Resume$ Resume(Creators.Act $this) {
/*  73 */       return SupervisorStrategy$Resume$.MODULE$;
/*     */     }
/*     */     
/*     */     public static SupervisorStrategy$Escalate$ Escalate(Creators.Act $this) {
/*  78 */       return SupervisorStrategy$Escalate$.MODULE$;
/*     */     }
/*     */     
/*     */     public static void becomeStacked(Creators.Act $this, PartialFunction r) {
/*  85 */       $this.context().become(r, false);
/*     */     }
/*     */     
/*     */     public static void become(Creators.Act $this, PartialFunction r) {
/*  92 */       $this.context().become(r, true);
/*     */     }
/*     */     
/*     */     public static void unbecome(Creators.Act $this) {
/*  98 */       $this.context().unbecome();
/*     */     }
/*     */     
/*     */     public static void superviseWith(Creators.Act $this, SupervisorStrategy s) {
/* 103 */       $this.akka$actor$dsl$Creators$Act$$strategy_$eq(s);
/*     */     }
/*     */     
/*     */     public static void whenStarting(Creators.Act $this, Function0 body) {
/* 109 */       $this.akka$actor$dsl$Creators$Act$$preStartFun_$eq(body);
/*     */     }
/*     */     
/*     */     public static void whenFailing(Creators.Act $this, Function2 body) {
/* 116 */       $this.akka$actor$dsl$Creators$Act$$preRestartFun_$eq(body);
/*     */     }
/*     */     
/*     */     public static void whenRestarted(Creators.Act $this, Function1 body) {
/* 122 */       $this.akka$actor$dsl$Creators$Act$$postRestartFun_$eq(body);
/*     */     }
/*     */     
/*     */     public static void whenStopping(Creators.Act $this, Function0 body) {
/* 128 */       $this.akka$actor$dsl$Creators$Act$$postStopFun_$eq(body);
/*     */     }
/*     */     
/*     */     public static void preStart(Creators.Act $this) {
/* 130 */       if ($this.akka$actor$dsl$Creators$Act$$preStartFun() == null) {
/* 130 */         $this.akka$actor$dsl$Creators$Act$$super$preStart();
/*     */       } else {
/* 130 */         $this.akka$actor$dsl$Creators$Act$$preStartFun().apply$mcV$sp();
/*     */       } 
/*     */     }
/*     */     
/*     */     public static void preRestart(Creators.Act $this, Throwable cause, Option<Object> msg) {
/* 131 */       if ($this.akka$actor$dsl$Creators$Act$$preRestartFun() == null) {
/* 131 */         $this.akka$actor$dsl$Creators$Act$$super$preRestart(cause, msg);
/*     */       } else {
/* 131 */         $this.akka$actor$dsl$Creators$Act$$preRestartFun().apply(cause, msg);
/*     */       } 
/*     */     }
/*     */     
/*     */     public static void postRestart(Creators.Act $this, Throwable cause) {
/* 132 */       if ($this.akka$actor$dsl$Creators$Act$$postRestartFun() == null) {
/* 132 */         $this.akka$actor$dsl$Creators$Act$$super$postRestart(cause);
/*     */       } else {
/* 132 */         $this.akka$actor$dsl$Creators$Act$$postRestartFun().apply(cause);
/*     */       } 
/*     */     }
/*     */     
/*     */     public static void postStop(Creators.Act $this) {
/* 133 */       if ($this.akka$actor$dsl$Creators$Act$$postStopFun() == null) {
/* 133 */         $this.akka$actor$dsl$Creators$Act$$super$postStop();
/*     */       } else {
/* 133 */         $this.akka$actor$dsl$Creators$Act$$postStopFun().apply$mcV$sp();
/*     */       } 
/*     */     }
/*     */     
/*     */     public static SupervisorStrategy supervisorStrategy(Creators.Act $this) {
/* 134 */       return ($this.akka$actor$dsl$Creators$Act$$strategy() == null) ? $this.akka$actor$dsl$Creators$Act$$super$supervisorStrategy() : $this.akka$actor$dsl$Creators$Act$$strategy();
/*     */     }
/*     */     
/*     */     public static PartialFunction receive(Creators.Act $this) {
/* 139 */       return (PartialFunction)Actor$emptyBehavior$.MODULE$;
/*     */     }
/*     */   }
/*     */   
/*     */   public interface ActWithStash extends Act, Stash {}
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\dsl\Creators.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */