/*     */ package uk.me.jstott.jcoord;
/*     */ 
/*     */ import uk.me.jstott.jcoord.datum.Datum;
/*     */ import uk.me.jstott.jcoord.datum.WGS84Datum;
/*     */ 
/*     */ public class UTMRef extends CoordinateSystem {
/*     */   private double easting;
/*     */   
/*     */   private double northing;
/*     */   
/*     */   private char latZone;
/*     */   
/*     */   private int lngZone;
/*     */   
/*     */   public UTMRef(double easting, double northing, char latZone, int lngZone) throws NotDefinedOnUTMGridException {
/*  78 */     this(lngZone, latZone, easting, northing);
/*     */   }
/*     */   
/*     */   public UTMRef(int lngZone, char latZone, double easting, double northing) throws NotDefinedOnUTMGridException {
/* 108 */     super((Datum)WGS84Datum.getInstance());
/* 110 */     if (lngZone < 1 || lngZone > 60)
/* 111 */       throw new NotDefinedOnUTMGridException("Longitude zone (" + lngZone + ") is not defined on the UTM grid."); 
/* 115 */     if (latZone < 'C' || latZone > 'X')
/* 116 */       throw new NotDefinedOnUTMGridException("Latitude zone (" + latZone + ") is not defined on the UTM grid."); 
/* 120 */     if (easting < 0.0D || easting > 1000000.0D)
/* 121 */       throw new NotDefinedOnUTMGridException("Easting (" + easting + ") is not defined on the UTM grid."); 
/* 125 */     if (northing < 0.0D || northing > 1.0E7D)
/* 126 */       throw new NotDefinedOnUTMGridException("Northing (" + northing + ") is not defined on the UTM grid."); 
/* 130 */     this.easting = easting;
/* 131 */     this.northing = northing;
/* 132 */     this.latZone = latZone;
/* 133 */     this.lngZone = lngZone;
/*     */   }
/*     */   
/*     */   public LatLng toLatLng() {
/* 144 */     double UTM_F0 = 0.9996D;
/* 145 */     double a = getDatum().getReferenceEllipsoid().getSemiMajorAxis();
/* 146 */     double eSquared = getDatum().getReferenceEllipsoid().getEccentricitySquared();
/* 148 */     double ePrimeSquared = eSquared / (1.0D - eSquared);
/* 149 */     double e1 = (1.0D - Math.sqrt(1.0D - eSquared)) / (1.0D + Math.sqrt(1.0D - eSquared));
/* 150 */     double x = this.easting - 500000.0D;
/* 152 */     double y = this.northing;
/* 153 */     int zoneNumber = this.lngZone;
/* 154 */     char zoneLetter = this.latZone;
/* 156 */     double longitudeOrigin = (zoneNumber - 1.0D) * 6.0D - 180.0D + 3.0D;
/* 159 */     if (zoneLetter - 78 < 0)
/* 160 */       y -= 1.0E7D; 
/* 163 */     double m = y / UTM_F0;
/* 164 */     double mu = m / a * (1.0D - eSquared / 4.0D - 3.0D * eSquared * eSquared / 64.0D - 5.0D * Math.pow(eSquared, 3.0D) / 256.0D);
/* 168 */     double phi1Rad = mu + (3.0D * e1 / 2.0D - 27.0D * Math.pow(e1, 3.0D) / 32.0D) * Math.sin(2.0D * mu) + (21.0D * e1 * e1 / 16.0D - 55.0D * Math.pow(e1, 4.0D) / 32.0D) * Math.sin(4.0D * mu) + 151.0D * Math.pow(e1, 3.0D) / 96.0D * Math.sin(6.0D * mu);
/* 174 */     double n = a / Math.sqrt(1.0D - eSquared * Math.sin(phi1Rad) * Math.sin(phi1Rad));
/* 176 */     double t = Math.tan(phi1Rad) * Math.tan(phi1Rad);
/* 177 */     double c = ePrimeSquared * Math.cos(phi1Rad) * Math.cos(phi1Rad);
/* 178 */     double r = a * (1.0D - eSquared) / Math.pow(1.0D - eSquared * Math.sin(phi1Rad) * Math.sin(phi1Rad), 1.5D);
/* 180 */     double d = x / n * UTM_F0;
/* 182 */     double latitude = (phi1Rad - n * Math.tan(phi1Rad) / r * (d * d / 2.0D - (5.0D + 3.0D * t + 10.0D * c - 4.0D * c * c - 9.0D * ePrimeSquared) * Math.pow(d, 4.0D) / 24.0D + (61.0D + 90.0D * t + 298.0D * c + 45.0D * t * t - 252.0D * ePrimeSquared - 3.0D * c * c) * Math.pow(d, 6.0D) / 720.0D)) * 57.29577951308232D;
/* 192 */     double longitude = longitudeOrigin + (d - (1.0D + 2.0D * t + c) * Math.pow(d, 3.0D) / 6.0D + (5.0D - 2.0D * c + 28.0D * t - 3.0D * c * c + 8.0D * ePrimeSquared + 24.0D * t * t) * Math.pow(d, 5.0D) / 120.0D) / Math.cos(phi1Rad) * 57.29577951308232D;
/* 198 */     return new LatLng(latitude, longitude);
/*     */   }
/*     */   
/*     */   public static char getUTMLatitudeZoneLetter(double latitude) {
/* 211 */     if (84.0D >= latitude && latitude >= 72.0D)
/* 212 */       return 'X'; 
/* 213 */     if (72.0D > latitude && latitude >= 64.0D)
/* 214 */       return 'W'; 
/* 215 */     if (64.0D > latitude && latitude >= 56.0D)
/* 216 */       return 'V'; 
/* 217 */     if (56.0D > latitude && latitude >= 48.0D)
/* 218 */       return 'U'; 
/* 219 */     if (48.0D > latitude && latitude >= 40.0D)
/* 220 */       return 'T'; 
/* 221 */     if (40.0D > latitude && latitude >= 32.0D)
/* 222 */       return 'S'; 
/* 223 */     if (32.0D > latitude && latitude >= 24.0D)
/* 224 */       return 'R'; 
/* 225 */     if (24.0D > latitude && latitude >= 16.0D)
/* 226 */       return 'Q'; 
/* 227 */     if (16.0D > latitude && latitude >= 8.0D)
/* 228 */       return 'P'; 
/* 229 */     if (8.0D > latitude && latitude >= 0.0D)
/* 230 */       return 'N'; 
/* 231 */     if (0.0D > latitude && latitude >= -8.0D)
/* 232 */       return 'M'; 
/* 233 */     if (-8.0D > latitude && latitude >= -16.0D)
/* 234 */       return 'L'; 
/* 235 */     if (-16.0D > latitude && latitude >= -24.0D)
/* 236 */       return 'K'; 
/* 237 */     if (-24.0D > latitude && latitude >= -32.0D)
/* 238 */       return 'J'; 
/* 239 */     if (-32.0D > latitude && latitude >= -40.0D)
/* 240 */       return 'H'; 
/* 241 */     if (-40.0D > latitude && latitude >= -48.0D)
/* 242 */       return 'G'; 
/* 243 */     if (-48.0D > latitude && latitude >= -56.0D)
/* 244 */       return 'F'; 
/* 245 */     if (-56.0D > latitude && latitude >= -64.0D)
/* 246 */       return 'E'; 
/* 247 */     if (-64.0D > latitude && latitude >= -72.0D)
/* 248 */       return 'D'; 
/* 249 */     if (-72.0D > latitude && latitude >= -80.0D)
/* 250 */       return 'C'; 
/* 252 */     return 'Z';
/*     */   }
/*     */   
/*     */   public String toString() {
/* 263 */     return this.lngZone + Character.toString(this.latZone) + " " + this.easting + " " + this.northing;
/*     */   }
/*     */   
/*     */   public double getEasting() {
/* 275 */     return this.easting;
/*     */   }
/*     */   
/*     */   public double getNorthing() {
/* 286 */     return this.northing;
/*     */   }
/*     */   
/*     */   public char getLatZone() {
/* 297 */     return this.latZone;
/*     */   }
/*     */   
/*     */   public int getLngZone() {
/* 308 */     return this.lngZone;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar\\uk\me\jstott\jcoord\UTMRef.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */