/*     */ package org.geotools.data.shapefile.index.quadtree.fs;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.channels.ReadableByteChannel;
/*     */ import java.nio.charset.Charset;
/*     */ import java.util.logging.Logger;
/*     */ import org.geotools.data.shapefile.index.quadtree.StoreException;
/*     */ import org.geotools.util.logging.Logging;
/*     */ 
/*     */ public class IndexHeader {
/*     */   public static final byte LSB_ORDER = -1;
/*     */   
/*     */   public static final byte MSB_ORDER = -2;
/*     */   
/*     */   public static final byte NATIVE_ORDER = 0;
/*     */   
/*     */   public static final byte NEW_LSB_ORDER = 1;
/*     */   
/*     */   public static final byte NEW_MSB_ORDER = 2;
/*     */   
/*     */   private static final String SIGNATURE = "SQT";
/*     */   
/*     */   private static final byte VERSION = 1;
/*     */   
/*  43 */   private static final byte[] RESERVED = new byte[] { 0, 0, 0 };
/*     */   
/*  44 */   private static final Logger LOGGER = Logging.getLogger("org.geotools.index.quadtree");
/*     */   
/*     */   private byte byteOrder;
/*     */   
/*     */   public IndexHeader(byte byteOrder) {
/*  49 */     this.byteOrder = byteOrder;
/*     */   }
/*     */   
/*     */   public IndexHeader(ReadableByteChannel channel) throws IOException, StoreException {
/*  62 */     ByteBuffer buf = ByteBuffer.allocate(8);
/*  64 */     channel.read(buf);
/*  65 */     buf.flip();
/*  67 */     byte[] tmp = new byte[3];
/*  68 */     buf.get(tmp);
/*  70 */     String s = new String(tmp, "US-ASCII");
/*  72 */     if (!s.equals("SQT")) {
/*     */       boolean lsb;
/*  74 */       LOGGER.warning("Old qix file format; this file format is deprecated; It is strongly recommended to regenerate it in new format.");
/*  78 */       buf.position(0);
/*  79 */       tmp = buf.array();
/*  83 */       if (tmp[4] == 0 && tmp[5] == 0 && tmp[6] == 0 && tmp[7] == 0) {
/*  85 */         lsb = (tmp[0] != 0 || tmp[1] != 0);
/*     */       } else {
/*  87 */         lsb = (tmp[4] != 0 || tmp[5] != 0);
/*     */       } 
/*  90 */       this.byteOrder = lsb ? -1 : -2;
/*     */     } else {
/*  92 */       this.byteOrder = buf.get();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void writeTo(ByteBuffer buf) {
/*  97 */     Charset charSet = Charset.forName("US-ASCII");
/*  99 */     ByteBuffer tmp = charSet.encode("SQT");
/* 100 */     tmp.position(0);
/* 101 */     buf.put(tmp);
/* 102 */     buf.put(this.byteOrder);
/* 103 */     buf.put((byte)1);
/* 104 */     buf.put(RESERVED);
/*     */   }
/*     */   
/*     */   public byte getByteOrder() {
/* 113 */     return this.byteOrder;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\shapefile\index\quadtree\fs\IndexHeader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */