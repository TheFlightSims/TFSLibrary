/*     */ package org.geotools.data.shapefile.dbf;
/*     */ 
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.DataInputStream;
/*     */ import java.io.IOException;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.ByteOrder;
/*     */ import java.nio.channels.FileChannel;
/*     */ import java.nio.channels.ReadableByteChannel;
/*     */ import java.nio.charset.Charset;
/*     */ import java.util.Arrays;
/*     */ import java.util.Calendar;
/*     */ import java.util.Locale;
/*     */ import java.util.TimeZone;
/*     */ import org.geotools.data.shapefile.files.FileReader;
/*     */ import org.geotools.data.shapefile.files.ShpFileType;
/*     */ import org.geotools.data.shapefile.files.ShpFiles;
/*     */ import org.geotools.data.shapefile.files.StreamLogging;
/*     */ import org.geotools.resources.NIOUtilities;
/*     */ 
/*     */ public class DbaseFileReader implements FileReader {
/*     */   DbaseFileHeader header;
/*     */   
/*     */   ByteBuffer buffer;
/*     */   
/*     */   ReadableByteChannel channel;
/*     */   
/*     */   byte[] bytes;
/*     */   
/*     */   char[] fieldTypes;
/*     */   
/*     */   int[] fieldLengths;
/*     */   
/*     */   int[] fieldOffsets;
/*     */   
/*     */   public final class Row {
/*     */     boolean deleted;
/*     */     
/*     */     public Object read(int column) throws IOException {
/*  79 */       int offset = DbaseFileReader.this.fieldOffsets[column];
/*  80 */       return DbaseFileReader.this.readObject(offset, column);
/*     */     }
/*     */     
/*     */     public String toString() {
/*  84 */       StringBuffer ret = new StringBuffer("DBF Row - ");
/*  85 */       for (int i = 0; i < DbaseFileReader.this.header.getNumFields(); i++) {
/*  86 */         ret.append(DbaseFileReader.this.header.getFieldName(i)).append(": \"");
/*     */         try {
/*  88 */           ret.append(read(i));
/*  89 */         } catch (IOException ioe) {
/*  90 */           ret.append(ioe.getMessage());
/*     */         } 
/*  92 */         ret.append("\" ");
/*     */       } 
/*  94 */       return ret.toString();
/*     */     }
/*     */     
/*     */     public boolean isDeleted() {
/*  98 */       return this.deleted;
/*     */     }
/*     */   }
/*     */   
/* 116 */   int cnt = 1;
/*     */   
/*     */   Row row;
/*     */   
/*     */   protected boolean useMemoryMappedBuffer;
/*     */   
/*     */   protected boolean randomAccessEnabled;
/*     */   
/* 124 */   protected long currentOffset = 0L;
/*     */   
/* 125 */   private final StreamLogging streamLogger = new StreamLogging("Dbase File Reader");
/*     */   
/*     */   private Charset stringCharset;
/*     */   
/*     */   private boolean oneBytePerChar;
/*     */   
/*     */   private Calendar calendar;
/*     */   
/* 133 */   private final long MILLISECS_PER_DAY = 86400000L;
/*     */   
/*     */   public DbaseFileReader(ShpFiles shapefileFiles, boolean useMemoryMappedBuffer, Charset charset, TimeZone timeZone) throws IOException {
/* 146 */     ReadableByteChannel dbfChannel = shapefileFiles.getReadChannel(ShpFileType.DBF, this);
/* 147 */     init(dbfChannel, useMemoryMappedBuffer, charset, timeZone);
/*     */   }
/*     */   
/*     */   public DbaseFileReader(ShpFiles shapefileFiles, boolean useMemoryMappedBuffer, Charset charset) throws IOException {
/* 152 */     ReadableByteChannel dbfChannel = shapefileFiles.getReadChannel(ShpFileType.DBF, this);
/* 153 */     init(dbfChannel, useMemoryMappedBuffer, charset, null);
/*     */   }
/*     */   
/*     */   public DbaseFileReader(ReadableByteChannel readChannel, boolean useMemoryMappedBuffer, Charset charset) throws IOException {
/* 158 */     init(readChannel, useMemoryMappedBuffer, charset, null);
/*     */   }
/*     */   
/*     */   public DbaseFileReader(ReadableByteChannel readChannel, boolean useMemoryMappedBuffer, Charset charset, TimeZone timeZone) throws IOException {
/* 163 */     init(readChannel, useMemoryMappedBuffer, charset, timeZone);
/*     */   }
/*     */   
/*     */   private void init(ReadableByteChannel dbfChannel, boolean useMemoryMappedBuffer, Charset charset, TimeZone timeZone) throws IOException {
/* 168 */     this.channel = dbfChannel;
/* 169 */     this.stringCharset = (charset == null) ? Charset.defaultCharset() : charset;
/* 170 */     TimeZone calTimeZone = (timeZone == null) ? TimeZone.getDefault() : timeZone;
/* 171 */     this.calendar = Calendar.getInstance(calTimeZone, Locale.US);
/* 173 */     this.useMemoryMappedBuffer = useMemoryMappedBuffer;
/* 174 */     this.randomAccessEnabled = this.channel instanceof FileChannel;
/* 175 */     this.streamLogger.open();
/* 176 */     this.header = new DbaseFileHeader();
/* 180 */     if (this.channel instanceof FileChannel && this.useMemoryMappedBuffer) {
/* 181 */       FileChannel fc = (FileChannel)this.channel;
/* 182 */       if (fc.size() - fc.position() < 2147483647L) {
/* 183 */         this.buffer = fc.map(FileChannel.MapMode.READ_ONLY, 0L, fc.size());
/*     */       } else {
/* 185 */         this.buffer = fc.map(FileChannel.MapMode.READ_ONLY, 0L, 2147483647L);
/*     */       } 
/* 187 */       this.buffer.position((int)fc.position());
/* 188 */       this.header.readHeader(this.buffer);
/* 190 */       this.currentOffset = 0L;
/*     */     } else {
/* 193 */       this.useMemoryMappedBuffer = false;
/* 194 */       this.header.readHeader(this.channel, charset);
/* 198 */       this.buffer = NIOUtilities.allocate(this.header.getRecordLength());
/* 200 */       fill(this.buffer, this.channel);
/* 201 */       this.buffer.flip();
/* 202 */       this.currentOffset = this.header.getHeaderLength();
/*     */     } 
/* 206 */     this.buffer.order(ByteOrder.LITTLE_ENDIAN);
/* 209 */     this.fieldTypes = new char[this.header.getNumFields()];
/* 210 */     this.fieldLengths = new int[this.header.getNumFields()];
/* 211 */     this.fieldOffsets = new int[this.header.getNumFields()];
/* 212 */     for (int i = 0, ii = this.header.getNumFields(); i < ii; i++) {
/* 213 */       this.fieldTypes[i] = this.header.getFieldType(i);
/* 214 */       this.fieldLengths[i] = this.header.getFieldLength(i);
/* 215 */       if (i > 0)
/* 216 */         this.fieldOffsets[i] = this.fieldOffsets[i - 1] + this.header.getFieldLength(i - 1); 
/*     */     } 
/* 218 */     this.bytes = new byte[this.header.getRecordLength() - 1];
/* 222 */     String cname = this.stringCharset.name();
/* 223 */     this.oneBytePerChar = ("ISO-8859-1".equals(cname) || "US-ASCII".equals(cname));
/* 225 */     this.row = new Row();
/*     */   }
/*     */   
/*     */   protected int fill(ByteBuffer buffer, ReadableByteChannel channel) throws IOException {
/* 230 */     int r = buffer.remaining();
/* 233 */     while (buffer.remaining() > 0 && r != -1)
/* 234 */       r = channel.read(buffer); 
/* 236 */     if (r == -1)
/* 237 */       buffer.limit(buffer.position()); 
/* 239 */     return r;
/*     */   }
/*     */   
/*     */   private void bufferCheck() throws IOException {
/* 245 */     if (this.useMemoryMappedBuffer) {
/* 246 */       if (this.buffer.remaining() < this.header.getRecordLength()) {
/* 248 */         FileChannel fc = (FileChannel)this.channel;
/* 249 */         int position = this.buffer.position();
/* 250 */         if (fc.size() > (position + Integer.MAX_VALUE)) {
/* 251 */           this.currentOffset = position;
/*     */         } else {
/* 253 */           this.currentOffset = fc.size() - 2147483647L;
/*     */         } 
/* 255 */         NIOUtilities.clean(this.buffer);
/* 256 */         this.buffer = fc.map(FileChannel.MapMode.READ_ONLY, this.currentOffset, 2147483647L);
/* 258 */         this.buffer = ((FileChannel)this.channel).map(FileChannel.MapMode.READ_ONLY, this.buffer.position(), 2147483647L);
/*     */       } 
/* 260 */     } else if (this.buffer.remaining() < this.header.getRecordLength()) {
/* 261 */       this.currentOffset += this.buffer.position();
/* 262 */       this.buffer.compact();
/* 263 */       fill(this.buffer, this.channel);
/* 264 */       this.buffer.position(0);
/*     */     } 
/*     */   }
/*     */   
/*     */   public DbaseFileHeader getHeader() {
/* 275 */     return this.header;
/*     */   }
/*     */   
/*     */   public void close() throws IOException {
/* 285 */     if (this.channel != null && this.channel.isOpen()) {
/* 286 */       this.channel.close();
/* 287 */       this.streamLogger.close();
/*     */     } 
/* 289 */     if (this.buffer != null)
/* 290 */       NIOUtilities.clean(this.buffer, this.useMemoryMappedBuffer); 
/* 293 */     this.buffer = null;
/* 294 */     this.channel = null;
/* 295 */     this.bytes = null;
/* 296 */     this.header = null;
/* 297 */     this.row = null;
/*     */   }
/*     */   
/*     */   public boolean hasNext() {
/* 306 */     return (this.cnt < this.header.getNumRecords() + 1);
/*     */   }
/*     */   
/*     */   public Object[] readEntry() throws IOException {
/* 317 */     return readEntry(new Object[this.header.getNumFields()]);
/*     */   }
/*     */   
/*     */   public Row readRow() throws IOException {
/* 321 */     read();
/* 322 */     return this.row;
/*     */   }
/*     */   
/*     */   public void skip() throws IOException {
/* 332 */     boolean foundRecord = false;
/* 333 */     while (!foundRecord) {
/* 335 */       bufferCheck();
/* 338 */       char tempDeleted = (char)this.buffer.get();
/* 341 */       this.buffer.position(this.buffer.position() + this.header.getRecordLength() - 1);
/* 351 */       if (tempDeleted != '*')
/* 352 */         foundRecord = true; 
/*     */     } 
/* 355 */     this.cnt++;
/*     */   }
/*     */   
/*     */   public Object[] readEntry(Object[] entry, int offset) throws IOException {
/* 371 */     if (entry.length - offset < this.header.getNumFields())
/* 372 */       throw new ArrayIndexOutOfBoundsException(); 
/* 375 */     read();
/* 376 */     if (this.row.deleted)
/* 377 */       return null; 
/* 381 */     int numFields = this.header.getNumFields();
/* 383 */     for (int j = 0; j < numFields; j++)
/* 384 */       entry[j + offset] = readObject(this.fieldOffsets[j], j); 
/* 387 */     return entry;
/*     */   }
/*     */   
/*     */   public Object readField(int fieldNum) throws IOException {
/* 400 */     return readObject(this.fieldOffsets[fieldNum], fieldNum);
/*     */   }
/*     */   
/*     */   public void transferTo(DbaseFileWriter writer) throws IOException {
/* 407 */     bufferCheck();
/* 408 */     this.buffer.limit(this.buffer.position() + this.header.getRecordLength());
/* 409 */     writer.channel.write(this.buffer);
/* 410 */     this.buffer.limit(this.buffer.capacity());
/* 412 */     this.cnt++;
/*     */   }
/*     */   
/*     */   public void read() throws IOException {
/* 421 */     boolean foundRecord = false;
/* 422 */     while (!foundRecord) {
/* 424 */       bufferCheck();
/* 427 */       char deleted = (char)this.buffer.get();
/* 428 */       this.row.deleted = (deleted == '*');
/* 430 */       this.buffer.limit(this.buffer.position() + this.header.getRecordLength() - 1);
/* 431 */       this.buffer.get(this.bytes);
/* 432 */       this.buffer.limit(this.buffer.capacity());
/* 434 */       foundRecord = true;
/*     */     } 
/* 437 */     this.cnt++;
/*     */   }
/*     */   
/*     */   public Object[] readEntry(Object[] entry) throws IOException {
/* 450 */     return readEntry(entry, 0);
/*     */   }
/*     */   
/*     */   private Object readObject(int fieldOffset, int fieldNum) throws IOException {
/* 454 */     char type = this.fieldTypes[fieldNum];
/* 455 */     int fieldLen = this.fieldLengths[fieldNum];
/* 456 */     Object object = null;
/* 457 */     if (fieldLen > 0) {
/*     */       char c;
/*     */       int i;
/*     */       String string;
/*     */       Class<Integer> clazz;
/*     */       Class<Long> clazz1;
/* 458 */       switch (type) {
/*     */         case 'L':
/*     */         case 'l':
/* 462 */           c = (char)this.bytes[fieldOffset];
/* 463 */           switch (c) {
/*     */             case 'T':
/*     */             case 'Y':
/*     */             case 't':
/*     */             case 'y':
/* 468 */               object = Boolean.TRUE;
/* 592 */               return object;
/*     */             case 'F':
/*     */             case 'N':
/*     */             case 'f':
/*     */             case 'n':
/*     */               object = Boolean.FALSE;
/* 592 */               return object;
/*     */           } 
/*     */           object = null;
/* 592 */           return object;
/*     */         case 'C':
/*     */         case 'c':
/*     */           if (this.bytes[fieldOffset] != 0)
/*     */             if (this.oneBytePerChar) {
/*     */               object = fastParse(this.bytes, fieldOffset, fieldLen).trim();
/*     */             } else {
/*     */               object = (new String(this.bytes, fieldOffset, fieldLen, this.stringCharset.name())).trim();
/*     */             }  
/* 592 */           return object;
/*     */         case 'D':
/*     */         case 'd':
/*     */           for (i = 0; i < 8; i++) {
/*     */             if (this.bytes[fieldOffset + i] != 48) {
/*     */               try {
/*     */                 String tempString = fastParse(this.bytes, fieldOffset, 4);
/*     */                 int tempYear = Integer.parseInt(tempString);
/*     */                 tempString = fastParse(this.bytes, fieldOffset + 4, 2);
/*     */                 int tempMonth = Integer.parseInt(tempString) - 1;
/*     */                 tempString = fastParse(this.bytes, fieldOffset + 6, 2);
/*     */                 int tempDay = Integer.parseInt(tempString);
/*     */                 this.calendar.clear();
/*     */                 this.calendar.set(1, tempYear);
/*     */                 this.calendar.set(2, tempMonth);
/*     */                 this.calendar.set(5, tempDay);
/*     */                 object = this.calendar.getTime();
/*     */               } catch (NumberFormatException nfe) {}
/*     */               break;
/*     */             } 
/*     */           } 
/* 592 */           return object;
/*     */         case '@':
/*     */           try {
/*     */             byte[] timestampBytes = { this.bytes[fieldOffset + 7], this.bytes[fieldOffset + 6], this.bytes[fieldOffset + 5], this.bytes[fieldOffset + 4], this.bytes[fieldOffset + 3], this.bytes[fieldOffset + 2], this.bytes[fieldOffset + 1], this.bytes[fieldOffset] };
/*     */             ByteArrayInputStream i_bytes = new ByteArrayInputStream(timestampBytes);
/*     */             DataInputStream i_stream = new DataInputStream(new BufferedInputStream(i_bytes));
/*     */             int time = i_stream.readInt();
/*     */             int days = i_stream.readInt();
/*     */             this.calendar.setTimeInMillis(days * 86400000L + DbaseFileHeader.MILLIS_SINCE_4713 + time);
/*     */             object = this.calendar.getTime();
/*     */           } catch (NumberFormatException nfe) {}
/* 592 */           return object;
/*     */         case 'N':
/*     */         case 'n':
/*     */           if (this.bytes[fieldOffset] == 42)
/* 592 */             return object; 
/*     */           string = fastParse(this.bytes, fieldOffset, fieldLen).trim();
/*     */           clazz = this.header.getFieldClass(fieldNum);
/*     */           if (clazz == Integer.class) {
/*     */             try {
/*     */               object = Integer.valueOf(Integer.parseInt(string));
/*     */             } catch (NumberFormatException e) {
/*     */               clazz1 = Long.class;
/*     */               if (clazz1 == Long.class) {
/*     */                 try {
/*     */                   object = Long.valueOf(Long.parseLong(string));
/*     */                 } catch (NumberFormatException e2) {}
/* 592 */                 return object;
/*     */               } 
/*     */             } 
/* 592 */             return object;
/*     */           } 
/*     */           if (clazz1 == Long.class) {
/*     */             try {
/*     */               object = Long.valueOf(Long.parseLong(string));
/*     */             } catch (NumberFormatException numberFormatException) {}
/* 592 */             return object;
/*     */           } 
/*     */         case 'F':
/*     */         case 'f':
/*     */           if (this.bytes[fieldOffset] != 42)
/*     */             try {
/*     */               object = Double.valueOf(Double.parseDouble(fastParse(this.bytes, fieldOffset, fieldLen)));
/*     */             } catch (NumberFormatException e) {
/*     */               object = new Double(0.0D);
/*     */             }  
/*     */       } 
/*     */       throw new IOException("Invalid field type : " + type);
/*     */     } 
/* 592 */     return object;
/*     */   }
/*     */   
/*     */   String fastParse(byte[] bytes, int fieldOffset, int fieldLen) {
/* 605 */     char[] chars = new char[fieldLen];
/* 606 */     for (int i = 0; i < fieldLen; i++)
/* 608 */       chars[i] = (char)(0xFF & bytes[fieldOffset + i]); 
/* 610 */     return new String(chars);
/*     */   }
/*     */   
/*     */   public static void main(String[] args) throws Exception {
/* 614 */     DbaseFileReader reader = new DbaseFileReader(new ShpFiles(args[0]), false, Charset.forName("ISO-8859-1"), null);
/* 616 */     System.out.println(reader.getHeader());
/* 617 */     int r = 0;
/* 618 */     while (reader.hasNext())
/* 619 */       System.out.println(++r + "," + Arrays.<Object>asList(reader.readEntry())); 
/* 622 */     reader.close();
/*     */   }
/*     */   
/*     */   public String id() {
/* 626 */     return getClass().getName();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\shapefile\dbf\DbaseFileReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */