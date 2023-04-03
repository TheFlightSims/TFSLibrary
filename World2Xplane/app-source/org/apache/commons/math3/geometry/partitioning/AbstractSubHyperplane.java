/*     */ package org.apache.commons.math3.geometry.partitioning;
/*     */ 
/*     */ import org.apache.commons.math3.geometry.Space;
/*     */ 
/*     */ public abstract class AbstractSubHyperplane<S extends Space, T extends Space> implements SubHyperplane<S> {
/*     */   private final Hyperplane<S> hyperplane;
/*     */   
/*     */   private final Region<T> remainingRegion;
/*     */   
/*     */   protected AbstractSubHyperplane(Hyperplane<S> hyperplane, Region<T> remainingRegion) {
/*  52 */     this.hyperplane = hyperplane;
/*  53 */     this.remainingRegion = remainingRegion;
/*     */   }
/*     */   
/*     */   public AbstractSubHyperplane<S, T> copySelf() {
/*  66 */     return buildNew(this.hyperplane, this.remainingRegion);
/*     */   }
/*     */   
/*     */   public Hyperplane<S> getHyperplane() {
/*  73 */     return this.hyperplane;
/*     */   }
/*     */   
/*     */   public Region<T> getRemainingRegion() {
/*  84 */     return this.remainingRegion;
/*     */   }
/*     */   
/*     */   public double getSize() {
/*  89 */     return this.remainingRegion.getSize();
/*     */   }
/*     */   
/*     */   public AbstractSubHyperplane<S, T> reunite(SubHyperplane<S> other) {
/*  95 */     AbstractSubHyperplane<S, T> o = (AbstractSubHyperplane)other;
/*  96 */     return buildNew(this.hyperplane, (new RegionFactory<T>()).union(this.remainingRegion, o.remainingRegion));
/*     */   }
/*     */   
/*     */   public AbstractSubHyperplane<S, T> applyTransform(Transform<S, T> transform) {
/* 111 */     Hyperplane<S> tHyperplane = transform.apply(this.hyperplane);
/* 112 */     BSPTree<T> tTree = recurseTransform(this.remainingRegion.getTree(false), tHyperplane, transform);
/* 114 */     return buildNew(tHyperplane, this.remainingRegion.buildNew(tTree));
/*     */   }
/*     */   
/*     */   private BSPTree<T> recurseTransform(BSPTree<T> node, Hyperplane<S> transformed, Transform<S, T> transform) {
/* 126 */     if (node.getCut() == null)
/* 127 */       return new BSPTree<T>(node.getAttribute()); 
/* 131 */     BoundaryAttribute<T> attribute = (BoundaryAttribute<T>)node.getAttribute();
/* 133 */     if (attribute != null) {
/* 134 */       SubHyperplane<T> tPO = (attribute.getPlusOutside() == null) ? null : transform.apply(attribute.getPlusOutside(), this.hyperplane, transformed);
/* 136 */       SubHyperplane<T> tPI = (attribute.getPlusInside() == null) ? null : transform.apply(attribute.getPlusInside(), this.hyperplane, transformed);
/* 138 */       attribute = new BoundaryAttribute<T>(tPO, tPI);
/*     */     } 
/* 141 */     return new BSPTree<T>(transform.apply(node.getCut(), this.hyperplane, transformed), recurseTransform(node.getPlus(), transformed, transform), recurseTransform(node.getMinus(), transformed, transform), attribute);
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 156 */     return this.remainingRegion.isEmpty();
/*     */   }
/*     */   
/*     */   protected abstract AbstractSubHyperplane<S, T> buildNew(Hyperplane<S> paramHyperplane, Region<T> paramRegion);
/*     */   
/*     */   public abstract Side side(Hyperplane<S> paramHyperplane);
/*     */   
/*     */   public abstract SubHyperplane.SplitSubHyperplane<S> split(Hyperplane<S> paramHyperplane);
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\geometry\partitioning\AbstractSubHyperplane.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */