/*     */ package com.vividsolutions.jts.index.strtree;
/*     */ 
/*     */ import java.util.Comparator;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ 
/*     */ public class SIRtree extends AbstractSTRtree {
/*  51 */   private Comparator comparator = new Comparator() {
/*     */       public int compare(Object o1, Object o2) {
/*  53 */         return AbstractSTRtree.compareDoubles(((Interval)((Boundable)o1).getBounds()).getCentre(), ((Interval)((Boundable)o2).getBounds()).getCentre());
/*     */       }
/*     */     };
/*     */   
/*  59 */   private AbstractSTRtree.IntersectsOp intersectsOp = new AbstractSTRtree.IntersectsOp() {
/*     */       public boolean intersects(Object aBounds, Object bBounds) {
/*  61 */         return ((Interval)aBounds).intersects((Interval)bBounds);
/*     */       }
/*     */     };
/*     */   
/*     */   public SIRtree() {
/*  68 */     this(10);
/*     */   }
/*     */   
/*     */   public SIRtree(int nodeCapacity) {
/*  75 */     super(nodeCapacity);
/*     */   }
/*     */   
/*     */   protected AbstractNode createNode(int level) {
/*  79 */     return new AbstractNode(level) {
/*     */         protected Object computeBounds() {
/*  81 */           Interval bounds = null;
/*  82 */           for (Iterator<Boundable> i = getChildBoundables().iterator(); i.hasNext(); ) {
/*  83 */             Boundable childBoundable = i.next();
/*  84 */             if (bounds == null) {
/*  85 */               bounds = new Interval((Interval)childBoundable.getBounds());
/*     */               continue;
/*     */             } 
/*  88 */             bounds.expandToInclude((Interval)childBoundable.getBounds());
/*     */           } 
/*  91 */           return bounds;
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public void insert(double x1, double x2, Object item) {
/* 100 */     insert(new Interval(Math.min(x1, x2), Math.max(x1, x2)), item);
/*     */   }
/*     */   
/*     */   public List query(double x) {
/* 107 */     return query(x, x);
/*     */   }
/*     */   
/*     */   public List query(double x1, double x2) {
/* 115 */     return query(new Interval(Math.min(x1, x2), Math.max(x1, x2)));
/*     */   }
/*     */   
/*     */   protected AbstractSTRtree.IntersectsOp getIntersectsOp() {
/* 119 */     return this.intersectsOp;
/*     */   }
/*     */   
/*     */   protected Comparator getComparator() {
/* 123 */     return this.comparator;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\index\strtree\SIRtree.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */