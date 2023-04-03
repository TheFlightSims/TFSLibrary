/*     */ package akka.routing;
/*     */ 
/*     */ import akka.actor.ActorContext;
/*     */ import akka.actor.Props;
/*     */ import akka.actor.SupervisorStrategy;
/*     */ import akka.actor.SupervisorStrategy$Escalate$;
/*     */ import scala.Function1;
/*     */ import scala.Option;
/*     */ import scala.Serializable;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractPartialFunction;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001E<Q!\001\002\t\002\035\tA\001U8pY*\0211\001B\001\be>,H/\0338h\025\005)\021\001B1lW\006\034\001\001\005\002\t\0235\t!AB\003\013\005!\0051B\001\003Q_>d7cA\005\r%A\021Q\002E\007\002\035)\tq\"A\003tG\006d\027-\003\002\022\035\t1\021I\\=SK\032\004\"!D\n\n\005Qq!\001D*fe&\fG.\033>bE2,\007\"\002\f\n\t\0039\022A\002\037j]&$h\bF\001\b\021\035I\022B1A\005\002i\t\021\004Z3gCVdGoU;qKJ4\030n]8s'R\024\030\r^3hsV\t1\004\005\002\035?5\tQD\003\002\037\t\005)\021m\031;pe&\021\001%\b\002\023'V\004XM\035<jg>\0248\013\036:bi\026<\027\020\003\004#\023\001\006IaG\001\033I\0264\027-\0367u'V\004XM\035<jg>\0248\013\036:bi\026<\027\020\t\005\bI%\t\t\021\"\003&\003-\021X-\0313SKN|GN^3\025\003\031\002\"a\n\027\016\003!R!!\013\026\002\t1\fgn\032\006\002W\005!!.\031<b\023\ti\003F\001\004PE*,7\r\036\004\b\025\t\001\n1!\0010'\rqC\002\r\t\003\021EJ!A\r\002\003\031I{W\017^3s\007>tg-[4\t\013QrC\021A\033\002\r\021Jg.\033;%)\0051\004CA\0078\023\tAdB\001\003V]&$\b\"\002\036/\r\003Y\024!\0048s\037\032Len\035;b]\016,7/F\001=!\tiQ(\003\002?\035\t\031\021J\034;\t\013\001sC\021A!\002#U\034X\rU8pY\022K7\017]1uG\",'/F\001C!\ti1)\003\002E\035\t9!i\\8mK\006t\007B\002$/\t\003!q)A\005oK^\024v.\036;fKR\031\001j\023)\021\005!I\025B\001&\003\005\031\021v.\036;fK\")A*\022a\001\033\006Y!o\\;uK\026\004&o\0349t!\tab*\003\002P;\t)\001K]8qg\")\021+\022a\001%\00691m\0348uKb$\bC\001\017T\023\t!VD\001\007BGR|'oQ8oi\026DH\017\003\004W]\021\005AaV\001\031K:\024\030n\0315XSRD\007k\\8m\t&\034\b/\031;dQ\026\024HcA'Y3\")A*\026a\001\033\")\021+\026a\001%\")1L\fD\0019\0069!/Z:ju\026\024X#A/\021\0075q\006-\003\002`\035\t1q\n\035;j_:\004\"\001C1\n\005\t\024!a\002*fg&TXM\035\005\006I:2\tAG\001\023gV\004XM\035<jg>\0248\013\036:bi\026<\027\020C\003g]\021\005q-A\003qe>\0048\017\006\002NQ\")A*\032a\001\033\")!N\fC!\003\006y2\017^8q%>,H/\032:XQ\026t\027\t\0347S_V$X-Z:SK6|g/\0323\t\r1tC\021\t\003n\003E\031'/Z1uKJ{W\017^3s\003\016$xN\035\013\002]B\021\001b\\\005\003a\n\0211BU8vi\026\024\030i\031;pe\002")
/*     */ public interface Pool extends RouterConfig {
/*     */   int nrOfInstances();
/*     */   
/*     */   boolean usePoolDispatcher();
/*     */   
/*     */   Routee newRoutee(Props paramProps, ActorContext paramActorContext);
/*     */   
/*     */   Props enrichWithPoolDispatcher(Props paramProps, ActorContext paramActorContext);
/*     */   
/*     */   Option<Resizer> resizer();
/*     */   
/*     */   SupervisorStrategy supervisorStrategy();
/*     */   
/*     */   Props props(Props paramProps);
/*     */   
/*     */   boolean stopRouterWhenAllRouteesRemoved();
/*     */   
/*     */   RouterActor createRouterActor();
/*     */   
/*     */   public static class $anonfun$1 extends AbstractPartialFunction<Throwable, SupervisorStrategy.Directive> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final <A1 extends Throwable, B1> B1 applyOrElse(Throwable x1, Function1 default) {
/* 164 */       Throwable throwable = x1;
/* 164 */       return 
/* 165 */         (B1)SupervisorStrategy$Escalate$.MODULE$;
/*     */     }
/*     */     
/*     */     public final boolean isDefinedAt(Throwable x1) {
/*     */       Throwable throwable = x1;
/*     */       return true;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\routing\Pool.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */