/*     */ package javax.measure.unit.format;
/*     */ 
/*     */ import java.util.Collections;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ import javax.measure.quantity.Acceleration;
/*     */ import javax.measure.quantity.Action;
/*     */ import javax.measure.quantity.AmountOfSubstance;
/*     */ import javax.measure.quantity.Angle;
/*     */ import javax.measure.quantity.Area;
/*     */ import javax.measure.quantity.DataAmount;
/*     */ import javax.measure.quantity.DataRate;
/*     */ import javax.measure.quantity.Dimensionless;
/*     */ import javax.measure.quantity.Duration;
/*     */ import javax.measure.quantity.DynamicViscosity;
/*     */ import javax.measure.quantity.ElectricCapacitance;
/*     */ import javax.measure.quantity.ElectricCharge;
/*     */ import javax.measure.quantity.ElectricConductance;
/*     */ import javax.measure.quantity.ElectricCurrent;
/*     */ import javax.measure.quantity.ElectricInductance;
/*     */ import javax.measure.quantity.ElectricPermittivity;
/*     */ import javax.measure.quantity.ElectricPotential;
/*     */ import javax.measure.quantity.ElectricResistance;
/*     */ import javax.measure.quantity.Energy;
/*     */ import javax.measure.quantity.Force;
/*     */ import javax.measure.quantity.Frequency;
/*     */ import javax.measure.quantity.Illuminance;
/*     */ import javax.measure.quantity.IonizingRadiation;
/*     */ import javax.measure.quantity.KinematicViscosity;
/*     */ import javax.measure.quantity.Length;
/*     */ import javax.measure.quantity.Luminance;
/*     */ import javax.measure.quantity.LuminousFlux;
/*     */ import javax.measure.quantity.LuminousIntensity;
/*     */ import javax.measure.quantity.MagneticFieldStrength;
/*     */ import javax.measure.quantity.MagneticFlux;
/*     */ import javax.measure.quantity.MagneticFluxDensity;
/*     */ import javax.measure.quantity.MagneticPermeability;
/*     */ import javax.measure.quantity.MagnetomotiveForce;
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
/*     */ import javax.measure.quantity.Wavenumber;
/*     */ import javax.measure.unit.AlternateUnit;
/*     */ import javax.measure.unit.BaseUnit;
/*     */ import javax.measure.unit.NonSI;
/*     */ import javax.measure.unit.SI;
/*     */ import javax.measure.unit.SystemOfUnits;
/*     */ import javax.measure.unit.Unit;
/*     */ 
/*     */ final class UCUM extends SystemOfUnits {
/*  55 */   private static HashSet<Unit<?>> UNITS = new HashSet<Unit<?>>();
/*     */   
/*     */   public static UCUM getInstance() {
/*  63 */     return INSTANCE;
/*     */   }
/*     */   
/*  65 */   private static final UCUM INSTANCE = new UCUM();
/*     */   
/*     */   private static <U extends Unit<?>> U ucum(U unit) {
/*  73 */     UNITS.add((Unit<?>)unit);
/*  74 */     return unit;
/*     */   }
/*     */   
/*     */   public Set<Unit<?>> getUnits() {
/*  88 */     return Collections.unmodifiableSet(UNITS);
/*     */   }
/*     */   
/*  94 */   public static final BaseUnit<Length> METER = ucum(SI.METRE);
/*     */   
/*  96 */   public static final BaseUnit<Duration> SECOND = ucum(SI.SECOND);
/*     */   
/* 103 */   public static final Unit<Mass> GRAM = ucum(SI.GRAM);
/*     */   
/* 105 */   public static final AlternateUnit<Angle> RADIAN = ucum(SI.RADIAN);
/*     */   
/* 107 */   public static final BaseUnit<Temperature> KELVIN = ucum(SI.KELVIN);
/*     */   
/* 109 */   public static final AlternateUnit<ElectricCharge> COULOMB = ucum(SI.COULOMB);
/*     */   
/* 111 */   public static final BaseUnit<LuminousIntensity> CANDELA = ucum(SI.CANDELA);
/*     */   
/* 116 */   public static final Unit<Dimensionless> TRIILLIONS = ucum(Unit.ONE.times(1000000000000L));
/*     */   
/* 118 */   public static final Unit<Dimensionless> BILLIONS = ucum(Unit.ONE.times(1000000000L));
/*     */   
/* 120 */   public static final Unit<Dimensionless> MILLIONS = ucum(Unit.ONE.times(1000000L));
/*     */   
/* 122 */   public static final Unit<Dimensionless> THOUSANDS = ucum(Unit.ONE.times(1000L));
/*     */   
/* 124 */   public static final Unit<Dimensionless> HUNDREDS = ucum(Unit.ONE.times(100L));
/*     */   
/* 126 */   public static final Unit<Dimensionless> PI = ucum(Unit.ONE.times(Math.PI));
/*     */   
/* 128 */   public static final Unit<Dimensionless> PERCENT = ucum(Unit.ONE.divide(100L));
/*     */   
/* 130 */   public static final Unit<Dimensionless> PER_THOUSAND = ucum(Unit.ONE.divide(1000L));
/*     */   
/* 132 */   public static final Unit<Dimensionless> PER_MILLION = ucum(Unit.ONE.divide(1000000L));
/*     */   
/* 134 */   public static final Unit<Dimensionless> PER_BILLION = ucum(Unit.ONE.divide(1000000000L));
/*     */   
/* 136 */   public static final Unit<Dimensionless> PER_TRILLION = ucum(Unit.ONE.divide(1000000000000L));
/*     */   
/* 145 */   public static final Unit<AmountOfSubstance> MOLE = (Unit<AmountOfSubstance>)ucum(SI.MOLE);
/*     */   
/* 151 */   public static final Unit<SolidAngle> STERADIAN = (Unit<SolidAngle>)ucum(SI.STERADIAN);
/*     */   
/* 153 */   public static final Unit<Frequency> HERTZ = (Unit<Frequency>)ucum(SI.HERTZ);
/*     */   
/* 155 */   public static final Unit<Force> NEWTON = (Unit<Force>)ucum(SI.NEWTON);
/*     */   
/* 157 */   public static final Unit<Pressure> PASCAL = (Unit<Pressure>)ucum(SI.PASCAL);
/*     */   
/* 159 */   public static final Unit<Energy> JOULE = (Unit<Energy>)ucum(SI.JOULE);
/*     */   
/* 161 */   public static final Unit<Power> WATT = (Unit<Power>)ucum(SI.WATT);
/*     */   
/* 167 */   public static final Unit<ElectricCurrent> AMPERE = (Unit<ElectricCurrent>)ucum(SI.AMPERE);
/*     */   
/* 173 */   public static final Unit<ElectricPotential> VOLT = (Unit<ElectricPotential>)ucum(SI.VOLT);
/*     */   
/* 175 */   public static final Unit<ElectricCapacitance> FARAD = (Unit<ElectricCapacitance>)ucum(SI.FARAD);
/*     */   
/* 177 */   public static final Unit<ElectricResistance> OHM = (Unit<ElectricResistance>)ucum(SI.OHM);
/*     */   
/* 183 */   public static final Unit<ElectricConductance> SIEMENS = (Unit<ElectricConductance>)ucum(SI.SIEMENS);
/*     */   
/* 185 */   public static final Unit<MagneticFlux> WEBER = (Unit<MagneticFlux>)ucum(SI.WEBER);
/*     */   
/* 187 */   public static final Unit<Temperature> CELSIUS = ucum(SI.CELSIUS);
/*     */   
/* 189 */   public static final Unit<MagneticFluxDensity> TESLA = (Unit<MagneticFluxDensity>)ucum(SI.TESLA);
/*     */   
/* 191 */   public static final Unit<ElectricInductance> HENRY = (Unit<ElectricInductance>)ucum(SI.HENRY);
/*     */   
/* 193 */   public static final Unit<LuminousFlux> LUMEN = (Unit<LuminousFlux>)ucum(SI.LUMEN);
/*     */   
/* 195 */   public static final Unit<Illuminance> LUX = (Unit<Illuminance>)ucum(SI.LUX);
/*     */   
/* 197 */   public static final Unit<RadioactiveActivity> BECQUEREL = (Unit<RadioactiveActivity>)ucum(SI.BECQUEREL);
/*     */   
/* 199 */   public static final Unit<RadiationDoseAbsorbed> GRAY = (Unit<RadiationDoseAbsorbed>)ucum(SI.GRAY);
/*     */   
/* 201 */   public static final Unit<RadiationDoseEffective> SIEVERT = (Unit<RadiationDoseEffective>)ucum(SI.SIEVERT);
/*     */   
/* 211 */   public static final Unit<Angle> DEGREE = ucum(NonSI.DEGREE_ANGLE);
/*     */   
/* 217 */   public static final Unit<Angle> GRADE = ucum(NonSI.GRADE);
/*     */   
/* 219 */   public static final Unit<Angle> GON = GRADE;
/*     */   
/* 221 */   public static final Unit<Angle> MINUTE_ANGLE = ucum(NonSI.MINUTE_ANGLE);
/*     */   
/* 223 */   public static final Unit<Angle> SECOND_ANGLE = ucum(NonSI.SECOND_ANGLE);
/*     */   
/* 225 */   public static final Unit<Volume> LITER = ucum(NonSI.LITRE);
/*     */   
/* 227 */   public static final Unit<Area> ARE = ucum(NonSI.ARE);
/*     */   
/* 229 */   public static final Unit<Duration> MINUTE = ucum(NonSI.MINUTE);
/*     */   
/* 231 */   public static final Unit<Duration> HOUR = ucum(NonSI.HOUR);
/*     */   
/* 233 */   public static final Unit<Duration> DAY = ucum(NonSI.DAY);
/*     */   
/* 235 */   public static final Unit<Duration> YEAR_TROPICAL = ucum(DAY.times(365.24219D));
/*     */   
/* 237 */   public static final Unit<Duration> YEAR_JULIAN = ucum(DAY.times(365.25D));
/*     */   
/* 239 */   public static final Unit<Duration> YEAR_GREGORIAN = ucum(DAY.times(365.2425D));
/*     */   
/* 241 */   public static final Unit<Duration> YEAR = ucum(DAY.times(365.25D));
/*     */   
/* 243 */   public static final Unit<Duration> MONTH_SYNODAL = ucum(DAY.times(29.53059D));
/*     */   
/* 245 */   public static final Unit<Duration> MONTH_JULIAN = ucum(YEAR_JULIAN.divide(12L));
/*     */   
/* 247 */   public static final Unit<Duration> MONTH_GREGORIAN = ucum(YEAR_GREGORIAN.divide(12L));
/*     */   
/* 249 */   public static final Unit<Duration> MONTH = ucum(YEAR_JULIAN.divide(12L));
/*     */   
/* 251 */   public static final Unit<Mass> TONNE = ucum(NonSI.METRIC_TON);
/*     */   
/* 253 */   public static final Unit<Pressure> BAR = ucum(NonSI.BAR);
/*     */   
/* 255 */   public static final Unit<Mass> ATOMIC_MASS_UNIT = ucum(NonSI.ATOMIC_MASS);
/*     */   
/* 257 */   public static final Unit<Energy> ELECTRON_VOLT = ucum(NonSI.ELECTRON_VOLT);
/*     */   
/* 259 */   public static final Unit<Length> ASTRONOMIC_UNIT = ucum(NonSI.ASTRONOMICAL_UNIT);
/*     */   
/* 261 */   public static final Unit<Length> PARSEC = ucum(NonSI.PARSEC);
/*     */   
/* 266 */   public static final Unit<Velocity> C = ucum(NonSI.C);
/*     */   
/* 268 */   public static final Unit<Action> PLANCK = ucum(JOULE.times((Unit)SECOND).times(6.6260755E-24D));
/*     */   
/* 270 */   public static final Unit<Dimensionless> BOLTZMAN = ucum(JOULE.divide((Unit)KELVIN).times(1.380658E-23D));
/*     */   
/* 272 */   public static final Unit<ElectricPermittivity> PERMITTIVITY_OF_VACUUM = ucum(FARAD.divide((Unit)METER).times(8.854187817E-12D));
/*     */   
/* 274 */   public static final Unit<MagneticPermeability> PERMEABILITY_OF_VACUUM = ucum(NEWTON.times(1.2566370614359173E-6D).divide(AMPERE.pow(2)));
/*     */   
/* 276 */   public static final Unit<ElectricCharge> ELEMENTARY_CHARGE = ucum(NonSI.E);
/*     */   
/* 278 */   public static final Unit<Mass> ELECTRON_MASS = ucum(NonSI.ELECTRON_MASS);
/*     */   
/* 280 */   public static final Unit<Mass> PROTON_MASS = ucum(GRAM.times(1.6726231E-24D));
/*     */   
/* 282 */   public static final Unit<Dimensionless> NEWTON_CONSTANT_OF_GRAVITY = ucum(METER.pow(3).times(SI.KILOGRAM.pow(-1)).times(SECOND.pow(-2)).times(6.67259E-11D));
/*     */   
/* 284 */   public static final Unit<Acceleration> ACCELLERATION_OF_FREEFALL = ucum(NonSI.G);
/*     */   
/* 286 */   public static final Unit<Pressure> ATMOSPHERE = ucum(NonSI.ATMOSPHERE);
/*     */   
/* 288 */   public static final Unit<Length> LIGHT_YEAR = ucum(NonSI.LIGHT_YEAR);
/*     */   
/* 290 */   public static final Unit<Force> GRAM_FORCE = ucum(GRAM.times(ACCELLERATION_OF_FREEFALL));
/*     */   
/* 296 */   public static final Unit<Wavenumber> KAYSER = ucum(Unit.ONE.divide(SI.CENTI((Unit)METER)));
/*     */   
/* 298 */   public static final Unit<Acceleration> GAL = ucum(SI.CENTI((Unit)METER).divide(SECOND.pow(2)));
/*     */   
/* 300 */   public static final Unit<Force> DYNE = ucum(NonSI.DYNE);
/*     */   
/* 302 */   public static final Unit<Energy> ERG = ucum(NonSI.ERG);
/*     */   
/* 304 */   public static final Unit<DynamicViscosity> POISE = ucum(NonSI.POISE);
/*     */   
/* 306 */   public static final Unit<ElectricCurrent> BIOT = ucum(AMPERE.times(10L));
/*     */   
/* 308 */   public static final Unit<KinematicViscosity> STOKES = ucum(NonSI.STOKE);
/*     */   
/* 310 */   public static final Unit<MagneticFlux> MAXWELL = ucum(NonSI.MAXWELL);
/*     */   
/* 312 */   public static final Unit<MagneticFluxDensity> GAUSS = ucum(NonSI.GAUSS);
/*     */   
/* 314 */   public static final Unit<MagneticFieldStrength> OERSTED = ucum(Unit.ONE.divide(PI).times(AMPERE).divide((Unit)METER).times(250L));
/*     */   
/* 316 */   public static final Unit<MagnetomotiveForce> GILBERT = ucum(OERSTED.times(SI.CENTI((Unit)METER)));
/*     */   
/* 318 */   public static final Unit<Luminance> STILB = ucum(CANDELA.divide(SI.CENTI((Unit)METER).pow(2)));
/*     */   
/* 320 */   public static final Unit<Illuminance> LAMBERT = ucum(NonSI.LAMBERT);
/*     */   
/* 322 */   public static final Unit<Illuminance> PHOT = ucum(LUX.divide(10000L));
/*     */   
/* 324 */   public static final Unit<RadioactiveActivity> CURIE = ucum(NonSI.CURIE);
/*     */   
/* 326 */   public static final Unit<IonizingRadiation> ROENTGEN = ucum(NonSI.ROENTGEN);
/*     */   
/* 328 */   public static final Unit<RadiationDoseAbsorbed> RAD = ucum(NonSI.RAD);
/*     */   
/* 330 */   public static final Unit<RadiationDoseEffective> REM = ucum(NonSI.REM);
/*     */   
/* 335 */   public static final Unit<Length> INCH_INTERNATIONAL = ucum(SI.CENTI((Unit)METER).times(254L).divide(100L));
/*     */   
/* 337 */   public static final Unit<Length> FOOT_INTERNATIONAL = ucum(INCH_INTERNATIONAL.times(12L));
/*     */   
/* 339 */   public static final Unit<Length> YARD_INTERNATIONAL = ucum(FOOT_INTERNATIONAL.times(3L));
/*     */   
/* 341 */   public static final Unit<Length> MILE_INTERNATIONAL = ucum(FOOT_INTERNATIONAL.times(5280L));
/*     */   
/* 343 */   public static final Unit<Length> FATHOM_INTERNATIONAL = ucum(FOOT_INTERNATIONAL.times(6L));
/*     */   
/* 345 */   public static final Unit<Length> NAUTICAL_MILE_INTERNATIONAL = ucum(METER.times(1852L));
/*     */   
/* 347 */   public static final Unit<Velocity> KNOT_INTERNATIONAL = ucum(NAUTICAL_MILE_INTERNATIONAL.divide(HOUR));
/*     */   
/* 349 */   public static final Unit<Area> SQUARE_INCH_INTERNATIONAL = ucum(INCH_INTERNATIONAL.pow(2));
/*     */   
/* 351 */   public static final Unit<Area> SQUARE_FOOT_INTERNATIONAL = ucum(FOOT_INTERNATIONAL.pow(2));
/*     */   
/* 353 */   public static final Unit<Area> SQUARE_YARD_INTERNATIONAL = ucum(YARD_INTERNATIONAL.pow(2));
/*     */   
/* 355 */   public static final Unit<Volume> CUBIC_INCH_INTERNATIONAL = ucum(INCH_INTERNATIONAL.pow(3));
/*     */   
/* 357 */   public static final Unit<Volume> CUBIC_FOOT_INTERNATIONAL = ucum(FOOT_INTERNATIONAL.pow(3));
/*     */   
/* 359 */   public static final Unit<Volume> CUBIC_YARD_INTERNATIONAL = ucum(YARD_INTERNATIONAL.pow(3));
/*     */   
/* 361 */   public static final Unit<Volume> BOARD_FOOT_INTERNATIONAL = ucum(CUBIC_INCH_INTERNATIONAL.times(144L));
/*     */   
/* 363 */   public static final Unit<Volume> CORD_INTERNATIONAL = ucum(CUBIC_FOOT_INTERNATIONAL.times(128L));
/*     */   
/* 365 */   public static final Unit<Length> MIL_INTERNATIONAL = ucum(INCH_INTERNATIONAL.divide(1000L));
/*     */   
/* 367 */   public static final Unit<Area> CIRCULAR_MIL_INTERNATIONAL = ucum(MIL_INTERNATIONAL.times(PI).divide(4L));
/*     */   
/* 369 */   public static final Unit<Length> HAND_INTERNATIONAL = ucum(INCH_INTERNATIONAL.times(4L));
/*     */   
/* 374 */   public static final Unit<Length> FOOT_US_SURVEY = ucum(METER.times(1200L).divide(3937L));
/*     */   
/* 376 */   public static final Unit<Length> YARD_US_SURVEY = ucum(FOOT_US_SURVEY.times(3L));
/*     */   
/* 378 */   public static final Unit<Length> INCH_US_SURVEY = ucum(FOOT_US_SURVEY.divide(12L));
/*     */   
/* 380 */   public static final Unit<Length> ROD_US_SURVEY = ucum(FOOT_US_SURVEY.times(33L).divide(2L));
/*     */   
/* 382 */   public static final Unit<Length> CHAIN_US_SURVEY = ucum(ROD_US_SURVEY.times(4L));
/*     */   
/* 384 */   public static final Unit<Length> LINK_US_SURVEY = ucum(CHAIN_US_SURVEY.divide(100L));
/*     */   
/* 386 */   public static final Unit<Length> RAMDEN_CHAIN_US_SURVEY = ucum(FOOT_US_SURVEY.times(100L));
/*     */   
/* 388 */   public static final Unit<Length> RAMDEN_LINK_US_SURVEY = ucum(CHAIN_US_SURVEY.divide(100L));
/*     */   
/* 390 */   public static final Unit<Length> FATHOM_US_SURVEY = ucum(FOOT_US_SURVEY.times(6L));
/*     */   
/* 392 */   public static final Unit<Length> FURLONG_US_SURVEY = ucum(ROD_US_SURVEY.times(40L));
/*     */   
/* 394 */   public static final Unit<Length> MILE_US_SURVEY = ucum(FURLONG_US_SURVEY.times(8L));
/*     */   
/* 396 */   public static final Unit<Area> ACRE_US_SURVEY = ucum(ROD_US_SURVEY.pow(2).times(160L));
/*     */   
/* 398 */   public static final Unit<Area> SQUARE_ROD_US_SURVEY = ucum(ROD_US_SURVEY.pow(2));
/*     */   
/* 400 */   public static final Unit<Area> SQUARE_MILE_US_SURVEY = ucum(MILE_US_SURVEY.pow(2));
/*     */   
/* 402 */   public static final Unit<Area> SECTION_US_SURVEY = ucum(MILE_US_SURVEY.pow(2));
/*     */   
/* 404 */   public static final Unit<Area> TOWNSHP_US_SURVEY = ucum(SECTION_US_SURVEY.times(36L));
/*     */   
/* 406 */   public static final Unit<Length> MIL_US_SURVEY = ucum(INCH_US_SURVEY.divide(1000L));
/*     */   
/* 411 */   public static final Unit<Length> INCH_BRITISH = ucum(SI.CENTI((Unit)METER).times(2539998L).divide(1000000L));
/*     */   
/* 413 */   public static final Unit<Length> FOOT_BRITISH = ucum(INCH_BRITISH.times(12L));
/*     */   
/* 415 */   public static final Unit<Length> ROD_BRITISH = ucum(FOOT_BRITISH.times(33L).divide(2L));
/*     */   
/* 417 */   public static final Unit<Length> CHAIN_BRITISH = ucum(ROD_BRITISH.times(4L));
/*     */   
/* 419 */   public static final Unit<Length> LINK_BRITISH = ucum(CHAIN_BRITISH.divide(100L));
/*     */   
/* 421 */   public static final Unit<Length> FATHOM_BRITISH = ucum(FOOT_BRITISH.times(6L));
/*     */   
/* 423 */   public static final Unit<Length> PACE_BRITISH = ucum(FOOT_BRITISH.times(5L).divide(20L));
/*     */   
/* 425 */   public static final Unit<Length> YARD_BRITISH = ucum(FOOT_BRITISH.times(3L));
/*     */   
/* 427 */   public static final Unit<Length> MILE_BRITISH = ucum(FOOT_BRITISH.times(5280L));
/*     */   
/* 429 */   public static final Unit<Length> NAUTICAL_MILE_BRITISH = ucum(FOOT_BRITISH.times(6080L));
/*     */   
/* 431 */   public static final Unit<Length> KNOT_BRITISH = ucum(NAUTICAL_MILE_BRITISH.divide(HOUR));
/*     */   
/* 433 */   public static final Unit<Area> ACRE_BRITISH = ucum(YARD_BRITISH.pow(2).times(4840L));
/*     */   
/* 438 */   public static final Unit<Volume> GALLON_US = ucum(CUBIC_INCH_INTERNATIONAL.times(231L));
/*     */   
/* 440 */   public static final Unit<Volume> BARREL_US = ucum(GALLON_US.times(42L));
/*     */   
/* 442 */   public static final Unit<Volume> QUART_US = ucum(GALLON_US.divide(4L));
/*     */   
/* 444 */   public static final Unit<Volume> PINT_US = ucum(QUART_US.divide(2L));
/*     */   
/* 446 */   public static final Unit<Volume> GILL_US = ucum(PINT_US.divide(4L));
/*     */   
/* 448 */   public static final Unit<Volume> FLUID_OUNCE_US = ucum(GILL_US.divide(4L));
/*     */   
/* 450 */   public static final Unit<Volume> FLUID_DRAM_US = ucum(FLUID_OUNCE_US.divide(8L));
/*     */   
/* 452 */   public static final Unit<Volume> MINIM_US = ucum(FLUID_DRAM_US.divide(60L));
/*     */   
/* 454 */   public static final Unit<Volume> CORD_US = ucum(CUBIC_FOOT_INTERNATIONAL.times(128L));
/*     */   
/* 456 */   public static final Unit<Volume> BUSHEL_US = ucum(CUBIC_INCH_INTERNATIONAL.times(215042L).divide(100L));
/*     */   
/* 458 */   public static final Unit<Volume> GALLON_WINCHESTER = ucum(BUSHEL_US.divide(8L));
/*     */   
/* 460 */   public static final Unit<Volume> PECK_US = ucum(BUSHEL_US.divide(4L));
/*     */   
/* 462 */   public static final Unit<Volume> DRY_QUART_US = ucum(PECK_US.divide(8L));
/*     */   
/* 464 */   public static final Unit<Volume> DRY_PINT_US = ucum(DRY_QUART_US.divide(2L));
/*     */   
/* 466 */   public static final Unit<Volume> TABLESPOON_US = ucum(FLUID_OUNCE_US.divide(2L));
/*     */   
/* 468 */   public static final Unit<Volume> TEASPOON_US = ucum(TABLESPOON_US.divide(3L));
/*     */   
/* 470 */   public static final Unit<Volume> CUP_US = ucum(TABLESPOON_US.times(16L));
/*     */   
/* 475 */   public static final Unit<Volume> GALLON_BRITISH = ucum(LITER.times(454609L).divide(100000L));
/*     */   
/* 477 */   public static final Unit<Volume> PECK_BRITISH = ucum(GALLON_BRITISH.times(2L));
/*     */   
/* 479 */   public static final Unit<Volume> BUSHEL_BRITISH = ucum(PECK_BRITISH.times(4L));
/*     */   
/* 481 */   public static final Unit<Volume> QUART_BRITISH = ucum(GALLON_BRITISH.divide(4L));
/*     */   
/* 483 */   public static final Unit<Volume> PINT_BRITISH = ucum(QUART_BRITISH.divide(2L));
/*     */   
/* 485 */   public static final Unit<Volume> GILL_BRITISH = ucum(PINT_BRITISH.divide(4L));
/*     */   
/* 487 */   public static final Unit<Volume> FLUID_OUNCE_BRITISH = ucum(GILL_BRITISH.divide(5L));
/*     */   
/* 489 */   public static final Unit<Volume> FLUID_DRAM_BRITISH = ucum(FLUID_OUNCE_BRITISH.divide(8L));
/*     */   
/* 491 */   public static final Unit<Volume> MINIM_BRITISH = ucum(FLUID_DRAM_BRITISH.divide(60L));
/*     */   
/* 496 */   public static final Unit<Mass> GRAIN = ucum(SI.MILLI(GRAM).times(6479891L).divide(100000L));
/*     */   
/* 498 */   public static final Unit<Mass> POUND = ucum(GRAM.times(7000L));
/*     */   
/* 500 */   public static final Unit<Mass> OUNCE = ucum(POUND.divide(16L));
/*     */   
/* 502 */   public static final Unit<Mass> DRAM = ucum(OUNCE.divide(16L));
/*     */   
/* 504 */   public static final Unit<Mass> SHORT_HUNDREDWEIGHT = ucum(POUND.times(100L));
/*     */   
/* 506 */   public static final Unit<Mass> LONG_HUNDREDWEIGHT = ucum(POUND.times(112L));
/*     */   
/* 508 */   public static final Unit<Mass> SHORT_TON = ucum(SHORT_HUNDREDWEIGHT.times(20L));
/*     */   
/* 510 */   public static final Unit<Mass> LONG_TON = ucum(LONG_HUNDREDWEIGHT.times(20L));
/*     */   
/* 512 */   public static final Unit<Mass> STONE = ucum(POUND.times(14L));
/*     */   
/* 516 */   public static final Unit<Force> POUND_FORCE = ucum(POUND.times(ACCELLERATION_OF_FREEFALL));
/*     */   
/* 521 */   public static final Unit<Mass> PENNYWEIGHT_TROY = ucum(GRAIN.times(24L));
/*     */   
/* 523 */   public static final Unit<Mass> OUNCE_TROY = ucum(PENNYWEIGHT_TROY.times(24L));
/*     */   
/* 525 */   public static final Unit<Mass> POUND_TROY = ucum(OUNCE_TROY.times(12L));
/*     */   
/* 530 */   public static final Unit<Mass> SCRUPLE_APOTHECARY = ucum(GRAIN.times(20L));
/*     */   
/* 532 */   public static final Unit<Mass> DRAM_APOTHECARY = ucum(SCRUPLE_APOTHECARY.times(3L));
/*     */   
/* 534 */   public static final Unit<Mass> OUNCE_APOTHECARY = ucum(DRAM_APOTHECARY.times(8L));
/*     */   
/* 536 */   public static final Unit<Mass> POUND_APOTHECARY = ucum(OUNCE_APOTHECARY.times(12L));
/*     */   
/* 541 */   public static final Unit<Length> LINE = ucum(INCH_INTERNATIONAL.divide(12L));
/*     */   
/* 543 */   public static final Unit<Length> POINT = ucum(LINE.divide(6L));
/*     */   
/* 545 */   public static final Unit<Length> PICA = ucum(POINT.times(12L));
/*     */   
/* 547 */   public static final Unit<Length> POINT_PRINTER = ucum(INCH_INTERNATIONAL.times(13837L).divide(1000000L));
/*     */   
/* 549 */   public static final Unit<Length> PICA_PRINTER = ucum(POINT_PRINTER.times(12L));
/*     */   
/* 551 */   public static final Unit<Length> PIED = ucum(SI.CENTI((Unit)METER).times(3248L).divide(100L));
/*     */   
/* 553 */   public static final Unit<Length> POUCE = ucum(PIED.divide(12L));
/*     */   
/* 555 */   public static final Unit<Length> LINGE = ucum(POUCE.divide(12L));
/*     */   
/* 557 */   public static final Unit<Length> DIDOT = ucum(LINGE.divide(6L));
/*     */   
/* 559 */   public static final Unit<Length> CICERO = ucum(DIDOT.times(12L));
/*     */   
/* 564 */   public static final Unit<Temperature> FAHRENHEIT = ucum(KELVIN.times(5L).divide(9L).plus(459.67D));
/*     */   
/* 566 */   public static final Unit<Energy> CALORIE_AT_15C = ucum(JOULE.times(41858L).divide(10000L));
/*     */   
/* 568 */   public static final Unit<Energy> CALORIE_AT_20C = ucum(JOULE.times(41819L).divide(10000L));
/*     */   
/* 570 */   public static final Unit<Energy> CALORIE_MEAN = ucum(JOULE.times(419002L).divide(100000L));
/*     */   
/* 572 */   public static final Unit<Energy> CALORIE_INTERNATIONAL_TABLE = ucum(JOULE.times(41868L).divide(10000L));
/*     */   
/* 574 */   public static final Unit<Energy> CALORIE_THERMOCHEMICAL = ucum(JOULE.times(4184L).divide(1000L));
/*     */   
/* 576 */   public static final Unit<Energy> CALORIE = ucum(CALORIE_THERMOCHEMICAL);
/*     */   
/* 578 */   public static final Unit<Energy> CALORIE_FOOD = ucum(SI.KILO(CALORIE_THERMOCHEMICAL));
/*     */   
/* 580 */   public static final Unit<Energy> BTU_AT_39F = ucum(SI.KILO(JOULE).times(105967L).divide(100000L));
/*     */   
/* 582 */   public static final Unit<Energy> BTU_AT_59F = ucum(SI.KILO(JOULE).times(105480L).divide(100000L));
/*     */   
/* 584 */   public static final Unit<Energy> BTU_AT_60F = ucum(SI.KILO(JOULE).times(105468L).divide(100000L));
/*     */   
/* 586 */   public static final Unit<Energy> BTU_MEAN = ucum(SI.KILO(JOULE).times(105587L).divide(100000L));
/*     */   
/* 588 */   public static final Unit<Energy> BTU_INTERNATIONAL_TABLE = ucum(SI.KILO(JOULE).times(105505585262L).divide(100000000000L));
/*     */   
/* 590 */   public static final Unit<Energy> BTU_THERMOCHEMICAL = ucum(SI.KILO(JOULE).times(105735L).divide(100000L));
/*     */   
/* 592 */   public static final Unit<Energy> BTU = ucum(BTU_THERMOCHEMICAL);
/*     */   
/* 594 */   public static final Unit<Power> HORSEPOWER = ucum(FOOT_INTERNATIONAL.times(POUND_FORCE).divide((Unit)SECOND));
/*     */   
/* 602 */   public static final Unit<Volume> STERE = ucum(METER.pow(3));
/*     */   
/* 604 */   public static final Unit<Length> ANGSTROM = ucum(SI.NANO((Unit)METER).divide(10L));
/*     */   
/* 606 */   public static final Unit<Area> BARN = ucum(SI.FEMTO((Unit)METER).pow(2).times(100L));
/*     */   
/* 608 */   public static final Unit<Pressure> ATMOSPHERE_TECHNICAL = ucum(SI.KILO(GRAM_FORCE).divide(SI.CENTI((Unit)METER).pow(2)));
/*     */   
/* 610 */   public static final Unit<ElectricConductance> MHO = ucum(SIEMENS.alternate("mho").asType(ElectricConductance.class));
/*     */   
/* 612 */   public static final Unit<Pressure> POUND_PER_SQUARE_INCH = ucum(POUND_FORCE.divide(INCH_INTERNATIONAL.pow(2)));
/*     */   
/* 614 */   public static final Unit<Pressure> CIRCLE = ucum(PI.times((Unit)RADIAN).times(2L));
/*     */   
/* 616 */   public static final Unit<Pressure> SPHERE = ucum(PI.times(STERADIAN).times(4L));
/*     */   
/* 618 */   public static final Unit<Mass> CARAT_METRIC = ucum(GRAM.divide(5L));
/*     */   
/* 620 */   public static final Unit<Dimensionless> CARAT_GOLD = ucum(Unit.ONE.divide(24L));
/*     */   
/* 625 */   public static final Unit<DataAmount> BIT = (Unit<DataAmount>)ucum(SI.BIT);
/*     */   
/* 627 */   public static final Unit<DataAmount> BYTE = ucum(NonSI.BYTE);
/*     */   
/* 629 */   public static final Unit<DataRate> BAUD = ucum(Unit.ONE.divide((Unit)SECOND));
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\measur\\unit\format\UCUM.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */