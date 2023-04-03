package org.hsqldb.types;

import java.text.Collator;
import java.util.Locale;
import org.hsqldb.HsqlException;
import org.hsqldb.HsqlNameManager;
import org.hsqldb.SchemaObject;
import org.hsqldb.Session;
import org.hsqldb.error.Error;
import org.hsqldb.lib.HashMap;
import org.hsqldb.lib.Iterator;
import org.hsqldb.lib.OrderedHashSet;
import org.hsqldb.lib.StringUtil;
import org.hsqldb.rights.Grantee;

public class Collation implements SchemaObject {
  static String defaultCollationName = "SQL_TEXT";
  
  static String defaultIgnoreCaseCollationName = "SQL_TEXT_UCC";
  
  public static final HashMap nameToJavaName = new HashMap(101);
  
  public static final HashMap dbNameToJavaName = new HashMap(101);
  
  public static final HashMap dbNameToCollation = new HashMap(11);
  
  static final Collation defaultCollation = new Collation(true);
  
  static final Collation defaultIgnoreCaseCollation = new Collation(false);
  
  final HsqlNameManager.HsqlName name;
  
  private Collator collator;
  
  private Locale locale;
  
  private boolean isUnicodeSimple;
  
  private boolean isUpperCaseCompare;
  
  private boolean isFinal;
  
  private boolean padSpace = true;
  
  private Charset charset;
  
  private HsqlNameManager.HsqlName sourceName;
  
  private Collation(boolean paramBoolean) {
    String str = paramBoolean ? defaultCollationName : defaultIgnoreCaseCollationName;
    this.locale = Locale.ENGLISH;
    this.name = HsqlNameManager.newInfoSchemaObjectName(str, false, 15);
    this.isUnicodeSimple = paramBoolean;
    this.isFinal = true;
  }
  
  private Collation(String paramString1, String paramString2, String paramString3, int paramInt1, int paramInt2, boolean paramBoolean) {
    this.locale = new Locale(paramString2, paramString3);
    this.collator = Collator.getInstance(this.locale);
    if (paramInt1 >= 0)
      this.collator.setStrength(paramInt1); 
    if (paramInt2 >= 0)
      this.collator.setDecomposition(paramInt2); 
    paramInt1 = this.collator.getStrength();
    this.isUnicodeSimple = false;
    this.name = HsqlNameManager.newInfoSchemaObjectName(paramString1, true, 15);
    this.charset = Charset.SQL_TEXT;
    this.isUpperCaseCompare = paramBoolean;
    this.isFinal = true;
  }
  
  public Collation(HsqlNameManager.HsqlName paramHsqlName, Collation paramCollation, Charset paramCharset, Boolean paramBoolean) {
    this.name = paramHsqlName;
    this.locale = paramCollation.locale;
    this.collator = paramCollation.collator;
    this.isUnicodeSimple = paramCollation.isUnicodeSimple;
    this.isFinal = true;
    this.charset = paramCharset;
    this.sourceName = paramCollation.name;
    if (paramBoolean != null)
      this.padSpace = paramBoolean.booleanValue(); 
  }
  
  public static Collation getDefaultInstance() {
    return defaultCollation;
  }
  
  public static Collation getDefaultIgnoreCaseInstance() {
    return defaultIgnoreCaseCollation;
  }
  
  public static Collation newDatabaseInstance() {
    Collation collation = new Collation(true);
    collation.isFinal = false;
    return collation;
  }
  
  public static Iterator getCollationsIterator() {
    return nameToJavaName.keySet().iterator();
  }
  
  public static Iterator getLocalesIterator() {
    return nameToJavaName.values().iterator();
  }
  
  public static synchronized Collation getCollation(String paramString) {
    Collation collation = (Collation)dbNameToCollation.get(paramString);
    if (collation != null)
      return collation; 
    collation = getNewCollation(paramString);
    dbNameToCollation.put(paramString, collation);
    return collation;
  }
  
  public static synchronized Collation getUpperCaseCompareCollation(Collation paramCollation) {
    if (defaultCollationName.equals(paramCollation.name.name) || defaultIgnoreCaseCollationName.equals(paramCollation.name.name))
      return defaultIgnoreCaseCollation; 
    if (paramCollation.isUpperCaseCompare)
      return paramCollation; 
    String str = (paramCollation.getName()).name;
    if (str.contains(" UCC"))
      return paramCollation; 
    str = str + " UCC";
    return getCollation(str);
  }
  
  private static Collation getNewCollation(String paramString) {
    int i = -1;
    int j = -1;
    boolean bool = false;
    String[] arrayOfString = StringUtil.split(paramString, " ");
    String str1 = arrayOfString[0];
    byte b = 1;
    int k = arrayOfString.length;
    if (arrayOfString.length > b && "UCC".equals(arrayOfString[k - 1])) {
      bool = true;
      k--;
    } 
    if (b < k) {
      i = Integer.valueOf(arrayOfString[b]).intValue();
      b++;
    } 
    if (b < k) {
      j = Integer.valueOf(arrayOfString[b]).intValue();
      b++;
    } 
    if (b < k)
      throw Error.error(5501, paramString); 
    String str2 = (String)dbNameToJavaName.get(str1);
    if (str2 == null) {
      str2 = (String)nameToJavaName.get(str1);
      if (str2 == null)
        throw Error.error(5501, str2); 
    } 
    arrayOfString = StringUtil.split(str2, "-");
    String str3 = arrayOfString[0];
    String str4 = (arrayOfString.length == 2) ? arrayOfString[1] : "";
    return new Collation(paramString, str3, str4, i, j, bool);
  }
  
  public void setPadding(boolean paramBoolean) {
    if (this.isFinal)
      throw Error.error(5503); 
    this.padSpace = paramBoolean;
  }
  
  public void setCollationAsLocale() {
    Locale locale = Locale.getDefault();
    String str = locale.getDisplayLanguage(Locale.ENGLISH);
    try {
      setCollation(str, Boolean.valueOf(false));
    } catch (HsqlException hsqlException) {}
  }
  
  public void setCollation(String paramString, Boolean paramBoolean) {
    if (this.isFinal)
      throw Error.error(5503, paramString); 
    Collation collation = getCollation(paramString);
    this.name.rename(collation.name.name, true);
    this.locale = collation.locale;
    this.collator = collation.collator;
    this.isUnicodeSimple = collation.isUnicodeSimple;
    this.padSpace = paramBoolean.booleanValue();
  }
  
  public boolean isPadSpace() {
    return this.padSpace;
  }
  
  public boolean isUnicodeSimple() {
    return this.isUnicodeSimple;
  }
  
  public boolean isUpperCaseCompare() {
    return this.isUpperCaseCompare;
  }
  
  public boolean isCaseSensitive() {
    return (this.collator == null) ? this.isUnicodeSimple : (!this.isUpperCaseCompare);
  }
  
  public int compare(String paramString1, String paramString2) {
    int i;
    if (this.collator == null) {
      if (this.isUnicodeSimple) {
        i = paramString1.compareTo(paramString2);
      } else {
        i = paramString1.compareToIgnoreCase(paramString2);
      } 
    } else if (this.isUpperCaseCompare) {
      i = this.collator.compare(toUpperCase(paramString1), toUpperCase(paramString2));
    } else {
      i = this.collator.compare(paramString1, paramString2);
    } 
    return (i == 0) ? 0 : ((i < 0) ? -1 : 1);
  }
  
  public String toUpperCase(String paramString) {
    return paramString.toUpperCase(this.locale);
  }
  
  public String toLowerCase(String paramString) {
    return paramString.toLowerCase(this.locale);
  }
  
  public boolean isDefaultCollation() {
    return (this.collator == null && (this.isUnicodeSimple & this.padSpace) != 0);
  }
  
  public boolean isObjectCollation() {
    return this.isFinal;
  }
  
  public HsqlNameManager.HsqlName getName() {
    return this.name;
  }
  
  public int getType() {
    return 15;
  }
  
  public HsqlNameManager.HsqlName getSchemaName() {
    return this.name.schema;
  }
  
  public HsqlNameManager.HsqlName getCatalogName() {
    return this.name.schema.schema;
  }
  
  public Grantee getOwner() {
    return this.name.schema.owner;
  }
  
  public OrderedHashSet getReferences() {
    return new OrderedHashSet();
  }
  
  public OrderedHashSet getComponents() {
    return null;
  }
  
  public void compile(Session paramSession, SchemaObject paramSchemaObject) {}
  
  public String getSQL() {
    StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append("CREATE").append(' ');
    stringBuffer.append("COLLATION").append(' ');
    if ("INFORMATION_SCHEMA".equals(this.name.schema.name)) {
      stringBuffer.append(this.name.getStatementName());
    } else {
      stringBuffer.append(this.name.getSchemaQualifiedStatementName());
    } 
    stringBuffer.append(' ').append("FOR").append(' ');
    if ("INFORMATION_SCHEMA".equals(this.charset.name.schema.name)) {
      stringBuffer.append(this.charset.name.getStatementName());
    } else {
      stringBuffer.append(this.charset.name.getSchemaQualifiedStatementName());
    } 
    stringBuffer.append(' ').append("FROM").append(' ');
    stringBuffer.append(this.sourceName.statementName);
    stringBuffer.append(' ');
    if (!this.padSpace)
      stringBuffer.append("NO").append(' ').append("PAD"); 
    return stringBuffer.toString();
  }
  
  public long getChangeTimestamp() {
    return 0L;
  }
  
  public String getCollateSQL() {
    StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append("COLLATE").append(' ');
    stringBuffer.append((getName()).statementName);
    return stringBuffer.toString();
  }
  
  public String getDatabaseCollationSQL() {
    StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append("SET").append(' ');
    stringBuffer.append("DATABASE").append(' ');
    stringBuffer.append("COLLATION").append(' ');
    stringBuffer.append((getName()).statementName);
    stringBuffer.append(' ');
    if (!this.padSpace)
      stringBuffer.append("NO").append(' ').append("PAD"); 
    return stringBuffer.toString();
  }
  
  static {
    defaultCollation.charset = Charset.SQL_TEXT;
    defaultIgnoreCaseCollation.charset = Charset.SQL_TEXT;
    dbNameToCollation.put(defaultCollationName, defaultCollation);
    dbNameToCollation.put(defaultIgnoreCaseCollationName, defaultIgnoreCaseCollation);
  }
  
  static {
    nameToJavaName.put("Afrikaans", "af-ZA");
    nameToJavaName.put("Amharic", "am-ET");
    nameToJavaName.put("Arabic", "ar");
    nameToJavaName.put("Assamese", "as-IN");
    nameToJavaName.put("Azerbaijani_Latin", "az-AZ");
    nameToJavaName.put("Azerbaijani_Cyrillic", "az-cyrillic");
    nameToJavaName.put("Belarusian", "be-BY");
    nameToJavaName.put("Bulgarian", "bg-BG");
    nameToJavaName.put("Bengali", "bn-IN");
    nameToJavaName.put("Tibetan", "bo-CN");
    nameToJavaName.put("Bosnian", "bs-BA");
    nameToJavaName.put("Catalan", "ca-ES");
    nameToJavaName.put("Czech", "cs-CZ");
    nameToJavaName.put("Welsh", "cy-GB");
    nameToJavaName.put("Danish", "da-DK");
    nameToJavaName.put("German", "de-DE");
    nameToJavaName.put("Greek", "el-GR");
    nameToJavaName.put("Latin1_General", "en-US");
    nameToJavaName.put("English", "en-US");
    nameToJavaName.put("Spanish", "es-ES");
    nameToJavaName.put("Estonian", "et-EE");
    nameToJavaName.put("Basque", "eu");
    nameToJavaName.put("Finnish", "fi-FI");
    nameToJavaName.put("French", "fr-FR");
    nameToJavaName.put("Guarani", "gn-PY");
    nameToJavaName.put("Gujarati", "gu-IN");
    nameToJavaName.put("Hausa", "ha-NG");
    nameToJavaName.put("Hebrew", "he-IL");
    nameToJavaName.put("Hindi", "hi-IN");
    nameToJavaName.put("Croatian", "hr-HR");
    nameToJavaName.put("Hungarian", "hu-HU");
    nameToJavaName.put("Armenian", "hy-AM");
    nameToJavaName.put("Indonesian", "id-ID");
    nameToJavaName.put("Igbo", "ig-NG");
    nameToJavaName.put("Icelandic", "is-IS");
    nameToJavaName.put("Italian", "it-IT");
    nameToJavaName.put("Inuktitut", "iu-CA");
    nameToJavaName.put("Japanese", "ja-JP");
    nameToJavaName.put("Georgian", "ka-GE");
    nameToJavaName.put("Kazakh", "kk-KZ");
    nameToJavaName.put("Khmer", "km-KH");
    nameToJavaName.put("Kannada", "kn-IN");
    nameToJavaName.put("Korean", "ko-KR");
    nameToJavaName.put("Konkani", "kok-IN");
    nameToJavaName.put("Kashmiri", "ks");
    nameToJavaName.put("Kirghiz", "ky-KG");
    nameToJavaName.put("Lao", "lo-LA");
    nameToJavaName.put("Lithuanian", "lt-LT");
    nameToJavaName.put("Latvian", "lv-LV");
    nameToJavaName.put("Maori", "mi-NZ");
    nameToJavaName.put("Macedonian", "mk-MK");
    nameToJavaName.put("Malayalam", "ml-IN");
    nameToJavaName.put("Mongolian", "mn-MN");
    nameToJavaName.put("Manipuri", "mni-IN");
    nameToJavaName.put("Marathi", "mr-IN");
    nameToJavaName.put("Malay", "ms-MY");
    nameToJavaName.put("Maltese", "mt-MT");
    nameToJavaName.put("Burmese", "my-MM");
    nameToJavaName.put("Danish_Norwegian", "nb-NO");
    nameToJavaName.put("Nepali", "ne-NP");
    nameToJavaName.put("Dutch", "nl-NL");
    nameToJavaName.put("Norwegian", "nn-NO");
    nameToJavaName.put("Oriya", "or-IN");
    nameToJavaName.put("Punjabi", "pa-IN");
    nameToJavaName.put("Polish", "pl-PL");
    nameToJavaName.put("Pashto", "ps-AF");
    nameToJavaName.put("Portuguese", "pt-PT");
    nameToJavaName.put("Romanian", "ro-RO");
    nameToJavaName.put("Russian", "ru-RU");
    nameToJavaName.put("Sanskrit", "sa-IN");
    nameToJavaName.put("Sindhi", "sd-IN");
    nameToJavaName.put("Slovak", "sk-SK");
    nameToJavaName.put("Slovenian", "sl-SI");
    nameToJavaName.put("Somali", "so-SO");
    nameToJavaName.put("Albanian", "sq-AL");
    nameToJavaName.put("Serbian_Cyrillic", "sr-YU");
    nameToJavaName.put("Serbian_Latin", "sh-BA");
    nameToJavaName.put("Swedish", "sv-SE");
    nameToJavaName.put("Swahili", "sw-KE");
    nameToJavaName.put("Tamil", "ta-IN");
    nameToJavaName.put("Telugu", "te-IN");
    nameToJavaName.put("Tajik", "tg-TJ");
    nameToJavaName.put("Thai", "th-TH");
    nameToJavaName.put("Turkmen", "tk-TM");
    nameToJavaName.put("Tswana", "tn-BW");
    nameToJavaName.put("Turkish", "tr-TR");
    nameToJavaName.put("Tatar", "tt-RU");
    nameToJavaName.put("Ukrainian", "uk-UA");
    nameToJavaName.put("Urdu", "ur-PK");
    nameToJavaName.put("Uzbek_Latin", "uz-UZ");
    nameToJavaName.put("Venda", "ven-ZA");
    nameToJavaName.put("Vietnamese", "vi-VN");
    nameToJavaName.put("Yoruba", "yo-NG");
    nameToJavaName.put("Chinese", "zh-CN");
    nameToJavaName.put("Zulu", "zu-ZA");
    Iterator iterator = nameToJavaName.values().iterator();
    while (iterator.hasNext()) {
      String str1 = (String)iterator.next();
      String str2 = str1.replace('-', '_').toUpperCase(Locale.ENGLISH);
      dbNameToJavaName.put(str2, str1);
    } 
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\types\Collation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */