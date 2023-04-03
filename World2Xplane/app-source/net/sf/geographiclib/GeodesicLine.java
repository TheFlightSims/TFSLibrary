/*     */ package net.sf.geographiclib;
/*     */ 
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ 
/*     */ public class GeodesicLine {
/*     */   private static final int nC1_ = 6;
/*     */   
/*     */   private static final int nC1p_ = 6;
/*     */   
/*     */   private static final int nC2_ = 6;
/*     */   
/*     */   private static final int nC3_ = 6;
/*     */   
/*     */   private static final int nC4_ = 6;
/*     */   
/*     */   private double _lat1;
/*     */   
/*     */   private double _lon1;
/*     */   
/*     */   private double _azi1;
/*     */   
/*     */   private double _a;
/*     */   
/*     */   private double _f;
/*     */   
/*     */   private double _b;
/*     */   
/*     */   private double _c2;
/*     */   
/*     */   private double _f1;
/*     */   
/*     */   private double _salp0;
/*     */   
/*     */   private double _calp0;
/*     */   
/*     */   private double _k2;
/*     */   
/*     */   private double _salp1;
/*     */   
/*     */   private double _calp1;
/*     */   
/*     */   private double _ssig1;
/*     */   
/*     */   private double _csig1;
/*     */   
/*     */   private double _dn1;
/*     */   
/*     */   private double _stau1;
/*     */   
/*     */   private double _ctau1;
/*     */   
/*     */   private double _somg1;
/*     */   
/*     */   private double _comg1;
/*     */   
/*     */   private double _A1m1;
/*     */   
/*     */   private double _A2m1;
/*     */   
/*     */   private double _A3c;
/*     */   
/*     */   private double _B11;
/*     */   
/*     */   private double _B21;
/*     */   
/*     */   private double _B31;
/*     */   
/*     */   private double _A4;
/*     */   
/*     */   private double _B41;
/*     */   
/*     */   private double[] _C1a;
/*     */   
/*     */   private double[] _C1pa;
/*     */   
/*     */   private double[] _C2a;
/*     */   
/*     */   private double[] _C3a;
/*     */   
/*     */   private double[] _C4a;
/*     */   
/*     */   private int _caps;
/*     */   
/*     */   public GeodesicLine(Geodesic g, double lat1, double lon1, double azi1) {
/* 134 */     this(g, lat1, lon1, azi1, 32671);
/*     */   }
/*     */   
/*     */   public GeodesicLine(Geodesic g, double lat1, double lon1, double azi1, int caps) {
/* 183 */     this._a = g._a;
/* 184 */     this._f = g._f;
/* 185 */     this._b = g._b;
/* 186 */     this._c2 = g._c2;
/* 187 */     this._f1 = g._f1;
/* 189 */     this._caps = caps | 0x80 | 0x200;
/* 192 */     azi1 = Geodesic.AngRound(GeoMath.AngNormalize(azi1));
/* 193 */     lon1 = GeoMath.AngNormalize(lon1);
/* 194 */     this._lat1 = lat1;
/* 195 */     this._lon1 = lon1;
/* 196 */     this._azi1 = azi1;
/* 198 */     double alp1 = azi1 * 0.017453292519943295D;
/* 201 */     this._salp1 = (azi1 == -180.0D) ? 0.0D : FastMath.sin(alp1);
/* 202 */     this._calp1 = (FastMath.abs(azi1) == 90.0D) ? 0.0D : FastMath.cos(alp1);
/* 204 */     double phi = lat1 * 0.017453292519943295D;
/* 206 */     double sbet1 = this._f1 * FastMath.sin(phi);
/* 207 */     double cbet1 = (FastMath.abs(lat1) == 90.0D) ? Geodesic.tiny_ : FastMath.cos(phi);
/* 208 */     Pair p = Geodesic.SinCosNorm(sbet1, cbet1);
/* 209 */     sbet1 = p.first;
/* 209 */     cbet1 = p.second;
/* 210 */     this._dn1 = FastMath.sqrt(1.0D + g._ep2 * GeoMath.sq(sbet1));
/* 213 */     this._salp0 = this._salp1 * cbet1;
/* 216 */     this._calp0 = GeoMath.hypot(this._calp1, this._salp1 * sbet1);
/* 226 */     this._ssig1 = sbet1;
/* 226 */     this._somg1 = this._salp0 * sbet1;
/* 227 */     this._csig1 = this._comg1 = (sbet1 != 0.0D || this._calp1 != 0.0D) ? (cbet1 * this._calp1) : 1.0D;
/* 228 */     p = Geodesic.SinCosNorm(this._ssig1, this._csig1);
/* 229 */     this._ssig1 = p.first;
/* 229 */     this._csig1 = p.second;
/* 232 */     this._k2 = GeoMath.sq(this._calp0) * g._ep2;
/* 233 */     double eps = this._k2 / (2.0D * (1.0D + FastMath.sqrt(1.0D + this._k2)) + this._k2);
/* 235 */     if ((this._caps & 0x1) != 0) {
/* 236 */       this._A1m1 = Geodesic.A1m1f(eps);
/* 237 */       this._C1a = new double[7];
/* 238 */       Geodesic.C1f(eps, this._C1a);
/* 239 */       this._B11 = Geodesic.SinCosSeries(true, this._ssig1, this._csig1, this._C1a);
/* 240 */       double s = FastMath.sin(this._B11), c = FastMath.cos(this._B11);
/* 242 */       this._stau1 = this._ssig1 * c + this._csig1 * s;
/* 243 */       this._ctau1 = this._csig1 * c - this._ssig1 * s;
/*     */     } 
/* 248 */     if ((this._caps & 0x2) != 0) {
/* 249 */       this._C1pa = new double[7];
/* 250 */       Geodesic.C1pf(eps, this._C1pa);
/*     */     } 
/* 253 */     if ((this._caps & 0x4) != 0) {
/* 254 */       this._C2a = new double[7];
/* 255 */       this._A2m1 = Geodesic.A2m1f(eps);
/* 256 */       Geodesic.C2f(eps, this._C2a);
/* 257 */       this._B21 = Geodesic.SinCosSeries(true, this._ssig1, this._csig1, this._C2a);
/*     */     } 
/* 260 */     if ((this._caps & 0x8) != 0) {
/* 261 */       this._C3a = new double[6];
/* 262 */       g.C3f(eps, this._C3a);
/* 263 */       this._A3c = -this._f * this._salp0 * g.A3f(eps);
/* 264 */       this._B31 = Geodesic.SinCosSeries(true, this._ssig1, this._csig1, this._C3a);
/*     */     } 
/* 267 */     if ((this._caps & 0x10) != 0) {
/* 268 */       this._C4a = new double[6];
/* 269 */       g.C4f(eps, this._C4a);
/* 271 */       this._A4 = GeoMath.sq(this._a) * this._calp0 * this._salp0 * g._e2;
/* 272 */       this._B41 = Geodesic.SinCosSeries(false, this._ssig1, this._csig1, this._C4a);
/*     */     } 
/*     */   }
/*     */   
/*     */   private GeodesicLine() {
/* 284 */     this._caps = 0;
/*     */   }
/*     */   
/*     */   public GeodesicData Position(double s12) {
/* 304 */     return Position(false, s12, 904);
/*     */   }
/*     */   
/*     */   public GeodesicData Position(double s12, int outmask) {
/* 324 */     return Position(false, s12, outmask);
/*     */   }
/*     */   
/*     */   public GeodesicData ArcPosition(double a12) {
/* 345 */     return Position(true, a12, 1929);
/*     */   }
/*     */   
/*     */   public GeodesicData ArcPosition(double a12, int outmask) {
/* 366 */     return Position(true, a12, outmask);
/*     */   }
/*     */   
/*     */   public GeodesicData Position(boolean arcmode, double s12_a12, int outmask) {
/*     */     double sig12, ssig12, csig12;
/* 409 */     outmask &= this._caps & 0x7F80;
/* 410 */     GeodesicData r = new GeodesicData();
/* 411 */     if (!Init() || (!arcmode && (this._caps & 0x803 & 0x7F80) == 0))
/* 415 */       return r; 
/* 416 */     r.lat1 = this._lat1;
/* 416 */     r.lon1 = this._lon1;
/* 416 */     r.azi1 = this._azi1;
/* 419 */     double B12 = 0.0D, AB1 = 0.0D;
/* 420 */     if (arcmode) {
/* 422 */       r.a12 = s12_a12;
/* 423 */       sig12 = s12_a12 * 0.017453292519943295D;
/* 424 */       double s12a = FastMath.abs(s12_a12);
/* 425 */       s12a -= 180.0D * FastMath.floor(s12a / 180.0D);
/* 426 */       ssig12 = (s12a == 0.0D) ? 0.0D : FastMath.sin(sig12);
/* 427 */       csig12 = (s12a == 90.0D) ? 0.0D : FastMath.cos(sig12);
/*     */     } else {
/* 430 */       r.s12 = s12_a12;
/* 432 */       double tau12 = s12_a12 / this._b * (1.0D + this._A1m1);
/* 433 */       double s = FastMath.sin(tau12);
/* 434 */       double c = FastMath.cos(tau12);
/* 436 */       B12 = -Geodesic.SinCosSeries(true, this._stau1 * c + this._ctau1 * s, this._ctau1 * c - this._stau1 * s, this._C1pa);
/* 440 */       sig12 = tau12 - B12 - this._B11;
/* 441 */       r.a12 = sig12 / 0.017453292519943295D;
/* 442 */       ssig12 = FastMath.sin(sig12);
/* 442 */       csig12 = FastMath.cos(sig12);
/* 443 */       if (FastMath.abs(this._f) > 0.01D) {
/* 466 */         double d1 = this._ssig1 * csig12 + this._csig1 * ssig12;
/* 467 */         double d2 = this._csig1 * csig12 - this._ssig1 * ssig12;
/* 468 */         B12 = Geodesic.SinCosSeries(true, d1, d2, this._C1a);
/* 469 */         double serr = (1.0D + this._A1m1) * (sig12 + B12 - this._B11) - s12_a12 / this._b;
/* 470 */         sig12 -= serr / FastMath.sqrt(1.0D + this._k2 * GeoMath.sq(d1));
/* 471 */         ssig12 = FastMath.sin(sig12);
/* 471 */         csig12 = FastMath.cos(sig12);
/*     */       } 
/*     */     } 
/* 479 */     double ssig2 = this._ssig1 * csig12 + this._csig1 * ssig12;
/* 480 */     double csig2 = this._csig1 * csig12 - this._ssig1 * ssig12;
/* 481 */     double dn2 = FastMath.sqrt(1.0D + this._k2 * GeoMath.sq(ssig2));
/* 482 */     if ((outmask & 0x3405) != 0) {
/* 484 */       if (arcmode || FastMath.abs(this._f) > 0.01D)
/* 485 */         B12 = Geodesic.SinCosSeries(true, ssig2, csig2, this._C1a); 
/* 486 */       AB1 = (1.0D + this._A1m1) * (B12 - this._B11);
/*     */     } 
/* 489 */     double sbet2 = this._calp0 * ssig2;
/* 491 */     double cbet2 = GeoMath.hypot(this._salp0, this._calp0 * csig2);
/* 492 */     if (cbet2 == 0.0D)
/* 494 */       cbet2 = csig2 = Geodesic.tiny_; 
/* 496 */     double somg2 = this._salp0 * ssig2, comg2 = csig2;
/* 498 */     double salp2 = this._salp0, calp2 = this._calp0 * csig2;
/* 500 */     double omg12 = FastMath.atan2(somg2 * this._comg1 - comg2 * this._somg1, comg2 * this._comg1 + somg2 * this._somg1);
/* 503 */     if ((outmask & 0x401) != 0 && arcmode)
/* 504 */       r.s12 = this._b * ((1.0D + this._A1m1) * sig12 + AB1); 
/* 506 */     if ((outmask & 0x108) != 0) {
/* 507 */       double lam12 = omg12 + this._A3c * (sig12 + Geodesic.SinCosSeries(true, ssig2, csig2, this._C3a) - this._B31);
/* 510 */       double lon12 = lam12 / 0.017453292519943295D;
/* 513 */       lon12 = GeoMath.AngNormalize2(lon12);
/* 514 */       r.lon2 = GeoMath.AngNormalize(this._lon1 + lon12);
/*     */     } 
/* 517 */     if ((outmask & 0x80) != 0)
/* 518 */       r.lat2 = FastMath.atan2(sbet2, this._f1 * cbet2) / 0.017453292519943295D; 
/* 520 */     if ((outmask & 0x200) != 0)
/* 522 */       r.azi2 = 0.0D - FastMath.atan2(-salp2, calp2) / 0.017453292519943295D; 
/* 524 */     if ((outmask & 0x3005) != 0) {
/* 527 */       double B22 = Geodesic.SinCosSeries(true, ssig2, csig2, this._C2a);
/* 528 */       double AB2 = (1.0D + this._A2m1) * (B22 - this._B21);
/* 529 */       double J12 = (this._A1m1 - this._A2m1) * sig12 + AB1 - AB2;
/* 530 */       if ((outmask & 0x1005) != 0)
/* 533 */         r.m12 = this._b * (dn2 * this._csig1 * ssig2 - this._dn1 * this._ssig1 * csig2 - this._csig1 * csig2 * J12); 
/* 535 */       if ((outmask & 0x2005) != 0) {
/* 536 */         double t = this._k2 * (ssig2 - this._ssig1) * (ssig2 + this._ssig1) / (this._dn1 + dn2);
/* 537 */         r.M12 = csig12 + (t * ssig2 - csig2 * J12) * this._ssig1 / this._dn1;
/* 538 */         r.M21 = csig12 - (t * this._ssig1 - this._csig1 * J12) * ssig2 / dn2;
/*     */       } 
/*     */     } 
/* 542 */     if ((outmask & 0x4010) != 0) {
/* 544 */       double salp12, calp12, B42 = Geodesic.SinCosSeries(false, ssig2, csig2, this._C4a);
/* 546 */       if (this._calp0 == 0.0D || this._salp0 == 0.0D) {
/* 548 */         salp12 = salp2 * this._calp1 - calp2 * this._salp1;
/* 549 */         calp12 = calp2 * this._calp1 + salp2 * this._salp1;
/* 554 */         if (salp12 == 0.0D && calp12 < 0.0D) {
/* 555 */           salp12 = Geodesic.tiny_ * this._calp1;
/* 556 */           calp12 = -1.0D;
/*     */         } 
/*     */       } else {
/* 567 */         salp12 = this._calp0 * this._salp0 * ((csig12 <= 0.0D) ? (this._csig1 * (1.0D - csig12) + ssig12 * this._ssig1) : (ssig12 * (this._csig1 * ssig12 / (1.0D + csig12) + this._ssig1)));
/* 570 */         calp12 = GeoMath.sq(this._salp0) + GeoMath.sq(this._calp0) * this._csig1 * csig2;
/*     */       } 
/* 572 */       r.S12 = this._c2 * FastMath.atan2(salp12, calp12) + this._A4 * (B42 - this._B41);
/*     */     } 
/* 575 */     return r;
/*     */   }
/*     */   
/*     */   private boolean Init() {
/* 581 */     return (this._caps != 0);
/*     */   }
/*     */   
/*     */   public double Latitude() {
/* 587 */     return Init() ? this._lat1 : Double.NaN;
/*     */   }
/*     */   
/*     */   public double Longitude() {
/* 593 */     return Init() ? this._lon1 : Double.NaN;
/*     */   }
/*     */   
/*     */   public double Azimuth() {
/* 599 */     return Init() ? this._azi1 : Double.NaN;
/*     */   }
/*     */   
/*     */   public double EquatorialAzimuth() {
/* 606 */     return Init() ? (FastMath.atan2(this._salp0, this._calp0) / 0.017453292519943295D) : Double.NaN;
/*     */   }
/*     */   
/*     */   public double EquatorialArc() {
/* 615 */     return Init() ? (FastMath.atan2(this._ssig1, this._csig1) / 0.017453292519943295D) : Double.NaN;
/*     */   }
/*     */   
/*     */   public double MajorRadius() {
/* 624 */     return Init() ? this._a : Double.NaN;
/*     */   }
/*     */   
/*     */   public double Flattening() {
/* 631 */     return Init() ? this._f : Double.NaN;
/*     */   }
/*     */   
/*     */   public int Capabilities() {
/* 637 */     return this._caps;
/*     */   }
/*     */   
/*     */   public boolean Capabilities(int testcaps) {
/* 644 */     testcaps &= 0x7F80;
/* 645 */     return ((this._caps & testcaps) == testcaps);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\net\sf\geographiclib\GeodesicLine.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */