package org.hsqldb;

import org.hsqldb.error.Error;
import org.hsqldb.lib.IntValueHashMap;
import org.hsqldb.lib.OrderedIntHashSet;
import org.hsqldb.map.ValuePool;
import org.hsqldb.types.BinaryData;
import org.hsqldb.types.BinaryType;
import org.hsqldb.types.CharacterType;
import org.hsqldb.types.DTIType;
import org.hsqldb.types.DateTimeType;
import org.hsqldb.types.NumberType;
import org.hsqldb.types.Type;

public class FunctionSQL extends Expression {
  protected static final int FUNC_POSITION_CHAR = 1;
  
  private static final int FUNC_POSITION_BINARY = 2;
  
  private static final int FUNC_OCCURENCES_REGEX = 3;
  
  private static final int FUNC_POSITION_REGEX = 4;
  
  protected static final int FUNC_EXTRACT = 5;
  
  protected static final int FUNC_BIT_LENGTH = 6;
  
  protected static final int FUNC_CHAR_LENGTH = 7;
  
  protected static final int FUNC_OCTET_LENGTH = 8;
  
  private static final int FUNC_CARDINALITY = 9;
  
  private static final int FUNC_MAX_CARDINALITY = 10;
  
  private static final int FUNC_TRIM_ARRAY = 11;
  
  private static final int FUNC_ABS = 12;
  
  private static final int FUNC_MOD = 13;
  
  protected static final int FUNC_LN = 14;
  
  private static final int FUNC_EXP = 15;
  
  private static final int FUNC_POWER = 16;
  
  private static final int FUNC_SQRT = 17;
  
  private static final int FUNC_FLOOR = 20;
  
  private static final int FUNC_CEILING = 21;
  
  private static final int FUNC_WIDTH_BUCKET = 22;
  
  protected static final int FUNC_SUBSTRING_CHAR = 23;
  
  private static final int FUNC_SUBSTRING_REG_EXPR = 24;
  
  private static final int FUNC_SUBSTRING_REGEX = 25;
  
  protected static final int FUNC_FOLD_LOWER = 26;
  
  protected static final int FUNC_FOLD_UPPER = 27;
  
  private static final int FUNC_TRANSCODING = 28;
  
  private static final int FUNC_TRANSLITERATION = 29;
  
  private static final int FUNC_REGEX_TRANSLITERATION = 30;
  
  protected static final int FUNC_TRIM_CHAR = 31;
  
  static final int FUNC_OVERLAY_CHAR = 32;
  
  private static final int FUNC_CHAR_NORMALIZE = 33;
  
  private static final int FUNC_SUBSTRING_BINARY = 40;
  
  private static final int FUNC_TRIM_BINARY = 41;
  
  private static final int FUNC_OVERLAY_BINARY = 42;
  
  protected static final int FUNC_CURRENT_DATE = 43;
  
  protected static final int FUNC_CURRENT_TIME = 44;
  
  protected static final int FUNC_CURRENT_TIMESTAMP = 50;
  
  protected static final int FUNC_LOCALTIME = 51;
  
  protected static final int FUNC_LOCALTIMESTAMP = 52;
  
  private static final int FUNC_CURRENT_CATALOG = 53;
  
  private static final int FUNC_CURRENT_DEFAULT_TRANSFORM_GROUP = 54;
  
  private static final int FUNC_CURRENT_PATH = 55;
  
  private static final int FUNC_CURRENT_ROLE = 56;
  
  private static final int FUNC_CURRENT_SCHEMA = 57;
  
  private static final int FUNC_CURRENT_TRANSFORM_GROUP_FOR_TYPE = 58;
  
  private static final int FUNC_CURRENT_USER = 59;
  
  private static final int FUNC_SESSION_USER = 60;
  
  private static final int FUNC_SYSTEM_USER = 61;
  
  protected static final int FUNC_USER = 62;
  
  private static final int FUNC_VALUE = 63;
  
  static final short[] noParamList = new short[0];
  
  static final short[] emptyParamList = new short[] { 816, 802 };
  
  static final short[] optionalNoParamList = new short[] { 864, 2, 816, 802 };
  
  static final short[] optionalSingleParamList = new short[] { 816, 864, 1, 818, 802 };
  
  static final short[] singleParamList = new short[] { 816, 818, 802 };
  
  static final short[] optionalIntegerParamList = new short[] { 864, 3, 816, 866, 802 };
  
  static final short[] optionalDoubleParamList = new short[] { 816, 818, 864, 2, 804, 818, 802 };
  
  static final short[] doubleParamList = new short[] { 816, 818, 804, 818, 802 };
  
  static final short[] tripleParamList = new short[] { 816, 818, 804, 818, 804, 818, 802 };
  
  static final short[] quadParamList = new short[] { 816, 818, 804, 818, 804, 818, 804, 818, 802 };
  
  static IntValueHashMap valueFuncMap = new IntValueHashMap();
  
  static IntValueHashMap regularFuncMap = new IntValueHashMap();
  
  static OrderedIntHashSet nonDeterministicFuncSet = new OrderedIntHashSet();
  
  int funcType;
  
  boolean isDeterministic;
  
  String name;
  
  short[] parseList;
  
  short[] parseListAlt;
  
  boolean isSQLValueFunction;
  
  public static FunctionSQL newSQLFunction(String paramString, ParserDQL.CompileContext paramCompileContext) {
    int i = regularFuncMap.get(paramString, -1);
    boolean bool = false;
    if (i == -1) {
      i = valueFuncMap.get(paramString, -1);
      bool = true;
    } 
    if (i == -1)
      return null; 
    FunctionSQL functionSQL = new FunctionSQL(i);
    if (i == 63) {
      if (paramCompileContext.currentDomain == null)
        return null; 
      functionSQL.dataType = paramCompileContext.currentDomain;
    } else {
      functionSQL.isSQLValueFunction = bool;
    } 
    return functionSQL;
  }
  
  protected FunctionSQL() {
    super(28);
  }
  
  protected FunctionSQL(int paramInt) {
    this();
    this.funcType = paramInt;
    this.isDeterministic = !nonDeterministicFuncSet.contains(paramInt);
    switch (paramInt) {
      case 1:
      case 2:
        this.name = "POSITION";
        this.parseList = new short[] { 
            816, 818, 130, 818, 864, 5, 306, 863, 2, 355, 
            454, 802 };
      case 3:
      case 4:
        return;
      case 5:
        this.name = "EXTRACT";
        this.parseList = new short[] { 
            816, 863, 17, 323, 173, 73, 127, 169, 250, 673, 
            791, 722, 674, 672, 791, 671, 708, 735, 283, 284, 
            115, 818, 802 };
      case 7:
        this.name = "CHAR_LENGTH";
        this.parseList = new short[] { 816, 818, 864, 5, 306, 863, 2, 355, 454, 802 };
      case 6:
        this.name = "BIT_LENGTH";
        this.parseList = singleParamList;
      case 8:
        this.name = "OCTET_LENGTH";
        this.parseList = singleParamList;
      case 9:
        this.name = "CARDINALITY";
        this.parseList = singleParamList;
      case 10:
        this.name = "MAX_CARDINALITY";
        this.parseList = singleParamList;
      case 11:
        this.name = "TRIM_ARRAY";
        this.parseList = doubleParamList;
      case 12:
        this.name = "ABS";
        this.parseList = singleParamList;
      case 13:
        this.name = "MOD";
        this.parseList = doubleParamList;
      case 14:
        this.name = "LN";
        this.parseList = singleParamList;
      case 15:
        this.name = "EXP";
        this.parseList = singleParamList;
      case 16:
        this.name = "POWER";
        this.parseList = doubleParamList;
      case 17:
        this.name = "SQRT";
        this.parseList = singleParamList;
      case 20:
        this.name = "FLOOR";
        this.parseList = singleParamList;
      case 21:
        this.name = "CEILING";
        this.parseList = singleParamList;
      case 22:
        this.name = "WIDTH_BUCKET";
        this.parseList = quadParamList;
      case 23:
      case 40:
        this.name = "SUBSTRING";
        this.parseList = new short[] { 
            816, 818, 115, 818, 864, 2, 112, 818, 864, 5, 
            306, 863, 2, 355, 454, 802 };
        this.parseListAlt = new short[] { 816, 818, 804, 818, 864, 2, 804, 818, 802 };
      case 26:
        this.name = "LOWER";
        this.parseList = singleParamList;
      case 27:
        this.name = "UPPER";
        this.parseList = singleParamList;
      case 31:
      case 41:
        this.name = "TRIM";
        this.parseList = new short[] { 
            816, 864, 11, 864, 5, 863, 3, 151, 286, 23, 
            864, 1, 818, 115, 818, 802 };
      case 32:
      case 42:
        this.name = "OVERLAY";
        this.parseList = new short[] { 
            816, 818, 473, 818, 115, 818, 864, 2, 112, 818, 
            864, 2, 306, 355, 802 };
      case 53:
        this.name = "CURRENT_CATALOG";
        this.parseList = noParamList;
      case 56:
        this.name = "CURRENT_ROLE";
        this.parseList = noParamList;
      case 57:
        this.name = "CURRENT_SCHEMA";
        this.parseList = noParamList;
      case 59:
        this.name = "CURRENT_USER";
        this.parseList = noParamList;
      case 60:
        this.name = "SESSION_USER";
        this.parseList = noParamList;
      case 61:
        this.name = "SYSTEM_USER";
        this.parseList = noParamList;
      case 62:
        this.name = "USER";
        this.parseList = optionalNoParamList;
      case 63:
        this.name = "VALUE";
        this.parseList = noParamList;
      case 43:
        this.name = "CURRENT_DATE";
        this.parseList = noParamList;
      case 44:
        this.name = "CURRENT_TIME";
        this.parseList = optionalIntegerParamList;
      case 50:
        this.name = "CURRENT_TIMESTAMP";
        this.parseList = optionalIntegerParamList;
      case 51:
        this.name = "LOCALTIME";
        this.parseList = optionalIntegerParamList;
      case 52:
        this.name = "LOCALTIMESTAMP";
        this.parseList = optionalIntegerParamList;
    } 
    throw Error.runtimeError(201, "FunctionSQL");
  }
  
  public void setArguments(Expression[] paramArrayOfExpression) {
    this.nodes = paramArrayOfExpression;
  }
  
  public Expression getFunctionExpression() {
    return this;
  }
  
  public Object getValue(Session paramSession) {
    Object[] arrayOfObject = new Object[this.nodes.length];
    for (byte b = 0; b < this.nodes.length; b++) {
      Expression expression = this.nodes[b];
      if (expression != null)
        arrayOfObject[b] = expression.getValue(paramSession, expression.dataType); 
    } 
    return getValue(paramSession, arrayOfObject);
  }
  
  Object getValue(Session paramSession, Object[] paramArrayOfObject) {
    // Byte code:
    //   0: aload_0
    //   1: getfield funcType : I
    //   4: tableswitch default -> 2882, 1 -> 272, 2 -> 417, 3 -> 2882, 4 -> 2882, 5 -> 513, 6 -> 677, 7 -> 644, 8 -> 745, 9 -> 813, 10 -> 843, 11 -> 869, 12 -> 949, 13 -> 968, 14 -> 1033, 15 -> 1077, 16 -> 1106, 17 -> 1197, 18 -> 2882, 19 -> 2882, 20 -> 1226, 21 -> 1248, 22 -> 1270, 23 -> 1769, 24 -> 2882, 25 -> 2882, 26 -> 1936, 27 -> 1959, 28 -> 2882, 29 -> 2882, 30 -> 2882, 31 -> 1982, 32 -> 2132, 33 -> 2882, 34 -> 2882, 35 -> 2882, 36 -> 2882, 37 -> 2882, 38 -> 2882, 39 -> 2882, 40 -> 2268, 41 -> 2399, 42 -> 2562, 43 -> 2797, 44 -> 2826, 45 -> 2882, 46 -> 2882, 47 -> 2882, 48 -> 2882, 49 -> 2882, 50 -> 2840, 51 -> 2854, 52 -> 2868, 53 -> 2704, 54 -> 2882, 55 -> 2882, 56 -> 2715, 57 -> 2737, 58 -> 2882, 59 -> 2745, 60 -> 2756, 61 -> 2767, 62 -> 2778, 63 -> 2789
    //   272: aload_2
    //   273: iconst_0
    //   274: aaload
    //   275: ifnull -> 284
    //   278: aload_2
    //   279: iconst_1
    //   280: aaload
    //   281: ifnonnull -> 286
    //   284: aconst_null
    //   285: areturn
    //   286: lconst_0
    //   287: lstore_3
    //   288: aload_0
    //   289: getfield nodes : [Lorg/hsqldb/Expression;
    //   292: arraylength
    //   293: iconst_3
    //   294: if_icmple -> 337
    //   297: aload_0
    //   298: getfield nodes : [Lorg/hsqldb/Expression;
    //   301: iconst_3
    //   302: aaload
    //   303: ifnull -> 337
    //   306: aload_0
    //   307: getfield nodes : [Lorg/hsqldb/Expression;
    //   310: iconst_3
    //   311: aaload
    //   312: aload_1
    //   313: invokevirtual getValue : (Lorg/hsqldb/Session;)Ljava/lang/Object;
    //   316: astore #5
    //   318: aload #5
    //   320: checkcast java/lang/Number
    //   323: invokevirtual longValue : ()J
    //   326: lconst_1
    //   327: lsub
    //   328: lstore_3
    //   329: lload_3
    //   330: lconst_0
    //   331: lcmp
    //   332: ifge -> 337
    //   335: lconst_0
    //   336: lstore_3
    //   337: aload_0
    //   338: getfield nodes : [Lorg/hsqldb/Expression;
    //   341: iconst_1
    //   342: aaload
    //   343: getfield dataType : Lorg/hsqldb/types/Type;
    //   346: checkcast org/hsqldb/types/CharacterType
    //   349: aload_1
    //   350: aload_2
    //   351: iconst_1
    //   352: aaload
    //   353: aload_2
    //   354: iconst_0
    //   355: aaload
    //   356: aload_0
    //   357: getfield nodes : [Lorg/hsqldb/Expression;
    //   360: iconst_0
    //   361: aaload
    //   362: getfield dataType : Lorg/hsqldb/types/Type;
    //   365: lload_3
    //   366: invokevirtual position : (Lorg/hsqldb/SessionInterface;Ljava/lang/Object;Ljava/lang/Object;Lorg/hsqldb/types/Type;J)J
    //   369: lconst_1
    //   370: ladd
    //   371: lstore #5
    //   373: aload_0
    //   374: getfield nodes : [Lorg/hsqldb/Expression;
    //   377: iconst_2
    //   378: aaload
    //   379: ifnull -> 411
    //   382: aload_0
    //   383: getfield nodes : [Lorg/hsqldb/Expression;
    //   386: iconst_2
    //   387: aaload
    //   388: getfield valueData : Ljava/lang/Object;
    //   391: checkcast java/lang/Number
    //   394: invokevirtual intValue : ()I
    //   397: sipush #454
    //   400: if_icmpne -> 411
    //   403: lload #5
    //   405: ldc2_w 2
    //   408: lmul
    //   409: lstore #5
    //   411: lload #5
    //   413: invokestatic getLong : (J)Ljava/lang/Long;
    //   416: areturn
    //   417: aload_2
    //   418: iconst_0
    //   419: aaload
    //   420: ifnull -> 429
    //   423: aload_2
    //   424: iconst_1
    //   425: aaload
    //   426: ifnonnull -> 431
    //   429: aconst_null
    //   430: areturn
    //   431: aload_0
    //   432: getfield nodes : [Lorg/hsqldb/Expression;
    //   435: iconst_1
    //   436: aaload
    //   437: getfield dataType : Lorg/hsqldb/types/Type;
    //   440: checkcast org/hsqldb/types/BinaryType
    //   443: aload_1
    //   444: aload_2
    //   445: iconst_1
    //   446: aaload
    //   447: checkcast org/hsqldb/types/BlobData
    //   450: aload_2
    //   451: iconst_0
    //   452: aaload
    //   453: checkcast org/hsqldb/types/BlobData
    //   456: aload_0
    //   457: getfield nodes : [Lorg/hsqldb/Expression;
    //   460: iconst_0
    //   461: aaload
    //   462: getfield dataType : Lorg/hsqldb/types/Type;
    //   465: lconst_0
    //   466: invokevirtual position : (Lorg/hsqldb/SessionInterface;Lorg/hsqldb/types/BlobData;Lorg/hsqldb/types/BlobData;Lorg/hsqldb/types/Type;J)J
    //   469: lconst_1
    //   470: ladd
    //   471: lstore_3
    //   472: aload_0
    //   473: getfield nodes : [Lorg/hsqldb/Expression;
    //   476: iconst_2
    //   477: aaload
    //   478: ifnull -> 508
    //   481: aload_0
    //   482: getfield nodes : [Lorg/hsqldb/Expression;
    //   485: iconst_2
    //   486: aaload
    //   487: getfield valueData : Ljava/lang/Object;
    //   490: checkcast java/lang/Number
    //   493: invokevirtual intValue : ()I
    //   496: sipush #454
    //   499: if_icmpne -> 508
    //   502: lload_3
    //   503: ldc2_w 2
    //   506: lmul
    //   507: lstore_3
    //   508: lload_3
    //   509: invokestatic getLong : (J)Ljava/lang/Long;
    //   512: areturn
    //   513: aload_2
    //   514: iconst_1
    //   515: aaload
    //   516: ifnonnull -> 521
    //   519: aconst_null
    //   520: areturn
    //   521: aload_0
    //   522: getfield nodes : [Lorg/hsqldb/Expression;
    //   525: iconst_0
    //   526: aaload
    //   527: getfield valueData : Ljava/lang/Object;
    //   530: checkcast java/lang/Number
    //   533: invokevirtual intValue : ()I
    //   536: istore_3
    //   537: iload_3
    //   538: invokestatic getFieldNameTypeForToken : (I)I
    //   541: istore_3
    //   542: iload_3
    //   543: lookupswitch default -> 616, 106 -> 576, 264 -> 595, 265 -> 595
    //   576: aload_0
    //   577: getfield nodes : [Lorg/hsqldb/Expression;
    //   580: iconst_1
    //   581: aaload
    //   582: getfield dataType : Lorg/hsqldb/types/Type;
    //   585: checkcast org/hsqldb/types/DTIType
    //   588: aload_2
    //   589: iconst_1
    //   590: aaload
    //   591: invokevirtual getSecondPart : (Ljava/lang/Object;)Ljava/math/BigDecimal;
    //   594: areturn
    //   595: aload_0
    //   596: getfield nodes : [Lorg/hsqldb/Expression;
    //   599: iconst_1
    //   600: aaload
    //   601: getfield dataType : Lorg/hsqldb/types/Type;
    //   604: checkcast org/hsqldb/types/DateTimeType
    //   607: aload_1
    //   608: aload_2
    //   609: iconst_1
    //   610: aaload
    //   611: iload_3
    //   612: invokevirtual getPartString : (Lorg/hsqldb/Session;Ljava/lang/Object;I)Ljava/lang/String;
    //   615: areturn
    //   616: aload_0
    //   617: getfield nodes : [Lorg/hsqldb/Expression;
    //   620: iconst_1
    //   621: aaload
    //   622: getfield dataType : Lorg/hsqldb/types/Type;
    //   625: checkcast org/hsqldb/types/DTIType
    //   628: aload_1
    //   629: aload_2
    //   630: iconst_1
    //   631: aaload
    //   632: iload_3
    //   633: invokevirtual getPart : (Lorg/hsqldb/Session;Ljava/lang/Object;I)I
    //   636: istore #4
    //   638: iload #4
    //   640: invokestatic getInt : (I)Ljava/lang/Integer;
    //   643: areturn
    //   644: aload_2
    //   645: iconst_0
    //   646: aaload
    //   647: ifnonnull -> 652
    //   650: aconst_null
    //   651: areturn
    //   652: aload_0
    //   653: getfield nodes : [Lorg/hsqldb/Expression;
    //   656: iconst_0
    //   657: aaload
    //   658: getfield dataType : Lorg/hsqldb/types/Type;
    //   661: checkcast org/hsqldb/types/CharacterType
    //   664: aload_1
    //   665: aload_2
    //   666: iconst_0
    //   667: aaload
    //   668: invokevirtual size : (Lorg/hsqldb/SessionInterface;Ljava/lang/Object;)J
    //   671: lstore_3
    //   672: lload_3
    //   673: invokestatic getLong : (J)Ljava/lang/Long;
    //   676: areturn
    //   677: aload_2
    //   678: iconst_0
    //   679: aaload
    //   680: ifnonnull -> 685
    //   683: aconst_null
    //   684: areturn
    //   685: aload_0
    //   686: getfield nodes : [Lorg/hsqldb/Expression;
    //   689: iconst_0
    //   690: aaload
    //   691: getfield dataType : Lorg/hsqldb/types/Type;
    //   694: invokevirtual isBinaryType : ()Z
    //   697: ifeq -> 716
    //   700: aload_2
    //   701: iconst_0
    //   702: aaload
    //   703: checkcast org/hsqldb/types/BlobData
    //   706: aload_1
    //   707: invokeinterface bitLength : (Lorg/hsqldb/SessionInterface;)J
    //   712: lstore_3
    //   713: goto -> 740
    //   716: ldc2_w 16
    //   719: aload_0
    //   720: getfield nodes : [Lorg/hsqldb/Expression;
    //   723: iconst_0
    //   724: aaload
    //   725: getfield dataType : Lorg/hsqldb/types/Type;
    //   728: checkcast org/hsqldb/types/CharacterType
    //   731: aload_1
    //   732: aload_2
    //   733: iconst_0
    //   734: aaload
    //   735: invokevirtual size : (Lorg/hsqldb/SessionInterface;Ljava/lang/Object;)J
    //   738: lmul
    //   739: lstore_3
    //   740: lload_3
    //   741: invokestatic getLong : (J)Ljava/lang/Long;
    //   744: areturn
    //   745: aload_2
    //   746: iconst_0
    //   747: aaload
    //   748: ifnonnull -> 753
    //   751: aconst_null
    //   752: areturn
    //   753: aload_0
    //   754: getfield nodes : [Lorg/hsqldb/Expression;
    //   757: iconst_0
    //   758: aaload
    //   759: getfield dataType : Lorg/hsqldb/types/Type;
    //   762: invokevirtual isBinaryType : ()Z
    //   765: ifeq -> 784
    //   768: aload_2
    //   769: iconst_0
    //   770: aaload
    //   771: checkcast org/hsqldb/types/BlobData
    //   774: aload_1
    //   775: invokeinterface length : (Lorg/hsqldb/SessionInterface;)J
    //   780: lstore_3
    //   781: goto -> 808
    //   784: ldc2_w 2
    //   787: aload_0
    //   788: getfield nodes : [Lorg/hsqldb/Expression;
    //   791: iconst_0
    //   792: aaload
    //   793: getfield dataType : Lorg/hsqldb/types/Type;
    //   796: checkcast org/hsqldb/types/CharacterType
    //   799: aload_1
    //   800: aload_2
    //   801: iconst_0
    //   802: aaload
    //   803: invokevirtual size : (Lorg/hsqldb/SessionInterface;Ljava/lang/Object;)J
    //   806: lmul
    //   807: lstore_3
    //   808: lload_3
    //   809: invokestatic getLong : (J)Ljava/lang/Long;
    //   812: areturn
    //   813: aload_2
    //   814: iconst_0
    //   815: aaload
    //   816: ifnonnull -> 821
    //   819: aconst_null
    //   820: areturn
    //   821: aload_0
    //   822: getfield nodes : [Lorg/hsqldb/Expression;
    //   825: iconst_0
    //   826: aaload
    //   827: getfield dataType : Lorg/hsqldb/types/Type;
    //   830: aload_1
    //   831: aload_2
    //   832: iconst_0
    //   833: aaload
    //   834: invokevirtual cardinality : (Lorg/hsqldb/Session;Ljava/lang/Object;)I
    //   837: istore_3
    //   838: iload_3
    //   839: invokestatic getInt : (I)Ljava/lang/Integer;
    //   842: areturn
    //   843: aload_2
    //   844: iconst_0
    //   845: aaload
    //   846: ifnonnull -> 851
    //   849: aconst_null
    //   850: areturn
    //   851: aload_0
    //   852: getfield nodes : [Lorg/hsqldb/Expression;
    //   855: iconst_0
    //   856: aaload
    //   857: getfield dataType : Lorg/hsqldb/types/Type;
    //   860: invokevirtual arrayLimitCardinality : ()I
    //   863: istore_3
    //   864: iload_3
    //   865: invokestatic getInt : (I)Ljava/lang/Integer;
    //   868: areturn
    //   869: aload_2
    //   870: iconst_0
    //   871: aaload
    //   872: ifnonnull -> 877
    //   875: aconst_null
    //   876: areturn
    //   877: aload_2
    //   878: iconst_1
    //   879: aaload
    //   880: ifnonnull -> 885
    //   883: aconst_null
    //   884: areturn
    //   885: aload_2
    //   886: iconst_0
    //   887: aaload
    //   888: checkcast [Ljava/lang/Object;
    //   891: checkcast [Ljava/lang/Object;
    //   894: astore_3
    //   895: aload_2
    //   896: iconst_1
    //   897: aaload
    //   898: checkcast java/lang/Number
    //   901: invokevirtual intValue : ()I
    //   904: istore #4
    //   906: iload #4
    //   908: iflt -> 918
    //   911: iload #4
    //   913: aload_3
    //   914: arraylength
    //   915: if_icmple -> 925
    //   918: sipush #3490
    //   921: invokestatic error : (I)Lorg/hsqldb/HsqlException;
    //   924: athrow
    //   925: aload_3
    //   926: arraylength
    //   927: iload #4
    //   929: isub
    //   930: anewarray java/lang/Object
    //   933: astore #5
    //   935: aload_3
    //   936: iconst_0
    //   937: aload #5
    //   939: iconst_0
    //   940: aload #5
    //   942: arraylength
    //   943: invokestatic arraycopy : (Ljava/lang/Object;ILjava/lang/Object;II)V
    //   946: aload #5
    //   948: areturn
    //   949: aload_2
    //   950: iconst_0
    //   951: aaload
    //   952: ifnonnull -> 957
    //   955: aconst_null
    //   956: areturn
    //   957: aload_0
    //   958: getfield dataType : Lorg/hsqldb/types/Type;
    //   961: aload_2
    //   962: iconst_0
    //   963: aaload
    //   964: invokevirtual absolute : (Ljava/lang/Object;)Ljava/lang/Object;
    //   967: areturn
    //   968: aload_2
    //   969: iconst_0
    //   970: aaload
    //   971: ifnull -> 980
    //   974: aload_2
    //   975: iconst_1
    //   976: aaload
    //   977: ifnonnull -> 982
    //   980: aconst_null
    //   981: areturn
    //   982: aload_0
    //   983: getfield nodes : [Lorg/hsqldb/Expression;
    //   986: iconst_0
    //   987: aaload
    //   988: getfield dataType : Lorg/hsqldb/types/Type;
    //   991: checkcast org/hsqldb/types/NumberType
    //   994: aload_1
    //   995: aload_2
    //   996: iconst_0
    //   997: aaload
    //   998: aload_2
    //   999: iconst_1
    //   1000: aaload
    //   1001: aload_0
    //   1002: getfield nodes : [Lorg/hsqldb/Expression;
    //   1005: iconst_0
    //   1006: aaload
    //   1007: getfield dataType : Lorg/hsqldb/types/Type;
    //   1010: invokevirtual modulo : (Lorg/hsqldb/Session;Ljava/lang/Object;Ljava/lang/Object;Lorg/hsqldb/types/Type;)Ljava/lang/Object;
    //   1013: astore_3
    //   1014: aload_0
    //   1015: getfield dataType : Lorg/hsqldb/types/Type;
    //   1018: aload_1
    //   1019: aload_3
    //   1020: aload_0
    //   1021: getfield nodes : [Lorg/hsqldb/Expression;
    //   1024: iconst_0
    //   1025: aaload
    //   1026: getfield dataType : Lorg/hsqldb/types/Type;
    //   1029: invokevirtual convertToType : (Lorg/hsqldb/SessionInterface;Ljava/lang/Object;Lorg/hsqldb/types/Type;)Ljava/lang/Object;
    //   1032: areturn
    //   1033: aload_2
    //   1034: iconst_0
    //   1035: aaload
    //   1036: ifnonnull -> 1041
    //   1039: aconst_null
    //   1040: areturn
    //   1041: aload_2
    //   1042: iconst_0
    //   1043: aaload
    //   1044: checkcast java/lang/Number
    //   1047: invokevirtual doubleValue : ()D
    //   1050: dstore_3
    //   1051: dload_3
    //   1052: dconst_0
    //   1053: dcmpg
    //   1054: ifgt -> 1064
    //   1057: sipush #3444
    //   1060: invokestatic error : (I)Lorg/hsqldb/HsqlException;
    //   1063: athrow
    //   1064: dload_3
    //   1065: invokestatic log : (D)D
    //   1068: dstore_3
    //   1069: dload_3
    //   1070: invokestatic doubleToLongBits : (D)J
    //   1073: invokestatic getDouble : (J)Ljava/lang/Double;
    //   1076: areturn
    //   1077: aload_2
    //   1078: iconst_0
    //   1079: aaload
    //   1080: ifnonnull -> 1085
    //   1083: aconst_null
    //   1084: areturn
    //   1085: aload_2
    //   1086: iconst_0
    //   1087: aaload
    //   1088: checkcast java/lang/Number
    //   1091: invokevirtual doubleValue : ()D
    //   1094: invokestatic exp : (D)D
    //   1097: dstore_3
    //   1098: dload_3
    //   1099: invokestatic doubleToLongBits : (D)J
    //   1102: invokestatic getDouble : (J)Ljava/lang/Double;
    //   1105: areturn
    //   1106: aload_2
    //   1107: iconst_0
    //   1108: aaload
    //   1109: ifnull -> 1118
    //   1112: aload_2
    //   1113: iconst_1
    //   1114: aaload
    //   1115: ifnonnull -> 1120
    //   1118: aconst_null
    //   1119: areturn
    //   1120: aload_2
    //   1121: iconst_0
    //   1122: aaload
    //   1123: checkcast java/lang/Number
    //   1126: invokevirtual doubleValue : ()D
    //   1129: dstore_3
    //   1130: aload_2
    //   1131: iconst_1
    //   1132: aaload
    //   1133: checkcast java/lang/Number
    //   1136: invokevirtual doubleValue : ()D
    //   1139: dstore #5
    //   1141: dload_3
    //   1142: dconst_0
    //   1143: dcmpl
    //   1144: ifne -> 1180
    //   1147: dload #5
    //   1149: dconst_0
    //   1150: dcmpg
    //   1151: ifge -> 1161
    //   1154: sipush #3445
    //   1157: invokestatic error : (I)Lorg/hsqldb/HsqlException;
    //   1160: athrow
    //   1161: dload #5
    //   1163: dconst_0
    //   1164: dcmpl
    //   1165: ifne -> 1174
    //   1168: dconst_1
    //   1169: dstore #7
    //   1171: goto -> 1188
    //   1174: dconst_0
    //   1175: dstore #7
    //   1177: goto -> 1188
    //   1180: dload_3
    //   1181: dload #5
    //   1183: invokestatic pow : (DD)D
    //   1186: dstore #7
    //   1188: dload #7
    //   1190: invokestatic doubleToLongBits : (D)J
    //   1193: invokestatic getDouble : (J)Ljava/lang/Double;
    //   1196: areturn
    //   1197: aload_2
    //   1198: iconst_0
    //   1199: aaload
    //   1200: ifnonnull -> 1205
    //   1203: aconst_null
    //   1204: areturn
    //   1205: aload_2
    //   1206: iconst_0
    //   1207: aaload
    //   1208: checkcast java/lang/Number
    //   1211: invokevirtual doubleValue : ()D
    //   1214: invokestatic sqrt : (D)D
    //   1217: dstore_3
    //   1218: dload_3
    //   1219: invokestatic doubleToLongBits : (D)J
    //   1222: invokestatic getDouble : (J)Ljava/lang/Double;
    //   1225: areturn
    //   1226: aload_2
    //   1227: iconst_0
    //   1228: aaload
    //   1229: ifnonnull -> 1234
    //   1232: aconst_null
    //   1233: areturn
    //   1234: aload_0
    //   1235: getfield dataType : Lorg/hsqldb/types/Type;
    //   1238: checkcast org/hsqldb/types/NumberType
    //   1241: aload_2
    //   1242: iconst_0
    //   1243: aaload
    //   1244: invokevirtual floor : (Ljava/lang/Object;)Ljava/lang/Object;
    //   1247: areturn
    //   1248: aload_2
    //   1249: iconst_0
    //   1250: aaload
    //   1251: ifnonnull -> 1256
    //   1254: aconst_null
    //   1255: areturn
    //   1256: aload_0
    //   1257: getfield dataType : Lorg/hsqldb/types/Type;
    //   1260: checkcast org/hsqldb/types/NumberType
    //   1263: aload_2
    //   1264: iconst_0
    //   1265: aaload
    //   1266: invokevirtual ceiling : (Ljava/lang/Object;)Ljava/lang/Object;
    //   1269: areturn
    //   1270: iconst_0
    //   1271: istore_3
    //   1272: iload_3
    //   1273: aload_2
    //   1274: arraylength
    //   1275: if_icmpge -> 1292
    //   1278: aload_2
    //   1279: iload_3
    //   1280: aaload
    //   1281: ifnonnull -> 1286
    //   1284: aconst_null
    //   1285: areturn
    //   1286: iinc #3, 1
    //   1289: goto -> 1272
    //   1292: aload_0
    //   1293: getfield nodes : [Lorg/hsqldb/Expression;
    //   1296: iconst_3
    //   1297: aaload
    //   1298: getfield dataType : Lorg/hsqldb/types/Type;
    //   1301: checkcast org/hsqldb/types/NumberType
    //   1304: aload_2
    //   1305: iconst_3
    //   1306: aaload
    //   1307: invokevirtual isNegative : (Ljava/lang/Object;)Z
    //   1310: ifeq -> 1320
    //   1313: sipush #3446
    //   1316: invokestatic error : (I)Lorg/hsqldb/HsqlException;
    //   1319: athrow
    //   1320: aload_0
    //   1321: getfield nodes : [Lorg/hsqldb/Expression;
    //   1324: iconst_1
    //   1325: aaload
    //   1326: getfield dataType : Lorg/hsqldb/types/Type;
    //   1329: aload_1
    //   1330: aload_2
    //   1331: iconst_1
    //   1332: aaload
    //   1333: aload_2
    //   1334: iconst_2
    //   1335: aaload
    //   1336: invokevirtual compare : (Lorg/hsqldb/Session;Ljava/lang/Object;Ljava/lang/Object;)I
    //   1339: istore_3
    //   1340: aload_0
    //   1341: getfield nodes : [Lorg/hsqldb/Expression;
    //   1344: iconst_0
    //   1345: aaload
    //   1346: getfield dataType : Lorg/hsqldb/types/Type;
    //   1349: invokevirtual isNumberType : ()Z
    //   1352: ifeq -> 1369
    //   1355: aload_0
    //   1356: getfield nodes : [Lorg/hsqldb/Expression;
    //   1359: iconst_0
    //   1360: aaload
    //   1361: getfield dataType : Lorg/hsqldb/types/Type;
    //   1364: astore #4
    //   1366: goto -> 1395
    //   1369: aload_0
    //   1370: getfield nodes : [Lorg/hsqldb/Expression;
    //   1373: iconst_0
    //   1374: aaload
    //   1375: getfield dataType : Lorg/hsqldb/types/Type;
    //   1378: aload_1
    //   1379: aload_0
    //   1380: getfield nodes : [Lorg/hsqldb/Expression;
    //   1383: iconst_0
    //   1384: aaload
    //   1385: getfield dataType : Lorg/hsqldb/types/Type;
    //   1388: bipush #33
    //   1390: invokevirtual getCombinedType : (Lorg/hsqldb/Session;Lorg/hsqldb/types/Type;I)Lorg/hsqldb/types/Type;
    //   1393: astore #4
    //   1395: iload_3
    //   1396: tableswitch default -> 1661, -1 -> 1431, 0 -> 1424, 1 -> 1546
    //   1424: sipush #3446
    //   1427: invokestatic error : (I)Lorg/hsqldb/HsqlException;
    //   1430: athrow
    //   1431: aload_0
    //   1432: getfield nodes : [Lorg/hsqldb/Expression;
    //   1435: iconst_0
    //   1436: aaload
    //   1437: getfield dataType : Lorg/hsqldb/types/Type;
    //   1440: aload_1
    //   1441: aload_2
    //   1442: iconst_0
    //   1443: aaload
    //   1444: aload_2
    //   1445: iconst_1
    //   1446: aaload
    //   1447: invokevirtual compare : (Lorg/hsqldb/Session;Ljava/lang/Object;Ljava/lang/Object;)I
    //   1450: ifge -> 1457
    //   1453: getstatic org/hsqldb/map/ValuePool.INTEGER_0 : Ljava/lang/Integer;
    //   1456: areturn
    //   1457: aload_0
    //   1458: getfield nodes : [Lorg/hsqldb/Expression;
    //   1461: iconst_0
    //   1462: aaload
    //   1463: getfield dataType : Lorg/hsqldb/types/Type;
    //   1466: aload_1
    //   1467: aload_2
    //   1468: iconst_0
    //   1469: aaload
    //   1470: aload_2
    //   1471: iconst_2
    //   1472: aaload
    //   1473: invokevirtual compare : (Lorg/hsqldb/Session;Ljava/lang/Object;Ljava/lang/Object;)I
    //   1476: iflt -> 1497
    //   1479: aload_0
    //   1480: getfield dataType : Lorg/hsqldb/types/Type;
    //   1483: aload_1
    //   1484: aload_2
    //   1485: iconst_3
    //   1486: aaload
    //   1487: getstatic org/hsqldb/map/ValuePool.INTEGER_1 : Ljava/lang/Integer;
    //   1490: getstatic org/hsqldb/types/Type.SQL_INTEGER : Lorg/hsqldb/types/NumberType;
    //   1493: invokevirtual add : (Lorg/hsqldb/Session;Ljava/lang/Object;Ljava/lang/Object;Lorg/hsqldb/types/Type;)Ljava/lang/Object;
    //   1496: areturn
    //   1497: aload #4
    //   1499: aload_1
    //   1500: aload_2
    //   1501: iconst_0
    //   1502: aaload
    //   1503: aload_2
    //   1504: iconst_1
    //   1505: aaload
    //   1506: aload_0
    //   1507: getfield nodes : [Lorg/hsqldb/Expression;
    //   1510: iconst_0
    //   1511: aaload
    //   1512: getfield dataType : Lorg/hsqldb/types/Type;
    //   1515: invokevirtual subtract : (Lorg/hsqldb/Session;Ljava/lang/Object;Ljava/lang/Object;Lorg/hsqldb/types/Type;)Ljava/lang/Object;
    //   1518: astore #5
    //   1520: aload #4
    //   1522: aload_1
    //   1523: aload_2
    //   1524: iconst_2
    //   1525: aaload
    //   1526: aload_2
    //   1527: iconst_1
    //   1528: aaload
    //   1529: aload_0
    //   1530: getfield nodes : [Lorg/hsqldb/Expression;
    //   1533: iconst_0
    //   1534: aaload
    //   1535: getfield dataType : Lorg/hsqldb/types/Type;
    //   1538: invokevirtual subtract : (Lorg/hsqldb/Session;Ljava/lang/Object;Ljava/lang/Object;Lorg/hsqldb/types/Type;)Ljava/lang/Object;
    //   1541: astore #6
    //   1543: goto -> 1670
    //   1546: aload_0
    //   1547: getfield nodes : [Lorg/hsqldb/Expression;
    //   1550: iconst_0
    //   1551: aaload
    //   1552: getfield dataType : Lorg/hsqldb/types/Type;
    //   1555: aload_1
    //   1556: aload_2
    //   1557: iconst_0
    //   1558: aaload
    //   1559: aload_2
    //   1560: iconst_1
    //   1561: aaload
    //   1562: invokevirtual compare : (Lorg/hsqldb/Session;Ljava/lang/Object;Ljava/lang/Object;)I
    //   1565: ifle -> 1572
    //   1568: getstatic org/hsqldb/map/ValuePool.INTEGER_0 : Ljava/lang/Integer;
    //   1571: areturn
    //   1572: aload_0
    //   1573: getfield nodes : [Lorg/hsqldb/Expression;
    //   1576: iconst_0
    //   1577: aaload
    //   1578: getfield dataType : Lorg/hsqldb/types/Type;
    //   1581: aload_1
    //   1582: aload_2
    //   1583: iconst_0
    //   1584: aaload
    //   1585: aload_2
    //   1586: iconst_2
    //   1587: aaload
    //   1588: invokevirtual compare : (Lorg/hsqldb/Session;Ljava/lang/Object;Ljava/lang/Object;)I
    //   1591: ifgt -> 1612
    //   1594: aload_0
    //   1595: getfield dataType : Lorg/hsqldb/types/Type;
    //   1598: aload_1
    //   1599: aload_2
    //   1600: iconst_3
    //   1601: aaload
    //   1602: getstatic org/hsqldb/map/ValuePool.INTEGER_1 : Ljava/lang/Integer;
    //   1605: getstatic org/hsqldb/types/Type.SQL_INTEGER : Lorg/hsqldb/types/NumberType;
    //   1608: invokevirtual add : (Lorg/hsqldb/Session;Ljava/lang/Object;Ljava/lang/Object;Lorg/hsqldb/types/Type;)Ljava/lang/Object;
    //   1611: areturn
    //   1612: aload #4
    //   1614: aload_1
    //   1615: aload_2
    //   1616: iconst_1
    //   1617: aaload
    //   1618: aload_2
    //   1619: iconst_0
    //   1620: aaload
    //   1621: aload_0
    //   1622: getfield nodes : [Lorg/hsqldb/Expression;
    //   1625: iconst_0
    //   1626: aaload
    //   1627: getfield dataType : Lorg/hsqldb/types/Type;
    //   1630: invokevirtual subtract : (Lorg/hsqldb/Session;Ljava/lang/Object;Ljava/lang/Object;Lorg/hsqldb/types/Type;)Ljava/lang/Object;
    //   1633: astore #5
    //   1635: aload #4
    //   1637: aload_1
    //   1638: aload_2
    //   1639: iconst_1
    //   1640: aaload
    //   1641: aload_2
    //   1642: iconst_2
    //   1643: aaload
    //   1644: aload_0
    //   1645: getfield nodes : [Lorg/hsqldb/Expression;
    //   1648: iconst_0
    //   1649: aaload
    //   1650: getfield dataType : Lorg/hsqldb/types/Type;
    //   1653: invokevirtual subtract : (Lorg/hsqldb/Session;Ljava/lang/Object;Ljava/lang/Object;Lorg/hsqldb/types/Type;)Ljava/lang/Object;
    //   1656: astore #6
    //   1658: goto -> 1670
    //   1661: sipush #201
    //   1664: ldc ''
    //   1666: invokestatic runtimeError : (ILjava/lang/String;)Ljava/lang/RuntimeException;
    //   1669: athrow
    //   1670: aload #4
    //   1672: getfield typeCode : I
    //   1675: bipush #8
    //   1677: if_icmpne -> 1687
    //   1680: aload #4
    //   1682: astore #7
    //   1684: goto -> 1716
    //   1687: getstatic org/hsqldb/types/IntervalType.factorType : Lorg/hsqldb/types/NumberType;
    //   1690: astore #7
    //   1692: aload #7
    //   1694: aload_1
    //   1695: aload #5
    //   1697: aload #4
    //   1699: invokevirtual convertToType : (Lorg/hsqldb/SessionInterface;Ljava/lang/Object;Lorg/hsqldb/types/Type;)Ljava/lang/Object;
    //   1702: astore #5
    //   1704: aload #7
    //   1706: aload_1
    //   1707: aload #6
    //   1709: aload #4
    //   1711: invokevirtual convertToType : (Lorg/hsqldb/SessionInterface;Ljava/lang/Object;Lorg/hsqldb/types/Type;)Ljava/lang/Object;
    //   1714: astore #6
    //   1716: aload #7
    //   1718: aload #5
    //   1720: aload_2
    //   1721: iconst_3
    //   1722: aaload
    //   1723: invokevirtual multiply : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   1726: astore #5
    //   1728: aload #7
    //   1730: aload_1
    //   1731: aload #5
    //   1733: aload #6
    //   1735: invokevirtual divide : (Lorg/hsqldb/Session;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   1738: astore #5
    //   1740: aload_0
    //   1741: getfield dataType : Lorg/hsqldb/types/Type;
    //   1744: aload_1
    //   1745: aload #5
    //   1747: invokevirtual convertToDefaultType : (Lorg/hsqldb/SessionInterface;Ljava/lang/Object;)Ljava/lang/Object;
    //   1750: astore #5
    //   1752: aload_0
    //   1753: getfield dataType : Lorg/hsqldb/types/Type;
    //   1756: aload_1
    //   1757: aload #5
    //   1759: getstatic org/hsqldb/map/ValuePool.INTEGER_1 : Ljava/lang/Integer;
    //   1762: getstatic org/hsqldb/types/Type.SQL_INTEGER : Lorg/hsqldb/types/NumberType;
    //   1765: invokevirtual add : (Lorg/hsqldb/Session;Ljava/lang/Object;Ljava/lang/Object;Lorg/hsqldb/types/Type;)Ljava/lang/Object;
    //   1768: areturn
    //   1769: aload_2
    //   1770: iconst_0
    //   1771: aaload
    //   1772: ifnull -> 1781
    //   1775: aload_2
    //   1776: iconst_1
    //   1777: aaload
    //   1778: ifnonnull -> 1783
    //   1781: aconst_null
    //   1782: areturn
    //   1783: getstatic org/hsqldb/types/Type.SQL_BIGINT : Lorg/hsqldb/types/NumberType;
    //   1786: aload_1
    //   1787: aload_2
    //   1788: iconst_1
    //   1789: aaload
    //   1790: aload_0
    //   1791: getfield nodes : [Lorg/hsqldb/Expression;
    //   1794: iconst_1
    //   1795: aaload
    //   1796: getfield dataType : Lorg/hsqldb/types/Type;
    //   1799: invokevirtual convertToType : (Lorg/hsqldb/SessionInterface;Ljava/lang/Object;Lorg/hsqldb/types/Type;)Ljava/lang/Object;
    //   1802: astore_3
    //   1803: aload_3
    //   1804: checkcast java/lang/Number
    //   1807: invokevirtual longValue : ()J
    //   1810: lconst_1
    //   1811: lsub
    //   1812: lstore #4
    //   1814: lconst_0
    //   1815: lstore #6
    //   1817: aload_0
    //   1818: getfield nodes : [Lorg/hsqldb/Expression;
    //   1821: iconst_2
    //   1822: aaload
    //   1823: ifnull -> 1863
    //   1826: aload_2
    //   1827: iconst_2
    //   1828: aaload
    //   1829: ifnonnull -> 1834
    //   1832: aconst_null
    //   1833: areturn
    //   1834: getstatic org/hsqldb/types/Type.SQL_BIGINT : Lorg/hsqldb/types/NumberType;
    //   1837: aload_1
    //   1838: aload_2
    //   1839: iconst_2
    //   1840: aaload
    //   1841: aload_0
    //   1842: getfield nodes : [Lorg/hsqldb/Expression;
    //   1845: iconst_2
    //   1846: aaload
    //   1847: getfield dataType : Lorg/hsqldb/types/Type;
    //   1850: invokevirtual convertToType : (Lorg/hsqldb/SessionInterface;Ljava/lang/Object;Lorg/hsqldb/types/Type;)Ljava/lang/Object;
    //   1853: astore_3
    //   1854: aload_3
    //   1855: checkcast java/lang/Number
    //   1858: invokevirtual longValue : ()J
    //   1861: lstore #6
    //   1863: aload_0
    //   1864: getfield nodes : [Lorg/hsqldb/Expression;
    //   1867: arraylength
    //   1868: iconst_3
    //   1869: if_icmple -> 1902
    //   1872: aload_0
    //   1873: getfield nodes : [Lorg/hsqldb/Expression;
    //   1876: iconst_3
    //   1877: aaload
    //   1878: ifnull -> 1902
    //   1881: aload_0
    //   1882: getfield nodes : [Lorg/hsqldb/Expression;
    //   1885: iconst_2
    //   1886: aaload
    //   1887: getfield valueData : Ljava/lang/Object;
    //   1890: checkcast java/lang/Number
    //   1893: invokevirtual intValue : ()I
    //   1896: sipush #454
    //   1899: if_icmpne -> 1902
    //   1902: aload_0
    //   1903: getfield dataType : Lorg/hsqldb/types/Type;
    //   1906: checkcast org/hsqldb/types/CharacterType
    //   1909: aload_1
    //   1910: aload_2
    //   1911: iconst_0
    //   1912: aaload
    //   1913: lload #4
    //   1915: lload #6
    //   1917: aload_0
    //   1918: getfield nodes : [Lorg/hsqldb/Expression;
    //   1921: iconst_2
    //   1922: aaload
    //   1923: ifnull -> 1930
    //   1926: iconst_1
    //   1927: goto -> 1931
    //   1930: iconst_0
    //   1931: iconst_0
    //   1932: invokevirtual substring : (Lorg/hsqldb/SessionInterface;Ljava/lang/Object;JJZZ)Ljava/lang/Object;
    //   1935: areturn
    //   1936: aload_2
    //   1937: iconst_0
    //   1938: aaload
    //   1939: ifnonnull -> 1944
    //   1942: aconst_null
    //   1943: areturn
    //   1944: aload_0
    //   1945: getfield dataType : Lorg/hsqldb/types/Type;
    //   1948: checkcast org/hsqldb/types/CharacterType
    //   1951: aload_1
    //   1952: aload_2
    //   1953: iconst_0
    //   1954: aaload
    //   1955: invokevirtual lower : (Lorg/hsqldb/Session;Ljava/lang/Object;)Ljava/lang/Object;
    //   1958: areturn
    //   1959: aload_2
    //   1960: iconst_0
    //   1961: aaload
    //   1962: ifnonnull -> 1967
    //   1965: aconst_null
    //   1966: areturn
    //   1967: aload_0
    //   1968: getfield dataType : Lorg/hsqldb/types/Type;
    //   1971: checkcast org/hsqldb/types/CharacterType
    //   1974: aload_1
    //   1975: aload_2
    //   1976: iconst_0
    //   1977: aaload
    //   1978: invokevirtual upper : (Lorg/hsqldb/Session;Ljava/lang/Object;)Ljava/lang/Object;
    //   1981: areturn
    //   1982: aload_2
    //   1983: iconst_1
    //   1984: aaload
    //   1985: ifnull -> 1994
    //   1988: aload_2
    //   1989: iconst_2
    //   1990: aaload
    //   1991: ifnonnull -> 1996
    //   1994: aconst_null
    //   1995: areturn
    //   1996: iconst_0
    //   1997: istore_3
    //   1998: iconst_0
    //   1999: istore #4
    //   2001: aload_0
    //   2002: getfield nodes : [Lorg/hsqldb/Expression;
    //   2005: iconst_0
    //   2006: aaload
    //   2007: getfield valueData : Ljava/lang/Object;
    //   2010: checkcast java/lang/Number
    //   2013: invokevirtual intValue : ()I
    //   2016: lookupswitch default -> 2071, 23 -> 2052, 151 -> 2060, 286 -> 2065
    //   2052: iconst_1
    //   2053: dup
    //   2054: istore #4
    //   2056: istore_3
    //   2057: goto -> 2080
    //   2060: iconst_1
    //   2061: istore_3
    //   2062: goto -> 2080
    //   2065: iconst_1
    //   2066: istore #4
    //   2068: goto -> 2080
    //   2071: sipush #201
    //   2074: ldc 'FunctionSQL'
    //   2076: invokestatic runtimeError : (ILjava/lang/String;)Ljava/lang/RuntimeException;
    //   2079: athrow
    //   2080: aload_2
    //   2081: iconst_1
    //   2082: aaload
    //   2083: checkcast java/lang/String
    //   2086: astore #5
    //   2088: aload #5
    //   2090: invokevirtual length : ()I
    //   2093: iconst_1
    //   2094: if_icmpeq -> 2104
    //   2097: sipush #3460
    //   2100: invokestatic error : (I)Lorg/hsqldb/HsqlException;
    //   2103: athrow
    //   2104: aload #5
    //   2106: iconst_0
    //   2107: invokevirtual charAt : (I)C
    //   2110: istore #6
    //   2112: aload_0
    //   2113: getfield dataType : Lorg/hsqldb/types/Type;
    //   2116: checkcast org/hsqldb/types/CharacterType
    //   2119: aload_1
    //   2120: aload_2
    //   2121: iconst_2
    //   2122: aaload
    //   2123: iload #6
    //   2125: iload_3
    //   2126: iload #4
    //   2128: invokevirtual trim : (Lorg/hsqldb/SessionInterface;Ljava/lang/Object;CZZ)Ljava/lang/Object;
    //   2131: areturn
    //   2132: aload_2
    //   2133: iconst_0
    //   2134: aaload
    //   2135: ifnull -> 2150
    //   2138: aload_2
    //   2139: iconst_1
    //   2140: aaload
    //   2141: ifnull -> 2150
    //   2144: aload_2
    //   2145: iconst_2
    //   2146: aaload
    //   2147: ifnonnull -> 2152
    //   2150: aconst_null
    //   2151: areturn
    //   2152: getstatic org/hsqldb/types/Type.SQL_BIGINT : Lorg/hsqldb/types/NumberType;
    //   2155: aload_1
    //   2156: aload_2
    //   2157: iconst_2
    //   2158: aaload
    //   2159: aload_0
    //   2160: getfield nodes : [Lorg/hsqldb/Expression;
    //   2163: iconst_2
    //   2164: aaload
    //   2165: getfield dataType : Lorg/hsqldb/types/Type;
    //   2168: invokevirtual convertToType : (Lorg/hsqldb/SessionInterface;Ljava/lang/Object;Lorg/hsqldb/types/Type;)Ljava/lang/Object;
    //   2171: astore_3
    //   2172: aload_3
    //   2173: checkcast java/lang/Number
    //   2176: invokevirtual longValue : ()J
    //   2179: lconst_1
    //   2180: lsub
    //   2181: lstore #4
    //   2183: lconst_0
    //   2184: lstore #6
    //   2186: aload_0
    //   2187: getfield nodes : [Lorg/hsqldb/Expression;
    //   2190: iconst_3
    //   2191: aaload
    //   2192: ifnull -> 2232
    //   2195: aload_2
    //   2196: iconst_3
    //   2197: aaload
    //   2198: ifnonnull -> 2203
    //   2201: aconst_null
    //   2202: areturn
    //   2203: getstatic org/hsqldb/types/Type.SQL_BIGINT : Lorg/hsqldb/types/NumberType;
    //   2206: aload_1
    //   2207: aload_2
    //   2208: iconst_3
    //   2209: aaload
    //   2210: aload_0
    //   2211: getfield nodes : [Lorg/hsqldb/Expression;
    //   2214: iconst_3
    //   2215: aaload
    //   2216: getfield dataType : Lorg/hsqldb/types/Type;
    //   2219: invokevirtual convertToType : (Lorg/hsqldb/SessionInterface;Ljava/lang/Object;Lorg/hsqldb/types/Type;)Ljava/lang/Object;
    //   2222: astore_3
    //   2223: aload_3
    //   2224: checkcast java/lang/Number
    //   2227: invokevirtual longValue : ()J
    //   2230: lstore #6
    //   2232: aload_0
    //   2233: getfield dataType : Lorg/hsqldb/types/Type;
    //   2236: checkcast org/hsqldb/types/CharacterType
    //   2239: aconst_null
    //   2240: aload_2
    //   2241: iconst_0
    //   2242: aaload
    //   2243: aload_2
    //   2244: iconst_1
    //   2245: aaload
    //   2246: lload #4
    //   2248: lload #6
    //   2250: aload_0
    //   2251: getfield nodes : [Lorg/hsqldb/Expression;
    //   2254: iconst_3
    //   2255: aaload
    //   2256: ifnull -> 2263
    //   2259: iconst_1
    //   2260: goto -> 2264
    //   2263: iconst_0
    //   2264: invokevirtual overlay : (Lorg/hsqldb/SessionInterface;Ljava/lang/Object;Ljava/lang/Object;JJZ)Ljava/lang/Object;
    //   2267: areturn
    //   2268: aload_2
    //   2269: iconst_0
    //   2270: aaload
    //   2271: ifnull -> 2280
    //   2274: aload_2
    //   2275: iconst_1
    //   2276: aaload
    //   2277: ifnonnull -> 2282
    //   2280: aconst_null
    //   2281: areturn
    //   2282: getstatic org/hsqldb/types/Type.SQL_BIGINT : Lorg/hsqldb/types/NumberType;
    //   2285: aload_1
    //   2286: aload_2
    //   2287: iconst_1
    //   2288: aaload
    //   2289: aload_0
    //   2290: getfield nodes : [Lorg/hsqldb/Expression;
    //   2293: iconst_1
    //   2294: aaload
    //   2295: getfield dataType : Lorg/hsqldb/types/Type;
    //   2298: invokevirtual convertToType : (Lorg/hsqldb/SessionInterface;Ljava/lang/Object;Lorg/hsqldb/types/Type;)Ljava/lang/Object;
    //   2301: astore_3
    //   2302: aload_3
    //   2303: checkcast java/lang/Number
    //   2306: invokevirtual longValue : ()J
    //   2309: lconst_1
    //   2310: lsub
    //   2311: lstore #4
    //   2313: lconst_0
    //   2314: lstore #6
    //   2316: aload_0
    //   2317: getfield nodes : [Lorg/hsqldb/Expression;
    //   2320: iconst_2
    //   2321: aaload
    //   2322: ifnull -> 2363
    //   2325: aload_2
    //   2326: iconst_2
    //   2327: aaload
    //   2328: ifnonnull -> 2333
    //   2331: aconst_null
    //   2332: areturn
    //   2333: getstatic org/hsqldb/types/Type.SQL_BIGINT : Lorg/hsqldb/types/NumberType;
    //   2336: aload_1
    //   2337: aload_2
    //   2338: iconst_2
    //   2339: aaload
    //   2340: aload_0
    //   2341: getfield nodes : [Lorg/hsqldb/Expression;
    //   2344: iconst_2
    //   2345: aaload
    //   2346: getfield dataType : Lorg/hsqldb/types/Type;
    //   2349: invokevirtual convertToType : (Lorg/hsqldb/SessionInterface;Ljava/lang/Object;Lorg/hsqldb/types/Type;)Ljava/lang/Object;
    //   2352: astore_3
    //   2353: aload_3
    //   2354: checkcast java/lang/Number
    //   2357: invokevirtual intValue : ()I
    //   2360: i2l
    //   2361: lstore #6
    //   2363: aload_0
    //   2364: getfield dataType : Lorg/hsqldb/types/Type;
    //   2367: checkcast org/hsqldb/types/BinaryType
    //   2370: aload_1
    //   2371: aload_2
    //   2372: iconst_0
    //   2373: aaload
    //   2374: checkcast org/hsqldb/types/BlobData
    //   2377: lload #4
    //   2379: lload #6
    //   2381: aload_0
    //   2382: getfield nodes : [Lorg/hsqldb/Expression;
    //   2385: iconst_2
    //   2386: aaload
    //   2387: ifnull -> 2394
    //   2390: iconst_1
    //   2391: goto -> 2395
    //   2394: iconst_0
    //   2395: invokevirtual substring : (Lorg/hsqldb/SessionInterface;Lorg/hsqldb/types/BlobData;JJZ)Lorg/hsqldb/types/BlobData;
    //   2398: areturn
    //   2399: aload_2
    //   2400: iconst_1
    //   2401: aaload
    //   2402: ifnull -> 2411
    //   2405: aload_2
    //   2406: iconst_2
    //   2407: aaload
    //   2408: ifnonnull -> 2413
    //   2411: aconst_null
    //   2412: areturn
    //   2413: iconst_0
    //   2414: istore_3
    //   2415: iconst_0
    //   2416: istore #4
    //   2418: aload_0
    //   2419: getfield nodes : [Lorg/hsqldb/Expression;
    //   2422: iconst_0
    //   2423: aaload
    //   2424: getfield valueData : Ljava/lang/Object;
    //   2427: checkcast java/lang/Number
    //   2430: invokevirtual intValue : ()I
    //   2433: istore #5
    //   2435: iload #5
    //   2437: lookupswitch default -> 2491, 23 -> 2472, 151 -> 2480, 286 -> 2485
    //   2472: iconst_1
    //   2473: dup
    //   2474: istore #4
    //   2476: istore_3
    //   2477: goto -> 2500
    //   2480: iconst_1
    //   2481: istore_3
    //   2482: goto -> 2500
    //   2485: iconst_1
    //   2486: istore #4
    //   2488: goto -> 2500
    //   2491: sipush #201
    //   2494: ldc 'FunctionSQL'
    //   2496: invokestatic runtimeError : (ILjava/lang/String;)Ljava/lang/RuntimeException;
    //   2499: athrow
    //   2500: aload_2
    //   2501: iconst_1
    //   2502: aaload
    //   2503: checkcast org/hsqldb/types/BlobData
    //   2506: astore #6
    //   2508: aload #6
    //   2510: aload_1
    //   2511: invokeinterface length : (Lorg/hsqldb/SessionInterface;)J
    //   2516: lconst_1
    //   2517: lcmp
    //   2518: ifeq -> 2528
    //   2521: sipush #3460
    //   2524: invokestatic error : (I)Lorg/hsqldb/HsqlException;
    //   2527: athrow
    //   2528: aload #6
    //   2530: invokeinterface getBytes : ()[B
    //   2535: astore #7
    //   2537: aload_0
    //   2538: getfield dataType : Lorg/hsqldb/types/Type;
    //   2541: checkcast org/hsqldb/types/BinaryType
    //   2544: aload_1
    //   2545: aload_2
    //   2546: iconst_2
    //   2547: aaload
    //   2548: checkcast org/hsqldb/types/BlobData
    //   2551: aload #7
    //   2553: iconst_0
    //   2554: baload
    //   2555: iload_3
    //   2556: iload #4
    //   2558: invokevirtual trim : (Lorg/hsqldb/Session;Lorg/hsqldb/types/BlobData;IZZ)Lorg/hsqldb/types/BlobData;
    //   2561: areturn
    //   2562: aload_2
    //   2563: iconst_0
    //   2564: aaload
    //   2565: ifnull -> 2580
    //   2568: aload_2
    //   2569: iconst_1
    //   2570: aaload
    //   2571: ifnull -> 2580
    //   2574: aload_2
    //   2575: iconst_2
    //   2576: aaload
    //   2577: ifnonnull -> 2582
    //   2580: aconst_null
    //   2581: areturn
    //   2582: getstatic org/hsqldb/types/Type.SQL_BIGINT : Lorg/hsqldb/types/NumberType;
    //   2585: aload_1
    //   2586: aload_2
    //   2587: iconst_2
    //   2588: aaload
    //   2589: aload_0
    //   2590: getfield nodes : [Lorg/hsqldb/Expression;
    //   2593: iconst_2
    //   2594: aaload
    //   2595: getfield dataType : Lorg/hsqldb/types/Type;
    //   2598: invokevirtual convertToType : (Lorg/hsqldb/SessionInterface;Ljava/lang/Object;Lorg/hsqldb/types/Type;)Ljava/lang/Object;
    //   2601: astore_3
    //   2602: aload_3
    //   2603: checkcast java/lang/Number
    //   2606: invokevirtual longValue : ()J
    //   2609: lconst_1
    //   2610: lsub
    //   2611: lstore #4
    //   2613: lconst_0
    //   2614: lstore #6
    //   2616: aload_0
    //   2617: getfield nodes : [Lorg/hsqldb/Expression;
    //   2620: iconst_3
    //   2621: aaload
    //   2622: ifnull -> 2662
    //   2625: aload_2
    //   2626: iconst_3
    //   2627: aaload
    //   2628: ifnonnull -> 2633
    //   2631: aconst_null
    //   2632: areturn
    //   2633: getstatic org/hsqldb/types/Type.SQL_BIGINT : Lorg/hsqldb/types/NumberType;
    //   2636: aload_1
    //   2637: aload_2
    //   2638: iconst_3
    //   2639: aaload
    //   2640: aload_0
    //   2641: getfield nodes : [Lorg/hsqldb/Expression;
    //   2644: iconst_3
    //   2645: aaload
    //   2646: getfield dataType : Lorg/hsqldb/types/Type;
    //   2649: invokevirtual convertToType : (Lorg/hsqldb/SessionInterface;Ljava/lang/Object;Lorg/hsqldb/types/Type;)Ljava/lang/Object;
    //   2652: astore_3
    //   2653: aload_3
    //   2654: checkcast java/lang/Number
    //   2657: invokevirtual longValue : ()J
    //   2660: lstore #6
    //   2662: aload_0
    //   2663: getfield dataType : Lorg/hsqldb/types/Type;
    //   2666: checkcast org/hsqldb/types/BinaryType
    //   2669: aload_1
    //   2670: aload_2
    //   2671: iconst_0
    //   2672: aaload
    //   2673: checkcast org/hsqldb/types/BlobData
    //   2676: aload_2
    //   2677: iconst_1
    //   2678: aaload
    //   2679: checkcast org/hsqldb/types/BlobData
    //   2682: lload #4
    //   2684: lload #6
    //   2686: aload_0
    //   2687: getfield nodes : [Lorg/hsqldb/Expression;
    //   2690: iconst_3
    //   2691: aaload
    //   2692: ifnull -> 2699
    //   2695: iconst_1
    //   2696: goto -> 2700
    //   2699: iconst_0
    //   2700: invokevirtual overlay : (Lorg/hsqldb/Session;Lorg/hsqldb/types/BlobData;Lorg/hsqldb/types/BlobData;JJZ)Lorg/hsqldb/types/BlobData;
    //   2703: areturn
    //   2704: aload_1
    //   2705: getfield database : Lorg/hsqldb/Database;
    //   2708: invokevirtual getCatalogName : ()Lorg/hsqldb/HsqlNameManager$HsqlName;
    //   2711: getfield name : Ljava/lang/String;
    //   2714: areturn
    //   2715: aload_1
    //   2716: invokevirtual getRole : ()Lorg/hsqldb/rights/Grantee;
    //   2719: ifnonnull -> 2726
    //   2722: aconst_null
    //   2723: goto -> 2736
    //   2726: aload_1
    //   2727: invokevirtual getRole : ()Lorg/hsqldb/rights/Grantee;
    //   2730: invokevirtual getName : ()Lorg/hsqldb/HsqlNameManager$HsqlName;
    //   2733: invokevirtual getNameString : ()Ljava/lang/String;
    //   2736: areturn
    //   2737: aload_1
    //   2738: invokevirtual getCurrentSchemaHsqlName : ()Lorg/hsqldb/HsqlNameManager$HsqlName;
    //   2741: getfield name : Ljava/lang/String;
    //   2744: areturn
    //   2745: aload_1
    //   2746: invokevirtual getUser : ()Lorg/hsqldb/rights/User;
    //   2749: invokevirtual getName : ()Lorg/hsqldb/HsqlNameManager$HsqlName;
    //   2752: invokevirtual getNameString : ()Ljava/lang/String;
    //   2755: areturn
    //   2756: aload_1
    //   2757: invokevirtual getUser : ()Lorg/hsqldb/rights/User;
    //   2760: invokevirtual getName : ()Lorg/hsqldb/HsqlNameManager$HsqlName;
    //   2763: invokevirtual getNameString : ()Ljava/lang/String;
    //   2766: areturn
    //   2767: aload_1
    //   2768: invokevirtual getUser : ()Lorg/hsqldb/rights/User;
    //   2771: invokevirtual getName : ()Lorg/hsqldb/HsqlNameManager$HsqlName;
    //   2774: invokevirtual getNameString : ()Ljava/lang/String;
    //   2777: areturn
    //   2778: aload_1
    //   2779: invokevirtual getUser : ()Lorg/hsqldb/rights/User;
    //   2782: invokevirtual getName : ()Lorg/hsqldb/HsqlNameManager$HsqlName;
    //   2785: invokevirtual getNameString : ()Ljava/lang/String;
    //   2788: areturn
    //   2789: aload_1
    //   2790: getfield sessionData : Lorg/hsqldb/SessionData;
    //   2793: getfield currentValue : Ljava/lang/Object;
    //   2796: areturn
    //   2797: aload_1
    //   2798: getfield database : Lorg/hsqldb/Database;
    //   2801: getfield sqlSyntaxOra : Z
    //   2804: ifeq -> 2821
    //   2807: aload_0
    //   2808: getfield dataType : Lorg/hsqldb/types/Type;
    //   2811: aload_1
    //   2812: aload_1
    //   2813: iconst_0
    //   2814: invokevirtual getCurrentTimestamp : (Z)Lorg/hsqldb/types/TimestampData;
    //   2817: invokevirtual convertToTypeLimits : (Lorg/hsqldb/SessionInterface;Ljava/lang/Object;)Ljava/lang/Object;
    //   2820: areturn
    //   2821: aload_1
    //   2822: invokevirtual getCurrentDate : ()Lorg/hsqldb/types/TimestampData;
    //   2825: areturn
    //   2826: aload_0
    //   2827: getfield dataType : Lorg/hsqldb/types/Type;
    //   2830: aload_1
    //   2831: aload_1
    //   2832: iconst_1
    //   2833: invokevirtual getCurrentTime : (Z)Lorg/hsqldb/types/TimeData;
    //   2836: invokevirtual convertToTypeLimits : (Lorg/hsqldb/SessionInterface;Ljava/lang/Object;)Ljava/lang/Object;
    //   2839: areturn
    //   2840: aload_0
    //   2841: getfield dataType : Lorg/hsqldb/types/Type;
    //   2844: aload_1
    //   2845: aload_1
    //   2846: iconst_1
    //   2847: invokevirtual getCurrentTimestamp : (Z)Lorg/hsqldb/types/TimestampData;
    //   2850: invokevirtual convertToTypeLimits : (Lorg/hsqldb/SessionInterface;Ljava/lang/Object;)Ljava/lang/Object;
    //   2853: areturn
    //   2854: aload_0
    //   2855: getfield dataType : Lorg/hsqldb/types/Type;
    //   2858: aload_1
    //   2859: aload_1
    //   2860: iconst_0
    //   2861: invokevirtual getCurrentTime : (Z)Lorg/hsqldb/types/TimeData;
    //   2864: invokevirtual convertToTypeLimits : (Lorg/hsqldb/SessionInterface;Ljava/lang/Object;)Ljava/lang/Object;
    //   2867: areturn
    //   2868: aload_0
    //   2869: getfield dataType : Lorg/hsqldb/types/Type;
    //   2872: aload_1
    //   2873: aload_1
    //   2874: iconst_0
    //   2875: invokevirtual getCurrentTimestamp : (Z)Lorg/hsqldb/types/TimestampData;
    //   2878: invokevirtual convertToTypeLimits : (Lorg/hsqldb/SessionInterface;Ljava/lang/Object;)Ljava/lang/Object;
    //   2881: areturn
    //   2882: sipush #201
    //   2885: ldc 'FunctionSQL'
    //   2887: invokestatic runtimeError : (ILjava/lang/String;)Ljava/lang/RuntimeException;
    //   2890: athrow
  }
  
  public void resolveTypes(Session paramSession, Expression paramExpression) {
    DTIType dTIType;
    int i;
    for (i = 0; i < this.nodes.length; i++) {
      if (this.nodes[i] != null)
        this.nodes[i].resolveTypes(paramSession, this); 
    } 
    switch (this.funcType) {
      case 1:
      case 2:
        if ((this.nodes[0]).dataType == null) {
          if ((this.nodes[1]).dataType == null)
            throw Error.error(5567); 
          if ((this.nodes[1]).dataType.typeCode == 40 || (this.nodes[1]).dataType.isBinaryType()) {
            (this.nodes[0]).dataType = (this.nodes[1]).dataType;
          } else {
            (this.nodes[0]).dataType = (Type)Type.SQL_VARCHAR;
          } 
        } 
        if ((this.nodes[1]).dataType == null)
          if ((this.nodes[0]).dataType.typeCode == 40 || (this.nodes[0]).dataType.isBinaryType()) {
            (this.nodes[1]).dataType = (this.nodes[0]).dataType;
          } else {
            (this.nodes[1]).dataType = (Type)Type.SQL_VARCHAR;
          }  
        if ((this.nodes[0]).dataType.isCharacterType() && (this.nodes[1]).dataType.isCharacterType()) {
          this.funcType = 1;
        } else if ((this.nodes[0]).dataType.isBinaryType() && (this.nodes[1]).dataType.isBinaryType()) {
          if ((this.nodes[0]).dataType.isBitType() || (this.nodes[1]).dataType.isBitType())
            throw Error.error(5563); 
          this.funcType = 2;
        } else {
          throw Error.error(5563);
        } 
        if (this.nodes.length > 3 && this.nodes[3] != null) {
          if (this.nodes[3].isDynamicParam())
            (this.nodes[3]).dataType = (Type)Type.SQL_BIGINT; 
          if (!(this.nodes[3]).dataType.isNumberType())
            throw Error.error(5563); 
        } 
        this.dataType = (Type)Type.SQL_BIGINT;
      case 5:
        if ((this.nodes[1]).dataType == null)
          throw Error.error(5567); 
        if (!(this.nodes[1]).dataType.isDateTimeType() && !(this.nodes[1]).dataType.isIntervalType())
          throw Error.error(5563); 
        i = ((Number)(this.nodes[0]).valueData).intValue();
        dTIType = (DTIType)(this.nodes[1]).dataType;
        i = DTIType.getFieldNameTypeForToken(i);
        this.dataType = dTIType.getExtractType(i);
      case 6:
        if ((this.nodes[0]).dataType == null)
          (this.nodes[0]).dataType = (Type)Type.SQL_BIT_VARYING_MAX_LENGTH; 
        if (!(this.nodes[0]).dataType.isCharacterType() && !(this.nodes[0]).dataType.isBinaryType())
          throw Error.error(5563); 
        this.dataType = (Type)Type.SQL_BIGINT;
      case 7:
        if ((this.nodes[0]).dataType == null)
          (this.nodes[0]).dataType = (Type)Type.SQL_VARCHAR; 
        if (!(this.nodes[0]).dataType.isCharacterType())
          throw Error.error(5563); 
      case 8:
        if ((this.nodes[0]).dataType == null)
          (this.nodes[0]).dataType = (Type)Type.SQL_VARCHAR; 
        if (!(this.nodes[0]).dataType.isCharacterType() && !(this.nodes[0]).dataType.isBinaryType())
          throw Error.error(5563); 
        this.dataType = (Type)Type.SQL_BIGINT;
      case 9:
        if ((this.nodes[0]).dataType == null)
          throw Error.error(5567); 
        if (!(this.nodes[0]).dataType.isArrayType())
          throw Error.error(5563); 
        this.dataType = (Type)Type.SQL_INTEGER;
      case 10:
        if ((this.nodes[0]).dataType == null)
          throw Error.error(5567); 
        if (!(this.nodes[0]).dataType.isArrayType())
          throw Error.error(5563); 
        this.dataType = (Type)Type.SQL_INTEGER;
      case 11:
        if ((this.nodes[0]).dataType == null)
          throw Error.error(5567); 
        if (!(this.nodes[0]).dataType.isArrayType())
          throw Error.error(5563); 
        if ((this.nodes[1]).dataType == null)
          (this.nodes[1]).dataType = (Type)Type.SQL_INTEGER; 
        if (!(this.nodes[1]).dataType.isIntegralType())
          throw Error.error(5563); 
        this.dataType = (this.nodes[0]).dataType;
      case 13:
        if ((this.nodes[0]).dataType == null)
          (this.nodes[0]).dataType = (this.nodes[1]).dataType; 
        if ((this.nodes[1]).dataType == null)
          (this.nodes[1]).dataType = (this.nodes[0]).dataType; 
        if ((this.nodes[0]).dataType == null)
          throw Error.error(5567); 
        if (!(this.nodes[0]).dataType.isNumberType() || !(this.nodes[1]).dataType.isNumberType())
          throw Error.error(5563); 
        (this.nodes[0]).dataType = ((NumberType)(this.nodes[0]).dataType).getIntegralType();
        (this.nodes[1]).dataType = ((NumberType)(this.nodes[1]).dataType).getIntegralType();
        this.dataType = (this.nodes[1]).dataType;
      case 16:
        if ((this.nodes[0]).dataType == null)
          (this.nodes[0]).dataType = (this.nodes[1]).dataType; 
        if ((this.nodes[1]).dataType == null)
          (this.nodes[1]).dataType = (this.nodes[0]).dataType; 
        if ((this.nodes[0]).dataType == null)
          throw Error.error(5567); 
        if (!(this.nodes[0]).dataType.isNumberType() || !(this.nodes[1]).dataType.isNumberType())
          throw Error.error(5563); 
        (this.nodes[0]).dataType = (Type)Type.SQL_DOUBLE;
        (this.nodes[1]).dataType = (Type)Type.SQL_DOUBLE;
        this.dataType = (Type)Type.SQL_DOUBLE;
      case 14:
      case 15:
      case 17:
        if ((this.nodes[0]).dataType == null)
          (this.nodes[0]).dataType = (Type)Type.SQL_DOUBLE; 
        if (!(this.nodes[0]).dataType.isNumberType())
          throw Error.error(5563); 
        (this.nodes[0]).dataType = (Type)Type.SQL_DOUBLE;
        this.dataType = (Type)Type.SQL_DOUBLE;
      case 12:
        if ((this.nodes[0]).dataType != null && (this.nodes[0]).dataType.isIntervalType())
          this.dataType = (this.nodes[0]).dataType; 
      case 20:
      case 21:
        if ((this.nodes[0]).dataType == null)
          (this.nodes[0]).dataType = (Type)Type.SQL_DOUBLE; 
        if (!(this.nodes[0]).dataType.isNumberType())
          throw Error.error(5563); 
        this.dataType = (this.nodes[0]).dataType;
        if ((this.dataType.typeCode == 3 || this.dataType.typeCode == 2) && this.dataType.scale > 0)
          this.dataType = (Type)NumberType.getNumberType(this.dataType.typeCode, this.dataType.precision + 1L, 0); 
      case 22:
        (this.nodes[0]).dataType = Type.getAggregateType((this.nodes[0]).dataType, (this.nodes[1]).dataType);
        (this.nodes[0]).dataType = Type.getAggregateType((this.nodes[0]).dataType, (this.nodes[2]).dataType);
        if ((this.nodes[0]).dataType == null)
          throw Error.error(5567); 
        if (!(this.nodes[0]).dataType.isNumberType() && !(this.nodes[0]).dataType.isDateTimeType())
          throw Error.error(5563); 
        (this.nodes[1]).dataType = (this.nodes[0]).dataType;
        (this.nodes[2]).dataType = (this.nodes[0]).dataType;
        if ((this.nodes[3]).dataType == null)
          (this.nodes[3]).dataType = (Type)Type.SQL_INTEGER; 
        if (!(this.nodes[3]).dataType.isIntegralType())
          throw Error.error(5563); 
        this.dataType = (this.nodes[3]).dataType;
      case 23:
      case 40:
        if ((this.nodes[0]).dataType == null)
          (this.nodes[0]).dataType = (Type)Type.SQL_VARCHAR_DEFAULT; 
        if ((this.nodes[1]).dataType == null)
          (this.nodes[1]).dataType = (Type)Type.SQL_NUMERIC; 
        if (!(this.nodes[1]).dataType.isNumberType())
          throw Error.error(5563); 
        if (this.nodes[2] != null) {
          if ((this.nodes[2]).dataType == null)
            (this.nodes[2]).dataType = (Type)Type.SQL_NUMERIC; 
          if (!(this.nodes[2]).dataType.isNumberType())
            throw Error.error(5563); 
          (this.nodes[2]).dataType = ((NumberType)(this.nodes[2]).dataType).getIntegralType();
        } 
        this.dataType = (this.nodes[0]).dataType;
        if (this.dataType.isCharacterType()) {
          this.funcType = 23;
          if (this.dataType.typeCode == 1)
            this.dataType = (Type)CharacterType.getCharacterType(12, this.dataType.precision, this.dataType.getCollation()); 
        } else if (this.dataType.isBinaryType()) {
          this.funcType = 40;
        } else {
          throw Error.error(5563);
        } 
        if (this.nodes.length <= 3 || this.nodes[3] != null);
      case 26:
      case 27:
        if ((this.nodes[0]).dataType == null)
          (this.nodes[0]).dataType = (Type)Type.SQL_VARCHAR_DEFAULT; 
        this.dataType = (this.nodes[0]).dataType;
        if (!this.dataType.isCharacterType())
          throw Error.error(5563); 
      case 31:
      case 41:
        if (this.nodes[0] == null)
          this.nodes[0] = new ExpressionValue(ValuePool.getInt(23), (Type)Type.SQL_INTEGER); 
        if ((this.nodes[2]).dataType == null)
          (this.nodes[2]).dataType = (Type)Type.SQL_VARCHAR_DEFAULT; 
        this.dataType = (this.nodes[2]).dataType;
        if (this.dataType.isCharacterType()) {
          this.funcType = 31;
          if (this.dataType.typeCode == 1)
            this.dataType = (Type)CharacterType.getCharacterType(12, this.dataType.precision, this.dataType.getCollation()); 
          if (this.nodes[1] == null)
            this.nodes[1] = new ExpressionValue(" ", (Type)Type.SQL_CHAR); 
        } else if (this.dataType.isBinaryType()) {
          this.funcType = 41;
          if (this.nodes[1] == null)
            this.nodes[1] = new ExpressionValue(new BinaryData(new byte[] { 0 }, false), (Type)Type.SQL_BINARY); 
        } else {
          throw Error.error(5563);
        } 
      case 32:
      case 42:
        if ((this.nodes[0]).dataType == null) {
          if ((this.nodes[1]).dataType == null) {
            (this.nodes[0]).dataType = (Type)Type.SQL_VARCHAR_DEFAULT;
            (this.nodes[1]).dataType = (Type)Type.SQL_VARCHAR_DEFAULT;
          } 
          if ((this.nodes[1]).dataType.typeCode == 40 || (this.nodes[1]).dataType.isBinaryType()) {
            (this.nodes[0]).dataType = (this.nodes[1]).dataType;
          } else {
            (this.nodes[0]).dataType = (Type)Type.SQL_VARCHAR_DEFAULT;
          } 
        } 
        if ((this.nodes[1]).dataType == null)
          if ((this.nodes[0]).dataType.typeCode == 40 || (this.nodes[0]).dataType.isBinaryType()) {
            (this.nodes[1]).dataType = (this.nodes[0]).dataType;
          } else {
            (this.nodes[1]).dataType = (Type)Type.SQL_VARCHAR_DEFAULT;
          }  
        if ((this.nodes[0]).dataType.isCharacterType() && (this.nodes[1]).dataType.isCharacterType()) {
          this.funcType = 32;
          if ((this.nodes[0]).dataType.typeCode == 40 || (this.nodes[1]).dataType.typeCode == 40) {
            this.dataType = (Type)CharacterType.getCharacterType(40, (this.nodes[0]).dataType.precision + (this.nodes[1]).dataType.precision, (this.nodes[0]).dataType.getCollation());
          } else {
            this.dataType = (Type)CharacterType.getCharacterType(12, (this.nodes[0]).dataType.precision + (this.nodes[1]).dataType.precision, (this.nodes[0]).dataType.getCollation());
          } 
        } else if ((this.nodes[0]).dataType.isBinaryType() && (this.nodes[1]).dataType.isBinaryType()) {
          this.funcType = 42;
          if ((this.nodes[0]).dataType.typeCode == 30 || (this.nodes[1]).dataType.typeCode == 30) {
            this.dataType = (Type)BinaryType.getBinaryType(30, (this.nodes[0]).dataType.precision + (this.nodes[1]).dataType.precision);
          } else {
            this.dataType = (Type)BinaryType.getBinaryType(61, (this.nodes[0]).dataType.precision + (this.nodes[1]).dataType.precision);
          } 
        } else {
          throw Error.error(5563);
        } 
        if ((this.nodes[2]).dataType == null)
          (this.nodes[2]).dataType = (Type)Type.SQL_NUMERIC; 
        if (!(this.nodes[2]).dataType.isNumberType())
          throw Error.error(5563); 
        (this.nodes[2]).dataType = ((NumberType)(this.nodes[2]).dataType).getIntegralType();
        if (this.nodes[3] != null) {
          if ((this.nodes[3]).dataType == null)
            (this.nodes[3]).dataType = (Type)Type.SQL_NUMERIC; 
          if (!(this.nodes[3]).dataType.isNumberType())
            throw Error.error(5563); 
          (this.nodes[3]).dataType = ((NumberType)(this.nodes[3]).dataType).getIntegralType();
        } 
      case 53:
      case 54:
      case 55:
      case 56:
      case 57:
      case 58:
      case 59:
      case 60:
      case 61:
      case 62:
        this.dataType = TypeInvariants.SQL_IDENTIFIER;
      case 63:
        return;
      case 43:
        if (paramSession.database.sqlSyntaxOra) {
          this.dataType = (Type)Type.SQL_TIMESTAMP_NO_FRACTION;
        } else {
          this.dataType = (Type)Type.SQL_DATE;
        } 
      case 44:
        i = 0;
        if (this.nodes.length > 0 && this.nodes[0] != null)
          i = ((Integer)(this.nodes[0]).valueData).intValue(); 
        this.dataType = (Type)DateTimeType.getDateTimeType(94, i);
      case 50:
        i = 6;
        if (this.nodes.length > 0 && this.nodes[0] != null)
          i = ((Integer)(this.nodes[0]).valueData).intValue(); 
        this.dataType = (Type)DateTimeType.getDateTimeType(95, i);
      case 51:
        i = 0;
        if (this.nodes.length > 0 && this.nodes[0] != null)
          i = ((Integer)(this.nodes[0]).valueData).intValue(); 
        this.dataType = (Type)DateTimeType.getDateTimeType(92, i);
      case 52:
        i = 6;
        if (this.nodes.length > 0 && this.nodes[0] != null)
          i = ((Integer)(this.nodes[0]).valueData).intValue(); 
        this.dataType = (Type)DateTimeType.getDateTimeType(93, i);
    } 
    throw Error.runtimeError(201, "FunctionSQL");
  }
  
  public String getSQL() {
    int i;
    String str1;
    String str2;
    int j;
    StringBuffer stringBuffer = new StringBuffer();
    switch (this.funcType) {
      case 1:
      case 2:
        stringBuffer.append("POSITION").append('(').append(this.nodes[0].getSQL()).append(' ').append("IN").append(' ').append(this.nodes[1].getSQL());
        if (this.nodes[2] != null && Boolean.TRUE.equals((this.nodes[2]).valueData))
          stringBuffer.append(' ').append("USING").append(' ').append("OCTETS"); 
        stringBuffer.append(')');
      case 3:
      case 4:
        return stringBuffer.toString();
      case 5:
        i = ((Integer)(this.nodes[0]).valueData).intValue();
        i = DTIType.getFieldNameTypeForToken(i);
        str2 = DTIType.getFieldNameTokenForType(i);
        stringBuffer.append("EXTRACT").append('(').append(str2).append(' ').append("FROM").append(' ').append(this.nodes[1].getSQL()).append(')');
      case 7:
        stringBuffer.append("CHAR_LENGTH").append('(').append(this.nodes[0].getSQL()).append(')');
      case 6:
        stringBuffer.append("BIT_LENGTH").append('(').append(this.nodes[0].getSQL()).append(')');
      case 8:
        stringBuffer.append("OCTET_LENGTH").append('(').append(this.nodes[0].getSQL()).append(')');
      case 9:
        stringBuffer.append("CARDINALITY").append('(').append(this.nodes[0].getSQL()).append(')');
      case 10:
        stringBuffer.append("MAX_CARDINALITY").append('(').append(this.nodes[0].getSQL()).append(')');
      case 11:
        stringBuffer.append("TRIM_ARRAY").append('(').append(this.nodes[0].getSQL()).append(',').append(this.nodes[1].getSQL()).append(')');
      case 12:
        stringBuffer.append("ABS").append('(').append(this.nodes[0].getSQL()).append(')');
      case 13:
        stringBuffer.append("MOD").append('(').append(this.nodes[0].getSQL()).append(',').append(this.nodes[1].getSQL()).append(')');
      case 14:
        stringBuffer.append("LN").append('(').append(this.nodes[0].getSQL()).append(')');
      case 15:
        stringBuffer.append("EXP").append('(').append(this.nodes[0].getSQL()).append(')');
      case 16:
        stringBuffer.append("POWER").append('(').append(this.nodes[0].getSQL()).append(',').append(this.nodes[1].getSQL()).append(')');
      case 17:
        stringBuffer.append("SQRT").append('(').append(this.nodes[0].getSQL()).append(')');
      case 20:
        stringBuffer.append("FLOOR").append('(').append(this.nodes[0].getSQL()).append(')');
      case 21:
        stringBuffer.append("CEILING").append('(').append(this.nodes[0].getSQL()).append(')');
      case 22:
        stringBuffer.append("WIDTH_BUCKET").append('(').append(this.nodes[0].getSQL()).append(',').append(this.nodes[1].getSQL()).append(',').append(this.nodes[2].getSQL()).append(',').append(this.nodes[3].getSQL()).append(')');
      case 23:
      case 40:
        stringBuffer.append("SUBSTRING").append('(').append(this.nodes[0].getSQL()).append(' ').append("FROM").append(' ').append(this.nodes[1].getSQL());
        if (this.nodes[2] != null)
          stringBuffer.append(' ').append("FOR").append(' ').append(this.nodes[2].getSQL()); 
        if (this.nodes.length > 3 && this.nodes[3] != null && Boolean.TRUE.equals((this.nodes[3]).valueData))
          stringBuffer.append(' ').append("USING").append(' ').append("OCTETS"); 
        stringBuffer.append(')');
      case 26:
        stringBuffer.append("LOWER").append('(').append(this.nodes[0].getSQL()).append(')');
      case 27:
        stringBuffer.append("UPPER").append('(').append(this.nodes[0].getSQL()).append(')');
      case 32:
      case 42:
        stringBuffer.append("OVERLAY").append('(').append(this.nodes[0].getSQL()).append(' ').append("PLACING").append(' ').append(this.nodes[1].getSQL()).append(' ').append("FROM").append(' ').append(this.nodes[2].getSQL());
        if (this.nodes[3] != null)
          stringBuffer.append(' ').append("FOR").append(' ').append(this.nodes[3].getSQL()); 
        if (this.nodes[4] != null && Boolean.TRUE.equals((this.nodes[4]).valueData))
          stringBuffer.append(' ').append("USING").append(' ').append("OCTETS"); 
        stringBuffer.append(')');
      case 31:
      case 41:
        str1 = null;
        switch (((Number)(this.nodes[0]).valueData).intValue()) {
          case 23:
            str1 = "BOTH";
            break;
          case 151:
            str1 = "LEADING";
            break;
          case 286:
            str1 = "TRAILING";
            break;
        } 
        stringBuffer.append("TRIM").append('(').append(str1).append(' ').append(this.nodes[1].getSQL()).append(' ').append("FROM").append(' ').append(this.nodes[2].getSQL()).append(')');
      case 43:
      case 53:
      case 54:
      case 55:
      case 56:
      case 57:
      case 58:
      case 59:
      case 60:
      case 61:
      case 62:
      case 63:
        return this.name;
      case 44:
      case 51:
        j = 0;
        if (this.nodes.length > 0 && this.nodes[0] != null)
          j = ((Number)(this.nodes[0]).valueData).intValue(); 
        if (j == 0)
          return this.name; 
        stringBuffer.append(this.name).append("(").append(j);
        stringBuffer.append(")");
        return stringBuffer.toString();
      case 50:
      case 52:
        j = 6;
        if (this.nodes.length > 0 && this.nodes[0] != null)
          j = ((Number)(this.nodes[0]).valueData).intValue(); 
        if (j == 6)
          return this.name; 
        stringBuffer.append(this.name).append("(").append(j);
        stringBuffer.append(")");
        return stringBuffer.toString();
    } 
    throw Error.runtimeError(201, "FunctionSQL");
  }
  
  public boolean equals(Expression paramExpression) {
    return (paramExpression instanceof FunctionSQL && this.funcType == ((FunctionSQL)paramExpression).funcType) ? super.equals(paramExpression) : false;
  }
  
  public int hashCode() {
    return this.opType + this.funcType;
  }
  
  public String describe(Session paramSession, int paramInt) {
    StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append('\n');
    byte b;
    for (b = 0; b < paramInt; b++)
      stringBuffer.append(' '); 
    stringBuffer.append("FUNCTION ").append("=[\n");
    stringBuffer.append(this.name).append("(");
    for (b = 0; b < this.nodes.length; b++) {
      if (this.nodes[b] != null)
        stringBuffer.append("[").append(this.nodes[b].describe(paramSession, paramInt)).append("]"); 
    } 
    stringBuffer.append(") returns ").append(this.dataType.getNameString());
    stringBuffer.append("]\n");
    return stringBuffer.toString();
  }
  
  public boolean isDeterministic() {
    return this.isDeterministic;
  }
  
  public boolean isValueFunction() {
    return this.isSQLValueFunction;
  }
  
  static {
    regularFuncMap.put("POSITION", 1);
    regularFuncMap.put("POSITION_REGEX", 4);
    regularFuncMap.put("EXTRACT", 5);
    regularFuncMap.put("BIT_LENGTH", 6);
    regularFuncMap.put("CHAR_LENGTH", 7);
    regularFuncMap.put("CHARACTER_LENGTH", 7);
    regularFuncMap.put("OCTET_LENGTH", 8);
    regularFuncMap.put("CARDINALITY", 9);
    regularFuncMap.put("MAX_CARDINALITY", 10);
    regularFuncMap.put("TRIM_ARRAY", 11);
    regularFuncMap.put("ABS", 12);
    regularFuncMap.put("MOD", 13);
    regularFuncMap.put("LN", 14);
    regularFuncMap.put("EXP", 15);
    regularFuncMap.put("POWER", 16);
    regularFuncMap.put("SQRT", 17);
    regularFuncMap.put("FLOOR", 20);
    regularFuncMap.put("CEILING", 21);
    regularFuncMap.put("CEIL", 21);
    regularFuncMap.put("WIDTH_BUCKET", 22);
    regularFuncMap.put("SUBSTRING", 23);
    regularFuncMap.put("SUBSTRING_REGEX", 25);
    regularFuncMap.put("LOWER", 26);
    regularFuncMap.put("UPPER", 27);
    regularFuncMap.put("TRIM", 31);
    regularFuncMap.put("OVERLAY", 32);
    regularFuncMap.put("TRIM", 41);
    valueFuncMap.put("CURRENT_DATE", 43);
    valueFuncMap.put("CURRENT_TIME", 44);
    valueFuncMap.put("CURRENT_TIMESTAMP", 50);
    valueFuncMap.put("LOCALTIME", 51);
    valueFuncMap.put("LOCALTIMESTAMP", 52);
    valueFuncMap.put("CURRENT_CATALOG", 53);
    valueFuncMap.put("CURRENT_PATH", 55);
    valueFuncMap.put("CURRENT_ROLE", 56);
    valueFuncMap.put("CURRENT_SCHEMA", 57);
    valueFuncMap.put("CURRENT_USER", 59);
    valueFuncMap.put("SESSION_USER", 60);
    valueFuncMap.put("SYSTEM_USER", 61);
    valueFuncMap.put("USER", 62);
    valueFuncMap.put("VALUE", 63);
    nonDeterministicFuncSet.addAll(valueFuncMap.values());
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\FunctionSQL.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */