/*    */ package org.geotools.referencing.operation.builder;
/*    */ 
/*    */ import java.awt.geom.Line2D;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.opengis.geometry.DirectPosition;
/*    */ 
/*    */ class Quadrilateral extends Polygon {
/*    */   public DirectPosition p0;
/*    */   
/*    */   public DirectPosition p1;
/*    */   
/*    */   public DirectPosition p2;
/*    */   
/*    */   public DirectPosition p3;
/*    */   
/*    */   public Quadrilateral(DirectPosition p0, DirectPosition p1, DirectPosition p2, DirectPosition p3) {
/* 55 */     super(new DirectPosition[] { p0, p1, p2, p3, p0 });
/* 56 */     this.p0 = p0;
/* 57 */     this.p1 = p1;
/* 58 */     this.p2 = p2;
/* 59 */     this.p3 = p3;
/*    */   }
/*    */   
/*    */   public boolean isConvex() {
/* 68 */     return Line2D.linesIntersect(this.p0.getCoordinate()[0], this.p0.getCoordinate()[1], this.p2.getCoordinate()[0], this.p2.getCoordinate()[1], this.p1.getCoordinate()[0], this.p1.getCoordinate()[1], this.p3.getCoordinate()[0], this.p3.getCoordinate()[1]);
/*    */   }
/*    */   
/*    */   public List<TINTriangle> getTriangles() {
/* 82 */     ArrayList<TINTriangle> triangles = new ArrayList<TINTriangle>();
/* 83 */     TINTriangle trigA = new TINTriangle(this.p0, this.p1, this.p2);
/* 84 */     TINTriangle trigB = new TINTriangle(this.p0, this.p3, this.p2);
/*    */     try {
/* 87 */       trigA.addAdjacentTriangle(trigB);
/* 88 */       trigB.addAdjacentTriangle(trigA);
/* 89 */     } catch (TriangulationException e) {
/* 91 */       e.printStackTrace();
/*    */     } 
/* 94 */     triangles.add(trigA);
/* 95 */     triangles.add(trigB);
/* 97 */     return triangles;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\operation\builder\Quadrilateral.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */