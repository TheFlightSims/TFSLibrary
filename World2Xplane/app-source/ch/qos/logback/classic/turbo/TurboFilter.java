/*    */ package ch.qos.logback.classic.turbo;
/*    */ 
/*    */ import ch.qos.logback.classic.Level;
/*    */ import ch.qos.logback.classic.Logger;
/*    */ import ch.qos.logback.core.spi.ContextAwareBase;
/*    */ import ch.qos.logback.core.spi.FilterReply;
/*    */ import ch.qos.logback.core.spi.LifeCycle;
/*    */ import org.slf4j.Marker;
/*    */ 
/*    */ public abstract class TurboFilter extends ContextAwareBase implements LifeCycle {
/*    */   private String name;
/*    */   
/*    */   boolean start = false;
/*    */   
/*    */   public abstract FilterReply decide(Marker paramMarker, Logger paramLogger, Level paramLevel, String paramString, Object[] paramArrayOfObject, Throwable paramThrowable);
/*    */   
/*    */   public void start() {
/* 57 */     this.start = true;
/*    */   }
/*    */   
/*    */   public boolean isStarted() {
/* 61 */     return this.start;
/*    */   }
/*    */   
/*    */   public void stop() {
/* 65 */     this.start = false;
/*    */   }
/*    */   
/*    */   public String getName() {
/* 70 */     return this.name;
/*    */   }
/*    */   
/*    */   public void setName(String name) {
/* 74 */     this.name = name;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\classic\turbo\TurboFilter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */