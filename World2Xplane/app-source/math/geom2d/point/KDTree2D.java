/*     */ package math.geom2d.point;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import java.util.List;
/*     */ import math.geom2d.Box2D;
/*     */ import math.geom2d.Point2D;
/*     */ import math.geom2d.Vector2D;
/*     */ import math.geom2d.line.StraightLine2D;
/*     */ 
/*     */ public class KDTree2D {
/*     */   private Node root;
/*     */   
/*     */   private Comparator<Point2D> xComparator;
/*     */   
/*     */   private Comparator<Point2D> yComparator;
/*     */   
/*     */   public class Node {
/*     */     private Point2D point;
/*     */     
/*     */     private Node left;
/*     */     
/*     */     private Node right;
/*     */     
/*     */     public Node(Point2D point) {
/*  40 */       this.point = point;
/*  41 */       this.left = null;
/*  42 */       this.right = null;
/*     */     }
/*     */     
/*     */     public Node(Point2D point, Node left, Node right) {
/*  46 */       this.point = point;
/*  47 */       this.left = left;
/*  48 */       this.right = right;
/*     */     }
/*     */     
/*     */     public Point2D getPoint() {
/*  52 */       return this.point;
/*     */     }
/*     */     
/*     */     public Node getLeftChild() {
/*  56 */       return this.left;
/*     */     }
/*     */     
/*     */     public Node getRightChild() {
/*  60 */       return this.right;
/*     */     }
/*     */     
/*     */     public boolean isLeaf() {
/*  64 */       return (this.left == null && this.right == null);
/*     */     }
/*     */   }
/*     */   
/*     */   private class XComparator implements Comparator<Point2D> {
/*     */     private XComparator() {}
/*     */     
/*     */     public int compare(Point2D p1, Point2D p2) {
/*  73 */       if (p1.x() < p2.x())
/*  74 */         return -1; 
/*  75 */       if (p1.x() > p2.x())
/*  76 */         return 1; 
/*  77 */       return Double.compare(p1.y(), p2.y());
/*     */     }
/*     */   }
/*     */   
/*     */   private class YComparator implements Comparator<Point2D> {
/*     */     private YComparator() {}
/*     */     
/*     */     public int compare(Point2D p1, Point2D p2) {
/*  86 */       if (p1.y() < p2.y())
/*  87 */         return -1; 
/*  88 */       if (p1.y() > p2.y())
/*  89 */         return 1; 
/*  90 */       return Double.compare(p1.x(), p2.x());
/*     */     }
/*     */   }
/*     */   
/*     */   public KDTree2D(ArrayList<Point2D> points) {
/* 100 */     this.xComparator = new XComparator(null);
/* 101 */     this.yComparator = new YComparator(null);
/* 102 */     this.root = makeTree(points, 0);
/*     */   }
/*     */   
/*     */   private Node makeTree(List<Point2D> points, int depth) {
/* 107 */     if (points.size() == 0)
/* 108 */       return null; 
/* 111 */     int dir = depth % 2;
/* 114 */     if (dir == 0) {
/* 116 */       Collections.sort(points, this.xComparator);
/*     */     } else {
/* 119 */       Collections.sort(points, this.yComparator);
/*     */     } 
/* 122 */     int n = points.size();
/* 123 */     int med = n / 2;
/* 125 */     return new Node(
/* 126 */         points.get(med), 
/* 127 */         makeTree(points.subList(0, med), depth + 1), 
/* 128 */         makeTree(points.subList(med + 1, n), depth + 1));
/*     */   }
/*     */   
/*     */   public Node getRoot() {
/* 132 */     return this.root;
/*     */   }
/*     */   
/*     */   public boolean contains(Point2D value) {
/* 136 */     return contains(value, this.root, 0);
/*     */   }
/*     */   
/*     */   private boolean contains(Point2D point, Node node, int depth) {
/*     */     int res;
/* 140 */     if (node == null)
/* 140 */       return false; 
/* 143 */     int dir = depth % 2;
/* 147 */     if (dir == 0) {
/* 149 */       res = this.xComparator.compare(point, node.point);
/*     */     } else {
/* 152 */       res = this.yComparator.compare(point, node.point);
/*     */     } 
/* 155 */     if (res < 0)
/* 156 */       return contains(point, node.left, depth + 1); 
/* 157 */     if (res > 0)
/* 158 */       return contains(point, node.right, depth + 1); 
/* 160 */     return true;
/*     */   }
/*     */   
/*     */   public Node getNode(Point2D point) {
/* 164 */     return getNode(point, this.root, 0);
/*     */   }
/*     */   
/*     */   private Node getNode(Point2D point, Node node, int depth) {
/*     */     int res;
/* 168 */     if (node == null)
/* 168 */       return null; 
/* 170 */     int dir = depth % 2;
/* 174 */     if (dir == 0) {
/* 176 */       res = this.xComparator.compare(point, node.point);
/*     */     } else {
/* 179 */       res = this.yComparator.compare(point, node.point);
/*     */     } 
/* 182 */     if (res < 0)
/* 183 */       return getNode(point, node.left, depth + 1); 
/* 184 */     if (res > 0)
/* 185 */       return getNode(point, node.right, depth + 1); 
/* 187 */     return node;
/*     */   }
/*     */   
/*     */   public void add(Point2D point) {
/* 191 */     add(point, this.root, 0);
/*     */   }
/*     */   
/*     */   private void add(Point2D point, Node node, int depth) {
/* 196 */     int res, dir = depth % 2;
/* 200 */     if (dir == 0) {
/* 202 */       res = this.xComparator.compare(point, node.point);
/*     */     } else {
/* 205 */       res = this.yComparator.compare(point, node.point);
/*     */     } 
/* 208 */     if (res < 0)
/* 209 */       if (node.left == null) {
/* 210 */         node.left = new Node(point);
/*     */       } else {
/* 212 */         add(point, node.left, depth + 1);
/*     */       }  
/* 214 */     if (res > 0)
/* 215 */       if (node.right == null) {
/* 216 */         node.right = new Node(point);
/*     */       } else {
/* 218 */         add(point, node.right, depth + 1);
/*     */       }  
/*     */   }
/*     */   
/*     */   public Collection<Point2D> rangeSearch(Box2D range) {
/* 222 */     ArrayList<Point2D> points = new ArrayList<Point2D>();
/* 223 */     rangeSearch(range, points, this.root, 0);
/* 224 */     return points;
/*     */   }
/*     */   
/*     */   private void rangeSearch(Box2D range, Collection<Point2D> points, Node node, int depth) {
/* 232 */     if (node == null)
/*     */       return; 
/* 236 */     Point2D point = node.getPoint();
/* 237 */     double x = point.x();
/* 238 */     double y = point.y();
/* 241 */     boolean tx1 = (range.getMinX() < x);
/* 242 */     boolean ty1 = (range.getMinY() < y);
/* 243 */     boolean tx2 = (x <= range.getMaxX());
/* 244 */     boolean ty2 = (y <= range.getMaxY());
/* 247 */     if (tx1 && tx2 && ty1 && ty2)
/* 248 */       points.add(point); 
/* 251 */     int dir = depth % 2;
/* 253 */     if ((dir == 0) ? tx1 : ty1)
/* 254 */       rangeSearch(range, points, node.left, depth + 1); 
/* 255 */     if ((dir == 0) ? tx2 : ty2)
/* 256 */       rangeSearch(range, points, node.right, depth + 1); 
/*     */   }
/*     */   
/*     */   public Point2D nearestNeighbor(Point2D point) {
/* 261 */     return nearestNeighbor(point, this.root, this.root, 0).getPoint();
/*     */   }
/*     */   
/*     */   private Node nearestNeighbor(Point2D point, Node candidate, Node node, int depth) {
/*     */     Node node1, node2;
/*     */     StraightLine2D line;
/* 271 */     double distCand = candidate.point.distance(point);
/* 272 */     double dist = node.point.distance(point);
/* 273 */     if (dist < distCand)
/* 274 */       candidate = node; 
/* 278 */     int dir = depth % 2;
/* 284 */     Point2D anchor = node.getPoint();
/* 286 */     if (dir == 0) {
/* 287 */       boolean b = (point.x() < anchor.x());
/* 288 */       node1 = b ? node.left : node.right;
/* 289 */       node2 = b ? node.right : node.left;
/* 290 */       line = new StraightLine2D(anchor, new Vector2D(0.0D, 1.0D));
/*     */     } else {
/* 292 */       boolean b = (point.y() < anchor.y());
/* 293 */       node1 = b ? node.left : node.right;
/* 294 */       node2 = b ? node.right : node.left;
/* 295 */       line = new StraightLine2D(anchor, new Vector2D(1.0D, 0.0D));
/*     */     } 
/* 298 */     if (node1 != null) {
/* 300 */       candidate = nearestNeighbor(point, candidate, node1, depth + 1);
/* 303 */       distCand = candidate.getPoint().distance(point);
/*     */     } 
/* 308 */     if (line.distance(point) < distCand && node2 != null)
/* 309 */       candidate = nearestNeighbor(point, candidate, node2, depth + 1); 
/* 312 */     return candidate;
/*     */   }
/*     */   
/*     */   public static void main(String[] args) {
/* 320 */     int n = 3;
/* 321 */     ArrayList<Point2D> points = new ArrayList<Point2D>(n);
/* 322 */     points.add(new Point2D(5.0D, 5.0D));
/* 323 */     points.add(new Point2D(10.0D, 10.0D));
/* 324 */     points.add(new Point2D(20.0D, 20.0D));
/* 326 */     System.out.println("Check KDTree2D");
/* 328 */     KDTree2D tree = new KDTree2D(points);
/* 330 */     System.out.println(tree.contains(new Point2D(5.0D, 5.0D)));
/* 331 */     System.out.println(tree.contains(new Point2D(6.0D, 5.0D)));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom2d\point\KDTree2D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */