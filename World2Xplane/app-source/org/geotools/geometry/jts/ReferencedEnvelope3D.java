/*      */ package org.geotools.geometry.jts;
/*      */ 
/*      */ import com.vividsolutions.jts.geom.Coordinate;
/*      */ import com.vividsolutions.jts.geom.Envelope;
/*      */ import org.geotools.geometry.DirectPosition3D;
/*      */ import org.geotools.geometry.GeneralEnvelope;
/*      */ import org.geotools.referencing.CRS;
/*      */ import org.geotools.resources.i18n.Errors;
/*      */ import org.opengis.geometry.BoundingBox;
/*      */ import org.opengis.geometry.BoundingBox3D;
/*      */ import org.opengis.geometry.DirectPosition;
/*      */ import org.opengis.geometry.Envelope;
/*      */ import org.opengis.geometry.MismatchedDimensionException;
/*      */ import org.opengis.referencing.FactoryException;
/*      */ import org.opengis.referencing.crs.CoordinateReferenceSystem;
/*      */ import org.opengis.referencing.operation.CoordinateOperation;
/*      */ import org.opengis.referencing.operation.CoordinateOperationFactory;
/*      */ import org.opengis.referencing.operation.MathTransform;
/*      */ import org.opengis.referencing.operation.TransformException;
/*      */ 
/*      */ public class ReferencedEnvelope3D extends ReferencedEnvelope implements BoundingBox3D {
/*      */   private static final long serialVersionUID = -3188702602373537163L;
/*      */   
/*      */   private double minz;
/*      */   
/*      */   private double maxz;
/*      */   
/*      */   public static boolean intersects(Coordinate p1, Coordinate p2, Coordinate q) {
/*   72 */     if (q.x >= ((p1.x < p2.x) ? p1.x : p2.x) && q.x <= ((p1.x > p2.x) ? p1.x : p2.x) && q.y >= ((p1.y < p2.y) ? p1.y : p2.y) && q.y <= ((p1.y > p2.y) ? p1.y : p2.y))
/*   76 */       return true; 
/*   78 */     return false;
/*      */   }
/*      */   
/*      */   public static boolean intersects(Coordinate p1, Coordinate p2, Coordinate q1, Coordinate q2) {
/*   97 */     double minq = Math.min(q1.x, q2.x);
/*   98 */     double maxq = Math.max(q1.x, q2.x);
/*   99 */     double minp = Math.min(p1.x, p2.x);
/*  100 */     double maxp = Math.max(p1.x, p2.x);
/*  102 */     if (minp > maxq)
/*  103 */       return false; 
/*  104 */     if (maxp < minq)
/*  105 */       return false; 
/*  107 */     minq = Math.min(q1.y, q2.y);
/*  108 */     maxq = Math.max(q1.y, q2.y);
/*  109 */     minp = Math.min(p1.y, p2.y);
/*  110 */     maxp = Math.max(p1.y, p2.y);
/*  112 */     if (minp > maxq)
/*  113 */       return false; 
/*  114 */     if (maxp < minq)
/*  115 */       return false; 
/*  116 */     return true;
/*      */   }
/*      */   
/*      */   public void init() {
/*  134 */     setToNull();
/*      */   }
/*      */   
/*      */   public void init(double x1, double x2, double y1, double y2, double z1, double z2) {
/*  156 */     init(x1, x2, y1, y2);
/*  157 */     if (z1 < z2) {
/*  158 */       this.minz = z1;
/*  159 */       this.maxz = z2;
/*      */     } else {
/*  161 */       this.minz = z2;
/*  162 */       this.maxz = z1;
/*      */     } 
/*      */   }
/*      */   
/*      */   public void init(Coordinate p1, Coordinate p2) {
/*  176 */     init(p1.x, p2.x, p1.y, p2.y, p1.z, p2.z);
/*      */   }
/*      */   
/*      */   public void init(Coordinate p) {
/*  187 */     init(p.x, p.x, p.y, p.y, p.z, p.z);
/*      */   }
/*      */   
/*      */   public void init(Envelope env) {
/*  191 */     super.init(env);
/*  192 */     if (env instanceof BoundingBox3D) {
/*  193 */       this.minz = ((BoundingBox3D)env).getMinZ();
/*  194 */       this.maxz = ((BoundingBox3D)env).getMaxZ();
/*      */     } 
/*      */   }
/*      */   
/*      */   public void init(ReferencedEnvelope3D env) {
/*  205 */     super.init(env);
/*  206 */     this.minz = env.minz;
/*  207 */     this.maxz = env.maxz;
/*      */   }
/*      */   
/*      */   public void setToNull() {
/*  215 */     super.setToNull();
/*  216 */     this.minz = 0.0D;
/*  217 */     this.maxz = -1.0D;
/*      */   }
/*      */   
/*      */   public double getDepth() {
/*  226 */     if (isNull())
/*  227 */       return 0.0D; 
/*  229 */     return this.maxz - this.minz;
/*      */   }
/*      */   
/*      */   public double getMinZ() {
/*  239 */     return this.minz;
/*      */   }
/*      */   
/*      */   public double getMaxZ() {
/*  249 */     return this.maxz;
/*      */   }
/*      */   
/*      */   public double getVolume() {
/*  259 */     return getWidth() * getHeight() * getDepth();
/*      */   }
/*      */   
/*      */   public double minExtent() {
/*  268 */     if (isNull())
/*  269 */       return 0.0D; 
/*  270 */     return Math.min(getWidth(), Math.min(getHeight(), getDepth()));
/*      */   }
/*      */   
/*      */   public double maxExtent() {
/*  279 */     if (isNull())
/*  280 */       return 0.0D; 
/*  281 */     return Math.max(getWidth(), Math.max(getHeight(), getDepth()));
/*      */   }
/*      */   
/*      */   public void expandToInclude(Coordinate p) {
/*  293 */     expandToInclude(p.x, p.y, p.z);
/*      */   }
/*      */   
/*      */   public void expandBy(double distance) {
/*  304 */     expandBy(distance, distance, distance);
/*      */   }
/*      */   
/*      */   public void expandBy(double deltaX, double deltaY, double deltaZ) {
/*  317 */     if (isNull())
/*      */       return; 
/*  320 */     this.minz -= deltaZ;
/*  321 */     this.maxz += deltaZ;
/*  322 */     expandBy(deltaX, deltaY);
/*  325 */     if (this.minz > this.maxz)
/*  326 */       setToNull(); 
/*      */   }
/*      */   
/*      */   public void expandToInclude(double x, double y, double z) {
/*  344 */     if (isNull()) {
/*  345 */       expandToInclude(x, y);
/*  346 */       this.minz = z;
/*  347 */       this.maxz = z;
/*      */     } else {
/*  349 */       expandToInclude(x, y);
/*  350 */       if (z < this.minz)
/*  351 */         this.minz = z; 
/*  353 */       if (z > this.maxz)
/*  354 */         this.maxz = z; 
/*      */     } 
/*      */   }
/*      */   
/*      */   public void expandToInclude(DirectPosition pt) {
/*  360 */     double x = pt.getOrdinate(0);
/*  361 */     double y = pt.getOrdinate(1);
/*  362 */     double z = (pt.getDimension() >= 3) ? pt.getOrdinate(2) : Double.NaN;
/*  363 */     expandToInclude(x, y, z);
/*      */   }
/*      */   
/*      */   public void translate(double transX, double transY, double transZ) {
/*  376 */     if (isNull())
/*      */       return; 
/*  379 */     init(getMinX() + transX, getMaxX() + transX, getMinY() + transY, getMaxY() + transY, getMinZ() + transZ, getMaxZ() + transZ);
/*      */   }
/*      */   
/*      */   public Coordinate centre() {
/*  391 */     if (isNull())
/*  392 */       return null; 
/*  393 */     return new Coordinate((getMinX() + getMaxX()) / 2.0D, (getMinY() + getMaxY()) / 2.0D, (getMinZ() + getMaxZ()) / 2.0D);
/*      */   }
/*      */   
/*      */   public boolean intersects(ReferencedEnvelope3D other) {
/*  407 */     if (isNull() || other.isNull())
/*  408 */       return false; 
/*  410 */     return (intersects(other) && other.minz <= this.maxz && other.maxz >= this.minz);
/*      */   }
/*      */   
/*      */   public boolean overlaps(ReferencedEnvelope3D other) {
/*  419 */     return intersects(other);
/*      */   }
/*      */   
/*      */   public boolean intersects(Coordinate p) {
/*  432 */     return intersects(p.x, p.y, p.z);
/*      */   }
/*      */   
/*      */   public boolean overlaps(Coordinate p) {
/*  439 */     return intersects(p);
/*      */   }
/*      */   
/*      */   public boolean intersects(double x, double y, double z) {
/*  456 */     if (isNull())
/*  457 */       return false; 
/*  458 */     return (intersects(x, y) && z <= this.maxz && z <= this.maxz);
/*      */   }
/*      */   
/*      */   public boolean overlaps(double x, double y, double z) {
/*  465 */     return intersects(x, y, z);
/*      */   }
/*      */   
/*      */   public boolean contains(Coordinate p) {
/*  483 */     return covers(p);
/*      */   }
/*      */   
/*      */   public boolean contains(double x, double y, double z) {
/*  504 */     return covers(x, y, z);
/*      */   }
/*      */   
/*      */   public boolean covers(double x, double y, double z) {
/*  520 */     if (isNull())
/*  521 */       return false; 
/*  522 */     return (covers(x, y) && z >= this.minz && z <= this.maxz);
/*      */   }
/*      */   
/*      */   public boolean covers(Coordinate p) {
/*  535 */     return covers(p.x, p.y, p.z);
/*      */   }
/*      */   
/*      */   public boolean covers(ReferencedEnvelope3D other) {
/*  547 */     if (isNull() || other.isNull())
/*  548 */       return false; 
/*  550 */     return (covers(other) && other.getMinZ() >= this.minz && other.getMaxZ() <= this.maxz);
/*      */   }
/*      */   
/*      */   public double distance(ReferencedEnvelope3D env) {
/*  560 */     if (intersects(env))
/*  561 */       return 0.0D; 
/*  563 */     double dx = 0.0D;
/*  564 */     if (getMaxX() < env.getMinX()) {
/*  565 */       dx = env.getMinX() - getMaxX();
/*  566 */     } else if (getMinX() > env.getMaxX()) {
/*  567 */       dx = getMinX() - env.getMaxX();
/*      */     } 
/*  569 */     double dy = 0.0D;
/*  570 */     if (getMaxY() < env.getMinY()) {
/*  571 */       dy = env.getMinY() - getMaxY();
/*  572 */     } else if (getMinY() > env.getMaxY()) {
/*  573 */       dy = getMinY() - env.getMaxY();
/*      */     } 
/*  575 */     double dz = 0.0D;
/*  576 */     if (this.maxz < env.minz) {
/*  577 */       dz = env.minz - this.maxz;
/*  578 */     } else if (this.minz > env.maxz) {
/*  579 */       dy = this.minz - env.maxz;
/*      */     } 
/*  583 */     if (dx == 0.0D && dz == 0.0D)
/*  584 */       return dy; 
/*  585 */     if (dy == 0.0D && dz == 0.0D)
/*  586 */       return dx; 
/*  587 */     if (dx == 0.0D && dy == 0.0D)
/*  588 */       return dz; 
/*  589 */     return Math.sqrt(dx * dx + dy * dy + dz * dz);
/*      */   }
/*      */   
/*  595 */   public static ReferencedEnvelope3D EVERYTHING = new ReferencedEnvelope3D(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, null) {
/*      */       private static final long serialVersionUID = -3188702602373537164L;
/*      */       
/*      */       public boolean contains(BoundingBox bbox) {
/*  601 */         return true;
/*      */       }
/*      */       
/*      */       public boolean contains(Coordinate p) {
/*  605 */         return true;
/*      */       }
/*      */       
/*      */       public boolean contains(DirectPosition pos) {
/*  609 */         return true;
/*      */       }
/*      */       
/*      */       public boolean contains(double x, double y, double z) {
/*  613 */         return true;
/*      */       }
/*      */       
/*      */       public boolean isEmpty() {
/*  617 */         return false;
/*      */       }
/*      */       
/*      */       public boolean isNull() {
/*  621 */         return true;
/*      */       }
/*      */       
/*      */       public double getArea() {
/*  626 */         return Double.POSITIVE_INFINITY;
/*      */       }
/*      */       
/*      */       public double getVolume() {
/*  631 */         return Double.POSITIVE_INFINITY;
/*      */       }
/*      */       
/*      */       public void setBounds(BoundingBox3D arg0) {
/*  635 */         throw new IllegalStateException("Cannot modify ReferencedEnvelope.EVERYTHING");
/*      */       }
/*      */       
/*      */       public Coordinate centre() {
/*  638 */         return new Coordinate();
/*      */       }
/*      */       
/*      */       public void setToNull() {}
/*      */       
/*      */       public boolean equals(Object obj) {
/*  644 */         if (obj == EVERYTHING)
/*  645 */           return true; 
/*  647 */         if (obj instanceof ReferencedEnvelope3D) {
/*  648 */           ReferencedEnvelope3D other = (ReferencedEnvelope3D)obj;
/*  649 */           if (other.crs != EVERYTHING.crs)
/*  649 */             return false; 
/*  650 */           if (other.getMinX() != EVERYTHING.getMinX())
/*  650 */             return false; 
/*  651 */           if (other.getMinY() != EVERYTHING.getMinY())
/*  651 */             return false; 
/*  652 */           if (other.getMinZ() != EVERYTHING.getMinZ())
/*  652 */             return false; 
/*  653 */           if (other.getMaxX() != EVERYTHING.getMaxX())
/*  653 */             return false; 
/*  654 */           if (other.getMaxY() != EVERYTHING.getMaxY())
/*  654 */             return false; 
/*  655 */           if (other.getMaxZ() != EVERYTHING.getMaxZ())
/*  655 */             return false; 
/*  657 */           return true;
/*      */         } 
/*  659 */         return super.equals(obj);
/*      */       }
/*      */       
/*      */       public String toString() {
/*  663 */         return "ReferencedEnvelope.EVERYTHING";
/*      */       }
/*      */     };
/*      */   
/*      */   public ReferencedEnvelope3D() {
/*  671 */     this((CoordinateReferenceSystem)null);
/*      */   }
/*      */   
/*      */   public ReferencedEnvelope3D(CoordinateReferenceSystem crs) throws MismatchedDimensionException {
/*  682 */     this.crs = crs;
/*  683 */     checkCoordinateReferenceSystemDimension();
/*      */   }
/*      */   
/*      */   public ReferencedEnvelope3D(double x1, double x2, double y1, double y2, double z1, double z2, CoordinateReferenceSystem crs) throws MismatchedDimensionException {
/*  701 */     init(x1, x2, y1, y2, z1, z2);
/*  702 */     this.crs = crs;
/*  703 */     checkCoordinateReferenceSystemDimension();
/*      */   }
/*      */   
/*      */   public ReferencedEnvelope3D(ReferencedEnvelope3D envelope) throws MismatchedDimensionException {
/*  715 */     init(envelope);
/*  716 */     this.crs = envelope.getCoordinateReferenceSystem();
/*  717 */     checkCoordinateReferenceSystemDimension();
/*      */   }
/*      */   
/*      */   public ReferencedEnvelope3D(BoundingBox3D bbox) throws MismatchedDimensionException {
/*  728 */     this(bbox.getMinX(), bbox.getMaxX(), bbox.getMinY(), bbox.getMaxY(), bbox.getMinZ(), bbox.getMaxZ(), bbox.getCoordinateReferenceSystem());
/*      */   }
/*      */   
/*      */   public ReferencedEnvelope3D(Envelope envelope, CoordinateReferenceSystem crs) throws MismatchedDimensionException {
/*  741 */     super(envelope, crs);
/*  742 */     if (envelope instanceof ReferencedEnvelope3D) {
/*  743 */       this.minz = ((ReferencedEnvelope3D)envelope).getMinZ();
/*  744 */       this.maxz = ((ReferencedEnvelope3D)envelope).getMaxZ();
/*      */     } 
/*      */   }
/*      */   
/*      */   public ReferencedEnvelope3D(Envelope envelope) throws MismatchedDimensionException {
/*  757 */     init(envelope.getMinimum(0), envelope.getMaximum(0), envelope.getMinimum(1), envelope.getMaximum(1), envelope.getMinimum(2), envelope.getMaximum(2));
/*  760 */     this.crs = envelope.getCoordinateReferenceSystem();
/*  761 */     checkCoordinateReferenceSystemDimension();
/*      */   }
/*      */   
/*      */   public ReferencedEnvelope3D(ReferencedEnvelope3D envelope, CoordinateReferenceSystem crs) throws MismatchedDimensionException {
/*  773 */     init(envelope);
/*  774 */     this.crs = crs;
/*  775 */     checkCoordinateReferenceSystemDimension();
/*      */   }
/*      */   
/*      */   public void init(BoundingBox bounds) {
/*  782 */     init(bounds.getMinimum(0), bounds.getMaximum(0), bounds.getMinimum(1), bounds.getMaximum(1), bounds.getMinimum(2), bounds.getMaximum(2));
/*  785 */     this.crs = bounds.getCoordinateReferenceSystem();
/*      */   }
/*      */   
/*      */   private static ReferencedEnvelope3D getJTSEnvelope(BoundingBox3D bbox) {
/*  792 */     if (bbox == null)
/*  793 */       throw new NullPointerException("Provided bbox envelope was null"); 
/*  795 */     if (bbox instanceof ReferencedEnvelope3D)
/*  796 */       return (ReferencedEnvelope3D)bbox; 
/*  798 */     return new ReferencedEnvelope3D(bbox);
/*      */   }
/*      */   
/*      */   public int getDimension() {
/*  805 */     return 3;
/*      */   }
/*      */   
/*      */   public double getMinimum(int dimension) {
/*  812 */     switch (dimension) {
/*      */       case 0:
/*  814 */         return getMinX();
/*      */       case 1:
/*  817 */         return getMinY();
/*      */       case 2:
/*  820 */         return getMinZ();
/*      */     } 
/*  823 */     throw new IndexOutOfBoundsException(String.valueOf(dimension));
/*      */   }
/*      */   
/*      */   public double getMaximum(int dimension) {
/*  831 */     switch (dimension) {
/*      */       case 0:
/*  833 */         return getMaxX();
/*      */       case 1:
/*  836 */         return getMaxY();
/*      */       case 2:
/*  840 */         return getMaxZ();
/*      */     } 
/*  843 */     throw new IndexOutOfBoundsException(String.valueOf(dimension));
/*      */   }
/*      */   
/*      */   public double getMedian(int dimension) {
/*  851 */     switch (dimension) {
/*      */       case 0:
/*  853 */         return 0.5D * (getMinX() + getMaxX());
/*      */       case 1:
/*  856 */         return 0.5D * (getMinY() + getMaxY());
/*      */       case 2:
/*  859 */         return 0.5D * (getMinZ() + getMaxZ());
/*      */     } 
/*  862 */     throw new IndexOutOfBoundsException(String.valueOf(dimension));
/*      */   }
/*      */   
/*      */   public double getSpan(int dimension) {
/*  872 */     switch (dimension) {
/*      */       case 0:
/*  874 */         return getWidth();
/*      */       case 1:
/*  877 */         return getHeight();
/*      */       case 2:
/*  880 */         return getDepth();
/*      */     } 
/*  883 */     throw new IndexOutOfBoundsException(String.valueOf(dimension));
/*      */   }
/*      */   
/*      */   public DirectPosition getLowerCorner() {
/*  892 */     return (DirectPosition)new DirectPosition3D(this.crs, getMinX(), getMinY(), getMinZ());
/*      */   }
/*      */   
/*      */   public DirectPosition getUpperCorner() {
/*  900 */     return (DirectPosition)new DirectPosition3D(this.crs, getMaxX(), getMaxY(), getMinZ());
/*      */   }
/*      */   
/*      */   public boolean isEmpty() {
/*  908 */     return isNull();
/*      */   }
/*      */   
/*      */   public boolean contains(DirectPosition pos) {
/*  916 */     ensureCompatibleReferenceSystem(pos);
/*  917 */     return contains(pos.getOrdinate(0), pos.getOrdinate(1), pos.getOrdinate(2));
/*      */   }
/*      */   
/*      */   public boolean contains(BoundingBox3D bbox) {
/*  925 */     ensureCompatibleReferenceSystem((BoundingBox)bbox);
/*  927 */     return covers(getJTSEnvelope(bbox));
/*      */   }
/*      */   
/*      */   public boolean intersects(BoundingBox3D bbox) {
/*  935 */     ensureCompatibleReferenceSystem((BoundingBox)bbox);
/*  937 */     return intersects(getJTSEnvelope(bbox));
/*      */   }
/*      */   
/*      */   public ReferencedEnvelope3D intersection(ReferencedEnvelope3D env) {
/*  950 */     ensureCompatibleReferenceSystem(env);
/*  952 */     if (isNull() || env.isNull() || !intersects(env))
/*  953 */       return new ReferencedEnvelope3D(); 
/*  955 */     double intMinX = (getMinX() > env.getMinX()) ? getMinX() : env.getMinX();
/*  956 */     double intMinY = (getMinY() > env.getMinY()) ? getMinY() : env.getMinY();
/*  957 */     double intMinZ = (this.minz > env.minz) ? this.minz : env.minz;
/*  958 */     double intMaxX = (getMaxX() < env.getMaxX()) ? getMaxX() : env.getMaxX();
/*  959 */     double intMaxY = (getMaxY() < env.getMaxY()) ? getMaxY() : env.getMaxY();
/*  960 */     double intMaxZ = (this.maxz < env.maxz) ? this.maxz : env.maxz;
/*  962 */     return new ReferencedEnvelope3D(intMinX, intMaxX, intMinY, intMaxY, intMinZ, intMaxZ, env.getCoordinateReferenceSystem());
/*      */   }
/*      */   
/*      */   public void include(BoundingBox3D bbox) {
/*  970 */     if (this.crs == null)
/*  971 */       this.crs = bbox.getCoordinateReferenceSystem(); 
/*  973 */     expandToInclude(getJTSEnvelope(bbox));
/*      */   }
/*      */   
/*      */   public void expandToInclude(ReferencedEnvelope3D other) {
/*  985 */     ensureCompatibleReferenceSystem(other);
/*  987 */     if (other.isNull())
/*      */       return; 
/*  990 */     if (isNull()) {
/*  991 */       expandToInclude(other);
/*  992 */       this.minz = other.getMinZ();
/*  993 */       this.maxz = other.getMaxZ();
/*      */     } else {
/*  995 */       expandToInclude(other);
/*  996 */       if (other.minz < this.minz)
/*  997 */         this.minz = other.minz; 
/*  999 */       if (other.maxz > this.maxz)
/* 1000 */         this.maxz = other.maxz; 
/*      */     } 
/*      */   }
/*      */   
/*      */   public void include(double x, double y, double z) {
/* 1010 */     expandToInclude(x, y, z);
/*      */   }
/*      */   
/*      */   public void setBounds(BoundingBox3D bbox) {
/* 1019 */     ensureCompatibleReferenceSystem((BoundingBox)bbox);
/* 1020 */     init(getJTSEnvelope(bbox));
/*      */   }
/*      */   
/*      */   public BoundingBox toBounds(CoordinateReferenceSystem targetCRS) throws TransformException {
/*      */     try {
/* 1032 */       return transform(targetCRS, true);
/* 1033 */     } catch (FactoryException e) {
/* 1034 */       throw new TransformException(e.getLocalizedMessage(), e);
/*      */     } 
/*      */   }
/*      */   
/*      */   public ReferencedEnvelope transform(CoordinateReferenceSystem targetCRS, boolean lenient) throws TransformException, FactoryException {
/* 1056 */     return transform(targetCRS, lenient, 5);
/*      */   }
/*      */   
/*      */   public ReferencedEnvelope transform(CoordinateReferenceSystem targetCRS, boolean lenient, int numPointsForTransformation) throws TransformException, FactoryException {
/* 1084 */     if (this.crs == null) {
/* 1085 */       if (isEmpty())
/* 1088 */         return new ReferencedEnvelope3D(targetCRS); 
/* 1092 */       throw new NullPointerException("Unable to transform referenced envelope, crs has not yet been provided.");
/*      */     } 
/* 1095 */     if (getDimension() != targetCRS.getCoordinateSystem().getDimension()) {
/* 1096 */       if (lenient)
/* 1097 */         return JTS.transformTo2D(this, targetCRS, lenient, numPointsForTransformation); 
/* 1100 */       throw new MismatchedDimensionException(Errors.format(94, this.crs.getName().getCode(), new Integer(getDimension()), new Integer(targetCRS.getCoordinateSystem().getDimension())));
/*      */     } 
/* 1108 */     CoordinateOperationFactory coordinateOperationFactory = CRS.getCoordinateOperationFactory(lenient);
/* 1110 */     CoordinateOperation operation = coordinateOperationFactory.createOperation(this.crs, targetCRS);
/* 1111 */     GeneralEnvelope transformed = CRS.transform(operation, this);
/* 1112 */     transformed.setCoordinateReferenceSystem(targetCRS);
/* 1116 */     ReferencedEnvelope3D target = new ReferencedEnvelope3D((Envelope)transformed);
/* 1117 */     MathTransform transform = operation.getMathTransform();
/* 1118 */     JTS.transform(this, target, transform, numPointsForTransformation);
/* 1120 */     target.expandToInclude(0.0D, 0.0D, this.minz);
/* 1121 */     target.expandToInclude(0.0D, 0.0D, this.maxz);
/* 1123 */     return target;
/*      */   }
/*      */   
/*      */   public int hashCode() {
/* 1133 */     int result = super.hashCode();
/* 1134 */     result = 37 * result + Coordinate.hashCode(this.minz);
/* 1135 */     result = 37 * result + Coordinate.hashCode(this.maxz);
/* 1137 */     int code = result ^ 0xAB296275;
/* 1138 */     if (this.crs != null)
/* 1139 */       code ^= this.crs.hashCode(); 
/* 1141 */     return code;
/*      */   }
/*      */   
/*      */   public boolean equals(Object other) {
/* 1150 */     if (!(other instanceof ReferencedEnvelope3D))
/* 1151 */       return false; 
/* 1153 */     ReferencedEnvelope3D otherEnvelope = (ReferencedEnvelope3D)other;
/* 1154 */     if (isNull())
/* 1155 */       return otherEnvelope.isNull(); 
/* 1157 */     if (super.equals(other) && this.minz == otherEnvelope.getMinZ() && this.minz == otherEnvelope.getMinZ()) {
/* 1160 */       CoordinateReferenceSystem otherCRS = (other instanceof ReferencedEnvelope3D) ? ((ReferencedEnvelope3D)other).crs : null;
/* 1163 */       return CRS.equalsIgnoreMetadata(this.crs, otherCRS);
/*      */     } 
/* 1165 */     return false;
/*      */   }
/*      */   
/*      */   public boolean boundsEquals3D(Envelope other, double eps) {
/* 1187 */     eps *= 0.5D * (getWidth() + getHeight());
/* 1189 */     double[] delta = new double[4];
/* 1190 */     delta[0] = getMinimum(0) - other.getMinimum(0);
/* 1191 */     delta[1] = getMaximum(0) - other.getMaximum(0);
/* 1192 */     delta[2] = getMinimum(1) - other.getMinimum(1);
/* 1193 */     delta[3] = getMaximum(1) - other.getMaximum(1);
/* 1194 */     delta[4] = getMinimum(2) - other.getMinimum(2);
/* 1195 */     delta[5] = getMaximum(2) - other.getMaximum(2);
/* 1197 */     for (int i = 0; i < delta.length; i++) {
/* 1202 */       if (Math.abs(delta[i]) > eps)
/* 1203 */         return false; 
/*      */     } 
/* 1206 */     return true;
/*      */   }
/*      */   
/*      */   public void include(double x, double y) {
/* 1211 */     expandToInclude(x, y);
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\geometry\jts\ReferencedEnvelope3D.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */