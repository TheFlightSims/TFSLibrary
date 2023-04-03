/*     */ package org.geotools.data.shapefile.dbf;
/*     */ 
/*     */ import java.io.BufferedOutputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.channels.WritableByteChannel;
/*     */ import java.nio.charset.Charset;
/*     */ import java.text.FieldPosition;
/*     */ import java.text.NumberFormat;
/*     */ import java.util.Arrays;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import java.util.Locale;
/*     */ import java.util.TimeZone;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import org.geotools.data.shapefile.files.StreamLogging;
/*     */ import org.geotools.resources.NIOUtilities;
/*     */ import org.geotools.util.logging.Logging;
/*     */ 
/*     */ public class DbaseFileWriter {
/*     */   private DbaseFileHeader header;
/*     */   
/*     */   private FieldFormatter formatter;
/*     */   
/*     */   WritableByteChannel channel;
/*     */   
/*     */   private ByteBuffer buffer;
/*     */   
/*     */   private final byte[][] nullValues;
/*     */   
/*  72 */   private StreamLogging streamLogger = new StreamLogging("Dbase File Writer");
/*     */   
/*     */   private Charset charset;
/*     */   
/*     */   private TimeZone timeZone;
/*     */   
/*  76 */   private boolean reportFieldSizeErrors = Boolean.getBoolean("org.geotools.shapefile.reportFieldSizeErrors");
/*     */   
/*     */   public DbaseFileWriter(DbaseFileHeader header, WritableByteChannel out) throws IOException {
/*  91 */     this(header, out, null, null);
/*     */   }
/*     */   
/*     */   public DbaseFileWriter(DbaseFileHeader header, WritableByteChannel out, Charset charset) throws IOException {
/* 107 */     this(header, out, charset, null);
/*     */   }
/*     */   
/*     */   public DbaseFileWriter(DbaseFileHeader header, WritableByteChannel out, Charset charset, TimeZone timeZone) throws IOException {
/* 125 */     header.writeHeader(out);
/* 126 */     this.header = header;
/* 127 */     this.channel = out;
/* 128 */     this.charset = (charset == null) ? Charset.defaultCharset() : charset;
/* 129 */     this.timeZone = (timeZone == null) ? TimeZone.getDefault() : timeZone;
/* 130 */     this.formatter = new FieldFormatter(this.charset, this.timeZone, !this.reportFieldSizeErrors);
/* 131 */     this.streamLogger.open();
/* 136 */     this.nullValues = new byte[header.getNumFields()][];
/* 137 */     for (int i = 0; i < this.nullValues.length; i++) {
/*     */       char nullChar;
/* 139 */       switch (header.getFieldType(i)) {
/*     */         case 'C':
/*     */         case 'G':
/*     */         case 'M':
/*     */         case 'c':
/* 144 */           nullChar = Character.MIN_VALUE;
/*     */           break;
/*     */         case 'L':
/*     */         case 'l':
/* 148 */           nullChar = '?';
/*     */           break;
/*     */         case 'F':
/*     */         case 'N':
/*     */         case 'f':
/*     */         case 'n':
/* 154 */           nullChar = '*';
/*     */           break;
/*     */         case 'D':
/*     */         case 'd':
/* 158 */           nullChar = '0';
/*     */           break;
/*     */         case '@':
/* 162 */           nullChar = Character.MIN_VALUE;
/*     */           break;
/*     */         default:
/* 166 */           nullChar = '0';
/*     */           break;
/*     */       } 
/* 169 */       this.nullValues[i] = new byte[header.getFieldLength(i)];
/* 170 */       Arrays.fill(this.nullValues[i], (byte)nullChar);
/*     */     } 
/* 172 */     this.buffer = NIOUtilities.allocate(header.getRecordLength());
/*     */   }
/*     */   
/*     */   private void write() throws IOException {
/* 176 */     this.buffer.position(0);
/* 177 */     int r = this.buffer.remaining();
/* 178 */     while ((r -= this.channel.write(this.buffer)) > 0);
/*     */   }
/*     */   
/*     */   public void write(Object[] record) throws IOException, DbaseFileException {
/* 194 */     if (record.length != this.header.getNumFields())
/* 195 */       throw new DbaseFileException("Wrong number of fields " + record.length + " expected " + this.header.getNumFields()); 
/* 199 */     this.buffer.position(0);
/* 202 */     this.buffer.put((byte)32);
/* 205 */     for (int i = 0; i < this.header.getNumFields(); i++) {
/*     */       byte[] bytes;
/* 207 */       if (record[i] == null) {
/* 208 */         bytes = this.nullValues[i];
/*     */       } else {
/* 210 */         bytes = fieldBytes(record[i], i);
/* 214 */         if (bytes.length != (this.nullValues[i]).length)
/* 215 */           bytes = this.nullValues[i]; 
/*     */       } 
/* 218 */       this.buffer.put(bytes);
/*     */     } 
/* 221 */     write();
/*     */   }
/*     */   
/*     */   private byte[] fieldBytes(Object obj, int col) throws UnsupportedEncodingException {
/*     */     String o;
/* 239 */     int fieldLen = this.header.getFieldLength(col);
/* 240 */     switch (this.header.getFieldType(col)) {
/*     */       case 'C':
/*     */       case 'c':
/* 243 */         o = this.formatter.getFieldString(fieldLen, obj.toString());
/* 301 */         return o.getBytes(this.charset.name());
/*     */       case 'L':
/*     */       case 'l':
/*     */         if (obj instanceof Boolean) {
/*     */           o = ((Boolean)obj).booleanValue() ? "T" : "F";
/*     */         } else {
/*     */           o = "?";
/*     */         } 
/* 301 */         return o.getBytes(this.charset.name());
/*     */       case 'G':
/*     */       case 'M':
/*     */         o = this.formatter.getFieldString(fieldLen, obj.toString());
/* 301 */         return o.getBytes(this.charset.name());
/*     */       case 'N':
/*     */       case 'n':
/*     */         if (this.header.getFieldDecimalCount(col) == 0) {
/*     */           o = this.formatter.getFieldString(fieldLen, 0, (Number)obj);
/* 301 */           return o.getBytes(this.charset.name());
/*     */         } 
/*     */       case 'F':
/*     */       case 'f':
/*     */         o = this.formatter.getFieldString(fieldLen, this.header.getFieldDecimalCount(col), (Number)obj);
/* 301 */         return o.getBytes(this.charset.name());
/*     */       case 'D':
/*     */       case 'd':
/*     */         if (obj instanceof Calendar) {
/*     */           o = this.formatter.getFieldString(((Calendar)obj).getTime());
/*     */         } else {
/*     */           o = this.formatter.getFieldString((Date)obj);
/*     */         } 
/* 301 */         return o.getBytes(this.charset.name());
/*     */       case '@':
/*     */         o = this.formatter.getFieldStringDateTime((Date)obj);
/*     */         if (Boolean.getBoolean("org.geotools.shapefile.datetime")) {
/*     */           char[] carr = o.toCharArray();
/*     */           byte[] barr = new byte[carr.length];
/*     */           for (int i = 0; i < carr.length; i++)
/*     */             barr[i] = (byte)carr[i]; 
/*     */           return barr;
/*     */         } 
/* 301 */         return o.getBytes(this.charset.name());
/*     */     } 
/*     */     throw new RuntimeException("Unknown type " + this.header.getFieldType(col));
/*     */   }
/*     */   
/*     */   public void close() throws IOException {
/* 319 */     if (this.channel != null && this.channel.isOpen()) {
/* 320 */       this.channel.close();
/* 321 */       this.streamLogger.close();
/*     */     } 
/* 323 */     if (this.buffer != null)
/* 324 */       NIOUtilities.clean(this.buffer, false); 
/* 326 */     this.buffer = null;
/* 327 */     this.channel = null;
/* 328 */     this.formatter = null;
/*     */   }
/*     */   
/*     */   public static class FieldFormatter {
/* 333 */     private StringBuffer buffer = new StringBuffer(255);
/*     */     
/* 334 */     private NumberFormat numFormat = NumberFormat.getNumberInstance(Locale.US);
/*     */     
/*     */     private Calendar calendar;
/*     */     
/* 336 */     private final long MILLISECS_PER_DAY = 86400000L;
/*     */     
/*     */     private String emptyString;
/*     */     
/*     */     private static final int MAXCHARS = 255;
/*     */     
/*     */     private Charset charset;
/*     */     
/*     */     private boolean swallowFieldSizeErrors = false;
/*     */     
/* 343 */     private static Logger logger = Logging.getLogger("org.geotools.data.shapefile");
/*     */     
/*     */     public FieldFormatter(Charset charset, TimeZone timeZone, boolean swallowFieldSizeErrors) {
/* 348 */       this.numFormat.setGroupingUsed(false);
/* 351 */       StringBuffer sb = new StringBuffer(255);
/* 352 */       sb.setLength(255);
/* 353 */       for (int i = 0; i < 255; i++)
/* 354 */         sb.setCharAt(i, ' '); 
/* 357 */       this.charset = charset;
/* 359 */       this.calendar = Calendar.getInstance(timeZone, Locale.US);
/* 361 */       this.emptyString = sb.toString();
/* 363 */       this.swallowFieldSizeErrors = swallowFieldSizeErrors;
/*     */     }
/*     */     
/*     */     public String getFieldString(int size, String s) {
/*     */       try {
/* 368 */         this.buffer.replace(0, size, this.emptyString);
/* 369 */         this.buffer.setLength(size);
/* 371 */         int maxSize = size;
/* 372 */         if (s != null) {
/* 373 */           this.buffer.replace(0, size, s);
/* 374 */           int currentBytes = (s.substring(0, Math.min(size, s.length())).getBytes(this.charset.name())).length;
/* 376 */           if (currentBytes > size) {
/* 377 */             char[] c = new char[1];
/* 378 */             for (int index = size - 1; currentBytes > size; index--) {
/* 379 */               c[0] = this.buffer.charAt(index);
/* 380 */               String string = new String(c);
/* 381 */               this.buffer.deleteCharAt(index);
/* 382 */               currentBytes -= (string.getBytes()).length;
/* 383 */               maxSize--;
/*     */             } 
/* 386 */           } else if (s.length() < size) {
/* 387 */             maxSize = size - currentBytes - s.length();
/* 388 */             for (int i = s.length(); i < size; i++)
/* 389 */               this.buffer.append(' '); 
/*     */           } 
/*     */         } 
/* 395 */         this.buffer.setLength(maxSize);
/* 397 */         return this.buffer.toString();
/* 398 */       } catch (UnsupportedEncodingException e) {
/* 399 */         throw new RuntimeException("This error should never occurr", e);
/*     */       } 
/*     */     }
/*     */     
/*     */     public String getFieldString(Date d) {
/* 405 */       if (d != null) {
/* 406 */         this.buffer.delete(0, this.buffer.length());
/* 408 */         this.calendar.setTime(d);
/* 409 */         int year = this.calendar.get(1);
/* 410 */         int month = this.calendar.get(2) + 1;
/* 412 */         int day = this.calendar.get(5);
/* 414 */         if (year < 1000)
/* 415 */           if (year >= 100) {
/* 416 */             this.buffer.append("0");
/* 417 */           } else if (year >= 10) {
/* 418 */             this.buffer.append("00");
/*     */           } else {
/* 420 */             this.buffer.append("000");
/*     */           }  
/* 423 */         this.buffer.append(year);
/* 425 */         if (month < 10)
/* 426 */           this.buffer.append("0"); 
/* 428 */         this.buffer.append(month);
/* 430 */         if (day < 10)
/* 431 */           this.buffer.append("0"); 
/* 433 */         this.buffer.append(day);
/*     */       } else {
/* 435 */         this.buffer.setLength(8);
/* 436 */         this.buffer.replace(0, 8, this.emptyString);
/*     */       } 
/* 439 */       this.buffer.setLength(8);
/* 440 */       return this.buffer.toString();
/*     */     }
/*     */     
/*     */     public String getFieldStringDateTime(Date d) {
/* 446 */       if (d == null)
/* 446 */         return null; 
/* 448 */       long difference = d.getTime() - DbaseFileHeader.MILLIS_SINCE_4713;
/* 450 */       int days = (int)(difference / 86400000L);
/* 451 */       int time = (int)(difference % 86400000L);
/*     */       try {
/* 454 */         ByteArrayOutputStream o_bytes = new ByteArrayOutputStream();
/* 456 */         DataOutputStream o_stream = new DataOutputStream(new BufferedOutputStream(o_bytes));
/* 457 */         o_stream.writeInt(days);
/* 458 */         o_stream.writeInt(time);
/* 459 */         o_stream.flush();
/* 460 */         byte[] bytes = o_bytes.toByteArray();
/* 463 */         char[] out = { (char)bytes[3], (char)bytes[2], (char)bytes[1], (char)bytes[0], (char)bytes[7], (char)bytes[6], (char)bytes[5], (char)bytes[4] };
/* 470 */         return new String(out);
/* 471 */       } catch (IOException e) {
/* 474 */         return null;
/*     */       } 
/*     */     }
/*     */     
/*     */     public String getFieldString(int size, int decimalPlaces, Number n) {
/* 479 */       this.buffer.delete(0, this.buffer.length());
/* 481 */       if (n != null) {
/* 482 */         double dval = n.doubleValue();
/* 494 */         if (Double.isNaN(dval) || Double.isInfinite(dval)) {
/* 495 */           this.buffer.append(n.toString());
/*     */         } else {
/* 499 */           this.numFormat.setMaximumFractionDigits(decimalPlaces);
/* 500 */           this.numFormat.setMinimumFractionDigits(decimalPlaces);
/* 501 */           FieldPosition fp = new FieldPosition(1);
/* 502 */           this.numFormat.format(n, this.buffer, fp);
/* 506 */           if (fp.getBeginIndex() >= size) {
/* 507 */             this.buffer.delete(0, this.buffer.length());
/* 508 */             this.buffer.append(n.toString());
/* 509 */             if (this.buffer.length() > size) {
/* 511 */               logger.logp(Level.WARNING, getClass().getName(), "getFieldString", "Writing DBF data, value {0} cannot be represented in size {1,number}", new Object[] { n, Integer.valueOf(size) });
/* 513 */               if (!this.swallowFieldSizeErrors)
/* 515 */                 throw new IllegalArgumentException("Value " + n + " cannot be represented in size " + size); 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/* 522 */       int diff = size - this.buffer.length();
/* 523 */       if (diff > 0) {
/* 524 */         this.buffer.insert(0, this.emptyString.substring(0, diff));
/* 525 */       } else if (diff < 0) {
/* 526 */         this.buffer.setLength(size);
/*     */       } 
/* 528 */       return this.buffer.toString();
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean getReportFieldSizeErrors() {
/* 533 */     return this.reportFieldSizeErrors;
/*     */   }
/*     */   
/*     */   public void setReportFieldSizeErrors(boolean reportFieldSizeErrors) {
/* 537 */     this.reportFieldSizeErrors = reportFieldSizeErrors;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\shapefile\dbf\DbaseFileWriter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */