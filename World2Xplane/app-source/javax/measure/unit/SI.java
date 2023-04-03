/*     */ package javax.measure.unit;
/*     */ 
/*     */ import java.util.Collections;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ import javax.measure.converter.MultiplyConverter;
/*     */ import javax.measure.converter.RationalConverter;
/*     */ import javax.measure.converter.UnitConverter;
/*     */ import javax.measure.quantity.Acceleration;
/*     */ import javax.measure.quantity.AmountOfSubstance;
/*     */ import javax.measure.quantity.Angle;
/*     */ import javax.measure.quantity.Area;
/*     */ import javax.measure.quantity.CatalyticActivity;
/*     */ import javax.measure.quantity.DataAmount;
/*     */ import javax.measure.quantity.Duration;
/*     */ import javax.measure.quantity.ElectricCapacitance;
/*     */ import javax.measure.quantity.ElectricCharge;
/*     */ import javax.measure.quantity.ElectricConductance;
/*     */ import javax.measure.quantity.ElectricCurrent;
/*     */ import javax.measure.quantity.ElectricInductance;
/*     */ import javax.measure.quantity.ElectricPotential;
/*     */ import javax.measure.quantity.ElectricResistance;
/*     */ import javax.measure.quantity.Energy;
/*     */ import javax.measure.quantity.Force;
/*     */ import javax.measure.quantity.Frequency;
/*     */ import javax.measure.quantity.Illuminance;
/*     */ import javax.measure.quantity.Length;
/*     */ import javax.measure.quantity.LuminousFlux;
/*     */ import javax.measure.quantity.LuminousIntensity;
/*     */ import javax.measure.quantity.MagneticFlux;
/*     */ import javax.measure.quantity.MagneticFluxDensity;
/*     */ import javax.measure.quantity.Mass;
/*     */ import javax.measure.quantity.Power;
/*     */ import javax.measure.quantity.Pressure;
/*     */ import javax.measure.quantity.RadiationDoseAbsorbed;
/*     */ import javax.measure.quantity.RadiationDoseEffective;
/*     */ import javax.measure.quantity.RadioactiveActivity;
/*     */ import javax.measure.quantity.SolidAngle;
/*     */ import javax.measure.quantity.Temperature;
/*     */ import javax.measure.quantity.Velocity;
/*     */ import javax.measure.quantity.Volume;
/*     */ 
/*     */ public final class SI extends SystemOfUnits {
/*  41 */   private static HashSet<Unit<?>> UNITS = new HashSet<Unit<?>>();
/*     */   
/*     */   public static SI getInstance() {
/*  56 */     return INSTANCE;
/*     */   }
/*     */   
/*  58 */   private static final SI INSTANCE = new SI();
/*     */   
/*  72 */   public static final BaseUnit<ElectricCurrent> AMPERE = si(new BaseUnit<ElectricCurrent>("A"));
/*     */   
/*  84 */   public static final BaseUnit<LuminousIntensity> CANDELA = si(new BaseUnit<LuminousIntensity>("cd"));
/*     */   
/*  93 */   public static final BaseUnit<Temperature> KELVIN = si(new BaseUnit<Temperature>("K"));
/*     */   
/* 103 */   public static final BaseUnit<Mass> KILOGRAM = si(new BaseUnit<Mass>("kg"));
/*     */   
/* 110 */   public static final BaseUnit<Length> METRE = si(new BaseUnit<Length>("m"));
/*     */   
/* 115 */   public static final Unit<Length> METER = METRE;
/*     */   
/* 122 */   public static final BaseUnit<AmountOfSubstance> MOLE = si(new BaseUnit<AmountOfSubstance>("mol"));
/*     */   
/* 131 */   public static final BaseUnit<Duration> SECOND = si(new BaseUnit<Duration>("s"));
/*     */   
/* 142 */   public static final Unit<Mass> GRAM = KILOGRAM.divide(1000L);
/*     */   
/* 149 */   public static final AlternateUnit<Angle> RADIAN = si(new AlternateUnit<Angle>("rad", Unit.ONE));
/*     */   
/* 158 */   public static final AlternateUnit<SolidAngle> STERADIAN = si(new AlternateUnit<SolidAngle>("sr", Unit.ONE));
/*     */   
/* 164 */   public static final AlternateUnit<DataAmount> BIT = si(new AlternateUnit<DataAmount>("bit", Unit.ONE));
/*     */   
/* 173 */   public static final AlternateUnit<Frequency> HERTZ = si(new AlternateUnit<Frequency>("Hz", Unit.ONE.divide(SECOND)));
/*     */   
/* 182 */   public static final AlternateUnit<Force> NEWTON = si(new AlternateUnit<Force>("N", METRE.times(KILOGRAM).divide(SECOND.pow(2))));
/*     */   
/* 190 */   public static final AlternateUnit<Pressure> PASCAL = si(new AlternateUnit<Pressure>("Pa", NEWTON.divide(METRE.pow(2))));
/*     */   
/* 199 */   public static final AlternateUnit<Energy> JOULE = si(new AlternateUnit<Energy>("J", NEWTON.times(METRE)));
/*     */   
/* 207 */   public static final AlternateUnit<Power> WATT = si(new AlternateUnit<Power>("W", JOULE.divide(SECOND)));
/*     */   
/* 217 */   public static final AlternateUnit<ElectricCharge> COULOMB = si(new AlternateUnit<ElectricCharge>("C", SECOND.times(AMPERE)));
/*     */   
/* 228 */   public static final AlternateUnit<ElectricPotential> VOLT = si(new AlternateUnit<ElectricPotential>("V", WATT.divide(AMPERE)));
/*     */   
/* 238 */   public static final AlternateUnit<ElectricCapacitance> FARAD = si(new AlternateUnit<ElectricCapacitance>("F", COULOMB.divide(VOLT)));
/*     */   
/* 248 */   public static final AlternateUnit<ElectricResistance> OHM = si(new AlternateUnit<ElectricResistance>("Ω", VOLT.divide(AMPERE)));
/*     */   
/* 256 */   public static final AlternateUnit<ElectricConductance> SIEMENS = si(new AlternateUnit<ElectricConductance>("S", AMPERE.divide(VOLT)));
/*     */   
/* 266 */   public static final AlternateUnit<MagneticFlux> WEBER = si(new AlternateUnit<MagneticFlux>("Wb", VOLT.times(SECOND)));
/*     */   
/* 275 */   public static final AlternateUnit<MagneticFluxDensity> TESLA = si(new AlternateUnit<MagneticFluxDensity>("T", WEBER.divide(METRE.pow(2))));
/*     */   
/* 285 */   public static final AlternateUnit<ElectricInductance> HENRY = si(new AlternateUnit<ElectricInductance>("H", WEBER.divide(AMPERE)));
/*     */   
/* 294 */   public static final Unit<Temperature> CELSIUS = si(KELVIN.plus(273.15D));
/*     */   
/* 301 */   public static final AlternateUnit<LuminousFlux> LUMEN = si(new AlternateUnit<LuminousFlux>("lm", CANDELA.times(STERADIAN)));
/*     */   
/* 308 */   public static final AlternateUnit<Illuminance> LUX = si(new AlternateUnit<Illuminance>("lx", LUMEN.divide(METRE.pow(2))));
/*     */   
/* 317 */   public static final AlternateUnit<RadioactiveActivity> BECQUEREL = si(new AlternateUnit<RadioactiveActivity>("Bq", Unit.ONE.divide(SECOND)));
/*     */   
/* 327 */   public static final AlternateUnit<RadiationDoseAbsorbed> GRAY = si(new AlternateUnit<RadiationDoseAbsorbed>("Gy", JOULE.divide(KILOGRAM)));
/*     */   
/* 337 */   public static final AlternateUnit<RadiationDoseEffective> SIEVERT = si(new AlternateUnit<RadiationDoseEffective>("Sv", JOULE.divide(KILOGRAM)));
/*     */   
/* 343 */   public static final AlternateUnit<CatalyticActivity> KATAL = si(new AlternateUnit<CatalyticActivity>("kat", MOLE.divide(SECOND)));
/*     */   
/* 353 */   public static final Unit<Velocity> METRES_PER_SECOND = si(new ProductUnit<Velocity>(METRE.divide(SECOND)));
/*     */   
/* 359 */   public static final Unit<Velocity> METERS_PER_SECOND = METRES_PER_SECOND;
/*     */   
/* 364 */   public static final Unit<Acceleration> METRES_PER_SQUARE_SECOND = si(new ProductUnit<Acceleration>(METRES_PER_SECOND.divide(SECOND)));
/*     */   
/* 370 */   public static final Unit<Acceleration> METERS_PER_SQUARE_SECOND = METRES_PER_SQUARE_SECOND;
/*     */   
/* 375 */   public static final Unit<Area> SQUARE_METRE = si(new ProductUnit<Area>(METRE.times(METRE)));
/*     */   
/* 381 */   public static final Unit<Volume> CUBIC_METRE = si(new ProductUnit<Volume>(SQUARE_METRE.times(METRE)));
/*     */   
/* 387 */   public static final Unit<Length> KILOMETRE = METER.times(1000L);
/*     */   
/* 392 */   public static final Unit<Length> KILOMETER = KILOMETRE;
/*     */   
/* 397 */   public static final Unit<Length> CENTIMETRE = METRE.divide(100L);
/*     */   
/* 402 */   public static final Unit<Length> CENTIMETER = CENTIMETRE;
/*     */   
/* 407 */   public static final Unit<Length> MILLIMETRE = METRE.divide(1000L);
/*     */   
/* 412 */   public static final Unit<Length> MILLIMETER = MILLIMETRE;
/*     */   
/*     */   public static <Q extends javax.measure.quantity.Quantity> Unit<Q> YOTTA(Unit<Q> unit) {
/* 426 */     return unit.transform((UnitConverter)E24);
/*     */   }
/*     */   
/*     */   public static <Q extends javax.measure.quantity.Quantity> Unit<Q> ZETTA(Unit<Q> unit) {
/* 437 */     return unit.transform((UnitConverter)E21);
/*     */   }
/*     */   
/*     */   public static <Q extends javax.measure.quantity.Quantity> Unit<Q> EXA(Unit<Q> unit) {
/* 448 */     return unit.transform((UnitConverter)E18);
/*     */   }
/*     */   
/*     */   public static <Q extends javax.measure.quantity.Quantity> Unit<Q> PETA(Unit<Q> unit) {
/* 459 */     return unit.transform((UnitConverter)E15);
/*     */   }
/*     */   
/*     */   public static <Q extends javax.measure.quantity.Quantity> Unit<Q> TERA(Unit<Q> unit) {
/* 470 */     return unit.transform((UnitConverter)E12);
/*     */   }
/*     */   
/*     */   public static <Q extends javax.measure.quantity.Quantity> Unit<Q> GIGA(Unit<Q> unit) {
/* 481 */     return unit.transform((UnitConverter)E9);
/*     */   }
/*     */   
/*     */   public static <Q extends javax.measure.quantity.Quantity> Unit<Q> MEGA(Unit<Q> unit) {
/* 492 */     return unit.transform((UnitConverter)E6);
/*     */   }
/*     */   
/*     */   public static <Q extends javax.measure.quantity.Quantity> Unit<Q> KILO(Unit<Q> unit) {
/* 503 */     return unit.transform((UnitConverter)E3);
/*     */   }
/*     */   
/*     */   public static <Q extends javax.measure.quantity.Quantity> Unit<Q> HECTO(Unit<Q> unit) {
/* 514 */     return unit.transform((UnitConverter)E2);
/*     */   }
/*     */   
/*     */   public static <Q extends javax.measure.quantity.Quantity> Unit<Q> DEKA(Unit<Q> unit) {
/* 525 */     return unit.transform((UnitConverter)E1);
/*     */   }
/*     */   
/*     */   public static <Q extends javax.measure.quantity.Quantity> Unit<Q> DECI(Unit<Q> unit) {
/* 536 */     return unit.transform((UnitConverter)Em1);
/*     */   }
/*     */   
/*     */   public static <Q extends javax.measure.quantity.Quantity> Unit<Q> CENTI(Unit<Q> unit) {
/* 547 */     return unit.transform((UnitConverter)Em2);
/*     */   }
/*     */   
/*     */   public static <Q extends javax.measure.quantity.Quantity> Unit<Q> MILLI(Unit<Q> unit) {
/* 558 */     return unit.transform((UnitConverter)Em3);
/*     */   }
/*     */   
/*     */   public static <Q extends javax.measure.quantity.Quantity> Unit<Q> MICRO(Unit<Q> unit) {
/* 569 */     return unit.transform((UnitConverter)Em6);
/*     */   }
/*     */   
/*     */   public static <Q extends javax.measure.quantity.Quantity> Unit<Q> NANO(Unit<Q> unit) {
/* 580 */     return unit.transform((UnitConverter)Em9);
/*     */   }
/*     */   
/*     */   public static <Q extends javax.measure.quantity.Quantity> Unit<Q> PICO(Unit<Q> unit) {
/* 591 */     return unit.transform((UnitConverter)Em12);
/*     */   }
/*     */   
/*     */   public static <Q extends javax.measure.quantity.Quantity> Unit<Q> FEMTO(Unit<Q> unit) {
/* 602 */     return unit.transform((UnitConverter)Em15);
/*     */   }
/*     */   
/*     */   public static <Q extends javax.measure.quantity.Quantity> Unit<Q> ATTO(Unit<Q> unit) {
/* 613 */     return unit.transform((UnitConverter)Em18);
/*     */   }
/*     */   
/*     */   public static <Q extends javax.measure.quantity.Quantity> Unit<Q> ZEPTO(Unit<Q> unit) {
/* 624 */     return unit.transform((UnitConverter)Em21);
/*     */   }
/*     */   
/*     */   public static <Q extends javax.measure.quantity.Quantity> Unit<Q> YOCTO(Unit<Q> unit) {
/* 635 */     return unit.transform((UnitConverter)Em24);
/*     */   }
/*     */   
/*     */   public Set<Unit<?>> getUnits() {
/* 648 */     return Collections.unmodifiableSet(UNITS);
/*     */   }
/*     */   
/*     */   private static <U extends Unit<?>> U si(U unit) {
/* 658 */     UNITS.add((Unit<?>)unit);
/* 659 */     return unit;
/*     */   }
/*     */   
/* 664 */   static final MultiplyConverter E24 = new MultiplyConverter(1.0E24D);
/*     */   
/* 666 */   static final MultiplyConverter E21 = new MultiplyConverter(1.0E21D);
/*     */   
/* 668 */   static final RationalConverter E18 = new RationalConverter(1000000000000000000L, 1L);
/*     */   
/* 671 */   static final RationalConverter E15 = new RationalConverter(1000000000000000L, 1L);
/*     */   
/* 674 */   static final RationalConverter E12 = new RationalConverter(1000000000000L, 1L);
/*     */   
/* 677 */   static final RationalConverter E9 = new RationalConverter(1000000000L, 1L);
/*     */   
/* 679 */   static final RationalConverter E6 = new RationalConverter(1000000L, 1L);
/*     */   
/* 681 */   static final RationalConverter E3 = new RationalConverter(1000L, 1L);
/*     */   
/* 683 */   static final RationalConverter E2 = new RationalConverter(100L, 1L);
/*     */   
/* 685 */   static final RationalConverter E1 = new RationalConverter(10L, 1L);
/*     */   
/* 687 */   static final RationalConverter Em1 = new RationalConverter(1L, 10L);
/*     */   
/* 689 */   static final RationalConverter Em2 = new RationalConverter(1L, 100L);
/*     */   
/* 691 */   static final RationalConverter Em3 = new RationalConverter(1L, 1000L);
/*     */   
/* 693 */   static final RationalConverter Em6 = new RationalConverter(1L, 1000000L);
/*     */   
/* 695 */   static final RationalConverter Em9 = new RationalConverter(1L, 1000000000L);
/*     */   
/* 697 */   static final RationalConverter Em12 = new RationalConverter(1L, 1000000000000L);
/*     */   
/* 700 */   static final RationalConverter Em15 = new RationalConverter(1L, 1000000000000000L);
/*     */   
/* 703 */   static final RationalConverter Em18 = new RationalConverter(1L, 1000000000000000000L);
/*     */   
/* 706 */   static final MultiplyConverter Em21 = new MultiplyConverter(1.0E-21D);
/*     */   
/* 708 */   static final MultiplyConverter Em24 = new MultiplyConverter(1.0E-24D);
/*     */   
/* 713 */   public static final Unit<Velocity> METRE_PER_SECOND = METRES_PER_SECOND;
/*     */   
/* 718 */   public static final Unit<Acceleration> METRE_PER_SQUARE_SECOND = METRES_PER_SQUARE_SECOND;
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\measur\\unit\SI.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */