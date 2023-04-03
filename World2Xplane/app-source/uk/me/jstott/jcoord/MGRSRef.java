/*     */ package uk.me.jstott.jcoord;
/*     */ 
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ import uk.me.jstott.jcoord.datum.Datum;
/*     */ import uk.me.jstott.jcoord.datum.WGS84Datum;
/*     */ 
/*     */ public class MGRSRef extends CoordinateSystem {
/*     */   private int utmZoneNumber;
/*     */   
/*     */   private char utmZoneChar;
/*     */   
/*     */   private char eastingID;
/*     */   
/*     */   private char northingID;
/*     */   
/*     */   private int easting;
/*     */   
/*     */   private int northing;
/*     */   
/*     */   private int precision;
/*     */   
/*     */   private boolean isBessel;
/*     */   
/*     */   public static final int PRECISION_10000M = 10000;
/*     */   
/*     */   public static final int PRECISION_1000M = 1000;
/*     */   
/*     */   public static final int PRECISION_100M = 100;
/*     */   
/*     */   public static final int PRECISION_10M = 10;
/*     */   
/*     */   public static final int PRECISION_1M = 1;
/*     */   
/* 201 */   private static final char[] northingIDs = new char[] { 
/* 201 */       'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 
/* 201 */       'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V' };
/*     */   
/*     */   public MGRSRef(UTMRef utm) {
/* 215 */     this(utm, false);
/*     */   }
/*     */   
/*     */   public MGRSRef(UTMRef utm, boolean isBessel) {
/* 235 */     super((Datum)WGS84Datum.getInstance());
/* 237 */     int lngZone = utm.getLngZone();
/* 238 */     int set = (lngZone - 1) % 6 + 1;
/* 239 */     int eID = (int)Math.floor(utm.getEasting() / 100000.0D) + 8 * (set - 1) % 3;
/* 241 */     int nID = (int)Math.floor(utm.getNorthing() % 2000000.0D / 100000.0D);
/* 243 */     if (eID > 8)
/* 244 */       eID++; 
/* 245 */     if (eID > 14)
/* 246 */       eID++; 
/* 248 */     char eIDc = (char)(eID + 64);
/* 251 */     if (set % 2 == 0)
/* 252 */       nID += 5; 
/* 255 */     if (isBessel)
/* 256 */       nID += 10; 
/* 259 */     if (nID > 19)
/* 260 */       nID -= 20; 
/* 263 */     char nIDc = northingIDs[nID];
/* 265 */     this.utmZoneNumber = lngZone;
/* 266 */     this.utmZoneChar = utm.getLatZone();
/* 267 */     this.eastingID = eIDc;
/* 268 */     this.northingID = nIDc;
/* 269 */     this.easting = (int)Math.round(utm.getEasting()) % 100000;
/* 270 */     this.northing = (int)Math.round(utm.getNorthing()) % 100000;
/* 271 */     this.precision = 1;
/* 272 */     this.isBessel = isBessel;
/*     */   }
/*     */   
/*     */   public MGRSRef(int utmZoneNumber, char utmZoneChar, char eastingID, char northingID, int easting, int northing, int precision) throws IllegalArgumentException {
/* 307 */     this(utmZoneNumber, utmZoneChar, eastingID, northingID, easting, northing, precision, false);
/*     */   }
/*     */   
/*     */   public MGRSRef(int utmZoneNumber, char utmZoneChar, char eastingID, char northingID, int easting, int northing, int precision, boolean isBessel) throws IllegalArgumentException {
/* 348 */     super((Datum)WGS84Datum.getInstance());
/* 350 */     if (utmZoneNumber < 1 || utmZoneNumber > 60)
/* 351 */       throw new IllegalArgumentException("Invalid utmZoneNumber (" + utmZoneNumber + ")"); 
/* 354 */     if (utmZoneChar < 'A' || utmZoneChar > 'Z')
/* 355 */       throw new IllegalArgumentException("Invalid utmZoneChar (" + utmZoneChar + ")"); 
/* 358 */     if (eastingID < 'A' || eastingID > 'Z' || eastingID == 'I' || eastingID == 'O')
/* 360 */       throw new IllegalArgumentException("Invalid eastingID (" + eastingID + ")"); 
/* 363 */     if (northingID < 'A' || northingID > 'Z' || northingID == 'I' || northingID == 'O')
/* 365 */       throw new IllegalArgumentException("Invalid northingID (" + northingID + ")"); 
/* 368 */     if (easting < 0 || easting > 99999)
/* 369 */       throw new IllegalArgumentException("Invalid easting (" + easting + ")"); 
/* 371 */     if (northing < 0 || northing > 99999)
/* 372 */       throw new IllegalArgumentException("Invalid northing (" + northing + ")"); 
/* 374 */     if (precision != 1 && precision != 10 && precision != 100 && precision != 1000 && precision != 10000)
/* 377 */       throw new IllegalArgumentException("Invalid precision (" + precision + ")"); 
/* 381 */     this.utmZoneNumber = utmZoneNumber;
/* 382 */     this.utmZoneChar = utmZoneChar;
/* 383 */     this.eastingID = eastingID;
/* 384 */     this.northingID = northingID;
/* 385 */     this.easting = easting;
/* 386 */     this.northing = northing;
/* 387 */     this.precision = precision;
/* 388 */     this.isBessel = isBessel;
/*     */   }
/*     */   
/*     */   public MGRSRef(String ref) throws IllegalArgumentException {
/* 405 */     this(ref, false);
/*     */   }
/*     */   
/*     */   public MGRSRef(String ref, boolean isBessel) throws IllegalArgumentException {
/* 425 */     super((Datum)WGS84Datum.getInstance());
/* 427 */     Pattern p = Pattern.compile("(\\d{1,2})([C-X&&[^IO]])([A-Z&&[^IO]])([A-Z&&[^IO]])(\\d{2,10})");
/* 430 */     Matcher m = p.matcher(ref);
/* 432 */     if (!m.matches())
/* 433 */       throw new IllegalArgumentException("Invalid MGRS reference (" + ref + ")"); 
/* 436 */     this.utmZoneNumber = Integer.parseInt(m.group(1));
/* 437 */     this.utmZoneChar = m.group(2).charAt(0);
/* 438 */     this.eastingID = m.group(3).charAt(0);
/* 439 */     this.northingID = m.group(4).charAt(0);
/* 440 */     String en = m.group(5);
/* 441 */     int enl = en.length();
/* 442 */     if (enl % 2 != 0)
/* 443 */       throw new IllegalArgumentException("Invalid MGRS reference (" + ref + ")"); 
/* 445 */     this.precision = (int)Math.pow(10.0D, (5 - enl / 2));
/* 446 */     this.easting = Integer.parseInt(en.substring(0, enl / 2)) * this.precision;
/* 448 */     this.northing = Integer.parseInt(en.substring(enl / 2)) * this.precision;
/*     */   }
/*     */   
/*     */   public UTMRef toUTMRef() {
/* 461 */     int set = (this.utmZoneNumber - 1) % 6 + 1;
/* 462 */     int e = this.eastingID - 65;
/* 463 */     if (e > 15)
/* 464 */       e--; 
/* 465 */     if (e > 9)
/* 466 */       e--; 
/* 468 */     int ex = (this.easting + (e % 8 + 1) * 100000) % 1000000;
/* 472 */     int n = this.northingID - 64;
/* 473 */     if (n > 15)
/* 474 */       n--; 
/* 475 */     if (n > 9)
/* 476 */       n--; 
/* 477 */     if (set % 2 == 0)
/* 478 */       n -= 5; 
/* 479 */     if (n < 0)
/* 480 */       n += 16; 
/* 482 */     int nx = 0;
/* 484 */     boolean isOffset = (set % 2 == 0);
/* 486 */     switch (this.utmZoneChar) {
/*     */       case 'Q':
/* 488 */         if ((!isOffset && this.northingID < 'T') || (isOffset && (this.northingID < 'C' || this.northingID > 'E')))
/* 490 */           nx += 2000000; 
/*     */         break;
/*     */       case 'R':
/* 494 */         nx += 2000000;
/*     */         break;
/*     */       case 'S':
/* 497 */         if ((!isOffset && this.northingID < 'R') || (isOffset && this.northingID > 'E')) {
/* 498 */           nx += 4000000;
/*     */           break;
/*     */         } 
/* 500 */         nx += 2000000;
/*     */         break;
/*     */       case 'T':
/* 504 */         nx += 4000000;
/*     */         break;
/*     */       case 'U':
/* 507 */         if ((!isOffset && this.northingID < 'P') || (isOffset && this.northingID < 'U')) {
/* 508 */           nx += 6000000;
/*     */           break;
/*     */         } 
/* 510 */         nx += 4000000;
/*     */         break;
/*     */       case 'V':
/*     */       case 'W':
/* 515 */         nx += 6000000;
/*     */         break;
/*     */       case 'X':
/* 519 */         nx += 8000000;
/*     */         break;
/*     */     } 
/* 525 */     nx += 100000 * (n - 1) + this.northing;
/* 527 */     return new UTMRef(this.utmZoneNumber, this.utmZoneChar, ex, nx);
/*     */   }
/*     */   
/*     */   public LatLng toLatLng() {
/* 539 */     return toUTMRef().toLatLng();
/*     */   }
/*     */   
/*     */   public String toString() {
/* 552 */     return toString(this.precision);
/*     */   }
/*     */   
/*     */   public String toString(int precision) {
/* 570 */     if (precision != 1 && precision != 10 && precision != 100 && precision != 1000 && precision != 10000)
/* 573 */       throw new IllegalArgumentException("Precision (" + precision + ") must be 1m, 10m, 100m, 1000m or 10000m"); 
/* 577 */     int eastingR = (int)Math.floor((this.easting / precision));
/* 578 */     int northingR = (int)Math.floor((this.northing / precision));
/* 580 */     int padding = 5;
/* 582 */     switch (precision) {
/*     */       case 10:
/* 584 */         padding = 4;
/*     */         break;
/*     */       case 100:
/* 587 */         padding = 3;
/*     */         break;
/*     */       case 1000:
/* 590 */         padding = 2;
/*     */         break;
/*     */       case 10000:
/* 593 */         padding = 1;
/*     */         break;
/*     */     } 
/* 597 */     String eastingRs = Integer.toString(eastingR);
/* 598 */     int ez = padding - eastingRs.length();
/* 599 */     while (ez > 0) {
/* 600 */       eastingRs = "0" + eastingRs;
/* 601 */       ez--;
/*     */     } 
/* 604 */     String northingRs = Integer.toString(northingR);
/* 605 */     int nz = padding - northingRs.length();
/* 606 */     while (nz > 0) {
/* 607 */       northingRs = "0" + northingRs;
/* 608 */       nz--;
/*     */     } 
/* 611 */     String utmZonePadding = "";
/* 612 */     if (this.utmZoneNumber < 10)
/* 613 */       utmZonePadding = "0"; 
/* 616 */     return utmZonePadding + this.utmZoneNumber + Character.toString(this.utmZoneChar) + Character.toString(this.eastingID) + Character.toString(this.northingID) + eastingRs + northingRs;
/*     */   }
/*     */   
/*     */   public int getEasting() {
/* 629 */     return this.easting;
/*     */   }
/*     */   
/*     */   public char getEastingID() {
/* 640 */     return this.eastingID;
/*     */   }
/*     */   
/*     */   public boolean isBessel() {
/* 651 */     return this.isBessel;
/*     */   }
/*     */   
/*     */   public int getNorthing() {
/* 662 */     return this.northing;
/*     */   }
/*     */   
/*     */   public char getNorthingID() {
/* 673 */     return this.northingID;
/*     */   }
/*     */   
/*     */   public int getPrecision() {
/* 684 */     return this.precision;
/*     */   }
/*     */   
/*     */   public char getUtmZoneChar() {
/* 695 */     return this.utmZoneChar;
/*     */   }
/*     */   
/*     */   public int getUtmZoneNumber() {
/* 706 */     return this.utmZoneNumber;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar\\uk\me\jstott\jcoord\MGRSRef.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */