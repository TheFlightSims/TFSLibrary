/*     */ package org.geotools.referencing.factory.epsg;
/*     */ 
/*     */ import java.awt.RenderingHints;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URL;
/*     */ import java.util.Iterator;
/*     */ import java.util.Set;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.LogRecord;
/*     */ import org.geotools.factory.Hints;
/*     */ import org.geotools.metadata.iso.citation.Citations;
/*     */ import org.geotools.referencing.ReferencingFactoryFinder;
/*     */ import org.geotools.referencing.factory.AbstractAuthorityFactory;
/*     */ import org.geotools.referencing.factory.DeferredAuthorityFactory;
/*     */ import org.geotools.referencing.factory.FactoryNotFoundException;
/*     */ import org.geotools.referencing.factory.PropertyCoordinateOperationAuthorityFactory;
/*     */ import org.geotools.referencing.factory.ReferencingFactoryContainer;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ import org.geotools.resources.i18n.Loggings;
/*     */ import org.geotools.util.logging.Logging;
/*     */ import org.opengis.metadata.Identifier;
/*     */ import org.opengis.metadata.citation.Citation;
/*     */ import org.opengis.referencing.FactoryException;
/*     */ import org.opengis.referencing.NoSuchAuthorityCodeException;
/*     */ import org.opengis.referencing.operation.CoordinateOperation;
/*     */ import org.opengis.referencing.operation.CoordinateOperationAuthorityFactory;
/*     */ 
/*     */ public class CoordinateOperationFactoryUsingWKT extends DeferredAuthorityFactory implements CoordinateOperationAuthorityFactory {
/*     */   protected Citation authority;
/*     */   
/*     */   public static final String FILENAME = "epsg_operations.properties";
/*     */   
/*     */   public static final int PRIORITY = 30;
/*     */   
/*     */   protected final ReferencingFactoryContainer factories;
/*     */   
/*     */   protected final String directory;
/*     */   
/* 117 */   protected CoordinateOperationAuthorityFactory fallbackAuthorityFactory = null;
/*     */   
/*     */   protected boolean fallbackAuthorityFactorySearched = false;
/*     */   
/*     */   public CoordinateOperationFactoryUsingWKT() {
/* 128 */     this((Hints)null, 30);
/*     */   }
/*     */   
/*     */   public CoordinateOperationFactoryUsingWKT(Hints userHints) {
/* 135 */     this(userHints, 30);
/*     */   }
/*     */   
/*     */   protected CoordinateOperationFactoryUsingWKT(Hints userHints, int priority) {
/* 142 */     super(userHints, priority);
/* 143 */     this.factories = ReferencingFactoryContainer.instance(userHints);
/* 146 */     Object directoryHint = null;
/* 147 */     if (userHints != null && userHints.get(Hints.CRS_AUTHORITY_EXTRA_DIRECTORY) != null) {
/* 148 */       directoryHint = userHints.get(Hints.CRS_AUTHORITY_EXTRA_DIRECTORY);
/* 149 */     } else if (Hints.getSystemDefault((RenderingHints.Key)Hints.CRS_AUTHORITY_EXTRA_DIRECTORY) != null) {
/* 150 */       directoryHint = Hints.getSystemDefault((RenderingHints.Key)Hints.CRS_AUTHORITY_EXTRA_DIRECTORY);
/*     */     } 
/* 152 */     if (directoryHint != null) {
/* 153 */       this.directory = directoryHint.toString();
/* 154 */       this.hints.put(Hints.CRS_AUTHORITY_EXTRA_DIRECTORY, this.directory);
/*     */     } else {
/* 156 */       this.directory = null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public synchronized Citation getAuthority() {
/* 162 */     if (this.authority == null)
/* 163 */       this.authority = Citations.EPSG; 
/* 165 */     return this.authority;
/*     */   }
/*     */   
/*     */   protected AbstractAuthorityFactory createBackingStore() throws FactoryException {
/*     */     try {
/* 178 */       URL url = getDefinitionsURL();
/* 179 */       if (url == null)
/* 180 */         throw new FactoryNotFoundException(Errors.format(50, "epsg_operations.properties")); 
/* 183 */       Iterator<? extends Identifier> ids = getAuthority().getIdentifiers().iterator();
/* 184 */       String authority = ids.hasNext() ? ((Identifier)ids.next()).getCode() : "EPSG";
/* 185 */       LogRecord record = Loggings.format(Level.CONFIG, 49, url.getPath(), authority);
/* 187 */       record.setLoggerName(LOGGER.getName());
/* 188 */       LOGGER.log(record);
/* 189 */       return (AbstractAuthorityFactory)new PropertyCoordinateOperationAuthorityFactory(this.factories, getAuthority(), url);
/* 190 */     } catch (IOException exception) {
/* 191 */       throw new FactoryException(Errors.format(28, "epsg_operations.properties"), exception);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected URL getDefinitionsURL() {
/*     */     try {
/* 212 */       if (this.directory != null) {
/* 213 */         File file = new File(this.directory, "epsg_operations.properties");
/* 214 */         if (file.isFile())
/* 215 */           return file.toURI().toURL(); 
/*     */       } 
/* 218 */     } catch (SecurityException exception) {
/* 219 */       Logging.unexpectedException(LOGGER, exception);
/* 220 */     } catch (MalformedURLException exception) {
/* 221 */       Logging.unexpectedException(LOGGER, exception);
/*     */     } 
/* 223 */     return getClass().getResource("epsg_operations.properties");
/*     */   }
/*     */   
/*     */   public Set<CoordinateOperation> createFromCoordinateReferenceSystemCodes(String sourceCRS, String targetCRS) throws NoSuchAuthorityCodeException, FactoryException {
/* 244 */     Set<CoordinateOperation> coordops = super.createFromCoordinateReferenceSystemCodes(sourceCRS, targetCRS);
/* 245 */     if (coordops.isEmpty()) {
/* 247 */       CoordinateOperationAuthorityFactory fallback = getFallbackAuthorityFactory();
/* 248 */       if (fallback != null)
/* 249 */         coordops = fallback.createFromCoordinateReferenceSystemCodes(sourceCRS, targetCRS); 
/*     */     } 
/* 252 */     return coordops;
/*     */   }
/*     */   
/*     */   public CoordinateOperation createCoordinateOperation(String code) throws NoSuchAuthorityCodeException, FactoryException {
/* 270 */     CoordinateOperation coordop = super.createCoordinateOperation(code);
/* 271 */     if (coordop == null) {
/* 272 */       CoordinateOperationAuthorityFactory fallback = getFallbackAuthorityFactory();
/* 273 */       if (fallback != null)
/* 274 */         coordop = fallback.createCoordinateOperation(code); 
/*     */     } 
/* 277 */     return coordop;
/*     */   }
/*     */   
/*     */   protected CoordinateOperationAuthorityFactory getFallbackAuthorityFactory() throws NoSuchAuthorityCodeException, FactoryException {
/* 291 */     if (!this.fallbackAuthorityFactorySearched) {
/* 292 */       CoordinateOperationAuthorityFactory candidate = null;
/* 298 */       Hints h = new Hints();
/* 299 */       h.put(Hints.FORCE_LONGITUDE_FIRST_AXIS_ORDER, Boolean.FALSE);
/* 300 */       h.put(Hints.FORCE_STANDARD_AXIS_DIRECTIONS, Boolean.FALSE);
/* 301 */       h.put(Hints.FORCE_STANDARD_AXIS_UNITS, Boolean.FALSE);
/* 303 */       Set<CoordinateOperationAuthorityFactory> factories = ReferencingFactoryFinder.getCoordinateOperationAuthorityFactories(h);
/* 305 */       Iterator<CoordinateOperationAuthorityFactory> it = factories.iterator();
/* 308 */       while (it.hasNext()) {
/* 309 */         candidate = it.next();
/* 310 */         if (candidate == this)
/*     */           break; 
/*     */       } 
/* 316 */       while (it.hasNext()) {
/* 317 */         candidate = it.next();
/* 318 */         if (!(candidate instanceof CoordinateOperationFactoryUsingWKT) && candidate.getAuthority().getTitle().equals(getAuthority().getTitle())) {
/* 320 */           this.fallbackAuthorityFactory = candidate;
/*     */           break;
/*     */         } 
/*     */       } 
/* 324 */       this.fallbackAuthorityFactorySearched = true;
/*     */     } 
/* 327 */     return this.fallbackAuthorityFactory;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\factory\epsg\CoordinateOperationFactoryUsingWKT.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */