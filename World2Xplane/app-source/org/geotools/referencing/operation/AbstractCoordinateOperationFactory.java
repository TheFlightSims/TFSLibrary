/*     */ package org.geotools.referencing.operation;
/*     */ 
/*     */ import java.awt.RenderingHints;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.LinkedList;
/*     */ import java.util.Map;
/*     */ import javax.measure.converter.ConversionException;
/*     */ import org.geotools.factory.Hints;
/*     */ import org.geotools.metadata.iso.citation.Citations;
/*     */ import org.geotools.metadata.iso.quality.PositionalAccuracyImpl;
/*     */ import org.geotools.referencing.AbstractIdentifiedObject;
/*     */ import org.geotools.referencing.CRS;
/*     */ import org.geotools.referencing.NamedIdentifier;
/*     */ import org.geotools.referencing.cs.AbstractCS;
/*     */ import org.geotools.referencing.factory.ReferencingFactory;
/*     */ import org.geotools.referencing.factory.ReferencingFactoryContainer;
/*     */ import org.geotools.referencing.operation.transform.ProjectiveTransform;
/*     */ import org.geotools.resources.Classes;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ import org.geotools.resources.i18n.Vocabulary;
/*     */ import org.geotools.util.CanonicalSet;
/*     */ import org.geotools.util.Utilities;
/*     */ import org.opengis.metadata.quality.PositionalAccuracy;
/*     */ import org.opengis.parameter.ParameterValueGroup;
/*     */ import org.opengis.referencing.FactoryException;
/*     */ import org.opengis.referencing.IdentifiedObject;
/*     */ import org.opengis.referencing.ReferenceIdentifier;
/*     */ import org.opengis.referencing.crs.CoordinateReferenceSystem;
/*     */ import org.opengis.referencing.cs.CoordinateSystem;
/*     */ import org.opengis.referencing.operation.ConcatenatedOperation;
/*     */ import org.opengis.referencing.operation.Conversion;
/*     */ import org.opengis.referencing.operation.CoordinateOperation;
/*     */ import org.opengis.referencing.operation.CoordinateOperationFactory;
/*     */ import org.opengis.referencing.operation.MathTransform;
/*     */ import org.opengis.referencing.operation.MathTransformFactory;
/*     */ import org.opengis.referencing.operation.Matrix;
/*     */ import org.opengis.referencing.operation.NoninvertibleTransformException;
/*     */ import org.opengis.referencing.operation.Operation;
/*     */ import org.opengis.referencing.operation.OperationMethod;
/*     */ import org.opengis.referencing.operation.OperationNotFoundException;
/*     */ import org.opengis.referencing.operation.SingleOperation;
/*     */ import org.opengis.referencing.operation.Transformation;
/*     */ 
/*     */ public abstract class AbstractCoordinateOperationFactory extends ReferencingFactory implements CoordinateOperationFactory {
/*  77 */   protected static final ReferenceIdentifier IDENTITY = (ReferenceIdentifier)new NamedIdentifier(Citations.GEOTOOLS, Vocabulary.formatInternational(102));
/*     */   
/*  85 */   protected static final ReferenceIdentifier AXIS_CHANGES = (ReferenceIdentifier)new NamedIdentifier(Citations.GEOTOOLS, Vocabulary.formatInternational(7));
/*     */   
/*  94 */   protected static final ReferenceIdentifier DATUM_SHIFT = (ReferenceIdentifier)new NamedIdentifier(Citations.GEOTOOLS, Vocabulary.formatInternational(40));
/*     */   
/* 108 */   protected static final ReferenceIdentifier ELLIPSOID_SHIFT = (ReferenceIdentifier)new NamedIdentifier(Citations.GEOTOOLS, Vocabulary.formatInternational(59));
/*     */   
/* 115 */   protected static final ReferenceIdentifier GEOCENTRIC_CONVERSION = (ReferenceIdentifier)new NamedIdentifier(Citations.GEOTOOLS, Vocabulary.formatInternational(79));
/*     */   
/* 122 */   protected static final ReferenceIdentifier INVERSE_OPERATION = (ReferenceIdentifier)new NamedIdentifier(Citations.GEOTOOLS, Vocabulary.formatInternational(113));
/*     */   
/*     */   private final ReferencingFactoryContainer factories;
/*     */   
/*     */   private final MathTransformFactory mtFactory;
/*     */   
/* 146 */   private final CanonicalSet<CoordinateOperation> pool = CanonicalSet.newInstance(CoordinateOperation.class);
/*     */   
/*     */   private boolean hintsInitialized;
/*     */   
/*     */   public AbstractCoordinateOperationFactory(Hints userHints) {
/* 165 */     this(userHints, 50);
/*     */   }
/*     */   
/*     */   public AbstractCoordinateOperationFactory(Hints userHints, int priority) {
/* 182 */     super(priority);
/* 183 */     this.factories = ReferencingFactoryContainer.instance(userHints);
/* 184 */     this.mtFactory = this.factories.getMathTransformFactory();
/*     */   }
/*     */   
/*     */   AbstractCoordinateOperationFactory(CoordinateOperationFactory factory, Hints hints, int priority) {
/* 196 */     super(priority);
/* 197 */     if (factory instanceof AbstractCoordinateOperationFactory) {
/* 198 */       this.factories = ((AbstractCoordinateOperationFactory)factory).getFactoryContainer();
/*     */     } else {
/* 200 */       this.factories = ReferencingFactoryContainer.instance(hints);
/*     */     } 
/* 202 */     this.mtFactory = this.factories.getMathTransformFactory();
/*     */   }
/*     */   
/*     */   public Map<RenderingHints.Key, ?> getImplementationHints() {
/* 213 */     synchronized (this.hints) {
/* 214 */       if (!this.hintsInitialized) {
/* 215 */         initializeHints();
/* 216 */         this.hintsInitialized = true;
/*     */       } 
/*     */     } 
/* 219 */     return super.getImplementationHints();
/*     */   }
/*     */   
/*     */   void initializeHints() {
/* 227 */     assert Thread.holdsLock(this.hints);
/* 228 */     ReferencingFactoryContainer factories = getFactoryContainer();
/* 229 */     this.hints.putAll(factories.getImplementationHints());
/*     */   }
/*     */   
/*     */   public final MathTransformFactory getMathTransformFactory() {
/* 240 */     return this.mtFactory;
/*     */   }
/*     */   
/*     */   final ReferencingFactoryContainer getFactoryContainer() {
/* 247 */     return this.factories;
/*     */   }
/*     */   
/*     */   protected Matrix swapAndScaleAxis(CoordinateSystem sourceCS, CoordinateSystem targetCS) throws OperationNotFoundException {
/*     */     try {
/* 278 */       return AbstractCS.swapAndScaleAxis(sourceCS, targetCS);
/* 279 */     } catch (IllegalArgumentException exception) {
/* 280 */       throw new OperationNotFoundException(getErrorMessage(sourceCS, targetCS), exception);
/* 281 */     } catch (ConversionException exception) {
/* 282 */       throw new OperationNotFoundException(getErrorMessage(sourceCS, targetCS), exception);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static Map<String, Object> getProperties(ReferenceIdentifier name) {
/*     */     Map<String, Object> properties;
/* 301 */     if (name == DATUM_SHIFT || name == ELLIPSOID_SHIFT) {
/* 302 */       properties = new HashMap<String, Object>(4);
/* 303 */       properties.put("name", name);
/* 304 */       properties.put("coordinateOperationAccuracy", new PositionalAccuracy[] { (name == DATUM_SHIFT) ? PositionalAccuracyImpl.DATUM_SHIFT_APPLIED : PositionalAccuracyImpl.DATUM_SHIFT_OMITTED });
/*     */     } else {
/* 309 */       properties = Collections.singletonMap("name", name);
/*     */     } 
/* 311 */     return properties;
/*     */   }
/*     */   
/*     */   protected CoordinateOperation createFromAffineTransform(ReferenceIdentifier name, CoordinateReferenceSystem sourceCRS, CoordinateReferenceSystem targetCRS, Matrix matrix) throws FactoryException {
/* 335 */     MathTransform transform = this.mtFactory.createAffineTransform(matrix);
/* 336 */     Map<String, ?> properties = getProperties(name);
/* 337 */     Class<? extends Operation> type = properties.containsKey("coordinateOperationAccuracy") ? (Class)Transformation.class : (Class)Conversion.class;
/* 340 */     return createFromMathTransform(properties, sourceCRS, targetCRS, transform, (OperationMethod)ProjectiveTransform.ProviderAffine.getProvider(transform.getSourceDimensions(), transform.getTargetDimensions()), (Class)type);
/*     */   }
/*     */   
/*     */   protected CoordinateOperation createFromParameters(ReferenceIdentifier name, CoordinateReferenceSystem sourceCRS, CoordinateReferenceSystem targetCRS, ParameterValueGroup parameters) throws FactoryException {
/* 364 */     Map<String, ?> properties = getProperties(name);
/* 365 */     MathTransform transform = this.mtFactory.createParameterizedTransform(parameters);
/* 366 */     OperationMethod method = this.mtFactory.getLastMethodUsed();
/* 367 */     return createFromMathTransform(properties, sourceCRS, targetCRS, transform, method, (Class)Operation.class);
/*     */   }
/*     */   
/*     */   protected CoordinateOperation createFromMathTransform(ReferenceIdentifier name, CoordinateReferenceSystem sourceCRS, CoordinateReferenceSystem targetCRS, MathTransform transform) throws FactoryException {
/* 388 */     return createFromMathTransform(Collections.singletonMap("name", name), sourceCRS, targetCRS, transform, null, CoordinateOperation.class);
/*     */   }
/*     */   
/*     */   protected CoordinateOperation createFromMathTransform(Map<String, ?> properties, CoordinateReferenceSystem sourceCRS, CoordinateReferenceSystem targetCRS, MathTransform transform, OperationMethod method, Class<? extends CoordinateOperation> type) throws FactoryException {
/* 418 */     if (transform instanceof CoordinateOperation) {
/* 419 */       CoordinateOperation coordinateOperation = (CoordinateOperation)transform;
/* 420 */       if (Utilities.equals(coordinateOperation.getSourceCRS(), sourceCRS) && Utilities.equals(coordinateOperation.getTargetCRS(), targetCRS) && Utilities.equals(coordinateOperation.getMathTransform(), transform))
/* 424 */         if (coordinateOperation instanceof Operation) {
/* 425 */           if (Utilities.equals(((Operation)coordinateOperation).getMethod(), method))
/* 426 */             return coordinateOperation; 
/*     */         } else {
/* 429 */           return coordinateOperation;
/*     */         }  
/*     */     } 
/* 433 */     CoordinateOperation operation = DefaultOperation.create(properties, sourceCRS, targetCRS, transform, method, type);
/* 434 */     operation = (CoordinateOperation)this.pool.unique(operation);
/* 435 */     return operation;
/*     */   }
/*     */   
/*     */   public Conversion createDefiningConversion(Map<String, ?> properties, OperationMethod method, ParameterValueGroup parameters) throws FactoryException {
/* 456 */     Conversion conversion = new DefiningConversion(properties, method, parameters);
/* 457 */     conversion = (Conversion)this.pool.unique(conversion);
/* 458 */     return conversion;
/*     */   }
/*     */   
/*     */   public CoordinateOperation createConcatenatedOperation(Map<String, ?> properties, CoordinateOperation[] operations) throws FactoryException {
/* 474 */     CoordinateOperation operation = new DefaultConcatenatedOperation(properties, operations, this.mtFactory);
/* 475 */     operation = (CoordinateOperation)this.pool.unique(operation);
/* 476 */     return operation;
/*     */   }
/*     */   
/*     */   protected CoordinateOperation concatenate(CoordinateOperation step1, CoordinateOperation step2) throws FactoryException {
/* 494 */     if (step1 == null)
/* 494 */       return step2; 
/* 495 */     if (step2 == null)
/* 495 */       return step1; 
/* 504 */     if (isIdentity(step1))
/* 504 */       return step2; 
/* 505 */     if (isIdentity(step2))
/* 505 */       return step1; 
/* 506 */     MathTransform mt1 = step1.getMathTransform();
/* 507 */     MathTransform mt2 = step2.getMathTransform();
/* 508 */     CoordinateReferenceSystem sourceCRS = step1.getSourceCRS();
/* 509 */     CoordinateReferenceSystem targetCRS = step2.getTargetCRS();
/* 510 */     CoordinateOperation step = null;
/* 511 */     if (step1.getName() == AXIS_CHANGES && mt1.getSourceDimensions() == mt1.getTargetDimensions())
/* 511 */       step = step2; 
/* 512 */     if (step2.getName() == AXIS_CHANGES && mt2.getSourceDimensions() == mt2.getTargetDimensions())
/* 512 */       step = step1; 
/* 513 */     if (step instanceof Operation)
/* 519 */       return createFromMathTransform(AbstractIdentifiedObject.getProperties((IdentifiedObject)step), sourceCRS, targetCRS, this.mtFactory.createConcatenatedTransform(mt1, mt2), ((Operation)step).getMethod(), CoordinateOperation.class); 
/* 523 */     return createConcatenatedOperation(getTemporaryName(sourceCRS, targetCRS), new CoordinateOperation[] { step1, step2 });
/*     */   }
/*     */   
/*     */   protected CoordinateOperation concatenate(CoordinateOperation step1, CoordinateOperation step2, CoordinateOperation step3) throws FactoryException {
/* 544 */     if (step1 == null)
/* 544 */       return concatenate(step2, step3); 
/* 545 */     if (step2 == null)
/* 545 */       return concatenate(step1, step3); 
/* 546 */     if (step3 == null)
/* 546 */       return concatenate(step1, step2); 
/* 547 */     assert CRS.equalsIgnoreMetadata(step1.getTargetCRS(), step2.getSourceCRS()) : step1;
/* 548 */     assert CRS.equalsIgnoreMetadata(step2.getTargetCRS(), step3.getSourceCRS()) : step3;
/* 550 */     if (isIdentity(step1))
/* 550 */       return concatenate(step2, step3); 
/* 551 */     if (isIdentity(step2))
/* 551 */       return concatenate(step1, step3); 
/* 552 */     if (isIdentity(step3))
/* 552 */       return concatenate(step1, step2); 
/* 553 */     if (step1.getName() == AXIS_CHANGES)
/* 553 */       return concatenate(concatenate(step1, step2), step3); 
/* 554 */     if (step3.getName() == AXIS_CHANGES)
/* 554 */       return concatenate(step1, concatenate(step2, step3)); 
/* 555 */     CoordinateReferenceSystem sourceCRS = step1.getSourceCRS();
/* 556 */     CoordinateReferenceSystem targetCRS = step3.getTargetCRS();
/* 557 */     return createConcatenatedOperation(getTemporaryName(sourceCRS, targetCRS), new CoordinateOperation[] { step1, step2, step3 });
/*     */   }
/*     */   
/*     */   private static boolean isIdentity(CoordinateOperation operation) {
/* 568 */     return (operation instanceof Conversion && operation.getMathTransform().isIdentity());
/*     */   }
/*     */   
/*     */   protected CoordinateOperation inverse(CoordinateOperation operation) throws NoninvertibleTransformException, FactoryException {
/* 584 */     CoordinateReferenceSystem sourceCRS = operation.getSourceCRS();
/* 585 */     CoordinateReferenceSystem targetCRS = operation.getTargetCRS();
/* 586 */     Map<String, Object> properties = AbstractIdentifiedObject.getProperties((IdentifiedObject)operation, null);
/* 587 */     properties.putAll(getTemporaryName(targetCRS, sourceCRS));
/* 588 */     if (operation instanceof ConcatenatedOperation) {
/* 589 */       LinkedList<CoordinateOperation> inverted = new LinkedList<CoordinateOperation>();
/* 590 */       for (SingleOperation singleOperation : ((ConcatenatedOperation)operation).getOperations())
/* 591 */         inverted.addFirst(inverse((CoordinateOperation)singleOperation)); 
/* 593 */       return createConcatenatedOperation(properties, inverted.<CoordinateOperation>toArray(new CoordinateOperation[inverted.size()]));
/*     */     } 
/* 596 */     MathTransform transform = operation.getMathTransform().inverse();
/* 597 */     Class<? extends CoordinateOperation> type = AbstractCoordinateOperation.getType(operation);
/* 598 */     OperationMethod method = (operation instanceof Operation) ? ((Operation)operation).getMethod() : null;
/* 600 */     return createFromMathTransform(properties, targetCRS, sourceCRS, transform, method, type);
/*     */   }
/*     */   
/*     */   static int getDimension(CoordinateReferenceSystem crs) {
/* 619 */     return (crs != null) ? crs.getCoordinateSystem().getDimension() : 0;
/*     */   }
/*     */   
/*     */   private static final class TemporaryIdentifier extends NamedIdentifier {
/*     */     private static final long serialVersionUID = -2784354058026177076L;
/*     */     
/*     */     private final ReferenceIdentifier parent;
/*     */     
/*     */     private final int count;
/*     */     
/*     */     public TemporaryIdentifier(ReferenceIdentifier parent) {
/* 638 */       this(parent, ((parent instanceof TemporaryIdentifier) ? ((TemporaryIdentifier)parent).count : 0) + 1);
/*     */     }
/*     */     
/*     */     private TemporaryIdentifier(ReferenceIdentifier parent, int count) {
/* 644 */       super(Citations.GEOTOOLS, unwrap(parent).getCode() + " (step " + count + ')');
/* 645 */       this.parent = parent;
/* 646 */       this.count = count;
/*     */     }
/*     */     
/*     */     public static ReferenceIdentifier unwrap(ReferenceIdentifier identifier) {
/* 651 */       while (identifier instanceof TemporaryIdentifier)
/* 652 */         identifier = ((TemporaryIdentifier)identifier).parent; 
/* 654 */       return identifier;
/*     */     }
/*     */   }
/*     */   
/*     */   private static String getClassName(IdentifiedObject object) {
/* 663 */     if (object != null) {
/* 664 */       Class<?> type = object.getClass();
/* 665 */       Class[] interfaces = type.getInterfaces();
/* 666 */       for (int i = 0; i < interfaces.length; i++) {
/* 667 */         Class<?> candidate = interfaces[i];
/* 668 */         if (candidate.getName().startsWith("org.opengis.referencing.")) {
/* 669 */           type = candidate;
/*     */           break;
/*     */         } 
/*     */       } 
/* 673 */       String name = Classes.getShortName(type);
/* 674 */       ReferenceIdentifier id = object.getName();
/* 675 */       if (id != null)
/* 676 */         name = name + '[' + id.getCode() + ']'; 
/* 678 */       return name;
/*     */     } 
/* 680 */     return null;
/*     */   }
/*     */   
/*     */   static Map<String, Object> getTemporaryName(IdentifiedObject source) {
/* 689 */     Map<String, Object> properties = new HashMap<String, Object>(4);
/* 690 */     properties.put("name", new TemporaryIdentifier(source.getName()));
/* 691 */     properties.put("remarks", Vocabulary.formatInternational(45, getClassName(source)));
/* 693 */     return properties;
/*     */   }
/*     */   
/*     */   static Map<String, ?> getTemporaryName(CoordinateReferenceSystem source, CoordinateReferenceSystem target) {
/* 704 */     String name = getClassName((IdentifiedObject)source) + " ⇨ " + getClassName((IdentifiedObject)target);
/* 705 */     return Collections.singletonMap("name", name);
/*     */   }
/*     */   
/*     */   protected static String getErrorMessage(IdentifiedObject source, IdentifiedObject target) {
/* 719 */     return Errors.format(140, getClassName(source), getClassName(target));
/*     */   }
/*     */   
/*     */   protected static void ensureNonNull(String name, Object object) throws IllegalArgumentException {
/* 733 */     if (object == null)
/* 734 */       throw new IllegalArgumentException(Errors.format(143, name)); 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\operation\AbstractCoordinateOperationFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */