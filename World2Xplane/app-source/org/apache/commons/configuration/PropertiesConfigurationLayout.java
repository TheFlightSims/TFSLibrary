/*      */ package org.apache.commons.configuration;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.io.Reader;
/*      */ import java.io.Writer;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Set;
/*      */ import org.apache.commons.collections.map.LinkedMap;
/*      */ import org.apache.commons.configuration.event.ConfigurationEvent;
/*      */ import org.apache.commons.configuration.event.ConfigurationListener;
/*      */ import org.apache.commons.lang.StringUtils;
/*      */ 
/*      */ public class PropertiesConfigurationLayout implements ConfigurationListener {
/*      */   private static final String CR = "\n";
/*      */   
/*      */   private static final String COMMENT_PREFIX = "# ";
/*      */   
/*      */   private PropertiesConfiguration configuration;
/*      */   
/*      */   private Map layoutData;
/*      */   
/*      */   private String headerComment;
/*      */   
/*      */   private String globalSeparator;
/*      */   
/*      */   private String lineSeparator;
/*      */   
/*      */   private int loadCounter;
/*      */   
/*      */   private boolean forceSingleLine;
/*      */   
/*      */   public PropertiesConfigurationLayout(PropertiesConfiguration config) {
/*  155 */     this(config, null);
/*      */   }
/*      */   
/*      */   public PropertiesConfigurationLayout(PropertiesConfiguration config, PropertiesConfigurationLayout c) {
/*  169 */     if (config == null)
/*  171 */       throw new IllegalArgumentException("Configuration must not be null!"); 
/*  174 */     this.configuration = config;
/*  175 */     this.layoutData = (Map)new LinkedMap();
/*  176 */     config.addConfigurationListener(this);
/*  178 */     if (c != null)
/*  180 */       copyFrom(c); 
/*      */   }
/*      */   
/*      */   public PropertiesConfiguration getConfiguration() {
/*  191 */     return this.configuration;
/*      */   }
/*      */   
/*      */   public String getCanonicalComment(String key, boolean commentChar) {
/*  210 */     String comment = getComment(key);
/*  211 */     if (comment == null)
/*  213 */       return null; 
/*  217 */     return trimComment(comment, commentChar);
/*      */   }
/*      */   
/*      */   public String getComment(String key) {
/*  232 */     return fetchLayoutData(key).getComment();
/*      */   }
/*      */   
/*      */   public void setComment(String key, String comment) {
/*  247 */     fetchLayoutData(key).setComment(comment);
/*      */   }
/*      */   
/*      */   public int getBlancLinesBefore(String key) {
/*  260 */     return fetchLayoutData(key).getBlancLines();
/*      */   }
/*      */   
/*      */   public void setBlancLinesBefore(String key, int number) {
/*  273 */     fetchLayoutData(key).setBlancLines(number);
/*      */   }
/*      */   
/*      */   public String getCanonicalHeaderComment(boolean commentChar) {
/*  287 */     return (getHeaderComment() == null) ? null : trimComment(getHeaderComment(), commentChar);
/*      */   }
/*      */   
/*      */   public String getHeaderComment() {
/*  301 */     return this.headerComment;
/*      */   }
/*      */   
/*      */   public void setHeaderComment(String comment) {
/*  312 */     this.headerComment = comment;
/*      */   }
/*      */   
/*      */   public boolean isSingleLine(String key) {
/*  324 */     return fetchLayoutData(key).isSingleLine();
/*      */   }
/*      */   
/*      */   public void setSingleLine(String key, boolean f) {
/*  340 */     fetchLayoutData(key).setSingleLine(f);
/*      */   }
/*      */   
/*      */   public boolean isForceSingleLine() {
/*  351 */     return this.forceSingleLine;
/*      */   }
/*      */   
/*      */   public void setForceSingleLine(boolean f) {
/*  365 */     this.forceSingleLine = f;
/*      */   }
/*      */   
/*      */   public String getSeparator(String key) {
/*  377 */     return fetchLayoutData(key).getSeparator();
/*      */   }
/*      */   
/*      */   public void setSeparator(String key, String sep) {
/*  398 */     fetchLayoutData(key).setSeparator(sep);
/*      */   }
/*      */   
/*      */   public String getGlobalSeparator() {
/*  409 */     return this.globalSeparator;
/*      */   }
/*      */   
/*      */   public void setGlobalSeparator(String globalSeparator) {
/*  428 */     this.globalSeparator = globalSeparator;
/*      */   }
/*      */   
/*      */   public String getLineSeparator() {
/*  439 */     return this.lineSeparator;
/*      */   }
/*      */   
/*      */   public void setLineSeparator(String lineSeparator) {
/*  452 */     this.lineSeparator = lineSeparator;
/*      */   }
/*      */   
/*      */   public Set getKeys() {
/*  462 */     return this.layoutData.keySet();
/*      */   }
/*      */   
/*      */   public void load(Reader in) throws ConfigurationException {
/*  474 */     if (++this.loadCounter == 1)
/*  476 */       getConfiguration().removeConfigurationListener(this); 
/*  478 */     PropertiesConfiguration.PropertiesReader reader = getConfiguration().getIOFactory().createPropertiesReader(in, getConfiguration().getListDelimiter());
/*      */     try {
/*  484 */       while (reader.nextProperty()) {
/*  486 */         if (getConfiguration().propertyLoaded(reader.getPropertyName(), reader.getPropertyValue())) {
/*  489 */           boolean contained = this.layoutData.containsKey(reader.getPropertyName());
/*  491 */           int blancLines = 0;
/*  492 */           int idx = checkHeaderComment(reader.getCommentLines());
/*  495 */           while (idx < reader.getCommentLines().size() && ((String)reader.getCommentLines().get(idx)).length() < 1) {
/*  497 */             idx++;
/*  498 */             blancLines++;
/*      */           } 
/*  500 */           String comment = extractComment(reader.getCommentLines(), idx, reader.getCommentLines().size() - 1);
/*  502 */           PropertyLayoutData data = fetchLayoutData(reader.getPropertyName());
/*  504 */           if (contained) {
/*  506 */             data.addComment(comment);
/*  507 */             data.setSingleLine(false);
/*      */             continue;
/*      */           } 
/*  511 */           data.setComment(comment);
/*  512 */           data.setBlancLines(blancLines);
/*  513 */           data.setSeparator(reader.getPropertySeparator());
/*      */         } 
/*      */       } 
/*  518 */     } catch (IOException ioex) {
/*  520 */       throw new ConfigurationException(ioex);
/*      */     } finally {
/*  524 */       if (--this.loadCounter == 0)
/*  526 */         getConfiguration().addConfigurationListener(this); 
/*      */     } 
/*      */   }
/*      */   
/*      */   public void save(Writer out) throws ConfigurationException {
/*      */     try {
/*  542 */       char delimiter = getConfiguration().isDelimiterParsingDisabled() ? Character.MIN_VALUE : getConfiguration().getListDelimiter();
/*  544 */       PropertiesConfiguration.PropertiesWriter writer = getConfiguration().getIOFactory().createPropertiesWriter(out, delimiter);
/*  546 */       writer.setGlobalSeparator(getGlobalSeparator());
/*  547 */       if (getLineSeparator() != null)
/*  549 */         writer.setLineSeparator(getLineSeparator()); 
/*  552 */       if (this.headerComment != null) {
/*  554 */         writeComment(writer, getCanonicalHeaderComment(true));
/*  555 */         writer.writeln((String)null);
/*      */       } 
/*  558 */       for (Iterator it = this.layoutData.keySet().iterator(); it.hasNext(); ) {
/*  560 */         String key = it.next();
/*  561 */         if (getConfiguration().containsKey(key)) {
/*  565 */           for (int i = 0; i < getBlancLinesBefore(key); i++)
/*  567 */             writer.writeln((String)null); 
/*  571 */           writeComment(writer, getCanonicalComment(key, true));
/*  574 */           boolean singleLine = ((isForceSingleLine() || isSingleLine(key)) && !getConfiguration().isDelimiterParsingDisabled());
/*  576 */           writer.setCurrentSeparator(getSeparator(key));
/*  577 */           writer.writeProperty(key, getConfiguration().getProperty(key), singleLine);
/*      */         } 
/*      */       } 
/*  581 */       writer.flush();
/*  583 */     } catch (IOException ioex) {
/*  585 */       throw new ConfigurationException(ioex);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void configurationChanged(ConfigurationEvent event) {
/*  597 */     if (event.isBeforeUpdate()) {
/*  599 */       if (20 == event.getType())
/*  601 */         clear(); 
/*      */     } else {
/*      */       boolean contained;
/*      */       PropertyLayoutData data;
/*  607 */       switch (event.getType()) {
/*      */         case 1:
/*  610 */           contained = this.layoutData.containsKey(event.getPropertyName());
/*  612 */           data = fetchLayoutData(event.getPropertyName());
/*  614 */           data.setSingleLine(!contained);
/*      */           break;
/*      */         case 2:
/*  617 */           this.layoutData.remove(event.getPropertyName());
/*      */           break;
/*      */         case 4:
/*  620 */           clear();
/*      */           break;
/*      */         case 3:
/*  623 */           fetchLayoutData(event.getPropertyName());
/*      */           break;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private PropertyLayoutData fetchLayoutData(String key) {
/*  638 */     if (key == null)
/*  640 */       throw new IllegalArgumentException("Property key must not be null!"); 
/*  643 */     PropertyLayoutData data = (PropertyLayoutData)this.layoutData.get(key);
/*  644 */     if (data == null) {
/*  646 */       data = new PropertyLayoutData();
/*  647 */       data.setSingleLine(true);
/*  648 */       this.layoutData.put(key, data);
/*      */     } 
/*  651 */     return data;
/*      */   }
/*      */   
/*      */   private void clear() {
/*  659 */     this.layoutData.clear();
/*  660 */     setHeaderComment(null);
/*      */   }
/*      */   
/*      */   static boolean isCommentLine(String line) {
/*  672 */     return PropertiesConfiguration.isCommentLine(line);
/*      */   }
/*      */   
/*      */   static String trimComment(String s, boolean comment) {
/*      */     int pos;
/*  687 */     StringBuffer buf = new StringBuffer(s.length());
/*  688 */     int lastPos = 0;
/*      */     do {
/*  693 */       pos = s.indexOf("\n", lastPos);
/*  694 */       if (pos < 0)
/*      */         continue; 
/*  696 */       String line = s.substring(lastPos, pos);
/*  697 */       buf.append(stripCommentChar(line, comment)).append("\n");
/*  698 */       lastPos = pos + "\n".length();
/*  700 */     } while (pos >= 0);
/*  702 */     if (lastPos < s.length())
/*  704 */       buf.append(stripCommentChar(s.substring(lastPos), comment)); 
/*  706 */     return buf.toString();
/*      */   }
/*      */   
/*      */   static String stripCommentChar(String s, boolean comment) {
/*  720 */     if (s.length() < 1 || isCommentLine(s) == comment)
/*  722 */       return s; 
/*  727 */     if (!comment) {
/*  729 */       int pos = 0;
/*  732 */       while ("#!".indexOf(s.charAt(pos)) < 0)
/*  734 */         pos++; 
/*  738 */       pos++;
/*  740 */       while (pos < s.length() && Character.isWhitespace(s.charAt(pos)))
/*  742 */         pos++; 
/*  745 */       return (pos < s.length()) ? s.substring(pos) : "";
/*      */     } 
/*  750 */     return "# " + s;
/*      */   }
/*      */   
/*      */   private String extractComment(List commentLines, int from, int to) {
/*  766 */     if (to < from)
/*  768 */       return null; 
/*  773 */     StringBuffer buf = new StringBuffer(commentLines.get(from));
/*  774 */     for (int i = from + 1; i <= to; i++) {
/*  776 */       buf.append("\n");
/*  777 */       buf.append(commentLines.get(i));
/*      */     } 
/*  779 */     return buf.toString();
/*      */   }
/*      */   
/*      */   private int checkHeaderComment(List commentLines) {
/*  796 */     if (this.loadCounter == 1 && getHeaderComment() == null && this.layoutData.isEmpty()) {
/*  800 */       int index = commentLines.size() - 1;
/*  802 */       while (index >= 0 && ((String)commentLines.get(index)).length() > 0)
/*  804 */         index--; 
/*  806 */       setHeaderComment(extractComment(commentLines, 0, index - 1));
/*  807 */       return index + 1;
/*      */     } 
/*  811 */     return 0;
/*      */   }
/*      */   
/*      */   private void copyFrom(PropertiesConfigurationLayout c) {
/*  822 */     for (Iterator it = c.getKeys().iterator(); it.hasNext(); ) {
/*  824 */       String key = it.next();
/*  825 */       PropertyLayoutData data = (PropertyLayoutData)c.layoutData.get(key);
/*  827 */       this.layoutData.put(key, data.clone());
/*      */     } 
/*      */   }
/*      */   
/*      */   private static void writeComment(PropertiesConfiguration.PropertiesWriter writer, String comment) throws IOException {
/*  843 */     if (comment != null)
/*  845 */       writer.writeln(StringUtils.replace(comment, "\n", writer.getLineSeparator())); 
/*      */   }
/*      */   
/*      */   static class PropertyLayoutData implements Cloneable {
/*      */     private boolean singleLine = true;
/*      */     
/*      */     private int blancLines;
/*      */     
/*  874 */     private String separator = " = ";
/*      */     
/*      */     private StringBuffer comment;
/*      */     
/*      */     public int getBlancLines() {
/*  884 */       return this.blancLines;
/*      */     }
/*      */     
/*      */     public void setBlancLines(int blancLines) {
/*  894 */       this.blancLines = blancLines;
/*      */     }
/*      */     
/*      */     public boolean isSingleLine() {
/*  904 */       return this.singleLine;
/*      */     }
/*      */     
/*      */     public void setSingleLine(boolean singleLine) {
/*  914 */       this.singleLine = singleLine;
/*      */     }
/*      */     
/*      */     public void addComment(String s) {
/*  925 */       if (s != null)
/*  927 */         if (this.comment == null) {
/*  929 */           this.comment = new StringBuffer(s);
/*      */         } else {
/*  933 */           this.comment.append("\n").append(s);
/*      */         }  
/*      */     }
/*      */     
/*      */     public void setComment(String s) {
/*  945 */       if (s == null) {
/*  947 */         this.comment = null;
/*      */       } else {
/*  951 */         this.comment = new StringBuffer(s);
/*      */       } 
/*      */     }
/*      */     
/*      */     public String getComment() {
/*  963 */       return (this.comment == null) ? null : this.comment.toString();
/*      */     }
/*      */     
/*      */     public String getSeparator() {
/*  973 */       return this.separator;
/*      */     }
/*      */     
/*      */     public void setSeparator(String separator) {
/*  983 */       this.separator = separator;
/*      */     }
/*      */     
/*      */     public Object clone() {
/*      */       try {
/*  995 */         PropertyLayoutData copy = (PropertyLayoutData)super.clone();
/*  996 */         if (this.comment != null)
/*  999 */           copy.comment = new StringBuffer(getComment()); 
/* 1001 */         return copy;
/* 1003 */       } catch (CloneNotSupportedException cnex) {
/* 1006 */         throw new ConfigurationRuntimeException(cnex);
/*      */       } 
/*      */     }
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\configuration\PropertiesConfigurationLayout.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */