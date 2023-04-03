/*     */ package javax.measure.unit;
/*     */ 
/*     */ import java.util.Collections;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ import javax.measure.converter.LogConverter;
/*     */ import javax.measure.converter.RationalConverter;
/*     */ import javax.measure.converter.UnitConverter;
/*     */ import javax.measure.quantity.Acceleration;
/*     */ import javax.measure.quantity.AmountOfSubstance;
/*     */ import javax.measure.quantity.Angle;
/*     */ import javax.measure.quantity.Area;
/*     */ import javax.measure.quantity.DataAmount;
/*     */ import javax.measure.quantity.Dimensionless;
/*     */ import javax.measure.quantity.Duration;
/*     */ import javax.measure.quantity.DynamicViscosity;
/*     */ import javax.measure.quantity.ElectricCharge;
/*     */ import javax.measure.quantity.ElectricCurrent;
/*     */ import javax.measure.quantity.Energy;
/*     */ import javax.measure.quantity.Force;
/*     */ import javax.measure.quantity.Illuminance;
/*     */ import javax.measure.quantity.KinematicViscosity;
/*     */ import javax.measure.quantity.Length;
/*     */ import javax.measure.quantity.MagneticFlux;
/*     */ import javax.measure.quantity.MagneticFluxDensity;
/*     */ import javax.measure.quantity.Mass;
/*     */ import javax.measure.quantity.Power;
/*     */ import javax.measure.quantity.Pressure;
/*     */ import javax.measure.quantity.Quantity;
/*     */ import javax.measure.quantity.RadiationDoseAbsorbed;
/*     */ import javax.measure.quantity.RadiationDoseEffective;
/*     */ import javax.measure.quantity.RadioactiveActivity;
/*     */ import javax.measure.quantity.SolidAngle;
/*     */ import javax.measure.quantity.Temperature;
/*     */ import javax.measure.quantity.Velocity;
/*     */ import javax.measure.quantity.Volume;
/*     */ 
/*     */ public final class NonSI extends SystemOfUnits {
/*  34 */   private static HashSet<Unit<?>> UNITS = new HashSet<Unit<?>>();
/*     */   
/*     */   private static final int STANDARD_GRAVITY_DIVIDEND = 980665;
/*     */   
/*     */   private static final int STANDARD_GRAVITY_DIVISOR = 100000;
/*     */   
/*     */   private static final int INTERNATIONAL_FOOT_DIVIDEND = 3048;
/*     */   
/*     */   private static final int INTERNATIONAL_FOOT_DIViSOR = 10000;
/*     */   
/*     */   private static final int AVOIRDUPOIS_POUND_DIVIDEND = 45359237;
/*     */   
/*     */   private static final int AVOIRDUPOIS_POUND_DIVISOR = 100000000;
/*     */   
/*     */   private static final double AVOGADRO_CONSTANT = 6.02214199E23D;
/*     */   
/*     */   private static final double ELEMENTARY_CHARGE = 1.602176462E-19D;
/*     */   
/*     */   public static NonSI getInstance() {
/*  76 */     return INSTANCE;
/*     */   }
/*     */   
/*  78 */   private static final NonSI INSTANCE = new NonSI();
/*     */   
/*  88 */   public static final Unit<Dimensionless> PERCENT = nonSI(Unit.ONE.divide(100L));
/*     */   
/*  94 */   public static final Unit<Dimensionless> DECIBEL = nonSI(Unit.ONE.transform((new LogConverter(10.0D)).inverse().concatenate((UnitConverter)new RationalConverter(1L, 10L))));
/*     */   
/* 106 */   public static final Unit<AmountOfSubstance> ATOM = nonSI(SI.MOLE.divide(6.02214199E23D));
/*     */   
/* 117 */   public static final Unit<Length> FOOT = nonSI(SI.METRE.times(3048L).divide(10000L));
/*     */   
/* 124 */   public static final Unit<Length> FOOT_SURVEY_US = nonSI(SI.METRE.times(1200L).divide(3937L));
/*     */   
/* 131 */   public static final Unit<Length> YARD = nonSI(FOOT.times(3L));
/*     */   
/* 137 */   public static final Unit<Length> INCH = nonSI(FOOT.divide(12L));
/*     */   
/* 143 */   public static final Unit<Length> MILE = nonSI(SI.METRE.times(1609344L).divide(1000L));
/*     */   
/* 149 */   public static final Unit<Length> NAUTICAL_MILE = nonSI(SI.METRE.times(1852L));
/*     */   
/* 155 */   public static final Unit<Length> ANGSTROM = nonSI(SI.METRE.divide(10000000000L));
/*     */   
/* 161 */   public static final Unit<Length> ASTRONOMICAL_UNIT = nonSI(SI.METRE.times(1.49597870691E11D));
/*     */   
/* 168 */   public static final Unit<Length> LIGHT_YEAR = nonSI(SI.METRE.times(9.460528405E15D));
/*     */   
/* 177 */   public static final Unit<Length> PARSEC = nonSI(SI.METRE.times(3.085677E16D));
/*     */   
/* 184 */   public static final Unit<Length> POINT = nonSI(INCH.times(13837L).divide(1000000L));
/*     */   
/* 192 */   public static final Unit<Length> PIXEL = nonSI(INCH.divide(72L));
/*     */   
/* 197 */   public static final Unit<Length> COMPUTER_POINT = PIXEL;
/*     */   
/* 207 */   public static final Unit<Duration> MINUTE = nonSI(SI.SECOND.times(60L));
/*     */   
/* 213 */   public static final Unit<Duration> HOUR = nonSI(MINUTE.times(60L));
/*     */   
/* 219 */   public static final Unit<Duration> DAY = nonSI(HOUR.times(24L));
/*     */   
/* 225 */   public static final Unit<Duration> WEEK = nonSI(DAY.times(7L));
/*     */   
/* 231 */   public static final Unit<Duration> YEAR = nonSI(SI.SECOND.times(31556952L));
/*     */   
/* 237 */   public static final Unit<Duration> MONTH = nonSI(YEAR.divide(12L));
/*     */   
/* 245 */   public static final Unit<Duration> DAY_SIDEREAL = nonSI(SI.SECOND.times(86164.09D));
/*     */   
/* 252 */   public static final Unit<Duration> YEAR_SIDEREAL = nonSI(SI.SECOND.times(3.155814954E7D));
/*     */   
/* 259 */   public static final Unit<Duration> YEAR_CALENDAR = nonSI(DAY.times(365L));
/*     */   
/* 269 */   public static final Unit<Mass> ATOMIC_MASS = nonSI(SI.KILOGRAM.times(1.6605387280149467E-27D));
/*     */   
/* 276 */   public static final Unit<Mass> ELECTRON_MASS = nonSI(SI.KILOGRAM.times(9.10938188E-31D));
/*     */   
/* 283 */   public static final Unit<Mass> POUND = nonSI(SI.KILOGRAM.times(45359237L).divide(100000000L));
/*     */   
/* 289 */   public static final Unit<Mass> OUNCE = nonSI(POUND.divide(16L));
/*     */   
/* 295 */   public static final Unit<Mass> TON_US = nonSI(POUND.times(2000L));
/*     */   
/* 301 */   public static final Unit<Mass> TON_UK = nonSI(POUND.times(2240L));
/*     */   
/* 307 */   public static final Unit<Mass> METRIC_TON = nonSI(SI.KILOGRAM.times(1000L));
/*     */   
/* 317 */   public static final Unit<ElectricCharge> E = nonSI(SI.COULOMB.times(1.602176462E-19D));
/*     */   
/* 325 */   public static final Unit<ElectricCharge> FARADAY = nonSI(SI.COULOMB.times(96485.3414719984D));
/*     */   
/* 333 */   public static final Unit<ElectricCharge> FRANKLIN = nonSI(SI.COULOMB.times(3.3356E-10D));
/*     */   
/* 344 */   public static final Unit<Temperature> RANKINE = nonSI(SI.KELVIN.times(5L).divide(9L));
/*     */   
/* 351 */   public static final Unit<Temperature> FAHRENHEIT = nonSI(RANKINE.plus(459.67D));
/*     */   
/* 361 */   public static final Unit<Angle> REVOLUTION = nonSI(SI.RADIAN.times(6.283185307179586D));
/*     */   
/* 367 */   public static final Unit<Angle> DEGREE_ANGLE = nonSI(REVOLUTION.divide(360L));
/*     */   
/* 373 */   public static final Unit<Angle> MINUTE_ANGLE = nonSI(DEGREE_ANGLE.divide(60L));
/*     */   
/* 379 */   public static final Unit<Angle> SECOND_ANGLE = nonSI(MINUTE_ANGLE.divide(60L));
/*     */   
/* 385 */   public static final Unit<Angle> CENTIRADIAN = nonSI(SI.RADIAN.divide(100L));
/*     */   
/* 391 */   public static final Unit<Angle> GRADE = nonSI(REVOLUTION.divide(400L));
/*     */   
/* 401 */   public static final Unit<Velocity> MILES_PER_HOUR = nonSI(MILE.divide(HOUR)).asType(Velocity.class);
/*     */   
/* 408 */   public static final Unit<Velocity> KILOMETRES_PER_HOUR = nonSI(SI.KILOMETRE.divide(HOUR)).asType(Velocity.class);
/*     */   
/* 414 */   public static final Unit<Velocity> KILOMETERS_PER_HOUR = KILOMETRES_PER_HOUR;
/*     */   
/* 420 */   public static final Unit<Velocity> KNOT = nonSI(NAUTICAL_MILE.divide(HOUR)).asType(Velocity.class);
/*     */   
/* 427 */   public static final Unit<Velocity> MACH = nonSI(SI.METRES_PER_SECOND.times(331.6D));
/*     */   
/* 433 */   public static final Unit<Velocity> C = nonSI(SI.METRES_PER_SECOND.times(299792458L));
/*     */   
/* 443 */   public static final Unit<Acceleration> G = nonSI(SI.METRES_PER_SQUARE_SECOND.times(980665L).divide(100000L));
/*     */   
/* 454 */   public static final Unit<Area> ARE = nonSI(SI.SQUARE_METRE.times(100L));
/*     */   
/* 460 */   public static final Unit<Area> HECTARE = nonSI(ARE.times(100L));
/*     */   
/* 470 */   public static final Unit<DataAmount> BYTE = nonSI(SI.BIT.times(8L));
/*     */   
/* 475 */   public static final Unit<DataAmount> OCTET = BYTE;
/*     */   
/* 487 */   public static final Unit<ElectricCurrent> GILBERT = nonSI(SI.AMPERE.times(0.7957747154594768D));
/*     */   
/* 498 */   public static final Unit<Energy> ERG = nonSI(SI.JOULE.divide(10000000L));
/*     */   
/* 504 */   public static final Unit<Energy> ELECTRON_VOLT = nonSI(SI.JOULE.times(1.602176462E-19D));
/*     */   
/* 515 */   public static final Unit<Illuminance> LAMBERT = nonSI(SI.LUX.times(10000L));
/*     */   
/* 525 */   public static final Unit<MagneticFlux> MAXWELL = nonSI(SI.WEBER.divide(100000000L));
/*     */   
/* 535 */   public static final Unit<MagneticFluxDensity> GAUSS = nonSI(SI.TESLA.divide(10000L));
/*     */   
/* 545 */   public static final Unit<Force> DYNE = nonSI(SI.NEWTON.divide(100000L));
/*     */   
/* 551 */   public static final Unit<Force> KILOGRAM_FORCE = nonSI(SI.NEWTON.times(980665L).divide(100000L));
/*     */   
/* 558 */   public static final Unit<Force> POUND_FORCE = nonSI(SI.NEWTON.times(44482216152605L).divide(10000000000000L));
/*     */   
/* 571 */   public static final Unit<Power> HORSEPOWER = nonSI(SI.WATT.times(735.499D));
/*     */   
/* 581 */   public static final Unit<Pressure> ATMOSPHERE = nonSI(SI.PASCAL.times(101325L));
/*     */   
/* 587 */   public static final Unit<Pressure> BAR = nonSI(SI.PASCAL.times(100000L));
/*     */   
/* 594 */   public static final Unit<Pressure> MILLIMETER_OF_MERCURY = nonSI(SI.PASCAL.times(133.322D));
/*     */   
/* 602 */   public static final Unit<Pressure> INCH_OF_MERCURY = nonSI(SI.PASCAL.times(3386.388D));
/*     */   
/* 612 */   public static final Unit<RadiationDoseAbsorbed> RAD = nonSI(SI.GRAY.divide(100L));
/*     */   
/* 618 */   public static final Unit<RadiationDoseEffective> REM = nonSI(SI.SIEVERT.divide(100L));
/*     */   
/* 628 */   public static final Unit<RadioactiveActivity> CURIE = nonSI(SI.BECQUEREL.times(37000000000L));
/*     */   
/* 635 */   public static final Unit<RadioactiveActivity> RUTHERFORD = nonSI(SI.BECQUEREL.times(1000000L));
/*     */   
/* 646 */   public static final Unit<SolidAngle> SPHERE = nonSI(SI.STERADIAN.times(12.566370614359172D));
/*     */   
/* 657 */   public static final Unit<Volume> LITRE = nonSI(SI.CUBIC_METRE.divide(1000L));
/*     */   
/* 662 */   public static final Unit<Volume> LITER = LITRE;
/*     */   
/* 667 */   public static final Unit<Volume> CUBIC_INCH = nonSI(INCH.pow(3).asType(Volume.class));
/*     */   
/* 675 */   public static final Unit<Volume> GALLON_LIQUID_US = nonSI(CUBIC_INCH.times(231L));
/*     */   
/* 681 */   public static final Unit<Volume> OUNCE_LIQUID_US = nonSI(GALLON_LIQUID_US.divide(128L));
/*     */   
/* 688 */   public static final Unit<Volume> GALLON_DRY_US = nonSI(CUBIC_INCH.times(2688025L).divide(10000L));
/*     */   
/* 694 */   public static final Unit<Volume> GALLON_UK = nonSI(LITRE.times(454609L).divide(100000L));
/*     */   
/* 700 */   public static final Unit<Volume> OUNCE_LIQUID_UK = nonSI(GALLON_UK.divide(160L));
/*     */   
/* 712 */   public static final Unit<DynamicViscosity> POISE = (Unit<DynamicViscosity>)nonSI(SI.GRAM.divide(SI.<Length>CENTI(SI.METRE).times(SI.SECOND)));
/*     */   
/* 720 */   public static final Unit<KinematicViscosity> STOKE = (Unit<KinematicViscosity>)nonSI(SI.<Length>CENTI(SI.METRE).pow(2).divide(SI.SECOND));
/*     */   
/* 731 */   public static final Unit<?> ROENTGEN = nonSI(SI.COULOMB.divide(SI.KILOGRAM).times(2.58E-4D));
/*     */   
/*     */   public Set<Unit<?>> getUnits() {
/* 744 */     return Collections.unmodifiableSet(UNITS);
/*     */   }
/*     */   
/*     */   private static <U extends Unit<?>> U nonSI(U unit) {
/* 754 */     UNITS.add((Unit<?>)unit);
/* 755 */     return unit;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\measur\\unit\NonSI.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */