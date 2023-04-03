/*     */ package org.geotools.data.shapefile.dbf;
/*     */ 
/*     */ import java.io.EOFException;
/*     */ import java.io.IOException;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.ByteOrder;
/*     */ import java.nio.channels.ReadableByteChannel;
/*     */ import java.nio.channels.WritableByteChannel;
/*     */ import java.nio.charset.Charset;
/*     */ import java.sql.Timestamp;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import org.geotools.resources.NIOUtilities;
/*     */ import org.geotools.util.logging.Logging;
/*     */ 
/*     */ public class DbaseFileHeader {
/*     */   private static final int FILE_DESCRIPTOR_SIZE = 32;
/*     */   
/*     */   private static final byte MAGIC = 3;
/*     */   
/*     */   private static final int MINIMUM_HEADER = 33;
/*     */   
/*  57 */   private Date date = new Date();
/*     */   
/*  59 */   private int recordCnt = 0;
/*     */   
/*  61 */   private int fieldCnt = 0;
/*     */   
/*  65 */   private int recordLength = 1;
/*     */   
/*  69 */   private int headerLength = -1;
/*     */   
/*  71 */   private int largestFieldSize = 0;
/*     */   
/*  73 */   private Logger logger = Logging.getLogger("org.geotools.data.shapefile");
/*     */   
/*  91 */   public static long MILLIS_SINCE_4713 = -210866803200000L;
/*     */   
/*     */   class DbaseField {
/*     */     String fieldName;
/*     */     
/*     */     char fieldType;
/*     */     
/*     */     int fieldDataAddress;
/*     */     
/*     */     int fieldLength;
/*     */     
/*     */     int decimalCount;
/*     */   }
/*     */   
/* 117 */   private DbaseField[] fields = new DbaseField[0];
/*     */   
/*     */   private void read(ByteBuffer buffer, ReadableByteChannel channel) throws IOException {
/* 121 */     while (buffer.remaining() > 0) {
/* 122 */       if (channel.read(buffer) == -1)
/* 123 */         throw new EOFException("Premature end of file"); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public Class getFieldClass(int i) {
/*     */     Class<Double> clazz2;
/*     */     Class<Boolean> clazz1;
/*     */     Class<Date> clazz;
/* 149 */     Class<String> typeClass = null;
/* 151 */     switch ((this.fields[i]).fieldType) {
/*     */       case 'C':
/* 153 */         typeClass = String.class;
/* 189 */         return typeClass;
/*     */       case 'N':
/*     */         if ((this.fields[i]).decimalCount == 0) {
/*     */           if ((this.fields[i]).fieldLength < 10) {
/*     */             Class<Integer> clazz3 = Integer.class;
/*     */           } else {
/*     */             Class<Long> clazz3 = Long.class;
/*     */           } 
/*     */         } else {
/*     */           clazz2 = Double.class;
/*     */         } 
/* 189 */         return clazz2;
/*     */       case 'F':
/*     */         clazz2 = Double.class;
/* 189 */         return clazz2;
/*     */       case 'L':
/*     */         return Boolean.class;
/*     */       case 'D':
/*     */         return Date.class;
/*     */       case '@':
/*     */         return Timestamp.class;
/*     */     } 
/*     */     return String.class;
/*     */   }
/*     */   
/*     */   public void addColumn(String inFieldName, char inFieldType, int inFieldLength, int inDecimalCount) throws DbaseFileException {
/* 225 */     if (inFieldLength <= 0)
/* 226 */       throw new DbaseFileException("field length <= 0"); 
/* 228 */     if (this.fields == null)
/* 229 */       this.fields = new DbaseField[0]; 
/* 231 */     int tempLength = 1;
/* 233 */     DbaseField[] tempFieldDescriptors = new DbaseField[this.fields.length + 1];
/* 234 */     for (int i = 0; i < this.fields.length; i++) {
/* 235 */       (this.fields[i]).fieldDataAddress = tempLength;
/* 236 */       tempLength += (this.fields[i]).fieldLength;
/* 237 */       tempFieldDescriptors[i] = this.fields[i];
/*     */     } 
/* 239 */     tempFieldDescriptors[this.fields.length] = new DbaseField();
/* 240 */     (tempFieldDescriptors[this.fields.length]).fieldLength = inFieldLength;
/* 241 */     (tempFieldDescriptors[this.fields.length]).decimalCount = inDecimalCount;
/* 242 */     (tempFieldDescriptors[this.fields.length]).fieldDataAddress = tempLength;
/* 245 */     String tempFieldName = inFieldName;
/* 246 */     if (tempFieldName == null)
/* 247 */       tempFieldName = "NoName"; 
/* 251 */     if (tempFieldName.length() > 10) {
/* 252 */       tempFieldName = tempFieldName.substring(0, 10);
/* 253 */       if (this.logger.isLoggable(Level.WARNING))
/* 254 */         this.logger.warning("FieldName " + inFieldName + " is longer than 10 characters, truncating to " + tempFieldName); 
/*     */     } 
/* 259 */     (tempFieldDescriptors[this.fields.length]).fieldName = tempFieldName;
/* 262 */     if (inFieldType == 'C' || inFieldType == 'c') {
/* 263 */       (tempFieldDescriptors[this.fields.length]).fieldType = 'C';
/* 264 */       if (inFieldLength > 254 && 
/* 265 */         this.logger.isLoggable(Level.FINE))
/* 266 */         this.logger.fine("Field Length for " + inFieldName + " set to " + inFieldLength + " Which is longer than 254, not consistent with dbase III"); 
/* 274 */     } else if (inFieldType == 'S' || inFieldType == 's') {
/* 275 */       (tempFieldDescriptors[this.fields.length]).fieldType = 'C';
/* 276 */       if (this.logger.isLoggable(Level.WARNING))
/* 277 */         this.logger.warning("Field type for " + inFieldName + " set to S which is flat out wrong people!, I am setting this to C, in the hopes you meant character."); 
/* 282 */       if (inFieldLength > 254 && 
/* 283 */         this.logger.isLoggable(Level.FINE))
/* 284 */         this.logger.fine("Field Length for " + inFieldName + " set to " + inFieldLength + " Which is longer than 254, not consistent with dbase III"); 
/* 292 */       (tempFieldDescriptors[this.fields.length]).fieldLength = 8;
/* 293 */     } else if (inFieldType == 'D' || inFieldType == 'd') {
/* 294 */       (tempFieldDescriptors[this.fields.length]).fieldType = 'D';
/* 295 */       if (inFieldLength != 8 && 
/* 296 */         this.logger.isLoggable(Level.FINE))
/* 297 */         this.logger.fine("Field Length for " + inFieldName + " set to " + inFieldLength + " Setting to 8 digits YYYYMMDD"); 
/* 301 */       (tempFieldDescriptors[this.fields.length]).fieldLength = 8;
/* 302 */     } else if (inFieldType == '@') {
/* 303 */       (tempFieldDescriptors[this.fields.length]).fieldType = '@';
/* 304 */       if (inFieldLength != 8 && 
/* 305 */         this.logger.isLoggable(Level.FINE))
/* 306 */         this.logger.fine("Field Length for " + inFieldName + " set to " + inFieldLength + " Setting to 8 digits - two longs," + "one long for date and one long for time"); 
/* 311 */       (tempFieldDescriptors[this.fields.length]).fieldLength = 8;
/* 312 */     } else if (inFieldType == 'F' || inFieldType == 'f') {
/* 313 */       (tempFieldDescriptors[this.fields.length]).fieldType = 'F';
/* 314 */       if (inFieldLength > 20 && 
/* 315 */         this.logger.isLoggable(Level.FINE))
/* 316 */         this.logger.fine("Field Length for " + inFieldName + " set to " + inFieldLength + " Preserving length, but should be set to Max of 20 not valid for dbase IV, and UP specification, not present in dbaseIII."); 
/* 324 */     } else if (inFieldType == 'N' || inFieldType == 'n') {
/* 325 */       (tempFieldDescriptors[this.fields.length]).fieldType = 'N';
/* 326 */       if (inFieldLength > 18 && 
/* 327 */         this.logger.isLoggable(Level.FINE))
/* 328 */         this.logger.fine("Field Length for " + inFieldName + " set to " + inFieldLength + " Preserving length, but should be set to Max of 18 for dbase III specification."); 
/* 336 */       if (inDecimalCount < 0) {
/* 337 */         if (this.logger.isLoggable(Level.FINE))
/* 338 */           this.logger.fine("Field Decimal Position for " + inFieldName + " set to " + inDecimalCount + " Setting to 0 no decimal data will be saved."); 
/* 342 */         (tempFieldDescriptors[this.fields.length]).decimalCount = 0;
/*     */       } 
/* 344 */       if (inDecimalCount > inFieldLength - 1) {
/* 345 */         if (this.logger.isLoggable(Level.WARNING))
/* 346 */           this.logger.warning("Field Decimal Position for " + inFieldName + " set to " + inDecimalCount + " Setting to " + (inFieldLength - 1) + " no non decimal data will be saved."); 
/* 351 */         (tempFieldDescriptors[this.fields.length]).decimalCount = inFieldLength - 1;
/*     */       } 
/* 353 */     } else if (inFieldType == 'L' || inFieldType == 'l') {
/* 354 */       (tempFieldDescriptors[this.fields.length]).fieldType = 'L';
/* 355 */       if (inFieldLength != 1 && 
/* 356 */         this.logger.isLoggable(Level.FINE))
/* 357 */         this.logger.fine("Field Length for " + inFieldName + " set to " + inFieldLength + " Setting to length of 1 for logical fields."); 
/* 362 */       (tempFieldDescriptors[this.fields.length]).fieldLength = 1;
/*     */     } else {
/* 364 */       throw new DbaseFileException("Undefined field type " + inFieldType + " For column " + inFieldName);
/*     */     } 
/* 368 */     tempLength += (tempFieldDescriptors[this.fields.length]).fieldLength;
/* 372 */     this.fields = tempFieldDescriptors;
/* 373 */     this.fieldCnt = this.fields.length;
/* 374 */     this.headerLength = 33 + 32 * this.fields.length;
/* 375 */     this.recordLength = tempLength;
/*     */   }
/*     */   
/*     */   public int removeColumn(String inFieldName) {
/* 388 */     int retCol = -1;
/* 389 */     int tempLength = 1;
/* 390 */     DbaseField[] tempFieldDescriptors = new DbaseField[this.fields.length - 1];
/* 391 */     for (int i = 0, j = 0; i < this.fields.length; i++) {
/* 392 */       if (!inFieldName.equalsIgnoreCase((this.fields[i]).fieldName.trim())) {
/* 395 */         if (i == j && i == this.fields.length - 1) {
/* 396 */           System.err.println("Could not find a field named '" + inFieldName + "' for removal");
/* 398 */           return retCol;
/*     */         } 
/* 400 */         tempFieldDescriptors[j] = this.fields[i];
/* 401 */         (tempFieldDescriptors[j]).fieldDataAddress = tempLength;
/* 402 */         tempLength += (tempFieldDescriptors[j]).fieldLength;
/* 404 */         j++;
/*     */       } else {
/* 406 */         retCol = i;
/*     */       } 
/*     */     } 
/* 411 */     this.fields = tempFieldDescriptors;
/* 412 */     this.headerLength = 33 + 32 * this.fields.length;
/* 413 */     this.recordLength = tempLength;
/* 415 */     return retCol;
/*     */   }
/*     */   
/*     */   public int getFieldLength(int inIndex) {
/* 427 */     return (this.fields[inIndex]).fieldLength;
/*     */   }
/*     */   
/*     */   public int getFieldDecimalCount(int inIndex) {
/* 439 */     return (this.fields[inIndex]).decimalCount;
/*     */   }
/*     */   
/*     */   public String getFieldName(int inIndex) {
/* 451 */     return (this.fields[inIndex]).fieldName;
/*     */   }
/*     */   
/*     */   public char getFieldType(int inIndex) {
/* 463 */     return (this.fields[inIndex]).fieldType;
/*     */   }
/*     */   
/*     */   public Date getLastUpdateDate() {
/* 472 */     return this.date;
/*     */   }
/*     */   
/*     */   public int getNumFields() {
/* 481 */     return this.fields.length;
/*     */   }
/*     */   
/*     */   public int getNumRecords() {
/* 490 */     return this.recordCnt;
/*     */   }
/*     */   
/*     */   public int getRecordLength() {
/* 499 */     return this.recordLength;
/*     */   }
/*     */   
/*     */   public int getHeaderLength() {
/* 508 */     return this.headerLength;
/*     */   }
/*     */   
/*     */   public void readHeader(ReadableByteChannel channel) throws IOException {
/* 522 */     readHeader(channel, Charset.defaultCharset());
/*     */   }
/*     */   
/*     */   public void readHeader(ReadableByteChannel channel, Charset charset) throws IOException {
/* 537 */     ByteBuffer in = NIOUtilities.allocate(1024);
/*     */     try {
/* 541 */       in.order(ByteOrder.LITTLE_ENDIAN);
/* 544 */       in.limit(10);
/* 546 */       read(in, channel);
/* 547 */       in.position(0);
/* 550 */       byte magic = in.get();
/* 551 */       if (magic != 3)
/* 552 */         throw new IOException("Unsupported DBF file Type " + Integer.toHexString(magic)); 
/* 557 */       int tempUpdateYear = in.get();
/* 558 */       int tempUpdateMonth = in.get();
/* 559 */       int tempUpdateDay = in.get();
/* 561 */       if (tempUpdateYear > 90) {
/* 562 */         tempUpdateYear += 1900;
/*     */       } else {
/* 564 */         tempUpdateYear += 2000;
/*     */       } 
/* 566 */       Calendar c = Calendar.getInstance();
/* 567 */       c.set(1, tempUpdateYear);
/* 568 */       c.set(2, tempUpdateMonth - 1);
/* 569 */       c.set(5, tempUpdateDay);
/* 570 */       this.date = c.getTime();
/* 573 */       this.recordCnt = in.getInt();
/* 578 */       this.headerLength = in.get() & 0xFF | (in.get() & 0xFF) << 8;
/* 581 */       if (this.headerLength > in.capacity()) {
/* 582 */         NIOUtilities.clean(in, false);
/* 583 */         in = NIOUtilities.allocate(this.headerLength - 10);
/*     */       } 
/* 585 */       in.limit(this.headerLength - 10);
/* 586 */       in.position(0);
/* 587 */       read(in, channel);
/* 588 */       in.position(0);
/* 592 */       this.recordLength = in.get() & 0xFF | (in.get() & 0xFF) << 8;
/* 595 */       in.position(in.position() + 20);
/* 598 */       this.fieldCnt = (this.headerLength - 32 - 1) / 32;
/* 602 */       List<DbaseField> lfields = new ArrayList();
/* 603 */       for (int i = 0; i < this.fieldCnt; i++) {
/* 604 */         DbaseField field = new DbaseField();
/* 607 */         byte[] buffer = new byte[11];
/* 608 */         in.get(buffer);
/* 609 */         String name = new String(buffer, charset.name());
/* 610 */         int nullPoint = name.indexOf(false);
/* 611 */         if (nullPoint != -1)
/* 612 */           name = name.substring(0, nullPoint); 
/* 614 */         field.fieldName = name.trim();
/* 617 */         field.fieldType = (char)in.get();
/* 620 */         field.fieldDataAddress = in.getInt();
/* 623 */         int length = in.get();
/* 624 */         if (length < 0)
/* 625 */           length += 256; 
/* 627 */         field.fieldLength = length;
/* 629 */         if (length > this.largestFieldSize)
/* 630 */           this.largestFieldSize = length; 
/* 634 */         field.decimalCount = in.get();
/* 638 */         in.position(in.position() + 14);
/* 643 */         if (field.fieldLength > 0)
/* 644 */           lfields.add(field); 
/*     */       } 
/* 650 */       in.position(in.position() + 1);
/* 652 */       this.fields = new DbaseField[lfields.size()];
/* 653 */       this.fields = lfields.<DbaseField>toArray(this.fields);
/*     */     } finally {
/* 655 */       NIOUtilities.clean(in, false);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void readHeader(ByteBuffer in) throws IOException {
/* 672 */     in.order(ByteOrder.LITTLE_ENDIAN);
/* 675 */     byte magic = in.get();
/* 676 */     if (magic != 3)
/* 677 */       throw new IOException("Unsupported DBF file Type " + Integer.toHexString(magic)); 
/* 682 */     int tempUpdateYear = in.get();
/* 683 */     int tempUpdateMonth = in.get();
/* 684 */     int tempUpdateDay = in.get();
/* 686 */     if (tempUpdateYear > 90) {
/* 687 */       tempUpdateYear += 1900;
/*     */     } else {
/* 689 */       tempUpdateYear += 2000;
/*     */     } 
/* 691 */     Calendar c = Calendar.getInstance();
/* 692 */     c.set(1, tempUpdateYear);
/* 693 */     c.set(2, tempUpdateMonth - 1);
/* 694 */     c.set(5, tempUpdateDay);
/* 695 */     this.date = c.getTime();
/* 698 */     this.recordCnt = in.getInt();
/* 703 */     this.headerLength = in.get() & 0xFF | (in.get() & 0xFF) << 8;
/* 706 */     if (this.headerLength > in.capacity())
/* 707 */       throw new IllegalArgumentException("The contract says the buffer should be long enough to fit all the header!"); 
/* 712 */     this.recordLength = in.get() & 0xFF | (in.get() & 0xFF) << 8;
/* 715 */     in.position(in.position() + 20);
/* 718 */     this.fieldCnt = (this.headerLength - 32 - 1) / 32;
/* 722 */     List<DbaseField> lfields = new ArrayList();
/* 723 */     for (int i = 0; i < this.fieldCnt; i++) {
/* 724 */       DbaseField field = new DbaseField();
/* 727 */       byte[] buffer = new byte[11];
/* 728 */       in.get(buffer);
/* 729 */       String name = new String(buffer);
/* 730 */       int nullPoint = name.indexOf(false);
/* 731 */       if (nullPoint != -1)
/* 732 */         name = name.substring(0, nullPoint); 
/* 734 */       field.fieldName = name.trim();
/* 737 */       field.fieldType = (char)in.get();
/* 740 */       field.fieldDataAddress = in.getInt();
/* 743 */       int length = in.get();
/* 744 */       if (length < 0)
/* 745 */         length += 256; 
/* 747 */       field.fieldLength = length;
/* 749 */       if (length > this.largestFieldSize)
/* 750 */         this.largestFieldSize = length; 
/* 754 */       field.decimalCount = in.get();
/* 758 */       in.position(in.position() + 14);
/* 763 */       if (field.fieldLength > 0)
/* 764 */         lfields.add(field); 
/*     */     } 
/* 770 */     in.position(in.position() + 1);
/* 772 */     this.fields = new DbaseField[lfields.size()];
/* 773 */     this.fields = lfields.<DbaseField>toArray(this.fields);
/*     */   }
/*     */   
/*     */   public int getLargestFieldSize() {
/* 782 */     return this.largestFieldSize;
/*     */   }
/*     */   
/*     */   public void setNumRecords(int inNumRecords) {
/* 792 */     this.recordCnt = inNumRecords;
/*     */   }
/*     */   
/*     */   public void writeHeader(WritableByteChannel out) throws IOException {
/* 807 */     if (this.headerLength == -1)
/* 808 */       this.headerLength = 33; 
/* 810 */     ByteBuffer buffer = NIOUtilities.allocate(this.headerLength);
/*     */     try {
/* 812 */       buffer.order(ByteOrder.LITTLE_ENDIAN);
/* 815 */       buffer.put((byte)3);
/* 818 */       Calendar c = Calendar.getInstance();
/* 819 */       c.setTime(new Date());
/* 820 */       buffer.put((byte)(c.get(1) % 100));
/* 821 */       buffer.put((byte)(c.get(2) + 1));
/* 822 */       buffer.put((byte)c.get(5));
/* 825 */       buffer.putInt(this.recordCnt);
/* 828 */       buffer.putShort((short)this.headerLength);
/* 831 */       buffer.putShort((short)this.recordLength);
/* 835 */       buffer.position(buffer.position() + 20);
/* 838 */       int tempOffset = 0;
/* 839 */       for (int i = 0; i < this.fields.length; i++) {
/* 842 */         for (int j = 0; j < 11; j++) {
/* 843 */           if ((this.fields[i]).fieldName.length() > j) {
/* 844 */             buffer.put((byte)(this.fields[i]).fieldName.charAt(j));
/*     */           } else {
/* 846 */             buffer.put((byte)0);
/*     */           } 
/*     */         } 
/* 851 */         buffer.put((byte)(this.fields[i]).fieldType);
/* 854 */         buffer.putInt(tempOffset);
/* 855 */         tempOffset += (this.fields[i]).fieldLength;
/* 858 */         buffer.put((byte)(this.fields[i]).fieldLength);
/* 861 */         buffer.put((byte)(this.fields[i]).decimalCount);
/* 865 */         buffer.position(buffer.position() + 14);
/*     */       } 
/* 869 */       buffer.put((byte)13);
/* 871 */       buffer.position(0);
/* 873 */       int r = buffer.remaining();
/* 874 */       while ((r -= out.write(buffer)) > 0);
/*     */     } finally {
/* 878 */       NIOUtilities.clean(buffer, false);
/*     */     } 
/*     */   }
/*     */   
/*     */   public String toString() {
/* 888 */     StringBuffer fs = new StringBuffer();
/* 889 */     for (int i = 0, ii = this.fields.length; i < ii; i++) {
/* 890 */       DbaseField f = this.fields[i];
/* 891 */       fs.append(f.fieldName + " " + f.fieldType + " " + f.fieldLength + " " + f.decimalCount + " " + f.fieldDataAddress + "\n");
/*     */     } 
/* 895 */     return "DB3 Header\nDate : " + this.date + "\n" + "Records : " + this.recordCnt + "\n" + "Fields : " + this.fieldCnt + "\n" + fs;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\shapefile\dbf\DbaseFileHeader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */