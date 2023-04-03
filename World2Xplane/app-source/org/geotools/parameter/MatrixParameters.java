/*     */ package org.geotools.parameter;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import org.geotools.io.TableWriter;
/*     */ import org.geotools.referencing.operation.matrix.MatrixFactory;
/*     */ import org.geotools.referencing.operation.matrix.XMatrix;
/*     */ import org.geotools.resources.UnmodifiableArrayList;
/*     */ import org.geotools.resources.XArray;
/*     */ import org.geotools.util.Utilities;
/*     */ import org.opengis.parameter.GeneralParameterDescriptor;
/*     */ import org.opengis.parameter.GeneralParameterValue;
/*     */ import org.opengis.parameter.ParameterDescriptor;
/*     */ import org.opengis.parameter.ParameterDescriptorGroup;
/*     */ import org.opengis.parameter.ParameterNotFoundException;
/*     */ import org.opengis.parameter.ParameterValue;
/*     */ import org.opengis.parameter.ParameterValueGroup;
/*     */ import org.opengis.referencing.ReferenceIdentifier;
/*     */ import org.opengis.referencing.operation.Matrix;
/*     */ import org.opengis.util.GenericName;
/*     */ import org.opengis.util.InternationalString;
/*     */ 
/*     */ public class MatrixParameters extends ParameterGroup implements ParameterDescriptorGroup {
/*     */   private static final long serialVersionUID = -7747712999115044943L;
/*     */   
/*     */   private ParameterValue<Double>[][] matrixValues;
/*     */   
/*     */   private ParameterValue<Integer> numRow;
/*     */   
/*     */   private ParameterValue<Integer> numCol;
/*     */   
/*     */   public MatrixParameters(MatrixParameterDescriptors descriptor) {
/*  89 */     super(descriptor);
/*  90 */     this.numRow = Parameters.cast((ParameterValue)parameter(0), Integer.class);
/*  91 */     this.numCol = Parameters.cast((ParameterValue)parameter(1), Integer.class);
/*     */   }
/*     */   
/*     */   public ParameterDescriptorGroup getDescriptor() {
/* 101 */     return this;
/*     */   }
/*     */   
/*     */   public ReferenceIdentifier getName() {
/* 109 */     return this.descriptor.getName();
/*     */   }
/*     */   
/*     */   public Collection<GenericName> getAlias() {
/* 117 */     return this.descriptor.getAlias();
/*     */   }
/*     */   
/*     */   public Set<ReferenceIdentifier> getIdentifiers() {
/* 125 */     return this.descriptor.getIdentifiers();
/*     */   }
/*     */   
/*     */   public InternationalString getRemarks() {
/* 133 */     return this.descriptor.getRemarks();
/*     */   }
/*     */   
/*     */   public int getMinimumOccurs() {
/* 141 */     return this.descriptor.getMinimumOccurs();
/*     */   }
/*     */   
/*     */   public int getMaximumOccurs() {
/* 149 */     return this.descriptor.getMaximumOccurs();
/*     */   }
/*     */   
/*     */   public GeneralParameterDescriptor descriptor(String name) throws ParameterNotFoundException {
/* 167 */     return ((MatrixParameterDescriptors)this.descriptor).descriptor(name, this.numRow.intValue(), this.numCol.intValue());
/*     */   }
/*     */   
/*     */   public ParameterValue<?> parameter(String name) throws ParameterNotFoundException {
/* 185 */     ensureNonNull("name", name);
/* 186 */     name = name.trim();
/* 187 */     MatrixParameterDescriptors descriptor = (MatrixParameterDescriptors)this.descriptor;
/* 188 */     String prefix = descriptor.prefix;
/* 189 */     RuntimeException cause = null;
/* 190 */     if (name.regionMatches(true, 0, prefix, 0, prefix.length())) {
/* 191 */       int split = name.indexOf(descriptor.separator, prefix.length());
/* 192 */       if (split >= 0)
/*     */         try {
/* 193 */           int row = Integer.parseInt(name.substring(prefix.length(), split));
/* 194 */           int col = Integer.parseInt(name.substring(split + 1));
/* 195 */           return parameter(row, col);
/* 196 */         } catch (NumberFormatException exception) {
/* 197 */           cause = exception;
/* 198 */         } catch (IndexOutOfBoundsException exception) {
/* 199 */           cause = exception;
/*     */         }  
/*     */     } 
/*     */     try {
/* 207 */       return super.parameter(name);
/* 208 */     } catch (ParameterNotFoundException exception) {
/* 209 */       if (cause != null)
/*     */         try {
/* 210 */           exception.initCause(cause);
/* 211 */         } catch (IllegalStateException ignore) {} 
/* 214 */       throw exception;
/*     */     } 
/*     */   }
/*     */   
/*     */   public final ParameterValue<Double> parameter(int row, int column) throws IndexOutOfBoundsException {
/* 230 */     return parameter(row, column, this.numRow.intValue(), this.numCol.intValue());
/*     */   }
/*     */   
/*     */   private ParameterValue<Double> parameter(int row, int column, int numRow, int numCol) throws IndexOutOfBoundsException {
/*     */     ParameterValue[] arrayOfParameterValue;
/* 248 */     MatrixParameterDescriptors.checkIndice("row", row, numRow);
/* 249 */     MatrixParameterDescriptors.checkIndice("column", column, numCol);
/* 250 */     if (this.matrixValues == null)
/* 251 */       this.matrixValues = (ParameterValue<Double>[][])new ParameterValue[numRow][]; 
/* 253 */     if (row >= this.matrixValues.length)
/* 254 */       this.matrixValues = (ParameterValue<Double>[][])XArray.resize((Object[])this.matrixValues, numRow); 
/* 256 */     ParameterValue<Double>[] rowValues = this.matrixValues[row];
/* 257 */     if (rowValues == null)
/* 258 */       this.matrixValues[row] = (ParameterValue<Double>[])(arrayOfParameterValue = new ParameterValue[numCol]); 
/* 260 */     if (column >= arrayOfParameterValue.length)
/* 261 */       this.matrixValues[row] = (ParameterValue<Double>[])(arrayOfParameterValue = (ParameterValue[])XArray.resize((Object[])arrayOfParameterValue, numCol)); 
/* 263 */     ParameterValue<Double> param = arrayOfParameterValue[column];
/* 264 */     if (param == null)
/* 265 */       arrayOfParameterValue[column] = param = new FloatParameter(((MatrixParameterDescriptors)this.descriptor).descriptor(row, column, numRow, numCol)); 
/* 268 */     return param;
/*     */   }
/*     */   
/*     */   public List<GeneralParameterDescriptor> descriptors() {
/* 276 */     return ((MatrixParameterDescriptors)this.descriptor).descriptors(this.numRow.intValue(), this.numCol.intValue());
/*     */   }
/*     */   
/*     */   public List<GeneralParameterValue> values() {
/* 289 */     int numRow = this.numRow.intValue();
/* 290 */     int numCol = this.numCol.intValue();
/* 291 */     ParameterValue[] parameters = new ParameterValue[numRow * numCol + 2];
/* 292 */     int k = 0;
/* 293 */     parameters[k++] = this.numRow;
/* 294 */     parameters[k++] = this.numCol;
/* 295 */     if (this.matrixValues != null) {
/* 296 */       int maxRow = Math.min(numRow, this.matrixValues.length);
/* 297 */       for (int j = 0; j < maxRow; j++) {
/* 298 */         ParameterValue<Double>[] arrayOfParameterValue = this.matrixValues[j];
/* 299 */         if (arrayOfParameterValue != null) {
/* 300 */           int maxCol = Math.min(numCol, arrayOfParameterValue.length);
/* 301 */           for (int i = 0; i < maxCol; i++) {
/* 302 */             ParameterValue<Double> value = arrayOfParameterValue[i];
/* 303 */             if (value != null)
/* 304 */               parameters[k++] = value; 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 310 */     return (List<GeneralParameterValue>)UnmodifiableArrayList.wrap(XArray.resize((Object[])parameters, k));
/*     */   }
/*     */   
/*     */   public ParameterValueGroup createValue() {
/* 318 */     return (ParameterValueGroup)this.descriptor.createValue();
/*     */   }
/*     */   
/*     */   public Matrix getMatrix() {
/* 327 */     int numRow = this.numRow.intValue();
/* 328 */     int numCol = this.numCol.intValue();
/* 329 */     XMatrix xMatrix = MatrixFactory.create(numRow, numCol);
/* 330 */     if (this.matrixValues != null)
/* 331 */       for (int j = 0; j < numRow; j++) {
/* 332 */         ParameterValue<Double>[] arrayOfParameterValue = this.matrixValues[j];
/* 333 */         if (arrayOfParameterValue != null)
/* 334 */           for (int i = 0; i < numCol; i++) {
/* 335 */             ParameterValue<Double> element = arrayOfParameterValue[i];
/* 336 */             if (element != null)
/* 337 */               xMatrix.setElement(j, i, element.doubleValue()); 
/*     */           }  
/*     */       }  
/* 343 */     return (Matrix)xMatrix;
/*     */   }
/*     */   
/*     */   public void setMatrix(Matrix matrix) {
/* 355 */     MatrixParameterDescriptors matrixDescriptor = (MatrixParameterDescriptors)this.descriptor;
/* 357 */     int numRow = matrix.getNumRow();
/* 358 */     int numCol = matrix.getNumCol();
/* 359 */     this.numRow.setValue(numRow);
/* 360 */     this.numCol.setValue(numCol);
/* 361 */     for (int row = 0; row < numRow; row++) {
/* 362 */       for (int col = 0; col < numCol; col++) {
/* 363 */         double element = matrix.getElement(row, col);
/* 364 */         ParameterDescriptor<Double> descriptor = matrixDescriptor.descriptor(row, col);
/* 365 */         double value = ((Double)descriptor.getDefaultValue()).doubleValue();
/* 366 */         if (Double.doubleToLongBits(element) == Double.doubleToLongBits(value)) {
/* 371 */           if (this.matrixValues != null && this.matrixValues[row] != null)
/* 372 */             this.matrixValues[row][col] = null; 
/*     */         } else {
/* 376 */           if (this.matrixValues == null)
/* 377 */             this.matrixValues = (ParameterValue<Double>[][])new ParameterValue[numRow][]; 
/* 379 */           if (this.matrixValues[row] == null)
/* 380 */             this.matrixValues[row] = (ParameterValue<Double>[])new ParameterValue[numCol]; 
/* 382 */           this.matrixValues[row][col] = new FloatParameter(descriptor, element);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/* 392 */     if (object == this)
/* 393 */       return true; 
/* 395 */     if (super.equals(object)) {
/* 396 */       MatrixParameters that = (MatrixParameters)object;
/* 397 */       int numRow = this.numRow.intValue();
/* 398 */       int numCol = this.numCol.intValue();
/* 399 */       for (int j = 0; j < numRow; j++) {
/* 400 */         for (int i = 0; i < numCol; i++) {
/* 401 */           if (!Utilities.equals(parameter(j, i, numRow, numCol), that.parameter(j, i, numRow, numCol)))
/* 404 */             return false; 
/*     */         } 
/*     */       } 
/* 408 */       return true;
/*     */     } 
/* 410 */     return false;
/*     */   }
/*     */   
/*     */   public MatrixParameters clone() {
/* 419 */     MatrixParameters copy = (MatrixParameters)super.clone();
/* 420 */     if (copy.matrixValues != null) {
/* 421 */       copy.numRow = (ParameterValue<Integer>)copy.parameter(0);
/* 422 */       copy.numCol = (ParameterValue<Integer>)copy.parameter(1);
/* 423 */       copy.matrixValues = (ParameterValue<Double>[][])copy.matrixValues.clone();
/* 424 */       for (int j = 0; j < copy.matrixValues.length; j++) {
/* 425 */         ParameterValue<Double>[] arrayOfParameterValue = copy.matrixValues[j];
/* 426 */         if (arrayOfParameterValue != null) {
/* 427 */           ParameterValue[] arrayOfParameterValue1 = (ParameterValue[])arrayOfParameterValue.clone();
/* 428 */           for (int i = 0; i < arrayOfParameterValue1.length; i++) {
/* 429 */             if (arrayOfParameterValue1[i] != null)
/* 430 */               arrayOfParameterValue1[i] = arrayOfParameterValue1[i].clone(); 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 436 */     return copy;
/*     */   }
/*     */   
/*     */   protected void write(TableWriter table) throws IOException {
/* 447 */     table.write(getName(this.descriptor));
/* 448 */     table.nextColumn();
/* 449 */     table.write(61);
/* 450 */     table.nextColumn();
/* 451 */     table.write(getMatrix().toString());
/* 452 */     table.nextLine();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\parameter\MatrixParameters.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */