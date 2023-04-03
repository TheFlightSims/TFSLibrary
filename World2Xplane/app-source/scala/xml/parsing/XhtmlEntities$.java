/*    */ package scala.xml.parsing;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.MatchError;
/*    */ import scala.Serializable;
/*    */ import scala.Tuple2;
/*    */ import scala.collection.GenTraversableOnce;
/*    */ import scala.collection.Seq;
/*    */ import scala.collection.immutable.List;
/*    */ import scala.collection.immutable.Map;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ import scala.xml.dtd.EntityDef;
/*    */ import scala.xml.dtd.IntDef;
/*    */ import scala.xml.dtd.ParsedEntityDecl;
/*    */ 
/*    */ public final class XhtmlEntities$ {
/*    */   public static final XhtmlEntities$ MODULE$;
/*    */   
/*    */   private final List<Tuple2<String, Object>> entList;
/*    */   
/*    */   private final Map<String, Object> entMap;
/*    */   
/*    */   private final List<Tuple2<String, ParsedEntityDecl>> entities;
/*    */   
/*    */   private XhtmlEntities$() {
/* 18 */     MODULE$ = this;
/* 19 */     (new Tuple2[249])[0] = new Tuple2("quot", BoxesRunTime.boxToInteger(34));
/* 19 */     (new Tuple2[249])[1] = new Tuple2("amp", BoxesRunTime.boxToInteger(38));
/* 19 */     (new Tuple2[249])[2] = new Tuple2("lt", BoxesRunTime.boxToInteger(60));
/* 19 */     (new Tuple2[249])[3] = new Tuple2("gt", BoxesRunTime.boxToInteger(62));
/* 19 */     (new Tuple2[249])[4] = new Tuple2("nbsp", BoxesRunTime.boxToInteger(160));
/* 19 */     (new Tuple2[249])[5] = new Tuple2("iexcl", BoxesRunTime.boxToInteger(161));
/* 19 */     (new Tuple2[249])[6] = new Tuple2("cent", BoxesRunTime.boxToInteger(162));
/* 19 */     (new Tuple2[249])[7] = new Tuple2("pound", BoxesRunTime.boxToInteger(163));
/* 19 */     (new Tuple2[249])[8] = new Tuple2("curren", BoxesRunTime.boxToInteger(164));
/* 19 */     (new Tuple2[249])[9] = new Tuple2("yen", BoxesRunTime.boxToInteger(165));
/* 19 */     (new Tuple2[249])[10] = 
/* 20 */       new Tuple2("euro", BoxesRunTime.boxToInteger(8364));
/* 20 */     (new Tuple2[249])[11] = new Tuple2("brvbar", BoxesRunTime.boxToInteger(166));
/* 20 */     (new Tuple2[249])[12] = new Tuple2("sect", BoxesRunTime.boxToInteger(167));
/* 20 */     (new Tuple2[249])[13] = new Tuple2("uml", BoxesRunTime.boxToInteger(168));
/* 20 */     (new Tuple2[249])[14] = new Tuple2("copy", BoxesRunTime.boxToInteger(169));
/* 20 */     (new Tuple2[249])[15] = new Tuple2("ordf", BoxesRunTime.boxToInteger(170));
/* 20 */     (new Tuple2[249])[16] = new Tuple2("laquo", BoxesRunTime.boxToInteger(171));
/* 20 */     (new Tuple2[249])[17] = new Tuple2("shy", BoxesRunTime.boxToInteger(173));
/* 20 */     (new Tuple2[249])[18] = new Tuple2("reg", BoxesRunTime.boxToInteger(174));
/* 20 */     (new Tuple2[249])[19] = new Tuple2("trade", BoxesRunTime.boxToInteger(8482));
/* 21 */     (new Tuple2[249])[20] = new Tuple2("macr", BoxesRunTime.boxToInteger(175));
/* 21 */     (new Tuple2[249])[21] = new Tuple2("deg", BoxesRunTime.boxToInteger(176));
/* 21 */     (new Tuple2[249])[22] = new Tuple2("plusmn", BoxesRunTime.boxToInteger(177));
/* 21 */     (new Tuple2[249])[23] = new Tuple2("sup2", BoxesRunTime.boxToInteger(178));
/* 21 */     (new Tuple2[249])[24] = new Tuple2("sup3", BoxesRunTime.boxToInteger(179));
/* 21 */     (new Tuple2[249])[25] = new Tuple2("acute", BoxesRunTime.boxToInteger(180));
/* 21 */     (new Tuple2[249])[26] = new Tuple2("micro", BoxesRunTime.boxToInteger(181));
/* 21 */     (new Tuple2[249])[27] = new Tuple2("para", BoxesRunTime.boxToInteger(182));
/* 21 */     (new Tuple2[249])[28] = new Tuple2("middot", BoxesRunTime.boxToInteger(183));
/* 21 */     (new Tuple2[249])[29] = new Tuple2("cedil", BoxesRunTime.boxToInteger(184));
/* 22 */     (new Tuple2[249])[30] = new Tuple2("sup1", BoxesRunTime.boxToInteger(185));
/* 22 */     (new Tuple2[249])[31] = new Tuple2("ordm", BoxesRunTime.boxToInteger(186));
/* 22 */     (new Tuple2[249])[32] = new Tuple2("raquo", BoxesRunTime.boxToInteger(187));
/* 22 */     (new Tuple2[249])[33] = new Tuple2("frac14", BoxesRunTime.boxToInteger(188));
/* 22 */     (new Tuple2[249])[34] = new Tuple2("frac12", BoxesRunTime.boxToInteger(189));
/* 22 */     (new Tuple2[249])[35] = new Tuple2("frac34", BoxesRunTime.boxToInteger(190));
/* 22 */     (new Tuple2[249])[36] = new Tuple2("iquest", BoxesRunTime.boxToInteger(191));
/* 22 */     (new Tuple2[249])[37] = new Tuple2("times", BoxesRunTime.boxToInteger(215));
/* 22 */     (new Tuple2[249])[38] = new Tuple2("divide", BoxesRunTime.boxToInteger(247));
/* 23 */     (new Tuple2[249])[39] = new Tuple2("Agrave", BoxesRunTime.boxToInteger(192));
/* 23 */     (new Tuple2[249])[40] = new Tuple2("Aacute", BoxesRunTime.boxToInteger(193));
/* 23 */     (new Tuple2[249])[41] = new Tuple2("Acirc", BoxesRunTime.boxToInteger(194));
/* 23 */     (new Tuple2[249])[42] = new Tuple2("Atilde", BoxesRunTime.boxToInteger(195));
/* 23 */     (new Tuple2[249])[43] = new Tuple2("Auml", BoxesRunTime.boxToInteger(196));
/* 23 */     (new Tuple2[249])[44] = new Tuple2("Aring", BoxesRunTime.boxToInteger(197));
/* 23 */     (new Tuple2[249])[45] = new Tuple2("AElig", BoxesRunTime.boxToInteger(198));
/* 23 */     (new Tuple2[249])[46] = new Tuple2("Ccedil", BoxesRunTime.boxToInteger(199));
/* 23 */     (new Tuple2[249])[47] = new Tuple2("Egrave", BoxesRunTime.boxToInteger(200));
/* 24 */     (new Tuple2[249])[48] = new Tuple2("Eacute", BoxesRunTime.boxToInteger(201));
/* 24 */     (new Tuple2[249])[49] = new Tuple2("Ecirc", BoxesRunTime.boxToInteger(202));
/* 24 */     (new Tuple2[249])[50] = new Tuple2("Euml", BoxesRunTime.boxToInteger(203));
/* 24 */     (new Tuple2[249])[51] = new Tuple2("Igrave", BoxesRunTime.boxToInteger(204));
/* 24 */     (new Tuple2[249])[52] = new Tuple2("Iacute", BoxesRunTime.boxToInteger(205));
/* 24 */     (new Tuple2[249])[53] = new Tuple2("Icirc", BoxesRunTime.boxToInteger(206));
/* 24 */     (new Tuple2[249])[54] = new Tuple2("Iuml", BoxesRunTime.boxToInteger(207));
/* 24 */     (new Tuple2[249])[55] = new Tuple2("ETH", BoxesRunTime.boxToInteger(208));
/* 24 */     (new Tuple2[249])[56] = new Tuple2("Ntilde", BoxesRunTime.boxToInteger(209));
/* 25 */     (new Tuple2[249])[57] = new Tuple2("Ograve", BoxesRunTime.boxToInteger(210));
/* 25 */     (new Tuple2[249])[58] = new Tuple2("Oacute", BoxesRunTime.boxToInteger(211));
/* 25 */     (new Tuple2[249])[59] = new Tuple2("Ocirc", BoxesRunTime.boxToInteger(212));
/* 25 */     (new Tuple2[249])[60] = new Tuple2("Otilde", BoxesRunTime.boxToInteger(213));
/* 25 */     (new Tuple2[249])[61] = new Tuple2("Ouml", BoxesRunTime.boxToInteger(214));
/* 25 */     (new Tuple2[249])[62] = new Tuple2("Oslash", BoxesRunTime.boxToInteger(216));
/* 25 */     (new Tuple2[249])[63] = new Tuple2("Ugrave", BoxesRunTime.boxToInteger(217));
/* 25 */     (new Tuple2[249])[64] = new Tuple2("Uacute", BoxesRunTime.boxToInteger(218));
/* 25 */     (new Tuple2[249])[65] = new Tuple2("Ucirc", BoxesRunTime.boxToInteger(219));
/* 26 */     (new Tuple2[249])[66] = new Tuple2("Uuml", BoxesRunTime.boxToInteger(220));
/* 26 */     (new Tuple2[249])[67] = new Tuple2("Yacute", BoxesRunTime.boxToInteger(221));
/* 26 */     (new Tuple2[249])[68] = new Tuple2("THORN", BoxesRunTime.boxToInteger(222));
/* 26 */     (new Tuple2[249])[69] = new Tuple2("szlig", BoxesRunTime.boxToInteger(223));
/* 26 */     (new Tuple2[249])[70] = new Tuple2("agrave", BoxesRunTime.boxToInteger(224));
/* 26 */     (new Tuple2[249])[71] = new Tuple2("aacute", BoxesRunTime.boxToInteger(225));
/* 26 */     (new Tuple2[249])[72] = new Tuple2("acirc", BoxesRunTime.boxToInteger(226));
/* 26 */     (new Tuple2[249])[73] = new Tuple2("atilde", BoxesRunTime.boxToInteger(227));
/* 26 */     (new Tuple2[249])[74] = new Tuple2("auml", BoxesRunTime.boxToInteger(228));
/* 27 */     (new Tuple2[249])[75] = new Tuple2("aring", BoxesRunTime.boxToInteger(229));
/* 27 */     (new Tuple2[249])[76] = new Tuple2("aelig", BoxesRunTime.boxToInteger(230));
/* 27 */     (new Tuple2[249])[77] = new Tuple2("ccedil", BoxesRunTime.boxToInteger(231));
/* 27 */     (new Tuple2[249])[78] = new Tuple2("egrave", BoxesRunTime.boxToInteger(232));
/* 27 */     (new Tuple2[249])[79] = new Tuple2("eacute", BoxesRunTime.boxToInteger(233));
/* 27 */     (new Tuple2[249])[80] = new Tuple2("ecirc", BoxesRunTime.boxToInteger(234));
/* 27 */     (new Tuple2[249])[81] = new Tuple2("euml", BoxesRunTime.boxToInteger(235));
/* 27 */     (new Tuple2[249])[82] = new Tuple2("igrave", BoxesRunTime.boxToInteger(236));
/* 27 */     (new Tuple2[249])[83] = new Tuple2("iacute", BoxesRunTime.boxToInteger(237));
/* 28 */     (new Tuple2[249])[84] = new Tuple2("icirc", BoxesRunTime.boxToInteger(238));
/* 28 */     (new Tuple2[249])[85] = new Tuple2("iuml", BoxesRunTime.boxToInteger(239));
/* 28 */     (new Tuple2[249])[86] = new Tuple2("eth", BoxesRunTime.boxToInteger(240));
/* 28 */     (new Tuple2[249])[87] = new Tuple2("ntilde", BoxesRunTime.boxToInteger(241));
/* 28 */     (new Tuple2[249])[88] = new Tuple2("ograve", BoxesRunTime.boxToInteger(242));
/* 28 */     (new Tuple2[249])[89] = new Tuple2("oacute", BoxesRunTime.boxToInteger(243));
/* 28 */     (new Tuple2[249])[90] = new Tuple2("ocirc", BoxesRunTime.boxToInteger(244));
/* 28 */     (new Tuple2[249])[91] = new Tuple2("otilde", BoxesRunTime.boxToInteger(245));
/* 28 */     (new Tuple2[249])[92] = new Tuple2("ouml", BoxesRunTime.boxToInteger(246));
/* 29 */     (new Tuple2[249])[93] = new Tuple2("oslash", BoxesRunTime.boxToInteger(248));
/* 29 */     (new Tuple2[249])[94] = new Tuple2("ugrave", BoxesRunTime.boxToInteger(249));
/* 29 */     (new Tuple2[249])[95] = new Tuple2("uacute", BoxesRunTime.boxToInteger(250));
/* 29 */     (new Tuple2[249])[96] = new Tuple2("ucirc", BoxesRunTime.boxToInteger(251));
/* 29 */     (new Tuple2[249])[97] = new Tuple2("uuml", BoxesRunTime.boxToInteger(252));
/* 29 */     (new Tuple2[249])[98] = new Tuple2("yacute", BoxesRunTime.boxToInteger(253));
/* 29 */     (new Tuple2[249])[99] = new Tuple2("thorn", BoxesRunTime.boxToInteger(254));
/* 29 */     (new Tuple2[249])[100] = new Tuple2("yuml", BoxesRunTime.boxToInteger(255));
/* 29 */     (new Tuple2[249])[101] = new Tuple2("OElig", BoxesRunTime.boxToInteger(338));
/* 30 */     (new Tuple2[249])[102] = new Tuple2("oelig", BoxesRunTime.boxToInteger(339));
/* 30 */     (new Tuple2[249])[103] = new Tuple2("Scaron", BoxesRunTime.boxToInteger(352));
/* 30 */     (new Tuple2[249])[104] = new Tuple2("scaron", BoxesRunTime.boxToInteger(353));
/* 30 */     (new Tuple2[249])[105] = new Tuple2("Yuml", BoxesRunTime.boxToInteger(376));
/* 30 */     (new Tuple2[249])[106] = new Tuple2("circ", BoxesRunTime.boxToInteger(710));
/* 30 */     (new Tuple2[249])[107] = new Tuple2("ensp", BoxesRunTime.boxToInteger(8194));
/* 30 */     (new Tuple2[249])[108] = new Tuple2("emsp", BoxesRunTime.boxToInteger(8195));
/* 30 */     (new Tuple2[249])[109] = new Tuple2("zwnj", BoxesRunTime.boxToInteger(204));
/* 30 */     (new Tuple2[249])[110] = new Tuple2("zwj", BoxesRunTime.boxToInteger(8205));
/* 30 */     (new Tuple2[249])[111] = new Tuple2("lrm", BoxesRunTime.boxToInteger(8206));
/* 31 */     (new Tuple2[249])[112] = new Tuple2("rlm", BoxesRunTime.boxToInteger(8207));
/* 31 */     (new Tuple2[249])[113] = new Tuple2("ndash", BoxesRunTime.boxToInteger(8211));
/* 31 */     (new Tuple2[249])[114] = new Tuple2("mdash", BoxesRunTime.boxToInteger(8212));
/* 31 */     (new Tuple2[249])[115] = new Tuple2("lsquo", BoxesRunTime.boxToInteger(8216));
/* 31 */     (new Tuple2[249])[116] = new Tuple2("rsquo", BoxesRunTime.boxToInteger(8217));
/* 31 */     (new Tuple2[249])[117] = new Tuple2("sbquo", BoxesRunTime.boxToInteger(8218));
/* 31 */     (new Tuple2[249])[118] = new Tuple2("ldquo", BoxesRunTime.boxToInteger(8220));
/* 31 */     (new Tuple2[249])[119] = new Tuple2("rdquo", BoxesRunTime.boxToInteger(8221));
/* 31 */     (new Tuple2[249])[120] = new Tuple2("bdquo", BoxesRunTime.boxToInteger(8222));
/* 32 */     (new Tuple2[249])[121] = new Tuple2("dagger", BoxesRunTime.boxToInteger(8224));
/* 32 */     (new Tuple2[249])[122] = new Tuple2("Dagger", BoxesRunTime.boxToInteger(8225));
/* 32 */     (new Tuple2[249])[123] = new Tuple2("permil", BoxesRunTime.boxToInteger(8240));
/* 32 */     (new Tuple2[249])[124] = new Tuple2("lsaquo", BoxesRunTime.boxToInteger(8249));
/* 32 */     (new Tuple2[249])[125] = new Tuple2("rsaquo", BoxesRunTime.boxToInteger(8250));
/* 32 */     (new Tuple2[249])[126] = new Tuple2("fnof", BoxesRunTime.boxToInteger(402));
/* 32 */     (new Tuple2[249])[127] = new Tuple2("bull", BoxesRunTime.boxToInteger(8226));
/* 32 */     (new Tuple2[249])[128] = new Tuple2("hellip", BoxesRunTime.boxToInteger(8230));
/* 32 */     (new Tuple2[249])[129] = new Tuple2("prime", BoxesRunTime.boxToInteger(8242));
/* 33 */     (new Tuple2[249])[130] = new Tuple2("Prime", BoxesRunTime.boxToInteger(8243));
/* 33 */     (new Tuple2[249])[131] = new Tuple2("oline", BoxesRunTime.boxToInteger(8254));
/* 33 */     (new Tuple2[249])[132] = new Tuple2("frasl", BoxesRunTime.boxToInteger(8260));
/* 33 */     (new Tuple2[249])[133] = new Tuple2("weierp", BoxesRunTime.boxToInteger(8472));
/* 33 */     (new Tuple2[249])[134] = new Tuple2("image", BoxesRunTime.boxToInteger(8465));
/* 33 */     (new Tuple2[249])[135] = new Tuple2("real", BoxesRunTime.boxToInteger(8476));
/* 33 */     (new Tuple2[249])[136] = new Tuple2("alefsym", BoxesRunTime.boxToInteger(8501));
/* 33 */     (new Tuple2[249])[137] = new Tuple2("larr", BoxesRunTime.boxToInteger(8592));
/* 33 */     (new Tuple2[249])[138] = new Tuple2("uarr", BoxesRunTime.boxToInteger(8593));
/* 34 */     (new Tuple2[249])[139] = new Tuple2("rarr", BoxesRunTime.boxToInteger(8594));
/* 34 */     (new Tuple2[249])[140] = new Tuple2("darr", BoxesRunTime.boxToInteger(8495));
/* 34 */     (new Tuple2[249])[141] = new Tuple2("harr", BoxesRunTime.boxToInteger(8596));
/* 34 */     (new Tuple2[249])[142] = new Tuple2("crarr", BoxesRunTime.boxToInteger(8629));
/* 34 */     (new Tuple2[249])[143] = new Tuple2("lArr", BoxesRunTime.boxToInteger(8656));
/* 34 */     (new Tuple2[249])[144] = new Tuple2("uArr", BoxesRunTime.boxToInteger(8657));
/* 34 */     (new Tuple2[249])[145] = new Tuple2("rArr", BoxesRunTime.boxToInteger(8658));
/* 34 */     (new Tuple2[249])[146] = new Tuple2("dArr", BoxesRunTime.boxToInteger(8659));
/* 34 */     (new Tuple2[249])[147] = new Tuple2("hArr", BoxesRunTime.boxToInteger(8660));
/* 35 */     (new Tuple2[249])[148] = new Tuple2("forall", BoxesRunTime.boxToInteger(8704));
/* 35 */     (new Tuple2[249])[149] = new Tuple2("part", BoxesRunTime.boxToInteger(8706));
/* 35 */     (new Tuple2[249])[150] = new Tuple2("exist", BoxesRunTime.boxToInteger(8707));
/* 35 */     (new Tuple2[249])[151] = new Tuple2("empty", BoxesRunTime.boxToInteger(8709));
/* 35 */     (new Tuple2[249])[152] = new Tuple2("nabla", BoxesRunTime.boxToInteger(8711));
/* 35 */     (new Tuple2[249])[153] = new Tuple2("isin", BoxesRunTime.boxToInteger(8712));
/* 35 */     (new Tuple2[249])[154] = new Tuple2("notin", BoxesRunTime.boxToInteger(8713));
/* 35 */     (new Tuple2[249])[155] = new Tuple2("ni", BoxesRunTime.boxToInteger(8715));
/* 35 */     (new Tuple2[249])[156] = new Tuple2("prod", BoxesRunTime.boxToInteger(8719));
/* 36 */     (new Tuple2[249])[157] = new Tuple2("sum", BoxesRunTime.boxToInteger(8721));
/* 36 */     (new Tuple2[249])[158] = new Tuple2("minus", BoxesRunTime.boxToInteger(8722));
/* 36 */     (new Tuple2[249])[159] = new Tuple2("lowast", BoxesRunTime.boxToInteger(8727));
/* 36 */     (new Tuple2[249])[160] = new Tuple2("radic", BoxesRunTime.boxToInteger(8730));
/* 36 */     (new Tuple2[249])[161] = new Tuple2("prop", BoxesRunTime.boxToInteger(8733));
/* 36 */     (new Tuple2[249])[162] = new Tuple2("infin", BoxesRunTime.boxToInteger(8734));
/* 36 */     (new Tuple2[249])[163] = new Tuple2("ang", BoxesRunTime.boxToInteger(8736));
/* 36 */     (new Tuple2[249])[164] = new Tuple2("and", BoxesRunTime.boxToInteger(8743));
/* 36 */     (new Tuple2[249])[165] = new Tuple2("or", BoxesRunTime.boxToInteger(8744));
/* 37 */     (new Tuple2[249])[166] = new Tuple2("cap", BoxesRunTime.boxToInteger(8745));
/* 37 */     (new Tuple2[249])[167] = new Tuple2("cup", BoxesRunTime.boxToInteger(8746));
/* 37 */     (new Tuple2[249])[168] = new Tuple2("int", BoxesRunTime.boxToInteger(8747));
/* 37 */     (new Tuple2[249])[169] = new Tuple2("there4", BoxesRunTime.boxToInteger(8756));
/* 37 */     (new Tuple2[249])[170] = new Tuple2("sim", BoxesRunTime.boxToInteger(8764));
/* 37 */     (new Tuple2[249])[171] = new Tuple2("cong", BoxesRunTime.boxToInteger(8773));
/* 37 */     (new Tuple2[249])[172] = new Tuple2("asymp", BoxesRunTime.boxToInteger(8776));
/* 37 */     (new Tuple2[249])[173] = new Tuple2("ne", BoxesRunTime.boxToInteger(8800));
/* 37 */     (new Tuple2[249])[174] = new Tuple2("equiv", BoxesRunTime.boxToInteger(8801));
/* 37 */     (new Tuple2[249])[175] = new Tuple2("le", BoxesRunTime.boxToInteger(8804));
/* 38 */     (new Tuple2[249])[176] = new Tuple2("ge", BoxesRunTime.boxToInteger(8805));
/* 38 */     (new Tuple2[249])[177] = new Tuple2("sub", BoxesRunTime.boxToInteger(8834));
/* 38 */     (new Tuple2[249])[178] = new Tuple2("sup", BoxesRunTime.boxToInteger(8835));
/* 38 */     (new Tuple2[249])[179] = new Tuple2("nsub", BoxesRunTime.boxToInteger(8836));
/* 38 */     (new Tuple2[249])[180] = new Tuple2("sube", BoxesRunTime.boxToInteger(8838));
/* 38 */     (new Tuple2[249])[181] = new Tuple2("supe", BoxesRunTime.boxToInteger(8839));
/* 38 */     (new Tuple2[249])[182] = new Tuple2("oplus", BoxesRunTime.boxToInteger(8853));
/* 38 */     (new Tuple2[249])[183] = new Tuple2("otimes", BoxesRunTime.boxToInteger(8855));
/* 38 */     (new Tuple2[249])[184] = new Tuple2("perp", BoxesRunTime.boxToInteger(8869));
/* 38 */     (new Tuple2[249])[185] = new Tuple2("sdot", BoxesRunTime.boxToInteger(8901));
/* 39 */     (new Tuple2[249])[186] = new Tuple2("lceil", BoxesRunTime.boxToInteger(8968));
/* 39 */     (new Tuple2[249])[187] = new Tuple2("rceil", BoxesRunTime.boxToInteger(8969));
/* 39 */     (new Tuple2[249])[188] = new Tuple2("lfloor", BoxesRunTime.boxToInteger(8970));
/* 39 */     (new Tuple2[249])[189] = new Tuple2("rfloor", BoxesRunTime.boxToInteger(8971));
/* 39 */     (new Tuple2[249])[190] = new Tuple2("lang", BoxesRunTime.boxToInteger(9001));
/* 39 */     (new Tuple2[249])[191] = new Tuple2("rang", BoxesRunTime.boxToInteger(9002));
/* 39 */     (new Tuple2[249])[192] = new Tuple2("loz", BoxesRunTime.boxToInteger(9674));
/* 39 */     (new Tuple2[249])[193] = new Tuple2("spades", BoxesRunTime.boxToInteger(9824));
/* 39 */     (new Tuple2[249])[194] = new Tuple2("clubs", BoxesRunTime.boxToInteger(9827));
/* 40 */     (new Tuple2[249])[195] = new Tuple2("hearts", BoxesRunTime.boxToInteger(9829));
/* 40 */     (new Tuple2[249])[196] = new Tuple2("diams", BoxesRunTime.boxToInteger(9830));
/* 40 */     (new Tuple2[249])[197] = new Tuple2("Alpha", BoxesRunTime.boxToInteger(913));
/* 40 */     (new Tuple2[249])[198] = new Tuple2("Beta", BoxesRunTime.boxToInteger(914));
/* 40 */     (new Tuple2[249])[199] = new Tuple2("Gamma", BoxesRunTime.boxToInteger(915));
/* 40 */     (new Tuple2[249])[200] = new Tuple2("Delta", BoxesRunTime.boxToInteger(916));
/* 40 */     (new Tuple2[249])[201] = new Tuple2("Epsilon", BoxesRunTime.boxToInteger(917));
/* 40 */     (new Tuple2[249])[202] = new Tuple2("Zeta", BoxesRunTime.boxToInteger(918));
/* 40 */     (new Tuple2[249])[203] = new Tuple2("Eta", BoxesRunTime.boxToInteger(919));
/* 41 */     (new Tuple2[249])[204] = new Tuple2("Theta", BoxesRunTime.boxToInteger(920));
/* 41 */     (new Tuple2[249])[205] = new Tuple2("Iota", BoxesRunTime.boxToInteger(921));
/* 41 */     (new Tuple2[249])[206] = new Tuple2("Kappa", BoxesRunTime.boxToInteger(922));
/* 41 */     (new Tuple2[249])[207] = new Tuple2("Lambda", BoxesRunTime.boxToInteger(923));
/* 41 */     (new Tuple2[249])[208] = new Tuple2("Mu", BoxesRunTime.boxToInteger(924));
/* 41 */     (new Tuple2[249])[209] = new Tuple2("Nu", BoxesRunTime.boxToInteger(925));
/* 41 */     (new Tuple2[249])[210] = new Tuple2("Xi", BoxesRunTime.boxToInteger(926));
/* 41 */     (new Tuple2[249])[211] = new Tuple2("Omicron", BoxesRunTime.boxToInteger(927));
/* 41 */     (new Tuple2[249])[212] = new Tuple2("Pi", BoxesRunTime.boxToInteger(928));
/* 41 */     (new Tuple2[249])[213] = new Tuple2("Rho", BoxesRunTime.boxToInteger(929));
/* 42 */     (new Tuple2[249])[214] = new Tuple2("Sigma", BoxesRunTime.boxToInteger(931));
/* 42 */     (new Tuple2[249])[215] = new Tuple2("Tau", BoxesRunTime.boxToInteger(932));
/* 42 */     (new Tuple2[249])[216] = new Tuple2("Upsilon", BoxesRunTime.boxToInteger(933));
/* 42 */     (new Tuple2[249])[217] = new Tuple2("Phi", BoxesRunTime.boxToInteger(934));
/* 42 */     (new Tuple2[249])[218] = new Tuple2("Chi", BoxesRunTime.boxToInteger(935));
/* 42 */     (new Tuple2[249])[219] = new Tuple2("Psi", BoxesRunTime.boxToInteger(936));
/* 42 */     (new Tuple2[249])[220] = new Tuple2("Omega", BoxesRunTime.boxToInteger(937));
/* 42 */     (new Tuple2[249])[221] = new Tuple2("alpha", BoxesRunTime.boxToInteger(945));
/* 42 */     (new Tuple2[249])[222] = new Tuple2("beta", BoxesRunTime.boxToInteger(946));
/* 42 */     (new Tuple2[249])[223] = new Tuple2("gamma", BoxesRunTime.boxToInteger(947));
/* 43 */     (new Tuple2[249])[224] = new Tuple2("delta", BoxesRunTime.boxToInteger(948));
/* 43 */     (new Tuple2[249])[225] = new Tuple2("epsilon", BoxesRunTime.boxToInteger(949));
/* 43 */     (new Tuple2[249])[226] = new Tuple2("zeta", BoxesRunTime.boxToInteger(950));
/* 43 */     (new Tuple2[249])[227] = new Tuple2("eta", BoxesRunTime.boxToInteger(951));
/* 43 */     (new Tuple2[249])[228] = new Tuple2("theta", BoxesRunTime.boxToInteger(952));
/* 43 */     (new Tuple2[249])[229] = new Tuple2("iota", BoxesRunTime.boxToInteger(953));
/* 43 */     (new Tuple2[249])[230] = new Tuple2("kappa", BoxesRunTime.boxToInteger(954));
/* 43 */     (new Tuple2[249])[231] = new Tuple2("lambda", BoxesRunTime.boxToInteger(955));
/* 43 */     (new Tuple2[249])[232] = new Tuple2("mu", BoxesRunTime.boxToInteger(956));
/* 43 */     (new Tuple2[249])[233] = new Tuple2("nu", BoxesRunTime.boxToInteger(957));
/* 44 */     (new Tuple2[249])[234] = new Tuple2("xi", BoxesRunTime.boxToInteger(958));
/* 44 */     (new Tuple2[249])[235] = new Tuple2("omicron", BoxesRunTime.boxToInteger(959));
/* 44 */     (new Tuple2[249])[236] = new Tuple2("pi", BoxesRunTime.boxToInteger(960));
/* 44 */     (new Tuple2[249])[237] = new Tuple2("rho", BoxesRunTime.boxToInteger(961));
/* 44 */     (new Tuple2[249])[238] = new Tuple2("sigmaf", BoxesRunTime.boxToInteger(962));
/* 44 */     (new Tuple2[249])[239] = new Tuple2("sigma", BoxesRunTime.boxToInteger(963));
/* 44 */     (new Tuple2[249])[240] = new Tuple2("tau", BoxesRunTime.boxToInteger(964));
/* 44 */     (new Tuple2[249])[241] = new Tuple2("upsilon", BoxesRunTime.boxToInteger(965));
/* 44 */     (new Tuple2[249])[242] = new Tuple2("phi", BoxesRunTime.boxToInteger(966));
/* 44 */     (new Tuple2[249])[243] = new Tuple2("chi", BoxesRunTime.boxToInteger(967));
/* 45 */     (new Tuple2[249])[244] = new Tuple2("psi", BoxesRunTime.boxToInteger(968));
/* 45 */     (new Tuple2[249])[245] = new Tuple2("omega", BoxesRunTime.boxToInteger(969));
/* 45 */     (new Tuple2[249])[246] = new Tuple2("thetasym", BoxesRunTime.boxToInteger(977));
/* 45 */     (new Tuple2[249])[247] = new Tuple2("upsih", BoxesRunTime.boxToInteger(978));
/* 45 */     (new Tuple2[249])[248] = new Tuple2("piv", BoxesRunTime.boxToInteger(982));
/*    */     this.entList = scala.collection.immutable.List$.MODULE$.apply((Seq)scala.Predef$.MODULE$.wrapRefArray((Object[])new Tuple2[249]));
/* 47 */     this.entMap = scala.Predef$.MODULE$.Map().empty().$plus$plus((GenTraversableOnce)entList().map((Function1)new XhtmlEntities.$anonfun$1(), scala.collection.immutable.List$.MODULE$.canBuildFrom()));
/* 49 */     this.entities = (List<Tuple2<String, ParsedEntityDecl>>)entList()
/* 50 */       .map((Function1)new XhtmlEntities.$anonfun$2(), scala.collection.immutable.List$.MODULE$.canBuildFrom());
/*    */   }
/*    */   
/*    */   public List<Tuple2<String, Object>> entList() {
/*    */     return this.entList;
/*    */   }
/*    */   
/*    */   public Map<String, Object> entMap() {
/*    */     return this.entMap;
/*    */   }
/*    */   
/*    */   public static class $anonfun$1 extends AbstractFunction1<Tuple2<String, Object>, Tuple2<String, Object>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Tuple2<String, Object> apply(Tuple2 x0$2) {
/*    */       if (x0$2 != null)
/*    */         return new Tuple2(x0$2._1(), BoxesRunTime.boxToCharacter((char)x0$2._2$mcI$sp())); 
/*    */       throw new MatchError(x0$2);
/*    */     }
/*    */   }
/*    */   
/*    */   public List<Tuple2<String, ParsedEntityDecl>> entities() {
/*    */     return this.entities;
/*    */   }
/*    */   
/*    */   public static class $anonfun$2 extends AbstractFunction1<Tuple2<String, Object>, Tuple2<String, ParsedEntityDecl>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Tuple2<String, ParsedEntityDecl> apply(Tuple2 x0$1) {
/* 50 */       if (x0$1 != null)
/* 50 */         return new Tuple2(x0$1._1(), new ParsedEntityDecl((String)x0$1._1(), (EntityDef)new IntDef(BoxesRunTime.boxToCharacter((char)x0$1._2$mcI$sp()).toString()))); 
/* 50 */       throw new MatchError(x0$1);
/*    */     }
/*    */   }
/*    */   
/*    */   public List<Tuple2<String, ParsedEntityDecl>> apply() {
/* 52 */     return entities();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\parsing\XhtmlEntities$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */