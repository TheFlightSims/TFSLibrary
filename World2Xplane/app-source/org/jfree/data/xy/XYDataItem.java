/*     */ package org.jfree.data.xy;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.jfree.util.ObjectUtilities;
/*     */ 
/*     */ public class XYDataItem implements Cloneable, Comparable, Serializable {
/*     */   private static final long serialVersionUID = 2751513470325494890L;
/*     */   
/*     */   private Number x;
/*     */   
/*     */   private Number y;
/*     */   
/*     */   public XYDataItem(Number x, Number y) {
/*  71 */     if (x == null)
/*  72 */       throw new IllegalArgumentException("Null 'x' argument."); 
/*  74 */     this.x = x;
/*  75 */     this.y = y;
/*     */   }
/*     */   
/*     */   public XYDataItem(double x, double y) {
/*  85 */     this(new Double(x), new Double(y));
/*     */   }
/*     */   
/*     */   public Number getX() {
/*  94 */     return this.x;
/*     */   }
/*     */   
/*     */   public Number getY() {
/* 103 */     return this.y;
/*     */   }
/*     */   
/*     */   public void setY(double y) {
/* 113 */     setY(new Double(y));
/*     */   }
/*     */   
/*     */   public void setY(Number y) {
/* 123 */     this.y = y;
/*     */   }
/*     */   
/*     */   public int compareTo(Object o1) {
/*     */     int result;
/* 144 */     if (o1 instanceof XYDataItem) {
/* 145 */       XYDataItem dataItem = (XYDataItem)o1;
/* 146 */       double compare = this.x.doubleValue() - dataItem.getX().doubleValue();
/* 148 */       if (compare > 0.0D) {
/* 149 */         result = 1;
/* 152 */       } else if (compare < 0.0D) {
/* 153 */         result = -1;
/*     */       } else {
/* 156 */         result = 0;
/*     */       } 
/*     */     } else {
/* 165 */       result = 1;
/*     */     } 
/* 168 */     return result;
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 181 */     return super.clone();
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 193 */     if (obj == this)
/* 194 */       return true; 
/* 196 */     if (!(obj instanceof XYDataItem))
/* 197 */       return false; 
/* 199 */     XYDataItem that = (XYDataItem)obj;
/* 200 */     if (!this.x.equals(that.x))
/* 201 */       return false; 
/* 203 */     if (!ObjectUtilities.equal(this.y, that.y))
/* 204 */       return false; 
/* 206 */     return true;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 216 */     int result = this.x.hashCode();
/* 217 */     result = 29 * result + ((this.y != null) ? this.y.hashCode() : 0);
/* 218 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\data\xy\XYDataItem.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */