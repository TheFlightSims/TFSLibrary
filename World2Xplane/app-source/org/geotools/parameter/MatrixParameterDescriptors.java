/*     */ package org.geotools.parameter;
/*     */ 
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.measure.unit.Unit;
/*     */ import org.geotools.referencing.AbstractIdentifiedObject;
/*     */ import org.geotools.referencing.operation.matrix.MatrixFactory;
/*     */ import org.geotools.referencing.operation.matrix.XMatrix;
/*     */ import org.geotools.resources.UnmodifiableArrayList;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ import org.geotools.util.Utilities;
/*     */ import org.opengis.parameter.GeneralParameterDescriptor;
/*     */ import org.opengis.parameter.GeneralParameterValue;
/*     */ import org.opengis.parameter.InvalidParameterNameException;
/*     */ import org.opengis.parameter.ParameterDescriptor;
/*     */ import org.opengis.parameter.ParameterNotFoundException;
/*     */ import org.opengis.parameter.ParameterValue;
/*     */ import org.opengis.parameter.ParameterValueGroup;
/*     */ import org.opengis.referencing.operation.Matrix;
/*     */ 
/*     */ public class MatrixParameterDescriptors extends DefaultParameterDescriptorGroup {
/*     */   private static final long serialVersionUID = -7386537348359343836L;
/*     */   
/*     */   public static final int DEFAULT_MATRIX_SIZE = 3;
/*     */   
/*     */   private static final int CACHE_SIZE = 8;
/*     */   
/*  92 */   private final ParameterDescriptor<Double>[] parameters = (ParameterDescriptor<Double>[])new ParameterDescriptor[64];
/*     */   
/*     */   protected final ParameterDescriptor<Integer> numRow;
/*     */   
/*     */   protected final ParameterDescriptor<Integer> numCol;
/*     */   
/*     */   protected final String prefix;
/*     */   
/*     */   protected final char separator;
/*     */   
/*     */   public MatrixParameterDescriptors(Map<String, ?> properties) {
/* 131 */     this(properties, (ParameterDescriptor<?>[])new ParameterDescriptor[] { DefaultParameterDescriptor.create("num_row", 3, 2, 50), DefaultParameterDescriptor.create("num_col", 3, 2, 50) }"elt_", '_');
/*     */   }
/*     */   
/*     */   public MatrixParameterDescriptors(Map<String, ?> properties, ParameterDescriptor<?>[] parameters, String prefix, char separator) {
/* 154 */     super(properties, (GeneralParameterDescriptor[])parameters);
/* 155 */     if (parameters.length < 2)
/* 157 */       throw new IllegalArgumentException(); 
/* 159 */     this.numRow = Parameters.cast(parameters[0], Integer.class);
/* 160 */     this.numCol = Parameters.cast(parameters[1], Integer.class);
/* 161 */     ensureNonNull("prefix", prefix);
/* 162 */     this.prefix = prefix;
/* 163 */     this.separator = separator;
/*     */   }
/*     */   
/*     */   static void checkIndice(String name, int index, int upper) throws IndexOutOfBoundsException {
/* 177 */     if (index < 0 || index >= upper)
/* 178 */       throw new IndexOutOfBoundsException(Errors.format(58, name, Integer.valueOf(index))); 
/*     */   }
/*     */   
/*     */   public final GeneralParameterDescriptor descriptor(String name) throws ParameterNotFoundException {
/* 199 */     return descriptor(name, ((Number)this.numRow.getMaximumValue()).intValue(), ((Number)this.numCol.getMaximumValue()).intValue());
/*     */   }
/*     */   
/*     */   final GeneralParameterDescriptor descriptor(String name, int numRow, int numCol) throws ParameterNotFoundException {
/* 215 */     ensureNonNull("name", name);
/* 216 */     name = name.trim();
/* 217 */     RuntimeException cause = null;
/* 218 */     if (name.regionMatches(true, 0, this.prefix, 0, this.prefix.length())) {
/* 219 */       int split = name.indexOf(this.separator, this.prefix.length());
/* 220 */       if (split >= 0)
/*     */         try {
/* 221 */           int row = Integer.parseInt(name.substring(this.prefix.length(), split));
/* 222 */           int col = Integer.parseInt(name.substring(split + 1));
/* 223 */           return (GeneralParameterDescriptor)descriptor(row, col, numRow, numCol);
/* 224 */         } catch (NumberFormatException exception) {
/* 225 */           cause = exception;
/* 226 */         } catch (IndexOutOfBoundsException exception) {
/* 227 */           cause = exception;
/*     */         }  
/*     */     } 
/*     */     try {
/* 235 */       return super.descriptor(name);
/* 236 */     } catch (ParameterNotFoundException exception) {
/* 237 */       if (cause != null)
/*     */         try {
/* 238 */           exception.initCause(cause);
/* 239 */         } catch (IllegalStateException ignore) {} 
/* 242 */       throw exception;
/*     */     } 
/*     */   }
/*     */   
/*     */   public final ParameterDescriptor<Double> descriptor(int row, int column) throws IndexOutOfBoundsException {
/* 261 */     return descriptor(row, column, ((Number)this.numRow.getMaximumValue()).intValue(), ((Number)this.numCol.getMaximumValue()).intValue());
/*     */   }
/*     */   
/*     */   final ParameterDescriptor<Double> descriptor(int row, int column, int numRow, int numCol) throws IndexOutOfBoundsException {
/* 279 */     checkIndice("row", row, numRow);
/* 280 */     checkIndice("column", column, numCol);
/* 281 */     int index = -1;
/* 283 */     if (row < 8 && column < 8) {
/* 284 */       index = row * 8 + column;
/* 285 */       ParameterDescriptor<Double> parameterDescriptor = this.parameters[index];
/* 286 */       if (parameterDescriptor != null)
/* 287 */         return parameterDescriptor; 
/*     */     } 
/* 296 */     ParameterDescriptor<Double> param = new DefaultParameterDescriptor<Double>(Collections.singletonMap("name", this.prefix + row + this.separator + column), Double.class, null, Double.valueOf((row == column) ? 1.0D : 0.0D), null, null, Unit.ONE, true);
/* 300 */     if (index >= 0)
/* 301 */       this.parameters[index] = param; 
/* 303 */     return param;
/*     */   }
/*     */   
/*     */   public final List<GeneralParameterDescriptor> descriptors() {
/* 315 */     return descriptors(((Integer)this.numRow.getDefaultValue()).intValue(), ((Integer)this.numCol.getDefaultValue()).intValue());
/*     */   }
/*     */   
/*     */   final List<GeneralParameterDescriptor> descriptors(int numRow, int numCol) {
/* 328 */     GeneralParameterDescriptor[] parameters = new GeneralParameterDescriptor[numRow * numCol + 2];
/* 329 */     int k = 0;
/* 330 */     parameters[k++] = (GeneralParameterDescriptor)this.numRow;
/* 331 */     parameters[k++] = (GeneralParameterDescriptor)this.numCol;
/* 332 */     for (int j = 0; j < numRow; j++) {
/* 333 */       for (int i = 0; i < numCol; i++)
/* 334 */         parameters[k++] = (GeneralParameterDescriptor)descriptor(j, i, numRow, numCol); 
/*     */     } 
/* 337 */     assert k == parameters.length : k;
/* 338 */     return (List<GeneralParameterDescriptor>)UnmodifiableArrayList.wrap((Object[])parameters);
/*     */   }
/*     */   
/*     */   public ParameterValueGroup createValue() {
/* 351 */     return new MatrixParameters(this);
/*     */   }
/*     */   
/*     */   public Matrix getMatrix(ParameterValueGroup parameters) throws InvalidParameterNameException {
/* 364 */     if (parameters instanceof MatrixParameters)
/* 366 */       return ((MatrixParameters)parameters).getMatrix(); 
/* 369 */     ParameterValue numRowParam = parameters.parameter(this.numRow.getName().toString());
/* 370 */     ParameterValue numColParam = parameters.parameter(this.numCol.getName().toString());
/* 371 */     int numRow = numRowParam.intValue();
/* 372 */     int numCol = numColParam.intValue();
/* 373 */     XMatrix xMatrix = MatrixFactory.create(numRow, numCol);
/* 374 */     List<GeneralParameterValue> params = parameters.values();
/* 375 */     if (params != null)
/* 376 */       for (GeneralParameterValue param : params) {
/* 377 */         if (param == numRowParam || param == numColParam)
/*     */           continue; 
/* 380 */         RuntimeException cause = null;
/* 381 */         String name = param.getDescriptor().getName().toString();
/* 382 */         if (name.regionMatches(true, 0, this.prefix, 0, this.prefix.length())) {
/* 383 */           int split = name.indexOf(this.separator, this.prefix.length());
/* 384 */           if (split >= 0)
/*     */             try {
/* 385 */               int row = Integer.parseInt(name.substring(this.prefix.length(), split));
/* 386 */               int col = Integer.parseInt(name.substring(split + 1));
/* 387 */               xMatrix.setElement(row, col, ((ParameterValue)param).doubleValue());
/*     */               continue;
/* 389 */             } catch (NumberFormatException numberFormatException) {
/* 390 */               cause = numberFormatException;
/* 391 */             } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
/* 392 */               cause = indexOutOfBoundsException;
/*     */             }  
/*     */         } 
/* 396 */         InvalidParameterNameException exception = new InvalidParameterNameException(Errors.format(176, name), name);
/* 398 */         if (cause != null)
/* 399 */           exception.initCause(cause); 
/* 401 */         throw exception;
/*     */       }  
/* 404 */     return (Matrix)xMatrix;
/*     */   }
/*     */   
/*     */   public boolean equals(AbstractIdentifiedObject object, boolean compareMetadata) {
/* 417 */     if (super.equals(object, compareMetadata)) {
/* 418 */       MatrixParameterDescriptors that = (MatrixParameterDescriptors)object;
/* 419 */       return (this.separator == that.separator && Utilities.equals(this.prefix, that.prefix));
/*     */     } 
/* 421 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 432 */     return super.hashCode() ^ this.prefix.hashCode() ^ 37 * this.separator;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\parameter\MatrixParameterDescriptors.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */