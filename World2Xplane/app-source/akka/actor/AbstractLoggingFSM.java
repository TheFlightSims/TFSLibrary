/*      */ package akka.actor;
/*      */ 
/*      */ import scala.collection.IndexedSeq;
/*      */ import scala.reflect.ScalaSignature;
/*      */ import scala.runtime.TraitSetter;
/*      */ 
/*      */ @ScalaSignature(bytes = "\006\001\0252Q!\001\002\002\002\035\021!#\0212tiJ\f7\r\036'pO\036Lgn\032$T\033*\0211\001B\001\006C\016$xN\035\006\002\013\005!\021m[6b\007\001)2\001C\b\035'\r\001\021B\b\t\005\025-i1$D\001\003\023\ta!AA\006BEN$(/Y2u\rNk\005C\001\b\020\031\001!Q\001\005\001C\002E\021\021aU\t\003%a\001\"a\005\f\016\003QQ\021!F\001\006g\016\fG.Y\005\003/Q\021qAT8uQ&tw\r\005\002\0243%\021!\004\006\002\004\003:L\bC\001\b\035\t\025i\002A1\001\022\005\005!\005\003\002\006 \033mI!\001\t\002\003\0251{wmZ5oO\032\033V\nC\003#\001\021\0051%\001\004=S:LGO\020\013\002IA!!\002A\007\034\001")
/*      */ public abstract class AbstractLoggingFSM<S, D> extends AbstractFSM<S, D> implements LoggingFSM<S, D> {
/*      */   private final boolean debugEvent;
/*      */   
/*      */   private final FSM.Event<Object>[] akka$actor$LoggingFSM$$events;
/*      */   
/*      */   private final Object[] akka$actor$LoggingFSM$$states;
/*      */   
/*      */   private int akka$actor$LoggingFSM$$pos;
/*      */   
/*      */   private boolean akka$actor$LoggingFSM$$full;
/*      */   
/*      */   public boolean debugEvent() {
/* 1132 */     return this.debugEvent;
/*      */   }
/*      */   
/*      */   public FSM.Event<D>[] akka$actor$LoggingFSM$$events() {
/* 1132 */     return (FSM.Event[])this.akka$actor$LoggingFSM$$events;
/*      */   }
/*      */   
/*      */   public Object[] akka$actor$LoggingFSM$$states() {
/* 1132 */     return this.akka$actor$LoggingFSM$$states;
/*      */   }
/*      */   
/*      */   public int akka$actor$LoggingFSM$$pos() {
/* 1132 */     return this.akka$actor$LoggingFSM$$pos;
/*      */   }
/*      */   
/*      */   @TraitSetter
/*      */   public void akka$actor$LoggingFSM$$pos_$eq(int x$1) {
/* 1132 */     this.akka$actor$LoggingFSM$$pos = x$1;
/*      */   }
/*      */   
/*      */   public boolean akka$actor$LoggingFSM$$full() {
/* 1132 */     return this.akka$actor$LoggingFSM$$full;
/*      */   }
/*      */   
/*      */   @TraitSetter
/*      */   public void akka$actor$LoggingFSM$$full_$eq(boolean x$1) {
/* 1132 */     this.akka$actor$LoggingFSM$$full = x$1;
/*      */   }
/*      */   
/*      */   public void akka$actor$LoggingFSM$$super$processEvent(FSM.Event event, Object source) {
/* 1132 */     FSM$class.processEvent(this, event, source);
/*      */   }
/*      */   
/*      */   public void akka$actor$LoggingFSM$_setter_$debugEvent_$eq(boolean x$1) {
/* 1132 */     this.debugEvent = x$1;
/*      */   }
/*      */   
/*      */   public void akka$actor$LoggingFSM$_setter_$akka$actor$LoggingFSM$$events_$eq(FSM.Event[] x$1) {
/* 1132 */     this.akka$actor$LoggingFSM$$events = (FSM.Event<Object>[])x$1;
/*      */   }
/*      */   
/*      */   public void akka$actor$LoggingFSM$_setter_$akka$actor$LoggingFSM$$states_$eq(Object[] x$1) {
/* 1132 */     this.akka$actor$LoggingFSM$$states = x$1;
/*      */   }
/*      */   
/*      */   public int logDepth() {
/* 1132 */     return LoggingFSM$class.logDepth(this);
/*      */   }
/*      */   
/*      */   public void processEvent(FSM.Event event, Object source) {
/* 1132 */     LoggingFSM$class.processEvent(this, event, source);
/*      */   }
/*      */   
/*      */   public IndexedSeq<FSM.LogEntry<S, D>> getLog() {
/* 1132 */     return LoggingFSM$class.getLog(this);
/*      */   }
/*      */   
/*      */   public AbstractLoggingFSM() {
/* 1132 */     LoggingFSM$class.$init$(this);
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\AbstractLoggingFSM.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */