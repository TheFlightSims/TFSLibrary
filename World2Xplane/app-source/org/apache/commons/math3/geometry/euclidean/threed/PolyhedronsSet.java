/*     */ package org.apache.commons.math3.geometry.euclidean.threed;
/*     */ 
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.util.Collection;
/*     */ import org.apache.commons.math3.geometry.Vector;
/*     */ import org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D;
/*     */ import org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D;
/*     */ import org.apache.commons.math3.geometry.euclidean.twod.Line;
/*     */ import org.apache.commons.math3.geometry.euclidean.twod.SubLine;
/*     */ import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
/*     */ import org.apache.commons.math3.geometry.partitioning.AbstractRegion;
/*     */ import org.apache.commons.math3.geometry.partitioning.BSPTree;
/*     */ import org.apache.commons.math3.geometry.partitioning.BSPTreeVisitor;
/*     */ import org.apache.commons.math3.geometry.partitioning.BoundaryAttribute;
/*     */ import org.apache.commons.math3.geometry.partitioning.Hyperplane;
/*     */ import org.apache.commons.math3.geometry.partitioning.Region;
/*     */ import org.apache.commons.math3.geometry.partitioning.RegionFactory;
/*     */ import org.apache.commons.math3.geometry.partitioning.SubHyperplane;
/*     */ import org.apache.commons.math3.geometry.partitioning.Transform;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ 
/*     */ public class PolyhedronsSet extends AbstractRegion<Euclidean3D, Euclidean2D> {
/*     */   public PolyhedronsSet() {}
/*     */   
/*     */   public PolyhedronsSet(BSPTree<Euclidean3D> tree) {
/*  60 */     super(tree);
/*     */   }
/*     */   
/*     */   public PolyhedronsSet(Collection<SubHyperplane<Euclidean3D>> boundary) {
/*  83 */     super(boundary);
/*     */   }
/*     */   
/*     */   public PolyhedronsSet(double xMin, double xMax, double yMin, double yMax, double zMin, double zMax) {
/*  98 */     this((new RegionFactory()).buildConvex(new Hyperplane[] { new Plane(new Vector3D(xMin, 0.0D, 0.0D), Vector3D.MINUS_I), new Plane(new Vector3D(xMax, 0.0D, 0.0D), Vector3D.PLUS_I), new Plane(new Vector3D(0.0D, yMin, 0.0D), Vector3D.MINUS_J), new Plane(new Vector3D(0.0D, yMax, 0.0D), Vector3D.PLUS_J), new Plane(new Vector3D(0.0D, 0.0D, zMin), Vector3D.MINUS_K), new Plane(new Vector3D(0.0D, 0.0D, zMax), Vector3D.PLUS_K) }).getTree(false));
/*     */   }
/*     */   
/*     */   public PolyhedronsSet buildNew(BSPTree<Euclidean3D> tree) {
/* 110 */     return new PolyhedronsSet(tree);
/*     */   }
/*     */   
/*     */   protected void computeGeometricalProperties() {
/* 118 */     getTree(true).visit(new FacetsContributionVisitor());
/* 120 */     if (getSize() < 0.0D) {
/* 123 */       setSize(Double.POSITIVE_INFINITY);
/* 124 */       setBarycenter(Vector3D.NaN);
/*     */     } else {
/* 127 */       setSize(getSize() / 3.0D);
/* 128 */       setBarycenter(new Vector3D(1.0D / 4.0D * getSize(), (Vector3D)getBarycenter()));
/*     */     } 
/*     */   }
/*     */   
/*     */   private class FacetsContributionVisitor implements BSPTreeVisitor<Euclidean3D> {
/*     */     public FacetsContributionVisitor() {
/* 138 */       PolyhedronsSet.this.setSize(0.0D);
/* 139 */       PolyhedronsSet.this.setBarycenter(new Vector3D(0.0D, 0.0D, 0.0D));
/*     */     }
/*     */     
/*     */     public BSPTreeVisitor.Order visitOrder(BSPTree<Euclidean3D> node) {
/* 144 */       return BSPTreeVisitor.Order.MINUS_SUB_PLUS;
/*     */     }
/*     */     
/*     */     public void visitInternalNode(BSPTree<Euclidean3D> node) {
/* 150 */       BoundaryAttribute<Euclidean3D> attribute = (BoundaryAttribute<Euclidean3D>)node.getAttribute();
/* 152 */       if (attribute.getPlusOutside() != null)
/* 153 */         addContribution(attribute.getPlusOutside(), false); 
/* 155 */       if (attribute.getPlusInside() != null)
/* 156 */         addContribution(attribute.getPlusInside(), true); 
/*     */     }
/*     */     
/*     */     public void visitLeafNode(BSPTree<Euclidean3D> node) {}
/*     */     
/*     */     private void addContribution(SubHyperplane<Euclidean3D> facet, boolean reversed) {
/* 170 */       Region<Euclidean2D> polygon = ((SubPlane)facet).getRemainingRegion();
/* 171 */       double area = polygon.getSize();
/* 173 */       if (Double.isInfinite(area)) {
/* 174 */         PolyhedronsSet.this.setSize(Double.POSITIVE_INFINITY);
/* 175 */         PolyhedronsSet.this.setBarycenter(Vector3D.NaN);
/*     */       } else {
/* 178 */         Plane plane = (Plane)facet.getHyperplane();
/* 179 */         Vector3D facetB = plane.toSpace(polygon.getBarycenter());
/* 180 */         double scaled = area * facetB.dotProduct(plane.getNormal());
/* 181 */         if (reversed)
/* 182 */           scaled = -scaled; 
/* 185 */         PolyhedronsSet.this.setSize(PolyhedronsSet.this.getSize() + scaled);
/* 186 */         PolyhedronsSet.this.setBarycenter(new Vector3D(1.0D, (Vector3D)PolyhedronsSet.this.getBarycenter(), scaled, facetB));
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public SubHyperplane<Euclidean3D> firstIntersection(Vector3D point, Line line) {
/* 202 */     return recurseFirstIntersection(getTree(true), point, line);
/*     */   }
/*     */   
/*     */   private SubHyperplane<Euclidean3D> recurseFirstIntersection(BSPTree<Euclidean3D> node, Vector3D point, Line line) {
/*     */     BSPTree<Euclidean3D> near, far;
/* 217 */     SubHyperplane<Euclidean3D> cut = node.getCut();
/* 218 */     if (cut == null)
/* 219 */       return null; 
/* 221 */     BSPTree<Euclidean3D> minus = node.getMinus();
/* 222 */     BSPTree<Euclidean3D> plus = node.getPlus();
/* 223 */     Plane plane = (Plane)cut.getHyperplane();
/* 226 */     double offset = plane.getOffset(point);
/* 227 */     boolean in = (FastMath.abs(offset) < 1.0E-10D);
/* 230 */     if (offset < 0.0D) {
/* 231 */       near = minus;
/* 232 */       far = plus;
/*     */     } else {
/* 234 */       near = plus;
/* 235 */       far = minus;
/*     */     } 
/* 238 */     if (in) {
/* 240 */       SubHyperplane<Euclidean3D> facet = boundaryFacet(point, node);
/* 241 */       if (facet != null)
/* 242 */         return facet; 
/*     */     } 
/* 247 */     SubHyperplane<Euclidean3D> crossed = recurseFirstIntersection(near, point, line);
/* 248 */     if (crossed != null)
/* 249 */       return crossed; 
/* 252 */     if (!in) {
/* 254 */       Vector3D hit3D = plane.intersection(line);
/* 255 */       if (hit3D != null) {
/* 256 */         SubHyperplane<Euclidean3D> facet = boundaryFacet(hit3D, node);
/* 257 */         if (facet != null)
/* 258 */           return facet; 
/*     */       } 
/*     */     } 
/* 264 */     return recurseFirstIntersection(far, point, line);
/*     */   }
/*     */   
/*     */   private SubHyperplane<Euclidean3D> boundaryFacet(Vector3D point, BSPTree<Euclidean3D> node) {
/* 276 */     Vector2D point2D = ((Plane)node.getCut().getHyperplane()).toSubSpace(point);
/* 278 */     BoundaryAttribute<Euclidean3D> attribute = (BoundaryAttribute<Euclidean3D>)node.getAttribute();
/* 280 */     if (attribute.getPlusOutside() != null && ((SubPlane)attribute.getPlusOutside()).getRemainingRegion().checkPoint((Vector)point2D) == Region.Location.INSIDE)
/* 282 */       return attribute.getPlusOutside(); 
/* 284 */     if (attribute.getPlusInside() != null && ((SubPlane)attribute.getPlusInside()).getRemainingRegion().checkPoint((Vector)point2D) == Region.Location.INSIDE)
/* 286 */       return attribute.getPlusInside(); 
/* 288 */     return null;
/*     */   }
/*     */   
/*     */   public PolyhedronsSet rotate(Vector3D center, Rotation rotation) {
/* 298 */     return (PolyhedronsSet)applyTransform(new RotationTransform(center, rotation));
/*     */   }
/*     */   
/*     */   private static class RotationTransform implements Transform<Euclidean3D, Euclidean2D> {
/*     */     private Vector3D center;
/*     */     
/*     */     private Rotation rotation;
/*     */     
/*     */     private Plane cachedOriginal;
/*     */     
/*     */     private Transform<Euclidean2D, Euclidean1D> cachedTransform;
/*     */     
/*     */     public RotationTransform(Vector3D center, Rotation rotation) {
/* 321 */       this.center = center;
/* 322 */       this.rotation = rotation;
/*     */     }
/*     */     
/*     */     public Vector3D apply(Vector<Euclidean3D> point) {
/* 327 */       Vector3D delta = ((Vector3D)point).subtract(this.center);
/* 328 */       return new Vector3D(1.0D, this.center, 1.0D, this.rotation.applyTo(delta));
/*     */     }
/*     */     
/*     */     public Plane apply(Hyperplane<Euclidean3D> hyperplane) {
/* 333 */       return ((Plane)hyperplane).rotate(this.center, this.rotation);
/*     */     }
/*     */     
/*     */     public SubHyperplane<Euclidean2D> apply(SubHyperplane<Euclidean2D> sub, Hyperplane<Euclidean3D> original, Hyperplane<Euclidean3D> transformed) {
/* 340 */       if (original != this.cachedOriginal) {
/* 343 */         Plane oPlane = (Plane)original;
/* 344 */         Plane tPlane = (Plane)transformed;
/* 345 */         Vector3D p00 = oPlane.getOrigin();
/* 346 */         Vector3D p10 = oPlane.toSpace((Vector<Euclidean2D>)new Vector2D(1.0D, 0.0D));
/* 347 */         Vector3D p01 = oPlane.toSpace((Vector<Euclidean2D>)new Vector2D(0.0D, 1.0D));
/* 348 */         Vector2D tP00 = tPlane.toSubSpace(apply(p00));
/* 349 */         Vector2D tP10 = tPlane.toSubSpace(apply(p10));
/* 350 */         Vector2D tP01 = tPlane.toSubSpace(apply(p01));
/* 351 */         AffineTransform at = new AffineTransform(tP10.getX() - tP00.getX(), tP10.getY() - tP00.getY(), tP01.getX() - tP00.getX(), tP01.getY() - tP00.getY(), tP00.getX(), tP00.getY());
/* 356 */         this.cachedOriginal = (Plane)original;
/* 357 */         this.cachedTransform = Line.getTransform(at);
/*     */       } 
/* 360 */       return (SubHyperplane<Euclidean2D>)((SubLine)sub).applyTransform(this.cachedTransform);
/*     */     }
/*     */   }
/*     */   
/*     */   public PolyhedronsSet translate(Vector3D translation) {
/* 371 */     return (PolyhedronsSet)applyTransform(new TranslationTransform(translation));
/*     */   }
/*     */   
/*     */   private static class TranslationTransform implements Transform<Euclidean3D, Euclidean2D> {
/*     */     private Vector3D translation;
/*     */     
/*     */     private Plane cachedOriginal;
/*     */     
/*     */     private Transform<Euclidean2D, Euclidean1D> cachedTransform;
/*     */     
/*     */     public TranslationTransform(Vector3D translation) {
/* 390 */       this.translation = translation;
/*     */     }
/*     */     
/*     */     public Vector3D apply(Vector<Euclidean3D> point) {
/* 395 */       return new Vector3D(1.0D, (Vector3D)point, 1.0D, this.translation);
/*     */     }
/*     */     
/*     */     public Plane apply(Hyperplane<Euclidean3D> hyperplane) {
/* 400 */       return ((Plane)hyperplane).translate(this.translation);
/*     */     }
/*     */     
/*     */     public SubHyperplane<Euclidean2D> apply(SubHyperplane<Euclidean2D> sub, Hyperplane<Euclidean3D> original, Hyperplane<Euclidean3D> transformed) {
/* 407 */       if (original != this.cachedOriginal) {
/* 410 */         Plane oPlane = (Plane)original;
/* 411 */         Plane tPlane = (Plane)transformed;
/* 412 */         Vector2D shift = tPlane.toSubSpace(apply(oPlane.getOrigin()));
/* 413 */         AffineTransform at = AffineTransform.getTranslateInstance(shift.getX(), shift.getY());
/* 416 */         this.cachedOriginal = (Plane)original;
/* 417 */         this.cachedTransform = Line.getTransform(at);
/*     */       } 
/* 422 */       return (SubHyperplane<Euclidean2D>)((SubLine)sub).applyTransform(this.cachedTransform);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\geometry\euclidean\threed\PolyhedronsSet.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */