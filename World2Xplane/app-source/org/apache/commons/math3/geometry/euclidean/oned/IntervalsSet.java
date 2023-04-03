/*     */ package org.apache.commons.math3.geometry.euclidean.oned;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import org.apache.commons.math3.geometry.partitioning.AbstractRegion;
/*     */ import org.apache.commons.math3.geometry.partitioning.BSPTree;
/*     */ import org.apache.commons.math3.geometry.partitioning.Region;
/*     */ import org.apache.commons.math3.geometry.partitioning.SubHyperplane;
/*     */ 
/*     */ public class IntervalsSet extends AbstractRegion<Euclidean1D, Euclidean1D> {
/*     */   public IntervalsSet() {}
/*     */   
/*     */   public IntervalsSet(double lower, double upper) {
/*  46 */     super(buildTree(lower, upper));
/*     */   }
/*     */   
/*     */   public IntervalsSet(BSPTree<Euclidean1D> tree) {
/*  59 */     super(tree);
/*     */   }
/*     */   
/*     */   public IntervalsSet(Collection<SubHyperplane<Euclidean1D>> boundary) {
/*  82 */     super(boundary);
/*     */   }
/*     */   
/*     */   private static BSPTree<Euclidean1D> buildTree(double lower, double upper) {
/*  93 */     if (Double.isInfinite(lower) && lower < 0.0D) {
/*  94 */       if (Double.isInfinite(upper) && upper > 0.0D)
/*  96 */         return new BSPTree(Boolean.TRUE); 
/*  99 */       SubOrientedPoint subOrientedPoint = (new OrientedPoint(new Vector1D(upper), true)).wholeHyperplane();
/* 101 */       return new BSPTree((SubHyperplane)subOrientedPoint, new BSPTree(Boolean.FALSE), new BSPTree(Boolean.TRUE), null);
/*     */     } 
/* 106 */     SubOrientedPoint subOrientedPoint1 = (new OrientedPoint(new Vector1D(lower), false)).wholeHyperplane();
/* 108 */     if (Double.isInfinite(upper) && upper > 0.0D)
/* 110 */       return new BSPTree((SubHyperplane)subOrientedPoint1, new BSPTree(Boolean.FALSE), new BSPTree(Boolean.TRUE), null); 
/* 117 */     SubOrientedPoint subOrientedPoint2 = (new OrientedPoint(new Vector1D(upper), true)).wholeHyperplane();
/* 119 */     return new BSPTree((SubHyperplane)subOrientedPoint1, new BSPTree(Boolean.FALSE), new BSPTree((SubHyperplane)subOrientedPoint2, new BSPTree(Boolean.FALSE), new BSPTree(Boolean.TRUE), null), null);
/*     */   }
/*     */   
/*     */   public IntervalsSet buildNew(BSPTree<Euclidean1D> tree) {
/* 132 */     return new IntervalsSet(tree);
/*     */   }
/*     */   
/*     */   protected void computeGeometricalProperties() {
/* 138 */     if (getTree(false).getCut() == null) {
/* 139 */       setBarycenter(Vector1D.NaN);
/* 140 */       setSize(((Boolean)getTree(false).getAttribute()).booleanValue() ? Double.POSITIVE_INFINITY : 0.0D);
/*     */     } else {
/* 142 */       double size = 0.0D;
/* 143 */       double sum = 0.0D;
/* 144 */       for (Interval interval : asList()) {
/* 145 */         size += interval.getLength();
/* 146 */         sum += interval.getLength() * interval.getMidPoint();
/*     */       } 
/* 148 */       setSize(size);
/* 149 */       setBarycenter(Double.isInfinite(size) ? Vector1D.NaN : new Vector1D(sum / size));
/*     */     } 
/*     */   }
/*     */   
/*     */   public double getInf() {
/* 160 */     BSPTree<Euclidean1D> node = getTree(false);
/* 161 */     double inf = Double.POSITIVE_INFINITY;
/* 162 */     while (node.getCut() != null) {
/* 163 */       OrientedPoint op = (OrientedPoint)node.getCut().getHyperplane();
/* 164 */       inf = op.getLocation().getX();
/* 165 */       node = op.isDirect() ? node.getMinus() : node.getPlus();
/*     */     } 
/* 167 */     return ((Boolean)node.getAttribute()).booleanValue() ? Double.NEGATIVE_INFINITY : inf;
/*     */   }
/*     */   
/*     */   public double getSup() {
/* 177 */     BSPTree<Euclidean1D> node = getTree(false);
/* 178 */     double sup = Double.NEGATIVE_INFINITY;
/* 179 */     while (node.getCut() != null) {
/* 180 */       OrientedPoint op = (OrientedPoint)node.getCut().getHyperplane();
/* 181 */       sup = op.getLocation().getX();
/* 182 */       node = op.isDirect() ? node.getPlus() : node.getMinus();
/*     */     } 
/* 184 */     return ((Boolean)node.getAttribute()).booleanValue() ? Double.POSITIVE_INFINITY : sup;
/*     */   }
/*     */   
/*     */   public List<Interval> asList() {
/* 201 */     List<Interval> list = new ArrayList<Interval>();
/* 202 */     recurseList(getTree(false), list, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
/* 204 */     return list;
/*     */   }
/*     */   
/*     */   private void recurseList(BSPTree<Euclidean1D> node, List<Interval> list, double lower, double upper) {
/* 217 */     if (node.getCut() == null) {
/* 218 */       if (((Boolean)node.getAttribute()).booleanValue())
/* 220 */         list.add(new Interval(lower, upper)); 
/*     */     } else {
/* 223 */       OrientedPoint op = (OrientedPoint)node.getCut().getHyperplane();
/* 224 */       Vector1D loc = op.getLocation();
/* 225 */       double x = loc.getX();
/* 228 */       BSPTree<Euclidean1D> low = op.isDirect() ? node.getMinus() : node.getPlus();
/* 230 */       BSPTree<Euclidean1D> high = op.isDirect() ? node.getPlus() : node.getMinus();
/* 233 */       recurseList(low, list, lower, x);
/* 234 */       if (checkPoint(low, loc) == Region.Location.INSIDE && checkPoint(high, loc) == Region.Location.INSIDE)
/* 237 */         x = ((Interval)list.remove(list.size() - 1)).getLower(); 
/* 239 */       recurseList(high, list, x, upper);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\geometry\euclidean\oned\IntervalsSet.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */