/*     */ package com.world2xplane.Geom.Geom3D;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ import java.util.Vector;
/*     */ 
/*     */ class TriangleSet {
/* 208 */   Vector<Triangle> triangleSet = new Vector<>();
/*     */   
/*     */   public Edge findEdgeThatContains(Vector2D point) {
/* 213 */     Vector<EdgeDistancePack> edgeVector = new Vector<>();
/* 215 */     for (Triangle triangle : this.triangleSet)
/* 216 */       edgeVector.add(triangle.findNearestEdge(point)); 
/* 219 */     EdgeDistancePack[] edgeDistancePacks = new EdgeDistancePack[edgeVector.size()];
/* 220 */     edgeVector.toArray(edgeDistancePacks);
/* 223 */     Arrays.sort((Object[])edgeDistancePacks);
/* 225 */     return (edgeDistancePacks[0]).edge;
/*     */   }
/*     */   
/*     */   public void add(Triangle triangle) {
/* 229 */     this.triangleSet.add(triangle);
/*     */   }
/*     */   
/*     */   public void remove(Triangle triangle) {
/* 233 */     this.triangleSet.remove(triangle);
/*     */   }
/*     */   
/*     */   public void removeTrianglesUsing(Vector2D point) {
/* 237 */     Vector<Triangle> removeList = new Vector<>();
/* 238 */     for (Triangle triangle : this.triangleSet) {
/* 239 */       if (triangle.hasVertex(point))
/* 240 */         removeList.add(triangle); 
/*     */     } 
/* 244 */     this.triangleSet.removeAll(removeList);
/*     */   }
/*     */   
/*     */   public Triangle findContainingTriangle(Vector2D point) {
/* 248 */     for (Triangle triangle : this.triangleSet) {
/* 249 */       if (triangle.contains(point))
/* 250 */         return triangle; 
/*     */     } 
/* 253 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\Geom\Geom3D\TriangleSet.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */