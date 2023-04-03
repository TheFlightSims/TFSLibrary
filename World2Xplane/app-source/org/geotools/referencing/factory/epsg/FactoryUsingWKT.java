/*     */ package org.geotools.referencing.factory.epsg;
/*     */ 
/*     */ import java.awt.RenderingHints;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintWriter;
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URL;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.TreeMap;
/*     */ import java.util.TreeSet;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.LogRecord;
/*     */ import org.geotools.factory.Hints;
/*     */ import org.geotools.io.IndentedLineWriter;
/*     */ import org.geotools.io.TableWriter;
/*     */ import org.geotools.metadata.iso.citation.CitationImpl;
/*     */ import org.geotools.metadata.iso.citation.Citations;
/*     */ import org.geotools.referencing.ReferencingFactoryFinder;
/*     */ import org.geotools.referencing.factory.AbstractAuthorityFactory;
/*     */ import org.geotools.referencing.factory.DeferredAuthorityFactory;
/*     */ import org.geotools.referencing.factory.FactoryNotFoundException;
/*     */ import org.geotools.referencing.factory.PropertyAuthorityFactory;
/*     */ import org.geotools.referencing.factory.ReferencingFactoryContainer;
/*     */ import org.geotools.resources.Arguments;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ import org.geotools.resources.i18n.Loggings;
/*     */ import org.geotools.resources.i18n.Vocabulary;
/*     */ import org.geotools.util.logging.Logging;
/*     */ import org.opengis.metadata.Identifier;
/*     */ import org.opengis.metadata.citation.Citation;
/*     */ import org.opengis.referencing.FactoryException;
/*     */ import org.opengis.referencing.IdentifiedObject;
/*     */ import org.opengis.referencing.crs.CRSAuthorityFactory;
/*     */ import org.opengis.referencing.crs.CoordinateReferenceSystem;
/*     */ 
/*     */ public class FactoryUsingWKT extends DeferredAuthorityFactory implements CRSAuthorityFactory {
/*     */   private Citation authority;
/*     */   
/*     */   public static final String FILENAME = "epsg.properties";
/*     */   
/*     */   private final ReferencingFactoryContainer factories;
/*     */   
/*     */   protected static final int DEFAULT_PRIORITY = 80;
/*     */   
/*     */   private final File directory;
/*     */   
/*     */   public FactoryUsingWKT() {
/* 140 */     this((Hints)null);
/*     */   }
/*     */   
/*     */   public FactoryUsingWKT(Hints userHints) {
/* 150 */     this(userHints, 80);
/*     */   }
/*     */   
/*     */   protected FactoryUsingWKT(Hints userHints, int priority) {
/* 157 */     super(userHints, priority);
/* 158 */     this.factories = ReferencingFactoryContainer.instance(userHints);
/* 159 */     Object hint = null;
/* 160 */     if (userHints != null)
/* 161 */       hint = userHints.get(Hints.CRS_AUTHORITY_EXTRA_DIRECTORY); 
/* 163 */     if (hint instanceof File) {
/* 164 */       this.directory = (File)hint;
/* 165 */     } else if (hint instanceof String) {
/* 166 */       this.directory = new File((String)hint);
/*     */     } else {
/* 168 */       this.directory = null;
/*     */     } 
/* 170 */     this.hints.put(Hints.CRS_AUTHORITY_EXTRA_DIRECTORY, this.directory);
/* 172 */     setTimeout(900000L);
/*     */   }
/*     */   
/*     */   public synchronized Citation getAuthority() {
/* 185 */     if (this.authority == null) {
/* 186 */       Citation[] authorities = getAuthorities();
/* 187 */       switch (authorities.length) {
/*     */         case 0:
/* 188 */           this.authority = Citations.EPSG;
/* 202 */           return this.authority;
/*     */         case 1:
/*     */           this.authority = authorities[0];
/* 202 */           return this.authority;
/*     */       } 
/*     */       CitationImpl c = new CitationImpl(authorities[0]);
/*     */       Collection<Identifier> identifiers = c.getIdentifiers();
/*     */       for (int i = 1; i < authorities.length; i++)
/*     */         identifiers.addAll(authorities[i].getIdentifiers()); 
/*     */       c.freeze();
/*     */       this.authority = (Citation)c;
/*     */     } 
/* 202 */     return this.authority;
/*     */   }
/*     */   
/*     */   protected Citation[] getAuthorities() {
/* 219 */     return new Citation[] { Citations.EPSG };
/*     */   }
/*     */   
/*     */   protected URL getDefinitionsURL() {
/*     */     try {
/* 241 */       if (this.directory != null) {
/* 242 */         File file = new File(this.directory, "epsg.properties");
/* 243 */         if (file.isFile())
/* 244 */           return file.toURI().toURL(); 
/*     */       } 
/* 247 */     } catch (SecurityException exception) {
/* 248 */       Logging.unexpectedException(LOGGER, exception);
/* 249 */     } catch (MalformedURLException exception) {
/* 250 */       Logging.unexpectedException(LOGGER, exception);
/*     */     } 
/* 252 */     return FactoryUsingWKT.class.getResource("epsg.properties");
/*     */   }
/*     */   
/*     */   protected AbstractAuthorityFactory createBackingStore() throws FactoryException {
/*     */     try {
/* 265 */       URL url = getDefinitionsURL();
/* 266 */       if (url == null)
/* 267 */         throw new FactoryNotFoundException(Errors.format(50, "epsg.properties")); 
/* 270 */       Iterator<? extends Identifier> ids = getAuthority().getIdentifiers().iterator();
/* 271 */       String authority = ids.hasNext() ? ((Identifier)ids.next()).getCode() : "EPSG";
/* 272 */       LogRecord record = Loggings.format(Level.CONFIG, 49, url.getPath(), authority);
/* 274 */       record.setLoggerName(LOGGER.getName());
/* 275 */       LOGGER.log(record);
/* 276 */       return (AbstractAuthorityFactory)new PropertyAuthorityFactory(this.factories, getAuthorities(), url);
/* 277 */     } catch (IOException exception) {
/* 278 */       throw new FactoryException(Errors.format(28, "epsg.properties"), exception);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static final <T extends AbstractAuthorityFactory> T getFactory(Class<T> type) {
/* 286 */     return type.cast(ReferencingFactoryFinder.getCRSAuthorityFactory("EPSG", new Hints((RenderingHints.Key)Hints.CRS_AUTHORITY_FACTORY, type)));
/*     */   }
/*     */   
/*     */   protected Set reportDuplicatedCodes(PrintWriter out) throws FactoryException {
/* 303 */     AbstractAuthorityFactory sqlFactory = (AbstractAuthorityFactory)getFactory(ThreadedEpsgFactory.class);
/* 304 */     Vocabulary resources = Vocabulary.getResources(null);
/* 305 */     out.println(resources.getLabel(27));
/*     */     try {
/* 307 */       IndentedLineWriter w = new IndentedLineWriter(out);
/* 308 */       w.setIndentation(4);
/* 309 */       w.write(sqlFactory.getBackingStoreDescription());
/* 310 */       w.flush();
/* 311 */     } catch (IOException e) {
/* 313 */       throw new AssertionError(e);
/*     */     } 
/* 315 */     out.println();
/* 316 */     Set<String> wktCodes = getAuthorityCodes(IdentifiedObject.class);
/* 317 */     Set<String> sqlCodes = sqlFactory.getAuthorityCodes(IdentifiedObject.class);
/* 318 */     Set<String> duplicated = new TreeSet<String>();
/* 319 */     for (String code : wktCodes) {
/* 320 */       code = code.trim();
/* 321 */       if (sqlCodes.contains(code))
/* 322 */         duplicated.add(code); 
/*     */     } 
/* 332 */     if (duplicated.isEmpty()) {
/* 333 */       out.println(resources.getString(153));
/*     */     } else {
/* 335 */       for (String code : duplicated) {
/* 336 */         out.print(resources.getLabel(52));
/* 337 */         out.println(code);
/*     */       } 
/*     */     } 
/* 340 */     return duplicated;
/*     */   }
/*     */   
/*     */   protected Set reportInstantiationFailures(PrintWriter out) throws FactoryException {
/* 357 */     Set<String> codes = getAuthorityCodes(CoordinateReferenceSystem.class);
/* 358 */     Map<String, String> failures = new TreeMap<String, String>();
/* 359 */     for (String code : codes) {
/*     */       try {
/* 361 */         createCoordinateReferenceSystem(code);
/* 362 */       } catch (FactoryException exception) {
/* 363 */         failures.put(code, exception.getLocalizedMessage());
/*     */       } 
/*     */     } 
/* 366 */     if (!failures.isEmpty()) {
/* 367 */       TableWriter writer = new TableWriter(out, " ");
/* 368 */       for (Map.Entry<String, String> entry : failures.entrySet()) {
/* 369 */         writer.write(entry.getKey());
/* 370 */         writer.write(58);
/* 371 */         writer.nextColumn();
/* 372 */         writer.write(entry.getValue());
/* 373 */         writer.nextLine();
/*     */       } 
/*     */       try {
/* 376 */         writer.flush();
/* 377 */       } catch (IOException e) {
/* 379 */         throw new AssertionError(e);
/*     */       } 
/*     */     } 
/* 382 */     return failures.keySet();
/*     */   }
/*     */   
/*     */   public static void main(String[] args) throws FactoryException {
/* 405 */     main(args, FactoryUsingWKT.class);
/*     */   }
/*     */   
/*     */   static void main(String[] args, Class<? extends FactoryUsingWKT> type) throws FactoryException {
/* 414 */     Arguments arguments = new Arguments(args);
/* 415 */     Locale.setDefault(arguments.locale);
/* 416 */     boolean duplicated = arguments.getFlag("-duplicated");
/* 417 */     boolean instantiate = arguments.getFlag("-test");
/* 418 */     args = arguments.getRemainingArguments(0);
/* 419 */     FactoryUsingWKT factory = getFactory((Class)type);
/* 420 */     if (duplicated)
/* 421 */       factory.reportDuplicatedCodes(arguments.out); 
/* 423 */     if (instantiate)
/* 424 */       factory.reportInstantiationFailures(arguments.out); 
/* 426 */     factory.dispose();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\factory\epsg\FactoryUsingWKT.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */