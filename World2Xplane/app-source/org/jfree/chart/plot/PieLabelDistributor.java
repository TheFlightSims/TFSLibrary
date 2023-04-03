/*     */ package org.jfree.chart.plot;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ 
/*     */ public class PieLabelDistributor {
/*     */   private List labels;
/*     */   
/*  60 */   private double minGap = 4.0D;
/*     */   
/*     */   public PieLabelDistributor(int labelCount) {
/*  68 */     this.labels = new ArrayList(labelCount);
/*     */   }
/*     */   
/*     */   public PieLabelRecord getPieLabelRecord(int index) {
/*  79 */     return this.labels.get(index);
/*     */   }
/*     */   
/*     */   public void addPieLabelRecord(PieLabelRecord record) {
/*  88 */     this.labels.add(record);
/*     */   }
/*     */   
/*     */   public int getItemCount() {
/*  97 */     return this.labels.size();
/*     */   }
/*     */   
/*     */   public void distributeLabels(double minY, double height) {
/* 107 */     sort();
/* 108 */     if (isOverlap())
/* 109 */       adjustInwards(); 
/* 113 */     if (isOverlap())
/* 114 */       adjustDownwards(minY, height); 
/* 117 */     if (isOverlap())
/* 118 */       adjustUpwards(minY, height); 
/* 121 */     if (isOverlap())
/* 122 */       spreadEvenly(minY, height); 
/*     */   }
/*     */   
/*     */   private boolean isOverlap() {
/* 134 */     double y = 0.0D;
/* 135 */     for (int i = 0; i < this.labels.size(); i++) {
/* 136 */       PieLabelRecord plr = getPieLabelRecord(i);
/* 137 */       if (y > plr.getLowerY())
/* 138 */         return true; 
/* 140 */       y = plr.getUpperY();
/*     */     } 
/* 142 */     return false;
/*     */   }
/*     */   
/*     */   protected void adjustInwards() {
/* 150 */     int lower = 0;
/* 151 */     int upper = this.labels.size() - 1;
/* 152 */     while (upper > lower) {
/* 153 */       if (lower < upper - 1) {
/* 154 */         PieLabelRecord r0 = getPieLabelRecord(lower);
/* 155 */         PieLabelRecord r1 = getPieLabelRecord(lower + 1);
/* 156 */         if (r1.getLowerY() < r0.getUpperY()) {
/* 157 */           double adjust = r0.getUpperY() - r1.getLowerY() + this.minGap;
/* 159 */           r1.setAllocatedY(r1.getAllocatedY() + adjust);
/*     */         } 
/*     */       } 
/* 162 */       PieLabelRecord r2 = getPieLabelRecord(upper - 1);
/* 163 */       PieLabelRecord r3 = getPieLabelRecord(upper);
/* 164 */       if (r2.getUpperY() > r3.getLowerY()) {
/* 165 */         double adjust = r2.getUpperY() - r3.getLowerY() + this.minGap;
/* 166 */         r2.setAllocatedY(r2.getAllocatedY() - adjust);
/*     */       } 
/* 168 */       lower++;
/* 169 */       upper--;
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void adjustDownwards(double minY, double height) {
/* 181 */     for (int i = 0; i < this.labels.size() - 1; i++) {
/* 182 */       PieLabelRecord record0 = getPieLabelRecord(i);
/* 183 */       PieLabelRecord record1 = getPieLabelRecord(i + 1);
/* 184 */       if (record1.getLowerY() < record0.getUpperY())
/* 185 */         record1.setAllocatedY(Math.min(minY + height, record0.getUpperY() + this.minGap + record1.getLabelHeight() / 2.0D)); 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void adjustUpwards(double minY, double height) {
/* 204 */     for (int i = this.labels.size() - 1; i > 0; i--) {
/* 205 */       PieLabelRecord record0 = getPieLabelRecord(i);
/* 206 */       PieLabelRecord record1 = getPieLabelRecord(i - 1);
/* 207 */       if (record1.getUpperY() > record0.getLowerY())
/* 208 */         record1.setAllocatedY(Math.max(minY, record0.getLowerY() - this.minGap - record1.getLabelHeight() / 2.0D)); 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void spreadEvenly(double minY, double height) {
/* 227 */     double y = minY;
/* 228 */     double sumOfLabelHeights = 0.0D;
/* 229 */     for (int i = 0; i < this.labels.size(); i++)
/* 230 */       sumOfLabelHeights += getPieLabelRecord(i).getLabelHeight(); 
/* 232 */     double gap = height - sumOfLabelHeights;
/* 233 */     if (this.labels.size() > 1)
/* 234 */       gap /= (this.labels.size() - 1); 
/* 236 */     for (int j = 0; j < this.labels.size(); j++) {
/* 237 */       PieLabelRecord record = getPieLabelRecord(j);
/* 238 */       y += record.getLabelHeight() / 2.0D;
/* 239 */       record.setAllocatedY(y);
/* 240 */       y = y + record.getLabelHeight() / 2.0D + gap;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void sort() {
/* 248 */     Collections.sort(this.labels);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 258 */     StringBuffer result = new StringBuffer();
/* 259 */     for (int i = 0; i < this.labels.size(); i++)
/* 260 */       result.append(getPieLabelRecord(i).toString()).append("\n"); 
/* 262 */     return result.toString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\plot\PieLabelDistributor.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */