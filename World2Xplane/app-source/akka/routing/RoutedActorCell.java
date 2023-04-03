/*     */ package akka.routing;
/*     */ 
/*     */ import akka.actor.Actor;
/*     */ import akka.actor.ActorCell;
/*     */ import akka.actor.ActorContext;
/*     */ import akka.actor.ActorRef;
/*     */ import akka.actor.ActorSystem;
/*     */ import akka.actor.ActorSystemImpl;
/*     */ import akka.actor.Cell;
/*     */ import akka.actor.IndirectActorProducer;
/*     */ import akka.actor.InternalActorRef;
/*     */ import akka.actor.Props;
/*     */ import akka.actor.dungeon.Dispatch;
/*     */ import akka.dispatch.Envelope;
/*     */ import akka.dispatch.MessageDispatcher;
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.Function2;
/*     */ import scala.Predef$;
/*     */ import scala.Serializable;
/*     */ import scala.collection.GenTraversableOnce;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.immutable.IndexedSeq;
/*     */ import scala.collection.immutable.IndexedSeq$;
/*     */ import scala.collection.immutable.Iterable;
/*     */ import scala.collection.immutable.List$;
/*     */ import scala.collection.package$;
/*     */ import scala.package$;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.AbstractFunction2;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005=uAB\001\003\021\003!a!A\bS_V$X\rZ!di>\0248)\0327m\025\t\031A!A\004s_V$\030N\\4\013\003\025\tA!Y6lCB\021q\001C\007\002\005\0311\021B\001E\001\t)\021qBU8vi\026$\027i\031;pe\016+G\016\\\n\003\021-\001\"\001D\b\016\0035Q\021AD\001\006g\016\fG.Y\005\003!5\021a!\0218z%\0264\007\"\002\n\t\t\003!\022A\002\037j]&$hh\001\001\025\003\0311AA\006\005\001/\t\021\"k\\;uKJ\f5\r^8s\007J,\027\r^8s'\r)2\002\007\t\0033qi\021A\007\006\0037\021\tQ!Y2u_JL!!\b\016\003+%sG-\033:fGR\f5\r^8s!J|G-^2fe\"Aq$\006B\001B\003%\001%\001\007s_V$XM]\"p]\032Lw\r\005\002\bC%\021!E\001\002\r%>,H/\032:D_:4\027n\032\005\006%U!\t\001\n\013\003K\035\002\"AJ\013\016\003!AQaH\022A\002\001BQ!K\013\005B)\n!\"Y2u_J\034E.Y:t+\005Y\003c\001\0272g5\tQF\003\002/_\005!A.\0318h\025\005\001\024\001\0026bm\006L!AM\027\003\013\rc\027m]:\021\005\035!\024BA\033\003\005-\021v.\036;fe\006\033Go\034:\t\013]*B\021\t\035\002\017A\024x\016Z;dKR\t1GB\003\n\005\001!!h\005\002:wA\021\021\004P\005\003{i\021\021\"Q2u_J\034U\r\0347\t\023}J$\021!Q\001\n\001\033\025aB0tsN$X-\034\t\0033\005K!A\021\016\003\037\005\033Go\034:TsN$X-\\%na2L!\001\022\037\002\rML8\017^3n\021%1\025H!A!\002\0239%*\001\003`e\0264\007CA\rI\023\tI%D\001\tJ]R,'O\\1m\003\016$xN\035*fM&\0211\nP\001\005g\026dg\rC\005Ns\t\005\t\025!\003O#\006aqL]8vi\026\024\bK]8qgB\021\021dT\005\003!j\021Q\001\025:paNL!A\025\037\002\013A\024x\016]:\t\023QK$\021!Q\001\nU[\026!E0s_V$XM\035#jgB\fGo\0315feB\021a+W\007\002/*\021\001\fB\001\tI&\034\b/\031;dQ&\021!l\026\002\022\033\026\0348/Y4f\t&\034\b/\031;dQ\026\024\030B\001/=\003)!\027n\0359bi\016DWM\035\005\t=f\022)\031!C\001?\006Y!o\\;uK\026\004&o\0349t+\005q\005\002C1:\005\003\005\013\021\002(\002\031I|W\017^3f!J|\007o\035\021\t\023\rL$\021!Q\001\n\035#\027aC0tkB,'O^5t_JL!!\032\037\002\rA\f'/\0328u\021\025\021\022\b\"\001h)\035A\027N[6m[:\004\"aB\035\t\013}2\007\031\001!\t\013\0313\007\031A$\t\01353\007\031\001(\t\013Q3\007\031A+\t\013y3\007\031\001(\t\013\r4\007\031A$\t\021}I$\031!C\001\tA,\022\001\t\005\007ef\002\013\021\002\021\002\033I|W\017^3s\007>tg-[4!\021\035!\030\b1A\005\nU\fqa\030:pkR,'/F\001w!\t9q/\003\002y\005\t1!k\\;uKJDqA_\035A\002\023%10A\006`e>,H/\032:`I\025\fHC\001?\000!\taQ0\003\002\033\t!QK\\5u\021!\t\t!_A\001\002\0041\030a\001=%c!9\021QA\035!B\0231\030\001C0s_V$XM\035\021)\t\005\r\021\021\002\t\004\031\005-\021bAA\007\033\tAao\0347bi&dW\r\003\004\002\022e\"\t!^\001\007e>,H/\032:\t\017\005U\021\b\"\001\002\030\005I\021\r\0323S_V$X-\032\013\004y\006e\001\002CA\016\003'\001\r!!\b\002\rI|W\017^3f!\r9\021qD\005\004\003C\021!A\002*pkR,W\rC\004\002&e\"\t!a\n\002\025\005$GMU8vi\026,7\017F\002}\003SA\001\"a\013\002$\001\007\021QF\001\be>,H/Z3t!\031\ty#!\017\002\0365\021\021\021\007\006\005\003g\t)$A\005j[6,H/\0312mK*\031\021qG\007\002\025\r|G\016\\3di&|g.\003\003\002<\005E\"\001C%uKJ\f'\r\\3\t\017\005}\022\b\"\001\002B\005a!/Z7pm\026\024v.\036;fKR)A0a\021\002F!A\0211DA\037\001\004\ti\002\003\005\002H\005u\002\031AA%\003%\031Ho\0349DQ&dG\rE\002\r\003\027J1!!\024\016\005\035\021un\0347fC:Dq!!\025:\t\003\t\031&A\007sK6|g/\032*pkR,Wm\035\013\006y\006U\023q\013\005\t\003W\ty\0051\001\002.!A\021qIA(\001\004\tI\005C\004\002\\e\"I!!\030\002\013]\fGo\0315\025\007q\fy\006\003\005\002\034\005e\003\031AA\017\021\035\t\031'\017C\005\003K\nq!\0368xCR\034\007\016F\002}\003OB\001\"a\007\002b\001\007\021Q\004\005\b\003WJD\021BA7\003-\031Ho\0349JM\016C\027\016\0343\025\007q\fy\007\003\005\002\034\005%\004\031AA\017\021\035\t\031(\017C!\003k\nQa\035;beR$\"!a\036\016\003eBq!a\037:\t#\ti(A\007qe\026\034V\017]3s'R\f'\017\036\013\002y\"9\021\021Q\035\005B\005\r\025aC:f]\022lUm]:bO\026$2\001`AC\021!\t9)a A\002\005%\025\001C3om\026dw\016]3\021\007Y\013Y)C\002\002\016^\023\001\"\0228wK2|\007/\032")
/*     */ public class RoutedActorCell extends ActorCell {
/*     */   private final Props routeeProps;
/*     */   
/*     */   private final RouterConfig routerConfig;
/*     */   
/*     */   private volatile Router _router;
/*     */   
/*     */   public static class RouterActorCreator implements IndirectActorProducer {
/*     */     private final RouterConfig routerConfig;
/*     */     
/*     */     public RouterActorCreator(RouterConfig routerConfig) {}
/*     */     
/*     */     public Class<RouterActor> actorClass() {
/*  32 */       return RouterActor.class;
/*     */     }
/*     */     
/*     */     public RouterActor produce() {
/*  33 */       return this.routerConfig.createRouterActor();
/*     */     }
/*     */   }
/*     */   
/*     */   public Props routeeProps() {
/*  46 */     return this.routeeProps;
/*     */   }
/*     */   
/*     */   public RoutedActorCell(ActorSystemImpl _system, InternalActorRef _ref, Props _routerProps, MessageDispatcher _routerDispatcher, Props routeeProps, InternalActorRef _supervisor) {
/*  48 */     super(_system, _ref, _routerProps, _routerDispatcher, _supervisor);
/*  50 */     this.routerConfig = props().routerConfig();
/*  53 */     null;
/*  53 */     this._router = null;
/*     */   }
/*     */   
/*     */   public RouterConfig routerConfig() {
/*     */     return this.routerConfig;
/*     */   }
/*     */   
/*     */   private Router _router() {
/*  53 */     return this._router;
/*     */   }
/*     */   
/*     */   private void _router_$eq(Router x$1) {
/*  53 */     this._router = x$1;
/*     */   }
/*     */   
/*     */   public Router router() {
/*  54 */     return _router();
/*     */   }
/*     */   
/*     */   public void addRoutee(Routee routee) {
/*  56 */     (new Routee[1])[0] = routee;
/*  56 */     addRoutees((Iterable<Routee>)List$.MODULE$.apply((Seq)Predef$.MODULE$.wrapRefArray((Object[])new Routee[1])));
/*     */   }
/*     */   
/*     */   public void addRoutees(Iterable routees) {
/*  63 */     routees.foreach((Function1)new RoutedActorCell$$anonfun$addRoutees$1(this));
/*  64 */     Router r = _router();
/*  65 */     _router_$eq(r.withRoutees((IndexedSeq<Routee>)r.routees().$plus$plus((GenTraversableOnce)routees, IndexedSeq$.MODULE$.canBuildFrom())));
/*     */   }
/*     */   
/*     */   public class RoutedActorCell$$anonfun$addRoutees$1 extends AbstractFunction1<Routee, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final void apply(Routee routee) {
/*     */       this.$outer.akka$routing$RoutedActorCell$$watch(routee);
/*     */     }
/*     */     
/*     */     public RoutedActorCell$$anonfun$addRoutees$1(RoutedActorCell $outer) {}
/*     */   }
/*     */   
/*     */   public void removeRoutee(Routee routee, boolean stopChild) {
/*  69 */     (new Routee[1])[0] = routee;
/*  69 */     removeRoutees((Iterable<Routee>)List$.MODULE$.apply((Seq)Predef$.MODULE$.wrapRefArray((Object[])new Routee[1])), stopChild);
/*     */   }
/*     */   
/*     */   public void removeRoutees(Iterable routees, boolean stopChild) {
/*  76 */     Router r = _router();
/*  77 */     IndexedSeq<Routee> newRoutees = (IndexedSeq)routees.foldLeft(r.routees(), (Function2)new RoutedActorCell$$anonfun$1(this));
/*  78 */     _router_$eq(r.withRoutees(newRoutees));
/*  79 */     if (stopChild)
/*  79 */       routees.foreach((Function1)new RoutedActorCell$$anonfun$removeRoutees$1(this)); 
/*     */   }
/*     */   
/*     */   public class RoutedActorCell$$anonfun$1 extends AbstractFunction2<IndexedSeq<Routee>, Routee, IndexedSeq<Routee>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final IndexedSeq<Routee> apply(IndexedSeq xs, Routee x) {
/*     */       this.$outer.akka$routing$RoutedActorCell$$unwatch(x);
/*     */       return (IndexedSeq<Routee>)xs.filterNot((Function1)new RoutedActorCell$$anonfun$1$$anonfun$apply$1(this, x));
/*     */     }
/*     */     
/*     */     public RoutedActorCell$$anonfun$1(RoutedActorCell $outer) {}
/*     */     
/*     */     public class RoutedActorCell$$anonfun$1$$anonfun$apply$1 extends AbstractFunction1<Routee, Object> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final Routee x$12;
/*     */       
/*     */       public final boolean apply(Routee x$1) {
/*     */         Routee routee = this.x$12;
/*     */         if (x$1 == null) {
/*     */           if (routee != null);
/*     */         } else if (x$1.equals(routee)) {
/*     */         
/*     */         } 
/*     */       }
/*     */       
/*     */       public RoutedActorCell$$anonfun$1$$anonfun$apply$1(RoutedActorCell$$anonfun$1 $outer, Routee x$12) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public class RoutedActorCell$$anonfun$removeRoutees$1 extends AbstractFunction1<Routee, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final void apply(Routee routee) {
/*  79 */       this.$outer.akka$routing$RoutedActorCell$$stopIfChild(routee);
/*     */     }
/*     */     
/*     */     public RoutedActorCell$$anonfun$removeRoutees$1(RoutedActorCell $outer) {}
/*     */   }
/*     */   
/*     */   public void akka$routing$RoutedActorCell$$watch(Routee routee) {
/*  82 */     Routee routee1 = routee;
/*  83 */     if (routee1 instanceof ActorRefRoutee) {
/*  83 */       ActorRefRoutee actorRefRoutee = (ActorRefRoutee)routee1;
/*  83 */       ActorRef ref = actorRefRoutee.ref();
/*  83 */       watch(ref);
/*  83 */       BoxedUnit boxedUnit = BoxedUnit.UNIT;
/*     */     } else {
/*  84 */       BoxedUnit boxedUnit = BoxedUnit.UNIT;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void akka$routing$RoutedActorCell$$unwatch(Routee routee) {
/*  87 */     Routee routee1 = routee;
/*  88 */     if (routee1 instanceof ActorRefRoutee) {
/*  88 */       ActorRefRoutee actorRefRoutee = (ActorRefRoutee)routee1;
/*  88 */       ActorRef ref = actorRefRoutee.ref();
/*  88 */       unwatch(ref);
/*  88 */       BoxedUnit boxedUnit = BoxedUnit.UNIT;
/*     */     } else {
/*  89 */       BoxedUnit boxedUnit = BoxedUnit.UNIT;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void akka$routing$RoutedActorCell$$stopIfChild(Routee routee) {
/*     */     // Byte code:
/*     */     //   0: aload_1
/*     */     //   1: astore_2
/*     */     //   2: aload_2
/*     */     //   3: instanceof akka/routing/ActorRefRoutee
/*     */     //   6: ifeq -> 187
/*     */     //   9: aload_2
/*     */     //   10: checkcast akka/routing/ActorRefRoutee
/*     */     //   13: astore_3
/*     */     //   14: aload_3
/*     */     //   15: invokevirtual ref : ()Lakka/actor/ActorRef;
/*     */     //   18: astore #4
/*     */     //   20: aload_0
/*     */     //   21: aload #4
/*     */     //   23: invokevirtual path : ()Lakka/actor/ActorPath;
/*     */     //   26: invokeinterface name : ()Ljava/lang/String;
/*     */     //   31: invokevirtual child : (Ljava/lang/String;)Lscala/Option;
/*     */     //   34: astore #6
/*     */     //   36: aload #6
/*     */     //   38: instanceof scala/Some
/*     */     //   41: ifeq -> 174
/*     */     //   44: aload #6
/*     */     //   46: checkcast scala/Some
/*     */     //   49: astore #7
/*     */     //   51: aload #7
/*     */     //   53: invokevirtual x : ()Ljava/lang/Object;
/*     */     //   56: checkcast akka/actor/ActorRef
/*     */     //   59: astore #8
/*     */     //   61: aload #4
/*     */     //   63: aload #8
/*     */     //   65: astore #9
/*     */     //   67: dup
/*     */     //   68: ifnonnull -> 80
/*     */     //   71: pop
/*     */     //   72: aload #9
/*     */     //   74: ifnull -> 88
/*     */     //   77: goto -> 174
/*     */     //   80: aload #9
/*     */     //   82: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   85: ifeq -> 174
/*     */     //   88: new scala/concurrent/duration/package$DurationInt
/*     */     //   91: dup
/*     */     //   92: getstatic scala/concurrent/duration/package$.MODULE$ : Lscala/concurrent/duration/package$;
/*     */     //   95: bipush #100
/*     */     //   97: invokevirtual DurationInt : (I)I
/*     */     //   100: invokespecial <init> : (I)V
/*     */     //   103: invokevirtual milliseconds : ()Lscala/concurrent/duration/FiniteDuration;
/*     */     //   106: astore #11
/*     */     //   108: aload #4
/*     */     //   110: astore #12
/*     */     //   112: getstatic akka/actor/PoisonPill$.MODULE$ : Lakka/actor/PoisonPill$;
/*     */     //   115: astore #13
/*     */     //   117: aload_0
/*     */     //   118: invokevirtual dispatcher : ()Lakka/dispatch/MessageDispatcher;
/*     */     //   121: astore #14
/*     */     //   123: aload_0
/*     */     //   124: invokevirtual system : ()Lakka/actor/ActorSystemImpl;
/*     */     //   127: invokevirtual scheduler : ()Lakka/actor/Scheduler;
/*     */     //   130: aload #11
/*     */     //   132: aload #12
/*     */     //   134: aload #13
/*     */     //   136: invokeinterface scheduleOnce$default$5 : (Lscala/concurrent/duration/FiniteDuration;Lakka/actor/ActorRef;Ljava/lang/Object;)Lakka/actor/ActorRef;
/*     */     //   141: astore #15
/*     */     //   143: aload_0
/*     */     //   144: invokevirtual system : ()Lakka/actor/ActorSystemImpl;
/*     */     //   147: invokevirtual scheduler : ()Lakka/actor/Scheduler;
/*     */     //   150: aload #11
/*     */     //   152: aload #12
/*     */     //   154: aload #13
/*     */     //   156: aload #14
/*     */     //   158: aload #15
/*     */     //   160: invokeinterface scheduleOnce : (Lscala/concurrent/duration/FiniteDuration;Lakka/actor/ActorRef;Ljava/lang/Object;Lscala/concurrent/ExecutionContext;Lakka/actor/ActorRef;)Lakka/actor/Cancellable;
/*     */     //   165: pop
/*     */     //   166: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */     //   169: astore #10
/*     */     //   171: goto -> 179
/*     */     //   174: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */     //   177: astore #10
/*     */     //   179: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */     //   182: astore #5
/*     */     //   184: goto -> 192
/*     */     //   187: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */     //   190: astore #5
/*     */     //   192: return
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #92	-> 0
/*     */     //   #93	-> 2
/*     */     //   #94	-> 36
/*     */     //   #98	-> 88
/*     */     //   #99	-> 174
/*     */     //   #93	-> 179
/*     */     //   #101	-> 187
/*     */     //   #92	-> 192
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	193	0	this	Lakka/routing/RoutedActorCell;
/*     */     //   0	193	1	routee	Lakka/routing/Routee;
/*     */     //   20	173	4	ref	Lakka/actor/ActorRef;
/*     */     //   108	58	11	x$4	Lscala/concurrent/duration/FiniteDuration;
/*     */     //   112	54	12	x$5	Lakka/actor/ActorRef;
/*     */     //   117	49	13	x$6	Lakka/actor/PoisonPill$;
/*     */     //   123	43	14	x$7	Lakka/dispatch/MessageDispatcher;
/*     */     //   143	23	15	x$8	Lakka/actor/ActorRef;
/*     */   }
/*     */   
/*     */   public RoutedActorCell start() {
/* 106 */     _router_$eq(routerConfig().createRouter((ActorSystem)system()));
/* 107 */     RouterConfig routerConfig = routerConfig();
/* 108 */     if (routerConfig instanceof DeprecatedRouterConfig) {
/* 108 */       DeprecatedRouterConfig deprecatedRouterConfig = (DeprecatedRouterConfig)routerConfig;
/* 109 */       if (deprecatedRouterConfig.nrOfInstances() > 0)
/* 110 */         addRoutees((Iterable<Routee>)package$.MODULE$.Vector().fill(deprecatedRouterConfig.nrOfInstances(), (Function0)new RoutedActorCell$$anonfun$start$1(this, deprecatedRouterConfig))); 
/* 111 */       Iterable<String> paths = deprecatedRouterConfig.paths();
/* 113 */       addRoutees((Iterable<Routee>)paths.map((Function1)new RoutedActorCell$$anonfun$start$2(this, deprecatedRouterConfig), package$.MODULE$.breakOut(Predef$.MODULE$.fallbackStringCanBuildFrom())));
/* 113 */       BoxedUnit boxedUnit = paths.nonEmpty() ? BoxedUnit.UNIT : BoxedUnit.UNIT;
/* 114 */     } else if (routerConfig instanceof Pool) {
/* 114 */       Pool pool = (Pool)routerConfig;
/* 116 */       addRoutees((Iterable<Routee>)package$.MODULE$.Vector().fill(pool.nrOfInstances(), (Function0)new RoutedActorCell$$anonfun$start$3(this, pool)));
/* 116 */       BoxedUnit boxedUnit = (pool.nrOfInstances() > 0) ? BoxedUnit.UNIT : BoxedUnit.UNIT;
/* 117 */     } else if (routerConfig instanceof Group) {
/* 117 */       Group group = (Group)routerConfig;
/* 118 */       Iterable<String> paths = group.paths();
/* 120 */       addRoutees((Iterable<Routee>)paths.map((Function1)new RoutedActorCell$$anonfun$start$4(this, group), package$.MODULE$.breakOut(Predef$.MODULE$.fallbackStringCanBuildFrom())));
/* 120 */       BoxedUnit boxedUnit = paths.nonEmpty() ? BoxedUnit.UNIT : BoxedUnit.UNIT;
/*     */     } else {
/* 121 */       BoxedUnit boxedUnit = BoxedUnit.UNIT;
/*     */     } 
/* 123 */     preSuperStart();
/* 124 */     return (RoutedActorCell)Dispatch.class.start(this);
/*     */   }
/*     */   
/*     */   public class RoutedActorCell$$anonfun$start$1 extends AbstractFunction0<Routee> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final DeprecatedRouterConfig x2$1;
/*     */     
/*     */     public final Routee apply() {
/*     */       return this.x2$1.newRoutee(this.$outer.routeeProps(), (ActorContext)this.$outer);
/*     */     }
/*     */     
/*     */     public RoutedActorCell$$anonfun$start$1(RoutedActorCell $outer, DeprecatedRouterConfig x2$1) {}
/*     */   }
/*     */   
/*     */   public class RoutedActorCell$$anonfun$start$2 extends AbstractFunction1<String, Routee> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final DeprecatedRouterConfig x2$1;
/*     */     
/*     */     public final Routee apply(String p) {
/*     */       return this.x2$1.routeeFor(p, (ActorContext)this.$outer);
/*     */     }
/*     */     
/*     */     public RoutedActorCell$$anonfun$start$2(RoutedActorCell $outer, DeprecatedRouterConfig x2$1) {}
/*     */   }
/*     */   
/*     */   public class RoutedActorCell$$anonfun$start$3 extends AbstractFunction0<Routee> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Pool x3$1;
/*     */     
/*     */     public final Routee apply() {
/*     */       return this.x3$1.newRoutee(this.$outer.routeeProps(), (ActorContext)this.$outer);
/*     */     }
/*     */     
/*     */     public RoutedActorCell$$anonfun$start$3(RoutedActorCell $outer, Pool x3$1) {}
/*     */   }
/*     */   
/*     */   public class RoutedActorCell$$anonfun$start$4 extends AbstractFunction1<String, Routee> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Group x4$1;
/*     */     
/*     */     public final Routee apply(String p) {
/*     */       return this.x4$1.routeeFor(p, (ActorContext)this.$outer);
/*     */     }
/*     */     
/*     */     public RoutedActorCell$$anonfun$start$4(RoutedActorCell $outer, Group x4$1) {}
/*     */   }
/*     */   
/*     */   public void preSuperStart() {}
/*     */   
/*     */   public void sendMessage(Envelope envelope) {
/* 144 */     if (routerConfig().isManagementMessage(envelope.message())) {
/* 145 */       Dispatch.class.sendMessage(this, envelope);
/*     */     } else {
/* 147 */       router().route(envelope.message(), envelope.sender());
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\routing\RoutedActorCell.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */