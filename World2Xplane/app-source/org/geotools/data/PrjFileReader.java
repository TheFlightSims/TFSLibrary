/*     */ package org.geotools.data;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.ByteOrder;
/*     */ import java.nio.CharBuffer;
/*     */ import java.nio.channels.FileChannel;
/*     */ import java.nio.channels.ReadableByteChannel;
/*     */ import java.nio.charset.Charset;
/*     */ import java.nio.charset.CharsetDecoder;
/*     */ import org.geotools.factory.Hints;
/*     */ import org.geotools.referencing.ReferencingFactoryFinder;
/*     */ import org.geotools.resources.NIOUtilities;
/*     */ import org.opengis.referencing.FactoryException;
/*     */ import org.opengis.referencing.crs.CoordinateReferenceSystem;
/*     */ 
/*     */ public class PrjFileReader {
/*     */   ByteBuffer buffer;
/*     */   
/*     */   ReadableByteChannel channel;
/*     */   
/*     */   CharBuffer charBuffer;
/*     */   
/*     */   CharsetDecoder decoder;
/*     */   
/*     */   CoordinateReferenceSystem crs;
/*     */   
/*     */   public PrjFileReader(ReadableByteChannel channel) throws IOException, FactoryException {
/*  63 */     this(channel, null);
/*     */   }
/*     */   
/*     */   public PrjFileReader(ReadableByteChannel channel, Hints hints) throws IOException, FactoryException {
/*     */     try {
/*  79 */       Charset chars = Charset.forName("ISO-8859-1");
/*  80 */       this.decoder = chars.newDecoder();
/*  81 */       this.channel = channel;
/*  83 */       init();
/*  86 */       this.decoder.decode(this.buffer, this.charBuffer, true);
/*  87 */       this.buffer.limit(this.buffer.capacity());
/*  88 */       this.charBuffer.flip();
/*  89 */       this.crs = ReferencingFactoryFinder.getCRSFactory(hints).createFromWKT(this.charBuffer.toString());
/*     */     } finally {
/*  92 */       close();
/*     */     } 
/*     */   }
/*     */   
/*     */   public CoordinateReferenceSystem getCoodinateSystem() {
/* 102 */     return this.crs;
/*     */   }
/*     */   
/*     */   public CoordinateReferenceSystem getCoordinateReferenceSystem() {
/* 111 */     return this.crs;
/*     */   }
/*     */   
/*     */   private int fill(ByteBuffer buffer, ReadableByteChannel channel) throws IOException {
/* 116 */     int r = buffer.remaining();
/* 119 */     while (buffer.remaining() > 0 && r != -1)
/* 120 */       r = channel.read(buffer); 
/* 122 */     if (r == -1)
/* 123 */       buffer.limit(buffer.position()); 
/* 125 */     return r;
/*     */   }
/*     */   
/*     */   private void init() throws IOException {
/* 131 */     if (this.channel instanceof FileChannel) {
/* 132 */       FileChannel fc = (FileChannel)this.channel;
/* 133 */       this.buffer = fc.map(FileChannel.MapMode.READ_ONLY, 0L, fc.size());
/* 134 */       this.buffer.position((int)fc.position());
/*     */     } else {
/* 138 */       int size = 8192;
/* 142 */       this.buffer = ByteBuffer.allocateDirect(size);
/* 144 */       fill(this.buffer, this.channel);
/* 145 */       this.buffer.flip();
/*     */     } 
/* 149 */     this.buffer.order(ByteOrder.LITTLE_ENDIAN);
/* 151 */     this.charBuffer = CharBuffer.allocate(8192);
/* 152 */     Charset chars = Charset.forName("ISO-8859-1");
/* 153 */     this.decoder = chars.newDecoder();
/*     */   }
/*     */   
/*     */   public void close() throws IOException {
/* 163 */     if (this.buffer != null) {
/* 164 */       NIOUtilities.clean(this.buffer);
/* 165 */       this.buffer = null;
/*     */     } 
/* 167 */     if (this.channel.isOpen())
/* 168 */       this.channel.close(); 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\PrjFileReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */