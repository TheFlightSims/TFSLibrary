/*      */ package org.geotools.referencing.wkt;
/*      */ 
/*      */ import java.io.BufferedReader;
/*      */ import java.text.ParseException;
/*      */ import java.text.ParsePosition;
/*      */ import java.util.Collections;
/*      */ import java.util.HashMap;
/*      */ import java.util.LinkedHashMap;
/*      */ import java.util.Locale;
/*      */ import java.util.Map;
/*      */ import javax.measure.quantity.Angle;
/*      */ import javax.measure.quantity.Length;
/*      */ import javax.measure.unit.NonSI;
/*      */ import javax.measure.unit.SI;
/*      */ import javax.measure.unit.Unit;
/*      */ import org.geotools.metadata.iso.citation.Citations;
/*      */ import org.geotools.referencing.NamedIdentifier;
/*      */ import org.geotools.referencing.ReferencingFactoryFinder;
/*      */ import org.geotools.referencing.cs.AbstractCS;
/*      */ import org.geotools.referencing.cs.DefaultCoordinateSystemAxis;
/*      */ import org.geotools.referencing.cs.DirectionAlongMeridian;
/*      */ import org.geotools.referencing.datum.BursaWolfParameters;
/*      */ import org.geotools.referencing.datum.DefaultGeodeticDatum;
/*      */ import org.geotools.referencing.datum.DefaultPrimeMeridian;
/*      */ import org.geotools.referencing.datum.DefaultVerticalDatum;
/*      */ import org.geotools.referencing.factory.ReferencingFactoryContainer;
/*      */ import org.geotools.referencing.operation.DefiningConversion;
/*      */ import org.geotools.resources.Arguments;
/*      */ import org.geotools.resources.i18n.Errors;
/*      */ import org.opengis.metadata.citation.Citation;
/*      */ import org.opengis.parameter.ParameterNotFoundException;
/*      */ import org.opengis.parameter.ParameterValue;
/*      */ import org.opengis.parameter.ParameterValueGroup;
/*      */ import org.opengis.referencing.FactoryException;
/*      */ import org.opengis.referencing.NoSuchIdentifierException;
/*      */ import org.opengis.referencing.crs.CRSFactory;
/*      */ import org.opengis.referencing.crs.CompoundCRS;
/*      */ import org.opengis.referencing.crs.CoordinateReferenceSystem;
/*      */ import org.opengis.referencing.crs.DerivedCRS;
/*      */ import org.opengis.referencing.crs.EngineeringCRS;
/*      */ import org.opengis.referencing.crs.GeocentricCRS;
/*      */ import org.opengis.referencing.crs.GeographicCRS;
/*      */ import org.opengis.referencing.crs.ProjectedCRS;
/*      */ import org.opengis.referencing.crs.VerticalCRS;
/*      */ import org.opengis.referencing.cs.AxisDirection;
/*      */ import org.opengis.referencing.cs.CSFactory;
/*      */ import org.opengis.referencing.cs.CoordinateSystem;
/*      */ import org.opengis.referencing.cs.CoordinateSystemAxis;
/*      */ import org.opengis.referencing.cs.EllipsoidalCS;
/*      */ import org.opengis.referencing.datum.DatumFactory;
/*      */ import org.opengis.referencing.datum.Ellipsoid;
/*      */ import org.opengis.referencing.datum.EngineeringDatum;
/*      */ import org.opengis.referencing.datum.GeodeticDatum;
/*      */ import org.opengis.referencing.datum.PrimeMeridian;
/*      */ import org.opengis.referencing.datum.VerticalDatum;
/*      */ import org.opengis.referencing.datum.VerticalDatumType;
/*      */ import org.opengis.referencing.operation.Conversion;
/*      */ import org.opengis.referencing.operation.MathTransform;
/*      */ import org.opengis.referencing.operation.MathTransformFactory;
/*      */ import org.opengis.referencing.operation.NoninvertibleTransformException;
/*      */ import org.opengis.referencing.operation.OperationMethod;
/*      */ 
/*      */ public class Parser extends MathTransformParser {
/*      */   private static final long serialVersionUID = -144097689843465085L;
/*      */   
/*      */   private static final boolean ALLOW_ORACLE_SYNTAX = true;
/*      */   
/*      */   private static Map<String, Class<?>> TYPES;
/*      */   
/*      */   protected final DatumFactory datumFactory;
/*      */   
/*      */   protected final CSFactory csFactory;
/*      */   
/*      */   protected final CRSFactory crsFactory;
/*      */   
/*      */   private final Map<String, AxisDirection> directions;
/*      */   
/*      */   public Parser() {
/*  131 */     this(Symbols.DEFAULT);
/*      */   }
/*      */   
/*      */   public Parser(Symbols symbols) {
/*  142 */     this(symbols, ReferencingFactoryFinder.getDatumFactory(null), ReferencingFactoryFinder.getCSFactory(null), ReferencingFactoryFinder.getCRSFactory(null), ReferencingFactoryFinder.getMathTransformFactory(null));
/*      */   }
/*      */   
/*      */   public Parser(Symbols symbols, ReferencingFactoryContainer factories) {
/*  156 */     this(symbols, factories.getDatumFactory(), factories.getCSFactory(), factories.getCRSFactory(), factories.getMathTransformFactory());
/*      */   }
/*      */   
/*      */   public Parser(Symbols symbols, DatumFactory datumFactory, CSFactory csFactory, CRSFactory crsFactory, MathTransformFactory mtFactory) {
/*  181 */     super(symbols, mtFactory);
/*  182 */     this.datumFactory = datumFactory;
/*  183 */     this.csFactory = csFactory;
/*  184 */     this.crsFactory = crsFactory;
/*  185 */     AxisDirection[] values = AxisDirection.values();
/*  186 */     this.directions = new HashMap<String, AxisDirection>((int)Math.ceil(((values.length + 1) / 0.75F)), 0.75F);
/*  188 */     for (int i = 0; i < values.length; i++)
/*  189 */       this.directions.put(values[i].name().trim().toUpperCase(), values[i]); 
/*      */   }
/*      */   
/*      */   public CoordinateReferenceSystem parseCoordinateReferenceSystem(String text) throws ParseException {
/*  203 */     Element element = getTree(text, new ParsePosition(0));
/*  204 */     CoordinateReferenceSystem crs = parseCoordinateReferenceSystem(element);
/*  205 */     element.close();
/*  206 */     return crs;
/*      */   }
/*      */   
/*      */   private CoordinateReferenceSystem parseCoordinateReferenceSystem(Element element) throws ParseException {
/*  219 */     Object key = element.peek();
/*  220 */     if (key instanceof Element) {
/*      */       DerivedCRS derivedCRS;
/*  221 */       String keyword = ((Element)key).keyword.trim().toUpperCase(this.symbols.locale);
/*  222 */       CoordinateReferenceSystem r = null;
/*      */       try {
/*  224 */         if ("GEOGCS".equals(keyword)) {
/*      */           GeographicCRS geographicCRS;
/*  224 */           return (CoordinateReferenceSystem)(geographicCRS = parseGeoGCS(element));
/*      */         } 
/*  225 */         if ("PROJCS".equals(keyword)) {
/*      */           ProjectedCRS projectedCRS;
/*  225 */           return (CoordinateReferenceSystem)(projectedCRS = parseProjCS(element));
/*      */         } 
/*  226 */         if ("GEOCCS".equals(keyword)) {
/*      */           GeocentricCRS geocentricCRS;
/*  226 */           return (CoordinateReferenceSystem)(geocentricCRS = parseGeoCCS(element));
/*      */         } 
/*  227 */         if ("VERT_CS".equals(keyword)) {
/*      */           VerticalCRS verticalCRS;
/*  227 */           return (CoordinateReferenceSystem)(verticalCRS = parseVertCS(element));
/*      */         } 
/*  228 */         if ("LOCAL_CS".equals(keyword)) {
/*      */           EngineeringCRS engineeringCRS;
/*  228 */           return (CoordinateReferenceSystem)(engineeringCRS = parseLocalCS(element));
/*      */         } 
/*  229 */         if ("COMPD_CS".equals(keyword)) {
/*      */           CompoundCRS compoundCRS;
/*  229 */           return (CoordinateReferenceSystem)(compoundCRS = parseCompdCS(element));
/*      */         } 
/*  230 */         if ("FITTED_CS".equals(keyword))
/*  230 */           return (CoordinateReferenceSystem)(derivedCRS = parseFittedCS(element)); 
/*      */       } finally {
/*  233 */         assert isValid(derivedCRS, keyword) : element;
/*      */       } 
/*      */     } 
/*  236 */     throw element.parseFailed(null, Errors.format(187, key));
/*      */   }
/*      */   
/*      */   protected Object parse(Element element) throws ParseException {
/*  252 */     Object key = element.peek();
/*  253 */     if (key instanceof Element) {
/*  254 */       String keyword = ((Element)key).keyword.trim().toUpperCase(this.symbols.locale);
/*  255 */       Object r = null;
/*      */       try {
/*  257 */         if ("AXIS".equals(keyword))
/*  257 */           return r = parseAxis(element, SI.METER, true); 
/*  258 */         if ("PRIMEM".equals(keyword))
/*  258 */           return r = parsePrimem(element, NonSI.DEGREE_ANGLE); 
/*  259 */         if ("TOWGS84".equals(keyword))
/*  259 */           return r = parseToWGS84(element); 
/*  260 */         if ("SPHEROID".equals(keyword))
/*  260 */           return r = parseSpheroid(element); 
/*  261 */         if ("VERT_DATUM".equals(keyword))
/*  261 */           return r = parseVertDatum(element); 
/*  262 */         if ("LOCAL_DATUM".equals(keyword))
/*  262 */           return r = parseLocalDatum(element); 
/*  263 */         if ("DATUM".equals(keyword))
/*  263 */           return r = parseDatum(element, (PrimeMeridian)DefaultPrimeMeridian.GREENWICH); 
/*  264 */         r = parseMathTransform(element, false);
/*  265 */         if (r != null)
/*  266 */           return r; 
/*      */       } finally {
/*  270 */         assert isValid(r, keyword) : element;
/*      */       } 
/*      */     } 
/*  273 */     return parseCoordinateReferenceSystem(element);
/*      */   }
/*      */   
/*      */   private static boolean isValid(Object parsed, String keyword) {
/*  281 */     if (parsed == null)
/*  283 */       return true; 
/*  285 */     Class<?> type = getClassOf(keyword);
/*  286 */     return (type != null && type.isInstance(parsed));
/*      */   }
/*      */   
/*      */   protected Map<String, Object> alterProperties(Map<String, Object> properties) {
/*  310 */     return properties;
/*      */   }
/*      */   
/*      */   private Map<String, Object> parseAuthority(Element parent, String name) throws ParseException {
/*      */     Map<String, Object> properties;
/*  333 */     boolean isRoot = parent.isRoot();
/*  334 */     Element element = parent.pullOptionalElement("AUTHORITY");
/*  336 */     if (element == null) {
/*  337 */       if (isRoot) {
/*  338 */         properties = new HashMap<String, Object>(4);
/*  339 */         properties.put("name", name);
/*      */       } else {
/*  341 */         properties = Collections.singletonMap("name", name);
/*      */       } 
/*      */     } else {
/*  344 */       String auth = element.pullString("name");
/*  346 */       String code = element.pullOptionalString("code");
/*  347 */       if (code == null) {
/*  348 */         int codeNumber = element.pullInteger("code");
/*  349 */         code = String.valueOf(codeNumber);
/*      */       } 
/*  351 */       element.close();
/*  352 */       Citation authority = Citations.fromName(auth);
/*  353 */       properties = new HashMap<String, Object>(4);
/*  354 */       properties.put("name", new NamedIdentifier(authority, name));
/*  355 */       properties.put("identifiers", new NamedIdentifier(authority, code));
/*      */     } 
/*  357 */     if (isRoot)
/*  358 */       properties = alterProperties(properties); 
/*  360 */     return properties;
/*      */   }
/*      */   
/*      */   private <T extends javax.measure.quantity.Quantity> Unit<T> parseUnit(Element parent, Unit<T> unit) throws ParseException {
/*  382 */     Element element = parent.pullElement("UNIT");
/*  383 */     String name = element.pullString("name");
/*  384 */     double factor = element.pullDouble("factor");
/*  385 */     Map<String, ?> properties = parseAuthority(element, name);
/*  386 */     element.close();
/*  387 */     return (factor != 1.0D) ? unit.times(factor) : unit;
/*      */   }
/*      */   
/*      */   private CoordinateSystemAxis parseAxis(Element parent, Unit<?> unit, boolean required) throws ParseException {
/*      */     Element element;
/*      */     AxisDirection direction;
/*  417 */     if (required) {
/*  418 */       element = parent.pullElement("AXIS");
/*      */     } else {
/*  420 */       element = parent.pullOptionalElement("AXIS");
/*  421 */       if (element == null)
/*  422 */         return null; 
/*      */     } 
/*  425 */     String name = element.pullString("name");
/*  426 */     Element orientation = element.pullOptionalVoidElement();
/*  428 */     if (orientation != null) {
/*  429 */       direction = this.directions.get(orientation.keyword.trim().toUpperCase());
/*      */     } else {
/*  431 */       String directionName = element.pullString("orientation");
/*  432 */       direction = DirectionAlongMeridian.parse(directionName).getDirection();
/*      */     } 
/*  434 */     Map<String, ?> properties = parseAuthority(element, name);
/*  435 */     element.close();
/*  437 */     if (direction == null)
/*  438 */       throw element.parseFailed(null, Errors.format(187, orientation)); 
/*      */     try {
/*  441 */       return createAxis(properties, name, direction, unit);
/*  442 */     } catch (FactoryException exception) {
/*  443 */       throw element.parseFailed(exception, null);
/*      */     } 
/*      */   }
/*      */   
/*      */   private CoordinateSystemAxis createAxis(Map<String, ?> properties, String abbreviation, AxisDirection direction, Unit<?> unit) throws FactoryException {
/*  465 */     DefaultCoordinateSystemAxis defaultCoordinateSystemAxis = DefaultCoordinateSystemAxis.getPredefined(abbreviation, direction);
/*  467 */     if (defaultCoordinateSystemAxis != null && unit.equals(defaultCoordinateSystemAxis.getUnit()))
/*  468 */       return (CoordinateSystemAxis)defaultCoordinateSystemAxis; 
/*  470 */     if (properties == null)
/*  471 */       properties = Collections.singletonMap("name", abbreviation); 
/*  473 */     return this.csFactory.createCoordinateSystemAxis(properties, abbreviation, direction, unit);
/*      */   }
/*      */   
/*      */   private PrimeMeridian parsePrimem(Element parent, Unit<Angle> angularUnit) throws ParseException {
/*  491 */     Element element = parent.pullElement("PRIMEM");
/*  492 */     String name = element.pullString("name");
/*  493 */     double longitude = element.pullDouble("longitude");
/*  494 */     Map<String, ?> properties = parseAuthority(element, name);
/*  495 */     element.close();
/*      */     try {
/*  497 */       return this.datumFactory.createPrimeMeridian(properties, longitude, angularUnit);
/*  498 */     } catch (FactoryException exception) {
/*  499 */       throw element.parseFailed(exception, null);
/*      */     } 
/*      */   }
/*      */   
/*      */   private static BursaWolfParameters parseToWGS84(Element parent) throws ParseException {
/*  519 */     Element element = parent.pullOptionalElement("TOWGS84");
/*  520 */     if (element == null)
/*  521 */       return null; 
/*  523 */     BursaWolfParameters info = new BursaWolfParameters((GeodeticDatum)DefaultGeodeticDatum.WGS84);
/*  524 */     info.dx = element.pullDouble("dx");
/*  525 */     info.dy = element.pullDouble("dy");
/*  526 */     info.dz = element.pullDouble("dz");
/*  527 */     if (element.peek() != null) {
/*  528 */       info.ex = element.pullDouble("ex");
/*  529 */       info.ey = element.pullDouble("ey");
/*  530 */       info.ez = element.pullDouble("ez");
/*  531 */       info.ppm = element.pullDouble("ppm");
/*      */     } 
/*  533 */     element.close();
/*  534 */     return info;
/*      */   }
/*      */   
/*      */   private Ellipsoid parseSpheroid(Element parent) throws ParseException {
/*  549 */     Element element = parent.pullElement("SPHEROID");
/*  550 */     String name = element.pullString("name");
/*  551 */     double semiMajorAxis = element.pullDouble("semiMajorAxis");
/*  552 */     double inverseFlattening = element.pullDouble("inverseFlattening");
/*  553 */     Map<String, ?> properties = parseAuthority(element, name);
/*  554 */     element.close();
/*  555 */     if (inverseFlattening == 0.0D)
/*  557 */       inverseFlattening = Double.POSITIVE_INFINITY; 
/*      */     try {
/*  560 */       return this.datumFactory.createFlattenedSphere(properties, semiMajorAxis, inverseFlattening, SI.METER);
/*  562 */     } catch (FactoryException exception) {
/*  563 */       throw element.parseFailed(exception, null);
/*      */     } 
/*      */   }
/*      */   
/*      */   private ParameterValueGroup parseProjection(Element parent, Ellipsoid ellipsoid, Unit<Length> linearUnit, Unit<Angle> angularUnit) throws ParseException {
/*      */     ParameterValueGroup parameters;
/*  587 */     Element element = parent.pullElement("PROJECTION");
/*  588 */     String classification = element.pullString("name");
/*  589 */     Map<String, ?> properties = parseAuthority(element, classification);
/*  590 */     element.close();
/*      */     try {
/*  603 */       parameters = this.mtFactory.getDefaultParameters(classification);
/*  604 */     } catch (NoSuchIdentifierException exception) {
/*  605 */       throw element.parseFailed(exception, null);
/*      */     } 
/*  607 */     Element param = parent;
/*      */     try {
/*  609 */       if (ellipsoid != null) {
/*  610 */         Unit<Length> axisUnit = ellipsoid.getAxisUnit();
/*  611 */         parameters.parameter("semi_major").setValue(ellipsoid.getSemiMajorAxis(), axisUnit);
/*  612 */         parameters.parameter("semi_minor").setValue(ellipsoid.getSemiMinorAxis(), axisUnit);
/*      */       } 
/*  614 */       while ((param = parent.pullOptionalElement("PARAMETER")) != null) {
/*  615 */         String paramName = param.pullString("name");
/*  616 */         double paramValue = param.pullDouble("value");
/*  617 */         ParameterValue<?> parameter = parameters.parameter(paramName);
/*  618 */         Unit<?> expected = parameter.getDescriptor().getUnit();
/*  619 */         if (expected != null && !Unit.ONE.equals(expected)) {
/*  620 */           if (linearUnit != null && SI.METER.isCompatible(expected)) {
/*  621 */             parameter.setValue(paramValue, linearUnit);
/*      */             continue;
/*      */           } 
/*  624 */           if (angularUnit != null && SI.RADIAN.isCompatible(expected)) {
/*  625 */             parameter.setValue(paramValue, angularUnit);
/*      */             continue;
/*      */           } 
/*      */         } 
/*  629 */         parameter.setValue(paramValue);
/*      */       } 
/*  631 */     } catch (ParameterNotFoundException exception) {
/*  632 */       throw param.parseFailed(exception, Errors.format(176, exception.getParameterName()));
/*      */     } 
/*  635 */     return parameters;
/*      */   }
/*      */   
/*      */   private GeodeticDatum parseDatum(Element parent, PrimeMeridian meridian) throws ParseException {
/*  654 */     Element element = parent.pullElement("DATUM");
/*  655 */     String name = element.pullString("name");
/*  656 */     Ellipsoid ellipsoid = parseSpheroid(element);
/*  657 */     BursaWolfParameters toWGS84 = parseToWGS84(element);
/*  658 */     Map<String, Object> properties = parseAuthority(element, name);
/*  659 */     if (toWGS84 == null && element.peek() instanceof Number) {
/*  660 */       toWGS84 = new BursaWolfParameters((GeodeticDatum)DefaultGeodeticDatum.WGS84);
/*  661 */       toWGS84.dx = element.pullDouble("dx");
/*  662 */       toWGS84.dy = element.pullDouble("dy");
/*  663 */       toWGS84.dz = element.pullDouble("dz");
/*  664 */       toWGS84.ex = element.pullDouble("ex");
/*  665 */       toWGS84.ey = element.pullDouble("ey");
/*  666 */       toWGS84.ez = element.pullDouble("ez");
/*  667 */       toWGS84.ppm = element.pullDouble("ppm");
/*      */     } 
/*  669 */     element.close();
/*  670 */     if (toWGS84 != null) {
/*  671 */       if (!(properties instanceof HashMap))
/*  672 */         properties = new HashMap<String, Object>(properties); 
/*  674 */       properties.put("bursaWolf", toWGS84);
/*      */     } 
/*      */     try {
/*  677 */       return this.datumFactory.createGeodeticDatum(properties, ellipsoid, meridian);
/*  678 */     } catch (FactoryException exception) {
/*  679 */       throw element.parseFailed(exception, null);
/*      */     } 
/*      */   }
/*      */   
/*      */   private VerticalDatum parseVertDatum(Element parent) throws ParseException {
/*  695 */     Element element = parent.pullElement("VERT_DATUM");
/*  696 */     String name = element.pullString("name");
/*  697 */     int datum = element.pullInteger("datum");
/*  698 */     Map<String, ?> properties = parseAuthority(element, name);
/*  699 */     element.close();
/*  700 */     VerticalDatumType type = DefaultVerticalDatum.getVerticalDatumTypeFromLegacyCode(datum);
/*  701 */     if (type == null)
/*  702 */       throw element.parseFailed(null, Errors.format(187, Integer.valueOf(datum))); 
/*      */     try {
/*  705 */       return this.datumFactory.createVerticalDatum(properties, type);
/*  706 */     } catch (FactoryException exception) {
/*  707 */       throw element.parseFailed(exception, null);
/*      */     } 
/*      */   }
/*      */   
/*      */   private EngineeringDatum parseLocalDatum(Element parent) throws ParseException {
/*  725 */     Element element = parent.pullElement("LOCAL_DATUM");
/*  726 */     String name = element.pullString("name");
/*  727 */     int datum = element.pullInteger("datum");
/*  728 */     Map<String, ?> properties = parseAuthority(element, name);
/*  729 */     element.close();
/*      */     try {
/*  731 */       return this.datumFactory.createEngineeringDatum(properties);
/*  732 */     } catch (FactoryException exception) {
/*  733 */       throw element.parseFailed(exception, null);
/*      */     } 
/*      */   }
/*      */   
/*      */   private EngineeringCRS parseLocalCS(Element parent) throws ParseException {
/*      */     // Byte code:
/*      */     //   0: aload_1
/*      */     //   1: ldc 'LOCAL_CS'
/*      */     //   3: invokevirtual pullElement : (Ljava/lang/String;)Lorg/geotools/referencing/wkt/Element;
/*      */     //   6: astore_2
/*      */     //   7: aload_2
/*      */     //   8: ldc 'name'
/*      */     //   10: invokevirtual pullString : (Ljava/lang/String;)Ljava/lang/String;
/*      */     //   13: astore_3
/*      */     //   14: aload_0
/*      */     //   15: aload_2
/*      */     //   16: invokespecial parseLocalDatum : (Lorg/geotools/referencing/wkt/Element;)Lorg/opengis/referencing/datum/EngineeringDatum;
/*      */     //   19: astore #4
/*      */     //   21: aload_0
/*      */     //   22: aload_2
/*      */     //   23: getstatic javax/measure/unit/SI.METER : Ljavax/measure/unit/Unit;
/*      */     //   26: invokespecial parseUnit : (Lorg/geotools/referencing/wkt/Element;Ljavax/measure/unit/Unit;)Ljavax/measure/unit/Unit;
/*      */     //   29: astore #5
/*      */     //   31: aload_0
/*      */     //   32: aload_2
/*      */     //   33: aload #5
/*      */     //   35: iconst_1
/*      */     //   36: invokespecial parseAxis : (Lorg/geotools/referencing/wkt/Element;Ljavax/measure/unit/Unit;Z)Lorg/opengis/referencing/cs/CoordinateSystemAxis;
/*      */     //   39: astore #6
/*      */     //   41: new java/util/ArrayList
/*      */     //   44: dup
/*      */     //   45: invokespecial <init> : ()V
/*      */     //   48: astore #7
/*      */     //   50: aload #7
/*      */     //   52: aload #6
/*      */     //   54: invokeinterface add : (Ljava/lang/Object;)Z
/*      */     //   59: pop
/*      */     //   60: aload_0
/*      */     //   61: aload_2
/*      */     //   62: aload #5
/*      */     //   64: iconst_0
/*      */     //   65: invokespecial parseAxis : (Lorg/geotools/referencing/wkt/Element;Ljavax/measure/unit/Unit;Z)Lorg/opengis/referencing/cs/CoordinateSystemAxis;
/*      */     //   68: astore #6
/*      */     //   70: aload #6
/*      */     //   72: ifnonnull -> 50
/*      */     //   75: aload_0
/*      */     //   76: aload_2
/*      */     //   77: aload_3
/*      */     //   78: invokespecial parseAuthority : (Lorg/geotools/referencing/wkt/Element;Ljava/lang/String;)Ljava/util/Map;
/*      */     //   81: astore #8
/*      */     //   83: aload_2
/*      */     //   84: invokevirtual close : ()V
/*      */     //   87: new org/geotools/referencing/cs/AbstractCS
/*      */     //   90: dup
/*      */     //   91: ldc 'name'
/*      */     //   93: aload_3
/*      */     //   94: invokestatic singletonMap : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/util/Map;
/*      */     //   97: aload #7
/*      */     //   99: aload #7
/*      */     //   101: invokeinterface size : ()I
/*      */     //   106: anewarray org/opengis/referencing/cs/CoordinateSystemAxis
/*      */     //   109: invokeinterface toArray : ([Ljava/lang/Object;)[Ljava/lang/Object;
/*      */     //   114: checkcast [Lorg/opengis/referencing/cs/CoordinateSystemAxis;
/*      */     //   117: invokespecial <init> : (Ljava/util/Map;[Lorg/opengis/referencing/cs/CoordinateSystemAxis;)V
/*      */     //   120: astore #9
/*      */     //   122: aload_0
/*      */     //   123: getfield crsFactory : Lorg/opengis/referencing/crs/CRSFactory;
/*      */     //   126: aload #8
/*      */     //   128: aload #4
/*      */     //   130: aload #9
/*      */     //   132: invokeinterface createEngineeringCRS : (Ljava/util/Map;Lorg/opengis/referencing/datum/EngineeringDatum;Lorg/opengis/referencing/cs/CoordinateSystem;)Lorg/opengis/referencing/crs/EngineeringCRS;
/*      */     //   137: areturn
/*      */     //   138: astore #10
/*      */     //   140: aload_2
/*      */     //   141: aload #10
/*      */     //   143: aconst_null
/*      */     //   144: invokevirtual parseFailed : (Ljava/lang/Exception;Ljava/lang/String;)Ljava/text/ParseException;
/*      */     //   147: athrow
/*      */     // Line number table:
/*      */     //   Java source line number -> byte code offset
/*      */     //   #754	-> 0
/*      */     //   #755	-> 7
/*      */     //   #756	-> 14
/*      */     //   #757	-> 21
/*      */     //   #758	-> 31
/*      */     //   #759	-> 41
/*      */     //   #761	-> 50
/*      */     //   #762	-> 60
/*      */     //   #763	-> 70
/*      */     //   #764	-> 75
/*      */     //   #765	-> 83
/*      */     //   #767	-> 87
/*      */     //   #770	-> 122
/*      */     //   #771	-> 138
/*      */     //   #772	-> 140
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	descriptor
/*      */     //   140	8	10	exception	Lorg/opengis/referencing/FactoryException;
/*      */     //   0	148	0	this	Lorg/geotools/referencing/wkt/Parser;
/*      */     //   0	148	1	parent	Lorg/geotools/referencing/wkt/Element;
/*      */     //   7	141	2	element	Lorg/geotools/referencing/wkt/Element;
/*      */     //   14	134	3	name	Ljava/lang/String;
/*      */     //   21	127	4	datum	Lorg/opengis/referencing/datum/EngineeringDatum;
/*      */     //   31	117	5	linearUnit	Ljavax/measure/unit/Unit;
/*      */     //   41	107	6	axis	Lorg/opengis/referencing/cs/CoordinateSystemAxis;
/*      */     //   50	98	7	list	Ljava/util/List;
/*      */     //   83	65	8	properties	Ljava/util/Map;
/*      */     //   122	26	9	cs	Lorg/opengis/referencing/cs/CoordinateSystem;
/*      */     // Local variable type table:
/*      */     //   start	length	slot	name	signature
/*      */     //   31	117	5	linearUnit	Ljavax/measure/unit/Unit<Ljavax/measure/quantity/Length;>;
/*      */     //   50	98	7	list	Ljava/util/List<Lorg/opengis/referencing/cs/CoordinateSystemAxis;>;
/*      */     //   83	65	8	properties	Ljava/util/Map<Ljava/lang/String;*>;
/*      */     // Exception table:
/*      */     //   from	to	target	type
/*      */     //   122	137	138	org/opengis/referencing/FactoryException
/*      */   }
/*      */   
/*      */   private GeocentricCRS parseGeoCCS(Element parent) throws ParseException {
/*  790 */     Element element = parent.pullElement("GEOCCS");
/*  791 */     String name = element.pullString("name");
/*  792 */     Map<String, ?> properties = parseAuthority(element, name);
/*  793 */     PrimeMeridian meridian = parsePrimem(element, NonSI.DEGREE_ANGLE);
/*  794 */     GeodeticDatum datum = parseDatum(element, meridian);
/*  795 */     Unit<Length> linearUnit = parseUnit(element, SI.METER);
/*  797 */     CoordinateSystemAxis axis0 = parseAxis(element, linearUnit, false);
/*      */     try {
/*      */       CoordinateSystemAxis axis1, axis2;
/*  799 */       if (axis0 != null) {
/*  800 */         axis1 = parseAxis(element, linearUnit, true);
/*  801 */         axis2 = parseAxis(element, linearUnit, true);
/*      */       } else {
/*  804 */         axis0 = createAxis((Map<String, ?>)null, "X", AxisDirection.OTHER, linearUnit);
/*  805 */         axis1 = createAxis((Map<String, ?>)null, "Y", AxisDirection.EAST, linearUnit);
/*  806 */         axis2 = createAxis((Map<String, ?>)null, "Z", AxisDirection.NORTH, linearUnit);
/*      */       } 
/*  808 */       element.close();
/*  809 */       return this.crsFactory.createGeocentricCRS(properties, datum, this.csFactory.createCartesianCS(properties, axis0, axis1, axis2));
/*  811 */     } catch (FactoryException exception) {
/*  812 */       throw element.parseFailed(exception, null);
/*      */     } 
/*      */   }
/*      */   
/*      */   private VerticalCRS parseVertCS(Element parent) throws ParseException {
/*  829 */     Element element = parent.pullElement("VERT_CS");
/*  830 */     if (element == null)
/*  831 */       return null; 
/*  833 */     String name = element.pullString("name");
/*  834 */     VerticalDatum datum = parseVertDatum(element);
/*  835 */     Unit<Length> linearUnit = parseUnit(element, SI.METER);
/*  836 */     CoordinateSystemAxis axis = parseAxis(element, linearUnit, false);
/*  837 */     Map<String, ?> properties = parseAuthority(element, name);
/*  838 */     element.close();
/*      */     try {
/*  840 */       if (axis == null)
/*  841 */         axis = createAxis((Map<String, ?>)null, "Z", AxisDirection.UP, linearUnit); 
/*  843 */       return this.crsFactory.createVerticalCRS(properties, datum, this.csFactory.createVerticalCS(Collections.singletonMap("name", name), axis));
/*  845 */     } catch (FactoryException exception) {
/*  846 */       throw element.parseFailed(exception, null);
/*      */     } 
/*      */   }
/*      */   
/*      */   private GeographicCRS parseGeoGCS(Element parent) throws ParseException {
/*  862 */     Element element = parent.pullElement("GEOGCS");
/*  863 */     String name = element.pullString("name");
/*  864 */     Map<String, ?> properties = parseAuthority(element, name);
/*  865 */     Unit<Angle> angularUnit = parseUnit(element, (Unit<Angle>)SI.RADIAN);
/*  866 */     PrimeMeridian meridian = parsePrimem(element, angularUnit);
/*  867 */     GeodeticDatum datum = parseDatum(element, meridian);
/*  868 */     CoordinateSystemAxis axis0 = parseAxis(element, angularUnit, false);
/*  870 */     CoordinateSystemAxis axis2 = null;
/*      */     try {
/*      */       CoordinateSystemAxis axis1;
/*      */       EllipsoidalCS ellipsoidalCS;
/*  872 */       if (axis0 != null) {
/*  873 */         axis1 = parseAxis(element, angularUnit, true);
/*  874 */         if (axis1 != null)
/*  875 */           axis2 = parseAxis(element, SI.METER, false); 
/*      */       } else {
/*  879 */         axis0 = createAxis((Map<String, ?>)null, "Lon", AxisDirection.EAST, angularUnit);
/*  880 */         axis1 = createAxis((Map<String, ?>)null, "Lat", AxisDirection.NORTH, angularUnit);
/*      */       } 
/*  882 */       element.close();
/*  884 */       if (axis2 != null) {
/*  885 */         ellipsoidalCS = this.csFactory.createEllipsoidalCS(properties, axis0, axis1, axis2);
/*      */       } else {
/*  887 */         ellipsoidalCS = this.csFactory.createEllipsoidalCS(properties, axis0, axis1);
/*      */       } 
/*  889 */       return this.crsFactory.createGeographicCRS(properties, datum, ellipsoidalCS);
/*  891 */     } catch (FactoryException exception) {
/*  892 */       throw element.parseFailed(exception, null);
/*      */     } 
/*      */   }
/*      */   
/*      */   private ProjectedCRS parseProjCS(Element parent) throws ParseException {
/*  910 */     Element element = parent.pullElement("PROJCS");
/*  911 */     String name = element.pullString("name");
/*  912 */     Map<String, ?> properties = parseAuthority(element, name);
/*  913 */     GeographicCRS geoCRS = parseGeoGCS(element);
/*  914 */     Ellipsoid ellipsoid = geoCRS.getDatum().getEllipsoid();
/*  915 */     Unit<Length> linearUnit = parseUnit(element, SI.METER);
/*  916 */     Unit<Angle> angularUnit = geoCRS.getCoordinateSystem().getAxis(0).getUnit().asType(Angle.class);
/*  917 */     ParameterValueGroup projection = parseProjection(element, ellipsoid, linearUnit, angularUnit);
/*  918 */     CoordinateSystemAxis axis0 = parseAxis(element, linearUnit, false);
/*      */     try {
/*      */       CoordinateSystemAxis axis1;
/*  921 */       if (axis0 != null) {
/*  922 */         axis1 = parseAxis(element, linearUnit, true);
/*      */       } else {
/*  925 */         axis0 = createAxis((Map<String, ?>)null, "X", AxisDirection.EAST, linearUnit);
/*  926 */         axis1 = createAxis((Map<String, ?>)null, "Y", AxisDirection.NORTH, linearUnit);
/*      */       } 
/*  928 */       element.close();
/*  929 */       DefiningConversion definingConversion = new DefiningConversion(name, projection);
/*  930 */       return this.crsFactory.createProjectedCRS(properties, geoCRS, (Conversion)definingConversion, this.csFactory.createCartesianCS(properties, axis0, axis1));
/*  932 */     } catch (FactoryException exception) {
/*  933 */       throw element.parseFailed(exception, null);
/*      */     } 
/*      */   }
/*      */   
/*      */   private CompoundCRS parseCompdCS(Element parent) throws ParseException {
/*  950 */     CoordinateReferenceSystem[] CRS = new CoordinateReferenceSystem[2];
/*  951 */     Element element = parent.pullElement("COMPD_CS");
/*  952 */     String name = element.pullString("name");
/*  953 */     Map<String, ?> properties = parseAuthority(element, name);
/*  954 */     CRS[0] = parseCoordinateReferenceSystem(element);
/*  955 */     CRS[1] = parseCoordinateReferenceSystem(element);
/*  956 */     element.close();
/*      */     try {
/*  958 */       return this.crsFactory.createCompoundCRS(properties, CRS);
/*  959 */     } catch (FactoryException exception) {
/*  960 */       throw element.parseFailed(exception, null);
/*      */     } 
/*      */   }
/*      */   
/*      */   private DerivedCRS parseFittedCS(Element parent) throws ParseException {
/*  977 */     Element element = parent.pullElement("FITTED_CS");
/*  978 */     String name = element.pullString("name");
/*  979 */     Map<String, ?> properties = parseAuthority(element, name);
/*  980 */     MathTransform toBase = parseMathTransform(element, true);
/*  981 */     CoordinateReferenceSystem base = parseCoordinateReferenceSystem(element);
/*  982 */     OperationMethod method = getOperationMethod();
/*  983 */     element.close();
/*  990 */     CoordinateSystemAxis[] axis = new CoordinateSystemAxis[toBase.getSourceDimensions()];
/*  991 */     StringBuilder buffer = new StringBuilder(name);
/*  992 */     buffer.append(" axis ");
/*  993 */     int start = buffer.length();
/*      */     try {
/*  995 */       for (int i = 0; i < axis.length; i++) {
/*  996 */         String number = String.valueOf(i);
/*  997 */         buffer.setLength(start);
/*  998 */         buffer.append(number);
/*  999 */         axis[i] = this.csFactory.createCoordinateSystemAxis(Collections.singletonMap("name", buffer.toString()), number, AxisDirection.OTHER, Unit.ONE);
/*      */       } 
/* 1003 */       DefiningConversion definingConversion = new DefiningConversion(Collections.singletonMap("name", method.getName().getCode()), method, toBase.inverse());
/* 1006 */       AbstractCS abstractCS = new AbstractCS(properties, axis);
/* 1007 */       return this.crsFactory.createDerivedCRS(properties, base, (Conversion)definingConversion, (CoordinateSystem)abstractCS);
/* 1008 */     } catch (FactoryException exception) {
/* 1009 */       throw element.parseFailed(exception, null);
/* 1010 */     } catch (NoninvertibleTransformException exception) {
/* 1011 */       throw element.parseFailed(exception, null);
/*      */     } 
/*      */   }
/*      */   
/*      */   public static Class<?> getClassOf(String element) {
/* 1023 */     if (element == null)
/* 1024 */       return null; 
/* 1026 */     element = element.trim().toUpperCase(Locale.US);
/* 1027 */     Class<?> type = getTypeMap().get(element);
/* 1029 */     assert type == null || type.equals(MathTransform.class) || element.equals(getNameOf(type)) : type;
/* 1030 */     return type;
/*      */   }
/*      */   
/*      */   public static String getNameOf(Class<?> type) {
/* 1043 */     if (type != null)
/* 1044 */       for (Map.Entry<String, Class<?>> entry : getTypeMap().entrySet()) {
/* 1045 */         Class<?> candidate = entry.getValue();
/* 1046 */         if (candidate.isAssignableFrom(type))
/* 1047 */           return entry.getKey(); 
/*      */       }  
/* 1051 */     return null;
/*      */   }
/*      */   
/*      */   private static Map<String, Class<?>> getTypeMap() {
/* 1058 */     if (TYPES == null) {
/* 1059 */       Map<String, Class<?>> map = new LinkedHashMap<String, Class<?>>(25);
/* 1060 */       map.put("GEOGCS", GeographicCRS.class);
/* 1061 */       map.put("PROJCS", ProjectedCRS.class);
/* 1062 */       map.put("GEOCCS", GeocentricCRS.class);
/* 1063 */       map.put("VERT_CS", VerticalCRS.class);
/* 1064 */       map.put("LOCAL_CS", EngineeringCRS.class);
/* 1065 */       map.put("COMPD_CS", CompoundCRS.class);
/* 1066 */       map.put("FITTED_CS", DerivedCRS.class);
/* 1067 */       map.put("AXIS", CoordinateSystemAxis.class);
/* 1068 */       map.put("PRIMEM", PrimeMeridian.class);
/* 1069 */       map.put("TOWGS84", BursaWolfParameters.class);
/* 1070 */       map.put("SPHEROID", Ellipsoid.class);
/* 1071 */       map.put("VERT_DATUM", VerticalDatum.class);
/* 1072 */       map.put("LOCAL_DATUM", EngineeringDatum.class);
/* 1073 */       map.put("DATUM", GeodeticDatum.class);
/* 1074 */       map.put("PARAM_MT", MathTransform.class);
/* 1075 */       map.put("CONCAT_MT", MathTransform.class);
/* 1076 */       map.put("INVERSE_MT", MathTransform.class);
/* 1077 */       map.put("PASSTHROUGH_MT", MathTransform.class);
/* 1078 */       TYPES = map;
/*      */     } 
/* 1081 */     return TYPES;
/*      */   }
/*      */   
/*      */   public static void main(String[] args) {
/* 1105 */     Arguments arguments = new Arguments(args);
/* 1106 */     Integer indentation = arguments.getOptionalInteger("Indentation");
/* 1107 */     String authority = arguments.getOptionalString("-authority");
/* 1108 */     args = arguments.getRemainingArguments(0);
/* 1109 */     if (indentation != null)
/* 1110 */       Formattable.setIndentation(indentation.intValue()); 
/* 1112 */     BufferedReader in = new BufferedReader(Arguments.getReader(System.in));
/*      */     try {
/* 1114 */       Parser parser = new Parser();
/* 1115 */       if (authority != null)
/* 1116 */         parser.setAuthority(Citations.fromName(authority)); 
/* 1118 */       parser.reformat(in, arguments.out, arguments.err);
/* 1119 */     } catch (Exception exception) {
/* 1120 */       exception.printStackTrace(arguments.err);
/*      */     } 
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\wkt\Parser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */