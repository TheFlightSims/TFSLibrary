/*    */ package com.typesafe.config.impl;
/*    */ 
/*    */ import com.typesafe.config.ConfigIncludeContext;
/*    */ import com.typesafe.config.ConfigParseOptions;
/*    */ import com.typesafe.config.ConfigParseable;
/*    */ 
/*    */ class SimpleIncludeContext implements ConfigIncludeContext {
/*    */   private final Parseable parseable;
/*    */   
/*    */   SimpleIncludeContext(Parseable parseable) {
/* 15 */     this.parseable = parseable;
/*    */   }
/*    */   
/*    */   SimpleIncludeContext withParseable(Parseable parseable) {
/* 19 */     if (parseable == this.parseable)
/* 20 */       return this; 
/* 22 */     return new SimpleIncludeContext(parseable);
/*    */   }
/*    */   
/*    */   public ConfigParseable relativeTo(String filename) {
/* 27 */     if (this.parseable != null)
/* 28 */       return this.parseable.relativeTo(filename); 
/* 30 */     return null;
/*    */   }
/*    */   
/*    */   public ConfigParseOptions parseOptions() {
/* 35 */     return SimpleIncluder.clearForInclude(this.parseable.options());
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\typesafe\config\impl\SimpleIncludeContext.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */