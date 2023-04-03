/*     */ package math.geom2d.grid;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import math.geom2d.Box2D;
/*     */ import math.geom2d.Point2D;
/*     */ import math.geom2d.line.LineSegment2D;
/*     */ import math.geom2d.point.PointArray2D;
/*     */ import math.geom2d.point.PointSet2D;
/*     */ 
/*     */ public class SquareGrid2D implements Grid2D {
/*  25 */   double x0 = 0.0D;
/*     */   
/*  26 */   double y0 = 0.0D;
/*     */   
/*  28 */   double sx = 1.0D;
/*     */   
/*  29 */   double sy = 1.0D;
/*     */   
/*     */   public SquareGrid2D() {}
/*     */   
/*     */   public SquareGrid2D(Point2D origin) {
/*  36 */     this(origin.x(), origin.y(), 1.0D, 1.0D);
/*     */   }
/*     */   
/*     */   public SquareGrid2D(Point2D origin, double s) {
/*  40 */     this(origin.x(), origin.y(), s, s);
/*     */   }
/*     */   
/*     */   public SquareGrid2D(Point2D origin, double sx, double sy) {
/*  44 */     this(origin.x(), origin.y(), sx, sy);
/*     */   }
/*     */   
/*     */   public SquareGrid2D(double x0, double y0, double s) {
/*  48 */     this(x0, y0, s, s);
/*     */   }
/*     */   
/*     */   public SquareGrid2D(double s) {
/*  52 */     this(0.0D, 0.0D, s, s);
/*     */   }
/*     */   
/*     */   public SquareGrid2D(double sx, double sy) {
/*  56 */     this(0.0D, 0.0D, sx, sy);
/*     */   }
/*     */   
/*     */   public SquareGrid2D(double x0, double y0, double sx, double sy) {
/*  60 */     this.x0 = x0;
/*  61 */     this.y0 = y0;
/*  62 */     this.sx = sx;
/*  63 */     this.sy = sy;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public void setOrigin(Point2D point) {
/*  71 */     this.x0 = point.x();
/*  72 */     this.y0 = point.y();
/*     */   }
/*     */   
/*     */   public Point2D getOrigin() {
/*  76 */     return new Point2D(this.x0, this.y0);
/*     */   }
/*     */   
/*     */   public double getSizeX() {
/*  80 */     return this.sx;
/*     */   }
/*     */   
/*     */   public double getSizeY() {
/*  84 */     return this.sy;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public void setSize(double s) {
/*  92 */     this.sx = s;
/*  93 */     this.sy = s;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public void setSize(double sx, double sy) {
/* 101 */     this.sx = sx;
/* 102 */     this.sy = sy;
/*     */   }
/*     */   
/*     */   public Point2D getClosestVertex(Point2D point) {
/* 111 */     double nx = Math.round((point.x() - this.x0) / this.sx);
/* 112 */     double ny = Math.round((point.y() - this.y0) / this.sy);
/* 113 */     return new Point2D(nx * this.sx + this.x0, ny * this.sy + this.y0);
/*     */   }
/*     */   
/*     */   public Collection<LineSegment2D> getEdges(Box2D box) {
/* 127 */     double xmin = box.getMinX();
/* 128 */     double ymin = box.getMinY();
/* 129 */     double xmax = box.getMaxX();
/* 130 */     double ymax = box.getMaxY();
/* 133 */     double xi = Math.ceil((xmin - this.x0) / this.sx) * this.sx + this.x0;
/* 134 */     double yi = Math.ceil((ymin - this.y0) / this.sy) * this.sy + this.y0;
/* 136 */     ArrayList<LineSegment2D> array = new ArrayList<LineSegment2D>();
/* 139 */     for (double y = yi; y - ymax < 1.0E-12D; y += this.sy)
/* 140 */       array.add(new LineSegment2D(xmin, y, xmax, y)); 
/* 143 */     for (double x = xi; x - xmax < 1.0E-12D; x += this.sx)
/* 144 */       array.add(new LineSegment2D(x, ymin, x, ymax)); 
/* 147 */     return array;
/*     */   }
/*     */   
/*     */   public PointSet2D getVertices(Box2D box) {
/* 161 */     double xmin = box.getMinX();
/* 162 */     double ymin = box.getMinY();
/* 163 */     double xmax = box.getMaxX();
/* 164 */     double ymax = box.getMaxY();
/* 167 */     double xi = Math.ceil((xmin - this.x0) / this.sx) * this.sx + this.x0;
/* 168 */     double yi = Math.ceil((ymin - this.y0) / this.sy) * this.sy + this.y0;
/* 170 */     ArrayList<Point2D> array = new ArrayList<Point2D>();
/* 173 */     for (double y = yi; y - ymax < 1.0E-12D; y += this.sy) {
/* 174 */       for (double x = xi; x - xmax < 1.0E-12D; x += this.sx)
/* 175 */         array.add(new Point2D(x, y)); 
/*     */     } 
/* 178 */     return (PointSet2D)new PointArray2D(array);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom2d\grid\SquareGrid2D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */