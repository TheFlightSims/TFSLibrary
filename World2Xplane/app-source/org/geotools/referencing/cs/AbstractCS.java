/*     */ package org.geotools.referencing.cs;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import javax.measure.converter.ConversionException;
/*     */ import javax.measure.converter.UnitConverter;
/*     */ import javax.measure.unit.SI;
/*     */ import javax.measure.unit.Unit;
/*     */ import org.geotools.measure.Measure;
/*     */ import org.geotools.referencing.AbstractIdentifiedObject;
/*     */ import org.geotools.referencing.operation.matrix.GeneralMatrix;
/*     */ import org.geotools.referencing.wkt.Formatter;
/*     */ import org.geotools.resources.Classes;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ import org.geotools.resources.i18n.Vocabulary;
/*     */ import org.geotools.util.Utilities;
/*     */ import org.opengis.geometry.MismatchedDimensionException;
/*     */ import org.opengis.referencing.IdentifiedObject;
/*     */ import org.opengis.referencing.cs.AxisDirection;
/*     */ import org.opengis.referencing.cs.CoordinateSystem;
/*     */ import org.opengis.referencing.cs.CoordinateSystemAxis;
/*     */ import org.opengis.referencing.operation.Matrix;
/*     */ import org.opengis.util.InternationalString;
/*     */ 
/*     */ public class AbstractCS extends AbstractIdentifiedObject implements CoordinateSystem {
/*     */   private static final long serialVersionUID = 6757665252533744744L;
/*     */   
/*  86 */   private static final DefaultCoordinateSystemAxis[] DIRECTION_CHECKS = new DefaultCoordinateSystemAxis[] { DefaultCoordinateSystemAxis.NORTHING, DefaultCoordinateSystemAxis.EASTING, DefaultCoordinateSystemAxis.SOUTHING, DefaultCoordinateSystemAxis.WESTING };
/*     */   
/*     */   private final CoordinateSystemAxis[] axis;
/*     */   
/*     */   private transient Unit<?> distanceUnit;
/*     */   
/*     */   public AbstractCS(CoordinateSystem cs) {
/* 116 */     super((IdentifiedObject)cs);
/* 117 */     if (cs instanceof AbstractCS) {
/* 118 */       this.axis = ((AbstractCS)cs).axis;
/*     */     } else {
/* 120 */       this.axis = new CoordinateSystemAxis[cs.getDimension()];
/* 121 */       for (int i = 0; i < this.axis.length; i++)
/* 122 */         this.axis[i] = cs.getAxis(i); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public AbstractCS(String name, CoordinateSystemAxis[] axis) {
/* 134 */     this(Collections.singletonMap("name", name), axis);
/*     */   }
/*     */   
/*     */   public AbstractCS(Map<String, ?> properties, CoordinateSystemAxis[] axis) {
/* 146 */     super(properties);
/* 147 */     ensureNonNull("axis", axis);
/* 148 */     this.axis = (CoordinateSystemAxis[])axis.clone();
/* 149 */     for (int i = 0; i < axis.length; i++) {
/* 150 */       ensureNonNull("axis", (Object[])axis, i);
/* 151 */       AxisDirection direction = axis[i].getDirection();
/* 152 */       ensureNonNull("direction", direction);
/* 158 */       if (!isCompatibleDirection(direction))
/* 160 */         throw new IllegalArgumentException(Errors.format(60, direction.name(), getClass())); 
/* 163 */       Unit<?> unit = axis[i].getUnit();
/* 164 */       ensureNonNull("unit", unit);
/* 165 */       if (!isCompatibleUnit(direction, unit))
/* 166 */         throw new IllegalArgumentException(Errors.format(76, unit)); 
/* 173 */       AxisDirection check = direction.absolute();
/* 174 */       if (!check.equals(AxisDirection.OTHER))
/* 175 */         for (int k = i; --k >= 0;) {
/* 176 */           if (check.equals(axis[k].getDirection().absolute())) {
/* 178 */             String nameI = axis[i].getDirection().name();
/* 179 */             String nameJ = axis[k].getDirection().name();
/* 180 */             throw new IllegalArgumentException(Errors.format(36, nameI, nameJ));
/*     */           } 
/*     */         }  
/* 191 */       String name = axis[i].getName().getCode();
/* 192 */       for (int j = 0; j < DIRECTION_CHECKS.length; j++) {
/* 193 */         DefaultCoordinateSystemAxis candidate = DIRECTION_CHECKS[j];
/* 194 */         if (candidate.nameMatches(name)) {
/* 195 */           AxisDirection expected = candidate.getDirection();
/* 196 */           if (!direction.equals(expected)) {
/* 197 */             DirectionAlongMeridian m = DirectionAlongMeridian.parse(direction);
/* 207 */             if (m == null)
/* 208 */               throw new IllegalArgumentException(Errors.format(77, name, direction.name())); 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   static Map<String, Object> name(int key) {
/* 228 */     Map<String, Object> properties = new HashMap<String, Object>(4);
/* 229 */     InternationalString name = Vocabulary.formatInternational(key);
/* 230 */     properties.put("name", name.toString());
/* 231 */     properties.put("alias", name);
/* 232 */     return properties;
/*     */   }
/*     */   
/*     */   protected boolean isCompatibleDirection(AxisDirection direction) {
/* 245 */     return true;
/*     */   }
/*     */   
/*     */   protected boolean isCompatibleUnit(AxisDirection direction, Unit<?> unit) {
/* 262 */     return true;
/*     */   }
/*     */   
/*     */   public int getDimension() {
/* 270 */     return this.axis.length;
/*     */   }
/*     */   
/*     */   public CoordinateSystemAxis getAxis(int dimension) throws IndexOutOfBoundsException {
/* 281 */     return this.axis[dimension];
/*     */   }
/*     */   
/*     */   private static AxisDirection[] getAxisDirections(CoordinateSystem cs) {
/* 291 */     AxisDirection[] axis = new AxisDirection[cs.getDimension()];
/* 292 */     for (int i = 0; i < axis.length; i++)
/* 293 */       axis[i] = cs.getAxis(i).getDirection(); 
/* 295 */     return axis;
/*     */   }
/*     */   
/*     */   public static Matrix swapAndScaleAxis(CoordinateSystem sourceCS, CoordinateSystem targetCS) throws IllegalArgumentException, ConversionException {
/* 327 */     if (!Classes.sameInterfaces(sourceCS.getClass(), targetCS.getClass(), CoordinateSystem.class))
/* 328 */       throw new IllegalArgumentException(Errors.format(73)); 
/* 331 */     AxisDirection[] sourceAxis = getAxisDirections(sourceCS);
/* 332 */     AxisDirection[] targetAxis = getAxisDirections(targetCS);
/* 333 */     GeneralMatrix matrix = new GeneralMatrix(sourceAxis, targetAxis);
/* 334 */     assert Arrays.equals((Object[])sourceAxis, (Object[])targetAxis) == matrix.isIdentity() : matrix;
/* 350 */     int sourceDim = matrix.getNumCol() - 1;
/* 351 */     int targetDim = matrix.getNumRow() - 1;
/* 352 */     assert sourceDim == sourceCS.getDimension() : sourceCS;
/* 353 */     assert targetDim == targetCS.getDimension() : targetCS;
/* 354 */     for (int j = 0; j < targetDim; j++) {
/* 355 */       Unit<?> targetUnit = targetCS.getAxis(j).getUnit();
/* 356 */       for (int i = 0; i < sourceDim; i++) {
/* 357 */         double element = matrix.getElement(j, i);
/* 358 */         if (element != 0.0D) {
/* 363 */           Unit<?> sourceUnit = sourceCS.getAxis(i).getUnit();
/* 364 */           if (!Utilities.equals(sourceUnit, targetUnit)) {
/* 369 */             UnitConverter converter = sourceUnit.getConverterTo(targetUnit);
/* 370 */             if (!converter.isLinear())
/* 371 */               throw new ConversionException(Errors.format(114, sourceUnit, targetUnit)); 
/* 374 */             double offset = converter.convert(0.0D);
/* 376 */             double scale = converter.convert(1.0D) - offset;
/* 377 */             matrix.setElement(j, i, element * scale);
/* 378 */             matrix.setElement(j, sourceDim, matrix.getElement(j, sourceDim) + element * offset);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 381 */     return (Matrix)matrix;
/*     */   }
/*     */   
/*     */   public static CoordinateSystem standard(CoordinateSystem cs) throws IllegalArgumentException {
/* 418 */     return PredefinedCS.standard(cs);
/*     */   }
/*     */   
/*     */   final Unit<?> getDistanceUnit() throws ConversionException {
/* 432 */     Unit<?> unit = this.distanceUnit;
/* 433 */     if (unit == null) {
/* 434 */       for (int i = 0; i < this.axis.length; i++) {
/* 435 */         Unit<?> candidate = this.axis[i].getUnit();
/* 436 */         if (candidate != null && !candidate.isCompatible((Unit)SI.RADIAN)) {
/* 438 */           if (unit != null) {
/* 439 */             UnitConverter converter = candidate.getConverterTo(unit);
/* 440 */             if (!converter.isLinear())
/* 443 */               throw new ConversionException("Unit conversion is non-linear"); 
/* 446 */             double scale = converter.convert(1.0D) - converter.convert(0.0D);
/* 447 */             if (Math.abs(scale) <= 1.0D)
/*     */               continue; 
/*     */           } 
/* 453 */           unit = candidate;
/*     */         } 
/*     */         continue;
/*     */       } 
/* 456 */       this.distanceUnit = unit;
/*     */     } 
/* 458 */     return unit;
/*     */   }
/*     */   
/*     */   final void ensureDimensionMatch(String name, double[] coordinates) throws MismatchedDimensionException {
/* 471 */     if (coordinates.length != this.axis.length)
/* 472 */       throw new MismatchedDimensionException(Errors.format(94, name, Integer.valueOf(coordinates.length), Integer.valueOf(this.axis.length))); 
/*     */   }
/*     */   
/*     */   public Measure distance(double[] coord1, double[] coord2) throws UnsupportedOperationException, MismatchedDimensionException {
/* 493 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   final CoordinateSystemAxis[] axisUsingUnit(Unit<?> unit) throws IllegalArgumentException {
/* 508 */     CoordinateSystemAxis[] newAxis = null;
/* 509 */     for (int i = 0; i < this.axis.length; i++) {
/* 510 */       CoordinateSystemAxis a = this.axis[i];
/* 511 */       if (!unit.equals(a.getUnit())) {
/* 513 */         if (a instanceof DefaultCoordinateSystemAxis) {
/* 514 */           converted = (DefaultCoordinateSystemAxis)a;
/*     */         } else {
/* 516 */           converted = new DefaultCoordinateSystemAxis(a);
/* 517 */           a = converted;
/*     */         } 
/* 519 */         DefaultCoordinateSystemAxis converted = converted.usingUnit(unit);
/* 520 */         if (converted != a) {
/* 521 */           if (newAxis == null) {
/* 522 */             newAxis = new CoordinateSystemAxis[this.axis.length];
/* 523 */             System.arraycopy(this.axis, 0, newAxis, 0, i);
/*     */           } 
/* 525 */           newAxis[i] = converted;
/*     */         } 
/*     */       } 
/*     */     } 
/* 529 */     return newAxis;
/*     */   }
/*     */   
/*     */   private static DefaultCoordinateSystemAxis[] getDefaultAxis(CoordinateSystem cs) {
/* 538 */     DefaultCoordinateSystemAxis[] axis = new DefaultCoordinateSystemAxis[cs.getDimension()];
/* 539 */     for (int i = 0; i < axis.length; i++) {
/* 540 */       CoordinateSystemAxis a = cs.getAxis(i);
/* 541 */       DefaultCoordinateSystemAxis c = DefaultCoordinateSystemAxis.getPredefined(a);
/* 542 */       if (c == null)
/* 543 */         if (a instanceof DefaultCoordinateSystemAxis) {
/* 544 */           c = (DefaultCoordinateSystemAxis)a;
/*     */         } else {
/* 546 */           c = new DefaultCoordinateSystemAxis(a);
/*     */         }  
/* 549 */       axis[i] = c;
/*     */     } 
/* 551 */     return axis;
/*     */   }
/*     */   
/*     */   final boolean axisColinearWith(CoordinateSystem userCS) {
/* 569 */     if (userCS.getDimension() != getDimension())
/* 570 */       return false; 
/* 572 */     DefaultCoordinateSystemAxis[] axis0 = getDefaultAxis(this);
/* 573 */     DefaultCoordinateSystemAxis[] axis1 = getDefaultAxis(userCS);
/* 574 */     for (int i = 0; i < axis0.length; i++) {
/* 575 */       DefaultCoordinateSystemAxis direct = axis0[i];
/* 576 */       DefaultCoordinateSystemAxis opposite = direct.getOpposite();
/* 577 */       int j = 0;
/*     */       while (true) {
/* 577 */         if (j < axis1.length) {
/* 578 */           DefaultCoordinateSystemAxis candidate = axis1[j];
/* 579 */           if (candidate != null && (
/* 580 */             candidate.equals(direct, false, false) || (opposite != null && candidate.equals(opposite, false, false)))) {
/* 583 */             axis1[j] = null;
/*     */             break;
/*     */           } 
/*     */           j++;
/*     */           continue;
/*     */         } 
/* 588 */         return false;
/*     */       } 
/*     */     } 
/* 590 */     assert directionColinearWith(userCS);
/* 591 */     return true;
/*     */   }
/*     */   
/*     */   final boolean directionColinearWith(CoordinateSystem userCS) {
/* 598 */     if (userCS.getDimension() != this.axis.length)
/* 599 */       return false; 
/* 601 */     AxisDirection[] checks = new AxisDirection[this.axis.length];
/*     */     int i;
/* 602 */     for (i = 0; i < checks.length; i++)
/* 603 */       checks[i] = userCS.getAxis(i).getDirection().absolute(); 
/* 605 */     for (i = 0; i < this.axis.length; i++) {
/* 606 */       AxisDirection direction = this.axis[i].getDirection().absolute();
/* 607 */       int j = 0;
/*     */       while (true) {
/* 607 */         if (j < checks.length) {
/* 608 */           AxisDirection candidate = checks[j];
/* 609 */           if (candidate != null && candidate.equals(direction)) {
/* 610 */             checks[j] = null;
/*     */             break;
/*     */           } 
/*     */           j++;
/*     */           continue;
/*     */         } 
/* 614 */         return false;
/*     */       } 
/*     */     } 
/* 616 */     return true;
/*     */   }
/*     */   
/*     */   public boolean equals(AbstractIdentifiedObject object, boolean compareMetadata) {
/* 629 */     if (object == this)
/* 630 */       return true; 
/* 632 */     if (super.equals(object, compareMetadata)) {
/* 633 */       AbstractCS that = (AbstractCS)object;
/* 634 */       return equals((IdentifiedObject[])this.axis, (IdentifiedObject[])that.axis, compareMetadata);
/*     */     } 
/* 636 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 647 */     int code = 1480995944;
/* 648 */     for (int i = 0; i < this.axis.length; i++)
/* 649 */       code = code * 37 + this.axis[i].hashCode(); 
/* 651 */     return code;
/*     */   }
/*     */   
/*     */   protected String formatWKT(Formatter formatter) {
/* 665 */     for (int i = 0; i < this.axis.length; i++)
/* 666 */       formatter.append((IdentifiedObject)this.axis[i]); 
/* 668 */     formatter.setInvalidWKT(CoordinateSystem.class);
/* 669 */     return super.formatWKT(formatter);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\cs\AbstractCS.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */