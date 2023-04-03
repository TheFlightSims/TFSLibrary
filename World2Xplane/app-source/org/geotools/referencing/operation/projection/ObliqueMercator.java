/*     */ package org.geotools.referencing.operation.projection;
/*     */ 
/*     */ import java.awt.geom.Point2D;
/*     */ import java.util.Collection;
/*     */ import javax.measure.unit.NonSI;
/*     */ import org.geotools.metadata.iso.citation.Citations;
/*     */ import org.geotools.referencing.NamedIdentifier;
/*     */ import org.geotools.resources.i18n.Vocabulary;
/*     */ import org.opengis.parameter.GeneralParameterDescriptor;
/*     */ import org.opengis.parameter.ParameterDescriptor;
/*     */ import org.opengis.parameter.ParameterDescriptorGroup;
/*     */ import org.opengis.parameter.ParameterNotFoundException;
/*     */ import org.opengis.parameter.ParameterValueGroup;
/*     */ import org.opengis.referencing.ReferenceIdentifier;
/*     */ import org.opengis.referencing.operation.CylindricalProjection;
/*     */ import org.opengis.referencing.operation.MathTransform;
/*     */ import org.opengis.util.InternationalString;
/*     */ 
/*     */ public class ObliqueMercator extends MapProjection {
/*     */   private static final long serialVersionUID = 5382294977124711214L;
/*     */   
/*     */   private static final double EPSILON = 1.0E-6D;
/*     */   
/*     */   private static final double EPSILON_LATITUDE = 1.0E-10D;
/*     */   
/*     */   protected final double latitudeOfCentre;
/*     */   
/*     */   protected final double longitudeOfCentre;
/*     */   
/*     */   protected final double azimuth;
/*     */   
/*     */   protected final double rectifiedGridAngle;
/*     */   
/*     */   private final double latitudeOf1stPoint;
/*     */   
/*     */   private final double longitudeOf1stPoint;
/*     */   
/*     */   private final double latitudeOf2ndPoint;
/*     */   
/*     */   private final double longitudeOf2ndPoint;
/*     */   
/*     */   private final double B;
/*     */   
/*     */   private final double A;
/*     */   
/*     */   private final double E;
/*     */   
/*     */   private final double ArB;
/*     */   
/*     */   private final double AB;
/*     */   
/*     */   private final double BrA;
/*     */   
/*     */   private final double v_pole_n;
/*     */   
/*     */   private final double v_pole_s;
/*     */   
/*     */   private final double singamma0;
/*     */   
/*     */   private final double cosgamma0;
/*     */   
/*     */   private final double sinrot;
/*     */   
/*     */   private final double cosrot;
/*     */   
/*     */   private final double u_c;
/*     */   
/*     */   final boolean twoPoint;
/*     */   
/*     */   protected ObliqueMercator(ParameterValueGroup parameters) throws ParameterNotFoundException {
/* 304 */     this(parameters, Provider.PARAMETERS.descriptors(), false, false);
/*     */   }
/*     */   
/*     */   ObliqueMercator(ParameterValueGroup parameters, Collection<GeneralParameterDescriptor> expected, boolean twoPoint, boolean hotine) throws ParameterNotFoundException {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: aload_1
/*     */     //   2: aload_2
/*     */     //   3: invokespecial <init> : (Lorg/opengis/parameter/ParameterValueGroup;Ljava/util/Collection;)V
/*     */     //   6: aload_0
/*     */     //   7: iload_3
/*     */     //   8: putfield twoPoint : Z
/*     */     //   11: aload_0
/*     */     //   12: ldc2_w NaN
/*     */     //   15: putfield centralMeridian : D
/*     */     //   18: aload_0
/*     */     //   19: ldc2_w NaN
/*     */     //   22: putfield latitudeOfOrigin : D
/*     */     //   25: aload_0
/*     */     //   26: aload_0
/*     */     //   27: aload_2
/*     */     //   28: getstatic org/geotools/referencing/operation/projection/ObliqueMercator$Provider.LATITUDE_OF_CENTRE : Lorg/opengis/parameter/ParameterDescriptor;
/*     */     //   31: aload_1
/*     */     //   32: invokevirtual doubleValue : (Ljava/util/Collection;Lorg/opengis/parameter/ParameterDescriptor;Lorg/opengis/parameter/ParameterValueGroup;)D
/*     */     //   35: putfield latitudeOfCentre : D
/*     */     //   38: getstatic org/geotools/referencing/operation/projection/ObliqueMercator$Provider.LATITUDE_OF_CENTRE : Lorg/opengis/parameter/ParameterDescriptor;
/*     */     //   41: aload_0
/*     */     //   42: getfield latitudeOfCentre : D
/*     */     //   45: iconst_0
/*     */     //   46: invokestatic ensureLatitudeInRange : (Lorg/opengis/parameter/ParameterDescriptor;DZ)V
/*     */     //   49: dconst_1
/*     */     //   50: aload_0
/*     */     //   51: getfield excentricitySquared : D
/*     */     //   54: dsub
/*     */     //   55: invokestatic sqrt : (D)D
/*     */     //   58: dstore #5
/*     */     //   60: aload_0
/*     */     //   61: getfield latitudeOfCentre : D
/*     */     //   64: invokestatic sin : (D)D
/*     */     //   67: dstore #7
/*     */     //   69: aload_0
/*     */     //   70: getfield latitudeOfCentre : D
/*     */     //   73: invokestatic cos : (D)D
/*     */     //   76: dstore #9
/*     */     //   78: dload #9
/*     */     //   80: dload #9
/*     */     //   82: dmul
/*     */     //   83: dstore #11
/*     */     //   85: aload_0
/*     */     //   86: dconst_1
/*     */     //   87: aload_0
/*     */     //   88: getfield excentricitySquared : D
/*     */     //   91: dload #11
/*     */     //   93: dload #11
/*     */     //   95: dmul
/*     */     //   96: dmul
/*     */     //   97: dconst_1
/*     */     //   98: aload_0
/*     */     //   99: getfield excentricitySquared : D
/*     */     //   102: dsub
/*     */     //   103: ddiv
/*     */     //   104: dadd
/*     */     //   105: invokestatic sqrt : (D)D
/*     */     //   108: putfield B : D
/*     */     //   111: dconst_1
/*     */     //   112: aload_0
/*     */     //   113: getfield excentricitySquared : D
/*     */     //   116: dload #7
/*     */     //   118: dmul
/*     */     //   119: dload #7
/*     */     //   121: dmul
/*     */     //   122: dsub
/*     */     //   123: dstore #13
/*     */     //   125: aload_0
/*     */     //   126: aload_0
/*     */     //   127: getfield B : D
/*     */     //   130: dload #5
/*     */     //   132: dmul
/*     */     //   133: dload #13
/*     */     //   135: ddiv
/*     */     //   136: putfield A : D
/*     */     //   139: aload_0
/*     */     //   140: getfield B : D
/*     */     //   143: dload #5
/*     */     //   145: dmul
/*     */     //   146: dload #9
/*     */     //   148: dload #13
/*     */     //   150: invokestatic sqrt : (D)D
/*     */     //   153: dmul
/*     */     //   154: ddiv
/*     */     //   155: dstore #15
/*     */     //   157: dload #15
/*     */     //   159: dload #15
/*     */     //   161: dmul
/*     */     //   162: dconst_1
/*     */     //   163: dsub
/*     */     //   164: dstore #17
/*     */     //   166: dload #17
/*     */     //   168: dconst_0
/*     */     //   169: dcmpg
/*     */     //   170: ifge -> 179
/*     */     //   173: dconst_0
/*     */     //   174: dstore #17
/*     */     //   176: goto -> 200
/*     */     //   179: dload #17
/*     */     //   181: invokestatic sqrt : (D)D
/*     */     //   184: dstore #17
/*     */     //   186: aload_0
/*     */     //   187: getfield latitudeOfCentre : D
/*     */     //   190: dconst_0
/*     */     //   191: dcmpg
/*     */     //   192: ifge -> 200
/*     */     //   195: dload #17
/*     */     //   197: dneg
/*     */     //   198: dstore #17
/*     */     //   200: dload #17
/*     */     //   202: dload #15
/*     */     //   204: dadd
/*     */     //   205: dup2
/*     */     //   206: dstore #17
/*     */     //   208: dstore #17
/*     */     //   210: aload_0
/*     */     //   211: dload #17
/*     */     //   213: aload_0
/*     */     //   214: aload_0
/*     */     //   215: getfield latitudeOfCentre : D
/*     */     //   218: dload #7
/*     */     //   220: invokevirtual tsfn : (DD)D
/*     */     //   223: aload_0
/*     */     //   224: getfield B : D
/*     */     //   227: invokestatic pow : (DD)D
/*     */     //   230: dmul
/*     */     //   231: putfield E : D
/*     */     //   234: iload_3
/*     */     //   235: ifeq -> 757
/*     */     //   238: aload_0
/*     */     //   239: ldc2_w NaN
/*     */     //   242: putfield longitudeOfCentre : D
/*     */     //   245: aload_0
/*     */     //   246: aload_0
/*     */     //   247: aload_2
/*     */     //   248: getstatic org/geotools/referencing/operation/projection/ObliqueMercator$Provider_TwoPoint.LAT_OF_1ST_POINT : Lorg/opengis/parameter/ParameterDescriptor;
/*     */     //   251: aload_1
/*     */     //   252: invokevirtual doubleValue : (Ljava/util/Collection;Lorg/opengis/parameter/ParameterDescriptor;Lorg/opengis/parameter/ParameterValueGroup;)D
/*     */     //   255: putfield latitudeOf1stPoint : D
/*     */     //   258: getstatic org/geotools/referencing/operation/projection/ObliqueMercator$Provider_TwoPoint.LAT_OF_1ST_POINT : Lorg/opengis/parameter/ParameterDescriptor;
/*     */     //   261: aload_0
/*     */     //   262: getfield latitudeOf1stPoint : D
/*     */     //   265: iconst_0
/*     */     //   266: invokestatic ensureLatitudeInRange : (Lorg/opengis/parameter/ParameterDescriptor;DZ)V
/*     */     //   269: aload_0
/*     */     //   270: aload_0
/*     */     //   271: aload_2
/*     */     //   272: getstatic org/geotools/referencing/operation/projection/ObliqueMercator$Provider_TwoPoint.LONG_OF_1ST_POINT : Lorg/opengis/parameter/ParameterDescriptor;
/*     */     //   275: aload_1
/*     */     //   276: invokevirtual doubleValue : (Ljava/util/Collection;Lorg/opengis/parameter/ParameterDescriptor;Lorg/opengis/parameter/ParameterValueGroup;)D
/*     */     //   279: putfield longitudeOf1stPoint : D
/*     */     //   282: getstatic org/geotools/referencing/operation/projection/ObliqueMercator$Provider_TwoPoint.LONG_OF_1ST_POINT : Lorg/opengis/parameter/ParameterDescriptor;
/*     */     //   285: aload_0
/*     */     //   286: getfield longitudeOf1stPoint : D
/*     */     //   289: iconst_1
/*     */     //   290: invokestatic ensureLongitudeInRange : (Lorg/opengis/parameter/ParameterDescriptor;DZ)V
/*     */     //   293: aload_0
/*     */     //   294: aload_0
/*     */     //   295: aload_2
/*     */     //   296: getstatic org/geotools/referencing/operation/projection/ObliqueMercator$Provider_TwoPoint.LAT_OF_2ND_POINT : Lorg/opengis/parameter/ParameterDescriptor;
/*     */     //   299: aload_1
/*     */     //   300: invokevirtual doubleValue : (Ljava/util/Collection;Lorg/opengis/parameter/ParameterDescriptor;Lorg/opengis/parameter/ParameterValueGroup;)D
/*     */     //   303: putfield latitudeOf2ndPoint : D
/*     */     //   306: getstatic org/geotools/referencing/operation/projection/ObliqueMercator$Provider_TwoPoint.LAT_OF_2ND_POINT : Lorg/opengis/parameter/ParameterDescriptor;
/*     */     //   309: aload_0
/*     */     //   310: getfield latitudeOf2ndPoint : D
/*     */     //   313: iconst_1
/*     */     //   314: invokestatic ensureLatitudeInRange : (Lorg/opengis/parameter/ParameterDescriptor;DZ)V
/*     */     //   317: aload_0
/*     */     //   318: aload_2
/*     */     //   319: getstatic org/geotools/referencing/operation/projection/ObliqueMercator$Provider_TwoPoint.LONG_OF_2ND_POINT : Lorg/opengis/parameter/ParameterDescriptor;
/*     */     //   322: aload_1
/*     */     //   323: invokevirtual doubleValue : (Ljava/util/Collection;Lorg/opengis/parameter/ParameterDescriptor;Lorg/opengis/parameter/ParameterValueGroup;)D
/*     */     //   326: dstore #21
/*     */     //   328: getstatic org/geotools/referencing/operation/projection/ObliqueMercator$Provider_TwoPoint.LONG_OF_2ND_POINT : Lorg/opengis/parameter/ParameterDescriptor;
/*     */     //   331: dload #21
/*     */     //   333: iconst_1
/*     */     //   334: invokestatic ensureLongitudeInRange : (Lorg/opengis/parameter/ParameterDescriptor;DZ)V
/*     */     //   337: aconst_null
/*     */     //   338: astore #23
/*     */     //   340: aconst_null
/*     */     //   341: astore #24
/*     */     //   343: aload_0
/*     */     //   344: getfield latitudeOf1stPoint : D
/*     */     //   347: aload_0
/*     */     //   348: getfield latitudeOf2ndPoint : D
/*     */     //   351: dsub
/*     */     //   352: invokestatic abs : (D)D
/*     */     //   355: ldc2_w 1.0E-10
/*     */     //   358: dcmpg
/*     */     //   359: ifge -> 382
/*     */     //   362: getstatic org/geotools/referencing/operation/projection/ObliqueMercator$Provider_TwoPoint.LAT_OF_1ST_POINT : Lorg/opengis/parameter/ParameterDescriptor;
/*     */     //   365: astore #23
/*     */     //   367: getstatic org/geotools/referencing/operation/projection/ObliqueMercator$Provider_TwoPoint.LAT_OF_2ND_POINT : Lorg/opengis/parameter/ParameterDescriptor;
/*     */     //   370: invokeinterface getName : ()Lorg/opengis/referencing/ReferenceIdentifier;
/*     */     //   375: invokeinterface getCode : ()Ljava/lang/String;
/*     */     //   380: astore #24
/*     */     //   382: aload_0
/*     */     //   383: getfield latitudeOf1stPoint : D
/*     */     //   386: invokestatic abs : (D)D
/*     */     //   389: ldc2_w 1.0E-10
/*     */     //   392: dcmpg
/*     */     //   393: ifge -> 414
/*     */     //   396: getstatic org/geotools/referencing/operation/projection/ObliqueMercator$Provider_TwoPoint.LAT_OF_1ST_POINT : Lorg/opengis/parameter/ParameterDescriptor;
/*     */     //   399: astore #23
/*     */     //   401: new org/geotools/measure/Latitude
/*     */     //   404: dup
/*     */     //   405: aload_0
/*     */     //   406: getfield latitudeOf1stPoint : D
/*     */     //   409: invokespecial <init> : (D)V
/*     */     //   412: astore #24
/*     */     //   414: aload_0
/*     */     //   415: getfield latitudeOf2ndPoint : D
/*     */     //   418: ldc2_w 1.5707963267948966
/*     */     //   421: dadd
/*     */     //   422: invokestatic abs : (D)D
/*     */     //   425: ldc2_w 1.0E-10
/*     */     //   428: dcmpg
/*     */     //   429: ifge -> 450
/*     */     //   432: getstatic org/geotools/referencing/operation/projection/ObliqueMercator$Provider_TwoPoint.LAT_OF_2ND_POINT : Lorg/opengis/parameter/ParameterDescriptor;
/*     */     //   435: astore #23
/*     */     //   437: new org/geotools/measure/Latitude
/*     */     //   440: dup
/*     */     //   441: aload_0
/*     */     //   442: getfield latitudeOf2ndPoint : D
/*     */     //   445: invokespecial <init> : (D)V
/*     */     //   448: astore #24
/*     */     //   450: aload #23
/*     */     //   452: ifnull -> 490
/*     */     //   455: aload #23
/*     */     //   457: invokeinterface getName : ()Lorg/opengis/referencing/ReferenceIdentifier;
/*     */     //   462: invokeinterface getCode : ()Ljava/lang/String;
/*     */     //   467: astore #25
/*     */     //   469: new org/opengis/parameter/InvalidParameterValueException
/*     */     //   472: dup
/*     */     //   473: bipush #58
/*     */     //   475: aload #25
/*     */     //   477: aload #24
/*     */     //   479: invokestatic format : (ILjava/lang/Object;Ljava/lang/Object;)Ljava/lang/String;
/*     */     //   482: aload #25
/*     */     //   484: aload #24
/*     */     //   486: invokespecial <init> : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V
/*     */     //   489: athrow
/*     */     //   490: aload_0
/*     */     //   491: aload_0
/*     */     //   492: getfield latitudeOf1stPoint : D
/*     */     //   495: aload_0
/*     */     //   496: getfield latitudeOf1stPoint : D
/*     */     //   499: invokestatic sin : (D)D
/*     */     //   502: invokevirtual tsfn : (DD)D
/*     */     //   505: aload_0
/*     */     //   506: getfield B : D
/*     */     //   509: invokestatic pow : (DD)D
/*     */     //   512: dstore #25
/*     */     //   514: aload_0
/*     */     //   515: aload_0
/*     */     //   516: getfield latitudeOf2ndPoint : D
/*     */     //   519: aload_0
/*     */     //   520: getfield latitudeOf2ndPoint : D
/*     */     //   523: invokestatic sin : (D)D
/*     */     //   526: invokevirtual tsfn : (DD)D
/*     */     //   529: aload_0
/*     */     //   530: getfield B : D
/*     */     //   533: invokestatic pow : (DD)D
/*     */     //   536: dstore #27
/*     */     //   538: aload_0
/*     */     //   539: getfield E : D
/*     */     //   542: dload #25
/*     */     //   544: ddiv
/*     */     //   545: dstore #29
/*     */     //   547: dload #27
/*     */     //   549: dload #25
/*     */     //   551: dsub
/*     */     //   552: dload #27
/*     */     //   554: dload #25
/*     */     //   556: dadd
/*     */     //   557: ddiv
/*     */     //   558: dstore #31
/*     */     //   560: aload_0
/*     */     //   561: getfield E : D
/*     */     //   564: aload_0
/*     */     //   565: getfield E : D
/*     */     //   568: dmul
/*     */     //   569: dstore #33
/*     */     //   571: dload #33
/*     */     //   573: dload #27
/*     */     //   575: dload #25
/*     */     //   577: dmul
/*     */     //   578: dsub
/*     */     //   579: dload #33
/*     */     //   581: dload #27
/*     */     //   583: dload #25
/*     */     //   585: dmul
/*     */     //   586: dadd
/*     */     //   587: ddiv
/*     */     //   588: dstore #33
/*     */     //   590: aload_0
/*     */     //   591: getfield longitudeOf1stPoint : D
/*     */     //   594: dload #21
/*     */     //   596: dsub
/*     */     //   597: dstore #35
/*     */     //   599: dload #35
/*     */     //   601: ldc2_w -3.141592653589793
/*     */     //   604: dcmpg
/*     */     //   605: ifge -> 619
/*     */     //   608: dload #21
/*     */     //   610: ldc2_w 6.283185307179586
/*     */     //   613: dsub
/*     */     //   614: dstore #21
/*     */     //   616: goto -> 636
/*     */     //   619: dload #35
/*     */     //   621: ldc2_w 3.141592653589793
/*     */     //   624: dcmpl
/*     */     //   625: ifle -> 636
/*     */     //   628: dload #21
/*     */     //   630: ldc2_w 6.283185307179586
/*     */     //   633: dadd
/*     */     //   634: dstore #21
/*     */     //   636: aload_0
/*     */     //   637: dload #21
/*     */     //   639: putfield longitudeOf2ndPoint : D
/*     */     //   642: aload_0
/*     */     //   643: ldc2_w 0.5
/*     */     //   646: aload_0
/*     */     //   647: getfield longitudeOf1stPoint : D
/*     */     //   650: dload #21
/*     */     //   652: dadd
/*     */     //   653: dmul
/*     */     //   654: dload #33
/*     */     //   656: ldc2_w 0.5
/*     */     //   659: aload_0
/*     */     //   660: getfield B : D
/*     */     //   663: dmul
/*     */     //   664: aload_0
/*     */     //   665: getfield longitudeOf1stPoint : D
/*     */     //   668: dload #21
/*     */     //   670: dsub
/*     */     //   671: dmul
/*     */     //   672: invokestatic tan : (D)D
/*     */     //   675: dmul
/*     */     //   676: dload #31
/*     */     //   678: ddiv
/*     */     //   679: invokestatic atan : (D)D
/*     */     //   682: aload_0
/*     */     //   683: getfield B : D
/*     */     //   686: ddiv
/*     */     //   687: dsub
/*     */     //   688: invokestatic rollLongitude : (D)D
/*     */     //   691: putfield centralMeridian : D
/*     */     //   694: ldc2_w 2.0
/*     */     //   697: aload_0
/*     */     //   698: getfield B : D
/*     */     //   701: aload_0
/*     */     //   702: getfield longitudeOf1stPoint : D
/*     */     //   705: aload_0
/*     */     //   706: getfield centralMeridian : D
/*     */     //   709: dsub
/*     */     //   710: invokestatic rollLongitude : (D)D
/*     */     //   713: dmul
/*     */     //   714: invokestatic sin : (D)D
/*     */     //   717: dmul
/*     */     //   718: dload #29
/*     */     //   720: dconst_1
/*     */     //   721: dload #29
/*     */     //   723: ddiv
/*     */     //   724: dsub
/*     */     //   725: ddiv
/*     */     //   726: invokestatic atan : (D)D
/*     */     //   729: dstore #19
/*     */     //   731: aload_0
/*     */     //   732: dload #15
/*     */     //   734: dload #19
/*     */     //   736: invokestatic sin : (D)D
/*     */     //   739: dmul
/*     */     //   740: invokestatic asin : (D)D
/*     */     //   743: putfield azimuth : D
/*     */     //   746: aload_0
/*     */     //   747: aload_0
/*     */     //   748: getfield azimuth : D
/*     */     //   751: putfield rectifiedGridAngle : D
/*     */     //   754: goto -> 1059
/*     */     //   757: aload_0
/*     */     //   758: ldc2_w NaN
/*     */     //   761: putfield latitudeOf1stPoint : D
/*     */     //   764: aload_0
/*     */     //   765: ldc2_w NaN
/*     */     //   768: putfield longitudeOf1stPoint : D
/*     */     //   771: aload_0
/*     */     //   772: ldc2_w NaN
/*     */     //   775: putfield latitudeOf2ndPoint : D
/*     */     //   778: aload_0
/*     */     //   779: ldc2_w NaN
/*     */     //   782: putfield longitudeOf2ndPoint : D
/*     */     //   785: aload_0
/*     */     //   786: aload_0
/*     */     //   787: aload_2
/*     */     //   788: getstatic org/geotools/referencing/operation/projection/ObliqueMercator$Provider.LONGITUDE_OF_CENTRE : Lorg/opengis/parameter/ParameterDescriptor;
/*     */     //   791: aload_1
/*     */     //   792: invokevirtual doubleValue : (Ljava/util/Collection;Lorg/opengis/parameter/ParameterDescriptor;Lorg/opengis/parameter/ParameterValueGroup;)D
/*     */     //   795: putfield longitudeOfCentre : D
/*     */     //   798: getstatic org/geotools/referencing/operation/projection/ObliqueMercator$Provider.LONGITUDE_OF_CENTRE : Lorg/opengis/parameter/ParameterDescriptor;
/*     */     //   801: aload_0
/*     */     //   802: getfield longitudeOfCentre : D
/*     */     //   805: iconst_1
/*     */     //   806: invokestatic ensureLongitudeInRange : (Lorg/opengis/parameter/ParameterDescriptor;DZ)V
/*     */     //   809: aload_0
/*     */     //   810: aload_0
/*     */     //   811: aload_2
/*     */     //   812: getstatic org/geotools/referencing/operation/projection/ObliqueMercator$Provider.AZIMUTH : Lorg/opengis/parameter/ParameterDescriptor;
/*     */     //   815: aload_1
/*     */     //   816: invokevirtual doubleValue : (Ljava/util/Collection;Lorg/opengis/parameter/ParameterDescriptor;Lorg/opengis/parameter/ParameterValueGroup;)D
/*     */     //   819: putfield azimuth : D
/*     */     //   822: aload_0
/*     */     //   823: getfield azimuth : D
/*     */     //   826: ldc2_w -4.71238898038469
/*     */     //   829: dcmpl
/*     */     //   830: ifle -> 844
/*     */     //   833: aload_0
/*     */     //   834: getfield azimuth : D
/*     */     //   837: ldc2_w -1.5707963267948966
/*     */     //   840: dcmpg
/*     */     //   841: iflt -> 866
/*     */     //   844: aload_0
/*     */     //   845: getfield azimuth : D
/*     */     //   848: ldc2_w 1.5707963267948966
/*     */     //   851: dcmpl
/*     */     //   852: ifle -> 918
/*     */     //   855: aload_0
/*     */     //   856: getfield azimuth : D
/*     */     //   859: ldc2_w 4.71238898038469
/*     */     //   862: dcmpg
/*     */     //   863: ifge -> 918
/*     */     //   866: getstatic org/geotools/referencing/operation/projection/ObliqueMercator$Provider.AZIMUTH : Lorg/opengis/parameter/ParameterDescriptor;
/*     */     //   869: invokeinterface getName : ()Lorg/opengis/referencing/ReferenceIdentifier;
/*     */     //   874: invokeinterface getCode : ()Ljava/lang/String;
/*     */     //   879: astore #21
/*     */     //   881: new org/geotools/measure/Angle
/*     */     //   884: dup
/*     */     //   885: aload_0
/*     */     //   886: getfield azimuth : D
/*     */     //   889: invokestatic toDegrees : (D)D
/*     */     //   892: invokespecial <init> : (D)V
/*     */     //   895: astore #22
/*     */     //   897: new org/opengis/parameter/InvalidParameterValueException
/*     */     //   900: dup
/*     */     //   901: bipush #58
/*     */     //   903: aload #21
/*     */     //   905: aload #22
/*     */     //   907: invokestatic format : (ILjava/lang/Object;Ljava/lang/Object;)Ljava/lang/String;
/*     */     //   910: aload #21
/*     */     //   912: aload #22
/*     */     //   914: invokespecial <init> : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V
/*     */     //   917: athrow
/*     */     //   918: aload_0
/*     */     //   919: aload_2
/*     */     //   920: getstatic org/geotools/referencing/operation/projection/ObliqueMercator$Provider.RECTIFIED_GRID_ANGLE : Lorg/opengis/parameter/ParameterDescriptor;
/*     */     //   923: aload_1
/*     */     //   924: invokevirtual doubleValue : (Ljava/util/Collection;Lorg/opengis/parameter/ParameterDescriptor;Lorg/opengis/parameter/ParameterValueGroup;)D
/*     */     //   927: dstore #11
/*     */     //   929: dload #11
/*     */     //   931: invokestatic isNaN : (D)Z
/*     */     //   934: ifeq -> 943
/*     */     //   937: aload_0
/*     */     //   938: getfield azimuth : D
/*     */     //   941: dstore #11
/*     */     //   943: aload_0
/*     */     //   944: dload #11
/*     */     //   946: putfield rectifiedGridAngle : D
/*     */     //   949: aload_0
/*     */     //   950: getfield azimuth : D
/*     */     //   953: invokestatic sin : (D)D
/*     */     //   956: dload #15
/*     */     //   958: ddiv
/*     */     //   959: invokestatic asin : (D)D
/*     */     //   962: dstore #19
/*     */     //   964: ldc2_w 0.5
/*     */     //   967: dload #17
/*     */     //   969: dconst_1
/*     */     //   970: dload #17
/*     */     //   972: ddiv
/*     */     //   973: dsub
/*     */     //   974: dmul
/*     */     //   975: dload #19
/*     */     //   977: invokestatic tan : (D)D
/*     */     //   980: dmul
/*     */     //   981: dstore #11
/*     */     //   983: dload #11
/*     */     //   985: invokestatic abs : (D)D
/*     */     //   988: dconst_1
/*     */     //   989: dcmpl
/*     */     //   990: ifle -> 1040
/*     */     //   993: dload #11
/*     */     //   995: invokestatic abs : (D)D
/*     */     //   998: dconst_1
/*     */     //   999: dsub
/*     */     //   1000: invokestatic abs : (D)D
/*     */     //   1003: ldc2_w 1.0E-6
/*     */     //   1006: dcmpl
/*     */     //   1007: ifle -> 1024
/*     */     //   1010: new java/lang/IllegalArgumentException
/*     */     //   1013: dup
/*     */     //   1014: sipush #168
/*     */     //   1017: invokestatic format : (I)Ljava/lang/String;
/*     */     //   1020: invokespecial <init> : (Ljava/lang/String;)V
/*     */     //   1023: athrow
/*     */     //   1024: dload #11
/*     */     //   1026: dconst_0
/*     */     //   1027: dcmpl
/*     */     //   1028: ifle -> 1035
/*     */     //   1031: dconst_1
/*     */     //   1032: goto -> 1038
/*     */     //   1035: ldc2_w -1.0
/*     */     //   1038: dstore #11
/*     */     //   1040: aload_0
/*     */     //   1041: aload_0
/*     */     //   1042: getfield longitudeOfCentre : D
/*     */     //   1045: dload #11
/*     */     //   1047: invokestatic asin : (D)D
/*     */     //   1050: aload_0
/*     */     //   1051: getfield B : D
/*     */     //   1054: ddiv
/*     */     //   1055: dsub
/*     */     //   1056: putfield centralMeridian : D
/*     */     //   1059: aload_0
/*     */     //   1060: dload #19
/*     */     //   1062: invokestatic sin : (D)D
/*     */     //   1065: putfield singamma0 : D
/*     */     //   1068: aload_0
/*     */     //   1069: dload #19
/*     */     //   1071: invokestatic cos : (D)D
/*     */     //   1074: putfield cosgamma0 : D
/*     */     //   1077: aload_0
/*     */     //   1078: aload_0
/*     */     //   1079: getfield rectifiedGridAngle : D
/*     */     //   1082: invokestatic sin : (D)D
/*     */     //   1085: putfield sinrot : D
/*     */     //   1088: aload_0
/*     */     //   1089: aload_0
/*     */     //   1090: getfield rectifiedGridAngle : D
/*     */     //   1093: invokestatic cos : (D)D
/*     */     //   1096: putfield cosrot : D
/*     */     //   1099: aload_0
/*     */     //   1100: aload_0
/*     */     //   1101: getfield A : D
/*     */     //   1104: aload_0
/*     */     //   1105: getfield B : D
/*     */     //   1108: ddiv
/*     */     //   1109: putfield ArB : D
/*     */     //   1112: aload_0
/*     */     //   1113: aload_0
/*     */     //   1114: getfield A : D
/*     */     //   1117: aload_0
/*     */     //   1118: getfield B : D
/*     */     //   1121: dmul
/*     */     //   1122: putfield AB : D
/*     */     //   1125: aload_0
/*     */     //   1126: aload_0
/*     */     //   1127: getfield B : D
/*     */     //   1130: aload_0
/*     */     //   1131: getfield A : D
/*     */     //   1134: ddiv
/*     */     //   1135: putfield BrA : D
/*     */     //   1138: aload_0
/*     */     //   1139: aload_0
/*     */     //   1140: getfield ArB : D
/*     */     //   1143: ldc2_w 0.5
/*     */     //   1146: ldc2_w 1.5707963267948966
/*     */     //   1149: dload #19
/*     */     //   1151: dsub
/*     */     //   1152: dmul
/*     */     //   1153: invokestatic tan : (D)D
/*     */     //   1156: invokestatic log : (D)D
/*     */     //   1159: dmul
/*     */     //   1160: putfield v_pole_n : D
/*     */     //   1163: aload_0
/*     */     //   1164: aload_0
/*     */     //   1165: getfield ArB : D
/*     */     //   1168: ldc2_w 0.5
/*     */     //   1171: ldc2_w 1.5707963267948966
/*     */     //   1174: dload #19
/*     */     //   1176: dadd
/*     */     //   1177: dmul
/*     */     //   1178: invokestatic tan : (D)D
/*     */     //   1181: invokestatic log : (D)D
/*     */     //   1184: dmul
/*     */     //   1185: putfield v_pole_s : D
/*     */     //   1188: iload #4
/*     */     //   1190: ifeq -> 1201
/*     */     //   1193: aload_0
/*     */     //   1194: dconst_0
/*     */     //   1195: putfield u_c : D
/*     */     //   1198: goto -> 1293
/*     */     //   1201: aload_0
/*     */     //   1202: getfield azimuth : D
/*     */     //   1205: invokestatic abs : (D)D
/*     */     //   1208: ldc2_w 1.5707963267948966
/*     */     //   1211: dsub
/*     */     //   1212: invokestatic abs : (D)D
/*     */     //   1215: ldc2_w 1.0E-10
/*     */     //   1218: dcmpg
/*     */     //   1219: ifge -> 1243
/*     */     //   1222: aload_0
/*     */     //   1223: aload_0
/*     */     //   1224: getfield A : D
/*     */     //   1227: aload_0
/*     */     //   1228: getfield longitudeOfCentre : D
/*     */     //   1231: aload_0
/*     */     //   1232: getfield centralMeridian : D
/*     */     //   1235: dsub
/*     */     //   1236: dmul
/*     */     //   1237: putfield u_c : D
/*     */     //   1240: goto -> 1293
/*     */     //   1243: aload_0
/*     */     //   1244: getfield ArB : D
/*     */     //   1247: dload #15
/*     */     //   1249: dload #15
/*     */     //   1251: dmul
/*     */     //   1252: dconst_1
/*     */     //   1253: dsub
/*     */     //   1254: invokestatic sqrt : (D)D
/*     */     //   1257: aload_0
/*     */     //   1258: getfield azimuth : D
/*     */     //   1261: invokestatic cos : (D)D
/*     */     //   1264: invokestatic atan2 : (DD)D
/*     */     //   1267: dmul
/*     */     //   1268: invokestatic abs : (D)D
/*     */     //   1271: dstore #21
/*     */     //   1273: aload_0
/*     */     //   1274: getfield latitudeOfCentre : D
/*     */     //   1277: dconst_0
/*     */     //   1278: dcmpg
/*     */     //   1279: ifge -> 1287
/*     */     //   1282: dload #21
/*     */     //   1284: dneg
/*     */     //   1285: dstore #21
/*     */     //   1287: aload_0
/*     */     //   1288: dload #21
/*     */     //   1290: putfield u_c : D
/*     */     //   1293: return
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #324	-> 0
/*     */     //   #325	-> 6
/*     */     //   #331	-> 11
/*     */     //   #332	-> 18
/*     */     //   #333	-> 25
/*     */     //   #338	-> 38
/*     */     //   #345	-> 49
/*     */     //   #346	-> 60
/*     */     //   #347	-> 69
/*     */     //   #348	-> 78
/*     */     //   #349	-> 85
/*     */     //   #350	-> 111
/*     */     //   #351	-> 125
/*     */     //   #352	-> 139
/*     */     //   #353	-> 157
/*     */     //   #354	-> 166
/*     */     //   #355	-> 173
/*     */     //   #357	-> 179
/*     */     //   #358	-> 186
/*     */     //   #359	-> 195
/*     */     //   #362	-> 200
/*     */     //   #363	-> 210
/*     */     //   #371	-> 234
/*     */     //   #372	-> 238
/*     */     //   #373	-> 245
/*     */     //   #375	-> 258
/*     */     //   #376	-> 269
/*     */     //   #377	-> 282
/*     */     //   #378	-> 293
/*     */     //   #379	-> 306
/*     */     //   #381	-> 317
/*     */     //   #382	-> 328
/*     */     //   #387	-> 337
/*     */     //   #388	-> 340
/*     */     //   #389	-> 343
/*     */     //   #390	-> 362
/*     */     //   #391	-> 367
/*     */     //   #394	-> 382
/*     */     //   #395	-> 396
/*     */     //   #396	-> 401
/*     */     //   #399	-> 414
/*     */     //   #400	-> 432
/*     */     //   #401	-> 437
/*     */     //   #404	-> 450
/*     */     //   #405	-> 455
/*     */     //   #406	-> 469
/*     */     //   #412	-> 490
/*     */     //   #413	-> 514
/*     */     //   #414	-> 538
/*     */     //   #415	-> 547
/*     */     //   #416	-> 560
/*     */     //   #417	-> 571
/*     */     //   #418	-> 590
/*     */     //   #419	-> 599
/*     */     //   #420	-> 608
/*     */     //   #421	-> 619
/*     */     //   #422	-> 628
/*     */     //   #424	-> 636
/*     */     //   #425	-> 642
/*     */     //   #427	-> 694
/*     */     //   #429	-> 731
/*     */     //   #430	-> 746
/*     */     //   #431	-> 754
/*     */     //   #437	-> 757
/*     */     //   #438	-> 764
/*     */     //   #439	-> 771
/*     */     //   #440	-> 778
/*     */     //   #441	-> 785
/*     */     //   #442	-> 798
/*     */     //   #443	-> 809
/*     */     //   #445	-> 822
/*     */     //   #448	-> 866
/*     */     //   #449	-> 881
/*     */     //   #450	-> 897
/*     */     //   #453	-> 918
/*     */     //   #454	-> 929
/*     */     //   #455	-> 937
/*     */     //   #457	-> 943
/*     */     //   #458	-> 949
/*     */     //   #460	-> 964
/*     */     //   #461	-> 983
/*     */     //   #462	-> 993
/*     */     //   #463	-> 1010
/*     */     //   #465	-> 1024
/*     */     //   #467	-> 1040
/*     */     //   #472	-> 1059
/*     */     //   #473	-> 1068
/*     */     //   #474	-> 1077
/*     */     //   #475	-> 1088
/*     */     //   #476	-> 1099
/*     */     //   #477	-> 1112
/*     */     //   #478	-> 1125
/*     */     //   #479	-> 1138
/*     */     //   #480	-> 1163
/*     */     //   #481	-> 1188
/*     */     //   #482	-> 1193
/*     */     //   #484	-> 1201
/*     */     //   #486	-> 1222
/*     */     //   #488	-> 1243
/*     */     //   #489	-> 1273
/*     */     //   #490	-> 1282
/*     */     //   #492	-> 1287
/*     */     //   #495	-> 1293
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   469	21	25	name	Ljava/lang/String;
/*     */     //   328	426	21	longitudeOf2ndPoint	D
/*     */     //   340	414	23	desc	Lorg/opengis/parameter/ParameterDescriptor;
/*     */     //   343	411	24	value	Ljava/lang/Object;
/*     */     //   514	240	25	H	D
/*     */     //   538	216	27	L	D
/*     */     //   547	207	29	Fp	D
/*     */     //   560	194	31	P	D
/*     */     //   571	183	33	J	D
/*     */     //   599	155	35	diff	D
/*     */     //   731	26	19	gamma0	D
/*     */     //   881	37	21	name	Ljava/lang/String;
/*     */     //   897	21	22	value	Lorg/geotools/measure/Angle;
/*     */     //   1273	20	21	u_c	D
/*     */     //   0	1294	0	this	Lorg/geotools/referencing/operation/projection/ObliqueMercator;
/*     */     //   0	1294	1	parameters	Lorg/opengis/parameter/ParameterValueGroup;
/*     */     //   0	1294	2	expected	Ljava/util/Collection;
/*     */     //   0	1294	3	twoPoint	Z
/*     */     //   0	1294	4	hotine	Z
/*     */     //   60	1234	5	com	D
/*     */     //   69	1225	7	sinphi0	D
/*     */     //   78	1216	9	cosphi0	D
/*     */     //   85	1209	11	temp	D
/*     */     //   125	1169	13	con	D
/*     */     //   157	1137	15	D	D
/*     */     //   166	1128	17	F	D
/*     */     //   964	330	19	gamma0	D
/*     */     // Local variable type table:
/*     */     //   start	length	slot	name	signature
/*     */     //   0	1294	2	expected	Ljava/util/Collection<Lorg/opengis/parameter/GeneralParameterDescriptor;>;
/*     */   }
/*     */   
/*     */   public ParameterDescriptorGroup getParameterDescriptors() {
/* 501 */     return this.twoPoint ? Provider_TwoPoint.PARAMETERS : Provider.PARAMETERS;
/*     */   }
/*     */   
/*     */   public ParameterValueGroup getParameterValues() {
/* 509 */     ParameterValueGroup values = super.getParameterValues();
/* 510 */     Collection<GeneralParameterDescriptor> expected = getParameterDescriptors().descriptors();
/* 513 */     set(expected, Provider.LATITUDE_OF_CENTRE, values, this.latitudeOfCentre);
/* 514 */     set(expected, Provider.LONGITUDE_OF_CENTRE, values, this.longitudeOfCentre);
/* 515 */     set(expected, Provider.AZIMUTH, values, this.azimuth);
/* 516 */     set(expected, Provider.RECTIFIED_GRID_ANGLE, values, this.rectifiedGridAngle);
/* 517 */     set(expected, Provider_TwoPoint.LAT_OF_1ST_POINT, values, this.latitudeOf1stPoint);
/* 518 */     set(expected, Provider_TwoPoint.LONG_OF_1ST_POINT, values, this.longitudeOf1stPoint);
/* 519 */     set(expected, Provider_TwoPoint.LAT_OF_2ND_POINT, values, this.latitudeOf2ndPoint);
/* 520 */     set(expected, Provider_TwoPoint.LONG_OF_2ND_POINT, values, this.longitudeOf2ndPoint);
/* 521 */     return values;
/*     */   }
/*     */   
/*     */   protected Point2D transformNormalized(double x, double y, Point2D ptDst) throws ProjectionException {
/*     */     double u, v;
/* 534 */     if (Math.abs(Math.abs(y) - 1.5707963267948966D) > 1.0E-6D) {
/* 535 */       double Q = this.E / Math.pow(tsfn(y, Math.sin(y)), this.B);
/* 536 */       double temp = 1.0D / Q;
/* 537 */       double S = 0.5D * (Q - temp);
/* 538 */       double V = Math.sin(this.B * x);
/* 539 */       double U = (S * this.singamma0 - V * this.cosgamma0) / 0.5D * (Q + temp);
/* 540 */       if (Math.abs(Math.abs(U) - 1.0D) < 1.0E-6D)
/* 541 */         throw new ProjectionException(80, "v"); 
/* 543 */       v = 0.5D * this.ArB * Math.log((1.0D - U) / (1.0D + U));
/* 544 */       temp = Math.cos(this.B * x);
/* 545 */       if (Math.abs(temp) < 1.0E-10D) {
/* 546 */         u = this.AB * x;
/*     */       } else {
/* 548 */         u = this.ArB * Math.atan2(S * this.cosgamma0 + V * this.singamma0, temp);
/*     */       } 
/*     */     } else {
/* 551 */       v = (y > 0.0D) ? this.v_pole_n : this.v_pole_s;
/* 552 */       u = this.ArB * y;
/*     */     } 
/* 554 */     u -= this.u_c;
/* 555 */     x = v * this.cosrot + u * this.sinrot;
/* 556 */     y = u * this.cosrot - v * this.sinrot;
/* 558 */     if (ptDst != null) {
/* 559 */       ptDst.setLocation(x, y);
/* 560 */       return ptDst;
/*     */     } 
/* 562 */     return new Point2D.Double(x, y);
/*     */   }
/*     */   
/*     */   protected Point2D inverseTransformNormalized(double x, double y, Point2D ptDst) throws ProjectionException {
/* 571 */     double v = x * this.cosrot - y * this.sinrot;
/* 572 */     double u = y * this.cosrot + x * this.sinrot + this.u_c;
/* 573 */     double Qp = Math.exp(-this.BrA * v);
/* 574 */     double temp = 1.0D / Qp;
/* 575 */     double Sp = 0.5D * (Qp - temp);
/* 576 */     double Vp = Math.sin(this.BrA * u);
/* 577 */     double Up = (Vp * this.cosgamma0 + Sp * this.singamma0) / 0.5D * (Qp + temp);
/* 578 */     if (Math.abs(Math.abs(Up) - 1.0D) < 1.0E-6D) {
/* 579 */       x = 0.0D;
/* 580 */       y = (Up < 0.0D) ? -1.5707963267948966D : 1.5707963267948966D;
/*     */     } else {
/* 582 */       y = Math.pow(this.E / Math.sqrt((1.0D + Up) / (1.0D - Up)), 1.0D / this.B);
/* 583 */       y = cphi2(y);
/* 584 */       x = -Math.atan2(Sp * this.cosgamma0 - Vp * this.singamma0, Math.cos(this.BrA * u)) / this.B;
/*     */     } 
/* 586 */     if (ptDst != null) {
/* 587 */       ptDst.setLocation(x, y);
/* 588 */       return ptDst;
/*     */     } 
/* 590 */     return new Point2D.Double(x, y);
/*     */   }
/*     */   
/*     */   protected double getToleranceForAssertions(double longitude, double latitude) {
/* 602 */     if (Math.abs(longitude - this.centralMeridian) / 2.0D + Math.abs(latitude - this.latitudeOfCentre) > 10.0D)
/* 604 */       return 1.0D; 
/* 606 */     return super.getToleranceForAssertions(longitude, latitude);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 614 */     long code = Double.doubleToLongBits(this.latitudeOfCentre);
/* 615 */     code = code * 37L + Double.doubleToLongBits(this.longitudeOfCentre);
/* 616 */     code = code * 37L + Double.doubleToLongBits(this.azimuth);
/* 617 */     code = code * 37L + Double.doubleToLongBits(this.rectifiedGridAngle);
/* 618 */     code = code * 37L + Double.doubleToLongBits(this.latitudeOf1stPoint);
/* 619 */     code = code * 37L + Double.doubleToLongBits(this.latitudeOf2ndPoint);
/* 620 */     return ((int)code ^ (int)(code >>> 32L)) + 37 * super.hashCode();
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/* 628 */     if (object == this)
/* 630 */       return true; 
/* 632 */     if (super.equals(object)) {
/* 633 */       ObliqueMercator that = (ObliqueMercator)object;
/* 634 */       return (this.twoPoint == that.twoPoint && equals(this.latitudeOfCentre, that.latitudeOfCentre) && equals(this.longitudeOfCentre, that.longitudeOfCentre) && equals(this.azimuth, that.azimuth) && equals(this.rectifiedGridAngle, that.rectifiedGridAngle) && equals(this.latitudeOf1stPoint, that.latitudeOf1stPoint) && equals(this.longitudeOf1stPoint, that.longitudeOf1stPoint) && equals(this.latitudeOf2ndPoint, that.latitudeOf2ndPoint) && equals(this.longitudeOf2ndPoint, that.longitudeOf2ndPoint) && equals(this.u_c, that.u_c));
/*     */     } 
/* 648 */     return false;
/*     */   }
/*     */   
/*     */   public static class Provider extends MapProjection.AbstractProvider {
/*     */     private static final long serialVersionUID = 201776686002266891L;
/*     */     
/* 682 */     public static final ParameterDescriptor LATITUDE_OF_CENTRE = createDescriptor((ReferenceIdentifier[])new NamedIdentifier[] { new NamedIdentifier(Citations.OGC, "latitude_of_center"), new NamedIdentifier(Citations.EPSG, "Latitude of projection centre"), new NamedIdentifier(Citations.ESRI, "Latitude_Of_Center"), new NamedIdentifier(Citations.GEOTIFF, "CenterLat") }0.0D, -90.0D, 90.0D, NonSI.DEGREE_ANGLE);
/*     */     
/* 695 */     public static final ParameterDescriptor LONGITUDE_OF_CENTRE = createDescriptor((ReferenceIdentifier[])new NamedIdentifier[] { new NamedIdentifier(Citations.OGC, "longitude_of_center"), new NamedIdentifier(Citations.EPSG, "Longitude of projection centre"), new NamedIdentifier(Citations.ESRI, "Longitude_Of_Center"), new NamedIdentifier(Citations.GEOTIFF, "CenterLong") }0.0D, -180.0D, 180.0D, NonSI.DEGREE_ANGLE);
/*     */     
/* 709 */     public static final ParameterDescriptor AZIMUTH = createDescriptor((ReferenceIdentifier[])new NamedIdentifier[] { new NamedIdentifier(Citations.OGC, "azimuth"), new NamedIdentifier(Citations.ESRI, "Azimuth"), new NamedIdentifier(Citations.EPSG, "Azimuth of initial line"), new NamedIdentifier(Citations.GEOTIFF, "AzimuthAngle") }0.0D, -360.0D, 360.0D, NonSI.DEGREE_ANGLE);
/*     */     
/* 723 */     public static final ParameterDescriptor RECTIFIED_GRID_ANGLE = createOptionalDescriptor((ReferenceIdentifier[])new NamedIdentifier[] { new NamedIdentifier(Citations.OGC, "rectified_grid_angle"), new NamedIdentifier(Citations.EPSG, "Angle from Rectified to Skew Grid"), new NamedIdentifier(Citations.ESRI, "XY_Plane_Rotation"), new NamedIdentifier(Citations.GEOTIFF, "RectifiedGridAngle") }-360.0D, 360.0D, NonSI.DEGREE_ANGLE);
/*     */     
/* 735 */     static final InternationalString NAME = Vocabulary.formatInternational(154);
/*     */     
/* 741 */     static final ParameterDescriptorGroup PARAMETERS = createDescriptorGroup((ReferenceIdentifier[])new NamedIdentifier[] { new NamedIdentifier(Citations.OGC, "Oblique_Mercator"), new NamedIdentifier(Citations.EPSG, "Oblique Mercator"), new NamedIdentifier(Citations.EPSG, "Hotine Oblique Mercator (variant B)"), new NamedIdentifier(Citations.EPSG, "9815"), new NamedIdentifier(Citations.GEOTIFF, "CT_ObliqueMercator"), new NamedIdentifier(Citations.ESRI, "Hotine_Oblique_Mercator_Azimuth_Center"), new NamedIdentifier(Citations.ESRI, "Rectified_Skew_Orthomorphic_Center"), new NamedIdentifier(Citations.GEOTOOLS, NAME) }(GeneralParameterDescriptor[])new ParameterDescriptor[] { SEMI_MAJOR, SEMI_MINOR, LONGITUDE_OF_CENTRE, LATITUDE_OF_CENTRE, AZIMUTH, RECTIFIED_GRID_ANGLE, SCALE_FACTOR, FALSE_EASTING, FALSE_NORTHING });
/*     */     
/*     */     public Provider() {
/* 762 */       super(PARAMETERS);
/*     */     }
/*     */     
/*     */     protected Provider(ParameterDescriptorGroup params) {
/* 771 */       super(params);
/*     */     }
/*     */     
/*     */     public Class<CylindricalProjection> getOperationType() {
/* 779 */       return CylindricalProjection.class;
/*     */     }
/*     */     
/*     */     protected MathTransform createMathTransform(ParameterValueGroup parameters) throws ParameterNotFoundException {
/* 792 */       Collection<GeneralParameterDescriptor> descriptors = PARAMETERS.descriptors();
/* 793 */       return (MathTransform)new ObliqueMercator(parameters, descriptors, false, false);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Provider_TwoPoint extends Provider {
/*     */     private static final long serialVersionUID = 7124258885016543889L;
/*     */     
/* 819 */     public static final ParameterDescriptor LAT_OF_1ST_POINT = createDescriptor((ReferenceIdentifier[])new NamedIdentifier[] { new NamedIdentifier(Citations.ESRI, "Latitude_Of_1st_Point") }0.0D, -90.0D, 90.0D, NonSI.DEGREE_ANGLE);
/*     */     
/* 829 */     public static final ParameterDescriptor LONG_OF_1ST_POINT = createDescriptor((ReferenceIdentifier[])new NamedIdentifier[] { new NamedIdentifier(Citations.ESRI, "Longitude_Of_1st_Point") }0.0D, -180.0D, 180.0D, NonSI.DEGREE_ANGLE);
/*     */     
/* 839 */     public static final ParameterDescriptor LAT_OF_2ND_POINT = createDescriptor((ReferenceIdentifier[])new NamedIdentifier[] { new NamedIdentifier(Citations.ESRI, "Latitude_Of_2nd_Point") }0.0D, -90.0D, 90.0D, NonSI.DEGREE_ANGLE);
/*     */     
/* 849 */     public static final ParameterDescriptor LONG_OF_2ND_POINT = createDescriptor((ReferenceIdentifier[])new NamedIdentifier[] { new NamedIdentifier(Citations.ESRI, "Longitude_Of_2nd_Point") }0.0D, -180.0D, 180.0D, NonSI.DEGREE_ANGLE);
/*     */     
/* 858 */     static final ParameterDescriptorGroup PARAMETERS = createDescriptorGroup((ReferenceIdentifier[])new NamedIdentifier[] { new NamedIdentifier(Citations.ESRI, "Hotine_Oblique_Mercator_Two_Point_Center"), new NamedIdentifier(Citations.GEOTOOLS, NAME) }(GeneralParameterDescriptor[])new ParameterDescriptor[] { SEMI_MAJOR, SEMI_MINOR, LAT_OF_1ST_POINT, LONG_OF_1ST_POINT, LAT_OF_2ND_POINT, LONG_OF_2ND_POINT, LATITUDE_OF_CENTRE, SCALE_FACTOR, FALSE_EASTING, FALSE_NORTHING });
/*     */     
/*     */     public Provider_TwoPoint() {
/* 873 */       super(PARAMETERS);
/*     */     }
/*     */     
/*     */     protected Provider_TwoPoint(ParameterDescriptorGroup params) {
/* 882 */       super(params);
/*     */     }
/*     */     
/*     */     protected MathTransform createMathTransform(ParameterValueGroup parameters) throws ParameterNotFoundException {
/* 896 */       Collection<GeneralParameterDescriptor> descriptors = PARAMETERS.descriptors();
/* 897 */       return (MathTransform)new ObliqueMercator(parameters, descriptors, true, false);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\operation\projection\ObliqueMercator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */