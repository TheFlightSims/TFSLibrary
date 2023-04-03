/*     */ package com.seisw.util.geom;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class PolySimple implements Poly {
/*  69 */   protected List<Point2D> m_List = new ArrayList<Point2D>();
/*     */   
/*     */   private boolean m_Contributes = true;
/*     */   
/*     */   public boolean equals(Object obj) {
/*  93 */     if (!(obj instanceof PolySimple))
/*  95 */       return false; 
/*  97 */     PolySimple that = (PolySimple)obj;
/*  99 */     int this_num = this.m_List.size();
/* 100 */     int that_num = that.m_List.size();
/* 101 */     if (this_num != that_num)
/* 101 */       return false; 
/* 107 */     if (this_num > 0) {
/* 109 */       double this_x = getX(0);
/* 110 */       double this_y = getY(0);
/* 111 */       int that_first_index = -1;
/*     */       int that_index;
/* 112 */       for (that_index = 0; that_first_index == -1 && that_index < that_num; that_index++) {
/* 114 */         double that_x = that.getX(that_index);
/* 115 */         double that_y = that.getY(that_index);
/* 116 */         if (this_x == that_x && this_y == that_y)
/* 118 */           that_first_index = that_index; 
/*     */       } 
/* 121 */       if (that_first_index == -1)
/* 121 */         return false; 
/* 122 */       that_index = that_first_index;
/* 123 */       for (int this_index = 0; this_index < this_num; this_index++) {
/* 125 */         this_x = getX(this_index);
/* 126 */         this_y = getY(this_index);
/* 127 */         double that_x = that.getX(that_index);
/* 128 */         double that_y = that.getY(that_index);
/* 130 */         if (this_x != that_x || this_y != that_y)
/* 130 */           return false; 
/* 132 */         that_index++;
/* 133 */         if (that_index >= that_num)
/* 135 */           that_index = 0; 
/*     */       } 
/*     */     } 
/* 139 */     return true;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 155 */     int result = 17;
/* 156 */     result = 37 * result + this.m_List.hashCode();
/* 157 */     return result;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 165 */     return "PolySimple: num_points=" + getNumPoints();
/*     */   }
/*     */   
/*     */   public void clear() {
/* 176 */     this.m_List.clear();
/*     */   }
/*     */   
/*     */   public void add(double x, double y) {
/* 184 */     add(new Point2D(x, y));
/*     */   }
/*     */   
/*     */   public void add(Point2D p) {
/* 192 */     this.m_List.add(p);
/*     */   }
/*     */   
/*     */   public void add(Poly p) {
/* 200 */     throw new IllegalStateException("Cannot add poly to a simple poly.");
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 208 */     return this.m_List.isEmpty();
/*     */   }
/*     */   
/*     */   public Rectangle2D getBounds() {
/* 216 */     double xmin = Double.MAX_VALUE;
/* 217 */     double ymin = Double.MAX_VALUE;
/* 218 */     double xmax = -1.7976931348623157E308D;
/* 219 */     double ymax = -1.7976931348623157E308D;
/* 221 */     for (int i = 0; i < this.m_List.size(); i++) {
/* 223 */       double x = getX(i);
/* 224 */       double y = getY(i);
/* 225 */       if (x < xmin)
/* 225 */         xmin = x; 
/* 226 */       if (x > xmax)
/* 226 */         xmax = x; 
/* 227 */       if (y < ymin)
/* 227 */         ymin = y; 
/* 228 */       if (y > ymax)
/* 228 */         ymax = y; 
/*     */     } 
/* 231 */     return new Rectangle2D(xmin, ymin, xmax - xmin, ymax - ymin);
/*     */   }
/*     */   
/*     */   public Poly getInnerPoly(int polyIndex) {
/* 240 */     if (polyIndex != 0)
/* 242 */       throw new IllegalStateException("PolySimple only has one poly"); 
/* 244 */     return this;
/*     */   }
/*     */   
/*     */   public int getNumInnerPoly() {
/* 252 */     return 1;
/*     */   }
/*     */   
/*     */   public int getNumPoints() {
/* 260 */     return this.m_List.size();
/*     */   }
/*     */   
/*     */   public double getX(int index) {
/* 268 */     return ((Point2D)this.m_List.get(index)).getX();
/*     */   }
/*     */   
/*     */   public double getY(int index) {
/* 276 */     return ((Point2D)this.m_List.get(index)).getY();
/*     */   }
/*     */   
/*     */   public boolean isHole() {
/* 284 */     return false;
/*     */   }
/*     */   
/*     */   public void setIsHole(boolean isHole) {
/* 292 */     throw new IllegalStateException("PolySimple cannot be a hole");
/*     */   }
/*     */   
/*     */   public boolean isContributing(int polyIndex) {
/* 303 */     if (polyIndex != 0)
/* 305 */       throw new IllegalStateException("PolySimple only has one poly"); 
/* 307 */     return this.m_Contributes;
/*     */   }
/*     */   
/*     */   public void setContributing(int polyIndex, boolean contributes) {
/* 318 */     if (polyIndex != 0)
/* 320 */       throw new IllegalStateException("PolySimple only has one poly"); 
/* 322 */     this.m_Contributes = contributes;
/*     */   }
/*     */   
/*     */   public Poly intersection(Poly p) {
/* 333 */     return Clip.intersection(this, p, (Class)getClass());
/*     */   }
/*     */   
/*     */   public Poly union(Poly p) {
/* 344 */     return Clip.union(this, p, (Class)getClass());
/*     */   }
/*     */   
/*     */   public Poly xor(Poly p) {
/* 355 */     return Clip.xor(p, this, (Class)getClass());
/*     */   }
/*     */   
/*     */   public Poly difference(Poly p) {
/* 366 */     return Clip.difference(this, p, (Class)getClass());
/*     */   }
/*     */   
/*     */   public double getArea() {
/* 377 */     if (getNumPoints() < 3)
/* 379 */       return 0.0D; 
/* 381 */     double ax = getX(0);
/* 382 */     double ay = getY(0);
/* 383 */     double area = 0.0D;
/* 384 */     for (int i = 1; i < getNumPoints() - 1; i++) {
/* 386 */       double bx = getX(i);
/* 387 */       double by = getY(i);
/* 388 */       double cx = getX(i + 1);
/* 389 */       double cy = getY(i + 1);
/* 390 */       double tarea = (cx - bx) * (ay - by) - (ax - bx) * (cy - by);
/* 391 */       area += tarea;
/*     */     } 
/* 393 */     area = 0.5D * Math.abs(area);
/* 394 */     return area;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\seis\\util\geom\PolySimple.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */