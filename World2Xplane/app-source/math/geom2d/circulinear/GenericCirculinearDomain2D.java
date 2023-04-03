/*    */ package math.geom2d.circulinear;
/*    */ 
/*    */ import java.util.Collection;
/*    */ import math.geom2d.domain.Boundary2D;
/*    */ import math.geom2d.domain.Domain2D;
/*    */ import math.geom2d.domain.GenericDomain2D;
/*    */ import math.geom2d.transform.CircleInversion2D;
/*    */ 
/*    */ public class GenericCirculinearDomain2D extends GenericDomain2D implements CirculinearDomain2D {
/*    */   public static GenericCirculinearDomain2D create(CirculinearBoundary2D boundary) {
/* 30 */     return new GenericCirculinearDomain2D(boundary);
/*    */   }
/*    */   
/*    */   public GenericCirculinearDomain2D(CirculinearBoundary2D boundary) {
/* 37 */     super(boundary);
/*    */   }
/*    */   
/*    */   public CirculinearBoundary2D boundary() {
/* 42 */     return (CirculinearBoundary2D)this.boundary;
/*    */   }
/*    */   
/*    */   public Collection<? extends CirculinearContour2D> contours() {
/* 49 */     return ((CirculinearBoundary2D)this.boundary).continuousCurves();
/*    */   }
/*    */   
/*    */   public CirculinearDomain2D complement() {
/* 54 */     return new GenericCirculinearDomain2D(
/* 55 */         (CirculinearBoundary2D)this.boundary.reverse());
/*    */   }
/*    */   
/*    */   public CirculinearDomain2D buffer(double dist) {
/* 63 */     CirculinearBoundary2D newBoundary = (
/* 64 */       (CirculinearBoundary2D)this.boundary).parallel(dist);
/* 65 */     return new GenericCirculinearDomain2D(
/* 66 */         CirculinearContourArray2D.create(
/* 67 */           CirculinearCurves2D.splitIntersectingContours(
/* 68 */             newBoundary.continuousCurves())));
/*    */   }
/*    */   
/*    */   public CirculinearDomain2D transform(CircleInversion2D inv) {
/* 76 */     CirculinearBoundary2D boundary2 = (CirculinearBoundary2D)this.boundary;
/* 79 */     boundary2 = boundary2.transform(inv).reverse();
/* 82 */     return new GenericCirculinearDomain2D(boundary2);
/*    */   }
/*    */   
/*    */   public String toString() {
/* 90 */     return "GenericCirculinearDomain2D(boundary=" + this.boundary + ")";
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom2d\circulinear\GenericCirculinearDomain2D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */