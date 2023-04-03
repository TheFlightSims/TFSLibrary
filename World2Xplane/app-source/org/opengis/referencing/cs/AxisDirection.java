/*     */ package org.opengis.referencing.cs;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.opengis.annotation.Obligation;
/*     */ import org.opengis.annotation.Specification;
/*     */ import org.opengis.annotation.UML;
/*     */ import org.opengis.util.CodeList;
/*     */ 
/*     */ @UML(identifier = "CS_AxisDirection", specification = Specification.ISO_19111)
/*     */ public final class AxisDirection extends CodeList<AxisDirection> {
/*     */   private static final long serialVersionUID = -4405275475770755714L;
/*     */   
/*  50 */   private static final List<AxisDirection> VALUES = new ArrayList<AxisDirection>(32);
/*     */   
/*     */   @UML(identifier = "CS_AxisOrientationEnum.CS_AO_Other", specification = Specification.OGC_01009)
/*  56 */   public static final AxisDirection OTHER = new AxisDirection("OTHER");
/*     */   
/*     */   @UML(identifier = "north", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19111)
/*     */   public static final AxisDirection NORTH;
/*     */   
/*     */   @UML(identifier = "northNorthEast", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19111)
/*     */   public static final AxisDirection NORTH_NORTH_EAST;
/*     */   
/*     */   @UML(identifier = "northEast", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19111)
/*     */   public static final AxisDirection NORTH_EAST;
/*     */   
/*     */   @UML(identifier = "eastNorthEast", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19111)
/*     */   public static final AxisDirection EAST_NORTH_EAST;
/*     */   
/*     */   @UML(identifier = "east", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19111)
/*     */   public static final AxisDirection EAST;
/*     */   
/*     */   @UML(identifier = "eastSouthEast", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19111)
/*     */   public static final AxisDirection EAST_SOUTH_EAST;
/*     */   
/*     */   @UML(identifier = "southEast", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19111)
/*     */   public static final AxisDirection SOUTH_EAST;
/*     */   
/*     */   @UML(identifier = "southSouthEast", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19111)
/*     */   public static final AxisDirection SOUTH_SOUTH_EAST;
/*     */   
/*     */   @UML(identifier = "south", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19111)
/*     */   public static final AxisDirection SOUTH;
/*     */   
/*     */   @UML(identifier = "southSouthWest", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19111)
/*     */   public static final AxisDirection SOUTH_SOUTH_WEST;
/*     */   
/*     */   @UML(identifier = "southWest", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19111)
/*     */   public static final AxisDirection SOUTH_WEST;
/*     */   
/*     */   @UML(identifier = "westSouthWest", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19111)
/*     */   public static final AxisDirection WEST_SOUTH_WEST;
/*     */   
/*     */   @UML(identifier = "west", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19111)
/*     */   public static final AxisDirection WEST;
/*     */   
/*     */   @UML(identifier = "westNorthWest", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19111)
/*     */   public static final AxisDirection WEST_NORTH_WEST;
/*     */   
/*     */   @UML(identifier = "northWest", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19111)
/*     */   public static final AxisDirection NORTH_WEST;
/*     */   
/*     */   @UML(identifier = "northNorthWest", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19111)
/*     */   public static final AxisDirection NORTH_NORTH_WEST;
/*     */   
/*     */   @UML(identifier = "up", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19111)
/*     */   public static final AxisDirection UP;
/*     */   
/*     */   @UML(identifier = "down", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19111)
/*     */   public static final AxisDirection DOWN;
/*     */   
/*     */   @UML(identifier = "geocentricX", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19111)
/*     */   public static final AxisDirection GEOCENTRIC_X;
/*     */   
/*     */   @UML(identifier = "geocentricY", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19111)
/*     */   public static final AxisDirection GEOCENTRIC_Y;
/*     */   
/*     */   @UML(identifier = "geocentricZ", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19111)
/*     */   public static final AxisDirection GEOCENTRIC_Z;
/*     */   
/*     */   @UML(identifier = "future", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19111)
/*     */   public static final AxisDirection FUTURE;
/*     */   
/*     */   @UML(identifier = "past", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19111)
/*     */   public static final AxisDirection PAST;
/*     */   
/*     */   @UML(identifier = "columnPositive", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19111)
/*     */   public static final AxisDirection COLUMN_POSITIVE;
/*     */   
/*     */   @UML(identifier = "columnNegative", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19111)
/*     */   public static final AxisDirection COLUMN_NEGATIVE;
/*     */   
/*     */   @UML(identifier = "rowPositive", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19111)
/*     */   public static final AxisDirection ROW_POSITIVE;
/*     */   
/*     */   @UML(identifier = "rowNegative", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19111)
/*     */   public static final AxisDirection ROW_NEGATIVE;
/*     */   
/*     */   @UML(identifier = "displayRight", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19111)
/*     */   public static final AxisDirection DISPLAY_RIGHT;
/*     */   
/*     */   @UML(identifier = "displayLeft", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19111)
/*     */   public static final AxisDirection DISPLAY_LEFT;
/*     */   
/*     */   @UML(identifier = "displayUp", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19111)
/*     */   public static final AxisDirection DISPLAY_UP;
/*     */   
/*     */   @UML(identifier = "displayDown", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19111)
/*     */   public static final AxisDirection DISPLAY_DOWN;
/*     */   
/*     */   private transient AxisDirection opposite;
/*     */   
/*     */   static {
/*  58 */     OTHER.opposite = OTHER;
/*  68 */     NORTH = new AxisDirection("NORTH");
/*  76 */     NORTH_NORTH_EAST = new AxisDirection("NORTH_NORTH_EAST");
/*  84 */     NORTH_EAST = new AxisDirection("NORTH_EAST");
/*  92 */     EAST_NORTH_EAST = new AxisDirection("EAST_NORTH_EAST");
/*  99 */     EAST = new AxisDirection("EAST");
/* 107 */     EAST_SOUTH_EAST = new AxisDirection("EAST_SOUTH_EAST");
/* 115 */     SOUTH_EAST = new AxisDirection("SOUTH_EAST");
/* 123 */     SOUTH_SOUTH_EAST = new AxisDirection("SOUTH_SOUTH_EAST");
/* 129 */     SOUTH = new AxisDirection("SOUTH", NORTH);
/* 137 */     SOUTH_SOUTH_WEST = new AxisDirection("SOUTH_SOUTH_WEST", NORTH_NORTH_EAST);
/* 145 */     SOUTH_WEST = new AxisDirection("SOUTH_WEST", NORTH_EAST);
/* 153 */     WEST_SOUTH_WEST = new AxisDirection("WEST_SOUTH_WEST", EAST_NORTH_EAST);
/* 160 */     WEST = new AxisDirection("WEST", EAST);
/* 168 */     WEST_NORTH_WEST = new AxisDirection("WEST_NORTH_WEST", EAST_SOUTH_EAST);
/* 176 */     NORTH_WEST = new AxisDirection("NORTH_WEST", SOUTH_EAST);
/* 184 */     NORTH_NORTH_WEST = new AxisDirection("NORTH_NORTH_WEST", SOUTH_SOUTH_EAST);
/* 192 */     UP = new AxisDirection("UP");
/* 200 */     DOWN = new AxisDirection("DOWN", UP);
/* 209 */     GEOCENTRIC_X = new AxisDirection("GEOCENTRIC_X");
/* 219 */     GEOCENTRIC_Y = new AxisDirection("GEOCENTRIC_Y");
/* 228 */     GEOCENTRIC_Z = new AxisDirection("GEOCENTRIC_Z");
/* 236 */     FUTURE = new AxisDirection("FUTURE");
/* 244 */     PAST = new AxisDirection("PAST", FUTURE);
/* 252 */     COLUMN_POSITIVE = new AxisDirection("COLUMN_POSITIVE");
/* 260 */     COLUMN_NEGATIVE = new AxisDirection("COLUMN_NEGATIVE", COLUMN_POSITIVE);
/* 268 */     ROW_POSITIVE = new AxisDirection("ROW_POSITIVE");
/* 276 */     ROW_NEGATIVE = new AxisDirection("ROW_NEGATIVE", ROW_POSITIVE);
/* 284 */     DISPLAY_RIGHT = new AxisDirection("DISPLAY_RIGHT");
/* 292 */     DISPLAY_LEFT = new AxisDirection("DISPLAY_LEFT", DISPLAY_RIGHT);
/* 300 */     DISPLAY_UP = new AxisDirection("DISPLAY_UP");
/* 308 */     DISPLAY_DOWN = new AxisDirection("DISPLAY_DOWN", DISPLAY_UP);
/*     */   }
/*     */   
/*     */   private AxisDirection(String name) {
/* 323 */     super(name, VALUES);
/*     */   }
/*     */   
/*     */   private AxisDirection(String name, AxisDirection opposite) {
/* 330 */     this(name);
/* 331 */     if (opposite.opposite != null)
/* 332 */       throw new IllegalArgumentException(String.valueOf(opposite)); 
/* 334 */     this.opposite = opposite;
/* 335 */     opposite.opposite = this;
/*     */   }
/*     */   
/*     */   public static AxisDirection[] values() {
/* 344 */     synchronized (VALUES) {
/* 345 */       return VALUES.<AxisDirection>toArray(new AxisDirection[VALUES.size()]);
/*     */     } 
/*     */   }
/*     */   
/*     */   public AxisDirection[] family() {
/* 353 */     return values();
/*     */   }
/*     */   
/*     */   public AxisDirection opposite() {
/* 369 */     return this.opposite;
/*     */   }
/*     */   
/*     */   public AxisDirection absolute() {
/* 412 */     AxisDirection opposite = this.opposite;
/* 413 */     if (opposite != null && 
/* 414 */       opposite.ordinal() < ordinal())
/* 415 */       return opposite; 
/* 418 */     return this;
/*     */   }
/*     */   
/*     */   public static AxisDirection valueOf(String code) {
/* 429 */     return (AxisDirection)valueOf(AxisDirection.class, code);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\referencing\cs\AxisDirection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */