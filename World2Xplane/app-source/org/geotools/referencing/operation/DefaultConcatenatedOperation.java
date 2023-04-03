/*     */ package org.geotools.referencing.operation;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedHashSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.geotools.referencing.AbstractIdentifiedObject;
/*     */ import org.geotools.referencing.operation.transform.ConcatenatedTransform;
/*     */ import org.geotools.referencing.wkt.Formatter;
/*     */ import org.geotools.resources.Classes;
/*     */ import org.geotools.resources.UnmodifiableArrayList;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ import org.opengis.metadata.quality.PositionalAccuracy;
/*     */ import org.opengis.referencing.FactoryException;
/*     */ import org.opengis.referencing.IdentifiedObject;
/*     */ import org.opengis.referencing.crs.CoordinateReferenceSystem;
/*     */ import org.opengis.referencing.operation.ConcatenatedOperation;
/*     */ import org.opengis.referencing.operation.CoordinateOperation;
/*     */ import org.opengis.referencing.operation.MathTransform;
/*     */ import org.opengis.referencing.operation.MathTransformFactory;
/*     */ import org.opengis.referencing.operation.SingleOperation;
/*     */ 
/*     */ public class DefaultConcatenatedOperation extends AbstractCoordinateOperation implements ConcatenatedOperation {
/*     */   private static final long serialVersionUID = 4199619838029045700L;
/*     */   
/*     */   private final List<SingleOperation> operations;
/*     */   
/*     */   public DefaultConcatenatedOperation(String name, CoordinateOperation[] operations) {
/*  88 */     this(Collections.singletonMap("name", name), operations);
/*     */   }
/*     */   
/*     */   public DefaultConcatenatedOperation(Map<String, ?> properties, CoordinateOperation[] operations) {
/* 102 */     this(properties, new ArrayList<SingleOperation>((operations != null) ? operations.length : 4), operations);
/*     */   }
/*     */   
/*     */   public DefaultConcatenatedOperation(Map<String, ?> properties, CoordinateOperation[] operations, MathTransformFactory factory) throws FactoryException {
/* 122 */     this(properties, new ArrayList<SingleOperation>((operations != null) ? operations.length : 4), operations, factory);
/*     */   }
/*     */   
/*     */   private DefaultConcatenatedOperation(Map<String, ?> properties, ArrayList<SingleOperation> list, CoordinateOperation[] operations) {
/* 134 */     this(properties, expand(operations, list), list);
/*     */   }
/*     */   
/*     */   private DefaultConcatenatedOperation(Map<String, ?> properties, ArrayList<SingleOperation> list, CoordinateOperation[] operations, MathTransformFactory factory) throws FactoryException {
/* 147 */     this(properties, expand(operations, list, factory, true), list);
/*     */   }
/*     */   
/*     */   private DefaultConcatenatedOperation(Map<String, ?> properties, MathTransform transform, List<SingleOperation> operations) {
/* 158 */     super(mergeAccuracy(properties, (List)operations), ((SingleOperation)operations.get(0)).getSourceCRS(), ((SingleOperation)operations.get(operations.size() - 1)).getTargetCRS(), transform);
/* 162 */     this.operations = (List<SingleOperation>)UnmodifiableArrayList.wrap(operations.toArray((Object[])new SingleOperation[operations.size()]));
/*     */   }
/*     */   
/*     */   private static MathTransform expand(CoordinateOperation[] operations, List<SingleOperation> list) {
/*     */     try {
/* 174 */       return expand(operations, list, (MathTransformFactory)null, true);
/* 175 */     } catch (FactoryException exception) {
/* 177 */       throw new AssertionError(exception);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static MathTransform expand(CoordinateOperation[] operations, List<SingleOperation> target, MathTransformFactory factory, boolean wantTransform) throws FactoryException {
/* 199 */     MathTransform transform = null;
/* 200 */     ensureNonNull("operations", operations);
/* 201 */     for (int i = 0; i < operations.length; i++) {
/* 202 */       ensureNonNull("operations", (Object[])operations, i);
/* 203 */       CoordinateOperation op = operations[i];
/* 204 */       if (op instanceof SingleOperation) {
/* 205 */         target.add((SingleOperation)op);
/* 206 */       } else if (op instanceof ConcatenatedOperation) {
/* 207 */         ConcatenatedOperation cop = (ConcatenatedOperation)op;
/* 208 */         List<SingleOperation> cops = cop.getOperations();
/* 209 */         expand(cops.<CoordinateOperation>toArray(new CoordinateOperation[cops.size()]), target, factory, false);
/*     */       } else {
/* 211 */         throw new IllegalArgumentException(Errors.format(61, Classes.getClass(op), SingleOperation.class));
/*     */       } 
/* 217 */       if (i != 0) {
/* 218 */         CoordinateReferenceSystem previous = operations[i - 1].getTargetCRS();
/* 219 */         CoordinateReferenceSystem next = op.getSourceCRS();
/* 220 */         if (previous != null && next != null) {
/* 221 */           int dim1 = previous.getCoordinateSystem().getDimension();
/* 222 */           int dim2 = next.getCoordinateSystem().getDimension();
/* 223 */           if (dim1 != dim2)
/* 224 */             throw new IllegalArgumentException(Errors.format(93, Integer.valueOf(dim1), Integer.valueOf(dim2))); 
/*     */         } 
/*     */       } 
/* 232 */       if (wantTransform) {
/* 233 */         MathTransform step = op.getMathTransform();
/* 234 */         if (transform == null) {
/* 235 */           transform = step;
/* 236 */         } else if (factory != null) {
/* 237 */           transform = factory.createConcatenatedTransform(transform, step);
/*     */         } else {
/* 239 */           transform = ConcatenatedTransform.create(transform, step);
/*     */         } 
/*     */       } 
/*     */     } 
/* 243 */     if (wantTransform) {
/* 244 */       int size = target.size();
/* 245 */       if (size <= 1)
/* 246 */         throw new IllegalArgumentException(Errors.format(99, "operations[" + size + ']')); 
/*     */     } 
/* 250 */     return transform;
/*     */   }
/*     */   
/*     */   private static Map<String, ?> mergeAccuracy(Map<String, ?> properties, List<? extends CoordinateOperation> operations) {
/* 277 */     if (!properties.containsKey("coordinateOperationAccuracy")) {
/* 278 */       Set<PositionalAccuracy> accuracy = null;
/* 279 */       for (CoordinateOperation op : operations) {
/* 280 */         if (op instanceof org.opengis.referencing.operation.Transformation) {
/* 282 */           Collection<PositionalAccuracy> candidates = op.getCoordinateOperationAccuracy();
/* 283 */           if (candidates != null && !candidates.isEmpty()) {
/* 284 */             if (accuracy == null)
/* 285 */               accuracy = new LinkedHashSet<PositionalAccuracy>(); 
/* 287 */             accuracy.addAll(candidates);
/*     */           } 
/*     */         } 
/*     */       } 
/* 291 */       if (accuracy != null) {
/* 292 */         Map<String, Object> merged = new HashMap<String, Object>(properties);
/* 293 */         merged.put("coordinateOperationAccuracy", accuracy.toArray(new PositionalAccuracy[accuracy.size()]));
/* 295 */         return merged;
/*     */       } 
/*     */     } 
/* 298 */     return properties;
/*     */   }
/*     */   
/*     */   public List<SingleOperation> getOperations() {
/* 305 */     return this.operations;
/*     */   }
/*     */   
/*     */   public boolean equals(AbstractIdentifiedObject object, boolean compareMetadata) {
/* 320 */     if (object == this)
/* 321 */       return true; 
/* 323 */     if (super.equals(object, compareMetadata)) {
/* 324 */       DefaultConcatenatedOperation that = (DefaultConcatenatedOperation)object;
/* 325 */       return equals(this.operations, that.operations, compareMetadata);
/*     */     } 
/* 327 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 335 */     return this.operations.hashCode() ^ 0xFD679FC4;
/*     */   }
/*     */   
/*     */   protected String formatWKT(Formatter formatter) {
/* 343 */     String label = super.formatWKT(formatter);
/* 344 */     for (Iterator<SingleOperation> it = this.operations.iterator(); it.hasNext();)
/* 345 */       formatter.append((IdentifiedObject)it.next()); 
/* 347 */     return label;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\operation\DefaultConcatenatedOperation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */