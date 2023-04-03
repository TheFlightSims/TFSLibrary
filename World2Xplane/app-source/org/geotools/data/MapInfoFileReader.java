/*     */ package org.geotools.data;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.File;
/*     */ import java.io.FileReader;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStreamReader;
/*     */ import java.net.URL;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ import org.geotools.geometry.DirectPosition2D;
/*     */ import org.geotools.referencing.CRS;
/*     */ import org.geotools.referencing.operation.builder.AffineTransformBuilder;
/*     */ import org.geotools.referencing.operation.builder.MappedPosition;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ import org.geotools.util.logging.Logging;
/*     */ import org.opengis.geometry.DirectPosition;
/*     */ import org.opengis.referencing.FactoryException;
/*     */ import org.opengis.referencing.crs.CoordinateReferenceSystem;
/*     */ import org.opengis.referencing.operation.MathTransform;
/*     */ 
/*     */ public class MapInfoFileReader {
/*  58 */   private static final Logger LOGGER = Logging.getLogger("org.geotools.data.MapInfoFileReader");
/*     */   
/*  64 */   private static Map<Integer, String> PROJECTIONS = new HashMap<Integer, String>();
/*     */   
/*  69 */   private static Map<Integer, String> DATUMS = new HashMap<Integer, String>();
/*     */   
/*  74 */   private static Map<String, String> UNITS = new HashMap<String, String>();
/*     */   
/*  79 */   private static final String[] PARAMETERS1 = new String[] { "central_meridian", "latitude_of_origin", "standard_parallel_1", "standard_parallel_2", "false_easting", "false_northing" };
/*     */   
/*  80 */   private static final String[] PARAMETERS2 = new String[] { "central_meridian", "latitude_of_origin", "scale_factor", "false_easting", "false_northing" };
/*     */   
/*     */   static {
/*  83 */     PROJECTIONS.put(Integer.valueOf(2), "Cylindrical_Equal_Area");
/*  84 */     PROJECTIONS.put(Integer.valueOf(3), "Lambert_Conformal_Conic_2SP");
/*  85 */     PROJECTIONS.put(Integer.valueOf(4), "Lambert_Azimuthal_Equal_Area");
/*  86 */     PROJECTIONS.put(Integer.valueOf(7), "Hotine_Oblique_Mercator");
/*  87 */     PROJECTIONS.put(Integer.valueOf(8), "Transverse_Mercator");
/*  88 */     PROJECTIONS.put(Integer.valueOf(9), "Albers_Conic_Equal_Area");
/*  89 */     PROJECTIONS.put(Integer.valueOf(10), "Mercator_1SP");
/*  90 */     PROJECTIONS.put(Integer.valueOf(18), "New_Zealand_Map_Grid");
/*  91 */     PROJECTIONS.put(Integer.valueOf(19), "Lambert_Conformal_Conic_2SP_Belgium");
/*  92 */     PROJECTIONS.put(Integer.valueOf(26), "Mercator_1SP");
/*  93 */     PROJECTIONS.put(Integer.valueOf(27), "Polyconic");
/*  94 */     PROJECTIONS.put(Integer.valueOf(28), "Lambert_Azimuthal_Equal_Area");
/*  95 */     PROJECTIONS.put(Integer.valueOf(30), "Cassini_Soldner");
/*  96 */     PROJECTIONS.put(Integer.valueOf(32), "Krovak");
/*  98 */     DATUMS.put(Integer.valueOf(1), "GEOGCS[\"Adindan\", DATUM[\"Adindan\", SPHEROID[\"Clarke 1880 (RGS)\",6378249.145,293.465], TOWGS84[-166,-15,204,0,0,0,0]], PRIMEM[\"Greenwich\",0], UNIT[\"degree\",0.0174532925199433]]");
/*  99 */     DATUMS.put(Integer.valueOf(2), "GEOGCS[\"Afgooye\", DATUM[\"Afgooye\", SPHEROID[\"Krassowsky 1940\",6378245,298.3], TOWGS84[-43,-163,45,0,0,0,0]], PRIMEM[\"Greenwich\",0], UNIT[\"degree\",0.0174532925199433]]");
/* 100 */     DATUMS.put(Integer.valueOf(3), "GEOGCS[\"Ain el Abd\", DATUM[\"Ain_el_Abd_1970\", SPHEROID[\"International 1924\",6378388,297], TOWGS84[-143,-236,7,0,0,0,0]], PRIMEM[\"Greenwich\",0], UNIT[\"degree\",0.0174532925199433]]");
/* 101 */     DATUMS.put(Integer.valueOf(5), "GEOGCS[\"Arc 1950\", DATUM[\"Arc_1950\", SPHEROID[\"Clarke 1880 (Arc)\",6378249.145,293.4663077], TOWGS84[-143,-90,-294,0,0,0,0]], PRIMEM[\"Greenwich\",0], UNIT[\"degree\",0.0174532925199433]]");
/* 102 */     DATUMS.put(Integer.valueOf(6), "GEOGCS[\"Arc 1960\", DATUM[\"Arc_1960\", SPHEROID[\"Clarke 1880 (RGS)\",6378249.145,293.465], TOWGS84[-160,-6,-302,0,0,0,0]], PRIMEM[\"Greenwich\",0], UNIT[\"degree\",0.0174532925199433]]");
/* 103 */     DATUMS.put(Integer.valueOf(12), "GEOGCS[\"AGD66\", DATUM[\"Australian_Geodetic_Datum_1966\", SPHEROID[\"Australian National Spheroid\",6378160,298.25], TOWGS84[-117.808,-51.536,137.784,0.303,0.446,0.234,-0.29]], PRIMEM[\"Greenwich\",0], UNIT[\"degree\",0.0174532925199433]]");
/* 104 */     DATUMS.put(Integer.valueOf(13), "GEOGCS[\"AGD84\", DATUM[\"Australian_Geodetic_Datum_1984\", SPHEROID[\"Australian National Spheroid\",6378160,298.25], TOWGS84[-134,-48,149,0,0,0,0]], PRIMEM[\"Greenwich\",0], UNIT[\"degree\",0.0174532925199433]]");
/* 105 */     DATUMS.put(Integer.valueOf(15), "GEOGCS[\"Bermuda 1957\", DATUM[\"Bermuda_1957\", SPHEROID[\"Clarke 1866\",6378206.4,294.9786982139006], TOWGS84[-73,213,296,0,0,0,0]], PRIMEM[\"Greenwich\",0], UNIT[\"degree\",0.0174532925199433]]");
/* 106 */     DATUMS.put(Integer.valueOf(16), "GEOGCS[\"Bogota 1975\", DATUM[\"Bogota_1975\", SPHEROID[\"International 1924\",6378388,297], TOWGS84[307,304,-318,0,0,0,0]], PRIMEM[\"Greenwich\",0], UNIT[\"degree\",0.0174532925199433]]");
/* 107 */     DATUMS.put(Integer.valueOf(17), "GEOGCS[\"Campo Inchauspe\", DATUM[\"Campo_Inchauspe\", SPHEROID[\"International 1924\",6378388,297], TOWGS84[-148,136,90,0,0,0,0]], PRIMEM[\"Greenwich\",0], UNIT[\"degree\",0.0174532925199433]]");
/* 108 */     DATUMS.put(Integer.valueOf(19), "GEOGCS[\"Cape\", DATUM[\"Cape\", SPHEROID[\"Clarke 1880 (Arc)\",6378249.145,293.4663077], TOWGS84[-136,-108,-292,0,0,0,0]], PRIMEM[\"Greenwich\",0], UNIT[\"degree\",0.0174532925199433]]");
/* 109 */     DATUMS.put(Integer.valueOf(20), "GEOGCS[\"Cape Canaveral\", DATUM[\"Cape_Canaveral\", SPHEROID[\"Clarke 1866\",6378206.4,294.9786982139006], TOWGS84[-2,151,181,0,0,0,0]], PRIMEM[\"Greenwich\",0], UNIT[\"degree\",0.0174532925199433]]");
/* 110 */     DATUMS.put(Integer.valueOf(21), "GEOGCS[\"Carthage\", DATUM[\"Carthage\", SPHEROID[\"Clarke 1880 (IGN)\",6378249.2,293.4660212936265], TOWGS84[-263,6,431,0,0,0,0]], PRIMEM[\"Greenwich\",0], UNIT[\"degree\",0.0174532925199433]]");
/* 111 */     DATUMS.put(Integer.valueOf(22), "GEOGCS[\"Chatham Islands 1971\", DATUM[\"Chatham_Islands_Datum_1971\", SPHEROID[\"International 1924\",6378388,297], TOWGS84[175,-38,113,0,0,0,0]], PRIMEM[\"Greenwich\",0], UNIT[\"degree\",0.0174532925199433]]");
/* 112 */     DATUMS.put(Integer.valueOf(23), "GEOGCS[\"Chua\", DATUM[\"Chua\", SPHEROID[\"International 1924\",6378388,297], TOWGS84[-134,229,-29,0,0,0,0]], PRIMEM[\"Greenwich\",0], UNIT[\"degree\",0.0174532925199433]]");
/* 113 */     DATUMS.put(Integer.valueOf(24), "GEOGCS[\"Corrego Alegre 1970-72\", DATUM[\"Corrego_Alegre_1970_72\", SPHEROID[\"International 1924\",6378388,297], TOWGS84[-206,172,-6,0,0,0,0]], PRIMEM[\"Greenwich\",0], UNIT[\"degree\",0.0174532925199433]]");
/* 114 */     DATUMS.put(Integer.valueOf(25), "GEOGCS[\"Batavia\", DATUM[\"Batavia\", SPHEROID[\"Bessel 1841\",6377397.155,299.1528128], TOWGS84[-377,681,-50,0,0,0,0]], PRIMEM[\"Greenwich\",0], UNIT[\"degree\",0.0174532925199433]]");
/* 115 */     DATUMS.put(Integer.valueOf(27), "GEOGCS[\"Easter Island 1967\", DATUM[\"Easter_Island_1967\", SPHEROID[\"International 1924\",6378388,297], TOWGS84[211,147,111,0,0,0,0]], PRIMEM[\"Greenwich\",0], UNIT[\"degree\",0.0174532925199433]]");
/* 116 */     DATUMS.put(Integer.valueOf(28), "GEOGCS[\"ED50\", DATUM[\"European_Datum_1950\", SPHEROID[\"International 1924\",6378388,297], TOWGS84[-87,-98,-121,0,0,0,0]], PRIMEM[\"Greenwich\",0], UNIT[\"degree\",0.0174532925199433]]");
/* 117 */     DATUMS.put(Integer.valueOf(29), "GEOGCS[\"ED79\", DATUM[\"European_Datum_1979\", SPHEROID[\"International 1924\",6378388,297], TOWGS84[-86,-98,-119,0,0,0,0]], PRIMEM[\"Greenwich\",0], UNIT[\"degree\",0.0174532925199433]]");
/* 118 */     DATUMS.put(Integer.valueOf(30), "GEOGCS[\"Gandajika 1970\", DATUM[\"Gandajika_1970\", SPHEROID[\"International 1924\",6378388,297], TOWGS84[-133,-321,50,0,0,0,0]], PRIMEM[\"Greenwich\",0], UNIT[\"degree\",0.0174532925199433]]");
/* 119 */     DATUMS.put(Integer.valueOf(31), "GEOGCS[\"NZGD49\", DATUM[\"New_Zealand_Geodetic_Datum_1949\", SPHEROID[\"International 1924\",6378388,297], TOWGS84[59.47,-5.04,187.44,0.47,-0.1,1.024,-4.5993]], PRIMEM[\"Greenwich\",0], UNIT[\"degree\",0.0174532925199433]]");
/* 120 */     DATUMS.put(Integer.valueOf(34), "GEOGCS[\"Guam 1963\", DATUM[\"Guam_1963\", SPHEROID[\"Clarke 1866\",6378206.4,294.9786982139006], TOWGS84[-100,-248,259,0,0,0,0]], PRIMEM[\"Greenwich\",0], UNIT[\"degree\",0.0174532925199433]]");
/* 121 */     DATUMS.put(Integer.valueOf(36), "GEOGCS[\"Hito XVIII 1963\", DATUM[\"Hito_XVIII_1963\", SPHEROID[\"International 1924\",6378388,297], TOWGS84[16,196,93,0,0,0,0]], PRIMEM[\"Greenwich\",0], UNIT[\"degree\",0.0174532925199433]]");
/* 122 */     DATUMS.put(Integer.valueOf(37), "GEOGCS[\"Hjorsey 1955\", DATUM[\"Hjorsey_1955\", SPHEROID[\"International 1924\",6378388,297], TOWGS84[-73,46,-86,0,0,0,0]], PRIMEM[\"Greenwich\",0], UNIT[\"degree\",0.0174532925199433]]");
/* 123 */     DATUMS.put(Integer.valueOf(38), "GEOGCS[\"Hong Kong 1963\", DATUM[\"Hong_Kong_1963\", SPHEROID[\"Clarke 1858\",6378293.645208759,294.2606763692569]], PRIMEM[\"Greenwich\",0], UNIT[\"degree\",0.0174532925199433]]");
/* 124 */     DATUMS.put(Integer.valueOf(39), "GEOGCS[\"Hu Tzu Shan 1950\", DATUM[\"Hu_Tzu_Shan_1950\", SPHEROID[\"International 1924\",6378388,297], TOWGS84[-637,-549,-203,0,0,0,0]], PRIMEM[\"Greenwich\",0], UNIT[\"degree\",0.0174532925199433]]");
/* 125 */     DATUMS.put(Integer.valueOf(44), "GEOGCS[\"Johnston Island 1961\", DATUM[\"Johnston_Island_1961\", SPHEROID[\"International 1924\",6378388,297], TOWGS84[189,-79,-202,0,0,0,0]], PRIMEM[\"Greenwich\",0], UNIT[\"degree\",0.0174532925199433]]");
/* 126 */     DATUMS.put(Integer.valueOf(45), "GEOGCS[\"Kandawala\", DATUM[\"Kandawala\", SPHEROID[\"Everest 1830 (1937 Adjustment)\",6377276.345,300.8017], TOWGS84[-97,787,86,0,0,0,0]], PRIMEM[\"Greenwich\",0], UNIT[\"degree\",0.0174532925199433]]");
/* 127 */     DATUMS.put(Integer.valueOf(47), "GEOGCS[\"Kertau 1968\", DATUM[\"Kertau_1968\", SPHEROID[\"Everest 1830 Modified\",6377304.063,300.8017], TOWGS84[-11,851,5,0,0,0,0]], PRIMEM[\"Greenwich\",0], UNIT[\"degree\",0.0174532925199433]]");
/* 128 */     DATUMS.put(Integer.valueOf(49), "GEOGCS[\"Liberia 1964\", DATUM[\"Liberia_1964\", SPHEROID[\"Clarke 1880 (RGS)\",6378249.145,293.465], TOWGS84[-90,40,88,0,0,0,0]], PRIMEM[\"Greenwich\",0], UNIT[\"degree\",0.0174532925199433]]");
/* 129 */     DATUMS.put(Integer.valueOf(52), "GEOGCS[\"Mahe 1971\", DATUM[\"Mahe_1971\", SPHEROID[\"Clarke 1880 (RGS)\",6378249.145,293.465], TOWGS84[41,-220,-134,0,0,0,0]], PRIMEM[\"Greenwich\",0], UNIT[\"degree\",0.0174532925199433]]");
/* 130 */     DATUMS.put(Integer.valueOf(54), "GEOGCS[\"Massawa\", DATUM[\"Massawa\", SPHEROID[\"Bessel 1841\",6377397.155,299.1528128], TOWGS84[639,405,60,0,0,0,0]], PRIMEM[\"Greenwich\",0], UNIT[\"degree\",0.0174532925199433]]");
/* 131 */     DATUMS.put(Integer.valueOf(55), "GEOGCS[\"Merchich\", DATUM[\"Merchich\", SPHEROID[\"Clarke 1880 (IGN)\",6378249.2,293.4660212936265], TOWGS84[31,146,47,0,0,0,0]], PRIMEM[\"Greenwich\",0], UNIT[\"degree\",0.0174532925199433]]");
/* 132 */     DATUMS.put(Integer.valueOf(57), "GEOGCS[\"Minna\", DATUM[\"Minna\", SPHEROID[\"Clarke 1880 (RGS)\",6378249.145,293.465], TOWGS84[-92,-93,122,0,0,0,0]], PRIMEM[\"Greenwich\",0], UNIT[\"degree\",0.0174532925199433]]");
/* 133 */     DATUMS.put(Integer.valueOf(61), "GEOGCS[\"Naparima 1972\", DATUM[\"Naparima_1972\", SPHEROID[\"International 1924\",6378388,297], TOWGS84[-10,375,165,0,0,0,0]], PRIMEM[\"Greenwich\",0], UNIT[\"degree\",0.0174532925199433]]");
/* 134 */     DATUMS.put(Integer.valueOf(62), "GEOGCS[\"NAD27\", DATUM[\"North_American_Datum_1927\", SPHEROID[\"Clarke 1866\",6378206.4,294.9786982139006]], PRIMEM[\"Greenwich\",0], UNIT[\"degree\",0.0174532925199433]]");
/* 135 */     DATUMS.put(Integer.valueOf(74), "GEOGCS[\"NAD83\", DATUM[\"North_American_Datum_1983\", SPHEROID[\"GRS 1980\",6378137,298.257222101], TOWGS84[0,0,0,0,0,0,0]], PRIMEM[\"Greenwich\",0], UNIT[\"degree\",0.0174532925199433]]");
/* 136 */     DATUMS.put(Integer.valueOf(77), "GEOGCS[\"Old Hawaiian\", DATUM[\"Old_Hawaiian\", SPHEROID[\"Clarke 1866\",6378206.4,294.9786982139006], TOWGS84[61,-285,-181,0,0,0,0]], PRIMEM[\"Greenwich\",0], UNIT[\"degree\",0.0174532925199433]]");
/* 137 */     DATUMS.put(Integer.valueOf(79), "GEOGCS[\"OSGB 1936\", DATUM[\"OSGB_1936\", SPHEROID[\"Airy 1830\",6377563.396,299.3249646], TOWGS84[446.448,-125.157,542.06,0.15,0.247,0.842,-20.489]], PRIMEM[\"Greenwich\",0], UNIT[\"degree\",0.0174532925199433]]");
/* 138 */     DATUMS.put(Integer.valueOf(81), "GEOGCS[\"Pitcairn 1967\", DATUM[\"Pitcairn_1967\", SPHEROID[\"International 1924\",6378388,297], TOWGS84[185,165,42,0,0,0,0]], PRIMEM[\"Greenwich\",0], UNIT[\"degree\",0.0174532925199433]]");
/* 139 */     DATUMS.put(Integer.valueOf(82), "GEOGCS[\"PSAD56\", DATUM[\"Provisional_South_American_Datum_1956\", SPHEROID[\"International 1924\",6378388,297], TOWGS84[-288,175,-376,0,0,0,0]], PRIMEM[\"Greenwich\",0], UNIT[\"degree\",0.0174532925199433]]");
/* 140 */     DATUMS.put(Integer.valueOf(83), "GEOGCS[\"Puerto Rico\", DATUM[\"Puerto_Rico\", SPHEROID[\"Clarke 1866\",6378206.4,294.9786982139006], TOWGS84[11,72,-101,0,0,0,0]], PRIMEM[\"Greenwich\",0], UNIT[\"degree\",0.0174532925199433]]");
/* 141 */     DATUMS.put(Integer.valueOf(84), "GEOGCS[\"QND95\", DATUM[\"Qatar_National_Datum_1995\", SPHEROID[\"International 1924\",6378388,297], TOWGS84[-119.425,-303.659,-11.0006,1.1643,0.174458,1.09626,3.65706]], PRIMEM[\"Greenwich\",0], UNIT[\"degree\",0.0174532925199433]]");
/* 142 */     DATUMS.put(Integer.valueOf(85), "GEOGCS[\"Qornoq\", DATUM[\"Qornoq\", SPHEROID[\"International 1924\",6378388,297], TOWGS84[164,138,-189,0,0,0,0]], PRIMEM[\"Greenwich\",0], UNIT[\"degree\",0.0174532925199433]]");
/* 143 */     DATUMS.put(Integer.valueOf(86), "GEOGCS[\"RGR92\", DATUM[\"Reseau_Geodesique_de_la_Reunion_1992\", SPHEROID[\"GRS 1980\",6378137,298.257222101], TOWGS84[0,0,0,0,0,0,0]], PRIMEM[\"Greenwich\",0], UNIT[\"degree\",0.0174532925199433]]");
/* 144 */     DATUMS.put(Integer.valueOf(87), "GEOGCS[\"Monte Mario\", DATUM[\"Monte_Mario\", SPHEROID[\"International 1924\",6378388,297], TOWGS84[-104.1,-49.1,-9.9,0.971,-2.917,0.714,-11.68]], PRIMEM[\"Greenwich\",0], UNIT[\"degree\",0.0174532925199433]]");
/* 145 */     DATUMS.put(Integer.valueOf(90), "GEOGCS[\"Sapper Hill 1943\", DATUM[\"Sapper_Hill_1943\", SPHEROID[\"International 1924\",6378388,297], TOWGS84[-355,21,72,0,0,0,0]], PRIMEM[\"Greenwich\",0], UNIT[\"degree\",0.0174532925199433]]");
/* 146 */     DATUMS.put(Integer.valueOf(91), "GEOGCS[\"Schwarzeck\", DATUM[\"Schwarzeck\", SPHEROID[\"Bessel Namibia (GLM)\",6377483.865280419,299.1528128], TOWGS84[616,97,-251,0,0,0,0]], PRIMEM[\"Greenwich\",0], UNIT[\"degree\",0.0174532925199433]]");
/* 147 */     DATUMS.put(Integer.valueOf(92), "GEOGCS[\"SAD69\", DATUM[\"South_American_Datum_1969\", SPHEROID[\"GRS 1967\",6378160,298.247167427], TOWGS84[-57,1,-41,0,0,0,0]], PRIMEM[\"Greenwich\",0], UNIT[\"degree\",0.0174532925199433]]");
/* 148 */     DATUMS.put(Integer.valueOf(92), "GEOGCS[\"SAD69\", DATUM[\"South_American_Datum_1969\", SPHEROID[\"GRS 1967 Modified\",6378160,298.25], TOWGS84[-57,1,-41,0,0,0,0]], PRIMEM[\"Greenwich\",0], UNIT[\"degree\",0.0174532925199433]]");
/* 149 */     DATUMS.put(Integer.valueOf(96), "GEOGCS[\"Timbalai 1948\", DATUM[\"Timbalai_1948\", SPHEROID[\"Everest 1830 (1967 Definition)\",6377298.556,300.8017], TOWGS84[-679,669,-48,0,0,0,0]], PRIMEM[\"Greenwich\",0], UNIT[\"degree\",0.0174532925199433]]");
/* 150 */     DATUMS.put(Integer.valueOf(97), "GEOGCS[\"Tokyo\", DATUM[\"Tokyo\", SPHEROID[\"Bessel 1841\",6377397.155,299.1528128], TOWGS84[-146.414,507.337,680.507,0,0,0,0]], PRIMEM[\"Greenwich\",0], UNIT[\"degree\",0.0174532925199433]]");
/* 151 */     DATUMS.put(Integer.valueOf(99), "GEOGCS[\"Viti Levu 1916\", DATUM[\"Viti_Levu_1916\", SPHEROID[\"Clarke 1880 (RGS)\",6378249.145,293.465], TOWGS84[51,391,-36,0,0,0,0]], PRIMEM[\"Greenwich\",0], UNIT[\"degree\",0.0174532925199433]]");
/* 152 */     DATUMS.put(Integer.valueOf(102), "GEOGCS[\"WGS 66\", DATUM[\"World_Geodetic_System_1966\", SPHEROID[\"NWL 9D\",6378145,298.25]], PRIMEM[\"Greenwich\",0], UNIT[\"degree\",0.0174532925199433]]");
/* 153 */     DATUMS.put(Integer.valueOf(103), "GEOGCS[\"WGS 72\", DATUM[\"WGS_1972\", SPHEROID[\"WGS 72\",6378135,298.26], TOWGS84[0,0,4.5,0,0,0.554,0.2263]], PRIMEM[\"Greenwich\",0], UNIT[\"degree\",0.0174532925199433]]");
/* 154 */     DATUMS.put(Integer.valueOf(104), "GEOGCS[\"WGS 84\", DATUM[\"WGS_1984\", SPHEROID[\"WGS 84\",6378137,298.257223563]], PRIMEM[\"Greenwich\",0], UNIT[\"degree\",0.0174532925199433]]");
/* 155 */     DATUMS.put(Integer.valueOf(105), "GEOGCS[\"Yacare\", DATUM[\"Yacare\", SPHEROID[\"International 1924\",6378388,297], TOWGS84[-155,171,37,0,0,0,0]], PRIMEM[\"Greenwich\",0], UNIT[\"degree\",0.0174532925199433]]");
/* 156 */     DATUMS.put(Integer.valueOf(106), "GEOGCS[\"Zanderij\", DATUM[\"Zanderij\", SPHEROID[\"International 1924\",6378388,297], TOWGS84[-265,120,-358,0,0,0,0]], PRIMEM[\"Greenwich\",0], UNIT[\"degree\",0.0174532925199433]]");
/* 157 */     DATUMS.put(Integer.valueOf(108), "GEOGCS[\"ED87\", DATUM[\"European_Datum_1987\", SPHEROID[\"International 1924\",6378388,297], TOWGS84[-83.11,-97.38,-117.22,0.00569291,-0.0446976,0.0442851,0.1218]], PRIMEM[\"Greenwich\",0], UNIT[\"degree\",0.0174532925199433]]");
/* 158 */     DATUMS.put(Integer.valueOf(112), "GEOGCS[\"RT90\", DATUM[\"Rikets_koordinatsystem_1990\", SPHEROID[\"Bessel 1841\",6377397.155,299.1528128], TOWGS84[414.1,41.3,603.1,-0.855,2.141,-7.023,0]], PRIMEM[\"Greenwich\",0], UNIT[\"degree\",0.0174532925199433]]");
/* 159 */     DATUMS.put(Integer.valueOf(117), "GEOGCS[\"NZGD2000\", DATUM[\"New_Zealand_Geodetic_Datum_2000\", SPHEROID[\"GRS 1980\",6378137,298.257222101], TOWGS84[0,0,0,0,0,0,0]], PRIMEM[\"Greenwich\",0], UNIT[\"degree\",0.0174532925199433]]");
/* 160 */     DATUMS.put(Integer.valueOf(118), "GEOGCS[\"American Samoa 1962\", DATUM[\"American_Samoa_1962\", SPHEROID[\"Clarke 1866\",6378206.4,294.9786982139006], TOWGS84[-115,118,426,0,0,0,0]], PRIMEM[\"Greenwich\",0], UNIT[\"degree\",0.0174532925199433]]");
/* 161 */     DATUMS.put(Integer.valueOf(120), "GEOGCS[\"Ayabelle Lighthouse\", DATUM[\"Ayabelle_Lighthouse\", SPHEROID[\"Clarke 1880 (RGS)\",6378249.145,293.465], TOWGS84[-79,-129,145,0,0,0,0]], PRIMEM[\"Greenwich\",0], UNIT[\"degree\",0.0174532925199433]]");
/* 162 */     DATUMS.put(Integer.valueOf(121), "GEOGCS[\"Bukit Rimpah\", DATUM[\"Bukit_Rimpah\", SPHEROID[\"Bessel 1841\",6377397.155,299.1528128], TOWGS84[-384,664,-48,0,0,0,0]], PRIMEM[\"Greenwich\",0], UNIT[\"degree\",0.0174532925199433]]");
/* 163 */     DATUMS.put(Integer.valueOf(123), "GEOGCS[\"Dabola 1981\", DATUM[\"Dabola_1981\", SPHEROID[\"Clarke 1880 (IGN)\",6378249.2,293.4660212936265], TOWGS84[-83,37,124,0,0,0,0]], PRIMEM[\"Greenwich\",0], UNIT[\"degree\",0.0174532925199433]]");
/* 164 */     DATUMS.put(Integer.valueOf(124), "GEOGCS[\"Deception Island\", DATUM[\"Deception_Island\", SPHEROID[\"Clarke 1880 (RGS)\",6378249.145,293.465], TOWGS84[260,12,-147,0,0,0,0]], PRIMEM[\"Greenwich\",0], UNIT[\"degree\",0.0174532925199433]]");
/* 165 */     DATUMS.put(Integer.valueOf(127), "GEOGCS[\"Herat North\", DATUM[\"Herat_North\", SPHEROID[\"International 1924\",6378388,297], TOWGS84[-333,-222,114,0,0,0,0]], PRIMEM[\"Greenwich\",0], UNIT[\"degree\",0.0174532925199433]]");
/* 166 */     DATUMS.put(Integer.valueOf(129), "GEOGCS[\"Indian 1975\", DATUM[\"Indian_1975\", SPHEROID[\"Everest 1830 (1937 Adjustment)\",6377276.345,300.8017], TOWGS84[210,814,289,0,0,0,0]], PRIMEM[\"Greenwich\",0], UNIT[\"degree\",0.0174532925199433]]");
/* 167 */     DATUMS.put(Integer.valueOf(130), "GEOGCS[\"Indian 1954\", DATUM[\"Indian_1954\", SPHEROID[\"Everest 1830 (1937 Adjustment)\",6377276.345,300.8017], TOWGS84[217,823,299,0,0,0,0]], PRIMEM[\"Greenwich\",0], UNIT[\"degree\",0.0174532925199433]]");
/* 168 */     DATUMS.put(Integer.valueOf(131), "GEOGCS[\"Indian 1960\", DATUM[\"Indian_1960\", SPHEROID[\"Everest 1830 (1937 Adjustment)\",6377276.345,300.8017], TOWGS84[198,881,317,0,0,0,0]], PRIMEM[\"Greenwich\",0], UNIT[\"degree\",0.0174532925199433]]");
/* 169 */     DATUMS.put(Integer.valueOf(133), "GEOGCS[\"ID74\", DATUM[\"Indonesian_Datum_1974\", SPHEROID[\"Indonesian National Spheroid\",6378160,298.247], TOWGS84[-24,-15,5,0,0,0,0]], PRIMEM[\"Greenwich\",0], UNIT[\"degree\",0.0174532925199433]]");
/* 170 */     DATUMS.put(Integer.valueOf(136), "GEOGCS[\"Leigon\", DATUM[\"Leigon\", SPHEROID[\"Clarke 1880 (RGS)\",6378249.145,293.465], TOWGS84[-130,29,364,0,0,0,0]], PRIMEM[\"Greenwich\",0], UNIT[\"degree\",0.0174532925199433]]");
/* 171 */     DATUMS.put(Integer.valueOf(138), "GEOGCS[\"M'poraloko\", DATUM[\"M_poraloko\", SPHEROID[\"Clarke 1880 (IGN)\",6378249.2,293.4660212936265], TOWGS84[-74,-130,42,0,0,0,0]], PRIMEM[\"Greenwich\",0], UNIT[\"degree\",0.0174532925199433]]");
/* 172 */     DATUMS.put(Integer.valueOf(141), "GEOGCS[\"Point 58\", DATUM[\"Point_58\", SPHEROID[\"Clarke 1880 (RGS)\",6378249.145,293.465], TOWGS84[-106,-129,165,0,0,0,0]], PRIMEM[\"Greenwich\",0], UNIT[\"degree\",0.0174532925199433]]");
/* 173 */     DATUMS.put(Integer.valueOf(142), "GEOGCS[\"Pointe Noire\", DATUM[\"Congo_1960_Pointe_Noire\", SPHEROID[\"Clarke 1880 (IGN)\",6378249.2,293.4660212936265], TOWGS84[-148,51,-291,0,0,0,0]], PRIMEM[\"Greenwich\",0], UNIT[\"degree\",0.0174532925199433]]");
/* 174 */     DATUMS.put(Integer.valueOf(143), "GEOGCS[\"Porto Santo\", DATUM[\"Porto_Santo_1936\", SPHEROID[\"International 1924\",6378388,297], TOWGS84[-499,-249,314,0,0,0,0]], PRIMEM[\"Greenwich\",0], UNIT[\"degree\",0.0174532925199433]]");
/* 175 */     DATUMS.put(Integer.valueOf(144), "GEOGCS[\"Selvagem Grande\", DATUM[\"Selvagem_Grande\", SPHEROID[\"International 1924\",6378388,297], TOWGS84[-289,-124,60,0,0,0,0]], PRIMEM[\"Greenwich\",0], UNIT[\"degree\",0.0174532925199433]]");
/* 176 */     DATUMS.put(Integer.valueOf(146), "GEOGCS[\"S-JTSK\", DATUM[\"System_Jednotne_Trigonometricke_Site_Katastralni\", SPHEROID[\"Bessel 1841\",6377397.155,299.1528128], TOWGS84[589,76,480,0,0,0,0]], PRIMEM[\"Greenwich\",0], UNIT[\"degree\",0.0174532925199433]]");
/* 177 */     DATUMS.put(Integer.valueOf(147), "GEOGCS[\"Tananarive\", DATUM[\"Tananarive_1925\", SPHEROID[\"International 1924\",6378388,297], TOWGS84[-189,-242,-91,0,0,0,0]], PRIMEM[\"Greenwich\",0], UNIT[\"degree\",0.0174532925199433]]");
/* 178 */     DATUMS.put(Integer.valueOf(150), "GEOGCS[\"Hartebeesthoek94\", DATUM[\"Hartebeesthoek94\", SPHEROID[\"WGS 84\",6378137,298.257223563], TOWGS84[0,0,0,0,0,0,0]], PRIMEM[\"Greenwich\",0], UNIT[\"degree\",0.0174532925199433]]");
/* 179 */     DATUMS.put(Integer.valueOf(151), "GEOGCS[\"ATS77\", DATUM[\"Average_Terrestrial_System_1977\", SPHEROID[\"Average Terrestrial System 1977\",6378135,298.257]], PRIMEM[\"Greenwich\",0], UNIT[\"degree\",0.0174532925199433]]");
/* 180 */     DATUMS.put(Integer.valueOf(152), "GEOGCS[\"JGD2000\", DATUM[\"Japanese_Geodetic_Datum_2000\", SPHEROID[\"GRS 1980\",6378137,298.257222101], TOWGS84[0,0,0,0,0,0,0]], PRIMEM[\"Greenwich\",0], UNIT[\"degree\",0.0174532925199433]]");
/* 181 */     DATUMS.put(Integer.valueOf(1001), "GEOGCS[\"Pulkovo 1942\", DATUM[\"Pulkovo_1942\", SPHEROID[\"Krassowsky 1940\",6378245,298.3], TOWGS84[23.92,-141.27,-80.9,-0,0.35,0.82,-0.12]], PRIMEM[\"Greenwich\",0], UNIT[\"degree\",0.0174532925199433]]");
/* 182 */     DATUMS.put(Integer.valueOf(1002), "GEOGCS[\"NTF\", DATUM[\"Nouvelle_Triangulation_Francaise\", SPHEROID[\"Clarke 1880 (IGN)\",6378249.2,293.4660212936265], TOWGS84[-168,-60,320,0,0,0,0]], PRIMEM[\"Greenwich\",0], UNIT[\"degree\",0.0174532925199433]]");
/* 183 */     DATUMS.put(Integer.valueOf(1004), "GEOGCS[\"HD72\", DATUM[\"Hungarian_Datum_1972\", SPHEROID[\"GRS 1967\",6378160,298.247167427], TOWGS84[52.17,-71.82,-14.9,0,0,0,0]], PRIMEM[\"Greenwich\",0], UNIT[\"degree\",0.0174532925199433]]");
/* 184 */     DATUMS.put(Integer.valueOf(1017), "GEOGCS[\"Xian 1980\", DATUM[\"Xian_1980\", SPHEROID[\"IAG 1975\",6378140,298.257]], PRIMEM[\"Greenwich\",0], UNIT[\"degree\",0.0174532925199433]]");
/* 185 */     DATUMS.put(Integer.valueOf(1020), "GEOGCS[\"S-JTSK (Ferro)\", DATUM[\"System_Jednotne_Trigonometricke_Site_Katastralni_Ferro\", SPHEROID[\"Bessel 1841\",6377397.155,299.1528128], TOWGS84[589,76,480,0,0,0,0]], PRIMEM[\"Ferro\",-17.66666666666667], UNIT[\"degree\",0.0174532925199433]]");
/* 187 */     UNITS.put("m", "UNIT[\"Meter\",1]");
/* 188 */     UNITS.put("ft", "UNIT[\"foot\",0.3048]");
/* 189 */     UNITS.put("li", "UNIT[\"link\",0.201168]");
/* 190 */     UNITS.put("survey ft", "UNIT[\"US survey foot\",0.3048006096012192]");
/*     */   }
/*     */   
/* 196 */   private List<MappedPosition> controlPoints = new ArrayList<MappedPosition>();
/*     */   
/*     */   private CoordinateReferenceSystem coordinateReferenceSystem;
/*     */   
/*     */   public MapInfoFileReader(File tabfile) throws IOException {
/* 211 */     if (tabfile == null)
/* 212 */       throw new IllegalArgumentException(Errors.format(143, "tabfile")); 
/* 214 */     if (!tabfile.isFile() || !tabfile.canRead())
/* 215 */       throw new IllegalArgumentException(Errors.format(50, tabfile)); 
/* 218 */     parseTabFile(new BufferedReader(new FileReader(tabfile)));
/*     */   }
/*     */   
/*     */   public MapInfoFileReader(URL tabfile) throws IOException {
/* 229 */     if (tabfile == null)
/* 230 */       throw new IllegalArgumentException(Errors.format(143, "inFile")); 
/* 232 */     parseTabFile(new BufferedReader(new InputStreamReader(tabfile.openStream())));
/*     */   }
/*     */   
/*     */   private void parseTabFile(BufferedReader bufferedreader) throws IOException, DataSourceException {
/* 245 */     Pattern patternPoint = Pattern.compile(" *\\(([0-9\\.]*),([0-9\\.]*)\\) *\\(([0-9\\.]*),([0-9\\.]*)\\).*");
/* 246 */     Pattern patternCoordSys = Pattern.compile("CoordSys *Earth *Projection *([0-9]*) *, *([0-9]*) *,?(.*)");
/* 247 */     Pattern patternUnit = Pattern.compile(" *\"([^\"]*)\" *,?(.*)");
/*     */     try {
/*     */       String str;
/* 250 */       while ((str = bufferedreader.readLine()) != null) {
/* 251 */         Matcher matcherPoint = patternPoint.matcher(str);
/* 252 */         if (matcherPoint.find()) {
/* 253 */           double d1 = Double.parseDouble(matcherPoint.group(1));
/* 254 */           double d2 = Double.parseDouble(matcherPoint.group(2));
/* 255 */           double d3 = Double.parseDouble(matcherPoint.group(3));
/* 256 */           double d4 = Double.parseDouble(matcherPoint.group(4));
/* 258 */           DirectPosition2D directPosition2D1 = new DirectPosition2D(null, d1, d2);
/* 259 */           DirectPosition2D directPosition2D2 = new DirectPosition2D(null, d3, d4);
/* 261 */           this.controlPoints.add(new MappedPosition((DirectPosition)directPosition2D2, (DirectPosition)directPosition2D1));
/*     */           continue;
/*     */         } 
/* 263 */         Matcher matcherCoordSys = patternCoordSys.matcher(str);
/* 264 */         if (matcherCoordSys.find())
/*     */           try {
/*     */             String[] PARAMETERS;
/* 266 */             int projectionType = Integer.parseInt(matcherCoordSys.group(1));
/* 267 */             int datum = Integer.parseInt(matcherCoordSys.group(2));
/* 268 */             String rest = matcherCoordSys.group(3);
/* 269 */             String unit = "m";
/* 271 */             Matcher matcherUnit = patternUnit.matcher(rest);
/* 272 */             if (matcherUnit.find()) {
/* 273 */               unit = matcherUnit.group(1);
/* 274 */               rest = matcherUnit.group(2);
/*     */             } 
/* 277 */             String s = "PROJCS[\"Unnamed\", " + (String)DATUMS.get(Integer.valueOf(datum)) + ",";
/* 279 */             String proj = PROJECTIONS.get(Integer.valueOf(projectionType));
/* 280 */             if (proj != null)
/* 281 */               s = s + " PROJECTION[\"" + proj + "\"], "; 
/* 284 */             String[] parts = rest.split(",");
/* 287 */             if (parts.length > 5) {
/* 288 */               PARAMETERS = PARAMETERS1;
/*     */             } else {
/* 290 */               PARAMETERS = PARAMETERS2;
/*     */             } 
/* 293 */             boolean flag = false;
/* 294 */             for (int i = 0; i < parts.length && i < PARAMETERS.length; i++) {
/* 295 */               String n = parts[i].trim();
/* 296 */               int space = n.indexOf(" ");
/* 297 */               if (space > 0)
/* 298 */                 n = n.substring(0, space); 
/* 300 */               double d = Double.parseDouble(n);
/* 302 */               if (i == 3 && flag) {
/* 303 */                 s = s + " PARAMETER[\"scale_factor\"," + d + "],";
/* 304 */               } else if (i == 2 && d == 0.0D) {
/* 305 */                 flag = true;
/*     */               } else {
/* 308 */                 s = s + " PARAMETER[\"" + PARAMETERS[i] + "\"," + d + "],";
/*     */               } 
/*     */             } 
/* 312 */             s = s + " " + (String)UNITS.get(unit);
/* 314 */             s = s + "]";
/* 316 */             if (LOGGER.isLoggable(Level.FINE))
/* 317 */               LOGGER.log(Level.FINE, "Mapinfo converted CRS: " + s); 
/* 320 */             this.coordinateReferenceSystem = CRS.parseWKT(s);
/* 322 */           } catch (Exception e) {
/* 323 */             LOGGER.log(Level.WARNING, "Failed to parse and encode mapinfo crs: " + e.getLocalizedMessage(), e);
/*     */           }  
/*     */       } 
/* 329 */     } catch (Exception e) {
/* 330 */       throw new DataSourceException(e);
/*     */     } finally {
/*     */       try {
/* 333 */         bufferedreader.close();
/* 334 */       } catch (Throwable t) {
/* 335 */         if (LOGGER.isLoggable(Level.FINE))
/* 336 */           LOGGER.log(Level.FINE, t.getLocalizedMessage(), t); 
/*     */       } 
/*     */     } 
/* 342 */     if (this.controlPoints.size() < 3)
/* 343 */       throw new DataSourceException("Didn't find a minimum of three control points in the .tab file."); 
/*     */   }
/*     */   
/*     */   public synchronized List<MappedPosition> getControlPoints() {
/* 353 */     return this.controlPoints;
/*     */   }
/*     */   
/*     */   public synchronized MathTransform getTransform() {
/* 362 */     AffineTransformBuilder builder = new AffineTransformBuilder(this.controlPoints);
/*     */     try {
/* 364 */       return builder.getMathTransform();
/* 365 */     } catch (FactoryException e) {
/* 366 */       LOGGER.log(Level.WARNING, e.getLocalizedMessage(), (Throwable)e);
/* 367 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public synchronized CoordinateReferenceSystem getCRS() {
/* 377 */     return this.coordinateReferenceSystem;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\MapInfoFileReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */