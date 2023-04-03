/*     */ package uk.me.jstott.jcoord;
/*     */ 
/*     */ public class OSRef {
/*     */   private double easting;
/*     */   
/*     */   private double northing;
/*     */   
/*     */   public OSRef(double easting, double northing) {
/*  37 */     this.easting = easting;
/*  38 */     this.northing = northing;
/*     */   }
/*     */   
/*     */   public OSRef(String ref) throws IllegalArgumentException {
/*  57 */     char char1 = ref.charAt(0);
/*  58 */     char char2 = ref.charAt(1);
/*  60 */     int east = Integer.parseInt(ref.substring(2, 5)) * 100;
/*  61 */     int north = Integer.parseInt(ref.substring(5, 8)) * 100;
/*  62 */     if (char1 == 'H') {
/*  63 */       north += 1000000;
/*  64 */     } else if (char1 == 'N') {
/*  65 */       north += 500000;
/*  66 */     } else if (char1 == 'O') {
/*  67 */       north += 500000;
/*  68 */       east += 500000;
/*  69 */     } else if (char1 == 'T') {
/*  70 */       east += 500000;
/*     */     } 
/*  72 */     int char2ord = char2;
/*  73 */     if (char2ord > 73)
/*  74 */       char2ord--; 
/*  75 */     double nx = ((char2ord - 65) % 5 * 100000);
/*  76 */     double ny = (4.0D - Math.floor(((char2ord - 65) / 5))) * 100000.0D;
/*  77 */     this.easting = east + nx;
/*  78 */     this.northing = north + ny;
/*     */   }
/*     */   
/*     */   public String toString() {
/*  90 */     return "(" + this.easting + ", " + this.northing + ")";
/*     */   }
/*     */   
/*     */   public String toSixFigureString() {
/*     */     String firstLetter;
/* 103 */     int hundredkmE = (int)Math.floor(this.easting / 100000.0D);
/* 104 */     int hundredkmN = (int)Math.floor(this.northing / 100000.0D);
/* 106 */     if (hundredkmN < 5) {
/* 107 */       if (hundredkmE < 5) {
/* 108 */         firstLetter = "S";
/*     */       } else {
/* 110 */         firstLetter = "T";
/*     */       } 
/* 112 */     } else if (hundredkmN < 10) {
/* 113 */       if (hundredkmE < 5) {
/* 114 */         firstLetter = "N";
/*     */       } else {
/* 116 */         firstLetter = "O";
/*     */       } 
/*     */     } else {
/* 119 */       firstLetter = "H";
/*     */     } 
/* 122 */     int index = 65 + (4 - hundredkmN % 5) * 5 + hundredkmE % 5;
/* 124 */     if (index >= 73)
/* 125 */       index++; 
/* 126 */     String secondLetter = Character.toString((char)index);
/* 128 */     int e = (int)Math.floor((this.easting - (100000 * hundredkmE)) / 100.0D);
/* 129 */     int n = (int)Math.floor((this.northing - (100000 * hundredkmN)) / 100.0D);
/* 130 */     String es = "" + e;
/* 131 */     if (e < 100)
/* 132 */       es = "0" + es; 
/* 133 */     if (e < 10)
/* 134 */       es = "0" + es; 
/* 135 */     String ns = "" + n;
/* 136 */     if (n < 100)
/* 137 */       ns = "0" + ns; 
/* 138 */     if (n < 10)
/* 139 */       ns = "0" + ns; 
/* 141 */     return firstLetter + secondLetter + es + ns;
/*     */   }
/*     */   
/*     */   public LatLng toLatLng() {
/* 155 */     double OSGB_F0 = 0.9996012717D;
/* 156 */     double N0 = -100000.0D;
/* 157 */     double E0 = 400000.0D;
/* 158 */     double phi0 = Math.toRadians(49.0D);
/* 159 */     double lambda0 = Math.toRadians(-2.0D);
/* 160 */     double a = RefEll.AIRY_1830.getMaj();
/* 161 */     double b = RefEll.AIRY_1830.getMin();
/* 162 */     double eSquared = RefEll.AIRY_1830.getEcc();
/* 163 */     double phi = 0.0D;
/* 164 */     double lambda = 0.0D;
/* 165 */     double E = this.easting;
/* 166 */     double N = this.northing;
/* 167 */     double n = (a - b) / (a + b);
/* 168 */     double M = 0.0D;
/* 169 */     double phiPrime = (N - N0) / a * OSGB_F0 + phi0;
/*     */     while (true) {
/* 171 */       M = b * OSGB_F0 * ((1.0D + n + 1.25D * n * n + 1.25D * n * n * n) * (phiPrime - phi0) - (3.0D * n + 3.0D * n * n + 2.625D * n * n * n) * Math.sin(phiPrime - phi0) * Math.cos(phiPrime + phi0) + (1.875D * n * n + 1.875D * n * n * n) * Math.sin(2.0D * (phiPrime - phi0)) * Math.cos(2.0D * (phiPrime + phi0)) - 1.4583333333333333D * n * n * n * Math.sin(3.0D * (phiPrime - phi0)) * Math.cos(3.0D * (phiPrime + phi0)));
/* 181 */       phiPrime += (N - N0 - M) / a * OSGB_F0;
/* 182 */       if (N - N0 - M < 0.001D) {
/* 183 */         double v = a * OSGB_F0 * Math.pow(1.0D - eSquared * Util.sinSquared(phiPrime), -0.5D);
/* 186 */         double rho = a * OSGB_F0 * (1.0D - eSquared) * Math.pow(1.0D - eSquared * Util.sinSquared(phiPrime), -1.5D);
/* 189 */         double etaSquared = v / rho - 1.0D;
/* 190 */         double VII = Math.tan(phiPrime) / 2.0D * rho * v;
/* 191 */         double VIII = Math.tan(phiPrime) / 24.0D * rho * Math.pow(v, 3.0D) * (5.0D + 3.0D * Util.tanSquared(phiPrime) + etaSquared - 9.0D * Util.tanSquared(phiPrime) * etaSquared);
/* 195 */         double IX = Math.tan(phiPrime) / 720.0D * rho * Math.pow(v, 5.0D) * (61.0D + 90.0D * Util.tanSquared(phiPrime) + 45.0D * Util.tanSquared(phiPrime) * Util.tanSquared(phiPrime));
/* 199 */         double X = Util.sec(phiPrime) / v;
/* 200 */         double XI = Util.sec(phiPrime) / 6.0D * v * v * v * (v / rho + 2.0D * Util.tanSquared(phiPrime));
/* 203 */         double XII = Util.sec(phiPrime) / 120.0D * Math.pow(v, 5.0D) * (5.0D + 28.0D * Util.tanSquared(phiPrime) + 24.0D * Util.tanSquared(phiPrime) * Util.tanSquared(phiPrime));
/* 207 */         double XIIA = Util.sec(phiPrime) / 5040.0D * Math.pow(v, 7.0D) * (61.0D + 662.0D * Util.tanSquared(phiPrime) + 1320.0D * Util.tanSquared(phiPrime) * Util.tanSquared(phiPrime) + 720.0D * Util.tanSquared(phiPrime) * Util.tanSquared(phiPrime) * Util.tanSquared(phiPrime));
/* 214 */         phi = phiPrime - VII * Math.pow(E - E0, 2.0D) + VIII * Math.pow(E - E0, 4.0D) - IX * Math.pow(E - E0, 6.0D);
/* 217 */         lambda = lambda0 + X * (E - E0) - XI * Math.pow(E - E0, 3.0D) + XII * Math.pow(E - E0, 5.0D) - XIIA * Math.pow(E - E0, 7.0D);
/* 221 */         return new LatLng(Math.toDegrees(phi), Math.toDegrees(lambda));
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public double getEasting() {
/* 232 */     return this.easting;
/*     */   }
/*     */   
/*     */   public double getNorthing() {
/* 243 */     return this.northing;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar\\uk\me\jstott\jcoord\OSRef.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */