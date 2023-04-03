/*     */ package com.seisw.util.geom;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class PolyDefault implements Poly {
/*     */   private boolean m_IsHole = false;
/*     */   
/*  76 */   protected List<Poly> m_List = new ArrayList<Poly>();
/*     */   
/*     */   public PolyDefault() {
/*  84 */     this(false);
/*     */   }
/*     */   
/*     */   public PolyDefault(boolean isHole) {
/*  89 */     this.m_IsHole = isHole;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 100 */     if (!(obj instanceof PolyDefault))
/* 102 */       return false; 
/* 104 */     PolyDefault that = (PolyDefault)obj;
/* 106 */     if (this.m_IsHole != that.m_IsHole)
/* 106 */       return false; 
/* 107 */     if (!this.m_List.equals(that.m_List))
/* 107 */       return false; 
/* 109 */     return true;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 120 */     int result = 17;
/* 121 */     result = 37 * result + this.m_List.hashCode();
/* 122 */     return result;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 130 */     return super.toString();
/*     */   }
/*     */   
/*     */   public void clear() {
/* 141 */     this.m_List.clear();
/*     */   }
/*     */   
/*     */   public void add(double x, double y) {
/* 152 */     add(new Point2D(x, y));
/*     */   }
/*     */   
/*     */   public void add(Point2D p) {
/* 163 */     if (this.m_List.size() == 0)
/* 165 */       this.m_List.add(new PolySimple()); 
/* 167 */     ((Poly)this.m_List.get(0)).add(p);
/*     */   }
/*     */   
/*     */   public void add(Poly p) {
/* 180 */     if (this.m_List.size() > 0 && this.m_IsHole)
/* 182 */       throw new IllegalStateException("Cannot add polys to something designated as a hole."); 
/* 184 */     this.m_List.add(p);
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 192 */     return this.m_List.isEmpty();
/*     */   }
/*     */   
/*     */   public Rectangle2D getBounds() {
/* 201 */     if (this.m_List.size() == 0)
/* 203 */       return new Rectangle2D(); 
/* 205 */     if (this.m_List.size() == 1) {
/* 207 */       Poly ip = getInnerPoly(0);
/* 208 */       return ip.getBounds();
/*     */     } 
/* 212 */     throw new UnsupportedOperationException("getBounds not supported on complex poly.");
/*     */   }
/*     */   
/*     */   public Poly getInnerPoly(int polyIndex) {
/* 221 */     return this.m_List.get(polyIndex);
/*     */   }
/*     */   
/*     */   public int getNumInnerPoly() {
/* 229 */     return this.m_List.size();
/*     */   }
/*     */   
/*     */   public int getNumPoints() {
/* 237 */     return ((Poly)this.m_List.get(0)).getNumPoints();
/*     */   }
/*     */   
/*     */   public double getX(int index) {
/* 245 */     return ((Poly)this.m_List.get(0)).getX(index);
/*     */   }
/*     */   
/*     */   public double getY(int index) {
/* 253 */     return ((Poly)this.m_List.get(0)).getY(index);
/*     */   }
/*     */   
/*     */   public boolean isHole() {
/* 264 */     if (this.m_List.size() > 1)
/* 266 */       throw new IllegalStateException("Cannot call on a poly made up of more than one poly."); 
/* 268 */     return this.m_IsHole;
/*     */   }
/*     */   
/*     */   public void setIsHole(boolean isHole) {
/* 278 */     if (this.m_List.size() > 1)
/* 280 */       throw new IllegalStateException("Cannot call on a poly made up of more than one poly."); 
/* 282 */     this.m_IsHole = isHole;
/*     */   }
/*     */   
/*     */   public boolean isContributing(int polyIndex) {
/* 291 */     return ((Poly)this.m_List.get(polyIndex)).isContributing(0);
/*     */   }
/*     */   
/*     */   public void setContributing(int polyIndex, boolean contributes) {
/* 302 */     if (this.m_List.size() != 1)
/* 304 */       throw new IllegalStateException("Only applies to polys of size 1"); 
/* 306 */     ((Poly)this.m_List.get(polyIndex)).setContributing(0, contributes);
/*     */   }
/*     */   
/*     */   public Poly intersection(Poly p) {
/* 317 */     return Clip.intersection(p, this, (Class)getClass());
/*     */   }
/*     */   
/*     */   public Poly union(Poly p) {
/* 328 */     return Clip.union(p, this, (Class)getClass());
/*     */   }
/*     */   
/*     */   public Poly xor(Poly p) {
/* 339 */     return Clip.xor(p, this, (Class)getClass());
/*     */   }
/*     */   
/*     */   public Poly difference(Poly p) {
/* 350 */     return Clip.difference(this, p, (Class)getClass());
/*     */   }
/*     */   
/*     */   public double getArea() {
/* 358 */     double area = 0.0D;
/* 359 */     for (int i = 0; i < getNumInnerPoly(); i++) {
/* 361 */       Poly p = getInnerPoly(i);
/* 362 */       double tarea = p.getArea() * (p.isHole() ? -1.0D : 1.0D);
/* 363 */       area += tarea;
/*     */     } 
/* 365 */     return area;
/*     */   }
/*     */   
/*     */   void print() {
/* 373 */     for (int i = 0; i < this.m_List.size(); i++) {
/* 375 */       Poly p = getInnerPoly(i);
/* 376 */       System.out.println("InnerPoly(" + i + ").hole=" + p.isHole());
/* 377 */       for (int j = 0; j < p.getNumPoints(); j++)
/* 379 */         System.out.println(String.valueOf(p.getX(j)) + "  " + p.getY(j)); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\seis\\util\geom\PolyDefault.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */