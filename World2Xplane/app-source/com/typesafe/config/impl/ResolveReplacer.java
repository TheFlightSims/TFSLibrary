/*    */ package com.typesafe.config.impl;
/*    */ 
/*    */ abstract class ResolveReplacer {
/* 12 */   private volatile AbstractConfigValue replacement = null;
/*    */   
/*    */   final AbstractConfigValue replace(ResolveContext context) throws AbstractConfigValue.NotPossibleToResolve {
/* 15 */     if (this.replacement == null)
/* 16 */       this.replacement = makeReplacement(context); 
/* 17 */     return this.replacement;
/*    */   }
/*    */   
/* 23 */   static final ResolveReplacer cycleResolveReplacer = new ResolveReplacer() {
/*    */       protected AbstractConfigValue makeReplacement(ResolveContext context) throws AbstractConfigValue.NotPossibleToResolve {
/* 27 */         throw new AbstractConfigValue.NotPossibleToResolve(context);
/*    */       }
/*    */     };
/*    */   
/*    */   protected abstract AbstractConfigValue makeReplacement(ResolveContext paramResolveContext) throws AbstractConfigValue.NotPossibleToResolve;
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\typesafe\config\impl\ResolveReplacer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */