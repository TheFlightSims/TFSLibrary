/*    */ package org.geotools.factory;
/*    */ 
/*    */ public abstract class FactoryFinder {
/* 35 */   public static final Hints EMPTY_HINTS = new StrictHints.Empty();
/*    */   
/*    */   public static Hints mergeSystemHints(Hints hints) {
/* 55 */     if (hints instanceof StrictHints)
/* 63 */       return hints; 
/* 65 */     Hints merged = Hints.getDefaults(true);
/* 66 */     if (hints != null)
/* 67 */       merged.add(hints); 
/* 69 */     return merged;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\factory\FactoryFinder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */