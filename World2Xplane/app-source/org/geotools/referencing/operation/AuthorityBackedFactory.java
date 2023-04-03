/*     */ package org.geotools.referencing.operation;
/*     */ 
/*     */ import java.awt.RenderingHints;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.LogRecord;
/*     */ import org.geotools.factory.FactoryRegistryException;
/*     */ import org.geotools.factory.Hints;
/*     */ import org.geotools.factory.OptionalFactory;
/*     */ import org.geotools.referencing.AbstractIdentifiedObject;
/*     */ import org.geotools.referencing.CRS;
/*     */ import org.geotools.referencing.ReferencingFactoryFinder;
/*     */ import org.geotools.referencing.factory.BackingStoreException;
/*     */ import org.geotools.resources.i18n.Loggings;
/*     */ import org.opengis.metadata.citation.Citation;
/*     */ import org.opengis.referencing.AuthorityFactory;
/*     */ import org.opengis.referencing.FactoryException;
/*     */ import org.opengis.referencing.IdentifiedObject;
/*     */ import org.opengis.referencing.NoSuchAuthorityCodeException;
/*     */ import org.opengis.referencing.ReferenceIdentifier;
/*     */ import org.opengis.referencing.crs.CoordinateReferenceSystem;
/*     */ import org.opengis.referencing.operation.ConcatenatedOperation;
/*     */ import org.opengis.referencing.operation.CoordinateOperation;
/*     */ import org.opengis.referencing.operation.CoordinateOperationAuthorityFactory;
/*     */ import org.opengis.referencing.operation.MathTransform;
/*     */ import org.opengis.referencing.operation.MathTransformFactory;
/*     */ import org.opengis.referencing.operation.NoninvertibleTransformException;
/*     */ import org.opengis.referencing.operation.Operation;
/*     */ import org.opengis.referencing.operation.OperationMethod;
/*     */ import org.opengis.referencing.operation.SingleOperation;
/*     */ 
/*     */ public class AuthorityBackedFactory extends DefaultCoordinateOperationFactory implements OptionalFactory {
/*     */   static final int PRIORITY = 60;
/*     */   
/*     */   private static final String DEFAULT_AUTHORITY = "EPSG";
/*     */   
/*     */   private CoordinateOperationAuthorityFactory authorityFactory;
/*     */   
/*  94 */   private final ThreadLocal<Boolean> processing = new ThreadLocal<Boolean>();
/*     */   
/*     */   public AuthorityBackedFactory() {
/* 102 */     this((Hints)null);
/*     */   }
/*     */   
/*     */   public AuthorityBackedFactory(Hints userHints) {
/* 114 */     super(userHints, 60);
/* 123 */     userHints = new Hints((RenderingHints)userHints);
/* 124 */     userHints.keySet().removeAll(this.hints.keySet());
/* 125 */     userHints.remove(Hints.FORCE_LONGITUDE_FIRST_AXIS_ORDER);
/* 126 */     userHints.remove(Hints.FORCE_STANDARD_AXIS_DIRECTIONS);
/* 127 */     userHints.remove(Hints.FORCE_STANDARD_AXIS_UNITS);
/* 128 */     if (!userHints.isEmpty()) {
/* 129 */       noForce(userHints);
/* 130 */       this.authorityFactory = ReferencingFactoryFinder.getCoordinateOperationAuthorityFactory("EPSG", userHints);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void noForce(Hints userHints) {
/* 148 */     userHints.put(Hints.FORCE_LONGITUDE_FIRST_AXIS_ORDER, Boolean.FALSE);
/* 149 */     userHints.put(Hints.FORCE_STANDARD_AXIS_DIRECTIONS, Boolean.FALSE);
/* 150 */     userHints.put(Hints.FORCE_STANDARD_AXIS_UNITS, Boolean.FALSE);
/*     */   }
/*     */   
/*     */   protected CoordinateOperationAuthorityFactory getAuthorityFactory() {
/* 163 */     if (this.authorityFactory == null) {
/* 169 */       Hints hints = new Hints();
/* 170 */       noForce(hints);
/* 171 */       this.authorityFactory = ReferencingFactoryFinder.getCoordinateOperationAuthorityFactory("EPSG", hints);
/*     */     } 
/* 174 */     return this.authorityFactory;
/*     */   }
/*     */   
/*     */   protected CoordinateOperation createFromDatabase(CoordinateReferenceSystem sourceCRS, CoordinateReferenceSystem targetCRS) {
/*     */     boolean inverse;
/*     */     Set<CoordinateOperation> operations;
/* 206 */     if (Boolean.TRUE.equals(this.processing.get()))
/* 207 */       return null; 
/* 212 */     CoordinateOperationAuthorityFactory authorityFactory = getAuthorityFactory();
/* 213 */     Citation authority = authorityFactory.getAuthority();
/* 214 */     ReferenceIdentifier referenceIdentifier1 = AbstractIdentifiedObject.getIdentifier((IdentifiedObject)sourceCRS, authority);
/* 215 */     if (referenceIdentifier1 == null)
/* 216 */       return null; 
/* 218 */     ReferenceIdentifier referenceIdentifier2 = AbstractIdentifiedObject.getIdentifier((IdentifiedObject)targetCRS, authority);
/* 219 */     if (referenceIdentifier2 == null)
/* 220 */       return null; 
/* 222 */     String sourceCode = referenceIdentifier1.getCode().trim();
/* 223 */     String targetCode = referenceIdentifier2.getCode().trim();
/* 224 */     if (sourceCode.equals(targetCode))
/* 237 */       return null; 
/*     */     try {
/* 242 */       operations = authorityFactory.createFromCoordinateReferenceSystemCodes(sourceCode, targetCode);
/* 243 */       inverse = (operations == null || operations.isEmpty());
/* 244 */       if (inverse)
/* 251 */         operations = authorityFactory.createFromCoordinateReferenceSystemCodes(targetCode, sourceCode); 
/* 253 */     } catch (NoSuchAuthorityCodeException exception) {
/* 259 */       return null;
/* 260 */     } catch (FactoryException exception) {
/* 265 */       log((Exception)exception, (AuthorityFactory)authorityFactory, Level.FINER);
/* 266 */       return null;
/*     */     } 
/* 268 */     if (operations != null)
/* 269 */       for (Iterator<CoordinateOperation> it = operations.iterator(); it.hasNext(); ) {
/*     */         CoordinateOperation candidate;
/*     */         try {
/* 274 */           candidate = it.next();
/* 275 */           if (candidate == null)
/*     */             continue; 
/* 278 */           if (inverse)
/* 279 */             candidate = inverse(candidate); 
/* 281 */         } catch (NoninvertibleTransformException e) {
/*     */           continue;
/* 285 */         } catch (FactoryException exception) {
/* 287 */           log((Exception)exception, (AuthorityFactory)authorityFactory);
/*     */           continue;
/* 289 */         } catch (BackingStoreException exception) {
/* 290 */           log((Exception)exception, (AuthorityFactory)authorityFactory);
/*     */           continue;
/*     */         } 
/* 301 */         CoordinateReferenceSystem source = candidate.getSourceCRS();
/* 302 */         CoordinateReferenceSystem target = candidate.getTargetCRS();
/*     */         try {
/*     */           MathTransform prepend, append;
/* 305 */           if (!CRS.equalsIgnoreMetadata(sourceCRS, source)) {
/*     */             try {
/* 306 */               this.processing.set(Boolean.TRUE);
/* 307 */               prepend = createOperation(sourceCRS, source).getMathTransform();
/* 308 */               source = sourceCRS;
/*     */             } finally {
/* 310 */               this.processing.remove();
/*     */             } 
/*     */           } else {
/* 312 */             prepend = null;
/*     */           } 
/* 314 */           if (!CRS.equalsIgnoreMetadata(target, targetCRS)) {
/*     */             try {
/* 315 */               this.processing.set(Boolean.TRUE);
/* 316 */               append = createOperation(target, targetCRS).getMathTransform();
/* 317 */               target = targetCRS;
/*     */             } finally {
/* 319 */               this.processing.remove();
/*     */             } 
/*     */           } else {
/* 321 */             append = null;
/*     */           } 
/* 323 */           candidate = transform(source, prepend, candidate, append, target);
/* 324 */         } catch (FactoryException exception) {
/* 336 */           log((Exception)exception, (AuthorityFactory)authorityFactory);
/* 337 */           return null;
/*     */         } 
/* 339 */         if (accept(candidate))
/* 340 */           return candidate; 
/*     */       }  
/* 344 */     return null;
/*     */   }
/*     */   
/*     */   private CoordinateOperation transform(CoordinateReferenceSystem sourceCRS, MathTransform prepend, CoordinateOperation operation, MathTransform append, CoordinateReferenceSystem targetCRS) throws FactoryException {
/* 372 */     if ((prepend == null || prepend.isIdentity()) && (append == null || append.isIdentity())) {
/* 373 */       if (!CRS.equalsIgnoreMetadata(sourceCRS, operation.getSourceCRS()) || !CRS.equalsIgnoreMetadata(targetCRS, operation.getTargetCRS()))
/* 375 */         return new ForcedCRSOperation(operation, sourceCRS, targetCRS); 
/* 377 */       return operation;
/*     */     } 
/* 380 */     Map<String, ?> properties = AbstractIdentifiedObject.getProperties((IdentifiedObject)operation);
/* 388 */     if (operation instanceof ConcatenatedOperation) {
/* 389 */       List<SingleOperation> c = ((ConcatenatedOperation)operation).getOperations();
/* 390 */       CoordinateOperation[] op = c.<CoordinateOperation>toArray(new CoordinateOperation[c.size()]);
/* 391 */       if (op.length != 0) {
/* 392 */         CoordinateOperation first = op[0];
/* 393 */         if (op.length == 1) {
/* 394 */           op[0] = transform(sourceCRS, prepend, first, append, targetCRS);
/*     */         } else {
/* 396 */           CoordinateOperation last = op[op.length - 1];
/* 397 */           op[0] = transform(sourceCRS, prepend, first, (MathTransform)null, first.getTargetCRS());
/* 398 */           op[op.length - 1] = transform(last.getSourceCRS(), (MathTransform)null, last, append, targetCRS);
/*     */         } 
/* 400 */         return createConcatenatedOperation(properties, op);
/*     */       } 
/*     */     } 
/* 406 */     MathTransform transform = operation.getMathTransform();
/* 407 */     MathTransformFactory mtFactory = getMathTransformFactory();
/* 408 */     if (prepend != null)
/* 409 */       transform = mtFactory.createConcatenatedTransform(prepend, transform); 
/* 411 */     if (append != null)
/* 412 */       transform = mtFactory.createConcatenatedTransform(transform, append); 
/* 414 */     assert !transform.equals(operation.getMathTransform()) : transform;
/* 415 */     Class<? extends CoordinateOperation> type = AbstractCoordinateOperation.getType(operation);
/* 416 */     OperationMethod method = null;
/* 417 */     if (operation instanceof Operation) {
/* 418 */       method = ((Operation)operation).getMethod();
/* 419 */       if (method != null) {
/* 420 */         int sourceDimensions = transform.getSourceDimensions();
/* 421 */         int targetDimensions = transform.getTargetDimensions();
/* 422 */         if (sourceDimensions != method.getSourceDimensions() || targetDimensions != method.getTargetDimensions())
/* 425 */           method = new DefaultOperationMethod(method, sourceDimensions, targetDimensions); 
/*     */       } 
/*     */     } 
/* 429 */     return createFromMathTransform(properties, sourceCRS, targetCRS, transform, method, type);
/*     */   }
/*     */   
/*     */   private static void log(Exception exception, AuthorityFactory factory) {
/* 436 */     log(exception, factory, Level.WARNING);
/*     */   }
/*     */   
/*     */   private static void log(Exception exception, AuthorityFactory factory, Level level) {
/* 442 */     LogRecord record = Loggings.format(level, 6, factory.getAuthority().getTitle());
/* 445 */     record.setSourceClassName(AuthorityBackedFactory.class.getName());
/* 446 */     record.setSourceMethodName("createFromDatabase");
/* 447 */     record.setThrown(exception);
/* 448 */     record.setLoggerName(LOGGER.getName());
/* 449 */     LOGGER.log(record);
/*     */   }
/*     */   
/*     */   protected boolean accept(CoordinateOperation operation) {
/* 462 */     return true;
/*     */   }
/*     */   
/*     */   public boolean isAvailable() {
/*     */     try {
/* 471 */       CoordinateOperationAuthorityFactory authorityFactory = getAuthorityFactory();
/* 472 */       if (authorityFactory instanceof OptionalFactory)
/* 473 */         return ((OptionalFactory)authorityFactory).isAvailable(); 
/* 475 */       return true;
/* 476 */     } catch (FactoryRegistryException exception) {
/* 479 */       return false;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\operation\AuthorityBackedFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */