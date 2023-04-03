/*     */ package org.jfree.chart.plot;
/*     */ 
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import org.jfree.chart.renderer.RendererState;
/*     */ 
/*     */ public class PiePlotState extends RendererState {
/*     */   private int passesRequired;
/*     */   
/*     */   private double total;
/*     */   
/*     */   private double latestAngle;
/*     */   
/*     */   private Rectangle2D explodedPieArea;
/*     */   
/*     */   private Rectangle2D pieArea;
/*     */   
/*     */   private double pieCenterX;
/*     */   
/*     */   private double pieCenterY;
/*     */   
/*     */   private double pieHRadius;
/*     */   
/*     */   private double pieWRadius;
/*     */   
/*     */   private Rectangle2D linkArea;
/*     */   
/*     */   public PiePlotState(PlotRenderingInfo info) {
/*  91 */     super(info);
/*  92 */     this.passesRequired = 1;
/*  93 */     this.total = 0.0D;
/*     */   }
/*     */   
/*     */   public int getPassesRequired() {
/* 102 */     return this.passesRequired;
/*     */   }
/*     */   
/*     */   public void setPassesRequired(int passes) {
/* 111 */     this.passesRequired = passes;
/*     */   }
/*     */   
/*     */   public double getTotal() {
/* 120 */     return this.total;
/*     */   }
/*     */   
/*     */   public void setTotal(double total) {
/* 129 */     this.total = total;
/*     */   }
/*     */   
/*     */   public double getLatestAngle() {
/* 138 */     return this.latestAngle;
/*     */   }
/*     */   
/*     */   public void setLatestAngle(double angle) {
/* 147 */     this.latestAngle = angle;
/*     */   }
/*     */   
/*     */   public Rectangle2D getPieArea() {
/* 156 */     return this.pieArea;
/*     */   }
/*     */   
/*     */   public void setPieArea(Rectangle2D area) {
/* 165 */     this.pieArea = area;
/*     */   }
/*     */   
/*     */   public Rectangle2D getExplodedPieArea() {
/* 174 */     return this.explodedPieArea;
/*     */   }
/*     */   
/*     */   public void setExplodedPieArea(Rectangle2D area) {
/* 183 */     this.explodedPieArea = area;
/*     */   }
/*     */   
/*     */   public double getPieCenterX() {
/* 192 */     return this.pieCenterX;
/*     */   }
/*     */   
/*     */   public void setPieCenterX(double x) {
/* 201 */     this.pieCenterX = x;
/*     */   }
/*     */   
/*     */   public double getPieCenterY() {
/* 212 */     return this.pieCenterY;
/*     */   }
/*     */   
/*     */   public void setPieCenterY(double y) {
/* 222 */     this.pieCenterY = y;
/*     */   }
/*     */   
/*     */   public Rectangle2D getLinkArea() {
/* 232 */     return this.linkArea;
/*     */   }
/*     */   
/*     */   public void setLinkArea(Rectangle2D area) {
/* 242 */     this.linkArea = area;
/*     */   }
/*     */   
/*     */   public double getPieHRadius() {
/* 251 */     return this.pieHRadius;
/*     */   }
/*     */   
/*     */   public void setPieHRadius(double radius) {
/* 260 */     this.pieHRadius = radius;
/*     */   }
/*     */   
/*     */   public double getPieWRadius() {
/* 269 */     return this.pieWRadius;
/*     */   }
/*     */   
/*     */   public void setPieWRadius(double radius) {
/* 278 */     this.pieWRadius = radius;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\plot\PiePlotState.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */