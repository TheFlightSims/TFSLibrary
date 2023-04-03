/*     */ package org.apache.commons.configuration;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintWriter;
/*     */ import java.io.Reader;
/*     */ import java.io.Writer;
/*     */ import java.net.URL;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.collections.set.ListOrderedSet;
/*     */ import org.apache.commons.configuration.tree.ConfigurationNode;
/*     */ import org.apache.commons.configuration.tree.ViewNode;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ 
/*     */ public class HierarchicalINIConfiguration extends AbstractHierarchicalFileConfiguration {
/*     */   protected static final String COMMENT_CHARS = "#;";
/*     */   
/*     */   protected static final String SEPARATOR_CHARS = "=:";
/*     */   
/*     */   private static final long serialVersionUID = 2548006161386850670L;
/*     */   
/* 225 */   private static final String LINE_SEPARATOR = System.getProperty("line.separator");
/*     */   
/*     */   private static final String QUOTE_CHARACTERS = "\"'";
/*     */   
/*     */   private static final String LINE_CONT = "\\";
/*     */   
/*     */   public HierarchicalINIConfiguration() {}
/*     */   
/*     */   public HierarchicalINIConfiguration(String filename) throws ConfigurationException {
/* 254 */     super(filename);
/*     */   }
/*     */   
/*     */   public HierarchicalINIConfiguration(File file) throws ConfigurationException {
/* 266 */     super(file);
/*     */   }
/*     */   
/*     */   public HierarchicalINIConfiguration(URL url) throws ConfigurationException {
/* 277 */     super(url);
/*     */   }
/*     */   
/*     */   public void save(Writer writer) throws ConfigurationException {
/* 289 */     PrintWriter out = new PrintWriter(writer);
/* 290 */     Iterator it = getSections().iterator();
/* 291 */     while (it.hasNext()) {
/*     */       Configuration subset;
/* 293 */       String section = it.next();
/* 295 */       if (section != null) {
/* 297 */         out.print("[");
/* 298 */         out.print(section);
/* 299 */         out.print("]");
/* 300 */         out.println();
/* 301 */         subset = createSubnodeConfiguration(getSectionNode(section));
/*     */       } else {
/* 305 */         subset = getSection((String)null);
/*     */       } 
/* 308 */       Iterator keys = subset.getKeys();
/* 309 */       while (keys.hasNext()) {
/* 311 */         String key = keys.next();
/* 312 */         Object value = subset.getProperty(key);
/* 313 */         if (value instanceof Collection) {
/* 315 */           Iterator values = ((Collection)value).iterator();
/* 316 */           while (values.hasNext()) {
/* 318 */             value = values.next();
/* 319 */             out.print(key);
/* 320 */             out.print(" = ");
/* 321 */             out.print(formatValue(value.toString()));
/* 322 */             out.println();
/*     */           } 
/*     */           continue;
/*     */         } 
/* 327 */         out.print(key);
/* 328 */         out.print(" = ");
/* 329 */         out.print(formatValue(value.toString()));
/* 330 */         out.println();
/*     */       } 
/* 334 */       out.println();
/*     */     } 
/* 337 */     out.flush();
/*     */   }
/*     */   
/*     */   public void load(Reader reader) throws ConfigurationException {
/*     */     try {
/* 353 */       BufferedReader bufferedReader = new BufferedReader(reader);
/* 354 */       ConfigurationNode sectionNode = getRootNode();
/* 356 */       String line = bufferedReader.readLine();
/* 357 */       while (line != null) {
/* 359 */         line = line.trim();
/* 360 */         if (!isCommentLine(line))
/* 362 */           if (isSectionLine(line)) {
/* 364 */             String section = line.substring(1, line.length() - 1);
/* 365 */             sectionNode = getSectionNode(section);
/*     */           } else {
/* 370 */             String key = "";
/* 371 */             String value = "";
/* 372 */             int index = findSeparator(line);
/* 373 */             if (index >= 0) {
/* 375 */               key = line.substring(0, index);
/* 376 */               value = parseValue(line.substring(index + 1), bufferedReader);
/*     */             } else {
/* 380 */               key = line;
/*     */             } 
/* 382 */             key = key.trim();
/* 383 */             if (key.length() < 1)
/* 386 */               key = " "; 
/* 388 */             HierarchicalConfiguration.Node node = createNode(key);
/* 389 */             node.setValue(value);
/* 390 */             sectionNode.addChild((ConfigurationNode)node);
/*     */           }  
/* 394 */         line = bufferedReader.readLine();
/*     */       } 
/* 397 */     } catch (IOException e) {
/* 399 */       throw new ConfigurationException("Unable to load the configuration", e);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static String parseValue(String val, BufferedReader reader) throws IOException {
/*     */     boolean lineContinues;
/* 427 */     StringBuffer propertyValue = new StringBuffer();
/* 429 */     String value = val.trim();
/*     */     do {
/* 433 */       boolean quoted = (value.startsWith("\"") || value.startsWith("'"));
/* 434 */       boolean stop = false;
/* 435 */       boolean escape = false;
/* 437 */       char quote = quoted ? value.charAt(0) : Character.MIN_VALUE;
/* 439 */       int i = quoted ? 1 : 0;
/* 441 */       StringBuffer result = new StringBuffer();
/* 442 */       char lastChar = Character.MIN_VALUE;
/* 443 */       while (i < value.length() && !stop) {
/* 445 */         char c = value.charAt(i);
/* 447 */         if (quoted) {
/* 449 */           if ('\\' == c && !escape) {
/* 451 */             escape = true;
/* 453 */           } else if (!escape && quote == c) {
/* 455 */             stop = true;
/* 457 */           } else if (escape && quote == c) {
/* 459 */             escape = false;
/* 460 */             result.append(c);
/*     */           } else {
/* 464 */             if (escape) {
/* 466 */               escape = false;
/* 467 */               result.append('\\');
/*     */             } 
/* 470 */             result.append(c);
/*     */           } 
/* 475 */         } else if (isCommentChar(c) && Character.isWhitespace(lastChar)) {
/* 477 */           stop = true;
/*     */         } else {
/* 481 */           result.append(c);
/*     */         } 
/* 485 */         i++;
/* 486 */         lastChar = c;
/*     */       } 
/* 489 */       String v = result.toString();
/* 490 */       if (!quoted) {
/* 492 */         v = v.trim();
/* 493 */         lineContinues = lineContinues(v);
/* 494 */         if (lineContinues)
/* 497 */           v = v.substring(0, v.length() - 1).trim(); 
/*     */       } else {
/* 502 */         lineContinues = lineContinues(value, i);
/*     */       } 
/* 504 */       propertyValue.append(v);
/* 506 */       if (!lineContinues)
/*     */         continue; 
/* 508 */       propertyValue.append(LINE_SEPARATOR);
/* 509 */       value = reader.readLine();
/* 511 */     } while (lineContinues && value != null);
/* 513 */     return propertyValue.toString();
/*     */   }
/*     */   
/*     */   private static boolean lineContinues(String line) {
/* 524 */     String s = line.trim();
/* 525 */     return (s.equals("\\") || (s.length() > 2 && s.endsWith("\\") && Character.isWhitespace(s.charAt(s.length() - 2))));
/*     */   }
/*     */   
/*     */   private static boolean lineContinues(String line, int pos) {
/*     */     String s;
/* 544 */     if (pos >= line.length()) {
/* 546 */       s = line;
/*     */     } else {
/* 550 */       int end = pos;
/* 551 */       while (end < line.length() && !isCommentChar(line.charAt(end)))
/* 553 */         end++; 
/* 555 */       s = line.substring(pos, end);
/*     */     } 
/* 558 */     return lineContinues(s);
/*     */   }
/*     */   
/*     */   private static boolean isCommentChar(char c) {
/* 569 */     return ("#;".indexOf(c) >= 0);
/*     */   }
/*     */   
/*     */   private static int findSeparator(String line) {
/* 584 */     int index = findSeparatorBeforeQuote(line, findFirstOccurrence(line, "\"'"));
/* 587 */     if (index < 0)
/* 589 */       index = findFirstOccurrence(line, "=:"); 
/* 591 */     return index;
/*     */   }
/*     */   
/*     */   private static int findFirstOccurrence(String line, String separators) {
/* 605 */     int index = -1;
/* 607 */     for (int i = 0; i < separators.length(); i++) {
/* 609 */       char sep = separators.charAt(i);
/* 610 */       int pos = line.indexOf(sep);
/* 611 */       if (pos >= 0)
/* 613 */         if (index < 0 || pos < index)
/* 615 */           index = pos;  
/*     */     } 
/* 620 */     return index;
/*     */   }
/*     */   
/*     */   private static int findSeparatorBeforeQuote(String line, int quoteIndex) {
/* 636 */     int index = quoteIndex - 1;
/* 637 */     while (index >= 0 && Character.isWhitespace(line.charAt(index)))
/* 639 */       index--; 
/* 642 */     if (index >= 0 && "=:".indexOf(line.charAt(index)) < 0)
/* 644 */       index = -1; 
/* 647 */     return index;
/*     */   }
/*     */   
/*     */   private String formatValue(String value) {
/* 655 */     boolean quoted = false;
/* 657 */     for (int i = 0; i < "#;".length() && !quoted; i++) {
/* 659 */       char c = "#;".charAt(i);
/* 660 */       if (value.indexOf(c) != -1)
/* 662 */         quoted = true; 
/*     */     } 
/* 666 */     if (quoted)
/* 668 */       return '"' + StringUtils.replace(value, "\"", "\\\"") + '"'; 
/* 672 */     return value;
/*     */   }
/*     */   
/*     */   protected boolean isCommentLine(String line) {
/* 685 */     if (line == null)
/* 687 */       return false; 
/* 690 */     return (line.length() < 1 || "#;".indexOf(line.charAt(0)) >= 0);
/*     */   }
/*     */   
/*     */   protected boolean isSectionLine(String line) {
/* 701 */     if (line == null)
/* 703 */       return false; 
/* 705 */     return (line.startsWith("[") && line.endsWith("]"));
/*     */   }
/*     */   
/*     */   public Set getSections() {
/* 716 */     ListOrderedSet listOrderedSet = new ListOrderedSet();
/* 717 */     boolean globalSection = false;
/* 718 */     boolean inSection = false;
/* 720 */     for (Iterator it = getRootNode().getChildren().iterator(); it.hasNext(); ) {
/* 722 */       ConfigurationNode node = it.next();
/* 723 */       if (isSectionNode(node)) {
/* 725 */         inSection = true;
/* 726 */         listOrderedSet.add(node.getName());
/*     */         continue;
/*     */       } 
/* 730 */       if (!inSection && !globalSection) {
/* 732 */         globalSection = true;
/* 733 */         listOrderedSet.add(null);
/*     */       } 
/*     */     } 
/* 738 */     return (Set)listOrderedSet;
/*     */   }
/*     */   
/*     */   public SubnodeConfiguration getSection(String name) {
/* 769 */     if (name == null)
/* 771 */       return getGlobalSection(); 
/*     */     try {
/* 778 */       return configurationAt(name);
/* 780 */     } catch (IllegalArgumentException iex) {
/* 784 */       return new SubnodeConfiguration(this, getSectionNode(name));
/*     */     } 
/*     */   }
/*     */   
/*     */   private ConfigurationNode getSectionNode(String sectionName) {
/* 799 */     List nodes = getRootNode().getChildren(sectionName);
/* 800 */     if (!nodes.isEmpty())
/* 802 */       return nodes.get(0); 
/* 805 */     HierarchicalConfiguration.Node node = createNode(sectionName);
/* 806 */     markSectionNode((ConfigurationNode)node);
/* 807 */     getRootNode().addChild((ConfigurationNode)node);
/* 808 */     return (ConfigurationNode)node;
/*     */   }
/*     */   
/*     */   private SubnodeConfiguration getGlobalSection() {
/* 819 */     ViewNode parent = new ViewNode();
/* 821 */     for (Iterator it = getRootNode().getChildren().iterator(); it.hasNext(); ) {
/* 823 */       ConfigurationNode node = it.next();
/* 824 */       if (!isSectionNode(node))
/* 826 */         synchronized (node) {
/* 828 */           parent.addChild(node);
/*     */         }  
/*     */     } 
/* 833 */     return createSubnodeConfiguration((ConfigurationNode)parent);
/*     */   }
/*     */   
/*     */   private static void markSectionNode(ConfigurationNode node) {
/* 845 */     node.setReference(Boolean.TRUE);
/*     */   }
/*     */   
/*     */   private static boolean isSectionNode(ConfigurationNode node) {
/* 856 */     return (node.getReference() != null || node.getChildrenCount() > 0);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\configuration\HierarchicalINIConfiguration.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */