/*     */ package org.geotools.geometry;
/*     */ 
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import org.geotools.referencing.CRS;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ import org.geotools.util.Utilities;
/*     */ import org.opengis.geometry.BoundingBox;
/*     */ import org.opengis.geometry.DirectPosition;
/*     */ import org.opengis.geometry.Envelope;
/*     */ import org.opengis.geometry.MismatchedDimensionException;
/*     */ import org.opengis.geometry.MismatchedReferenceSystemException;
/*     */ import org.opengis.referencing.crs.CoordinateReferenceSystem;
/*     */ import org.opengis.referencing.operation.TransformException;
/*     */ import org.opengis.util.Cloneable;
/*     */ 
/*     */ public class Envelope2D extends Rectangle2D.Double implements BoundingBox, Envelope, Cloneable {
/*     */   private static final long serialVersionUID = -3319231220761419350L;
/*     */   
/*     */   private CoordinateReferenceSystem crs;
/*     */   
/*     */   public Envelope2D() {
/*  83 */     this.width = -1.0D;
/*  84 */     this.height = -1.0D;
/*     */   }
/*     */   
/*     */   public Envelope2D(CoordinateReferenceSystem crs) {
/*  93 */     this();
/*  94 */     setCoordinateReferenceSystem(crs);
/*     */   }
/*     */   
/*     */   public Envelope2D(Envelope envelope) {
/* 103 */     super(envelope.getMinimum(0), envelope.getMinimum(1), envelope.getSpan(0), envelope.getSpan(1));
/* 107 */     int dimension = envelope.getDimension();
/* 108 */     if (dimension != 2)
/* 109 */       throw new MismatchedDimensionException(Errors.format(127, Integer.valueOf(dimension))); 
/* 112 */     setCoordinateReferenceSystem(envelope.getCoordinateReferenceSystem());
/*     */   }
/*     */   
/*     */   public Envelope2D(CoordinateReferenceSystem crs, Rectangle2D rect) {
/* 122 */     super(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
/* 123 */     setCoordinateReferenceSystem(crs);
/*     */   }
/*     */   
/*     */   public Envelope2D(CoordinateReferenceSystem crs, double x, double y, double width, double height) {
/* 143 */     super(x, y, width, height);
/* 144 */     setCoordinateReferenceSystem(crs);
/*     */   }
/*     */   
/*     */   public Envelope2D(DirectPosition2D minDP, DirectPosition2D maxDP) throws MismatchedReferenceSystemException {
/* 171 */     super(Math.min(minDP.x, maxDP.x), Math.min(minDP.y, maxDP.y), Math.abs(maxDP.x - minDP.x), Math.abs(maxDP.y - minDP.y));
/* 175 */     setCoordinateReferenceSystem(AbstractEnvelope.getCoordinateReferenceSystem(minDP, maxDP));
/*     */   }
/*     */   
/*     */   public final CoordinateReferenceSystem getCoordinateReferenceSystem() {
/* 184 */     return this.crs;
/*     */   }
/*     */   
/*     */   public void setCoordinateReferenceSystem(CoordinateReferenceSystem crs) {
/* 193 */     AbstractDirectPosition.checkCoordinateReferenceSystemDimension(crs, getDimension());
/* 194 */     this.crs = crs;
/*     */   }
/*     */   
/*     */   public final int getDimension() {
/* 201 */     return 2;
/*     */   }
/*     */   
/*     */   public DirectPosition getLowerCorner() {
/* 214 */     return new DirectPosition2D(this.crs, getMinX(), getMinY());
/*     */   }
/*     */   
/*     */   public DirectPosition getUpperCorner() {
/* 227 */     return new DirectPosition2D(this.crs, getMaxX(), getMaxY());
/*     */   }
/*     */   
/*     */   private static IndexOutOfBoundsException indexOutOfBounds(int dimension) {
/* 234 */     return new IndexOutOfBoundsException(Errors.format(79, Integer.valueOf(dimension)));
/*     */   }
/*     */   
/*     */   public final double getMinimum(int dimension) throws IndexOutOfBoundsException {
/* 245 */     switch (dimension) {
/*     */       case 0:
/* 246 */         return getMinX();
/*     */       case 1:
/* 247 */         return getMinY();
/*     */     } 
/* 248 */     throw indexOutOfBounds(dimension);
/*     */   }
/*     */   
/*     */   public final double getMaximum(int dimension) throws IndexOutOfBoundsException {
/* 260 */     switch (dimension) {
/*     */       case 0:
/* 261 */         return getMaxX();
/*     */       case 1:
/* 262 */         return getMaxY();
/*     */     } 
/* 263 */     throw indexOutOfBounds(dimension);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public final double getCenter(int dimension) {
/* 277 */     return getMedian(dimension);
/*     */   }
/*     */   
/*     */   public final double getMedian(int dimension) throws IndexOutOfBoundsException {
/* 290 */     switch (dimension) {
/*     */       case 0:
/* 291 */         return getCenterX();
/*     */       case 1:
/* 292 */         return getCenterY();
/*     */     } 
/* 293 */     throw indexOutOfBounds(dimension);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public final double getLength(int dimension) {
/* 309 */     return getSpan(dimension);
/*     */   }
/*     */   
/*     */   public final double getSpan(int dimension) throws IndexOutOfBoundsException {
/* 322 */     switch (dimension) {
/*     */       case 0:
/* 323 */         return getWidth();
/*     */       case 1:
/* 324 */         return getHeight();
/*     */     } 
/* 325 */     throw indexOutOfBounds(dimension);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 335 */     int code = super.hashCode() ^ 0x6F2121AA;
/* 336 */     if (this.crs != null)
/* 337 */       code += this.crs.hashCode(); 
/* 339 */     return code;
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/* 350 */     if (super.equals(object)) {
/* 351 */       CoordinateReferenceSystem otherCRS = (object instanceof Envelope2D) ? ((Envelope2D)object).crs : null;
/* 353 */       return Utilities.equals(this.crs, otherCRS);
/*     */     } 
/* 355 */     return false;
/*     */   }
/*     */   
/*     */   public boolean boundsEquals(Envelope that, int xDim, int yDim, double eps) {
/* 375 */     eps *= 0.5D * (this.width + this.height);
/* 376 */     for (int i = 0; i < 4; i++) {
/*     */       double value2D, valueND;
/* 377 */       int dim2D = i & 0x1;
/* 378 */       int dimND = (dim2D == 0) ? xDim : yDim;
/* 380 */       if ((i & 0x2) == 0) {
/* 381 */         value2D = getMinimum(dim2D);
/* 382 */         valueND = that.getMinimum(dimND);
/*     */       } else {
/* 384 */         value2D = getMaximum(dim2D);
/* 385 */         valueND = that.getMaximum(dimND);
/*     */       } 
/* 388 */       if (Math.abs(value2D - valueND) > eps)
/* 389 */         return false; 
/*     */     } 
/* 392 */     return true;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 405 */     return AbstractEnvelope.toString(this);
/*     */   }
/*     */   
/*     */   public void setBounds(BoundingBox bounds) {
/* 410 */     this.crs = bounds.getCoordinateReferenceSystem();
/* 411 */     this.x = bounds.getMinX();
/* 412 */     this.y = bounds.getMinY();
/* 413 */     this.width = bounds.getWidth();
/* 414 */     this.height = bounds.getHeight();
/*     */   }
/*     */   
/*     */   public void include(BoundingBox bounds) {
/* 418 */     if (this.crs == null) {
/* 419 */       this.crs = bounds.getCoordinateReferenceSystem();
/*     */     } else {
/* 421 */       ensureCompatibleReferenceSystem(bounds);
/*     */     } 
/* 423 */     if (bounds.isEmpty())
/*     */       return; 
/* 426 */     if (isNull()) {
/* 427 */       setBounds(bounds);
/*     */     } else {
/* 429 */       if (bounds.getMinX() < getMinX()) {
/* 430 */         this.width += getMinX() - bounds.getMinX();
/* 431 */         this.x = bounds.getMinX();
/*     */       } 
/* 433 */       if (bounds.getMaxX() > getMaxX())
/* 434 */         this.width += bounds.getMaxX() - getMaxX(); 
/* 436 */       if (bounds.getMinY() < getMinY()) {
/* 437 */         this.height += getMinY() - bounds.getMinY();
/* 438 */         this.y = bounds.getMinY();
/*     */       } 
/* 440 */       if (bounds.getMaxY() > getMaxY())
/* 441 */         this.height += bounds.getMaxY() - getMaxY(); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void include(double x, double y) {
/* 447 */     if (isNull()) {
/* 448 */       this.x = x;
/* 449 */       this.y = y;
/* 450 */       this.width = 0.0D;
/* 451 */       this.height = 0.0D;
/*     */     } else {
/* 453 */       if (x < getMinX()) {
/* 454 */         this.width += getMinX() - x;
/* 455 */         this.x = x;
/*     */       } 
/* 457 */       if (x > getMaxX())
/* 458 */         this.width += x - getMaxX(); 
/* 460 */       if (y < getMinY()) {
/* 461 */         this.height += getMinY() - y;
/* 462 */         this.y = y;
/*     */       } 
/* 464 */       if (y > getMaxY())
/* 465 */         this.height += y - getMaxY(); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean intersects(BoundingBox bounds) {
/* 482 */     ensureCompatibleReferenceSystem(bounds);
/* 483 */     if (isNull() || bounds.isEmpty())
/* 484 */       return false; 
/* 487 */     return (bounds.getMinX() <= getMaxX() && bounds.getMaxX() >= getMinX() && bounds.getMinY() <= getMaxY() && bounds.getMaxY() >= getMinY());
/*     */   }
/*     */   
/*     */   public boolean contains(BoundingBox bounds) {
/* 492 */     ensureCompatibleReferenceSystem(bounds);
/* 493 */     if (isEmpty() || bounds.isEmpty())
/* 494 */       return false; 
/* 496 */     return (bounds.getMinX() >= getMinX() && bounds.getMaxX() <= getMaxX() && bounds.getMinY() >= getMinY() && bounds.getMaxY() <= getMaxY());
/*     */   }
/*     */   
/*     */   public boolean contains(DirectPosition location) {
/* 501 */     ensureCompatibleReferenceSystem(location);
/* 502 */     if (isEmpty())
/* 503 */       return false; 
/* 505 */     return (location.getOrdinate(0) >= getMinX() && location.getOrdinate(0) <= getMaxX() && location.getOrdinate(1) >= getMinY() && location.getOrdinate(1) <= getMaxY());
/*     */   }
/*     */   
/*     */   public BoundingBox toBounds(CoordinateReferenceSystem targetCRS) throws TransformException {
/* 510 */     Envelope transformed = new GeneralEnvelope(this);
/* 511 */     transformed = CRS.transform(transformed, targetCRS);
/* 512 */     return new Envelope2D(transformed);
/*     */   }
/*     */   
/*     */   private void ensureCompatibleReferenceSystem(BoundingBox bbox) throws MismatchedReferenceSystemException {
/* 524 */     if (this.crs != null) {
/* 525 */       CoordinateReferenceSystem other = bbox.getCoordinateReferenceSystem();
/* 526 */       if (other != null && 
/* 527 */         !CRS.equalsIgnoreMetadata(this.crs, other))
/* 528 */         throw new MismatchedReferenceSystemException(Errors.format(92)); 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void ensureCompatibleReferenceSystem(DirectPosition location) {
/* 536 */     if (this.crs != null) {
/* 537 */       CoordinateReferenceSystem other = location.getCoordinateReferenceSystem();
/* 538 */       if (other != null && 
/* 539 */         !CRS.equalsIgnoreMetadata(this.crs, other))
/* 540 */         throw new MismatchedReferenceSystemException(Errors.format(92)); 
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean isNull() {
/* 554 */     return (getMinX() == 0.0D && getMinY() == 0.0D && getWidth() < 0.0D && getHeight() < 0.0D);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\geometry\Envelope2D.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */