/*     */ package au.com.objectix.jgridshift;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.RandomAccessFile;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class SubGrid implements Cloneable, Serializable {
/*     */   private static final int REC_SIZE = 16;
/*     */   
/*     */   private String subGridName;
/*     */   
/*     */   private String parentSubGridName;
/*     */   
/*     */   private String created;
/*     */   
/*     */   private String updated;
/*     */   
/*     */   private double minLat;
/*     */   
/*     */   private double maxLat;
/*     */   
/*     */   private double minLon;
/*     */   
/*     */   private double maxLon;
/*     */   
/*     */   private double latInterval;
/*     */   
/*     */   private double lonInterval;
/*     */   
/*     */   private int nodeCount;
/*     */   
/*     */   private int lonColumnCount;
/*     */   
/*     */   private int latRowCount;
/*     */   
/*     */   private float[] latShift;
/*     */   
/*     */   private float[] lonShift;
/*     */   
/*     */   private float[] latAccuracy;
/*     */   
/*     */   private float[] lonAccuracy;
/*     */   
/*     */   private RandomAccessFile raf;
/*     */   
/*     */   private long subGridOffset;
/*     */   
/*     */   boolean bigEndian;
/*     */   
/*     */   private SubGrid[] subGrid;
/*     */   
/*     */   public SubGrid(InputStream in, boolean bigEndian, boolean loadAccuracy) throws IOException {
/*  71 */     byte[] b8 = new byte[8];
/*  72 */     byte[] b4 = new byte[4];
/*  73 */     in.read(b8);
/*  74 */     in.read(b8);
/*  75 */     this.subGridName = (new String(b8)).trim();
/*  76 */     in.read(b8);
/*  77 */     in.read(b8);
/*  78 */     this.parentSubGridName = (new String(b8)).trim();
/*  79 */     in.read(b8);
/*  80 */     in.read(b8);
/*  81 */     this.created = new String(b8);
/*  82 */     in.read(b8);
/*  83 */     in.read(b8);
/*  84 */     this.updated = new String(b8);
/*  85 */     in.read(b8);
/*  86 */     in.read(b8);
/*  87 */     this.minLat = Util.getDouble(b8, bigEndian);
/*  88 */     in.read(b8);
/*  89 */     in.read(b8);
/*  90 */     this.maxLat = Util.getDouble(b8, bigEndian);
/*  91 */     in.read(b8);
/*  92 */     in.read(b8);
/*  93 */     this.minLon = Util.getDouble(b8, bigEndian);
/*  94 */     in.read(b8);
/*  95 */     in.read(b8);
/*  96 */     this.maxLon = Util.getDouble(b8, bigEndian);
/*  97 */     in.read(b8);
/*  98 */     in.read(b8);
/*  99 */     this.latInterval = Util.getDouble(b8, bigEndian);
/* 100 */     in.read(b8);
/* 101 */     in.read(b8);
/* 102 */     this.lonInterval = Util.getDouble(b8, bigEndian);
/* 103 */     this.lonColumnCount = 1 + (int)((this.maxLon - this.minLon) / this.lonInterval);
/* 104 */     this.latRowCount = 1 + (int)((this.maxLat - this.minLat) / this.latInterval);
/* 105 */     in.read(b8);
/* 106 */     in.read(b8);
/* 107 */     this.nodeCount = Util.getInt(b8, bigEndian);
/* 108 */     if (this.nodeCount != this.lonColumnCount * this.latRowCount)
/* 109 */       throw new IllegalStateException("SubGrid " + this.subGridName + " has inconsistent grid dimesions"); 
/* 111 */     this.latShift = new float[this.nodeCount];
/* 112 */     this.lonShift = new float[this.nodeCount];
/* 113 */     if (loadAccuracy) {
/* 114 */       this.latAccuracy = new float[this.nodeCount];
/* 115 */       this.lonAccuracy = new float[this.nodeCount];
/*     */     } 
/* 118 */     for (int i = 0; i < this.nodeCount; i++) {
/* 119 */       in.read(b4);
/* 120 */       this.latShift[i] = Util.getFloat(b4, bigEndian);
/* 121 */       in.read(b4);
/* 122 */       this.lonShift[i] = Util.getFloat(b4, bigEndian);
/* 123 */       in.read(b4);
/* 124 */       if (loadAccuracy)
/* 125 */         this.latAccuracy[i] = Util.getFloat(b4, bigEndian); 
/* 127 */       in.read(b4);
/* 128 */       if (loadAccuracy)
/* 129 */         this.lonAccuracy[i] = Util.getFloat(b4, bigEndian); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public SubGrid(RandomAccessFile raf, long subGridOffset, boolean bigEndian) throws IOException {
/* 144 */     this.raf = raf;
/* 145 */     this.subGridOffset = subGridOffset;
/* 146 */     this.bigEndian = bigEndian;
/* 147 */     raf.seek(subGridOffset);
/* 148 */     byte[] b8 = new byte[8];
/* 149 */     raf.read(b8);
/* 150 */     raf.read(b8);
/* 151 */     this.subGridName = (new String(b8)).trim();
/* 152 */     raf.read(b8);
/* 153 */     raf.read(b8);
/* 154 */     this.parentSubGridName = (new String(b8)).trim();
/* 155 */     raf.read(b8);
/* 156 */     raf.read(b8);
/* 157 */     this.created = new String(b8);
/* 158 */     raf.read(b8);
/* 159 */     raf.read(b8);
/* 160 */     this.updated = new String(b8);
/* 161 */     raf.read(b8);
/* 162 */     raf.read(b8);
/* 163 */     this.minLat = Util.getDouble(b8, bigEndian);
/* 164 */     raf.read(b8);
/* 165 */     raf.read(b8);
/* 166 */     this.maxLat = Util.getDouble(b8, bigEndian);
/* 167 */     raf.read(b8);
/* 168 */     raf.read(b8);
/* 169 */     this.minLon = Util.getDouble(b8, bigEndian);
/* 170 */     raf.read(b8);
/* 171 */     raf.read(b8);
/* 172 */     this.maxLon = Util.getDouble(b8, bigEndian);
/* 173 */     raf.read(b8);
/* 174 */     raf.read(b8);
/* 175 */     this.latInterval = Util.getDouble(b8, bigEndian);
/* 176 */     raf.read(b8);
/* 177 */     raf.read(b8);
/* 178 */     this.lonInterval = Util.getDouble(b8, bigEndian);
/* 179 */     this.lonColumnCount = 1 + (int)((this.maxLon - this.minLon) / this.lonInterval);
/* 180 */     this.latRowCount = 1 + (int)((this.maxLat - this.minLat) / this.latInterval);
/* 181 */     raf.read(b8);
/* 182 */     raf.read(b8);
/* 183 */     this.nodeCount = Util.getInt(b8, bigEndian);
/* 184 */     if (this.nodeCount != this.lonColumnCount * this.latRowCount)
/* 185 */       throw new IllegalStateException("SubGrid " + this.subGridName + " has inconsistent grid dimesions"); 
/*     */   }
/*     */   
/*     */   public SubGrid getSubGridForCoord(double lon, double lat) {
/* 203 */     if (isCoordWithin(lon, lat)) {
/* 204 */       if (this.subGrid == null)
/* 205 */         return this; 
/* 207 */       for (int i = 0; i < this.subGrid.length; i++) {
/* 208 */         if (this.subGrid[i].isCoordWithin(lon, lat))
/* 209 */           return this.subGrid[i].getSubGridForCoord(lon, lat); 
/*     */       } 
/* 212 */       return this;
/*     */     } 
/* 215 */     return null;
/*     */   }
/*     */   
/*     */   private boolean isCoordWithin(double lon, double lat) {
/* 229 */     if (lon >= this.minLon && lon < this.maxLon && lat >= this.minLat && lat < this.maxLat)
/* 230 */       return true; 
/* 232 */     return false;
/*     */   }
/*     */   
/*     */   private final double interpolate(float a, float b, float c, float d, double X, double Y) {
/* 250 */     return a + (b - a) * X + (c - a) * Y + (a + d - b - c) * X * Y;
/*     */   }
/*     */   
/*     */   public GridShift interpolateGridShift(GridShift gs) throws IOException {
/* 266 */     int lonIndex = (int)((gs.getLonPositiveWestSeconds() - this.minLon) / this.lonInterval);
/* 267 */     int latIndex = (int)((gs.getLatSeconds() - this.minLat) / this.latInterval);
/* 269 */     double X = (gs.getLonPositiveWestSeconds() - this.minLon + this.lonInterval * lonIndex) / this.lonInterval;
/* 270 */     double Y = (gs.getLatSeconds() - this.minLat + this.latInterval * latIndex) / this.latInterval;
/* 274 */     int indexA = lonIndex + latIndex * this.lonColumnCount;
/* 275 */     int indexB = indexA + 1;
/* 276 */     int indexC = indexA + this.lonColumnCount;
/* 277 */     int indexD = indexC + 1;
/* 279 */     if (this.raf == null) {
/* 280 */       gs.setLonShiftPositiveWestSeconds(interpolate(this.lonShift[indexA], this.lonShift[indexB], this.lonShift[indexC], this.lonShift[indexD], X, Y));
/* 283 */       gs.setLatShiftSeconds(interpolate(this.latShift[indexA], this.latShift[indexB], this.latShift[indexC], this.latShift[indexD], X, Y));
/* 286 */       if (this.lonAccuracy == null) {
/* 287 */         gs.setLonAccuracyAvailable(false);
/*     */       } else {
/* 289 */         gs.setLonAccuracyAvailable(true);
/* 290 */         gs.setLonAccuracySeconds(interpolate(this.lonAccuracy[indexA], this.lonAccuracy[indexB], this.lonAccuracy[indexC], this.lonAccuracy[indexD], X, Y));
/*     */       } 
/* 294 */       if (this.latAccuracy == null) {
/* 295 */         gs.setLatAccuracyAvailable(false);
/*     */       } else {
/* 297 */         gs.setLatAccuracyAvailable(true);
/* 298 */         gs.setLatAccuracySeconds(interpolate(this.latAccuracy[indexA], this.latAccuracy[indexB], this.latAccuracy[indexC], this.latAccuracy[indexD], X, Y));
/*     */       } 
/*     */     } else {
/* 302 */       synchronized (this.raf) {
/* 303 */         byte[] b4 = new byte[4];
/* 304 */         long nodeOffset = this.subGridOffset + 176L + (indexA * 16);
/* 305 */         this.raf.seek(nodeOffset);
/* 306 */         this.raf.read(b4);
/* 307 */         float latShiftA = Util.getFloat(b4, this.bigEndian);
/* 308 */         this.raf.read(b4);
/* 309 */         float lonShiftA = Util.getFloat(b4, this.bigEndian);
/* 310 */         this.raf.read(b4);
/* 311 */         float latAccuracyA = Util.getFloat(b4, this.bigEndian);
/* 312 */         this.raf.read(b4);
/* 313 */         float lonAccuracyA = Util.getFloat(b4, this.bigEndian);
/* 315 */         nodeOffset = this.subGridOffset + 176L + (indexB * 16);
/* 316 */         this.raf.seek(nodeOffset);
/* 317 */         this.raf.read(b4);
/* 318 */         float latShiftB = Util.getFloat(b4, this.bigEndian);
/* 319 */         this.raf.read(b4);
/* 320 */         float lonShiftB = Util.getFloat(b4, this.bigEndian);
/* 321 */         this.raf.read(b4);
/* 322 */         float latAccuracyB = Util.getFloat(b4, this.bigEndian);
/* 323 */         this.raf.read(b4);
/* 324 */         float lonAccuracyB = Util.getFloat(b4, this.bigEndian);
/* 326 */         nodeOffset = this.subGridOffset + 176L + (indexC * 16);
/* 327 */         this.raf.seek(nodeOffset);
/* 328 */         this.raf.read(b4);
/* 329 */         float latShiftC = Util.getFloat(b4, this.bigEndian);
/* 330 */         this.raf.read(b4);
/* 331 */         float lonShiftC = Util.getFloat(b4, this.bigEndian);
/* 332 */         this.raf.read(b4);
/* 333 */         float latAccuracyC = Util.getFloat(b4, this.bigEndian);
/* 334 */         this.raf.read(b4);
/* 335 */         float lonAccuracyC = Util.getFloat(b4, this.bigEndian);
/* 337 */         nodeOffset = this.subGridOffset + 176L + (indexD * 16);
/* 338 */         this.raf.seek(nodeOffset);
/* 339 */         this.raf.read(b4);
/* 340 */         float latShiftD = Util.getFloat(b4, this.bigEndian);
/* 341 */         this.raf.read(b4);
/* 342 */         float lonShiftD = Util.getFloat(b4, this.bigEndian);
/* 343 */         this.raf.read(b4);
/* 344 */         float latAccuracyD = Util.getFloat(b4, this.bigEndian);
/* 345 */         this.raf.read(b4);
/* 346 */         float lonAccuracyD = Util.getFloat(b4, this.bigEndian);
/* 348 */         gs.setLonShiftPositiveWestSeconds(interpolate(lonShiftA, lonShiftB, lonShiftC, lonShiftD, X, Y));
/* 351 */         gs.setLatShiftSeconds(interpolate(latShiftA, latShiftB, latShiftC, latShiftD, X, Y));
/* 354 */         gs.setLonAccuracyAvailable(true);
/* 355 */         gs.setLonAccuracySeconds(interpolate(lonAccuracyA, lonAccuracyB, lonAccuracyC, lonAccuracyD, X, Y));
/* 358 */         gs.setLatAccuracyAvailable(true);
/* 359 */         gs.setLatAccuracySeconds(interpolate(latAccuracyA, latAccuracyB, latAccuracyC, latAccuracyD, X, Y));
/*     */       } 
/*     */     } 
/* 363 */     return gs;
/*     */   }
/*     */   
/*     */   public String getParentSubGridName() {
/* 367 */     return this.parentSubGridName;
/*     */   }
/*     */   
/*     */   public String getSubGridName() {
/* 371 */     return this.subGridName;
/*     */   }
/*     */   
/*     */   public int getNodeCount() {
/* 375 */     return this.nodeCount;
/*     */   }
/*     */   
/*     */   public int getSubGridCount() {
/* 379 */     return (this.subGrid == null) ? 0 : this.subGrid.length;
/*     */   }
/*     */   
/*     */   public SubGrid getSubGrid(int index) {
/* 383 */     return (this.subGrid == null) ? null : this.subGrid[index];
/*     */   }
/*     */   
/*     */   public void setSubGridArray(SubGrid[] subGrid) {
/* 391 */     this.subGrid = subGrid;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 395 */     return this.subGridName;
/*     */   }
/*     */   
/*     */   public String getDetails() {
/* 399 */     StringBuffer buf = new StringBuffer("Sub Grid : ");
/* 400 */     buf.append(this.subGridName);
/* 401 */     buf.append("\nParent   : ");
/* 402 */     buf.append(this.parentSubGridName);
/* 403 */     buf.append("\nCreated  : ");
/* 404 */     buf.append(this.created);
/* 405 */     buf.append("\nUpdated  : ");
/* 406 */     buf.append(this.updated);
/* 407 */     buf.append("\nMin Lat  : ");
/* 408 */     buf.append(this.minLat);
/* 409 */     buf.append("\nMax Lat  : ");
/* 410 */     buf.append(this.maxLat);
/* 411 */     buf.append("\nMin Lon  : ");
/* 412 */     buf.append(this.minLon);
/* 413 */     buf.append("\nMax Lon  : ");
/* 414 */     buf.append(this.maxLon);
/* 415 */     buf.append("\nLat Intvl: ");
/* 416 */     buf.append(this.latInterval);
/* 417 */     buf.append("\nLon Intvl: ");
/* 418 */     buf.append(this.lonInterval);
/* 419 */     buf.append("\nNode Cnt : ");
/* 420 */     buf.append(this.nodeCount);
/* 421 */     return buf.toString();
/*     */   }
/*     */   
/*     */   public Object clone() {
/* 428 */     SubGrid clone = null;
/*     */     try {
/* 430 */       clone = (SubGrid)super.clone();
/* 431 */     } catch (CloneNotSupportedException cnse) {}
/* 434 */     if (this.subGrid != null) {
/* 435 */       clone.subGrid = new SubGrid[this.subGrid.length];
/* 436 */       for (int i = 0; i < this.subGrid.length; i++)
/* 437 */         clone.subGrid[i] = (SubGrid)this.subGrid[i].clone(); 
/*     */     } 
/* 440 */     return clone;
/*     */   }
/*     */   
/*     */   public double getMaxLat() {
/* 446 */     return this.maxLat;
/*     */   }
/*     */   
/*     */   public double getMaxLon() {
/* 453 */     return this.maxLon;
/*     */   }
/*     */   
/*     */   public double getMinLat() {
/* 460 */     return this.minLat;
/*     */   }
/*     */   
/*     */   public double getMinLon() {
/* 467 */     return this.minLon;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\au\com\objectix\jgridshift\SubGrid.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */