/*     */ package akka.event;
/*     */ 
/*     */ import akka.actor.ActorRef;
/*     */ import akka.actor.ScalaActorRef;
/*     */ import akka.actor.package$;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import scala.Serializable;
/*     */ import scala.collection.immutable.Iterable;
/*     */ import scala.collection.immutable.TreeSet;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxedUnit;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005Ua!C\001\003!\003\r\taBA\002\005M\t5\r^8s\0072\f7o]5gS\016\fG/[8o\025\t\031A!A\003fm\026tGOC\001\006\003\021\t7n[1\004\001M\021\001\001\003\t\003\0231i\021A\003\006\002\027\005)1oY1mC&\021QB\003\002\007\003:L(+\0324\t\013=\001A\021\001\t\002\r\021Jg.\033;%)\005\t\002CA\005\023\023\t\031\"B\001\003V]&$\bbB\013\001\005\004%IAF\001\006K6\004H/_\013\002/A\031\001$H\020\016\003eQ!AG\016\002\023%lW.\036;bE2,'B\001\017\013\003)\031w\016\0347fGRLwN\\\005\003=e\021q\001\026:fKN+G\017\005\002!G5\t\021E\003\002#\t\005)\021m\031;pe&\021A%\t\002\t\003\016$xN\035*fM\"1a\005\001Q\001\n]\ta!Z7qif\004\003b\002\025\001\005\004%I!K\001\t[\006\004\b/\0338hgV\t!\006\005\003,e}9R\"\001\027\013\0055r\023AC2p]\016,(O]3oi*\021q\006M\001\005kRLGNC\0012\003\021Q\027M^1\n\005Mb#!E\"p]\016,(O]3oi\"\0137\017['ba\"1Q\007\001Q\001\n)\n\021\"\\1qa&twm\035\021\t\013]\002AQ\003\035\002\023\005\0348o\\2jCR,GcA\035=}A\021\021BO\005\003w)\021qAQ8pY\026\fg\016C\003>m\001\007q$A\005n_:LGo\034:fI\")qH\016a\001?\0059Qn\0348ji>\024\bF\001\034B!\t\021U)D\001D\025\t!%\"\001\006b]:|G/\031;j_:L!AR\"\003\017Q\f\027\016\034:fG\")\001\n\001C\013\023\006QA-[:t_\016L\027\r^3\025\005)k\005c\001\rL?%\021A*\007\002\t\023R,'/\0312mK\")Qh\022a\001?!)\001\n\001C\013\037R\031\021\bU)\t\013ur\005\031A\020\t\013}r\005\031A\020)\0059\013\005\"\002+\001\r#)\026\001C2mCN\034\030NZ=\025\005Yc\006CA,Y\033\005\001\021BA-[\005)\031E.Y:tS\032LWM]\005\0037\n\021q\"Q2u_J\034E.Y:tS\032LWM\035\005\006\007M\003\r!\030\t\003/zK!a\0301\003\013\0253XM\034;\n\005\005\024!\001C#wK:$()^:\t\013\r\004a\021\0033\002\0175\f\007oU5{KV\tQ\r\005\002\nM&\021qM\003\002\004\023:$\b\"B5\001\t\003Q\027a\0029vE2L7\017\033\013\003#-DQa\0015A\002uCQ!\034\001\005\0029\f\021b];cg\016\024\030NY3\025\007ezg\017C\003qY\002\007\021/\001\006tk\n\0348M]5cKJ\004\"a\026:\n\005M$(AC*vEN\034'/\0332fe&\021QO\001\002\016\003\016$xN]#wK:$()^:\t\013]d\007\031\001,\002\005Q|\007\"B=\001\t\003Q\030aC;ogV\0247o\031:jE\026$2!O>}\021\025\001\b\0201\001r\021\025i\b\0201\001W\003\0211'o\\7\t\013e\004A\021A@\025\007E\t\t\001C\003q}\002\007\021O\005\004\002\006\005%\021Q\002\004\007\003\017\001\001!a\001\003\031q\022XMZ5oK6,g\016\036 \021\007\005-\001!D\001\003%\031\ty!!\005\002\024\0311\021q\001\001\001\003\033\0012!a\003u!\r\tYA\027")
/*     */ public interface ActorClassification {
/*     */   void akka$event$ActorClassification$_setter_$akka$event$ActorClassification$$empty_$eq(TreeSet paramTreeSet);
/*     */   
/*     */   void akka$event$ActorClassification$_setter_$akka$event$ActorClassification$$mappings_$eq(ConcurrentHashMap paramConcurrentHashMap);
/*     */   
/*     */   TreeSet<ActorRef> akka$event$ActorClassification$$empty();
/*     */   
/*     */   ConcurrentHashMap<ActorRef, TreeSet<ActorRef>> akka$event$ActorClassification$$mappings();
/*     */   
/*     */   boolean associate(ActorRef paramActorRef1, ActorRef paramActorRef2);
/*     */   
/*     */   Iterable<ActorRef> dissociate(ActorRef paramActorRef);
/*     */   
/*     */   boolean dissociate(ActorRef paramActorRef1, ActorRef paramActorRef2);
/*     */   
/*     */   ActorRef classify(Object paramObject);
/*     */   
/*     */   int mapSize();
/*     */   
/*     */   void publish(Object paramObject);
/*     */   
/*     */   boolean subscribe(ActorRef paramActorRef1, ActorRef paramActorRef2);
/*     */   
/*     */   boolean unsubscribe(ActorRef paramActorRef1, ActorRef paramActorRef2);
/*     */   
/*     */   void unsubscribe(ActorRef paramActorRef);
/*     */   
/*     */   public class ActorClassification$$anonfun$publish$2 extends AbstractFunction1<ActorRef, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Object event$2;
/*     */     
/*     */     public final void apply(ActorRef x$5) {
/* 336 */       ScalaActorRef qual$1 = package$.MODULE$.actorRef2Scala(x$5);
/* 336 */       Object x$6 = this.event$2;
/* 336 */       ActorRef x$7 = qual$1.$bang$default$2(x$6);
/* 336 */       qual$1.$bang(x$6, x$7);
/*     */     }
/*     */     
/*     */     public ActorClassification$$anonfun$publish$2(ActorClassification $outer, Object event$2) {}
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\event\ActorClassification.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */