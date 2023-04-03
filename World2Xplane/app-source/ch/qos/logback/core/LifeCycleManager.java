/*    */ package ch.qos.logback.core;
/*    */ 
/*    */ import ch.qos.logback.core.spi.LifeCycle;
/*    */ import java.util.HashSet;
/*    */ import java.util.Set;
/*    */ 
/*    */ public class LifeCycleManager {
/* 31 */   private final Set<LifeCycle> components = new HashSet<LifeCycle>();
/*    */   
/*    */   public void register(LifeCycle component) {
/* 39 */     this.components.add(component);
/*    */   }
/*    */   
/*    */   public void reset() {
/* 48 */     for (LifeCycle component : this.components) {
/* 49 */       if (component.isStarted())
/* 50 */         component.stop(); 
/*    */     } 
/* 53 */     this.components.clear();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\core\LifeCycleManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */