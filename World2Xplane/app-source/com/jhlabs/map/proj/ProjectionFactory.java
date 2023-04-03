/*     */ package com.jhlabs.map.proj;
/*     */ 
/*     */ import com.jhlabs.map.AngleFormat;
/*     */ import com.jhlabs.map.Unit;
/*     */ import com.jhlabs.map.Units;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.File;
/*     */ import java.io.FileReader;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.StreamTokenizer;
/*     */ import java.util.Hashtable;
/*     */ import java.util.StringTokenizer;
/*     */ import java.util.Vector;
/*     */ 
/*     */ public class ProjectionFactory {
/*     */   private static final double SIXTH = 0.16666666666666666D;
/*     */   
/*     */   private static final double RA4 = 0.04722222222222222D;
/*     */   
/*     */   private static final double RA6 = 0.022156084656084655D;
/*     */   
/*     */   private static final double RV4 = 0.06944444444444445D;
/*     */   
/*     */   private static final double RV6 = 0.04243827160493827D;
/*     */   
/*  32 */   private static AngleFormat format = new AngleFormat("DdM", true);
/*     */   
/*     */   static Hashtable registry;
/*     */   
/*     */   public static Projection fromPROJ4Specification(String[] args) {
/*  38 */     Projection projection = null;
/*  39 */     Ellipsoid ellipsoid = null;
/*  40 */     double a = 0.0D, b = 0.0D, es = 0.0D;
/*  42 */     Hashtable params = new Hashtable();
/*  43 */     for (int i = 0; i < args.length; i++) {
/*  44 */       String arg = args[i];
/*  45 */       if (arg.startsWith("+")) {
/*  46 */         int index = arg.indexOf('=');
/*  47 */         if (index != -1) {
/*  48 */           String key = arg.substring(1, index);
/*  49 */           String value = arg.substring(index + 1);
/*  50 */           params.put(key, value);
/*     */         } 
/*     */       } 
/*     */     } 
/*  56 */     String s = (String)params.get("proj");
/*  57 */     if (s != null) {
/*  58 */       projection = getNamedPROJ4Projection(s);
/*  59 */       if (projection == null)
/*  60 */         throw new ProjectionException("Unknown projection: " + s); 
/*     */     } 
/*  63 */     s = (String)params.get("init");
/*  64 */     if (s != null) {
/*  65 */       projection = getNamedPROJ4CoordinateSystem(s);
/*  66 */       if (projection == null)
/*  67 */         throw new ProjectionException("Unknown projection: " + s); 
/*     */     } 
/*  71 */     s = (String)params.get("R");
/*  72 */     if (s != null) {
/*  73 */       a = Double.parseDouble(s);
/*     */     } else {
/*  75 */       s = (String)params.get("ellps");
/*  76 */       if (s != null) {
/*  77 */         Ellipsoid[] ellipsoids = Ellipsoid.ellipsoids;
/*  78 */         for (int j = 0; j < ellipsoids.length; j++) {
/*  79 */           if ((ellipsoids[j]).shortName.equals(s)) {
/*  80 */             ellipsoid = ellipsoids[j];
/*     */             break;
/*     */           } 
/*     */         } 
/*  84 */         if (ellipsoid == null)
/*  85 */           throw new ProjectionException("Unknown ellipsoid: " + s); 
/*  86 */         es = ellipsoid.eccentricity2;
/*  87 */         a = ellipsoid.equatorRadius;
/*     */       } else {
/*  89 */         s = (String)params.get("a");
/*  90 */         if (s != null)
/*  91 */           a = Double.parseDouble(s); 
/*  92 */         s = (String)params.get("es");
/*  93 */         if (s != null) {
/*  94 */           es = Double.parseDouble(s);
/*     */         } else {
/*  96 */           s = (String)params.get("rf");
/*  97 */           if (s != null) {
/*  98 */             es = Double.parseDouble(s);
/*  99 */             es *= 2.0D - es;
/*     */           } else {
/* 101 */             s = (String)params.get("f");
/* 102 */             if (s != null) {
/* 103 */               es = Double.parseDouble(s);
/* 104 */               es = 1.0D / es;
/* 105 */               es *= 2.0D - es;
/*     */             } else {
/* 107 */               s = (String)params.get("b");
/* 108 */               if (s != null) {
/* 109 */                 b = Double.parseDouble(s);
/* 110 */                 es = 1.0D - b * b / a * a;
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/* 115 */         if (b == 0.0D)
/* 116 */           b = a * Math.sqrt(1.0D - es); 
/*     */       } 
/* 119 */       s = (String)params.get("R_A");
/* 120 */       if (s != null && Boolean.getBoolean(s)) {
/* 121 */         a *= 1.0D - es * (0.16666666666666666D + es * (0.04722222222222222D + es * 0.022156084656084655D));
/*     */       } else {
/* 123 */         s = (String)params.get("R_V");
/* 124 */         if (s != null && Boolean.getBoolean(s)) {
/* 125 */           a *= 1.0D - es * (0.16666666666666666D + es * (0.06944444444444445D + es * 0.04243827160493827D));
/*     */         } else {
/* 127 */           s = (String)params.get("R_a");
/* 128 */           if (s != null && Boolean.getBoolean(s)) {
/* 129 */             a = 0.5D * (a + b);
/*     */           } else {
/* 131 */             s = (String)params.get("R_g");
/* 132 */             if (s != null && Boolean.getBoolean(s)) {
/* 133 */               a = Math.sqrt(a * b);
/*     */             } else {
/* 135 */               s = (String)params.get("R_h");
/* 136 */               if (s != null && Boolean.getBoolean(s)) {
/* 137 */                 a = 2.0D * a * b / (a + b);
/* 138 */                 es = 0.0D;
/*     */               } else {
/* 140 */                 s = (String)params.get("R_lat_a");
/* 141 */                 if (s != null) {
/* 142 */                   double tmp = Math.sin(parseAngle(s));
/* 143 */                   if (Math.abs(tmp) > 1.5707963267948966D)
/* 144 */                     throw new ProjectionException("-11"); 
/* 145 */                   tmp = 1.0D - es * tmp * tmp;
/* 146 */                   a *= 0.5D * (1.0D - es + tmp) / tmp * Math.sqrt(tmp);
/* 147 */                   es = 0.0D;
/*     */                 } else {
/* 149 */                   s = (String)params.get("R_lat_g");
/* 150 */                   if (s != null) {
/* 151 */                     double tmp = Math.sin(parseAngle(s));
/* 152 */                     if (Math.abs(tmp) > 1.5707963267948966D)
/* 153 */                       throw new ProjectionException("-11"); 
/* 154 */                     tmp = 1.0D - es * tmp * tmp;
/* 155 */                     a *= Math.sqrt(1.0D - es) / tmp;
/* 156 */                     es = 0.0D;
/*     */                   } 
/*     */                 } 
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 165 */     projection.setEllipsoid(new Ellipsoid("", a, es, ""));
/* 168 */     s = (String)params.get("lat_0");
/* 169 */     if (s != null)
/* 170 */       projection.setProjectionLatitudeDegrees(parseAngle(s)); 
/* 171 */     s = (String)params.get("lon_0");
/* 172 */     if (s != null)
/* 173 */       projection.setProjectionLongitudeDegrees(parseAngle(s)); 
/* 174 */     s = (String)params.get("lat_ts");
/* 175 */     if (s != null)
/* 176 */       projection.setTrueScaleLatitudeDegrees(parseAngle(s)); 
/* 177 */     s = (String)params.get("x_0");
/* 178 */     if (s != null)
/* 179 */       projection.setFalseEasting(Double.parseDouble(s)); 
/* 180 */     s = (String)params.get("y_0");
/* 181 */     if (s != null)
/* 182 */       projection.setFalseNorthing(Double.parseDouble(s)); 
/* 184 */     s = (String)params.get("k_0");
/* 185 */     if (s == null)
/* 186 */       s = (String)params.get("k"); 
/* 187 */     if (s != null)
/* 188 */       projection.setScaleFactor(Double.parseDouble(s)); 
/* 190 */     s = (String)params.get("units");
/* 191 */     if (s != null) {
/* 192 */       Unit unit = Units.findUnits(s);
/* 193 */       if (unit != null)
/* 194 */         projection.setFromMetres(unit.value); 
/*     */     } 
/* 196 */     s = (String)params.get("to_meter");
/* 197 */     if (s != null)
/* 198 */       projection.setFromMetres(1.0D / Double.parseDouble(s)); 
/* 200 */     if (projection instanceof TransverseMercatorProjection) {
/* 201 */       s = (String)params.get("zone");
/* 202 */       if (s != null)
/* 203 */         ((TransverseMercatorProjection)projection).setUTMZone(Integer.parseInt(s)); 
/*     */     } 
/* 216 */     projection.initialize();
/* 218 */     return projection;
/*     */   }
/*     */   
/*     */   private static double parseAngle(String s) {
/* 222 */     return format.parse(s, null).doubleValue();
/*     */   }
/*     */   
/*     */   static void register(String name, Class cls, String description) {
/* 228 */     registry.put(name, cls);
/*     */   }
/*     */   
/*     */   static Projection getNamedPROJ4Projection(String name) {
/* 232 */     if (registry == null)
/* 233 */       initialize(); 
/* 234 */     Class cls = (Class)registry.get(name);
/* 235 */     if (cls != null)
/*     */       try {
/* 237 */         Projection projection = cls.newInstance();
/* 238 */         if (projection != null)
/* 239 */           projection.setName(name); 
/* 240 */         return projection;
/* 242 */       } catch (IllegalAccessException e) {
/*     */       
/* 244 */       } catch (InstantiationException e) {} 
/* 247 */     return null;
/*     */   }
/*     */   
/*     */   static void initialize() {
/* 251 */     registry = new Hashtable();
/* 252 */     register("aea", AlbersProjection.class, "Albers Equal Area");
/* 253 */     register("aeqd", EquidistantAzimuthalProjection.class, "Azimuthal Equidistant");
/* 254 */     register("airy", AiryProjection.class, "Airy");
/* 255 */     register("aitoff", AitoffProjection.class, "Aitoff");
/* 256 */     register("alsk", Projection.class, "Mod. Stereographics of Alaska");
/* 257 */     register("apian", Projection.class, "Apian Globular I");
/* 258 */     register("august", AugustProjection.class, "August Epicycloidal");
/* 259 */     register("bacon", Projection.class, "Bacon Globular");
/* 260 */     register("bipc", BipolarProjection.class, "Bipolar conic of western hemisphere");
/* 261 */     register("boggs", BoggsProjection.class, "Boggs Eumorphic");
/* 262 */     register("bonne", BonneProjection.class, "Bonne (Werner lat_1=90)");
/* 263 */     register("cass", CassiniProjection.class, "Cassini");
/* 264 */     register("cc", CentralCylindricalProjection.class, "Central Cylindrical");
/* 265 */     register("cea", Projection.class, "Equal Area Cylindrical");
/* 267 */     register("collg", CollignonProjection.class, "Collignon");
/* 268 */     register("crast", CrasterProjection.class, "Craster Parabolic (Putnins P4)");
/* 269 */     register("denoy", DenoyerProjection.class, "Denoyer Semi-Elliptical");
/* 270 */     register("eck1", Eckert1Projection.class, "Eckert I");
/* 271 */     register("eck2", Eckert2Projection.class, "Eckert II");
/* 273 */     register("eck4", Eckert4Projection.class, "Eckert IV");
/* 274 */     register("eck5", Eckert5Projection.class, "Eckert V");
/* 276 */     register("eqc", PlateCarreeProjection.class, "Equidistant Cylindrical (Plate Caree)");
/* 277 */     register("eqdc", EquidistantConicProjection.class, "Equidistant Conic");
/* 278 */     register("euler", EulerProjection.class, "Euler");
/* 279 */     register("fahey", FaheyProjection.class, "Fahey");
/* 280 */     register("fouc", FoucautProjection.class, "Foucaut");
/* 281 */     register("fouc_s", FoucautSinusoidalProjection.class, "Foucaut Sinusoidal");
/* 282 */     register("gall", GallProjection.class, "Gall (Gall Stereographic)");
/* 285 */     register("gnom", GnomonicAzimuthalProjection.class, "Gnomonic");
/* 286 */     register("goode", GoodeProjection.class, "Goode Homolosine");
/* 289 */     register("hammer", HammerProjection.class, "Hammer & Eckert-Greifendorff");
/* 290 */     register("hatano", HatanoProjection.class, "Hatano Asymmetrical Equal Area");
/* 292 */     register("kav5", KavraiskyVProjection.class, "Kavraisky V");
/* 296 */     register("lagrng", LagrangeProjection.class, "Lagrange");
/* 297 */     register("larr", LarriveeProjection.class, "Larrivee");
/* 298 */     register("lask", LaskowskiProjection.class, "Laskowski");
/* 299 */     register("lcc", LambertConformalConicProjection.class, "Lambert Conformal Conic");
/* 300 */     register("leac", LambertEqualAreaConicProjection.class, "Lambert Equal Area Conic");
/* 302 */     register("loxim", LoximuthalProjection.class, "Loximuthal");
/* 303 */     register("lsat", LandsatProjection.class, "Space oblique for LANDSAT");
/* 305 */     register("mbt_fps", MBTFPSProjection.class, "McBryde-Thomas Flat-Pole Sine (No. 2)");
/* 306 */     register("mbtfpp", MBTFPPProjection.class, "McBride-Thomas Flat-Polar Parabolic");
/* 307 */     register("mbtfpq", MBTFPQProjection.class, "McBryde-Thomas Flat-Polar Quartic");
/* 309 */     register("merc", MercatorProjection.class, "Mercator");
/* 311 */     register("mill", MillerProjection.class, "Miller Cylindrical");
/* 313 */     register("moll", MolleweideProjection.class, "Mollweide");
/* 314 */     register("murd1", Murdoch1Projection.class, "Murdoch I");
/* 315 */     register("murd2", Murdoch2Projection.class, "Murdoch II");
/* 316 */     register("murd3", Murdoch3Projection.class, "Murdoch III");
/* 317 */     register("nell", NellProjection.class, "Nell");
/* 319 */     register("nicol", NicolosiProjection.class, "Nicolosi Globular");
/* 320 */     register("nsper", PerspectiveProjection.class, "Near-sided perspective");
/* 325 */     register("omerc", ObliqueMercatorProjection.class, "Oblique Mercator");
/* 327 */     register("ortho", OrthographicAzimuthalProjection.class, "Orthographic");
/* 328 */     register("pconic", PerspectiveConicProjection.class, "Perspective Conic");
/* 329 */     register("poly", PolyconicProjection.class, "Polyconic (American)");
/* 331 */     register("putp2", PutninsP2Projection.class, "Putnins P2");
/* 334 */     register("putp4p", PutninsP4Projection.class, "Putnins P4'");
/* 335 */     register("putp5", PutninsP5Projection.class, "Putnins P5");
/* 336 */     register("putp5p", PutninsP5PProjection.class, "Putnins P5'");
/* 339 */     register("qua_aut", QuarticAuthalicProjection.class, "Quartic Authalic");
/* 340 */     register("robin", RobinsonProjection.class, "Robinson");
/* 341 */     register("rpoly", RectangularPolyconicProjection.class, "Rectangular Polyconic");
/* 342 */     register("sinu", SinusoidalProjection.class, "Sinusoidal (Sanson-Flamsteed)");
/* 344 */     register("stere", StereographicAzimuthalProjection.class, "Stereographic");
/* 345 */     register("tcc", TCCProjection.class, "Transverse Central Cylindrical");
/* 346 */     register("tcea", TCEAProjection.class, "Transverse Cylindrical Equal Area");
/* 348 */     register("tmerc", TransverseMercatorProjection.class, "Transverse Mercator");
/* 353 */     register("urmfps", URMFPSProjection.class, "Urmaev Flat-Polar Sinusoidal");
/* 354 */     register("utm", TransverseMercatorProjection.class, "Universal Transverse Mercator (UTM)");
/* 355 */     register("vandg", VanDerGrintenProjection.class, "van der Grinten (I)");
/* 359 */     register("vitk1", VitkovskyProjection.class, "Vitkovsky I");
/* 360 */     register("wag1", Wagner1Projection.class, "Wagner I (Kavraisky VI)");
/* 361 */     register("wag2", Wagner2Projection.class, "Wagner II");
/* 362 */     register("wag3", Wagner3Projection.class, "Wagner III");
/* 363 */     register("wag4", Wagner4Projection.class, "Wagner IV");
/* 364 */     register("wag5", Wagner5Projection.class, "Wagner V");
/* 366 */     register("wag7", Wagner7Projection.class, "Wagner VII");
/* 367 */     register("weren", WerenskioldProjection.class, "Werenskiold I");
/* 370 */     register("wintri", WinkelTripelProjection.class, "Winkel Tripel");
/*     */   }
/*     */   
/*     */   public static Projection readProjectionFile(String file, String name) throws IOException {
/* 374 */     BufferedReader reader = new BufferedReader(new InputStreamReader(ProjectionFactory.class.getResourceAsStream("/nad/" + file)));
/* 375 */     StreamTokenizer t = new StreamTokenizer(reader);
/* 376 */     t.commentChar(35);
/* 377 */     t.ordinaryChars(48, 57);
/* 378 */     t.ordinaryChars(46, 46);
/* 379 */     t.ordinaryChars(45, 45);
/* 380 */     t.ordinaryChars(43, 43);
/* 381 */     t.wordChars(48, 57);
/* 382 */     t.wordChars(39, 39);
/* 383 */     t.wordChars(34, 34);
/* 384 */     t.wordChars(95, 95);
/* 385 */     t.wordChars(46, 46);
/* 386 */     t.wordChars(45, 45);
/* 387 */     t.wordChars(43, 43);
/* 388 */     t.wordChars(44, 44);
/* 389 */     t.nextToken();
/* 390 */     while (t.ttype == 60) {
/* 391 */       t.nextToken();
/* 392 */       if (t.ttype != -3)
/* 393 */         throw new IOException(t.lineno() + ": Word expected after '<'"); 
/* 394 */       String cname = t.sval;
/* 395 */       t.nextToken();
/* 396 */       if (t.ttype != 62)
/* 397 */         throw new IOException(t.lineno() + ": '>' expected"); 
/* 398 */       t.nextToken();
/* 399 */       Vector v = new Vector();
/* 400 */       String values = "";
/* 401 */       while (t.ttype != 60) {
/* 402 */         if (t.ttype == 43)
/* 403 */           t.nextToken(); 
/* 404 */         if (t.ttype != -3)
/* 405 */           throw new IOException(t.lineno() + ": Word expected after '+'"); 
/* 406 */         String key = t.sval;
/* 407 */         t.nextToken();
/* 408 */         if (t.ttype == 61) {
/* 409 */           t.nextToken();
/* 410 */           if (t.ttype != -3)
/* 411 */             throw new IOException(t.lineno() + ": Value expected after '='"); 
/* 412 */           String value = t.sval;
/* 413 */           t.nextToken();
/* 414 */           if (key.startsWith("+")) {
/* 415 */             v.add(key + "=" + value);
/*     */             continue;
/*     */           } 
/* 417 */           v.add("+" + key + "=" + value);
/*     */         } 
/*     */       } 
/* 420 */       t.nextToken();
/* 421 */       if (t.ttype != 62)
/* 422 */         throw new IOException(t.lineno() + ": '<>' expected"); 
/* 423 */       t.nextToken();
/* 424 */       if (cname.equals(name)) {
/* 425 */         String[] args = new String[v.size()];
/* 426 */         v.copyInto((Object[])args);
/* 427 */         reader.close();
/* 428 */         return fromPROJ4Specification(args);
/*     */       } 
/*     */     } 
/* 431 */     reader.close();
/* 432 */     return null;
/*     */   }
/*     */   
/*     */   public static Projection getNamedPROJ4CoordinateSystem(String name) {
/* 436 */     String[] files = { "world", "nad83", "nad27", "esri", "epsg" };
/*     */     try {
/* 444 */       for (int i = 0; i < files.length; i++) {
/* 445 */         Projection projection = readProjectionFile(files[i], name);
/* 446 */         if (projection != null)
/* 447 */           return projection; 
/*     */       } 
/* 450 */     } catch (IOException e) {
/* 451 */       e.printStackTrace();
/*     */     } 
/* 453 */     return null;
/*     */   }
/*     */   
/*     */   public static void main(String[] args) {
/* 457 */     Projection projection = fromPROJ4Specification(args);
/* 458 */     if (projection != null) {
/* 459 */       System.out.println(projection.getPROJ4Description());
/* 460 */       for (int i = 0; i < args.length; i++) {
/* 461 */         String arg = args[i];
/* 462 */         if (!arg.startsWith("+") && !arg.startsWith("-"))
/*     */           try {
/* 464 */             BufferedReader reader = new BufferedReader(new FileReader(new File(args[i])));
/* 465 */             Point2D.Double p = new Point2D.Double();
/*     */             String line;
/* 467 */             while ((line = reader.readLine()) != null) {
/* 468 */               StringTokenizer t = new StringTokenizer(line, " ");
/* 469 */               String slon = t.nextToken();
/* 470 */               String slat = t.nextToken();
/* 471 */               p.x = format.parse(slon, null).doubleValue();
/* 472 */               p.y = format.parse(slat, null).doubleValue();
/* 473 */               projection.transform(p, p);
/* 474 */               System.out.println(p.x + " " + p.y);
/*     */             } 
/* 477 */           } catch (IOException e) {
/* 478 */             System.out.println("IOException: " + args[i] + ": " + e.getMessage());
/*     */           }  
/*     */       } 
/*     */     } else {
/* 483 */       System.out.println("Can't find projection " + args[0]);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\jhlabs\map\proj\ProjectionFactory.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */