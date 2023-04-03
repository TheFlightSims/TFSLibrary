/*     */ package org.geotools.geometry.jts;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import org.geotools.geometry.DirectPosition2D;
/*     */ import org.geotools.geometry.GeneralEnvelope;
/*     */ import org.geotools.referencing.CRS;
/*     */ import org.geotools.resources.Classes;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ import org.opengis.geometry.BoundingBox;
/*     */ import org.opengis.geometry.DirectPosition;
/*     */ import org.opengis.geometry.Envelope;
/*     */ import org.opengis.geometry.MismatchedDimensionException;
/*     */ import org.opengis.geometry.MismatchedReferenceSystemException;
/*     */ import org.opengis.referencing.FactoryException;
/*     */ import org.opengis.referencing.crs.CoordinateReferenceSystem;
/*     */ import org.opengis.referencing.operation.CoordinateOperation;
/*     */ import org.opengis.referencing.operation.CoordinateOperationFactory;
/*     */ import org.opengis.referencing.operation.MathTransform;
/*     */ import org.opengis.referencing.operation.TransformException;
/*     */ 
/*     */ public class ReferencedEnvelope extends Envelope implements Envelope, BoundingBox {
/*  68 */   public static ReferencedEnvelope EVERYTHING = new ReferencedEnvelope(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, null) {
/*     */       private static final long serialVersionUID = -3188702602373537164L;
/*     */       
/*     */       public boolean contains(BoundingBox bbox) {
/*  73 */         return true;
/*     */       }
/*     */       
/*     */       public boolean contains(Coordinate p) {
/*  77 */         return true;
/*     */       }
/*     */       
/*     */       public boolean contains(DirectPosition pos) {
/*  81 */         return true;
/*     */       }
/*     */       
/*     */       public boolean contains(double x, double y) {
/*  85 */         return true;
/*     */       }
/*     */       
/*     */       public boolean contains(Envelope other) {
/*  89 */         return true;
/*     */       }
/*     */       
/*     */       public boolean isEmpty() {
/*  93 */         return false;
/*     */       }
/*     */       
/*     */       public boolean isNull() {
/*  97 */         return true;
/*     */       }
/*     */       
/*     */       public double getArea() {
/* 102 */         return Double.POSITIVE_INFINITY;
/*     */       }
/*     */       
/*     */       public void setBounds(BoundingBox arg0) {
/* 106 */         throw new IllegalStateException("Cannot modify ReferencedEnvelope.EVERYTHING");
/*     */       }
/*     */       
/*     */       public Coordinate centre() {
/* 109 */         return new Coordinate();
/*     */       }
/*     */       
/*     */       public void setToNull() {}
/*     */       
/*     */       public boolean equals(Object obj) {
/* 115 */         if (obj == EVERYTHING)
/* 116 */           return true; 
/* 118 */         if (obj instanceof ReferencedEnvelope) {
/* 119 */           ReferencedEnvelope other = (ReferencedEnvelope)obj;
/* 120 */           if (other.crs != EVERYTHING.crs)
/* 120 */             return false; 
/* 121 */           if (other.getMinX() != EVERYTHING.getMinX())
/* 121 */             return false; 
/* 122 */           if (other.getMinY() != EVERYTHING.getMinY())
/* 122 */             return false; 
/* 123 */           if (other.getMaxX() != EVERYTHING.getMaxX())
/* 123 */             return false; 
/* 124 */           if (other.getMaxY() != EVERYTHING.getMaxY())
/* 124 */             return false; 
/* 126 */           return true;
/*     */         } 
/* 128 */         return super.equals(obj);
/*     */       }
/*     */       
/*     */       public String toString() {
/* 132 */         return "ReferencedEnvelope.EVERYTHING";
/*     */       }
/*     */     };
/*     */   
/*     */   private static final long serialVersionUID = -3188702602373537163L;
/*     */   
/*     */   protected CoordinateReferenceSystem crs;
/*     */   
/*     */   public ReferencedEnvelope() {
/* 149 */     this((CoordinateReferenceSystem)null);
/*     */   }
/*     */   
/*     */   public ReferencedEnvelope(CoordinateReferenceSystem crs) throws MismatchedDimensionException {
/* 160 */     this.crs = crs;
/* 161 */     checkCoordinateReferenceSystemDimension();
/*     */   }
/*     */   
/*     */   public ReferencedEnvelope(double x1, double x2, double y1, double y2, CoordinateReferenceSystem crs) throws MismatchedDimensionException {
/* 177 */     super(x1, x2, y1, y2);
/* 178 */     this.crs = crs;
/* 179 */     checkCoordinateReferenceSystemDimension();
/*     */   }
/*     */   
/*     */   public ReferencedEnvelope(Rectangle2D rectangle, CoordinateReferenceSystem crs) throws MismatchedDimensionException {
/* 194 */     this(rectangle.getMinX(), rectangle.getMaxX(), rectangle.getMinY(), rectangle.getMaxY(), crs);
/*     */   }
/*     */   
/*     */   public ReferencedEnvelope(ReferencedEnvelope envelope) throws MismatchedDimensionException {
/* 207 */     super(envelope);
/* 208 */     this.crs = envelope.getCoordinateReferenceSystem();
/* 209 */     checkCoordinateReferenceSystemDimension();
/*     */   }
/*     */   
/*     */   public ReferencedEnvelope(BoundingBox bbox) throws MismatchedDimensionException {
/* 221 */     this(bbox.getMinX(), bbox.getMaxX(), bbox.getMinY(), bbox.getMaxY(), bbox.getCoordinateReferenceSystem());
/*     */   }
/*     */   
/*     */   public ReferencedEnvelope(Envelope envelope) throws MismatchedDimensionException {
/* 235 */     super(envelope.getMinimum(0), envelope.getMaximum(0), envelope.getMinimum(1), envelope.getMaximum(1));
/* 237 */     this.crs = envelope.getCoordinateReferenceSystem();
/* 238 */     checkCoordinateReferenceSystemDimension();
/*     */   }
/*     */   
/*     */   public ReferencedEnvelope(Envelope envelope, CoordinateReferenceSystem crs) throws MismatchedDimensionException {
/* 250 */     super(envelope);
/* 251 */     this.crs = crs;
/* 252 */     checkCoordinateReferenceSystemDimension();
/*     */   }
/*     */   
/*     */   public void init(BoundingBox bounds) {
/* 259 */     init(bounds.getMinimum(0), bounds.getMaximum(0), bounds.getMinimum(1), bounds.getMaximum(1));
/* 261 */     this.crs = bounds.getCoordinateReferenceSystem();
/*     */   }
/*     */   
/*     */   private static Envelope getJTSEnvelope(BoundingBox bbox) {
/* 268 */     if (bbox == null)
/* 269 */       throw new NullPointerException("Provided bbox envelope was null"); 
/* 271 */     if (bbox instanceof Envelope)
/* 272 */       return (Envelope)bbox; 
/* 274 */     return new ReferencedEnvelope(bbox);
/*     */   }
/*     */   
/*     */   protected void checkCoordinateReferenceSystemDimension() throws MismatchedDimensionException {
/* 284 */     if (this.crs != null) {
/* 285 */       int expected = getDimension();
/* 286 */       int dimension = this.crs.getCoordinateSystem().getDimension();
/* 287 */       if (dimension > expected)
/* 290 */         throw new MismatchedDimensionException(Errors.format(94, this.crs.getName().getCode(), new Integer(dimension), new Integer(expected))); 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void ensureCompatibleReferenceSystem(BoundingBox bbox) throws MismatchedReferenceSystemException {
/* 305 */     if (this.crs != null) {
/* 306 */       CoordinateReferenceSystem other = bbox.getCoordinateReferenceSystem();
/* 307 */       if (other != null && 
/* 308 */         !CRS.equalsIgnoreMetadata(this.crs, other))
/* 309 */         throw new MismatchedReferenceSystemException(Errors.format(92)); 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void ensureCompatibleReferenceSystem(DirectPosition location) {
/* 321 */     if (this.crs != null) {
/* 322 */       CoordinateReferenceSystem other = location.getCoordinateReferenceSystem();
/* 323 */       if (other != null && 
/* 324 */         !CRS.equalsIgnoreMetadata(this.crs, other))
/* 325 */         throw new MismatchedReferenceSystemException(Errors.format(92)); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public CoordinateReferenceSystem getCoordinateReferenceSystem() {
/* 335 */     return this.crs;
/*     */   }
/*     */   
/*     */   public CoordinateReferenceSystem crs() {
/* 343 */     return getCoordinateReferenceSystem();
/*     */   }
/*     */   
/*     */   public int getDimension() {
/* 350 */     return 2;
/*     */   }
/*     */   
/*     */   public double getMinimum(int dimension) {
/* 357 */     switch (dimension) {
/*     */       case 0:
/* 359 */         return getMinX();
/*     */       case 1:
/* 362 */         return getMinY();
/*     */     } 
/* 365 */     throw new IndexOutOfBoundsException(String.valueOf(dimension));
/*     */   }
/*     */   
/*     */   public double minX() {
/* 373 */     return getMinX();
/*     */   }
/*     */   
/*     */   public double minY() {
/* 380 */     return getMinY();
/*     */   }
/*     */   
/*     */   public double getMaximum(int dimension) {
/* 387 */     switch (dimension) {
/*     */       case 0:
/* 389 */         return getMaxX();
/*     */       case 1:
/* 392 */         return getMaxY();
/*     */     } 
/* 395 */     throw new IndexOutOfBoundsException(String.valueOf(dimension));
/*     */   }
/*     */   
/*     */   public double maxX() {
/* 403 */     return getMaxX();
/*     */   }
/*     */   
/*     */   public double maxY() {
/* 410 */     return getMaxY();
/*     */   }
/*     */   
/*     */   public double getCenter(int dimension) {
/* 417 */     return getMedian(dimension);
/*     */   }
/*     */   
/*     */   public double getMedian(int dimension) {
/* 424 */     switch (dimension) {
/*     */       case 0:
/* 426 */         return 0.5D * (getMinX() + getMaxX());
/*     */       case 1:
/* 429 */         return 0.5D * (getMinY() + getMaxY());
/*     */     } 
/* 432 */     throw new IndexOutOfBoundsException(String.valueOf(dimension));
/*     */   }
/*     */   
/*     */   public double getLength(int dimension) {
/* 440 */     return getSpan(dimension);
/*     */   }
/*     */   
/*     */   public double getSpan(int dimension) {
/* 448 */     switch (dimension) {
/*     */       case 0:
/* 450 */         return getWidth();
/*     */       case 1:
/* 453 */         return getHeight();
/*     */     } 
/* 456 */     throw new IndexOutOfBoundsException(String.valueOf(dimension));
/*     */   }
/*     */   
/*     */   public DirectPosition getLowerCorner() {
/* 465 */     return (DirectPosition)new DirectPosition2D(this.crs, getMinX(), getMinY());
/*     */   }
/*     */   
/*     */   public DirectPosition getUpperCorner() {
/* 473 */     return (DirectPosition)new DirectPosition2D(this.crs, getMaxX(), getMaxY());
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 482 */     return isNull();
/*     */   }
/*     */   
/*     */   public boolean contains(DirectPosition pos) {
/* 491 */     ensureCompatibleReferenceSystem(pos);
/* 492 */     return contains(pos.getOrdinate(0), pos.getOrdinate(1));
/*     */   }
/*     */   
/*     */   public boolean contains(BoundingBox bbox) {
/* 501 */     ensureCompatibleReferenceSystem(bbox);
/* 503 */     return contains(getJTSEnvelope(bbox));
/*     */   }
/*     */   
/*     */   public boolean intersects(BoundingBox bbox) {
/* 512 */     ensureCompatibleReferenceSystem(bbox);
/* 514 */     return intersects(getJTSEnvelope(bbox));
/*     */   }
/*     */   
/*     */   public Envelope intersection(Envelope env) {
/* 521 */     if (env instanceof BoundingBox) {
/* 522 */       BoundingBox bbox = (BoundingBox)env;
/* 523 */       ensureCompatibleReferenceSystem(bbox);
/*     */     } 
/* 525 */     return super.intersection(env);
/*     */   }
/*     */   
/*     */   public void include(BoundingBox bbox) {
/* 533 */     if (this.crs == null) {
/* 534 */       this.crs = bbox.getCoordinateReferenceSystem();
/*     */     } else {
/* 537 */       ensureCompatibleReferenceSystem(bbox);
/*     */     } 
/* 539 */     super.expandToInclude(getJTSEnvelope(bbox));
/*     */   }
/*     */   
/*     */   public void expandToInclude(DirectPosition pt) {
/* 547 */     Coordinate coordinate = new Coordinate(pt.getOrdinate(0), pt.getOrdinate(1));
/* 548 */     expandToInclude(coordinate);
/*     */   }
/*     */   
/*     */   public void expandToInclude(Envelope other) {
/* 555 */     if (other instanceof BoundingBox) {
/* 556 */       if (other.isNull())
/*     */         return; 
/* 560 */       BoundingBox bbox = (BoundingBox)other;
/* 561 */       ensureCompatibleReferenceSystem(bbox);
/* 563 */       expandToInclude(bbox.getLowerCorner());
/* 564 */       expandToInclude(bbox.getUpperCorner());
/*     */     } else {
/* 567 */       super.expandToInclude(other);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void include(double x, double y) {
/* 577 */     expandToInclude(x, y);
/*     */   }
/*     */   
/*     */   public void setBounds(BoundingBox bbox) {
/* 586 */     ensureCompatibleReferenceSystem(bbox);
/* 587 */     init(getJTSEnvelope(bbox));
/*     */   }
/*     */   
/*     */   public BoundingBox toBounds(CoordinateReferenceSystem targetCRS) throws TransformException {
/*     */     try {
/* 600 */       return transform(targetCRS, true);
/* 601 */     } catch (FactoryException e) {
/* 602 */       throw new TransformException(e.getLocalizedMessage(), e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public ReferencedEnvelope transform(CoordinateReferenceSystem targetCRS, boolean lenient) throws TransformException, FactoryException {
/* 624 */     return transform(targetCRS, lenient, 5);
/*     */   }
/*     */   
/*     */   public ReferencedEnvelope transform(CoordinateReferenceSystem targetCRS, boolean lenient, int numPointsForTransformation) throws TransformException, FactoryException {
/* 650 */     if (this.crs == null) {
/* 651 */       if (isEmpty())
/* 654 */         return new ReferencedEnvelope(targetCRS); 
/* 658 */       throw new NullPointerException("Unable to transform referenced envelope, crs has not yet been provided.");
/*     */     } 
/* 661 */     if (getDimension() != targetCRS.getCoordinateSystem().getDimension()) {
/* 662 */       if (lenient)
/* 663 */         return JTS.transformTo3D(this, targetCRS, lenient, numPointsForTransformation); 
/* 666 */       throw new MismatchedDimensionException(Errors.format(94, this.crs.getName().getCode(), new Integer(getDimension()), new Integer(targetCRS.getCoordinateSystem().getDimension())));
/*     */     } 
/* 675 */     CoordinateOperationFactory coordinateOperationFactory = CRS.getCoordinateOperationFactory(lenient);
/* 677 */     CoordinateOperation operation = coordinateOperationFactory.createOperation(this.crs, targetCRS);
/* 678 */     GeneralEnvelope transformed = CRS.transform(operation, this);
/* 679 */     transformed.setCoordinateReferenceSystem(targetCRS);
/* 684 */     ReferencedEnvelope target = new ReferencedEnvelope((Envelope)transformed);
/* 685 */     MathTransform transform = operation.getMathTransform();
/* 686 */     JTS.transform(this, target, transform, numPointsForTransformation);
/* 688 */     return target;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 697 */     int code = super.hashCode() ^ 0xAB296275;
/* 698 */     if (this.crs != null)
/* 699 */       code ^= this.crs.hashCode(); 
/* 701 */     return code;
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/* 709 */     if (super.equals(object)) {
/* 710 */       CoordinateReferenceSystem otherCRS = (object instanceof ReferencedEnvelope) ? ((ReferencedEnvelope)object).crs : null;
/* 713 */       return CRS.equalsIgnoreMetadata(this.crs, otherCRS);
/*     */     } 
/* 715 */     return false;
/*     */   }
/*     */   
/*     */   public boolean boundsEquals2D(Envelope other, double eps) {
/* 737 */     eps *= 0.5D * (getWidth() + getHeight());
/* 739 */     double[] delta = new double[4];
/* 740 */     delta[0] = getMinimum(0) - other.getMinimum(0);
/* 741 */     delta[1] = getMaximum(0) - other.getMaximum(0);
/* 742 */     delta[2] = getMinimum(1) - other.getMinimum(1);
/* 743 */     delta[3] = getMaximum(1) - other.getMaximum(1);
/* 745 */     for (int i = 0; i < delta.length; i++) {
/* 750 */       if (Math.abs(delta[i]) > eps)
/* 751 */         return false; 
/*     */     } 
/* 754 */     return true;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 763 */     StringBuilder buffer = (new StringBuilder(Classes.getShortClassName(this))).append('[');
/* 764 */     int dimension = getDimension();
/* 766 */     for (int i = 0; i < dimension; i++) {
/* 767 */       if (i != 0)
/* 768 */         buffer.append(", "); 
/* 771 */       buffer.append(getMinimum(i)).append(" : ").append(getMaximum(i));
/*     */     } 
/* 774 */     return buffer.append(']').toString();
/*     */   }
/*     */   
/*     */   public static ReferencedEnvelope create(ReferencedEnvelope origional) {
/* 783 */     if (origional instanceof ReferencedEnvelope3D)
/* 784 */       return new ReferencedEnvelope3D((ReferencedEnvelope3D)origional); 
/* 786 */     return new ReferencedEnvelope(origional);
/*     */   }
/*     */   
/*     */   public static ReferencedEnvelope create(CoordinateReferenceSystem crs) {
/* 796 */     if (crs != null && crs.getCoordinateSystem().getDimension() > 2)
/* 797 */       return new ReferencedEnvelope3D(crs); 
/* 799 */     return new ReferencedEnvelope(crs);
/*     */   }
/*     */   
/*     */   public static ReferencedEnvelope create(Envelope env, CoordinateReferenceSystem crs) {
/* 812 */     if (env == null)
/* 813 */       return null; 
/* 816 */     if (env.getDimension() >= 3)
/* 817 */       return new ReferencedEnvelope3D((ReferencedEnvelope3D)reference(env), crs); 
/* 820 */     return new ReferencedEnvelope(reference(env), crs);
/*     */   }
/*     */   
/*     */   public static ReferencedEnvelope create(ReferencedEnvelope env, CoordinateReferenceSystem crs) {
/* 830 */     return create(env, crs);
/*     */   }
/*     */   
/*     */   public static ReferencedEnvelope create(Envelope env, CoordinateReferenceSystem crs) {
/* 840 */     if (env == null)
/* 841 */       return null; 
/* 843 */     if (crs.getCoordinateSystem().getDimension() >= 3)
/* 844 */       return new ReferencedEnvelope3D(env.getMinX(), env.getMaxX(), env.getMinY(), env.getMaxY(), Double.NaN, Double.NaN, crs); 
/* 847 */     return new ReferencedEnvelope(env, crs);
/*     */   }
/*     */   
/*     */   public static ReferencedEnvelope reference(Envelope e) {
/* 864 */     if (e == null)
/* 865 */       return null; 
/* 867 */     if (e instanceof ReferencedEnvelope3D)
/* 868 */       return (ReferencedEnvelope3D)e; 
/* 871 */     if (e instanceof ReferencedEnvelope)
/* 872 */       return (ReferencedEnvelope)e; 
/* 875 */     return new ReferencedEnvelope(e, null);
/*     */   }
/*     */   
/*     */   public static ReferencedEnvelope reference(BoundingBox e) {
/* 890 */     return reference((Envelope)e);
/*     */   }
/*     */   
/*     */   public static ReferencedEnvelope reference(ReferencedEnvelope e) {
/* 904 */     return reference(e);
/*     */   }
/*     */   
/*     */   public static ReferencedEnvelope reference(Envelope env) {
/* 916 */     if (env == null)
/* 917 */       return null; 
/* 920 */     if (env instanceof ReferencedEnvelope3D)
/* 921 */       return (ReferencedEnvelope3D)env; 
/* 924 */     if (env instanceof ReferencedEnvelope)
/* 925 */       return (ReferencedEnvelope)env; 
/* 928 */     if (env.getDimension() == 3)
/* 929 */       return new ReferencedEnvelope3D(env); 
/* 932 */     return new ReferencedEnvelope(env);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\geometry\jts\ReferencedEnvelope.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */