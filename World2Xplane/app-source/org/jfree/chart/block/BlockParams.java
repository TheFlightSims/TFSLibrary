/*     */ package org.jfree.chart.block;
/*     */ 
/*     */ public class BlockParams implements EntityBlockParams {
/*  75 */   private double translateX = 0.0D;
/*     */   
/*  76 */   private double translateY = 0.0D;
/*     */   
/*     */   private boolean generateEntities = false;
/*     */   
/*     */   public boolean getGenerateEntities() {
/*  87 */     return this.generateEntities;
/*     */   }
/*     */   
/*     */   public void setGenerateEntities(boolean generate) {
/*  96 */     this.generateEntities = generate;
/*     */   }
/*     */   
/*     */   public double getTranslateX() {
/* 106 */     return this.translateX;
/*     */   }
/*     */   
/*     */   public void setTranslateX(double x) {
/* 116 */     this.translateX = x;
/*     */   }
/*     */   
/*     */   public double getTranslateY() {
/* 126 */     return this.translateY;
/*     */   }
/*     */   
/*     */   public void setTranslateY(double y) {
/* 136 */     this.translateY = y;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\block\BlockParams.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */