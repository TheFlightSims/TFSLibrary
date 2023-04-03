/*     */ package com.vividsolutions.jts.operation.buffer;
/*     */ 
/*     */ public class BufferParameters {
/*     */   public static final int CAP_ROUND = 1;
/*     */   
/*     */   public static final int CAP_FLAT = 2;
/*     */   
/*     */   public static final int CAP_SQUARE = 3;
/*     */   
/*     */   public static final int JOIN_ROUND = 1;
/*     */   
/*     */   public static final int JOIN_MITRE = 2;
/*     */   
/*     */   public static final int JOIN_BEVEL = 3;
/*     */   
/*     */   public static final int DEFAULT_QUADRANT_SEGMENTS = 8;
/*     */   
/*     */   public static final double DEFAULT_MITRE_LIMIT = 5.0D;
/*     */   
/*  94 */   private int quadrantSegments = 8;
/*     */   
/*  95 */   private int endCapStyle = 1;
/*     */   
/*  96 */   private int joinStyle = 1;
/*     */   
/*  97 */   private double mitreLimit = 5.0D;
/*     */   
/*     */   private boolean isSingleSided = false;
/*     */   
/*     */   public BufferParameters() {}
/*     */   
/*     */   public BufferParameters(int quadrantSegments) {
/* 115 */     setQuadrantSegments(quadrantSegments);
/*     */   }
/*     */   
/*     */   public BufferParameters(int quadrantSegments, int endCapStyle) {
/* 128 */     setQuadrantSegments(quadrantSegments);
/* 129 */     setEndCapStyle(endCapStyle);
/*     */   }
/*     */   
/*     */   public BufferParameters(int quadrantSegments, int endCapStyle, int joinStyle, double mitreLimit) {
/* 146 */     setQuadrantSegments(quadrantSegments);
/* 147 */     setEndCapStyle(endCapStyle);
/* 148 */     setJoinStyle(joinStyle);
/* 149 */     setMitreLimit(mitreLimit);
/*     */   }
/*     */   
/*     */   public int getQuadrantSegments() {
/* 159 */     return this.quadrantSegments;
/*     */   }
/*     */   
/*     */   public void setQuadrantSegments(int quadSegs) {
/* 187 */     this.quadrantSegments = quadSegs;
/* 199 */     if (this.quadrantSegments == 0)
/* 200 */       this.joinStyle = 3; 
/* 201 */     if (this.quadrantSegments < 0) {
/* 202 */       this.joinStyle = 2;
/* 203 */       this.mitreLimit = Math.abs(this.quadrantSegments);
/*     */     } 
/* 206 */     if (quadSegs <= 0)
/* 207 */       this.quadrantSegments = 1; 
/* 214 */     if (this.joinStyle != 1)
/* 215 */       this.quadrantSegments = 8; 
/*     */   }
/*     */   
/*     */   public static double bufferDistanceError(int quadSegs) {
/* 228 */     double alpha = 1.5707963267948966D / quadSegs;
/* 229 */     return 1.0D - Math.cos(alpha / 2.0D);
/*     */   }
/*     */   
/*     */   public int getEndCapStyle() {
/* 239 */     return this.endCapStyle;
/*     */   }
/*     */   
/*     */   public void setEndCapStyle(int endCapStyle) {
/* 251 */     this.endCapStyle = endCapStyle;
/*     */   }
/*     */   
/*     */   public int getJoinStyle() {
/* 261 */     return this.joinStyle;
/*     */   }
/*     */   
/*     */   public void setJoinStyle(int joinStyle) {
/* 273 */     this.joinStyle = joinStyle;
/*     */   }
/*     */   
/*     */   public double getMitreLimit() {
/* 283 */     return this.mitreLimit;
/*     */   }
/*     */   
/*     */   public void setMitreLimit(double mitreLimit) {
/* 301 */     this.mitreLimit = mitreLimit;
/*     */   }
/*     */   
/*     */   public void setSingleSided(boolean isSingleSided) {
/* 324 */     this.isSingleSided = isSingleSided;
/*     */   }
/*     */   
/*     */   public boolean isSingleSided() {
/* 333 */     return this.isSingleSided;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\operation\buffer\BufferParameters.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */