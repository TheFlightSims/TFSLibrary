/*    */ package scala.xml.include.sax;
/*    */ 
/*    */ import java.io.InputStream;
/*    */ import scala.runtime.ObjectRef;
/*    */ 
/*    */ public final class EncodingHeuristics$ {
/*    */   public static final EncodingHeuristics$ MODULE$;
/*    */   
/*    */   private EncodingHeuristics$() {
/* 27 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   public String readEncodingFromStream(InputStream in) {
/*    */     // Byte code:
/*    */     //   0: new scala/runtime/ObjectRef
/*    */     //   3: dup
/*    */     //   4: aconst_null
/*    */     //   5: invokespecial <init> : (Ljava/lang/Object;)V
/*    */     //   8: astore #5
/*    */     //   10: aload_1
/*    */     //   11: sipush #1024
/*    */     //   14: invokevirtual mark : (I)V
/*    */     //   17: new scala/Tuple4
/*    */     //   20: dup
/*    */     //   21: aload_1
/*    */     //   22: invokevirtual read : ()I
/*    */     //   25: invokestatic boxToInteger : (I)Ljava/lang/Integer;
/*    */     //   28: aload_1
/*    */     //   29: invokevirtual read : ()I
/*    */     //   32: invokestatic boxToInteger : (I)Ljava/lang/Integer;
/*    */     //   35: aload_1
/*    */     //   36: invokevirtual read : ()I
/*    */     //   39: invokestatic boxToInteger : (I)Ljava/lang/Integer;
/*    */     //   42: aload_1
/*    */     //   43: invokevirtual read : ()I
/*    */     //   46: invokestatic boxToInteger : (I)Ljava/lang/Integer;
/*    */     //   49: invokespecial <init> : (Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)V
/*    */     //   52: astore_3
/*    */     //   53: aload #5
/*    */     //   55: aload_3
/*    */     //   56: ifnull -> 117
/*    */     //   59: iconst_0
/*    */     //   60: aload_3
/*    */     //   61: invokevirtual _1 : ()Ljava/lang/Object;
/*    */     //   64: invokestatic unboxToInt : (Ljava/lang/Object;)I
/*    */     //   67: if_icmpne -> 117
/*    */     //   70: iconst_0
/*    */     //   71: aload_3
/*    */     //   72: invokevirtual _2 : ()Ljava/lang/Object;
/*    */     //   75: invokestatic unboxToInt : (Ljava/lang/Object;)I
/*    */     //   78: if_icmpne -> 117
/*    */     //   81: sipush #254
/*    */     //   84: aload_3
/*    */     //   85: invokevirtual _3 : ()Ljava/lang/Object;
/*    */     //   88: invokestatic unboxToInt : (Ljava/lang/Object;)I
/*    */     //   91: if_icmpne -> 117
/*    */     //   94: sipush #255
/*    */     //   97: aload_3
/*    */     //   98: invokevirtual _4 : ()Ljava/lang/Object;
/*    */     //   101: invokestatic unboxToInt : (Ljava/lang/Object;)I
/*    */     //   104: if_icmpne -> 117
/*    */     //   107: getstatic scala/xml/include/sax/EncodingHeuristics$EncodingNames$.MODULE$ : Lscala/xml/include/sax/EncodingHeuristics$EncodingNames$;
/*    */     //   110: invokevirtual bigUCS4 : ()Ljava/lang/String;
/*    */     //   113: astore_2
/*    */     //   114: goto -> 438
/*    */     //   117: aload_3
/*    */     //   118: ifnull -> 179
/*    */     //   121: sipush #255
/*    */     //   124: aload_3
/*    */     //   125: invokevirtual _1 : ()Ljava/lang/Object;
/*    */     //   128: invokestatic unboxToInt : (Ljava/lang/Object;)I
/*    */     //   131: if_icmpne -> 179
/*    */     //   134: sipush #254
/*    */     //   137: aload_3
/*    */     //   138: invokevirtual _2 : ()Ljava/lang/Object;
/*    */     //   141: invokestatic unboxToInt : (Ljava/lang/Object;)I
/*    */     //   144: if_icmpne -> 179
/*    */     //   147: iconst_0
/*    */     //   148: aload_3
/*    */     //   149: invokevirtual _3 : ()Ljava/lang/Object;
/*    */     //   152: invokestatic unboxToInt : (Ljava/lang/Object;)I
/*    */     //   155: if_icmpne -> 179
/*    */     //   158: iconst_0
/*    */     //   159: aload_3
/*    */     //   160: invokevirtual _4 : ()Ljava/lang/Object;
/*    */     //   163: invokestatic unboxToInt : (Ljava/lang/Object;)I
/*    */     //   166: if_icmpne -> 179
/*    */     //   169: getstatic scala/xml/include/sax/EncodingHeuristics$EncodingNames$.MODULE$ : Lscala/xml/include/sax/EncodingHeuristics$EncodingNames$;
/*    */     //   172: invokevirtual littleUCS4 : ()Ljava/lang/String;
/*    */     //   175: astore_2
/*    */     //   176: goto -> 438
/*    */     //   179: aload_3
/*    */     //   180: ifnull -> 241
/*    */     //   183: iconst_0
/*    */     //   184: aload_3
/*    */     //   185: invokevirtual _1 : ()Ljava/lang/Object;
/*    */     //   188: invokestatic unboxToInt : (Ljava/lang/Object;)I
/*    */     //   191: if_icmpne -> 241
/*    */     //   194: iconst_0
/*    */     //   195: aload_3
/*    */     //   196: invokevirtual _2 : ()Ljava/lang/Object;
/*    */     //   199: invokestatic unboxToInt : (Ljava/lang/Object;)I
/*    */     //   202: if_icmpne -> 241
/*    */     //   205: sipush #255
/*    */     //   208: aload_3
/*    */     //   209: invokevirtual _3 : ()Ljava/lang/Object;
/*    */     //   212: invokestatic unboxToInt : (Ljava/lang/Object;)I
/*    */     //   215: if_icmpne -> 241
/*    */     //   218: sipush #254
/*    */     //   221: aload_3
/*    */     //   222: invokevirtual _4 : ()Ljava/lang/Object;
/*    */     //   225: invokestatic unboxToInt : (Ljava/lang/Object;)I
/*    */     //   228: if_icmpne -> 241
/*    */     //   231: getstatic scala/xml/include/sax/EncodingHeuristics$EncodingNames$.MODULE$ : Lscala/xml/include/sax/EncodingHeuristics$EncodingNames$;
/*    */     //   234: invokevirtual unusualUCS4 : ()Ljava/lang/String;
/*    */     //   237: astore_2
/*    */     //   238: goto -> 438
/*    */     //   241: aload_3
/*    */     //   242: ifnull -> 303
/*    */     //   245: sipush #254
/*    */     //   248: aload_3
/*    */     //   249: invokevirtual _1 : ()Ljava/lang/Object;
/*    */     //   252: invokestatic unboxToInt : (Ljava/lang/Object;)I
/*    */     //   255: if_icmpne -> 303
/*    */     //   258: sipush #255
/*    */     //   261: aload_3
/*    */     //   262: invokevirtual _2 : ()Ljava/lang/Object;
/*    */     //   265: invokestatic unboxToInt : (Ljava/lang/Object;)I
/*    */     //   268: if_icmpne -> 303
/*    */     //   271: iconst_0
/*    */     //   272: aload_3
/*    */     //   273: invokevirtual _3 : ()Ljava/lang/Object;
/*    */     //   276: invokestatic unboxToInt : (Ljava/lang/Object;)I
/*    */     //   279: if_icmpne -> 303
/*    */     //   282: iconst_0
/*    */     //   283: aload_3
/*    */     //   284: invokevirtual _4 : ()Ljava/lang/Object;
/*    */     //   287: invokestatic unboxToInt : (Ljava/lang/Object;)I
/*    */     //   290: if_icmpne -> 303
/*    */     //   293: getstatic scala/xml/include/sax/EncodingHeuristics$EncodingNames$.MODULE$ : Lscala/xml/include/sax/EncodingHeuristics$EncodingNames$;
/*    */     //   296: invokevirtual unusualUCS4 : ()Ljava/lang/String;
/*    */     //   299: astore_2
/*    */     //   300: goto -> 438
/*    */     //   303: aload_3
/*    */     //   304: ifnull -> 343
/*    */     //   307: sipush #254
/*    */     //   310: aload_3
/*    */     //   311: invokevirtual _1 : ()Ljava/lang/Object;
/*    */     //   314: invokestatic unboxToInt : (Ljava/lang/Object;)I
/*    */     //   317: if_icmpne -> 343
/*    */     //   320: sipush #255
/*    */     //   323: aload_3
/*    */     //   324: invokevirtual _2 : ()Ljava/lang/Object;
/*    */     //   327: invokestatic unboxToInt : (Ljava/lang/Object;)I
/*    */     //   330: if_icmpne -> 343
/*    */     //   333: getstatic scala/xml/include/sax/EncodingHeuristics$EncodingNames$.MODULE$ : Lscala/xml/include/sax/EncodingHeuristics$EncodingNames$;
/*    */     //   336: invokevirtual bigUTF16 : ()Ljava/lang/String;
/*    */     //   339: astore_2
/*    */     //   340: goto -> 438
/*    */     //   343: aload_3
/*    */     //   344: ifnull -> 383
/*    */     //   347: sipush #255
/*    */     //   350: aload_3
/*    */     //   351: invokevirtual _1 : ()Ljava/lang/Object;
/*    */     //   354: invokestatic unboxToInt : (Ljava/lang/Object;)I
/*    */     //   357: if_icmpne -> 383
/*    */     //   360: sipush #254
/*    */     //   363: aload_3
/*    */     //   364: invokevirtual _2 : ()Ljava/lang/Object;
/*    */     //   367: invokestatic unboxToInt : (Ljava/lang/Object;)I
/*    */     //   370: if_icmpne -> 383
/*    */     //   373: getstatic scala/xml/include/sax/EncodingHeuristics$EncodingNames$.MODULE$ : Lscala/xml/include/sax/EncodingHeuristics$EncodingNames$;
/*    */     //   376: invokevirtual littleUTF16 : ()Ljava/lang/String;
/*    */     //   379: astore_2
/*    */     //   380: goto -> 438
/*    */     //   383: aload_3
/*    */     //   384: ifnull -> 436
/*    */     //   387: sipush #239
/*    */     //   390: aload_3
/*    */     //   391: invokevirtual _1 : ()Ljava/lang/Object;
/*    */     //   394: invokestatic unboxToInt : (Ljava/lang/Object;)I
/*    */     //   397: if_icmpne -> 436
/*    */     //   400: sipush #187
/*    */     //   403: aload_3
/*    */     //   404: invokevirtual _2 : ()Ljava/lang/Object;
/*    */     //   407: invokestatic unboxToInt : (Ljava/lang/Object;)I
/*    */     //   410: if_icmpne -> 436
/*    */     //   413: sipush #191
/*    */     //   416: aload_3
/*    */     //   417: invokevirtual _3 : ()Ljava/lang/Object;
/*    */     //   420: invokestatic unboxToInt : (Ljava/lang/Object;)I
/*    */     //   423: if_icmpne -> 436
/*    */     //   426: getstatic scala/xml/include/sax/EncodingHeuristics$EncodingNames$.MODULE$ : Lscala/xml/include/sax/EncodingHeuristics$EncodingNames$;
/*    */     //   429: invokevirtual utf8 : ()Ljava/lang/String;
/*    */     //   432: astore_2
/*    */     //   433: goto -> 438
/*    */     //   436: aconst_null
/*    */     //   437: astore_2
/*    */     //   438: aload_2
/*    */     //   439: putfield elem : Ljava/lang/Object;
/*    */     //   442: aload #5
/*    */     //   444: getfield elem : Ljava/lang/Object;
/*    */     //   447: checkcast java/lang/String
/*    */     //   450: ifnonnull -> 968
/*    */     //   453: aload #5
/*    */     //   455: aload_3
/*    */     //   456: ifnull -> 515
/*    */     //   459: iconst_0
/*    */     //   460: aload_3
/*    */     //   461: invokevirtual _1 : ()Ljava/lang/Object;
/*    */     //   464: invokestatic unboxToInt : (Ljava/lang/Object;)I
/*    */     //   467: if_icmpne -> 515
/*    */     //   470: iconst_0
/*    */     //   471: aload_3
/*    */     //   472: invokevirtual _2 : ()Ljava/lang/Object;
/*    */     //   475: invokestatic unboxToInt : (Ljava/lang/Object;)I
/*    */     //   478: if_icmpne -> 515
/*    */     //   481: iconst_0
/*    */     //   482: aload_3
/*    */     //   483: invokevirtual _3 : ()Ljava/lang/Object;
/*    */     //   486: invokestatic unboxToInt : (Ljava/lang/Object;)I
/*    */     //   489: if_icmpne -> 515
/*    */     //   492: bipush #60
/*    */     //   494: aload_3
/*    */     //   495: invokevirtual _4 : ()Ljava/lang/Object;
/*    */     //   498: invokestatic unboxToInt : (Ljava/lang/Object;)I
/*    */     //   501: if_icmpne -> 515
/*    */     //   504: getstatic scala/xml/include/sax/EncodingHeuristics$EncodingNames$.MODULE$ : Lscala/xml/include/sax/EncodingHeuristics$EncodingNames$;
/*    */     //   507: invokevirtual bigUCS4 : ()Ljava/lang/String;
/*    */     //   510: astore #4
/*    */     //   512: goto -> 955
/*    */     //   515: aload_3
/*    */     //   516: ifnull -> 575
/*    */     //   519: bipush #60
/*    */     //   521: aload_3
/*    */     //   522: invokevirtual _1 : ()Ljava/lang/Object;
/*    */     //   525: invokestatic unboxToInt : (Ljava/lang/Object;)I
/*    */     //   528: if_icmpne -> 575
/*    */     //   531: iconst_0
/*    */     //   532: aload_3
/*    */     //   533: invokevirtual _2 : ()Ljava/lang/Object;
/*    */     //   536: invokestatic unboxToInt : (Ljava/lang/Object;)I
/*    */     //   539: if_icmpne -> 575
/*    */     //   542: iconst_0
/*    */     //   543: aload_3
/*    */     //   544: invokevirtual _3 : ()Ljava/lang/Object;
/*    */     //   547: invokestatic unboxToInt : (Ljava/lang/Object;)I
/*    */     //   550: if_icmpne -> 575
/*    */     //   553: iconst_0
/*    */     //   554: aload_3
/*    */     //   555: invokevirtual _4 : ()Ljava/lang/Object;
/*    */     //   558: invokestatic unboxToInt : (Ljava/lang/Object;)I
/*    */     //   561: if_icmpne -> 575
/*    */     //   564: getstatic scala/xml/include/sax/EncodingHeuristics$EncodingNames$.MODULE$ : Lscala/xml/include/sax/EncodingHeuristics$EncodingNames$;
/*    */     //   567: invokevirtual littleUCS4 : ()Ljava/lang/String;
/*    */     //   570: astore #4
/*    */     //   572: goto -> 955
/*    */     //   575: aload_3
/*    */     //   576: ifnull -> 635
/*    */     //   579: iconst_0
/*    */     //   580: aload_3
/*    */     //   581: invokevirtual _1 : ()Ljava/lang/Object;
/*    */     //   584: invokestatic unboxToInt : (Ljava/lang/Object;)I
/*    */     //   587: if_icmpne -> 635
/*    */     //   590: iconst_0
/*    */     //   591: aload_3
/*    */     //   592: invokevirtual _2 : ()Ljava/lang/Object;
/*    */     //   595: invokestatic unboxToInt : (Ljava/lang/Object;)I
/*    */     //   598: if_icmpne -> 635
/*    */     //   601: bipush #60
/*    */     //   603: aload_3
/*    */     //   604: invokevirtual _3 : ()Ljava/lang/Object;
/*    */     //   607: invokestatic unboxToInt : (Ljava/lang/Object;)I
/*    */     //   610: if_icmpne -> 635
/*    */     //   613: iconst_0
/*    */     //   614: aload_3
/*    */     //   615: invokevirtual _4 : ()Ljava/lang/Object;
/*    */     //   618: invokestatic unboxToInt : (Ljava/lang/Object;)I
/*    */     //   621: if_icmpne -> 635
/*    */     //   624: getstatic scala/xml/include/sax/EncodingHeuristics$EncodingNames$.MODULE$ : Lscala/xml/include/sax/EncodingHeuristics$EncodingNames$;
/*    */     //   627: invokevirtual unusualUCS4 : ()Ljava/lang/String;
/*    */     //   630: astore #4
/*    */     //   632: goto -> 955
/*    */     //   635: aload_3
/*    */     //   636: ifnull -> 695
/*    */     //   639: iconst_0
/*    */     //   640: aload_3
/*    */     //   641: invokevirtual _1 : ()Ljava/lang/Object;
/*    */     //   644: invokestatic unboxToInt : (Ljava/lang/Object;)I
/*    */     //   647: if_icmpne -> 695
/*    */     //   650: bipush #60
/*    */     //   652: aload_3
/*    */     //   653: invokevirtual _2 : ()Ljava/lang/Object;
/*    */     //   656: invokestatic unboxToInt : (Ljava/lang/Object;)I
/*    */     //   659: if_icmpne -> 695
/*    */     //   662: iconst_0
/*    */     //   663: aload_3
/*    */     //   664: invokevirtual _3 : ()Ljava/lang/Object;
/*    */     //   667: invokestatic unboxToInt : (Ljava/lang/Object;)I
/*    */     //   670: if_icmpne -> 695
/*    */     //   673: iconst_0
/*    */     //   674: aload_3
/*    */     //   675: invokevirtual _4 : ()Ljava/lang/Object;
/*    */     //   678: invokestatic unboxToInt : (Ljava/lang/Object;)I
/*    */     //   681: if_icmpne -> 695
/*    */     //   684: getstatic scala/xml/include/sax/EncodingHeuristics$EncodingNames$.MODULE$ : Lscala/xml/include/sax/EncodingHeuristics$EncodingNames$;
/*    */     //   687: invokevirtual unusualUCS4 : ()Ljava/lang/String;
/*    */     //   690: astore #4
/*    */     //   692: goto -> 955
/*    */     //   695: aload_3
/*    */     //   696: ifnull -> 756
/*    */     //   699: iconst_0
/*    */     //   700: aload_3
/*    */     //   701: invokevirtual _1 : ()Ljava/lang/Object;
/*    */     //   704: invokestatic unboxToInt : (Ljava/lang/Object;)I
/*    */     //   707: if_icmpne -> 756
/*    */     //   710: bipush #60
/*    */     //   712: aload_3
/*    */     //   713: invokevirtual _2 : ()Ljava/lang/Object;
/*    */     //   716: invokestatic unboxToInt : (Ljava/lang/Object;)I
/*    */     //   719: if_icmpne -> 756
/*    */     //   722: iconst_0
/*    */     //   723: aload_3
/*    */     //   724: invokevirtual _3 : ()Ljava/lang/Object;
/*    */     //   727: invokestatic unboxToInt : (Ljava/lang/Object;)I
/*    */     //   730: if_icmpne -> 756
/*    */     //   733: bipush #63
/*    */     //   735: aload_3
/*    */     //   736: invokevirtual _4 : ()Ljava/lang/Object;
/*    */     //   739: invokestatic unboxToInt : (Ljava/lang/Object;)I
/*    */     //   742: if_icmpne -> 756
/*    */     //   745: getstatic scala/xml/include/sax/EncodingHeuristics$EncodingNames$.MODULE$ : Lscala/xml/include/sax/EncodingHeuristics$EncodingNames$;
/*    */     //   748: invokevirtual bigUTF16 : ()Ljava/lang/String;
/*    */     //   751: astore #4
/*    */     //   753: goto -> 955
/*    */     //   756: aload_3
/*    */     //   757: ifnull -> 817
/*    */     //   760: bipush #60
/*    */     //   762: aload_3
/*    */     //   763: invokevirtual _1 : ()Ljava/lang/Object;
/*    */     //   766: invokestatic unboxToInt : (Ljava/lang/Object;)I
/*    */     //   769: if_icmpne -> 817
/*    */     //   772: iconst_0
/*    */     //   773: aload_3
/*    */     //   774: invokevirtual _2 : ()Ljava/lang/Object;
/*    */     //   777: invokestatic unboxToInt : (Ljava/lang/Object;)I
/*    */     //   780: if_icmpne -> 817
/*    */     //   783: bipush #63
/*    */     //   785: aload_3
/*    */     //   786: invokevirtual _3 : ()Ljava/lang/Object;
/*    */     //   789: invokestatic unboxToInt : (Ljava/lang/Object;)I
/*    */     //   792: if_icmpne -> 817
/*    */     //   795: iconst_0
/*    */     //   796: aload_3
/*    */     //   797: invokevirtual _4 : ()Ljava/lang/Object;
/*    */     //   800: invokestatic unboxToInt : (Ljava/lang/Object;)I
/*    */     //   803: if_icmpne -> 817
/*    */     //   806: getstatic scala/xml/include/sax/EncodingHeuristics$EncodingNames$.MODULE$ : Lscala/xml/include/sax/EncodingHeuristics$EncodingNames$;
/*    */     //   809: invokevirtual littleUTF16 : ()Ljava/lang/String;
/*    */     //   812: astore #4
/*    */     //   814: goto -> 955
/*    */     //   817: aload_3
/*    */     //   818: ifnull -> 882
/*    */     //   821: bipush #60
/*    */     //   823: aload_3
/*    */     //   824: invokevirtual _1 : ()Ljava/lang/Object;
/*    */     //   827: invokestatic unboxToInt : (Ljava/lang/Object;)I
/*    */     //   830: if_icmpne -> 882
/*    */     //   833: bipush #63
/*    */     //   835: aload_3
/*    */     //   836: invokevirtual _2 : ()Ljava/lang/Object;
/*    */     //   839: invokestatic unboxToInt : (Ljava/lang/Object;)I
/*    */     //   842: if_icmpne -> 882
/*    */     //   845: bipush #120
/*    */     //   847: aload_3
/*    */     //   848: invokevirtual _3 : ()Ljava/lang/Object;
/*    */     //   851: invokestatic unboxToInt : (Ljava/lang/Object;)I
/*    */     //   854: if_icmpne -> 882
/*    */     //   857: bipush #109
/*    */     //   859: aload_3
/*    */     //   860: invokevirtual _4 : ()Ljava/lang/Object;
/*    */     //   863: invokestatic unboxToInt : (Ljava/lang/Object;)I
/*    */     //   866: if_icmpne -> 882
/*    */     //   869: aload_0
/*    */     //   870: aload_1
/*    */     //   871: sipush #1024
/*    */     //   874: invokespecial readASCIIEncoding$1 : (Ljava/io/InputStream;I)Ljava/lang/String;
/*    */     //   877: astore #4
/*    */     //   879: goto -> 955
/*    */     //   882: aload_3
/*    */     //   883: ifnull -> 947
/*    */     //   886: bipush #76
/*    */     //   888: aload_3
/*    */     //   889: invokevirtual _1 : ()Ljava/lang/Object;
/*    */     //   892: invokestatic unboxToInt : (Ljava/lang/Object;)I
/*    */     //   895: if_icmpne -> 947
/*    */     //   898: bipush #111
/*    */     //   900: aload_3
/*    */     //   901: invokevirtual _2 : ()Ljava/lang/Object;
/*    */     //   904: invokestatic unboxToInt : (Ljava/lang/Object;)I
/*    */     //   907: if_icmpne -> 947
/*    */     //   910: sipush #167
/*    */     //   913: aload_3
/*    */     //   914: invokevirtual _3 : ()Ljava/lang/Object;
/*    */     //   917: invokestatic unboxToInt : (Ljava/lang/Object;)I
/*    */     //   920: if_icmpne -> 947
/*    */     //   923: sipush #148
/*    */     //   926: aload_3
/*    */     //   927: invokevirtual _4 : ()Ljava/lang/Object;
/*    */     //   930: invokestatic unboxToInt : (Ljava/lang/Object;)I
/*    */     //   933: if_icmpne -> 947
/*    */     //   936: getstatic scala/xml/include/sax/EncodingHeuristics$EncodingNames$.MODULE$ : Lscala/xml/include/sax/EncodingHeuristics$EncodingNames$;
/*    */     //   939: invokevirtual utf8 : ()Ljava/lang/String;
/*    */     //   942: astore #4
/*    */     //   944: goto -> 955
/*    */     //   947: getstatic scala/xml/include/sax/EncodingHeuristics$EncodingNames$.MODULE$ : Lscala/xml/include/sax/EncodingHeuristics$EncodingNames$;
/*    */     //   950: invokevirtual utf8 : ()Ljava/lang/String;
/*    */     //   953: astore #4
/*    */     //   955: aload #4
/*    */     //   957: putfield elem : Ljava/lang/Object;
/*    */     //   960: aload_0
/*    */     //   961: aload_1
/*    */     //   962: aload #5
/*    */     //   964: invokespecial resetAndRet$1 : (Ljava/io/InputStream;Lscala/runtime/ObjectRef;)Ljava/lang/String;
/*    */     //   967: areturn
/*    */     //   968: aload_0
/*    */     //   969: aload_1
/*    */     //   970: aload #5
/*    */     //   972: invokespecial resetAndRet$1 : (Ljava/io/InputStream;Lscala/runtime/ObjectRef;)Ljava/lang/String;
/*    */     //   975: areturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #49	-> 0
/*    */     //   #55	-> 10
/*    */     //   #50	-> 10
/*    */     //   #56	-> 17
/*    */     //   #59	-> 53
/*    */     //   #60	-> 59
/*    */     //   #59	-> 60
/*    */     //   #60	-> 61
/*    */     //   #59	-> 71
/*    */     //   #60	-> 72
/*    */     //   #59	-> 84
/*    */     //   #60	-> 85
/*    */     //   #59	-> 97
/*    */     //   #60	-> 98
/*    */     //   #59	-> 117
/*    */     //   #61	-> 121
/*    */     //   #59	-> 124
/*    */     //   #61	-> 125
/*    */     //   #59	-> 137
/*    */     //   #61	-> 138
/*    */     //   #59	-> 148
/*    */     //   #61	-> 149
/*    */     //   #59	-> 159
/*    */     //   #61	-> 160
/*    */     //   #59	-> 179
/*    */     //   #62	-> 183
/*    */     //   #59	-> 184
/*    */     //   #62	-> 185
/*    */     //   #59	-> 195
/*    */     //   #62	-> 196
/*    */     //   #59	-> 208
/*    */     //   #62	-> 209
/*    */     //   #59	-> 221
/*    */     //   #62	-> 222
/*    */     //   #59	-> 241
/*    */     //   #63	-> 245
/*    */     //   #59	-> 248
/*    */     //   #63	-> 249
/*    */     //   #59	-> 261
/*    */     //   #63	-> 262
/*    */     //   #59	-> 272
/*    */     //   #63	-> 273
/*    */     //   #59	-> 283
/*    */     //   #63	-> 284
/*    */     //   #59	-> 303
/*    */     //   #64	-> 307
/*    */     //   #59	-> 310
/*    */     //   #64	-> 311
/*    */     //   #59	-> 323
/*    */     //   #64	-> 324
/*    */     //   #59	-> 343
/*    */     //   #65	-> 347
/*    */     //   #59	-> 350
/*    */     //   #65	-> 351
/*    */     //   #59	-> 363
/*    */     //   #65	-> 364
/*    */     //   #59	-> 383
/*    */     //   #66	-> 387
/*    */     //   #59	-> 390
/*    */     //   #66	-> 391
/*    */     //   #59	-> 403
/*    */     //   #66	-> 404
/*    */     //   #59	-> 416
/*    */     //   #66	-> 417
/*    */     //   #67	-> 437
/*    */     //   #59	-> 438
/*    */     //   #69	-> 442
/*    */     //   #86	-> 453
/*    */     //   #87	-> 459
/*    */     //   #86	-> 460
/*    */     //   #87	-> 461
/*    */     //   #86	-> 471
/*    */     //   #87	-> 472
/*    */     //   #86	-> 482
/*    */     //   #87	-> 483
/*    */     //   #86	-> 494
/*    */     //   #87	-> 495
/*    */     //   #86	-> 515
/*    */     //   #88	-> 519
/*    */     //   #86	-> 521
/*    */     //   #88	-> 522
/*    */     //   #86	-> 532
/*    */     //   #88	-> 533
/*    */     //   #86	-> 543
/*    */     //   #88	-> 544
/*    */     //   #86	-> 554
/*    */     //   #88	-> 555
/*    */     //   #86	-> 575
/*    */     //   #89	-> 579
/*    */     //   #86	-> 580
/*    */     //   #89	-> 581
/*    */     //   #86	-> 591
/*    */     //   #89	-> 592
/*    */     //   #86	-> 603
/*    */     //   #89	-> 604
/*    */     //   #86	-> 614
/*    */     //   #89	-> 615
/*    */     //   #86	-> 635
/*    */     //   #90	-> 639
/*    */     //   #86	-> 640
/*    */     //   #90	-> 641
/*    */     //   #86	-> 652
/*    */     //   #90	-> 653
/*    */     //   #86	-> 663
/*    */     //   #90	-> 664
/*    */     //   #86	-> 674
/*    */     //   #90	-> 675
/*    */     //   #86	-> 695
/*    */     //   #91	-> 699
/*    */     //   #86	-> 700
/*    */     //   #91	-> 701
/*    */     //   #86	-> 712
/*    */     //   #91	-> 713
/*    */     //   #86	-> 723
/*    */     //   #91	-> 724
/*    */     //   #86	-> 735
/*    */     //   #91	-> 736
/*    */     //   #86	-> 756
/*    */     //   #92	-> 760
/*    */     //   #86	-> 762
/*    */     //   #92	-> 763
/*    */     //   #86	-> 773
/*    */     //   #92	-> 774
/*    */     //   #86	-> 785
/*    */     //   #92	-> 786
/*    */     //   #86	-> 796
/*    */     //   #92	-> 797
/*    */     //   #86	-> 817
/*    */     //   #93	-> 821
/*    */     //   #86	-> 823
/*    */     //   #93	-> 824
/*    */     //   #86	-> 835
/*    */     //   #93	-> 836
/*    */     //   #86	-> 847
/*    */     //   #93	-> 848
/*    */     //   #86	-> 859
/*    */     //   #93	-> 860
/*    */     //   #86	-> 882
/*    */     //   #94	-> 886
/*    */     //   #86	-> 888
/*    */     //   #94	-> 889
/*    */     //   #86	-> 900
/*    */     //   #94	-> 901
/*    */     //   #86	-> 913
/*    */     //   #94	-> 914
/*    */     //   #86	-> 926
/*    */     //   #94	-> 927
/*    */     //   #95	-> 947
/*    */     //   #86	-> 955
/*    */     //   #97	-> 960
/*    */     //   #70	-> 968
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	976	0	this	Lscala/xml/include/sax/EncodingHeuristics$;
/*    */     //   0	976	1	in	Ljava/io/InputStream;
/*    */     //   10	957	5	ret	Lscala/runtime/ObjectRef;
/*    */     //   53	914	3	bytes	Lscala/Tuple4;
/*    */   }
/*    */   
/*    */   private final String resetAndRet$1(InputStream in$1, ObjectRef ret$1) {
/* 51 */     in$1.reset();
/* 51 */     return (String)ret$1.elem;
/*    */   }
/*    */   
/*    */   private final String readASCIIEncoding$1(InputStream in$1, int bytesToRead$1) {
/*    */     // Byte code:
/*    */     //   0: iload_2
/*    */     //   1: iconst_4
/*    */     //   2: isub
/*    */     //   3: newarray byte
/*    */     //   5: astore_3
/*    */     //   6: aload_1
/*    */     //   7: aload_3
/*    */     //   8: iconst_0
/*    */     //   9: iload_2
/*    */     //   10: iconst_4
/*    */     //   11: isub
/*    */     //   12: invokevirtual read : ([BII)I
/*    */     //   15: istore #4
/*    */     //   17: new java/lang/String
/*    */     //   20: dup
/*    */     //   21: aload_3
/*    */     //   22: iconst_0
/*    */     //   23: iload #4
/*    */     //   25: ldc 'ISO-8859-1'
/*    */     //   27: invokespecial <init> : ([BIILjava/lang/String;)V
/*    */     //   30: astore #7
/*    */     //   32: new scala/collection/immutable/StringOps
/*    */     //   35: dup
/*    */     //   36: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*    */     //   39: astore #5
/*    */     //   41: ldc '(?m).*?encoding\s*=\s*["'](.+?)['"]'
/*    */     //   43: invokespecial <init> : (Ljava/lang/String;)V
/*    */     //   46: invokevirtual r : ()Lscala/util/matching/Regex;
/*    */     //   49: astore #6
/*    */     //   51: aload #6
/*    */     //   53: aload #7
/*    */     //   55: invokevirtual findFirstMatchIn : (Ljava/lang/CharSequence;)Lscala/Option;
/*    */     //   58: astore #10
/*    */     //   60: getstatic scala/None$.MODULE$ : Lscala/None$;
/*    */     //   63: dup
/*    */     //   64: ifnonnull -> 76
/*    */     //   67: pop
/*    */     //   68: aload #10
/*    */     //   70: ifnull -> 84
/*    */     //   73: goto -> 95
/*    */     //   76: aload #10
/*    */     //   78: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   81: ifeq -> 95
/*    */     //   84: getstatic scala/xml/include/sax/EncodingHeuristics$EncodingNames$.MODULE$ : Lscala/xml/include/sax/EncodingHeuristics$EncodingNames$;
/*    */     //   87: invokevirtual default : ()Ljava/lang/String;
/*    */     //   90: astore #9
/*    */     //   92: goto -> 132
/*    */     //   95: aload #10
/*    */     //   97: instanceof scala/Some
/*    */     //   100: ifeq -> 135
/*    */     //   103: aload #10
/*    */     //   105: checkcast scala/Some
/*    */     //   108: astore #8
/*    */     //   110: aload #8
/*    */     //   112: invokevirtual x : ()Ljava/lang/Object;
/*    */     //   115: checkcast scala/util/matching/Regex$MatchData
/*    */     //   118: invokeinterface subgroups : ()Lscala/collection/immutable/List;
/*    */     //   123: iconst_0
/*    */     //   124: invokevirtual apply : (I)Ljava/lang/Object;
/*    */     //   127: checkcast java/lang/String
/*    */     //   130: astore #9
/*    */     //   132: aload #9
/*    */     //   134: areturn
/*    */     //   135: new scala/MatchError
/*    */     //   138: dup
/*    */     //   139: aload #10
/*    */     //   141: invokespecial <init> : (Ljava/lang/Object;)V
/*    */     //   144: athrow
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #73	-> 0
/*    */     //   #74	-> 6
/*    */     //   #77	-> 17
/*    */     //   #78	-> 32
/*    */     //   #79	-> 51
/*    */     //   #80	-> 60
/*    */     //   #81	-> 95
/*    */     //   #79	-> 110
/*    */     //   #81	-> 112
/*    */     //   #79	-> 132
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	145	0	this	Lscala/xml/include/sax/EncodingHeuristics$;
/*    */     //   0	145	1	in$1	Ljava/io/InputStream;
/*    */     //   0	145	2	bytesToRead$1	I
/*    */     //   6	128	3	data	[B
/*    */     //   17	117	4	length	I
/*    */     //   32	102	7	declaration	Ljava/lang/String;
/*    */     //   51	83	6	regexp	Lscala/util/matching/Regex;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\include\sax\EncodingHeuristics$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */