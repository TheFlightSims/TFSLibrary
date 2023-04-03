/*      */ package javax.media.jai;
/*      */ 
/*      */ import java.awt.Graphics2D;
/*      */ import java.awt.Point;
/*      */ import java.awt.Polygon;
/*      */ import java.awt.Rectangle;
/*      */ import java.awt.RenderingHints;
/*      */ import java.awt.Shape;
/*      */ import java.awt.geom.AffineTransform;
/*      */ import java.awt.geom.Area;
/*      */ import java.awt.geom.GeneralPath;
/*      */ import java.awt.geom.PathIterator;
/*      */ import java.awt.geom.Point2D;
/*      */ import java.awt.geom.Rectangle2D;
/*      */ import java.awt.image.BufferedImage;
/*      */ import java.awt.image.MultiPixelPackedSampleModel;
/*      */ import java.awt.image.SampleModel;
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.util.Arrays;
/*      */ import java.util.Comparator;
/*      */ import java.util.LinkedList;
/*      */ import java.util.ListIterator;
/*      */ import java.util.Vector;
/*      */ 
/*      */ public class ROIShape extends ROI {
/*   61 */   transient Shape theShape = null;
/*      */   
/*      */   private static Point2D.Double getIntersection(double x1, double y1, double x2, double y2, double u1, double v1, double u2, double v2) {
/*   82 */     double[][] a = new double[2][2];
/*   83 */     a[0][0] = y2 - y1;
/*   84 */     a[0][1] = x1 - x2;
/*   85 */     a[1][0] = v2 - v1;
/*   86 */     a[1][1] = u1 - u2;
/*   88 */     double[] c = new double[2];
/*   89 */     c[0] = y1 * (x1 - x2) + x1 * (y2 - y1);
/*   90 */     c[1] = v1 * (u1 - u2) + u1 * (v2 - v1);
/*   92 */     double det = a[0][0] * a[1][1] - a[0][1] * a[1][0];
/*   93 */     double tmp = a[0][0];
/*   94 */     a[0][0] = a[1][1] / det;
/*   95 */     a[0][1] = -a[0][1] / det;
/*   96 */     a[1][0] = -a[1][0] / det;
/*   97 */     a[1][1] = tmp / det;
/*   99 */     double x = a[0][0] * c[0] + a[0][1] * c[1];
/*  100 */     double y = a[1][0] * c[0] + a[1][1] * c[1];
/*  102 */     return new Point2D.Double(x, y);
/*      */   }
/*      */   
/*      */   private LinkedList polygonToRunLengthList(Rectangle clip, Polygon poly) {
/*  117 */     PolyShape ps = new PolyShape(this, poly, clip);
/*  118 */     return ps.getAsRectList();
/*      */   }
/*      */   
/*      */   private static int[][] rectangleListToBitmask(LinkedList rectangleList, Rectangle clip, int[][] mask) {
/*  136 */     int bitField = Integer.MIN_VALUE;
/*  139 */     int bitmaskIntWidth = (clip.width + 31) / 32;
/*  142 */     if (mask == null) {
/*  143 */       mask = new int[clip.height][bitmaskIntWidth];
/*  144 */     } else if (mask.length < clip.height || (mask[0]).length < bitmaskIntWidth) {
/*  146 */       throw new RuntimeException(JaiI18N.getString("ROIShape0"));
/*      */     } 
/*  150 */     ListIterator rectangleIter = rectangleList.listIterator(0);
/*  151 */     while (rectangleIter.hasNext()) {
/*      */       Rectangle rect;
/*  154 */       if (clip.intersects(rect = rectangleIter.next())) {
/*  155 */         rect = clip.intersection(rect);
/*  158 */         int yMin = rect.y - clip.y;
/*  159 */         int xMin = rect.x - clip.x;
/*  160 */         int yMax = yMin + rect.height - 1;
/*  161 */         int xMax = xMin + rect.width - 1;
/*  164 */         for (int y = yMin; y <= yMax; y++) {
/*  165 */           int[] bitrow = mask[y];
/*  166 */           for (int x = xMin; x <= xMax; x++) {
/*  167 */             int index = x / 32;
/*  168 */             int shift = x % 32;
/*  169 */             bitrow[index] = bitrow[index] | bitField >>> shift;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*  175 */     return mask;
/*      */   }
/*      */   
/*      */   public ROIShape(Shape s) {
/*  186 */     if (s == null)
/*  187 */       throw new IllegalArgumentException(JaiI18N.getString("ROIShape2")); 
/*  189 */     this.theShape = s;
/*      */   }
/*      */   
/*      */   public ROIShape(Area a) {
/*  198 */     AffineTransform at = new AffineTransform();
/*  199 */     PathIterator pi = a.getPathIterator(at);
/*  200 */     GeneralPath gp = new GeneralPath(pi.getWindingRule());
/*  201 */     gp.append(pi, false);
/*  203 */     this.theShape = gp;
/*      */   }
/*      */   
/*      */   private class PolyShape {
/*      */     private static final int POLYGON_UNCLASSIFIED = 0;
/*      */     
/*      */     private static final int POLYGON_DEGENERATE = 1;
/*      */     
/*      */     private static final int POLYGON_CONVEX = 2;
/*      */     
/*      */     private static final int POLYGON_CONCAVE = 3;
/*      */     
/*      */     private Polygon poly;
/*      */     
/*      */     private Rectangle clip;
/*      */     
/*      */     private int type;
/*      */     
/*      */     private boolean insidePolygon;
/*      */     
/*      */     private final ROIShape this$0;
/*      */     
/*      */     PolyShape(ROIShape this$0, Polygon polygon, Rectangle clipRect) {
/*  246 */       this.this$0 = this$0;
/*      */       this.type = 0;
/*      */       this.insidePolygon = false;
/*  248 */       this.poly = polygon;
/*  249 */       this.clip = clipRect;
/*  252 */       this.insidePolygon = this.poly.contains(clipRect);
/*  253 */       this.type = 0;
/*      */     }
/*      */     
/*      */     private class PolyEdge implements Comparator {
/*      */       public double x;
/*      */       
/*      */       public double dx;
/*      */       
/*      */       public int i;
/*      */       
/*      */       private final ROIShape.PolyShape this$1;
/*      */       
/*      */       PolyEdge(ROIShape.PolyShape this$1, double x, double dx, int i) {
/*  276 */         this.this$1 = this$1;
/*  277 */         this.x = x;
/*  278 */         this.dx = dx;
/*  279 */         this.i = i;
/*      */       }
/*      */       
/*      */       public int compare(Object o1, Object o2) {
/*      */         int returnValue;
/*  293 */         double x1 = ((PolyEdge)o1).x;
/*  294 */         double x2 = ((PolyEdge)o2).x;
/*  297 */         if (x1 < x2) {
/*  298 */           returnValue = -1;
/*  299 */         } else if (x1 > x2) {
/*  300 */           returnValue = 1;
/*      */         } else {
/*  302 */           returnValue = 0;
/*      */         } 
/*  305 */         return returnValue;
/*      */       }
/*      */     }
/*      */     
/*      */     public LinkedList getAsRectList() {
/*  317 */       LinkedList rectList = new LinkedList();
/*  319 */       if (this.insidePolygon) {
/*  320 */         rectList.addLast(this.poly.getBounds());
/*      */       } else {
/*  323 */         classifyPolygon();
/*  326 */         switch (this.type) {
/*      */           case 1:
/*  328 */             rectList = null;
/*  341 */             return rectList;
/*      */           case 2:
/*      */             rectList = scanConvex(rectList);
/*  341 */             return rectList;
/*      */           case 3:
/*      */             rectList = scanConcave(rectList);
/*  341 */             return rectList;
/*      */         } 
/*      */         throw new RuntimeException(JaiI18N.getString("ROIShape1"));
/*      */       } 
/*  341 */       return rectList;
/*      */     }
/*      */     
/*      */     private int classifyPolygon() {
/*      */       int previousDirection;
/*  349 */       if (this.type != 0)
/*  350 */         return this.type; 
/*  353 */       int n = this.poly.npoints;
/*  354 */       if (n < 3) {
/*  355 */         this.type = 1;
/*  356 */         return this.type;
/*      */       } 
/*  358 */       if (this.poly.getBounds().contains(this.clip)) {
/*  359 */         this.type = 2;
/*  360 */         return this.type;
/*      */       } 
/*  364 */       int[] x = this.poly.xpoints;
/*  365 */       int[] y = this.poly.ypoints;
/*  369 */       int previousSign = sgn((x[0] - x[1]) * (y[1] - y[2]) - (x[1] - x[2]) * (y[0] - y[1]));
/*  371 */       boolean allZero = (previousSign == 0);
/*  375 */       if (x[0] < x[1]) {
/*  376 */         previousDirection = -1;
/*  377 */       } else if (x[0] > x[1]) {
/*  378 */         previousDirection = 1;
/*  379 */       } else if (y[0] < y[1]) {
/*  380 */         previousDirection = -1;
/*  381 */       } else if (y[0] > y[1]) {
/*  382 */         previousDirection = 1;
/*      */       } else {
/*  384 */         previousDirection = 0;
/*      */       } 
/*  392 */       int numDirectionChanges = 0;
/*  393 */       for (int i = 1; i < n; i++) {
/*  395 */         int currentDirection, j = (i + 1) % n;
/*  396 */         int k = (i + 2) % n;
/*  400 */         if (x[i] < x[j]) {
/*  401 */           currentDirection = -1;
/*  402 */         } else if (x[i] > x[j]) {
/*  403 */           currentDirection = 1;
/*  404 */         } else if (y[i] < y[j]) {
/*  405 */           currentDirection = -1;
/*  406 */         } else if (y[i] > y[j]) {
/*  407 */           currentDirection = 1;
/*      */         } else {
/*  409 */           currentDirection = 0;
/*      */         } 
/*  413 */         if (currentDirection != 0 && currentDirection == -previousDirection)
/*  415 */           numDirectionChanges++; 
/*  417 */         previousDirection = currentDirection;
/*  421 */         int sign = sgn((x[i] - x[j]) * (y[j] - y[k]) - (x[j] - x[k]) * (y[i] - y[j]));
/*  423 */         allZero = (allZero && sign == 0);
/*  424 */         if (!allZero) {
/*  425 */           if (sign != 0 && sign == -previousSign) {
/*  426 */             this.type = 3;
/*      */             break;
/*      */           } 
/*  428 */           if (sign != 0)
/*  429 */             previousSign = sign; 
/*      */         } 
/*      */       } 
/*  434 */       if (this.type == 0)
/*  435 */         if (allZero) {
/*  436 */           this.type = 1;
/*  437 */         } else if (numDirectionChanges > 2) {
/*  438 */           this.type = 3;
/*      */         } else {
/*  440 */           this.type = 2;
/*      */         }  
/*  444 */       return this.type;
/*      */     }
/*      */     
/*      */     private final int sgn(int i) {
/*      */       int sign;
/*  456 */       if (i > 0) {
/*  457 */         sign = 1;
/*  458 */       } else if (i < 0) {
/*  459 */         sign = -1;
/*      */       } else {
/*  461 */         sign = 0;
/*      */       } 
/*  463 */       return sign;
/*      */     }
/*      */     
/*      */     private LinkedList scanConvex(LinkedList rectList) {
/*  476 */       if (rectList == null)
/*  477 */         rectList = new LinkedList(); 
/*  481 */       int yMin = this.poly.ypoints[0];
/*  482 */       int topVertex = 0;
/*  483 */       int n = this.poly.npoints;
/*  484 */       for (int i = 1; i < n; i++) {
/*  485 */         if (this.poly.ypoints[i] < yMin) {
/*  486 */           yMin = this.poly.ypoints[i];
/*  487 */           topVertex = i;
/*      */         } 
/*      */       } 
/*  492 */       int leftIndex = topVertex;
/*  493 */       int rightIndex = topVertex;
/*  496 */       int numRemaining = n;
/*  499 */       int y = yMin;
/*  502 */       int intYLeft = y - 1;
/*  503 */       int intYRight = intYLeft;
/*  506 */       double[] px = intArrayToDoubleArray(this.poly.xpoints);
/*  507 */       int[] py = this.poly.ypoints;
/*  509 */       double[] leftX = new double[1];
/*  510 */       double[] leftDX = new double[1];
/*  511 */       double[] rightX = new double[1];
/*  512 */       double[] rightDX = new double[1];
/*  516 */       while (numRemaining > 0) {
/*  520 */         while (intYLeft <= y && numRemaining > 0) {
/*  521 */           numRemaining--;
/*  522 */           int j = leftIndex - 1;
/*  523 */           if (j < 0)
/*  523 */             j = n - 1; 
/*  524 */           intersectX(px[leftIndex], py[leftIndex], px[j], py[j], y, leftX, leftDX);
/*  526 */           intYLeft = py[j];
/*  527 */           leftIndex = j;
/*      */         } 
/*  531 */         while (intYRight <= y && numRemaining > 0) {
/*  532 */           numRemaining--;
/*  533 */           int j = rightIndex + 1;
/*  534 */           if (j >= n)
/*  534 */             j = 0; 
/*  535 */           intersectX(px[rightIndex], py[rightIndex], px[j], py[j], y, rightX, rightDX);
/*  537 */           intYRight = py[j];
/*  538 */           rightIndex = j;
/*      */         } 
/*  542 */         while (y < intYLeft && y < intYRight) {
/*  543 */           if (y >= this.clip.y && y < this.clip.getMaxY()) {
/*      */             Rectangle rect;
/*  545 */             if (leftX[0] <= rightX[0]) {
/*  546 */               rect = scanSegment(y, leftX[0], rightX[0]);
/*      */             } else {
/*  548 */               rect = scanSegment(y, rightX[0], leftX[0]);
/*      */             } 
/*  550 */             if (rect != null)
/*  551 */               rectList.addLast(rect); 
/*      */           } 
/*  554 */           y++;
/*  555 */           leftX[0] = leftX[0] + leftDX[0];
/*  556 */           rightX[0] = rightX[0] + rightDX[0];
/*      */         } 
/*      */       } 
/*  560 */       return rectList;
/*      */     }
/*      */     
/*      */     private Rectangle scanSegment(int y, double leftX, double rightX) {
/*  574 */       double x = leftX - 0.5D;
/*  575 */       int xl = (x < this.clip.x) ? this.clip.x : (int)Math.ceil(x);
/*  576 */       int xr = (int)Math.floor(rightX - 0.5D);
/*  577 */       if (xr >= this.clip.x + this.clip.width)
/*  577 */         xr = this.clip.x + this.clip.width - 1; 
/*  578 */       if (xl > xr)
/*  578 */         return null; 
/*  580 */       return new Rectangle(xl, y, xr - xl + 1, 1);
/*      */     }
/*      */     
/*      */     private void intersectX(double x1, int y1, double x2, int y2, int y, double[] x, double[] dx) {
/*  598 */       int dy = y2 - y1;
/*  599 */       if (dy == 0)
/*  599 */         dy = 1; 
/*  600 */       double frac = (y - y1) + 0.5D;
/*  602 */       dx[0] = (x2 - x1) / dy;
/*  603 */       x[0] = x1 + dx[0] * frac;
/*      */     }
/*      */     
/*      */     private LinkedList scanConcave(LinkedList rectList) {
/*  616 */       if (rectList == null)
/*  617 */         rectList = new LinkedList(); 
/*  620 */       int numVertices = this.poly.npoints;
/*  621 */       if (numVertices <= 0)
/*  621 */         return null; 
/*  624 */       Vector indVector = new Vector();
/*  625 */       indVector.add(new Integer(0));
/*  626 */       for (int count = 1; count < numVertices; count++) {
/*  629 */         int index = 0;
/*  630 */         int value = this.poly.ypoints[count];
/*  631 */         while (index < count) {
/*  632 */           int elt = ((Integer)indVector.get(index)).intValue();
/*  633 */           if (value <= this.poly.ypoints[elt])
/*      */             break; 
/*  634 */           index++;
/*      */         } 
/*  636 */         indVector.insertElementAt(new Integer(count), index);
/*      */       } 
/*  641 */       int[] ind = vectorToIntArray(indVector);
/*  644 */       Vector activeEdges = new Vector(numVertices);
/*  647 */       int y0 = Math.max((int)this.clip.getMinY(), (int)Math.ceil((this.poly.ypoints[ind[0]] - 0.5F)));
/*  649 */       int y1 = Math.min((int)this.clip.getMaxY(), (int)Math.floor((this.poly.ypoints[ind[numVertices - 1]] - 0.5F)));
/*  655 */       int nextVertex = 0;
/*  656 */       for (int y = y0; y <= y1; y++) {
/*  659 */         while (nextVertex < numVertices && this.poly.ypoints[ind[nextVertex]] <= y + 0.5F) {
/*  660 */           int i = ind[nextVertex];
/*  664 */           int j = (i > 0) ? (i - 1) : (numVertices - 1);
/*  665 */           if (this.poly.ypoints[j] <= y - 0.5F) {
/*  666 */             deleteEdge(activeEdges, j);
/*  667 */           } else if (this.poly.ypoints[j] > y + 0.5F) {
/*  668 */             appendEdge(activeEdges, j, y);
/*      */           } 
/*  671 */           j = (i < numVertices - 1) ? (i + 1) : 0;
/*  672 */           if (this.poly.ypoints[j] <= y - 0.5F) {
/*  673 */             deleteEdge(activeEdges, i);
/*  674 */           } else if (this.poly.ypoints[j] > y + 0.5F) {
/*  675 */             appendEdge(activeEdges, i, y);
/*      */           } 
/*  678 */           nextVertex++;
/*      */         } 
/*  682 */         Object[] edges = activeEdges.toArray();
/*  683 */         Arrays.sort(edges, (PolyEdge)edges[0]);
/*  686 */         int numActive = activeEdges.size();
/*  687 */         for (int k = 0; k < numActive; k += 2) {
/*  689 */           PolyEdge edge1 = (PolyEdge)edges[k];
/*  690 */           PolyEdge edge2 = (PolyEdge)edges[k + 1];
/*  693 */           int xl = (int)Math.ceil(edge1.x - 0.5D);
/*  694 */           if (xl < this.clip.getMinX())
/*  695 */             xl = (int)this.clip.getMinX(); 
/*  699 */           int xr = (int)Math.floor(edge2.x - 0.5D);
/*  700 */           if (xr > this.clip.getMaxX())
/*  701 */             xr = (int)this.clip.getMaxX(); 
/*  705 */           if (xl <= xr) {
/*  706 */             Rectangle r = new Rectangle(xl, y, xr - xl + 1, 1);
/*  707 */             rectList.addLast(r);
/*      */           } 
/*  711 */           edge1.x += edge1.dx;
/*  712 */           activeEdges.setElementAt(edge1, k);
/*  713 */           edge2.x += edge2.dx;
/*  714 */           activeEdges.setElementAt(edge2, k + 1);
/*      */         } 
/*      */       } 
/*  718 */       return rectList;
/*      */     }
/*      */     
/*      */     private void deleteEdge(Vector edges, int i) {
/*  728 */       int numActive = edges.size();
/*      */       int j;
/*  730 */       for (j = 0; j < numActive; j++) {
/*  731 */         PolyEdge edge = edges.get(j);
/*  732 */         if (edge.i == i)
/*      */           break; 
/*      */       } 
/*  734 */       if (j < numActive)
/*  735 */         edges.removeElementAt(j); 
/*      */     }
/*      */     
/*      */     private void appendEdge(Vector edges, int i, int y) {
/*  747 */       int ip, iq, j = (i + 1) % this.poly.npoints;
/*  750 */       if (this.poly.ypoints[i] < this.poly.ypoints[j]) {
/*  751 */         ip = i;
/*  752 */         iq = j;
/*      */       } else {
/*  754 */         ip = j;
/*  755 */         iq = i;
/*      */       } 
/*  757 */       double dx = (this.poly.xpoints[iq] - this.poly.xpoints[ip]) / (this.poly.ypoints[iq] - this.poly.ypoints[ip]);
/*  760 */       double x = dx * (y + 0.5F - this.poly.ypoints[ip]) + this.poly.xpoints[ip];
/*  761 */       edges.add(new PolyEdge(this, x, dx, i));
/*      */     }
/*      */     
/*      */     private double[] intArrayToDoubleArray(int[] intArray) {
/*  769 */       int length = intArray.length;
/*  770 */       double[] doubleArray = new double[length];
/*  771 */       for (int i = 0; i < length; i++)
/*  772 */         doubleArray[i] = intArray[i]; 
/*  774 */       return doubleArray;
/*      */     }
/*      */     
/*      */     private int[] vectorToIntArray(Vector vector) {
/*  786 */       int size = vector.size();
/*  787 */       int[] array = new int[size];
/*  788 */       Object[] objects = vector.toArray();
/*  789 */       for (int i = 0; i < size; i++)
/*  790 */         array[i] = ((Integer)objects[i]).intValue(); 
/*  792 */       return array;
/*      */     }
/*      */   }
/*      */   
/*      */   public Rectangle getBounds() {
/*  798 */     return this.theShape.getBounds();
/*      */   }
/*      */   
/*      */   public Rectangle2D getBounds2D() {
/*  803 */     return this.theShape.getBounds2D();
/*      */   }
/*      */   
/*      */   public boolean contains(Point p) {
/*  815 */     if (p == null)
/*  816 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  819 */     return contains(p.x, p.y);
/*      */   }
/*      */   
/*      */   public boolean contains(Point2D p) {
/*  831 */     if (p == null)
/*  832 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  835 */     return contains((int)p.getX(), (int)p.getY());
/*      */   }
/*      */   
/*      */   public boolean contains(int x, int y) {
/*  846 */     return this.theShape.contains(x, y);
/*      */   }
/*      */   
/*      */   public boolean contains(double x, double y) {
/*  859 */     return contains((int)x, (int)y);
/*      */   }
/*      */   
/*      */   public boolean contains(Rectangle rect) {
/*  873 */     if (rect == null)
/*  874 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  877 */     return contains(new Rectangle2D.Float(rect.x, rect.y, rect.width, rect.height));
/*      */   }
/*      */   
/*      */   public boolean contains(Rectangle2D rect) {
/*  894 */     if (rect == null)
/*  895 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  898 */     return this.theShape.contains(rect);
/*      */   }
/*      */   
/*      */   public boolean contains(int x, int y, int w, int h) {
/*  913 */     return contains(new Rectangle2D.Float(x, y, w, h));
/*      */   }
/*      */   
/*      */   public boolean contains(double x, double y, double w, double h) {
/*  931 */     return this.theShape.contains(x, y, w, h);
/*      */   }
/*      */   
/*      */   public boolean intersects(Rectangle r) {
/*  944 */     if (r == null)
/*  945 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  948 */     return intersects(new Rectangle2D.Float(r.x, r.y, r.width, r.height));
/*      */   }
/*      */   
/*      */   public boolean intersects(Rectangle2D r) {
/*  964 */     if (r == null)
/*  965 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  968 */     return this.theShape.intersects(r);
/*      */   }
/*      */   
/*      */   public boolean intersects(int x, int y, int w, int h) {
/*  982 */     return intersects(new Rectangle2D.Float(x, y, w, h));
/*      */   }
/*      */   
/*      */   public boolean intersects(double x, double y, double w, double h) {
/*  999 */     return this.theShape.intersects(x, y, w, h);
/*      */   }
/*      */   
/*      */   public ROI add(ROI roi) {
/* 1011 */     if (roi == null)
/* 1012 */       throw new IllegalArgumentException(JaiI18N.getString("ROIShape3")); 
/* 1015 */     if (!(roi instanceof ROIShape))
/* 1016 */       return super.add(roi); 
/* 1018 */     ROIShape rois = (ROIShape)roi;
/* 1019 */     Area a1 = new Area(this.theShape);
/* 1020 */     Area a2 = new Area(rois.theShape);
/* 1021 */     a1.add(a2);
/* 1022 */     return new ROIShape(a1);
/*      */   }
/*      */   
/*      */   public ROI subtract(ROI roi) {
/* 1035 */     if (roi == null)
/* 1036 */       throw new IllegalArgumentException(JaiI18N.getString("ROIShape3")); 
/* 1039 */     if (!(roi instanceof ROIShape))
/* 1040 */       return super.subtract(roi); 
/* 1042 */     ROIShape rois = (ROIShape)roi;
/* 1043 */     Area a1 = new Area(this.theShape);
/* 1044 */     Area a2 = new Area(rois.theShape);
/* 1045 */     a1.subtract(a2);
/* 1046 */     return new ROIShape(a1);
/*      */   }
/*      */   
/*      */   public ROI intersect(ROI roi) {
/* 1059 */     if (roi == null)
/* 1060 */       throw new IllegalArgumentException(JaiI18N.getString("ROIShape3")); 
/* 1063 */     if (!(roi instanceof ROIShape))
/* 1064 */       return super.intersect(roi); 
/* 1066 */     ROIShape rois = (ROIShape)roi;
/* 1067 */     Area a1 = new Area(this.theShape);
/* 1068 */     Area a2 = new Area(rois.theShape);
/* 1069 */     a1.intersect(a2);
/* 1070 */     return new ROIShape(a1);
/*      */   }
/*      */   
/*      */   public ROI exclusiveOr(ROI roi) {
/* 1083 */     if (roi == null)
/* 1084 */       throw new IllegalArgumentException(JaiI18N.getString("ROIShape3")); 
/* 1087 */     if (!(roi instanceof ROIShape))
/* 1088 */       return super.exclusiveOr(roi); 
/* 1090 */     ROIShape rois = (ROIShape)roi;
/* 1091 */     Area a1 = new Area(this.theShape);
/* 1092 */     Area a2 = new Area(rois.theShape);
/* 1093 */     a1.exclusiveOr(a2);
/* 1094 */     return new ROIShape(a1);
/*      */   }
/*      */   
/*      */   public Shape getAsShape() {
/* 1103 */     return this.theShape;
/*      */   }
/*      */   
/*      */   public PlanarImage getAsImage() {
/*      */     PlanarImage pi;
/*      */     Graphics2D g2d;
/* 1119 */     if (this.theImage != null)
/* 1120 */       return this.theImage; 
/* 1122 */     Rectangle r = this.theShape.getBounds();
/* 1127 */     if (r.x == 0 && r.y == 0) {
/* 1129 */       BufferedImage bi = new BufferedImage(r.width, r.height, 12);
/* 1133 */       pi = PlanarImage.wrapRenderedImage(bi);
/* 1134 */       g2d = bi.createGraphics();
/*      */     } else {
/* 1137 */       SampleModel sm = new MultiPixelPackedSampleModel(0, r.width, r.height, 1);
/* 1142 */       TiledImage ti = new TiledImage(r.x, r.y, r.width, r.height, r.x, r.y, sm, PlanarImage.createColorModel(sm));
/* 1147 */       pi = ti;
/* 1148 */       g2d = ti.createGraphics();
/*      */     } 
/* 1152 */     g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/* 1154 */     g2d.fill(this.theShape);
/* 1156 */     this.theImage = pi;
/* 1158 */     return this.theImage;
/*      */   }
/*      */   
/*      */   public ROI transform(AffineTransform at) {
/* 1169 */     if (at == null)
/* 1170 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 1173 */     return new ROIShape(at.createTransformedShape(this.theShape));
/*      */   }
/*      */   
/*      */   public int[][] getAsBitmask(int x, int y, int width, int height, int[][] mask) {
/* 1206 */     LinkedList rectList = getAsRectangleList(x, y, width, height, false);
/* 1208 */     if (rectList == null)
/* 1209 */       return (int[][])null; 
/* 1213 */     return rectangleListToBitmask(rectList, new Rectangle(x, y, width, height), mask);
/*      */   }
/*      */   
/*      */   public LinkedList getAsRectangleList(int x, int y, int width, int height) {
/* 1232 */     return getAsRectangleList(x, y, width, height, true);
/*      */   }
/*      */   
/*      */   protected LinkedList getAsRectangleList(int x, int y, int width, int height, boolean mergeRectangles) {
/* 1250 */     LinkedList rectangleList = null;
/* 1253 */     Rectangle clip = new Rectangle(x, y, width, height);
/* 1259 */     if (!(new Area(this.theShape)).intersects(clip))
/* 1260 */       return null; 
/* 1261 */     if (this.theShape instanceof Rectangle2D) {
/* 1265 */       Rectangle2D.Double dstRect = new Rectangle2D.Double();
/* 1266 */       Rectangle2D.intersect((Rectangle2D)this.theShape, clip, dstRect);
/* 1267 */       int rectX = (int)Math.round(dstRect.getMinX());
/* 1268 */       int rectY = (int)Math.round(dstRect.getMinY());
/* 1269 */       int rectW = (int)Math.round(dstRect.getMaxX() - rectX);
/* 1270 */       int rectH = (int)Math.round(dstRect.getMaxY() - rectY);
/* 1271 */       rectangleList = new LinkedList();
/* 1272 */       rectangleList.addLast(new Rectangle(rectX, rectY, rectW, rectH));
/* 1274 */     } else if (this.theShape instanceof Polygon) {
/* 1275 */       rectangleList = polygonToRunLengthList(clip, (Polygon)this.theShape);
/* 1276 */       if (mergeRectangles && rectangleList != null)
/* 1277 */         rectangleList = mergeRunLengthList(rectangleList); 
/*      */     } else {
/* 1281 */       getAsImage();
/* 1284 */       rectangleList = super.getAsRectangleList(x, y, width, height, mergeRectangles);
/*      */     } 
/* 1288 */     return rectangleList;
/*      */   }
/*      */   
/*      */   private void writeObject(ObjectOutputStream out) throws IOException {
/* 1298 */     LinkedList rectList = null;
/* 1299 */     if (this.theShape == null) {
/* 1300 */       rectList = new LinkedList();
/*      */     } else {
/* 1302 */       Rectangle r = getBounds();
/* 1303 */       rectList = getAsRectangleList(r.x, r.y, r.width, r.height);
/*      */     } 
/* 1307 */     out.defaultWriteObject();
/* 1308 */     out.writeObject(rectList);
/*      */   }
/*      */   
/*      */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 1320 */     LinkedList rectList = null;
/* 1321 */     in.defaultReadObject();
/* 1322 */     rectList = (LinkedList)in.readObject();
/* 1325 */     Area a = new Area();
/* 1326 */     int listSize = rectList.size();
/* 1327 */     for (int i = 0; i < listSize; i++)
/* 1328 */       a.add(new Area(rectList.get(i))); 
/* 1330 */     this.theShape = a;
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\ROIShape.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */