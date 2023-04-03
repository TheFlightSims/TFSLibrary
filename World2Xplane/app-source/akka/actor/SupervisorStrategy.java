/*     */ package akka.actor;
/*     */ 
/*     */ import akka.event.Logging;
/*     */ import akka.japi.Function;
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.Option;
/*     */ import scala.PartialFunction;
/*     */ import scala.Product;
/*     */ import scala.Serializable;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.Iterable;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.immutable.Seq;
/*     */ import scala.collection.mutable.ArrayBuffer;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.AbstractFunction2;
/*     */ import scala.runtime.AbstractPartialFunction;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\rur!B\001\003\021\0039\021AE*va\026\024h/[:peN#(/\031;fOfT!a\001\003\002\013\005\034Go\034:\013\003\025\tA!Y6lC\016\001\001C\001\005\n\033\005\021a!\002\006\003\021\003Y!AE*va\026\024h/[:peN#(/\031;fOf\0342!\003\007\023!\ti\001#D\001\017\025\005y\021!B:dC2\f\027BA\t\017\005\031\te.\037*fMB\021\001bE\005\003)\t\021aeU;qKJ4\030n]8s'R\024\030\r^3hs2{w\017\025:j_JLG/_%na2L7-\033;t\021\0251\022\002\"\001\030\003\031a\024N\\5u}Q\tqAB\004\032\023A\005\031\023\005\016\003\023\021K'/Z2uSZ,7C\001\r\rS\031ABdX9\002\b\031)Q$\003EA=\tAQi]2bY\006$XmE\003\035\031}\tC\005\005\002!15\t\021\002\005\002\016E%\0211E\004\002\b!J|G-^2u!\tiQ%\003\002'\035\ta1+\032:jC2L'0\0312mK\")a\003\bC\001QQ\t\021\006\005\002!9!91\006HA\001\n\003b\023!\0049s_\022,8\r\036)sK\032L\0070F\001.!\tq3'D\0010\025\t\001\024'\001\003mC:<'\"\001\032\002\t)\fg/Y\005\003i=\022aa\025;sS:<\007b\002\034\035\003\003%\taN\001\raJ|G-^2u\003JLG/_\013\002qA\021Q\"O\005\003u9\0211!\0238u\021\035aD$!A\005\002u\na\002\035:pIV\034G/\0227f[\026tG\017\006\002?\003B\021QbP\005\003\001:\0211!\0218z\021\035\0215(!AA\002a\n1\001\037\0232\021\035!E$!A\005B\025\013q\002\035:pIV\034G/\023;fe\006$xN]\013\002\rB\031qI\023 \016\003!S!!\023\b\002\025\r|G\016\\3di&|g.\003\002L\021\nA\021\n^3sCR|'\017C\004N9\005\005I\021\001(\002\021\r\fg.R9vC2$\"a\024*\021\0055\001\026BA)\017\005\035\021un\0347fC:DqA\021'\002\002\003\007a\bC\004U9\005\005I\021I+\002\021!\f7\017[\"pI\026$\022\001\017\005\b/r\t\t\021\"\021Y\003!!xn\025;sS:<G#A\027\t\017ic\022\021!C\0057\006Y!/Z1e%\026\034x\016\034<f)\005a\006C\001\030^\023\tqvF\001\004PE*,7\r\036\004\006A&A\t)\031\002\b%\026\034H/\031:u'\025yFbH\021%\021\0251r\f\"\001d)\005!\007C\001\021`\021\035Ys,!A\005B1BqAN0\002\002\023\005q\007C\004=?\006\005I\021\0015\025\005yJ\007b\002\"h\003\003\005\r\001\017\005\b\t~\013\t\021\"\021F\021\035iu,!A\005\0021$\"aT7\t\017\t[\027\021!a\001}!9AkXA\001\n\003*\006bB,`\003\003%\t\005\027\005\b5~\013\t\021\"\003\\\r\025\021\030\002#!t\005\031\021Vm];nKN)\021\017D\020\"I!)a#\035C\001kR\ta\017\005\002!c\"91&]A\001\n\003b\003b\002\034r\003\003%\ta\016\005\byE\f\t\021\"\001{)\tq4\020C\004Cs\006\005\t\031\001\035\t\017\021\013\030\021!C!\013\"9Q*]A\001\n\003qHCA(\000\021\035\021U0!AA\002yBq\001V9\002\002\023\005S\013C\004Xc\006\005I\021\t-\t\017i\013\030\021!C\0057\0329\021\021B\005\t\002\006-!\001B*u_B\034b!a\002\r?\005\"\003b\002\f\002\b\021\005\021q\002\013\003\003#\0012\001IA\004\021!Y\023qAA\001\n\003b\003\002\003\034\002\b\005\005I\021A\034\t\023q\n9!!A\005\002\005eAc\001 \002\034!A!)a\006\002\002\003\007\001\b\003\005E\003\017\t\t\021\"\021F\021%i\025qAA\001\n\003\t\t\003F\002P\003GA\001BQA\020\003\003\005\rA\020\005\t)\006\035\021\021!C!+\"Aq+a\002\002\002\023\005\003\f\003\005[\003\017\t\t\021\"\003\\\017\031\ti#\003EAm\0061!+Z:v[\026<a!!\r\n\021\003#\027a\002*fgR\f'\017^\004\b\003kI\001\022QA\t\003\021\031Fo\0349\b\r\005e\022\002#!*\003!)5oY1mCR,\007bBA\037\023\021\005\021qH\001\007e\026\034X/\\3\026\005\005\005cb\001\021\002,!9\021QI\005\005\002\005\035\023a\002:fgR\f'\017^\013\003\003\023r1\001IA\030\021\035\ti%\003C\001\003\037\nAa\035;paV\021\021\021\013\b\004A\005M\002bBA+\023\021\005\021qK\001\tKN\034\027\r\\1uKV\021\021\021\f\b\004A\005]\002\"CA/\023\t\007IQAA0\0039!WMZ1vYR$UmY5eKJ,\"!!\031\021\007\001\n\031'\002\004\002f%\001\021q\r\002\b\t\026\034\027\016Z3s!\031i\021\021NA7?%\031\0211\016\b\003\037A\013'\017^5bY\032+hn\031;j_:\004B!a\034\002\0009!\021\021OA>\035\021\t\031(!\037\016\005\005U$bAA<\r\0051AH]8pizJ\021aD\005\004\003{r\021a\0029bG.\fw-Z\005\005\003\003\013\031IA\005UQJ|w/\0312mK*\031\021Q\020\b\t\021\005\035\025\002)A\007\003C\nq\002Z3gCVdG\017R3dS\022,'\017\t\005\n\003\027K!\031!C\003\003\033\013q\002Z3gCVdGo\025;sCR,w-_\013\003\003\037\0032\001CAI\r\031Q!!!\001\002\024N\031\021\021\023\007\t\017Y\t\t\n\"\001\002\030R\021\021q\022\005\t\0037\013\tJ\"\001\002\036\0069A-Z2jI\026\024XCAAP!\021\t\t+a\031\017\005!\001\001\002CAS\003#3\t!a*\002+!\fg\016\0327f\007\"LG\016\032+fe6Lg.\031;fIRA\021\021VAX\003s\013\031\rE\002\016\003WK1!!,\017\005\021)f.\033;\t\021\005E\0261\025a\001\003g\013qaY8oi\026DH\017E\002\t\003kK1!a.\003\0051\t5\r^8s\007>tG/\032=u\021!\tY,a)A\002\005u\026!B2iS2$\007c\001\005\002@&\031\021\021\031\002\003\021\005\033Go\034:SK\032D\001\"!2\002$\002\007\021qY\001\tG\"LG\016\032:f]B1\021qNAe\003{KA!a3\002\004\nA\021\n^3sC\ndW\r\003\005\002P\006Ee\021AAi\0039\001(o\\2fgN4\025-\0337ve\026$b\"!+\002T\006U\027q[Am\003;\f9\017\003\005\0022\0065\007\031AAZ\021\035\t)%!4A\002=C\001\"a/\002N\002\007\021Q\030\005\t\0037\fi\r1\001\002n\005)1-Y;tK\"A\021q\\Ag\001\004\t\t/A\003ti\006$8\017E\002\t\003GL1!!:\003\005E\031\005.\0337e%\026\034H/\031:u'R\fGo\035\005\t\003\013\fi\r1\001\002jB1\021qNAe\003CD\001\"!<\002\022\022\005\021q^\001\016Q\006tG\r\\3GC&dWO]3\025\027=\013\t0a=\002v\006]\030\021 \005\t\003c\013Y\0171\001\0024\"A\0211XAv\001\004\ti\f\003\005\002\\\006-\b\031AA7\021!\ty.a;A\002\005\005\b\002CAc\003W\004\r!!;\t\021\005u\030\021\023C\t\003\fa\002\\8hO&tw-\0228bE2,G-F\001P\021!\021\031!!%\005\002\t\025\021A\0037pO\032\013\027\016\\;sKRQ\021\021\026B\004\005\023\021YA!\004\t\021\005E&\021\001a\001\003gC\001\"a/\003\002\001\007\021Q\030\005\t\0037\024\t\0011\001\002n!A!q\002B\001\001\004\021\t\"\001\005eK\016L7/[8o!\r\t\t\013\007\005\t\005+\t\t\n\"\003\003\030\0059\001/\0362mSNDGCBAU\0053\021Y\002\003\005\0022\nM\001\031AAZ\021!\021iBa\005A\002\t}\021\001\0037pO\0263XM\034;\021\t\t\005\"1\007\b\005\005G\021iC\004\003\003&\t%b\002BA:\005OI\021!B\005\004\005W!\021!B3wK:$\030\002\002B\030\005c\tq\001T8hO&twMC\002\003,\021IAA!\016\0038\tAAj\\4Fm\026tGO\003\003\0030\tE\002\002\003B\036\003##)A!\020\002\027I,7/^7f\007\"LG\016\032\013\007\003S\023yD!\021\t\021\005m&\021\ba\001\003{C\001\"a7\003:\001\007\021Q\016\005\t\005\013\n\t\n\"\002\003H\005a!/Z:uCJ$8\t[5mIRA\021\021\026B%\005\027\022i\005\003\005\002<\n\r\003\031AA_\021!\tYNa\021A\002\0055\004b\002B(\005\007\002\raT\001\rgV\034\b/\0328e\r&\0248\017\036\005\t\005'J\001\025!\004\002\020\006\001B-\0324bk2$8\013\036:bi\026<\027\020\t\005\n\005/J!\031!C\003\003\033\013\001c\035;paBLgnZ*ue\006$XmZ=\t\021\tm\023\002)A\007\003\037\013\021c\035;paBLgnZ*ue\006$XmZ=!\021\035\021y&\003C\002\005C\nAc]3r)\"\024xn^1cY\026\024D)Z2jI\026\024H\003BA1\005GB\001B!\032\003^\001\007!qM\001\tiJ\f\007/\022=jiB1!\021\016B8\005gj!Aa\033\013\007\t5\004*A\005j[6,H/\0312mK&!!\021\017B6\005\r\031V-\035\031\005\005k\0229\t\005\004\003x\tu$1\021\b\004\033\te\024b\001B>\035\0051\001K]3eK\032LAAa \003\002\n)1\t\\1tg*\031!1\020\b\021\t\t\025%q\021\007\001\t1\021IIa\031\002\002\003\005)\021\001BF\005\ryF%M\t\005\005\033\013i\007E\002\016\005\037K1A!%\017\005\035qu\016\0365j]\036,aA!&\n\001\t]%\001\003&EK\016LG-\032:\021\017\te%qTA7?5\021!1\024\006\004\005;#\021\001\0026ba&LAA!)\003\034\nAa)\0368di&|g.\002\004\003&&\001!q\025\002\017\007\006,8/\032#je\026\034G/\033<f!\031i!\021\026BW?%\031!1\026\b\003\rQ+\b\017\\33a\021\021yKa-\021\r\t]$Q\020BY!\021\021)Ia-\005\031\tU&1UA\001\002\003\025\tAa#\003\007}##\007C\004\003:&!\tAa/\002\0275\f7.\032#fG&$WM\035\013\005\003C\022i\f\003\005\003f\t]\006\031\001B`!\031\021IGa\034\003BB\"!1\031Bd!\031\0219H! \003FB!!Q\021Bd\t1\021IM!0\002\002\003\005)\021\001BF\005\ryFe\r\005\b\005sKA\021\001Bg)\021\t\tGa4\t\021\t\025$1\032a\001\005#\004RA\fBj\005+L1!a30a\021\0219Na7\021\r\t]$Q\020Bm!\021\021)Ia7\005\031\tu'qZA\001\002\003\025\tAa#\003\007}#C\007C\004\003:&!\tA!9\025\t\005\005$1\035\005\t\005K\024y\0161\001\003h\006!a\r\\1u!\031\ty'!3\003jB\031\001Ea)\t\017\te\026\002\"\001\003nR!\021\021\rBx\021!\021\tPa;A\002\tM\030\001\0024v]\016\0042\001\tBJ\021!\02190\003C\001\t\te\030\001B:peR$BAa?\003~B1!\021\016B8\005SD\001Ba@\003v\002\007!q]\001\003S:D\001ba\001\n\t\003!1QA\001\026o&$\b.\0338US6,'+\0318hK>\003H/[8o)\021\0319a!\b\021\0135\031Ia!\004\n\007\r-aB\001\004PaRLwN\034\t\005\007\037\031I\"\004\002\004\022)!11CB\013\003!!WO]1uS>t'bAB\f\035\005Q1m\0348dkJ\024XM\034;\n\t\rm1\021\003\002\t\tV\024\030\r^5p]\"A1qDB\001\001\004\031i!A\bxSRD\027N\034+j[\026\024\026M\\4f\021!\031\031#\003C\001\t\r\025\022\001F7bq:\023xJ\032*fiJLWm](qi&|g\016\006\003\004(\r%\002\003B\007\004\naBqaa\013\004\"\001\007\001(\001\bnCbt%o\0244SKR\024\030.Z:\t\025\r=\022B1A\005\002\021\031\t$A\bfg\016\fG.\031;f\t\0264\027-\0367u+\t\031\031\004\005\004\016\007kq\024\021L\005\004\007oq!!\003$v]\016$\030n\03482\021!\031Y$\003Q\001\n\rM\022\001E3tG\006d\027\r^3EK\032\fW\017\034;!\001")
/*     */ public abstract class SupervisorStrategy {
/*     */   public static PartialFunction<Throwable, Directive> seqCauseDirective2Decider(Iterable<Tuple2<Class<? extends Throwable>, Directive>> paramIterable) {
/*     */     return SupervisorStrategy$.MODULE$.seqCauseDirective2Decider(paramIterable);
/*     */   }
/*     */   
/*     */   public static PartialFunction<Throwable, Directive> makeDecider(Function<Throwable, Directive> paramFunction) {
/*     */     return SupervisorStrategy$.MODULE$.makeDecider(paramFunction);
/*     */   }
/*     */   
/*     */   public static PartialFunction<Throwable, Directive> makeDecider(Iterable<Tuple2<Class<? extends Throwable>, Directive>> paramIterable) {
/*     */     return SupervisorStrategy$.MODULE$.makeDecider(paramIterable);
/*     */   }
/*     */   
/*     */   public static PartialFunction<Throwable, Directive> makeDecider(Iterable<Class<? extends Throwable>> paramIterable) {
/*     */     return SupervisorStrategy$.MODULE$.makeDecider(paramIterable);
/*     */   }
/*     */   
/*     */   public static PartialFunction<Throwable, Directive> makeDecider(Seq<Class<? extends Throwable>> paramSeq) {
/*     */     return SupervisorStrategy$.MODULE$.makeDecider(paramSeq);
/*     */   }
/*     */   
/*     */   public static PartialFunction<Throwable, Directive> seqThrowable2Decider(Seq<Class<? extends Throwable>> paramSeq) {
/*     */     return SupervisorStrategy$.MODULE$.seqThrowable2Decider(paramSeq);
/*     */   }
/*     */   
/*     */   public static SupervisorStrategy stoppingStrategy() {
/*     */     return SupervisorStrategy$.MODULE$.stoppingStrategy();
/*     */   }
/*     */   
/*     */   public static SupervisorStrategy defaultStrategy() {
/*     */     return SupervisorStrategy$.MODULE$.defaultStrategy();
/*     */   }
/*     */   
/*     */   public static PartialFunction<Throwable, Directive> defaultDecider() {
/*     */     return SupervisorStrategy$.MODULE$.defaultDecider();
/*     */   }
/*     */   
/*     */   public static Escalate$ escalate() {
/*     */     return SupervisorStrategy$.MODULE$.escalate();
/*     */   }
/*     */   
/*     */   public static Stop$ stop() {
/*     */     return SupervisorStrategy$.MODULE$.stop();
/*     */   }
/*     */   
/*     */   public static Restart$ restart() {
/*     */     return SupervisorStrategy$.MODULE$.restart();
/*     */   }
/*     */   
/*     */   public static Resume$ resume() {
/*     */     return SupervisorStrategy$.MODULE$.resume();
/*     */   }
/*     */   
/*     */   public abstract PartialFunction<Throwable, Directive> decider();
/*     */   
/*     */   public abstract void handleChildTerminated(ActorContext paramActorContext, ActorRef paramActorRef, Iterable<ActorRef> paramIterable);
/*     */   
/*     */   public abstract void processFailure(ActorContext paramActorContext, boolean paramBoolean, ActorRef paramActorRef, Throwable paramThrowable, ChildRestartStats paramChildRestartStats, Iterable<ChildRestartStats> paramIterable);
/*     */   
/*     */   public static class Resume$ implements Directive, Product, Serializable {
/*     */     public static final Resume$ MODULE$;
/*     */     
/*     */     public String productPrefix() {
/* 103 */       return "Resume";
/*     */     }
/*     */     
/*     */     public int productArity() {
/* 103 */       return 0;
/*     */     }
/*     */     
/*     */     public Object productElement(int x$1) {
/* 103 */       int i = x$1;
/* 103 */       throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */     }
/*     */     
/*     */     public Iterator<Object> productIterator() {
/* 103 */       return scala.runtime.ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */     }
/*     */     
/*     */     public boolean canEqual(Object x$1) {
/* 103 */       return x$1 instanceof Resume$;
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 103 */       return -1850559411;
/*     */     }
/*     */     
/*     */     public String toString() {
/* 103 */       return "Resume";
/*     */     }
/*     */     
/*     */     private Object readResolve() {
/* 103 */       return MODULE$;
/*     */     }
/*     */     
/*     */     public Resume$() {
/* 103 */       MODULE$ = this;
/* 103 */       Product.class.$init$(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Restart$ implements Directive, Product, Serializable {
/*     */     public static final Restart$ MODULE$;
/*     */     
/*     */     public String productPrefix() {
/* 109 */       return "Restart";
/*     */     }
/*     */     
/*     */     public int productArity() {
/* 109 */       return 0;
/*     */     }
/*     */     
/*     */     public Object productElement(int x$1) {
/* 109 */       int i = x$1;
/* 109 */       throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */     }
/*     */     
/*     */     public Iterator<Object> productIterator() {
/* 109 */       return scala.runtime.ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */     }
/*     */     
/*     */     public boolean canEqual(Object x$1) {
/* 109 */       return x$1 instanceof Restart$;
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 109 */       return -1532807697;
/*     */     }
/*     */     
/*     */     public String toString() {
/* 109 */       return "Restart";
/*     */     }
/*     */     
/*     */     private Object readResolve() {
/* 109 */       return MODULE$;
/*     */     }
/*     */     
/*     */     public Restart$() {
/* 109 */       MODULE$ = this;
/* 109 */       Product.class.$init$(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Stop$ implements Directive, Product, Serializable {
/*     */     public static final Stop$ MODULE$;
/*     */     
/*     */     public String productPrefix() {
/* 114 */       return "Stop";
/*     */     }
/*     */     
/*     */     public int productArity() {
/* 114 */       return 0;
/*     */     }
/*     */     
/*     */     public Object productElement(int x$1) {
/* 114 */       int i = x$1;
/* 114 */       throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */     }
/*     */     
/*     */     public Iterator<Object> productIterator() {
/* 114 */       return scala.runtime.ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */     }
/*     */     
/*     */     public boolean canEqual(Object x$1) {
/* 114 */       return x$1 instanceof Stop$;
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 114 */       return 2587682;
/*     */     }
/*     */     
/*     */     public String toString() {
/* 114 */       return "Stop";
/*     */     }
/*     */     
/*     */     private Object readResolve() {
/* 114 */       return MODULE$;
/*     */     }
/*     */     
/*     */     public Stop$() {
/* 114 */       MODULE$ = this;
/* 114 */       Product.class.$init$(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Escalate$ implements Directive, Product, Serializable {
/*     */     public static final Escalate$ MODULE$;
/*     */     
/*     */     public String productPrefix() {
/* 121 */       return "Escalate";
/*     */     }
/*     */     
/*     */     public int productArity() {
/* 121 */       return 0;
/*     */     }
/*     */     
/*     */     public Object productElement(int x$1) {
/* 121 */       int i = x$1;
/* 121 */       throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */     }
/*     */     
/*     */     public Iterator<Object> productIterator() {
/* 121 */       return scala.runtime.ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */     }
/*     */     
/*     */     public boolean canEqual(Object x$1) {
/* 121 */       return x$1 instanceof Escalate$;
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 121 */       return 1905718866;
/*     */     }
/*     */     
/*     */     public String toString() {
/* 121 */       return "Escalate";
/*     */     }
/*     */     
/*     */     private Object readResolve() {
/* 121 */       return MODULE$;
/*     */     }
/*     */     
/*     */     public Escalate$() {
/* 121 */       MODULE$ = this;
/* 121 */       Product.class.$init$(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class $anonfun$1 extends AbstractPartialFunction<Throwable, Directive> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final <A1 extends Throwable, B1> B1 applyOrElse(Throwable x1, Function1 default) {
/*     */       Object object;
/* 154 */       Throwable throwable = x1;
/* 155 */       if (throwable instanceof ActorInitializationException) {
/* 155 */         object = SupervisorStrategy.Stop$.MODULE$;
/* 156 */       } else if (throwable instanceof ActorKilledException) {
/* 156 */         object = SupervisorStrategy.Stop$.MODULE$;
/* 157 */       } else if (throwable instanceof DeathPactException) {
/* 157 */         object = SupervisorStrategy.Stop$.MODULE$;
/* 158 */       } else if (throwable instanceof Exception) {
/* 158 */         object = SupervisorStrategy.Restart$.MODULE$;
/*     */       } else {
/*     */         object = default.apply(x1);
/*     */       } 
/*     */       return (B1)object;
/*     */     }
/*     */     
/*     */     public final boolean isDefinedAt(Throwable x1) {
/*     */       boolean bool;
/*     */       Throwable throwable = x1;
/*     */       if (throwable instanceof ActorInitializationException) {
/*     */         bool = true;
/*     */       } else if (throwable instanceof ActorKilledException) {
/*     */         bool = true;
/*     */       } else if (throwable instanceof DeathPactException) {
/*     */         bool = true;
/* 158 */       } else if (throwable instanceof Exception) {
/* 158 */         bool = true;
/*     */       } else {
/*     */         bool = false;
/*     */       } 
/*     */       return bool;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class SupervisorStrategy$$anonfun$stoppingDecider$1$1 extends AbstractPartialFunction<Throwable, Directive> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final <A1 extends Throwable, B1> B1 applyOrElse(Throwable x2, Function1 default) {
/*     */       Object object;
/* 175 */       Throwable throwable = x2;
/* 176 */       if (throwable instanceof Exception) {
/* 176 */         object = SupervisorStrategy.Stop$.MODULE$;
/*     */       } else {
/*     */         object = default.apply(x2);
/*     */       } 
/*     */       return (B1)object;
/*     */     }
/*     */     
/*     */     public final boolean isDefinedAt(Throwable x2) {
/*     */       boolean bool;
/*     */       Throwable throwable = x2;
/* 176 */       if (throwable instanceof Exception) {
/* 176 */         bool = true;
/*     */       } else {
/*     */         bool = false;
/*     */       } 
/*     */       return bool;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class SupervisorStrategy$$anonfun$makeDecider$1 extends AbstractPartialFunction<Throwable, Directive> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Seq trapExit$1;
/*     */     
/*     */     public final <A1 extends Throwable, B1> B1 applyOrElse(Throwable x3, Function1 default) {
/* 195 */       Throwable throwable = x3;
/* 195 */       return (B1)(
/* 196 */         this.trapExit$1.exists((Function1)new SupervisorStrategy$$anonfun$makeDecider$1$$anonfun$applyOrElse$2(this, throwable)) ? SupervisorStrategy.Restart$.MODULE$ : SupervisorStrategy.Escalate$.MODULE$);
/*     */     }
/*     */     
/*     */     public final boolean isDefinedAt(Throwable x3) {
/*     */       Throwable throwable = x3;
/*     */       return true;
/*     */     }
/*     */     
/*     */     public SupervisorStrategy$$anonfun$makeDecider$1(Seq trapExit$1) {}
/*     */     
/*     */     public class SupervisorStrategy$$anonfun$makeDecider$1$$anonfun$applyOrElse$2 extends AbstractFunction1<Class<? extends Throwable>, Object> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final Throwable x1$1;
/*     */       
/*     */       public final boolean apply(Class x$1) {
/* 196 */         return x$1.isInstance(this.x1$1);
/*     */       }
/*     */       
/*     */       public SupervisorStrategy$$anonfun$makeDecider$1$$anonfun$applyOrElse$2(SupervisorStrategy$$anonfun$makeDecider$1 $outer, Throwable x1$1) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public static class SupervisorStrategy$$anonfun$makeDecider$2 extends AbstractPartialFunction<Throwable, Directive> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Seq directives$1;
/*     */     
/*     */     public final <A1 extends Throwable, B1> B1 applyOrElse(Throwable x4, Function1 default) {
/* 214 */       Throwable throwable = x4;
/* 214 */       return (B1)this.directives$1.collectFirst((PartialFunction)new SupervisorStrategy$$anonfun$makeDecider$2$$anonfun$applyOrElse$1(this, throwable)).getOrElse((Function0)new SupervisorStrategy$$anonfun$makeDecider$2$$anonfun$applyOrElse$3(this));
/*     */     }
/*     */     
/*     */     public final boolean isDefinedAt(Throwable x4) {
/* 214 */       Throwable throwable = x4;
/* 214 */       return true;
/*     */     }
/*     */     
/*     */     public SupervisorStrategy$$anonfun$makeDecider$2(Seq directives$1) {}
/*     */     
/*     */     public class SupervisorStrategy$$anonfun$makeDecider$2$$anonfun$applyOrElse$1 extends AbstractPartialFunction<Tuple2<Class<? extends Throwable>, SupervisorStrategy.Directive>, SupervisorStrategy.Directive> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final Throwable x1$2;
/*     */       
/*     */       public final <A1 extends Tuple2<Class<? extends Throwable>, SupervisorStrategy.Directive>, B1> B1 applyOrElse(Tuple2 x5, Function1 default) {
/* 214 */         Tuple2 tuple2 = x5;
/* 214 */         if (tuple2 != null) {
/* 214 */           Class c = (Class)tuple2._1();
/* 214 */           SupervisorStrategy.Directive d = (SupervisorStrategy.Directive)tuple2._2();
/* 214 */           if (c.isInstance(this.x1$2))
/* 214 */             return (B1)d; 
/*     */         } 
/* 214 */         return (B1)default.apply(x5);
/*     */       }
/*     */       
/*     */       public final boolean isDefinedAt(Tuple2 x5) {
/* 214 */         Tuple2 tuple2 = x5;
/* 214 */         if (tuple2 != null) {
/* 214 */           Class c = (Class)tuple2._1();
/* 214 */           if (c.isInstance(this.x1$2))
/* 214 */             return true; 
/*     */         } 
/* 214 */         return false;
/*     */       }
/*     */       
/*     */       public SupervisorStrategy$$anonfun$makeDecider$2$$anonfun$applyOrElse$1(SupervisorStrategy$$anonfun$makeDecider$2 $outer, Throwable x1$2) {}
/*     */     }
/*     */     
/*     */     public class SupervisorStrategy$$anonfun$makeDecider$2$$anonfun$applyOrElse$3 extends AbstractFunction0<SupervisorStrategy.Escalate$> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final SupervisorStrategy.Escalate$ apply() {
/* 214 */         return SupervisorStrategy.Escalate$.MODULE$;
/*     */       }
/*     */       
/*     */       public SupervisorStrategy$$anonfun$makeDecider$2$$anonfun$applyOrElse$3(SupervisorStrategy$$anonfun$makeDecider$2 $outer) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public static class SupervisorStrategy$$anonfun$makeDecider$3 extends AbstractPartialFunction<Throwable, Directive> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Function func$1;
/*     */     
/*     */     public final <A1 extends Throwable, B1> B1 applyOrElse(Throwable x6, Function1 default) {
/* 220 */       Throwable throwable = x6;
/* 220 */       return (B1)this.func$1.apply(throwable);
/*     */     }
/*     */     
/*     */     public final boolean isDefinedAt(Throwable x6) {
/* 220 */       Throwable throwable = x6;
/* 220 */       return true;
/*     */     }
/*     */     
/*     */     public SupervisorStrategy$$anonfun$makeDecider$3(Function func$1) {}
/*     */   }
/*     */   
/*     */   public static class SupervisorStrategy$$anonfun$sort$1 extends AbstractFunction2<ArrayBuffer<Tuple2<Class<? extends Throwable>, Directive>>, Tuple2<Class<? extends Throwable>, Directive>, ArrayBuffer<Tuple2<Class<? extends Throwable>, Directive>>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final ArrayBuffer<Tuple2<Class<? extends Throwable>, SupervisorStrategy.Directive>> apply(ArrayBuffer<Tuple2<Class<? extends Throwable>, SupervisorStrategy.Directive>> buf, Tuple2 ca) {
/* 230 */       int i = buf.indexWhere((Function1)new $anonfun$2(this, ca));
/* 230 */       switch (i) {
/*     */         default:
/* 232 */           (new Tuple2[1])[0] = ca;
/* 232 */           buf.insert(i, (Seq)scala.Predef$.MODULE$.wrapRefArray((Object[])new Tuple2[1]));
/* 234 */           return buf;
/*     */         case -1:
/*     */           break;
/*     */       } 
/*     */       (new Tuple2[1])[0] = ca;
/*     */       buf.append((Seq)scala.Predef$.MODULE$.wrapRefArray((Object[])new Tuple2[1]));
/* 234 */       return buf;
/*     */     }
/*     */     
/*     */     public class $anonfun$2 extends AbstractFunction1<Tuple2<Class<? extends Throwable>, SupervisorStrategy.Directive>, Object> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final Tuple2 ca$1;
/*     */       
/*     */       public final boolean apply(Tuple2 x$3) {
/*     */         return ((Class)x$3._1()).isAssignableFrom((Class)this.ca$1._1());
/*     */       }
/*     */       
/*     */       public $anonfun$2(SupervisorStrategy$$anonfun$sort$1 $outer, Tuple2 ca$1) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public static class $anonfun$3 extends AbstractFunction1<Object, Escalate$> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final SupervisorStrategy.Escalate$ apply(Object x$4) {
/* 243 */       return SupervisorStrategy.Escalate$.MODULE$;
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean handleFailure(ActorContext context, ActorRef child, Throwable cause, ChildRestartStats stats, Iterable children) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: invokevirtual decider : ()Lscala/PartialFunction;
/*     */     //   4: aload_3
/*     */     //   5: getstatic akka/actor/SupervisorStrategy$.MODULE$ : Lakka/actor/SupervisorStrategy$;
/*     */     //   8: invokevirtual escalateDefault : ()Lscala/Function1;
/*     */     //   11: invokeinterface applyOrElse : (Ljava/lang/Object;Lscala/Function1;)Ljava/lang/Object;
/*     */     //   16: checkcast akka/actor/SupervisorStrategy$Directive
/*     */     //   19: astore #6
/*     */     //   21: aload #6
/*     */     //   23: astore #7
/*     */     //   25: getstatic akka/actor/SupervisorStrategy$Resume$.MODULE$ : Lakka/actor/SupervisorStrategy$Resume$;
/*     */     //   28: aload #7
/*     */     //   30: astore #8
/*     */     //   32: dup
/*     */     //   33: ifnonnull -> 45
/*     */     //   36: pop
/*     */     //   37: aload #8
/*     */     //   39: ifnull -> 53
/*     */     //   42: goto -> 78
/*     */     //   45: aload #8
/*     */     //   47: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   50: ifeq -> 78
/*     */     //   53: aload #7
/*     */     //   55: astore #9
/*     */     //   57: aload_0
/*     */     //   58: aload_1
/*     */     //   59: aload_2
/*     */     //   60: aload_3
/*     */     //   61: aload #9
/*     */     //   63: invokevirtual logFailure : (Lakka/actor/ActorContext;Lakka/actor/ActorRef;Ljava/lang/Throwable;Lakka/actor/SupervisorStrategy$Directive;)V
/*     */     //   66: aload_0
/*     */     //   67: aload_2
/*     */     //   68: aload_3
/*     */     //   69: invokevirtual resumeChild : (Lakka/actor/ActorRef;Ljava/lang/Throwable;)V
/*     */     //   72: iconst_1
/*     */     //   73: istore #10
/*     */     //   75: goto -> 240
/*     */     //   78: getstatic akka/actor/SupervisorStrategy$Restart$.MODULE$ : Lakka/actor/SupervisorStrategy$Restart$;
/*     */     //   81: aload #7
/*     */     //   83: astore #11
/*     */     //   85: dup
/*     */     //   86: ifnonnull -> 98
/*     */     //   89: pop
/*     */     //   90: aload #11
/*     */     //   92: ifnull -> 106
/*     */     //   95: goto -> 137
/*     */     //   98: aload #11
/*     */     //   100: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   103: ifeq -> 137
/*     */     //   106: aload #7
/*     */     //   108: astore #12
/*     */     //   110: aload_0
/*     */     //   111: aload_1
/*     */     //   112: aload_2
/*     */     //   113: aload_3
/*     */     //   114: aload #12
/*     */     //   116: invokevirtual logFailure : (Lakka/actor/ActorContext;Lakka/actor/ActorRef;Ljava/lang/Throwable;Lakka/actor/SupervisorStrategy$Directive;)V
/*     */     //   119: aload_0
/*     */     //   120: aload_1
/*     */     //   121: iconst_1
/*     */     //   122: aload_2
/*     */     //   123: aload_3
/*     */     //   124: aload #4
/*     */     //   126: aload #5
/*     */     //   128: invokevirtual processFailure : (Lakka/actor/ActorContext;ZLakka/actor/ActorRef;Ljava/lang/Throwable;Lakka/actor/ChildRestartStats;Lscala/collection/Iterable;)V
/*     */     //   131: iconst_1
/*     */     //   132: istore #10
/*     */     //   134: goto -> 240
/*     */     //   137: getstatic akka/actor/SupervisorStrategy$Stop$.MODULE$ : Lakka/actor/SupervisorStrategy$Stop$;
/*     */     //   140: aload #7
/*     */     //   142: astore #13
/*     */     //   144: dup
/*     */     //   145: ifnonnull -> 157
/*     */     //   148: pop
/*     */     //   149: aload #13
/*     */     //   151: ifnull -> 165
/*     */     //   154: goto -> 196
/*     */     //   157: aload #13
/*     */     //   159: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   162: ifeq -> 196
/*     */     //   165: aload #7
/*     */     //   167: astore #14
/*     */     //   169: aload_0
/*     */     //   170: aload_1
/*     */     //   171: aload_2
/*     */     //   172: aload_3
/*     */     //   173: aload #14
/*     */     //   175: invokevirtual logFailure : (Lakka/actor/ActorContext;Lakka/actor/ActorRef;Ljava/lang/Throwable;Lakka/actor/SupervisorStrategy$Directive;)V
/*     */     //   178: aload_0
/*     */     //   179: aload_1
/*     */     //   180: iconst_0
/*     */     //   181: aload_2
/*     */     //   182: aload_3
/*     */     //   183: aload #4
/*     */     //   185: aload #5
/*     */     //   187: invokevirtual processFailure : (Lakka/actor/ActorContext;ZLakka/actor/ActorRef;Ljava/lang/Throwable;Lakka/actor/ChildRestartStats;Lscala/collection/Iterable;)V
/*     */     //   190: iconst_1
/*     */     //   191: istore #10
/*     */     //   193: goto -> 240
/*     */     //   196: getstatic akka/actor/SupervisorStrategy$Escalate$.MODULE$ : Lakka/actor/SupervisorStrategy$Escalate$;
/*     */     //   199: aload #7
/*     */     //   201: astore #15
/*     */     //   203: dup
/*     */     //   204: ifnonnull -> 216
/*     */     //   207: pop
/*     */     //   208: aload #15
/*     */     //   210: ifnull -> 224
/*     */     //   213: goto -> 243
/*     */     //   216: aload #15
/*     */     //   218: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   221: ifeq -> 243
/*     */     //   224: aload #7
/*     */     //   226: astore #16
/*     */     //   228: aload_0
/*     */     //   229: aload_1
/*     */     //   230: aload_2
/*     */     //   231: aload_3
/*     */     //   232: aload #16
/*     */     //   234: invokevirtual logFailure : (Lakka/actor/ActorContext;Lakka/actor/ActorRef;Ljava/lang/Throwable;Lakka/actor/SupervisorStrategy$Directive;)V
/*     */     //   237: iconst_0
/*     */     //   238: istore #10
/*     */     //   240: iload #10
/*     */     //   242: ireturn
/*     */     //   243: new scala/MatchError
/*     */     //   246: dup
/*     */     //   247: aload #7
/*     */     //   249: invokespecial <init> : (Ljava/lang/Object;)V
/*     */     //   252: athrow
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #295	-> 0
/*     */     //   #296	-> 21
/*     */     //   #297	-> 25
/*     */     //   #298	-> 57
/*     */     //   #299	-> 66
/*     */     //   #300	-> 72
/*     */     //   #297	-> 73
/*     */     //   #301	-> 78
/*     */     //   #302	-> 110
/*     */     //   #303	-> 119
/*     */     //   #304	-> 131
/*     */     //   #301	-> 132
/*     */     //   #305	-> 137
/*     */     //   #306	-> 169
/*     */     //   #307	-> 178
/*     */     //   #308	-> 190
/*     */     //   #305	-> 191
/*     */     //   #309	-> 196
/*     */     //   #310	-> 228
/*     */     //   #311	-> 237
/*     */     //   #309	-> 238
/*     */     //   #296	-> 240
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	253	0	this	Lakka/actor/SupervisorStrategy;
/*     */     //   0	253	1	context	Lakka/actor/ActorContext;
/*     */     //   0	253	2	child	Lakka/actor/ActorRef;
/*     */     //   0	253	3	cause	Ljava/lang/Throwable;
/*     */     //   0	253	4	stats	Lakka/actor/ChildRestartStats;
/*     */     //   0	253	5	children	Lscala/collection/Iterable;
/*     */     //   21	221	6	directive	Lakka/actor/SupervisorStrategy$Directive;
/*     */   }
/*     */   
/*     */   public boolean loggingEnabled() {
/* 318 */     return true;
/*     */   }
/*     */   
/*     */   public void logFailure(ActorContext context, ActorRef child, Throwable cause, Directive decision) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: invokevirtual loggingEnabled : ()Z
/*     */     //   4: ifeq -> 189
/*     */     //   7: aload_3
/*     */     //   8: astore #6
/*     */     //   10: aload #6
/*     */     //   12: instanceof akka/actor/ActorInitializationException
/*     */     //   15: ifeq -> 46
/*     */     //   18: aload #6
/*     */     //   20: checkcast akka/actor/ActorInitializationException
/*     */     //   23: astore #7
/*     */     //   25: aload #7
/*     */     //   27: invokevirtual getCause : ()Ljava/lang/Throwable;
/*     */     //   30: ifnull -> 46
/*     */     //   33: aload #7
/*     */     //   35: invokevirtual getCause : ()Ljava/lang/Throwable;
/*     */     //   38: invokevirtual getMessage : ()Ljava/lang/String;
/*     */     //   41: astore #8
/*     */     //   43: goto -> 53
/*     */     //   46: aload #6
/*     */     //   48: invokevirtual getMessage : ()Ljava/lang/String;
/*     */     //   51: astore #8
/*     */     //   53: aload #8
/*     */     //   55: astore #5
/*     */     //   57: aload #4
/*     */     //   59: astore #9
/*     */     //   61: getstatic akka/actor/SupervisorStrategy$Resume$.MODULE$ : Lakka/actor/SupervisorStrategy$Resume$;
/*     */     //   64: aload #9
/*     */     //   66: astore #10
/*     */     //   68: dup
/*     */     //   69: ifnonnull -> 81
/*     */     //   72: pop
/*     */     //   73: aload #10
/*     */     //   75: ifnull -> 89
/*     */     //   78: goto -> 122
/*     */     //   81: aload #10
/*     */     //   83: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   86: ifeq -> 122
/*     */     //   89: aload_0
/*     */     //   90: aload_1
/*     */     //   91: new akka/event/Logging$Warning
/*     */     //   94: dup
/*     */     //   95: aload_2
/*     */     //   96: invokevirtual path : ()Lakka/actor/ActorPath;
/*     */     //   99: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   102: aload_0
/*     */     //   103: invokevirtual getClass : ()Ljava/lang/Class;
/*     */     //   106: aload #5
/*     */     //   108: invokespecial <init> : (Ljava/lang/String;Ljava/lang/Class;Ljava/lang/Object;)V
/*     */     //   111: invokespecial publish : (Lakka/actor/ActorContext;Lakka/event/Logging$LogEvent;)V
/*     */     //   114: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */     //   117: astore #11
/*     */     //   119: goto -> 189
/*     */     //   122: getstatic akka/actor/SupervisorStrategy$Escalate$.MODULE$ : Lakka/actor/SupervisorStrategy$Escalate$;
/*     */     //   125: aload #9
/*     */     //   127: astore #12
/*     */     //   129: dup
/*     */     //   130: ifnonnull -> 142
/*     */     //   133: pop
/*     */     //   134: aload #12
/*     */     //   136: ifnull -> 150
/*     */     //   139: goto -> 158
/*     */     //   142: aload #12
/*     */     //   144: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   147: ifeq -> 158
/*     */     //   150: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */     //   153: astore #11
/*     */     //   155: goto -> 189
/*     */     //   158: aload_0
/*     */     //   159: aload_1
/*     */     //   160: new akka/event/Logging$Error
/*     */     //   163: dup
/*     */     //   164: aload_3
/*     */     //   165: aload_2
/*     */     //   166: invokevirtual path : ()Lakka/actor/ActorPath;
/*     */     //   169: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   172: aload_0
/*     */     //   173: invokevirtual getClass : ()Ljava/lang/Class;
/*     */     //   176: aload #5
/*     */     //   178: invokespecial <init> : (Ljava/lang/Throwable;Ljava/lang/String;Ljava/lang/Class;Ljava/lang/Object;)V
/*     */     //   181: invokespecial publish : (Lakka/actor/ActorContext;Lakka/event/Logging$LogEvent;)V
/*     */     //   184: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */     //   187: astore #11
/*     */     //   189: return
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #329	-> 0
/*     */     //   #330	-> 7
/*     */     //   #331	-> 10
/*     */     //   #332	-> 46
/*     */     //   #330	-> 53
/*     */     //   #334	-> 57
/*     */     //   #335	-> 61
/*     */     //   #336	-> 122
/*     */     //   #337	-> 158
/*     */     //   #329	-> 189
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	190	0	this	Lakka/actor/SupervisorStrategy;
/*     */     //   0	190	1	context	Lakka/actor/ActorContext;
/*     */     //   0	190	2	child	Lakka/actor/ActorRef;
/*     */     //   0	190	3	cause	Ljava/lang/Throwable;
/*     */     //   0	190	4	decision	Lakka/actor/SupervisorStrategy$Directive;
/*     */     //   57	133	5	logMessage	Ljava/lang/String;
/*     */   }
/*     */   
/*     */   private void publish(ActorContext context, Logging.LogEvent logEvent) {
/*     */     try {
/* 343 */       context.system().eventStream().publish(logEvent);
/*     */     } finally {
/*     */       BoxedUnit boxedUnit;
/* 343 */       Exception exception1 = null, exception2 = exception1;
/* 343 */       Option option = scala.util.control.NonFatal$.MODULE$.unapply(exception2);
/* 343 */       if (option.isEmpty())
/* 343 */         throw exception1; 
/*     */     } 
/*     */   }
/*     */   
/*     */   public final void resumeChild(ActorRef child, Throwable cause) {
/* 350 */     ((InternalActorRef)child).resume(cause);
/*     */   }
/*     */   
/*     */   public final void restartChild(ActorRef child, Throwable cause, boolean suspendFirst) {
/* 363 */     InternalActorRef c = (InternalActorRef)child;
/* 364 */     if (suspendFirst)
/* 364 */       c.suspend(); 
/* 365 */     c.restart(cause);
/*     */   }
/*     */   
/*     */   public static interface Directive {}
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\SupervisorStrategy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */