/*    */ package org.java.plugin.registry;
/*    */ 
/*    */ public enum ParameterMultiplicity {
/* 28 */   ONE {
/*    */     public String toCode() {
/* 34 */       return "one";
/*    */     }
/*    */   },
/* 41 */   ANY {
/*    */     public String toCode() {
/* 47 */       return "any";
/*    */     }
/*    */   },
/* 54 */   NONE_OR_ONE {
/*    */     public String toCode() {
/* 60 */       return "none-or-one";
/*    */     }
/*    */   },
/* 67 */   ONE_OR_MORE {
/*    */     public String toCode() {
/* 73 */       return "one-or-more";
/*    */     }
/*    */   };
/*    */   
/*    */   public static ParameterMultiplicity fromCode(String code) {
/* 89 */     for (ParameterMultiplicity item : values()) {
/* 90 */       if (item.toCode().equals(code))
/* 91 */         return item; 
/*    */     } 
/* 94 */     throw new IllegalArgumentException("unknown parameter multiplicity code " + code);
/*    */   }
/*    */   
/*    */   public abstract String toCode();
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\java\plugin\registry\ParameterMultiplicity.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */