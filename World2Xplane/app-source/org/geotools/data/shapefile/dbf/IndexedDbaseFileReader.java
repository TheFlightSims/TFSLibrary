/*     */ package org.geotools.data.shapefile.dbf;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.nio.channels.FileChannel;
/*     */ import java.nio.charset.Charset;
/*     */ import java.util.Arrays;
/*     */ import java.util.TimeZone;
/*     */ import org.geotools.data.shapefile.ShapefileDataStoreFactory;
/*     */ import org.geotools.data.shapefile.files.ShpFiles;
/*     */ import org.geotools.resources.NIOUtilities;
/*     */ 
/*     */ public class IndexedDbaseFileReader extends DbaseFileReader {
/*     */   public IndexedDbaseFileReader(ShpFiles shpFiles) throws IOException {
/*  76 */     this(shpFiles, false);
/*     */   }
/*     */   
/*     */   public IndexedDbaseFileReader(ShpFiles shpFiles, boolean useMemoryMappedBuffer) throws IOException {
/*  88 */     super(shpFiles, useMemoryMappedBuffer, (Charset)ShapefileDataStoreFactory.DBFCHARSET.getDefaultValue(), TimeZone.getDefault());
/*     */   }
/*     */   
/*     */   public IndexedDbaseFileReader(ShpFiles shpFiles, boolean useMemoryMappedBuffer, Charset stringCharset) throws IOException {
/*  94 */     super(shpFiles, useMemoryMappedBuffer, stringCharset, TimeZone.getDefault());
/*     */   }
/*     */   
/*     */   public IndexedDbaseFileReader(ShpFiles shpFiles, boolean useMemoryMappedBuffer, Charset stringCharset, TimeZone timeZone) throws IOException {
/*  99 */     super(shpFiles, useMemoryMappedBuffer, stringCharset, timeZone);
/*     */   }
/*     */   
/*     */   public void goTo(int recno) throws IOException, UnsupportedOperationException {
/* 104 */     if (this.randomAccessEnabled) {
/* 105 */       long newPosition = this.header.getHeaderLength() + this.header.getRecordLength() * (recno - 1);
/* 108 */       if (this.useMemoryMappedBuffer) {
/* 109 */         if (newPosition < this.currentOffset || this.currentOffset + this.buffer.limit() < newPosition + this.header.getRecordLength()) {
/* 112 */           NIOUtilities.clean(this.buffer);
/* 113 */           FileChannel fc = (FileChannel)this.channel;
/* 114 */           if (fc.size() > newPosition + 2147483647L) {
/* 115 */             this.currentOffset = newPosition;
/*     */           } else {
/* 117 */             this.currentOffset = fc.size() - 2147483647L;
/*     */           } 
/* 119 */           this.buffer = fc.map(FileChannel.MapMode.READ_ONLY, this.currentOffset, 2147483647L);
/* 120 */           this.buffer.position((int)(newPosition - this.currentOffset));
/*     */         } else {
/* 122 */           this.buffer.position((int)(newPosition - this.currentOffset));
/*     */         } 
/* 125 */       } else if (this.currentOffset <= newPosition && this.currentOffset + this.buffer.limit() >= newPosition) {
/* 127 */         this.buffer.position((int)(newPosition - this.currentOffset));
/*     */       } else {
/* 131 */         FileChannel fc = (FileChannel)this.channel;
/* 132 */         fc.position(newPosition);
/* 133 */         this.currentOffset = newPosition;
/* 134 */         this.buffer.limit(this.buffer.capacity());
/* 135 */         this.buffer.position(0);
/* 136 */         fill(this.buffer, fc);
/* 137 */         this.buffer.position(0);
/*     */       } 
/*     */     } else {
/* 141 */       throw new UnsupportedOperationException("Random access not enabled!");
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean IsRandomAccessEnabled() {
/* 147 */     return this.randomAccessEnabled;
/*     */   }
/*     */   
/*     */   public static void main(String[] args) throws Exception {
/* 151 */     IndexedDbaseFileReader reader = new IndexedDbaseFileReader(new ShpFiles(args[0]), false);
/* 152 */     System.out.println(reader.getHeader());
/* 153 */     int r = 0;
/* 154 */     while (reader.hasNext())
/* 155 */       System.out.println(++r + "," + Arrays.<Object>asList(reader.readEntry())); 
/* 157 */     reader.close();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\shapefile\dbf\IndexedDbaseFileReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */