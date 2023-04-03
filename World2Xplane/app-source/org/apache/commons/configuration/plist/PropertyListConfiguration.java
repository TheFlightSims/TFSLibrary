/*     */ package org.apache.commons.configuration.plist;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.PrintWriter;
/*     */ import java.io.Reader;
/*     */ import java.io.Writer;
/*     */ import java.net.URL;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.TimeZone;
/*     */ import org.apache.commons.codec.binary.Hex;
/*     */ import org.apache.commons.configuration.AbstractHierarchicalFileConfiguration;
/*     */ import org.apache.commons.configuration.Configuration;
/*     */ import org.apache.commons.configuration.ConfigurationException;
/*     */ import org.apache.commons.configuration.HierarchicalConfiguration;
/*     */ import org.apache.commons.configuration.MapConfiguration;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ 
/*     */ public class PropertyListConfiguration extends AbstractHierarchicalFileConfiguration {
/*  86 */   private static final DateComponentParser DATE_SEPARATOR_PARSER = new DateSeparatorParser("-");
/*     */   
/*  90 */   private static final DateComponentParser TIME_SEPARATOR_PARSER = new DateSeparatorParser(":");
/*     */   
/*  94 */   private static final DateComponentParser BLANK_SEPARATOR_PARSER = new DateSeparatorParser(" ");
/*     */   
/*  98 */   private static final DateComponentParser[] DATE_PARSERS = new DateComponentParser[] { 
/*  98 */       new DateSeparatorParser("<*D"), new DateFieldParser(1, 4), DATE_SEPARATOR_PARSER, new DateFieldParser(2, 2, 1), DATE_SEPARATOR_PARSER, new DateFieldParser(5, 2), BLANK_SEPARATOR_PARSER, new DateFieldParser(11, 2), TIME_SEPARATOR_PARSER, new DateFieldParser(12, 2), 
/*  98 */       TIME_SEPARATOR_PARSER, new DateFieldParser(13, 2), BLANK_SEPARATOR_PARSER, new DateTimeZoneParser(), new DateSeparatorParser(">") };
/*     */   
/*     */   private static final String TIME_ZONE_PREFIX = "GMT";
/*     */   
/*     */   private static final long serialVersionUID = 3227248503779092127L;
/*     */   
/*     */   private static final int MILLIS_PER_MINUTE = 60000;
/*     */   
/*     */   private static final int MINUTES_PER_HOUR = 60;
/*     */   
/*     */   private static final int INDENT_SIZE = 4;
/*     */   
/*     */   private static final int TIME_ZONE_LENGTH = 5;
/*     */   
/*     */   private static final char PAD_CHAR = '0';
/*     */   
/*     */   public PropertyListConfiguration() {}
/*     */   
/*     */   public PropertyListConfiguration(HierarchicalConfiguration c) {
/* 148 */     super(c);
/*     */   }
/*     */   
/*     */   public PropertyListConfiguration(String fileName) throws ConfigurationException {
/* 159 */     super(fileName);
/*     */   }
/*     */   
/*     */   public PropertyListConfiguration(File file) throws ConfigurationException {
/* 170 */     super(file);
/*     */   }
/*     */   
/*     */   public PropertyListConfiguration(URL url) throws ConfigurationException {
/* 181 */     super(url);
/*     */   }
/*     */   
/*     */   public void setProperty(String key, Object value) {
/* 187 */     if (value instanceof byte[]) {
/* 189 */       fireEvent(3, key, value, true);
/* 190 */       setDetailEvents(false);
/*     */       try {
/* 193 */         clearProperty(key);
/* 194 */         addPropertyDirect(key, value);
/*     */       } finally {
/* 198 */         setDetailEvents(true);
/*     */       } 
/* 200 */       fireEvent(3, key, value, false);
/*     */     } else {
/* 204 */       super.setProperty(key, value);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void addProperty(String key, Object value) {
/* 210 */     if (value instanceof byte[]) {
/* 212 */       fireEvent(1, key, value, true);
/* 213 */       addPropertyDirect(key, value);
/* 214 */       fireEvent(1, key, value, false);
/*     */     } else {
/* 218 */       super.addProperty(key, value);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void load(Reader in) throws ConfigurationException {
/* 224 */     PropertyListParser parser = new PropertyListParser(in);
/*     */     try {
/* 227 */       PropertyListConfiguration propertyListConfiguration = parser.parse();
/* 228 */       setRoot(propertyListConfiguration.getRoot());
/* 230 */     } catch (ParseException e) {
/* 232 */       throw new ConfigurationException(e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void save(Writer out) throws ConfigurationException {
/* 238 */     PrintWriter writer = new PrintWriter(out);
/* 239 */     printNode(writer, 0, getRoot());
/* 240 */     writer.flush();
/*     */   }
/*     */   
/*     */   private void printNode(PrintWriter out, int indentLevel, HierarchicalConfiguration.Node node) {
/* 248 */     String padding = StringUtils.repeat(" ", indentLevel * 4);
/* 250 */     if (node.getName() != null)
/* 252 */       out.print(padding + quoteString(node.getName()) + " = "); 
/* 255 */     List children = new ArrayList(node.getChildren());
/* 256 */     if (!children.isEmpty()) {
/* 259 */       if (indentLevel > 0)
/* 261 */         out.println(); 
/* 264 */       out.println(padding + "{");
/* 267 */       Iterator it = children.iterator();
/* 268 */       while (it.hasNext()) {
/* 270 */         HierarchicalConfiguration.Node child = it.next();
/* 272 */         printNode(out, indentLevel + 1, child);
/* 275 */         Object value = child.getValue();
/* 276 */         if (value != null && !(value instanceof Map) && !(value instanceof Configuration))
/* 278 */           out.println(";"); 
/* 282 */         if (it.hasNext() && (value == null || value instanceof List))
/* 284 */           out.println(); 
/*     */       } 
/* 288 */       out.print(padding + "}");
/* 291 */       if (node.getParent() != null)
/* 293 */         out.println(); 
/* 296 */     } else if (node.getValue() == null) {
/* 298 */       out.println();
/* 299 */       out.print(padding + "{ };");
/* 302 */       if (node.getParentNode() != null)
/* 304 */         out.println(); 
/*     */     } else {
/* 310 */       Object value = node.getValue();
/* 311 */       printValue(out, indentLevel, value);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void printValue(PrintWriter out, int indentLevel, Object value) {
/* 320 */     String padding = StringUtils.repeat(" ", indentLevel * 4);
/* 322 */     if (value instanceof List) {
/* 324 */       out.print("( ");
/* 325 */       Iterator it = ((List)value).iterator();
/* 326 */       while (it.hasNext()) {
/* 328 */         printValue(out, indentLevel + 1, it.next());
/* 329 */         if (it.hasNext())
/* 331 */           out.print(", "); 
/*     */       } 
/* 334 */       out.print(" )");
/* 336 */     } else if (value instanceof HierarchicalConfiguration) {
/* 338 */       printNode(out, indentLevel, ((HierarchicalConfiguration)value).getRoot());
/* 340 */     } else if (value instanceof Configuration) {
/* 343 */       out.println();
/* 344 */       out.println(padding + "{");
/* 346 */       Configuration config = (Configuration)value;
/* 347 */       Iterator it = config.getKeys();
/* 348 */       while (it.hasNext()) {
/* 350 */         String key = it.next();
/* 351 */         HierarchicalConfiguration.Node node = new HierarchicalConfiguration.Node(key);
/* 352 */         node.setValue(config.getProperty(key));
/* 354 */         printNode(out, indentLevel + 1, node);
/* 355 */         out.println(";");
/*     */       } 
/* 357 */       out.println(padding + "}");
/* 359 */     } else if (value instanceof Map) {
/* 362 */       Map map = (Map)value;
/* 363 */       printValue(out, indentLevel, new MapConfiguration(map));
/* 365 */     } else if (value instanceof byte[]) {
/* 367 */       out.print("<" + new String(Hex.encodeHex((byte[])value)) + ">");
/* 369 */     } else if (value instanceof Date) {
/* 371 */       out.print(formatDate((Date)value));
/* 373 */     } else if (value != null) {
/* 375 */       out.print(quoteString(String.valueOf(value)));
/*     */     } 
/*     */   }
/*     */   
/*     */   String quoteString(String s) {
/* 398 */     if (s == null)
/* 400 */       return null; 
/* 403 */     if (s.indexOf(' ') != -1 || s.indexOf('\t') != -1 || s.indexOf('\r') != -1 || s.indexOf('\n') != -1 || s.indexOf('"') != -1 || s.indexOf('(') != -1 || s.indexOf(')') != -1 || s.indexOf('{') != -1 || s.indexOf('}') != -1 || s.indexOf('=') != -1 || s.indexOf(',') != -1 || s.indexOf(';') != -1) {
/* 416 */       s = StringUtils.replace(s, "\"", "\\\"");
/* 417 */       s = "\"" + s + "\"";
/*     */     } 
/* 420 */     return s;
/*     */   }
/*     */   
/*     */   static Date parseDate(String s) throws ParseException {
/* 433 */     Calendar cal = Calendar.getInstance();
/* 434 */     cal.clear();
/* 435 */     int index = 0;
/* 437 */     for (int i = 0; i < DATE_PARSERS.length; i++)
/* 439 */       index += DATE_PARSERS[i].parseComponent(s, index, cal); 
/* 442 */     return cal.getTime();
/*     */   }
/*     */   
/*     */   static String formatDate(Calendar cal) {
/* 454 */     StringBuffer buf = new StringBuffer();
/* 456 */     for (int i = 0; i < DATE_PARSERS.length; i++)
/* 458 */       DATE_PARSERS[i].formatComponent(buf, cal); 
/* 461 */     return buf.toString();
/*     */   }
/*     */   
/*     */   static String formatDate(Date date) {
/* 472 */     Calendar cal = Calendar.getInstance();
/* 473 */     cal.setTime(date);
/* 474 */     return formatDate(cal);
/*     */   }
/*     */   
/*     */   private static abstract class DateComponentParser {
/*     */     private DateComponentParser() {}
/*     */     
/*     */     protected void checkLength(String s, int index, int length) throws ParseException {
/* 520 */       int len = (s == null) ? 0 : s.length();
/* 521 */       if (index + length > len)
/* 523 */         throw new ParseException("Input string too short: " + s + ", index: " + index); 
/*     */     }
/*     */     
/*     */     protected void padNum(StringBuffer buf, int num, int length) {
/* 538 */       buf.append(StringUtils.leftPad(String.valueOf(num), length, '0'));
/*     */     }
/*     */     
/*     */     public abstract int parseComponent(String param1String, int param1Int, Calendar param1Calendar) throws ParseException;
/*     */     
/*     */     public abstract void formatComponent(StringBuffer param1StringBuffer, Calendar param1Calendar);
/*     */   }
/*     */   
/*     */   private static class DateFieldParser extends DateComponentParser {
/*     */     private int calendarField;
/*     */     
/*     */     private int length;
/*     */     
/*     */     private int offset;
/*     */     
/*     */     public DateFieldParser(int calFld, int len) {
/* 567 */       this(calFld, len, 0);
/*     */     }
/*     */     
/*     */     public DateFieldParser(int calFld, int len, int ofs) {
/* 580 */       this.calendarField = calFld;
/* 581 */       this.length = len;
/* 582 */       this.offset = ofs;
/*     */     }
/*     */     
/*     */     public void formatComponent(StringBuffer buf, Calendar cal) {
/* 587 */       padNum(buf, cal.get(this.calendarField) + this.offset, this.length);
/*     */     }
/*     */     
/*     */     public int parseComponent(String s, int index, Calendar cal) throws ParseException {
/* 593 */       checkLength(s, index, this.length);
/*     */       try {
/* 596 */         cal.set(this.calendarField, Integer.parseInt(s.substring(index, index + this.length)) - this.offset);
/* 599 */         return this.length;
/* 601 */       } catch (NumberFormatException nfex) {
/* 603 */         throw new ParseException("Invalid number: " + s + ", index " + index);
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   private static class DateSeparatorParser extends DateComponentParser {
/*     */     private String separator;
/*     */     
/*     */     public DateSeparatorParser(String sep) {
/* 626 */       this.separator = sep;
/*     */     }
/*     */     
/*     */     public void formatComponent(StringBuffer buf, Calendar cal) {
/* 631 */       buf.append(this.separator);
/*     */     }
/*     */     
/*     */     public int parseComponent(String s, int index, Calendar cal) throws ParseException {
/* 637 */       checkLength(s, index, this.separator.length());
/* 638 */       if (!s.startsWith(this.separator, index))
/* 640 */         throw new ParseException("Invalid input: " + s + ", index " + index + ", expected " + this.separator); 
/* 643 */       return this.separator.length();
/*     */     }
/*     */   }
/*     */   
/*     */   private static class DateTimeZoneParser extends DateComponentParser {
/*     */     private DateTimeZoneParser() {}
/*     */     
/*     */     public void formatComponent(StringBuffer buf, Calendar cal) {
/* 655 */       TimeZone tz = cal.getTimeZone();
/* 656 */       int ofs = tz.getRawOffset() / 60000;
/* 657 */       if (ofs < 0) {
/* 659 */         buf.append('-');
/* 660 */         ofs = -ofs;
/*     */       } else {
/* 664 */         buf.append('+');
/*     */       } 
/* 666 */       int hour = ofs / 60;
/* 667 */       int min = ofs % 60;
/* 668 */       padNum(buf, hour, 2);
/* 669 */       padNum(buf, min, 2);
/*     */     }
/*     */     
/*     */     public int parseComponent(String s, int index, Calendar cal) throws ParseException {
/* 675 */       checkLength(s, index, 5);
/* 676 */       TimeZone tz = TimeZone.getTimeZone("GMT" + s.substring(index, index + 5));
/* 678 */       cal.setTimeZone(tz);
/* 679 */       return 5;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\configuration\plist\PropertyListConfiguration.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */