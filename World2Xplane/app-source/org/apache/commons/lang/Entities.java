/*      */ package org.apache.commons.lang;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.io.StringWriter;
/*      */ import java.io.Writer;
/*      */ import java.util.HashMap;
/*      */ import java.util.Map;
/*      */ import java.util.TreeMap;
/*      */ 
/*      */ class Entities {
/*   45 */   private static final String[][] BASIC_ARRAY = new String[][] { { "quot", "34" }, { "amp", "38" }, { "lt", "60" }, { "gt", "62" } };
/*      */   
/*   51 */   private static final String[][] APOS_ARRAY = new String[][] { { "apos", "39" } };
/*      */   
/*   55 */   static final String[][] ISO8859_1_ARRAY = new String[][] { 
/*   55 */       { "nbsp", "160" }, { "iexcl", "161" }, { "cent", "162" }, { "pound", "163" }, { "curren", "164" }, { "yen", "165" }, { "brvbar", "166" }, { "sect", "167" }, { "uml", "168" }, { "copy", "169" }, 
/*   55 */       { "ordf", "170" }, { "laquo", "171" }, { "not", "172" }, { "shy", "173" }, { "reg", "174" }, { "macr", "175" }, { "deg", "176" }, { "plusmn", "177" }, { "sup2", "178" }, { "sup3", "179" }, 
/*   55 */       { "acute", "180" }, { "micro", "181" }, { "para", "182" }, { "middot", "183" }, { "cedil", "184" }, { "sup1", "185" }, { "ordm", "186" }, { "raquo", "187" }, { "frac14", "188" }, { "frac12", "189" }, 
/*   55 */       { "frac34", "190" }, { "iquest", "191" }, { "Agrave", "192" }, { "Aacute", "193" }, { "Acirc", "194" }, { "Atilde", "195" }, { "Auml", "196" }, { "Aring", "197" }, { "AElig", "198" }, { "Ccedil", "199" }, 
/*   55 */       { "Egrave", "200" }, { "Eacute", "201" }, { "Ecirc", "202" }, { "Euml", "203" }, { "Igrave", "204" }, { "Iacute", "205" }, { "Icirc", "206" }, { "Iuml", "207" }, { "ETH", "208" }, { "Ntilde", "209" }, 
/*   55 */       { "Ograve", "210" }, { "Oacute", "211" }, { "Ocirc", "212" }, { "Otilde", "213" }, { "Ouml", "214" }, { "times", "215" }, { "Oslash", "216" }, { "Ugrave", "217" }, { "Uacute", "218" }, { "Ucirc", "219" }, 
/*   55 */       { "Uuml", "220" }, { "Yacute", "221" }, { "THORN", "222" }, { "szlig", "223" }, { "agrave", "224" }, { "aacute", "225" }, { "acirc", "226" }, { "atilde", "227" }, { "auml", "228" }, { "aring", "229" }, 
/*   55 */       { "aelig", "230" }, { "ccedil", "231" }, { "egrave", "232" }, { "eacute", "233" }, { "ecirc", "234" }, { "euml", "235" }, { "igrave", "236" }, { "iacute", "237" }, { "icirc", "238" }, { "iuml", "239" }, 
/*   55 */       { "eth", "240" }, { "ntilde", "241" }, { "ograve", "242" }, { "oacute", "243" }, { "ocirc", "244" }, { "otilde", "245" }, { "ouml", "246" }, { "divide", "247" }, { "oslash", "248" }, { "ugrave", "249" }, 
/*   55 */       { "uacute", "250" }, { "ucirc", "251" }, { "uuml", "252" }, { "yacute", "253" }, { "thorn", "254" }, { "yuml", "255" } };
/*      */   
/*  155 */   static final String[][] HTML40_ARRAY = new String[][] { 
/*  155 */       { "fnof", "402" }, { "Alpha", "913" }, { "Beta", "914" }, { "Gamma", "915" }, { "Delta", "916" }, { "Epsilon", "917" }, { "Zeta", "918" }, { "Eta", "919" }, { "Theta", "920" }, { "Iota", "921" }, 
/*  155 */       { "Kappa", "922" }, { "Lambda", "923" }, { "Mu", "924" }, { "Nu", "925" }, { "Xi", "926" }, { "Omicron", "927" }, { "Pi", "928" }, { "Rho", "929" }, { "Sigma", "931" }, { "Tau", "932" }, 
/*  155 */       { "Upsilon", "933" }, { "Phi", "934" }, { "Chi", "935" }, { "Psi", "936" }, { "Omega", "937" }, { "alpha", "945" }, { "beta", "946" }, { "gamma", "947" }, { "delta", "948" }, { "epsilon", "949" }, 
/*  155 */       { "zeta", "950" }, { "eta", "951" }, { "theta", "952" }, { "iota", "953" }, { "kappa", "954" }, { "lambda", "955" }, { "mu", "956" }, { "nu", "957" }, { "xi", "958" }, { "omicron", "959" }, 
/*  155 */       { "pi", "960" }, { "rho", "961" }, { "sigmaf", "962" }, { "sigma", "963" }, { "tau", "964" }, { "upsilon", "965" }, { "phi", "966" }, { "chi", "967" }, { "psi", "968" }, { "omega", "969" }, 
/*  155 */       { "thetasym", "977" }, { "upsih", "978" }, { "piv", "982" }, { "bull", "8226" }, { "hellip", "8230" }, { "prime", "8242" }, { "Prime", "8243" }, { "oline", "8254" }, { "frasl", "8260" }, { "weierp", "8472" }, 
/*  155 */       { "image", "8465" }, { "real", "8476" }, { "trade", "8482" }, { "alefsym", "8501" }, { "larr", "8592" }, { "uarr", "8593" }, { "rarr", "8594" }, { "darr", "8595" }, { "harr", "8596" }, { "crarr", "8629" }, 
/*  155 */       { "lArr", "8656" }, { "uArr", "8657" }, { "rArr", "8658" }, { "dArr", "8659" }, { "hArr", "8660" }, { "forall", "8704" }, { "part", "8706" }, { "exist", "8707" }, { "empty", "8709" }, { "nabla", "8711" }, 
/*  155 */       { "isin", "8712" }, { "notin", "8713" }, { "ni", "8715" }, { "prod", "8719" }, { "sum", "8721" }, { "minus", "8722" }, { "lowast", "8727" }, { "radic", "8730" }, { "prop", "8733" }, { "infin", "8734" }, 
/*  155 */       { "ang", "8736" }, { "and", "8743" }, { "or", "8744" }, { "cap", "8745" }, { "cup", "8746" }, { "int", "8747" }, { "there4", "8756" }, { "sim", "8764" }, { "cong", "8773" }, { "asymp", "8776" }, 
/*  155 */       { "ne", "8800" }, { "equiv", "8801" }, { "le", "8804" }, { "ge", "8805" }, { "sub", "8834" }, { "sup", "8835" }, { "sube", "8838" }, { "supe", "8839" }, { "oplus", "8853" }, { "otimes", "8855" }, 
/*  155 */       { "perp", "8869" }, { "sdot", "8901" }, { "lceil", "8968" }, { "rceil", "8969" }, { "lfloor", "8970" }, { "rfloor", "8971" }, { "lang", "9001" }, { "rang", "9002" }, { "loz", "9674" }, { "spades", "9824" }, 
/*  155 */       { "clubs", "9827" }, { "hearts", "9829" }, { "diams", "9830" }, { "OElig", "338" }, { "oelig", "339" }, { "Scaron", "352" }, { "scaron", "353" }, { "Yuml", "376" }, { "circ", "710" }, { "tilde", "732" }, 
/*  155 */       { "ensp", "8194" }, { "emsp", "8195" }, { "thinsp", "8201" }, { "zwnj", "8204" }, { "zwj", "8205" }, { "lrm", "8206" }, { "rlm", "8207" }, { "ndash", "8211" }, { "mdash", "8212" }, { "lsquo", "8216" }, 
/*  155 */       { "rsquo", "8217" }, { "sbquo", "8218" }, { "ldquo", "8220" }, { "rdquo", "8221" }, { "bdquo", "8222" }, { "dagger", "8224" }, { "Dagger", "8225" }, { "permil", "8240" }, { "lsaquo", "8249" }, { "rsaquo", "8250" }, 
/*  155 */       { "euro", "8364" } };
/*      */   
/*      */   public static final Entities XML;
/*      */   
/*      */   public static final Entities HTML32;
/*      */   
/*      */   public static final Entities HTML40;
/*      */   
/*      */   private final EntityMap map;
/*      */   
/*      */   static {
/*  374 */     Entities xml = new Entities();
/*  375 */     xml.addEntities(BASIC_ARRAY);
/*  376 */     xml.addEntities(APOS_ARRAY);
/*  377 */     XML = xml;
/*  381 */     Entities html32 = new Entities();
/*  382 */     html32.addEntities(BASIC_ARRAY);
/*  383 */     html32.addEntities(ISO8859_1_ARRAY);
/*  384 */     HTML32 = html32;
/*  388 */     Entities html40 = new Entities();
/*  389 */     fillWithHtml40Entities(html40);
/*  390 */     HTML40 = html40;
/*      */   }
/*      */   
/*      */   static void fillWithHtml40Entities(Entities entities) {
/*  402 */     entities.addEntities(BASIC_ARRAY);
/*  403 */     entities.addEntities(ISO8859_1_ARRAY);
/*  404 */     entities.addEntities(HTML40_ARRAY);
/*      */   }
/*      */   
/*      */   static interface EntityMap {
/*      */     void add(String param1String, int param1Int);
/*      */     
/*      */     String name(int param1Int);
/*      */     
/*      */     int value(String param1String);
/*      */   }
/*      */   
/*      */   static class PrimitiveEntityMap implements EntityMap {
/*  444 */     private final Map mapNameToValue = new HashMap();
/*      */     
/*  446 */     private final IntHashMap mapValueToName = new IntHashMap();
/*      */     
/*      */     public void add(String name, int value) {
/*  453 */       this.mapNameToValue.put(name, new Integer(value));
/*  454 */       this.mapValueToName.put(value, name);
/*      */     }
/*      */     
/*      */     public String name(int value) {
/*  461 */       return (String)this.mapValueToName.get(value);
/*      */     }
/*      */     
/*      */     public int value(String name) {
/*  468 */       Object value = this.mapNameToValue.get(name);
/*  469 */       if (value == null)
/*  470 */         return -1; 
/*  472 */       return ((Integer)value).intValue();
/*      */     }
/*      */   }
/*      */   
/*      */   static abstract class MapIntMap implements EntityMap {
/*      */     protected final Map mapNameToValue;
/*      */     
/*      */     protected final Map mapValueToName;
/*      */     
/*      */     MapIntMap(Map nameToValue, Map valueToName) {
/*  488 */       this.mapNameToValue = nameToValue;
/*  489 */       this.mapValueToName = valueToName;
/*      */     }
/*      */     
/*      */     public void add(String name, int value) {
/*  496 */       this.mapNameToValue.put(name, new Integer(value));
/*  497 */       this.mapValueToName.put(new Integer(value), name);
/*      */     }
/*      */     
/*      */     public String name(int value) {
/*  504 */       return (String)this.mapValueToName.get(new Integer(value));
/*      */     }
/*      */     
/*      */     public int value(String name) {
/*  511 */       Object value = this.mapNameToValue.get(name);
/*  512 */       if (value == null)
/*  513 */         return -1; 
/*  515 */       return ((Integer)value).intValue();
/*      */     }
/*      */   }
/*      */   
/*      */   static class HashEntityMap extends MapIntMap {
/*      */     public HashEntityMap() {
/*  524 */       super(new HashMap(), new HashMap());
/*      */     }
/*      */   }
/*      */   
/*      */   static class TreeEntityMap extends MapIntMap {
/*      */     public TreeEntityMap() {
/*  533 */       super(new TreeMap(), new TreeMap());
/*      */     }
/*      */   }
/*      */   
/*      */   static class LookupEntityMap extends PrimitiveEntityMap {
/*      */     private String[] lookupTable;
/*      */     
/*      */     private static final int LOOKUP_TABLE_SIZE = 256;
/*      */     
/*      */     public String name(int value) {
/*  547 */       if (value < 256)
/*  548 */         return lookupTable()[value]; 
/*  550 */       return super.name(value);
/*      */     }
/*      */     
/*      */     private String[] lookupTable() {
/*  561 */       if (this.lookupTable == null)
/*  562 */         createLookupTable(); 
/*  564 */       return this.lookupTable;
/*      */     }
/*      */     
/*      */     private void createLookupTable() {
/*  573 */       this.lookupTable = new String[256];
/*  574 */       for (int i = 0; i < 256; i++)
/*  575 */         this.lookupTable[i] = super.name(i); 
/*      */     }
/*      */   }
/*      */   
/*      */   static class ArrayEntityMap implements EntityMap {
/*      */     protected final int growBy;
/*      */     
/*  584 */     protected int size = 0;
/*      */     
/*      */     protected String[] names;
/*      */     
/*      */     protected int[] values;
/*      */     
/*      */     public ArrayEntityMap() {
/*  594 */       this.growBy = 100;
/*  595 */       this.names = new String[this.growBy];
/*  596 */       this.values = new int[this.growBy];
/*      */     }
/*      */     
/*      */     public ArrayEntityMap(int growBy) {
/*  607 */       this.growBy = growBy;
/*  608 */       this.names = new String[growBy];
/*  609 */       this.values = new int[growBy];
/*      */     }
/*      */     
/*      */     public void add(String name, int value) {
/*  616 */       ensureCapacity(this.size + 1);
/*  617 */       this.names[this.size] = name;
/*  618 */       this.values[this.size] = value;
/*  619 */       this.size++;
/*      */     }
/*      */     
/*      */     protected void ensureCapacity(int capacity) {
/*  629 */       if (capacity > this.names.length) {
/*  630 */         int newSize = Math.max(capacity, this.size + this.growBy);
/*  631 */         String[] newNames = new String[newSize];
/*  632 */         System.arraycopy(this.names, 0, newNames, 0, this.size);
/*  633 */         this.names = newNames;
/*  634 */         int[] newValues = new int[newSize];
/*  635 */         System.arraycopy(this.values, 0, newValues, 0, this.size);
/*  636 */         this.values = newValues;
/*      */       } 
/*      */     }
/*      */     
/*      */     public String name(int value) {
/*  644 */       for (int i = 0; i < this.size; i++) {
/*  645 */         if (this.values[i] == value)
/*  646 */           return this.names[i]; 
/*      */       } 
/*  649 */       return null;
/*      */     }
/*      */     
/*      */     public int value(String name) {
/*  656 */       for (int i = 0; i < this.size; i++) {
/*  657 */         if (this.names[i].equals(name))
/*  658 */           return this.values[i]; 
/*      */       } 
/*  661 */       return -1;
/*      */     }
/*      */   }
/*      */   
/*      */   static class BinaryEntityMap extends ArrayEntityMap {
/*      */     public BinaryEntityMap() {}
/*      */     
/*      */     public BinaryEntityMap(int growBy) {
/*  684 */       super(growBy);
/*      */     }
/*      */     
/*      */     private int binarySearch(int key) {
/*  696 */       int low = 0;
/*  697 */       int high = this.size - 1;
/*  699 */       while (low <= high) {
/*  700 */         int mid = low + high >>> 1;
/*  701 */         int midVal = this.values[mid];
/*  703 */         if (midVal < key) {
/*  704 */           low = mid + 1;
/*      */           continue;
/*      */         } 
/*  705 */         if (midVal > key) {
/*  706 */           high = mid - 1;
/*      */           continue;
/*      */         } 
/*  708 */         return mid;
/*      */       } 
/*  711 */       return -(low + 1);
/*      */     }
/*      */     
/*      */     public void add(String name, int value) {
/*  718 */       ensureCapacity(this.size + 1);
/*  719 */       int insertAt = binarySearch(value);
/*  720 */       if (insertAt > 0)
/*      */         return; 
/*  723 */       insertAt = -(insertAt + 1);
/*  724 */       System.arraycopy(this.values, insertAt, this.values, insertAt + 1, this.size - insertAt);
/*  725 */       this.values[insertAt] = value;
/*  726 */       System.arraycopy(this.names, insertAt, this.names, insertAt + 1, this.size - insertAt);
/*  727 */       this.names[insertAt] = name;
/*  728 */       this.size++;
/*      */     }
/*      */     
/*      */     public String name(int value) {
/*  735 */       int index = binarySearch(value);
/*  736 */       if (index < 0)
/*  737 */         return null; 
/*  739 */       return this.names[index];
/*      */     }
/*      */   }
/*      */   
/*      */   public Entities() {
/*  749 */     this.map = new LookupEntityMap();
/*      */   }
/*      */   
/*      */   Entities(EntityMap emap) {
/*  758 */     this.map = emap;
/*      */   }
/*      */   
/*      */   public void addEntities(String[][] entityArray) {
/*  770 */     for (int i = 0; i < entityArray.length; i++)
/*  771 */       addEntity(entityArray[i][0], Integer.parseInt(entityArray[i][1])); 
/*      */   }
/*      */   
/*      */   public void addEntity(String name, int value) {
/*  786 */     this.map.add(name, value);
/*      */   }
/*      */   
/*      */   public String entityName(int value) {
/*  799 */     return this.map.name(value);
/*      */   }
/*      */   
/*      */   public int entityValue(String name) {
/*  812 */     return this.map.value(name);
/*      */   }
/*      */   
/*      */   public String escape(String str) {
/*  830 */     StringWriter stringWriter = createStringWriter(str);
/*      */     try {
/*  832 */       escape(stringWriter, str);
/*  833 */     } catch (IOException e) {
/*  836 */       throw new UnhandledException(e);
/*      */     } 
/*  838 */     return stringWriter.toString();
/*      */   }
/*      */   
/*      */   public void escape(Writer writer, String str) throws IOException {
/*  859 */     int len = str.length();
/*  860 */     for (int i = 0; i < len; i++) {
/*  861 */       char c = str.charAt(i);
/*  862 */       String entityName = entityName(c);
/*  863 */       if (entityName == null) {
/*  864 */         if (c > '') {
/*  865 */           writer.write("&#");
/*  866 */           writer.write(Integer.toString(c, 10));
/*  867 */           writer.write(59);
/*      */         } else {
/*  869 */           writer.write(c);
/*      */         } 
/*      */       } else {
/*  872 */         writer.write(38);
/*  873 */         writer.write(entityName);
/*  874 */         writer.write(59);
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public String unescape(String str) {
/*  894 */     int firstAmp = str.indexOf('&');
/*  895 */     if (firstAmp < 0)
/*  896 */       return str; 
/*  898 */     StringWriter stringWriter = createStringWriter(str);
/*      */     try {
/*  900 */       doUnescape(stringWriter, str, firstAmp);
/*  901 */     } catch (IOException e) {
/*  904 */       throw new UnhandledException(e);
/*      */     } 
/*  906 */     return stringWriter.toString();
/*      */   }
/*      */   
/*      */   private StringWriter createStringWriter(String str) {
/*  917 */     return new StringWriter((int)(str.length() + str.length() * 0.1D));
/*      */   }
/*      */   
/*      */   public void unescape(Writer writer, String str) throws IOException {
/*  938 */     int firstAmp = str.indexOf('&');
/*  939 */     if (firstAmp < 0) {
/*  940 */       writer.write(str);
/*      */       return;
/*      */     } 
/*  943 */     doUnescape(writer, str, firstAmp);
/*      */   }
/*      */   
/*      */   private void doUnescape(Writer writer, String str, int firstAmp) throws IOException {
/*  961 */     writer.write(str, 0, firstAmp);
/*  962 */     int len = str.length();
/*  963 */     for (int i = firstAmp; i < len; i++) {
/*  964 */       char c = str.charAt(i);
/*  965 */       if (c == '&') {
/*  966 */         int nextIdx = i + 1;
/*  967 */         int semiColonIdx = str.indexOf(';', nextIdx);
/*  968 */         if (semiColonIdx == -1) {
/*  969 */           writer.write(c);
/*      */         } else {
/*  972 */           int amphersandIdx = str.indexOf('&', i + 1);
/*  973 */           if (amphersandIdx != -1 && amphersandIdx < semiColonIdx) {
/*  975 */             writer.write(c);
/*      */           } else {
/*  978 */             String entityContent = str.substring(nextIdx, semiColonIdx);
/*  979 */             int entityValue = -1;
/*  980 */             int entityContentLen = entityContent.length();
/*  981 */             if (entityContentLen > 0)
/*  982 */               if (entityContent.charAt(0) == '#') {
/*  984 */                 if (entityContentLen > 1) {
/*  985 */                   char isHexChar = entityContent.charAt(1);
/*      */                   try {
/*  987 */                     switch (isHexChar) {
/*      */                       case 'X':
/*      */                       case 'x':
/*  990 */                         entityValue = Integer.parseInt(entityContent.substring(2), 16);
/*      */                         break;
/*      */                       default:
/*  994 */                         entityValue = Integer.parseInt(entityContent.substring(1), 10);
/*      */                         break;
/*      */                     } 
/*  997 */                     if (entityValue > 65535)
/*  998 */                       entityValue = -1; 
/* 1000 */                   } catch (NumberFormatException e) {
/* 1001 */                     entityValue = -1;
/*      */                   } 
/*      */                 } 
/*      */               } else {
/* 1005 */                 entityValue = entityValue(entityContent);
/*      */               }  
/* 1009 */             if (entityValue == -1) {
/* 1010 */               writer.write(38);
/* 1011 */               writer.write(entityContent);
/* 1012 */               writer.write(59);
/*      */             } else {
/* 1014 */               writer.write(entityValue);
/*      */             } 
/* 1016 */             i = semiColonIdx;
/*      */           } 
/*      */         } 
/*      */       } else {
/* 1018 */         writer.write(c);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\lang\Entities.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */