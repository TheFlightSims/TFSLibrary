/*     */ package math.geom2d.domain;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import math.geom2d.AffineTransform2D;
/*     */ import math.geom2d.Box2D;
/*     */ import math.geom2d.Shape2D;
/*     */ import math.geom2d.ShapeArray2D;
/*     */ import math.geom2d.ShapeSet2D;
/*     */ import math.geom2d.UnboundedShape2DException;
/*     */ import math.geom2d.polygon.LinearCurve2D;
/*     */ import math.geom2d.polygon.LinearRing2D;
/*     */ import math.geom2d.polygon.MultiPolygon2D;
/*     */ import math.geom2d.polygon.Polygon2D;
/*     */ 
/*     */ public class DomainArray2D<T extends Domain2D> extends ShapeArray2D<T> implements DomainSet2D<T> {
/*     */   public static <D extends Domain2D> DomainArray2D<D> create(Collection<D> array) {
/*  32 */     return new DomainArray2D<D>(array);
/*     */   }
/*     */   
/*     */   public static <D extends Domain2D> DomainArray2D<D> create(Domain2D... array) {
/*  36 */     return new DomainArray2D<D>((D[])array);
/*     */   }
/*     */   
/*     */   public DomainArray2D() {}
/*     */   
/*     */   public DomainArray2D(int n) {
/*  49 */     super(n);
/*     */   }
/*     */   
/*     */   public DomainArray2D(Collection<T> domains) {
/*  56 */     super(domains);
/*     */   }
/*     */   
/*     */   public DomainArray2D(Domain2D... domains) {
/*  63 */     super((Shape2D[])domains);
/*     */   }
/*     */   
/*     */   public Boundary2D boundary() {
/*  70 */     int n = this.shapes.size();
/*  71 */     ArrayList<Contour2D> boundaries = 
/*  72 */       new ArrayList<Contour2D>(n);
/*  73 */     for (Domain2D domain : this)
/*  74 */       boundaries.addAll(domain.boundary().continuousCurves()); 
/*  75 */     return new ContourArray2D<Contour2D>(boundaries);
/*     */   }
/*     */   
/*     */   public Collection<? extends Contour2D> contours() {
/*  82 */     return boundary().continuousCurves();
/*     */   }
/*     */   
/*     */   public DomainSet2D<? extends Domain2D> complement() {
/*  89 */     int n = this.shapes.size();
/*  90 */     ArrayList<Domain2D> complements = new ArrayList<Domain2D>(n);
/*  91 */     for (Domain2D domain : this)
/*  92 */       complements.add(domain.complement()); 
/*  93 */     return new DomainArray2D((Collection)complements);
/*     */   }
/*     */   
/*     */   public Polygon2D asPolygon(int n) {
/* 101 */     int nContours = 0;
/* 102 */     for (Domain2D domain : this.shapes)
/* 103 */       nContours += domain.boundary().continuousCurves().size(); 
/* 106 */     ArrayList<LinearRing2D> rings = new ArrayList<LinearRing2D>(nContours);
/* 107 */     for (Domain2D domain : this.shapes) {
/* 108 */       for (Contour2D contour : domain.boundary().continuousCurves()) {
/* 110 */         if (!contour.isBounded())
/* 111 */           throw new UnboundedShape2DException(this); 
/* 114 */         if (!contour.isClosed())
/* 115 */           throw new IllegalArgumentException("Can not transform open curve to linear ring"); 
/* 117 */         LinearCurve2D poly = contour.asPolyline(n);
/* 118 */         assert poly instanceof LinearRing2D : "expected result as a linear ring";
/* 120 */         rings.add((LinearRing2D)poly);
/*     */       } 
/*     */     } 
/* 124 */     return (Polygon2D)new MultiPolygon2D(rings);
/*     */   }
/*     */   
/*     */   public void fill(Graphics2D g2) {
/* 131 */     for (Domain2D domain : this)
/* 132 */       domain.fill(g2); 
/*     */   }
/*     */   
/*     */   public DomainArray2D<? extends Domain2D> transform(AffineTransform2D trans) {
/* 143 */     DomainArray2D<Domain2D> result = 
/* 144 */       new DomainArray2D(this.shapes.size());
/* 147 */     for (Domain2D domain : this)
/* 148 */       result.add(domain.transform(trans)); 
/* 149 */     return result;
/*     */   }
/*     */   
/*     */   public Domain2D clip(Box2D box) {
/* 156 */     ArrayList<Domain2D> clippedShapes = new ArrayList<Domain2D>();
/* 157 */     for (Domain2D domain2D : this)
/* 158 */       clippedShapes.add(domain2D.clip(box)); 
/* 159 */     return new DomainArray2D((Collection)clippedShapes);
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 165 */     if (!(obj instanceof DomainArray2D))
/* 166 */       return false; 
/* 168 */     return super.equals(obj);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom2d\domain\DomainArray2D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */