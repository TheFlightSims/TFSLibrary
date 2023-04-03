/*     */ package org.jfree.chart;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ 
/*     */ public class LegendItemCollection implements Cloneable, Serializable {
/*     */   private static final long serialVersionUID = 1365215565589815953L;
/*     */   
/*  68 */   private List items = new ArrayList();
/*     */   
/*     */   public void add(LegendItem item) {
/*  77 */     this.items.add(item);
/*     */   }
/*     */   
/*     */   public void addAll(LegendItemCollection collection) {
/*  86 */     this.items.addAll(collection.items);
/*     */   }
/*     */   
/*     */   public LegendItem get(int index) {
/*  97 */     return this.items.get(index);
/*     */   }
/*     */   
/*     */   public int getItemCount() {
/* 106 */     return this.items.size();
/*     */   }
/*     */   
/*     */   public Iterator iterator() {
/* 115 */     return this.items.iterator();
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 126 */     if (obj == this)
/* 127 */       return true; 
/* 129 */     if (!(obj instanceof LegendItemCollection))
/* 130 */       return false; 
/* 132 */     LegendItemCollection that = (LegendItemCollection)obj;
/* 133 */     if (!this.items.equals(that.items))
/* 134 */       return false; 
/* 136 */     return true;
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 148 */     return super.clone();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\LegendItemCollection.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */