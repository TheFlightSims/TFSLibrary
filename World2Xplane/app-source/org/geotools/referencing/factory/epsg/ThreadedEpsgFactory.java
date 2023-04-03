/*     */ package org.geotools.referencing.factory.epsg;
/*     */ 
/*     */ import java.sql.Connection;
/*     */ import java.sql.DatabaseMetaData;
/*     */ import java.sql.SQLException;
/*     */ import java.util.jar.Attributes;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.LogRecord;
/*     */ import javax.naming.InitialContext;
/*     */ import javax.naming.NamingException;
/*     */ import javax.naming.NoInitialContextException;
/*     */ import javax.sql.DataSource;
/*     */ import org.geotools.factory.GeoTools;
/*     */ import org.geotools.factory.Hints;
/*     */ import org.geotools.metadata.iso.citation.Citations;
/*     */ import org.geotools.referencing.factory.AbstractAuthorityFactory;
/*     */ import org.geotools.referencing.factory.DeferredAuthorityFactory;
/*     */ import org.geotools.referencing.factory.FactoryNotFoundException;
/*     */ import org.geotools.referencing.factory.ReferencingFactoryContainer;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ import org.geotools.resources.i18n.Loggings;
/*     */ import org.geotools.resources.i18n.Vocabulary;
/*     */ import org.opengis.metadata.citation.Citation;
/*     */ import org.opengis.referencing.FactoryException;
/*     */ import org.opengis.referencing.crs.CRSAuthorityFactory;
/*     */ import org.opengis.referencing.cs.CSAuthorityFactory;
/*     */ import org.opengis.referencing.datum.DatumAuthorityFactory;
/*     */ import org.opengis.referencing.operation.CoordinateOperationAuthorityFactory;
/*     */ 
/*     */ public class ThreadedEpsgFactory extends DeferredAuthorityFactory implements CRSAuthorityFactory, CSAuthorityFactory, DatumAuthorityFactory, CoordinateOperationAuthorityFactory {
/*     */   public static final String DATASOURCE_NAME = "java:comp/env/jdbc/EPSG";
/*     */   
/*     */   private static final boolean ALLOW_REGISTRATION = false;
/*     */   
/*     */   static final int PRIORITY = 90;
/*     */   
/*     */   private final ReferencingFactoryContainer factories;
/*     */   
/*     */   private transient InitialContext registerInto;
/*     */   
/*     */   private String datasourceName;
/*     */   
/*     */   protected DataSource datasource;
/*     */   
/*     */   protected boolean dynamicDataSource = true;
/*     */   
/*     */   public ThreadedEpsgFactory() {
/* 150 */     this((Hints)null);
/*     */   }
/*     */   
/*     */   public ThreadedEpsgFactory(Hints userHints) {
/* 157 */     this(userHints, 90);
/*     */   }
/*     */   
/*     */   public ThreadedEpsgFactory(Hints userHints, int priority) {
/* 172 */     super(userHints, priority);
/* 174 */     Object hint = (userHints == null) ? null : userHints.get(Hints.EPSG_DATA_SOURCE);
/* 176 */     if (hint == null) {
/* 177 */       this.datasourceName = "java:comp/env/jdbc/EPSG";
/* 179 */       this.hints.put(Hints.EPSG_DATA_SOURCE, this.datasourceName);
/* 180 */     } else if (hint instanceof String) {
/* 181 */       this.datasourceName = (String)hint;
/* 183 */       this.hints.put(Hints.EPSG_DATA_SOURCE, this.datasourceName);
/* 184 */     } else if (hint instanceof Attributes.Name) {
/* 185 */       Attributes.Name name = (Attributes.Name)hint;
/* 186 */       this.hints.put(Hints.EPSG_DATA_SOURCE, name);
/* 187 */       this.datasourceName = name.toString();
/* 189 */     } else if (hint instanceof DataSource) {
/* 190 */       this.datasource = (DataSource)hint;
/* 191 */       this.hints.put(Hints.EPSG_DATA_SOURCE, this.datasource);
/* 192 */       this.datasourceName = "java:comp/env/jdbc/EPSG";
/* 193 */       this.dynamicDataSource = false;
/*     */     } 
/* 195 */     this.factories = ReferencingFactoryContainer.instance(userHints);
/* 196 */     long timeout = 1800000L;
/* 197 */     String defaultTimeout = System.getProperty("org.geotools.epsg.factory.timeout", String.valueOf(1800000));
/*     */     try {
/* 199 */       timeout = Long.valueOf(defaultTimeout).longValue();
/* 200 */     } catch (NumberFormatException e) {
/* 201 */       LOGGER.log(Level.WARNING, "Invalid value for org.geotools.epsg.factory.timeout, using the default (30 minutes) instead");
/*     */     } 
/* 205 */     if (timeout > 0L) {
/* 206 */       LOGGER.log(Level.FINE, "Setting the EPSG factory " + getClass().getName() + " to a " + timeout + "ms timeout");
/* 207 */       setTimeout(timeout);
/*     */     } else {
/* 209 */       LOGGER.log(Level.FINE, "The EPSG factory " + getClass().getName() + " will not timeout");
/*     */     } 
/*     */   }
/*     */   
/*     */   public Citation getAuthority() {
/* 220 */     Citation authority = super.getAuthority();
/* 221 */     return (authority != null) ? authority : Citations.EPSG;
/*     */   }
/*     */   
/*     */   public final synchronized DataSource getDataSource() throws SQLException {
/* 237 */     if (this.datasource == null)
/* 240 */       if (!isAvailable()) {
/* 242 */         this.datasource = null;
/* 243 */         throw new SQLException(Errors.format(131));
/*     */       }  
/* 246 */     return this.datasource;
/*     */   }
/*     */   
/*     */   public synchronized void setDataSource(DataSource datasource) throws SQLException {
/* 258 */     if (datasource != this.datasource) {
/*     */       try {
/* 260 */         dispose();
/* 261 */       } catch (FactoryException exception) {
/* 262 */         Throwable cause = exception.getCause();
/* 263 */         if (cause instanceof SQLException)
/* 264 */           throw (SQLException)cause; 
/* 266 */         if (cause instanceof RuntimeException)
/* 267 */           throw (RuntimeException)cause; 
/* 270 */         SQLException e = new SQLException(exception.getLocalizedMessage());
/* 271 */         e.initCause((Throwable)exception);
/* 272 */         throw e;
/*     */       } 
/* 274 */       this.datasource = datasource;
/*     */     } 
/*     */   }
/*     */   
/*     */   protected DataSource createDataSource() throws SQLException {
/* 311 */     InitialContext context = null;
/* 312 */     DataSource source = null;
/*     */     try {
/* 314 */       context = GeoTools.getInitialContext(new Hints(this.hints));
/* 315 */       source = (DataSource)context.lookup(this.datasourceName);
/* 316 */     } catch (IllegalArgumentException exception) {
/*     */     
/* 318 */     } catch (NoInitialContextException exception) {
/*     */     
/* 320 */     } catch (NamingException exception) {
/* 321 */       this.registerInto = context;
/*     */     } 
/* 324 */     return source;
/*     */   }
/*     */   
/*     */   protected AbstractAuthorityFactory createBackingStore(Hints hints) throws SQLException {
/* 346 */     DataSource source = getDataSource();
/* 347 */     Connection connection = source.getConnection();
/* 348 */     String quote = connection.getMetaData().getIdentifierQuoteString();
/* 349 */     if (quote.equals("\""))
/* 356 */       return (AbstractAuthorityFactory)new FactoryUsingAnsiSQL(hints, connection); 
/* 358 */     return (AbstractAuthorityFactory)new FactoryUsingSQL(hints, connection);
/*     */   }
/*     */   
/*     */   private AbstractAuthorityFactory createBackingStore0() throws FactoryException, SQLException {
/*     */     DataSource source;
/*     */     AbstractAuthorityFactory factory;
/* 373 */     assert Thread.holdsLock(this);
/* 374 */     Hints sourceHints = new Hints(this.hints);
/* 375 */     sourceHints.putAll(this.factories.getImplementationHints());
/* 376 */     if (this.datasource != null)
/* 377 */       return createBackingStore(sourceHints); 
/*     */     try {
/* 386 */       source = createDataSource();
/* 387 */       InitialContext context = this.registerInto;
/*     */     } finally {
/* 389 */       this.registerInto = null;
/*     */     } 
/* 391 */     if (source == null)
/* 392 */       throw new FactoryNotFoundException(Errors.format(131)); 
/*     */     try {
/* 396 */       this.datasource = source;
/* 397 */       factory = createBackingStore(sourceHints);
/*     */     } finally {
/* 399 */       this.datasource = null;
/*     */     } 
/* 418 */     this.datasource = source;
/* 419 */     return factory;
/*     */   }
/*     */   
/*     */   protected AbstractAuthorityFactory createBackingStore() throws FactoryException {
/*     */     AbstractAuthorityFactory factory;
/* 431 */     String product = '<' + Vocabulary.format(252) + '>';
/* 432 */     String url = product;
/*     */     try {
/* 434 */       factory = createBackingStore0();
/* 435 */       if (factory instanceof DirectEpsgFactory) {
/* 436 */         DatabaseMetaData info = ((DirectEpsgFactory)factory).getConnection().getMetaData();
/* 437 */         product = info.getDatabaseProductName();
/* 438 */         url = info.getURL();
/*     */       } 
/* 440 */     } catch (SQLException exception) {
/* 441 */       throw new FactoryException(Errors.format(21, "EPSG"), exception);
/*     */     } 
/* 444 */     log(Loggings.format(Level.CONFIG, 13, url, product));
/* 445 */     if (factory instanceof DirectEpsgFactory)
/* 446 */       ((DirectEpsgFactory)factory).buffered = (AbstractAuthorityFactory)this; 
/* 448 */     return factory;
/*     */   }
/*     */   
/*     */   private static void log(LogRecord record) {
/* 455 */     record.setSourceClassName(ThreadedEpsgFactory.class.getName());
/* 456 */     record.setSourceMethodName("createBackingStore");
/* 457 */     record.setLoggerName(LOGGER.getName());
/* 458 */     LOGGER.log(record);
/*     */   }
/*     */   
/*     */   protected boolean canDisposeBackingStore(AbstractAuthorityFactory backingStore) {
/* 470 */     if (backingStore instanceof DirectEpsgFactory)
/* 471 */       return ((DirectEpsgFactory)backingStore).canDispose(); 
/* 473 */     return super.canDisposeBackingStore(backingStore);
/*     */   }
/*     */   
/*     */   protected void disposeBackingStore() {
/* 478 */     super.disposeBackingStore();
/* 479 */     if (this.dynamicDataSource)
/* 480 */       this.datasource = null; 
/*     */   }
/*     */   
/*     */   public synchronized void dispose() throws FactoryException {
/* 486 */     super.dispose();
/* 487 */     this.datasource = null;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\factory\epsg\ThreadedEpsgFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */