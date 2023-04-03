/*     */ package org.jfree.chart.axis;
/*     */ 
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.Serializable;
/*     */ import org.jfree.ui.RectangleEdge;
/*     */ import org.jfree.util.PublicCloneable;
/*     */ 
/*     */ public class AxisSpace implements Cloneable, PublicCloneable, Serializable {
/*     */   private static final long serialVersionUID = -2490732595134766305L;
/*     */   
/*  81 */   private double top = 0.0D;
/*     */   
/*  82 */   private double bottom = 0.0D;
/*     */   
/*  83 */   private double left = 0.0D;
/*     */   
/*  84 */   private double right = 0.0D;
/*     */   
/*     */   public double getTop() {
/*  93 */     return this.top;
/*     */   }
/*     */   
/*     */   public void setTop(double space) {
/* 102 */     this.top = space;
/*     */   }
/*     */   
/*     */   public double getBottom() {
/* 111 */     return this.bottom;
/*     */   }
/*     */   
/*     */   public void setBottom(double space) {
/* 120 */     this.bottom = space;
/*     */   }
/*     */   
/*     */   public double getLeft() {
/* 129 */     return this.left;
/*     */   }
/*     */   
/*     */   public void setLeft(double space) {
/* 138 */     this.left = space;
/*     */   }
/*     */   
/*     */   public double getRight() {
/* 147 */     return this.right;
/*     */   }
/*     */   
/*     */   public void setRight(double space) {
/* 156 */     this.right = space;
/*     */   }
/*     */   
/*     */   public void add(double space, RectangleEdge edge) {
/* 166 */     if (edge == null)
/* 167 */       throw new IllegalArgumentException("Null 'edge' argument."); 
/* 169 */     if (edge == RectangleEdge.TOP) {
/* 170 */       this.top += space;
/* 172 */     } else if (edge == RectangleEdge.BOTTOM) {
/* 173 */       this.bottom += space;
/* 175 */     } else if (edge == RectangleEdge.LEFT) {
/* 176 */       this.left += space;
/* 178 */     } else if (edge == RectangleEdge.RIGHT) {
/* 179 */       this.right += space;
/*     */     } else {
/* 182 */       throw new IllegalStateException("Unrecognised 'edge' argument.");
/*     */     } 
/*     */   }
/*     */   
/*     */   public void ensureAtLeast(AxisSpace space) {
/* 192 */     this.top = Math.max(this.top, space.top);
/* 193 */     this.bottom = Math.max(this.bottom, space.bottom);
/* 194 */     this.left = Math.max(this.left, space.left);
/* 195 */     this.right = Math.max(this.right, space.right);
/*     */   }
/*     */   
/*     */   public void ensureAtLeast(double space, RectangleEdge edge) {
/* 206 */     if (edge == RectangleEdge.TOP) {
/* 207 */       if (this.top < space)
/* 208 */         this.top = space; 
/* 211 */     } else if (edge == RectangleEdge.BOTTOM) {
/* 212 */       if (this.bottom < space)
/* 213 */         this.bottom = space; 
/* 216 */     } else if (edge == RectangleEdge.LEFT) {
/* 217 */       if (this.left < space)
/* 218 */         this.left = space; 
/* 221 */     } else if (edge == RectangleEdge.RIGHT) {
/* 222 */       if (this.right < space)
/* 223 */         this.right = space; 
/*     */     } else {
/* 227 */       throw new IllegalStateException("AxisSpace.ensureAtLeast(): unrecognised AxisLocation.");
/*     */     } 
/*     */   }
/*     */   
/*     */   public Rectangle2D shrink(Rectangle2D area, Rectangle2D result) {
/* 242 */     if (result == null)
/* 243 */       result = new Rectangle2D.Double(); 
/* 245 */     result.setRect(area.getX() + this.left, area.getY() + this.top, area.getWidth() - this.left - this.right, area.getHeight() - this.top - this.bottom);
/* 251 */     return result;
/*     */   }
/*     */   
/*     */   public Rectangle2D expand(Rectangle2D area, Rectangle2D result) {
/* 263 */     if (result == null)
/* 264 */       result = new Rectangle2D.Double(); 
/* 266 */     result.setRect(area.getX() - this.left, area.getY() - this.top, area.getWidth() + this.left + this.right, area.getHeight() + this.top + this.bottom);
/* 272 */     return result;
/*     */   }
/*     */   
/*     */   public Rectangle2D reserved(Rectangle2D area, RectangleEdge edge) {
/* 284 */     Rectangle2D result = null;
/* 285 */     if (edge == RectangleEdge.TOP) {
/* 286 */       result = new Rectangle2D.Double(area.getX(), area.getY(), area.getWidth(), this.top);
/* 290 */     } else if (edge == RectangleEdge.BOTTOM) {
/* 291 */       result = new Rectangle2D.Double(area.getX(), area.getMaxY() - this.top, area.getWidth(), this.bottom);
/* 296 */     } else if (edge == RectangleEdge.LEFT) {
/* 297 */       result = new Rectangle2D.Double(area.getX(), area.getY(), this.left, area.getHeight());
/* 301 */     } else if (edge == RectangleEdge.RIGHT) {
/* 302 */       result = new Rectangle2D.Double(area.getMaxX() - this.right, area.getY(), this.right, area.getHeight());
/*     */     } 
/* 307 */     return result;
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 319 */     return super.clone();
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 330 */     if (obj == this)
/* 331 */       return true; 
/* 333 */     if (!(obj instanceof AxisSpace))
/* 334 */       return false; 
/* 336 */     AxisSpace that = (AxisSpace)obj;
/* 337 */     if (this.top != that.top)
/* 338 */       return false; 
/* 340 */     if (this.bottom != that.bottom)
/* 341 */       return false; 
/* 343 */     if (this.left != that.left)
/* 344 */       return false; 
/* 346 */     if (this.right != that.right)
/* 347 */       return false; 
/* 349 */     return true;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 358 */     int result = 23;
/* 359 */     long l = Double.doubleToLongBits(this.top);
/* 360 */     result = 37 * result + (int)(l ^ l >>> 32L);
/* 361 */     l = Double.doubleToLongBits(this.bottom);
/* 362 */     result = 37 * result + (int)(l ^ l >>> 32L);
/* 363 */     l = Double.doubleToLongBits(this.left);
/* 364 */     result = 37 * result + (int)(l ^ l >>> 32L);
/* 365 */     l = Double.doubleToLongBits(this.right);
/* 366 */     result = 37 * result + (int)(l ^ l >>> 32L);
/* 367 */     return result;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 376 */     return super.toString() + "[left=" + this.left + ",right=" + this.right + ",top=" + this.top + ",bottom=" + this.bottom + "]";
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\axis\AxisSpace.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */