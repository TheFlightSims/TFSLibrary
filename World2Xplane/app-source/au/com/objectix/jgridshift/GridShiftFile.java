/*     */ package au.com.objectix.jgridshift;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.RandomAccessFile;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ 
/*     */ public class GridShiftFile implements Serializable {
/*     */   private static final int REC_SIZE = 16;
/*     */   
/*     */   private String overviewHeaderCountId;
/*     */   
/*     */   private int overviewHeaderCount;
/*     */   
/*     */   private int subGridHeaderCount;
/*     */   
/*     */   private int subGridCount;
/*     */   
/*     */   private String shiftType;
/*     */   
/*     */   private String version;
/*     */   
/*  69 */   private String fromEllipsoid = "";
/*     */   
/*  70 */   private String toEllipsoid = "";
/*     */   
/*     */   private double fromSemiMajorAxis;
/*     */   
/*     */   private double fromSemiMinorAxis;
/*     */   
/*     */   private double toSemiMajorAxis;
/*     */   
/*     */   private double toSemiMinorAxis;
/*     */   
/*     */   private SubGrid[] topLevelSubGrid;
/*     */   
/*     */   private SubGrid lastSubGrid;
/*     */   
/*     */   private transient RandomAccessFile raf;
/*     */   
/*     */   public void loadGridShiftFile(InputStream in, boolean loadAccuracy) throws IOException {
/*  99 */     byte[] b8 = new byte[8];
/* 100 */     boolean bigEndian = true;
/* 101 */     this.fromEllipsoid = "";
/* 102 */     this.toEllipsoid = "";
/* 103 */     this.topLevelSubGrid = null;
/* 104 */     in.read(b8);
/* 105 */     this.overviewHeaderCountId = new String(b8);
/* 106 */     if (!"NUM_OREC".equals(this.overviewHeaderCountId))
/* 107 */       throw new IllegalArgumentException("Input file is not an NTv2 grid shift file"); 
/* 109 */     in.read(b8);
/* 110 */     this.overviewHeaderCount = Util.getIntBE(b8, 0);
/* 111 */     if (this.overviewHeaderCount == 11) {
/* 112 */       bigEndian = true;
/*     */     } else {
/* 114 */       this.overviewHeaderCount = Util.getIntLE(b8, 0);
/* 115 */       if (this.overviewHeaderCount == 11) {
/* 116 */         bigEndian = false;
/*     */       } else {
/* 118 */         throw new IllegalArgumentException("Input file is not an NTv2 grid shift file");
/*     */       } 
/*     */     } 
/* 121 */     in.read(b8);
/* 122 */     in.read(b8);
/* 123 */     this.subGridHeaderCount = Util.getInt(b8, bigEndian);
/* 124 */     in.read(b8);
/* 125 */     in.read(b8);
/* 126 */     this.subGridCount = Util.getInt(b8, bigEndian);
/* 127 */     SubGrid[] subGrid = new SubGrid[this.subGridCount];
/* 128 */     in.read(b8);
/* 129 */     in.read(b8);
/* 130 */     this.shiftType = new String(b8);
/* 131 */     in.read(b8);
/* 132 */     in.read(b8);
/* 133 */     this.version = new String(b8);
/* 134 */     in.read(b8);
/* 135 */     in.read(b8);
/* 136 */     this.fromEllipsoid = new String(b8);
/* 137 */     in.read(b8);
/* 138 */     in.read(b8);
/* 139 */     this.toEllipsoid = new String(b8);
/* 140 */     in.read(b8);
/* 141 */     in.read(b8);
/* 142 */     this.fromSemiMajorAxis = Util.getDouble(b8, bigEndian);
/* 143 */     in.read(b8);
/* 144 */     in.read(b8);
/* 145 */     this.fromSemiMinorAxis = Util.getDouble(b8, bigEndian);
/* 146 */     in.read(b8);
/* 147 */     in.read(b8);
/* 148 */     this.toSemiMajorAxis = Util.getDouble(b8, bigEndian);
/* 149 */     in.read(b8);
/* 150 */     in.read(b8);
/* 151 */     this.toSemiMinorAxis = Util.getDouble(b8, bigEndian);
/* 153 */     for (int i = 0; i < this.subGridCount; i++)
/* 154 */       subGrid[i] = new SubGrid(in, bigEndian, loadAccuracy); 
/* 156 */     this.topLevelSubGrid = createSubGridTree(subGrid);
/* 157 */     this.lastSubGrid = this.topLevelSubGrid[0];
/* 159 */     in.close();
/*     */   }
/*     */   
/*     */   public void loadGridShiftFile(RandomAccessFile raf) throws IOException {
/* 172 */     this.raf = raf;
/* 173 */     byte[] b8 = new byte[8];
/* 174 */     boolean bigEndian = true;
/* 175 */     this.fromEllipsoid = "";
/* 176 */     this.toEllipsoid = "";
/* 177 */     this.topLevelSubGrid = null;
/* 178 */     raf.seek(0L);
/* 179 */     raf.read(b8);
/* 180 */     this.overviewHeaderCountId = new String(b8);
/* 181 */     if (!"NUM_OREC".equals(this.overviewHeaderCountId)) {
/* 182 */       this.raf = null;
/* 183 */       throw new IllegalArgumentException("Input file is not an NTv2 grid shift file");
/*     */     } 
/* 185 */     raf.read(b8);
/* 186 */     this.overviewHeaderCount = Util.getIntBE(b8, 0);
/* 187 */     if (this.overviewHeaderCount == 11) {
/* 188 */       bigEndian = true;
/*     */     } else {
/* 190 */       this.overviewHeaderCount = Util.getIntLE(b8, 0);
/* 191 */       if (this.overviewHeaderCount == 11) {
/* 192 */         bigEndian = false;
/*     */       } else {
/* 194 */         this.raf = null;
/* 195 */         throw new IllegalArgumentException("Input file is not an NTv2 grid shift file");
/*     */       } 
/*     */     } 
/* 198 */     raf.read(b8);
/* 199 */     raf.read(b8);
/* 200 */     this.subGridHeaderCount = Util.getInt(b8, bigEndian);
/* 201 */     raf.read(b8);
/* 202 */     raf.read(b8);
/* 203 */     this.subGridCount = Util.getInt(b8, bigEndian);
/* 204 */     SubGrid[] subGrid = new SubGrid[this.subGridCount];
/* 205 */     raf.read(b8);
/* 206 */     raf.read(b8);
/* 207 */     this.shiftType = new String(b8);
/* 208 */     raf.read(b8);
/* 209 */     raf.read(b8);
/* 210 */     this.version = new String(b8);
/* 211 */     raf.read(b8);
/* 212 */     raf.read(b8);
/* 213 */     this.fromEllipsoid = new String(b8);
/* 214 */     raf.read(b8);
/* 215 */     raf.read(b8);
/* 216 */     this.toEllipsoid = new String(b8);
/* 217 */     raf.read(b8);
/* 218 */     raf.read(b8);
/* 219 */     this.fromSemiMajorAxis = Util.getDouble(b8, bigEndian);
/* 220 */     raf.read(b8);
/* 221 */     raf.read(b8);
/* 222 */     this.fromSemiMinorAxis = Util.getDouble(b8, bigEndian);
/* 223 */     raf.read(b8);
/* 224 */     raf.read(b8);
/* 225 */     this.toSemiMajorAxis = Util.getDouble(b8, bigEndian);
/* 226 */     raf.read(b8);
/* 227 */     raf.read(b8);
/* 228 */     this.toSemiMinorAxis = Util.getDouble(b8, bigEndian);
/* 230 */     long offset = (this.overviewHeaderCount * 16);
/* 231 */     for (int i = 0; i < this.subGridCount; i++) {
/* 232 */       subGrid[i] = new SubGrid(raf, offset, bigEndian);
/* 233 */       offset = offset + (this.subGridHeaderCount * 16) + (subGrid[i].getNodeCount() * 16);
/*     */     } 
/* 235 */     this.topLevelSubGrid = createSubGridTree(subGrid);
/* 236 */     this.lastSubGrid = this.topLevelSubGrid[0];
/*     */   }
/*     */   
/*     */   private SubGrid[] createSubGridTree(SubGrid[] subGrid) {
/* 246 */     int topLevelCount = 0;
/* 247 */     HashMap subGridMap = new HashMap();
/* 248 */     for (int i = 0; i < subGrid.length; i++) {
/* 249 */       if (subGrid[i].getParentSubGridName().equalsIgnoreCase("NONE"))
/* 250 */         topLevelCount++; 
/* 252 */       subGridMap.put(subGrid[i].getSubGridName(), new ArrayList());
/*     */     } 
/* 254 */     SubGrid[] topLevelSubGrid = new SubGrid[topLevelCount];
/* 255 */     topLevelCount = 0;
/* 256 */     for (int j = 0; j < subGrid.length; j++) {
/* 257 */       if (subGrid[j].getParentSubGridName().equalsIgnoreCase("NONE")) {
/* 258 */         topLevelSubGrid[topLevelCount++] = subGrid[j];
/*     */       } else {
/* 260 */         ArrayList parent = (ArrayList)subGridMap.get(subGrid[j].getParentSubGridName());
/* 261 */         parent.add(subGrid[j]);
/*     */       } 
/*     */     } 
/* 264 */     SubGrid[] nullArray = new SubGrid[0];
/* 265 */     for (int k = 0; k < subGrid.length; k++) {
/* 266 */       ArrayList subSubGrids = (ArrayList)subGridMap.get(subGrid[k].getSubGridName());
/* 267 */       if (subSubGrids.size() > 0) {
/* 268 */         SubGrid[] subGridArray = (SubGrid[])subSubGrids.toArray((Object[])nullArray);
/* 269 */         subGrid[k].setSubGridArray(subGridArray);
/*     */       } 
/*     */     } 
/* 272 */     return topLevelSubGrid;
/*     */   }
/*     */   
/*     */   public boolean gridShiftForward(GridShift gs) throws IOException {
/* 284 */     SubGrid subGrid = this.lastSubGrid.getSubGridForCoord(gs.getLonPositiveWestSeconds(), gs.getLatSeconds());
/* 285 */     if (subGrid == null)
/* 286 */       subGrid = getSubGrid(gs.getLonPositiveWestSeconds(), gs.getLatSeconds()); 
/* 288 */     if (subGrid == null)
/* 289 */       return false; 
/* 291 */     subGrid.interpolateGridShift(gs);
/* 292 */     gs.setSubGridName(subGrid.getSubGridName());
/* 293 */     this.lastSubGrid = subGrid;
/* 294 */     return true;
/*     */   }
/*     */   
/*     */   public boolean gridShiftReverse(GridShift gs) throws IOException {
/* 307 */     GridShift forwardGs = new GridShift();
/* 308 */     forwardGs.setLonPositiveWestSeconds(gs.getLonPositiveWestSeconds());
/* 309 */     forwardGs.setLatSeconds(gs.getLatSeconds());
/* 310 */     for (int i = 0; i < 4; i++) {
/* 311 */       if (!gridShiftForward(forwardGs))
/* 312 */         return false; 
/* 314 */       forwardGs.setLonPositiveWestSeconds(gs.getLonPositiveWestSeconds() - forwardGs.getLonShiftPositiveWestSeconds());
/* 316 */       forwardGs.setLatSeconds(gs.getLatSeconds() - forwardGs.getLatShiftSeconds());
/*     */     } 
/* 318 */     gs.setLonShiftPositiveWestSeconds(-forwardGs.getLonShiftPositiveWestSeconds());
/* 319 */     gs.setLatShiftSeconds(-forwardGs.getLatShiftSeconds());
/* 320 */     gs.setLonAccuracyAvailable(forwardGs.isLonAccuracyAvailable());
/* 321 */     if (forwardGs.isLonAccuracyAvailable())
/* 322 */       gs.setLonAccuracySeconds(forwardGs.getLonAccuracySeconds()); 
/* 324 */     gs.setLatAccuracyAvailable(forwardGs.isLatAccuracyAvailable());
/* 325 */     if (forwardGs.isLatAccuracyAvailable())
/* 326 */       gs.setLatAccuracySeconds(forwardGs.getLatAccuracySeconds()); 
/* 328 */     return true;
/*     */   }
/*     */   
/*     */   private SubGrid getSubGrid(double lon, double lat) {
/* 340 */     SubGrid sub = null;
/* 341 */     for (int i = 0; i < this.topLevelSubGrid.length; i++) {
/* 342 */       sub = this.topLevelSubGrid[i].getSubGridForCoord(lon, lat);
/* 343 */       if (sub != null)
/*     */         break; 
/*     */     } 
/* 347 */     return sub;
/*     */   }
/*     */   
/*     */   public boolean isLoaded() {
/* 351 */     return (this.topLevelSubGrid != null);
/*     */   }
/*     */   
/*     */   public void unload() throws IOException {
/* 355 */     this.topLevelSubGrid = null;
/* 356 */     if (this.raf != null) {
/* 357 */       this.raf.close();
/* 358 */       this.raf = null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public String toString() {
/* 363 */     StringBuffer buf = new StringBuffer("Headers  : ");
/* 364 */     buf.append(this.overviewHeaderCount);
/* 365 */     buf.append("\nSub Hdrs : ");
/* 366 */     buf.append(this.subGridHeaderCount);
/* 367 */     buf.append("\nSub Grids: ");
/* 368 */     buf.append(this.subGridCount);
/* 369 */     buf.append("\nType     : ");
/* 370 */     buf.append(this.shiftType);
/* 371 */     buf.append("\nVersion  : ");
/* 372 */     buf.append(this.version);
/* 373 */     buf.append("\nFr Ellpsd: ");
/* 374 */     buf.append(this.fromEllipsoid);
/* 375 */     buf.append("\nTo Ellpsd: ");
/* 376 */     buf.append(this.toEllipsoid);
/* 377 */     buf.append("\nFr Maj Ax: ");
/* 378 */     buf.append(this.fromSemiMajorAxis);
/* 379 */     buf.append("\nFr Min Ax: ");
/* 380 */     buf.append(this.fromSemiMinorAxis);
/* 381 */     buf.append("\nTo Maj Ax: ");
/* 382 */     buf.append(this.toSemiMajorAxis);
/* 383 */     buf.append("\nTo Min Ax: ");
/* 384 */     buf.append(this.toSemiMinorAxis);
/* 385 */     return buf.toString();
/*     */   }
/*     */   
/*     */   public SubGrid[] getSubGridTree() {
/* 394 */     SubGrid[] clone = new SubGrid[this.topLevelSubGrid.length];
/* 395 */     for (int i = 0; i < this.topLevelSubGrid.length; i++)
/* 396 */       clone[i] = (SubGrid)this.topLevelSubGrid[i].clone(); 
/* 398 */     return clone;
/*     */   }
/*     */   
/*     */   public String getFromEllipsoid() {
/* 402 */     return this.fromEllipsoid;
/*     */   }
/*     */   
/*     */   public String getToEllipsoid() {
/* 406 */     return this.toEllipsoid;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\au\com\objectix\jgridshift\GridShiftFile.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */