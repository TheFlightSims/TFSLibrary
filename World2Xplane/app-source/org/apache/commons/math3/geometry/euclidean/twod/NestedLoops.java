/*     */ package org.apache.commons.math3.geometry.euclidean.twod;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import org.apache.commons.math3.exception.MathIllegalArgumentException;
/*     */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*     */ import org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D;
/*     */ import org.apache.commons.math3.geometry.euclidean.oned.IntervalsSet;
/*     */ import org.apache.commons.math3.geometry.partitioning.Region;
/*     */ import org.apache.commons.math3.geometry.partitioning.RegionFactory;
/*     */ import org.apache.commons.math3.geometry.partitioning.SubHyperplane;
/*     */ 
/*     */ class NestedLoops {
/*     */   private Vector2D[] loop;
/*     */   
/*     */   private ArrayList<NestedLoops> surrounded;
/*     */   
/*     */   private Region<Euclidean2D> polygon;
/*     */   
/*     */   private boolean originalIsClockwise;
/*     */   
/*     */   public NestedLoops() {
/*  68 */     this.surrounded = new ArrayList<NestedLoops>();
/*     */   }
/*     */   
/*     */   private NestedLoops(Vector2D[] loop) throws MathIllegalArgumentException {
/*  78 */     if (loop[0] == null)
/*  79 */       throw new MathIllegalArgumentException(LocalizedFormats.OUTLINE_BOUNDARY_LOOP_OPEN, new Object[0]); 
/*  82 */     this.loop = loop;
/*  83 */     this.surrounded = new ArrayList<NestedLoops>();
/*  86 */     ArrayList<SubHyperplane<Euclidean2D>> edges = new ArrayList<SubHyperplane<Euclidean2D>>();
/*  87 */     Vector2D current = loop[loop.length - 1];
/*  88 */     for (int i = 0; i < loop.length; i++) {
/*  89 */       Vector2D previous = current;
/*  90 */       current = loop[i];
/*  91 */       Line line = new Line(previous, current);
/*  92 */       IntervalsSet region = new IntervalsSet(line.toSubSpace(previous).getX(), line.toSubSpace(current).getX());
/*  94 */       edges.add(new SubLine(line, (Region<Euclidean1D>)region));
/*     */     } 
/*  96 */     this.polygon = (Region<Euclidean2D>)new PolygonsSet(edges);
/*  99 */     if (Double.isInfinite(this.polygon.getSize())) {
/* 100 */       this.polygon = (new RegionFactory()).getComplement(this.polygon);
/* 101 */       this.originalIsClockwise = false;
/*     */     } else {
/* 103 */       this.originalIsClockwise = true;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void add(Vector2D[] bLoop) throws MathIllegalArgumentException {
/* 114 */     add(new NestedLoops(bLoop));
/*     */   }
/*     */   
/*     */   private void add(NestedLoops node) throws MathIllegalArgumentException {
/* 125 */     for (NestedLoops child : this.surrounded) {
/* 126 */       if (child.polygon.contains(node.polygon)) {
/* 127 */         child.add(node);
/*     */         return;
/*     */       } 
/*     */     } 
/* 133 */     for (Iterator<NestedLoops> iterator = this.surrounded.iterator(); iterator.hasNext(); ) {
/* 134 */       NestedLoops child = iterator.next();
/* 135 */       if (node.polygon.contains(child.polygon)) {
/* 136 */         node.surrounded.add(child);
/* 137 */         iterator.remove();
/*     */       } 
/*     */     } 
/* 142 */     RegionFactory<Euclidean2D> factory = new RegionFactory();
/* 143 */     for (NestedLoops child : this.surrounded) {
/* 144 */       if (!factory.intersection(node.polygon, child.polygon).isEmpty())
/* 145 */         throw new MathIllegalArgumentException(LocalizedFormats.CROSSING_BOUNDARY_LOOPS, new Object[0]); 
/*     */     } 
/* 149 */     this.surrounded.add(node);
/*     */   }
/*     */   
/*     */   public void correctOrientation() {
/* 159 */     for (NestedLoops child : this.surrounded)
/* 160 */       child.setClockWise(true); 
/*     */   }
/*     */   
/*     */   private void setClockWise(boolean clockwise) {
/* 170 */     if (this.originalIsClockwise ^ clockwise) {
/* 172 */       int min = -1;
/* 173 */       int max = this.loop.length;
/* 174 */       while (++min < --max) {
/* 175 */         Vector2D tmp = this.loop[min];
/* 176 */         this.loop[min] = this.loop[max];
/* 177 */         this.loop[max] = tmp;
/*     */       } 
/*     */     } 
/* 182 */     for (NestedLoops child : this.surrounded)
/* 183 */       child.setClockWise(!clockwise); 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\geometry\euclidean\twod\NestedLoops.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */