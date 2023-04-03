/*     */ package com.typesafe.config;
/*     */ 
/*     */ public final class ConfigRenderOptions {
/*     */   private final boolean originComments;
/*     */   
/*     */   private final boolean comments;
/*     */   
/*     */   private final boolean formatted;
/*     */   
/*     */   private final boolean json;
/*     */   
/*     */   private ConfigRenderOptions(boolean originComments, boolean comments, boolean formatted, boolean json) {
/*  27 */     this.originComments = originComments;
/*  28 */     this.comments = comments;
/*  29 */     this.formatted = formatted;
/*  30 */     this.json = json;
/*     */   }
/*     */   
/*     */   public static ConfigRenderOptions defaults() {
/*  41 */     return new ConfigRenderOptions(true, true, true, true);
/*     */   }
/*     */   
/*     */   public static ConfigRenderOptions concise() {
/*  51 */     return new ConfigRenderOptions(false, false, false, true);
/*     */   }
/*     */   
/*     */   public ConfigRenderOptions setComments(boolean value) {
/*  64 */     if (value == this.comments)
/*  65 */       return this; 
/*  67 */     return new ConfigRenderOptions(this.originComments, value, this.formatted, this.json);
/*     */   }
/*     */   
/*     */   public boolean getComments() {
/*  77 */     return this.comments;
/*     */   }
/*     */   
/*     */   public ConfigRenderOptions setOriginComments(boolean value) {
/*  97 */     if (value == this.originComments)
/*  98 */       return this; 
/* 100 */     return new ConfigRenderOptions(value, this.comments, this.formatted, this.json);
/*     */   }
/*     */   
/*     */   public boolean getOriginComments() {
/* 110 */     return this.originComments;
/*     */   }
/*     */   
/*     */   public ConfigRenderOptions setFormatted(boolean value) {
/* 122 */     if (value == this.formatted)
/* 123 */       return this; 
/* 125 */     return new ConfigRenderOptions(this.originComments, this.comments, value, this.json);
/*     */   }
/*     */   
/*     */   public boolean getFormatted() {
/* 135 */     return this.formatted;
/*     */   }
/*     */   
/*     */   public ConfigRenderOptions setJson(boolean value) {
/* 150 */     if (value == this.json)
/* 151 */       return this; 
/* 153 */     return new ConfigRenderOptions(this.originComments, this.comments, this.formatted, value);
/*     */   }
/*     */   
/*     */   public boolean getJson() {
/* 163 */     return this.json;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 168 */     StringBuilder sb = new StringBuilder("ConfigRenderOptions(");
/* 169 */     if (this.originComments)
/* 170 */       sb.append("originComments,"); 
/* 171 */     if (this.comments)
/* 172 */       sb.append("comments,"); 
/* 173 */     if (this.formatted)
/* 174 */       sb.append("formatted,"); 
/* 175 */     if (this.json)
/* 176 */       sb.append("json,"); 
/* 177 */     if (sb.charAt(sb.length() - 1) == ',')
/* 178 */       sb.setLength(sb.length() - 1); 
/* 179 */     sb.append(")");
/* 180 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\typesafe\config\ConfigRenderOptions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */