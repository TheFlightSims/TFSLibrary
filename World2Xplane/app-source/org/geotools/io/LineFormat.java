/*     */ package org.geotools.io;
/*     */ 
/*     */ import java.lang.reflect.Array;
/*     */ import java.text.FieldPosition;
/*     */ import java.text.Format;
/*     */ import java.text.NumberFormat;
/*     */ import java.text.ParseException;
/*     */ import java.text.ParsePosition;
/*     */ import java.util.Arrays;
/*     */ import java.util.Locale;
/*     */ import org.geotools.resources.ClassChanger;
/*     */ import org.geotools.resources.XArray;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ 
/*     */ public class LineFormat extends Format {
/*     */   private static final long serialVersionUID = 1663380990689494113L;
/*     */   
/*     */   private int count;
/*     */   
/*     */   private Object[] data;
/*     */   
/*     */   private final Format[] format;
/*     */   
/* 108 */   private final ParsePosition position = new ParsePosition(0);
/*     */   
/*     */   private int[] limits;
/*     */   
/*     */   private String line;
/*     */   
/*     */   public LineFormat() {
/* 127 */     this(NumberFormat.getNumberInstance());
/*     */   }
/*     */   
/*     */   public LineFormat(Locale locale) {
/* 135 */     this(NumberFormat.getNumberInstance(locale));
/*     */   }
/*     */   
/*     */   public LineFormat(Format format) throws IllegalArgumentException {
/* 145 */     this.data = new Object[16];
/* 146 */     this.limits = new int[this.data.length + 1];
/* 147 */     this.format = new Format[] { format };
/* 148 */     if (format == null) {
/* 149 */       Integer one = Integer.valueOf(1);
/* 150 */       throw new IllegalArgumentException(Errors.format(145, one, one));
/*     */     } 
/*     */   }
/*     */   
/*     */   public LineFormat(Format[] formats) throws IllegalArgumentException {
/* 165 */     this.data = new Object[formats.length];
/* 166 */     this.format = new Format[formats.length];
/* 167 */     this.limits = new int[formats.length + 1];
/* 168 */     System.arraycopy(formats, 0, this.format, 0, formats.length);
/* 169 */     for (int i = 0; i < this.format.length; i++) {
/* 170 */       if (this.format[i] == null)
/* 171 */         throw new IllegalArgumentException(Errors.format(145, Integer.valueOf(i + 1), Integer.valueOf(this.format.length))); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void clear() {
/* 181 */     this.line = null;
/* 182 */     Arrays.fill(this.data, (Object)null);
/* 183 */     this.count = 0;
/*     */   }
/*     */   
/*     */   public int setLine(String line) throws ParseException {
/* 196 */     return setLine(line, 0, line.length());
/*     */   }
/*     */   
/*     */   public int setLine(String line, int lower, int upper) throws ParseException {
/* 215 */     this.line = line;
/* 216 */     Arrays.fill(this.data, (Object)null);
/* 217 */     this.count = 0;
/* 224 */     while (lower < upper) {
/* 227 */       if (!Character.isWhitespace(line.charAt(lower))) {
/* 234 */         this.position.setIndex(lower);
/* 235 */         Object datum = this.format[Math.min(this.count, this.format.length - 1)].parseObject(line, this.position);
/* 236 */         int next = this.position.getIndex();
/* 237 */         if (datum == null || next <= lower) {
/* 238 */           int error = this.position.getErrorIndex();
/* 239 */           int end = error;
/* 240 */           for (; end < upper && !Character.isWhitespace(line.charAt(end)); end++);
/* 241 */           throw new ParseException(Errors.format(155, line.substring(lower, end).trim(), line.substring(error, Math.min(error + 1, end))), error);
/*     */         } 
/* 249 */         if (this.count >= this.data.length) {
/* 250 */           this.data = XArray.resize(this.data, this.count + Math.min(this.count, 256));
/* 251 */           this.limits = XArray.resize(this.limits, this.data.length + 1);
/*     */         } 
/* 253 */         this.limits[this.count] = lower;
/* 254 */         this.data[this.count++] = datum;
/* 255 */         lower = next;
/*     */         continue;
/*     */       } 
/*     */       lower++;
/*     */     } 
/* 257 */     this.limits[this.count] = lower;
/* 258 */     return this.count;
/*     */   }
/*     */   
/*     */   public int getValueCount() {
/* 265 */     return this.count;
/*     */   }
/*     */   
/*     */   public void setValues(Object values) throws IllegalArgumentException {
/* 278 */     int length = Array.getLength(values);
/* 279 */     this.data = XArray.resize(this.data, length);
/* 280 */     for (int i = 0; i < length; i++)
/* 281 */       this.data[i] = Array.get(values, i); 
/* 283 */     this.count = length;
/*     */   }
/*     */   
/*     */   public void setValue(int index, Object value) throws ArrayIndexOutOfBoundsException {
/* 296 */     if (index > this.count)
/* 297 */       throw new ArrayIndexOutOfBoundsException(index); 
/* 299 */     if (value == null)
/* 300 */       throw new IllegalArgumentException(Errors.format(143, "value")); 
/* 302 */     if (index == this.count) {
/* 303 */       if (index == this.data.length)
/* 304 */         this.data = XArray.resize(this.data, index + Math.min(index, 256)); 
/* 306 */       this.count++;
/*     */     } 
/* 308 */     this.data[index] = value;
/*     */   }
/*     */   
/*     */   public Object getValue(int index) throws ArrayIndexOutOfBoundsException {
/* 320 */     if (index < this.count)
/* 321 */       return this.data[index]; 
/* 323 */     throw new ArrayIndexOutOfBoundsException(index);
/*     */   }
/*     */   
/*     */   private Object getValues() {
/* 330 */     Object[] values = new Object[this.count];
/* 331 */     System.arraycopy(this.data, 0, values, 0, this.count);
/* 332 */     return values;
/*     */   }
/*     */   
/*     */   private Number getNumber(int index) throws ParseException {
/* 343 */     Exception error = null;
/* 344 */     if (this.data[index] instanceof Comparable)
/*     */       try {
/* 346 */         return ClassChanger.toNumber((Comparable)this.data[index]);
/* 347 */       } catch (ClassNotFoundException classNotFoundException) {
/* 348 */         error = classNotFoundException;
/*     */       }  
/* 351 */     ParseException exception = new ParseException(Errors.format(191, this.data[index]), this.limits[index]);
/* 353 */     if (error != null)
/* 354 */       exception.initCause(error); 
/* 356 */     throw exception;
/*     */   }
/*     */   
/*     */   public double[] getValues(double[] array) throws ParseException {
/* 373 */     if (array != null) {
/* 374 */       checkLength(array.length);
/*     */     } else {
/* 376 */       array = new double[this.count];
/*     */     } 
/* 378 */     for (int i = 0; i < this.count; i++)
/* 379 */       array[i] = getNumber(i).doubleValue(); 
/* 381 */     return array;
/*     */   }
/*     */   
/*     */   public float[] getValues(float[] array) throws ParseException {
/* 398 */     if (array != null) {
/* 399 */       checkLength(array.length);
/*     */     } else {
/* 401 */       array = new float[this.count];
/*     */     } 
/* 403 */     for (int i = 0; i < this.count; i++)
/* 404 */       array[i] = getNumber(i).floatValue(); 
/* 406 */     return array;
/*     */   }
/*     */   
/*     */   public long[] getValues(long[] array) throws ParseException {
/* 423 */     if (array != null) {
/* 424 */       checkLength(array.length);
/*     */     } else {
/* 426 */       array = new long[this.count];
/*     */     } 
/* 428 */     for (int i = 0; i < this.count; i++) {
/* 429 */       Number n = getNumber(i);
/* 430 */       array[i] = n.longValue();
/* 430 */       if (n.longValue() != n.doubleValue())
/* 431 */         throw notAnInteger(i); 
/*     */     } 
/* 434 */     return array;
/*     */   }
/*     */   
/*     */   public int[] getValues(int[] array) throws ParseException {
/* 451 */     if (array != null) {
/* 452 */       checkLength(array.length);
/*     */     } else {
/* 454 */       array = new int[this.count];
/*     */     } 
/* 456 */     for (int i = 0; i < this.count; i++) {
/* 457 */       Number n = getNumber(i);
/* 458 */       array[i] = n.intValue();
/* 458 */       if (n.intValue() != n.doubleValue())
/* 459 */         throw notAnInteger(i); 
/*     */     } 
/* 462 */     return array;
/*     */   }
/*     */   
/*     */   public short[] getValues(short[] array) throws ParseException {
/* 479 */     if (array != null) {
/* 480 */       checkLength(array.length);
/*     */     } else {
/* 482 */       array = new short[this.count];
/*     */     } 
/* 484 */     for (int i = 0; i < this.count; i++) {
/* 485 */       Number n = getNumber(i);
/* 486 */       array[i] = n.shortValue();
/* 486 */       if (n.shortValue() != n.doubleValue())
/* 487 */         throw notAnInteger(i); 
/*     */     } 
/* 490 */     return array;
/*     */   }
/*     */   
/*     */   public byte[] getValues(byte[] array) throws ParseException {
/* 507 */     if (array != null) {
/* 508 */       checkLength(array.length);
/*     */     } else {
/* 510 */       array = new byte[this.count];
/*     */     } 
/* 512 */     for (int i = 0; i < this.count; i++) {
/* 513 */       Number n = getNumber(i);
/* 514 */       array[i] = n.byteValue();
/* 514 */       if (n.byteValue() != n.doubleValue())
/* 515 */         throw notAnInteger(i); 
/*     */     } 
/* 518 */     return array;
/*     */   }
/*     */   
/*     */   private void checkLength(int expected) throws ParseException {
/* 529 */     if (this.count != expected) {
/* 530 */       int lower = this.limits[Math.min(this.count, expected)];
/* 531 */       int upper = this.limits[Math.min(this.count, expected + 1)];
/* 532 */       throw new ParseException(Errors.format((this.count < expected) ? 87 : 86, Integer.valueOf(this.count), Integer.valueOf(expected), this.line.substring(lower, upper).trim()), lower);
/*     */     } 
/*     */   }
/*     */   
/*     */   private ParseException notAnInteger(int i) {
/* 545 */     return new ParseException(Errors.format(120, this.line.substring(this.limits[i], this.limits[i + 1])), this.limits[i]);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 556 */     return toString(new StringBuffer()).toString();
/*     */   }
/*     */   
/*     */   private StringBuffer toString(StringBuffer buffer) {
/* 565 */     FieldPosition field = new FieldPosition(0);
/* 566 */     for (int i = 0; i < this.count; i++) {
/* 567 */       if (i != 0)
/* 568 */         buffer.append('\t'); 
/* 570 */       buffer = this.format[Math.min(this.format.length - 1, i)].format(this.data[i], buffer, field);
/*     */     } 
/* 572 */     return buffer;
/*     */   }
/*     */   
/*     */   public StringBuffer format(Object values, StringBuffer toAppendTo, FieldPosition position) {
/* 586 */     setValues(values);
/* 587 */     return toString(toAppendTo);
/*     */   }
/*     */   
/*     */   private static int getLineEnd(String source, int offset, boolean s) {
/* 594 */     int length = source.length();
/* 595 */     while (offset < length) {
/* 596 */       char c = source.charAt(offset);
/* 597 */       if (((c == '\r' || c == '\n')) == s)
/*     */         break; 
/* 600 */       offset++;
/*     */     } 
/* 602 */     return offset;
/*     */   }
/*     */   
/*     */   public Object parseObject(String source, ParsePosition position) {
/* 611 */     int lower = position.getIndex();
/* 612 */     int upper = getLineEnd(source, lower, true);
/*     */     try {
/* 614 */       setLine(source.substring(lower, upper));
/* 615 */       position.setIndex(getLineEnd(source, upper, false));
/* 616 */       return getValues();
/* 617 */     } catch (ParseException e) {
/* 618 */       position.setErrorIndex(e.getErrorOffset());
/* 619 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public Object parseObject(String source) throws ParseException {
/* 630 */     setLine(source.substring(0, getLineEnd(source, 0, true)));
/* 631 */     return getValues();
/*     */   }
/*     */   
/*     */   public LineFormat clone() {
/* 640 */     LineFormat copy = (LineFormat)super.clone();
/* 641 */     copy.data = (Object[])this.data.clone();
/* 642 */     copy.limits = (int[])this.limits.clone();
/* 643 */     return copy;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\io\LineFormat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */