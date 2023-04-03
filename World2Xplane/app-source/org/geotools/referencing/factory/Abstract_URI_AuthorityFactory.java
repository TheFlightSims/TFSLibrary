/*     */ package org.geotools.referencing.factory;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import java.util.SortedMap;
/*     */ import java.util.TreeMap;
/*     */ import org.geotools.factory.Hints;
/*     */ import org.geotools.util.Version;
/*     */ import org.opengis.metadata.citation.Citation;
/*     */ import org.opengis.referencing.AuthorityFactory;
/*     */ import org.opengis.referencing.FactoryException;
/*     */ import org.opengis.referencing.NoSuchAuthorityCodeException;
/*     */ import org.opengis.referencing.crs.CRSAuthorityFactory;
/*     */ import org.opengis.referencing.cs.CSAuthorityFactory;
/*     */ import org.opengis.referencing.datum.DatumAuthorityFactory;
/*     */ import org.opengis.referencing.operation.CoordinateOperationAuthorityFactory;
/*     */ 
/*     */ public abstract class Abstract_URI_AuthorityFactory extends AuthorityFactoryAdapter implements CRSAuthorityFactory, CSAuthorityFactory, DatumAuthorityFactory, CoordinateOperationAuthorityFactory {
/*     */   private final AllAuthoritiesFactory factory;
/*     */   
/*  59 */   private final SortedMap<Version, AuthorityFactory> byVersions = new TreeMap<Version, AuthorityFactory>();
/*     */   
/*     */   private transient URI_Parser last;
/*     */   
/*     */   public Abstract_URI_AuthorityFactory(String hintsAuthority) {
/*  73 */     this((Hints)null, hintsAuthority);
/*     */   }
/*     */   
/*     */   public Abstract_URI_AuthorityFactory(Hints userHints, String hintsAuthority) {
/*  84 */     this(HTTP_AuthorityFactory.getFactory(userHints, hintsAuthority));
/*     */   }
/*     */   
/*     */   public Abstract_URI_AuthorityFactory(AllAuthoritiesFactory factory) {
/*  93 */     super(factory);
/*  94 */     this.factory = factory;
/*     */   }
/*     */   
/*     */   protected abstract URI_Parser buildParser(String paramString) throws NoSuchAuthorityCodeException;
/*     */   
/*     */   public abstract Citation getAuthority();
/*     */   
/*     */   private URI_Parser getParser(String code) throws NoSuchAuthorityCodeException {
/* 124 */     URI_Parser parser = this.last;
/* 125 */     if (parser == null || !parser.uri.equals(code))
/* 126 */       this.last = parser = buildParser(code); 
/* 128 */     return parser;
/*     */   }
/*     */   
/*     */   protected AuthorityFactory getAuthorityFactory(String code) throws FactoryException {
/* 142 */     if (code != null)
/* 143 */       return getAuthorityFactory((Class)(getParser(code)).type.type.asSubclass(AuthorityFactory.class), code); 
/* 145 */     return super.getAuthorityFactory(code);
/*     */   }
/*     */   
/*     */   protected DatumAuthorityFactory getDatumAuthorityFactory(String code) throws FactoryException {
/* 164 */     if (code != null) {
/* 165 */       URI_Parser parser = getParser(code);
/* 166 */       parser.logWarningIfTypeMismatch(this, (Class)DatumAuthorityFactory.class);
/* 167 */       AuthorityFactory factory = getVersionedFactory(parser);
/* 168 */       if (factory instanceof DatumAuthorityFactory)
/* 169 */         return (DatumAuthorityFactory)factory; 
/*     */     } 
/* 172 */     return super.getDatumAuthorityFactory(code);
/*     */   }
/*     */   
/*     */   protected CSAuthorityFactory getCSAuthorityFactory(String code) throws FactoryException {
/* 190 */     if (code != null) {
/* 191 */       URI_Parser parser = getParser(code);
/* 192 */       parser.logWarningIfTypeMismatch(this, (Class)CSAuthorityFactory.class);
/* 193 */       AuthorityFactory factory = getVersionedFactory(parser);
/* 194 */       if (factory instanceof CSAuthorityFactory)
/* 195 */         return (CSAuthorityFactory)factory; 
/*     */     } 
/* 198 */     return super.getCSAuthorityFactory(code);
/*     */   }
/*     */   
/*     */   protected CRSAuthorityFactory getCRSAuthorityFactory(String code) throws FactoryException {
/* 216 */     if (code != null) {
/* 217 */       URI_Parser parser = getParser(code);
/* 218 */       parser.logWarningIfTypeMismatch(this, (Class)CRSAuthorityFactory.class);
/* 219 */       AuthorityFactory factory = getVersionedFactory(parser);
/* 220 */       if (factory instanceof CRSAuthorityFactory)
/* 221 */         return (CRSAuthorityFactory)factory; 
/*     */     } 
/* 224 */     return super.getCRSAuthorityFactory(code);
/*     */   }
/*     */   
/*     */   protected CoordinateOperationAuthorityFactory getCoordinateOperationAuthorityFactory(String code) throws FactoryException {
/* 243 */     if (code != null) {
/* 244 */       URI_Parser parser = getParser(code);
/* 245 */       parser.logWarningIfTypeMismatch(this, (Class)CoordinateOperationAuthorityFactory.class);
/* 246 */       AuthorityFactory factory = getVersionedFactory(parser);
/* 247 */       if (factory instanceof CoordinateOperationAuthorityFactory)
/* 248 */         return (CoordinateOperationAuthorityFactory)factory; 
/*     */     } 
/* 251 */     return super.getCoordinateOperationAuthorityFactory(code);
/*     */   }
/*     */   
/*     */   private AuthorityFactory getVersionedFactory(URI_Parser parser) throws FactoryException {
/*     */     AuthorityFactory factory;
/* 264 */     Version version = parser.version;
/* 265 */     if (version == null)
/* 266 */       return null; 
/* 269 */     synchronized (this.byVersions) {
/* 270 */       factory = this.byVersions.get(version);
/* 271 */       if (factory == null) {
/* 272 */         factory = createVersionedFactory(version);
/* 273 */         if (factory != null)
/* 274 */           this.byVersions.put(version, factory); 
/*     */       } 
/*     */     } 
/* 278 */     return factory;
/*     */   }
/*     */   
/*     */   protected AuthorityFactory createVersionedFactory(Version version) throws FactoryException {
/* 295 */     Hints hints = new Hints(this.factory.getImplementationHints());
/* 296 */     hints.put(Hints.VERSION, version);
/* 297 */     List<AuthorityFactory> factories = Arrays.asList(new AuthorityFactory[] { new AllAuthoritiesFactory(hints), this.factory });
/* 301 */     return FallbackAuthorityFactory.create(factories);
/*     */   }
/*     */   
/*     */   protected String toBackingFactoryCode(String code) throws FactoryException {
/* 313 */     return getParser(code).getAuthorityCode();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\factory\Abstract_URI_AuthorityFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */