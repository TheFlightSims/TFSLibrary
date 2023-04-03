/*     */ package org.apache.commons.math3.geometry.euclidean.twod;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import org.apache.commons.math3.exception.MathInternalError;
/*     */ import org.apache.commons.math3.geometry.Vector;
/*     */ import org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D;
/*     */ import org.apache.commons.math3.geometry.euclidean.oned.Interval;
/*     */ import org.apache.commons.math3.geometry.euclidean.oned.IntervalsSet;
/*     */ import org.apache.commons.math3.geometry.euclidean.oned.Vector1D;
/*     */ import org.apache.commons.math3.geometry.partitioning.AbstractRegion;
/*     */ import org.apache.commons.math3.geometry.partitioning.AbstractSubHyperplane;
/*     */ import org.apache.commons.math3.geometry.partitioning.BSPTree;
/*     */ import org.apache.commons.math3.geometry.partitioning.BSPTreeVisitor;
/*     */ import org.apache.commons.math3.geometry.partitioning.BoundaryAttribute;
/*     */ import org.apache.commons.math3.geometry.partitioning.Hyperplane;
/*     */ import org.apache.commons.math3.geometry.partitioning.Region;
/*     */ import org.apache.commons.math3.geometry.partitioning.SubHyperplane;
/*     */ import org.apache.commons.math3.geometry.partitioning.utilities.AVLTree;
/*     */ import org.apache.commons.math3.geometry.partitioning.utilities.OrderedTuple;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ 
/*     */ public class PolygonsSet extends AbstractRegion<Euclidean2D, Euclidean1D> {
/*     */   private Vector2D[][] vertices;
/*     */   
/*     */   public PolygonsSet() {}
/*     */   
/*     */   public PolygonsSet(BSPTree<Euclidean2D> tree) {
/*  63 */     super(tree);
/*     */   }
/*     */   
/*     */   public PolygonsSet(Collection<SubHyperplane<Euclidean2D>> boundary) {
/*  87 */     super(boundary);
/*     */   }
/*     */   
/*     */   public PolygonsSet(double xMin, double xMax, double yMin, double yMax) {
/*  98 */     super((Hyperplane[])boxBoundary(xMin, xMax, yMin, yMax));
/*     */   }
/*     */   
/*     */   private static Line[] boxBoundary(double xMin, double xMax, double yMin, double yMax) {
/* 110 */     Vector2D minMin = new Vector2D(xMin, yMin);
/* 111 */     Vector2D minMax = new Vector2D(xMin, yMax);
/* 112 */     Vector2D maxMin = new Vector2D(xMax, yMin);
/* 113 */     Vector2D maxMax = new Vector2D(xMax, yMax);
/* 114 */     return new Line[] { new Line(minMin, maxMin), new Line(maxMin, maxMax), new Line(maxMax, minMax), new Line(minMax, minMin) };
/*     */   }
/*     */   
/*     */   public PolygonsSet buildNew(BSPTree<Euclidean2D> tree) {
/* 125 */     return new PolygonsSet(tree);
/*     */   }
/*     */   
/*     */   protected void computeGeometricalProperties() {
/* 132 */     Vector2D[][] v = getVertices();
/* 134 */     if (v.length == 0) {
/* 135 */       if (((Boolean)getTree(false).getAttribute()).booleanValue()) {
/* 136 */         setSize(Double.POSITIVE_INFINITY);
/* 137 */         setBarycenter(Vector2D.NaN);
/*     */       } else {
/* 139 */         setSize(0.0D);
/* 140 */         setBarycenter(new Vector2D(0.0D, 0.0D));
/*     */       } 
/* 142 */     } else if (v[0][0] == null) {
/* 144 */       setSize(Double.POSITIVE_INFINITY);
/* 145 */       setBarycenter(Vector2D.NaN);
/*     */     } else {
/* 149 */       double sum = 0.0D;
/* 150 */       double sumX = 0.0D;
/* 151 */       double sumY = 0.0D;
/* 153 */       for (Vector2D[] loop : v) {
/* 154 */         double x1 = loop[loop.length - 1].getX();
/* 155 */         double y1 = loop[loop.length - 1].getY();
/* 156 */         for (Vector2D point : loop) {
/* 157 */           double x0 = x1;
/* 158 */           double y0 = y1;
/* 159 */           x1 = point.getX();
/* 160 */           y1 = point.getY();
/* 161 */           double factor = x0 * y1 - y0 * x1;
/* 162 */           sum += factor;
/* 163 */           sumX += factor * (x0 + x1);
/* 164 */           sumY += factor * (y0 + y1);
/*     */         } 
/*     */       } 
/* 168 */       if (sum < 0.0D) {
/* 170 */         setSize(Double.POSITIVE_INFINITY);
/* 171 */         setBarycenter(Vector2D.NaN);
/*     */       } else {
/* 173 */         setSize(sum / 2.0D);
/* 174 */         setBarycenter(new Vector2D(sumX / 3.0D * sum, sumY / 3.0D * sum));
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public Vector2D[][] getVertices() {
/* 205 */     if (this.vertices == null)
/* 206 */       if (getTree(false).getCut() == null) {
/* 207 */         this.vertices = new Vector2D[0][];
/*     */       } else {
/* 211 */         SegmentsBuilder visitor = new SegmentsBuilder();
/* 212 */         getTree(true).visit(visitor);
/* 213 */         AVLTree<ComparableSegment> sorted = visitor.getSorted();
/* 217 */         ArrayList<List<ComparableSegment>> loops = new ArrayList<List<ComparableSegment>>();
/* 218 */         while (!sorted.isEmpty()) {
/* 219 */           AVLTree<ComparableSegment>.Node node = sorted.getSmallest();
/* 220 */           List<ComparableSegment> loop = followLoop(node, sorted);
/* 221 */           if (loop != null)
/* 222 */             loops.add(loop); 
/*     */         } 
/* 227 */         this.vertices = new Vector2D[loops.size()][];
/* 228 */         int i = 0;
/* 230 */         for (List<ComparableSegment> loop : loops) {
/* 231 */           if (loop.size() < 2) {
/* 233 */             Line line = ((ComparableSegment)loop.get(0)).getLine();
/* 234 */             (new Vector2D[3])[0] = null;
/* 234 */             (new Vector2D[3])[1] = line.toSpace((Vector<Euclidean1D>)new Vector1D(-3.4028234663852886E38D));
/* 234 */             (new Vector2D[3])[2] = line.toSpace((Vector<Euclidean1D>)new Vector1D(3.4028234663852886E38D));
/* 234 */             this.vertices[i++] = new Vector2D[3];
/*     */             continue;
/*     */           } 
/* 239 */           if (((ComparableSegment)loop.get(0)).getStart() == null) {
/* 241 */             Vector2D[] arrayOfVector2D = new Vector2D[loop.size() + 2];
/* 242 */             int k = 0;
/* 243 */             for (Segment segment : loop) {
/* 245 */               if (k == 0) {
/* 247 */                 double x = segment.getLine().toSubSpace(segment.getEnd()).getX();
/* 248 */                 x -= FastMath.max(1.0D, FastMath.abs(x / 2.0D));
/* 249 */                 arrayOfVector2D[k++] = null;
/* 250 */                 arrayOfVector2D[k++] = segment.getLine().toSpace((Vector<Euclidean1D>)new Vector1D(x));
/*     */               } 
/* 253 */               if (k < arrayOfVector2D.length - 1)
/* 255 */                 arrayOfVector2D[k++] = segment.getEnd(); 
/* 258 */               if (k == arrayOfVector2D.length - 1) {
/* 260 */                 double x = segment.getLine().toSubSpace(segment.getStart()).getX();
/* 261 */                 x += FastMath.max(1.0D, FastMath.abs(x / 2.0D));
/* 262 */                 arrayOfVector2D[k++] = segment.getLine().toSpace((Vector<Euclidean1D>)new Vector1D(x));
/*     */               } 
/*     */             } 
/* 266 */             this.vertices[i++] = arrayOfVector2D;
/*     */             continue;
/*     */           } 
/* 268 */           Vector2D[] array = new Vector2D[loop.size()];
/* 269 */           int j = 0;
/* 270 */           for (Segment segment : loop)
/* 271 */             array[j++] = segment.getStart(); 
/* 273 */           this.vertices[i++] = array;
/*     */         } 
/*     */       }  
/* 280 */     return (Vector2D[][])this.vertices.clone();
/*     */   }
/*     */   
/*     */   private List<ComparableSegment> followLoop(AVLTree<ComparableSegment>.Node node, AVLTree<ComparableSegment> sorted) {
/* 294 */     ArrayList<ComparableSegment> loop = new ArrayList<ComparableSegment>();
/* 295 */     ComparableSegment segment = (ComparableSegment)node.getElement();
/* 296 */     loop.add(segment);
/* 297 */     Vector2D globalStart = segment.getStart();
/* 298 */     Vector2D end = segment.getEnd();
/* 299 */     node.delete();
/* 302 */     boolean open = (segment.getStart() == null);
/* 304 */     while (end != null && (open || globalStart.distance(end) > 1.0E-10D)) {
/* 307 */       AVLTree<ComparableSegment>.Node selectedNode = null;
/* 308 */       ComparableSegment selectedSegment = null;
/* 309 */       double selectedDistance = Double.POSITIVE_INFINITY;
/* 310 */       ComparableSegment lowerLeft = new ComparableSegment(end, -1.0E-10D, -1.0E-10D);
/* 311 */       ComparableSegment upperRight = new ComparableSegment(end, 1.0E-10D, 1.0E-10D);
/* 312 */       AVLTree<ComparableSegment>.Node n = sorted.getNotSmaller(lowerLeft);
/* 313 */       for (; n != null && ((ComparableSegment)n.getElement()).compareTo(upperRight) <= 0; 
/* 314 */         n = n.getNext()) {
/* 315 */         segment = (ComparableSegment)n.getElement();
/* 316 */         double distance = end.distance(segment.getStart());
/* 317 */         if (distance < selectedDistance) {
/* 318 */           selectedNode = n;
/* 319 */           selectedSegment = segment;
/* 320 */           selectedDistance = distance;
/*     */         } 
/*     */       } 
/* 324 */       if (selectedDistance > 1.0E-10D)
/* 328 */         return null; 
/* 331 */       end = selectedSegment.getEnd();
/* 332 */       loop.add(selectedSegment);
/* 333 */       selectedNode.delete();
/*     */     } 
/* 337 */     if (loop.size() == 2 && !open)
/* 339 */       return null; 
/* 342 */     if (end == null && !open)
/* 343 */       throw new MathInternalError(); 
/* 346 */     return loop;
/*     */   }
/*     */   
/*     */   private static class ComparableSegment extends Segment implements Comparable<ComparableSegment> {
/*     */     private OrderedTuple sortingKey;
/*     */     
/*     */     public ComparableSegment(Vector2D start, Vector2D end, Line line) {
/* 362 */       super(start, end, line);
/* 363 */       this.sortingKey = (start == null) ? new OrderedTuple(new double[] { Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY }) : new OrderedTuple(new double[] { start.getX(), start.getY() });
/*     */     }
/*     */     
/*     */     public ComparableSegment(Vector2D start, double dx, double dy) {
/* 378 */       super(null, null, null);
/* 379 */       this.sortingKey = new OrderedTuple(new double[] { start.getX() + dx, start.getY() + dy });
/*     */     }
/*     */     
/*     */     public int compareTo(ComparableSegment o) {
/* 384 */       return this.sortingKey.compareTo(o.sortingKey);
/*     */     }
/*     */     
/*     */     public boolean equals(Object other) {
/* 390 */       if (this == other)
/* 391 */         return true; 
/* 392 */       if (other instanceof ComparableSegment)
/* 393 */         return (compareTo((ComparableSegment)other) == 0); 
/* 395 */       return false;
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 402 */       return getStart().hashCode() ^ getEnd().hashCode() ^ getLine().hashCode() ^ this.sortingKey.hashCode();
/*     */     }
/*     */   }
/*     */   
/*     */   private static class SegmentsBuilder implements BSPTreeVisitor<Euclidean2D> {
/* 416 */     private AVLTree<PolygonsSet.ComparableSegment> sorted = new AVLTree();
/*     */     
/*     */     public BSPTreeVisitor.Order visitOrder(BSPTree<Euclidean2D> node) {
/* 421 */       return BSPTreeVisitor.Order.MINUS_SUB_PLUS;
/*     */     }
/*     */     
/*     */     public void visitInternalNode(BSPTree<Euclidean2D> node) {
/* 427 */       BoundaryAttribute<Euclidean2D> attribute = (BoundaryAttribute<Euclidean2D>)node.getAttribute();
/* 428 */       if (attribute.getPlusOutside() != null)
/* 429 */         addContribution(attribute.getPlusOutside(), false); 
/* 431 */       if (attribute.getPlusInside() != null)
/* 432 */         addContribution(attribute.getPlusInside(), true); 
/*     */     }
/*     */     
/*     */     public void visitLeafNode(BSPTree<Euclidean2D> node) {}
/*     */     
/*     */     private void addContribution(SubHyperplane<Euclidean2D> sub, boolean reversed) {
/* 446 */       AbstractSubHyperplane<Euclidean2D, Euclidean1D> absSub = (AbstractSubHyperplane)sub;
/* 448 */       Line line = (Line)sub.getHyperplane();
/* 449 */       List<Interval> intervals = ((IntervalsSet)absSub.getRemainingRegion()).asList();
/* 450 */       for (Interval i : intervals) {
/* 451 */         Vector2D start = Double.isInfinite(i.getLower()) ? null : line.toSpace((Vector<Euclidean1D>)new Vector1D(i.getLower()));
/* 453 */         Vector2D end = Double.isInfinite(i.getUpper()) ? null : line.toSpace((Vector<Euclidean1D>)new Vector1D(i.getUpper()));
/* 455 */         if (reversed) {
/* 456 */           this.sorted.insert(new PolygonsSet.ComparableSegment(end, start, line.getReverse()));
/*     */           continue;
/*     */         } 
/* 458 */         this.sorted.insert(new PolygonsSet.ComparableSegment(start, end, line));
/*     */       } 
/*     */     }
/*     */     
/*     */     public AVLTree<PolygonsSet.ComparableSegment> getSorted() {
/* 467 */       return this.sorted;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\geometry\euclidean\twod\PolygonsSet.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */