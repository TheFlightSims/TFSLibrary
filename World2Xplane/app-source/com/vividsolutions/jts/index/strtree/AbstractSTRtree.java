/*     */ package com.vividsolutions.jts.index.strtree;
/*     */ 
/*     */ import com.vividsolutions.jts.index.ItemVisitor;
/*     */ import com.vividsolutions.jts.util.Assert;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ 
/*     */ public abstract class AbstractSTRtree implements Serializable {
/*     */   private static final long serialVersionUID = -3886435814360241337L;
/*     */   
/*     */   protected AbstractNode root;
/*     */   
/*     */   private boolean built = false;
/*     */   
/*  84 */   private ArrayList itemBoundables = new ArrayList();
/*     */   
/*     */   private int nodeCapacity;
/*     */   
/*     */   private static final int DEFAULT_NODE_CAPACITY = 10;
/*     */   
/*     */   public AbstractSTRtree() {
/*  95 */     this(10);
/*     */   }
/*     */   
/*     */   public AbstractSTRtree(int nodeCapacity) {
/* 105 */     Assert.isTrue((nodeCapacity > 1), "Node capacity must be greater than 1");
/* 106 */     this.nodeCapacity = nodeCapacity;
/*     */   }
/*     */   
/*     */   public synchronized void build() {
/* 116 */     if (this.built)
/*     */       return; 
/* 117 */     this.root = this.itemBoundables.isEmpty() ? createNode(0) : createHigherLevels(this.itemBoundables, -1);
/* 121 */     this.itemBoundables = null;
/* 122 */     this.built = true;
/*     */   }
/*     */   
/*     */   protected abstract AbstractNode createNode(int paramInt);
/*     */   
/*     */   protected List createParentBoundables(List<?> childBoundables, int newLevel) {
/* 132 */     Assert.isTrue(!childBoundables.isEmpty());
/* 133 */     ArrayList<AbstractNode> parentBoundables = new ArrayList();
/* 134 */     parentBoundables.add(createNode(newLevel));
/* 135 */     ArrayList<?> sortedChildBoundables = new ArrayList(childBoundables);
/* 136 */     Collections.sort(sortedChildBoundables, getComparator());
/* 137 */     for (Iterator<?> i = sortedChildBoundables.iterator(); i.hasNext(); ) {
/* 138 */       Boundable childBoundable = (Boundable)i.next();
/* 139 */       if (lastNode(parentBoundables).getChildBoundables().size() == getNodeCapacity())
/* 140 */         parentBoundables.add(createNode(newLevel)); 
/* 142 */       lastNode(parentBoundables).addChildBoundable(childBoundable);
/*     */     } 
/* 144 */     return parentBoundables;
/*     */   }
/*     */   
/*     */   protected AbstractNode lastNode(List<AbstractNode> nodes) {
/* 148 */     return nodes.get(nodes.size() - 1);
/*     */   }
/*     */   
/*     */   protected static int compareDoubles(double a, double b) {
/* 152 */     return (a > b) ? 1 : ((a < b) ? -1 : 0);
/*     */   }
/*     */   
/*     */   private AbstractNode createHigherLevels(List boundablesOfALevel, int level) {
/* 168 */     Assert.isTrue(!boundablesOfALevel.isEmpty());
/* 169 */     List<AbstractNode> parentBoundables = createParentBoundables(boundablesOfALevel, level + 1);
/* 170 */     if (parentBoundables.size() == 1)
/* 171 */       return parentBoundables.get(0); 
/* 173 */     return createHigherLevels(parentBoundables, level + 1);
/*     */   }
/*     */   
/*     */   public AbstractNode getRoot() {
/* 178 */     build();
/* 179 */     return this.root;
/*     */   }
/*     */   
/*     */   public int getNodeCapacity() {
/* 185 */     return this.nodeCapacity;
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 196 */     if (!this.built)
/* 196 */       return this.itemBoundables.isEmpty(); 
/* 197 */     return this.root.isEmpty();
/*     */   }
/*     */   
/*     */   protected int size() {
/* 201 */     if (isEmpty())
/* 202 */       return 0; 
/* 204 */     build();
/* 205 */     return size(this.root);
/*     */   }
/*     */   
/*     */   protected int size(AbstractNode node) {
/* 210 */     int size = 0;
/* 211 */     for (Iterator<Boundable> i = node.getChildBoundables().iterator(); i.hasNext(); ) {
/* 212 */       Boundable childBoundable = i.next();
/* 213 */       if (childBoundable instanceof AbstractNode) {
/* 214 */         size += size((AbstractNode)childBoundable);
/*     */         continue;
/*     */       } 
/* 216 */       if (childBoundable instanceof ItemBoundable)
/* 217 */         size++; 
/*     */     } 
/* 220 */     return size;
/*     */   }
/*     */   
/*     */   protected int depth() {
/* 224 */     if (isEmpty())
/* 225 */       return 0; 
/* 227 */     build();
/* 228 */     return depth(this.root);
/*     */   }
/*     */   
/*     */   protected int depth(AbstractNode node) {
/* 233 */     int maxChildDepth = 0;
/* 234 */     for (Iterator<Boundable> i = node.getChildBoundables().iterator(); i.hasNext(); ) {
/* 235 */       Boundable childBoundable = i.next();
/* 236 */       if (childBoundable instanceof AbstractNode) {
/* 237 */         int childDepth = depth((AbstractNode)childBoundable);
/* 238 */         if (childDepth > maxChildDepth)
/* 239 */           maxChildDepth = childDepth; 
/*     */       } 
/*     */     } 
/* 242 */     return maxChildDepth + 1;
/*     */   }
/*     */   
/*     */   protected void insert(Object bounds, Object item) {
/* 247 */     Assert.isTrue(!this.built, "Cannot insert items into an STR packed R-tree after it has been built.");
/* 248 */     this.itemBoundables.add(new ItemBoundable(bounds, item));
/*     */   }
/*     */   
/*     */   protected List query(Object searchBounds) {
/* 255 */     build();
/* 256 */     ArrayList matches = new ArrayList();
/* 257 */     if (isEmpty())
/* 259 */       return matches; 
/* 261 */     if (getIntersectsOp().intersects(this.root.getBounds(), searchBounds))
/* 262 */       query(searchBounds, this.root, matches); 
/* 264 */     return matches;
/*     */   }
/*     */   
/*     */   protected void query(Object searchBounds, ItemVisitor visitor) {
/* 271 */     build();
/* 272 */     if (isEmpty())
/*     */       return; 
/* 277 */     if (getIntersectsOp().intersects(this.root.getBounds(), searchBounds))
/* 278 */       query(searchBounds, this.root, visitor); 
/*     */   }
/*     */   
/*     */   protected abstract IntersectsOp getIntersectsOp();
/*     */   
/*     */   private void query(Object searchBounds, AbstractNode node, List<Object> matches) {
/* 290 */     List<Boundable> childBoundables = node.getChildBoundables();
/* 291 */     for (int i = 0; i < childBoundables.size(); i++) {
/* 292 */       Boundable childBoundable = childBoundables.get(i);
/* 293 */       if (getIntersectsOp().intersects(childBoundable.getBounds(), searchBounds))
/* 296 */         if (childBoundable instanceof AbstractNode) {
/* 297 */           query(searchBounds, (AbstractNode)childBoundable, matches);
/* 299 */         } else if (childBoundable instanceof ItemBoundable) {
/* 300 */           matches.add(((ItemBoundable)childBoundable).getItem());
/*     */         } else {
/* 303 */           Assert.shouldNeverReachHere();
/*     */         }  
/*     */     } 
/*     */   }
/*     */   
/*     */   private void query(Object searchBounds, AbstractNode node, ItemVisitor visitor) {
/* 309 */     List<Boundable> childBoundables = node.getChildBoundables();
/* 310 */     for (int i = 0; i < childBoundables.size(); i++) {
/* 311 */       Boundable childBoundable = childBoundables.get(i);
/* 312 */       if (getIntersectsOp().intersects(childBoundable.getBounds(), searchBounds))
/* 315 */         if (childBoundable instanceof AbstractNode) {
/* 316 */           query(searchBounds, (AbstractNode)childBoundable, visitor);
/* 318 */         } else if (childBoundable instanceof ItemBoundable) {
/* 319 */           visitor.visitItem(((ItemBoundable)childBoundable).getItem());
/*     */         } else {
/* 322 */           Assert.shouldNeverReachHere();
/*     */         }  
/*     */     } 
/*     */   }
/*     */   
/*     */   public List itemsTree() {
/* 341 */     build();
/* 343 */     List valuesTree = itemsTree(this.root);
/* 344 */     if (valuesTree == null)
/* 345 */       return new ArrayList(); 
/* 346 */     return valuesTree;
/*     */   }
/*     */   
/*     */   private List itemsTree(AbstractNode node) {
/* 351 */     List<List> valuesTreeForNode = new ArrayList();
/* 352 */     for (Iterator<Boundable> i = node.getChildBoundables().iterator(); i.hasNext(); ) {
/* 353 */       Boundable childBoundable = i.next();
/* 354 */       if (childBoundable instanceof AbstractNode) {
/* 355 */         List valuesTreeForChild = itemsTree((AbstractNode)childBoundable);
/* 357 */         if (valuesTreeForChild != null)
/* 358 */           valuesTreeForNode.add(valuesTreeForChild); 
/*     */         continue;
/*     */       } 
/* 360 */       if (childBoundable instanceof ItemBoundable) {
/* 361 */         valuesTreeForNode.add(((ItemBoundable)childBoundable).getItem());
/*     */         continue;
/*     */       } 
/* 364 */       Assert.shouldNeverReachHere();
/*     */     } 
/* 367 */     if (valuesTreeForNode.size() <= 0)
/* 368 */       return null; 
/* 369 */     return valuesTreeForNode;
/*     */   }
/*     */   
/*     */   protected boolean remove(Object searchBounds, Object item) {
/* 377 */     build();
/* 378 */     if (this.itemBoundables.isEmpty())
/* 379 */       Assert.isTrue((this.root.getBounds() == null)); 
/* 381 */     if (getIntersectsOp().intersects(this.root.getBounds(), searchBounds))
/* 382 */       return remove(searchBounds, this.root, item); 
/* 384 */     return false;
/*     */   }
/*     */   
/*     */   private boolean removeItem(AbstractNode node, Object item) {
/* 389 */     Boundable childToRemove = null;
/* 390 */     for (Iterator<Boundable> i = node.getChildBoundables().iterator(); i.hasNext(); ) {
/* 391 */       Boundable childBoundable = i.next();
/* 392 */       if (childBoundable instanceof ItemBoundable && (
/* 393 */         (ItemBoundable)childBoundable).getItem() == item)
/* 394 */         childToRemove = childBoundable; 
/*     */     } 
/* 397 */     if (childToRemove != null) {
/* 398 */       node.getChildBoundables().remove(childToRemove);
/* 399 */       return true;
/*     */     } 
/* 401 */     return false;
/*     */   }
/*     */   
/*     */   private boolean remove(Object searchBounds, AbstractNode node, Object item) {
/* 406 */     boolean found = removeItem(node, item);
/* 407 */     if (found)
/* 408 */       return true; 
/* 410 */     AbstractNode childToPrune = null;
/* 412 */     for (Iterator<Boundable> i = node.getChildBoundables().iterator(); i.hasNext(); ) {
/* 413 */       Boundable childBoundable = i.next();
/* 414 */       if (!getIntersectsOp().intersects(childBoundable.getBounds(), searchBounds))
/*     */         continue; 
/* 417 */       if (childBoundable instanceof AbstractNode) {
/* 418 */         found = remove(searchBounds, (AbstractNode)childBoundable, item);
/* 420 */         if (found) {
/* 421 */           childToPrune = (AbstractNode)childBoundable;
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/* 427 */     if (childToPrune != null && 
/* 428 */       childToPrune.getChildBoundables().isEmpty())
/* 429 */       node.getChildBoundables().remove(childToPrune); 
/* 432 */     return found;
/*     */   }
/*     */   
/*     */   protected List boundablesAtLevel(int level) {
/* 436 */     ArrayList boundables = new ArrayList();
/* 437 */     boundablesAtLevel(level, this.root, boundables);
/* 438 */     return boundables;
/*     */   }
/*     */   
/*     */   private void boundablesAtLevel(int level, AbstractNode top, Collection<AbstractNode> boundables) {
/* 445 */     Assert.isTrue((level > -2));
/* 446 */     if (top.getLevel() == level) {
/* 447 */       boundables.add(top);
/*     */       return;
/*     */     } 
/* 450 */     for (Iterator<Boundable> i = top.getChildBoundables().iterator(); i.hasNext(); ) {
/* 451 */       Boundable boundable = i.next();
/* 452 */       if (boundable instanceof AbstractNode) {
/* 453 */         boundablesAtLevel(level, (AbstractNode)boundable, boundables);
/*     */         continue;
/*     */       } 
/* 456 */       Assert.isTrue(boundable instanceof ItemBoundable);
/* 457 */       if (level == -1)
/* 457 */         boundables.add(boundable); 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected abstract Comparator getComparator();
/*     */   
/*     */   protected static interface IntersectsOp {
/*     */     boolean intersects(Object param1Object1, Object param1Object2);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\index\strtree\AbstractSTRtree.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */