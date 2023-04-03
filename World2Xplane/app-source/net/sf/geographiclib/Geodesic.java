/*      */ package net.sf.geographiclib;
/*      */ 
/*      */ import org.apache.commons.math3.util.FastMath;
/*      */ 
/*      */ public class Geodesic {
/*      */   protected static final int GEOGRAPHICLIB_GEODESIC_ORDER = 6;
/*      */   
/*      */   protected static final int nA1_ = 6;
/*      */   
/*      */   protected static final int nC1_ = 6;
/*      */   
/*      */   protected static final int nC1p_ = 6;
/*      */   
/*      */   protected static final int nA2_ = 6;
/*      */   
/*      */   protected static final int nC2_ = 6;
/*      */   
/*      */   protected static final int nA3_ = 6;
/*      */   
/*      */   protected static final int nA3x_ = 6;
/*      */   
/*      */   protected static final int nC3_ = 6;
/*      */   
/*      */   protected static final int nC3x_ = 15;
/*      */   
/*      */   protected static final int nC4_ = 6;
/*      */   
/*      */   protected static final int nC4x_ = 21;
/*      */   
/*      */   private static final int maxit1_ = 20;
/*      */   
/*      */   private static final int maxit2_ = 83;
/*      */   
/*  244 */   protected static final double tiny_ = FastMath.sqrt(GeoMath.min);
/*      */   
/*  245 */   private static final double tol0_ = GeoMath.epsilon;
/*      */   
/*  249 */   private static final double tol1_ = 200.0D * tol0_;
/*      */   
/*  250 */   private static final double tol2_ = FastMath.sqrt(tol0_);
/*      */   
/*  252 */   private static final double tolb_ = tol0_ * tol2_;
/*      */   
/*  253 */   private static final double xthresh_ = 1000.0D * tol2_;
/*      */   
/*      */   protected double _a;
/*      */   
/*      */   protected double _f;
/*      */   
/*      */   protected double _f1;
/*      */   
/*      */   protected double _e2;
/*      */   
/*      */   protected double _ep2;
/*      */   
/*      */   protected double _b;
/*      */   
/*      */   protected double _c2;
/*      */   
/*      */   private double _n;
/*      */   
/*      */   private double _etol2;
/*      */   
/*      */   private double[] _A3x;
/*      */   
/*      */   private double[] _C3x;
/*      */   
/*      */   private double[] _C4x;
/*      */   
/*      */   protected static double AngRound(double x) {
/*  261 */     double z = 0.0625D;
/*  262 */     double y = FastMath.abs(x);
/*  264 */     y = (y < 0.0625D) ? (0.0625D - 0.0625D - y) : y;
/*  265 */     return (x < 0.0D) ? -y : y;
/*      */   }
/*      */   
/*      */   protected static Pair SinCosNorm(double sinx, double cosx) {
/*  269 */     double r = GeoMath.hypot(sinx, cosx);
/*  270 */     return new Pair(sinx / r, cosx / r);
/*      */   }
/*      */   
/*      */   public Geodesic(double a, double f) {
/*  288 */     this._a = a;
/*  289 */     this._f = (f <= 1.0D) ? f : (1.0D / f);
/*  290 */     this._f1 = 1.0D - this._f;
/*  291 */     this._e2 = this._f * (2.0D - this._f);
/*  292 */     this._ep2 = this._e2 / GeoMath.sq(this._f1);
/*  293 */     this._n = this._f / (2.0D - this._f);
/*  294 */     this._b = this._a * this._f1;
/*  295 */     this._c2 = (GeoMath.sq(this._a) + GeoMath.sq(this._b) * ((this._e2 == 0.0D) ? 1.0D : (((this._e2 > 0.0D) ? GeoMath.atanh(FastMath.sqrt(this._e2)) : FastMath.atan(FastMath.sqrt(-this._e2))) / FastMath.sqrt(FastMath.abs(this._e2))))) / 2.0D;
/*  310 */     this._etol2 = 0.1D * tol2_ / FastMath.sqrt(FastMath.max(0.001D, FastMath.abs(this._f)) * FastMath.min(1.0D, 1.0D - this._f / 2.0D) / 2.0D);
/*  313 */     if (!GeoMath.isfinite(this._a) || this._a <= 0.0D)
/*  314 */       throw new GeographicErr("Major radius is not positive"); 
/*  315 */     if (!GeoMath.isfinite(this._b) || this._b <= 0.0D)
/*  316 */       throw new GeographicErr("Minor radius is not positive"); 
/*  317 */     this._A3x = new double[6];
/*  318 */     this._C3x = new double[15];
/*  319 */     this._C4x = new double[21];
/*  321 */     A3coeff();
/*  322 */     C3coeff();
/*  323 */     C4coeff();
/*      */   }
/*      */   
/*      */   public GeodesicData Direct(double lat1, double lon1, double azi1, double s12) {
/*  353 */     return Direct(lat1, lon1, azi1, false, s12, 904);
/*      */   }
/*      */   
/*      */   public GeodesicData Direct(double lat1, double lon1, double azi1, double s12, int outmask) {
/*  377 */     return Direct(lat1, lon1, azi1, false, s12, outmask);
/*      */   }
/*      */   
/*      */   public GeodesicData ArcDirect(double lat1, double lon1, double azi1, double a12) {
/*  407 */     return Direct(lat1, lon1, azi1, true, a12, 1929);
/*      */   }
/*      */   
/*      */   public GeodesicData ArcDirect(double lat1, double lon1, double azi1, double a12, int outmask) {
/*  432 */     return Direct(lat1, lon1, azi1, true, a12, outmask);
/*      */   }
/*      */   
/*      */   public GeodesicData Direct(double lat1, double lon1, double azi1, boolean arcmode, double s12_a12, int outmask) {
/*  483 */     return (new GeodesicLine(this, lat1, lon1, azi1, outmask | (arcmode ? 0 : 2051))).Position(arcmode, s12_a12, outmask);
/*      */   }
/*      */   
/*      */   public GeodesicData Inverse(double lat1, double lon1, double lat2, double lon2) {
/*  518 */     return Inverse(lat1, lon1, lat2, lon2, 1537);
/*      */   }
/*      */   
/*      */   public GeodesicData Inverse(double lat1, double lon1, double lat2, double lon2, int outmask) {
/*  558 */     outmask &= 0x7F80;
/*  559 */     GeodesicData r = new GeodesicData();
/*  560 */     lon1 = GeoMath.AngNormalize(lon1);
/*  561 */     lon2 = GeoMath.AngNormalize(lon2);
/*  565 */     double lon12 = GeoMath.AngDiff(lon1, lon2);
/*  567 */     lon12 = AngRound(lon12);
/*  569 */     int lonsign = (lon12 >= 0.0D) ? 1 : -1;
/*  570 */     lon12 *= lonsign;
/*  572 */     lat1 = AngRound(lat1);
/*  573 */     lat2 = AngRound(lat2);
/*  575 */     r.lat1 = lat1;
/*  575 */     r.lon1 = lon1;
/*  575 */     r.lat2 = lat2;
/*  575 */     r.lon2 = lon2;
/*  577 */     int swapp = (FastMath.abs(lat1) >= FastMath.abs(lat2)) ? 1 : -1;
/*  578 */     if (swapp < 0) {
/*  579 */       lonsign *= -1;
/*  580 */       double t = lat1;
/*  580 */       lat1 = lat2;
/*  580 */       lat2 = t;
/*      */     } 
/*  583 */     int latsign = (lat1 < 0.0D) ? 1 : -1;
/*  584 */     lat1 *= latsign;
/*  585 */     lat2 *= latsign;
/*  599 */     double m12x = Double.NaN, s12x = m12x;
/*  601 */     double phi = lat1 * 0.017453292519943295D;
/*  603 */     double sbet1 = this._f1 * FastMath.sin(phi);
/*  604 */     double cbet1 = (lat1 == -90.0D) ? tiny_ : FastMath.cos(phi);
/*  605 */     Pair p = SinCosNorm(sbet1, cbet1);
/*  606 */     sbet1 = p.first;
/*  606 */     cbet1 = p.second;
/*  608 */     phi = lat2 * 0.017453292519943295D;
/*  610 */     double sbet2 = this._f1 * FastMath.sin(phi);
/*  611 */     double cbet2 = (FastMath.abs(lat2) == 90.0D) ? tiny_ : FastMath.cos(phi);
/*  612 */     p = SinCosNorm(sbet2, cbet2);
/*  613 */     sbet2 = p.first;
/*  613 */     cbet2 = p.second;
/*  623 */     if (cbet1 < -sbet1) {
/*  624 */       if (cbet2 == cbet1)
/*  625 */         sbet2 = (sbet2 < 0.0D) ? sbet1 : -sbet1; 
/*  627 */     } else if (FastMath.abs(sbet2) == -sbet1) {
/*  628 */       cbet2 = cbet1;
/*      */     } 
/*  632 */     double dn1 = FastMath.sqrt(1.0D + this._ep2 * GeoMath.sq(sbet1));
/*  633 */     double dn2 = FastMath.sqrt(1.0D + this._ep2 * GeoMath.sq(sbet2));
/*  636 */     double lam12 = lon12 * 0.017453292519943295D;
/*  637 */     double slam12 = (FastMath.abs(lon12) == 180.0D) ? 0.0D : FastMath.sin(lam12);
/*  638 */     double clam12 = FastMath.cos(lam12);
/*  641 */     double salp2 = Double.NaN, calp2 = salp2, salp1 = calp2, calp1 = salp1, sig12 = calp1, a12 = sig12;
/*  643 */     double[] C1a = new double[7];
/*  644 */     double[] C2a = new double[7];
/*  645 */     double[] C3a = new double[6];
/*  647 */     boolean meridian = (lat1 == -90.0D || slam12 == 0.0D);
/*  649 */     if (meridian) {
/*  654 */       calp1 = clam12;
/*  654 */       salp1 = slam12;
/*  655 */       calp2 = 1.0D;
/*  655 */       salp2 = 0.0D;
/*  659 */       double ssig1 = sbet1, csig1 = calp1 * cbet1;
/*  660 */       double ssig2 = sbet2, csig2 = calp2 * cbet2;
/*  663 */       sig12 = FastMath.atan2(FastMath.max(csig1 * ssig2 - ssig1 * csig2, 0.0D), csig1 * csig2 + ssig1 * ssig2);
/*  666 */       LengthsV v = Lengths(this._n, sig12, ssig1, csig1, dn1, ssig2, csig2, dn2, cbet1, cbet2, ((outmask & 0x2005) != 0), C1a, C2a);
/*  670 */       s12x = v.s12b;
/*  670 */       m12x = v.m12b;
/*  671 */       if ((outmask & 0x2005) != 0) {
/*  672 */         r.M12 = v.M12;
/*  672 */         r.M21 = v.M21;
/*      */       } 
/*  682 */       if (sig12 < 1.0D || m12x >= 0.0D) {
/*  683 */         m12x *= this._b;
/*  684 */         s12x *= this._b;
/*  685 */         a12 = sig12 / 0.017453292519943295D;
/*      */       } else {
/*  688 */         meridian = false;
/*      */       } 
/*      */     } 
/*  691 */     double omg12 = Double.NaN;
/*  692 */     if (!meridian && sbet1 == 0.0D && (this._f <= 0.0D || lam12 <= Math.PI - this._f * Math.PI)) {
/*  698 */       calp1 = calp2 = 0.0D;
/*  698 */       salp1 = salp2 = 1.0D;
/*  699 */       s12x = this._a * lam12;
/*  700 */       sig12 = omg12 = lam12 / this._f1;
/*  701 */       m12x = this._b * FastMath.sin(sig12);
/*  702 */       if ((outmask & 0x2005) != 0)
/*  703 */         r.M12 = r.M21 = FastMath.cos(sig12); 
/*  704 */       a12 = lon12 / this._f1;
/*  706 */     } else if (!meridian) {
/*  714 */       InverseStartV v = InverseStart(sbet1, cbet1, dn1, sbet2, cbet2, dn2, lam12, C1a, C2a);
/*  718 */       sig12 = v.sig12;
/*  719 */       salp1 = v.salp1;
/*  719 */       calp1 = v.calp1;
/*  720 */       salp2 = v.salp2;
/*  720 */       calp2 = v.calp2;
/*  721 */       double dnm = v.dnm;
/*  724 */       if (sig12 >= 0.0D) {
/*  726 */         s12x = sig12 * this._b * dnm;
/*  727 */         m12x = GeoMath.sq(dnm) * this._b * FastMath.sin(sig12 / dnm);
/*  728 */         if ((outmask & 0x2005) != 0)
/*  729 */           r.M12 = r.M21 = FastMath.cos(sig12 / dnm); 
/*  730 */         a12 = sig12 / 0.017453292519943295D;
/*  731 */         omg12 = lam12 / this._f1 * dnm;
/*      */       } else {
/*  746 */         double eps = Double.NaN, csig2 = eps, ssig2 = csig2, csig1 = ssig2, ssig1 = csig1;
/*  747 */         int numit = 0;
/*  749 */         double salp1a = tiny_, calp1a = 1.0D, salp1b = tiny_, calp1b = -1.0D;
/*  750 */         for (boolean tripn = false, tripb = false; numit < 83; numit++) {
/*  755 */           Lambda12V w = Lambda12(sbet1, cbet1, dn1, sbet2, cbet2, dn2, salp1, calp1, (numit < 20), C1a, C2a, C3a);
/*  758 */           double d1 = w.lam12 - lam12;
/*  759 */           salp2 = w.salp2;
/*  759 */           calp2 = w.calp2;
/*  760 */           sig12 = w.sig12;
/*  761 */           ssig1 = w.ssig1;
/*  761 */           csig1 = w.csig1;
/*  762 */           ssig2 = w.ssig2;
/*  762 */           csig2 = w.csig2;
/*  763 */           eps = w.eps;
/*  763 */           omg12 = w.domg12;
/*  764 */           double dv = w.dlam12;
/*  768 */           if (tripb || FastMath.abs(d1) < (tripn ? 8 : 2) * tol0_)
/*      */             break; 
/*  770 */           if (d1 > 0.0D && (numit > 20 || calp1 / salp1 > calp1b / salp1b)) {
/*  771 */             salp1b = salp1;
/*  771 */             calp1b = calp1;
/*  772 */           } else if (d1 < 0.0D && (numit > 20 || calp1 / salp1 < calp1a / salp1a)) {
/*  773 */             salp1a = salp1;
/*  773 */             calp1a = calp1;
/*      */           } 
/*  774 */           if (numit < 20 && dv > 0.0D) {
/*  776 */             double dalp1 = -d1 / dv;
/*  778 */             double sdalp1 = FastMath.sin(dalp1), cdalp1 = FastMath.cos(dalp1);
/*  779 */             double nsalp1 = salp1 * cdalp1 + calp1 * sdalp1;
/*  780 */             if (nsalp1 > 0.0D && FastMath.abs(dalp1) < Math.PI) {
/*  781 */               calp1 = calp1 * cdalp1 - salp1 * sdalp1;
/*  782 */               salp1 = nsalp1;
/*  783 */               Pair pair1 = SinCosNorm(salp1, calp1);
/*  784 */               salp1 = pair1.first;
/*  784 */               calp1 = pair1.second;
/*  788 */               tripn = (FastMath.abs(d1) <= 16.0D * tol0_);
/*      */               continue;
/*      */             } 
/*      */           } 
/*  800 */           salp1 = (salp1a + salp1b) / 2.0D;
/*  801 */           calp1 = (calp1a + calp1b) / 2.0D;
/*  802 */           Pair pair = SinCosNorm(salp1, calp1);
/*  803 */           salp1 = pair.first;
/*  803 */           calp1 = pair.second;
/*  804 */           tripn = false;
/*  805 */           tripb = (FastMath.abs(salp1a - salp1) + calp1a - calp1 < tolb_ || FastMath.abs(salp1 - salp1b) + calp1 - calp1b < tolb_);
/*      */           continue;
/*      */         } 
/*  809 */         LengthsV lengthsV = Lengths(eps, sig12, ssig1, csig1, dn1, ssig2, csig2, dn2, cbet1, cbet2, ((outmask & 0x2005) != 0), C1a, C2a);
/*  813 */         s12x = lengthsV.s12b;
/*  813 */         m12x = lengthsV.m12b;
/*  814 */         if ((outmask & 0x2005) != 0) {
/*  815 */           r.M12 = lengthsV.M12;
/*  815 */           r.M21 = lengthsV.M21;
/*      */         } 
/*  818 */         m12x *= this._b;
/*  819 */         s12x *= this._b;
/*  820 */         a12 = sig12 / 0.017453292519943295D;
/*  821 */         omg12 = lam12 - omg12;
/*      */       } 
/*      */     } 
/*  825 */     if ((outmask & 0x401) != 0)
/*  826 */       r.s12 = 0.0D + s12x; 
/*  828 */     if ((outmask & 0x1005) != 0)
/*  829 */       r.m12 = 0.0D + m12x; 
/*  831 */     if ((outmask & 0x4010) != 0) {
/*  834 */       double alp12, salp0 = salp1 * cbet1;
/*  835 */       double calp0 = GeoMath.hypot(calp1, salp1 * sbet1);
/*  837 */       if (calp0 != 0.0D && salp0 != 0.0D) {
/*  840 */         double ssig1 = sbet1, csig1 = calp1 * cbet1;
/*  841 */         double ssig2 = sbet2, csig2 = calp2 * cbet2;
/*  842 */         double k2 = GeoMath.sq(calp0) * this._ep2;
/*  843 */         double eps = k2 / (2.0D * (1.0D + FastMath.sqrt(1.0D + k2)) + k2);
/*  845 */         double A4 = GeoMath.sq(this._a) * calp0 * salp0 * this._e2;
/*  846 */         Pair pair = SinCosNorm(ssig1, csig1);
/*  847 */         ssig1 = pair.first;
/*  847 */         csig1 = pair.second;
/*  848 */         pair = SinCosNorm(ssig2, csig2);
/*  849 */         ssig2 = pair.first;
/*  849 */         csig2 = pair.second;
/*  850 */         double[] C4a = new double[6];
/*  851 */         C4f(eps, C4a);
/*  853 */         double B41 = SinCosSeries(false, ssig1, csig1, C4a);
/*  854 */         double B42 = SinCosSeries(false, ssig2, csig2, C4a);
/*  855 */         r.S12 = A4 * (B42 - B41);
/*      */       } else {
/*  858 */         r.S12 = 0.0D;
/*      */       } 
/*  860 */       if (!meridian && omg12 < 2.356194490192345D && sbet2 - sbet1 < 1.75D) {
/*  867 */         double somg12 = FastMath.sin(omg12), domg12 = 1.0D + FastMath.cos(omg12);
/*  868 */         double dbet1 = 1.0D + cbet1, dbet2 = 1.0D + cbet2;
/*  869 */         alp12 = 2.0D * FastMath.atan2(somg12 * (sbet1 * dbet2 + sbet2 * dbet1), domg12 * (sbet1 * sbet2 + dbet1 * dbet2));
/*      */       } else {
/*  874 */         double salp12 = salp2 * calp1 - calp2 * salp1;
/*  875 */         double calp12 = calp2 * calp1 + salp2 * salp1;
/*  880 */         if (salp12 == 0.0D && calp12 < 0.0D) {
/*  881 */           salp12 = tiny_ * calp1;
/*  882 */           calp12 = -1.0D;
/*      */         } 
/*  884 */         alp12 = FastMath.atan2(salp12, calp12);
/*      */       } 
/*  886 */       r.S12 += this._c2 * alp12;
/*  887 */       r.S12 *= (swapp * lonsign * latsign);
/*  889 */       r.S12 += 0.0D;
/*      */     } 
/*  893 */     if (swapp < 0) {
/*  894 */       double t = salp1;
/*  894 */       salp1 = salp2;
/*  894 */       salp2 = t;
/*  895 */       t = calp1;
/*  895 */       calp1 = calp2;
/*  895 */       calp2 = t;
/*  896 */       if ((outmask & 0x2005) != 0) {
/*  897 */         t = r.M12;
/*  897 */         r.M12 = r.M21;
/*  897 */         r.M21 = t;
/*      */       } 
/*      */     } 
/*  900 */     salp1 *= (swapp * lonsign);
/*  900 */     calp1 *= (swapp * latsign);
/*  901 */     salp2 *= (swapp * lonsign);
/*  901 */     calp2 *= (swapp * latsign);
/*  903 */     if ((outmask & 0x200) != 0) {
/*  905 */       r.azi1 = 0.0D - FastMath.atan2(-salp1, calp1) / 0.017453292519943295D;
/*  906 */       r.azi2 = 0.0D - FastMath.atan2(-salp2, calp2) / 0.017453292519943295D;
/*      */     } 
/*  909 */     r.a12 = a12;
/*  910 */     return r;
/*      */   }
/*      */   
/*      */   public GeodesicLine Line(double lat1, double lon1, double azi1) {
/*  930 */     return Line(lat1, lon1, azi1, 32671);
/*      */   }
/*      */   
/*      */   public GeodesicLine Line(double lat1, double lon1, double azi1, int caps) {
/*  978 */     return new GeodesicLine(this, lat1, lon1, azi1, caps);
/*      */   }
/*      */   
/*      */   public double MajorRadius() {
/*  985 */     return this._a;
/*      */   }
/*      */   
/*      */   public double Flattening() {
/*  991 */     return this._f;
/*      */   }
/*      */   
/*      */   public double EllipsoidArea() {
/*  998 */     return 12.566370614359172D * this._c2;
/*      */   }
/*      */   
/* 1004 */   public static final Geodesic WGS84 = new Geodesic(6378137.0D, 0.0033528106647474805D);
/*      */   
/*      */   protected static double SinCosSeries(boolean sinp, double sinx, double cosx, double[] c) {
/* 1034 */     int k = c.length, n = k - (sinp ? 1 : 0);
/* 1036 */     double ar = 2.0D * (cosx - sinx) * (cosx + sinx);
/* 1037 */     double y0 = ((n & 0x1) != 0) ? c[--k] : 0.0D, y1 = 0.0D;
/* 1039 */     n /= 2;
/* 1040 */     while (n-- != 0) {
/* 1042 */       y1 = ar * y0 - y1 + c[--k];
/* 1043 */       y0 = ar * y1 - y0 + c[--k];
/*      */     } 
/* 1045 */     return sinp ? (2.0D * sinx * cosx * y0) : (cosx * (y0 - y1));
/*      */   }
/*      */   
/*      */   private class LengthsV {
/*      */     private LengthsV() {}
/*      */     
/* 1053 */     private double s12b = this.m12b = this.m0 = this.M12 = this.M21 = Double.NaN;
/*      */     
/*      */     private double m12b;
/*      */     
/*      */     private double m0;
/*      */     
/*      */     private double M12;
/*      */     
/*      */     private double M21;
/*      */   }
/*      */   
/*      */   private LengthsV Lengths(double eps, double sig12, double ssig1, double csig1, double dn1, double ssig2, double csig2, double dn2, double cbet1, double cbet2, boolean scalep, double[] C1a, double[] C2a) {
/* 1066 */     LengthsV v = new LengthsV();
/* 1067 */     C1f(eps, C1a);
/* 1068 */     C2f(eps, C2a);
/* 1070 */     double A1m1 = A1m1f(eps);
/* 1071 */     double AB1 = (1.0D + A1m1) * (SinCosSeries(true, ssig2, csig2, C1a) - SinCosSeries(true, ssig1, csig1, C1a));
/* 1073 */     double A2m1 = A2m1f(eps);
/* 1074 */     double AB2 = (1.0D + A2m1) * (SinCosSeries(true, ssig2, csig2, C2a) - SinCosSeries(true, ssig1, csig1, C2a));
/* 1076 */     v.m0 = A1m1 - A2m1;
/* 1077 */     double J12 = v.m0 * sig12 + AB1 - AB2;
/* 1081 */     v.m12b = dn2 * csig1 * ssig2 - dn1 * ssig1 * csig2 - csig1 * csig2 * J12;
/* 1084 */     v.s12b = (1.0D + A1m1) * sig12 + AB1;
/* 1085 */     if (scalep) {
/* 1086 */       double csig12 = csig1 * csig2 + ssig1 * ssig2;
/* 1087 */       double t = this._ep2 * (cbet1 - cbet2) * (cbet1 + cbet2) / (dn1 + dn2);
/* 1088 */       v.M12 = csig12 + (t * ssig2 - csig2 * J12) * ssig1 / dn1;
/* 1089 */       v.M21 = csig12 - (t * ssig1 - csig1 * J12) * ssig2 / dn2;
/*      */     } 
/* 1091 */     return v;
/*      */   }
/*      */   
/*      */   private static double Astroid(double x, double y) {
/* 1099 */     double k, p = GeoMath.sq(x);
/* 1100 */     double q = GeoMath.sq(y);
/* 1101 */     double r = (p + q - 1.0D) / 6.0D;
/* 1102 */     if (q != 0.0D || r > 0.0D) {
/* 1106 */       double S = p * q / 4.0D;
/* 1107 */       double r2 = GeoMath.sq(r);
/* 1108 */       double r3 = r * r2;
/* 1111 */       double disc = S * (S + 2.0D * r3);
/* 1112 */       double u = r;
/* 1113 */       if (disc >= 0.0D) {
/* 1114 */         double T3 = S + r3;
/* 1118 */         T3 += (T3 < 0.0D) ? -FastMath.sqrt(disc) : FastMath.sqrt(disc);
/* 1120 */         double T = GeoMath.cbrt(T3);
/* 1122 */         u += T + ((T != 0.0D) ? (r2 / T) : 0.0D);
/*      */       } else {
/* 1125 */         double ang = FastMath.atan2(FastMath.sqrt(-disc), -(S + r3));
/* 1128 */         u += 2.0D * r * FastMath.cos(ang / 3.0D);
/*      */       } 
/* 1131 */       double v = FastMath.sqrt(GeoMath.sq(u) + q);
/* 1133 */       double uv = (u < 0.0D) ? (q / (v - u)) : (u + v);
/* 1134 */       double w = (uv - q) / 2.0D * v;
/* 1137 */       k = uv / (FastMath.sqrt(uv + GeoMath.sq(w)) + w);
/*      */     } else {
/* 1141 */       k = 0.0D;
/*      */     } 
/* 1143 */     return k;
/*      */   }
/*      */   
/*      */   private class InverseStartV {
/*      */     private InverseStartV() {}
/*      */     
/* 1153 */     private double sig12 = this.salp1 = this.calp1 = this.salp2 = this.calp2 = this.dnm = Double.NaN;
/*      */     
/*      */     private double salp1;
/*      */     
/*      */     private double calp1;
/*      */     
/*      */     private double salp2;
/*      */     
/*      */     private double calp2;
/*      */     
/*      */     private double dnm;
/*      */   }
/*      */   
/*      */   private InverseStartV InverseStart(double sbet1, double cbet1, double dn1, double sbet2, double cbet2, double dn2, double lam12, double[] C1a, double[] C2a) {
/* 1167 */     InverseStartV w = new InverseStartV();
/* 1168 */     w.sig12 = -1.0D;
/* 1171 */     double sbet12 = sbet2 * cbet1 - cbet2 * sbet1;
/* 1172 */     double cbet12 = cbet2 * cbet1 + sbet2 * sbet1;
/* 1173 */     double sbet12a = sbet2 * cbet1 + cbet2 * sbet1;
/* 1174 */     boolean shortline = (cbet12 >= 0.0D && sbet12 < 0.5D && cbet2 * lam12 < 0.5D);
/* 1176 */     double omg12 = lam12;
/* 1177 */     if (shortline) {
/* 1178 */       double sbetm2 = GeoMath.sq(sbet1 + sbet2);
/* 1181 */       sbetm2 /= sbetm2 + GeoMath.sq(cbet1 + cbet2);
/* 1182 */       w.dnm = FastMath.sqrt(1.0D + this._ep2 * sbetm2);
/* 1183 */       omg12 /= this._f1 * w.dnm;
/*      */     } 
/* 1185 */     double somg12 = FastMath.sin(omg12), comg12 = FastMath.cos(omg12);
/* 1187 */     w.salp1 = cbet2 * somg12;
/* 1188 */     w.calp1 = (comg12 >= 0.0D) ? (sbet12 + cbet2 * sbet1 * GeoMath.sq(somg12) / (1.0D + comg12)) : (sbet12a - cbet2 * sbet1 * GeoMath.sq(somg12) / (1.0D - comg12));
/* 1193 */     double ssig12 = GeoMath.hypot(w.salp1, w.calp1);
/* 1194 */     double csig12 = sbet1 * sbet2 + cbet1 * cbet2 * comg12;
/* 1196 */     if (shortline && ssig12 < this._etol2) {
/* 1198 */       w.salp2 = cbet1 * somg12;
/* 1199 */       w.calp2 = sbet12 - cbet1 * sbet2 * ((comg12 >= 0.0D) ? (GeoMath.sq(somg12) / (1.0D + comg12)) : (1.0D - comg12));
/* 1201 */       Pair p = SinCosNorm(w.salp2, w.calp2);
/* 1202 */       w.salp2 = p.first;
/* 1202 */       w.calp2 = p.second;
/* 1204 */       w.sig12 = FastMath.atan2(ssig12, csig12);
/* 1205 */     } else if (FastMath.abs(this._n) <= 0.1D && csig12 < 0.0D && ssig12 < 6.0D * FastMath.abs(this._n) * Math.PI * GeoMath.sq(cbet1)) {
/*      */       double y;
/*      */       double lamscale;
/*      */       double x;
/* 1217 */       if (this._f >= 0.0D) {
/* 1221 */         double k2 = GeoMath.sq(sbet1) * this._ep2;
/* 1222 */         double eps = k2 / (2.0D * (1.0D + FastMath.sqrt(1.0D + k2)) + k2);
/* 1223 */         lamscale = this._f * cbet1 * A3f(eps) * Math.PI;
/* 1225 */         double betscale = lamscale * cbet1;
/* 1227 */         x = (lam12 - Math.PI) / lamscale;
/* 1228 */         y = sbet12a / betscale;
/*      */       } else {
/* 1232 */         double cbet12a = cbet2 * cbet1 - sbet2 * sbet1;
/* 1233 */         double bet12a = FastMath.atan2(sbet12a, cbet12a);
/* 1237 */         LengthsV v = Lengths(this._n, Math.PI + bet12a, sbet1, -cbet1, dn1, sbet2, cbet2, dn2, cbet1, cbet2, false, C1a, C2a);
/* 1241 */         double m12b = v.m12b, m0 = v.m0;
/* 1243 */         x = -1.0D + m12b / cbet1 * cbet2 * m0 * Math.PI;
/* 1244 */         double betscale = (x < -0.01D) ? (sbet12a / x) : (-this._f * GeoMath.sq(cbet1) * Math.PI);
/* 1246 */         lamscale = betscale / cbet1;
/* 1247 */         y = (lam12 - Math.PI) / lamscale;
/*      */       } 
/* 1250 */       if (y > -tol1_ && x > -1.0D - xthresh_) {
/* 1252 */         if (this._f >= 0.0D) {
/* 1253 */           w.salp1 = FastMath.min(1.0D, -x);
/* 1254 */           w.calp1 = -FastMath.sqrt(1.0D - GeoMath.sq(w.salp1));
/*      */         } else {
/* 1256 */           w.calp1 = FastMath.max((x > -tol1_) ? 0.0D : -1.0D, x);
/* 1257 */           w.salp1 = FastMath.sqrt(1.0D - GeoMath.sq(w.calp1));
/*      */         } 
/*      */       } else {
/* 1294 */         double k = Astroid(x, y);
/* 1296 */         double omg12a = lamscale * ((this._f >= 0.0D) ? (-x * k / (1.0D + k)) : (-y * (1.0D + k) / k));
/* 1297 */         somg12 = FastMath.sin(omg12a);
/* 1297 */         comg12 = -FastMath.cos(omg12a);
/* 1299 */         w.salp1 = cbet2 * somg12;
/* 1300 */         w.calp1 = sbet12a - cbet2 * sbet1 * GeoMath.sq(somg12) / (1.0D - comg12);
/*      */       } 
/*      */     } 
/* 1303 */     if (w.salp1 > 0.0D) {
/* 1304 */       Pair p = SinCosNorm(w.salp1, w.calp1);
/* 1305 */       w.salp1 = p.first;
/* 1305 */       w.calp1 = p.second;
/*      */     } else {
/* 1307 */       w.salp1 = 1.0D;
/* 1307 */       w.calp1 = 0.0D;
/*      */     } 
/* 1309 */     return w;
/*      */   }
/*      */   
/*      */   private class Lambda12V {
/*      */     private Lambda12V() {}
/*      */     
/* 1316 */     private double lam12 = this.salp2 = this.calp2 = this.sig12 = this.ssig1 = this.csig1 = this.ssig2 = this.csig2 = this.eps = this.domg12 = this.dlam12 = Double.NaN;
/*      */     
/*      */     private double salp2;
/*      */     
/*      */     private double calp2;
/*      */     
/*      */     private double sig12;
/*      */     
/*      */     private double ssig1;
/*      */     
/*      */     private double csig1;
/*      */     
/*      */     private double ssig2;
/*      */     
/*      */     private double csig2;
/*      */     
/*      */     private double eps;
/*      */     
/*      */     private double domg12;
/*      */     
/*      */     private double dlam12;
/*      */   }
/*      */   
/*      */   private Lambda12V Lambda12(double sbet1, double cbet1, double dn1, double sbet2, double cbet2, double dn2, double salp1, double calp1, boolean diffp, double[] C1a, double[] C2a, double[] C3a) {
/* 1330 */     Lambda12V w = new Lambda12V();
/* 1332 */     if (sbet1 == 0.0D && calp1 == 0.0D)
/* 1335 */       calp1 = -tiny_; 
/* 1339 */     double salp0 = salp1 * cbet1;
/* 1340 */     double calp0 = GeoMath.hypot(calp1, salp1 * sbet1);
/* 1345 */     w.ssig1 = sbet1;
/* 1345 */     double somg1 = salp0 * sbet1;
/*      */     double comg1;
/* 1346 */     w.csig1 = comg1 = calp1 * cbet1;
/* 1347 */     Pair p = SinCosNorm(w.ssig1, w.csig1);
/* 1348 */     w.ssig1 = p.first;
/* 1348 */     w.csig1 = p.second;
/* 1355 */     w.salp2 = (cbet2 != cbet1) ? (salp0 / cbet2) : salp1;
/* 1360 */     w.calp2 = (cbet2 != cbet1 || FastMath.abs(sbet2) != -sbet1) ? (FastMath.sqrt(GeoMath.sq(calp1 * cbet1) + ((cbet1 < -sbet1) ? ((cbet2 - cbet1) * (cbet1 + cbet2)) : ((sbet1 - sbet2) * (sbet1 + sbet2)))) / cbet2) : FastMath.abs(calp1);
/* 1368 */     w.ssig2 = sbet2;
/* 1368 */     double somg2 = salp0 * sbet2;
/*      */     double comg2;
/* 1369 */     w.csig2 = comg2 = w.calp2 * cbet2;
/* 1370 */     p = SinCosNorm(w.ssig2, w.csig2);
/* 1371 */     w.ssig2 = p.first;
/* 1371 */     w.csig2 = p.second;
/* 1375 */     w.sig12 = FastMath.atan2(FastMath.max(w.csig1 * w.ssig2 - w.ssig1 * w.csig2, 0.0D), w.csig1 * w.csig2 + w.ssig1 * w.ssig2);
/* 1379 */     double omg12 = FastMath.atan2(FastMath.max(comg1 * somg2 - somg1 * comg2, 0.0D), comg1 * comg2 + somg1 * somg2);
/* 1382 */     double k2 = GeoMath.sq(calp0) * this._ep2;
/* 1383 */     w.eps = k2 / (2.0D * (1.0D + FastMath.sqrt(1.0D + k2)) + k2);
/* 1384 */     C3f(w.eps, C3a);
/* 1385 */     double B312 = SinCosSeries(true, w.ssig2, w.csig2, C3a) - SinCosSeries(true, w.ssig1, w.csig1, C3a);
/* 1387 */     double h0 = -this._f * A3f(w.eps);
/* 1388 */     w.domg12 = salp0 * h0 * (w.sig12 + B312);
/* 1389 */     w.lam12 = omg12 + w.domg12;
/* 1391 */     if (diffp)
/* 1392 */       if (w.calp2 == 0.0D) {
/* 1393 */         w.dlam12 = -2.0D * this._f1 * dn1 / sbet1;
/*      */       } else {
/* 1396 */         LengthsV v = Lengths(w.eps, w.sig12, w.ssig1, w.csig1, dn1, w.ssig2, w.csig2, dn2, cbet1, cbet2, false, C1a, C2a);
/* 1399 */         w.dlam12 = v.m12b;
/* 1400 */         w.dlam12 *= this._f1 / w.calp2 * cbet2;
/*      */       }  
/* 1404 */     return w;
/*      */   }
/*      */   
/*      */   protected double A3f(double eps) {
/* 1409 */     double v = 0.0D;
/* 1410 */     for (int i = 6; i > 0;)
/* 1411 */       v = eps * v + this._A3x[--i]; 
/* 1412 */     return v;
/*      */   }
/*      */   
/*      */   protected void C3f(double eps, double[] c) {
/* 1418 */     for (int j = 15, k = 5; k > 0; ) {
/* 1419 */       double t = 0.0D;
/* 1420 */       for (int m = 6 - k; m > 0; m--)
/* 1421 */         t = eps * t + this._C3x[--j]; 
/* 1422 */       c[k--] = t;
/*      */     } 
/* 1425 */     double mult = 1.0D;
/* 1426 */     for (int i = 1; i < 6; ) {
/* 1427 */       mult *= eps;
/* 1428 */       c[i++] = c[i++] * mult;
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void C4f(double eps, double[] c) {
/* 1435 */     for (int j = 21, k = 6; k > 0; ) {
/* 1436 */       double t = 0.0D;
/* 1437 */       for (int m = 6 - k + 1; m > 0; m--)
/* 1438 */         t = eps * t + this._C4x[--j]; 
/* 1439 */       c[--k] = t;
/*      */     } 
/* 1442 */     double mult = 1.0D;
/* 1443 */     for (int i = 1; i < 6; ) {
/* 1444 */       mult *= eps;
/* 1445 */       c[i++] = c[i++] * mult;
/*      */     } 
/*      */   }
/*      */   
/*      */   protected static double A1m1f(double eps) {
/* 1454 */     double eps2 = GeoMath.sq(eps);
/* 1456 */     double t = eps2 * (eps2 * (eps2 + 4.0D) + 64.0D) / 256.0D;
/* 1457 */     return (t + eps) / (1.0D - eps);
/*      */   }
/*      */   
/*      */   protected static void C1f(double eps, double[] c) {
/* 1463 */     double eps2 = GeoMath.sq(eps);
/* 1464 */     double d = eps;
/* 1465 */     c[1] = d * ((6.0D - eps2) * eps2 - 16.0D) / 32.0D;
/* 1466 */     d *= eps;
/* 1467 */     c[2] = d * ((64.0D - 9.0D * eps2) * eps2 - 128.0D) / 2048.0D;
/* 1468 */     d *= eps;
/* 1469 */     c[3] = d * (9.0D * eps2 - 16.0D) / 768.0D;
/* 1470 */     d *= eps;
/* 1471 */     c[4] = d * (3.0D * eps2 - 5.0D) / 512.0D;
/* 1472 */     d *= eps;
/* 1473 */     c[5] = -7.0D * d / 1280.0D;
/* 1474 */     d *= eps;
/* 1475 */     c[6] = -7.0D * d / 2048.0D;
/*      */   }
/*      */   
/*      */   protected static void C1pf(double eps, double[] c) {
/* 1481 */     double eps2 = GeoMath.sq(eps);
/* 1482 */     double d = eps;
/* 1483 */     c[1] = d * (eps2 * (205.0D * eps2 - 432.0D) + 768.0D) / 1536.0D;
/* 1484 */     d *= eps;
/* 1485 */     c[2] = d * (eps2 * (4005.0D * eps2 - 4736.0D) + 3840.0D) / 12288.0D;
/* 1486 */     d *= eps;
/* 1487 */     c[3] = d * (116.0D - 225.0D * eps2) / 384.0D;
/* 1488 */     d *= eps;
/* 1489 */     c[4] = d * (2695.0D - 7173.0D * eps2) / 7680.0D;
/* 1490 */     d *= eps;
/* 1491 */     c[5] = 3467.0D * d / 7680.0D;
/* 1492 */     d *= eps;
/* 1493 */     c[6] = 38081.0D * d / 61440.0D;
/*      */   }
/*      */   
/*      */   protected static double A2m1f(double eps) {
/* 1499 */     double eps2 = GeoMath.sq(eps);
/* 1501 */     double t = eps2 * (eps2 * (25.0D * eps2 + 36.0D) + 64.0D) / 256.0D;
/* 1502 */     return t * (1.0D - eps) - eps;
/*      */   }
/*      */   
/*      */   protected static void C2f(double eps, double[] c) {
/* 1508 */     double eps2 = GeoMath.sq(eps);
/* 1509 */     double d = eps;
/* 1510 */     c[1] = d * (eps2 * (eps2 + 2.0D) + 16.0D) / 32.0D;
/* 1511 */     d *= eps;
/* 1512 */     c[2] = d * (eps2 * (35.0D * eps2 + 64.0D) + 384.0D) / 2048.0D;
/* 1513 */     d *= eps;
/* 1514 */     c[3] = d * (15.0D * eps2 + 80.0D) / 768.0D;
/* 1515 */     d *= eps;
/* 1516 */     c[4] = d * (7.0D * eps2 + 35.0D) / 512.0D;
/* 1517 */     d *= eps;
/* 1518 */     c[5] = 63.0D * d / 1280.0D;
/* 1519 */     d *= eps;
/* 1520 */     c[6] = 77.0D * d / 2048.0D;
/*      */   }
/*      */   
/*      */   protected void A3coeff() {
/* 1525 */     this._A3x[0] = 1.0D;
/* 1526 */     this._A3x[1] = (this._n - 1.0D) / 2.0D;
/* 1527 */     this._A3x[2] = (this._n * (3.0D * this._n - 1.0D) - 2.0D) / 8.0D;
/* 1528 */     this._A3x[3] = ((-this._n - 3.0D) * this._n - 1.0D) / 16.0D;
/* 1529 */     this._A3x[4] = (-2.0D * this._n - 3.0D) / 64.0D;
/* 1530 */     this._A3x[5] = -0.0234375D;
/*      */   }
/*      */   
/*      */   protected void C3coeff() {
/* 1535 */     this._C3x[0] = (1.0D - this._n) / 4.0D;
/* 1536 */     this._C3x[1] = (1.0D - this._n * this._n) / 8.0D;
/* 1537 */     this._C3x[2] = ((3.0D - this._n) * this._n + 3.0D) / 64.0D;
/* 1538 */     this._C3x[3] = (2.0D * this._n + 5.0D) / 128.0D;
/* 1539 */     this._C3x[4] = 0.0234375D;
/* 1540 */     this._C3x[5] = ((this._n - 3.0D) * this._n + 2.0D) / 32.0D;
/* 1541 */     this._C3x[6] = ((-3.0D * this._n - 2.0D) * this._n + 3.0D) / 64.0D;
/* 1542 */     this._C3x[7] = (this._n + 3.0D) / 128.0D;
/* 1543 */     this._C3x[8] = 0.01953125D;
/* 1544 */     this._C3x[9] = (this._n * (5.0D * this._n - 9.0D) + 5.0D) / 192.0D;
/* 1545 */     this._C3x[10] = (9.0D - 10.0D * this._n) / 384.0D;
/* 1546 */     this._C3x[11] = 0.013671875D;
/* 1547 */     this._C3x[12] = (7.0D - 14.0D * this._n) / 512.0D;
/* 1548 */     this._C3x[13] = 0.013671875D;
/* 1549 */     this._C3x[14] = 0.008203125D;
/*      */   }
/*      */   
/*      */   protected void C4coeff() {
/* 1556 */     this._C4x[0] = (this._n * (this._n * (this._n * (this._n * (100.0D * this._n + 208.0D) + 572.0D) + 3432.0D) - 12012.0D) + 30030.0D) / 45045.0D;
/* 1557 */     this._C4x[1] = (this._n * (this._n * (this._n * (64.0D * this._n + 624.0D) - 4576.0D) + 6864.0D) - 3003.0D) / 15015.0D;
/* 1558 */     this._C4x[2] = (this._n * ((14144.0D - 10656.0D * this._n) * this._n - 4576.0D) - 858.0D) / 45045.0D;
/* 1559 */     this._C4x[3] = ((-224.0D * this._n - 4784.0D) * this._n + 1573.0D) / 45045.0D;
/* 1560 */     this._C4x[4] = (1088.0D * this._n + 156.0D) / 45045.0D;
/* 1561 */     this._C4x[5] = 0.00646020646020646D;
/* 1562 */     this._C4x[6] = (this._n * (this._n * ((-64.0D * this._n - 624.0D) * this._n + 4576.0D) - 6864.0D) + 3003.0D) / 135135.0D;
/* 1563 */     this._C4x[7] = (this._n * (this._n * (5952.0D * this._n - 11648.0D) + 9152.0D) - 2574.0D) / 135135.0D;
/* 1564 */     this._C4x[8] = (this._n * (5792.0D * this._n + 1040.0D) - 1287.0D) / 135135.0D;
/* 1565 */     this._C4x[9] = (468.0D - 2944.0D * this._n) / 135135.0D;
/* 1566 */     this._C4x[10] = 1.11000111000111E-4D;
/* 1567 */     this._C4x[11] = (this._n * ((4160.0D - 1440.0D * this._n) * this._n - 4576.0D) + 1716.0D) / 225225.0D;
/* 1568 */     this._C4x[12] = ((4992.0D - 8448.0D * this._n) * this._n - 1144.0D) / 225225.0D;
/* 1569 */     this._C4x[13] = (1856.0D * this._n - 936.0D) / 225225.0D;
/* 1570 */     this._C4x[14] = 7.459207459207459E-4D;
/* 1571 */     this._C4x[15] = (this._n * (3584.0D * this._n - 3328.0D) + 1144.0D) / 315315.0D;
/* 1572 */     this._C4x[16] = (1024.0D * this._n - 208.0D) / 105105.0D;
/* 1573 */     this._C4x[17] = -0.0021565735851450138D;
/* 1574 */     this._C4x[18] = (832.0D - 2560.0D * this._n) / 405405.0D;
/* 1575 */     this._C4x[19] = -9.472009472009472E-4D;
/* 1576 */     this._C4x[20] = 0.0012916376552740189D;
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\net\sf\geographiclib\Geodesic.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */