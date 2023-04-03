/*     */ package org.geotools.referencing.operation;
/*     */ 
/*     */ import java.text.ParseException;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.Set;
/*     */ import java.util.TreeSet;
/*     */ import javax.imageio.spi.ServiceRegistry;
/*     */ import javax.measure.converter.ConversionException;
/*     */ import javax.measure.quantity.Length;
/*     */ import javax.measure.unit.Unit;
/*     */ import org.geotools.factory.FactoryRegistry;
/*     */ import org.geotools.factory.Hints;
/*     */ import org.geotools.metadata.iso.citation.Citations;
/*     */ import org.geotools.parameter.ParameterWriter;
/*     */ import org.geotools.parameter.Parameters;
/*     */ import org.geotools.referencing.AbstractIdentifiedObject;
/*     */ import org.geotools.referencing.cs.AbstractCS;
/*     */ import org.geotools.referencing.factory.ReferencingFactory;
/*     */ import org.geotools.referencing.operation.matrix.MatrixFactory;
/*     */ import org.geotools.referencing.operation.matrix.XMatrix;
/*     */ import org.geotools.referencing.operation.transform.ConcatenatedTransform;
/*     */ import org.geotools.referencing.operation.transform.PassThroughTransform;
/*     */ import org.geotools.referencing.operation.transform.ProjectiveTransform;
/*     */ import org.geotools.referencing.wkt.MathTransformParser;
/*     */ import org.geotools.referencing.wkt.Symbols;
/*     */ import org.geotools.resources.Arguments;
/*     */ import org.geotools.resources.CRSUtilities;
/*     */ import org.geotools.resources.LazySet;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ import org.geotools.util.CanonicalSet;
/*     */ import org.opengis.metadata.citation.Citation;
/*     */ import org.opengis.parameter.ParameterValueGroup;
/*     */ import org.opengis.referencing.FactoryException;
/*     */ import org.opengis.referencing.NoSuchIdentifierException;
/*     */ import org.opengis.referencing.crs.CoordinateReferenceSystem;
/*     */ import org.opengis.referencing.cs.CoordinateSystem;
/*     */ import org.opengis.referencing.datum.Ellipsoid;
/*     */ import org.opengis.referencing.operation.Conversion;
/*     */ import org.opengis.referencing.operation.MathTransform;
/*     */ import org.opengis.referencing.operation.MathTransformFactory;
/*     */ import org.opengis.referencing.operation.Matrix;
/*     */ import org.opengis.referencing.operation.Operation;
/*     */ import org.opengis.referencing.operation.OperationMethod;
/*     */ import org.opengis.referencing.operation.Projection;
/*     */ 
/*     */ public class DefaultMathTransformFactory extends ReferencingFactory implements MathTransformFactory {
/* 118 */   private static final Hints HINTS = null;
/*     */   
/*     */   private transient MathTransformParser parser;
/*     */   
/*     */   private transient MathTransformProvider lastProvider;
/*     */   
/* 135 */   private static final ThreadLocal<OperationMethod> lastMethod = new ThreadLocal<OperationMethod>();
/*     */   
/*     */   private final CanonicalSet<MathTransform> pool;
/*     */   
/*     */   private final FactoryRegistry registry;
/*     */   
/*     */   public DefaultMathTransformFactory() {
/* 152 */     this(new Class[] { MathTransformProvider.class });
/*     */   }
/*     */   
/*     */   private DefaultMathTransformFactory(Class<?>[] categories) {
/* 163 */     this.registry = new FactoryRegistry(Arrays.asList(categories));
/* 164 */     this.pool = CanonicalSet.newInstance(MathTransform.class);
/*     */   }
/*     */   
/*     */   public Citation getVendor() {
/* 176 */     return Citations.GEOTOOLS;
/*     */   }
/*     */   
/*     */   public Set<OperationMethod> getAvailableMethods(Class<? extends Operation> type) {
/* 194 */     return (Set<OperationMethod>)new LazySet(this.registry.getServiceProviders(MathTransformProvider.class, (type != null) ? new MethodFilter(type) : null, HINTS));
/*     */   }
/*     */   
/*     */   private static final class MethodFilter implements ServiceRegistry.Filter {
/*     */     private final Class<? extends Operation> type;
/*     */     
/*     */     public MethodFilter(Class<? extends Operation> type) {
/* 211 */       this.type = type;
/*     */     }
/*     */     
/*     */     public boolean filter(Object element) {
/* 219 */       if (element instanceof MathTransformProvider) {
/* 220 */         Class<? extends Operation> t = ((MathTransformProvider)element).getOperationType();
/* 221 */         if (t != null && !this.type.isAssignableFrom(t))
/* 222 */           return false; 
/*     */       } 
/* 225 */       return true;
/*     */     }
/*     */   }
/*     */   
/*     */   public OperationMethod getLastMethodUsed() {
/* 239 */     return lastMethod.get();
/*     */   }
/*     */   
/*     */   public OperationMethod getOperationMethod(String name) throws NoSuchIdentifierException {
/* 255 */     return getProvider(name);
/*     */   }
/*     */   
/*     */   private MathTransformProvider getProvider(String method) throws NoSuchIdentifierException {
/* 278 */     MathTransformProvider provider = this.lastProvider;
/* 279 */     if (provider != null && provider.nameMatches(method))
/* 280 */       return provider; 
/* 282 */     Iterator<MathTransformProvider> providers = this.registry.getServiceProviders(MathTransformProvider.class, null, HINTS);
/* 284 */     while (providers.hasNext()) {
/* 285 */       provider = providers.next();
/* 286 */       if (provider.nameMatches(method))
/* 287 */         return this.lastProvider = provider; 
/*     */     } 
/* 290 */     throw new NoSuchIdentifierException(Errors.format(141, method), method);
/*     */   }
/*     */   
/*     */   public ParameterValueGroup getDefaultParameters(String method) throws NoSuchIdentifierException {
/* 316 */     return getProvider(method).getParameters().createValue();
/*     */   }
/*     */   
/*     */   public MathTransform createBaseToDerived(CoordinateReferenceSystem baseCRS, ParameterValueGroup parameters, CoordinateSystem derivedCS) throws NoSuchIdentifierException, FactoryException {
/* 346 */     Ellipsoid ellipsoid = CRSUtilities.getHeadGeoEllipsoid(baseCRS);
/* 347 */     if (ellipsoid != null) {
/* 348 */       Unit<Length> axisUnit = ellipsoid.getAxisUnit();
/* 349 */       Parameters.ensureSet(parameters, "semi_major", ellipsoid.getSemiMajorAxis(), axisUnit, false);
/* 350 */       Parameters.ensureSet(parameters, "semi_minor", ellipsoid.getSemiMinorAxis(), axisUnit, false);
/*     */     } 
/* 352 */     MathTransform baseToDerived = createParameterizedTransform(parameters);
/* 353 */     OperationMethod method = lastMethod.get();
/* 354 */     baseToDerived = createBaseToDerived(baseCRS, baseToDerived, derivedCS);
/* 355 */     lastMethod.set(method);
/* 356 */     return baseToDerived;
/*     */   }
/*     */   
/*     */   public MathTransform createBaseToDerived(CoordinateReferenceSystem baseCRS, MathTransform projection, CoordinateSystem derivedCS) throws FactoryException {
/*     */     Matrix swap1, swap3;
/* 386 */     CoordinateSystem sourceCS = baseCRS.getCoordinateSystem();
/*     */     try {
/* 389 */       swap1 = AbstractCS.swapAndScaleAxis(sourceCS, AbstractCS.standard(sourceCS));
/* 390 */       swap3 = AbstractCS.swapAndScaleAxis(AbstractCS.standard(derivedCS), derivedCS);
/* 391 */     } catch (IllegalArgumentException cause) {
/* 393 */       throw new FactoryException(cause);
/* 394 */     } catch (ConversionException cause) {
/* 396 */       throw new FactoryException(cause);
/*     */     } 
/* 404 */     MathTransform step1 = createAffineTransform(swap1);
/* 405 */     MathTransform step3 = createAffineTransform(swap3);
/* 406 */     MathTransform step2 = projection;
/* 412 */     int numTrailingOrdinates = step3.getSourceDimensions() - step2.getTargetDimensions();
/* 413 */     if (numTrailingOrdinates > 0)
/* 414 */       step2 = createPassThroughTransform(0, step2, numTrailingOrdinates); 
/* 420 */     int sourceDim = step1.getTargetDimensions();
/* 421 */     int targetDim = step2.getSourceDimensions();
/* 422 */     if (sourceDim > targetDim) {
/* 423 */       XMatrix xMatrix = MatrixFactory.create(targetDim + 1, sourceDim + 1);
/* 424 */       xMatrix.setElement(targetDim, sourceDim, 1.0D);
/* 425 */       step1 = createConcatenatedTransform(createAffineTransform((Matrix)xMatrix), step1);
/*     */     } 
/* 427 */     return createConcatenatedTransform(createConcatenatedTransform(step1, step2), step3);
/*     */   }
/*     */   
/*     */   public MathTransform createParameterizedTransform(ParameterValueGroup parameters) throws NoSuchIdentifierException, FactoryException {
/*     */     MathTransform transform;
/* 456 */     OperationMethod method = null;
/*     */     try {
/* 458 */       String classification = parameters.getDescriptor().getName().getCode();
/* 459 */       MathTransformProvider provider = getProvider(classification);
/* 460 */       method = provider;
/*     */       try {
/* 462 */         parameters = provider.ensureValidValues(parameters);
/* 463 */         transform = provider.createMathTransform(parameters);
/* 464 */       } catch (IllegalArgumentException exception) {
/* 470 */         throw new FactoryException(exception);
/*     */       } 
/* 472 */       if (transform instanceof MathTransformProvider.Delegate) {
/* 473 */         MathTransformProvider.Delegate delegate = (MathTransformProvider.Delegate)transform;
/* 474 */         method = delegate.method;
/* 475 */         transform = delegate.transform;
/*     */       } 
/* 477 */       transform = (MathTransform)this.pool.unique(transform);
/*     */     } finally {
/* 479 */       lastMethod.set(method);
/*     */     } 
/* 481 */     return transform;
/*     */   }
/*     */   
/*     */   public MathTransform createAffineTransform(Matrix matrix) throws FactoryException {
/* 501 */     lastMethod.remove();
/* 502 */     return (MathTransform)this.pool.unique(ProjectiveTransform.create(matrix));
/*     */   }
/*     */   
/*     */   public MathTransform createConcatenatedTransform(MathTransform transform1, MathTransform transform2) throws FactoryException {
/*     */     try {
/* 526 */       tr = ConcatenatedTransform.create(transform1, transform2);
/* 527 */     } catch (IllegalArgumentException exception) {
/* 528 */       throw new FactoryException(exception);
/*     */     } 
/* 530 */     MathTransform tr = (MathTransform)this.pool.unique(tr);
/* 531 */     return tr;
/*     */   }
/*     */   
/*     */   public MathTransform createPassThroughTransform(int firstAffectedOrdinate, MathTransform subTransform, int numTrailingOrdinates) throws FactoryException {
/*     */     try {
/* 559 */       tr = PassThroughTransform.create(firstAffectedOrdinate, subTransform, numTrailingOrdinates);
/* 562 */     } catch (IllegalArgumentException exception) {
/* 563 */       throw new FactoryException(exception);
/*     */     } 
/* 565 */     MathTransform tr = (MathTransform)this.pool.unique(tr);
/* 566 */     return tr;
/*     */   }
/*     */   
/*     */   public MathTransform createFromXML(String xml) throws FactoryException {
/* 577 */     throw new FactoryException("Not yet implemented.");
/*     */   }
/*     */   
/*     */   public synchronized MathTransform createFromWKT(String text) throws FactoryException {
/* 594 */     if (this.parser == null)
/* 595 */       this.parser = new MathTransformParser(Symbols.DEFAULT, this); 
/*     */     try {
/* 598 */       return this.parser.parseMathTransform(text);
/* 599 */     } catch (ParseException exception) {
/* 600 */       Throwable cause = exception.getCause();
/* 601 */       if (cause instanceof FactoryException)
/* 602 */         throw (FactoryException)cause; 
/* 604 */       throw new FactoryException(exception);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void scanForPlugins() {
/* 619 */     this.registry.scanForPlugins();
/*     */   }
/*     */   
/*     */   public static void main(String[] args) {
/*     */     Class<Conversion> clazz;
/* 667 */     Arguments arguments = new Arguments(args);
/* 668 */     boolean printAll = arguments.getFlag("-all");
/* 669 */     Class<? extends Operation> type = null;
/* 670 */     if (arguments.getFlag("-projections"))
/* 670 */       Class<Projection> clazz1 = Projection.class; 
/* 671 */     if (arguments.getFlag("-conversions"))
/* 671 */       clazz = Conversion.class; 
/* 672 */     args = arguments.getRemainingArguments(1);
/*     */     try {
/* 674 */       DefaultMathTransformFactory factory = new DefaultMathTransformFactory();
/* 675 */       ParameterWriter writer = new ParameterWriter(arguments.out);
/* 676 */       writer.setLocale(arguments.locale);
/* 677 */       Set<OperationMethod> methods = Collections.emptySet();
/* 678 */       if (printAll || args.length == 0) {
/* 679 */         Set<String> scopes = new HashSet<String>();
/* 681 */         scopes.add("EPSG");
/* 682 */         scopes.add("Geotools");
/* 683 */         methods = new TreeSet<OperationMethod>(AbstractIdentifiedObject.NAME_COMPARATOR);
/* 684 */         methods.addAll(factory.getAvailableMethods((Class)clazz));
/* 685 */         writer.summary(methods, scopes);
/*     */       } 
/* 687 */       if (!printAll)
/* 688 */         if (args.length == 0) {
/* 689 */           methods = Collections.emptySet();
/*     */         } else {
/* 691 */           methods = Collections.singleton(factory.getProvider(args[0]));
/*     */         }  
/* 698 */       String lineSeparator = System.getProperty("line.separator", "\n");
/* 699 */       for (OperationMethod method : methods) {
/* 700 */         arguments.out.write(lineSeparator);
/* 701 */         writer.format(method);
/*     */       } 
/* 703 */     } catch (NoSuchIdentifierException exception) {
/* 704 */       arguments.err.println(exception.getLocalizedMessage());
/*     */       return;
/* 706 */     } catch (Exception exception) {
/* 707 */       exception.printStackTrace(arguments.err);
/*     */       return;
/*     */     } 
/* 710 */     arguments.out.flush();
/*     */   }
/*     */   
/*     */   public static void cleanupThreadLocals() {
/* 718 */     lastMethod.remove();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\operation\DefaultMathTransformFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */