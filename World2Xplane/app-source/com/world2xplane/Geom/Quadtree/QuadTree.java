/*     */ package com.world2xplane.Geom.Quadtree;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class QuadTree {
/*     */   private QuadTreeNode root_;
/*     */   
/*  43 */   private int count_ = 0;
/*     */   
/*     */   public QuadTree(double minX, double minY, double maxX, double maxY) {
/*  54 */     this.root_ = new QuadTreeNode(minX, minY, maxX - minX, maxY - minY, null);
/*     */   }
/*     */   
/*     */   public QuadTreeNode getRootNode() {
/*  64 */     return this.root_;
/*     */   }
/*     */   
/*     */   public void set(double x, double y, Object value) {
/*  76 */     QuadTreeNode root = this.root_;
/*  77 */     if (x < root.getX() || y < root.getY() || x > root.getX() + root.getW() || y > root.getY() + root.getH())
/*  78 */       throw new RuntimeException("Out of bounds : (" + x + ", " + y + ")"); 
/*  80 */     if (insert(root, new QuadTreePoint(x, y, value)))
/*  81 */       this.count_++; 
/*     */   }
/*     */   
/*     */   public Object get(double x, double y, Object opt_default) {
/*  97 */     QuadTreeNode node = find(this.root_, x, y);
/*  98 */     return (node != null) ? node.getPoint().getValue() : opt_default;
/*     */   }
/*     */   
/*     */   public Object remove(double x, double y) {
/* 110 */     QuadTreeNode node = find(this.root_, x, y);
/* 111 */     if (node != null) {
/* 112 */       Object value = node.getPoint().getValue();
/* 113 */       node.setPoint(null);
/* 114 */       node.setNodeType(QuadTreeNode.NodeType.EMPTY);
/* 115 */       balance(node);
/* 116 */       this.count_--;
/* 117 */       return value;
/*     */     } 
/* 119 */     return null;
/*     */   }
/*     */   
/*     */   public boolean contains(double x, double y) {
/* 131 */     return (get(x, y, null) != null);
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 138 */     return (this.root_.getNodeType() == QuadTreeNode.NodeType.EMPTY);
/*     */   }
/*     */   
/*     */   public int getCount() {
/* 145 */     return this.count_;
/*     */   }
/*     */   
/*     */   public void clear() {
/* 152 */     this.root_.setNw(null);
/* 153 */     this.root_.setNe(null);
/* 154 */     this.root_.setSw(null);
/* 155 */     this.root_.setSe(null);
/* 156 */     this.root_.setNodeType(QuadTreeNode.NodeType.EMPTY);
/* 157 */     this.root_.setPoint(null);
/* 158 */     this.count_ = 0;
/*     */   }
/*     */   
/*     */   public QuadTreePoint[] getKeys() {
/* 166 */     final List<QuadTreePoint> arr = new ArrayList<>();
/* 167 */     traverse(this.root_, new Func() {
/*     */           public void call(QuadTree quadTree, QuadTreeNode node) {
/* 169 */             arr.add(node.getPoint());
/*     */           }
/*     */         });
/* 172 */     return arr.<QuadTreePoint>toArray(new QuadTreePoint[arr.size()]);
/*     */   }
/*     */   
/*     */   public Object[] getValues() {
/* 180 */     final List<Object> arr = new ArrayList();
/* 181 */     traverse(this.root_, new Func() {
/*     */           public void call(QuadTree quadTree, QuadTreeNode node) {
/* 183 */             arr.add(node.getPoint().getValue());
/*     */           }
/*     */         });
/* 187 */     return arr.toArray(new Object[arr.size()]);
/*     */   }
/*     */   
/*     */   public QuadTreePoint[] searchIntersect(final double xmin, final double ymin, final double xmax, final double ymax) {
/* 191 */     final List<QuadTreePoint> arr = new ArrayList<>();
/* 192 */     navigate(this.root_, new Func() {
/*     */           public void call(QuadTree quadTree, QuadTreeNode node) {
/* 194 */             QuadTreePoint pt = node.getPoint();
/* 195 */             if (pt.getX() >= xmin && pt.getX() <= xmax && pt.getY() >= ymin && pt.getY() <= ymax)
/* 198 */               arr.add(node.getPoint()); 
/*     */           }
/*     */         },  xmin, ymin, xmax, ymax);
/* 203 */     return arr.<QuadTreePoint>toArray(new QuadTreePoint[arr.size()]);
/*     */   }
/*     */   
/*     */   public QuadTreePoint[] searchWithin(final double xmin, final double ymin, final double xmax, final double ymax) {
/* 207 */     final List<QuadTreePoint> arr = new ArrayList<>();
/* 208 */     navigate(this.root_, new Func() {
/*     */           public void call(QuadTree quadTree, QuadTreeNode node) {
/* 210 */             QuadTreePoint pt = node.getPoint();
/* 211 */             if (pt.getX() > xmin && pt.getX() < xmax && pt.getY() > ymin && pt.getY() < ymax)
/* 212 */               arr.add(node.getPoint()); 
/*     */           }
/*     */         },  xmin, ymin, xmax, ymax);
/* 216 */     return arr.<QuadTreePoint>toArray(new QuadTreePoint[arr.size()]);
/*     */   }
/*     */   
/*     */   public void navigate(QuadTreeNode node, Func func, double xmin, double ymin, double xmax, double ymax) {
/* 220 */     switch (node.getNodeType()) {
/*     */       case LEAF:
/* 222 */         func.call(this, node);
/*     */         break;
/*     */       case POINTER:
/* 226 */         if (intersects(xmin, ymax, xmax, ymin, node.getNe()))
/* 227 */           navigate(node.getNe(), func, xmin, ymin, xmax, ymax); 
/* 228 */         if (intersects(xmin, ymax, xmax, ymin, node.getSe()))
/* 229 */           navigate(node.getSe(), func, xmin, ymin, xmax, ymax); 
/* 230 */         if (intersects(xmin, ymax, xmax, ymin, node.getSw()))
/* 231 */           navigate(node.getSw(), func, xmin, ymin, xmax, ymax); 
/* 232 */         if (intersects(xmin, ymax, xmax, ymin, node.getNw()))
/* 233 */           navigate(node.getNw(), func, xmin, ymin, xmax, ymax); 
/*     */         break;
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean intersects(double left, double bottom, double right, double top, QuadTreeNode node) {
/* 239 */     return (node.getX() <= right && node.getX() + node.getW() >= left && node.getY() <= bottom && node.getY() + node.getH() >= top);
/*     */   }
/*     */   
/*     */   public QuadTree clone() {
/* 249 */     double x1 = this.root_.getX();
/* 250 */     double y1 = this.root_.getY();
/* 251 */     double x2 = x1 + this.root_.getW();
/* 252 */     double y2 = y1 + this.root_.getH();
/* 253 */     final QuadTree clone = new QuadTree(x1, y1, x2, y2);
/* 257 */     traverse(this.root_, new Func() {
/*     */           public void call(QuadTree quadTree, QuadTreeNode node) {
/* 259 */             clone.set(node.getPoint().getX(), node.getPoint().getY(), node.getPoint().getValue());
/*     */           }
/*     */         });
/* 264 */     return clone;
/*     */   }
/*     */   
/*     */   public void traverse(QuadTreeNode node, Func func) {
/* 278 */     switch (node.getNodeType()) {
/*     */       case LEAF:
/* 280 */         func.call(this, node);
/*     */         break;
/*     */       case POINTER:
/* 284 */         traverse(node.getNe(), func);
/* 285 */         traverse(node.getSe(), func);
/* 286 */         traverse(node.getSw(), func);
/* 287 */         traverse(node.getNw(), func);
/*     */         break;
/*     */     } 
/*     */   }
/*     */   
/*     */   public QuadTreeNode find(QuadTreeNode node, double x, double y) {
/* 303 */     QuadTreeNode resposne = null;
/* 304 */     switch (node.getNodeType()) {
/*     */       case EMPTY:
/* 319 */         return resposne;
/*     */       case LEAF:
/*     */         resposne = (node.getPoint().getX() == x && node.getPoint().getY() == y) ? node : null;
/*     */       case POINTER:
/*     */         resposne = find(getQuadrantForPoint(node, x, y), x, y);
/*     */     } 
/*     */     throw new RuntimeException("Invalid nodeType");
/*     */   }
/*     */   
/*     */   private boolean insert(QuadTreeNode parent, QuadTreePoint point) {
/* 333 */     Boolean result = Boolean.valueOf(false);
/* 334 */     switch (parent.getNodeType()) {
/*     */       case EMPTY:
/* 336 */         setPointForNode(parent, point);
/* 337 */         result = Boolean.valueOf(true);
/* 356 */         return result.booleanValue();
/*     */       case LEAF:
/*     */         if (parent.getPoint().getX() == point.getX() && parent.getPoint().getY() == point.getY()) {
/*     */           setPointForNode(parent, point);
/*     */           result = Boolean.valueOf(false);
/*     */         } else {
/*     */           split(parent);
/*     */           result = Boolean.valueOf(insert(parent, point));
/*     */         } 
/* 356 */         return result.booleanValue();
/*     */       case POINTER:
/*     */         result = Boolean.valueOf(insert(getQuadrantForPoint(parent, point.getX(), point.getY()), point));
/* 356 */         return result.booleanValue();
/*     */     } 
/*     */     throw new RuntimeException("Invalid nodeType in parent");
/*     */   }
/*     */   
/*     */   private void split(QuadTreeNode node) {
/* 366 */     QuadTreePoint oldPoint = node.getPoint();
/* 367 */     node.setPoint(null);
/* 369 */     node.setNodeType(QuadTreeNode.NodeType.POINTER);
/* 371 */     double x = node.getX();
/* 372 */     double y = node.getY();
/* 373 */     double hw = node.getW() / 2.0D;
/* 374 */     double hh = node.getH() / 2.0D;
/* 376 */     node.setNw(new QuadTreeNode(x, y, hw, hh, node));
/* 377 */     node.setNe(new QuadTreeNode(x + hw, y, hw, hh, node));
/* 378 */     node.setSw(new QuadTreeNode(x, y + hh, hw, hh, node));
/* 379 */     node.setSe(new QuadTreeNode(x + hw, y + hh, hw, hh, node));
/* 381 */     insert(node, oldPoint);
/*     */   }
/*     */   
/*     */   private void balance(QuadTreeNode node) {
/*     */     QuadTreeNode nw;
/*     */     QuadTreeNode ne;
/*     */     QuadTreeNode sw;
/*     */     QuadTreeNode se;
/*     */     QuadTreeNode firstLeaf;
/* 391 */     switch (node.getNodeType()) {
/*     */       case LEAF:
/*     */       case EMPTY:
/* 394 */         if (node.getParent() != null)
/* 395 */           balance(node.getParent()); 
/*     */         break;
/*     */       case POINTER:
/* 400 */         nw = node.getNw();
/* 401 */         ne = node.getNe();
/* 402 */         sw = node.getSw();
/* 403 */         se = node.getSe();
/* 404 */         firstLeaf = null;
/* 408 */         if (nw.getNodeType() != QuadTreeNode.NodeType.EMPTY)
/* 409 */           firstLeaf = nw; 
/* 411 */         if (ne.getNodeType() != QuadTreeNode.NodeType.EMPTY) {
/* 412 */           if (firstLeaf != null)
/*     */             break; 
/* 415 */           firstLeaf = ne;
/*     */         } 
/* 417 */         if (sw.getNodeType() != QuadTreeNode.NodeType.EMPTY) {
/* 418 */           if (firstLeaf != null)
/*     */             break; 
/* 421 */           firstLeaf = sw;
/*     */         } 
/* 423 */         if (se.getNodeType() != QuadTreeNode.NodeType.EMPTY) {
/* 424 */           if (firstLeaf != null)
/*     */             break; 
/* 427 */           firstLeaf = se;
/*     */         } 
/* 430 */         if (firstLeaf == null) {
/* 432 */           node.setNodeType(QuadTreeNode.NodeType.EMPTY);
/* 433 */           node.setNw(null);
/* 434 */           node.setNe(null);
/* 435 */           node.setSw(null);
/* 436 */           node.setSe(null);
/*     */         } else {
/* 438 */           if (firstLeaf.getNodeType() == QuadTreeNode.NodeType.POINTER)
/*     */             break; 
/* 444 */           node.setNodeType(QuadTreeNode.NodeType.LEAF);
/* 445 */           node.setNw(null);
/* 446 */           node.setNe(null);
/* 447 */           node.setSw(null);
/* 448 */           node.setSe(null);
/* 449 */           node.setPoint(firstLeaf.getPoint());
/*     */         } 
/* 453 */         if (node.getParent() != null)
/* 454 */           balance(node.getParent()); 
/*     */         break;
/*     */     } 
/*     */   }
/*     */   
/*     */   private QuadTreeNode getQuadrantForPoint(QuadTreeNode parent, double x, double y) {
/* 472 */     double mx = parent.getX() + parent.getW() / 2.0D;
/* 473 */     double my = parent.getY() + parent.getH() / 2.0D;
/* 474 */     if (x < mx)
/* 475 */       return (y < my) ? parent.getNw() : parent.getSw(); 
/* 477 */     return (y < my) ? parent.getNe() : parent.getSe();
/*     */   }
/*     */   
/*     */   private void setPointForNode(QuadTreeNode node, QuadTreePoint point) {
/* 488 */     if (node.getNodeType() == QuadTreeNode.NodeType.POINTER)
/* 489 */       throw new RuntimeException("Can not set point for node of type POINTER"); 
/* 491 */     node.setNodeType(QuadTreeNode.NodeType.LEAF);
/* 492 */     node.setPoint(point);
/*     */   }
/*     */   
/*     */   public static interface Func {
/*     */     void call(QuadTree param1QuadTree, QuadTreeNode param1QuadTreeNode);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\Geom\Quadtree\QuadTree.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */