/*     */ package com.world2xplane.Geom.Geom3D;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ 
/*     */ public class Triangle {
/*     */   public Vector2D a;
/*     */   
/*     */   public Vector2D b;
/*     */   
/*     */   public Vector2D c;
/*     */   
/*     */   public Triangle(Vector2D a, Vector2D b, Vector2D c) {
/*  26 */     this.a = a;
/*  27 */     this.b = b;
/*  28 */     this.c = c;
/*     */   }
/*     */   
/*     */   private Vector2D computeClosestPoint(Vector2D a, Vector2D b, Vector2D c) {
/*  32 */     Vector2D ab = b.sub(a);
/*  33 */     double t = c.sub(a).dot(ab) / ab.dot(ab);
/*  35 */     if (t < 0.0D)
/*  35 */       t = 0.0D; 
/*  36 */     if (t > 1.0D)
/*  36 */       t = 1.0D; 
/*  38 */     return a.add(ab.mult(t));
/*     */   }
/*     */   
/*     */   public boolean contains(Vector2D p) {
/*  50 */     double pab = p.sub(this.a).cross(this.b.sub(this.a));
/*  51 */     double pbc = p.sub(this.b).cross(this.c.sub(this.b));
/*  53 */     if (!hasSameSign(pab, pbc))
/*  54 */       return false; 
/*  56 */     double pca = p.sub(this.c).cross(this.a.sub(this.c));
/*  58 */     if (!hasSameSign(pab, pca))
/*  59 */       return false; 
/*  61 */     return true;
/*     */   }
/*     */   
/*     */   private boolean hasSameSign(double a, double b) {
/*  65 */     return (Math.signum(a) == Math.signum(b));
/*     */   }
/*     */   
/*     */   public boolean pointInCircumcircle(Vector2D d) {
/*  81 */     double a11 = this.a.x - d.x;
/*  82 */     double a21 = this.b.x - d.x;
/*  83 */     double a31 = this.c.x - d.x;
/*  85 */     double a12 = this.a.y - d.y;
/*  86 */     double a22 = this.b.y - d.y;
/*  87 */     double a32 = this.c.y - d.y;
/*  89 */     double a13 = (this.a.x - d.x) * (this.a.x - d.x) + (this.a.y - d.y) * (this.a.y - d.y);
/*  90 */     double a23 = (this.b.x - d.x) * (this.b.x - d.x) + (this.b.y - d.y) * (this.b.y - d.y);
/*  91 */     double a33 = (this.c.x - d.x) * (this.c.x - d.x) + (this.c.y - d.y) * (this.c.y - d.y);
/*  93 */     double det = a11 * a22 * a33 + a12 * a23 * a31 + a13 * a21 * a32 - a13 * a22 * a31 - a12 * a21 * a33 - a11 * a23 * a32;
/*  96 */     if (isOrientedCCW())
/*  97 */       return (det > 0.0D); 
/*  99 */     return (det < 0.0D);
/*     */   }
/*     */   
/*     */   public boolean isOrientedCCW() {
/* 114 */     double a11 = this.a.x - this.c.x;
/* 115 */     double a21 = this.b.x - this.c.x;
/* 117 */     double a12 = this.a.y - this.c.y;
/* 118 */     double a22 = this.b.y - this.c.y;
/* 120 */     double det = a11 * a22 - a12 * a21;
/* 122 */     return (det > 0.0D);
/*     */   }
/*     */   
/*     */   public boolean isNeighbour(Edge edge) {
/* 126 */     return ((this.a == edge.a || this.b == edge.a || this.c == edge.a) && (this.a == edge.b || this.b == edge.b || this.c == edge.b));
/*     */   }
/*     */   
/*     */   public Vector2D nonEdgeVertex(Edge edge) {
/* 130 */     if (this.a != edge.a && this.a != edge.b)
/* 130 */       return this.a; 
/* 131 */     if (this.b != edge.a && this.b != edge.b)
/* 131 */       return this.b; 
/* 132 */     if (this.c != edge.a && this.c != edge.b)
/* 132 */       return this.c; 
/* 133 */     return null;
/*     */   }
/*     */   
/*     */   public boolean hasVertex(Vector2D v1) {
/* 137 */     if (this.a == v1 || this.b == v1 || this.c == v1)
/* 138 */       return true; 
/* 140 */     return false;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 144 */     return "Triangle: " + this.a + this.b + this.c;
/*     */   }
/*     */   
/*     */   public EdgeDistancePack findNearestEdge(Vector2D point) {
/* 148 */     EdgeDistancePack[] edges = new EdgeDistancePack[3];
/* 150 */     edges[0] = new EdgeDistancePack(new Edge(this.a, this.b), computeClosestPoint(this.a, this.b, point).sub(point).mag());
/* 151 */     edges[1] = new EdgeDistancePack(new Edge(this.b, this.c), computeClosestPoint(this.b, this.c, point).sub(point).mag());
/* 152 */     edges[2] = new EdgeDistancePack(new Edge(this.c, this.a), computeClosestPoint(this.c, this.a, point).sub(point).mag());
/* 154 */     Arrays.sort((Object[])edges);
/* 155 */     return edges[0];
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\Geom\Geom3D\Triangle.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */