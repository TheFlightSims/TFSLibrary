/*     */ package org.jfree.chart.labels;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.jfree.ui.TextAnchor;
/*     */ 
/*     */ public class ItemLabelPosition implements Serializable {
/*     */   private static final long serialVersionUID = 5845390630157034499L;
/*     */   
/*     */   private ItemLabelAnchor itemLabelAnchor;
/*     */   
/*     */   private TextAnchor textAnchor;
/*     */   
/*     */   private TextAnchor rotationAnchor;
/*     */   
/*     */   private double angle;
/*     */   
/*     */   public ItemLabelPosition() {
/*  77 */     this(ItemLabelAnchor.OUTSIDE12, TextAnchor.BOTTOM_CENTER, TextAnchor.CENTER, 0.0D);
/*     */   }
/*     */   
/*     */   public ItemLabelPosition(ItemLabelAnchor itemLabelAnchor, TextAnchor textAnchor) {
/*  92 */     this(itemLabelAnchor, textAnchor, TextAnchor.CENTER, 0.0D);
/*     */   }
/*     */   
/*     */   public ItemLabelPosition(ItemLabelAnchor itemLabelAnchor, TextAnchor textAnchor, TextAnchor rotationAnchor, double angle) {
/* 113 */     if (itemLabelAnchor == null)
/* 114 */       throw new IllegalArgumentException("Null 'itemLabelAnchor' argument."); 
/* 118 */     if (textAnchor == null)
/* 119 */       throw new IllegalArgumentException("Null 'textAnchor' argument."); 
/* 121 */     if (rotationAnchor == null)
/* 122 */       throw new IllegalArgumentException("Null 'rotationAnchor' argument."); 
/* 127 */     this.itemLabelAnchor = itemLabelAnchor;
/* 128 */     this.textAnchor = textAnchor;
/* 129 */     this.rotationAnchor = rotationAnchor;
/* 130 */     this.angle = angle;
/*     */   }
/*     */   
/*     */   public ItemLabelAnchor getItemLabelAnchor() {
/* 140 */     return this.itemLabelAnchor;
/*     */   }
/*     */   
/*     */   public TextAnchor getTextAnchor() {
/* 149 */     return this.textAnchor;
/*     */   }
/*     */   
/*     */   public TextAnchor getRotationAnchor() {
/* 158 */     return this.rotationAnchor;
/*     */   }
/*     */   
/*     */   public double getAngle() {
/* 167 */     return this.angle;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 178 */     if (obj == this)
/* 179 */       return true; 
/* 181 */     if (!(obj instanceof ItemLabelPosition))
/* 182 */       return false; 
/* 184 */     ItemLabelPosition that = (ItemLabelPosition)obj;
/* 185 */     if (!this.itemLabelAnchor.equals(that.itemLabelAnchor))
/* 186 */       return false; 
/* 188 */     if (!this.textAnchor.equals(that.textAnchor))
/* 189 */       return false; 
/* 191 */     if (!this.rotationAnchor.equals(that.rotationAnchor))
/* 192 */       return false; 
/* 194 */     if (this.angle != that.angle)
/* 195 */       return false; 
/* 197 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\labels\ItemLabelPosition.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */