/*     */ package math.geom2d;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ 
/*     */ public class ShapeArray2D<T extends Shape2D> implements ShapeSet2D<T>, Cloneable {
/*     */   protected ArrayList<T> shapes;
/*     */   
/*     */   public static <T extends Shape2D> ShapeArray2D<T> create(Collection<T> shapes) {
/*  29 */     return new ShapeArray2D<T>(shapes);
/*     */   }
/*     */   
/*     */   public static <T extends Shape2D> ShapeArray2D<T> create(Shape2D... shapes) {
/*  33 */     return new ShapeArray2D<T>((T[])shapes);
/*     */   }
/*     */   
/*     */   public ShapeArray2D() {
/*  48 */     this.shapes = new ArrayList<T>();
/*     */   }
/*     */   
/*     */   public ShapeArray2D(int n) {
/*  52 */     this.shapes = new ArrayList<T>(n);
/*     */   }
/*     */   
/*     */   public ShapeArray2D(Collection<? extends T> shapes) {
/*  56 */     this.shapes = new ArrayList<T>(shapes.size());
/*  57 */     this.shapes.addAll(shapes);
/*     */   }
/*     */   
/*     */   public ShapeArray2D(Shape2D[] shapes) {
/*  61 */     this.shapes = new ArrayList<T>(shapes.length);
/*     */     byte b;
/*     */     int i;
/*     */     Shape2D[] arrayOfShape2D;
/*  62 */     for (i = (arrayOfShape2D = shapes).length, b = 0; b < i; ) {
/*  62 */       Shape2D shape2D = arrayOfShape2D[b];
/*  63 */       this.shapes.add((T)shape2D);
/*     */       b++;
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean add(T shape) {
/*  76 */     if (this.shapes.contains(shape))
/*  77 */       return false; 
/*  78 */     return this.shapes.add(shape);
/*     */   }
/*     */   
/*     */   public void add(int index, T shape) {
/*  82 */     this.shapes.add(index, shape);
/*     */   }
/*     */   
/*     */   public T get(int index) {
/*  92 */     return this.shapes.get(index);
/*     */   }
/*     */   
/*     */   public boolean remove(T shape) {
/* 101 */     return this.shapes.remove(shape);
/*     */   }
/*     */   
/*     */   public T remove(int index) {
/* 105 */     return this.shapes.remove(index);
/*     */   }
/*     */   
/*     */   public boolean contains(T shape) {
/* 112 */     return this.shapes.contains(shape);
/*     */   }
/*     */   
/*     */   public int indexOf(T shape) {
/* 116 */     return this.shapes.indexOf(shape);
/*     */   }
/*     */   
/*     */   public void clear() {
/* 123 */     this.shapes.clear();
/*     */   }
/*     */   
/*     */   public int size() {
/* 127 */     return this.shapes.size();
/*     */   }
/*     */   
/*     */   public Collection<T> shapes() {
/* 136 */     return this.shapes;
/*     */   }
/*     */   
/*     */   public Shape2D clip(Box2D box) {
/* 147 */     ArrayList<Shape2D> clippedShapes = new ArrayList<Shape2D>(size());
/* 148 */     for (Shape2D shape2D : this.shapes)
/* 149 */       clippedShapes.add(shape2D.clip(box)); 
/* 150 */     return new ShapeArray2D((Collection)clippedShapes);
/*     */   }
/*     */   
/*     */   public boolean contains(double x, double y) {
/* 157 */     for (Shape2D shape : this.shapes) {
/* 158 */       if (shape.contains(x, y))
/* 159 */         return true; 
/*     */     } 
/* 161 */     return false;
/*     */   }
/*     */   
/*     */   public boolean contains(Point2D p) {
/* 168 */     return contains(p.x(), p.y());
/*     */   }
/*     */   
/*     */   public void draw(Graphics2D g2) {
/* 175 */     for (Shape2D shape : this.shapes)
/* 176 */       shape.draw(g2); 
/*     */   }
/*     */   
/*     */   public Box2D boundingBox() {
/* 183 */     double xmin = Double.MAX_VALUE;
/* 184 */     double ymin = Double.MAX_VALUE;
/* 185 */     double xmax = Double.MIN_VALUE;
/* 186 */     double ymax = Double.MIN_VALUE;
/* 189 */     for (Shape2D shape : this.shapes) {
/* 191 */       Box2D box = shape.boundingBox();
/* 192 */       xmin = Math.min(xmin, box.getMinX());
/* 193 */       ymin = Math.min(ymin, box.getMinY());
/* 194 */       xmax = Math.max(xmax, box.getMaxX());
/* 195 */       ymax = Math.max(ymax, box.getMaxY());
/*     */     } 
/* 198 */     return new Box2D(xmin, xmax, ymin, ymax);
/*     */   }
/*     */   
/*     */   public double distance(Point2D p) {
/* 205 */     return distance(p.x(), p.y());
/*     */   }
/*     */   
/*     */   public double distance(double x, double y) {
/* 212 */     double dist = Double.POSITIVE_INFINITY;
/* 213 */     for (Shape2D shape : this.shapes)
/* 214 */       dist = Math.min(dist, shape.distance(x, y)); 
/* 215 */     return dist;
/*     */   }
/*     */   
/*     */   public boolean isBounded() {
/* 222 */     for (Shape2D shape : this.shapes) {
/* 223 */       if (!shape.isBounded())
/* 224 */         return false; 
/*     */     } 
/* 225 */     return true;
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 232 */     return this.shapes.isEmpty();
/*     */   }
/*     */   
/*     */   public ShapeSet2D<? extends Shape2D> transform(AffineTransform2D trans) {
/* 240 */     ShapeArray2D<Shape2D> result = 
/* 241 */       new ShapeArray2D(this.shapes.size());
/* 244 */     for (Shape2D shape : this.shapes)
/* 245 */       result.add(shape.transform(trans)); 
/* 246 */     return result;
/*     */   }
/*     */   
/*     */   public boolean almostEquals(GeometricObject2D obj, double eps) {
/* 256 */     if (this == obj)
/* 257 */       return true; 
/* 260 */     if (!(obj instanceof ShapeSet2D))
/* 261 */       return false; 
/* 262 */     ShapeArray2D<?> shapeSet = (ShapeArray2D)obj;
/* 265 */     if (this.shapes.size() != shapeSet.shapes.size())
/* 266 */       return false; 
/* 269 */     Iterator<?> iter2 = shapeSet.shapes.iterator();
/* 270 */     for (Shape2D shape2D : this.shapes) {
/* 271 */       if (!shape2D.almostEquals((GeometricObject2D)iter2.next(), eps))
/* 272 */         return false; 
/*     */     } 
/* 276 */     return true;
/*     */   }
/*     */   
/*     */   public Iterator<T> iterator() {
/* 286 */     return this.shapes.iterator();
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 298 */     if (this == obj)
/* 299 */       return true; 
/* 302 */     if (!(obj instanceof ShapeSet2D))
/* 303 */       return false; 
/* 304 */     ShapeArray2D<?> shapeSet = (ShapeArray2D)obj;
/* 307 */     if (this.shapes.size() != shapeSet.shapes.size())
/* 308 */       return false; 
/* 311 */     Iterator<?> iter2 = shapeSet.shapes.iterator();
/* 312 */     for (Shape2D shape2D : this.shapes) {
/* 313 */       if (!shape2D.equals(iter2.next()))
/* 314 */         return false; 
/*     */     } 
/* 318 */     return true;
/*     */   }
/*     */   
/*     */   public ShapeArray2D<? extends Shape2D> clone() {
/* 323 */     ArrayList<Shape2D> array = new ArrayList<Shape2D>(this.shapes.size());
/* 324 */     for (Shape2D shape2D : this.shapes)
/* 325 */       array.add(shape2D); 
/* 326 */     return new ShapeArray2D((Collection)array);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom2d\ShapeArray2D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */