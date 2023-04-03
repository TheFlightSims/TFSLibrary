/*    */ package akka.actor;
/*    */ 
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001q1A!\001\002\003\017\tQ2\013^8qa&twmU;qKJ4\030n]8s'R\024\030\r^3hs*\0211\001B\001\006C\016$xN\035\006\002\013\005!\021m[6b\007\001\0312\001\001\005\017!\tIA\"D\001\013\025\005Y\021!B:dC2\f\027BA\007\013\005\031\te.\037*fMB\021q\002E\007\002\005%\021\021C\001\002\037'V\004XM\035<jg>\0248\013\036:bi\026<\027pQ8oM&<WO]1u_JDQa\005\001\005\002Q\ta\001P5oSRtD#A\013\021\005=\001\001\"B\f\001\t\003B\022AB2sK\006$X\rF\001\032!\ty!$\003\002\034\005\t\0212+\0369feZL7o\034:TiJ\fG/Z4z\001")
/*    */ public final class StoppingSupervisorStrategy implements SupervisorStrategyConfigurator {
/*    */   public SupervisorStrategy create() {
/* 85 */     return SupervisorStrategy$.MODULE$.stoppingStrategy();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\StoppingSupervisorStrategy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */