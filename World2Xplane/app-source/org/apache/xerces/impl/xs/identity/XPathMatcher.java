package org.apache.xerces.impl.xs.identity;

import org.apache.xerces.impl.xpath.XPath;
import org.apache.xerces.util.IntStack;
import org.apache.xerces.xni.QName;
import org.apache.xerces.xni.XMLAttributes;
import org.apache.xerces.xs.ShortList;
import org.apache.xerces.xs.XSTypeDefinition;

public class XPathMatcher {
  protected static final boolean DEBUG_ALL = false;
  
  protected static final boolean DEBUG_METHODS = false;
  
  protected static final boolean DEBUG_METHODS2 = false;
  
  protected static final boolean DEBUG_METHODS3 = false;
  
  protected static final boolean DEBUG_MATCH = false;
  
  protected static final boolean DEBUG_STACK = false;
  
  protected static final boolean DEBUG_ANY = false;
  
  protected static final int MATCHED = 1;
  
  protected static final int MATCHED_ATTRIBUTE = 3;
  
  protected static final int MATCHED_DESCENDANT = 5;
  
  protected static final int MATCHED_DESCENDANT_PREVIOUS = 13;
  
  private final XPath.LocationPath[] fLocationPaths;
  
  private final int[] fMatched;
  
  protected Object fMatchedString;
  
  private final IntStack[] fStepIndexes;
  
  private final int[] fCurrentStep;
  
  private final int[] fNoMatchDepth;
  
  final QName fQName = new QName();
  
  public XPathMatcher(XPath paramXPath) {
    this.fLocationPaths = paramXPath.getLocationPaths();
    this.fStepIndexes = new IntStack[this.fLocationPaths.length];
    for (byte b = 0; b < this.fStepIndexes.length; b++)
      this.fStepIndexes[b] = new IntStack(); 
    this.fCurrentStep = new int[this.fLocationPaths.length];
    this.fNoMatchDepth = new int[this.fLocationPaths.length];
    this.fMatched = new int[this.fLocationPaths.length];
  }
  
  public boolean isMatched() {
    for (byte b = 0; b < this.fLocationPaths.length; b++) {
      if ((this.fMatched[b] & 0x1) == 1 && (this.fMatched[b] & 0xD) != 13 && (this.fNoMatchDepth[b] == 0 || (this.fMatched[b] & 0x5) == 5))
        return true; 
    } 
    return false;
  }
  
  protected void handleContent(XSTypeDefinition paramXSTypeDefinition, boolean paramBoolean, Object paramObject, short paramShort, ShortList paramShortList) {}
  
  protected void matched(Object paramObject, short paramShort, ShortList paramShortList, boolean paramBoolean) {}
  
  public void startDocumentFragment() {
    this.fMatchedString = null;
    for (byte b = 0; b < this.fLocationPaths.length; b++) {
      this.fStepIndexes[b].clear();
      this.fCurrentStep[b] = 0;
      this.fNoMatchDepth[b] = 0;
      this.fMatched[b] = 0;
    } 
  }
  
  public void startElement(QName paramQName, XMLAttributes paramXMLAttributes) {
    // Byte code:
    //   0: iconst_0
    //   1: istore_3
    //   2: goto -> 673
    //   5: aload_0
    //   6: getfield fCurrentStep : [I
    //   9: iload_3
    //   10: iaload
    //   11: istore #4
    //   13: aload_0
    //   14: getfield fStepIndexes : [Lorg/apache/xerces/util/IntStack;
    //   17: iload_3
    //   18: aaload
    //   19: iload #4
    //   21: invokevirtual push : (I)V
    //   24: aload_0
    //   25: getfield fMatched : [I
    //   28: iload_3
    //   29: iaload
    //   30: iconst_5
    //   31: iand
    //   32: iconst_1
    //   33: if_icmpeq -> 45
    //   36: aload_0
    //   37: getfield fNoMatchDepth : [I
    //   40: iload_3
    //   41: iaload
    //   42: ifle -> 58
    //   45: aload_0
    //   46: getfield fNoMatchDepth : [I
    //   49: iload_3
    //   50: dup2
    //   51: iaload
    //   52: iconst_1
    //   53: iadd
    //   54: iastore
    //   55: goto -> 670
    //   58: aload_0
    //   59: getfield fMatched : [I
    //   62: iload_3
    //   63: iaload
    //   64: iconst_5
    //   65: iand
    //   66: iconst_5
    //   67: if_icmpne -> 78
    //   70: aload_0
    //   71: getfield fMatched : [I
    //   74: iload_3
    //   75: bipush #13
    //   77: iastore
    //   78: aload_0
    //   79: getfield fLocationPaths : [Lorg/apache/xerces/impl/xpath/XPath$LocationPath;
    //   82: iload_3
    //   83: aaload
    //   84: getfield steps : [Lorg/apache/xerces/impl/xpath/XPath$Step;
    //   87: astore #5
    //   89: goto -> 102
    //   92: aload_0
    //   93: getfield fCurrentStep : [I
    //   96: iload_3
    //   97: dup2
    //   98: iaload
    //   99: iconst_1
    //   100: iadd
    //   101: iastore
    //   102: aload_0
    //   103: getfield fCurrentStep : [I
    //   106: iload_3
    //   107: iaload
    //   108: aload #5
    //   110: arraylength
    //   111: if_icmpge -> 133
    //   114: aload #5
    //   116: aload_0
    //   117: getfield fCurrentStep : [I
    //   120: iload_3
    //   121: iaload
    //   122: aaload
    //   123: getfield axis : Lorg/apache/xerces/impl/xpath/XPath$Axis;
    //   126: getfield type : S
    //   129: iconst_3
    //   130: if_icmpeq -> 92
    //   133: aload_0
    //   134: getfield fCurrentStep : [I
    //   137: iload_3
    //   138: iaload
    //   139: aload #5
    //   141: arraylength
    //   142: if_icmpne -> 155
    //   145: aload_0
    //   146: getfield fMatched : [I
    //   149: iload_3
    //   150: iconst_1
    //   151: iastore
    //   152: goto -> 670
    //   155: aload_0
    //   156: getfield fCurrentStep : [I
    //   159: iload_3
    //   160: iaload
    //   161: istore #6
    //   163: goto -> 176
    //   166: aload_0
    //   167: getfield fCurrentStep : [I
    //   170: iload_3
    //   171: dup2
    //   172: iaload
    //   173: iconst_1
    //   174: iadd
    //   175: iastore
    //   176: aload_0
    //   177: getfield fCurrentStep : [I
    //   180: iload_3
    //   181: iaload
    //   182: aload #5
    //   184: arraylength
    //   185: if_icmpge -> 207
    //   188: aload #5
    //   190: aload_0
    //   191: getfield fCurrentStep : [I
    //   194: iload_3
    //   195: iaload
    //   196: aaload
    //   197: getfield axis : Lorg/apache/xerces/impl/xpath/XPath$Axis;
    //   200: getfield type : S
    //   203: iconst_4
    //   204: if_icmpeq -> 166
    //   207: aload_0
    //   208: getfield fCurrentStep : [I
    //   211: iload_3
    //   212: iaload
    //   213: iload #6
    //   215: if_icmple -> 222
    //   218: iconst_1
    //   219: goto -> 223
    //   222: iconst_0
    //   223: istore #7
    //   225: aload_0
    //   226: getfield fCurrentStep : [I
    //   229: iload_3
    //   230: iaload
    //   231: aload #5
    //   233: arraylength
    //   234: if_icmpne -> 250
    //   237: aload_0
    //   238: getfield fNoMatchDepth : [I
    //   241: iload_3
    //   242: dup2
    //   243: iaload
    //   244: iconst_1
    //   245: iadd
    //   246: iastore
    //   247: goto -> 670
    //   250: aload_0
    //   251: getfield fCurrentStep : [I
    //   254: iload_3
    //   255: iaload
    //   256: iload #4
    //   258: if_icmpeq -> 272
    //   261: aload_0
    //   262: getfield fCurrentStep : [I
    //   265: iload_3
    //   266: iaload
    //   267: iload #6
    //   269: if_icmple -> 363
    //   272: aload #5
    //   274: aload_0
    //   275: getfield fCurrentStep : [I
    //   278: iload_3
    //   279: iaload
    //   280: aaload
    //   281: getfield axis : Lorg/apache/xerces/impl/xpath/XPath$Axis;
    //   284: getfield type : S
    //   287: iconst_1
    //   288: if_icmpne -> 363
    //   291: aload #5
    //   293: aload_0
    //   294: getfield fCurrentStep : [I
    //   297: iload_3
    //   298: iaload
    //   299: aaload
    //   300: astore #8
    //   302: aload #8
    //   304: getfield nodeTest : Lorg/apache/xerces/impl/xpath/XPath$NodeTest;
    //   307: astore #9
    //   309: aload #9
    //   311: aload_1
    //   312: invokestatic matches : (Lorg/apache/xerces/impl/xpath/XPath$NodeTest;Lorg/apache/xerces/xni/QName;)Z
    //   315: ifne -> 353
    //   318: aload_0
    //   319: getfield fCurrentStep : [I
    //   322: iload_3
    //   323: iaload
    //   324: iload #6
    //   326: if_icmple -> 340
    //   329: aload_0
    //   330: getfield fCurrentStep : [I
    //   333: iload_3
    //   334: iload #6
    //   336: iastore
    //   337: goto -> 670
    //   340: aload_0
    //   341: getfield fNoMatchDepth : [I
    //   344: iload_3
    //   345: dup2
    //   346: iaload
    //   347: iconst_1
    //   348: iadd
    //   349: iastore
    //   350: goto -> 670
    //   353: aload_0
    //   354: getfield fCurrentStep : [I
    //   357: iload_3
    //   358: dup2
    //   359: iaload
    //   360: iconst_1
    //   361: iadd
    //   362: iastore
    //   363: aload_0
    //   364: getfield fCurrentStep : [I
    //   367: iload_3
    //   368: iaload
    //   369: aload #5
    //   371: arraylength
    //   372: if_icmpne -> 408
    //   375: iload #7
    //   377: ifeq -> 398
    //   380: aload_0
    //   381: getfield fCurrentStep : [I
    //   384: iload_3
    //   385: iload #6
    //   387: iastore
    //   388: aload_0
    //   389: getfield fMatched : [I
    //   392: iload_3
    //   393: iconst_5
    //   394: iastore
    //   395: goto -> 670
    //   398: aload_0
    //   399: getfield fMatched : [I
    //   402: iload_3
    //   403: iconst_1
    //   404: iastore
    //   405: goto -> 670
    //   408: aload_0
    //   409: getfield fCurrentStep : [I
    //   412: iload_3
    //   413: iaload
    //   414: aload #5
    //   416: arraylength
    //   417: if_icmpge -> 670
    //   420: aload #5
    //   422: aload_0
    //   423: getfield fCurrentStep : [I
    //   426: iload_3
    //   427: iaload
    //   428: aaload
    //   429: getfield axis : Lorg/apache/xerces/impl/xpath/XPath$Axis;
    //   432: getfield type : S
    //   435: iconst_2
    //   436: if_icmpne -> 670
    //   439: aload_2
    //   440: invokeinterface getLength : ()I
    //   445: istore #8
    //   447: iload #8
    //   449: ifle -> 626
    //   452: aload #5
    //   454: aload_0
    //   455: getfield fCurrentStep : [I
    //   458: iload_3
    //   459: iaload
    //   460: aaload
    //   461: getfield nodeTest : Lorg/apache/xerces/impl/xpath/XPath$NodeTest;
    //   464: astore #9
    //   466: iconst_0
    //   467: istore #10
    //   469: goto -> 619
    //   472: aload_2
    //   473: iload #10
    //   475: aload_0
    //   476: getfield fQName : Lorg/apache/xerces/xni/QName;
    //   479: invokeinterface getName : (ILorg/apache/xerces/xni/QName;)V
    //   484: aload #9
    //   486: aload_0
    //   487: getfield fQName : Lorg/apache/xerces/xni/QName;
    //   490: invokestatic matches : (Lorg/apache/xerces/impl/xpath/XPath$NodeTest;Lorg/apache/xerces/xni/QName;)Z
    //   493: ifeq -> 616
    //   496: aload_0
    //   497: getfield fCurrentStep : [I
    //   500: iload_3
    //   501: dup2
    //   502: iaload
    //   503: iconst_1
    //   504: iadd
    //   505: iastore
    //   506: aload_0
    //   507: getfield fCurrentStep : [I
    //   510: iload_3
    //   511: iaload
    //   512: aload #5
    //   514: arraylength
    //   515: if_icmpne -> 626
    //   518: aload_0
    //   519: getfield fMatched : [I
    //   522: iload_3
    //   523: iconst_3
    //   524: iastore
    //   525: iconst_0
    //   526: istore #11
    //   528: goto -> 534
    //   531: iinc #11, 1
    //   534: iload #11
    //   536: iload_3
    //   537: if_icmpge -> 553
    //   540: aload_0
    //   541: getfield fMatched : [I
    //   544: iload #11
    //   546: iaload
    //   547: iconst_1
    //   548: iand
    //   549: iconst_1
    //   550: if_icmpne -> 531
    //   553: iload #11
    //   555: iload_3
    //   556: if_icmpne -> 626
    //   559: aload_2
    //   560: iload #10
    //   562: invokeinterface getAugmentations : (I)Lorg/apache/xerces/xni/Augmentations;
    //   567: ldc 'ATTRIBUTE_PSVI'
    //   569: invokeinterface getItem : (Ljava/lang/String;)Ljava/lang/Object;
    //   574: checkcast org/apache/xerces/xs/AttributePSVI
    //   577: astore #12
    //   579: aload_0
    //   580: aload #12
    //   582: invokeinterface getActualNormalizedValue : ()Ljava/lang/Object;
    //   587: putfield fMatchedString : Ljava/lang/Object;
    //   590: aload_0
    //   591: aload_0
    //   592: getfield fMatchedString : Ljava/lang/Object;
    //   595: aload #12
    //   597: invokeinterface getActualNormalizedValueType : ()S
    //   602: aload #12
    //   604: invokeinterface getItemValueTypes : ()Lorg/apache/xerces/xs/ShortList;
    //   609: iconst_0
    //   610: invokevirtual matched : (Ljava/lang/Object;SLorg/apache/xerces/xs/ShortList;Z)V
    //   613: goto -> 626
    //   616: iinc #10, 1
    //   619: iload #10
    //   621: iload #8
    //   623: if_icmplt -> 472
    //   626: aload_0
    //   627: getfield fMatched : [I
    //   630: iload_3
    //   631: iaload
    //   632: iconst_1
    //   633: iand
    //   634: iconst_1
    //   635: if_icmpeq -> 670
    //   638: aload_0
    //   639: getfield fCurrentStep : [I
    //   642: iload_3
    //   643: iaload
    //   644: iload #6
    //   646: if_icmple -> 660
    //   649: aload_0
    //   650: getfield fCurrentStep : [I
    //   653: iload_3
    //   654: iload #6
    //   656: iastore
    //   657: goto -> 670
    //   660: aload_0
    //   661: getfield fNoMatchDepth : [I
    //   664: iload_3
    //   665: dup2
    //   666: iaload
    //   667: iconst_1
    //   668: iadd
    //   669: iastore
    //   670: iinc #3, 1
    //   673: iload_3
    //   674: aload_0
    //   675: getfield fLocationPaths : [Lorg/apache/xerces/impl/xpath/XPath$LocationPath;
    //   678: arraylength
    //   679: if_icmplt -> 5
    //   682: return
  }
  
  public void endElement(QName paramQName, XSTypeDefinition paramXSTypeDefinition, boolean paramBoolean, Object paramObject, short paramShort, ShortList paramShortList) {
    for (byte b = 0; b < this.fLocationPaths.length; b++) {
      this.fCurrentStep[b] = this.fStepIndexes[b].pop();
      if (this.fNoMatchDepth[b] > 0) {
        this.fNoMatchDepth[b] = this.fNoMatchDepth[b] - 1;
      } else {
        byte b1;
        for (b1 = 0; b1 < b && (this.fMatched[b1] & 0x1) != 1; b1++);
        if (b1 >= b && this.fMatched[b1] != 0)
          if ((this.fMatched[b1] & 0x3) == 3) {
            this.fMatched[b] = 0;
          } else {
            handleContent(paramXSTypeDefinition, paramBoolean, paramObject, paramShort, paramShortList);
            this.fMatched[b] = 0;
          }  
      } 
    } 
  }
  
  public String toString() {
    StringBuffer stringBuffer = new StringBuffer();
    String str = super.toString();
    int i = str.lastIndexOf('.');
    if (i != -1)
      str = str.substring(i + 1); 
    stringBuffer.append(str);
    for (byte b = 0; b < this.fLocationPaths.length; b++) {
      stringBuffer.append('[');
      XPath.Step[] arrayOfStep = (this.fLocationPaths[b]).steps;
      for (byte b1 = 0; b1 < arrayOfStep.length; b1++) {
        if (b1 == this.fCurrentStep[b])
          stringBuffer.append('^'); 
        stringBuffer.append(arrayOfStep[b1].toString());
        if (b1 < arrayOfStep.length - 1)
          stringBuffer.append('/'); 
      } 
      if (this.fCurrentStep[b] == arrayOfStep.length)
        stringBuffer.append('^'); 
      stringBuffer.append(']');
      stringBuffer.append(',');
    } 
    return stringBuffer.toString();
  }
  
  private String normalize(String paramString) {
    StringBuffer stringBuffer = new StringBuffer();
    int i = paramString.length();
    for (byte b = 0; b < i; b++) {
      char c = paramString.charAt(b);
      switch (c) {
        case '\n':
          stringBuffer.append("\\n");
          break;
        default:
          stringBuffer.append(c);
          break;
      } 
    } 
    return stringBuffer.toString();
  }
  
  private static boolean matches(XPath.NodeTest paramNodeTest, QName paramQName) {
    return (paramNodeTest.type == 1) ? paramNodeTest.name.equals(paramQName) : ((paramNodeTest.type == 4) ? ((paramNodeTest.name.uri == paramQName.uri)) : true);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\impl\xs\identity\XPathMatcher.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */