/*      */ package org.geotools.referencing.factory.epsg;
/*      */ 
/*      */ import java.awt.RenderingHints;
/*      */ import java.io.File;
/*      */ import java.io.ObjectStreamException;
/*      */ import java.io.Serializable;
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
/*      */ import java.util.AbstractMap;
/*      */ import java.util.AbstractSet;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Calendar;
/*      */ import java.util.Collections;
/*      */ import java.util.HashMap;
/*      */ import java.util.HashSet;
/*      */ import java.util.IdentityHashMap;
/*      */ import java.util.LinkedHashSet;
/*      */ import java.util.List;
/*      */ import java.util.NoSuchElementException;
/*      */ import java.util.Set;
/*      */ import java.util.logging.Level;
/*      */ import java.util.logging.LogRecord;
/*      */ import javax.measure.unit.NonSI;
/*      */ import javax.measure.unit.SI;
/*      */ import javax.measure.unit.Unit;
/*      */ import javax.naming.NamingException;
/*      */ import javax.sql.DataSource;
/*      */ import org.geotools.factory.GeoTools;
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
/*      */ import org.geotools.referencing.factory.AbstractCachedAuthorityFactory;
/*      */ import org.geotools.referencing.factory.IdentifiedObjectFinder;
/*      */ import org.geotools.referencing.factory.ReferencingFactory;
/*      */ import org.geotools.referencing.operation.DefaultConcatenatedOperation;
/*      */ import org.geotools.referencing.operation.DefaultOperation;
/*      */ import org.geotools.referencing.operation.DefaultOperationMethod;
/*      */ import org.geotools.referencing.operation.DefiningConversion;
/*      */ import org.geotools.resources.CRSUtilities;
/*      */ import org.geotools.resources.i18n.Errors;
/*      */ import org.geotools.resources.i18n.Loggings;
/*      */ import org.geotools.resources.i18n.Vocabulary;
/*      */ import org.geotools.util.LocalName;
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
/*      */ import org.opengis.referencing.operation.MathTransform;
/*      */ import org.opengis.referencing.operation.OperationMethod;
/*      */ import org.opengis.referencing.operation.Projection;
/*      */ import org.opengis.referencing.operation.Transformation;
/*      */ import org.opengis.util.GenericName;
/*      */ import org.opengis.util.InternationalString;
/*      */ 
/*      */ public abstract class AbstractEpsgFactory extends AbstractCachedAuthorityFactory {
/*      */   private static final int BURSA_WOLF_MIN_CODE = 9603;
/*      */   
/*      */   private static final int BURSA_WOLF_MAX_CODE = 9607;
/*      */   
/*      */   private static final int ROTATION_FRAME_CODE = 9607;
/*      */   
/*      */   private static final int DUMMY_OPERATION = 1;
/*      */   
/*  194 */   private static final InternationalString TRANSFORMATION_ACCURACY = Vocabulary.formatInternational(223);
/*      */   
/*  203 */   private final Calendar calendar = Calendar.getInstance();
/*      */   
/*      */   private transient Citation authority;
/*      */   
/*      */   protected DataSource dataSource;
/*      */   
/*      */   private Connection connection;
/*      */   
/*  235 */   private final java.util.Map<String, PreparedStatement> statements = new IdentityHashMap<String, PreparedStatement>();
/*      */   
/*  242 */   private int lastObjectType = -1;
/*      */   
/*      */   private transient String lastTableForName;
/*      */   
/*  256 */   private final java.util.Map<String, AxisName> axisNames = new HashMap<String, AxisName>();
/*      */   
/*  264 */   private final java.util.Map<String, Short> axisCounts = new HashMap<String, Short>();
/*      */   
/*  272 */   private final java.util.Map<String, Boolean> codeProjection = new HashMap<String, Boolean>();
/*      */   
/*  278 */   private final java.util.Map<String, LocalName> scopes = new HashMap<String, LocalName>();
/*      */   
/*  284 */   private final java.util.Map<String, Object> properties = new HashMap<String, Object>();
/*      */   
/*  292 */   private final Set<String> safetyGuard = new HashSet<String>();
/*      */   
/*      */   public AbstractEpsgFactory(Hints userHints) throws FactoryException {
/*  295 */     super(80);
/*  298 */     this.hints.put(Hints.FORCE_LONGITUDE_FIRST_AXIS_ORDER, Boolean.FALSE);
/*  299 */     this.hints.put(Hints.FORCE_STANDARD_AXIS_DIRECTIONS, Boolean.FALSE);
/*  300 */     this.hints.put(Hints.FORCE_STANDARD_AXIS_UNITS, Boolean.FALSE);
/*  304 */     if (userHints != null) {
/*  305 */       Object hint = userHints.get(Hints.EPSG_DATA_SOURCE);
/*  306 */       if (hint instanceof String) {
/*  307 */         String name = (String)hint;
/*      */         try {
/*  309 */           this.dataSource = (DataSource)GeoTools.getInitialContext(userHints).lookup(name);
/*  310 */         } catch (NamingException e) {
/*  311 */           throw new FactoryException("A EPSG_DATA_SOURCE hint is required:" + e);
/*      */         } 
/*  313 */         this.hints.put(Hints.EPSG_DATA_SOURCE, this.dataSource);
/*  315 */       } else if (hint instanceof DataSource) {
/*  316 */         this.dataSource = (DataSource)hint;
/*  317 */         this.hints.put(Hints.EPSG_DATA_SOURCE, this.dataSource);
/*      */       } else {
/*  320 */         throw new FactoryException("A EPSG_DATA_SOURCE hint is required.");
/*      */       } 
/*      */     } else {
/*  324 */       throw new FactoryException("A EPSG_DATA_SOURCE hint is required.");
/*      */     } 
/*      */   }
/*      */   
/*      */   public AbstractEpsgFactory(Hints userHints, DataSource dataSource) {
/*  329 */     super(80);
/*  331 */     this.dataSource = dataSource;
/*  334 */     this.hints.put(Hints.FORCE_LONGITUDE_FIRST_AXIS_ORDER, Boolean.FALSE);
/*  335 */     this.hints.put(Hints.FORCE_STANDARD_AXIS_DIRECTIONS, Boolean.FALSE);
/*  336 */     this.hints.put(Hints.FORCE_STANDARD_AXIS_UNITS, Boolean.FALSE);
/*  337 */     this.hints.put(Hints.EPSG_DATA_SOURCE, dataSource);
/*      */   }
/*      */   
/*      */   public AbstractEpsgFactory(Hints userHints, Connection connection) {
/*  346 */     super(80, userHints);
/*  349 */     this.hints.put(Hints.FORCE_LONGITUDE_FIRST_AXIS_ORDER, Boolean.FALSE);
/*  350 */     this.hints.put(Hints.FORCE_STANDARD_AXIS_DIRECTIONS, Boolean.FALSE);
/*  351 */     this.hints.put(Hints.FORCE_STANDARD_AXIS_UNITS, Boolean.FALSE);
/*  352 */     this.connection = connection;
/*  353 */     ensureNonNull("connection", connection);
/*      */   }
/*      */   
/*      */   public synchronized Citation getAuthority() {
/*  362 */     if (this.authority == null)
/*      */       try {
/*  363 */         String query = adaptSQL("SELECT VERSION_NUMBER, VERSION_DATE FROM [Version History] ORDER BY VERSION_DATE DESC");
/*  365 */         DatabaseMetaData metadata = getConnection().getMetaData();
/*  366 */         Statement statement = getConnection().createStatement();
/*  367 */         ResultSet result = statement.executeQuery(query);
/*  368 */         if (result.next()) {
/*  369 */           String version = result.getString(1);
/*  370 */           Date date = result.getDate(2);
/*  371 */           String engine = metadata.getDatabaseProductName();
/*  372 */           CitationImpl c = new CitationImpl(Citations.EPSG);
/*  373 */           c.getAlternateTitles().add(Vocabulary.formatInternational(37, "EPSG", version, engine));
/*  375 */           c.setEdition((InternationalString)new SimpleInternationalString(version));
/*  376 */           c.setEditionDate(date);
/*  377 */           this.authority = (Citation)c.unmodifiable();
/*  378 */           this.hints.put(Hints.VERSION, new Version(version));
/*      */         } else {
/*  380 */           this.authority = Citations.EPSG;
/*      */         } 
/*  382 */         result.close();
/*  383 */         statement.close();
/*  384 */       } catch (SQLException exception) {
/*  385 */         Logging.unexpectedException(LOGGER, AbstractEpsgFactory.class, "getAuthority", exception);
/*  386 */         return Citations.EPSG;
/*      */       }  
/*  388 */     return this.authority;
/*      */   }
/*      */   
/*      */   public synchronized String getBackingStoreDescription() throws FactoryException {
/*  398 */     Citation authority = getAuthority();
/*  399 */     TableWriter table = new TableWriter(null, " ");
/*  400 */     Vocabulary resources = Vocabulary.getResources(null);
/*      */     InternationalString internationalString;
/*  402 */     if ((internationalString = authority.getEdition()) != null) {
/*  403 */       table.write(resources.getString(239, "EPSG"));
/*  404 */       table.write(58);
/*  405 */       table.nextColumn();
/*  406 */       table.write(internationalString.toString());
/*  407 */       table.nextLine();
/*      */     } 
/*      */     try {
/*  411 */       DatabaseMetaData metadata = getConnection().getMetaData();
/*      */       String s;
/*  412 */       if ((s = metadata.getDatabaseProductName()) != null) {
/*  413 */         table.write(resources.getLabel(35));
/*  414 */         table.nextColumn();
/*  415 */         table.write(s);
/*  416 */         if ((s = metadata.getDatabaseProductVersion()) != null) {
/*  417 */           table.write(32);
/*  418 */           table.write(resources.getString(238, s));
/*      */         } 
/*  420 */         table.nextLine();
/*      */       } 
/*  422 */       if ((s = metadata.getURL()) != null) {
/*  423 */         table.write(resources.getLabel(36));
/*  424 */         table.nextColumn();
/*  425 */         table.write(s);
/*  426 */         table.nextLine();
/*      */       } 
/*  428 */     } catch (SQLException exception) {
/*  429 */       throw new FactoryException(exception);
/*      */     } 
/*  431 */     return table.toString();
/*      */   }
/*      */   
/*      */   public java.util.Map<RenderingHints.Key, ?> getImplementationHints() {
/*  441 */     if (this.authority == null)
/*  443 */       getAuthority(); 
/*  445 */     return super.getImplementationHints();
/*      */   }
/*      */   
/*      */   protected synchronized Set<String> generateAuthorityCodes(Class<?> type) throws FactoryException {
/*  458 */     Set<String> result = new HashSet<String>();
/*  459 */     for (int i = 0; i < TABLES_INFO.length; i++) {
/*  460 */       TableInfo table = TABLES_INFO[i];
/*  461 */       if (table.isTypeOf(type)) {
/*  462 */         AuthorityCodeSet codes = new AuthorityCodeSet(TABLES_INFO[i], type);
/*  463 */         result.addAll(codes);
/*      */       } 
/*      */     } 
/*  466 */     return result;
/*      */   }
/*      */   
/*      */   public InternationalString getDescriptionText(String code) throws FactoryException {
/*  479 */     IdentifiedObject identifiedObject = createObject(code);
/*  480 */     ReferenceIdentifier referenceIdentifier = identifiedObject.getName();
/*  481 */     if (referenceIdentifier instanceof GenericName)
/*  482 */       return ((GenericName)referenceIdentifier).toInternationalString(); 
/*  484 */     return (InternationalString)new SimpleInternationalString(referenceIdentifier.getCode());
/*      */   }
/*      */   
/*      */   private PreparedStatement prepareStatement(String key, String sql) throws SQLException {
/*  503 */     assert Thread.holdsLock(this);
/*  504 */     PreparedStatement stmt = this.statements.get(key);
/*  505 */     if (stmt == null) {
/*  506 */       stmt = getConnection().prepareStatement(adaptSQL(sql));
/*  507 */       this.statements.put(key, stmt);
/*      */     } 
/*  509 */     return stmt;
/*      */   }
/*      */   
/*      */   private static String getString(ResultSet result, int columnIndex, String code) throws SQLException, FactoryException {
/*  527 */     String value = result.getString(columnIndex);
/*  528 */     ensureNonNull(result, columnIndex, code);
/*  529 */     return value.trim();
/*      */   }
/*      */   
/*      */   private static String getString(ResultSet result, int columnIndex, String code, int columnFault) throws SQLException, FactoryException {
/*  540 */     String str = result.getString(columnIndex);
/*  541 */     if (result.wasNull()) {
/*  542 */       ResultSetMetaData metadata = result.getMetaData();
/*  543 */       String column = metadata.getColumnName(columnFault);
/*  544 */       String table = metadata.getTableName(columnFault);
/*  545 */       result.close();
/*  546 */       throw new FactoryException(Errors.format(147, code, column, table));
/*      */     } 
/*  549 */     return str.trim();
/*      */   }
/*      */   
/*      */   private static double getDouble(ResultSet result, int columnIndex, String code) throws SQLException, FactoryException {
/*  567 */     double value = result.getDouble(columnIndex);
/*  568 */     ensureNonNull(result, columnIndex, code);
/*  569 */     return value;
/*      */   }
/*      */   
/*      */   private static int getInt(ResultSet result, int columnIndex, String code) throws SQLException, FactoryException {
/*  587 */     int value = result.getInt(columnIndex);
/*  588 */     ensureNonNull(result, columnIndex, code);
/*  589 */     return value;
/*      */   }
/*      */   
/*      */   private static void ensureNonNull(ResultSet result, int columnIndex, String code) throws SQLException, FactoryException {
/*  599 */     if (result.wasNull()) {
/*  600 */       ResultSetMetaData metadata = result.getMetaData();
/*  601 */       String column = metadata.getColumnName(columnIndex);
/*  602 */       String table = metadata.getTableName(columnIndex);
/*  603 */       result.close();
/*  604 */       throw new FactoryException(Errors.format(147, code, column, table));
/*      */     } 
/*      */   }
/*      */   
/*      */   private String toPrimaryKey(Class type, String code, String table, String codeColumn, String nameColumn) throws SQLException, FactoryException {
/*  634 */     assert Thread.holdsLock(this);
/*  635 */     String identifier = trimAuthority(code);
/*  636 */     if (!isPrimaryKey(identifier)) {
/*  642 */       String KEY = "NumericalIdentifier";
/*  643 */       PreparedStatement statement = this.statements.get("NumericalIdentifier");
/*  644 */       if (statement != null && 
/*  645 */         !table.equals(this.lastTableForName)) {
/*  646 */         this.statements.remove("NumericalIdentifier");
/*  647 */         statement.close();
/*  648 */         statement = null;
/*  649 */         this.lastTableForName = null;
/*      */       } 
/*  652 */       if (statement == null) {
/*  653 */         String query = "SELECT " + codeColumn + " FROM " + table + " WHERE " + nameColumn + " = ?";
/*  655 */         statement = getConnection().prepareStatement(adaptSQL(query));
/*  656 */         this.statements.put("NumericalIdentifier", statement);
/*      */       } 
/*  658 */       statement.setString(1, identifier);
/*  659 */       identifier = null;
/*  660 */       ResultSet result = statement.executeQuery();
/*  661 */       while (result.next())
/*  662 */         identifier = ensureSingleton(result.getString(1), identifier, code); 
/*  664 */       result.close();
/*  665 */       if (identifier == null)
/*  666 */         throw noSuchAuthorityCode(type, code); 
/*      */     } 
/*  669 */     return identifier;
/*      */   }
/*      */   
/*      */   private static <T> T ensureSingleton(T newValue, T oldValue, String code) throws FactoryException {
/*  691 */     if (oldValue == null)
/*  692 */       return newValue; 
/*  694 */     if (oldValue.equals(newValue))
/*  695 */       return oldValue; 
/*  697 */     throw new FactoryException(Errors.format(44, code));
/*      */   }
/*      */   
/*      */   private java.util.Map<String, Object> generateProperties(String name, String code, String remarks) throws SQLException, FactoryException {
/*  712 */     this.properties.clear();
/*  713 */     Citation authority = getAuthority();
/*  714 */     if (name != null)
/*  715 */       this.properties.put("name", new NamedIdentifier(authority, name.trim())); 
/*  718 */     if (code != null) {
/*  719 */       InternationalString edition = authority.getEdition();
/*  720 */       String version = (edition != null) ? edition.toString() : null;
/*  721 */       this.properties.put("identifiers", new NamedIdentifier(authority, code.trim(), version));
/*      */     } 
/*  724 */     if (remarks != null && (remarks = remarks.trim()).length() != 0)
/*  725 */       this.properties.put("remarks", remarks); 
/*  730 */     List<GenericName> alias = null;
/*  732 */     PreparedStatement stmt = prepareStatement("Alias", "SELECT NAMING_SYSTEM_NAME, ALIAS FROM [Alias] INNER JOIN [Naming System] ON [Alias].NAMING_SYSTEM_CODE = [Naming System].NAMING_SYSTEM_CODE WHERE OBJECT_CODE = ?");
/*  737 */     stmt.setString(1, code);
/*  738 */     ResultSet result = stmt.executeQuery();
/*  739 */     while (result.next()) {
/*      */       ScopedName scopedName;
/*  740 */       String scope = result.getString(1);
/*  741 */       String local = getString(result, 2, code);
/*  743 */       if (scope == null) {
/*  744 */         LocalName localName = new LocalName(local);
/*      */       } else {
/*  746 */         LocalName cached = this.scopes.get(scope);
/*  747 */         if (cached == null) {
/*  748 */           cached = new LocalName(scope);
/*  749 */           this.scopes.put(scope, cached);
/*      */         } 
/*  751 */         scopedName = new ScopedName((GenericName)cached, local);
/*      */       } 
/*  753 */       if (alias == null)
/*  754 */         alias = new ArrayList<GenericName>(); 
/*  756 */       alias.add(scopedName);
/*      */     } 
/*  758 */     result.close();
/*  759 */     if (alias != null)
/*  760 */       this.properties.put("alias", alias.toArray(new GenericName[alias.size()])); 
/*  763 */     return this.properties;
/*      */   }
/*      */   
/*      */   private java.util.Map<String, Object> generateProperties(String name, String code, String area, String scope, String remarks) throws SQLException, FactoryException {
/*  781 */     java.util.Map<String, Object> properties = generateProperties(name, code, remarks);
/*  782 */     if (area != null && (area = area.trim()).length() != 0) {
/*  783 */       Extent extent = generateExtent(area);
/*  784 */       properties.put("domainOfValidity", extent);
/*      */     } 
/*  786 */     if (scope != null && (scope = scope.trim()).length() != 0)
/*  787 */       properties.put("scope", scope); 
/*  789 */     return properties;
/*      */   }
/*      */   
/*      */   public synchronized IdentifiedObject generateObject(String code) throws FactoryException {
/*  805 */     ensureNonNull("code", code);
/*  806 */     String KEY = "IdentifiedObject";
/*  807 */     PreparedStatement stmt = this.statements.get("IdentifiedObject");
/*  808 */     StringBuilder query = null;
/*  817 */     String epsg = trimAuthority(code);
/*  818 */     boolean isPrimaryKey = isPrimaryKey(epsg);
/*  819 */     int tupleToSkip = isPrimaryKey ? this.lastObjectType : -1;
/*  820 */     int index = -1;
/*  821 */     for (int i = -1; i < TABLES_INFO.length; i++) {
/*  822 */       if (i == tupleToSkip)
/*      */         continue; 
/*      */       try {
/*  829 */         if (i >= 0) {
/*  830 */           TableInfo table = TABLES_INFO[i];
/*  831 */           String column = isPrimaryKey ? table.codeColumn : table.nameColumn;
/*  832 */           if (column == null)
/*      */             continue; 
/*  835 */           if (query == null)
/*  836 */             query = new StringBuilder("SELECT "); 
/*  838 */           query.setLength(7);
/*  839 */           query.append(table.codeColumn);
/*  840 */           query.append(" FROM ");
/*  841 */           query.append(table.table);
/*  842 */           query.append(" WHERE ");
/*  843 */           query.append(column);
/*  844 */           query.append(" = ?");
/*  845 */           if (isPrimaryKey) {
/*  846 */             assert !this.statements.containsKey("IdentifiedObject") : table;
/*  847 */             stmt = prepareStatement("IdentifiedObject", query.toString());
/*      */           } else {
/*  850 */             stmt = getConnection().prepareStatement(adaptSQL(query.toString()));
/*      */           } 
/*      */         } 
/*  859 */         stmt.setString(1, epsg);
/*  860 */         ResultSet result = stmt.executeQuery();
/*  861 */         boolean present = result.next();
/*  862 */         result.close();
/*  863 */         if (present) {
/*  864 */           if (index >= 0)
/*  865 */             throw new FactoryException(Errors.format(44, code)); 
/*  867 */           index = (i < 0) ? this.lastObjectType : i;
/*  868 */           if (isPrimaryKey)
/*      */             break; 
/*      */         } 
/*  874 */         if (isPrimaryKey && 
/*  875 */           this.statements.remove("IdentifiedObject") == null)
/*  876 */           throw new AssertionError(code); 
/*  879 */         stmt.close();
/*  880 */       } catch (SQLException exception) {
/*  881 */         throw databaseFailure(IdentifiedObject.class, code, exception);
/*      */       } 
/*      */       continue;
/*      */     } 
/*  887 */     if (isPrimaryKey)
/*  888 */       this.lastObjectType = index; 
/*  890 */     if (index >= 0) {
/*  891 */       switch (index) {
/*      */         case 0:
/*  892 */           return (IdentifiedObject)createCoordinateReferenceSystem(code);
/*      */         case 1:
/*  893 */           return (IdentifiedObject)createCoordinateSystem(code);
/*      */         case 2:
/*  894 */           return (IdentifiedObject)createCoordinateSystemAxis(code);
/*      */         case 3:
/*  895 */           return (IdentifiedObject)createDatum(code);
/*      */         case 4:
/*  896 */           return (IdentifiedObject)createEllipsoid(code);
/*      */         case 5:
/*  897 */           return (IdentifiedObject)createPrimeMeridian(code);
/*      */         case 6:
/*  898 */           return (IdentifiedObject)createCoordinateOperation(code);
/*      */         case 7:
/*  899 */           return (IdentifiedObject)generateOperationMethod(code);
/*      */         case 8:
/*  900 */           return (IdentifiedObject)generateParameterDescriptor(code);
/*      */         case 9:
/*  905 */           return createObject(code);
/*      */       } 
/*      */       throw new AssertionError(index);
/*      */     } 
/*      */   }
/*      */   
/*      */   public synchronized Unit<?> generateUnit(String code) throws FactoryException {
/*  920 */     ensureNonNull("code", code);
/*  921 */     Unit<?> returnValue = null;
/*      */     try {
/*  923 */       String primaryKey = toPrimaryKey(Unit.class, code, "[Unit of Measure]", "UOM_CODE", "UNIT_OF_MEAS_NAME");
/*  926 */       PreparedStatement stmt = prepareStatement("Unit", "SELECT UOM_CODE, FACTOR_B, FACTOR_C, TARGET_UOM_CODE FROM [Unit of Measure] WHERE UOM_CODE = ?");
/*  932 */       stmt.setString(1, primaryKey);
/*  933 */       ResultSet result = stmt.executeQuery();
/*  934 */       while (result.next()) {
/*  935 */         int source = getInt(result, 1, code);
/*  936 */         double b = result.getDouble(2);
/*  937 */         double c = result.getDouble(3);
/*  938 */         int target = getInt(result, 4, code);
/*  939 */         Unit<?> base = getUnit(target);
/*  940 */         if (base == null)
/*  941 */           throw noSuchAuthorityCode(Unit.class, String.valueOf(target)); 
/*  943 */         Unit<?> unit = getUnit(source);
/*  944 */         if (unit == null)
/*  946 */           if (b != 0.0D && c != 0.0D) {
/*  947 */             unit = (b == c) ? base : base.times(b / c);
/*      */           } else {
/*  950 */             throw new FactoryException("Unsupported unit: " + code);
/*      */           }  
/*  952 */         returnValue = ensureSingleton(unit, returnValue, code);
/*      */       } 
/*  954 */       result.close();
/*  956 */     } catch (SQLException exception) {
/*  957 */       throw databaseFailure(Unit.class, code, exception);
/*      */     } 
/*  959 */     if (returnValue == null)
/*  960 */       throw noSuchAuthorityCode(Unit.class, code); 
/*  962 */     return returnValue;
/*      */   }
/*      */   
/*      */   public synchronized Ellipsoid generateEllipsoid(String code) throws FactoryException {
/*  977 */     ensureNonNull("code", code);
/*  978 */     Ellipsoid returnValue = null;
/*      */     try {
/*  980 */       String primaryKey = toPrimaryKey(Ellipsoid.class, code, "[Ellipsoid]", "ELLIPSOID_CODE", "ELLIPSOID_NAME");
/*  983 */       PreparedStatement stmt = prepareStatement("Ellipsoid", "SELECT ELLIPSOID_CODE, ELLIPSOID_NAME, SEMI_MAJOR_AXIS, INV_FLATTENING, SEMI_MINOR_AXIS, UOM_CODE, REMARKS FROM [Ellipsoid] WHERE ELLIPSOID_CODE = ?");
/*  992 */       stmt.setString(1, primaryKey);
/*  993 */       ResultSet result = stmt.executeQuery();
/*  994 */       while (result.next()) {
/*      */         Ellipsoid ellipsoid;
/* 1000 */         String epsg = getString(result, 1, code);
/* 1001 */         String name = getString(result, 2, code);
/* 1002 */         double semiMajorAxis = getDouble(result, 3, code);
/* 1003 */         double inverseFlattening = result.getDouble(4);
/* 1004 */         double semiMinorAxis = result.getDouble(5);
/* 1005 */         String unitCode = getString(result, 6, code);
/* 1006 */         String remarks = result.getString(7);
/* 1007 */         Unit unit = createUnit(unitCode);
/* 1008 */         java.util.Map<String, Object> properties = generateProperties(name, epsg, remarks);
/* 1010 */         if (inverseFlattening == 0.0D) {
/* 1011 */           if (semiMinorAxis == 0.0D) {
/* 1013 */             String column = result.getMetaData().getColumnName(3);
/* 1014 */             result.close();
/* 1015 */             throw new FactoryException(Errors.format(147, code, column));
/*      */           } 
/* 1018 */           ellipsoid = this.factories.getDatumFactory().createEllipsoid(properties, semiMajorAxis, semiMinorAxis, unit);
/*      */         } else {
/* 1022 */           if (semiMinorAxis != 0.0D) {
/* 1025 */             LogRecord record = Loggings.format(Level.WARNING, 1, code);
/* 1026 */             record.setLoggerName(LOGGER.getName());
/* 1027 */             LOGGER.log(record);
/*      */           } 
/* 1029 */           ellipsoid = this.factories.getDatumFactory().createFlattenedSphere(properties, semiMajorAxis, inverseFlattening, unit);
/*      */         } 
/* 1036 */         returnValue = ensureSingleton(ellipsoid, returnValue, code);
/*      */       } 
/* 1038 */       result.close();
/* 1039 */     } catch (SQLException exception) {
/* 1040 */       throw databaseFailure(Ellipsoid.class, code, exception);
/*      */     } 
/* 1042 */     if (returnValue == null)
/* 1043 */       throw noSuchAuthorityCode(Ellipsoid.class, code); 
/* 1045 */     return returnValue;
/*      */   }
/*      */   
/*      */   public synchronized PrimeMeridian generatePrimeMeridian(String code) throws FactoryException {
/* 1060 */     ensureNonNull("code", code);
/* 1061 */     PrimeMeridian returnValue = null;
/*      */     try {
/* 1063 */       String primaryKey = toPrimaryKey(PrimeMeridian.class, code, "[Prime Meridian]", "PRIME_MERIDIAN_CODE", "PRIME_MERIDIAN_NAME");
/* 1066 */       PreparedStatement stmt = prepareStatement("PrimeMeridian", "SELECT PRIME_MERIDIAN_CODE, PRIME_MERIDIAN_NAME, GREENWICH_LONGITUDE, UOM_CODE, REMARKS FROM [Prime Meridian] WHERE PRIME_MERIDIAN_CODE = ?");
/* 1073 */       stmt.setString(1, primaryKey);
/* 1074 */       ResultSet result = stmt.executeQuery();
/* 1075 */       while (result.next()) {
/* 1076 */         String epsg = getString(result, 1, code);
/* 1077 */         String name = getString(result, 2, code);
/* 1078 */         double longitude = getDouble(result, 3, code);
/* 1079 */         String unit_code = getString(result, 4, code);
/* 1080 */         String remarks = result.getString(5);
/* 1081 */         Unit unit = createUnit(unit_code);
/* 1082 */         java.util.Map<String, Object> properties = generateProperties(name, epsg, remarks);
/* 1083 */         PrimeMeridian primeMeridian = this.factories.getDatumFactory().createPrimeMeridian(properties, longitude, unit);
/* 1085 */         returnValue = ensureSingleton(primeMeridian, returnValue, code);
/*      */       } 
/* 1087 */       result.close();
/* 1088 */     } catch (SQLException exception) {
/* 1089 */       throw databaseFailure(PrimeMeridian.class, code, exception);
/*      */     } 
/* 1091 */     if (returnValue == null)
/* 1092 */       throw noSuchAuthorityCode(PrimeMeridian.class, code); 
/* 1094 */     return returnValue;
/*      */   }
/*      */   
/*      */   public synchronized Extent generateExtent(String code) throws FactoryException {
/* 1109 */     ensureNonNull("code", code);
/* 1110 */     Extent returnValue = null;
/*      */     try {
/* 1112 */       String primaryKey = toPrimaryKey(Extent.class, code, "[Area]", "AREA_CODE", "AREA_NAME");
/* 1115 */       PreparedStatement stmt = prepareStatement("Area", "SELECT AREA_OF_USE, AREA_SOUTH_BOUND_LAT, AREA_NORTH_BOUND_LAT, AREA_WEST_BOUND_LON, AREA_EAST_BOUND_LON FROM [Area] WHERE AREA_CODE = ?");
/* 1122 */       stmt.setString(1, primaryKey);
/* 1123 */       ResultSet result = stmt.executeQuery();
/* 1124 */       while (result.next()) {
/* 1125 */         ExtentImpl extent = null;
/* 1126 */         String description = result.getString(1);
/* 1127 */         if (description != null) {
/* 1128 */           extent = new ExtentImpl();
/* 1129 */           extent.setDescription((InternationalString)new SimpleInternationalString(description));
/*      */         } 
/* 1131 */         double ymin = result.getDouble(2);
/* 1132 */         if (!result.wasNull()) {
/* 1133 */           double ymax = result.getDouble(3);
/* 1134 */           if (!result.wasNull()) {
/* 1135 */             double xmin = result.getDouble(4);
/* 1136 */             if (!result.wasNull()) {
/* 1137 */               double xmax = result.getDouble(5);
/* 1138 */               if (!result.wasNull()) {
/* 1139 */                 if (extent == null)
/* 1140 */                   extent = new ExtentImpl(); 
/* 1142 */                 extent.setGeographicElements(Collections.singleton(new GeographicBoundingBoxImpl(xmin, xmax, ymin, ymax)));
/*      */               } 
/*      */             } 
/*      */           } 
/*      */         } 
/* 1148 */         if (extent != null)
/* 1149 */           returnValue = (Extent)ensureSingleton(extent.unmodifiable(), returnValue, code); 
/*      */       } 
/* 1152 */       result.close();
/* 1153 */     } catch (SQLException exception) {
/* 1154 */       throw databaseFailure(Extent.class, code, exception);
/*      */     } 
/* 1156 */     if (returnValue == null)
/* 1157 */       throw noSuchAuthorityCode(Extent.class, code); 
/* 1159 */     return returnValue;
/*      */   }
/*      */   
/*      */   private BursaWolfParameters[] generateBursaWolfParameters(String code, ResultSet toClose) throws SQLException, FactoryException {
/* 1179 */     if (this.safetyGuard.contains(code))
/* 1185 */       return null; 
/* 1188 */     PreparedStatement stmt = prepareStatement("BursaWolfParametersSet", "SELECT CO.COORD_OP_CODE, CO.COORD_OP_METHOD_CODE, CRS2.DATUM_CODE FROM [Coordinate_Operation] AS CO INNER JOIN [Coordinate Reference System] AS CRS2 ON CO.TARGET_CRS_CODE = CRS2.COORD_REF_SYS_CODE WHERE CO.COORD_OP_METHOD_CODE >= 9603 AND CO.COORD_OP_METHOD_CODE <= 9607 AND CO.COORD_OP_CODE <> 1 AND CO.SOURCE_CRS_CODE IN ( SELECT CRS1.COORD_REF_SYS_CODE  FROM [Coordinate Reference System] AS CRS1  WHERE CRS1.DATUM_CODE = ?) ORDER BY CRS2.DATUM_CODE, ABS(CO.DEPRECATED), CO.COORD_OP_ACCURACY, CO.COORD_OP_CODE DESC");
/* 1205 */     stmt.setString(1, code);
/* 1206 */     ResultSet result = stmt.executeQuery();
/* 1207 */     List<Object> bwInfos = null;
/* 1208 */     while (result.next()) {
/* 1209 */       String operation = getString(result, 1, code);
/* 1210 */       int method = getInt(result, 2, code);
/* 1211 */       String datum = getString(result, 3, code);
/* 1212 */       if (bwInfos == null)
/* 1213 */         bwInfos = new ArrayList(); 
/* 1215 */       bwInfos.add(new BursaWolfInfo(operation, method, datum));
/*      */     } 
/* 1217 */     result.close();
/* 1218 */     if (bwInfos == null)
/* 1220 */       return null; 
/* 1222 */     toClose.close();
/* 1228 */     int size = bwInfos.size();
/* 1229 */     if (size > 1) {
/* 1230 */       BursaWolfInfo[] codes = bwInfos.<BursaWolfInfo>toArray(new BursaWolfInfo[size]);
/* 1231 */       sort((Object[])codes);
/* 1232 */       bwInfos.clear();
/* 1233 */       Set<String> added = new HashSet<String>();
/* 1234 */       for (int j = 0; j < codes.length; j++) {
/* 1235 */         BursaWolfInfo candidate = codes[j];
/* 1236 */         if (added.add(candidate.target))
/* 1237 */           bwInfos.add(candidate); 
/*      */       } 
/* 1240 */       size = bwInfos.size();
/*      */     } 
/* 1248 */     stmt = prepareStatement("BursaWolfParameters", "SELECT PARAMETER_CODE, PARAMETER_VALUE, UOM_CODE FROM [Coordinate_Operation Parameter Value] WHERE COORD_OP_CODE = ? AND COORD_OP_METHOD_CODE = ?");
/* 1254 */     for (int i = 0; i < size; i++) {
/*      */       GeodeticDatum datum;
/* 1255 */       BursaWolfInfo info = (BursaWolfInfo)bwInfos.get(i);
/*      */       try {
/* 1258 */         this.safetyGuard.add(code);
/* 1259 */         datum = createGeodeticDatum(info.target);
/*      */       } finally {
/* 1261 */         this.safetyGuard.remove(code);
/*      */       } 
/* 1263 */       BursaWolfParameters parameters = new BursaWolfParameters(datum);
/* 1264 */       stmt.setString(1, info.operation);
/* 1265 */       stmt.setInt(2, info.method);
/* 1266 */       result = stmt.executeQuery();
/* 1267 */       while (result.next())
/* 1268 */         setBursaWolfParameter(parameters, getInt(result, 1, info.operation), getDouble(result, 2, info.operation), createUnit(getString(result, 3, info.operation))); 
/* 1273 */       result.close();
/* 1274 */       if (info.method == 9607) {
/* 1277 */         parameters.ex = -parameters.ex;
/* 1278 */         parameters.ey = -parameters.ey;
/* 1279 */         parameters.ey = -parameters.ey;
/*      */       } 
/* 1281 */       bwInfos.set(i, parameters);
/*      */     } 
/* 1283 */     return bwInfos.<BursaWolfParameters>toArray(new BursaWolfParameters[size]);
/*      */   }
/*      */   
/*      */   public synchronized Datum generateDatum(String code) throws FactoryException {
/* 1300 */     ensureNonNull("code", code);
/* 1301 */     Datum returnValue = null;
/*      */     try {
/* 1303 */       String primaryKey = toPrimaryKey(Datum.class, code, "[Datum]", "DATUM_CODE", "DATUM_NAME");
/* 1306 */       PreparedStatement stmt = prepareStatement("Datum", "SELECT DATUM_CODE, DATUM_NAME, DATUM_TYPE, ORIGIN_DESCRIPTION, REALIZATION_EPOCH, AREA_OF_USE_CODE, DATUM_SCOPE, REMARKS, ELLIPSOID_CODE, PRIME_MERIDIAN_CODE FROM [Datum] WHERE DATUM_CODE = ?");
/* 1318 */       stmt.setString(1, primaryKey);
/* 1319 */       ResultSet result = stmt.executeQuery();
/* 1320 */       while (result.next()) {
/*      */         EngineeringDatum engineeringDatum;
/* 1321 */         String epsg = getString(result, 1, code);
/* 1322 */         String name = getString(result, 2, code);
/* 1323 */         String type = getString(result, 3, code).trim().toLowerCase();
/* 1324 */         String anchor = result.getString(4);
/* 1325 */         String epoch = result.getString(5);
/* 1326 */         String area = result.getString(6);
/* 1327 */         String scope = result.getString(7);
/* 1328 */         String remarks = result.getString(8);
/* 1329 */         java.util.Map<String, Object> properties = generateProperties(name, epsg, area, scope, remarks);
/* 1330 */         if (anchor != null)
/* 1331 */           properties.put("anchorPoint", anchor); 
/* 1333 */         if (epoch != null && epoch.length() != 0)
/*      */           try {
/* 1334 */             this.calendar.clear();
/* 1335 */             this.calendar.set(Integer.parseInt(epoch), 0, 1);
/* 1336 */             properties.put("realizationEpoch", this.calendar.getTime());
/* 1337 */           } catch (NumberFormatException exception) {
/* 1339 */             Logging.unexpectedException(LOGGER, AbstractEpsgFactory.class, "createDatum", exception);
/*      */           }  
/* 1341 */         DatumFactory factory = this.factories.getDatumFactory();
/* 1355 */         if (type.equals("geodetic")) {
/* 1356 */           properties = new HashMap<String, Object>(properties);
/* 1357 */           Ellipsoid ellipsoid = createEllipsoid(getString(result, 9, code));
/* 1358 */           PrimeMeridian meridian = createPrimeMeridian(getString(result, 10, code));
/* 1359 */           BursaWolfParameters[] param = generateBursaWolfParameters(primaryKey, result);
/* 1361 */           if (param != null) {
/* 1362 */             result = null;
/* 1363 */             properties.put("bursaWolf", param);
/*      */           } 
/* 1365 */           GeodeticDatum geodeticDatum = factory.createGeodeticDatum(properties, ellipsoid, meridian);
/* 1366 */         } else if (type.equals("vertical")) {
/* 1368 */           VerticalDatum verticalDatum = factory.createVerticalDatum(properties, VerticalDatumType.GEOIDAL);
/* 1369 */         } else if (type.equals("engineering")) {
/* 1370 */           engineeringDatum = factory.createEngineeringDatum(properties);
/*      */         } else {
/* 1372 */           result.close();
/* 1373 */           throw new FactoryException(Errors.format(187, type));
/*      */         } 
/* 1375 */         returnValue = (Datum)ensureSingleton(engineeringDatum, returnValue, code);
/* 1376 */         if (result == null)
/* 1379 */           return returnValue; 
/*      */       } 
/* 1382 */       result.close();
/* 1383 */     } catch (SQLException exception) {
/* 1384 */       throw databaseFailure(Datum.class, code, exception);
/*      */     } 
/* 1386 */     if (returnValue == null)
/* 1387 */       throw noSuchAuthorityCode(Datum.class, code); 
/* 1389 */     return returnValue;
/*      */   }
/*      */   
/*      */   private AxisName getAxisName(String code) throws FactoryException {
/* 1398 */     assert Thread.holdsLock(this);
/* 1399 */     AxisName returnValue = this.axisNames.get(code);
/* 1400 */     if (returnValue == null)
/*      */       try {
/* 1402 */         PreparedStatement stmt = prepareStatement("AxisName", "SELECT COORD_AXIS_NAME, DESCRIPTION, REMARKS FROM [Coordinate Axis Name] WHERE COORD_AXIS_NAME_CODE = ?");
/* 1405 */         stmt.setString(1, code);
/* 1406 */         ResultSet result = stmt.executeQuery();
/* 1407 */         while (result.next()) {
/* 1408 */           String name = getString(result, 1, code);
/* 1409 */           String description = result.getString(2);
/* 1410 */           String remarks = result.getString(3);
/* 1411 */           if (description == null) {
/* 1412 */             description = remarks;
/* 1413 */           } else if (remarks != null) {
/* 1414 */             description = description + System.getProperty("line.separator", "\n") + remarks;
/*      */           } 
/* 1416 */           AxisName axis = new AxisName(name, description);
/* 1417 */           returnValue = ensureSingleton(axis, returnValue, code);
/*      */         } 
/* 1419 */         result.close();
/* 1420 */         if (returnValue == null)
/* 1421 */           throw noSuchAuthorityCode(AxisName.class, code); 
/* 1423 */         this.axisNames.put(code, returnValue);
/* 1424 */       } catch (SQLException exception) {
/* 1425 */         throw databaseFailure(AxisName.class, code, exception);
/*      */       }  
/* 1427 */     return returnValue;
/*      */   }
/*      */   
/*      */   public synchronized CoordinateSystemAxis generateCoordinateSystemAxis(String code) throws FactoryException {
/* 1440 */     ensureNonNull("code", code);
/* 1441 */     CoordinateSystemAxis returnValue = null;
/*      */     try {
/* 1443 */       String primaryKey = trimAuthority(code);
/* 1445 */       PreparedStatement stmt = prepareStatement("Axis", "SELECT COORD_AXIS_CODE, COORD_AXIS_NAME_CODE, COORD_AXIS_ORIENTATION, COORD_AXIS_ABBREVIATION, UOM_CODE FROM [Coordinate Axis] WHERE COORD_AXIS_CODE = ?");
/* 1452 */       stmt.setString(1, code);
/* 1453 */       ResultSet result = stmt.executeQuery();
/* 1454 */       while (result.next()) {
/*      */         AxisDirection direction;
/* 1455 */         String epsg = getString(result, 1, code);
/* 1456 */         String nameCode = getString(result, 2, code);
/* 1457 */         String orientation = getString(result, 3, code);
/* 1458 */         String abbreviation = getString(result, 4, code);
/* 1459 */         String unit = getString(result, 5, code);
/*      */         try {
/* 1462 */           direction = DefaultCoordinateSystemAxis.getDirection(orientation);
/* 1463 */         } catch (NoSuchElementException exception) {
/* 1464 */           if (orientation.equalsIgnoreCase("Geocentre > equator/PM")) {
/* 1465 */             direction = AxisDirection.OTHER;
/* 1466 */           } else if (orientation.equalsIgnoreCase("Geocentre > equator/90dE")) {
/* 1467 */             direction = AxisDirection.EAST;
/* 1468 */           } else if (orientation.equalsIgnoreCase("Geocentre > north pole")) {
/* 1469 */             direction = AxisDirection.NORTH;
/*      */           } else {
/* 1471 */             throw new FactoryException(exception);
/*      */           } 
/*      */         } 
/* 1474 */         AxisName an = getAxisName(nameCode);
/* 1475 */         java.util.Map<String, Object> properties = generateProperties(an.name, epsg, an.description);
/* 1476 */         CSFactory factory = this.factories.getCSFactory();
/* 1477 */         CoordinateSystemAxis axis = factory.createCoordinateSystemAxis(properties, abbreviation, direction, createUnit(unit));
/* 1479 */         returnValue = ensureSingleton(axis, returnValue, code);
/*      */       } 
/* 1481 */       result.close();
/* 1482 */     } catch (SQLException exception) {
/* 1483 */       throw databaseFailure(CoordinateSystemAxis.class, code, exception);
/*      */     } 
/* 1485 */     if (returnValue == null)
/* 1486 */       throw noSuchAuthorityCode(CoordinateSystemAxis.class, code); 
/* 1488 */     return returnValue;
/*      */   }
/*      */   
/*      */   private CoordinateSystemAxis[] generateAxisForCoordinateSystem(String code, int dimension) throws SQLException, FactoryException {
/* 1506 */     assert Thread.holdsLock(this);
/* 1507 */     CoordinateSystemAxis[] axis = new CoordinateSystemAxis[dimension];
/* 1509 */     PreparedStatement stmt = prepareStatement("AxisOrder", "SELECT COORD_AXIS_CODE FROM [Coordinate Axis] WHERE COORD_SYS_CODE = ? ORDER BY [ORDER]");
/* 1515 */     stmt.setString(1, code);
/* 1516 */     ResultSet result = stmt.executeQuery();
/* 1517 */     int i = 0;
/* 1518 */     while (result.next()) {
/* 1519 */       String axisCode = getString(result, 1, code);
/* 1520 */       if (i < axis.length)
/* 1523 */         axis[i] = createCoordinateSystemAxis(axisCode); 
/* 1525 */       i++;
/*      */     } 
/* 1527 */     result.close();
/* 1528 */     if (i != axis.length)
/* 1529 */       throw new FactoryException(Errors.format(93, Integer.valueOf(axis.length), Integer.valueOf(i))); 
/* 1532 */     return axis;
/*      */   }
/*      */   
/*      */   public synchronized CoordinateSystem generateCoordinateSystem(String code) throws FactoryException {
/* 1547 */     ensureNonNull("code", code);
/* 1548 */     CoordinateSystem returnValue = null;
/*      */     try {
/* 1551 */       String primaryKey = toPrimaryKey(CoordinateSystem.class, code, "[Coordinate System]", "COORD_SYS_CODE", "COORD_SYS_NAME");
/* 1553 */       PreparedStatement stmt = prepareStatement("CoordinateSystem", "SELECT COORD_SYS_CODE, COORD_SYS_NAME, COORD_SYS_TYPE, DIMENSION, REMARKS FROM [Coordinate System] WHERE COORD_SYS_CODE = ?");
/* 1560 */       stmt.setString(1, primaryKey);
/* 1561 */       ResultSet result = stmt.executeQuery();
/* 1562 */       while (result.next()) {
/*      */         AffineCS affineCS;
/* 1563 */         String epsg = getString(result, 1, code);
/* 1564 */         String name = getString(result, 2, code);
/* 1565 */         String type = getString(result, 3, code).trim().toLowerCase();
/* 1566 */         int dimension = getInt(result, 4, code);
/* 1567 */         String remarks = result.getString(5);
/* 1568 */         CoordinateSystemAxis[] axis = generateAxisForCoordinateSystem(primaryKey, dimension);
/* 1569 */         java.util.Map<String, Object> properties = generateProperties(name, epsg, remarks);
/* 1570 */         CSFactory factory = this.factories.getCSFactory();
/* 1571 */         CoordinateSystem cs = null;
/* 1572 */         if (type.equals("ellipsoidal")) {
/*      */           EllipsoidalCS ellipsoidalCS;
/* 1573 */           switch (dimension) {
/*      */             case 2:
/* 1574 */               ellipsoidalCS = factory.createEllipsoidalCS(properties, axis[0], axis[1]);
/*      */               break;
/*      */             case 3:
/* 1575 */               ellipsoidalCS = factory.createEllipsoidalCS(properties, axis[0], axis[1], axis[2]);
/*      */               break;
/*      */           } 
/* 1577 */         } else if (type.equals("cartesian")) {
/*      */           CartesianCS cartesianCS;
/* 1578 */           switch (dimension) {
/*      */             case 2:
/* 1579 */               cartesianCS = factory.createCartesianCS(properties, axis[0], axis[1]);
/*      */               break;
/*      */             case 3:
/* 1580 */               cartesianCS = factory.createCartesianCS(properties, axis[0], axis[1], axis[2]);
/*      */               break;
/*      */           } 
/* 1582 */         } else if (type.equals("spherical")) {
/*      */           SphericalCS sphericalCS;
/* 1583 */           switch (dimension) {
/*      */             case 3:
/* 1584 */               sphericalCS = factory.createSphericalCS(properties, axis[0], axis[1], axis[2]);
/*      */               break;
/*      */           } 
/* 1586 */         } else if (type.equals("vertical") || type.equals("gravity-related")) {
/*      */           VerticalCS verticalCS;
/* 1587 */           switch (dimension) {
/*      */             case 1:
/* 1588 */               verticalCS = factory.createVerticalCS(properties, axis[0]);
/*      */               break;
/*      */           } 
/* 1590 */         } else if (type.equals("linear")) {
/*      */           LinearCS linearCS;
/* 1591 */           switch (dimension) {
/*      */             case 1:
/* 1592 */               linearCS = factory.createLinearCS(properties, axis[0]);
/*      */               break;
/*      */           } 
/* 1594 */         } else if (type.equals("polar")) {
/*      */           PolarCS polarCS;
/* 1595 */           switch (dimension) {
/*      */             case 2:
/* 1596 */               polarCS = factory.createPolarCS(properties, axis[0], axis[1]);
/*      */               break;
/*      */           } 
/* 1598 */         } else if (type.equals("cylindrical")) {
/*      */           CylindricalCS cylindricalCS;
/* 1599 */           switch (dimension) {
/*      */             case 3:
/* 1600 */               cylindricalCS = factory.createCylindricalCS(properties, axis[0], axis[1], axis[2]);
/*      */               break;
/*      */           } 
/* 1602 */         } else if (type.equals("affine")) {
/* 1603 */           switch (dimension) {
/*      */             case 2:
/* 1604 */               affineCS = factory.createAffineCS(properties, axis[0], axis[1]);
/*      */               break;
/*      */             case 3:
/* 1605 */               affineCS = factory.createAffineCS(properties, axis[0], axis[1], axis[2]);
/*      */               break;
/*      */           } 
/*      */         } else {
/* 1608 */           result.close();
/* 1609 */           throw new FactoryException(Errors.format(187, type));
/*      */         } 
/* 1611 */         if (affineCS == null) {
/* 1612 */           result.close();
/* 1613 */           throw new FactoryException(Errors.format(173, type));
/*      */         } 
/* 1616 */         returnValue = (CoordinateSystem)ensureSingleton(affineCS, returnValue, code);
/*      */       } 
/* 1618 */       result.close();
/* 1619 */     } catch (SQLException exception) {
/* 1620 */       throw databaseFailure(CoordinateSystem.class, code, exception);
/*      */     } 
/* 1622 */     if (returnValue == null)
/* 1623 */       throw noSuchAuthorityCode(CoordinateSystem.class, code); 
/* 1625 */     return returnValue;
/*      */   }
/*      */   
/*      */   private String toPrimaryKeyCRS(String code) throws SQLException, FactoryException {
/* 1635 */     return toPrimaryKey(CoordinateReferenceSystem.class, code, "[Coordinate Reference System]", "COORD_REF_SYS_CODE", "COORD_REF_SYS_NAME");
/*      */   }
/*      */   
/*      */   public synchronized CoordinateReferenceSystem generateCoordinateReferenceSystem(String code) throws FactoryException {
/* 1651 */     ensureNonNull("code", code);
/* 1652 */     CoordinateReferenceSystem returnValue = null;
/*      */     try {
/* 1654 */       String primaryKey = toPrimaryKeyCRS(code);
/* 1656 */       PreparedStatement stmt = prepareStatement("CoordinateReferenceSystem", "SELECT COORD_REF_SYS_CODE, COORD_REF_SYS_NAME, AREA_OF_USE_CODE, CRS_SCOPE, REMARKS, COORD_REF_SYS_KIND, COORD_SYS_CODE, DATUM_CODE, SOURCE_GEOGCRS_CODE, PROJECTION_CONV_CODE, CMPD_HORIZCRS_CODE, CMPD_VERTCRS_CODE FROM [Coordinate Reference System] WHERE COORD_REF_SYS_CODE = ?");
/* 1671 */       stmt.setString(1, primaryKey);
/* 1672 */       ResultSet result = stmt.executeQuery();
/* 1673 */       while (result.next()) {
/*      */         EngineeringCRS engineeringCRS;
/* 1674 */         String epsg = getString(result, 1, code);
/* 1675 */         String name = getString(result, 2, code);
/* 1676 */         String area = result.getString(3);
/* 1677 */         String scope = result.getString(4);
/* 1678 */         String remarks = result.getString(5);
/* 1679 */         String type = getString(result, 6, code);
/* 1683 */         CRSFactory factory = this.factories.getCRSFactory();
/* 1691 */         if (type.equalsIgnoreCase("geographic 2D") || type.equalsIgnoreCase("geographic 3D")) {
/*      */           GeodeticDatum datum;
/* 1694 */           String csCode = getString(result, 7, code);
/* 1695 */           String dmCode = result.getString(8);
/* 1696 */           EllipsoidalCS cs = createEllipsoidalCS(csCode);
/* 1698 */           if (dmCode != null) {
/* 1699 */             datum = createGeodeticDatum(dmCode);
/*      */           } else {
/* 1701 */             String geoCode = getString(result, 9, code, 8);
/* 1702 */             result.close();
/* 1703 */             result = null;
/* 1704 */             GeographicCRS baseCRS = createGeographicCRS(geoCode);
/* 1705 */             datum = baseCRS.getDatum();
/*      */           } 
/* 1707 */           java.util.Map<String, Object> properties = generateProperties(name, epsg, area, scope, remarks);
/* 1708 */           GeographicCRS geographicCRS = factory.createGeographicCRS(properties, datum, cs);
/* 1716 */         } else if (type.equalsIgnoreCase("projected")) {
/* 1717 */           String csCode = getString(result, 7, code);
/* 1718 */           String geoCode = getString(result, 9, code);
/* 1719 */           String opCode = getString(result, 10, code);
/* 1720 */           result.close();
/* 1721 */           result = null;
/* 1722 */           CartesianCS cs = createCartesianCS(csCode);
/* 1723 */           GeographicCRS baseCRS = createGeographicCRS(geoCode);
/* 1724 */           CoordinateOperation op = createCoordinateOperation(opCode);
/* 1725 */           if (op instanceof Conversion) {
/* 1726 */             java.util.Map<String, Object> properties = generateProperties(name, epsg, area, scope, remarks);
/* 1727 */             ProjectedCRS projectedCRS = factory.createProjectedCRS(properties, baseCRS, (Conversion)op, cs);
/*      */           } else {
/* 1729 */             throw noSuchAuthorityCode(Projection.class, opCode);
/*      */           } 
/* 1735 */         } else if (type.equalsIgnoreCase("vertical")) {
/* 1736 */           String csCode = getString(result, 7, code);
/* 1737 */           String dmCode = getString(result, 8, code);
/* 1738 */           VerticalCS cs = createVerticalCS(csCode);
/* 1739 */           VerticalDatum datum = createVerticalDatum(dmCode);
/* 1740 */           java.util.Map<String, Object> properties = generateProperties(name, epsg, area, scope, remarks);
/* 1741 */           VerticalCRS verticalCRS = factory.createVerticalCRS(properties, datum, cs);
/* 1749 */         } else if (type.equalsIgnoreCase("compound")) {
/*      */           CoordinateReferenceSystem crs1, crs2;
/* 1750 */           String code1 = getString(result, 11, code);
/* 1751 */           String code2 = getString(result, 12, code);
/* 1752 */           result.close();
/* 1753 */           result = null;
/* 1755 */           if (!this.safetyGuard.add(epsg))
/* 1756 */             throw recursiveCall(CompoundCRS.class, epsg); 
/*      */           try {
/* 1758 */             crs1 = createCoordinateReferenceSystem(code1);
/* 1759 */             crs2 = createCoordinateReferenceSystem(code2);
/*      */           } finally {
/* 1761 */             this.safetyGuard.remove(epsg);
/*      */           } 
/* 1764 */           java.util.Map<String, Object> properties = generateProperties(name, epsg, area, scope, remarks);
/* 1765 */           CompoundCRS compoundCRS = factory.createCompoundCRS(properties, new CoordinateReferenceSystem[] { crs1, crs2 });
/* 1771 */         } else if (type.equalsIgnoreCase("geocentric")) {
/* 1772 */           String csCode = getString(result, 7, code);
/* 1773 */           String dmCode = getString(result, 8, code);
/* 1774 */           CoordinateSystem cs = createCoordinateSystem(csCode);
/* 1775 */           GeodeticDatum datum = createGeodeticDatum(dmCode);
/* 1776 */           java.util.Map<String, Object> properties = generateProperties(name, epsg, area, scope, remarks);
/* 1777 */           if (cs instanceof CartesianCS) {
/* 1778 */             GeocentricCRS geocentricCRS = factory.createGeocentricCRS(properties, datum, (CartesianCS)cs);
/* 1779 */           } else if (cs instanceof SphericalCS) {
/* 1780 */             GeocentricCRS geocentricCRS = factory.createGeocentricCRS(properties, datum, (SphericalCS)cs);
/*      */           } else {
/* 1782 */             result.close();
/* 1783 */             throw new FactoryException(Errors.format(63, cs.getClass(), GeocentricCRS.class));
/*      */           } 
/* 1791 */         } else if (type.equalsIgnoreCase("engineering")) {
/* 1792 */           String csCode = getString(result, 7, code);
/* 1793 */           String dmCode = getString(result, 8, code);
/* 1794 */           CoordinateSystem cs = createCoordinateSystem(csCode);
/* 1795 */           EngineeringDatum datum = createEngineeringDatum(dmCode);
/* 1796 */           java.util.Map<String, Object> properties = generateProperties(name, epsg, area, scope, remarks);
/* 1797 */           engineeringCRS = factory.createEngineeringCRS(properties, datum, cs);
/*      */         } else {
/* 1803 */           result.close();
/* 1804 */           throw new FactoryException(Errors.format(187, type));
/*      */         } 
/* 1806 */         returnValue = (CoordinateReferenceSystem)ensureSingleton(engineeringCRS, returnValue, code);
/* 1807 */         if (result == null)
/* 1810 */           return returnValue; 
/*      */       } 
/* 1813 */       result.close();
/* 1814 */     } catch (SQLException exception) {
/* 1815 */       throw databaseFailure(CoordinateReferenceSystem.class, code, exception);
/*      */     } 
/* 1817 */     if (returnValue == null)
/* 1818 */       throw noSuchAuthorityCode(CoordinateReferenceSystem.class, code); 
/* 1820 */     return returnValue;
/*      */   }
/*      */   
/*      */   public synchronized ParameterDescriptor generateParameterDescriptor(String code) throws FactoryException {
/* 1834 */     ensureNonNull("code", code);
/* 1835 */     ParameterDescriptor returnValue = null;
/*      */     try {
/* 1838 */       String primaryKey = toPrimaryKey(ParameterDescriptor.class, code, "[Coordinate_Operation Parameter]", "PARAMETER_CODE", "PARAMETER_NAME");
/* 1840 */       PreparedStatement stmt = prepareStatement("ParameterDescriptor", "SELECT PARAMETER_CODE, PARAMETER_NAME, DESCRIPTION FROM [Coordinate_Operation Parameter] WHERE PARAMETER_CODE = ?");
/* 1846 */       stmt.setString(1, primaryKey);
/* 1847 */       ResultSet result = stmt.executeQuery();
/* 1848 */       while (result.next()) {
/*      */         Unit unit;
/*      */         Class<double> type;
/* 1849 */         String epsg = getString(result, 1, code);
/* 1850 */         String name = getString(result, 2, code);
/* 1851 */         String remarks = result.getString(3);
/* 1860 */         PreparedStatement units = prepareStatement("ParameterUnit", "SELECT MIN(UOM_CODE) AS UOM, MIN(PARAM_VALUE_FILE_REF) AS FILEREF FROM [Coordinate_Operation Parameter Value] WHERE (PARAMETER_CODE = ?) GROUP BY UOM_CODE ORDER BY COUNT(UOM_CODE) DESC");
/* 1867 */         units.setString(1, epsg);
/* 1868 */         ResultSet resultUnits = units.executeQuery();
/* 1869 */         if (resultUnits.next()) {
/* 1870 */           String element = resultUnits.getString(1);
/* 1871 */           unit = (element != null) ? createUnit(element) : null;
/* 1872 */           element = resultUnits.getString(2);
/* 1873 */           type = (element != null && element.trim().length() != 0) ? URI.class : double.class;
/*      */         } else {
/* 1875 */           unit = null;
/* 1876 */           type = double.class;
/*      */         } 
/* 1878 */         resultUnits.close();
/* 1883 */         java.util.Map<String, Object> properties = generateProperties(name, epsg, remarks);
/* 1884 */         DefaultParameterDescriptor defaultParameterDescriptor = new DefaultParameterDescriptor(properties, type, null, null, null, null, unit, true);
/* 1886 */         returnValue = (ParameterDescriptor)ensureSingleton(defaultParameterDescriptor, returnValue, code);
/*      */       } 
/* 1888 */     } catch (SQLException exception) {
/* 1889 */       throw databaseFailure(OperationMethod.class, code, exception);
/*      */     } 
/* 1891 */     if (returnValue == null)
/* 1892 */       throw noSuchAuthorityCode(OperationMethod.class, code); 
/* 1894 */     return returnValue;
/*      */   }
/*      */   
/*      */   private ParameterDescriptor[] generateParameterDescriptors(String method) throws FactoryException, SQLException {
/* 1908 */     PreparedStatement stmt = prepareStatement("ParameterDescriptors", "SELECT PARAMETER_CODE FROM [Coordinate_Operation Parameter Usage] WHERE COORD_OP_METHOD_CODE = ? ORDER BY SORT_ORDER");
/* 1913 */     stmt.setString(1, method);
/* 1914 */     ResultSet results = stmt.executeQuery();
/* 1915 */     List<ParameterDescriptor> descriptors = new ArrayList<ParameterDescriptor>();
/* 1916 */     while (results.next()) {
/* 1917 */       String param = getString(results, 1, method);
/* 1918 */       descriptors.add(generateParameterDescriptor(param));
/*      */     } 
/* 1920 */     results.close();
/* 1921 */     return descriptors.<ParameterDescriptor>toArray(new ParameterDescriptor[descriptors.size()]);
/*      */   }
/*      */   
/*      */   private void fillParameterValues(String method, String operation, ParameterValueGroup parameters) throws FactoryException, SQLException {
/* 1938 */     PreparedStatement stmt = prepareStatement("ParameterValues", "SELECT CP.PARAMETER_NAME, CV.PARAMETER_VALUE, CV.PARAM_VALUE_FILE_REF, CV.UOM_CODE FROM ([Coordinate_Operation Parameter Value] AS CV INNER JOIN [Coordinate_Operation Parameter] AS CP ON CV.PARAMETER_CODE = CP.PARAMETER_CODE) INNER JOIN [Coordinate_Operation Parameter Usage] AS CU ON (CP.PARAMETER_CODE = CU.PARAMETER_CODE) AND (CV.COORD_OP_METHOD_CODE = CU.COORD_OP_METHOD_CODE) WHERE CV.COORD_OP_METHOD_CODE = ? AND CV.COORD_OP_CODE = ? ORDER BY CU.SORT_ORDER");
/* 1952 */     stmt.setString(1, method);
/* 1953 */     stmt.setString(2, operation);
/* 1954 */     ResultSet result = stmt.executeQuery();
/* 1955 */     while (result.next()) {
/*      */       Unit unit;
/*      */       Object reference;
/*      */       ParameterValue param;
/* 1956 */       String name = getString(result, 1, operation);
/* 1957 */       double value = result.getDouble(2);
/* 1960 */       if (result.wasNull()) {
/* 1965 */         reference = getString(result, 3, operation);
/*      */         try {
/* 1967 */           reference = new URI((String)reference);
/* 1968 */         } catch (URISyntaxException exception) {
/* 1970 */           reference = new File((String)reference);
/*      */         } 
/* 1972 */         unit = null;
/*      */       } else {
/* 1974 */         reference = null;
/* 1975 */         String unitCode = result.getString(4);
/* 1976 */         unit = (unitCode != null) ? createUnit(unitCode) : null;
/*      */       } 
/*      */       try {
/* 1980 */         param = parameters.parameter(name);
/* 1981 */       } catch (ParameterNotFoundException exception) {
/* 1994 */         NoSuchIdentifierException e = new NoSuchIdentifierException(Errors.format(32, name), name);
/* 1996 */         e.initCause((Throwable)exception);
/* 1997 */         throw e;
/*      */       } 
/*      */       try {
/* 2000 */         if (reference != null) {
/* 2001 */           param.setValue(reference);
/*      */           continue;
/*      */         } 
/* 2002 */         if (unit != null) {
/* 2003 */           param.setValue(value, unit);
/*      */           continue;
/*      */         } 
/* 2005 */         param.setValue(value);
/* 2007 */       } catch (InvalidParameterValueException exception) {
/* 2008 */         throw new FactoryException(Errors.format(32, name), exception);
/*      */       } 
/*      */     } 
/* 2012 */     result.close();
/*      */   }
/*      */   
/*      */   public synchronized OperationMethod generateOperationMethod(String code) throws FactoryException {
/* 2026 */     ensureNonNull("code", code);
/* 2027 */     OperationMethod returnValue = null;
/*      */     try {
/* 2030 */       String primaryKey = toPrimaryKey(OperationMethod.class, code, "[Coordinate_Operation Method]", "COORD_OP_METHOD_CODE", "COORD_OP_METHOD_NAME");
/* 2032 */       PreparedStatement stmt = prepareStatement("OperationMethod", "SELECT COORD_OP_METHOD_CODE, COORD_OP_METHOD_NAME, FORMULA, REMARKS FROM [Coordinate_Operation Method] WHERE COORD_OP_METHOD_CODE = ?");
/* 2038 */       stmt.setString(1, primaryKey);
/* 2039 */       ResultSet result = stmt.executeQuery();
/* 2040 */       OperationMethod method = null;
/* 2041 */       while (result.next()) {
/* 2042 */         String epsg = getString(result, 1, code);
/* 2043 */         String name = getString(result, 2, code);
/* 2044 */         String formula = result.getString(3);
/* 2045 */         String remarks = result.getString(4);
/* 2046 */         int encoded = getDimensionsForMethod(epsg);
/* 2047 */         int sourceDimensions = encoded >>> 16;
/* 2048 */         int targetDimensions = encoded & 0xFFFF;
/* 2049 */         ParameterDescriptor[] descriptors = generateParameterDescriptors(epsg);
/* 2050 */         java.util.Map<String, Object> properties = generateProperties(name, epsg, remarks);
/* 2051 */         if (formula != null)
/* 2052 */           properties.put("formula", formula); 
/* 2054 */         DefaultOperationMethod defaultOperationMethod = new DefaultOperationMethod(properties, sourceDimensions, targetDimensions, (ParameterDescriptorGroup)new DefaultParameterDescriptorGroup(properties, (GeneralParameterDescriptor[])descriptors));
/* 2056 */         returnValue = (OperationMethod)ensureSingleton(defaultOperationMethod, returnValue, code);
/*      */       } 
/* 2058 */     } catch (SQLException exception) {
/* 2059 */       throw databaseFailure(OperationMethod.class, code, exception);
/*      */     } 
/* 2061 */     if (returnValue == null)
/* 2062 */       throw noSuchAuthorityCode(OperationMethod.class, code); 
/* 2064 */     return returnValue;
/*      */   }
/*      */   
/*      */   private int getDimensionsForMethod(String code) throws SQLException {
/* 2076 */     PreparedStatement stmt = prepareStatement("MethodDimensions", "SELECT SOURCE_CRS_CODE, TARGET_CRS_CODE FROM [Coordinate_Operation] WHERE COORD_OP_METHOD_CODE = ? AND SOURCE_CRS_CODE IS NOT NULL AND TARGET_CRS_CODE IS NOT NULL");
/* 2082 */     stmt.setString(1, code);
/* 2083 */     ResultSet result = stmt.executeQuery();
/* 2084 */     java.util.Map<Dimensions, Dimensions> dimensions = new HashMap<Dimensions, Dimensions>();
/* 2085 */     Dimensions temp = new Dimensions(131074);
/* 2086 */     Dimensions max = temp;
/* 2087 */     while (result.next()) {
/* 2088 */       short sourceDimensions = getDimensionForCRS(result.getString(1));
/* 2089 */       short targetDimensions = getDimensionForCRS(result.getString(2));
/* 2090 */       temp.encoded = sourceDimensions << 16 | targetDimensions;
/* 2091 */       Dimensions candidate = dimensions.get(temp);
/* 2092 */       if (candidate == null) {
/* 2093 */         candidate = new Dimensions(temp.encoded);
/* 2094 */         dimensions.put(candidate, candidate);
/*      */       } 
/* 2096 */       if (++candidate.occurences > max.occurences)
/* 2097 */         max = candidate; 
/*      */     } 
/* 2100 */     result.close();
/* 2101 */     return max.encoded;
/*      */   }
/*      */   
/*      */   private static final class Dimensions {
/*      */     int encoded;
/*      */     
/*      */     int occurences;
/*      */     
/*      */     Dimensions(int e) {
/* 2109 */       this.encoded = e;
/*      */     }
/*      */     
/*      */     public int hashCode() {
/* 2112 */       return this.encoded;
/*      */     }
/*      */     
/*      */     public boolean equals(Object object) {
/* 2115 */       return (object instanceof Dimensions && ((Dimensions)object).encoded == this.encoded);
/*      */     }
/*      */     
/*      */     public String toString() {
/* 2118 */       return "[(" + (this.encoded >>> 16) + ',' + (this.encoded & 0xFFFF) + ")×" + this.occurences + ']';
/*      */     }
/*      */   }
/*      */   
/*      */   private short getDimensionForCRS(String code) throws SQLException {
/*      */     short dimension;
/* 2129 */     Short cached = this.axisCounts.get(code);
/* 2131 */     if (cached == null) {
/* 2132 */       PreparedStatement stmt = prepareStatement("Dimension", "  SELECT COUNT(COORD_AXIS_CODE) FROM [Coordinate Axis] WHERE COORD_SYS_CODE = (SELECT COORD_SYS_CODE  FROM [Coordinate Reference System] WHERE COORD_REF_SYS_CODE = ?)");
/* 2138 */       stmt.setString(1, code);
/* 2139 */       ResultSet result = stmt.executeQuery();
/* 2140 */       dimension = result.next() ? result.getShort(1) : 2;
/* 2141 */       this.axisCounts.put(code, Short.valueOf(dimension));
/* 2142 */       result.close();
/*      */     } else {
/* 2144 */       dimension = cached.shortValue();
/*      */     } 
/* 2146 */     return dimension;
/*      */   }
/*      */   
/*      */   final synchronized boolean isProjection(String code) throws SQLException {
/* 2156 */     Boolean projection = this.codeProjection.get(code);
/* 2157 */     if (projection == null) {
/* 2158 */       PreparedStatement stmt = prepareStatement("isProjection", "SELECT COORD_REF_SYS_CODE FROM [Coordinate Reference System] WHERE PROJECTION_CONV_CODE = ? AND COORD_REF_SYS_KIND LIKE 'projected%'");
/* 2162 */       stmt.setString(1, code);
/* 2163 */       ResultSet result = stmt.executeQuery();
/* 2164 */       boolean found = result.next();
/* 2165 */       result.close();
/* 2166 */       projection = Boolean.valueOf(found);
/* 2167 */       this.codeProjection.put(code, projection);
/*      */     } 
/* 2169 */     return projection.booleanValue();
/*      */   }
/*      */   
/*      */   public synchronized CoordinateOperation generateCoordinateOperation(String code) throws FactoryException {
/* 2186 */     ensureNonNull("code", code);
/* 2187 */     CoordinateOperation returnValue = null;
/*      */     try {
/* 2189 */       String primaryKey = toPrimaryKey(CoordinateOperation.class, code, "[Coordinate_Operation]", "COORD_OP_CODE", "COORD_OP_NAME");
/* 2192 */       PreparedStatement stmt = prepareStatement("CoordinateOperation", "SELECT COORD_OP_CODE, COORD_OP_NAME, COORD_OP_TYPE, SOURCE_CRS_CODE, TARGET_CRS_CODE, COORD_OP_METHOD_CODE, COORD_TFM_VERSION, COORD_OP_ACCURACY, AREA_OF_USE_CODE, COORD_OP_SCOPE, REMARKS FROM [Coordinate_Operation] WHERE COORD_OP_CODE = ?");
/* 2205 */       stmt.setString(1, primaryKey);
/* 2206 */       ResultSet result = stmt.executeQuery();
/* 2207 */       while (result.next()) {
/*      */         String sourceCode, targetCode, methodCode;
/*      */         int sourceDimensions, targetDimensions;
/*      */         CoordinateReferenceSystem sourceCRS, targetCRS;
/*      */         boolean isBursaWolf;
/*      */         DefaultOperationMethod defaultOperationMethod;
/*      */         ParameterValueGroup parameters;
/*      */         CoordinateOperation operation;
/* 2208 */         String epsg = getString(result, 1, code);
/* 2209 */         String name = getString(result, 2, code);
/* 2210 */         String type = getString(result, 3, code).trim().toLowerCase();
/* 2211 */         boolean isTransformation = type.equals("transformation");
/* 2212 */         boolean isConversion = type.equals("conversion");
/* 2213 */         boolean isConcatenated = type.equals("concatenated operation");
/* 2215 */         if (isConversion) {
/* 2217 */           sourceCode = result.getString(4);
/* 2218 */           targetCode = result.getString(5);
/*      */         } else {
/* 2220 */           sourceCode = getString(result, 4, code);
/* 2221 */           targetCode = getString(result, 5, code);
/*      */         } 
/* 2223 */         if (isConcatenated) {
/* 2225 */           methodCode = result.getString(6);
/*      */         } else {
/* 2227 */           methodCode = getString(result, 6, code);
/*      */         } 
/* 2229 */         String version = result.getString(7);
/* 2230 */         double accuracy = result.getDouble(8);
/* 2230 */         if (result.wasNull())
/* 2230 */           accuracy = Double.NaN; 
/* 2231 */         String area = result.getString(9);
/* 2232 */         String scope = result.getString(10);
/* 2233 */         String remarks = result.getString(11);
/* 2245 */         if (sourceCode != null) {
/* 2246 */           sourceCRS = createCoordinateReferenceSystem(sourceCode);
/* 2247 */           sourceDimensions = sourceCRS.getCoordinateSystem().getDimension();
/*      */         } else {
/* 2249 */           sourceCRS = null;
/* 2250 */           sourceDimensions = 2;
/*      */         } 
/* 2252 */         if (targetCode != null) {
/* 2253 */           targetCRS = createCoordinateReferenceSystem(targetCode);
/* 2254 */           targetDimensions = targetCRS.getCoordinateSystem().getDimension();
/*      */         } else {
/* 2256 */           targetCRS = null;
/* 2257 */           targetDimensions = 2;
/*      */         } 
/* 2267 */         if (methodCode == null) {
/* 2268 */           isBursaWolf = false;
/* 2269 */           OperationMethod method = null;
/* 2270 */           parameters = null;
/*      */         } else {
/*      */           int num;
/*      */           try {
/* 2274 */             num = Integer.parseInt(methodCode);
/* 2275 */           } catch (NumberFormatException exception) {
/* 2276 */             result.close();
/* 2277 */             throw new FactoryException(exception);
/*      */           } 
/* 2279 */           isBursaWolf = (num >= 9603 && num <= 9607);
/* 2282 */           OperationMethod method = generateOperationMethod(methodCode);
/* 2283 */           if (method.getSourceDimensions() != sourceDimensions || method.getTargetDimensions() != targetDimensions)
/* 2286 */             defaultOperationMethod = new DefaultOperationMethod(method, sourceDimensions, targetDimensions); 
/* 2295 */           String classe = defaultOperationMethod.getName().getCode();
/* 2296 */           parameters = this.factories.getMathTransformFactory().getDefaultParameters(classe);
/* 2297 */           fillParameterValues(methodCode, epsg, parameters);
/*      */         } 
/* 2308 */         java.util.Map<String, Object> properties = generateProperties(name, epsg, area, scope, remarks);
/* 2309 */         if (version != null && (version = version.trim()).length() != 0)
/* 2310 */           properties.put("operationVersion", version); 
/* 2312 */         if (!Double.isNaN(accuracy)) {
/* 2315 */           QuantitativeResultImpl accuracyResult = new QuantitativeResultImpl(new double[] { accuracy });
/* 2319 */           accuracyResult.setValueUnit(SI.METER);
/* 2320 */           AbsoluteExternalPositionalAccuracyImpl accuracyElement = new AbsoluteExternalPositionalAccuracyImpl((Result)accuracyResult);
/* 2321 */           accuracyElement.setMeasureDescription(TRANSFORMATION_ACCURACY);
/* 2322 */           accuracyElement.setEvaluationMethodType(EvaluationMethodType.DIRECT_EXTERNAL);
/* 2323 */           properties.put("coordinateOperationAccuracy", new PositionalAccuracy[] { (PositionalAccuracy)accuracyElement.unmodifiable() });
/*      */         } 
/* 2337 */         if (isConversion && (sourceCRS == null || targetCRS == null)) {
/* 2340 */           DefiningConversion definingConversion = new DefiningConversion(properties, (OperationMethod)defaultOperationMethod, parameters);
/*      */         } else {
/*      */           Class<Conversion> expected;
/* 2341 */           if (isConcatenated) {
/* 2351 */             result.close();
/* 2352 */             result = null;
/* 2353 */             PreparedStatement cstmt = prepareStatement("ConcatenatedOperation", "SELECT SINGLE_OPERATION_CODE FROM [Coordinate_Operation Path] WHERE (CONCAT_OPERATION_CODE = ?) ORDER BY OP_PATH_STEP");
/* 2358 */             cstmt.setString(1, epsg);
/* 2359 */             ResultSet cr = cstmt.executeQuery();
/* 2360 */             List<String> codes = new ArrayList<String>();
/* 2361 */             while (cr.next())
/* 2362 */               codes.add(cr.getString(1)); 
/* 2364 */             cr.close();
/* 2365 */             CoordinateOperation[] operations = new CoordinateOperation[codes.size()];
/* 2366 */             if (!this.safetyGuard.add(epsg))
/* 2367 */               throw recursiveCall(ConcatenatedOperation.class, epsg); 
/*      */             try {
/* 2369 */               for (int i = 0; i < operations.length; i++)
/* 2370 */                 operations[i] = createCoordinateOperation(codes.get(i)); 
/*      */             } finally {
/* 2373 */               this.safetyGuard.remove(epsg);
/*      */             } 
/*      */             try {
/* 2376 */               return (CoordinateOperation)new DefaultConcatenatedOperation(properties, operations);
/* 2377 */             } catch (IllegalArgumentException exception) {
/* 2380 */               throw new FactoryException(exception);
/*      */             } 
/*      */           } 
/* 2389 */           if (isBursaWolf)
/*      */             try {
/* 2390 */               Ellipsoid ellipsoid = CRSUtilities.getHeadGeoEllipsoid(sourceCRS);
/* 2391 */               if (ellipsoid != null) {
/* 2392 */                 Unit axisUnit = ellipsoid.getAxisUnit();
/* 2393 */                 parameters.parameter("src_semi_major").setValue(ellipsoid.getSemiMajorAxis(), axisUnit);
/* 2394 */                 parameters.parameter("src_semi_minor").setValue(ellipsoid.getSemiMinorAxis(), axisUnit);
/* 2395 */                 parameters.parameter("src_dim").setValue(sourceCRS.getCoordinateSystem().getDimension());
/*      */               } 
/* 2397 */               ellipsoid = CRSUtilities.getHeadGeoEllipsoid(targetCRS);
/* 2398 */               if (ellipsoid != null) {
/* 2399 */                 Unit axisUnit = ellipsoid.getAxisUnit();
/* 2400 */                 parameters.parameter("tgt_semi_major").setValue(ellipsoid.getSemiMajorAxis(), axisUnit);
/* 2401 */                 parameters.parameter("tgt_semi_minor").setValue(ellipsoid.getSemiMinorAxis(), axisUnit);
/* 2402 */                 parameters.parameter("tgt_dim").setValue(targetCRS.getCoordinateSystem().getDimension());
/*      */               } 
/* 2404 */             } catch (ParameterNotFoundException exception) {
/* 2405 */               result.close();
/* 2406 */               throw new FactoryException(Errors.format(52, defaultOperationMethod.getName().getCode(), exception));
/*      */             }  
/* 2415 */           if (isTransformation) {
/* 2416 */             Class<Transformation> clazz = Transformation.class;
/* 2417 */           } else if (isConversion) {
/* 2418 */             expected = Conversion.class;
/*      */           } else {
/* 2420 */             result.close();
/* 2421 */             throw new FactoryException(Errors.format(187, type));
/*      */           } 
/* 2423 */           MathTransform mt = this.factories.getMathTransformFactory().createBaseToDerived(sourceCRS, parameters, targetCRS.getCoordinateSystem());
/* 2426 */           operation = DefaultOperation.create(properties, sourceCRS, targetCRS, mt, (OperationMethod)defaultOperationMethod, expected);
/*      */         } 
/* 2429 */         returnValue = ensureSingleton(operation, returnValue, code);
/* 2430 */         if (result == null)
/* 2433 */           return returnValue; 
/*      */       } 
/* 2436 */       result.close();
/* 2437 */     } catch (SQLException exception) {
/* 2438 */       throw databaseFailure(CoordinateOperation.class, code, exception);
/*      */     } 
/* 2440 */     if (returnValue == null)
/* 2441 */       throw noSuchAuthorityCode(CoordinateOperation.class, code); 
/* 2443 */     return returnValue;
/*      */   }
/*      */   
/*      */   public synchronized Set generateFromCoordinateReferenceSystemCodes(String sourceCode, String targetCode) throws FactoryException {
/* 2465 */     ensureNonNull("sourceCode", sourceCode);
/* 2466 */     ensureNonNull("targetCode", targetCode);
/* 2467 */     String pair = sourceCode + " ⇨ " + targetCode;
/* 2468 */     CoordinateOperationSet set = new CoordinateOperationSet((AuthorityFactory)this);
/*      */     try {
/* 2470 */       String sourceKey = toPrimaryKeyCRS(sourceCode);
/* 2471 */       String targetKey = toPrimaryKeyCRS(targetCode);
/* 2472 */       boolean searchTransformations = false;
/*      */       do {
/*      */         String key, sql;
/* 2481 */         if (searchTransformations) {
/* 2482 */           key = "TransformationFromCRS";
/* 2483 */           sql = "SELECT COORD_OP_CODE FROM [Coordinate_Operation] WHERE SOURCE_CRS_CODE = ? AND TARGET_CRS_CODE = ? ORDER BY ABS(DEPRECATED), COORD_OP_ACCURACY";
/*      */         } else {
/* 2489 */           key = "ConversionFromCRS";
/* 2490 */           sql = "SELECT PROJECTION_CONV_CODE FROM [Coordinate Reference System] WHERE SOURCE_GEOGCRS_CODE = ? AND COORD_REF_SYS_CODE = ?";
/*      */         } 
/* 2495 */         PreparedStatement stmt = prepareStatement(key, sql);
/* 2496 */         stmt.setString(1, sourceKey);
/* 2497 */         stmt.setString(2, targetKey);
/* 2498 */         ResultSet result = stmt.executeQuery();
/* 2499 */         while (result.next()) {
/* 2500 */           String code = getString(result, 1, pair);
/* 2501 */           set.addAuthorityCode(code, searchTransformations ? null : targetKey);
/*      */         } 
/* 2503 */         result.close();
/* 2504 */       } while ((searchTransformations = !searchTransformations) == true);
/* 2510 */       String[] codes = set.getAuthorityCodes();
/* 2511 */       sort((Object[])codes);
/* 2512 */       set.setAuthorityCodes(codes);
/* 2513 */     } catch (SQLException exception) {
/* 2514 */       throw databaseFailure(CoordinateOperation.class, pair, exception);
/*      */     } 
/* 2521 */     set.resolve(1);
/* 2522 */     return (Set)set;
/*      */   }
/*      */   
/*      */   private void sort(Object[] codes) throws SQLException, FactoryException {
/* 2539 */     if (codes.length <= 1)
/*      */       return; 
/* 2543 */     PreparedStatement stmt = prepareStatement("Supersession", "SELECT SUPERSEDED_BY FROM [Supersession] WHERE OBJECT_CODE = ? ORDER BY SUPERSESSION_YEAR DESC");
/* 2547 */     int maxIterations = 15;
/*      */     while (true) {
/* 2549 */       boolean changed = false;
/* 2550 */       for (int i = 0; i < codes.length; i++) {
/* 2551 */         String code = codes[i].toString();
/* 2552 */         stmt.setString(1, code);
/* 2553 */         ResultSet result = stmt.executeQuery();
/* 2554 */         while (result.next()) {
/* 2555 */           String replacement = getString(result, 1, code);
/* 2556 */           for (int j = i + 1; j < codes.length; j++) {
/* 2557 */             Object candidate = codes[j];
/* 2558 */             if (replacement.equals(candidate.toString())) {
/* 2562 */               System.arraycopy(codes, i, codes, i + 1, j - i);
/* 2563 */               codes[i++] = candidate;
/* 2564 */               changed = true;
/*      */             } 
/*      */           } 
/*      */         } 
/* 2568 */         result.close();
/*      */       } 
/* 2570 */       if (!changed)
/*      */         return; 
/* 2574 */       if (--maxIterations == 0) {
/* 2575 */         LOGGER.finer("Possible recursivity in supersessions.");
/*      */         return;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private final class Finder extends IdentifiedObjectFinder {
/*      */     Finder(Class type) {
/* 2590 */       super((AuthorityFactory)AbstractEpsgFactory.this, type);
/*      */     }
/*      */     
/*      */     protected Set getCodeCandidates(IdentifiedObject object) throws FactoryException {
/* 2600 */       String where, code, select = "COORD_REF_SYS_CODE";
/* 2601 */       String from = "[Coordinate Reference System]";
/* 2603 */       if (object instanceof Ellipsoid) {
/* 2604 */         select = "ELLIPSOID_CODE";
/* 2605 */         from = "[Ellipsoid]";
/* 2606 */         where = "SEMI_MAJOR_AXIS";
/* 2607 */         code = Double.toString(((Ellipsoid)object).getSemiMajorAxis());
/*      */       } else {
/*      */         Ellipsoid ellipsoid;
/* 2610 */         if (object instanceof GeneralDerivedCRS) {
/* 2611 */           CoordinateReferenceSystem coordinateReferenceSystem = ((GeneralDerivedCRS)object).getBaseCRS();
/* 2612 */           where = "SOURCE_GEOGCRS_CODE";
/* 2613 */         } else if (object instanceof SingleCRS) {
/* 2614 */           Datum datum = ((SingleCRS)object).getDatum();
/* 2615 */           where = "DATUM_CODE";
/* 2616 */         } else if (object instanceof GeodeticDatum) {
/* 2617 */           ellipsoid = ((GeodeticDatum)object).getEllipsoid();
/* 2618 */           select = "DATUM_CODE";
/* 2619 */           from = "[Datum]";
/* 2620 */           where = "ELLIPSOID_CODE";
/*      */         } else {
/* 2622 */           return super.getCodeCandidates(object);
/*      */         } 
/* 2624 */         IdentifiedObject dependency = AbstractEpsgFactory.this.getIdentifiedObjectFinder(ellipsoid.getClass()).find((IdentifiedObject)ellipsoid);
/* 2625 */         ReferenceIdentifier referenceIdentifier = AbstractIdentifiedObject.getIdentifier(dependency, getAuthority());
/* 2626 */         if (referenceIdentifier == null || (code = referenceIdentifier.getCode()) == null)
/* 2627 */           return super.getCodeCandidates(object); 
/*      */       } 
/* 2630 */       String sql = "SELECT " + select + " FROM " + from + " WHERE " + where + "='" + code + '\'';
/* 2631 */       sql = AbstractEpsgFactory.this.adaptSQL(sql);
/* 2632 */       Set<String> result = new LinkedHashSet<String>();
/*      */       try {
/* 2634 */         Statement s = AbstractEpsgFactory.this.getConnection().createStatement();
/* 2635 */         ResultSet r = s.executeQuery(sql);
/* 2636 */         while (r.next())
/* 2637 */           result.add(r.getString(1)); 
/* 2639 */         r.close();
/* 2640 */         s.close();
/* 2641 */       } catch (SQLException exception) {
/* 2642 */         throw AbstractEpsgFactory.databaseFailure(Identifier.class, code, exception);
/*      */       } 
/* 2644 */       return result;
/*      */     }
/*      */   }
/*      */   
/*      */   private static FactoryException recursiveCall(Class type, String code) {
/* 2652 */     return new FactoryException(Errors.format(164, type, code));
/*      */   }
/*      */   
/*      */   private static FactoryException databaseFailure(Class type, String code, SQLException cause) {
/* 2661 */     return new FactoryException(Errors.format(38, type, code), cause);
/*      */   }
/*      */   
/*      */   protected boolean isPrimaryKey(String code) throws FactoryException {
/* 2701 */     int length = code.length();
/* 2702 */     for (int i = 0; i < length; i++) {
/* 2703 */       char c = code.charAt(i);
/* 2704 */       if (!Character.isDigit(c) && !Character.isSpaceChar(c))
/* 2705 */         return false; 
/*      */     } 
/* 2708 */     return true;
/*      */   }
/*      */   
/*      */   final synchronized boolean canDispose() {
/* 2718 */     return true;
/*      */   }
/*      */   
/*      */   public synchronized void dispose() throws FactoryException {
/* 2728 */     disconnect();
/* 2729 */     super.dispose();
/*      */   }
/*      */   
/*      */   public void connect() throws FactoryException {
/*      */     try {
/* 2738 */       getConnection();
/* 2739 */     } catch (SQLException e) {
/* 2740 */       throw new FactoryException(e);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void disconnect() throws FactoryException {
/* 2751 */     if (this.connection != null) {
/*      */       boolean isClosed;
/*      */       try {
/* 2754 */         isClosed = this.connection.isClosed();
/* 2755 */         for (java.util.Iterator<PreparedStatement> it = this.statements.values().iterator(); it.hasNext(); ) {
/* 2756 */           ((PreparedStatement)it.next()).close();
/* 2757 */           it.remove();
/*      */         } 
/* 2759 */         this.connection.close();
/* 2760 */       } catch (SQLException exception) {
/* 2761 */         throw new FactoryException(exception);
/*      */       } 
/* 2763 */       if (!isClosed) {
/* 2769 */         LogRecord record = Loggings.format(Level.FINE, 12);
/* 2770 */         record.setLoggerName(LOGGER.getName());
/* 2771 */         LOGGER.log(record);
/*      */       } 
/* 2773 */       this.connection = null;
/*      */     } 
/*      */   }
/*      */   
/*      */   protected synchronized Connection getConnection() throws SQLException {
/* 2784 */     if (this.connection == null)
/* 2785 */       this.connection = this.dataSource.getConnection(); 
/* 2787 */     return this.connection;
/*      */   }
/*      */   
/*      */   protected void shutdown(boolean active) throws SQLException {}
/*      */   
/*      */   protected final void finalize() throws Throwable {
/* 2822 */     dispose();
/* 2823 */     super.finalize();
/*      */   }
/*      */   
/*      */   private static Unit<?> getUnit(int code) {
/* 2841 */     switch (code) {
/*      */       case 9001:
/* 2842 */         return SI.METER;
/*      */       case 9002:
/* 2843 */         return NonSI.FOOT;
/*      */       case 9030:
/* 2844 */         return NonSI.NAUTICAL_MILE;
/*      */       case 9036:
/* 2845 */         return SI.KILO(SI.METER);
/*      */       case 9101:
/* 2846 */         return (Unit<?>)SI.RADIAN;
/*      */       case 9102:
/*      */       case 9122:
/* 2848 */         return NonSI.DEGREE_ANGLE;
/*      */       case 9103:
/* 2849 */         return NonSI.MINUTE_ANGLE;
/*      */       case 9104:
/* 2850 */         return NonSI.SECOND_ANGLE;
/*      */       case 9105:
/* 2851 */         return NonSI.GRADE;
/*      */       case 9107:
/* 2852 */         return Units.DEGREE_MINUTE_SECOND;
/*      */       case 9108:
/* 2853 */         return Units.DEGREE_MINUTE_SECOND;
/*      */       case 9109:
/* 2854 */         return SI.MICRO((Unit)SI.RADIAN);
/*      */       case 9110:
/* 2855 */         return Units.SEXAGESIMAL_DMS;
/*      */       case 9201:
/*      */       case 9203:
/* 2858 */         return Unit.ONE;
/*      */       case 9202:
/* 2859 */         return Units.PPM;
/*      */     } 
/* 2860 */     return null;
/*      */   }
/*      */   
/*      */   private static void setBursaWolfParameter(BursaWolfParameters parameters, int code, double value, Unit<?> unit) throws FactoryException {
/* 2877 */     Unit<?> target = unit;
/* 2878 */     if (code >= 8605)
/* 2879 */       if (code <= 8607) {
/* 2879 */         target = SI.METER;
/* 2880 */       } else if (code <= 8710) {
/* 2880 */         target = NonSI.SECOND_ANGLE;
/* 2881 */       } else if (code == 8611) {
/* 2881 */         target = Units.PPM;
/*      */       }  
/* 2883 */     if (target != unit)
/* 2884 */       value = unit.getConverterTo(target).convert(value); 
/* 2886 */     switch (code) {
/*      */       case 8605:
/* 2887 */         parameters.dx = value;
/*      */         return;
/*      */       case 8606:
/* 2888 */         parameters.dy = value;
/*      */         return;
/*      */       case 8607:
/* 2889 */         parameters.dz = value;
/*      */         return;
/*      */       case 8608:
/* 2890 */         parameters.ex = value;
/*      */         return;
/*      */       case 8609:
/* 2891 */         parameters.ey = value;
/*      */         return;
/*      */       case 8610:
/* 2892 */         parameters.ez = value;
/*      */         return;
/*      */       case 8611:
/* 2893 */         parameters.ppm = value;
/*      */         return;
/*      */     } 
/* 2894 */     throw new FactoryException(Errors.format(176, Integer.valueOf(code)));
/*      */   }
/*      */   
/* 2914 */   private static final TableInfo[] TABLES_INFO = new TableInfo[] { new TableInfo(CoordinateReferenceSystem.class, "[Coordinate Reference System]", "COORD_REF_SYS_CODE", "COORD_REF_SYS_NAME", "COORD_REF_SYS_KIND", new Class[] { ProjectedCRS.class, GeographicCRS.class, GeocentricCRS.class }, new String[] { "projected", "geographic", "geocentric" }), new TableInfo(CoordinateSystem.class, "[Coordinate System]", "COORD_SYS_CODE", "COORD_SYS_NAME", "COORD_SYS_TYPE", new Class[] { CartesianCS.class, EllipsoidalCS.class, SphericalCS.class, VerticalCS.class }, new String[] { "Cartesian", "ellipsoidal", "spherical", "vertical" }), new TableInfo(CoordinateSystemAxis.class, "[Coordinate Axis] AS CA INNER JOIN [Coordinate Axis Name] AS CAN ON CA.COORD_AXIS_NAME_CODE=CAN.COORD_AXIS_NAME_CODE", "COORD_AXIS_CODE", "COORD_AXIS_NAME"), new TableInfo(Datum.class, "[Datum]", "DATUM_CODE", "DATUM_NAME", "DATUM_TYPE", new Class[] { GeodeticDatum.class, VerticalDatum.class, EngineeringDatum.class }, new String[] { "geodetic", "vertical", "engineering" }), new TableInfo(Ellipsoid.class, "[Ellipsoid]", "ELLIPSOID_CODE", "ELLIPSOID_NAME"), new TableInfo(PrimeMeridian.class, "[Prime Meridian]", "PRIME_MERIDIAN_CODE", "PRIME_MERIDIAN_NAME"), new TableInfo(CoordinateOperation.class, "[Coordinate_Operation]", "COORD_OP_CODE", "COORD_OP_NAME", "COORD_OP_TYPE", new Class[] { Projection.class, Conversion.class, Transformation.class }, new String[] { "conversion", "conversion", "transformation" }), new TableInfo(OperationMethod.class, "[Coordinate_Operation Method]", "COORD_OP_METHOD_CODE", "COORD_OP_METHOD_NAME"), new TableInfo(ParameterDescriptor.class, "[Coordinate_Operation Parameter]", "PARAMETER_CODE", "PARAMETER_NAME"), new TableInfo(Unit.class, "[Unit of Measure]", "UOM_CODE", "UNIT_OF_MEAS_NAME") };
/*      */   
/*      */   protected abstract String adaptSQL(String paramString);
/*      */   
/*      */   final class AuthorityCodeSet extends AbstractSet<String> implements Serializable {
/*      */     private static final long serialVersionUID = 7105664579449680562L;
/*      */     
/*      */     public final Class<?> type;
/*      */     
/*      */     private final boolean isProjection;
/*      */     
/*      */     private transient java.util.Map<String, String> asMap;
/*      */     
/*      */     final String sqlAll;
/*      */     
/*      */     private final String sqlSingle;
/*      */     
/*      */     private transient PreparedStatement queryAll;
/*      */     
/*      */     private transient PreparedStatement querySingle;
/*      */     
/* 3053 */     private int size = -1;
/*      */     
/*      */     public AuthorityCodeSet(TableInfo table, Class<?> type) {
/* 3064 */       StringBuilder buffer = new StringBuilder("SELECT ");
/* 3065 */       buffer.append(table.codeColumn);
/* 3066 */       if (table.nameColumn != null)
/* 3067 */         buffer.append(", ").append(table.nameColumn); 
/* 3069 */       buffer.append(" FROM ").append(table.table);
/* 3070 */       boolean hasWhere = false;
/* 3071 */       Class<?> tableType = table.type;
/* 3072 */       if (table.typeColumn != null) {
/* 3073 */         for (int i = 0; i < table.subTypes.length; i++) {
/* 3074 */           Class<?> candidate = table.subTypes[i];
/* 3075 */           if (candidate.isAssignableFrom(type)) {
/* 3076 */             buffer.append(" WHERE (").append(table.typeColumn).append(" LIKE '").append(table.typeNames[i]).append("%'");
/* 3078 */             hasWhere = true;
/* 3079 */             tableType = candidate;
/*      */             break;
/*      */           } 
/*      */         } 
/* 3083 */         if (hasWhere)
/* 3084 */           buffer.append(')'); 
/*      */       } 
/* 3087 */       this.type = tableType;
/* 3088 */       this.isProjection = Projection.class.isAssignableFrom(tableType);
/* 3089 */       int length = buffer.length();
/* 3090 */       buffer.append(" ORDER BY ").append(table.codeColumn);
/* 3091 */       this.sqlAll = AbstractEpsgFactory.this.adaptSQL(buffer.toString());
/* 3092 */       buffer.setLength(length);
/* 3093 */       buffer.append(hasWhere ? " AND " : " WHERE ").append(table.codeColumn).append(" = ?");
/* 3094 */       this.sqlSingle = AbstractEpsgFactory.this.adaptSQL(buffer.toString());
/*      */     }
/*      */     
/*      */     private ResultSet getAll() throws SQLException {
/* 3101 */       assert Thread.holdsLock(this);
/* 3102 */       if (this.queryAll != null)
/*      */         try {
/* 3104 */           return this.queryAll.executeQuery();
/* 3105 */         } catch (SQLException ignore) {
/* 3114 */           this.queryAll.close();
/* 3115 */           this.queryAll = null;
/* 3116 */           recoverableException("getAll", ignore);
/*      */         }  
/* 3119 */       this.queryAll = AbstractEpsgFactory.this.getConnection().prepareStatement(this.sqlAll);
/* 3120 */       return this.queryAll.executeQuery();
/*      */     }
/*      */     
/*      */     private ResultSet getSingle(Object code) throws SQLException {
/* 3127 */       assert Thread.holdsLock(this);
/* 3128 */       if (this.querySingle == null)
/* 3129 */         this.querySingle = AbstractEpsgFactory.this.getConnection().prepareStatement(this.sqlSingle); 
/* 3131 */       this.querySingle.setString(1, code.toString());
/* 3132 */       return this.querySingle.executeQuery();
/*      */     }
/*      */     
/*      */     private boolean isAcceptable(ResultSet results) throws SQLException {
/* 3140 */       if (!this.isProjection)
/* 3141 */         return true; 
/* 3143 */       String code = results.getString(1);
/* 3144 */       return AbstractEpsgFactory.this.isProjection(code);
/*      */     }
/*      */     
/*      */     private boolean isAcceptable(String code) throws SQLException {
/* 3152 */       if (!this.isProjection)
/* 3153 */         return true; 
/* 3155 */       return AbstractEpsgFactory.this.isProjection(code);
/*      */     }
/*      */     
/*      */     public synchronized boolean isEmpty() {
/* 3164 */       if (this.size != -1)
/* 3165 */         return (this.size == 0); 
/* 3167 */       boolean empty = true;
/*      */       try {
/* 3169 */         ResultSet results = getAll();
/* 3170 */         while (results.next()) {
/* 3171 */           if (isAcceptable(results)) {
/* 3172 */             empty = false;
/*      */             break;
/*      */           } 
/*      */         } 
/* 3176 */         results.close();
/* 3177 */       } catch (SQLException exception) {
/* 3178 */         unexpectedException("isEmpty", exception);
/*      */       } 
/* 3180 */       this.size = empty ? 0 : -2;
/* 3181 */       return empty;
/*      */     }
/*      */     
/*      */     public synchronized int size() {
/* 3188 */       if (this.size >= 0)
/* 3189 */         return this.size; 
/* 3191 */       int count = 0;
/*      */       try {
/* 3193 */         ResultSet results = getAll();
/* 3194 */         while (results.next()) {
/* 3195 */           if (isAcceptable(results))
/* 3196 */             count++; 
/*      */         } 
/* 3199 */         results.close();
/* 3200 */       } catch (SQLException exception) {
/* 3201 */         unexpectedException("size", exception);
/*      */       } 
/* 3203 */       this.size = count;
/* 3204 */       return count;
/*      */     }
/*      */     
/*      */     public synchronized boolean contains(Object code) {
/* 3212 */       boolean exists = false;
/* 3213 */       if (code != null)
/*      */         try {
/* 3214 */           ResultSet results = getSingle(code);
/* 3215 */           while (results.next()) {
/* 3216 */             if (isAcceptable(results)) {
/* 3217 */               exists = true;
/*      */               break;
/*      */             } 
/*      */           } 
/* 3221 */           results.close();
/* 3222 */         } catch (SQLException exception) {
/* 3223 */           unexpectedException("contains", exception);
/*      */         }  
/* 3225 */       return exists;
/*      */     }
/*      */     
/*      */     public synchronized java.util.Iterator<String> iterator() {
/*      */       try {
/* 3234 */         Iterator iterator = new Iterator(getAll());
/* 3240 */         this.queryAll = null;
/* 3241 */         return iterator;
/* 3242 */       } catch (SQLException exception) {
/* 3243 */         unexpectedException("iterator", exception);
/* 3244 */         Set<String> empty = Collections.emptySet();
/* 3245 */         return empty.iterator();
/*      */       } 
/*      */     }
/*      */     
/*      */     protected Object writeReplace() throws ObjectStreamException {
/* 3255 */       return new LinkedHashSet(this);
/*      */     }
/*      */     
/*      */     protected synchronized void finalize() throws SQLException {
/* 3265 */       if (this.querySingle != null) {
/* 3266 */         this.querySingle.close();
/* 3267 */         this.querySingle = null;
/*      */       } 
/* 3269 */       if (this.queryAll != null) {
/* 3270 */         this.queryAll.close();
/* 3271 */         this.queryAll = null;
/*      */       } 
/*      */     }
/*      */     
/*      */     private void unexpectedException(String method, SQLException exception) {
/* 3281 */       unexpectedException(AuthorityCodes.class, method, exception);
/*      */     }
/*      */     
/*      */     void unexpectedException(Class classe, String method, SQLException exception) {
/* 3291 */       Logging.unexpectedException(ReferencingFactory.LOGGER, classe, method, exception);
/*      */     }
/*      */     
/*      */     private void recoverableException(String method, SQLException exception) {
/* 3299 */       LogRecord record = Loggings.format(Level.FINE, 43);
/* 3300 */       record.setSourceClassName(AuthorityCodes.class.getName());
/* 3301 */       record.setSourceMethodName(method);
/* 3302 */       record.setThrown(exception);
/* 3303 */       record.setLoggerName(ReferencingFactory.LOGGER.getName());
/* 3304 */       ReferencingFactory.LOGGER.log(record);
/*      */     }
/*      */     
/*      */     private final class Iterator implements java.util.Iterator<String> {
/*      */       private ResultSet results;
/*      */       
/*      */       private transient String next;
/*      */       
/*      */       Iterator(ResultSet results) throws SQLException {
/* 3321 */         assert Thread.holdsLock(AbstractEpsgFactory.AuthorityCodeSet.this);
/* 3322 */         this.results = results;
/* 3323 */         toNext();
/*      */       }
/*      */       
/*      */       private void toNext() throws SQLException {
/* 3328 */         while (this.results.next()) {
/* 3329 */           this.next = this.results.getString(1);
/* 3330 */           if (AbstractEpsgFactory.AuthorityCodeSet.this.isAcceptable(this.next))
/*      */             return; 
/*      */         } 
/* 3334 */         finalize();
/*      */       }
/*      */       
/*      */       public boolean hasNext() {
/* 3339 */         return (this.results != null);
/*      */       }
/*      */       
/*      */       public String next() {
/* 3344 */         if (this.results == null)
/* 3345 */           throw new NoSuchElementException(); 
/* 3347 */         String current = this.next;
/*      */         try {
/* 3349 */           toNext();
/* 3350 */         } catch (SQLException exception) {
/* 3351 */           this.results = null;
/* 3352 */           AbstractEpsgFactory.AuthorityCodeSet.this.unexpectedException(Iterator.class, "next", exception);
/*      */         } 
/* 3354 */         return current;
/*      */       }
/*      */       
/*      */       public void remove() {
/* 3359 */         throw new UnsupportedOperationException();
/*      */       }
/*      */       
/*      */       protected void finalize() throws SQLException {
/* 3365 */         this.next = null;
/* 3366 */         if (this.results != null) {
/* 3367 */           PreparedStatement owner = (PreparedStatement)this.results.getStatement();
/* 3368 */           this.results.close();
/* 3369 */           this.results = null;
/* 3370 */           synchronized (AbstractEpsgFactory.AuthorityCodeSet.this) {
/* 3377 */             assert owner != AbstractEpsgFactory.AuthorityCodeSet.this.queryAll;
/* 3378 */             if (AbstractEpsgFactory.AuthorityCodeSet.this.queryAll == null) {
/* 3379 */               AbstractEpsgFactory.AuthorityCodeSet.this.queryAll = owner;
/*      */             } else {
/* 3381 */               owner.close();
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       }
/*      */     }
/*      */     
/*      */     final java.util.Map<String, String> asMap() {
/* 3392 */       if (this.asMap == null)
/* 3393 */         this.asMap = new Map(); 
/* 3395 */       return this.asMap;
/*      */     }
/*      */     
/*      */     private final class Map extends AbstractMap<String, String> {
/*      */       private Map() {}
/*      */       
/*      */       public int size() {
/* 3408 */         return AbstractEpsgFactory.AuthorityCodeSet.this.size();
/*      */       }
/*      */       
/*      */       public boolean isEmpty() {
/* 3416 */         return AbstractEpsgFactory.AuthorityCodeSet.this.isEmpty();
/*      */       }
/*      */       
/*      */       public String get(Object code) {
/* 3424 */         String value = null;
/* 3425 */         if (code != null)
/*      */           try {
/* 3426 */             synchronized (AbstractEpsgFactory.AuthorityCodeSet.this) {
/* 3427 */               ResultSet results = AbstractEpsgFactory.AuthorityCodeSet.this.getSingle(code);
/* 3428 */               while (results.next()) {
/* 3429 */                 if (AbstractEpsgFactory.AuthorityCodeSet.this.isAcceptable(results)) {
/* 3430 */                   value = results.getString(2);
/*      */                   break;
/*      */                 } 
/*      */               } 
/* 3434 */               results.close();
/*      */             } 
/* 3436 */           } catch (SQLException exception) {
/* 3437 */             AbstractEpsgFactory.AuthorityCodeSet.this.unexpectedException("get", exception);
/*      */           }  
/* 3439 */         return value;
/*      */       }
/*      */       
/*      */       public boolean containsKey(Object key) {
/* 3447 */         return AbstractEpsgFactory.AuthorityCodeSet.this.contains(key);
/*      */       }
/*      */       
/*      */       public Set<String> keySet() {
/* 3455 */         return AbstractEpsgFactory.AuthorityCodeSet.this;
/*      */       }
/*      */       
/*      */       public Set<java.util.Map.Entry<String, String>> entrySet() {
/* 3464 */         throw new UnsupportedOperationException();
/*      */       }
/*      */     }
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\factory\epsg\AbstractEpsgFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */