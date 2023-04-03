/*      */ package org.geotools.geometry;
/*      */ 
/*      */ import java.awt.geom.Rectangle2D;
/*      */ import java.io.Serializable;
/*      */ import java.util.Arrays;
/*      */ import javax.measure.converter.ConversionException;
/*      */ import javax.measure.unit.Unit;
/*      */ import org.geotools.metadata.iso.spatial.PixelTranslation;
/*      */ import org.geotools.referencing.CRS;
/*      */ import org.geotools.referencing.crs.DefaultGeographicCRS;
/*      */ import org.geotools.resources.Classes;
/*      */ import org.geotools.resources.geometry.XRectangle2D;
/*      */ import org.geotools.resources.i18n.Errors;
/*      */ import org.geotools.util.Utilities;
/*      */ import org.opengis.coverage.grid.GridEnvelope;
/*      */ import org.opengis.geometry.DirectPosition;
/*      */ import org.opengis.geometry.Envelope;
/*      */ import org.opengis.geometry.MismatchedDimensionException;
/*      */ import org.opengis.geometry.MismatchedReferenceSystemException;
/*      */ import org.opengis.metadata.extent.GeographicBoundingBox;
/*      */ import org.opengis.referencing.crs.CoordinateReferenceSystem;
/*      */ import org.opengis.referencing.cs.AxisDirection;
/*      */ import org.opengis.referencing.cs.CoordinateSystem;
/*      */ import org.opengis.referencing.cs.CoordinateSystemAxis;
/*      */ import org.opengis.referencing.cs.RangeMeaning;
/*      */ import org.opengis.referencing.datum.PixelInCell;
/*      */ import org.opengis.referencing.operation.MathTransform;
/*      */ import org.opengis.referencing.operation.TransformException;
/*      */ import org.opengis.util.Cloneable;
/*      */ 
/*      */ public class GeneralEnvelope extends AbstractEnvelope implements Cloneable, Serializable {
/*      */   private static final long serialVersionUID = 1752330560227688940L;
/*      */   
/*      */   private double[] ordinates;
/*      */   
/*      */   private CoordinateReferenceSystem crs;
/*      */   
/*      */   public GeneralEnvelope(int dimension) {
/*  105 */     this.ordinates = new double[dimension * 2];
/*  106 */     for (int i = 0; i < dimension * 2; i++)
/*  107 */       this.ordinates[i] = Double.NaN; 
/*      */   }
/*      */   
/*      */   public GeneralEnvelope(double min, double max) {
/*  120 */     this.ordinates = new double[] { min, max };
/*  121 */     checkCoordinates(this.ordinates);
/*      */   }
/*      */   
/*      */   public GeneralEnvelope(double[] minDP, double[] maxDP) throws IllegalArgumentException {
/*  139 */     ensureNonNull("minDP", minDP);
/*  140 */     ensureNonNull("maxDP", maxDP);
/*  141 */     ensureSameDimension(minDP.length, maxDP.length);
/*  142 */     this.ordinates = new double[minDP.length + maxDP.length];
/*  143 */     System.arraycopy(minDP, 0, this.ordinates, 0, minDP.length);
/*  144 */     System.arraycopy(maxDP, 0, this.ordinates, minDP.length, maxDP.length);
/*  145 */     checkCoordinates(this.ordinates);
/*      */   }
/*      */   
/*      */   public GeneralEnvelope(GeneralDirectPosition minDP, GeneralDirectPosition maxDP) throws MismatchedReferenceSystemException, IllegalArgumentException {
/*  169 */     this(minDP.ordinates, maxDP.ordinates);
/*  170 */     this.crs = getCoordinateReferenceSystem(minDP, maxDP);
/*  171 */     AbstractDirectPosition.checkCoordinateReferenceSystemDimension(this.crs, this.ordinates.length / 2);
/*      */   }
/*      */   
/*      */   public GeneralEnvelope(CoordinateReferenceSystem crs) {
/*  186 */     this(crs.getCoordinateSystem().getDimension());
/*  187 */     this.crs = crs;
/*      */   }
/*      */   
/*      */   public GeneralEnvelope(Envelope envelope) {
/*  197 */     ensureNonNull("envelope", envelope);
/*  198 */     if (envelope instanceof GeneralEnvelope) {
/*  199 */       GeneralEnvelope e = (GeneralEnvelope)envelope;
/*  200 */       this.ordinates = (double[])e.ordinates.clone();
/*  201 */       this.crs = e.crs;
/*      */     } else {
/*  203 */       this.crs = envelope.getCoordinateReferenceSystem();
/*  204 */       int dimension = envelope.getDimension();
/*  205 */       this.ordinates = new double[2 * dimension];
/*  206 */       for (int i = 0; i < dimension; i++) {
/*  207 */         this.ordinates[i] = envelope.getMinimum(i);
/*  208 */         this.ordinates[i + dimension] = envelope.getMaximum(i);
/*      */       } 
/*  210 */       checkCoordinates(this.ordinates);
/*      */     } 
/*      */   }
/*      */   
/*      */   public GeneralEnvelope(GeographicBoundingBox box) {
/*  224 */     ensureNonNull("box", box);
/*  225 */     this.ordinates = new double[] { box.getWestBoundLongitude(), box.getSouthBoundLatitude(), box.getEastBoundLongitude(), box.getNorthBoundLatitude() };
/*  227 */     this.crs = (CoordinateReferenceSystem)DefaultGeographicCRS.WGS84;
/*      */   }
/*      */   
/*      */   public GeneralEnvelope(Rectangle2D rect) {
/*  238 */     ensureNonNull("rect", rect);
/*  239 */     this.ordinates = new double[] { rect.getMinX(), rect.getMinY(), rect.getMaxX(), rect.getMaxY() };
/*  240 */     checkCoordinates(this.ordinates);
/*      */   }
/*      */   
/*      */   public GeneralEnvelope(GridEnvelope gridRange, PixelInCell anchor, MathTransform gridToCRS, CoordinateReferenceSystem crs) throws IllegalArgumentException {
/*      */     GeneralEnvelope transformed;
/*  279 */     ensureNonNull("gridRange", gridRange);
/*  280 */     ensureNonNull("gridToCRS", gridToCRS);
/*  281 */     int dimRange = gridRange.getDimension();
/*  282 */     int dimSource = gridToCRS.getSourceDimensions();
/*  283 */     int dimTarget = gridToCRS.getTargetDimensions();
/*  284 */     ensureSameDimension(dimRange, dimSource);
/*  285 */     ensureSameDimension(dimRange, dimTarget);
/*  286 */     this.ordinates = new double[dimSource * 2];
/*  287 */     double offset = PixelTranslation.getPixelTranslation(anchor) + 0.5D;
/*  288 */     for (int i = 0; i < dimSource; i++)
/*  297 */       setRange(i, gridRange.getLow(i) - offset, gridRange.getHigh(i) - offset - 1.0D); 
/*      */     try {
/*  301 */       transformed = CRS.transform(gridToCRS, this);
/*  302 */     } catch (TransformException exception) {
/*  303 */       throw new IllegalArgumentException(Errors.format(15, Classes.getClass(gridToCRS)), exception);
/*      */     } 
/*  306 */     assert transformed.ordinates.length == this.ordinates.length;
/*  307 */     System.arraycopy(transformed.ordinates, 0, this.ordinates, 0, this.ordinates.length);
/*  308 */     setCoordinateReferenceSystem(crs);
/*      */   }
/*      */   
/*      */   private static void ensureNonNull(String name, Object object) throws IllegalArgumentException {
/*  323 */     if (object == null)
/*  324 */       throw new IllegalArgumentException(Errors.format(143, name)); 
/*      */   }
/*      */   
/*      */   private static void ensureSameDimension(int dim1, int dim2) throws MismatchedDimensionException {
/*  333 */     if (dim1 != dim2)
/*  334 */       throw new MismatchedDimensionException(Errors.format(93, Integer.valueOf(dim1), Integer.valueOf(dim2))); 
/*      */   }
/*      */   
/*      */   private static void checkCoordinates(double[] ordinates) throws IllegalArgumentException {
/*  353 */     if (isNilCoordinates(ordinates))
/*      */       return; 
/*  356 */     if (isEmptyOrdinates(ordinates))
/*      */       return; 
/*  359 */     int dimension = ordinates.length / 2;
/*  360 */     for (int i = 0; i < dimension; i++) {
/*  361 */       if (ordinates[i] > ordinates[dimension + i])
/*  362 */         throw new IllegalArgumentException(Errors.format(66, Integer.valueOf(i))); 
/*      */     } 
/*      */   }
/*      */   
/*      */   public final CoordinateReferenceSystem getCoordinateReferenceSystem() {
/*  374 */     assert this.crs == null || this.crs.getCoordinateSystem().getDimension() == getDimension();
/*  375 */     return this.crs;
/*      */   }
/*      */   
/*      */   public void setCoordinateReferenceSystem(CoordinateReferenceSystem crs) throws MismatchedDimensionException {
/*  390 */     AbstractDirectPosition.checkCoordinateReferenceSystemDimension(crs, getDimension());
/*  391 */     this.crs = crs;
/*      */   }
/*      */   
/*      */   public boolean normalize(boolean crsDomain) {
/*  445 */     boolean changed = false;
/*  446 */     if (this.crs != null) {
/*  447 */       int dimension = this.ordinates.length / 2;
/*  448 */       CoordinateSystem cs = this.crs.getCoordinateSystem();
/*  449 */       for (int i = 0; i < dimension; i++) {
/*  450 */         int j = i + dimension;
/*  451 */         CoordinateSystemAxis axis = cs.getAxis(i);
/*  452 */         double minimum = axis.getMinimumValue();
/*  453 */         double maximum = axis.getMaximumValue();
/*  454 */         RangeMeaning rm = axis.getRangeMeaning();
/*  455 */         if (RangeMeaning.EXACT.equals(rm)) {
/*  456 */           if (this.ordinates[i] < minimum) {
/*  457 */             this.ordinates[i] = minimum;
/*  458 */             changed = true;
/*      */           } 
/*  460 */           if (this.ordinates[j] > maximum) {
/*  461 */             this.ordinates[j] = maximum;
/*  462 */             changed = true;
/*      */           } 
/*  464 */         } else if (RangeMeaning.WRAPAROUND.equals(rm)) {
/*  465 */           double length = maximum - minimum;
/*  466 */           if (length > 0.0D && length < Double.POSITIVE_INFINITY) {
/*  467 */             double offset = Math.floor((this.ordinates[i] - minimum) / length) * length;
/*  469 */             if (offset != 0.0D) {
/*  470 */               this.ordinates[i] = this.ordinates[i] - offset;
/*  471 */               this.ordinates[j] = this.ordinates[j] - offset;
/*  472 */               changed = true;
/*      */             } 
/*  474 */             if (this.ordinates[j] > maximum) {
/*  475 */               this.ordinates[i] = minimum;
/*  476 */               this.ordinates[j] = maximum;
/*  477 */               changed = true;
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*  482 */       if (crsDomain) {
/*  483 */         Envelope domain = CRS.getEnvelope(this.crs);
/*  484 */         if (domain != null) {
/*  485 */           CoordinateReferenceSystem domainCRS = domain.getCoordinateReferenceSystem();
/*  487 */           if (domainCRS == null) {
/*  488 */             intersect(domain);
/*      */           } else {
/*  496 */             CoordinateSystem domainCS = domainCRS.getCoordinateSystem();
/*  497 */             int domainDimension = domainCS.getDimension();
/*  498 */             for (int j = 0; j < domainDimension; j++) {
/*  499 */               double minimum = domain.getMinimum(j);
/*  500 */               double maximum = domain.getMaximum(j);
/*  501 */               AxisDirection direction = domainCS.getAxis(j).getDirection();
/*  502 */               for (int k = 0; k < dimension; k++) {
/*  503 */                 if (direction.equals(cs.getAxis(k).getDirection())) {
/*  504 */                   int m = k + dimension;
/*  505 */                   if (this.ordinates[k] < minimum)
/*  506 */                     this.ordinates[k] = minimum; 
/*  507 */                   if (this.ordinates[m] > maximum)
/*  508 */                     this.ordinates[m] = maximum; 
/*      */                 } 
/*      */               } 
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*  516 */     return changed;
/*      */   }
/*      */   
/*      */   public final int getDimension() {
/*  523 */     return this.ordinates.length / 2;
/*      */   }
/*      */   
/*      */   public DirectPosition getLowerCorner() {
/*  534 */     int dim = this.ordinates.length / 2;
/*  535 */     GeneralDirectPosition position = new GeneralDirectPosition(dim);
/*  536 */     System.arraycopy(this.ordinates, 0, position.ordinates, 0, dim);
/*  537 */     position.setCoordinateReferenceSystem(this.crs);
/*  538 */     return position;
/*      */   }
/*      */   
/*      */   public DirectPosition getUpperCorner() {
/*  549 */     int dim = this.ordinates.length / 2;
/*  550 */     GeneralDirectPosition position = new GeneralDirectPosition(dim);
/*  551 */     System.arraycopy(this.ordinates, dim, position.ordinates, 0, dim);
/*  552 */     position.setCoordinateReferenceSystem(this.crs);
/*  553 */     return position;
/*      */   }
/*      */   
/*      */   public DirectPosition getMedian() {
/*  565 */     GeneralDirectPosition position = new GeneralDirectPosition(this.ordinates.length / 2);
/*  566 */     for (int i = position.ordinates.length; --i >= 0;)
/*  567 */       position.ordinates[i] = getMedian(i); 
/*  569 */     position.setCoordinateReferenceSystem(this.crs);
/*  570 */     return position;
/*      */   }
/*      */   
/*      */   private static IndexOutOfBoundsException indexOutOfBounds(int dimension) {
/*  577 */     return new IndexOutOfBoundsException(Errors.format(79, Integer.valueOf(dimension)));
/*      */   }
/*      */   
/*      */   public final double getMinimum(int dimension) throws IndexOutOfBoundsException {
/*  591 */     if (dimension < this.ordinates.length / 2)
/*  592 */       return this.ordinates[dimension]; 
/*  594 */     throw indexOutOfBounds(dimension);
/*      */   }
/*      */   
/*      */   public final double getMaximum(int dimension) throws IndexOutOfBoundsException {
/*  608 */     if (dimension >= 0)
/*  609 */       return this.ordinates[dimension + this.ordinates.length / 2]; 
/*  611 */     throw indexOutOfBounds(dimension);
/*      */   }
/*      */   
/*      */   public final double getMedian(int dimension) throws IndexOutOfBoundsException {
/*  627 */     return 0.5D * (this.ordinates[dimension] + this.ordinates[dimension + this.ordinates.length / 2]);
/*      */   }
/*      */   
/*      */   public final double getSpan(int dimension) throws IndexOutOfBoundsException {
/*  642 */     return this.ordinates[dimension + this.ordinates.length / 2] - this.ordinates[dimension];
/*      */   }
/*      */   
/*      */   public double getSpan(int dimension, Unit<?> unit) throws IndexOutOfBoundsException, ConversionException {
/*  662 */     double value = getSpan(dimension);
/*  663 */     if (this.crs != null) {
/*  664 */       Unit<?> source = this.crs.getCoordinateSystem().getAxis(dimension).getUnit();
/*  665 */       if (source != null)
/*  666 */         value = source.getConverterTo(unit).convert(value); 
/*      */     } 
/*  669 */     return value;
/*      */   }
/*      */   
/*      */   public void setRange(int dimension, double minimum, double maximum) throws IndexOutOfBoundsException {
/*  686 */     if (minimum > maximum)
/*  689 */       minimum = maximum = 0.5D * (minimum + maximum); 
/*  691 */     if (dimension >= 0) {
/*  693 */       this.ordinates[dimension + this.ordinates.length / 2] = maximum;
/*  694 */       this.ordinates[dimension] = minimum;
/*      */     } else {
/*  696 */       throw indexOutOfBounds(dimension);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void setEnvelope(double... ordinates) {
/*  715 */     if ((ordinates.length & 0x1) != 0)
/*  716 */       throw new IllegalArgumentException(Errors.format(149, Integer.valueOf(ordinates.length))); 
/*  719 */     int dimension = ordinates.length >>> 1;
/*  720 */     int check = this.ordinates.length >>> 1;
/*  721 */     if (dimension != check)
/*  722 */       throw new MismatchedDimensionException(Errors.format(94, "ordinates", Integer.valueOf(dimension), Integer.valueOf(check))); 
/*  725 */     checkCoordinates(ordinates);
/*  726 */     System.arraycopy(ordinates, 0, this.ordinates, 0, ordinates.length);
/*      */   }
/*      */   
/*      */   public void setEnvelope(GeneralEnvelope envelope) throws MismatchedDimensionException {
/*  740 */     ensureNonNull("envelope", envelope);
/*  741 */     AbstractDirectPosition.ensureDimensionMatch("envelope", envelope.getDimension(), getDimension());
/*  743 */     System.arraycopy(envelope.ordinates, 0, this.ordinates, 0, this.ordinates.length);
/*  744 */     if (envelope.crs != null) {
/*  745 */       this.crs = envelope.crs;
/*  746 */       assert this.crs.getCoordinateSystem().getDimension() == getDimension() : this.crs;
/*  747 */       assert !envelope.getClass().equals(getClass()) || equals(envelope) : envelope;
/*      */     } 
/*      */   }
/*      */   
/*      */   public void setToInfinite() {
/*  760 */     int mid = this.ordinates.length / 2;
/*  761 */     Arrays.fill(this.ordinates, 0, mid, Double.NEGATIVE_INFINITY);
/*  762 */     Arrays.fill(this.ordinates, mid, this.ordinates.length, Double.POSITIVE_INFINITY);
/*  763 */     assert isInfinite() : this;
/*      */   }
/*      */   
/*      */   public boolean isInfinite() {
/*  775 */     for (int i = 0; i < this.ordinates.length; i++) {
/*  776 */       if (Double.isInfinite(this.ordinates[i]))
/*  777 */         return true; 
/*      */     } 
/*  780 */     return false;
/*      */   }
/*      */   
/*      */   public void setToNull() {
/*  791 */     Arrays.fill(this.ordinates, Double.NaN);
/*  792 */     assert isNull() : this;
/*      */   }
/*      */   
/*      */   public boolean isNull() {
/*  813 */     if (!isNilCoordinates(this.ordinates))
/*  814 */       return false; 
/*  816 */     assert isEmpty() : this;
/*  817 */     return true;
/*      */   }
/*      */   
/*      */   private static boolean isNilCoordinates(double[] ordinates) throws IllegalArgumentException {
/*  828 */     for (int i = 0; i < ordinates.length; i++) {
/*  829 */       if (!Double.isNaN(ordinates[i]))
/*  830 */         return false; 
/*      */     } 
/*  833 */     return true;
/*      */   }
/*      */   
/*      */   public boolean isEmpty() {
/*  845 */     if (isEmptyOrdinates(this.ordinates))
/*  846 */       return true; 
/*  848 */     assert !isNull() : this;
/*  849 */     return false;
/*      */   }
/*      */   
/*      */   private static boolean isEmptyOrdinates(double[] ordinates) {
/*  858 */     int dimension = ordinates.length / 2;
/*  859 */     if (dimension == 0)
/*  860 */       return true; 
/*  862 */     for (int i = 0; i < dimension; i++) {
/*  863 */       if (ordinates[i] >= ordinates[i + dimension])
/*  864 */         return true; 
/*      */     } 
/*  867 */     return false;
/*      */   }
/*      */   
/*      */   private static boolean equalsIgnoreMetadata(CoordinateReferenceSystem crs1, CoordinateReferenceSystem crs2) {
/*  876 */     return (crs1 == null || crs2 == null || CRS.equalsIgnoreMetadata(crs1, crs2));
/*      */   }
/*      */   
/*      */   public void add(DirectPosition position) throws MismatchedDimensionException {
/*  895 */     ensureNonNull("position", position);
/*  896 */     int dim = this.ordinates.length / 2;
/*  897 */     AbstractDirectPosition.ensureDimensionMatch("position", position.getDimension(), dim);
/*  898 */     assert equalsIgnoreMetadata(this.crs, position.getCoordinateReferenceSystem()) : position;
/*  899 */     for (int i = 0; i < dim; i++) {
/*  900 */       double value = position.getOrdinate(i);
/*  901 */       if (value < this.ordinates[i] || Double.isNaN(this.ordinates[i]))
/*  902 */         this.ordinates[i] = value; 
/*  903 */       if (value > this.ordinates[i + dim] || Double.isNaN(this.ordinates[i + dim]))
/*  904 */         this.ordinates[i + dim] = value; 
/*      */     } 
/*  906 */     assert isEmpty() || contains(position);
/*      */   }
/*      */   
/*      */   public void add(Envelope envelope) throws MismatchedDimensionException {
/*  922 */     ensureNonNull("envelope", envelope);
/*  923 */     int dim = this.ordinates.length / 2;
/*  924 */     AbstractDirectPosition.ensureDimensionMatch("envelope", envelope.getDimension(), dim);
/*  925 */     assert equalsIgnoreMetadata(this.crs, envelope.getCoordinateReferenceSystem()) : envelope;
/*  926 */     for (int i = 0; i < dim; i++) {
/*  927 */       double min = envelope.getMinimum(i);
/*  928 */       double max = envelope.getMaximum(i);
/*  929 */       if (min < this.ordinates[i])
/*  930 */         this.ordinates[i] = min; 
/*  931 */       if (max > this.ordinates[i + dim])
/*  932 */         this.ordinates[i + dim] = max; 
/*      */     } 
/*  934 */     assert isEmpty() || contains(envelope, true);
/*      */   }
/*      */   
/*      */   public boolean contains(DirectPosition position) throws MismatchedDimensionException {
/*  951 */     ensureNonNull("position", position);
/*  952 */     int dim = this.ordinates.length / 2;
/*  953 */     AbstractDirectPosition.ensureDimensionMatch("point", position.getDimension(), dim);
/*  954 */     assert equalsIgnoreMetadata(this.crs, position.getCoordinateReferenceSystem()) : position;
/*  955 */     for (int i = 0; i < dim; i++) {
/*  956 */       double value = position.getOrdinate(i);
/*  957 */       if (value < this.ordinates[i])
/*  958 */         return false; 
/*  959 */       if (value > this.ordinates[i + dim])
/*  960 */         return false; 
/*      */     } 
/*  963 */     return true;
/*      */   }
/*      */   
/*      */   public boolean contains(Envelope envelope, boolean edgesInclusive) throws MismatchedDimensionException {
/*  989 */     ensureNonNull("envelope", envelope);
/*  990 */     int dim = this.ordinates.length / 2;
/*  991 */     AbstractDirectPosition.ensureDimensionMatch("envelope", envelope.getDimension(), dim);
/*  992 */     assert equalsIgnoreMetadata(this.crs, envelope.getCoordinateReferenceSystem()) : envelope;
/*  993 */     for (int i = 0; i < dim; ) {
/*  994 */       double inner = envelope.getMinimum(i);
/*  995 */       double outer = this.ordinates[i];
/*  996 */       if (edgesInclusive ? (inner >= outer) : (inner > outer)) {
/*  999 */         inner = envelope.getMaximum(i);
/* 1000 */         outer = this.ordinates[i + dim];
/* 1001 */         if (edgesInclusive ? (inner <= outer) : (inner < outer)) {
/*      */           i++;
/*      */           continue;
/*      */         } 
/* 1002 */         return false;
/*      */       } 
/*      */       return false;
/*      */     } 
/* 1005 */     assert intersects(envelope, edgesInclusive);
/* 1006 */     return true;
/*      */   }
/*      */   
/*      */   public boolean intersects(Envelope envelope, boolean edgesInclusive) throws MismatchedDimensionException {
/* 1032 */     ensureNonNull("envelope", envelope);
/* 1033 */     int dim = this.ordinates.length / 2;
/* 1034 */     AbstractDirectPosition.ensureDimensionMatch("envelope", envelope.getDimension(), dim);
/* 1035 */     assert equalsIgnoreMetadata(this.crs, envelope.getCoordinateReferenceSystem()) : envelope;
/* 1036 */     for (int i = 0; i < dim; ) {
/* 1037 */       double inner = envelope.getMaximum(i);
/* 1038 */       double outer = this.ordinates[i];
/* 1039 */       if (edgesInclusive ? (inner >= outer) : (inner > outer)) {
/* 1042 */         inner = envelope.getMinimum(i);
/* 1043 */         outer = this.ordinates[i + dim];
/* 1044 */         if (edgesInclusive ? (inner <= outer) : (inner < outer)) {
/*      */           i++;
/*      */           continue;
/*      */         } 
/* 1045 */         return false;
/*      */       } 
/*      */       return false;
/*      */     } 
/* 1048 */     return true;
/*      */   }
/*      */   
/*      */   public void intersect(Envelope envelope) throws MismatchedDimensionException {
/* 1063 */     ensureNonNull("envelope", envelope);
/* 1064 */     int dim = this.ordinates.length / 2;
/* 1065 */     AbstractDirectPosition.ensureDimensionMatch("envelope", envelope.getDimension(), dim);
/* 1066 */     assert equalsIgnoreMetadata(this.crs, envelope.getCoordinateReferenceSystem()) : envelope;
/* 1067 */     for (int i = 0; i < dim; i++) {
/* 1068 */       double min = Math.max(this.ordinates[i], envelope.getMinimum(i));
/* 1069 */       double max = Math.min(this.ordinates[i + dim], envelope.getMaximum(i));
/* 1070 */       if (min > max)
/* 1073 */         min = max = 0.5D * (min + max); 
/* 1075 */       this.ordinates[i] = min;
/* 1076 */       this.ordinates[i + dim] = max;
/*      */     } 
/*      */   }
/*      */   
/*      */   public GeneralEnvelope getSubEnvelope(int lower, int upper) throws IndexOutOfBoundsException {
/* 1096 */     int curDim = this.ordinates.length / 2;
/* 1097 */     int newDim = upper - lower;
/* 1098 */     if (lower < 0 || lower > curDim)
/* 1099 */       throw new IndexOutOfBoundsException(Errors.format(58, "lower", Integer.valueOf(lower))); 
/* 1102 */     if (newDim < 0 || upper > curDim)
/* 1103 */       throw new IndexOutOfBoundsException(Errors.format(58, "upper", Integer.valueOf(upper))); 
/* 1106 */     GeneralEnvelope envelope = new GeneralEnvelope(newDim);
/* 1107 */     System.arraycopy(this.ordinates, lower, envelope.ordinates, 0, newDim);
/* 1108 */     System.arraycopy(this.ordinates, lower + curDim, envelope.ordinates, newDim, newDim);
/* 1109 */     return envelope;
/*      */   }
/*      */   
/*      */   public GeneralEnvelope getReducedEnvelope(int lower, int upper) throws IndexOutOfBoundsException {
/* 1126 */     int curDim = this.ordinates.length / 2;
/* 1127 */     int rmvDim = upper - lower;
/* 1128 */     if (lower < 0 || lower > curDim)
/* 1129 */       throw new IndexOutOfBoundsException(Errors.format(58, "lower", Integer.valueOf(lower))); 
/* 1132 */     if (rmvDim < 0 || upper > curDim)
/* 1133 */       throw new IndexOutOfBoundsException(Errors.format(58, "upper", Integer.valueOf(upper))); 
/* 1136 */     GeneralEnvelope envelope = new GeneralEnvelope(curDim - rmvDim);
/* 1137 */     System.arraycopy(this.ordinates, 0, envelope.ordinates, 0, lower);
/* 1138 */     System.arraycopy(this.ordinates, lower, envelope.ordinates, upper, curDim - upper);
/* 1139 */     return envelope;
/*      */   }
/*      */   
/*      */   public Rectangle2D toRectangle2D() throws IllegalStateException {
/* 1151 */     if (this.ordinates.length == 4)
/* 1152 */       return (Rectangle2D)XRectangle2D.createFromExtremums(this.ordinates[0], this.ordinates[1], this.ordinates[2], this.ordinates[3]); 
/* 1155 */     throw new IllegalStateException(Errors.format(127, Integer.valueOf(getDimension())));
/*      */   }
/*      */   
/*      */   public int hashCode() {
/* 1165 */     int code = Arrays.hashCode(this.ordinates);
/* 1166 */     if (this.crs != null)
/* 1167 */       code += this.crs.hashCode(); 
/* 1169 */     assert code == super.hashCode();
/* 1170 */     return code;
/*      */   }
/*      */   
/*      */   public boolean equals(Object object) {
/* 1178 */     if (object != null && object.getClass().equals(getClass())) {
/* 1179 */       GeneralEnvelope that = (GeneralEnvelope)object;
/* 1180 */       return (Arrays.equals(this.ordinates, that.ordinates) && Utilities.equals(this.crs, that.crs));
/*      */     } 
/* 1183 */     return false;
/*      */   }
/*      */   
/*      */   public boolean equals(Envelope envelope, double eps, boolean epsIsRelative) {
/* 1224 */     ensureNonNull("envelope", envelope);
/* 1225 */     int dimension = getDimension();
/* 1226 */     if (envelope.getDimension() != dimension)
/* 1227 */       return false; 
/* 1229 */     assert equalsIgnoreMetadata(this.crs, envelope.getCoordinateReferenceSystem()) : envelope;
/* 1230 */     for (int i = 0; i < dimension; i++) {
/*      */       double epsilon;
/* 1232 */       if (epsIsRelative) {
/* 1233 */         epsilon = Math.max(getSpan(i), envelope.getSpan(i));
/* 1234 */         epsilon = (epsilon > 0.0D && epsilon < Double.POSITIVE_INFINITY) ? (epsilon * eps) : eps;
/*      */       } else {
/* 1236 */         epsilon = eps;
/*      */       } 
/* 1239 */       if (Math.abs(getMinimum(i) - envelope.getMinimum(i)) > epsilon || Math.abs(getMaximum(i) - envelope.getMaximum(i)) > epsilon)
/* 1241 */         return false; 
/*      */     } 
/* 1244 */     return true;
/*      */   }
/*      */   
/*      */   public GeneralEnvelope clone() {
/*      */     try {
/* 1255 */       GeneralEnvelope e = (GeneralEnvelope)super.clone();
/* 1256 */       e.ordinates = (double[])e.ordinates.clone();
/* 1257 */       return e;
/* 1258 */     } catch (CloneNotSupportedException exception) {
/* 1260 */       throw new AssertionError(exception);
/*      */     } 
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\geometry\GeneralEnvelope.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */