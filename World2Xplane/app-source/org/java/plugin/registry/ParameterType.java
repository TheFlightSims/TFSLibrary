/*     */ package org.java.plugin.registry;
/*     */ 
/*     */ public enum ParameterType {
/*  28 */   STRING {
/*     */     public String toCode() {
/*  34 */       return "string";
/*     */     }
/*     */   },
/*  41 */   BOOLEAN {
/*     */     public String toCode() {
/*  47 */       return "boolean";
/*     */     }
/*     */   },
/*  54 */   NUMBER {
/*     */     public String toCode() {
/*  60 */       return "number";
/*     */     }
/*     */   },
/*  67 */   DATE {
/*     */     public String toCode() {
/*  73 */       return "date";
/*     */     }
/*     */   },
/*  80 */   TIME {
/*     */     public String toCode() {
/*  86 */       return "time";
/*     */     }
/*     */   },
/*  93 */   DATE_TIME {
/*     */     public String toCode() {
/*  99 */       return "date-time";
/*     */     }
/*     */   },
/* 106 */   NULL {
/*     */     public String toCode() {
/* 112 */       return "null";
/*     */     }
/*     */   },
/* 119 */   ANY {
/*     */     public String toCode() {
/* 125 */       return "any";
/*     */     }
/*     */   },
/* 132 */   PLUGIN_ID {
/*     */     public String toCode() {
/* 138 */       return "plugin-id";
/*     */     }
/*     */   },
/* 145 */   EXTENSION_POINT_ID {
/*     */     public String toCode() {
/* 151 */       return "extension-point-id";
/*     */     }
/*     */   },
/* 158 */   EXTENSION_ID {
/*     */     public String toCode() {
/* 164 */       return "extension-id";
/*     */     }
/*     */   },
/* 171 */   FIXED {
/*     */     public String toCode() {
/* 177 */       return "fixed";
/*     */     }
/*     */   },
/* 184 */   RESOURCE {
/*     */     public String toCode() {
/* 190 */       return "resource";
/*     */     }
/*     */   };
/*     */   
/*     */   public static ParameterType fromCode(String code) {
/* 205 */     for (ParameterType item : values()) {
/* 206 */       if (item.toCode().equals(code))
/* 207 */         return item; 
/*     */     } 
/* 210 */     throw new IllegalArgumentException("unknown parameter type code " + code);
/*     */   }
/*     */   
/*     */   public abstract String toCode();
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\java\plugin\registry\ParameterType.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */