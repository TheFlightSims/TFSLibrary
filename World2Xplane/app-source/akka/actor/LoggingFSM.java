/*     */ package akka.actor;
/*     */ 
/*     */ import scala.Serializable;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.IndexedSeq;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.TraitSetter;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005%a\001C\001\003!\003\r\taB?\003\0251{wmZ5oO\032\033VJ\003\002\004\t\005)\021m\031;pe*\tQ!\001\003bW.\f7\001A\013\004\021Uy2c\001\001\n\037A\021!\"D\007\002\027)\tA\"A\003tG\006d\027-\003\002\017\027\t1\021I\\=SK\032\004B\001E\t\024=5\t!!\003\002\023\005\t\031aiU'\021\005Q)B\002\001\003\006-\001\021\ra\006\002\002'F\021\001d\007\t\003\025eI!AG\006\003\0179{G\017[5oOB\021!\002H\005\003;-\0211!\0218z!\t!r\004B\003!\001\t\007qCA\001E\021\025\021\003\001\"\001$\003\031!\023N\\5uIQ\tA\005\005\002\013K%\021ae\003\002\005+:LG\017C\003)\001\021\005\021&\001\005m_\036$U\r\035;i+\005Q\003C\001\006,\023\ta3BA\002J]RD\001B\f\001C\002\023\005CaL\001\013I\026\024WoZ#wK:$X#\001\031\021\005)\t\024B\001\032\f\005\035\021un\0347fC:Da\001\016\001!\002\023\001\024a\0033fEV<WI^3oi\002BqA\016\001C\002\023%q'\001\004fm\026tGo]\013\002qA\031!\"O\036\n\005iZ!!B!se\006L\bC\001\037>\033\005\001\021B\001 \022\005\025)e/\0328u\021\031\001\005\001)A\005q\0059QM^3oiN\004\003b\002\"\001\005\004%IaQ\001\007gR\fG/Z:\026\003\021\0032AC\035\n\021\0311\005\001)A\005\t\00691\017^1uKN\004\003b\002%\001\001\004%I!K\001\004a>\034\bb\002&\001\001\004%IaS\001\ba>\034x\fJ3r)\t!C\nC\004N\023\006\005\t\031\001\026\002\007a$\023\007\003\004P\001\001\006KAK\001\005a>\034\b\005C\004R\001\001\007I\021B\030\002\t\031,H\016\034\005\b'\002\001\r\021\"\003U\003!1W\017\0347`I\025\fHC\001\023V\021\035i%+!AA\002ABaa\026\001!B\023\001\024!\0024vY2\004\003\"B-\001\t\023\031\023aB1em\006t7-\032\005\b7\002\001J\021\001\003]\0031\001(o\\2fgN,e/\0328u)\r!Sl\030\005\006=j\003\raO\001\006KZ,g\016\036\005\006Aj\003\r!C\001\007g>,(oY3\t\013\t\004A\021C2\002\r\035,G\017T8h+\005!\007cA3na:\021am\033\b\003O*l\021\001\033\006\003S\032\ta\001\020:p_Rt\024\"\001\007\n\0051\\\021a\0029bG.\fw-Z\005\003]>\024!\"\0238eKb,GmU3r\025\ta7\002\005\003riNqbB\001\ts\023\t\031(!A\002G'6K!!\036<\003\0211{w-\0228uefT!a\035\002\t\023a\004\021\021!A\005\ned\030AE:va\026\024H\005\035:pG\026\0348/\022<f]R$2\001\n>|\021\025qv\0171\001<\021\025\001w\0171\001\n\023\tY\026CE\003\003\003\t\031A\002\003\000\001\001i(\001\004\037sK\032Lg.Z7f]Rt\004\003\002\t\001'y\0012\001EA\003\023\r\t9A\001\002\006\003\016$xN\035")
/*     */ public interface LoggingFSM<S, D> extends FSM<S, D> {
/*     */   void akka$actor$LoggingFSM$_setter_$debugEvent_$eq(boolean paramBoolean);
/*     */   
/*     */   void akka$actor$LoggingFSM$_setter_$akka$actor$LoggingFSM$$events_$eq(FSM.Event[] paramArrayOfEvent);
/*     */   
/*     */   void akka$actor$LoggingFSM$_setter_$akka$actor$LoggingFSM$$states_$eq(Object[] paramArrayOfObject);
/*     */   
/*     */   void akka$actor$LoggingFSM$$super$processEvent(FSM.Event<D> paramEvent, Object paramObject);
/*     */   
/*     */   int logDepth();
/*     */   
/*     */   boolean debugEvent();
/*     */   
/*     */   FSM.Event<D>[] akka$actor$LoggingFSM$$events();
/*     */   
/*     */   Object[] akka$actor$LoggingFSM$$states();
/*     */   
/*     */   int akka$actor$LoggingFSM$$pos();
/*     */   
/*     */   @TraitSetter
/*     */   void akka$actor$LoggingFSM$$pos_$eq(int paramInt);
/*     */   
/*     */   boolean akka$actor$LoggingFSM$$full();
/*     */   
/*     */   @TraitSetter
/*     */   void akka$actor$LoggingFSM$$full_$eq(boolean paramBoolean);
/*     */   
/*     */   void processEvent(FSM.Event<D> paramEvent, Object paramObject);
/*     */   
/*     */   IndexedSeq<FSM.LogEntry<S, D>> getLog();
/*     */   
/*     */   public class LoggingFSM$$anonfun$2 extends AbstractFunction1<Tuple2<FSM.Event<D>, Object>, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final boolean apply(Tuple2 x$2) {
/* 747 */       return (x$2._1() != null);
/*     */     }
/*     */     
/*     */     public LoggingFSM$$anonfun$2(LoggingFSM $outer) {}
/*     */   }
/*     */   
/*     */   public class LoggingFSM$$anonfun$3 extends AbstractFunction1<Tuple2<FSM.Event<D>, Object>, FSM.LogEntry<S, D>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final FSM.LogEntry<S, D> apply(Tuple2 x) {
/* 747 */       return new FSM.LogEntry<S, D>((S)x._2(), ((FSM.Event<D>)x._1()).stateData(), ((FSM.Event)x._1()).event());
/*     */     }
/*     */     
/*     */     public LoggingFSM$$anonfun$3(LoggingFSM $outer) {}
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\LoggingFSM.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */