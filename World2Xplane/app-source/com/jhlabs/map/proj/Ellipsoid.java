/*     */ package com.jhlabs.map.proj;
/*     */ 
/*     */ public class Ellipsoid implements Cloneable {
/*     */   public String name;
/*     */   
/*     */   public String shortName;
/*     */   
/*  28 */   public double equatorRadius = 1.0D;
/*     */   
/*  29 */   public double poleRadius = 1.0D;
/*     */   
/*  30 */   public double eccentricity = 1.0D;
/*     */   
/*  31 */   public double eccentricity2 = 1.0D;
/*     */   
/*  34 */   public static final Ellipsoid SPHERE = new Ellipsoid("sphere", 6371008.7714D, 6371008.7714D, 0.0D, "Sphere");
/*     */   
/*  35 */   public static final Ellipsoid BESSEL = new Ellipsoid("bessel", 6377397.155D, 0.0D, 299.1528128D, "Bessel 1841");
/*     */   
/*  36 */   public static final Ellipsoid CLARKE_1866 = new Ellipsoid("clrk66", 6378206.4D, 6356583.8D, 0.0D, "Clarke 1866");
/*     */   
/*  37 */   public static final Ellipsoid CLARKE_1880 = new Ellipsoid("clrk80", 6378249.145D, 0.0D, 293.4663D, "Clarke 1880 mod.");
/*     */   
/*  38 */   public static final Ellipsoid AIRY = new Ellipsoid("airy", 6377563.396D, 6356256.91D, 0.0D, "Airy 1830");
/*     */   
/*  39 */   public static final Ellipsoid WGS_1960 = new Ellipsoid("WGS60", 6378165.0D, 0.0D, 298.3D, "WGS 60");
/*     */   
/*  40 */   public static final Ellipsoid WGS_1966 = new Ellipsoid("WGS66", 6378145.0D, 0.0D, 298.25D, "WGS 66");
/*     */   
/*  41 */   public static final Ellipsoid WGS_1972 = new Ellipsoid("WGS72", 6378135.0D, 0.0D, 298.26D, "WGS 72");
/*     */   
/*  42 */   public static final Ellipsoid WGS_1984 = new Ellipsoid("WGS84", 6378137.0D, 0.0D, 298.257223563D, "WGS 84");
/*     */   
/*  43 */   public static final Ellipsoid KRASOVSKY = new Ellipsoid("krass", 6378245.0D, 298.3D, 0.0D, "Krassovsky, 1942");
/*     */   
/*  44 */   public static final Ellipsoid EVEREST = new Ellipsoid("evrst30", 6377276.345D, 0.0D, 300.8017D, "Everest 1830");
/*     */   
/*  45 */   public static final Ellipsoid INTERNATIONAL_1967 = new Ellipsoid("new_intl", 6378157.5D, 6356772.2D, 0.0D, "New International 1967");
/*     */   
/*  46 */   public static final Ellipsoid GRS_1980 = new Ellipsoid("GRS80", 6378137.0D, 0.0D, 298.257222101D, "GRS 1980 (IUGG, 1980)");
/*     */   
/*  48 */   public static final Ellipsoid AUSTRALIAN = new Ellipsoid("australian", 6378160.0D, 6356774.7D, 298.25D, "Australian");
/*     */   
/*  50 */   public static final Ellipsoid[] ellipsoids = new Ellipsoid[] { 
/*  50 */       SPHERE, new Ellipsoid("MERIT", 6378137.0D, 0.0D, 298.257D, "MERIT 1983"), new Ellipsoid("SGS85", 6378136.0D, 0.0D, 298.257D, "Soviet Geodetic System 85"), GRS_1980, new Ellipsoid("IAU76", 6378140.0D, 0.0D, 298.257D, "IAU 1976"), AIRY, new Ellipsoid("APL4.9", 6378137.0D, 0.0D, 298.25D, "Appl. Physics. 1965"), new Ellipsoid("NWL9D", 6378145.0D, 298.25D, 0.0D, "Naval Weapons Lab., 1965"), new Ellipsoid("mod_airy", 6377340.189D, 6356034.446D, 0.0D, "Modified Airy"), new Ellipsoid("andrae", 6377104.43D, 300.0D, 0.0D, "Andrae 1876 (Den., Iclnd.)"), 
/*  50 */       new Ellipsoid("aust_SA", 6378160.0D, 0.0D, 298.25D, "Australian Natl & S. Amer. 1969"), new Ellipsoid("GRS67", 6378160.0D, 0.0D, 298.247167427D, "GRS 67 (IUGG 1967)"), BESSEL, new Ellipsoid("bess_nam", 6377483.865D, 0.0D, 299.1528128D, "Bessel 1841 (Namibia)"), CLARKE_1866, CLARKE_1880, new Ellipsoid("CPM", 6375738.7D, 0.0D, 334.29D, "Comm. des Poids et Mesures 1799"), new Ellipsoid("delmbr", 6376428.0D, 0.0D, 311.5D, "Delambre 1810 (Belgium)"), new Ellipsoid("engelis", 6378136.05D, 0.0D, 298.2566D, "Engelis 1985"), EVEREST, 
/*  50 */       new Ellipsoid("evrst48", 6377304.063D, 0.0D, 300.8017D, "Everest 1948"), new Ellipsoid("evrst56", 6377301.243D, 0.0D, 300.8017D, "Everest 1956"), new Ellipsoid("evrst69", 6377295.664D, 0.0D, 300.8017D, "Everest 1969"), new Ellipsoid("evrstSS", 6377298.556D, 0.0D, 300.8017D, "Everest (Sabah & Sarawak)"), new Ellipsoid("fschr60", 6378166.0D, 0.0D, 298.3D, "Fischer (Mercury Datum) 1960"), new Ellipsoid("fschr60m", 6378155.0D, 0.0D, 298.3D, "Modified Fischer 1960"), new Ellipsoid("fschr68", 6378150.0D, 0.0D, 298.3D, "Fischer 1968"), new Ellipsoid("helmert", 6378200.0D, 0.0D, 298.3D, "Helmert 1906"), new Ellipsoid("hough", 6378270.0D, 0.0D, 297.0D, "Hough"), new Ellipsoid("intl", 6378388.0D, 0.0D, 297.0D, "International 1909 (Hayford)"), 
/*  50 */       KRASOVSKY, new Ellipsoid("kaula", 6378163.0D, 0.0D, 298.24D, "Kaula 1961"), new Ellipsoid("lerch", 6378139.0D, 0.0D, 298.257D, "Lerch 1979"), new Ellipsoid("mprts", 6397300.0D, 0.0D, 191.0D, "Maupertius 1738"), INTERNATIONAL_1967, new Ellipsoid("plessis", 6376523.0D, 6355863.0D, 0.0D, "Plessis 1817 France)"), new Ellipsoid("SEasia", 6378155.0D, 6356773.3205D, 0.0D, "Southeast Asia"), new Ellipsoid("walbeck", 6376896.0D, 6355834.8467D, 0.0D, "Walbeck"), WGS_1960, WGS_1966, 
/*  50 */       WGS_1972, WGS_1984 };
/*     */   
/*     */   public Ellipsoid() {}
/*     */   
/*     */   public Ellipsoid(String shortName, double equatorRadius, double poleRadius, double reciprocalFlattening, String name) {
/* 100 */     this.shortName = shortName;
/* 101 */     this.name = name;
/* 102 */     this.equatorRadius = equatorRadius;
/* 103 */     this.poleRadius = poleRadius;
/* 104 */     if (reciprocalFlattening != 0.0D) {
/* 105 */       double flattening = 1.0D / reciprocalFlattening;
/* 106 */       double f = flattening;
/* 107 */       this.eccentricity2 = 2.0D * f - f * f;
/* 108 */       poleRadius = equatorRadius * Math.sqrt(1.0D - this.eccentricity2);
/*     */     } else {
/* 110 */       this.eccentricity2 = 1.0D - poleRadius * poleRadius / equatorRadius * equatorRadius;
/*     */     } 
/* 112 */     this.eccentricity = Math.sqrt(this.eccentricity2);
/*     */   }
/*     */   
/*     */   public Ellipsoid(String shortName, double equatorRadius, double eccentricity2, String name) {
/* 116 */     this.shortName = shortName;
/* 117 */     this.name = name;
/* 118 */     this.equatorRadius = equatorRadius;
/* 119 */     setEccentricitySquared(eccentricity2);
/*     */   }
/*     */   
/*     */   public Object clone() {
/*     */     try {
/* 124 */       Ellipsoid e = (Ellipsoid)super.clone();
/* 125 */       return e;
/* 127 */     } catch (CloneNotSupportedException e) {
/* 128 */       throw new InternalError();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setName(String name) {
/* 133 */     this.name = name;
/*     */   }
/*     */   
/*     */   public String getName() {
/* 137 */     return this.name;
/*     */   }
/*     */   
/*     */   public void setShortName(String shortName) {
/* 141 */     this.shortName = shortName;
/*     */   }
/*     */   
/*     */   public String getShortName() {
/* 145 */     return this.shortName;
/*     */   }
/*     */   
/*     */   public void setEquatorRadius(double equatorRadius) {
/* 149 */     this.equatorRadius = equatorRadius;
/*     */   }
/*     */   
/*     */   public double getEquatorRadius() {
/* 153 */     return this.equatorRadius;
/*     */   }
/*     */   
/*     */   public void setEccentricitySquared(double eccentricity2) {
/* 157 */     this.eccentricity2 = eccentricity2;
/* 158 */     this.poleRadius = this.equatorRadius * Math.sqrt(1.0D - eccentricity2);
/* 159 */     this.eccentricity = Math.sqrt(eccentricity2);
/*     */   }
/*     */   
/*     */   public double getEccentricitySquared() {
/* 163 */     return this.eccentricity2;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 167 */     return this.name;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\jhlabs\map\proj\Ellipsoid.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */