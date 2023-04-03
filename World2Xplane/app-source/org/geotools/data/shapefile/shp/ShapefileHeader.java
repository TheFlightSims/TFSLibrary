/*     */ package org.geotools.data.shapefile.shp;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.ByteOrder;
/*     */ 
/*     */ public class ShapefileHeader {
/*     */   public static final int MAGIC = 9994;
/*     */   
/*     */   public static final int VERSION = 1000;
/*     */   
/*  42 */   private int fileCode = -1;
/*     */   
/*  44 */   private int fileLength = -1;
/*     */   
/*  46 */   private int version = -1;
/*     */   
/*  48 */   private ShapeType shapeType = ShapeType.UNDEFINED;
/*     */   
/*     */   private double minX;
/*     */   
/*     */   private double maxX;
/*     */   
/*     */   private double minY;
/*     */   
/*     */   private double maxY;
/*     */   
/*     */   private void checkMagic(boolean strict) throws IOException {
/*  59 */     if (this.fileCode != 9994) {
/*  60 */       String message = "Wrong magic number, expected 9994, got " + this.fileCode;
/*  61 */       if (!strict) {
/*  62 */         System.err.println(message);
/*     */       } else {
/*  64 */         throw new IOException(message);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void checkVersion(boolean strict) throws IOException {
/*  70 */     if (this.version != 1000) {
/*  71 */       String message = "Wrong version, expected 9994, got " + this.version;
/*  72 */       if (!strict) {
/*  73 */         System.err.println(message);
/*     */       } else {
/*  75 */         throw new IOException(message);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void read(ByteBuffer file, boolean strict) throws IOException {
/*  81 */     file.order(ByteOrder.BIG_ENDIAN);
/*  82 */     this.fileCode = file.getInt();
/*  84 */     checkMagic(strict);
/*  87 */     file.position(file.position() + 20);
/*  89 */     this.fileLength = file.getInt();
/*  91 */     file.order(ByteOrder.LITTLE_ENDIAN);
/*  92 */     this.version = file.getInt();
/*  93 */     checkVersion(strict);
/*  94 */     this.shapeType = ShapeType.forID(file.getInt());
/*  96 */     this.minX = file.getDouble();
/*  97 */     this.minY = file.getDouble();
/*  98 */     this.maxX = file.getDouble();
/*  99 */     this.maxY = file.getDouble();
/* 102 */     file.order(ByteOrder.BIG_ENDIAN);
/* 104 */     file.position(file.position() + 32);
/*     */   }
/*     */   
/*     */   public void write(ByteBuffer file, ShapeType type, int numGeoms, int length, double minX, double minY, double maxX, double maxY) throws IOException {
/* 110 */     file.order(ByteOrder.BIG_ENDIAN);
/* 112 */     file.putInt(9994);
/*     */     int i;
/* 114 */     for (i = 0; i < 5; i++)
/* 115 */       file.putInt(0); 
/* 118 */     file.putInt(length);
/* 120 */     file.order(ByteOrder.LITTLE_ENDIAN);
/* 122 */     file.putInt(1000);
/* 123 */     file.putInt(type.id);
/* 126 */     file.putDouble(minX);
/* 127 */     file.putDouble(minY);
/* 128 */     file.putDouble(maxX);
/* 129 */     file.putDouble(maxY);
/* 132 */     file.order(ByteOrder.BIG_ENDIAN);
/* 133 */     for (i = 0; i < 8; i++)
/* 134 */       file.putInt(0); 
/*     */   }
/*     */   
/*     */   public ShapeType getShapeType() {
/* 139 */     return this.shapeType;
/*     */   }
/*     */   
/*     */   public int getVersion() {
/* 143 */     return this.version;
/*     */   }
/*     */   
/*     */   public int getFileLength() {
/* 147 */     return this.fileLength;
/*     */   }
/*     */   
/*     */   public double minX() {
/* 151 */     return this.minX;
/*     */   }
/*     */   
/*     */   public double minY() {
/* 155 */     return this.minY;
/*     */   }
/*     */   
/*     */   public double maxX() {
/* 159 */     return this.maxX;
/*     */   }
/*     */   
/*     */   public double maxY() {
/* 163 */     return this.maxY;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 167 */     String res = new String("ShapeFileHeader[ size " + this.fileLength + " version " + this.version + " shapeType " + this.shapeType + " bounds " + this.minX + "," + this.minY + "," + this.maxX + "," + this.maxY + " ]");
/* 170 */     return res;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\shapefile\shp\ShapefileHeader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */