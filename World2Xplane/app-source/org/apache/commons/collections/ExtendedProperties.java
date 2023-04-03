/*      */ package org.apache.commons.collections;
/*      */ 
/*      */ import java.io.File;
/*      */ import java.io.FileInputStream;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.io.LineNumberReader;
/*      */ import java.io.OutputStream;
/*      */ import java.io.PrintWriter;
/*      */ import java.io.Reader;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Enumeration;
/*      */ import java.util.Hashtable;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.NoSuchElementException;
/*      */ import java.util.Properties;
/*      */ import java.util.StringTokenizer;
/*      */ import java.util.Vector;
/*      */ 
/*      */ public class ExtendedProperties extends Hashtable {
/*      */   private ExtendedProperties defaults;
/*      */   
/*      */   protected String file;
/*      */   
/*      */   protected String basePath;
/*      */   
/*  168 */   protected String fileSeparator = System.getProperty("file.separator");
/*      */   
/*      */   protected boolean isInitialized = false;
/*      */   
/*  179 */   protected static String include = "include";
/*      */   
/*  187 */   protected ArrayList keysAsListed = new ArrayList();
/*      */   
/*      */   protected static final String START_TOKEN = "${";
/*      */   
/*      */   protected static final String END_TOKEN = "}";
/*      */   
/*      */   protected String interpolate(String base) {
/*  201 */     return interpolateHelper(base, (List)null);
/*      */   }
/*      */   
/*      */   protected String interpolateHelper(String base, List priorVariables) {
/*  220 */     if (base == null)
/*  221 */       return null; 
/*  226 */     if (priorVariables == null) {
/*  227 */       priorVariables = new ArrayList();
/*  228 */       priorVariables.add(base);
/*      */     } 
/*  231 */     int begin = -1;
/*  232 */     int end = -1;
/*  233 */     int prec = 0 - "}".length();
/*  234 */     String variable = null;
/*  235 */     StringBuffer result = new StringBuffer();
/*  239 */     while ((begin = base.indexOf("${", prec + "}".length())) > -1 && (end = base.indexOf("}", begin)) > -1) {
/*  240 */       result.append(base.substring(prec + "}".length(), begin));
/*  241 */       variable = base.substring(begin + "${".length(), end);
/*  244 */       if (priorVariables.contains(variable)) {
/*  245 */         String initialBase = priorVariables.remove(0).toString();
/*  246 */         priorVariables.add(variable);
/*  247 */         StringBuffer priorVariableSb = new StringBuffer();
/*  251 */         for (Iterator it = priorVariables.iterator(); it.hasNext(); ) {
/*  252 */           priorVariableSb.append(it.next());
/*  253 */           if (it.hasNext())
/*  254 */             priorVariableSb.append("->"); 
/*      */         } 
/*  258 */         throw new IllegalStateException("infinite loop in property interpolation of " + initialBase + ": " + priorVariableSb.toString());
/*      */       } 
/*  263 */       priorVariables.add(variable);
/*  267 */       Object value = getProperty(variable);
/*  268 */       if (value != null) {
/*  269 */         result.append(interpolateHelper(value.toString(), priorVariables));
/*  275 */         priorVariables.remove(priorVariables.size() - 1);
/*  276 */       } else if (this.defaults != null && this.defaults.getString(variable, (String)null) != null) {
/*  277 */         result.append(this.defaults.getString(variable));
/*      */       } else {
/*  280 */         result.append("${").append(variable).append("}");
/*      */       } 
/*  282 */       prec = end;
/*      */     } 
/*  284 */     result.append(base.substring(prec + "}".length(), base.length()));
/*  286 */     return result.toString();
/*      */   }
/*      */   
/*      */   private static String escape(String s) {
/*  293 */     StringBuffer buf = new StringBuffer(s);
/*  294 */     for (int i = 0; i < buf.length(); i++) {
/*  295 */       char c = buf.charAt(i);
/*  296 */       if (c == ',' || c == '\\') {
/*  297 */         buf.insert(i, '\\');
/*  298 */         i++;
/*      */       } 
/*      */     } 
/*  301 */     return buf.toString();
/*      */   }
/*      */   
/*      */   private static String unescape(String s) {
/*  308 */     StringBuffer buf = new StringBuffer(s);
/*  309 */     for (int i = 0; i < buf.length() - 1; i++) {
/*  310 */       char c1 = buf.charAt(i);
/*  311 */       char c2 = buf.charAt(i + 1);
/*  312 */       if (c1 == '\\' && c2 == '\\')
/*  313 */         buf.deleteCharAt(i); 
/*      */     } 
/*  316 */     return buf.toString();
/*      */   }
/*      */   
/*      */   private static int countPreceding(String line, int index, char ch) {
/*      */     int i;
/*  325 */     for (i = index - 1; i >= 0 && 
/*  326 */       line.charAt(i) == ch; i--);
/*  330 */     return index - 1 - i;
/*      */   }
/*      */   
/*      */   private static boolean endsWithSlash(String line) {
/*  337 */     if (!line.endsWith("\\"))
/*  338 */       return false; 
/*  340 */     return (countPreceding(line, line.length() - 1, '\\') % 2 == 0);
/*      */   }
/*      */   
/*      */   static class PropertiesReader extends LineNumberReader {
/*      */     public PropertiesReader(Reader reader) {
/*  356 */       super(reader);
/*      */     }
/*      */     
/*      */     public String readProperty() throws IOException {
/*  366 */       StringBuffer buffer = new StringBuffer();
/*  367 */       String line = readLine();
/*  368 */       while (line != null) {
/*  369 */         line = line.trim();
/*  370 */         if (line.length() != 0 && line.charAt(0) != '#')
/*  371 */           if (ExtendedProperties.endsWithSlash(line)) {
/*  372 */             line = line.substring(0, line.length() - 1);
/*  373 */             buffer.append(line);
/*      */           } else {
/*  375 */             buffer.append(line);
/*  376 */             return buffer.toString();
/*      */           }  
/*  379 */         line = readLine();
/*      */       } 
/*  381 */       return null;
/*      */     }
/*      */   }
/*      */   
/*      */   static class PropertiesTokenizer extends StringTokenizer {
/*      */     static final String DELIMITER = ",";
/*      */     
/*      */     public PropertiesTokenizer(String string) {
/*  402 */       super(string, ",");
/*      */     }
/*      */     
/*      */     public boolean hasMoreTokens() {
/*  411 */       return super.hasMoreTokens();
/*      */     }
/*      */     
/*      */     public String nextToken() {
/*  420 */       StringBuffer buffer = new StringBuffer();
/*  422 */       while (hasMoreTokens()) {
/*  423 */         String token = super.nextToken();
/*  424 */         if (ExtendedProperties.endsWithSlash(token)) {
/*  425 */           buffer.append(token.substring(0, token.length() - 1));
/*  426 */           buffer.append(",");
/*      */           continue;
/*      */         } 
/*  428 */         buffer.append(token);
/*      */       } 
/*  433 */       return buffer.toString().trim();
/*      */     }
/*      */   }
/*      */   
/*      */   public ExtendedProperties(String file) throws IOException {
/*  451 */     this(file, (String)null);
/*      */   }
/*      */   
/*      */   public ExtendedProperties(String file, String defaultFile) throws IOException {
/*  462 */     this.file = file;
/*  464 */     this.basePath = (new File(file)).getAbsolutePath();
/*  465 */     this.basePath = this.basePath.substring(0, this.basePath.lastIndexOf(this.fileSeparator) + 1);
/*  467 */     FileInputStream in = null;
/*      */     try {
/*  469 */       in = new FileInputStream(file);
/*  470 */       load(in);
/*      */     } finally {
/*      */       try {
/*  473 */         if (in != null)
/*  474 */           in.close(); 
/*  476 */       } catch (IOException ex) {}
/*      */     } 
/*  479 */     if (defaultFile != null)
/*  480 */       this.defaults = new ExtendedProperties(defaultFile); 
/*      */   }
/*      */   
/*      */   public boolean isInitialized() {
/*  489 */     return this.isInitialized;
/*      */   }
/*      */   
/*      */   public String getInclude() {
/*  499 */     return include;
/*      */   }
/*      */   
/*      */   public void setInclude(String inc) {
/*  509 */     include = inc;
/*      */   }
/*      */   
/*      */   public void load(InputStream input) throws IOException {
/*  519 */     load(input, (String)null);
/*      */   }
/*      */   
/*      */   public synchronized void load(InputStream input, String enc) throws IOException {
/*      */     // Byte code:
/*      */     //   0: aconst_null
/*      */     //   1: astore_3
/*      */     //   2: aload_2
/*      */     //   3: ifnull -> 28
/*      */     //   6: new org/apache/commons/collections/ExtendedProperties$PropertiesReader
/*      */     //   9: dup
/*      */     //   10: new java/io/InputStreamReader
/*      */     //   13: dup
/*      */     //   14: aload_1
/*      */     //   15: aload_2
/*      */     //   16: invokespecial <init> : (Ljava/io/InputStream;Ljava/lang/String;)V
/*      */     //   19: invokespecial <init> : (Ljava/io/Reader;)V
/*      */     //   22: astore_3
/*      */     //   23: goto -> 28
/*      */     //   26: astore #4
/*      */     //   28: aload_3
/*      */     //   29: ifnonnull -> 71
/*      */     //   32: new org/apache/commons/collections/ExtendedProperties$PropertiesReader
/*      */     //   35: dup
/*      */     //   36: new java/io/InputStreamReader
/*      */     //   39: dup
/*      */     //   40: aload_1
/*      */     //   41: ldc '8859_1'
/*      */     //   43: invokespecial <init> : (Ljava/io/InputStream;Ljava/lang/String;)V
/*      */     //   46: invokespecial <init> : (Ljava/io/Reader;)V
/*      */     //   49: astore_3
/*      */     //   50: goto -> 71
/*      */     //   53: astore #4
/*      */     //   55: new org/apache/commons/collections/ExtendedProperties$PropertiesReader
/*      */     //   58: dup
/*      */     //   59: new java/io/InputStreamReader
/*      */     //   62: dup
/*      */     //   63: aload_1
/*      */     //   64: invokespecial <init> : (Ljava/io/InputStream;)V
/*      */     //   67: invokespecial <init> : (Ljava/io/Reader;)V
/*      */     //   70: astore_3
/*      */     //   71: aload_3
/*      */     //   72: invokevirtual readProperty : ()Ljava/lang/String;
/*      */     //   75: astore #4
/*      */     //   77: aload #4
/*      */     //   79: ifnonnull -> 88
/*      */     //   82: aload_0
/*      */     //   83: iconst_1
/*      */     //   84: putfield isInitialized : Z
/*      */     //   87: return
/*      */     //   88: aload #4
/*      */     //   90: bipush #61
/*      */     //   92: invokevirtual indexOf : (I)I
/*      */     //   95: istore #5
/*      */     //   97: iload #5
/*      */     //   99: ifle -> 304
/*      */     //   102: aload #4
/*      */     //   104: iconst_0
/*      */     //   105: iload #5
/*      */     //   107: invokevirtual substring : (II)Ljava/lang/String;
/*      */     //   110: invokevirtual trim : ()Ljava/lang/String;
/*      */     //   113: astore #6
/*      */     //   115: aload #4
/*      */     //   117: iload #5
/*      */     //   119: iconst_1
/*      */     //   120: iadd
/*      */     //   121: invokevirtual substring : (I)Ljava/lang/String;
/*      */     //   124: invokevirtual trim : ()Ljava/lang/String;
/*      */     //   127: astore #7
/*      */     //   129: ldc ''
/*      */     //   131: aload #7
/*      */     //   133: invokevirtual equals : (Ljava/lang/Object;)Z
/*      */     //   136: ifeq -> 142
/*      */     //   139: goto -> 71
/*      */     //   142: aload_0
/*      */     //   143: invokevirtual getInclude : ()Ljava/lang/String;
/*      */     //   146: ifnull -> 296
/*      */     //   149: aload #6
/*      */     //   151: aload_0
/*      */     //   152: invokevirtual getInclude : ()Ljava/lang/String;
/*      */     //   155: invokevirtual equalsIgnoreCase : (Ljava/lang/String;)Z
/*      */     //   158: ifeq -> 296
/*      */     //   161: aconst_null
/*      */     //   162: astore #8
/*      */     //   164: aload #7
/*      */     //   166: aload_0
/*      */     //   167: getfield fileSeparator : Ljava/lang/String;
/*      */     //   170: invokevirtual startsWith : (Ljava/lang/String;)Z
/*      */     //   173: ifeq -> 190
/*      */     //   176: new java/io/File
/*      */     //   179: dup
/*      */     //   180: aload #7
/*      */     //   182: invokespecial <init> : (Ljava/lang/String;)V
/*      */     //   185: astore #8
/*      */     //   187: goto -> 259
/*      */     //   190: aload #7
/*      */     //   192: new java/lang/StringBuffer
/*      */     //   195: dup
/*      */     //   196: invokespecial <init> : ()V
/*      */     //   199: ldc '.'
/*      */     //   201: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   204: aload_0
/*      */     //   205: getfield fileSeparator : Ljava/lang/String;
/*      */     //   208: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   211: invokevirtual toString : ()Ljava/lang/String;
/*      */     //   214: invokevirtual startsWith : (Ljava/lang/String;)Z
/*      */     //   217: ifeq -> 228
/*      */     //   220: aload #7
/*      */     //   222: iconst_2
/*      */     //   223: invokevirtual substring : (I)Ljava/lang/String;
/*      */     //   226: astore #7
/*      */     //   228: new java/io/File
/*      */     //   231: dup
/*      */     //   232: new java/lang/StringBuffer
/*      */     //   235: dup
/*      */     //   236: invokespecial <init> : ()V
/*      */     //   239: aload_0
/*      */     //   240: getfield basePath : Ljava/lang/String;
/*      */     //   243: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   246: aload #7
/*      */     //   248: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   251: invokevirtual toString : ()Ljava/lang/String;
/*      */     //   254: invokespecial <init> : (Ljava/lang/String;)V
/*      */     //   257: astore #8
/*      */     //   259: aload #8
/*      */     //   261: ifnull -> 293
/*      */     //   264: aload #8
/*      */     //   266: invokevirtual exists : ()Z
/*      */     //   269: ifeq -> 293
/*      */     //   272: aload #8
/*      */     //   274: invokevirtual canRead : ()Z
/*      */     //   277: ifeq -> 293
/*      */     //   280: aload_0
/*      */     //   281: new java/io/FileInputStream
/*      */     //   284: dup
/*      */     //   285: aload #8
/*      */     //   287: invokespecial <init> : (Ljava/io/File;)V
/*      */     //   290: invokevirtual load : (Ljava/io/InputStream;)V
/*      */     //   293: goto -> 304
/*      */     //   296: aload_0
/*      */     //   297: aload #6
/*      */     //   299: aload #7
/*      */     //   301: invokevirtual addProperty : (Ljava/lang/String;Ljava/lang/Object;)V
/*      */     //   304: goto -> 71
/*      */     //   307: astore #9
/*      */     //   309: aload_0
/*      */     //   310: iconst_1
/*      */     //   311: putfield isInitialized : Z
/*      */     //   314: aload #9
/*      */     //   316: athrow
/*      */     // Line number table:
/*      */     //   Java source line number -> byte code offset
/*      */     //   #531	-> 0
/*      */     //   #532	-> 2
/*      */     //   #534	-> 6
/*      */     //   #538	-> 23
/*      */     //   #536	-> 26
/*      */     //   #541	-> 28
/*      */     //   #543	-> 32
/*      */     //   #549	-> 50
/*      */     //   #545	-> 53
/*      */     //   #548	-> 55
/*      */     //   #554	-> 71
/*      */     //   #555	-> 77
/*      */     //   #598	-> 82
/*      */     //   #599	-> 87
/*      */     //   #558	-> 88
/*      */     //   #560	-> 97
/*      */     //   #561	-> 102
/*      */     //   #562	-> 115
/*      */     //   #565	-> 129
/*      */     //   #566	-> 139
/*      */     //   #569	-> 142
/*      */     //   #571	-> 161
/*      */     //   #573	-> 164
/*      */     //   #575	-> 176
/*      */     //   #577	-> 187
/*      */     //   #581	-> 190
/*      */     //   #582	-> 220
/*      */     //   #585	-> 228
/*      */     //   #588	-> 259
/*      */     //   #589	-> 280
/*      */     //   #591	-> 293
/*      */     //   #592	-> 296
/*      */     //   #595	-> 304
/*      */     //   #598	-> 307
/*      */     //   #599	-> 314
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	descriptor
/*      */     //   28	0	4	ex	Ljava/io/UnsupportedEncodingException;
/*      */     //   55	16	4	ex	Ljava/io/UnsupportedEncodingException;
/*      */     //   164	129	8	file	Ljava/io/File;
/*      */     //   115	189	6	key	Ljava/lang/String;
/*      */     //   129	175	7	value	Ljava/lang/String;
/*      */     //   77	227	4	line	Ljava/lang/String;
/*      */     //   97	207	5	equalSign	I
/*      */     //   0	317	0	this	Lorg/apache/commons/collections/ExtendedProperties;
/*      */     //   0	317	1	input	Ljava/io/InputStream;
/*      */     //   0	317	2	enc	Ljava/lang/String;
/*      */     //   2	315	3	reader	Lorg/apache/commons/collections/ExtendedProperties$PropertiesReader;
/*      */     // Exception table:
/*      */     //   from	to	target	type
/*      */     //   6	23	26	java/io/UnsupportedEncodingException
/*      */     //   32	50	53	java/io/UnsupportedEncodingException
/*      */     //   71	82	307	finally
/*      */     //   88	309	307	finally
/*      */   }
/*      */   
/*      */   public Object getProperty(String key) {
/*  611 */     Object obj = get(key);
/*  613 */     if (obj == null)
/*  616 */       if (this.defaults != null)
/*  617 */         obj = this.defaults.get(key);  
/*  621 */     return obj;
/*      */   }
/*      */   
/*      */   public void addProperty(String key, Object value) {
/*  644 */     if (value instanceof String) {
/*  645 */       String str = (String)value;
/*  646 */       if (str.indexOf(",") > 0) {
/*  648 */         PropertiesTokenizer tokenizer = new PropertiesTokenizer(str);
/*  649 */         while (tokenizer.hasMoreTokens()) {
/*  650 */           String token = tokenizer.nextToken();
/*  651 */           addPropertyInternal(key, unescape(token));
/*      */         } 
/*      */       } else {
/*  655 */         addPropertyInternal(key, unescape(str));
/*      */       } 
/*      */     } else {
/*  658 */       addPropertyInternal(key, value);
/*      */     } 
/*  662 */     this.isInitialized = true;
/*      */   }
/*      */   
/*      */   private void addPropertyDirect(String key, Object value) {
/*  674 */     if (!containsKey(key))
/*  675 */       this.keysAsListed.add(key); 
/*  677 */     put((K)key, (V)value);
/*      */   }
/*      */   
/*      */   private void addPropertyInternal(String key, Object value) {
/*  692 */     Object current = get(key);
/*  694 */     if (current instanceof String) {
/*  696 */       List values = new Vector(2);
/*  697 */       values.add(current);
/*  698 */       values.add(value);
/*  699 */       put((K)key, (V)values);
/*  701 */     } else if (current instanceof List) {
/*  703 */       ((List)current).add(value);
/*      */     } else {
/*  707 */       if (!containsKey(key))
/*  708 */         this.keysAsListed.add(key); 
/*  710 */       put((K)key, (V)value);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void setProperty(String key, Object value) {
/*  723 */     clearProperty(key);
/*  724 */     addProperty(key, value);
/*      */   }
/*      */   
/*      */   public synchronized void save(OutputStream output, String header) throws IOException {
/*  737 */     if (output == null)
/*      */       return; 
/*  740 */     PrintWriter theWrtr = new PrintWriter(output);
/*  741 */     if (header != null)
/*  742 */       theWrtr.println(header); 
/*  745 */     Enumeration theKeys = keys();
/*  746 */     while (theKeys.hasMoreElements()) {
/*  747 */       String key = (String)theKeys.nextElement();
/*  748 */       Object value = get(key);
/*  749 */       if (value != null)
/*  750 */         if (value instanceof String) {
/*  751 */           StringBuffer currentOutput = new StringBuffer();
/*  752 */           currentOutput.append(key);
/*  753 */           currentOutput.append("=");
/*  754 */           currentOutput.append(escape((String)value));
/*  755 */           theWrtr.println(currentOutput.toString());
/*  757 */         } else if (value instanceof List) {
/*  758 */           List values = (List)value;
/*  759 */           for (Iterator it = values.iterator(); it.hasNext(); ) {
/*  760 */             String currentElement = it.next();
/*  761 */             StringBuffer currentOutput = new StringBuffer();
/*  762 */             currentOutput.append(key);
/*  763 */             currentOutput.append("=");
/*  764 */             currentOutput.append(escape(currentElement));
/*  765 */             theWrtr.println(currentOutput.toString());
/*      */           } 
/*      */         }  
/*  769 */       theWrtr.println();
/*  770 */       theWrtr.flush();
/*      */     } 
/*      */   }
/*      */   
/*      */   public void combine(ExtendedProperties props) {
/*  782 */     for (Iterator it = props.getKeys(); it.hasNext(); ) {
/*  783 */       String key = it.next();
/*  784 */       setProperty(key, props.get(key));
/*      */     } 
/*      */   }
/*      */   
/*      */   public void clearProperty(String key) {
/*  794 */     if (containsKey(key)) {
/*  797 */       for (int i = 0; i < this.keysAsListed.size(); i++) {
/*  798 */         if (this.keysAsListed.get(i).equals(key)) {
/*  799 */           this.keysAsListed.remove(i);
/*      */           break;
/*      */         } 
/*      */       } 
/*  803 */       remove(key);
/*      */     } 
/*      */   }
/*      */   
/*      */   public Iterator getKeys() {
/*  814 */     return this.keysAsListed.iterator();
/*      */   }
/*      */   
/*      */   public Iterator getKeys(String prefix) {
/*  825 */     Iterator keys = getKeys();
/*  826 */     ArrayList matchingKeys = new ArrayList();
/*  828 */     while (keys.hasNext()) {
/*  829 */       Object key = keys.next();
/*  831 */       if (key instanceof String && ((String)key).startsWith(prefix))
/*  832 */         matchingKeys.add(key); 
/*      */     } 
/*  835 */     return matchingKeys.iterator();
/*      */   }
/*      */   
/*      */   public ExtendedProperties subset(String prefix) {
/*  847 */     ExtendedProperties c = new ExtendedProperties();
/*  848 */     Iterator keys = getKeys();
/*  849 */     boolean validSubset = false;
/*  851 */     while (keys.hasNext()) {
/*  852 */       Object key = keys.next();
/*  854 */       if (key instanceof String && ((String)key).startsWith(prefix)) {
/*  855 */         if (!validSubset)
/*  856 */           validSubset = true; 
/*  865 */         String newKey = null;
/*  866 */         if (((String)key).length() == prefix.length()) {
/*  867 */           newKey = prefix;
/*      */         } else {
/*  869 */           newKey = ((String)key).substring(prefix.length() + 1);
/*      */         } 
/*  877 */         c.addPropertyDirect(newKey, get(key));
/*      */       } 
/*      */     } 
/*  881 */     if (validSubset)
/*  882 */       return c; 
/*  884 */     return null;
/*      */   }
/*      */   
/*      */   public void display() {
/*  892 */     Iterator i = getKeys();
/*  894 */     while (i.hasNext()) {
/*  895 */       String key = i.next();
/*  896 */       Object value = get(key);
/*  897 */       System.out.println(key + " => " + value);
/*      */     } 
/*      */   }
/*      */   
/*      */   public String getString(String key) {
/*  910 */     return getString(key, (String)null);
/*      */   }
/*      */   
/*      */   public String getString(String key, String defaultValue) {
/*  924 */     Object value = get(key);
/*  926 */     if (value instanceof String)
/*  927 */       return interpolate((String)value); 
/*  929 */     if (value == null) {
/*  930 */       if (this.defaults != null)
/*  931 */         return interpolate(this.defaults.getString(key, defaultValue)); 
/*  933 */       return interpolate(defaultValue);
/*      */     } 
/*  935 */     if (value instanceof List)
/*  936 */       return interpolate(((List)value).get(0)); 
/*  938 */     throw new ClassCastException('\'' + key + "' doesn't map to a String object");
/*      */   }
/*      */   
/*      */   public Properties getProperties(String key) {
/*  954 */     return getProperties(key, new Properties());
/*      */   }
/*      */   
/*      */   public Properties getProperties(String key, Properties defaults) {
/*  972 */     String[] tokens = getStringArray(key);
/*  975 */     Properties props = new Properties(defaults);
/*  976 */     for (int i = 0; i < tokens.length; i++) {
/*  977 */       String token = tokens[i];
/*  978 */       int equalSign = token.indexOf('=');
/*  979 */       if (equalSign > 0) {
/*  980 */         String pkey = token.substring(0, equalSign).trim();
/*  981 */         String pvalue = token.substring(equalSign + 1).trim();
/*  982 */         props.put(pkey, pvalue);
/*      */       } else {
/*  984 */         throw new IllegalArgumentException('\'' + token + "' does not contain " + "an equals sign");
/*      */       } 
/*      */     } 
/*  987 */     return props;
/*      */   }
/*      */   
/*      */   public String[] getStringArray(String key) {
/*      */     List values;
/* 1000 */     Object value = get(key);
/* 1003 */     if (value instanceof String) {
/* 1004 */       values = new Vector(1);
/* 1005 */       values.add(value);
/* 1007 */     } else if (value instanceof List) {
/* 1008 */       values = (List)value;
/*      */     } else {
/* 1010 */       if (value == null) {
/* 1011 */         if (this.defaults != null)
/* 1012 */           return this.defaults.getStringArray(key); 
/* 1014 */         return new String[0];
/*      */       } 
/* 1017 */       throw new ClassCastException('\'' + key + "' doesn't map to a String/List object");
/*      */     } 
/* 1020 */     String[] tokens = new String[values.size()];
/* 1021 */     for (int i = 0; i < tokens.length; i++)
/* 1022 */       tokens[i] = values.get(i); 
/* 1025 */     return tokens;
/*      */   }
/*      */   
/*      */   public Vector getVector(String key) {
/* 1038 */     return getVector(key, (Vector)null);
/*      */   }
/*      */   
/*      */   public Vector getVector(String key, Vector defaultValue) {
/* 1054 */     Object value = get(key);
/* 1056 */     if (value instanceof List)
/* 1057 */       return new Vector((List)value); 
/* 1059 */     if (value instanceof String) {
/* 1060 */       Vector values = new Vector(1);
/* 1061 */       values.add(value);
/* 1062 */       put((K)key, (V)values);
/* 1063 */       return values;
/*      */     } 
/* 1065 */     if (value == null) {
/* 1066 */       if (this.defaults != null)
/* 1067 */         return this.defaults.getVector(key, defaultValue); 
/* 1069 */       return (defaultValue == null) ? new Vector() : defaultValue;
/*      */     } 
/* 1072 */     throw new ClassCastException('\'' + key + "' doesn't map to a Vector object");
/*      */   }
/*      */   
/*      */   public List getList(String key) {
/* 1089 */     return getList(key, (List)null);
/*      */   }
/*      */   
/*      */   public List getList(String key, List defaultValue) {
/* 1106 */     Object value = get(key);
/* 1108 */     if (value instanceof List)
/* 1109 */       return new ArrayList((List)value); 
/* 1111 */     if (value instanceof String) {
/* 1112 */       List values = new ArrayList(1);
/* 1113 */       values.add(value);
/* 1114 */       put((K)key, (V)values);
/* 1115 */       return values;
/*      */     } 
/* 1117 */     if (value == null) {
/* 1118 */       if (this.defaults != null)
/* 1119 */         return this.defaults.getList(key, defaultValue); 
/* 1121 */       return (defaultValue == null) ? new ArrayList() : defaultValue;
/*      */     } 
/* 1124 */     throw new ClassCastException('\'' + key + "' doesn't map to a List object");
/*      */   }
/*      */   
/*      */   public boolean getBoolean(String key) {
/* 1139 */     Boolean b = getBoolean(key, (Boolean)null);
/* 1140 */     if (b != null)
/* 1141 */       return b.booleanValue(); 
/* 1143 */     throw new NoSuchElementException('\'' + key + "' doesn't map to an existing object");
/*      */   }
/*      */   
/*      */   public boolean getBoolean(String key, boolean defaultValue) {
/* 1157 */     return getBoolean(key, new Boolean(defaultValue)).booleanValue();
/*      */   }
/*      */   
/*      */   public Boolean getBoolean(String key, Boolean defaultValue) {
/* 1172 */     Object value = get(key);
/* 1174 */     if (value instanceof Boolean)
/* 1175 */       return (Boolean)value; 
/* 1177 */     if (value instanceof String) {
/* 1178 */       String s = testBoolean((String)value);
/* 1179 */       Boolean b = new Boolean(s);
/* 1180 */       put((K)key, (V)b);
/* 1181 */       return b;
/*      */     } 
/* 1183 */     if (value == null) {
/* 1184 */       if (this.defaults != null)
/* 1185 */         return this.defaults.getBoolean(key, defaultValue); 
/* 1187 */       return defaultValue;
/*      */     } 
/* 1190 */     throw new ClassCastException('\'' + key + "' doesn't map to a Boolean object");
/*      */   }
/*      */   
/*      */   public String testBoolean(String value) {
/* 1207 */     String s = value.toLowerCase();
/* 1209 */     if (s.equals("true") || s.equals("on") || s.equals("yes"))
/* 1210 */       return "true"; 
/* 1211 */     if (s.equals("false") || s.equals("off") || s.equals("no"))
/* 1212 */       return "false"; 
/* 1214 */     return null;
/*      */   }
/*      */   
/*      */   public byte getByte(String key) {
/* 1231 */     Byte b = getByte(key, (Byte)null);
/* 1232 */     if (b != null)
/* 1233 */       return b.byteValue(); 
/* 1235 */     throw new NoSuchElementException('\'' + key + " doesn't map to an existing object");
/*      */   }
/*      */   
/*      */   public byte getByte(String key, byte defaultValue) {
/* 1251 */     return getByte(key, new Byte(defaultValue)).byteValue();
/*      */   }
/*      */   
/*      */   public Byte getByte(String key, Byte defaultValue) {
/* 1267 */     Object value = get(key);
/* 1269 */     if (value instanceof Byte)
/* 1270 */       return (Byte)value; 
/* 1272 */     if (value instanceof String) {
/* 1273 */       Byte b = new Byte((String)value);
/* 1274 */       put((K)key, (V)b);
/* 1275 */       return b;
/*      */     } 
/* 1277 */     if (value == null) {
/* 1278 */       if (this.defaults != null)
/* 1279 */         return this.defaults.getByte(key, defaultValue); 
/* 1281 */       return defaultValue;
/*      */     } 
/* 1284 */     throw new ClassCastException('\'' + key + "' doesn't map to a Byte object");
/*      */   }
/*      */   
/*      */   public short getShort(String key) {
/* 1301 */     Short s = getShort(key, (Short)null);
/* 1302 */     if (s != null)
/* 1303 */       return s.shortValue(); 
/* 1305 */     throw new NoSuchElementException('\'' + key + "' doesn't map to an existing object");
/*      */   }
/*      */   
/*      */   public short getShort(String key, short defaultValue) {
/* 1321 */     return getShort(key, new Short(defaultValue)).shortValue();
/*      */   }
/*      */   
/*      */   public Short getShort(String key, Short defaultValue) {
/* 1337 */     Object value = get(key);
/* 1339 */     if (value instanceof Short)
/* 1340 */       return (Short)value; 
/* 1342 */     if (value instanceof String) {
/* 1343 */       Short s = new Short((String)value);
/* 1344 */       put((K)key, (V)s);
/* 1345 */       return s;
/*      */     } 
/* 1347 */     if (value == null) {
/* 1348 */       if (this.defaults != null)
/* 1349 */         return this.defaults.getShort(key, defaultValue); 
/* 1351 */       return defaultValue;
/*      */     } 
/* 1354 */     throw new ClassCastException('\'' + key + "' doesn't map to a Short object");
/*      */   }
/*      */   
/*      */   public int getInt(String name) {
/* 1366 */     return getInteger(name);
/*      */   }
/*      */   
/*      */   public int getInt(String name, int def) {
/* 1378 */     return getInteger(name, def);
/*      */   }
/*      */   
/*      */   public int getInteger(String key) {
/* 1394 */     Integer i = getInteger(key, (Integer)null);
/* 1395 */     if (i != null)
/* 1396 */       return i.intValue(); 
/* 1398 */     throw new NoSuchElementException('\'' + key + "' doesn't map to an existing object");
/*      */   }
/*      */   
/*      */   public int getInteger(String key, int defaultValue) {
/* 1414 */     Integer i = getInteger(key, (Integer)null);
/* 1416 */     if (i == null)
/* 1417 */       return defaultValue; 
/* 1419 */     return i.intValue();
/*      */   }
/*      */   
/*      */   public Integer getInteger(String key, Integer defaultValue) {
/* 1435 */     Object value = get(key);
/* 1437 */     if (value instanceof Integer)
/* 1438 */       return (Integer)value; 
/* 1440 */     if (value instanceof String) {
/* 1441 */       Integer i = new Integer((String)value);
/* 1442 */       put((K)key, (V)i);
/* 1443 */       return i;
/*      */     } 
/* 1445 */     if (value == null) {
/* 1446 */       if (this.defaults != null)
/* 1447 */         return this.defaults.getInteger(key, defaultValue); 
/* 1449 */       return defaultValue;
/*      */     } 
/* 1452 */     throw new ClassCastException('\'' + key + "' doesn't map to a Integer object");
/*      */   }
/*      */   
/*      */   public long getLong(String key) {
/* 1469 */     Long l = getLong(key, (Long)null);
/* 1470 */     if (l != null)
/* 1471 */       return l.longValue(); 
/* 1473 */     throw new NoSuchElementException('\'' + key + "' doesn't map to an existing object");
/*      */   }
/*      */   
/*      */   public long getLong(String key, long defaultValue) {
/* 1489 */     return getLong(key, new Long(defaultValue)).longValue();
/*      */   }
/*      */   
/*      */   public Long getLong(String key, Long defaultValue) {
/* 1505 */     Object value = get(key);
/* 1507 */     if (value instanceof Long)
/* 1508 */       return (Long)value; 
/* 1510 */     if (value instanceof String) {
/* 1511 */       Long l = new Long((String)value);
/* 1512 */       put((K)key, (V)l);
/* 1513 */       return l;
/*      */     } 
/* 1515 */     if (value == null) {
/* 1516 */       if (this.defaults != null)
/* 1517 */         return this.defaults.getLong(key, defaultValue); 
/* 1519 */       return defaultValue;
/*      */     } 
/* 1522 */     throw new ClassCastException('\'' + key + "' doesn't map to a Long object");
/*      */   }
/*      */   
/*      */   public float getFloat(String key) {
/* 1539 */     Float f = getFloat(key, (Float)null);
/* 1540 */     if (f != null)
/* 1541 */       return f.floatValue(); 
/* 1543 */     throw new NoSuchElementException('\'' + key + "' doesn't map to an existing object");
/*      */   }
/*      */   
/*      */   public float getFloat(String key, float defaultValue) {
/* 1559 */     return getFloat(key, new Float(defaultValue)).floatValue();
/*      */   }
/*      */   
/*      */   public Float getFloat(String key, Float defaultValue) {
/* 1575 */     Object value = get(key);
/* 1577 */     if (value instanceof Float)
/* 1578 */       return (Float)value; 
/* 1580 */     if (value instanceof String) {
/* 1581 */       Float f = new Float((String)value);
/* 1582 */       put((K)key, (V)f);
/* 1583 */       return f;
/*      */     } 
/* 1585 */     if (value == null) {
/* 1586 */       if (this.defaults != null)
/* 1587 */         return this.defaults.getFloat(key, defaultValue); 
/* 1589 */       return defaultValue;
/*      */     } 
/* 1592 */     throw new ClassCastException('\'' + key + "' doesn't map to a Float object");
/*      */   }
/*      */   
/*      */   public double getDouble(String key) {
/* 1609 */     Double d = getDouble(key, (Double)null);
/* 1610 */     if (d != null)
/* 1611 */       return d.doubleValue(); 
/* 1613 */     throw new NoSuchElementException('\'' + key + "' doesn't map to an existing object");
/*      */   }
/*      */   
/*      */   public double getDouble(String key, double defaultValue) {
/* 1629 */     return getDouble(key, new Double(defaultValue)).doubleValue();
/*      */   }
/*      */   
/*      */   public Double getDouble(String key, Double defaultValue) {
/* 1645 */     Object value = get(key);
/* 1647 */     if (value instanceof Double)
/* 1648 */       return (Double)value; 
/* 1650 */     if (value instanceof String) {
/* 1651 */       Double d = new Double((String)value);
/* 1652 */       put((K)key, (V)d);
/* 1653 */       return d;
/*      */     } 
/* 1655 */     if (value == null) {
/* 1656 */       if (this.defaults != null)
/* 1657 */         return this.defaults.getDouble(key, defaultValue); 
/* 1659 */       return defaultValue;
/*      */     } 
/* 1662 */     throw new ClassCastException('\'' + key + "' doesn't map to a Double object");
/*      */   }
/*      */   
/*      */   public static ExtendedProperties convertProperties(Properties props) {
/* 1676 */     ExtendedProperties c = new ExtendedProperties();
/* 1678 */     for (Enumeration e = props.propertyNames(); e.hasMoreElements(); ) {
/* 1679 */       String s = (String)e.nextElement();
/* 1680 */       c.setProperty(s, props.getProperty(s));
/*      */     } 
/* 1683 */     return c;
/*      */   }
/*      */   
/*      */   public ExtendedProperties() {}
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\ExtendedProperties.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */