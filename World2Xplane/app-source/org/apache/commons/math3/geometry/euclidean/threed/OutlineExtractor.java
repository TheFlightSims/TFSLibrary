/*     */ package org.apache.commons.math3.geometry.euclidean.threed;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import org.apache.commons.math3.geometry.Vector;
/*     */ import org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D;
/*     */ import org.apache.commons.math3.geometry.euclidean.twod.Line;
/*     */ import org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet;
/*     */ import org.apache.commons.math3.geometry.euclidean.twod.SubLine;
/*     */ import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
/*     */ import org.apache.commons.math3.geometry.partitioning.AbstractSubHyperplane;
/*     */ import org.apache.commons.math3.geometry.partitioning.BSPTree;
/*     */ import org.apache.commons.math3.geometry.partitioning.BSPTreeVisitor;
/*     */ import org.apache.commons.math3.geometry.partitioning.BoundaryAttribute;
/*     */ import org.apache.commons.math3.geometry.partitioning.Hyperplane;
/*     */ import org.apache.commons.math3.geometry.partitioning.Region;
/*     */ import org.apache.commons.math3.geometry.partitioning.RegionFactory;
/*     */ import org.apache.commons.math3.geometry.partitioning.SubHyperplane;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ 
/*     */ public class OutlineExtractor {
/*     */   private Vector3D u;
/*     */   
/*     */   private Vector3D v;
/*     */   
/*     */   private Vector3D w;
/*     */   
/*     */   public OutlineExtractor(Vector3D u, Vector3D v) {
/*  54 */     this.u = u;
/*  55 */     this.v = v;
/*  56 */     this.w = Vector3D.crossProduct(u, v);
/*     */   }
/*     */   
/*     */   public Vector2D[][] getOutline(PolyhedronsSet polyhedronsSet) {
/*  66 */     BoundaryProjector projector = new BoundaryProjector();
/*  67 */     polyhedronsSet.getTree(true).visit(projector);
/*  68 */     PolygonsSet projected = projector.getProjected();
/*  71 */     Vector2D[][] outline = projected.getVertices();
/*  72 */     for (int i = 0; i < outline.length; i++) {
/*  73 */       Vector2D[] rawLoop = outline[i];
/*  74 */       int end = rawLoop.length;
/*  75 */       int j = 0;
/*  76 */       while (j < end) {
/*  77 */         if (pointIsBetween(rawLoop, end, j)) {
/*  79 */           for (int k = j; k < end - 1; k++)
/*  80 */             rawLoop[k] = rawLoop[k + 1]; 
/*  82 */           end--;
/*     */           continue;
/*     */         } 
/*  85 */         j++;
/*     */       } 
/*  88 */       if (end != rawLoop.length) {
/*  90 */         outline[i] = new Vector2D[end];
/*  91 */         System.arraycopy(rawLoop, 0, outline[i], 0, end);
/*     */       } 
/*     */     } 
/*  95 */     return outline;
/*     */   }
/*     */   
/*     */   private boolean pointIsBetween(Vector2D[] loop, int n, int i) {
/* 108 */     Vector2D previous = loop[(i + n - 1) % n];
/* 109 */     Vector2D current = loop[i];
/* 110 */     Vector2D next = loop[(i + 1) % n];
/* 111 */     double dx1 = current.getX() - previous.getX();
/* 112 */     double dy1 = current.getY() - previous.getY();
/* 113 */     double dx2 = next.getX() - current.getX();
/* 114 */     double dy2 = next.getY() - current.getY();
/* 115 */     double cross = dx1 * dy2 - dx2 * dy1;
/* 116 */     double dot = dx1 * dx2 + dy1 * dy2;
/* 117 */     double d1d2 = FastMath.sqrt((dx1 * dx1 + dy1 * dy1) * (dx2 * dx2 + dy2 * dy2));
/* 118 */     return (FastMath.abs(cross) <= 1.0E-6D * d1d2 && dot >= 0.0D);
/*     */   }
/*     */   
/*     */   private class BoundaryProjector implements BSPTreeVisitor<Euclidean3D> {
/* 130 */     private PolygonsSet projected = new PolygonsSet(new BSPTree(Boolean.FALSE));
/*     */     
/*     */     public BSPTreeVisitor.Order visitOrder(BSPTree<Euclidean3D> node) {
/* 135 */       return BSPTreeVisitor.Order.MINUS_SUB_PLUS;
/*     */     }
/*     */     
/*     */     public void visitInternalNode(BSPTree<Euclidean3D> node) {
/* 141 */       BoundaryAttribute<Euclidean3D> attribute = (BoundaryAttribute<Euclidean3D>)node.getAttribute();
/* 143 */       if (attribute.getPlusOutside() != null)
/* 144 */         addContribution(attribute.getPlusOutside(), false); 
/* 146 */       if (attribute.getPlusInside() != null)
/* 147 */         addContribution(attribute.getPlusInside(), true); 
/*     */     }
/*     */     
/*     */     public void visitLeafNode(BSPTree<Euclidean3D> node) {}
/*     */     
/*     */     private void addContribution(SubHyperplane<Euclidean3D> facet, boolean reversed) {
/* 163 */       AbstractSubHyperplane<Euclidean3D, Euclidean2D> absFacet = (AbstractSubHyperplane)facet;
/* 165 */       Plane plane = (Plane)facet.getHyperplane();
/* 167 */       double scal = plane.getNormal().dotProduct(OutlineExtractor.this.w);
/* 168 */       if (FastMath.abs(scal) > 0.001D) {
/* 169 */         Vector2D[][] vertices = ((PolygonsSet)absFacet.getRemainingRegion()).getVertices();
/* 172 */         if ((((scal < 0.0D) ? 1 : 0) ^ reversed) != 0) {
/* 175 */           Vector2D[][] newVertices = new Vector2D[vertices.length][];
/* 176 */           for (int i = 0; i < vertices.length; i++) {
/* 177 */             Vector2D[] loop = vertices[i];
/* 178 */             Vector2D[] newLoop = new Vector2D[loop.length];
/* 179 */             if (loop[0] == null) {
/* 180 */               newLoop[0] = null;
/* 181 */               for (int j = 1; j < loop.length; j++)
/* 182 */                 newLoop[j] = loop[loop.length - j]; 
/*     */             } else {
/* 185 */               for (int j = 0; j < loop.length; j++)
/* 186 */                 newLoop[j] = loop[loop.length - j + 1]; 
/*     */             } 
/* 189 */             newVertices[i] = newLoop;
/*     */           } 
/* 193 */           vertices = newVertices;
/*     */         } 
/* 198 */         ArrayList<SubHyperplane<Euclidean2D>> edges = new ArrayList<SubHyperplane<Euclidean2D>>();
/* 199 */         for (Vector2D[] loop : vertices) {
/* 200 */           boolean closed = (loop[0] != null);
/* 201 */           int previous = closed ? (loop.length - 1) : 1;
/* 202 */           Vector3D previous3D = plane.toSpace((Vector<Euclidean2D>)loop[previous]);
/* 203 */           int current = (previous + 1) % loop.length;
/* 204 */           Vector2D pPoint = new Vector2D(previous3D.dotProduct(OutlineExtractor.this.u), previous3D.dotProduct(OutlineExtractor.this.v));
/* 206 */           while (current < loop.length) {
/*     */             SubHyperplane<Euclidean2D> subHyperplane;
/* 208 */             Vector3D current3D = plane.toSpace((Vector<Euclidean2D>)loop[current]);
/* 209 */             Vector2D cPoint = new Vector2D(current3D.dotProduct(OutlineExtractor.this.u), current3D.dotProduct(OutlineExtractor.this.v));
/* 211 */             Line line = new Line(pPoint, cPoint);
/* 213 */             SubLine subLine = line.wholeHyperplane();
/* 215 */             if (closed || previous != 1) {
/* 218 */               double angle = line.getAngle() + 1.5707963267948966D;
/* 219 */               Line l = new Line(pPoint, angle);
/* 221 */               subHyperplane = subLine.split((Hyperplane)l).getPlus();
/*     */             } 
/* 224 */             if (closed || current != loop.length - 1) {
/* 227 */               double angle = line.getAngle() + 1.5707963267948966D;
/* 228 */               Line l = new Line(cPoint, angle);
/* 230 */               subHyperplane = subHyperplane.split((Hyperplane)l).getMinus();
/*     */             } 
/* 233 */             edges.add(subHyperplane);
/* 235 */             previous = current++;
/* 236 */             previous3D = current3D;
/* 237 */             pPoint = cPoint;
/*     */           } 
/*     */         } 
/* 241 */         PolygonsSet projectedFacet = new PolygonsSet(edges);
/* 244 */         this.projected = (PolygonsSet)(new RegionFactory()).union((Region)this.projected, (Region)projectedFacet);
/*     */       } 
/*     */     }
/*     */     
/*     */     public PolygonsSet getProjected() {
/* 253 */       return this.projected;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\geometry\euclidean\threed\OutlineExtractor.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */