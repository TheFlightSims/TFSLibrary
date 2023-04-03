/*     */ package org.apache.commons.math3.geometry.partitioning;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Comparator;
/*     */ import java.util.Iterator;
/*     */ import java.util.TreeSet;
/*     */ import org.apache.commons.math3.geometry.Space;
/*     */ import org.apache.commons.math3.geometry.Vector;
/*     */ 
/*     */ public abstract class AbstractRegion<S extends Space, T extends Space> implements Region<S> {
/*     */   private BSPTree<S> tree;
/*     */   
/*     */   private double size;
/*     */   
/*     */   private Vector<S> barycenter;
/*     */   
/*     */   protected AbstractRegion() {
/*  50 */     this.tree = new BSPTree<S>(Boolean.TRUE);
/*     */   }
/*     */   
/*     */   protected AbstractRegion(BSPTree<S> tree) {
/*  66 */     this.tree = tree;
/*     */   }
/*     */   
/*     */   protected AbstractRegion(Collection<SubHyperplane<S>> boundary) {
/*  90 */     if (boundary.size() == 0) {
/*  93 */       this.tree = new BSPTree<S>(Boolean.TRUE);
/*     */     } else {
/* 100 */       TreeSet<SubHyperplane<S>> ordered = new TreeSet<SubHyperplane<S>>(new Comparator<SubHyperplane<S>>() {
/*     */             public int compare(SubHyperplane<S> o1, SubHyperplane<S> o2) {
/* 102 */               double size1 = o1.getSize();
/* 103 */               double size2 = o2.getSize();
/* 104 */               return (size2 < size1) ? -1 : ((o1 == o2) ? 0 : 1);
/*     */             }
/*     */           });
/* 107 */       ordered.addAll(boundary);
/* 110 */       this.tree = new BSPTree<S>();
/* 111 */       insertCuts(this.tree, ordered);
/* 114 */       this.tree.visit(new BSPTreeVisitor<S>() {
/*     */             public BSPTreeVisitor.Order visitOrder(BSPTree<S> node) {
/* 118 */               return BSPTreeVisitor.Order.PLUS_SUB_MINUS;
/*     */             }
/*     */             
/*     */             public void visitInternalNode(BSPTree<S> node) {}
/*     */             
/*     */             public void visitLeafNode(BSPTree<S> node) {
/* 127 */               node.setAttribute((node == node.getParent().getPlus()) ? Boolean.FALSE : Boolean.TRUE);
/*     */             }
/*     */           });
/*     */     } 
/*     */   }
/*     */   
/*     */   public AbstractRegion(Hyperplane<S>[] hyperplanes) {
/* 141 */     if (hyperplanes == null || hyperplanes.length == 0) {
/* 142 */       this.tree = new BSPTree<S>(Boolean.FALSE);
/*     */     } else {
/* 146 */       this.tree = hyperplanes[0].wholeSpace().getTree(false);
/* 149 */       BSPTree<S> node = this.tree;
/* 150 */       node.setAttribute(Boolean.TRUE);
/* 151 */       for (Hyperplane<S> hyperplane : hyperplanes) {
/* 152 */         if (node.insertCut(hyperplane)) {
/* 153 */           node.setAttribute(null);
/* 154 */           node.getPlus().setAttribute(Boolean.FALSE);
/* 155 */           node = node.getMinus();
/* 156 */           node.setAttribute(Boolean.TRUE);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void insertCuts(BSPTree<S> node, Collection<SubHyperplane<S>> boundary) {
/* 175 */     Iterator<SubHyperplane<S>> iterator = boundary.iterator();
/* 178 */     Hyperplane<S> inserted = null;
/* 179 */     while (inserted == null && iterator.hasNext()) {
/* 180 */       inserted = ((SubHyperplane<S>)iterator.next()).getHyperplane();
/* 181 */       if (!node.insertCut(inserted.copySelf()))
/* 182 */         inserted = null; 
/*     */     } 
/* 186 */     if (!iterator.hasNext())
/*     */       return; 
/* 191 */     ArrayList<SubHyperplane<S>> plusList = new ArrayList<SubHyperplane<S>>();
/* 192 */     ArrayList<SubHyperplane<S>> minusList = new ArrayList<SubHyperplane<S>>();
/* 193 */     while (iterator.hasNext()) {
/*     */       SubHyperplane.SplitSubHyperplane<S> split;
/* 194 */       SubHyperplane<S> other = iterator.next();
/* 195 */       switch (other.side(inserted)) {
/*     */         case PLUS:
/* 197 */           plusList.add(other);
/*     */         case MINUS:
/* 200 */           minusList.add(other);
/*     */         case BOTH:
/* 203 */           split = other.split(inserted);
/* 204 */           plusList.add(split.getPlus());
/* 205 */           minusList.add(split.getMinus());
/*     */       } 
/*     */     } 
/* 213 */     insertCuts(node.getPlus(), plusList);
/* 214 */     insertCuts(node.getMinus(), minusList);
/*     */   }
/*     */   
/*     */   public AbstractRegion<S, T> copySelf() {
/* 220 */     return buildNew(this.tree.copySelf());
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 225 */     return isEmpty(this.tree);
/*     */   }
/*     */   
/*     */   public boolean isEmpty(BSPTree<S> node) {
/* 235 */     if (node.getCut() == null)
/* 237 */       return !((Boolean)node.getAttribute()).booleanValue(); 
/* 241 */     return (isEmpty(node.getMinus()) && isEmpty(node.getPlus()));
/*     */   }
/*     */   
/*     */   public boolean contains(Region<S> region) {
/* 247 */     return (new RegionFactory<S>()).difference(region, this).isEmpty();
/*     */   }
/*     */   
/*     */   public Region.Location checkPoint(Vector<S> point) {
/* 252 */     return checkPoint(this.tree, point);
/*     */   }
/*     */   
/*     */   protected Region.Location checkPoint(BSPTree<S> node, Vector<S> point) {
/* 263 */     BSPTree<S> cell = node.getCell(point);
/* 264 */     if (cell.getCut() == null)
/* 266 */       return ((Boolean)cell.getAttribute()).booleanValue() ? Region.Location.INSIDE : Region.Location.OUTSIDE; 
/* 270 */     Region.Location minusCode = checkPoint(cell.getMinus(), point);
/* 271 */     Region.Location plusCode = checkPoint(cell.getPlus(), point);
/* 272 */     return (minusCode == plusCode) ? minusCode : Region.Location.BOUNDARY;
/*     */   }
/*     */   
/*     */   public BSPTree<S> getTree(boolean includeBoundaryAttributes) {
/* 278 */     if (includeBoundaryAttributes && this.tree.getCut() != null && this.tree.getAttribute() == null)
/* 280 */       recurseBuildBoundary(this.tree); 
/* 282 */     return this.tree;
/*     */   }
/*     */   
/*     */   private void recurseBuildBoundary(BSPTree<S> node) {
/* 289 */     if (node.getCut() != null) {
/* 291 */       SubHyperplane<S> plusOutside = null;
/* 292 */       SubHyperplane<S> plusInside = null;
/* 296 */       Characterization<S> plusChar = new Characterization<S>();
/* 297 */       characterize(node.getPlus(), node.getCut().copySelf(), plusChar);
/* 299 */       if (plusChar.hasOut()) {
/* 304 */         Characterization<S> minusChar = new Characterization<S>();
/* 305 */         characterize(node.getMinus(), plusChar.getOut(), minusChar);
/* 306 */         if (minusChar.hasIn())
/* 307 */           plusOutside = minusChar.getIn(); 
/*     */       } 
/* 311 */       if (plusChar.hasIn()) {
/* 316 */         Characterization<S> minusChar = new Characterization<S>();
/* 317 */         characterize(node.getMinus(), plusChar.getIn(), minusChar);
/* 318 */         if (minusChar.hasOut())
/* 319 */           plusInside = minusChar.getOut(); 
/*     */       } 
/* 323 */       node.setAttribute(new BoundaryAttribute<S>(plusOutside, plusInside));
/* 324 */       recurseBuildBoundary(node.getPlus());
/* 325 */       recurseBuildBoundary(node.getMinus());
/*     */     } 
/*     */   }
/*     */   
/*     */   private void characterize(BSPTree<S> node, SubHyperplane<S> sub, Characterization<S> characterization) {
/* 345 */     if (node.getCut() == null) {
/* 347 */       boolean inside = ((Boolean)node.getAttribute()).booleanValue();
/* 348 */       characterization.add(sub, inside);
/*     */     } else {
/*     */       SubHyperplane.SplitSubHyperplane<S> split;
/* 350 */       Hyperplane<S> hyperplane = node.getCut().getHyperplane();
/* 351 */       switch (sub.side(hyperplane)) {
/*     */         case PLUS:
/* 353 */           characterize(node.getPlus(), sub, characterization);
/*     */           return;
/*     */         case MINUS:
/* 356 */           characterize(node.getMinus(), sub, characterization);
/*     */           return;
/*     */         case BOTH:
/* 359 */           split = sub.split(hyperplane);
/* 360 */           characterize(node.getPlus(), split.getPlus(), characterization);
/* 361 */           characterize(node.getMinus(), split.getMinus(), characterization);
/*     */           return;
/*     */       } 
/* 365 */       throw new RuntimeException("internal error");
/*     */     } 
/*     */   }
/*     */   
/*     */   public double getBoundarySize() {
/* 372 */     BoundarySizeVisitor<S> visitor = new BoundarySizeVisitor<S>();
/* 373 */     getTree(true).visit(visitor);
/* 374 */     return visitor.getSize();
/*     */   }
/*     */   
/*     */   public double getSize() {
/* 379 */     if (this.barycenter == null)
/* 380 */       computeGeometricalProperties(); 
/* 382 */     return this.size;
/*     */   }
/*     */   
/*     */   protected void setSize(double size) {
/* 389 */     this.size = size;
/*     */   }
/*     */   
/*     */   public Vector<S> getBarycenter() {
/* 394 */     if (this.barycenter == null)
/* 395 */       computeGeometricalProperties(); 
/* 397 */     return this.barycenter;
/*     */   }
/*     */   
/*     */   protected void setBarycenter(Vector<S> barycenter) {
/* 404 */     this.barycenter = barycenter;
/*     */   }
/*     */   
/*     */   public Side side(Hyperplane<S> hyperplane) {
/* 414 */     Sides sides = new Sides();
/* 415 */     recurseSides(this.tree, hyperplane.wholeHyperplane(), sides);
/* 416 */     return sides.plusFound() ? (sides.minusFound() ? Side.BOTH : Side.PLUS) : (sides.minusFound() ? Side.MINUS : Side.HYPER);
/*     */   }
/*     */   
/*     */   private void recurseSides(BSPTree<S> node, SubHyperplane<S> sub, Sides sides) {
/*     */     SubHyperplane.SplitSubHyperplane<S> split;
/* 438 */     if (node.getCut() == null) {
/* 439 */       if (((Boolean)node.getAttribute()).booleanValue()) {
/* 441 */         sides.rememberPlusFound();
/* 442 */         sides.rememberMinusFound();
/*     */       } 
/*     */       return;
/*     */     } 
/* 447 */     Hyperplane<S> hyperplane = node.getCut().getHyperplane();
/* 448 */     switch (sub.side(hyperplane)) {
/*     */       case PLUS:
/* 451 */         if (node.getCut().side(sub.getHyperplane()) == Side.PLUS) {
/* 452 */           if (!isEmpty(node.getMinus()))
/* 453 */             sides.rememberPlusFound(); 
/* 456 */         } else if (!isEmpty(node.getMinus())) {
/* 457 */           sides.rememberMinusFound();
/*     */         } 
/* 460 */         if (!sides.plusFound() || !sides.minusFound())
/* 461 */           recurseSides(node.getPlus(), sub, sides); 
/*     */         return;
/*     */       case MINUS:
/* 466 */         if (node.getCut().side(sub.getHyperplane()) == Side.PLUS) {
/* 467 */           if (!isEmpty(node.getPlus()))
/* 468 */             sides.rememberPlusFound(); 
/* 471 */         } else if (!isEmpty(node.getPlus())) {
/* 472 */           sides.rememberMinusFound();
/*     */         } 
/* 475 */         if (!sides.plusFound() || !sides.minusFound())
/* 476 */           recurseSides(node.getMinus(), sub, sides); 
/*     */         return;
/*     */       case BOTH:
/* 481 */         split = sub.split(hyperplane);
/* 484 */         recurseSides(node.getPlus(), split.getPlus(), sides);
/* 487 */         if (!sides.plusFound() || !sides.minusFound())
/* 488 */           recurseSides(node.getMinus(), split.getMinus(), sides); 
/*     */         return;
/*     */     } 
/* 493 */     if (node.getCut().getHyperplane().sameOrientationAs(sub.getHyperplane())) {
/* 494 */       if (node.getPlus().getCut() != null || ((Boolean)node.getPlus().getAttribute()).booleanValue())
/* 495 */         sides.rememberPlusFound(); 
/* 497 */       if (node.getMinus().getCut() != null || ((Boolean)node.getMinus().getAttribute()).booleanValue())
/* 498 */         sides.rememberMinusFound(); 
/*     */     } else {
/* 501 */       if (node.getPlus().getCut() != null || ((Boolean)node.getPlus().getAttribute()).booleanValue())
/* 502 */         sides.rememberMinusFound(); 
/* 504 */       if (node.getMinus().getCut() != null || ((Boolean)node.getMinus().getAttribute()).booleanValue())
/* 505 */         sides.rememberPlusFound(); 
/*     */     } 
/*     */   }
/*     */   
/*     */   private static final class Sides {
/*     */     private boolean plusFound = false;
/*     */     
/*     */     private boolean minusFound = false;
/*     */     
/*     */     public void rememberPlusFound() {
/* 531 */       this.plusFound = true;
/*     */     }
/*     */     
/*     */     public boolean plusFound() {
/* 538 */       return this.plusFound;
/*     */     }
/*     */     
/*     */     public void rememberMinusFound() {
/* 544 */       this.minusFound = true;
/*     */     }
/*     */     
/*     */     public boolean minusFound() {
/* 551 */       return this.minusFound;
/*     */     }
/*     */   }
/*     */   
/*     */   public SubHyperplane<S> intersection(SubHyperplane<S> sub) {
/* 558 */     return recurseIntersection(this.tree, sub);
/*     */   }
/*     */   
/*     */   private SubHyperplane<S> recurseIntersection(BSPTree<S> node, SubHyperplane<S> sub) {
/*     */     SubHyperplane.SplitSubHyperplane<S> split;
/*     */     SubHyperplane<S> plus, minus;
/* 569 */     if (node.getCut() == null)
/* 570 */       return ((Boolean)node.getAttribute()).booleanValue() ? sub.copySelf() : null; 
/* 573 */     Hyperplane<S> hyperplane = node.getCut().getHyperplane();
/* 574 */     switch (sub.side(hyperplane)) {
/*     */       case PLUS:
/* 576 */         return recurseIntersection(node.getPlus(), sub);
/*     */       case MINUS:
/* 578 */         return recurseIntersection(node.getMinus(), sub);
/*     */       case BOTH:
/* 580 */         split = sub.split(hyperplane);
/* 581 */         plus = recurseIntersection(node.getPlus(), split.getPlus());
/* 582 */         minus = recurseIntersection(node.getMinus(), split.getMinus());
/* 583 */         if (plus == null)
/* 584 */           return minus; 
/* 585 */         if (minus == null)
/* 586 */           return plus; 
/* 588 */         return plus.reunite(minus);
/*     */     } 
/* 591 */     return recurseIntersection(node.getPlus(), recurseIntersection(node.getMinus(), sub));
/*     */   }
/*     */   
/*     */   public AbstractRegion<S, T> applyTransform(Transform<S, T> transform) {
/* 608 */     return buildNew(recurseTransform(getTree(false), transform));
/*     */   }
/*     */   
/*     */   private BSPTree<S> recurseTransform(BSPTree<S> node, Transform<S, T> transform) {
/* 619 */     if (node.getCut() == null)
/* 620 */       return new BSPTree<S>(node.getAttribute()); 
/* 623 */     SubHyperplane<S> sub = node.getCut();
/* 624 */     SubHyperplane<S> tSub = ((AbstractSubHyperplane)sub).applyTransform(transform);
/* 625 */     BoundaryAttribute<S> attribute = (BoundaryAttribute<S>)node.getAttribute();
/* 626 */     if (attribute != null) {
/* 627 */       SubHyperplane<S> tPO = (attribute.getPlusOutside() == null) ? null : ((AbstractSubHyperplane)attribute.getPlusOutside()).applyTransform(transform);
/* 629 */       SubHyperplane<S> tPI = (attribute.getPlusInside() == null) ? null : ((AbstractSubHyperplane)attribute.getPlusInside()).applyTransform(transform);
/* 631 */       attribute = new BoundaryAttribute<S>(tPO, tPI);
/*     */     } 
/* 634 */     return new BSPTree<S>(tSub, recurseTransform(node.getPlus(), transform), recurseTransform(node.getMinus(), transform), attribute);
/*     */   }
/*     */   
/*     */   public abstract AbstractRegion<S, T> buildNew(BSPTree<S> paramBSPTree);
/*     */   
/*     */   protected abstract void computeGeometricalProperties();
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\geometry\partitioning\AbstractRegion.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */