/*     */ package uk.me.jstott.jcoord;
/*     */ 
/*     */ public class LatLng {
/*     */   private double lat;
/*     */   
/*     */   private double lng;
/*     */   
/*     */   public LatLng(double lat, double lng) {
/*  37 */     this.lat = lat;
/*  38 */     this.lng = lng;
/*     */   }
/*     */   
/*     */   public String toString() {
/*  49 */     return "(" + this.lat + ", " + this.lng + ")";
/*     */   }
/*     */   
/*     */   public OSRef toOSRef() {
/*  61 */     RefEll airy1830 = new RefEll(6377563.396D, 6356256.909D);
/*  62 */     double OSGB_F0 = 0.9996012717D;
/*  63 */     double N0 = -100000.0D;
/*  64 */     double E0 = 400000.0D;
/*  65 */     double phi0 = Math.toRadians(49.0D);
/*  66 */     double lambda0 = Math.toRadians(-2.0D);
/*  67 */     double a = airy1830.getMaj();
/*  68 */     double b = airy1830.getMin();
/*  69 */     double eSquared = airy1830.getEcc();
/*  70 */     double phi = Math.toRadians(getLat());
/*  71 */     double lambda = Math.toRadians(getLng());
/*  72 */     double E = 0.0D;
/*  73 */     double N = 0.0D;
/*  74 */     double n = (a - b) / (a + b);
/*  75 */     double v = a * OSGB_F0 * Math.pow(1.0D - eSquared * Util.sinSquared(phi), -0.5D);
/*  77 */     double rho = a * OSGB_F0 * (1.0D - eSquared) * Math.pow(1.0D - eSquared * Util.sinSquared(phi), -1.5D);
/*  80 */     double etaSquared = v / rho - 1.0D;
/*  81 */     double M = b * OSGB_F0 * ((1.0D + n + 1.25D * n * n + 1.25D * n * n * n) * (phi - phi0) - (3.0D * n + 3.0D * n * n + 2.625D * n * n * n) * Math.sin(phi - phi0) * Math.cos(phi + phi0) + (1.875D * n * n + 1.875D * n * n * n) * Math.sin(2.0D * (phi - phi0)) * Math.cos(2.0D * (phi + phi0)) - 1.4583333333333333D * n * n * n * Math.sin(3.0D * (phi - phi0)) * Math.cos(3.0D * (phi + phi0)));
/*  90 */     double I = M + N0;
/*  91 */     double II = v / 2.0D * Math.sin(phi) * Math.cos(phi);
/*  92 */     double III = v / 24.0D * Math.sin(phi) * Math.pow(Math.cos(phi), 3.0D) * (5.0D - Util.tanSquared(phi) + 9.0D * etaSquared);
/*  95 */     double IIIA = v / 720.0D * Math.sin(phi) * Math.pow(Math.cos(phi), 5.0D) * (61.0D - 58.0D * Util.tanSquared(phi) + Math.pow(Math.tan(phi), 4.0D));
/* 101 */     double IV = v * Math.cos(phi);
/* 102 */     double V = v / 6.0D * Math.pow(Math.cos(phi), 3.0D) * (v / rho - Util.tanSquared(phi));
/* 105 */     double VI = v / 120.0D * Math.pow(Math.cos(phi), 5.0D) * (5.0D - 18.0D * Util.tanSquared(phi) + Math.pow(Math.tan(phi), 4.0D) + 14.0D * etaSquared - 58.0D * Util.tanSquared(phi) * etaSquared);
/* 112 */     N = I + II * Math.pow(lambda - lambda0, 2.0D) + III * Math.pow(lambda - lambda0, 4.0D) + IIIA * Math.pow(lambda - lambda0, 6.0D);
/* 116 */     E = E0 + IV * (lambda - lambda0) + V * Math.pow(lambda - lambda0, 3.0D) + VI * Math.pow(lambda - lambda0, 5.0D);
/* 120 */     return new OSRef(E, N);
/*     */   }
/*     */   
/*     */   public UTMRef toUTMRef() {
/* 131 */     double UTM_F0 = 0.9996D;
/* 132 */     double a = RefEll.WGS84.getMaj();
/* 133 */     double eSquared = RefEll.WGS84.getEcc();
/* 134 */     double longitude = this.lng;
/* 135 */     double latitude = this.lat;
/* 137 */     double latitudeRad = latitude * 0.017453292519943295D;
/* 138 */     double longitudeRad = longitude * 0.017453292519943295D;
/* 139 */     int longitudeZone = (int)Math.floor((longitude + 180.0D) / 6.0D) + 1;
/* 142 */     if (latitude >= 56.0D && latitude < 64.0D && longitude >= 3.0D && longitude < 12.0D)
/* 144 */       longitudeZone = 32; 
/* 148 */     if (latitude >= 72.0D && latitude < 84.0D)
/* 149 */       if (longitude >= 0.0D && longitude < 9.0D) {
/* 150 */         longitudeZone = 31;
/* 151 */       } else if (longitude >= 9.0D && longitude < 21.0D) {
/* 152 */         longitudeZone = 33;
/* 153 */       } else if (longitude >= 21.0D && longitude < 33.0D) {
/* 154 */         longitudeZone = 35;
/* 155 */       } else if (longitude >= 33.0D && longitude < 42.0D) {
/* 156 */         longitudeZone = 37;
/*     */       }  
/* 160 */     double longitudeOrigin = ((longitudeZone - 1) * 6 - 180 + 3);
/* 161 */     double longitudeOriginRad = longitudeOrigin * 0.017453292519943295D;
/* 163 */     char UTMZone = UTMRef.getUTMLatitudeZoneLetter(latitude);
/* 165 */     double ePrimeSquared = eSquared / (1.0D - eSquared);
/* 167 */     double n = a / Math.sqrt(1.0D - eSquared * Math.sin(latitudeRad) * Math.sin(latitudeRad));
/* 171 */     double t = Math.tan(latitudeRad) * Math.tan(latitudeRad);
/* 172 */     double c = ePrimeSquared * Math.cos(latitudeRad) * Math.cos(latitudeRad);
/* 173 */     double A = Math.cos(latitudeRad) * (longitudeRad - longitudeOriginRad);
/* 175 */     double M = a * ((1.0D - eSquared / 4.0D - 3.0D * eSquared * eSquared / 64.0D - 5.0D * eSquared * eSquared * eSquared / 256.0D) * latitudeRad - (3.0D * eSquared / 8.0D + 3.0D * eSquared * eSquared / 32.0D + 45.0D * eSquared * eSquared * eSquared / 1024.0D) * Math.sin(2.0D * latitudeRad) + (15.0D * eSquared * eSquared / 256.0D + 45.0D * eSquared * eSquared * eSquared / 1024.0D) * Math.sin(4.0D * latitudeRad) - 35.0D * eSquared * eSquared * eSquared / 3072.0D * Math.sin(6.0D * latitudeRad));
/* 188 */     double UTMEasting = UTM_F0 * n * (A + (1.0D - t + c) * Math.pow(A, 3.0D) / 6.0D + (5.0D - 18.0D * t + t * t + 72.0D * c - 58.0D * ePrimeSquared) * Math.pow(A, 5.0D) / 120.0D) + 500000.0D;
/* 195 */     double UTMNorthing = UTM_F0 * (M + n * Math.tan(latitudeRad) * (A * A / 2.0D + (5.0D - t + 9.0D * c + 4.0D * c * c) * Math.pow(A, 4.0D) / 24.0D + (61.0D - 58.0D * t + t * t + 600.0D * c - 330.0D * ePrimeSquared) * Math.pow(A, 6.0D) / 720.0D));
/* 203 */     if (latitude < 0.0D)
/* 204 */       UTMNorthing += 1.0E7D; 
/* 207 */     return new UTMRef(UTMEasting, UTMNorthing, UTMZone, longitudeZone);
/*     */   }
/*     */   
/*     */   public void toWGS84() {
/* 218 */     double a = RefEll.AIRY_1830.getMaj();
/* 219 */     double eSquared = RefEll.AIRY_1830.getEcc();
/* 220 */     double phi = Math.toRadians(this.lat);
/* 221 */     double lambda = Math.toRadians(this.lng);
/* 222 */     double v = a / Math.sqrt(1.0D - eSquared * Util.sinSquared(phi));
/* 223 */     double H = 0.0D;
/* 224 */     double x = (v + H) * Math.cos(phi) * Math.cos(lambda);
/* 225 */     double y = (v + H) * Math.cos(phi) * Math.sin(lambda);
/* 226 */     double z = ((1.0D - eSquared) * v + H) * Math.sin(phi);
/* 228 */     double tx = 446.448D;
/* 229 */     double ty = -124.157D;
/* 230 */     double tz = 542.06D;
/* 231 */     double s = -2.04894E-5D;
/* 232 */     double rx = Math.toRadians(4.172222E-5D);
/* 233 */     double ry = Math.toRadians(6.861111E-5D);
/* 234 */     double rz = Math.toRadians(2.3391666E-4D);
/* 236 */     double xB = tx + x * (1.0D + s) + -rx * y + ry * z;
/* 237 */     double yB = ty + rz * x + y * (1.0D + s) + -rx * z;
/* 238 */     double zB = tz + -ry * x + rx * y + z * (1.0D + s);
/* 240 */     a = RefEll.WGS84.getMaj();
/* 241 */     eSquared = RefEll.WGS84.getEcc();
/* 243 */     double lambdaB = Math.toDegrees(Math.atan(yB / xB));
/* 244 */     double p = Math.sqrt(xB * xB + yB * yB);
/* 245 */     double phiN = Math.atan(zB / p * (1.0D - eSquared));
/* 246 */     for (int i = 1; i < 10; i++) {
/* 247 */       v = a / Math.sqrt(1.0D - eSquared * Util.sinSquared(phiN));
/* 248 */       double phiN1 = Math.atan((zB + eSquared * v * Math.sin(phiN)) / p);
/* 249 */       phiN = phiN1;
/*     */     } 
/* 252 */     double phiB = Math.toDegrees(phiN);
/* 254 */     this.lat = phiB;
/* 255 */     this.lng = lambdaB;
/*     */   }
/*     */   
/*     */   public void toOSGB36() {
/* 266 */     RefEll wgs84 = new RefEll(6378137.0D, 6356752.3141D);
/* 267 */     double a = wgs84.getMaj();
/* 268 */     double eSquared = wgs84.getEcc();
/* 269 */     double phi = Math.toRadians(this.lat);
/* 270 */     double lambda = Math.toRadians(this.lng);
/* 271 */     double v = a / Math.sqrt(1.0D - eSquared * Util.sinSquared(phi));
/* 272 */     double H = 0.0D;
/* 273 */     double x = (v + H) * Math.cos(phi) * Math.cos(lambda);
/* 274 */     double y = (v + H) * Math.cos(phi) * Math.sin(lambda);
/* 275 */     double z = ((1.0D - eSquared) * v + H) * Math.sin(phi);
/* 277 */     double tx = -446.448D;
/* 278 */     double ty = 124.157D;
/* 279 */     double tz = -542.06D;
/* 280 */     double s = 2.04894E-5D;
/* 281 */     double rx = Math.toRadians(-4.172222E-5D);
/* 282 */     double ry = Math.toRadians(-6.861111E-5D);
/* 283 */     double rz = Math.toRadians(-2.3391666E-4D);
/* 285 */     double xB = tx + x * (1.0D + s) + -rx * y + ry * z;
/* 286 */     double yB = ty + rz * x + y * (1.0D + s) + -rx * z;
/* 287 */     double zB = tz + -ry * x + rx * y + z * (1.0D + s);
/* 289 */     RefEll airy1830 = new RefEll(6377563.396D, 6356256.909D);
/* 290 */     a = airy1830.getMaj();
/* 291 */     eSquared = airy1830.getEcc();
/* 293 */     double lambdaB = Math.toDegrees(Math.atan(yB / xB));
/* 294 */     double p = Math.sqrt(xB * xB + yB * yB);
/* 295 */     double phiN = Math.atan(zB / p * (1.0D - eSquared));
/* 296 */     for (int i = 1; i < 10; i++) {
/* 297 */       v = a / Math.sqrt(1.0D - eSquared * Util.sinSquared(phiN));
/* 298 */       double phiN1 = Math.atan((zB + eSquared * v * Math.sin(phiN)) / p);
/* 299 */       phiN = phiN1;
/*     */     } 
/* 302 */     double phiB = Math.toDegrees(phiN);
/* 304 */     this.lat = phiB;
/* 305 */     this.lng = lambdaB;
/*     */   }
/*     */   
/*     */   public double distance(LatLng ll) {
/* 318 */     double er = 6366.707D;
/* 320 */     double latFrom = Math.toRadians(getLat());
/* 321 */     double latTo = Math.toRadians(ll.getLat());
/* 322 */     double lngFrom = Math.toRadians(getLng());
/* 323 */     double lngTo = Math.toRadians(ll.getLng());
/* 325 */     double d = Math.acos(Math.sin(latFrom) * Math.sin(latTo) + Math.cos(latFrom) * Math.cos(latTo) * Math.cos(lngTo - lngFrom)) * er;
/* 330 */     return d;
/*     */   }
/*     */   
/*     */   public double getLat() {
/* 341 */     return this.lat;
/*     */   }
/*     */   
/*     */   public double getLng() {
/* 352 */     return this.lng;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar\\uk\me\jstott\jcoord\LatLng.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */