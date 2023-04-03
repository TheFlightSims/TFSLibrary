/*     */ package org.jfree.chart.axis;
/*     */ 
/*     */ import org.jfree.text.TextBlock;
/*     */ import org.jfree.text.TextBlockAnchor;
/*     */ import org.jfree.ui.TextAnchor;
/*     */ import org.jfree.util.ObjectUtilities;
/*     */ 
/*     */ public class CategoryTick extends Tick {
/*     */   private Comparable category;
/*     */   
/*     */   private TextBlock label;
/*     */   
/*     */   private TextBlockAnchor labelAnchor;
/*     */   
/*     */   public CategoryTick(Comparable category, TextBlock label, TextBlockAnchor labelAnchor, TextAnchor rotationAnchor, double angle) {
/*  80 */     super("", TextAnchor.CENTER, rotationAnchor, angle);
/*  81 */     this.category = category;
/*  82 */     this.label = label;
/*  83 */     this.labelAnchor = labelAnchor;
/*     */   }
/*     */   
/*     */   public Comparable getCategory() {
/*  93 */     return this.category;
/*     */   }
/*     */   
/*     */   public TextBlock getLabel() {
/* 102 */     return this.label;
/*     */   }
/*     */   
/*     */   public TextBlockAnchor getLabelAnchor() {
/* 111 */     return this.labelAnchor;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 122 */     if (this == obj)
/* 123 */       return true; 
/* 125 */     if (obj instanceof CategoryTick && super.equals(obj)) {
/* 126 */       CategoryTick that = (CategoryTick)obj;
/* 127 */       if (!ObjectUtilities.equal(this.category, that.category))
/* 128 */         return false; 
/* 130 */       if (!ObjectUtilities.equal(this.label, that.label))
/* 131 */         return false; 
/* 133 */       if (!ObjectUtilities.equal(this.labelAnchor, that.labelAnchor))
/* 134 */         return false; 
/* 136 */       return true;
/*     */     } 
/* 138 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 147 */     int result = 41;
/* 148 */     result = 37 * result + this.category.hashCode();
/* 149 */     result = 37 * result + this.label.hashCode();
/* 150 */     result = 37 * result + this.labelAnchor.hashCode();
/* 151 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\axis\CategoryTick.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */