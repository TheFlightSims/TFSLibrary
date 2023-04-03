/*     */ package com.vividsolutions.jts.index.strtree;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ import com.vividsolutions.jts.index.ItemVisitor;
/*     */ import com.vividsolutions.jts.index.SpatialIndex;
/*     */ import com.vividsolutions.jts.util.Assert;
/*     */ import com.vividsolutions.jts.util.PriorityQueue;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ 
/*     */ public class STRtree extends AbstractSTRtree implements SpatialIndex, Serializable {
/*     */   private static final long serialVersionUID = 259274702368956900L;
/*     */   
/*     */   private static final class STRtreeNode extends AbstractNode {
/*     */     private STRtreeNode(int level) {
/*  69 */       super(level);
/*     */     }
/*     */     
/*     */     protected Object computeBounds() {
/*  73 */       Envelope bounds = null;
/*  74 */       for (Iterator<Boundable> i = getChildBoundables().iterator(); i.hasNext(); ) {
/*  75 */         Boundable childBoundable = i.next();
/*  76 */         if (bounds == null) {
/*  77 */           bounds = new Envelope((Envelope)childBoundable.getBounds());
/*     */           continue;
/*     */         } 
/*  80 */         bounds.expandToInclude((Envelope)childBoundable.getBounds());
/*     */       } 
/*  83 */       return bounds;
/*     */     }
/*     */   }
/*     */   
/*  92 */   private static Comparator xComparator = new Comparator() {
/*     */       public int compare(Object o1, Object o2) {
/*  95 */         return AbstractSTRtree.compareDoubles(STRtree.centreX((Envelope)((Boundable)o1).getBounds()), STRtree.centreX((Envelope)((Boundable)o2).getBounds()));
/*     */       }
/*     */     };
/*     */   
/* 100 */   private static Comparator yComparator = new Comparator() {
/*     */       public int compare(Object o1, Object o2) {
/* 103 */         return AbstractSTRtree.compareDoubles(STRtree.centreY((Envelope)((Boundable)o1).getBounds()), STRtree.centreY((Envelope)((Boundable)o2).getBounds()));
/*     */       }
/*     */     };
/*     */   
/*     */   private static double centreX(Envelope e) {
/* 110 */     return avg(e.getMinX(), e.getMaxX());
/*     */   }
/*     */   
/*     */   private static double centreY(Envelope e) {
/* 114 */     return avg(e.getMinY(), e.getMaxY());
/*     */   }
/*     */   
/*     */   private static double avg(double a, double b) {
/* 117 */     return (a + b) / 2.0D;
/*     */   }
/*     */   
/* 119 */   private static AbstractSTRtree.IntersectsOp intersectsOp = new AbstractSTRtree.IntersectsOp() {
/*     */       public boolean intersects(Object aBounds, Object bBounds) {
/* 121 */         return ((Envelope)aBounds).intersects((Envelope)bBounds);
/*     */       }
/*     */     };
/*     */   
/*     */   private static final int DEFAULT_NODE_CAPACITY = 10;
/*     */   
/*     */   protected List createParentBoundables(List<?> childBoundables, int newLevel) {
/* 133 */     Assert.isTrue(!childBoundables.isEmpty());
/* 134 */     int minLeafCount = (int)Math.ceil(childBoundables.size() / getNodeCapacity());
/* 135 */     ArrayList<?> sortedChildBoundables = new ArrayList(childBoundables);
/* 136 */     Collections.sort(sortedChildBoundables, xComparator);
/* 137 */     List[] verticalSlices = verticalSlices(sortedChildBoundables, (int)Math.ceil(Math.sqrt(minLeafCount)));
/* 139 */     return createParentBoundablesFromVerticalSlices(verticalSlices, newLevel);
/*     */   }
/*     */   
/*     */   private List createParentBoundablesFromVerticalSlices(List[] verticalSlices, int newLevel) {
/* 143 */     Assert.isTrue((verticalSlices.length > 0));
/* 144 */     List parentBoundables = new ArrayList();
/* 145 */     for (int i = 0; i < verticalSlices.length; i++)
/* 146 */       parentBoundables.addAll(createParentBoundablesFromVerticalSlice(verticalSlices[i], newLevel)); 
/* 149 */     return parentBoundables;
/*     */   }
/*     */   
/*     */   protected List createParentBoundablesFromVerticalSlice(List childBoundables, int newLevel) {
/* 153 */     return super.createParentBoundables(childBoundables, newLevel);
/*     */   }
/*     */   
/*     */   protected List[] verticalSlices(List childBoundables, int sliceCount) {
/* 160 */     int sliceCapacity = (int)Math.ceil(childBoundables.size() / sliceCount);
/* 161 */     List[] slices = new List[sliceCount];
/* 162 */     Iterator<Boundable> i = childBoundables.iterator();
/* 163 */     for (int j = 0; j < sliceCount; j++) {
/* 164 */       slices[j] = new ArrayList();
/* 165 */       int boundablesAddedToSlice = 0;
/* 166 */       while (i.hasNext() && boundablesAddedToSlice < sliceCapacity) {
/* 167 */         Boundable childBoundable = i.next();
/* 168 */         slices[j].add(childBoundable);
/* 169 */         boundablesAddedToSlice++;
/*     */       } 
/*     */     } 
/* 172 */     return slices;
/*     */   }
/*     */   
/*     */   public STRtree() {
/* 182 */     this(10);
/*     */   }
/*     */   
/*     */   public STRtree(int nodeCapacity) {
/* 193 */     super(nodeCapacity);
/*     */   }
/*     */   
/*     */   protected AbstractNode createNode(int level) {
/* 197 */     return new STRtreeNode(level);
/*     */   }
/*     */   
/*     */   protected AbstractSTRtree.IntersectsOp getIntersectsOp() {
/* 201 */     return intersectsOp;
/*     */   }
/*     */   
/*     */   public void insert(Envelope itemEnv, Object item) {
/* 208 */     if (itemEnv.isNull())
/*     */       return; 
/* 209 */     insert(itemEnv, item);
/*     */   }
/*     */   
/*     */   public List query(Envelope searchEnv) {
/* 218 */     return query(searchEnv);
/*     */   }
/*     */   
/*     */   public void query(Envelope searchEnv, ItemVisitor visitor) {
/* 227 */     query(searchEnv, visitor);
/*     */   }
/*     */   
/*     */   public boolean remove(Envelope itemEnv, Object item) {
/* 238 */     return remove(itemEnv, item);
/*     */   }
/*     */   
/*     */   public int size() {
/* 248 */     return super.size();
/*     */   }
/*     */   
/*     */   public int depth() {
/* 258 */     return super.depth();
/*     */   }
/*     */   
/*     */   protected Comparator getComparator() {
/* 262 */     return yComparator;
/*     */   }
/*     */   
/*     */   public Object[] nearestNeighbour(ItemDistance itemDist) {
/* 276 */     BoundablePair bp = new BoundablePair(getRoot(), getRoot(), itemDist);
/* 277 */     return nearestNeighbour(bp);
/*     */   }
/*     */   
/*     */   public Object nearestNeighbour(Envelope env, Object item, ItemDistance itemDist) {
/* 298 */     Boundable bnd = new ItemBoundable(env, item);
/* 299 */     BoundablePair bp = new BoundablePair(getRoot(), bnd, itemDist);
/* 300 */     return nearestNeighbour(bp)[0];
/*     */   }
/*     */   
/*     */   public Object[] nearestNeighbour(STRtree tree, ItemDistance itemDist) {
/* 319 */     BoundablePair bp = new BoundablePair(getRoot(), tree.getRoot(), itemDist);
/* 320 */     return nearestNeighbour(bp);
/*     */   }
/*     */   
/*     */   private Object[] nearestNeighbour(BoundablePair initBndPair) {
/* 325 */     return nearestNeighbour(initBndPair, Double.POSITIVE_INFINITY);
/*     */   }
/*     */   
/*     */   private Object[] nearestNeighbour(BoundablePair initBndPair, double maxDistance) {
/* 330 */     double distanceLowerBound = maxDistance;
/* 331 */     BoundablePair minPair = null;
/* 334 */     PriorityQueue priQ = new PriorityQueue();
/* 337 */     priQ.add(initBndPair);
/* 339 */     while (!priQ.isEmpty() && distanceLowerBound > 0.0D) {
/* 341 */       BoundablePair bndPair = (BoundablePair)priQ.poll();
/* 342 */       double currentDistance = bndPair.getDistance();
/* 351 */       if (currentDistance >= distanceLowerBound)
/*     */         break; 
/* 361 */       if (bndPair.isLeaves()) {
/* 363 */         distanceLowerBound = currentDistance;
/* 364 */         minPair = bndPair;
/*     */         continue;
/*     */       } 
/* 380 */       bndPair.expandToQueue(priQ, distanceLowerBound);
/*     */     } 
/* 384 */     return new Object[] { ((ItemBoundable)minPair.getBoundable(0)).getItem(), ((ItemBoundable)minPair.getBoundable(1)).getItem() };
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\index\strtree\STRtree.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */