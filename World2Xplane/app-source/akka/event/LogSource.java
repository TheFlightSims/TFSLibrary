/*     */ package akka.event;
/*     */ 
/*     */ import akka.actor.Actor;
/*     */ import akka.actor.ActorRef;
/*     */ import akka.actor.ActorSystem;
/*     */ import scala.Option;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.util.control.NonFatal$;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005mdaB\001\003!\003\r\ta\002\002\n\031><7k\\;sG\026T!a\001\003\002\013\0254XM\034;\013\003\025\tA!Y6lC\016\001QC\001\005$'\t\001\021\002\005\002\013\0335\t1BC\001\r\003\025\0318-\0317b\023\tq1B\001\004B]f\024VM\032\005\006!\001!\t!E\001\007I%t\027\016\036\023\025\003I\001\"AC\n\n\005QY!\001B+oSRDQA\006\001\007\002]\t\021bZ3o'R\024\030N\\4\025\005ay\002CA\r\035\035\tQ!$\003\002\034\027\0051\001K]3eK\032L!!\b\020\003\rM#(/\0338h\025\tY2\002C\003!+\001\007\021%A\001u!\t\0213\005\004\001\005\r\021\002\001R1\001&\005\005!\026C\001\024*!\tQq%\003\002)\027\t9aj\034;iS:<\007C\001\006+\023\tY3BA\002B]fDQA\006\001\005\0025\"2\001\007\0300\021\025\001C\0061\001\"\021\025\001D\0061\0012\003\031\031\030p\035;f[B\021!'N\007\002g)\021A\007B\001\006C\016$xN]\005\003mM\0221\"Q2u_J\034\026p\035;f[\")\001\b\001C\001s\005Aq-\032;DY\006T(\020\006\002;\003B\0221h\020\t\0043qr\024BA\037\037\005\025\031E.Y:t!\t\021s\bB\005Ao\005\005\t\021!B\001K\t\031q\fJ\032\t\013\001:\004\031A\021)\007\001\031\025\n\005\002E\0176\tQI\003\002G\027\005Q\021M\0348pi\006$\030n\0348\n\005!+%\001E5na2L7-\033;O_R4u.\0368eC\005Q\025\001Z\"b]:|G\017\t4j]\022\004Cj\\4T_V\0248-\032\021g_J\004Ce\037+~AAdW-Y:fAM,W\rI*dC2\fGi\\2!M>\024\b\005T8h'>,(oY3!M>\024\b\005[8xAQ|\007e\0342uC&t\007e\034:!G>t7\017\036:vGR\004sN\\3/\017\025a%\001#\001N\003%aunZ*pkJ\034W\r\005\002O\0376\t!AB\003\002\005!\005\001k\005\002P\023!)!k\024C\001'\0061A(\0338jiz\"\022!\024\005\b+>\023\r\021b\001W\003)1'o\\7TiJLgnZ\013\002/B\031a\n\001\r\t\re{\005\025!\003X\003-1'o\\7TiJLgn\032\021\t\017m{%\031!C\0029\006IaM]8n\003\016$xN]\013\002;B\031a\n\0010\021\005Iz\026B\00114\005\025\t5\r^8s\021\031\021w\n)A\005;\006QaM]8n\003\016$xN\035\021\t\017\021|%\031!C\002K\006aaM]8n\003\016$xN\035*fMV\ta\rE\002O\001\035\004\"A\r5\n\005%\034$\001C!di>\024(+\0324\t\r-|\005\025!\003g\00351'o\\7BGR|'OU3gA!9Qn\024b\001\n\003q\027!\0034s_6\034E.Y:t+\005y\007c\001(\001aB\022\021o\035\t\0043q\022\bC\001\022t\t%!X/!A\001\002\013\005QEA\002`IQBaA^(!\002\0239\030A\0034s_6\034E.Y:tAA\031a\n\001=1\005e\\\bcA\r=uB\021!e\037\003\niV\f\t\021!A\003\002\025BQ!`(\005\004y\fAB\032:p[\006s\027p\0217bgN,2a`A\004+\t\t\t\001\005\003O\001\005\r\001\003B\r=\003\013\0012AIA\004\t\025!CP1\001&\021\035\tYa\024C\001\003\033\tQ!\0319qYf,B!a\004\002,Q!\021\021CA\027)\021\t\031\"a\t\021\r)\t)\002GA\r\023\r\t9b\003\002\007)V\004H.\032\0321\t\005m\021q\004\t\0053q\ni\002E\002#\003?!1\"!\t\002\n\005\005\t\021!B\001K\t!q\fJ\0311\021)\t)#!\003\002\002\003\017\021qE\001\013KZLG-\0328dK\022\n\004\003\002(\001\003S\0012AIA\026\t\031!\023\021\002b\001K!A\021qFA\005\001\004\tI#A\001p\021\035\tYa\024C\001\003g)B!!\016\002NQ1\021qGA(\003#\"B!!\017\002FA1!\"!\006\031\003w\001D!!\020\002BA!\021\004PA !\r\021\023\021\t\003\f\003\007\n\t$!A\001\002\013\005QE\001\003`IE\n\004BCA$\003c\t\t\021q\001\002J\005QQM^5eK:\034W\r\n\032\021\t9\003\0211\n\t\004E\0055CA\002\023\0022\t\007Q\005\003\005\0020\005E\002\031AA&\021\031\001\024\021\007a\001c!9\021QK(\005\002\005]\023A\0034s_6\fe.\037*fMR!\021\021LA3!\031Q\021Q\003\r\002\\A\"\021QLA1!\021IB(a\030\021\007\t\n\t\007B\006\002d\005M\023\021!A\001\006\003)#\001B0%cIBq!a\f\002T\001\007\021\002C\004\002V=#\t!!\033\025\r\005-\024qOA=!\031Q\021Q\003\r\002nA\"\021qNA:!\021IB(!\035\021\007\t\n\031\bB\006\002v\005\035\024\021!A\001\006\003)#\001B0%cMBq!a\f\002h\001\007\021\002\003\0041\003O\002\r!\r")
/*     */ public interface LogSource<T> {
/*     */   String genString(T paramT);
/*     */   
/*     */   String genString(T paramT, ActorSystem paramActorSystem);
/*     */   
/*     */   Class<?> getClazz(T paramT);
/*     */   
/*     */   public static class $anon$4 implements LogSource<String> {
/*     */     public $anon$4() {
/* 266 */       LogSource$class.$init$(this);
/*     */     }
/*     */     
/*     */     public String genString(String s) {
/* 267 */       return s;
/*     */     }
/*     */     
/*     */     public String genString(String s, ActorSystem system) {
/* 268 */       return (new StringBuilder()).append(s).append("(").append(system).append(")").toString();
/*     */     }
/*     */     
/*     */     public Class<DummyClassForStringSources> getClazz(String s) {
/* 269 */       return DummyClassForStringSources.class;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class $anon$5 implements LogSource<Actor> {
/*     */     public Class<?> getClazz(Object t) {
/* 272 */       return LogSource$class.getClazz(this, t);
/*     */     }
/*     */     
/*     */     public $anon$5() {
/* 272 */       LogSource$class.$init$(this);
/*     */     }
/*     */     
/*     */     public String genString(Actor a) {
/* 273 */       return LogSource$.MODULE$.fromActorRef().genString(a.self());
/*     */     }
/*     */     
/*     */     public String genString(Actor a, ActorSystem system) {
/* 274 */       return LogSource$.MODULE$.fromActorRef().genString(a.self(), system);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class $anon$6 implements LogSource<ActorRef> {
/*     */     public Class<?> getClazz(Object t) {
/* 277 */       return LogSource$class.getClazz(this, t);
/*     */     }
/*     */     
/*     */     public $anon$6() {
/* 277 */       LogSource$class.$init$(this);
/*     */     }
/*     */     
/*     */     public String genString(ActorRef a) {
/* 278 */       return a.path().toString();
/*     */     }
/*     */     
/*     */     public String genString(ActorRef a, ActorSystem system) {
/*     */       try {
/*     */       
/*     */       } finally {
/*     */         String str;
/* 279 */         Exception exception1 = null, exception2 = exception1;
/* 283 */         Option option = NonFatal$.MODULE$.unapply(exception2);
/* 283 */         if (option.isEmpty())
/*     */           throw exception1; 
/*     */       } 
/*     */       return str;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class $anon$7 implements LogSource<Class<?>> {
/*     */     public $anon$7() {
/* 288 */       LogSource$class.$init$(this);
/*     */     }
/*     */     
/*     */     public String genString(Class<?> c) {
/* 289 */       return Logging$.MODULE$.simpleName(c);
/*     */     }
/*     */     
/*     */     public String genString(Class<?> c, ActorSystem system) {
/* 290 */       return (new StringBuilder()).append(genString(c)).append("(").append(system).append(")").toString();
/*     */     }
/*     */     
/*     */     public Class<?> getClazz(Class<?> c) {
/* 291 */       return c;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\event\LogSource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */