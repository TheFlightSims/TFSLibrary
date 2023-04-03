/*     */ package com.world2xplane.Geom.Geom3D;
/*     */ 
/*     */ import java.util.Collections;
/*     */ import java.util.Vector;
/*     */ 
/*     */ public class DelaunayTriangulator {
/*     */   Vector<Vector2D> pointSet;
/*     */   
/*     */   TriangleSet triangleSet;
/*     */   
/*     */   Triangle superTriangle;
/*     */   
/*     */   public DelaunayTriangulator(Vector<Vector2D> pointSet) throws NotEnoughPointsException {
/*  27 */     if (pointSet.size() < 3)
/*  28 */       throw new NotEnoughPointsException("Less than three points in point set."); 
/*  30 */     this.pointSet = pointSet;
/*  31 */     this.triangleSet = new TriangleSet();
/*     */   }
/*     */   
/*     */   public void setPointSet(Vector<Vector2D> pointSet) {
/*  35 */     this.pointSet = pointSet;
/*     */   }
/*     */   
/*     */   public void shuffle() {
/*  39 */     Collections.shuffle(this.pointSet);
/*     */   }
/*     */   
/*     */   public void shuffle(int[] permutation) {
/*  43 */     Vector<Vector2D> temp = new Vector<>();
/*  45 */     for (int i = 0; i < permutation.length; i++)
/*  46 */       temp.add(this.pointSet.get(permutation[i])); 
/*  49 */     this.pointSet = temp;
/*     */   }
/*     */   
/*     */   public void compute() {
/*  53 */     this.triangleSet = new TriangleSet();
/*  56 */     double maxOfAnyCoordinate = 0.0D;
/*  58 */     for (Vector2D vector : getPointSet())
/*  59 */       maxOfAnyCoordinate = Math.max(Math.max(vector.x, vector.y), maxOfAnyCoordinate); 
/*  64 */     maxOfAnyCoordinate *= 16.0D;
/*  68 */     Vector2D p1 = new Vector2D(0.0D, 3.0D * maxOfAnyCoordinate);
/*  69 */     Vector2D p2 = new Vector2D(3.0D * maxOfAnyCoordinate, 0.0D);
/*  70 */     Vector2D p3 = new Vector2D(-3.0D * maxOfAnyCoordinate, -3.0D * maxOfAnyCoordinate);
/*  72 */     this.superTriangle = new Triangle(p1, p2, p3);
/*  73 */     this.triangleSet.add(this.superTriangle);
/*  75 */     for (int i = 0; i < this.pointSet.size(); i++) {
/*  78 */       Triangle triangle = this.triangleSet.findContainingTriangle(this.pointSet.get(i));
/*  80 */       if (triangle == null) {
/*  98 */         Edge edge = this.triangleSet.findEdgeThatContains(this.pointSet.get(i));
/* 100 */         Triangle first = findTriangleSharing(edge);
/* 101 */         Triangle second = findNeighbour(first, edge);
/* 103 */         Vector2D nonEdgeFirst = first.nonEdgeVertex(edge);
/* 104 */         Vector2D nonEdgeSecond = second.nonEdgeVertex(edge);
/* 106 */         this.triangleSet.remove(first);
/* 107 */         this.triangleSet.remove(second);
/* 109 */         Triangle tri1 = new Triangle(edge.a, nonEdgeFirst, this.pointSet.get(i));
/* 110 */         Triangle tri2 = new Triangle(edge.b, nonEdgeFirst, this.pointSet.get(i));
/* 111 */         Triangle tri3 = new Triangle(edge.a, nonEdgeSecond, this.pointSet.get(i));
/* 112 */         Triangle tri4 = new Triangle(edge.b, nonEdgeSecond, this.pointSet.get(i));
/* 114 */         this.triangleSet.add(tri1);
/* 115 */         this.triangleSet.add(tri2);
/* 116 */         this.triangleSet.add(tri3);
/* 117 */         this.triangleSet.add(tri4);
/* 119 */         legalizeEdge(tri1, new Edge(edge.a, nonEdgeFirst), this.pointSet.get(i));
/* 120 */         legalizeEdge(tri2, new Edge(edge.b, nonEdgeFirst), this.pointSet.get(i));
/* 121 */         legalizeEdge(tri3, new Edge(edge.a, nonEdgeSecond), this.pointSet.get(i));
/* 122 */         legalizeEdge(tri4, new Edge(edge.b, nonEdgeSecond), this.pointSet.get(i));
/*     */       } else {
/* 124 */         Vector2D a = triangle.a;
/* 125 */         Vector2D b = triangle.b;
/* 126 */         Vector2D c = triangle.c;
/* 128 */         this.triangleSet.remove(triangle);
/* 130 */         Triangle first = new Triangle(a, b, this.pointSet.get(i));
/* 131 */         Triangle second = new Triangle(b, c, this.pointSet.get(i));
/* 132 */         Triangle third = new Triangle(c, a, this.pointSet.get(i));
/* 134 */         this.triangleSet.add(first);
/* 135 */         this.triangleSet.add(second);
/* 136 */         this.triangleSet.add(third);
/* 138 */         legalizeEdge(first, new Edge(a, b), this.pointSet.get(i));
/* 139 */         legalizeEdge(second, new Edge(b, c), this.pointSet.get(i));
/* 140 */         legalizeEdge(third, new Edge(c, a), this.pointSet.get(i));
/*     */       } 
/*     */     } 
/* 145 */     this.triangleSet.removeTrianglesUsing(this.superTriangle.a);
/* 146 */     this.triangleSet.removeTrianglesUsing(this.superTriangle.b);
/* 147 */     this.triangleSet.removeTrianglesUsing(this.superTriangle.c);
/*     */   }
/*     */   
/*     */   private void legalizeEdge(Triangle triangle, Edge edge, Vector2D newVertex) {
/* 151 */     Triangle neighbourTriangle = findNeighbour(triangle, edge);
/* 154 */     if (neighbourTriangle != null && 
/* 155 */       neighbourTriangle.pointInCircumcircle(newVertex)) {
/* 156 */       this.triangleSet.remove(triangle);
/* 157 */       this.triangleSet.remove(neighbourTriangle);
/* 159 */       Vector2D nonEdge = neighbourTriangle.nonEdgeVertex(edge);
/* 161 */       Triangle first = new Triangle(nonEdge, edge.a, newVertex);
/* 162 */       Triangle second = new Triangle(nonEdge, edge.b, newVertex);
/* 164 */       this.triangleSet.add(first);
/* 165 */       this.triangleSet.add(second);
/* 167 */       legalizeEdge(first, new Edge(nonEdge, edge.a), newVertex);
/* 168 */       legalizeEdge(second, new Edge(nonEdge, edge.b), newVertex);
/*     */     } 
/*     */   }
/*     */   
/*     */   private Triangle findNeighbour(Triangle tri, Edge edge) {
/* 174 */     for (Triangle triangle : this.triangleSet.triangleSet) {
/* 175 */       if (triangle.isNeighbour(edge) && triangle != tri)
/* 176 */         return triangle; 
/*     */     } 
/* 180 */     return null;
/*     */   }
/*     */   
/*     */   private Triangle findTriangleSharing(Edge edge) {
/* 184 */     for (Triangle triangle : this.triangleSet.triangleSet) {
/* 185 */       if (triangle.isNeighbour(edge))
/* 186 */         return triangle; 
/*     */     } 
/* 190 */     return null;
/*     */   }
/*     */   
/*     */   public Vector<Vector2D> getPointSet() {
/* 194 */     return this.pointSet;
/*     */   }
/*     */   
/*     */   public Vector<Triangle> getTriangleSet() {
/* 198 */     return this.triangleSet.triangleSet;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\Geom\Geom3D\DelaunayTriangulator.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */