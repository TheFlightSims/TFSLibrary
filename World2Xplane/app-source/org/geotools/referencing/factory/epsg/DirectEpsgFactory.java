/*      */ package org.geotools.referencing.factory.epsg;
/*      */ 
/*      */ import java.awt.RenderingHints;
/*      */ import java.io.File;
/*      */ import java.lang.ref.Reference;
/*      */ import java.lang.ref.SoftReference;
/*      */ import java.lang.ref.WeakReference;
/*      */ import java.net.URI;
/*      */ import java.net.URISyntaxException;
/*      */ import java.sql.Connection;
/*      */ import java.sql.DatabaseMetaData;
/*      */ import java.sql.Date;
/*      */ import java.sql.PreparedStatement;
/*      */ import java.sql.ResultSet;
/*      */ import java.sql.ResultSetMetaData;
/*      */ import java.sql.SQLException;
/*      */ import java.sql.Statement;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Calendar;
/*      */ import java.util.Collection;
/*      */ import java.util.Collections;
/*      */ import java.util.HashMap;
/*      */ import java.util.HashSet;
/*      */ import java.util.IdentityHashMap;
/*      */ import java.util.Iterator;
/*      */ import java.util.LinkedHashMap;
/*      */ import java.util.LinkedHashSet;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.NoSuchElementException;
/*      */ import java.util.Set;
/*      */ import java.util.logging.Level;
/*      */ import java.util.logging.LogRecord;
/*      */ import javax.measure.unit.NonSI;
/*      */ import javax.measure.unit.SI;
/*      */ import javax.measure.unit.Unit;
/*      */ import javax.sql.DataSource;
/*      */ import org.geotools.factory.Hints;
/*      */ import org.geotools.io.TableWriter;
/*      */ import org.geotools.measure.Units;
/*      */ import org.geotools.metadata.iso.citation.CitationImpl;
/*      */ import org.geotools.metadata.iso.citation.Citations;
/*      */ import org.geotools.metadata.iso.extent.ExtentImpl;
/*      */ import org.geotools.metadata.iso.extent.GeographicBoundingBoxImpl;
/*      */ import org.geotools.metadata.iso.quality.AbsoluteExternalPositionalAccuracyImpl;
/*      */ import org.geotools.metadata.iso.quality.QuantitativeResultImpl;
/*      */ import org.geotools.parameter.DefaultParameterDescriptor;
/*      */ import org.geotools.parameter.DefaultParameterDescriptorGroup;
/*      */ import org.geotools.referencing.AbstractIdentifiedObject;
/*      */ import org.geotools.referencing.NamedIdentifier;
/*      */ import org.geotools.referencing.cs.DefaultCoordinateSystemAxis;
/*      */ import org.geotools.referencing.datum.BursaWolfParameters;
/*      */ import org.geotools.referencing.factory.AbstractAuthorityFactory;
/*      */ import org.geotools.referencing.factory.DirectAuthorityFactory;
/*      */ import org.geotools.referencing.factory.IdentifiedObjectFinder;
/*      */ import org.geotools.referencing.operation.DefaultConcatenatedOperation;
/*      */ import org.geotools.referencing.operation.DefaultOperation;
/*      */ import org.geotools.referencing.operation.DefaultOperationMethod;
/*      */ import org.geotools.referencing.operation.DefiningConversion;
/*      */ import org.geotools.resources.CRSUtilities;
/*      */ import org.geotools.resources.i18n.Errors;
/*      */ import org.geotools.resources.i18n.Loggings;
/*      */ import org.geotools.resources.i18n.Vocabulary;
/*      */ import org.geotools.util.LocalName;
/*      */ import org.geotools.util.NameFactory;
/*      */ import org.geotools.util.ScopedName;
/*      */ import org.geotools.util.SimpleInternationalString;
/*      */ import org.geotools.util.Version;
/*      */ import org.geotools.util.logging.Logging;
/*      */ import org.opengis.metadata.Identifier;
/*      */ import org.opengis.metadata.citation.Citation;
/*      */ import org.opengis.metadata.extent.Extent;
/*      */ import org.opengis.metadata.quality.EvaluationMethodType;
/*      */ import org.opengis.metadata.quality.PositionalAccuracy;
/*      */ import org.opengis.metadata.quality.Result;
/*      */ import org.opengis.parameter.GeneralParameterDescriptor;
/*      */ import org.opengis.parameter.InvalidParameterValueException;
/*      */ import org.opengis.parameter.ParameterDescriptor;
/*      */ import org.opengis.parameter.ParameterDescriptorGroup;
/*      */ import org.opengis.parameter.ParameterNotFoundException;
/*      */ import org.opengis.parameter.ParameterValue;
/*      */ import org.opengis.parameter.ParameterValueGroup;
/*      */ import org.opengis.referencing.AuthorityFactory;
/*      */ import org.opengis.referencing.FactoryException;
/*      */ import org.opengis.referencing.IdentifiedObject;
/*      */ import org.opengis.referencing.NoSuchIdentifierException;
/*      */ import org.opengis.referencing.ReferenceIdentifier;
/*      */ import org.opengis.referencing.crs.CRSAuthorityFactory;
/*      */ import org.opengis.referencing.crs.CRSFactory;
/*      */ import org.opengis.referencing.crs.CompoundCRS;
/*      */ import org.opengis.referencing.crs.CoordinateReferenceSystem;
/*      */ import org.opengis.referencing.crs.EngineeringCRS;
/*      */ import org.opengis.referencing.crs.GeneralDerivedCRS;
/*      */ import org.opengis.referencing.crs.GeocentricCRS;
/*      */ import org.opengis.referencing.crs.GeographicCRS;
/*      */ import org.opengis.referencing.crs.ProjectedCRS;
/*      */ import org.opengis.referencing.crs.SingleCRS;
/*      */ import org.opengis.referencing.crs.VerticalCRS;
/*      */ import org.opengis.referencing.cs.AffineCS;
/*      */ import org.opengis.referencing.cs.AxisDirection;
/*      */ import org.opengis.referencing.cs.CSAuthorityFactory;
/*      */ import org.opengis.referencing.cs.CSFactory;
/*      */ import org.opengis.referencing.cs.CartesianCS;
/*      */ import org.opengis.referencing.cs.CoordinateSystem;
/*      */ import org.opengis.referencing.cs.CoordinateSystemAxis;
/*      */ import org.opengis.referencing.cs.CylindricalCS;
/*      */ import org.opengis.referencing.cs.EllipsoidalCS;
/*      */ import org.opengis.referencing.cs.LinearCS;
/*      */ import org.opengis.referencing.cs.PolarCS;
/*      */ import org.opengis.referencing.cs.SphericalCS;
/*      */ import org.opengis.referencing.cs.VerticalCS;
/*      */ import org.opengis.referencing.datum.Datum;
/*      */ import org.opengis.referencing.datum.DatumAuthorityFactory;
/*      */ import org.opengis.referencing.datum.DatumFactory;
/*      */ import org.opengis.referencing.datum.Ellipsoid;
/*      */ import org.opengis.referencing.datum.EngineeringDatum;
/*      */ import org.opengis.referencing.datum.GeodeticDatum;
/*      */ import org.opengis.referencing.datum.PrimeMeridian;
/*      */ import org.opengis.referencing.datum.VerticalDatum;
/*      */ import org.opengis.referencing.datum.VerticalDatumType;
/*      */ import org.opengis.referencing.operation.ConcatenatedOperation;
/*      */ import org.opengis.referencing.operation.Conversion;
/*      */ import org.opengis.referencing.operation.CoordinateOperation;
/*      */ import org.opengis.referencing.operation.CoordinateOperationAuthorityFactory;
/*      */ import org.opengis.referencing.operation.MathTransform;
/*      */ import org.opengis.referencing.operation.OperationMethod;
/*      */ import org.opengis.referencing.operation.Projection;
/*      */ import org.opengis.referencing.operation.Transformation;
/*      */ import org.opengis.util.GenericName;
/*      */ import org.opengis.util.InternationalString;
/*      */ 
/*      */ public abstract class DirectEpsgFactory extends DirectAuthorityFactory implements CRSAuthorityFactory, CSAuthorityFactory, DatumAuthorityFactory, CoordinateOperationAuthorityFactory {
/*      */   private static final int BURSA_WOLF_MIN_CODE = 9603;
/*      */   
/*      */   private static final int BURSA_WOLF_MAX_CODE = 9607;
/*      */   
/*      */   private static final int ROTATION_FRAME_CODE = 9607;
/*      */   
/*      */   private static final int DUMMY_OPERATION = 1;
/*      */   
/*      */   private static Unit<?> getUnit(int code) {
/*  148 */     switch (code) {
/*      */       case 9001:
/*  149 */         return SI.METER;
/*      */       case 9002:
/*  150 */         return NonSI.FOOT;
/*      */       case 9030:
/*  151 */         return NonSI.NAUTICAL_MILE;
/*      */       case 9036:
/*  152 */         return SI.KILO(SI.METER);
/*      */       case 9101:
/*  153 */         return (Unit<?>)SI.RADIAN;
/*      */       case 9102:
/*      */       case 9122:
/*  155 */         return NonSI.DEGREE_ANGLE;
/*      */       case 9103:
/*  156 */         return NonSI.MINUTE_ANGLE;
/*      */       case 9104:
/*  157 */         return NonSI.SECOND_ANGLE;
/*      */       case 9105:
/*  158 */         return NonSI.GRADE;
/*      */       case 9107:
/*  159 */         return Units.DEGREE_MINUTE_SECOND;
/*      */       case 9108:
/*  160 */         return Units.DEGREE_MINUTE_SECOND;
/*      */       case 9109:
/*  161 */         return SI.MICRO((Unit)SI.RADIAN);
/*      */       case 9110:
/*  162 */         return Units.SEXAGESIMAL_DMS;
/*      */       case 9201:
/*      */       case 9203:
/*  165 */         return Unit.ONE;
/*      */       case 9202:
/*  166 */         return Units.PPM;
/*      */     } 
/*  167 */     return null;
/*      */   }
/*      */   
/*      */   private static void setBursaWolfParameter(BursaWolfParameters parameters, int code, double value, Unit<?> unit) throws FactoryException {
/*  184 */     Unit<?> target = unit;
/*  185 */     if (code >= 8605)
/*  186 */       if (code <= 8607) {
/*  186 */         target = SI.METER;
/*  187 */       } else if (code == 8611) {
/*  187 */         target = Units.PPM;
/*  188 */       } else if (code <= 8710) {
/*  188 */         target = NonSI.SECOND_ANGLE;
/*      */       }  
/*  190 */     if (target != unit)
/*  191 */       value = unit.getConverterTo(target).convert(value); 
/*  193 */     switch (code) {
/*      */       case 8605:
/*  194 */         parameters.dx = value;
/*      */         return;
/*      */       case 8606:
/*  195 */         parameters.dy = value;
/*      */         return;
/*      */       case 8607:
/*  196 */         parameters.dz = value;
/*      */         return;
/*      */       case 8608:
/*  197 */         parameters.ex = value;
/*      */         return;
/*      */       case 8609:
/*  198 */         parameters.ey = value;
/*      */         return;
/*      */       case 8610:
/*  199 */         parameters.ez = value;
/*      */         return;
/*      */       case 8611:
/*  200 */         parameters.ppm = value;
/*      */         return;
/*      */     } 
/*  201 */     throw new FactoryException(Errors.format(176, Integer.valueOf(code)));
/*      */   }
/*      */   
/*  227 */   private static final TableInfo[] TABLES_INFO = new TableInfo[] { new TableInfo(CoordinateReferenceSystem.class, "[Coordinate Reference System]", "COORD_REF_SYS_CODE", "COORD_REF_SYS_NAME", "COORD_REF_SYS_KIND", new Class[] { ProjectedCRS.class, GeographicCRS.class, GeocentricCRS.class }, new String[] { "projected", "geographic", "geocentric" }), new TableInfo(CoordinateSystem.class, "[Coordinate System]", "COORD_SYS_CODE", "COORD_SYS_NAME", "COORD_SYS_TYPE", new Class[] { CartesianCS.class, EllipsoidalCS.class, SphericalCS.class, VerticalCS.class }, new String[] { "Cartesian", "ellipsoidal", "spherical", "vertical" }), new TableInfo(CoordinateSystemAxis.class, "[Coordinate Axis] AS CA INNER JOIN [Coordinate Axis Name] AS CAN ON CA.COORD_AXIS_NAME_CODE=CAN.COORD_AXIS_NAME_CODE", "COORD_AXIS_CODE", "COORD_AXIS_NAME"), new TableInfo(Datum.class, "[Datum]", "DATUM_CODE", "DATUM_NAME", "DATUM_TYPE", new Class[] { GeodeticDatum.class, VerticalDatum.class, EngineeringDatum.class }, new String[] { "geodetic", "vertical", "engineering" }), new TableInfo(Ellipsoid.class, "[Ellipsoid]", "ELLIPSOID_CODE", "ELLIPSOID_NAME"), new TableInfo(PrimeMeridian.class, "[Prime Meridian]", "PRIME_MERIDIAN_CODE", "PRIME_MERIDIAN_NAME"), new TableInfo(CoordinateOperation.class, "[Coordinate_Operation]", "COORD_OP_CODE", "COORD_OP_NAME", "COORD_OP_TYPE", new Class[] { Projection.class, Conversion.class, Transformation.class }, new String[] { "conversion", "conversion", "transformation" }), new TableInfo(OperationMethod.class, "[Coordinate_Operation Method]", "COORD_OP_METHOD_CODE", "COORD_OP_METHOD_NAME"), new TableInfo(ParameterDescriptor.class, "[Coordinate_Operation Parameter]", "PARAMETER_CODE", "PARAMETER_NAME"), new TableInfo(Unit.class, "[Unit of Measure]", "UOM_CODE", "UNIT_OF_MEAS_NAME") };
/*      */   
/*  304 */   private static final InternationalString TRANSFORMATION_ACCURACY = Vocabulary.formatInternational(223);
/*      */   
/*      */   static final String SHUTDOWN_THREAD = "EPSG factory shutdown";
/*      */   
/*      */   private volatile transient Citation authority;
/*      */   
/*  326 */   private int lastObjectType = -1;
/*      */   
/*      */   private transient String lastTableForName;
/*      */   
/*  340 */   private final Calendar calendar = Calendar.getInstance();
/*      */   
/*  351 */   private final Map<String, PreparedStatement> statements = new IdentityHashMap<String, PreparedStatement>();
/*      */   
/*  370 */   private final Map<Class<?>, Reference<AuthorityCodes>> authorityCodes = new HashMap<Class<?>, Reference<AuthorityCodes>>();
/*      */   
/*  379 */   private final Map<String, AxisName> axisNames = new HashMap<String, AxisName>();
/*      */   
/*  387 */   private final Map<String, Short> axisCounts = new HashMap<String, Short>();
/*      */   
/*  395 */   private final Map<String, Boolean> codeProjection = new HashMap<String, Boolean>();
/*      */   
/*  402 */   private final Map<String, LocalName> scopes = new HashMap<String, LocalName>();
/*      */   
/*  408 */   private final Map<String, Object> properties = new HashMap<String, Object>();
/*      */   
/*  416 */   private final Set<String> safetyGuard = new HashSet<String>();
/*      */   
/*  423 */   AbstractAuthorityFactory buffered = (AbstractAuthorityFactory)this;
/*      */   
/*      */   private Connection connection;
/*      */   
/*      */   private DataSource dataSource;
/*      */   
/*      */   private String validationQuery;
/*      */   
/*      */   public DirectEpsgFactory(Hints userHints, Connection connection) {
/*  447 */     this(userHints, new SingleConnectionDataSource(connection));
/*      */   }
/*      */   
/*      */   public DirectEpsgFactory(Hints userHints, DataSource dataSource) {
/*  457 */     super(userHints, 80);
/*  460 */     this.hints.put(Hints.FORCE_LONGITUDE_FIRST_AXIS_ORDER, Boolean.FALSE);
/*  461 */     this.hints.put(Hints.FORCE_STANDARD_AXIS_DIRECTIONS, Boolean.FALSE);
/*  462 */     this.hints.put(Hints.FORCE_STANDARD_AXIS_UNITS, Boolean.FALSE);
/*  463 */     this.dataSource = dataSource;
/*  464 */     ensureNonNull("dataSource", dataSource);
/*      */   }
/*      */   
/*      */   public Citation getAuthority() {
/*  473 */     if (this.authority == null)
/*      */       try {
/*  474 */         synchronized (this) {
/*  475 */           if (this.authority == null) {
/*  477 */             String query = adaptSQL("SELECT VERSION_NUMBER, VERSION_DATE FROM [Version History] ORDER BY VERSION_DATE DESC, VERSION_NUMBER DESC");
/*  479 */             DatabaseMetaData metadata = getConnection().getMetaData();
/*  480 */             Statement statement = getConnection().createStatement();
/*  481 */             ResultSet result = statement.executeQuery(query);
/*  482 */             if (result.next()) {
/*  483 */               String version = result.getString(1);
/*  484 */               Date date = result.getDate(2);
/*  485 */               String engine = metadata.getDatabaseProductName();
/*  486 */               CitationImpl c = new CitationImpl(Citations.EPSG);
/*  487 */               c.getAlternateTitles().add(Vocabulary.formatInternational(37, "EPSG", version, engine));
/*  489 */               c.setEdition((InternationalString)new SimpleInternationalString(version));
/*  490 */               c.setEditionDate(date);
/*  491 */               this.authority = (Citation)c.unmodifiable();
/*  492 */               this.hints.put(Hints.VERSION, new Version(version));
/*      */             } else {
/*  494 */               this.authority = Citations.EPSG;
/*      */             } 
/*  496 */             result.close();
/*  497 */             statement.close();
/*      */           } 
/*      */         } 
/*  500 */       } catch (SQLException exception) {
/*  501 */         Logging.unexpectedException(LOGGER, DirectEpsgFactory.class, "getAuthority", exception);
/*  502 */         return Citations.EPSG;
/*      */       }  
/*  504 */     return this.authority;
/*      */   }
/*      */   
/*      */   public synchronized String getBackingStoreDescription() throws FactoryException {
/*  514 */     Citation authority = getAuthority();
/*  515 */     TableWriter table = new TableWriter(null, " ");
/*  516 */     Vocabulary resources = Vocabulary.getResources(null);
/*      */     InternationalString internationalString;
/*  518 */     if ((internationalString = authority.getEdition()) != null) {
/*  519 */       table.write(resources.getString(239, "EPSG"));
/*  520 */       table.write(58);
/*  521 */       table.nextColumn();
/*  522 */       table.write(internationalString.toString());
/*  523 */       table.nextLine();
/*      */     } 
/*      */     try {
/*  527 */       DatabaseMetaData metadata = getConnection().getMetaData();
/*      */       String s;
/*  528 */       if ((s = metadata.getDatabaseProductName()) != null) {
/*  529 */         table.write(resources.getLabel(35));
/*  530 */         table.nextColumn();
/*  531 */         table.write(s);
/*  532 */         if ((s = metadata.getDatabaseProductVersion()) != null) {
/*  533 */           table.write(32);
/*  534 */           table.write(resources.getString(238, s));
/*      */         } 
/*  536 */         table.nextLine();
/*      */       } 
/*  538 */       if ((s = metadata.getURL()) != null) {
/*  539 */         table.write(resources.getLabel(36));
/*  540 */         table.nextColumn();
/*  541 */         table.write(s);
/*  542 */         table.nextLine();
/*      */       } 
/*  544 */     } catch (SQLException exception) {
/*  545 */       throw new FactoryException(exception);
/*      */     } 
/*  547 */     return table.toString();
/*      */   }
/*      */   
/*      */   public Map<RenderingHints.Key, ?> getImplementationHints() {
/*  557 */     if (this.authority == null)
/*  559 */       getAuthority(); 
/*  561 */     return super.getImplementationHints();
/*      */   }
/*      */   
/*      */   public Set<String> getAuthorityCodes(Class<? extends IdentifiedObject> type) throws FactoryException {
/*  589 */     return getAuthorityCodes0(type);
/*      */   }
/*      */   
/*      */   private synchronized Set<String> getAuthorityCodes0(Class<?> type) throws FactoryException {
/*  601 */     Reference<AuthorityCodes> reference = this.authorityCodes.get(type);
/*  602 */     AuthorityCodes candidate = (reference != null) ? reference.get() : null;
/*  603 */     if (candidate != null)
/*  604 */       return candidate; 
/*  606 */     Set<String> result = Collections.emptySet();
/*  607 */     for (int i = 0; i < TABLES_INFO.length; i++) {
/*  608 */       TableInfo table = TABLES_INFO[i];
/*  623 */       if (table.type.isAssignableFrom(type) || type.isAssignableFrom(table.type)) {
/*      */         boolean cache;
/*  631 */         AuthorityCodes codes = new AuthorityCodes(TABLES_INFO[i], type, this);
/*  632 */         reference = this.authorityCodes.get(codes.type);
/*  633 */         candidate = (reference != null) ? reference.get() : null;
/*  635 */         if (candidate == null) {
/*  636 */           candidate = codes;
/*  637 */           cache = true;
/*      */         } else {
/*  640 */           assert candidate.sqlAll.equals(codes.sqlAll) : codes.type;
/*  641 */           cache = !(reference instanceof SoftReference);
/*      */         } 
/*  643 */         if (cache) {
/*  644 */           reference = new SoftReference<AuthorityCodes>(candidate);
/*  645 */           this.authorityCodes.put(codes.type, reference);
/*      */         } 
/*  652 */         if (result.isEmpty()) {
/*  653 */           result = candidate;
/*      */         } else {
/*  655 */           if (result instanceof AuthorityCodes)
/*  656 */             result = new LinkedHashSet<String>(result); 
/*  658 */           result.addAll(candidate);
/*      */         } 
/*      */       } 
/*      */     } 
/*  662 */     return result;
/*      */   }
/*      */   
/*      */   public InternationalString getDescriptionText(String code) throws FactoryException {
/*  675 */     String primaryKey = trimAuthority(code);
/*  676 */     for (int i = 0; i < TABLES_INFO.length; i++) {
/*  677 */       Set<String> codes = getAuthorityCodes0((TABLES_INFO[i]).type);
/*  678 */       if (codes instanceof AuthorityCodes) {
/*  679 */         String text = ((AuthorityCodes)codes).asMap().get(primaryKey);
/*  680 */         if (text != null)
/*  681 */           return (InternationalString)new SimpleInternationalString(text); 
/*      */       } 
/*      */     } 
/*  689 */     ReferenceIdentifier referenceIdentifier = createObject(code).getName();
/*  690 */     if (referenceIdentifier instanceof GenericName)
/*  691 */       return ((GenericName)referenceIdentifier).toInternationalString(); 
/*  693 */     return (InternationalString)new SimpleInternationalString(referenceIdentifier.getCode());
/*      */   }
/*      */   
/*      */   private PreparedStatement prepareStatement(String key, String sql) throws SQLException {
/*  712 */     assert Thread.holdsLock(this);
/*  713 */     PreparedStatement stmt = this.statements.get(key);
/*  714 */     Connection conn = null;
/*  715 */     if (stmt != null)
/*      */       try {
/*  717 */         conn = stmt.getConnection();
/*  718 */       } catch (SQLException sqle) {
/*  720 */         stmt = null;
/*      */       }  
/*  723 */     if (conn != null && !isConnectionValid(conn))
/*  724 */       stmt = null; 
/*  725 */     if (stmt == null) {
/*  726 */       stmt = getConnection().prepareStatement(adaptSQL(sql));
/*  727 */       this.statements.put(key, stmt);
/*      */     } 
/*  729 */     return stmt;
/*      */   }
/*      */   
/*      */   private static String getString(ResultSet result, int columnIndex, String code) throws SQLException, FactoryException {
/*  747 */     String value = result.getString(columnIndex);
/*  748 */     ensureNonNull(result, columnIndex, code);
/*  749 */     return value.trim();
/*      */   }
/*      */   
/*      */   private static String getString(ResultSet result, int columnIndex, String code, int columnFault) throws SQLException, FactoryException {
/*  760 */     String str = result.getString(columnIndex);
/*  761 */     if (result.wasNull()) {
/*  762 */       ResultSetMetaData metadata = result.getMetaData();
/*  763 */       String column = metadata.getColumnName(columnFault);
/*  764 */       String table = metadata.getTableName(columnFault);
/*  765 */       result.close();
/*  766 */       throw new FactoryException(Errors.format(147, code, column, table));
/*      */     } 
/*  769 */     return str.trim();
/*      */   }
/*      */   
/*      */   private static double getDouble(ResultSet result, int columnIndex, String code) throws SQLException, FactoryException {
/*  787 */     double value = result.getDouble(columnIndex);
/*  788 */     ensureNonNull(result, columnIndex, code);
/*  789 */     return value;
/*      */   }
/*      */   
/*      */   private static int getInt(ResultSet result, int columnIndex, String code) throws SQLException, FactoryException {
/*  807 */     int value = result.getInt(columnIndex);
/*  808 */     ensureNonNull(result, columnIndex, code);
/*  809 */     return value;
/*      */   }
/*      */   
/*      */   private static void ensureNonNull(ResultSet result, int columnIndex, String code) throws SQLException, FactoryException {
/*  819 */     if (result.wasNull()) {
/*  820 */       ResultSetMetaData metadata = result.getMetaData();
/*  821 */       String column = metadata.getColumnName(columnIndex);
/*  822 */       String table = metadata.getTableName(columnIndex);
/*  823 */       result.close();
/*  824 */       throw new FactoryException(Errors.format(147, code, column, table));
/*      */     } 
/*      */   }
/*      */   
/*      */   private String toPrimaryKey(Class type, String code, String table, String codeColumn, String nameColumn) throws SQLException, FactoryException {
/*  854 */     assert Thread.holdsLock(this);
/*  855 */     String identifier = trimAuthority(code);
/*  856 */     if (!isPrimaryKey(identifier)) {
/*  862 */       String KEY = "NumericalIdentifier";
/*  863 */       PreparedStatement statement = this.statements.get("NumericalIdentifier");
/*  864 */       if (statement != null && 
/*  865 */         !table.equals(this.lastTableForName)) {
/*  866 */         this.statements.remove("NumericalIdentifier");
/*  867 */         statement.close();
/*  868 */         statement = null;
/*  869 */         this.lastTableForName = null;
/*      */       } 
/*  872 */       if (statement == null) {
/*  873 */         String query = "SELECT " + codeColumn + " FROM " + table + " WHERE " + nameColumn + " = ?";
/*  875 */         statement = this.connection.prepareStatement(adaptSQL(query));
/*  876 */         this.statements.put("NumericalIdentifier", statement);
/*      */       } 
/*  878 */       statement.setString(1, identifier);
/*  879 */       identifier = null;
/*  880 */       ResultSet result = statement.executeQuery();
/*  881 */       while (result.next())
/*  882 */         identifier = ensureSingleton(result.getString(1), identifier, code); 
/*  884 */       result.close();
/*  885 */       if (identifier == null)
/*  886 */         throw noSuchAuthorityCode(type, code); 
/*      */     } 
/*  889 */     return identifier;
/*      */   }
/*      */   
/*      */   private static <T> T ensureSingleton(T newValue, T oldValue, String code) throws FactoryException {
/*  911 */     if (oldValue == null)
/*  912 */       return newValue; 
/*  914 */     if (oldValue.equals(newValue))
/*  915 */       return oldValue; 
/*  917 */     throw new FactoryException(Errors.format(44, code));
/*      */   }
/*      */   
/*      */   private Map<String, Object> createProperties(String name, String code, String remarks) throws SQLException, FactoryException {
/*  932 */     this.properties.clear();
/*  933 */     Citation authority = getAuthority();
/*  934 */     if (name != null)
/*  935 */       this.properties.put("name", new NamedIdentifier(authority, name.trim())); 
/*  938 */     if (code != null) {
/*  939 */       InternationalString edition = authority.getEdition();
/*  940 */       String version = (edition != null) ? edition.toString() : null;
/*  941 */       this.properties.put("identifiers", new NamedIdentifier(authority, code.trim(), version));
/*      */     } 
/*  944 */     if (remarks != null && (remarks = remarks.trim()).length() != 0)
/*  945 */       this.properties.put("remarks", remarks); 
/*  950 */     List<GenericName> alias = null;
/*  952 */     PreparedStatement stmt = prepareStatement("Alias", "SELECT NAMING_SYSTEM_NAME, ALIAS FROM [Alias] INNER JOIN [Naming System] ON [Alias].NAMING_SYSTEM_CODE = [Naming System].NAMING_SYSTEM_CODE WHERE OBJECT_CODE = ?");
/*  957 */     stmt.setString(1, code);
/*  958 */     ResultSet result = stmt.executeQuery();
/*  959 */     while (result.next()) {
/*      */       ScopedName scopedName;
/*  960 */       String scope = result.getString(1);
/*  961 */       String local = getString(result, 2, code);
/*  963 */       if (scope == null) {
/*  964 */         LocalName localName = new LocalName(local);
/*      */       } else {
/*  966 */         LocalName cached = this.scopes.get(scope);
/*  967 */         if (cached == null) {
/*  968 */           cached = new LocalName(scope);
/*  969 */           this.scopes.put(scope, cached);
/*      */         } 
/*  971 */         scopedName = new ScopedName((GenericName)cached, local);
/*      */       } 
/*  973 */       if (alias == null)
/*  974 */         alias = new ArrayList<GenericName>(); 
/*  976 */       alias.add(scopedName);
/*      */     } 
/*  978 */     result.close();
/*  979 */     if (alias != null)
/*  980 */       this.properties.put("alias", alias.toArray(new GenericName[alias.size()])); 
/*  983 */     return this.properties;
/*      */   }
/*      */   
/*      */   private Map<String, Object> createProperties(String name, String code, String area, String scope, String remarks) throws SQLException, FactoryException {
/* 1001 */     Map<String, Object> properties = createProperties(name, code, remarks);
/* 1002 */     if (area != null && (area = area.trim()).length() != 0) {
/* 1003 */       Extent extent = this.buffered.createExtent(area);
/* 1004 */       properties.put("domainOfValidity", extent);
/*      */     } 
/* 1006 */     if (scope != null && (scope = scope.trim()).length() != 0)
/* 1007 */       properties.put("scope", scope); 
/* 1009 */     return properties;
/*      */   }
/*      */   
/*      */   public synchronized IdentifiedObject createObject(String code) throws FactoryException {
/* 1026 */     ensureNonNull("code", code);
/* 1027 */     String KEY = "IdentifiedObject";
/* 1028 */     PreparedStatement stmt = this.statements.get("IdentifiedObject");
/* 1029 */     StringBuilder query = null;
/* 1038 */     String epsg = trimAuthority(code);
/* 1039 */     boolean isPrimaryKey = isPrimaryKey(epsg);
/* 1040 */     int tupleToSkip = isPrimaryKey ? this.lastObjectType : -1;
/* 1041 */     int index = -1;
/* 1042 */     for (int i = -1; i < TABLES_INFO.length; i++) {
/* 1043 */       if (i == tupleToSkip)
/*      */         continue; 
/*      */       try {
/* 1050 */         if (i >= 0) {
/* 1051 */           TableInfo table = TABLES_INFO[i];
/* 1052 */           String column = isPrimaryKey ? table.codeColumn : table.nameColumn;
/* 1053 */           if (column == null)
/*      */             continue; 
/* 1056 */           if (query == null)
/* 1057 */             query = new StringBuilder("SELECT "); 
/* 1059 */           query.setLength(7);
/* 1060 */           query.append(table.codeColumn);
/* 1061 */           query.append(" FROM ");
/* 1062 */           query.append(table.table);
/* 1063 */           query.append(" WHERE ");
/* 1064 */           query.append(column);
/* 1065 */           query.append(" = ?");
/* 1066 */           if (isPrimaryKey) {
/* 1067 */             assert !this.statements.containsKey("IdentifiedObject") : table;
/* 1068 */             stmt = prepareStatement("IdentifiedObject", query.toString());
/*      */           } else {
/* 1071 */             stmt = this.connection.prepareStatement(adaptSQL(query.toString()));
/*      */           } 
/*      */         } 
/* 1080 */         stmt.setString(1, epsg);
/* 1081 */         ResultSet result = stmt.executeQuery();
/* 1082 */         boolean present = result.next();
/* 1083 */         result.close();
/* 1084 */         if (present) {
/* 1085 */           if (index >= 0)
/* 1086 */             throw new FactoryException(Errors.format(44, code)); 
/* 1088 */           index = (i < 0) ? this.lastObjectType : i;
/* 1089 */           if (isPrimaryKey)
/*      */             break; 
/*      */         } 
/* 1095 */         if (isPrimaryKey && 
/* 1096 */           this.statements.remove("IdentifiedObject") == null)
/* 1097 */           throw new AssertionError(code); 
/* 1100 */         stmt.close();
/* 1101 */       } catch (SQLException exception) {
/* 1102 */         throw databaseFailure(IdentifiedObject.class, code, exception);
/*      */       } 
/*      */       continue;
/*      */     } 
/* 1108 */     if (isPrimaryKey)
/* 1109 */       this.lastObjectType = index; 
/* 1111 */     if (index >= 0) {
/* 1112 */       switch (index) {
/*      */         case 0:
/* 1113 */           return (IdentifiedObject)this.buffered.createCoordinateReferenceSystem(code);
/*      */         case 1:
/* 1114 */           return (IdentifiedObject)this.buffered.createCoordinateSystem(code);
/*      */         case 2:
/* 1115 */           return (IdentifiedObject)this.buffered.createCoordinateSystemAxis(code);
/*      */         case 3:
/* 1116 */           return (IdentifiedObject)this.buffered.createDatum(code);
/*      */         case 4:
/* 1117 */           return (IdentifiedObject)this.buffered.createEllipsoid(code);
/*      */         case 5:
/* 1118 */           return (IdentifiedObject)this.buffered.createPrimeMeridian(code);
/*      */         case 6:
/* 1119 */           return (IdentifiedObject)this.buffered.createCoordinateOperation(code);
/*      */         case 7:
/* 1120 */           return (IdentifiedObject)this.buffered.createOperationMethod(code);
/*      */         case 8:
/* 1121 */           return (IdentifiedObject)this.buffered.createParameterDescriptor(code);
/*      */         case 9:
/* 1126 */           return super.createObject(code);
/*      */       } 
/*      */       throw new AssertionError(index);
/*      */     } 
/*      */   }
/*      */   
/*      */   public synchronized Unit<?> createUnit(String code) throws FactoryException {
/* 1140 */     ensureNonNull("code", code);
/* 1141 */     Unit<?> returnValue = null;
/*      */     try {
/* 1143 */       String primaryKey = toPrimaryKey(Unit.class, code, "[Unit of Measure]", "UOM_CODE", "UNIT_OF_MEAS_NAME");
/* 1146 */       PreparedStatement stmt = prepareStatement("Unit", "SELECT UOM_CODE, FACTOR_B, FACTOR_C, TARGET_UOM_CODE FROM [Unit of Measure] WHERE UOM_CODE = ?");
/* 1152 */       stmt.setInt(1, Integer.parseInt(primaryKey));
/* 1153 */       ResultSet result = stmt.executeQuery();
/* 1154 */       while (result.next()) {
/* 1155 */         int source = getInt(result, 1, code);
/* 1156 */         double b = result.getDouble(2);
/* 1157 */         double c = result.getDouble(3);
/* 1158 */         int target = getInt(result, 4, code);
/* 1159 */         Unit<?> base = getUnit(target);
/* 1160 */         if (base == null)
/* 1161 */           throw noSuchAuthorityCode(Unit.class, String.valueOf(target)); 
/* 1163 */         Unit<?> unit = getUnit(source);
/* 1164 */         if (unit == null)
/* 1166 */           if (b != 0.0D && c != 0.0D) {
/* 1167 */             unit = (b == c) ? base : base.times(b / c);
/*      */           } else {
/* 1170 */             throw new FactoryException("Unsupported unit: " + code);
/*      */           }  
/* 1172 */         returnValue = ensureSingleton(unit, returnValue, code);
/*      */       } 
/* 1174 */       result.close();
/* 1176 */     } catch (SQLException exception) {
/* 1177 */       throw databaseFailure(Unit.class, code, exception);
/*      */     } 
/* 1179 */     if (returnValue == null)
/* 1180 */       throw noSuchAuthorityCode(Unit.class, code); 
/* 1182 */     return returnValue;
/*      */   }
/*      */   
/*      */   public synchronized Ellipsoid createEllipsoid(String code) throws FactoryException {
/* 1196 */     ensureNonNull("code", code);
/* 1197 */     Ellipsoid returnValue = null;
/*      */     try {
/* 1199 */       String primaryKey = toPrimaryKey(Ellipsoid.class, code, "[Ellipsoid]", "ELLIPSOID_CODE", "ELLIPSOID_NAME");
/* 1202 */       PreparedStatement stmt = prepareStatement("Ellipsoid", "SELECT ELLIPSOID_CODE, ELLIPSOID_NAME, SEMI_MAJOR_AXIS, INV_FLATTENING, SEMI_MINOR_AXIS, UOM_CODE, REMARKS FROM [Ellipsoid] WHERE ELLIPSOID_CODE = ?");
/* 1211 */       stmt.setInt(1, Integer.parseInt(primaryKey));
/* 1212 */       ResultSet result = stmt.executeQuery();
/* 1213 */       while (result.next()) {
/*      */         Ellipsoid ellipsoid;
/* 1219 */         String epsg = getString(result, 1, code);
/* 1220 */         String name = getString(result, 2, code);
/* 1221 */         double semiMajorAxis = getDouble(result, 3, code);
/* 1222 */         double inverseFlattening = result.getDouble(4);
/* 1223 */         double semiMinorAxis = result.getDouble(5);
/* 1224 */         String unitCode = getString(result, 6, code);
/* 1225 */         String remarks = result.getString(7);
/* 1226 */         Unit unit = this.buffered.createUnit(unitCode);
/* 1227 */         Map<String, Object> properties = createProperties(name, epsg, remarks);
/* 1229 */         if (inverseFlattening == 0.0D) {
/* 1230 */           if (semiMinorAxis == 0.0D) {
/* 1232 */             String column = result.getMetaData().getColumnName(3);
/* 1233 */             result.close();
/* 1234 */             throw new FactoryException(Errors.format(147, code, column));
/*      */           } 
/* 1237 */           ellipsoid = this.factories.getDatumFactory().createEllipsoid(properties, semiMajorAxis, semiMinorAxis, unit);
/*      */         } else {
/* 1241 */           if (semiMinorAxis != 0.0D) {
/* 1244 */             LogRecord record = Loggings.format(Level.WARNING, 1, code);
/* 1245 */             record.setLoggerName(LOGGER.getName());
/* 1246 */             LOGGER.log(record);
/*      */           } 
/* 1248 */           ellipsoid = this.factories.getDatumFactory().createFlattenedSphere(properties, semiMajorAxis, inverseFlattening, unit);
/*      */         } 
/* 1255 */         returnValue = ensureSingleton(ellipsoid, returnValue, code);
/*      */       } 
/* 1257 */       result.close();
/* 1258 */     } catch (SQLException exception) {
/* 1259 */       throw databaseFailure(Ellipsoid.class, code, exception);
/*      */     } 
/* 1261 */     if (returnValue == null)
/* 1262 */       throw noSuchAuthorityCode(Ellipsoid.class, code); 
/* 1264 */     return returnValue;
/*      */   }
/*      */   
/*      */   public synchronized PrimeMeridian createPrimeMeridian(String code) throws FactoryException {
/* 1280 */     ensureNonNull("code", code);
/* 1281 */     PrimeMeridian returnValue = null;
/*      */     try {
/* 1283 */       String primaryKey = toPrimaryKey(PrimeMeridian.class, code, "[Prime Meridian]", "PRIME_MERIDIAN_CODE", "PRIME_MERIDIAN_NAME");
/* 1286 */       PreparedStatement stmt = prepareStatement("PrimeMeridian", "SELECT PRIME_MERIDIAN_CODE, PRIME_MERIDIAN_NAME, GREENWICH_LONGITUDE, UOM_CODE, REMARKS FROM [Prime Meridian] WHERE PRIME_MERIDIAN_CODE = ?");
/* 1293 */       stmt.setInt(1, Integer.parseInt(primaryKey));
/* 1294 */       ResultSet result = stmt.executeQuery();
/* 1295 */       while (result.next()) {
/* 1296 */         String epsg = getString(result, 1, code);
/* 1297 */         String name = getString(result, 2, code);
/* 1298 */         double longitude = getDouble(result, 3, code);
/* 1299 */         String unit_code = getString(result, 4, code);
/* 1300 */         String remarks = result.getString(5);
/* 1301 */         Unit unit = this.buffered.createUnit(unit_code);
/* 1302 */         Map<String, Object> properties = createProperties(name, epsg, remarks);
/* 1303 */         PrimeMeridian primeMeridian = this.factories.getDatumFactory().createPrimeMeridian(properties, longitude, unit);
/* 1305 */         returnValue = ensureSingleton(primeMeridian, returnValue, code);
/*      */       } 
/* 1307 */       result.close();
/* 1308 */     } catch (SQLException exception) {
/* 1309 */       throw databaseFailure(PrimeMeridian.class, code, exception);
/*      */     } 
/* 1311 */     if (returnValue == null)
/* 1312 */       throw noSuchAuthorityCode(PrimeMeridian.class, code); 
/* 1314 */     return returnValue;
/*      */   }
/*      */   
/*      */   public synchronized Extent createExtent(String code) throws FactoryException {
/* 1328 */     ensureNonNull("code", code);
/* 1329 */     Extent returnValue = null;
/*      */     try {
/* 1331 */       String primaryKey = toPrimaryKey(Extent.class, code, "[Area]", "AREA_CODE", "AREA_NAME");
/* 1334 */       PreparedStatement stmt = prepareStatement("Area", "SELECT AREA_OF_USE, AREA_SOUTH_BOUND_LAT, AREA_NORTH_BOUND_LAT, AREA_WEST_BOUND_LON, AREA_EAST_BOUND_LON FROM [Area] WHERE AREA_CODE = ?");
/* 1341 */       stmt.setInt(1, Integer.parseInt(primaryKey));
/* 1342 */       ResultSet result = stmt.executeQuery();
/* 1343 */       while (result.next()) {
/* 1344 */         ExtentImpl extent = null;
/* 1345 */         String description = result.getString(1);
/* 1346 */         if (description != null) {
/* 1347 */           extent = new ExtentImpl();
/* 1348 */           extent.setDescription((InternationalString)new SimpleInternationalString(description));
/*      */         } 
/* 1350 */         double ymin = result.getDouble(2);
/* 1351 */         if (!result.wasNull()) {
/* 1352 */           double ymax = result.getDouble(3);
/* 1353 */           if (!result.wasNull()) {
/* 1354 */             double xmin = result.getDouble(4);
/* 1355 */             if (!result.wasNull()) {
/* 1356 */               double xmax = result.getDouble(5);
/* 1357 */               if (!result.wasNull()) {
/* 1358 */                 if (extent == null)
/* 1359 */                   extent = new ExtentImpl(); 
/* 1361 */                 extent.setGeographicElements(Collections.singleton(new GeographicBoundingBoxImpl(xmin, xmax, ymin, ymax)));
/*      */               } 
/*      */             } 
/*      */           } 
/*      */         } 
/* 1367 */         if (extent != null)
/* 1368 */           returnValue = (Extent)ensureSingleton(extent.unmodifiable(), returnValue, code); 
/*      */       } 
/* 1371 */       result.close();
/* 1372 */     } catch (SQLException exception) {
/* 1373 */       throw databaseFailure(Extent.class, code, exception);
/*      */     } 
/* 1375 */     if (returnValue == null)
/* 1376 */       throw noSuchAuthorityCode(Extent.class, code); 
/* 1378 */     return returnValue;
/*      */   }
/*      */   
/*      */   private BursaWolfParameters[] createBursaWolfParameters(String code, ResultSet toClose) throws SQLException, FactoryException {
/* 1398 */     if (this.safetyGuard.contains(code))
/* 1404 */       return null; 
/* 1407 */     PreparedStatement stmt = prepareStatement("BursaWolfParametersSet", "SELECT CO.COORD_OP_CODE, CO.COORD_OP_METHOD_CODE, CRS2.DATUM_CODE FROM [Coordinate_Operation] AS CO INNER JOIN [Coordinate Reference System] AS CRS2 ON CO.TARGET_CRS_CODE = CRS2.COORD_REF_SYS_CODE LEFT JOIN [Area] AS AREA on CO.AREA_OF_USE_CODE = AREA.AREA_CODE WHERE CO.COORD_OP_METHOD_CODE >= 9603 AND CO.COORD_OP_METHOD_CODE <= 9607 AND CO.COORD_OP_CODE <> 1 AND CO.SOURCE_CRS_CODE IN ( SELECT CRS1.COORD_REF_SYS_CODE  FROM [Coordinate Reference System] AS CRS1  WHERE CRS1.DATUM_CODE = ?) ORDER BY CRS2.DATUM_CODE, ABS(CO.DEPRECATED), CO.COORD_OP_ACCURACY, (AREA_NORTH_BOUND_LAT - AREA_SOUTH_BOUND_LAT) * (CASE WHEN AREA_EAST_BOUND_LON > AREA_WEST_BOUND_LON      THEN (AREA_EAST_BOUND_LON - AREA_WEST_BOUND_LON)      ELSE (360 - AREA_WEST_BOUND_LON - AREA_EAST_BOUND_LON) END) DESC, CO.COORD_OP_CODE DESC");
/* 1429 */     stmt.setInt(1, Integer.parseInt(code));
/* 1430 */     ResultSet result = stmt.executeQuery();
/* 1431 */     List<Object> bwInfos = null;
/* 1432 */     while (result.next()) {
/* 1433 */       String operation = getString(result, 1, code);
/* 1434 */       int method = getInt(result, 2, code);
/* 1435 */       String datum = getString(result, 3, code);
/* 1436 */       if (bwInfos == null)
/* 1437 */         bwInfos = new ArrayList(); 
/* 1439 */       bwInfos.add(new BursaWolfInfo(operation, method, datum));
/*      */     } 
/* 1441 */     result.close();
/* 1442 */     if (bwInfos == null)
/* 1444 */       return null; 
/* 1446 */     toClose.close();
/* 1452 */     int size = bwInfos.size();
/* 1453 */     if (size > 1) {
/* 1454 */       BursaWolfInfo[] codes = bwInfos.<BursaWolfInfo>toArray(new BursaWolfInfo[size]);
/* 1455 */       sort((Object[])codes);
/* 1456 */       bwInfos.clear();
/* 1457 */       Set<String> added = new HashSet<String>();
/* 1458 */       for (int j = 0; j < codes.length; j++) {
/* 1459 */         BursaWolfInfo candidate = codes[j];
/* 1460 */         if (added.add(candidate.target))
/* 1461 */           bwInfos.add(candidate); 
/*      */       } 
/* 1464 */       size = bwInfos.size();
/*      */     } 
/* 1472 */     stmt = prepareStatement("BursaWolfParameters", "SELECT PARAMETER_CODE, PARAMETER_VALUE, UOM_CODE FROM [Coordinate_Operation Parameter Value] WHERE COORD_OP_CODE = ? AND COORD_OP_METHOD_CODE = ?");
/* 1478 */     for (int i = 0; i < size; i++) {
/*      */       GeodeticDatum datum;
/* 1479 */       BursaWolfInfo info = (BursaWolfInfo)bwInfos.get(i);
/*      */       try {
/* 1482 */         this.safetyGuard.add(code);
/* 1483 */         datum = this.buffered.createGeodeticDatum(info.target);
/*      */       } finally {
/* 1485 */         this.safetyGuard.remove(code);
/*      */       } 
/* 1487 */       BursaWolfParameters parameters = new BursaWolfParameters(datum);
/* 1488 */       stmt.setInt(1, Integer.parseInt(info.operation));
/* 1489 */       stmt.setInt(2, info.method);
/* 1490 */       result = stmt.executeQuery();
/* 1491 */       while (result.next())
/* 1492 */         setBursaWolfParameter(parameters, getInt(result, 1, info.operation), getDouble(result, 2, info.operation), this.buffered.createUnit(getString(result, 3, info.operation))); 
/* 1497 */       result.close();
/* 1498 */       if (info.method == 9607) {
/* 1501 */         parameters.ex = -parameters.ex;
/* 1502 */         parameters.ey = -parameters.ey;
/* 1503 */         parameters.ey = -parameters.ey;
/*      */       } 
/* 1505 */       bwInfos.set(i, parameters);
/*      */     } 
/* 1507 */     return bwInfos.<BursaWolfParameters>toArray(new BursaWolfParameters[size]);
/*      */   }
/*      */   
/*      */   public synchronized Datum createDatum(String code) throws FactoryException {
/* 1525 */     ensureNonNull("code", code);
/* 1526 */     Datum returnValue = null;
/*      */     try {
/* 1528 */       String primaryKey = toPrimaryKey(Datum.class, code, "[Datum]", "DATUM_CODE", "DATUM_NAME");
/* 1531 */       PreparedStatement stmt = prepareStatement("Datum", "SELECT DATUM_CODE, DATUM_NAME, DATUM_TYPE, ORIGIN_DESCRIPTION, REALIZATION_EPOCH, AREA_OF_USE_CODE, DATUM_SCOPE, REMARKS, ELLIPSOID_CODE, PRIME_MERIDIAN_CODE FROM [Datum] WHERE DATUM_CODE = ?");
/* 1543 */       stmt.setInt(1, Integer.parseInt(primaryKey));
/* 1544 */       ResultSet result = stmt.executeQuery();
/* 1545 */       while (result.next()) {
/*      */         EngineeringDatum engineeringDatum;
/* 1546 */         String epsg = getString(result, 1, code);
/* 1547 */         String name = getString(result, 2, code);
/* 1548 */         String type = getString(result, 3, code).trim().toLowerCase();
/* 1549 */         String anchor = result.getString(4);
/* 1550 */         String epoch = result.getString(5);
/* 1551 */         String area = result.getString(6);
/* 1552 */         String scope = result.getString(7);
/* 1553 */         String remarks = result.getString(8);
/* 1554 */         Map<String, Object> properties = createProperties(name, epsg, area, scope, remarks);
/* 1555 */         if (anchor != null)
/* 1556 */           properties.put("anchorPoint", anchor); 
/* 1558 */         if (epoch != null && epoch.length() != 0)
/*      */           try {
/* 1559 */             this.calendar.clear();
/* 1560 */             this.calendar.set(Integer.parseInt(epoch), 0, 1);
/* 1561 */             properties.put("realizationEpoch", this.calendar.getTime());
/* 1562 */           } catch (NumberFormatException exception) {
/* 1564 */             Logging.unexpectedException(LOGGER, DirectEpsgFactory.class, "createDatum", exception);
/*      */           }  
/* 1566 */         DatumFactory factory = this.factories.getDatumFactory();
/* 1580 */         if (type.equals("geodetic")) {
/* 1581 */           properties = new HashMap<String, Object>(properties);
/* 1582 */           Ellipsoid ellipsoid = this.buffered.createEllipsoid(getString(result, 9, code));
/* 1583 */           PrimeMeridian meridian = this.buffered.createPrimeMeridian(getString(result, 10, code));
/* 1584 */           BursaWolfParameters[] param = createBursaWolfParameters(primaryKey, result);
/* 1585 */           if (param != null) {
/* 1586 */             result = null;
/* 1587 */             properties.put("bursaWolf", param);
/*      */           } 
/* 1589 */           GeodeticDatum geodeticDatum = factory.createGeodeticDatum(properties, ellipsoid, meridian);
/* 1590 */         } else if (type.equals("vertical")) {
/* 1592 */           VerticalDatum verticalDatum = factory.createVerticalDatum(properties, VerticalDatumType.GEOIDAL);
/* 1593 */         } else if (type.equals("engineering")) {
/* 1594 */           engineeringDatum = factory.createEngineeringDatum(properties);
/*      */         } else {
/* 1596 */           result.close();
/* 1597 */           throw new FactoryException(Errors.format(187, type));
/*      */         } 
/* 1599 */         returnValue = (Datum)ensureSingleton(engineeringDatum, returnValue, code);
/* 1600 */         if (result == null)
/* 1603 */           return returnValue; 
/*      */       } 
/* 1606 */       result.close();
/* 1607 */     } catch (SQLException exception) {
/* 1608 */       throw databaseFailure(Datum.class, code, exception);
/*      */     } 
/* 1610 */     if (returnValue == null)
/* 1611 */       throw noSuchAuthorityCode(Datum.class, code); 
/* 1613 */     return returnValue;
/*      */   }
/*      */   
/*      */   private AxisName getAxisName(String code) throws FactoryException {
/* 1622 */     assert Thread.holdsLock(this);
/* 1623 */     AxisName returnValue = this.axisNames.get(code);
/* 1624 */     if (returnValue == null)
/*      */       try {
/* 1626 */         PreparedStatement stmt = prepareStatement("AxisName", "SELECT COORD_AXIS_NAME, DESCRIPTION, REMARKS FROM [Coordinate Axis Name] WHERE COORD_AXIS_NAME_CODE = ?");
/* 1629 */         stmt.setInt(1, Integer.parseInt(code));
/* 1630 */         ResultSet result = stmt.executeQuery();
/* 1631 */         while (result.next()) {
/* 1632 */           String name = getString(result, 1, code);
/* 1633 */           String description = result.getString(2);
/* 1634 */           String remarks = result.getString(3);
/* 1635 */           if (description == null) {
/* 1636 */             description = remarks;
/* 1637 */           } else if (remarks != null) {
/* 1638 */             description = description + System.getProperty("line.separator", "\n") + remarks;
/*      */           } 
/* 1640 */           AxisName axis = new AxisName(name, description);
/* 1641 */           returnValue = ensureSingleton(axis, returnValue, code);
/*      */         } 
/* 1643 */         result.close();
/* 1644 */         if (returnValue == null)
/* 1645 */           throw noSuchAuthorityCode(AxisName.class, code); 
/* 1647 */         this.axisNames.put(code, returnValue);
/* 1648 */       } catch (SQLException exception) {
/* 1649 */         throw databaseFailure(AxisName.class, code, exception);
/*      */       }  
/* 1651 */     return returnValue;
/*      */   }
/*      */   
/*      */   public synchronized CoordinateSystemAxis createCoordinateSystemAxis(String code) throws FactoryException {
/* 1665 */     ensureNonNull("code", code);
/* 1666 */     CoordinateSystemAxis returnValue = null;
/*      */     try {
/* 1668 */       String primaryKey = trimAuthority(code);
/* 1670 */       PreparedStatement stmt = prepareStatement("Axis", "SELECT COORD_AXIS_CODE, COORD_AXIS_NAME_CODE, COORD_AXIS_ORIENTATION, COORD_AXIS_ABBREVIATION, UOM_CODE FROM [Coordinate Axis] WHERE COORD_AXIS_CODE = ?");
/* 1677 */       stmt.setInt(1, Integer.parseInt(primaryKey));
/* 1678 */       ResultSet result = stmt.executeQuery();
/* 1679 */       while (result.next()) {
/*      */         AxisDirection direction;
/* 1680 */         String epsg = getString(result, 1, code);
/* 1681 */         String nameCode = getString(result, 2, code);
/* 1682 */         String orientation = getString(result, 3, code);
/* 1683 */         String abbreviation = getString(result, 4, code);
/* 1684 */         String unit = getString(result, 5, code);
/*      */         try {
/* 1687 */           direction = DefaultCoordinateSystemAxis.getDirection(orientation);
/* 1688 */         } catch (NoSuchElementException exception) {
/* 1689 */           if (orientation.equalsIgnoreCase("Geocentre > equator/PM")) {
/* 1690 */             direction = AxisDirection.OTHER;
/* 1691 */           } else if (orientation.equalsIgnoreCase("Geocentre > equator/90dE") || orientation.equalsIgnoreCase("Geocentre > equator/90°E")) {
/* 1693 */             direction = AxisDirection.GEOCENTRIC_Y;
/* 1694 */           } else if (orientation.equalsIgnoreCase("Geocentre > equator/0dE") || orientation.equalsIgnoreCase("Geocentre > equator/0°E")) {
/* 1696 */             direction = AxisDirection.GEOCENTRIC_X;
/* 1697 */           } else if (orientation.equalsIgnoreCase("Geocentre > north pole")) {
/* 1698 */             direction = AxisDirection.GEOCENTRIC_Z;
/*      */           } else {
/* 1700 */             throw new FactoryException(exception);
/*      */           } 
/*      */         } 
/* 1703 */         AxisName an = getAxisName(nameCode);
/* 1704 */         Map<String, Object> properties = createProperties(an.name, epsg, an.description);
/* 1705 */         CSFactory factory = this.factories.getCSFactory();
/* 1706 */         CoordinateSystemAxis axis = factory.createCoordinateSystemAxis(properties, abbreviation, direction, this.buffered.createUnit(unit));
/* 1708 */         returnValue = ensureSingleton(axis, returnValue, code);
/*      */       } 
/* 1710 */       result.close();
/* 1711 */     } catch (SQLException exception) {
/* 1712 */       throw databaseFailure(CoordinateSystemAxis.class, code, exception);
/*      */     } 
/* 1714 */     if (returnValue == null)
/* 1715 */       throw noSuchAuthorityCode(CoordinateSystemAxis.class, code); 
/* 1717 */     return returnValue;
/*      */   }
/*      */   
/*      */   private CoordinateSystemAxis[] createAxesForCoordinateSystem(String code, int dimension) throws SQLException, FactoryException {
/* 1735 */     assert Thread.holdsLock(this);
/* 1736 */     CoordinateSystemAxis[] axis = new CoordinateSystemAxis[dimension];
/* 1738 */     PreparedStatement stmt = prepareStatement("AxisOrder", "SELECT COORD_AXIS_CODE FROM [Coordinate Axis] WHERE COORD_SYS_CODE = ? ORDER BY [ORDER]");
/* 1744 */     stmt.setInt(1, Integer.parseInt(code));
/* 1745 */     ResultSet result = stmt.executeQuery();
/* 1746 */     int i = 0;
/* 1747 */     while (result.next()) {
/* 1748 */       String axisCode = getString(result, 1, code);
/* 1749 */       if (i < axis.length)
/* 1752 */         axis[i] = this.buffered.createCoordinateSystemAxis(axisCode); 
/* 1754 */       i++;
/*      */     } 
/* 1756 */     result.close();
/* 1757 */     if (i != axis.length)
/* 1758 */       throw new FactoryException(Errors.format(93, Integer.valueOf(axis.length), Integer.valueOf(i))); 
/* 1761 */     return axis;
/*      */   }
/*      */   
/*      */   public synchronized CoordinateSystem createCoordinateSystem(String code) throws FactoryException {
/* 1777 */     ensureNonNull("code", code);
/* 1778 */     CoordinateSystem returnValue = null;
/*      */     try {
/* 1781 */       String primaryKey = toPrimaryKey(CoordinateSystem.class, code, "[Coordinate System]", "COORD_SYS_CODE", "COORD_SYS_NAME");
/* 1783 */       PreparedStatement stmt = prepareStatement("CoordinateSystem", "SELECT COORD_SYS_CODE, COORD_SYS_NAME, COORD_SYS_TYPE, DIMENSION, REMARKS FROM [Coordinate System] WHERE COORD_SYS_CODE = ?");
/* 1790 */       stmt.setInt(1, Integer.parseInt(primaryKey));
/* 1791 */       ResultSet result = stmt.executeQuery();
/* 1792 */       while (result.next()) {
/*      */         AffineCS affineCS;
/* 1793 */         String epsg = getString(result, 1, code);
/* 1794 */         String name = getString(result, 2, code);
/* 1795 */         String type = getString(result, 3, code).trim().toLowerCase();
/* 1796 */         int dimension = getInt(result, 4, code);
/* 1797 */         String remarks = result.getString(5);
/* 1798 */         CoordinateSystemAxis[] axis = createAxesForCoordinateSystem(primaryKey, dimension);
/* 1799 */         Map<String, Object> properties = createProperties(name, epsg, remarks);
/* 1800 */         CSFactory factory = this.factories.getCSFactory();
/* 1801 */         CoordinateSystem cs = null;
/* 1802 */         if (type.equals("ellipsoidal")) {
/*      */           EllipsoidalCS ellipsoidalCS;
/* 1803 */           switch (dimension) {
/*      */             case 2:
/* 1804 */               ellipsoidalCS = factory.createEllipsoidalCS(properties, axis[0], axis[1]);
/*      */               break;
/*      */             case 3:
/* 1805 */               ellipsoidalCS = factory.createEllipsoidalCS(properties, axis[0], axis[1], axis[2]);
/*      */               break;
/*      */           } 
/* 1807 */         } else if (type.equals("cartesian")) {
/*      */           CartesianCS cartesianCS;
/* 1808 */           switch (dimension) {
/*      */             case 2:
/* 1809 */               cartesianCS = factory.createCartesianCS(properties, axis[0], axis[1]);
/*      */               break;
/*      */             case 3:
/* 1810 */               cartesianCS = factory.createCartesianCS(properties, axis[0], axis[1], axis[2]);
/*      */               break;
/*      */           } 
/* 1812 */         } else if (type.equals("spherical")) {
/*      */           SphericalCS sphericalCS;
/* 1813 */           switch (dimension) {
/*      */             case 3:
/* 1814 */               sphericalCS = factory.createSphericalCS(properties, axis[0], axis[1], axis[2]);
/*      */               break;
/*      */           } 
/* 1816 */         } else if (type.equals("vertical") || type.equals("gravity-related")) {
/*      */           VerticalCS verticalCS;
/* 1817 */           switch (dimension) {
/*      */             case 1:
/* 1818 */               verticalCS = factory.createVerticalCS(properties, axis[0]);
/*      */               break;
/*      */           } 
/* 1820 */         } else if (type.equals("linear")) {
/*      */           LinearCS linearCS;
/* 1821 */           switch (dimension) {
/*      */             case 1:
/* 1822 */               linearCS = factory.createLinearCS(properties, axis[0]);
/*      */               break;
/*      */           } 
/* 1824 */         } else if (type.equals("polar")) {
/*      */           PolarCS polarCS;
/* 1825 */           switch (dimension) {
/*      */             case 2:
/* 1826 */               polarCS = factory.createPolarCS(properties, axis[0], axis[1]);
/*      */               break;
/*      */           } 
/* 1828 */         } else if (type.equals("cylindrical")) {
/*      */           CylindricalCS cylindricalCS;
/* 1829 */           switch (dimension) {
/*      */             case 3:
/* 1830 */               cylindricalCS = factory.createCylindricalCS(properties, axis[0], axis[1], axis[2]);
/*      */               break;
/*      */           } 
/* 1832 */         } else if (type.equals("affine")) {
/* 1833 */           switch (dimension) {
/*      */             case 2:
/* 1834 */               affineCS = factory.createAffineCS(properties, axis[0], axis[1]);
/*      */               break;
/*      */             case 3:
/* 1835 */               affineCS = factory.createAffineCS(properties, axis[0], axis[1], axis[2]);
/*      */               break;
/*      */           } 
/*      */         } else {
/* 1838 */           result.close();
/* 1839 */           throw new FactoryException(Errors.format(187, type));
/*      */         } 
/* 1841 */         if (affineCS == null) {
/* 1842 */           result.close();
/* 1843 */           throw new FactoryException(Errors.format(173, type));
/*      */         } 
/* 1846 */         returnValue = (CoordinateSystem)ensureSingleton(affineCS, returnValue, code);
/*      */       } 
/* 1848 */       result.close();
/* 1849 */     } catch (SQLException exception) {
/* 1850 */       throw databaseFailure(CoordinateSystem.class, code, exception);
/*      */     } 
/* 1852 */     if (returnValue == null)
/* 1853 */       throw noSuchAuthorityCode(CoordinateSystem.class, code); 
/* 1855 */     return returnValue;
/*      */   }
/*      */   
/*      */   private String toPrimaryKeyCRS(String code) throws SQLException, FactoryException {
/* 1865 */     return toPrimaryKey(CoordinateReferenceSystem.class, code, "[Coordinate Reference System]", "COORD_REF_SYS_CODE", "COORD_REF_SYS_NAME");
/*      */   }
/*      */   
/*      */   public synchronized CoordinateReferenceSystem createCoordinateReferenceSystem(String code) throws FactoryException {
/* 1882 */     ensureNonNull("code", code);
/* 1883 */     CoordinateReferenceSystem returnValue = null;
/*      */     try {
/* 1885 */       String primaryKey = toPrimaryKeyCRS(code);
/* 1887 */       PreparedStatement stmt = prepareStatement("CoordinateReferenceSystem", "SELECT COORD_REF_SYS_CODE, COORD_REF_SYS_NAME, AREA_OF_USE_CODE, CRS_SCOPE, REMARKS, COORD_REF_SYS_KIND, COORD_SYS_CODE, DATUM_CODE, SOURCE_GEOGCRS_CODE, PROJECTION_CONV_CODE, CMPD_HORIZCRS_CODE, CMPD_VERTCRS_CODE FROM [Coordinate Reference System] WHERE COORD_REF_SYS_CODE = ?");
/* 1902 */       stmt.setInt(1, Integer.parseInt(primaryKey));
/* 1903 */       ResultSet result = stmt.executeQuery();
/* 1904 */       while (result.next()) {
/*      */         EngineeringCRS engineeringCRS;
/* 1905 */         String epsg = getString(result, 1, code);
/* 1906 */         String name = getString(result, 2, code);
/* 1907 */         String area = result.getString(3);
/* 1908 */         String scope = result.getString(4);
/* 1909 */         String remarks = result.getString(5);
/* 1910 */         String type = getString(result, 6, code);
/* 1914 */         CRSFactory factory = this.factories.getCRSFactory();
/* 1922 */         if (type.equalsIgnoreCase("geographic 2D") || type.equalsIgnoreCase("geographic 3D")) {
/*      */           GeodeticDatum datum;
/* 1925 */           String csCode = getString(result, 7, code);
/* 1926 */           String dmCode = result.getString(8);
/* 1927 */           EllipsoidalCS cs = this.buffered.createEllipsoidalCS(csCode);
/* 1929 */           if (dmCode != null) {
/* 1930 */             datum = this.buffered.createGeodeticDatum(dmCode);
/*      */           } else {
/* 1932 */             String geoCode = getString(result, 9, code, 8);
/* 1933 */             result.close();
/* 1934 */             result = null;
/* 1935 */             GeographicCRS baseCRS = this.buffered.createGeographicCRS(geoCode);
/* 1936 */             datum = baseCRS.getDatum();
/*      */           } 
/* 1938 */           Map<String, Object> properties = createProperties(name, epsg, area, scope, remarks);
/* 1939 */           GeographicCRS geographicCRS = factory.createGeographicCRS(properties, datum, cs);
/* 1947 */         } else if (type.equalsIgnoreCase("projected")) {
/* 1948 */           String csCode = getString(result, 7, code);
/* 1949 */           String geoCode = getString(result, 9, code);
/* 1950 */           String opCode = getString(result, 10, code);
/* 1951 */           result.close();
/* 1952 */           result = null;
/* 1953 */           CartesianCS cs = this.buffered.createCartesianCS(csCode);
/* 1954 */           GeographicCRS baseCRS = this.buffered.createGeographicCRS(geoCode);
/* 1955 */           CoordinateOperation op = this.buffered.createCoordinateOperation(opCode);
/* 1956 */           if (op instanceof Conversion) {
/* 1957 */             Map<String, Object> properties = createProperties(name, epsg, area, scope, remarks);
/* 1958 */             ProjectedCRS projectedCRS = factory.createProjectedCRS(properties, baseCRS, (Conversion)op, cs);
/*      */           } else {
/* 1960 */             throw noSuchAuthorityCode(Projection.class, opCode);
/*      */           } 
/* 1966 */         } else if (type.equalsIgnoreCase("vertical")) {
/* 1967 */           String csCode = getString(result, 7, code);
/* 1968 */           String dmCode = getString(result, 8, code);
/* 1969 */           VerticalCS cs = this.buffered.createVerticalCS(csCode);
/* 1970 */           VerticalDatum datum = this.buffered.createVerticalDatum(dmCode);
/* 1971 */           Map<String, Object> properties = createProperties(name, epsg, area, scope, remarks);
/* 1972 */           VerticalCRS verticalCRS = factory.createVerticalCRS(properties, datum, cs);
/* 1980 */         } else if (type.equalsIgnoreCase("compound")) {
/*      */           CoordinateReferenceSystem crs1, crs2;
/* 1981 */           String code1 = getString(result, 11, code);
/* 1982 */           String code2 = getString(result, 12, code);
/* 1983 */           result.close();
/* 1984 */           result = null;
/* 1986 */           if (!this.safetyGuard.add(epsg))
/* 1987 */             throw recursiveCall(CompoundCRS.class, epsg); 
/*      */           try {
/* 1989 */             crs1 = this.buffered.createCoordinateReferenceSystem(code1);
/* 1990 */             crs2 = this.buffered.createCoordinateReferenceSystem(code2);
/*      */           } finally {
/* 1992 */             this.safetyGuard.remove(epsg);
/*      */           } 
/* 1995 */           Map<String, Object> properties = createProperties(name, epsg, area, scope, remarks);
/* 1996 */           CompoundCRS compoundCRS = factory.createCompoundCRS(properties, new CoordinateReferenceSystem[] { crs1, crs2 });
/* 2002 */         } else if (type.equalsIgnoreCase("geocentric")) {
/* 2003 */           String csCode = getString(result, 7, code);
/* 2004 */           String dmCode = getString(result, 8, code);
/* 2005 */           CoordinateSystem cs = this.buffered.createCoordinateSystem(csCode);
/* 2006 */           GeodeticDatum datum = this.buffered.createGeodeticDatum(dmCode);
/* 2007 */           Map<String, Object> properties = createProperties(name, epsg, area, scope, remarks);
/* 2008 */           if (cs instanceof CartesianCS) {
/* 2009 */             GeocentricCRS geocentricCRS = factory.createGeocentricCRS(properties, datum, (CartesianCS)cs);
/* 2010 */           } else if (cs instanceof SphericalCS) {
/* 2011 */             GeocentricCRS geocentricCRS = factory.createGeocentricCRS(properties, datum, (SphericalCS)cs);
/*      */           } else {
/* 2013 */             result.close();
/* 2014 */             throw new FactoryException(Errors.format(63, cs.getClass(), GeocentricCRS.class));
/*      */           } 
/* 2022 */         } else if (type.equalsIgnoreCase("engineering")) {
/* 2023 */           String csCode = getString(result, 7, code);
/* 2024 */           String dmCode = getString(result, 8, code);
/* 2025 */           CoordinateSystem cs = this.buffered.createCoordinateSystem(csCode);
/* 2026 */           EngineeringDatum datum = this.buffered.createEngineeringDatum(dmCode);
/* 2027 */           Map<String, Object> properties = createProperties(name, epsg, area, scope, remarks);
/* 2028 */           engineeringCRS = factory.createEngineeringCRS(properties, datum, cs);
/*      */         } else {
/* 2034 */           result.close();
/* 2035 */           throw new FactoryException(Errors.format(187, type));
/*      */         } 
/* 2037 */         returnValue = (CoordinateReferenceSystem)ensureSingleton(engineeringCRS, returnValue, code);
/* 2038 */         if (result == null)
/* 2041 */           return returnValue; 
/*      */       } 
/* 2044 */       result.close();
/* 2045 */     } catch (SQLException exception) {
/* 2046 */       throw databaseFailure(CoordinateReferenceSystem.class, code, exception);
/*      */     } 
/* 2048 */     if (returnValue == null)
/* 2049 */       throw noSuchAuthorityCode(CoordinateReferenceSystem.class, code); 
/* 2051 */     return returnValue;
/*      */   }
/*      */   
/*      */   public synchronized ParameterDescriptor createParameterDescriptor(String code) throws FactoryException {
/* 2066 */     ensureNonNull("code", code);
/* 2067 */     ParameterDescriptor returnValue = null;
/*      */     try {
/* 2070 */       String primaryKey = toPrimaryKey(ParameterDescriptor.class, code, "[Coordinate_Operation Parameter]", "PARAMETER_CODE", "PARAMETER_NAME");
/* 2072 */       PreparedStatement stmt = prepareStatement("ParameterDescriptor", "SELECT PARAMETER_CODE, PARAMETER_NAME, DESCRIPTION FROM [Coordinate_Operation Parameter] WHERE PARAMETER_CODE = ?");
/* 2078 */       stmt.setInt(1, Integer.parseInt(primaryKey));
/* 2079 */       ResultSet result = stmt.executeQuery();
/* 2080 */       while (result.next()) {
/*      */         Unit unit;
/*      */         Class<?> type;
/* 2081 */         String epsg = getString(result, 1, code);
/* 2082 */         String name = getString(result, 2, code);
/* 2083 */         String remarks = result.getString(3);
/* 2092 */         PreparedStatement units = prepareStatement("ParameterUnit", "SELECT MIN(UOM_CODE) AS UOM, MIN(PARAM_VALUE_FILE_REF) AS FILEREF FROM [Coordinate_Operation Parameter Value] WHERE (PARAMETER_CODE = ?) GROUP BY UOM_CODE ORDER BY COUNT(UOM_CODE) DESC");
/* 2099 */         units.setInt(1, Integer.parseInt(epsg));
/* 2100 */         ResultSet resultUnits = units.executeQuery();
/* 2101 */         if (resultUnits.next()) {
/* 2102 */           String element = resultUnits.getString(1);
/* 2103 */           unit = (element != null) ? this.buffered.createUnit(element) : null;
/* 2104 */           element = resultUnits.getString(2);
/* 2105 */           type = (element != null && element.trim().length() != 0) ? URI.class : double.class;
/*      */         } else {
/* 2107 */           unit = null;
/* 2108 */           type = double.class;
/*      */         } 
/* 2110 */         resultUnits.close();
/* 2115 */         Map<String, Object> properties = createProperties(name, epsg, remarks);
/* 2116 */         DefaultParameterDescriptor defaultParameterDescriptor = new DefaultParameterDescriptor(properties, type, null, null, null, null, unit, true);
/* 2118 */         returnValue = (ParameterDescriptor)ensureSingleton(defaultParameterDescriptor, returnValue, code);
/*      */       } 
/* 2120 */     } catch (SQLException exception) {
/* 2121 */       throw databaseFailure(OperationMethod.class, code, exception);
/*      */     } 
/* 2123 */     if (returnValue == null)
/* 2124 */       throw noSuchAuthorityCode(OperationMethod.class, code); 
/* 2126 */     return returnValue;
/*      */   }
/*      */   
/*      */   private ParameterDescriptor[] createParameterDescriptors(String method) throws FactoryException, SQLException {
/* 2140 */     PreparedStatement stmt = prepareStatement("ParameterDescriptors", "SELECT PARAMETER_CODE FROM [Coordinate_Operation Parameter Usage] WHERE COORD_OP_METHOD_CODE = ? ORDER BY SORT_ORDER");
/* 2145 */     stmt.setInt(1, Integer.parseInt(method));
/* 2146 */     ResultSet results = stmt.executeQuery();
/* 2147 */     List<ParameterDescriptor> descriptors = new ArrayList<ParameterDescriptor>();
/* 2148 */     while (results.next()) {
/* 2149 */       String param = getString(results, 1, method);
/* 2150 */       descriptors.add(this.buffered.createParameterDescriptor(param));
/*      */     } 
/* 2152 */     results.close();
/* 2153 */     return descriptors.<ParameterDescriptor>toArray(new ParameterDescriptor[descriptors.size()]);
/*      */   }
/*      */   
/*      */   private void fillParameterValues(String method, String operation, ParameterValueGroup parameters) throws FactoryException, SQLException {
/* 2170 */     PreparedStatement stmt = prepareStatement("ParameterValues", "SELECT CP.PARAMETER_NAME, CV.PARAMETER_VALUE, CV.PARAM_VALUE_FILE_REF, CV.UOM_CODE FROM ([Coordinate_Operation Parameter Value] AS CV INNER JOIN [Coordinate_Operation Parameter] AS CP ON CV.PARAMETER_CODE = CP.PARAMETER_CODE) INNER JOIN [Coordinate_Operation Parameter Usage] AS CU ON (CP.PARAMETER_CODE = CU.PARAMETER_CODE) AND (CV.COORD_OP_METHOD_CODE = CU.COORD_OP_METHOD_CODE) WHERE CV.COORD_OP_METHOD_CODE = ? AND CV.COORD_OP_CODE = ? ORDER BY CU.SORT_ORDER");
/* 2184 */     stmt.setInt(1, Integer.parseInt(method));
/* 2185 */     stmt.setInt(2, Integer.parseInt(operation));
/* 2186 */     ResultSet result = stmt.executeQuery();
/* 2187 */     while (result.next()) {
/*      */       Unit unit;
/*      */       Object reference;
/*      */       ParameterValue param;
/* 2188 */       String name = getString(result, 1, operation);
/* 2189 */       double value = result.getDouble(2);
/* 2192 */       if (result.wasNull()) {
/* 2197 */         reference = getString(result, 3, operation);
/*      */         try {
/* 2199 */           reference = new URI((String)reference);
/* 2200 */         } catch (URISyntaxException exception) {
/* 2202 */           reference = new File((String)reference);
/*      */         } 
/* 2204 */         unit = null;
/*      */       } else {
/* 2206 */         reference = null;
/* 2207 */         String unitCode = result.getString(4);
/* 2208 */         unit = (unitCode != null) ? this.buffered.createUnit(unitCode) : null;
/*      */       } 
/*      */       try {
/* 2212 */         param = parameters.parameter(name);
/* 2213 */       } catch (ParameterNotFoundException exception) {
/* 2226 */         NoSuchIdentifierException e = new NoSuchIdentifierException(Errors.format(32, name), name);
/* 2228 */         e.initCause((Throwable)exception);
/* 2229 */         throw e;
/*      */       } 
/*      */       try {
/* 2232 */         if (reference != null) {
/* 2233 */           param.setValue(reference);
/*      */           continue;
/*      */         } 
/* 2234 */         if (unit != null) {
/* 2235 */           param.setValue(value, unit);
/*      */           continue;
/*      */         } 
/* 2237 */         param.setValue(value);
/* 2239 */       } catch (InvalidParameterValueException exception) {
/* 2240 */         throw new FactoryException(Errors.format(32, name), exception);
/*      */       } 
/*      */     } 
/* 2244 */     result.close();
/*      */   }
/*      */   
/*      */   public synchronized OperationMethod createOperationMethod(String code) throws FactoryException {
/* 2259 */     ensureNonNull("code", code);
/* 2260 */     OperationMethod returnValue = null;
/*      */     try {
/* 2263 */       String primaryKey = toPrimaryKey(OperationMethod.class, code, "[Coordinate_Operation Method]", "COORD_OP_METHOD_CODE", "COORD_OP_METHOD_NAME");
/* 2265 */       PreparedStatement stmt = prepareStatement("OperationMethod", "SELECT COORD_OP_METHOD_CODE, COORD_OP_METHOD_NAME, FORMULA, REMARKS FROM [Coordinate_Operation Method] WHERE COORD_OP_METHOD_CODE = ?");
/* 2271 */       stmt.setInt(1, Integer.parseInt(primaryKey));
/* 2272 */       ResultSet result = stmt.executeQuery();
/* 2273 */       OperationMethod method = null;
/* 2274 */       while (result.next()) {
/* 2275 */         String epsg = getString(result, 1, code);
/* 2276 */         String name = getString(result, 2, code);
/* 2277 */         String formula = result.getString(3);
/* 2278 */         String remarks = result.getString(4);
/* 2279 */         int encoded = getDimensionsForMethod(epsg);
/* 2280 */         int sourceDimensions = encoded >>> 16;
/* 2281 */         int targetDimensions = encoded & 0xFFFF;
/* 2282 */         ParameterDescriptor[] descriptors = createParameterDescriptors(epsg);
/* 2285 */         GenericName[] aliases = null;
/*      */         try {
/* 2287 */           ParameterValueGroup pvg = this.factories.getMathTransformFactory().getDefaultParameters(name);
/* 2288 */           if (pvg != null && pvg.getDescriptor() != null && pvg.getDescriptor().getAlias() != null)
/* 2289 */             aliases = (GenericName[])pvg.getDescriptor().getAlias().toArray((Object[])new GenericName[pvg.getDescriptor().getAlias().size()]); 
/* 2291 */         } catch (NoSuchIdentifierException e) {}
/* 2294 */         Map<String, Object> properties = addAliases(createProperties(name, epsg, remarks), aliases);
/* 2295 */         if (formula != null)
/* 2296 */           properties.put("formula", formula); 
/* 2299 */         DefaultOperationMethod defaultOperationMethod = new DefaultOperationMethod(properties, sourceDimensions, targetDimensions, (ParameterDescriptorGroup)new DefaultParameterDescriptorGroup(properties, (GeneralParameterDescriptor[])descriptors));
/* 2301 */         returnValue = (OperationMethod)ensureSingleton(defaultOperationMethod, returnValue, code);
/*      */       } 
/* 2303 */     } catch (SQLException exception) {
/* 2304 */       throw databaseFailure(OperationMethod.class, code, exception);
/*      */     } 
/* 2306 */     if (returnValue == null)
/* 2307 */       throw noSuchAuthorityCode(OperationMethod.class, code); 
/* 2309 */     return returnValue;
/*      */   }
/*      */   
/*      */   private Map<String, Object> addAliases(Map<String, Object> properties, GenericName[] aliases) {
/* 2313 */     ensureNonNull("properties", properties);
/* 2314 */     Object value = properties.get("name");
/* 2315 */     ensureNonNull("name", value);
/* 2317 */     if (value instanceof Identifier) {
/* 2318 */       String name = ((Identifier)value).getCode();
/*      */     } else {
/* 2320 */       String name = value.toString();
/*      */     } 
/* 2322 */     if (aliases != null && aliases.length > 0) {
/* 2330 */       int count = aliases.length;
/* 2331 */       value = properties.get("alias");
/* 2332 */       if (value != null) {
/* 2333 */         Map<String, GenericName> merged = new LinkedHashMap<String, GenericName>();
/* 2334 */         putAll(NameFactory.toArray(value), merged);
/* 2335 */         count -= putAll(aliases, merged);
/* 2336 */         Collection<GenericName> c = merged.values();
/* 2337 */         aliases = c.<GenericName>toArray(new GenericName[c.size()]);
/*      */       } 
/* 2343 */       if (count > 0) {
/* 2344 */         Map<String, Object> copy = new HashMap<String, Object>(properties);
/* 2345 */         copy.put("alias", aliases);
/* 2346 */         properties = copy;
/*      */       } 
/*      */     } 
/* 2349 */     return properties;
/*      */   }
/*      */   
/*      */   private static final int putAll(GenericName[] names, Map<String, GenericName> map) {
/* 2359 */     int ignored = 0;
/* 2360 */     for (int i = 0; i < names.length; i++) {
/* 2361 */       GenericName name = names[i];
/* 2362 */       GenericName scoped = name.toFullyQualifiedName();
/* 2363 */       String key = toCaseless(scoped.toString());
/* 2364 */       GenericName old = map.put(key, name);
/* 2365 */       if (old instanceof ScopedName) {
/* 2366 */         map.put(key, old);
/* 2367 */         ignored++;
/*      */       } 
/*      */     } 
/* 2370 */     return ignored;
/*      */   }
/*      */   
/*      */   private static String toCaseless(String key) {
/* 2377 */     return key.replace('_', ' ').trim().toLowerCase();
/*      */   }
/*      */   
/*      */   private int getDimensionsForMethod(String code) throws SQLException {
/* 2389 */     PreparedStatement stmt = prepareStatement("MethodDimensions", "SELECT SOURCE_CRS_CODE, TARGET_CRS_CODE FROM [Coordinate_Operation] WHERE COORD_OP_METHOD_CODE = ? AND SOURCE_CRS_CODE IS NOT NULL AND TARGET_CRS_CODE IS NOT NULL");
/* 2395 */     stmt.setInt(1, Integer.parseInt(code));
/* 2396 */     ResultSet result = stmt.executeQuery();
/* 2397 */     Map<Dimensions, Dimensions> dimensions = new HashMap<Dimensions, Dimensions>();
/* 2398 */     Dimensions temp = new Dimensions(131074);
/* 2399 */     Dimensions max = temp;
/* 2400 */     while (result.next()) {
/* 2401 */       short sourceDimensions = getDimensionForCRS(result.getString(1));
/* 2402 */       short targetDimensions = getDimensionForCRS(result.getString(2));
/* 2403 */       temp.encoded = sourceDimensions << 16 | targetDimensions;
/* 2404 */       Dimensions candidate = dimensions.get(temp);
/* 2405 */       if (candidate == null) {
/* 2406 */         candidate = new Dimensions(temp.encoded);
/* 2407 */         dimensions.put(candidate, candidate);
/*      */       } 
/* 2409 */       if (++candidate.occurences > max.occurences)
/* 2410 */         max = candidate; 
/*      */     } 
/* 2413 */     result.close();
/* 2414 */     return max.encoded;
/*      */   }
/*      */   
/*      */   private static final class Dimensions {
/*      */     int encoded;
/*      */     
/*      */     int occurences;
/*      */     
/*      */     Dimensions(int e) {
/* 2422 */       this.encoded = e;
/*      */     }
/*      */     
/*      */     public int hashCode() {
/* 2425 */       return this.encoded;
/*      */     }
/*      */     
/*      */     public boolean equals(Object object) {
/* 2428 */       return (object instanceof Dimensions && ((Dimensions)object).encoded == this.encoded);
/*      */     }
/*      */     
/*      */     public String toString() {
/* 2431 */       return "[(" + (this.encoded >>> 16) + ',' + (this.encoded & 0xFFFF) + ")×" + this.occurences + ']';
/*      */     }
/*      */   }
/*      */   
/*      */   private short getDimensionForCRS(String code) throws SQLException {
/*      */     short dimension;
/* 2442 */     Short cached = this.axisCounts.get(code);
/* 2444 */     if (cached == null) {
/* 2445 */       PreparedStatement stmt = prepareStatement("Dimension", "  SELECT COUNT(COORD_AXIS_CODE) FROM [Coordinate Axis] WHERE COORD_SYS_CODE = (SELECT COORD_SYS_CODE  FROM [Coordinate Reference System] WHERE COORD_REF_SYS_CODE = ?)");
/* 2451 */       stmt.setString(1, code);
/* 2452 */       ResultSet result = stmt.executeQuery();
/* 2453 */       dimension = result.next() ? result.getShort(1) : 2;
/* 2454 */       this.axisCounts.put(code, Short.valueOf(dimension));
/* 2455 */       result.close();
/*      */     } else {
/* 2457 */       dimension = cached.shortValue();
/*      */     } 
/* 2459 */     return dimension;
/*      */   }
/*      */   
/*      */   final boolean isProjection(String code) throws SQLException {
/* 2469 */     Boolean projection = this.codeProjection.get(code);
/* 2470 */     if (projection == null) {
/* 2471 */       PreparedStatement stmt = prepareStatement("isProjection", "SELECT COORD_REF_SYS_CODE FROM [Coordinate Reference System] WHERE PROJECTION_CONV_CODE = ? AND COORD_REF_SYS_KIND LIKE 'projected%'");
/* 2475 */       stmt.setString(1, code);
/* 2476 */       ResultSet result = stmt.executeQuery();
/* 2477 */       boolean found = result.next();
/* 2478 */       result.close();
/* 2479 */       projection = Boolean.valueOf(found);
/* 2480 */       this.codeProjection.put(code, projection);
/*      */     } 
/* 2482 */     return projection.booleanValue();
/*      */   }
/*      */   
/*      */   public synchronized CoordinateOperation createCoordinateOperation(String code) throws FactoryException {
/* 2500 */     ensureNonNull("code", code);
/* 2501 */     CoordinateOperation returnValue = null;
/* 2502 */     ResultSet result = null;
/*      */     try {
/* 2504 */       String primaryKey = toPrimaryKey(CoordinateOperation.class, code, "[Coordinate_Operation]", "COORD_OP_CODE", "COORD_OP_NAME");
/* 2507 */       PreparedStatement stmt = prepareStatement("CoordinateOperation", "SELECT COORD_OP_CODE, COORD_OP_NAME, COORD_OP_TYPE, SOURCE_CRS_CODE, TARGET_CRS_CODE, COORD_OP_METHOD_CODE, COORD_TFM_VERSION, COORD_OP_ACCURACY, AREA_OF_USE_CODE, COORD_OP_SCOPE, REMARKS FROM [Coordinate_Operation] WHERE COORD_OP_CODE = ?");
/* 2520 */       stmt.setInt(1, Integer.parseInt(primaryKey));
/* 2521 */       result = stmt.executeQuery();
/* 2522 */       while (hasNext(result)) {
/*      */         String sourceCode, targetCode, methodCode;
/*      */         int sourceDimensions, targetDimensions;
/*      */         CoordinateReferenceSystem sourceCRS, targetCRS;
/*      */         boolean isBursaWolf;
/*      */         DefaultOperationMethod defaultOperationMethod;
/*      */         ParameterValueGroup parameters;
/*      */         CoordinateOperation operation;
/* 2523 */         String epsg = getString(result, 1, code);
/* 2524 */         String name = getString(result, 2, code);
/* 2525 */         String type = getString(result, 3, code).trim().toLowerCase();
/* 2526 */         boolean isTransformation = type.equals("transformation");
/* 2527 */         boolean isConversion = type.equals("conversion");
/* 2528 */         boolean isConcatenated = type.equals("concatenated operation");
/* 2530 */         if (isConversion) {
/* 2532 */           sourceCode = result.getString(4);
/* 2533 */           targetCode = result.getString(5);
/*      */         } else {
/* 2535 */           sourceCode = getString(result, 4, code);
/* 2536 */           targetCode = getString(result, 5, code);
/*      */         } 
/* 2538 */         if (isConcatenated) {
/* 2540 */           methodCode = result.getString(6);
/*      */         } else {
/* 2542 */           methodCode = getString(result, 6, code);
/*      */         } 
/* 2544 */         String version = result.getString(7);
/* 2545 */         double accuracy = result.getDouble(8);
/* 2545 */         if (result.wasNull())
/* 2545 */           accuracy = Double.NaN; 
/* 2546 */         String area = result.getString(9);
/* 2547 */         String scope = result.getString(10);
/* 2548 */         String remarks = result.getString(11);
/* 2560 */         if (sourceCode != null) {
/* 2561 */           sourceCRS = this.buffered.createCoordinateReferenceSystem(sourceCode);
/* 2562 */           sourceDimensions = sourceCRS.getCoordinateSystem().getDimension();
/*      */         } else {
/* 2564 */           sourceCRS = null;
/* 2565 */           sourceDimensions = 2;
/*      */         } 
/* 2567 */         if (targetCode != null) {
/* 2568 */           targetCRS = this.buffered.createCoordinateReferenceSystem(targetCode);
/* 2569 */           targetDimensions = targetCRS.getCoordinateSystem().getDimension();
/*      */         } else {
/* 2571 */           targetCRS = null;
/* 2572 */           targetDimensions = 2;
/*      */         } 
/* 2582 */         if (methodCode == null) {
/* 2583 */           isBursaWolf = false;
/* 2584 */           OperationMethod method = null;
/* 2585 */           parameters = null;
/*      */         } else {
/*      */           int num;
/*      */           try {
/* 2589 */             num = Integer.parseInt(methodCode);
/* 2590 */           } catch (NumberFormatException exception) {
/* 2591 */             throw new FactoryException(exception);
/*      */           } 
/* 2593 */           isBursaWolf = (num >= 9603 && num <= 9607);
/* 2596 */           OperationMethod method = this.buffered.createOperationMethod(methodCode);
/* 2597 */           if (method.getSourceDimensions() != sourceDimensions || method.getTargetDimensions() != targetDimensions)
/* 2600 */             defaultOperationMethod = new DefaultOperationMethod(method, sourceDimensions, targetDimensions); 
/* 2609 */           String classe = defaultOperationMethod.getName().getCode();
/* 2610 */           parameters = this.factories.getMathTransformFactory().getDefaultParameters(classe);
/* 2611 */           fillParameterValues(methodCode, epsg, parameters);
/*      */         } 
/* 2622 */         Map<String, Object> properties = createProperties(name, epsg, area, scope, remarks);
/* 2623 */         if (version != null && (version = version.trim()).length() != 0)
/* 2624 */           properties.put("operationVersion", version); 
/* 2626 */         if (!Double.isNaN(accuracy)) {
/* 2629 */           QuantitativeResultImpl accuracyResult = new QuantitativeResultImpl(new double[] { accuracy });
/* 2633 */           accuracyResult.setValueUnit(SI.METER);
/* 2634 */           AbsoluteExternalPositionalAccuracyImpl accuracyElement = new AbsoluteExternalPositionalAccuracyImpl((Result)accuracyResult);
/* 2635 */           accuracyElement.setMeasureDescription(TRANSFORMATION_ACCURACY);
/* 2636 */           accuracyElement.setEvaluationMethodType(EvaluationMethodType.DIRECT_EXTERNAL);
/* 2637 */           properties.put("coordinateOperationAccuracy", new PositionalAccuracy[] { (PositionalAccuracy)accuracyElement.unmodifiable() });
/*      */         } 
/* 2651 */         if (isConversion && (sourceCRS == null || targetCRS == null)) {
/* 2654 */           DefiningConversion definingConversion = new DefiningConversion(properties, (OperationMethod)defaultOperationMethod, parameters);
/*      */         } else {
/*      */           Class<Conversion> clazz;
/* 2655 */           if (isConcatenated) {
/* 2665 */             result = null;
/* 2666 */             PreparedStatement cstmt = prepareStatement("ConcatenatedOperation", "SELECT SINGLE_OPERATION_CODE FROM [Coordinate_Operation Path] WHERE (CONCAT_OPERATION_CODE = ?) ORDER BY OP_PATH_STEP");
/* 2671 */             cstmt.setString(1, epsg);
/* 2672 */             ResultSet cr = cstmt.executeQuery();
/* 2673 */             List<String> codes = new ArrayList<String>();
/* 2674 */             while (cr.next())
/* 2675 */               codes.add(cr.getString(1)); 
/* 2677 */             cr.close();
/* 2678 */             CoordinateOperation[] operations = new CoordinateOperation[codes.size()];
/* 2679 */             if (!this.safetyGuard.add(epsg))
/* 2680 */               throw recursiveCall(ConcatenatedOperation.class, epsg); 
/*      */             try {
/* 2682 */               for (int i = 0; i < operations.length; i++)
/* 2683 */                 operations[i] = this.buffered.createCoordinateOperation(codes.get(i)); 
/*      */             } finally {
/* 2686 */               this.safetyGuard.remove(epsg);
/*      */             } 
/*      */             try {
/* 2689 */               return (CoordinateOperation)new DefaultConcatenatedOperation(properties, operations);
/* 2690 */             } catch (IllegalArgumentException exception) {
/* 2693 */               throw new FactoryException(exception);
/*      */             } 
/*      */           } 
/* 2702 */           if (isBursaWolf)
/*      */             try {
/* 2703 */               Ellipsoid ellipsoid = CRSUtilities.getHeadGeoEllipsoid(sourceCRS);
/* 2704 */               if (ellipsoid != null) {
/* 2705 */                 Unit axisUnit = ellipsoid.getAxisUnit();
/* 2706 */                 parameters.parameter("src_semi_major").setValue(ellipsoid.getSemiMajorAxis(), axisUnit);
/* 2707 */                 parameters.parameter("src_semi_minor").setValue(ellipsoid.getSemiMinorAxis(), axisUnit);
/* 2708 */                 parameters.parameter("src_dim").setValue(sourceCRS.getCoordinateSystem().getDimension());
/*      */               } 
/* 2710 */               ellipsoid = CRSUtilities.getHeadGeoEllipsoid(targetCRS);
/* 2711 */               if (ellipsoid != null) {
/* 2712 */                 Unit axisUnit = ellipsoid.getAxisUnit();
/* 2713 */                 parameters.parameter("tgt_semi_major").setValue(ellipsoid.getSemiMajorAxis(), axisUnit);
/* 2714 */                 parameters.parameter("tgt_semi_minor").setValue(ellipsoid.getSemiMinorAxis(), axisUnit);
/* 2715 */                 parameters.parameter("tgt_dim").setValue(targetCRS.getCoordinateSystem().getDimension());
/*      */               } 
/* 2717 */             } catch (ParameterNotFoundException exception) {
/* 2718 */               throw new FactoryException(Errors.format(52, defaultOperationMethod.getName().getCode(), exception));
/*      */             }  
/* 2727 */           if (isTransformation) {
/* 2728 */             Class<Transformation> clazz1 = Transformation.class;
/* 2729 */           } else if (isConversion) {
/* 2730 */             clazz = Conversion.class;
/*      */           } else {
/* 2732 */             throw new FactoryException(Errors.format(187, type));
/*      */           } 
/* 2734 */           MathTransform mt = this.factories.getMathTransformFactory().createBaseToDerived(sourceCRS, parameters, targetCRS.getCoordinateSystem());
/* 2737 */           operation = DefaultOperation.create(properties, sourceCRS, targetCRS, mt, (OperationMethod)defaultOperationMethod, clazz);
/*      */         } 
/* 2740 */         returnValue = ensureSingleton(operation, returnValue, code);
/*      */       } 
/* 2742 */     } catch (SQLException exception) {
/* 2743 */       throw databaseFailure(CoordinateOperation.class, code, exception);
/*      */     } finally {
/* 2745 */       if (result != null)
/*      */         try {
/* 2747 */           result.close();
/* 2748 */         } catch (Exception e) {} 
/*      */     } 
/* 2753 */     if (returnValue == null)
/* 2754 */       throw noSuchAuthorityCode(CoordinateOperation.class, code); 
/* 2756 */     return returnValue;
/*      */   }
/*      */   
/*      */   private boolean hasNext(ResultSet result) throws SQLException {
/*      */     try {
/* 2765 */       return result.next();
/* 2766 */     } catch (SQLException e) {
/* 2767 */       if (result.isClosed())
/* 2768 */         return false; 
/* 2770 */       throw e;
/*      */     } 
/*      */   }
/*      */   
/*      */   public synchronized Set createFromCoordinateReferenceSystemCodes(String sourceCode, String targetCode) throws FactoryException {
/* 2795 */     ensureNonNull("sourceCode", sourceCode);
/* 2796 */     ensureNonNull("targetCode", targetCode);
/* 2797 */     String pair = sourceCode + " ⇨ " + targetCode;
/* 2798 */     CoordinateOperationSet set = new CoordinateOperationSet((AuthorityFactory)this.buffered);
/*      */     try {
/* 2800 */       String sourceKey = toPrimaryKeyCRS(sourceCode);
/* 2801 */       String targetKey = toPrimaryKeyCRS(targetCode);
/* 2802 */       boolean searchTransformations = false;
/*      */       do {
/*      */         String key, sql;
/* 2811 */         if (searchTransformations) {
/* 2812 */           key = "TransformationFromCRS";
/* 2813 */           sql = "SELECT COORD_OP_CODE FROM [Coordinate_Operation] left join [Area] on [Coordinate_Operation].area_of_use_code = [Area].area_code WHERE SOURCE_CRS_CODE = ? AND TARGET_CRS_CODE = ? ORDER BY ABS([Coordinate_Operation].DEPRECATED), COORD_OP_ACCURACY,\t(AREA_NORTH_BOUND_LAT - AREA_SOUTH_BOUND_LAT) *  (CASE WHEN AREA_EAST_BOUND_LON > AREA_WEST_BOUND_LON      THEN (AREA_EAST_BOUND_LON - AREA_WEST_BOUND_LON)      ELSE (360 - AREA_WEST_BOUND_LON - AREA_EAST_BOUND_LON) END) DESC, COORD_OP_CODE DESC";
/*      */         } else {
/* 2824 */           key = "ConversionFromCRS";
/* 2825 */           sql = "SELECT PROJECTION_CONV_CODE FROM [Coordinate Reference System] WHERE SOURCE_GEOGCRS_CODE = ? AND COORD_REF_SYS_CODE = ?";
/*      */         } 
/* 2830 */         PreparedStatement stmt = prepareStatement(key, sql);
/* 2831 */         stmt.setString(1, sourceKey);
/* 2832 */         stmt.setString(2, targetKey);
/* 2833 */         ResultSet result = stmt.executeQuery();
/* 2834 */         while (result.next()) {
/* 2835 */           String code = getString(result, 1, pair);
/* 2836 */           set.addAuthorityCode(code, searchTransformations ? null : targetKey);
/*      */         } 
/* 2838 */         result.close();
/* 2839 */       } while ((searchTransformations = !searchTransformations) == true);
/* 2845 */       String[] codes = set.getAuthorityCodes();
/* 2846 */       sort((Object[])codes);
/* 2847 */       set.setAuthorityCodes(codes);
/* 2848 */     } catch (SQLException exception) {
/* 2849 */       throw databaseFailure(CoordinateOperation.class, pair, exception);
/*      */     } 
/* 2856 */     set.resolve(1);
/* 2857 */     return (Set)set;
/*      */   }
/*      */   
/*      */   private void sort(Object[] codes) throws SQLException, FactoryException {
/* 2873 */     if (codes.length <= 1)
/*      */       return; 
/* 2877 */     PreparedStatement stmt = prepareStatement("Supersession", "SELECT SUPERSEDED_BY FROM [Supersession] WHERE OBJECT_CODE = ? ORDER BY SUPERSESSION_YEAR DESC");
/* 2881 */     int maxIterations = 15;
/*      */     while (true) {
/* 2883 */       boolean changed = false;
/* 2884 */       for (int i = 0; i < codes.length; i++) {
/* 2885 */         String code = codes[i].toString();
/* 2886 */         stmt.setInt(1, Integer.parseInt(code));
/* 2887 */         ResultSet result = stmt.executeQuery();
/* 2888 */         while (result.next()) {
/* 2889 */           String replacement = getString(result, 1, code);
/* 2890 */           for (int j = i + 1; j < codes.length; j++) {
/* 2891 */             Object candidate = codes[j];
/* 2892 */             if (replacement.equals(candidate.toString())) {
/* 2896 */               System.arraycopy(codes, i, codes, i + 1, j - i);
/* 2897 */               codes[i++] = candidate;
/* 2898 */               changed = true;
/*      */             } 
/*      */           } 
/*      */         } 
/* 2902 */         result.close();
/*      */       } 
/* 2904 */       if (!changed)
/*      */         return; 
/* 2908 */       if (--maxIterations == 0) {
/* 2909 */         LOGGER.finer("Possible recursivity in supersessions.");
/*      */         return;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public IdentifiedObjectFinder getIdentifiedObjectFinder(Class type) throws FactoryException {
/* 2923 */     return new Finder(this.buffered, type);
/*      */   }
/*      */   
/*      */   private final class Finder extends IdentifiedObjectFinder {
/*      */     Finder(AbstractAuthorityFactory buffered, Class type) {
/* 2938 */       super((AuthorityFactory)buffered, type);
/*      */     }
/*      */     
/*      */     protected Set getCodeCandidates(IdentifiedObject object) throws FactoryException {
/* 2948 */       String where, code, select = "COORD_REF_SYS_CODE";
/* 2949 */       String from = "[Coordinate Reference System]";
/* 2951 */       if (object instanceof Ellipsoid) {
/* 2952 */         select = "ELLIPSOID_CODE";
/* 2953 */         from = "[Ellipsoid]";
/* 2954 */         where = "SEMI_MAJOR_AXIS";
/* 2955 */         code = Double.toString(((Ellipsoid)object).getSemiMajorAxis());
/*      */       } else {
/*      */         Ellipsoid ellipsoid;
/* 2958 */         if (object instanceof GeneralDerivedCRS) {
/* 2959 */           CoordinateReferenceSystem coordinateReferenceSystem = ((GeneralDerivedCRS)object).getBaseCRS();
/* 2960 */           where = "SOURCE_GEOGCRS_CODE";
/* 2961 */         } else if (object instanceof SingleCRS) {
/* 2962 */           Datum datum = ((SingleCRS)object).getDatum();
/* 2963 */           where = "DATUM_CODE";
/* 2964 */         } else if (object instanceof GeodeticDatum) {
/* 2965 */           ellipsoid = ((GeodeticDatum)object).getEllipsoid();
/* 2966 */           select = "DATUM_CODE";
/* 2967 */           from = "[Datum]";
/* 2968 */           where = "ELLIPSOID_CODE";
/*      */         } else {
/* 2970 */           return super.getCodeCandidates(object);
/*      */         } 
/* 2972 */         IdentifiedObject dependency = DirectEpsgFactory.this.buffered.getIdentifiedObjectFinder(ellipsoid.getClass()).find((IdentifiedObject)ellipsoid);
/* 2973 */         ReferenceIdentifier referenceIdentifier = AbstractIdentifiedObject.getIdentifier(dependency, getAuthority());
/* 2974 */         if (referenceIdentifier == null || (code = referenceIdentifier.getCode()) == null)
/* 2975 */           return super.getCodeCandidates(object); 
/*      */       } 
/* 2978 */       String sql = "SELECT " + select + " FROM " + from + " WHERE " + where + "='" + code + '\'';
/* 2979 */       sql = DirectEpsgFactory.this.adaptSQL(sql);
/* 2980 */       Set<String> result = new LinkedHashSet<String>();
/*      */       try {
/* 2982 */         Statement s = DirectEpsgFactory.this.getConnection().createStatement();
/* 2983 */         ResultSet r = s.executeQuery(sql);
/* 2984 */         while (r.next())
/* 2985 */           result.add(r.getString(1)); 
/* 2987 */         r.close();
/* 2988 */         s.close();
/* 2989 */       } catch (SQLException exception) {
/* 2990 */         throw DirectEpsgFactory.databaseFailure(Identifier.class, code, exception);
/*      */       } 
/* 2992 */       return result;
/*      */     }
/*      */   }
/*      */   
/*      */   private static FactoryException recursiveCall(Class type, String code) {
/* 3000 */     return new FactoryException(Errors.format(164, type, code));
/*      */   }
/*      */   
/*      */   private static FactoryException databaseFailure(Class type, String code, SQLException cause) {
/* 3009 */     return new FactoryException(Errors.format(38, type, code), cause);
/*      */   }
/*      */   
/*      */   protected boolean isPrimaryKey(String code) throws FactoryException {
/* 3049 */     int length = code.length();
/* 3050 */     for (int i = 0; i < length; i++) {
/* 3051 */       char c = code.charAt(i);
/* 3052 */       if (!Character.isDigit(c) && !Character.isSpaceChar(c))
/* 3053 */         return false; 
/*      */     } 
/* 3056 */     return true;
/*      */   }
/*      */   
/*      */   final synchronized boolean canDispose() {
/* 3066 */     boolean can = true;
/* 3067 */     Map<SoftReference, WeakReference<AuthorityCodes>> pool = null;
/* 3068 */     Iterator<Map.Entry<Class<?>, Reference<AuthorityCodes>>> it = this.authorityCodes.entrySet().iterator();
/* 3069 */     while (it.hasNext()) {
/* 3071 */       Map.Entry<Class<?>, Reference<AuthorityCodes>> entry = it.next();
/* 3072 */       Reference<AuthorityCodes> reference = entry.getValue();
/* 3073 */       AuthorityCodes codes = reference.get();
/* 3074 */       if (codes == null) {
/* 3075 */         it.remove();
/*      */         continue;
/*      */       } 
/* 3084 */       can = false;
/* 3085 */       if (reference instanceof SoftReference) {
/* 3089 */         if (pool == null)
/* 3090 */           pool = new IdentityHashMap<SoftReference, WeakReference<AuthorityCodes>>(); 
/* 3092 */         WeakReference<AuthorityCodes> weak = pool.get(reference);
/* 3093 */         if (weak == null) {
/* 3094 */           weak = new WeakReference<AuthorityCodes>(codes);
/* 3095 */           pool.put((SoftReference)reference, weak);
/*      */         } 
/* 3097 */         entry.setValue(weak);
/*      */       } 
/*      */     } 
/* 3100 */     return can;
/*      */   }
/*      */   
/*      */   public synchronized void dispose() throws FactoryException {
/*      */     boolean isClosed;
/*      */     try {
/* 3112 */       Connection connection = getConnection();
/* 3113 */       isClosed = connection.isClosed();
/* 3114 */       Iterator<Reference<AuthorityCodes>> iterator = this.authorityCodes.values().iterator();
/* 3115 */       while (iterator.hasNext()) {
/* 3117 */         AuthorityCodes set = ((Reference<AuthorityCodes>)iterator.next()).get();
/* 3118 */         if (set != null)
/* 3119 */           set.finalize(); 
/* 3121 */         iterator.remove();
/*      */       } 
/* 3123 */       for (Iterator<PreparedStatement> it = this.statements.values().iterator(); it.hasNext(); ) {
/* 3124 */         ((PreparedStatement)it.next()).close();
/* 3125 */         it.remove();
/*      */       } 
/* 3127 */       shutdown(true);
/* 3128 */       connection.close();
/* 3129 */       this.dataSource = null;
/* 3130 */     } catch (SQLException exception) {
/* 3131 */       throw new FactoryException(exception);
/*      */     } 
/* 3133 */     super.dispose();
/*      */     try {
/* 3135 */       shutdown(false);
/* 3136 */     } catch (SQLException exception) {
/* 3137 */       throw new FactoryException(exception);
/*      */     } 
/* 3139 */     if (!isClosed) {
/* 3145 */       LogRecord record = Loggings.format(Level.FINE, 12);
/* 3146 */       record.setLoggerName(LOGGER.getName());
/* 3147 */       LOGGER.log(record);
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void shutdown(boolean active) throws SQLException {}
/*      */   
/*      */   protected final void finalize() throws Throwable {
/* 3184 */     dispose();
/* 3185 */     super.finalize();
/*      */   }
/*      */   
/*      */   protected synchronized Connection getConnection() throws SQLException {
/* 3195 */     if (this.connection == null) {
/* 3196 */       this.connection = this.dataSource.getConnection();
/* 3198 */     } else if (this.connection.isClosed() || !isConnectionValid(this.connection)) {
/* 3199 */       this.statements.clear();
/*      */       try {
/* 3205 */         this.connection.close();
/* 3206 */       } catch (Exception e) {
/* 3207 */         LOGGER.log(Level.FINER, "Error occurred while closing an invalid connection", e);
/*      */       } 
/* 3210 */       this.connection = this.dataSource.getConnection();
/*      */     } 
/* 3213 */     return this.connection;
/*      */   }
/*      */   
/*      */   protected boolean isConnectionValid(Connection conn) {
/* 3226 */     if (this.validationQuery == null)
/* 3227 */       return true; 
/* 3229 */     Statement st = null;
/*      */     try {
/* 3231 */       st = conn.createStatement();
/* 3232 */       st.execute(this.validationQuery);
/* 3233 */     } catch (SQLException e) {
/* 3234 */       return false;
/*      */     } finally {
/* 3236 */       if (st != null)
/*      */         try {
/* 3238 */           st.close();
/* 3239 */         } catch (SQLException e) {} 
/*      */     } 
/* 3243 */     return true;
/*      */   }
/*      */   
/*      */   public String getValidationQuery() {
/* 3251 */     return this.validationQuery;
/*      */   }
/*      */   
/*      */   public void setValidationQuery(String validationQuery) {
/* 3261 */     this.validationQuery = validationQuery;
/*      */   }
/*      */   
/*      */   protected abstract String adaptSQL(String paramString);
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\factory\epsg\DirectEpsgFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */