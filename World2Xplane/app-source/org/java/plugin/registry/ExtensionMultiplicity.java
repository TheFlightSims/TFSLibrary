/*    */ package org.java.plugin.registry;
/*    */ 
/*    */ public enum ExtensionMultiplicity {
/* 28 */   ANY {
/*    */     public String toCode() {
/* 34 */       return "any";
/*    */     }
/*    */   },
/* 41 */   ONE {
/*    */     public String toCode() {
/* 47 */       return "one";
/*    */     }
/*    */   },
/* 54 */   ONE_PER_PLUGIN {
/*    */     public String toCode() {
/* 60 */       return "one-per-plugin";
/*    */     }
/*    */   },
/* 67 */   NONE {
/*    */     public String toCode() {
/* 73 */       return "none";
/*    */     }
/*    */   };
/*    */   
/*    */   public static ExtensionMultiplicity fromCode(String code) {
/* 89 */     for (ExtensionMultiplicity item : values()) {
/* 90 */       if (item.toCode().equals(code))
/* 91 */         return item; 
/*    */     } 
/* 94 */     throw new IllegalArgumentException("unknown extension multiplicity code " + code);
/*    */   }
/*    */   
/*    */   public abstract String toCode();
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\java\plugin\registry\ExtensionMultiplicity.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */