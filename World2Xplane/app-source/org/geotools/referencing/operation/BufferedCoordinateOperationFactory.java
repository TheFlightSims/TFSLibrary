/*     */ package org.geotools.referencing.operation;
/*     */ 
/*     */ import java.util.Map;
/*     */ import org.geotools.factory.BufferedFactory;
/*     */ import org.geotools.factory.Hints;
/*     */ import org.geotools.referencing.ReferencingFactoryFinder;
/*     */ import org.geotools.util.SoftValueHashMap;
/*     */ import org.geotools.util.Utilities;
/*     */ import org.opengis.referencing.FactoryException;
/*     */ import org.opengis.referencing.crs.CoordinateReferenceSystem;
/*     */ import org.opengis.referencing.operation.CoordinateOperation;
/*     */ import org.opengis.referencing.operation.CoordinateOperationFactory;
/*     */ import org.opengis.referencing.operation.OperationMethod;
/*     */ import org.opengis.referencing.operation.OperationNotFoundException;
/*     */ 
/*     */ public class BufferedCoordinateOperationFactory extends AbstractCoordinateOperationFactory implements BufferedFactory {
/*     */   static final int PRIORITY = 70;
/*     */   
/*     */   private CoordinateOperationFactory factory;
/*     */   
/*     */   private static final class CRSPair {
/*     */     private final int hash;
/*     */     
/*     */     private final CoordinateReferenceSystem sourceCRS;
/*     */     
/*     */     private final CoordinateReferenceSystem targetCRS;
/*     */     
/*     */     public CRSPair(CoordinateReferenceSystem sourceCRS, CoordinateReferenceSystem targetCRS) {
/*  83 */       this.sourceCRS = sourceCRS;
/*  84 */       this.targetCRS = targetCRS;
/*  85 */       this.hash = 37 * sourceCRS.hashCode() + targetCRS.hashCode();
/*     */     }
/*     */     
/*     */     public int hashCode() {
/*  93 */       return this.hash;
/*     */     }
/*     */     
/*     */     public boolean equals(Object object) {
/* 105 */       if (object == this)
/* 106 */         return true; 
/* 108 */       if (object instanceof CRSPair) {
/* 109 */         CRSPair that = (CRSPair)object;
/* 110 */         return (Utilities.equals(this.sourceCRS, that.sourceCRS) && Utilities.equals(this.targetCRS, that.targetCRS));
/*     */       } 
/* 113 */       return false;
/*     */     }
/*     */   }
/*     */   
/* 130 */   private final Map<CRSPair, CoordinateOperation> pool = (Map<CRSPair, CoordinateOperation>)new SoftValueHashMap();
/*     */   
/*     */   public BufferedCoordinateOperationFactory() {
/* 137 */     super((Hints)null, 70);
/*     */   }
/*     */   
/*     */   public BufferedCoordinateOperationFactory(Hints userHints) {
/* 153 */     this(userHints, 70);
/*     */   }
/*     */   
/*     */   public BufferedCoordinateOperationFactory(Hints userHints, int priority) {
/* 165 */     this(getBackingFactory(userHints), userHints, priority);
/*     */   }
/*     */   
/*     */   public BufferedCoordinateOperationFactory(CoordinateOperationFactory factory, int priority) {
/* 179 */     this(factory, (Hints)null, priority);
/*     */   }
/*     */   
/*     */   private BufferedCoordinateOperationFactory(CoordinateOperationFactory factory, Hints userHints, int priority) {
/* 189 */     super(factory, userHints, priority);
/* 190 */     this.factory = factory;
/* 191 */     ensureNonNull("factory", factory);
/*     */   }
/*     */   
/*     */   private static CoordinateOperationFactory getBackingFactory(Hints hints) {
/* 198 */     for (CoordinateOperationFactory candidate : ReferencingFactoryFinder.getCoordinateOperationFactories(hints)) {
/* 199 */       if (!(candidate instanceof BufferedCoordinateOperationFactory))
/* 200 */         return candidate; 
/*     */     } 
/* 205 */     return ReferencingFactoryFinder.getCoordinateOperationFactory(hints);
/*     */   }
/*     */   
/*     */   private final CoordinateOperationFactory getBackingFactory() {
/* 213 */     assert Thread.holdsLock(this.hints);
/* 214 */     if (this.factory == null)
/* 215 */       this.factory = getBackingFactory((Hints)null); 
/* 217 */     return this.factory;
/*     */   }
/*     */   
/*     */   void initializeHints() {
/* 227 */     super.initializeHints();
/* 228 */     this.hints.put(Hints.COORDINATE_OPERATION_FACTORY, getBackingFactory());
/*     */   }
/*     */   
/*     */   public CoordinateOperation createOperation(CoordinateReferenceSystem sourceCRS, CoordinateReferenceSystem targetCRS) throws OperationNotFoundException, FactoryException {
/*     */     CoordinateOperation op;
/* 249 */     ensureNonNull("sourceCRS", sourceCRS);
/* 250 */     ensureNonNull("targetCRS", targetCRS);
/* 251 */     CRSPair key = new CRSPair(sourceCRS, targetCRS);
/* 253 */     synchronized (this.hints) {
/* 254 */       op = this.pool.get(key);
/* 255 */       if (op == null) {
/* 256 */         op = getBackingFactory().createOperation(sourceCRS, targetCRS);
/* 257 */         this.pool.put(key, op);
/*     */       } 
/*     */     } 
/* 260 */     return op;
/*     */   }
/*     */   
/*     */   public CoordinateOperation createOperation(CoordinateReferenceSystem sourceCRS, CoordinateReferenceSystem targetCRS, OperationMethod method) throws OperationNotFoundException, FactoryException {
/* 276 */     synchronized (this.hints) {
/* 277 */       return getBackingFactory().createOperation(sourceCRS, targetCRS, method);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\operation\BufferedCoordinateOperationFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */