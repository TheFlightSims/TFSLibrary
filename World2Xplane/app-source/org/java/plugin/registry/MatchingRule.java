/*    */ package org.java.plugin.registry;
/*    */ 
/*    */ public enum MatchingRule {
/* 28 */   EQUAL {
/*    */     public String toCode() {
/* 34 */       return "equal";
/*    */     }
/*    */   },
/* 41 */   EQUIVALENT {
/*    */     public String toCode() {
/* 47 */       return "equivalent";
/*    */     }
/*    */   },
/* 54 */   COMPATIBLE {
/*    */     public String toCode() {
/* 60 */       return "compatible";
/*    */     }
/*    */   },
/* 67 */   GREATER_OR_EQUAL {
/*    */     public String toCode() {
/* 73 */       return "greater-or-equal";
/*    */     }
/*    */   };
/*    */   
/*    */   public static MatchingRule fromCode(String code) {
/* 88 */     for (MatchingRule item : values()) {
/* 89 */       if (item.toCode().equals(code))
/* 90 */         return item; 
/*    */     } 
/* 93 */     throw new IllegalArgumentException("unknown matching rule code " + code);
/*    */   }
/*    */   
/*    */   public abstract String toCode();
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\java\plugin\registry\MatchingRule.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */