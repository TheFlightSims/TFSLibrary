/*    */ package ch.qos.logback.classic.turbo;
/*    */ 
/*    */ import ch.qos.logback.classic.Level;
/*    */ 
/*    */ public class MDCValueLevelPair {
/*    */   private String value;
/*    */   
/*    */   private Level level;
/*    */   
/*    */   public String getValue() {
/* 30 */     return this.value;
/*    */   }
/*    */   
/*    */   public void setValue(String name) {
/* 34 */     this.value = name;
/*    */   }
/*    */   
/*    */   public Level getLevel() {
/* 38 */     return this.level;
/*    */   }
/*    */   
/*    */   public void setLevel(Level level) {
/* 42 */     this.level = level;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\classic\turbo\MDCValueLevelPair.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */