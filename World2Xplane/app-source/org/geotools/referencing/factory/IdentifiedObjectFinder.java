/*     */ package org.geotools.referencing.factory;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.Set;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import org.geotools.metadata.iso.citation.Citations;
/*     */ import org.geotools.referencing.AbstractIdentifiedObject;
/*     */ import org.geotools.referencing.CRS;
/*     */ import org.geotools.util.logging.Logging;
/*     */ import org.opengis.metadata.Identifier;
/*     */ import org.opengis.metadata.citation.Citation;
/*     */ import org.opengis.referencing.AuthorityFactory;
/*     */ import org.opengis.referencing.FactoryException;
/*     */ import org.opengis.referencing.IdentifiedObject;
/*     */ import org.opengis.referencing.NoSuchAuthorityCodeException;
/*     */ import org.opengis.referencing.ReferenceIdentifier;
/*     */ import org.opengis.util.GenericName;
/*     */ 
/*     */ public class IdentifiedObjectFinder {
/*  61 */   public static final Logger LOGGER = Logging.getLogger("org.geotools.referencing.factory.finder");
/*     */   
/*     */   private AuthorityFactoryProxy proxy;
/*     */   
/*     */   private boolean fullScan = true;
/*     */   
/*     */   protected IdentifiedObjectFinder() {}
/*     */   
/*     */   IdentifiedObjectFinder(IdentifiedObjectFinder finder) {
/*  81 */     setProxy(finder.getProxy());
/*     */   }
/*     */   
/*     */   protected IdentifiedObjectFinder(AuthorityFactory factory, Class type) {
/*  95 */     setProxy(AuthorityFactoryProxy.getInstance(factory, type));
/*     */   }
/*     */   
/*     */   protected AuthorityFactoryProxy getProxy() {
/* 114 */     return this.proxy;
/*     */   }
/*     */   
/*     */   public boolean isFullScanAllowed() {
/* 124 */     return this.fullScan;
/*     */   }
/*     */   
/*     */   public void setFullScanAllowed(boolean fullScan) {
/* 132 */     this.fullScan = fullScan;
/*     */   }
/*     */   
/*     */   public IdentifiedObject find(IdentifiedObject object) throws FactoryException {
/* 168 */     IdentifiedObject candidate = createFromIdentifiers(object);
/* 169 */     if (candidate != null)
/* 170 */       return candidate; 
/* 177 */     candidate = createFromNames(object);
/* 178 */     if (candidate != null)
/* 179 */       return candidate; 
/* 184 */     return this.fullScan ? createFromCodes(object) : null;
/*     */   }
/*     */   
/*     */   public String findIdentifier(IdentifiedObject object) throws FactoryException {
/* 193 */     IdentifiedObject candidate = find(object);
/* 194 */     return (candidate != null) ? getIdentifier(candidate) : null;
/*     */   }
/*     */   
/*     */   protected Citation getAuthority() {
/* 202 */     return getProxy().getAuthorityFactory().getAuthority();
/*     */   }
/*     */   
/*     */   final String getIdentifier(IdentifiedObject object) {
/* 208 */     Citation authority = getAuthority();
/* 209 */     if (ReferencingFactory.ALL.equals(authority))
/* 215 */       authority = null; 
/* 217 */     ReferenceIdentifier identifier = AbstractIdentifiedObject.getIdentifier(object, authority);
/* 218 */     if (identifier == null)
/* 219 */       identifier = object.getName(); 
/* 222 */     String codespace = identifier.getCodeSpace();
/* 223 */     String code = identifier.getCode();
/* 224 */     if (codespace != null)
/* 225 */       return codespace + ':' + code; 
/* 227 */     return code;
/*     */   }
/*     */   
/*     */   final IdentifiedObject createFromIdentifiers(IdentifiedObject object) throws FactoryException {
/* 246 */     Citation authority = getProxy().getAuthorityFactory().getAuthority();
/* 247 */     boolean isAll = ReferencingFactory.ALL.equals(authority);
/* 248 */     for (Iterator<Identifier> it = object.getIdentifiers().iterator(); it.hasNext(); ) {
/* 249 */       Identifier id = it.next();
/* 250 */       if (!isAll && !Citations.identifierMatches(authority, id.getAuthority()))
/*     */         continue; 
/*     */       try {
/* 256 */         candidate = getProxy().create(id.getCode());
/* 257 */       } catch (NoSuchAuthorityCodeException e) {
/*     */         continue;
/*     */       } 
/* 261 */       IdentifiedObject candidate = deriveEquivalent(candidate, object);
/* 262 */       if (candidate != null)
/* 263 */         return candidate; 
/*     */     } 
/* 266 */     return null;
/*     */   }
/*     */   
/*     */   final IdentifiedObject createFromNames(IdentifiedObject object) throws FactoryException {
/*     */     try {
/* 288 */       IdentifiedObject candidate = getProxy().create(object.getName().getCode());
/* 289 */       candidate = deriveEquivalent(candidate, object);
/* 290 */       if (candidate != null)
/* 291 */         return candidate; 
/* 293 */     } catch (FactoryException e) {}
/* 302 */     for (Iterator<GenericName> it = object.getAlias().iterator(); it.hasNext(); ) {
/* 303 */       GenericName id = it.next();
/*     */       try {
/* 305 */         identifiedObject = getProxy().create(id.toString());
/* 306 */       } catch (FactoryException e) {
/*     */         continue;
/*     */       } 
/* 310 */       IdentifiedObject identifiedObject = deriveEquivalent(identifiedObject, object);
/* 311 */       if (identifiedObject != null)
/* 312 */         return identifiedObject; 
/*     */     } 
/* 315 */     return null;
/*     */   }
/*     */   
/*     */   final IdentifiedObject createFromCodes(IdentifiedObject object) throws FactoryException {
/* 340 */     Set codes = getCodeCandidates(object);
/* 341 */     for (Iterator<String> it = codes.iterator(); it.hasNext(); ) {
/* 342 */       String code = it.next();
/*     */       try {
/* 345 */         candidate = getProxy().create(code);
/* 347 */       } catch (FactoryException e) {
/* 348 */         LOGGER.log(Level.FINEST, "Could not create '" + code + "':" + e);
/*     */         continue;
/* 352 */       } catch (Exception problemCode) {
/* 353 */         LOGGER.log(Level.FINEST, "Could not create '" + code + "':" + problemCode, problemCode);
/*     */         continue;
/*     */       } 
/* 357 */       IdentifiedObject candidate = deriveEquivalent(candidate, object);
/* 358 */       if (candidate != null)
/* 359 */         return candidate; 
/*     */     } 
/* 362 */     return null;
/*     */   }
/*     */   
/*     */   protected Set getCodeCandidates(IdentifiedObject object) throws FactoryException {
/* 386 */     return getProxy().getAuthorityCodes();
/*     */   }
/*     */   
/*     */   protected IdentifiedObject deriveEquivalent(IdentifiedObject candidate, IdentifiedObject model) throws FactoryException {
/* 427 */     return CRS.equalsIgnoreMetadata(candidate, model) ? candidate : null;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 435 */     return getProxy().toString(IdentifiedObjectFinder.class);
/*     */   }
/*     */   
/*     */   public void setProxy(AuthorityFactoryProxy proxy) {
/* 445 */     this.proxy = proxy;
/*     */   }
/*     */   
/*     */   static class Adapter extends IdentifiedObjectFinder {
/*     */     protected final IdentifiedObjectFinder finder;
/*     */     
/*     */     protected Adapter(IdentifiedObjectFinder finder) {
/* 469 */       super(finder);
/* 470 */       this.finder = finder;
/*     */     }
/*     */     
/*     */     public void setFullScanAllowed(boolean fullScan) {
/* 478 */       this.finder.setFullScanAllowed(fullScan);
/* 479 */       super.setFullScanAllowed(fullScan);
/*     */     }
/*     */     
/*     */     protected Set getCodeCandidates(IdentifiedObject object) throws FactoryException {
/* 488 */       return this.finder.getCodeCandidates(object);
/*     */     }
/*     */     
/*     */     protected IdentifiedObject deriveEquivalent(IdentifiedObject candidate, IdentifiedObject model) throws FactoryException {
/* 501 */       return this.finder.deriveEquivalent(candidate, model);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\factory\IdentifiedObjectFinder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */