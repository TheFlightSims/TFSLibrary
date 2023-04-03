/*     */ package org.geotools.parameter;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.Serializable;
/*     */ import java.io.Writer;
/*     */ import java.lang.reflect.Array;
/*     */ import java.util.Iterator;
/*     */ import org.geotools.io.TableWriter;
/*     */ import org.geotools.referencing.wkt.Formattable;
/*     */ import org.geotools.referencing.wkt.Formatter;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ import org.geotools.util.Utilities;
/*     */ import org.opengis.parameter.GeneralParameterDescriptor;
/*     */ import org.opengis.parameter.GeneralParameterValue;
/*     */ import org.opengis.parameter.ParameterValue;
/*     */ import org.opengis.parameter.ParameterValueGroup;
/*     */ 
/*     */ public abstract class AbstractParameter extends Formattable implements GeneralParameterValue, Serializable {
/*     */   private static final long serialVersionUID = 8458179223988766398L;
/*     */   
/*     */   final GeneralParameterDescriptor descriptor;
/*     */   
/*     */   protected AbstractParameter(GeneralParameterDescriptor descriptor) {
/*  72 */     this.descriptor = descriptor;
/*  73 */     ensureNonNull("descriptor", descriptor);
/*     */   }
/*     */   
/*     */   public GeneralParameterDescriptor getDescriptor() {
/*  80 */     return this.descriptor;
/*     */   }
/*     */   
/*     */   static void ensureNonNull(String name, Object object) throws IllegalArgumentException {
/*  96 */     if (object == null)
/*  97 */       throw new IllegalArgumentException(Errors.format(143, name)); 
/*     */   }
/*     */   
/*     */   static void ensureNonNull(String name, Object[] array, int index) throws IllegalArgumentException {
/* 113 */     if (array[index] == null)
/* 114 */       throw new IllegalArgumentException(Errors.format(143, name + '[' + index + ']')); 
/*     */   }
/*     */   
/*     */   static <T> void ensureValidClass(Class<?> expectedClass, Object value) throws IllegalArgumentException {
/* 130 */     if (value != null) {
/* 131 */       Class<?> valueClass = value.getClass();
/* 132 */       if (!expectedClass.isAssignableFrom(valueClass))
/* 133 */         throw new IllegalArgumentException(Errors.format(61, valueClass, expectedClass)); 
/*     */     } 
/*     */   }
/*     */   
/*     */   static IllegalStateException unitlessParameter(GeneralParameterDescriptor descriptor) {
/* 144 */     return new IllegalStateException(Errors.format(179, getName(descriptor)));
/*     */   }
/*     */   
/*     */   static String getName(GeneralParameterDescriptor descriptor) {
/* 154 */     return descriptor.getName().getCode();
/*     */   }
/*     */   
/*     */   public AbstractParameter clone() {
/*     */     try {
/* 163 */       return (AbstractParameter)super.clone();
/* 164 */     } catch (CloneNotSupportedException exception) {
/* 166 */       throw new AssertionError(exception);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/* 178 */     if (object != null && object.getClass().equals(getClass())) {
/* 179 */       AbstractParameter that = (AbstractParameter)object;
/* 180 */       return Utilities.equals(this.descriptor, that.descriptor);
/*     */     } 
/* 182 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 191 */     return this.descriptor.hashCode() ^ 0xDC7086BE;
/*     */   }
/*     */   
/*     */   public final String toString() {
/* 200 */     TableWriter table = new TableWriter(null, 1);
/* 201 */     table.setMultiLinesCells(true);
/*     */     try {
/* 203 */       write(table);
/* 204 */     } catch (IOException exception) {
/* 206 */       throw new AssertionError(exception);
/*     */     } 
/* 208 */     return table.toString();
/*     */   }
/*     */   
/*     */   protected void write(TableWriter table) throws IOException {
/* 237 */     table.write(getName(this.descriptor));
/* 238 */     table.nextColumn();
/* 239 */     if (this instanceof ParameterValue) {
/* 245 */       table.write(61);
/* 246 */       table.nextColumn();
/* 247 */       append((Writer)table, ((ParameterValue)this).getValue());
/* 248 */     } else if (this instanceof ParameterValueGroup) {
/* 254 */       table.write(58);
/* 255 */       table.nextColumn();
/* 256 */       TableWriter inner = null;
/* 257 */       for (Iterator<GeneralParameterValue> it = ((ParameterValueGroup)this).values().iterator(); it.hasNext(); ) {
/* 258 */         GeneralParameterValue value = it.next();
/* 259 */         if (value instanceof AbstractParameter) {
/* 260 */           if (inner == null)
/* 261 */             inner = new TableWriter((Writer)table, 1); 
/* 263 */           ((AbstractParameter)value).write(inner);
/*     */           continue;
/*     */         } 
/* 266 */         if (inner != null) {
/* 267 */           inner.flush();
/* 268 */           inner = null;
/*     */         } 
/* 270 */         table.write(value.toString());
/* 271 */         table.write(System.getProperty("line.separator", "\r"));
/*     */       } 
/* 274 */       if (inner != null)
/* 275 */         inner.flush(); 
/*     */     } 
/* 282 */     table.nextLine();
/*     */   }
/*     */   
/*     */   private static void append(Writer buffer, Object value) throws IOException {
/* 291 */     if (value == null) {
/* 292 */       buffer.write("null");
/* 293 */     } else if (value.getClass().isArray()) {
/* 294 */       buffer.write(123);
/* 295 */       int length = Array.getLength(value);
/* 296 */       int limit = Math.min(5, length);
/* 297 */       for (int i = 0; i < limit; i++) {
/* 298 */         if (i != 0)
/* 299 */           buffer.write(", "); 
/* 301 */         append(buffer, Array.get(value, i));
/*     */       } 
/* 303 */       if (length > limit)
/* 304 */         buffer.write(", ..."); 
/* 306 */       buffer.write(125);
/*     */     } else {
/* 308 */       boolean isNumeric = value instanceof Number;
/* 309 */       if (!isNumeric)
/* 310 */         buffer.write(34); 
/* 312 */       buffer.write(value.toString());
/* 313 */       if (!isNumeric)
/* 314 */         buffer.write(34); 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected final String formatWKT(Formatter formatter) {
/* 327 */     return "PARAMETER";
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\parameter\AbstractParameter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */