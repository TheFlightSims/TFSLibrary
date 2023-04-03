/*      */ package org.geotools.referencing.cs;
/*      */ 
/*      */ import java.util.Collections;
/*      */ import java.util.HashMap;
/*      */ import java.util.Map;
/*      */ import java.util.NoSuchElementException;
/*      */ import javax.measure.converter.UnitConverter;
/*      */ import javax.measure.unit.NonSI;
/*      */ import javax.measure.unit.SI;
/*      */ import javax.measure.unit.Unit;
/*      */ import org.geotools.referencing.AbstractIdentifiedObject;
/*      */ import org.geotools.referencing.wkt.Formatter;
/*      */ import org.geotools.resources.i18n.Errors;
/*      */ import org.geotools.resources.i18n.Vocabulary;
/*      */ import org.geotools.util.NameFactory;
/*      */ import org.geotools.util.SimpleInternationalString;
/*      */ import org.geotools.util.Utilities;
/*      */ import org.opengis.referencing.IdentifiedObject;
/*      */ import org.opengis.referencing.cs.AxisDirection;
/*      */ import org.opengis.referencing.cs.CoordinateSystemAxis;
/*      */ import org.opengis.referencing.cs.RangeMeaning;
/*      */ import org.opengis.util.CodeList;
/*      */ import org.opengis.util.InternationalString;
/*      */ 
/*      */ public class DefaultCoordinateSystemAxis extends AbstractIdentifiedObject implements CoordinateSystemAxis {
/*      */   private static final long serialVersionUID = -7883614853277827689L;
/*      */   
/*      */   static final int COMPASS_DIRECTION_COUNT = 16;
/*      */   
/*   89 */   private static int PREDEFINED_COUNT = 0;
/*      */   
/*   95 */   private static final DefaultCoordinateSystemAxis[] PREDEFINED = new DefaultCoordinateSystemAxis[26];
/*      */   
/*  114 */   public static final DefaultCoordinateSystemAxis GEODETIC_LONGITUDE = new DefaultCoordinateSystemAxis(86, "λ", AxisDirection.EAST, NonSI.DEGREE_ANGLE);
/*      */   
/*  133 */   public static final DefaultCoordinateSystemAxis GEODETIC_LATITUDE = new DefaultCoordinateSystemAxis(85, "φ", AxisDirection.NORTH, NonSI.DEGREE_ANGLE);
/*      */   
/*  150 */   public static final DefaultCoordinateSystemAxis LONGITUDE = new DefaultCoordinateSystemAxis(132, "λ", AxisDirection.EAST, NonSI.DEGREE_ANGLE);
/*      */   
/*  167 */   public static final DefaultCoordinateSystemAxis LATITUDE = new DefaultCoordinateSystemAxis(120, "φ", AxisDirection.NORTH, NonSI.DEGREE_ANGLE);
/*      */   
/*  188 */   public static final DefaultCoordinateSystemAxis ELLIPSOIDAL_HEIGHT = new DefaultCoordinateSystemAxis(58, "h", AxisDirection.UP, SI.METER);
/*      */   
/*  205 */   public static final DefaultCoordinateSystemAxis GRAVITY_RELATED_HEIGHT = new DefaultCoordinateSystemAxis(93, "H", AxisDirection.UP, SI.METER);
/*      */   
/*  223 */   public static final DefaultCoordinateSystemAxis ALTITUDE = new DefaultCoordinateSystemAxis(5, "h", AxisDirection.UP, SI.METER);
/*      */   
/*  239 */   public static final DefaultCoordinateSystemAxis DEPTH = new DefaultCoordinateSystemAxis(44, "d", AxisDirection.DOWN, SI.METER);
/*      */   
/*      */   public static final DefaultCoordinateSystemAxis GEOCENTRIC_RADIUS;
/*      */   
/*      */   public static final DefaultCoordinateSystemAxis SPHERICAL_LONGITUDE;
/*      */   
/*      */   public static final DefaultCoordinateSystemAxis SPHERICAL_LATITUDE;
/*      */   
/*      */   public static final DefaultCoordinateSystemAxis X;
/*      */   
/*      */   public static final DefaultCoordinateSystemAxis Y;
/*      */   
/*      */   public static final DefaultCoordinateSystemAxis Z;
/*      */   
/*      */   public static final DefaultCoordinateSystemAxis GEOCENTRIC_X;
/*      */   
/*      */   public static final DefaultCoordinateSystemAxis GEOCENTRIC_Y;
/*      */   
/*      */   public static final DefaultCoordinateSystemAxis GEOCENTRIC_Z;
/*      */   
/*      */   public static final DefaultCoordinateSystemAxis EASTING;
/*      */   
/*      */   public static final DefaultCoordinateSystemAxis WESTING;
/*      */   
/*      */   public static final DefaultCoordinateSystemAxis NORTHING;
/*      */   
/*      */   public static final DefaultCoordinateSystemAxis SOUTHING;
/*      */   
/*      */   public static final DefaultCoordinateSystemAxis TIME;
/*      */   
/*      */   public static final DefaultCoordinateSystemAxis COLUMN;
/*      */   
/*      */   public static final DefaultCoordinateSystemAxis ROW;
/*      */   
/*      */   public static final DefaultCoordinateSystemAxis DISPLAY_X;
/*      */   
/*      */   public static final DefaultCoordinateSystemAxis DISPLAY_Y;
/*      */   
/*      */   private static final Map<String, CoordinateSystemAxis> ALIASES;
/*      */   
/*      */   private final String abbreviation;
/*      */   
/*      */   private final AxisDirection direction;
/*      */   
/*      */   private final Unit<?> unit;
/*      */   
/*      */   private final double minimum;
/*      */   
/*      */   private final double maximum;
/*      */   
/*      */   private final RangeMeaning rangeMeaning;
/*      */   
/*      */   private transient DefaultCoordinateSystemAxis opposite;
/*      */   
/*      */   static {
/*  242 */     ALTITUDE.opposite = DEPTH;
/*  243 */     DEPTH.opposite = ALTITUDE;
/*  265 */     GEOCENTRIC_RADIUS = new DefaultCoordinateSystemAxis(78, "r", AxisDirection.UP, SI.METER);
/*  286 */     SPHERICAL_LONGITUDE = new DefaultCoordinateSystemAxis(206, "Ω", AxisDirection.EAST, NonSI.DEGREE_ANGLE);
/*  307 */     SPHERICAL_LATITUDE = new DefaultCoordinateSystemAxis(205, "θ", AxisDirection.NORTH, NonSI.DEGREE_ANGLE);
/*  327 */     X = new DefaultCoordinateSystemAxis(-1, "x", AxisDirection.EAST, SI.METER);
/*  347 */     Y = new DefaultCoordinateSystemAxis(-1, "y", AxisDirection.NORTH, SI.METER);
/*  361 */     Z = new DefaultCoordinateSystemAxis(-1, "z", AxisDirection.UP, SI.METER);
/*  378 */     GEOCENTRIC_X = new DefaultCoordinateSystemAxis(80, "X", AxisDirection.OTHER, SI.METER);
/*  395 */     GEOCENTRIC_Y = new DefaultCoordinateSystemAxis(81, "Y", AxisDirection.EAST, SI.METER);
/*  412 */     GEOCENTRIC_Z = new DefaultCoordinateSystemAxis(82, "Z", AxisDirection.NORTH, SI.METER);
/*  431 */     EASTING = new DefaultCoordinateSystemAxis(55, "E", AxisDirection.EAST, SI.METER);
/*  448 */     WESTING = new DefaultCoordinateSystemAxis(244, "W", AxisDirection.WEST, SI.METER);
/*  451 */     EASTING.opposite = WESTING;
/*  452 */     WESTING.opposite = EASTING;
/*  471 */     NORTHING = new DefaultCoordinateSystemAxis(150, "N", AxisDirection.NORTH, SI.METER);
/*  488 */     SOUTHING = new DefaultCoordinateSystemAxis(203, "S", AxisDirection.SOUTH, SI.METER);
/*  491 */     NORTHING.opposite = SOUTHING;
/*  492 */     SOUTHING.opposite = NORTHING;
/*  503 */     TIME = new DefaultCoordinateSystemAxis(218, "t", AxisDirection.FUTURE, NonSI.DAY);
/*  513 */     COLUMN = new DefaultCoordinateSystemAxis(25, "i", AxisDirection.COLUMN_POSITIVE, Unit.ONE);
/*  523 */     ROW = new DefaultCoordinateSystemAxis(187, "j", AxisDirection.ROW_POSITIVE, Unit.ONE);
/*  534 */     DISPLAY_X = new DefaultCoordinateSystemAxis(-1, "x", AxisDirection.DISPLAY_RIGHT, Unit.ONE);
/*  545 */     DISPLAY_Y = new DefaultCoordinateSystemAxis(-1, "y", AxisDirection.DISPLAY_DOWN, Unit.ONE);
/*  559 */     ALIASES = new HashMap<String, CoordinateSystemAxis>(12);
/*  562 */     ALIASES.put("lat", GEODETIC_LATITUDE);
/*  563 */     ALIASES.put("latitude", GEODETIC_LATITUDE);
/*  564 */     ALIASES.put("geodetic latitude", GEODETIC_LATITUDE);
/*  565 */     ALIASES.put("lon", GEODETIC_LONGITUDE);
/*  566 */     ALIASES.put("long", GEODETIC_LONGITUDE);
/*  567 */     ALIASES.put("longitude", GEODETIC_LONGITUDE);
/*  568 */     ALIASES.put("geodetic longitude", GEODETIC_LONGITUDE);
/*      */   }
/*      */   
/*      */   private static boolean nameMatchesXY(String xy, String name) {
/*  592 */     xy = xy.trim();
/*  593 */     if (xy.length() == 1) {
/*      */       DefaultCoordinateSystemAxis axis;
/*  595 */       switch (Character.toLowerCase(xy.charAt(0))) {
/*      */         case 'x':
/*  596 */           axis = EASTING;
/*      */           break;
/*      */         case 'y':
/*  597 */           axis = NORTHING;
/*      */           break;
/*      */         default:
/*  598 */           return false;
/*      */       } 
/*  600 */       return (axis.nameMatches(name) || axis.getOpposite().nameMatches(name));
/*      */     } 
/*  602 */     return false;
/*      */   }
/*      */   
/*      */   public DefaultCoordinateSystemAxis(CoordinateSystemAxis axis) {
/*  651 */     super((IdentifiedObject)axis);
/*  652 */     this.abbreviation = axis.getAbbreviation();
/*  653 */     this.direction = axis.getDirection();
/*  654 */     this.unit = axis.getUnit();
/*  655 */     this.minimum = axis.getMinimumValue();
/*  656 */     this.maximum = axis.getMaximumValue();
/*  657 */     this.rangeMeaning = axis.getRangeMeaning();
/*      */   }
/*      */   
/*      */   public DefaultCoordinateSystemAxis(Map<String, ?> properties, String abbreviation, AxisDirection direction, Unit<?> unit, double minimum, double maximum, RangeMeaning rangeMeaning) {
/*  685 */     super(properties);
/*  686 */     this.abbreviation = abbreviation;
/*  687 */     this.direction = direction;
/*  688 */     this.unit = unit;
/*  689 */     this.minimum = minimum;
/*  690 */     this.maximum = maximum;
/*  691 */     this.rangeMeaning = rangeMeaning;
/*  692 */     ensureNonNull("abbreviation", abbreviation);
/*  693 */     ensureNonNull("direction", direction);
/*  694 */     ensureNonNull("unit", unit);
/*  695 */     ensureNonNull("rangeMeaning", rangeMeaning);
/*  696 */     if (minimum >= maximum)
/*  697 */       throw new IllegalArgumentException(Errors.format(14, Double.valueOf(minimum), Double.valueOf(maximum))); 
/*      */   }
/*      */   
/*      */   public DefaultCoordinateSystemAxis(Map<String, ?> properties, String abbreviation, AxisDirection direction, Unit<?> unit) {
/*  722 */     super(properties);
/*  723 */     this.abbreviation = abbreviation;
/*  724 */     this.direction = direction;
/*  725 */     this.unit = unit;
/*  726 */     ensureNonNull("abbreviation", abbreviation);
/*  727 */     ensureNonNull("direction", direction);
/*  728 */     ensureNonNull("unit", unit);
/*  729 */     if (unit.isCompatible(NonSI.DEGREE_ANGLE)) {
/*  730 */       UnitConverter fromDegrees = NonSI.DEGREE_ANGLE.getConverterTo(unit);
/*  731 */       AxisDirection dir = direction.absolute();
/*  732 */       if (dir.equals(AxisDirection.NORTH)) {
/*  733 */         double range = Math.abs(fromDegrees.convert(90.0D));
/*  734 */         this.minimum = -range;
/*  735 */         this.maximum = range;
/*  736 */         this.rangeMeaning = RangeMeaning.EXACT;
/*      */         return;
/*      */       } 
/*  739 */       if (dir.equals(AxisDirection.EAST)) {
/*  740 */         double range = Math.abs(fromDegrees.convert(180.0D));
/*  741 */         this.minimum = -range;
/*  742 */         this.maximum = range;
/*  743 */         this.rangeMeaning = RangeMeaning.WRAPAROUND;
/*      */         return;
/*      */       } 
/*      */     } 
/*  747 */     this.minimum = Double.NEGATIVE_INFINITY;
/*  748 */     this.maximum = Double.POSITIVE_INFINITY;
/*  749 */     this.rangeMeaning = RangeMeaning.EXACT;
/*      */   }
/*      */   
/*      */   public DefaultCoordinateSystemAxis(String abbreviation, AxisDirection direction, Unit<?> unit) {
/*  765 */     this(Collections.singletonMap("name", abbreviation), abbreviation, direction, unit);
/*      */   }
/*      */   
/*      */   public DefaultCoordinateSystemAxis(InternationalString name, String abbreviation, AxisDirection direction, Unit<?> unit) {
/*  788 */     this(toMap(name), abbreviation, direction, unit);
/*      */   }
/*      */   
/*      */   private static Map<String, Object> toMap(InternationalString name) {
/*  796 */     Map<String, Object> properties = new HashMap<String, Object>(4);
/*  797 */     if (name != null) {
/*  799 */       properties.put("name", name.toString(null));
/*  800 */       properties.put("alias", NameFactory.create((CharSequence[])new InternationalString[] { name }));
/*      */     } 
/*  802 */     return properties;
/*      */   }
/*      */   
/*      */   private DefaultCoordinateSystemAxis(int name, String abbreviation, AxisDirection direction, Unit<?> unit) {
/*  821 */     this((name >= 0) ? Vocabulary.formatInternational(name) : (InternationalString)new SimpleInternationalString(abbreviation), abbreviation, direction, unit);
/*  823 */     PREDEFINED[PREDEFINED_COUNT++] = this;
/*      */   }
/*      */   
/*      */   public static DefaultCoordinateSystemAxis getPredefined(String name, AxisDirection direction) {
/*  853 */     ensureNonNull("name", name);
/*  854 */     name = name.trim();
/*  855 */     DefaultCoordinateSystemAxis found = null;
/*  856 */     for (int i = 0; i < PREDEFINED_COUNT; i++) {
/*  857 */       DefaultCoordinateSystemAxis candidate = PREDEFINED[i];
/*  858 */       if (direction == null || direction.equals(candidate.getDirection())) {
/*  862 */         if (candidate.abbreviation.equals(name))
/*  863 */           return candidate; 
/*  865 */         if (found == null && candidate.nameMatches(name))
/*  874 */           if ((candidate != GEODETIC_LONGITUDE && candidate != GEODETIC_LATITUDE) || 
/*  875 */             name.toLowerCase().startsWith("geodetic"))
/*  879 */             found = candidate;  
/*      */       } 
/*      */     } 
/*  882 */     return found;
/*      */   }
/*      */   
/*      */   static DefaultCoordinateSystemAxis getPredefined(CoordinateSystemAxis axis) {
/*  890 */     return getPredefined(axis.getName().getCode(), axis.getDirection());
/*      */   }
/*      */   
/*      */   static DefaultCoordinateSystemAxis[] values() {
/*  898 */     return (DefaultCoordinateSystemAxis[])PREDEFINED.clone();
/*      */   }
/*      */   
/*      */   public static AxisDirection getDirection(String direction) throws NoSuchElementException {
/*  909 */     ensureNonNull("direction", direction);
/*  910 */     direction = direction.trim();
/*  911 */     AxisDirection candidate = DirectionAlongMeridian.findDirection(direction);
/*  912 */     if (candidate != null)
/*  913 */       return candidate; 
/*  924 */     DirectionAlongMeridian meridian = DirectionAlongMeridian.parse(direction);
/*  925 */     if (meridian != null) {
/*  926 */       candidate = meridian.getDirection();
/*  927 */       assert candidate == DirectionAlongMeridian.findDirection(meridian.toString());
/*  928 */       return candidate;
/*      */     } 
/*  930 */     throw new NoSuchElementException(Errors.format(181, direction));
/*      */   }
/*      */   
/*      */   public AxisDirection getDirection() {
/*  952 */     return this.direction;
/*      */   }
/*      */   
/*      */   public String getAbbreviation() {
/*  963 */     return this.abbreviation;
/*      */   }
/*      */   
/*      */   public Unit<?> getUnit() {
/*  973 */     return this.unit;
/*      */   }
/*      */   
/*      */   public double getMinimumValue() {
/*  984 */     return this.minimum;
/*      */   }
/*      */   
/*      */   public double getMaximumValue() {
/*  995 */     return this.maximum;
/*      */   }
/*      */   
/*      */   public RangeMeaning getRangeMeaning() {
/* 1008 */     return this.rangeMeaning;
/*      */   }
/*      */   
/*      */   final DefaultCoordinateSystemAxis getOpposite() {
/* 1016 */     return this.opposite;
/*      */   }
/*      */   
/*      */   public static boolean isCompassDirection(AxisDirection direction) {
/* 1030 */     ensureNonNull("direction", direction);
/* 1031 */     int n = direction.ordinal() - AxisDirection.NORTH.ordinal();
/* 1032 */     return (n >= 0 && n < 16);
/*      */   }
/*      */   
/*      */   public static double getAngle(AxisDirection source, AxisDirection target) {
/* 1072 */     ensureNonNull("source", source);
/* 1073 */     ensureNonNull("target", target);
/* 1075 */     int compass = getCompassAngle(source, target);
/* 1076 */     if (compass != Integer.MIN_VALUE)
/* 1077 */       return compass * 22.5D; 
/* 1080 */     DirectionAlongMeridian src = DirectionAlongMeridian.parse(source);
/* 1081 */     if (src != null) {
/* 1082 */       DirectionAlongMeridian tgt = DirectionAlongMeridian.parse(target);
/* 1083 */       if (tgt != null)
/* 1084 */         return src.getAngle(tgt); 
/*      */     } 
/* 1087 */     return Double.NaN;
/*      */   }
/*      */   
/*      */   static int getCompassAngle(AxisDirection source, AxisDirection target) {
/* 1095 */     int base = AxisDirection.NORTH.ordinal();
/* 1096 */     int src = source.ordinal() - base;
/* 1097 */     if (src >= 0 && src < 16) {
/* 1098 */       int tgt = target.ordinal() - base;
/* 1099 */       if (tgt >= 0 && tgt < 16) {
/* 1100 */         tgt = src - tgt;
/* 1101 */         if (tgt < -8) {
/* 1102 */           tgt += 16;
/* 1103 */         } else if (tgt > 8) {
/* 1104 */           tgt -= 16;
/*      */         } 
/* 1106 */         return tgt;
/*      */       } 
/*      */     } 
/* 1109 */     return Integer.MIN_VALUE;
/*      */   }
/*      */   
/*      */   public static boolean perpendicular(AxisDirection first, AxisDirection second) {
/* 1122 */     return (Math.abs(Math.abs(getAngle(first, second)) - 90.0D) <= 1.0E-10D);
/*      */   }
/*      */   
/*      */   final DefaultCoordinateSystemAxis usingUnit(Unit<?> newUnit) throws IllegalArgumentException {
/* 1135 */     if (this.unit.equals(newUnit))
/* 1136 */       return this; 
/* 1138 */     if (this.unit.isCompatible(newUnit))
/* 1139 */       return new DefaultCoordinateSystemAxis(getProperties((IdentifiedObject)this, null), this.abbreviation, this.direction, newUnit, this.minimum, this.maximum, this.rangeMeaning); 
/* 1142 */     throw new IllegalArgumentException(Errors.format(76, newUnit));
/*      */   }
/*      */   
/*      */   public boolean nameMatches(String name) {
/* 1169 */     if (super.nameMatches(name))
/* 1170 */       return true; 
/* 1181 */     Object type = ALIASES.get(name.trim().toLowerCase());
/* 1182 */     return (type != null && type == ALIASES.get(getName().getCode().trim().toLowerCase()));
/*      */   }
/*      */   
/*      */   public boolean equals(AbstractIdentifiedObject object, boolean compareMetadata) {
/* 1195 */     if (object == this)
/* 1196 */       return true; 
/* 1198 */     if (super.equals(object, compareMetadata))
/* 1199 */       return equals((DefaultCoordinateSystemAxis)object, compareMetadata, true); 
/* 1201 */     return false;
/*      */   }
/*      */   
/*      */   final boolean equals(DefaultCoordinateSystemAxis that, boolean compareMetadata, boolean compareUnit) {
/* 1213 */     if (compareMetadata) {
/* 1214 */       if (!Utilities.equals(this.abbreviation, that.abbreviation) || !Utilities.equals(this.rangeMeaning, that.rangeMeaning) || Double.doubleToLongBits(this.minimum) != Double.doubleToLongBits(that.minimum) || Double.doubleToLongBits(this.maximum) != Double.doubleToLongBits(that.maximum))
/* 1219 */         return false; 
/*      */     } else {
/* 1237 */       String thatName = that.getName().getCode();
/* 1238 */       if (!nameMatches(thatName)) {
/* 1242 */         String thisName = getName().getCode();
/* 1243 */         if (!nameMatches((IdentifiedObject)that, thisName)) {
/* 1248 */           if (!compareUnit)
/* 1249 */             return false; 
/* 1252 */           if (!nameMatchesXY(thatName, thisName) && !nameMatchesXY(thisName, thatName))
/* 1253 */             return false; 
/*      */         } 
/*      */       } 
/*      */     } 
/* 1258 */     return (Utilities.equals(this.direction, that.direction) && (!compareUnit || Utilities.equals(this.unit, that.unit)));
/*      */   }
/*      */   
/*      */   public int hashCode() {
/* 1268 */     int code = 1684127127;
/* 1269 */     code = code * 37 + this.abbreviation.hashCode();
/* 1270 */     code = code * 37 + this.direction.hashCode();
/* 1271 */     code = code * 37 + this.unit.hashCode();
/* 1272 */     return code;
/*      */   }
/*      */   
/*      */   protected String formatWKT(Formatter formatter) {
/* 1286 */     formatter.append((CodeList)this.direction);
/* 1287 */     return "AXIS";
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\cs\DefaultCoordinateSystemAxis.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */