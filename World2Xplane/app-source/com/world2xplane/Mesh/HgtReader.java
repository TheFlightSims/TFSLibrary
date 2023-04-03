/*     */ package com.world2xplane.Mesh;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.IOException;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.ByteOrder;
/*     */ import java.nio.ShortBuffer;
/*     */ import java.nio.channels.FileChannel;
/*     */ import math.geom2d.Point2D;
/*     */ 
/*     */ public class HgtReader {
/*     */   private static final int SECONDS_PER_MINUTE = 60;
/*     */   
/*     */   public static final String HGT_EXT = ".hgt";
/*     */   
/*     */   public static final int HGT_RES = 3;
/*     */   
/*     */   public static final int HGT_ROW_LENGTH = 1201;
/*     */   
/*     */   public static final int HGT_VOID = -32768;
/*     */   
/*     */   private final File file;
/*     */   
/*     */   private final ShortBuffer data;
/*     */   
/*     */   private final long fileLength;
/*     */   
/*     */   public HgtReader(File file) throws Exception {
/*  49 */     this.file = file;
/*  50 */     this.fileLength = file.length();
/*  51 */     this.data = readHgtFile(file.getAbsolutePath());
/*     */   }
/*     */   
/*     */   public double getElevationFromHgt(Point2D coor) {
/*  56 */     return readElevation(coor);
/*     */   }
/*     */   
/*     */   private ShortBuffer readHgtFile(String file) throws Exception {
/*  62 */     FileChannel fc = null;
/*  63 */     ShortBuffer sb = null;
/*     */     try {
/*  66 */       fc = (new FileInputStream(file)).getChannel();
/*  69 */       ByteBuffer bb = ByteBuffer.allocateDirect((int)fc.size());
/*  70 */       for (; bb.remaining() > 0; fc.read(bb));
/*  72 */       bb.flip();
/*  74 */       sb = bb.order(ByteOrder.BIG_ENDIAN).asShortBuffer();
/*     */     } finally {
/*  76 */       if (fc != null)
/*  76 */         fc.close(); 
/*     */     } 
/*  79 */     return sb;
/*     */   }
/*     */   
/*     */   public double readElevation(Point2D coor) {
/*  93 */     ElevationTile elevationTile = new ElevationTile();
/*     */     try {
/*  95 */       return elevationTile.getElevationFor(Double.valueOf(coor.x()), Double.valueOf(coor.y())).doubleValue();
/*  96 */     } catch (IOException e) {
/*  97 */       e.printStackTrace();
/*  98 */       return 0.0D;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static String getHgtFileName(Point2D latLon) {
/* 150 */     int lat = (int)latLon.y();
/* 151 */     int lon = (int)latLon.x() - 1;
/* 153 */     String latPref = "N";
/* 154 */     if (lat < 0)
/* 154 */       latPref = "S"; 
/* 156 */     String lonPref = "E";
/* 157 */     if (lon < 0)
/* 158 */       lonPref = "W"; 
/* 161 */     return String.format("%s%02d%s%03d%s", new Object[] { latPref, Integer.valueOf(lat), lonPref, Integer.valueOf(Math.abs(lon)), ".hgt" });
/*     */   }
/*     */   
/*     */   public static double frac(double d) {
/* 169 */     long iPart = (long)d;
/* 170 */     double fPart = d - iPart;
/* 171 */     return fPart;
/*     */   }
/*     */   
/*     */   public class ElevationTile {
/*     */     private static final int SRTM3_INTERVALS = 1200;
/*     */     
/*     */     private static final int SRTM3_FILE_SIZE = 2884802;
/*     */     
/*     */     private static final int SRTM1_INTERVALS = 3600;
/*     */     
/*     */     public static final int SRTM1_FILE_SIZE = 25934402;
/*     */     
/*     */     private static final int INVALID_VALUE_LIMIT = -15000;
/*     */     
/*     */     private int getIntervalCount() throws IOException {
/* 185 */       if (HgtReader.this.fileLength == 2884802L)
/* 186 */         return 1200; 
/* 187 */       if (HgtReader.this.fileLength == 25934402L)
/* 188 */         return 3600; 
/* 190 */       throw new IOException("Elevation tile " + HgtReader.this.file + " has invalid size " + HgtReader.this.fileLength);
/*     */     }
/*     */     
/*     */     private double calculateElevation(double dHeight12, double dLength12, double dDiff) {
/* 203 */       return dHeight12 * dDiff / dLength12;
/*     */     }
/*     */     
/*     */     public Double getElevationFor(Double longitude, Double latitude) throws IOException {
/* 207 */       if (longitude == null || latitude == null)
/* 208 */         return null; 
/* 211 */       int longitudeAsInt = longitude.intValue();
/* 212 */       int latitudeAsInt = latitude.intValue();
/* 214 */       if (longitude.doubleValue() < 0.0D) {
/* 215 */         longitudeAsInt = (longitudeAsInt - 1) * -1;
/* 216 */         longitude = Double.valueOf(longitudeAsInt + longitude.doubleValue() + longitudeAsInt);
/*     */       } 
/* 219 */       if (latitude.doubleValue() < 0.0D) {
/* 220 */         latitudeAsInt = (latitudeAsInt - 1) * -1;
/* 221 */         latitude = Double.valueOf(latitudeAsInt + latitude.doubleValue() + latitudeAsInt);
/*     */       } 
/* 224 */       int intervalCount = getIntervalCount();
/* 225 */       int longitudeIntervalIndex = (int)((longitude.doubleValue() - longitudeAsInt) * intervalCount);
/* 226 */       int latitudeIntervalIndex = (int)((latitude.doubleValue() - latitudeAsInt) * intervalCount);
/* 228 */       if (longitudeIntervalIndex >= intervalCount)
/* 229 */         longitudeIntervalIndex = intervalCount - 1; 
/* 232 */       if (latitudeIntervalIndex >= intervalCount)
/* 233 */         latitudeIntervalIndex = intervalCount - 1; 
/* 236 */       double dOffLon = longitude.doubleValue() - longitudeAsInt;
/* 237 */       double dOffLat = latitude.doubleValue() - latitudeAsInt;
/* 245 */       int pos = (intervalCount - latitudeIntervalIndex - 1) * (intervalCount + 1) + longitudeIntervalIndex;
/* 248 */       double dLeftTop = HgtReader.this.data.get(pos);
/* 250 */       pos = (intervalCount - latitudeIntervalIndex) * (intervalCount + 1) + longitudeIntervalIndex;
/* 254 */       double dLeftBottom = HgtReader.this.data.get(pos);
/* 256 */       pos = (intervalCount - latitudeIntervalIndex - 1) * (intervalCount + 1) + longitudeIntervalIndex + 1;
/* 260 */       double dRightTop = HgtReader.this.data.get(pos);
/* 262 */       pos = (intervalCount - latitudeIntervalIndex) * (intervalCount + 1) + longitudeIntervalIndex + 1;
/* 265 */       double dRightBottom = HgtReader.this.data.get(pos);
/* 268 */       if (dLeftTop < -15000.0D || dLeftBottom < -15000.0D || dRightTop < -15000.0D || dRightBottom < -15000.0D)
/* 270 */         return null; 
/* 274 */       double dDeltaLon = dOffLon - longitudeIntervalIndex * 1.0D / intervalCount;
/* 276 */       double dDeltaLat = dOffLat - latitudeIntervalIndex * 1.0D / intervalCount;
/* 279 */       double dLonHeightLeft = dLeftBottom - calculateElevation(dLeftBottom - dLeftTop, 1.0D / intervalCount, dDeltaLat);
/* 281 */       double dLonHeightRight = dRightBottom - calculateElevation(dRightBottom - dRightTop, 1.0D / intervalCount, dDeltaLat);
/* 284 */       double dElevation = dLonHeightLeft - calculateElevation(dLonHeightLeft - dLonHeightRight, 1.0D / intervalCount, dDeltaLon);
/* 286 */       return Double.valueOf(dElevation + 0.5D);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\Mesh\HgtReader.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */