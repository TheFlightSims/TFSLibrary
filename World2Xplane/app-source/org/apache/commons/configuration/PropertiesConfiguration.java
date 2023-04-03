/*      */ package org.apache.commons.configuration;
/*      */ 
/*      */ import java.io.File;
/*      */ import java.io.FilterWriter;
/*      */ import java.io.IOException;
/*      */ import java.io.LineNumberReader;
/*      */ import java.io.Reader;
/*      */ import java.io.Writer;
/*      */ import java.net.URL;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import org.apache.commons.lang.ArrayUtils;
/*      */ import org.apache.commons.lang.StringEscapeUtils;
/*      */ import org.apache.commons.lang.StringUtils;
/*      */ 
/*      */ public class PropertiesConfiguration extends AbstractFileConfiguration {
/*      */   static final String COMMENT_CHARS = "#!";
/*      */   
/*      */   static final String DEFAULT_SEPARATOR = " = ";
/*      */   
/*  187 */   private static final IOFactory DEFAULT_IO_FACTORY = new DefaultIOFactory();
/*      */   
/*  193 */   private static String include = "include";
/*      */   
/*  196 */   private static final char[] SEPARATORS = new char[] { '=', ':' };
/*      */   
/*  199 */   private static final char[] WHITE_SPACE = new char[] { ' ', '\t', '\f' };
/*      */   
/*      */   private static final String DEFAULT_ENCODING = "ISO-8859-1";
/*      */   
/*  208 */   private static final String LINE_SEPARATOR = System.getProperty("line.separator");
/*      */   
/*      */   private static final String ESCAPE = "\\";
/*      */   
/*      */   private static final String DOUBLE_ESC = "\\\\";
/*      */   
/*      */   private static final int HEX_RADIX = 16;
/*      */   
/*      */   private static final int UNICODE_LEN = 4;
/*      */   
/*      */   private PropertiesConfigurationLayout layout;
/*      */   
/*      */   private volatile IOFactory ioFactory;
/*      */   
/*      */   private boolean includesAllowed;
/*      */   
/*      */   public PropertiesConfiguration() {
/*  238 */     this.layout = createLayout();
/*  239 */     setIncludesAllowed(false);
/*      */   }
/*      */   
/*      */   public PropertiesConfiguration(String fileName) throws ConfigurationException {
/*  252 */     super(fileName);
/*      */   }
/*      */   
/*      */   public PropertiesConfiguration(File file) throws ConfigurationException {
/*  267 */     super(file);
/*  271 */     getLayout();
/*      */   }
/*      */   
/*      */   public PropertiesConfiguration(URL url) throws ConfigurationException {
/*  284 */     super(url);
/*      */   }
/*      */   
/*      */   public static String getInclude() {
/*  295 */     return include;
/*      */   }
/*      */   
/*      */   public static void setInclude(String inc) {
/*  306 */     include = inc;
/*      */   }
/*      */   
/*      */   protected void setIncludesAllowed(boolean includesAllowed) {
/*  318 */     this.includesAllowed = includesAllowed;
/*      */   }
/*      */   
/*      */   public boolean getIncludesAllowed() {
/*  328 */     return this.includesAllowed;
/*      */   }
/*      */   
/*      */   public String getHeader() {
/*  339 */     return getLayout().getHeaderComment();
/*      */   }
/*      */   
/*      */   public void setHeader(String header) {
/*  350 */     getLayout().setHeaderComment(header);
/*      */   }
/*      */   
/*      */   public String getEncoding() {
/*  362 */     String enc = super.getEncoding();
/*  363 */     return (enc != null) ? enc : "ISO-8859-1";
/*      */   }
/*      */   
/*      */   public synchronized PropertiesConfigurationLayout getLayout() {
/*  374 */     if (this.layout == null)
/*  376 */       this.layout = createLayout(); 
/*  378 */     return this.layout;
/*      */   }
/*      */   
/*      */   public synchronized void setLayout(PropertiesConfigurationLayout layout) {
/*  391 */     if (this.layout != null)
/*  393 */       removeConfigurationListener(this.layout); 
/*  396 */     if (layout == null) {
/*  398 */       this.layout = createLayout();
/*      */     } else {
/*  402 */       this.layout = layout;
/*      */     } 
/*      */   }
/*      */   
/*      */   protected PropertiesConfigurationLayout createLayout() {
/*  416 */     return new PropertiesConfigurationLayout(this);
/*      */   }
/*      */   
/*      */   public IOFactory getIOFactory() {
/*  428 */     return (this.ioFactory != null) ? this.ioFactory : DEFAULT_IO_FACTORY;
/*      */   }
/*      */   
/*      */   public void setIOFactory(IOFactory ioFactory) {
/*  448 */     if (ioFactory == null)
/*  450 */       throw new IllegalArgumentException("IOFactory must not be null!"); 
/*  453 */     this.ioFactory = ioFactory;
/*      */   }
/*      */   
/*      */   public synchronized void load(Reader in) throws ConfigurationException {
/*  468 */     boolean oldAutoSave = isAutoSave();
/*  469 */     setAutoSave(false);
/*      */     try {
/*  473 */       getLayout().load(in);
/*      */     } finally {
/*  477 */       setAutoSave(oldAutoSave);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void save(Writer writer) throws ConfigurationException {
/*  489 */     enterNoReload();
/*      */     try {
/*  492 */       getLayout().save(writer);
/*      */     } finally {
/*  496 */       exitNoReload();
/*      */     } 
/*      */   }
/*      */   
/*      */   public void setBasePath(String basePath) {
/*  508 */     super.setBasePath(basePath);
/*  509 */     setIncludesAllowed(StringUtils.isNotEmpty(basePath));
/*      */   }
/*      */   
/*      */   public Object clone() {
/*  519 */     PropertiesConfiguration copy = (PropertiesConfiguration)super.clone();
/*  520 */     if (this.layout != null)
/*  522 */       copy.setLayout(new PropertiesConfigurationLayout(copy, this.layout)); 
/*  524 */     return copy;
/*      */   }
/*      */   
/*      */   boolean propertyLoaded(String key, String value) throws ConfigurationException {
/*      */     boolean result;
/*  548 */     if (StringUtils.isNotEmpty(getInclude()) && key.equalsIgnoreCase(getInclude())) {
/*  551 */       if (getIncludesAllowed()) {
/*      */         String[] files;
/*  554 */         if (!isDelimiterParsingDisabled()) {
/*  556 */           files = StringUtils.split(value, getListDelimiter());
/*      */         } else {
/*  560 */           files = new String[] { value };
/*      */         } 
/*  562 */         for (int i = 0; i < files.length; i++)
/*  564 */           loadIncludeFile(interpolate(files[i].trim())); 
/*      */       } 
/*  567 */       result = false;
/*      */     } else {
/*  572 */       addProperty(key, value);
/*  573 */       result = true;
/*      */     } 
/*  576 */     return result;
/*      */   }
/*      */   
/*      */   static boolean isCommentLine(String line) {
/*  589 */     String s = line.trim();
/*  591 */     return (s.length() < 1 || "#!".indexOf(s.charAt(0)) >= 0);
/*      */   }
/*      */   
/*      */   private static int countTrailingBS(String line) {
/*  603 */     int bsCount = 0;
/*  604 */     for (int idx = line.length() - 1; idx >= 0 && line.charAt(idx) == '\\'; idx--)
/*  606 */       bsCount++; 
/*  609 */     return bsCount;
/*      */   }
/*      */   
/*      */   public static class PropertiesReader extends LineNumberReader {
/*      */     private List commentLines;
/*      */     
/*      */     private String propertyName;
/*      */     
/*      */     private String propertyValue;
/*      */     
/*  630 */     private String propertySeparator = " = ";
/*      */     
/*      */     private char delimiter;
/*      */     
/*      */     public PropertiesReader(Reader reader) {
/*  642 */       this(reader, AbstractConfiguration.getDefaultListDelimiter());
/*      */     }
/*      */     
/*      */     public PropertiesReader(Reader reader, char listDelimiter) {
/*  655 */       super(reader);
/*  656 */       this.commentLines = new ArrayList();
/*  657 */       this.delimiter = listDelimiter;
/*      */     }
/*      */     
/*      */     public String readProperty() throws IOException {
/*      */       String line;
/*  673 */       this.commentLines.clear();
/*  674 */       StringBuffer buffer = new StringBuffer();
/*      */       while (true) {
/*  678 */         line = readLine();
/*  679 */         if (line == null)
/*  682 */           return null; 
/*  685 */         if (PropertiesConfiguration.isCommentLine(line)) {
/*  687 */           this.commentLines.add(line);
/*      */           continue;
/*      */         } 
/*  691 */         line = line.trim();
/*  693 */         if (checkCombineLines(line)) {
/*  695 */           line = line.substring(0, line.length() - 1);
/*  696 */           buffer.append(line);
/*      */           continue;
/*      */         } 
/*      */         break;
/*      */       } 
/*  700 */       buffer.append(line);
/*  704 */       return buffer.toString();
/*      */     }
/*      */     
/*      */     public boolean nextProperty() throws IOException {
/*  720 */       String line = readProperty();
/*  722 */       if (line == null)
/*  724 */         return false; 
/*  728 */       parseProperty(line);
/*  729 */       return true;
/*      */     }
/*      */     
/*      */     public List getCommentLines() {
/*  741 */       return this.commentLines;
/*      */     }
/*      */     
/*      */     public String getPropertyName() {
/*  754 */       return this.propertyName;
/*      */     }
/*      */     
/*      */     public String getPropertyValue() {
/*  767 */       return this.propertyValue;
/*      */     }
/*      */     
/*      */     public String getPropertySeparator() {
/*  780 */       return this.propertySeparator;
/*      */     }
/*      */     
/*      */     protected void parseProperty(String line) {
/*  795 */       String[] property = doParseProperty(line);
/*  796 */       initPropertyName(property[0]);
/*  797 */       initPropertyValue(property[1]);
/*  798 */       initPropertySeparator(property[2]);
/*      */     }
/*      */     
/*      */     protected void initPropertyName(String name) {
/*  812 */       this.propertyName = StringEscapeUtils.unescapeJava(name);
/*      */     }
/*      */     
/*      */     protected void initPropertyValue(String value) {
/*  826 */       this.propertyValue = PropertiesConfiguration.unescapeJava(value, this.delimiter);
/*      */     }
/*      */     
/*      */     protected void initPropertySeparator(String value) {
/*  840 */       this.propertySeparator = value;
/*      */     }
/*      */     
/*      */     private static boolean checkCombineLines(String line) {
/*  852 */       return (PropertiesConfiguration.countTrailingBS(line) % 2 != 0);
/*      */     }
/*      */     
/*      */     private static String[] doParseProperty(String line) {
/*  866 */       String[] result = new String[3];
/*  867 */       StringBuffer key = new StringBuffer();
/*  868 */       StringBuffer value = new StringBuffer();
/*  869 */       StringBuffer separator = new StringBuffer();
/*  876 */       int state = 0;
/*  878 */       for (int pos = 0; pos < line.length(); pos++) {
/*  880 */         char c = line.charAt(pos);
/*  882 */         switch (state) {
/*      */           case 0:
/*  885 */             if (c == '\\') {
/*  887 */               state = 1;
/*      */               break;
/*      */             } 
/*  889 */             if (ArrayUtils.contains(PropertiesConfiguration.WHITE_SPACE, c)) {
/*  892 */               separator.append(c);
/*  893 */               state = 2;
/*      */               break;
/*      */             } 
/*  895 */             if (ArrayUtils.contains(PropertiesConfiguration.SEPARATORS, c)) {
/*  898 */               separator.append(c);
/*  899 */               state = 3;
/*      */               break;
/*      */             } 
/*  903 */             key.append(c);
/*      */             break;
/*      */           case 1:
/*  909 */             if (ArrayUtils.contains(PropertiesConfiguration.SEPARATORS, c) || ArrayUtils.contains(PropertiesConfiguration.WHITE_SPACE, c)) {
/*  912 */               key.append(c);
/*      */             } else {
/*  917 */               key.append('\\');
/*  918 */               key.append(c);
/*      */             } 
/*  922 */             state = 0;
/*      */             break;
/*      */           case 2:
/*  927 */             if (ArrayUtils.contains(PropertiesConfiguration.WHITE_SPACE, c) || ArrayUtils.contains(PropertiesConfiguration.SEPARATORS, c)) {
/*  930 */               separator.append(c);
/*      */               break;
/*      */             } 
/*  935 */             value.append(c);
/*  938 */             state = 3;
/*      */             break;
/*      */           case 3:
/*  944 */             value.append(c);
/*      */             break;
/*      */         } 
/*      */       } 
/*  949 */       result[0] = key.toString().trim();
/*  950 */       result[1] = value.toString().trim();
/*  951 */       result[2] = separator.toString();
/*  953 */       return result;
/*      */     }
/*      */   }
/*      */   
/*      */   public static class PropertiesWriter extends FilterWriter {
/*      */     private static final int BUF_SIZE = 8;
/*      */     
/*      */     private char delimiter;
/*      */     
/*      */     private String currentSeparator;
/*      */     
/*      */     private String globalSeparator;
/*      */     
/*      */     private String lineSeparator;
/*      */     
/*      */     public PropertiesWriter(Writer writer, char delimiter) {
/*  987 */       super(writer);
/*  988 */       this.delimiter = delimiter;
/*      */     }
/*      */     
/*      */     public String getCurrentSeparator() {
/*  999 */       return this.currentSeparator;
/*      */     }
/*      */     
/*      */     public void setCurrentSeparator(String currentSeparator) {
/* 1011 */       this.currentSeparator = currentSeparator;
/*      */     }
/*      */     
/*      */     public String getGlobalSeparator() {
/* 1022 */       return this.globalSeparator;
/*      */     }
/*      */     
/*      */     public void setGlobalSeparator(String globalSeparator) {
/* 1037 */       this.globalSeparator = globalSeparator;
/*      */     }
/*      */     
/*      */     public String getLineSeparator() {
/* 1048 */       return (this.lineSeparator != null) ? this.lineSeparator : PropertiesConfiguration.LINE_SEPARATOR;
/*      */     }
/*      */     
/*      */     public void setLineSeparator(String lineSeparator) {
/* 1061 */       this.lineSeparator = lineSeparator;
/*      */     }
/*      */     
/*      */     public void writeProperty(String key, Object value) throws IOException {
/* 1074 */       writeProperty(key, value, false);
/*      */     }
/*      */     
/*      */     public void writeProperty(String key, List values) throws IOException {
/* 1087 */       for (int i = 0; i < values.size(); i++)
/* 1089 */         writeProperty(key, values.get(i)); 
/*      */     }
/*      */     
/*      */     public void writeProperty(String key, Object value, boolean forceSingleLine) throws IOException {
/*      */       String v;
/* 1110 */       if (value instanceof List) {
/* 1112 */         List values = (List)value;
/* 1113 */         if (forceSingleLine) {
/* 1115 */           v = makeSingleLineValue(values);
/*      */         } else {
/* 1119 */           writeProperty(key, values);
/*      */           return;
/*      */         } 
/*      */       } else {
/* 1125 */         v = escapeValue(value, false);
/*      */       } 
/* 1128 */       write(escapeKey(key));
/* 1129 */       write(fetchSeparator(key, value));
/* 1130 */       write(v);
/* 1132 */       writeln((String)null);
/*      */     }
/*      */     
/*      */     public void writeComment(String comment) throws IOException {
/* 1143 */       writeln("# " + comment);
/*      */     }
/*      */     
/*      */     private String escapeKey(String key) {
/* 1155 */       StringBuffer newkey = new StringBuffer();
/* 1157 */       for (int i = 0; i < key.length(); i++) {
/* 1159 */         char c = key.charAt(i);
/* 1161 */         if (ArrayUtils.contains(PropertiesConfiguration.SEPARATORS, c) || ArrayUtils.contains(PropertiesConfiguration.WHITE_SPACE, c)) {
/* 1164 */           newkey.append('\\');
/* 1165 */           newkey.append(c);
/*      */         } else {
/* 1169 */           newkey.append(c);
/*      */         } 
/*      */       } 
/* 1173 */       return newkey.toString();
/*      */     }
/*      */     
/*      */     private String escapeValue(Object value, boolean inList) {
/* 1187 */       String escapedValue = handleBackslashs(value, inList);
/* 1188 */       if (this.delimiter != '\000')
/* 1190 */         escapedValue = StringUtils.replace(escapedValue, String.valueOf(this.delimiter), "\\" + this.delimiter); 
/* 1192 */       return escapedValue;
/*      */     }
/*      */     
/*      */     private String handleBackslashs(Object value, boolean inList) {
/* 1209 */       String strValue = String.valueOf(value);
/* 1211 */       if (inList && strValue.indexOf("\\\\") >= 0) {
/* 1213 */         char esc = "\\".charAt(0);
/* 1214 */         StringBuffer buf = new StringBuffer(strValue.length() + 8);
/* 1215 */         for (int i = 0; i < strValue.length(); i++) {
/* 1217 */           if (strValue.charAt(i) == esc && i < strValue.length() - 1 && strValue.charAt(i + 1) == esc) {
/* 1220 */             buf.append("\\\\").append("\\\\");
/* 1221 */             i++;
/*      */           } else {
/* 1225 */             buf.append(strValue.charAt(i));
/*      */           } 
/*      */         } 
/* 1229 */         strValue = buf.toString();
/*      */       } 
/* 1232 */       return StringEscapeUtils.escapeJava(strValue);
/*      */     }
/*      */     
/*      */     private String makeSingleLineValue(List values) {
/* 1244 */       if (!values.isEmpty()) {
/* 1246 */         Iterator it = values.iterator();
/* 1247 */         String lastValue = escapeValue(it.next(), true);
/* 1248 */         StringBuffer buf = new StringBuffer(lastValue);
/* 1249 */         while (it.hasNext()) {
/* 1254 */           if (lastValue.endsWith("\\") && PropertiesConfiguration.countTrailingBS(lastValue) / 2 % 2 != 0)
/* 1256 */             buf.append("\\").append("\\"); 
/* 1258 */           buf.append(this.delimiter);
/* 1259 */           lastValue = escapeValue(it.next(), true);
/* 1260 */           buf.append(lastValue);
/*      */         } 
/* 1262 */         return buf.toString();
/*      */       } 
/* 1266 */       return null;
/*      */     }
/*      */     
/*      */     public void writeln(String s) throws IOException {
/* 1280 */       if (s != null)
/* 1282 */         write(s); 
/* 1284 */       write(getLineSeparator());
/*      */     }
/*      */     
/*      */     protected String fetchSeparator(String key, Object value) {
/* 1304 */       return (getGlobalSeparator() != null) ? getGlobalSeparator() : getCurrentSeparator();
/*      */     }
/*      */   }
/*      */   
/*      */   public static interface IOFactory {
/*      */     PropertiesConfiguration.PropertiesReader createPropertiesReader(Reader param1Reader, char param1Char);
/*      */     
/*      */     PropertiesConfiguration.PropertiesWriter createPropertiesWriter(Writer param1Writer, char param1Char);
/*      */   }
/*      */   
/*      */   public static class DefaultIOFactory implements IOFactory {
/*      */     public PropertiesConfiguration.PropertiesReader createPropertiesReader(Reader in, char delimiter) {
/* 1377 */       return new PropertiesConfiguration.PropertiesReader(in, delimiter);
/*      */     }
/*      */     
/*      */     public PropertiesConfiguration.PropertiesWriter createPropertiesWriter(Writer out, char delimiter) {
/* 1383 */       return new PropertiesConfiguration.PropertiesWriter(out, delimiter);
/*      */     }
/*      */   }
/*      */   
/*      */   protected static String unescapeJava(String str, char delimiter) {
/* 1400 */     if (str == null)
/* 1402 */       return null; 
/* 1404 */     int sz = str.length();
/* 1405 */     StringBuffer out = new StringBuffer(sz);
/* 1406 */     StringBuffer unicode = new StringBuffer(4);
/* 1407 */     boolean hadSlash = false;
/* 1408 */     boolean inUnicode = false;
/* 1409 */     for (int i = 0; i < sz; i++) {
/* 1411 */       char ch = str.charAt(i);
/* 1412 */       if (inUnicode) {
/* 1416 */         unicode.append(ch);
/* 1417 */         if (unicode.length() == 4)
/*      */           try {
/* 1423 */             int value = Integer.parseInt(unicode.toString(), 16);
/* 1424 */             out.append((char)value);
/* 1425 */             unicode.setLength(0);
/* 1426 */             inUnicode = false;
/* 1427 */             hadSlash = false;
/* 1429 */           } catch (NumberFormatException nfe) {
/* 1431 */             throw new ConfigurationRuntimeException("Unable to parse unicode value: " + unicode, nfe);
/*      */           }  
/* 1437 */       } else if (hadSlash) {
/* 1440 */         hadSlash = false;
/* 1442 */         if (ch == '\\') {
/* 1444 */           out.append('\\');
/* 1446 */         } else if (ch == '\'') {
/* 1448 */           out.append('\'');
/* 1450 */         } else if (ch == '"') {
/* 1452 */           out.append('"');
/* 1454 */         } else if (ch == 'r') {
/* 1456 */           out.append('\r');
/* 1458 */         } else if (ch == 'f') {
/* 1460 */           out.append('\f');
/* 1462 */         } else if (ch == 't') {
/* 1464 */           out.append('\t');
/* 1466 */         } else if (ch == 'n') {
/* 1468 */           out.append('\n');
/* 1470 */         } else if (ch == 'b') {
/* 1472 */           out.append('\b');
/* 1474 */         } else if (ch == delimiter) {
/* 1476 */           out.append('\\');
/* 1477 */           out.append(delimiter);
/* 1479 */         } else if (ch == 'u') {
/* 1482 */           inUnicode = true;
/*      */         } else {
/* 1486 */           out.append(ch);
/*      */         } 
/* 1491 */       } else if (ch == '\\') {
/* 1493 */         hadSlash = true;
/*      */       } else {
/* 1496 */         out.append(ch);
/*      */       } 
/*      */     } 
/* 1499 */     if (hadSlash)
/* 1503 */       out.append('\\'); 
/* 1506 */     return out.toString();
/*      */   }
/*      */   
/*      */   private void loadIncludeFile(String fileName) throws ConfigurationException {
/* 1521 */     URL url = ConfigurationUtils.locate(getFileSystem(), getBasePath(), fileName);
/* 1522 */     if (url == null) {
/* 1524 */       URL baseURL = getURL();
/* 1525 */       if (baseURL != null)
/* 1527 */         url = ConfigurationUtils.locate(getFileSystem(), baseURL.toString(), fileName); 
/*      */     } 
/* 1531 */     if (url == null)
/* 1533 */       throw new ConfigurationException("Cannot resolve include file " + fileName); 
/* 1536 */     load(url);
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\configuration\PropertiesConfiguration.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */