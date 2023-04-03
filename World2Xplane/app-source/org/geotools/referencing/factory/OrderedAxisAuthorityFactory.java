/*     */ package org.geotools.referencing.factory;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ import java.util.Comparator;
/*     */ import javax.measure.unit.NonSI;
/*     */ import javax.measure.unit.SI;
/*     */ import javax.measure.unit.Unit;
/*     */ import org.geotools.factory.FactoryRegistryException;
/*     */ import org.geotools.factory.Hints;
/*     */ import org.geotools.referencing.cs.DefaultCoordinateSystemAxis;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ import org.opengis.referencing.crs.CRSAuthorityFactory;
/*     */ import org.opengis.referencing.cs.AxisDirection;
/*     */ import org.opengis.referencing.cs.CSAuthorityFactory;
/*     */ import org.opengis.referencing.cs.CoordinateSystemAxis;
/*     */ 
/*     */ public class OrderedAxisAuthorityFactory extends TransformedAuthorityFactory implements CSAuthorityFactory, CRSAuthorityFactory, Comparator {
/* 114 */   private static final AxisDirection[] DEFAULT_ORDER = new AxisDirection[] { 
/* 114 */       AxisDirection.EAST, AxisDirection.EAST_NORTH_EAST, AxisDirection.NORTH_EAST, AxisDirection.NORTH_NORTH_EAST, AxisDirection.NORTH, AxisDirection.UP, AxisDirection.GEOCENTRIC_X, AxisDirection.GEOCENTRIC_Y, AxisDirection.GEOCENTRIC_Z, AxisDirection.COLUMN_POSITIVE, 
/* 114 */       AxisDirection.ROW_POSITIVE, AxisDirection.DISPLAY_RIGHT, AxisDirection.DISPLAY_UP, AxisDirection.FUTURE };
/*     */   
/*     */   private final int[] directionRanks;
/*     */   
/*     */   protected final boolean forceStandardDirections;
/*     */   
/*     */   protected final boolean forceStandardUnits;
/*     */   
/*     */   public OrderedAxisAuthorityFactory(String authority, Hints userHints, AxisDirection[] axisOrder) throws FactoryRegistryException, IllegalArgumentException {
/* 183 */     super(authority, userHints);
/* 184 */     this.forceStandardUnits = booleanValue(userHints, Hints.FORCE_STANDARD_AXIS_UNITS);
/* 185 */     this.forceStandardDirections = booleanValue(userHints, Hints.FORCE_STANDARD_AXIS_DIRECTIONS);
/* 186 */     this.directionRanks = computeDirectionRanks(axisOrder);
/* 187 */     completeHints();
/*     */   }
/*     */   
/*     */   public OrderedAxisAuthorityFactory(AbstractAuthorityFactory factory, Hints userHints, AxisDirection[] axisOrder) throws IllegalArgumentException {
/* 212 */     super(factory);
/* 213 */     this.forceStandardUnits = booleanValue(userHints, Hints.FORCE_STANDARD_AXIS_UNITS);
/* 214 */     this.forceStandardDirections = booleanValue(userHints, Hints.FORCE_STANDARD_AXIS_DIRECTIONS);
/* 215 */     this.directionRanks = computeDirectionRanks(axisOrder);
/* 216 */     completeHints();
/*     */   }
/*     */   
/*     */   private static boolean booleanValue(Hints userHints, Hints.Key key) {
/* 223 */     if (userHints != null) {
/* 224 */       Boolean value = (Boolean)userHints.get(key);
/* 225 */       if (value != null)
/* 226 */         return value.booleanValue(); 
/*     */     } 
/* 229 */     return false;
/*     */   }
/*     */   
/*     */   private void completeHints() {
/* 237 */     this.hints.put(Hints.FORCE_STANDARD_AXIS_UNITS, Boolean.valueOf(this.forceStandardUnits));
/* 238 */     this.hints.put(Hints.FORCE_STANDARD_AXIS_DIRECTIONS, Boolean.valueOf(this.forceStandardDirections));
/* 241 */     if (compare(DefaultCoordinateSystemAxis.EASTING, DefaultCoordinateSystemAxis.NORTHING) < 0)
/* 242 */       this.hints.put(Hints.FORCE_LONGITUDE_FIRST_AXIS_ORDER, Boolean.TRUE); 
/*     */   }
/*     */   
/*     */   private static int[] computeDirectionRanks(AxisDirection[] axisOrder) throws IllegalArgumentException {
/* 256 */     if (axisOrder == null)
/* 257 */       axisOrder = DEFAULT_ORDER; 
/* 259 */     int length = 0;
/* 260 */     for (int i = 0; i < axisOrder.length; i++) {
/* 261 */       int ordinal = axisOrder[i].absolute().ordinal() + 1;
/* 262 */       if (ordinal > length)
/* 263 */         length = ordinal; 
/*     */     } 
/* 266 */     int[] directionRanks = new int[length];
/* 267 */     Arrays.fill(directionRanks, length);
/* 268 */     for (int j = 0; j < axisOrder.length; j++) {
/* 269 */       int ordinal = axisOrder[j].absolute().ordinal();
/* 270 */       int previous = directionRanks[ordinal];
/* 271 */       if (previous != length)
/* 273 */         throw new IllegalArgumentException(Errors.format(36, axisOrder[previous].name(), axisOrder[j].name())); 
/* 276 */       directionRanks[ordinal] = j;
/*     */     } 
/* 278 */     return directionRanks;
/*     */   }
/*     */   
/*     */   private final int rank(CoordinateSystemAxis axis) {
/* 286 */     int c = axis.getDirection().absolute().ordinal();
/* 287 */     c = (c >= 0 && c < this.directionRanks.length) ? this.directionRanks[c] : this.directionRanks.length;
/* 288 */     return c;
/*     */   }
/*     */   
/*     */   public int compare(Object axis1, Object axis2) {
/* 313 */     return rank((CoordinateSystemAxis)axis1) - rank((CoordinateSystemAxis)axis2);
/*     */   }
/*     */   
/*     */   protected Unit<?> replace(Unit<?> units) {
/* 335 */     if (this.forceStandardUnits) {
/* 336 */       if (units.isCompatible(SI.METER))
/* 337 */         return SI.METER; 
/* 339 */       if (units.equals(SI.RADIAN) || units.equals(NonSI.GRADE))
/* 340 */         return NonSI.DEGREE_ANGLE; 
/*     */     } 
/* 343 */     return units;
/*     */   }
/*     */   
/*     */   protected AxisDirection replace(AxisDirection direction) {
/* 358 */     return this.forceStandardDirections ? direction.absolute() : direction;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\factory\OrderedAxisAuthorityFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */