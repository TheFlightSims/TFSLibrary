/*     */ package org.jfree.chart.plot;
/*     */ 
/*     */ import org.jfree.text.TextBox;
/*     */ 
/*     */ public class PieLabelRecord implements Comparable {
/*     */   private Comparable key;
/*     */   
/*     */   private double angle;
/*     */   
/*     */   private double baseY;
/*     */   
/*     */   private double allocatedY;
/*     */   
/*     */   private TextBox label;
/*     */   
/*     */   private double labelHeight;
/*     */   
/*     */   private double gap;
/*     */   
/*     */   private double linkPercent;
/*     */   
/*     */   public PieLabelRecord(Comparable key, double angle, double baseY, TextBox label, double labelHeight, double gap, double linkPercent) {
/*  91 */     this.key = key;
/*  92 */     this.angle = angle;
/*  93 */     this.baseY = baseY;
/*  94 */     this.allocatedY = baseY;
/*  95 */     this.label = label;
/*  96 */     this.labelHeight = labelHeight;
/*  97 */     this.gap = gap;
/*  98 */     this.linkPercent = linkPercent;
/*     */   }
/*     */   
/*     */   public double getBaseY() {
/* 108 */     return this.baseY;
/*     */   }
/*     */   
/*     */   public void setBaseY(double base) {
/* 117 */     this.baseY = base;
/*     */   }
/*     */   
/*     */   public double getLowerY() {
/* 126 */     return this.allocatedY - this.labelHeight / 2.0D;
/*     */   }
/*     */   
/*     */   public double getUpperY() {
/* 135 */     return this.allocatedY + this.labelHeight / 2.0D;
/*     */   }
/*     */   
/*     */   public double getAngle() {
/* 144 */     return this.angle;
/*     */   }
/*     */   
/*     */   public Comparable getKey() {
/* 153 */     return this.key;
/*     */   }
/*     */   
/*     */   public TextBox getLabel() {
/* 162 */     return this.label;
/*     */   }
/*     */   
/*     */   public double getLabelHeight() {
/* 171 */     return this.labelHeight;
/*     */   }
/*     */   
/*     */   public double getAllocatedY() {
/* 180 */     return this.allocatedY;
/*     */   }
/*     */   
/*     */   public void setAllocatedY(double y) {
/* 189 */     this.allocatedY = y;
/*     */   }
/*     */   
/*     */   public double getGap() {
/* 198 */     return this.gap;
/*     */   }
/*     */   
/*     */   public double getLinkPercent() {
/* 207 */     return this.linkPercent;
/*     */   }
/*     */   
/*     */   public int compareTo(Object obj) {
/* 217 */     int result = 0;
/* 218 */     if (obj instanceof PieLabelRecord) {
/* 219 */       PieLabelRecord plr = (PieLabelRecord)obj;
/* 220 */       if (this.baseY < plr.baseY) {
/* 221 */         result = -1;
/* 223 */       } else if (this.baseY > plr.baseY) {
/* 224 */         result = 1;
/*     */       } 
/*     */     } 
/* 227 */     return result;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 236 */     return this.baseY + ", " + this.key.toString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\plot\PieLabelRecord.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */