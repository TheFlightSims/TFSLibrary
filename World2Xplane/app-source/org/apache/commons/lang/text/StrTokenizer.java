/*      */ package org.apache.commons.lang.text;
/*      */ 
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collections;
/*      */ import java.util.List;
/*      */ import java.util.ListIterator;
/*      */ import java.util.NoSuchElementException;
/*      */ 
/*      */ public class StrTokenizer implements ListIterator, Cloneable {
/*   93 */   private static final StrTokenizer CSV_TOKENIZER_PROTOTYPE = new StrTokenizer();
/*      */   
/*      */   static {
/*   94 */     CSV_TOKENIZER_PROTOTYPE.setDelimiterMatcher(StrMatcher.commaMatcher());
/*   95 */     CSV_TOKENIZER_PROTOTYPE.setQuoteMatcher(StrMatcher.doubleQuoteMatcher());
/*   96 */     CSV_TOKENIZER_PROTOTYPE.setIgnoredMatcher(StrMatcher.noneMatcher());
/*   97 */     CSV_TOKENIZER_PROTOTYPE.setTrimmerMatcher(StrMatcher.trimMatcher());
/*   98 */     CSV_TOKENIZER_PROTOTYPE.setEmptyTokenAsNull(false);
/*   99 */     CSV_TOKENIZER_PROTOTYPE.setIgnoreEmptyTokens(false);
/*      */   }
/*      */   
/*  101 */   private static final StrTokenizer TSV_TOKENIZER_PROTOTYPE = new StrTokenizer();
/*      */   
/*      */   private char[] chars;
/*      */   
/*      */   private String[] tokens;
/*      */   
/*      */   private int tokenPos;
/*      */   
/*      */   static {
/*  102 */     TSV_TOKENIZER_PROTOTYPE.setDelimiterMatcher(StrMatcher.tabMatcher());
/*  103 */     TSV_TOKENIZER_PROTOTYPE.setQuoteMatcher(StrMatcher.doubleQuoteMatcher());
/*  104 */     TSV_TOKENIZER_PROTOTYPE.setIgnoredMatcher(StrMatcher.noneMatcher());
/*  105 */     TSV_TOKENIZER_PROTOTYPE.setTrimmerMatcher(StrMatcher.trimMatcher());
/*  106 */     TSV_TOKENIZER_PROTOTYPE.setEmptyTokenAsNull(false);
/*  107 */     TSV_TOKENIZER_PROTOTYPE.setIgnoreEmptyTokens(false);
/*      */   }
/*      */   
/*  118 */   private StrMatcher delimMatcher = StrMatcher.splitMatcher();
/*      */   
/*  120 */   private StrMatcher quoteMatcher = StrMatcher.noneMatcher();
/*      */   
/*  122 */   private StrMatcher ignoredMatcher = StrMatcher.noneMatcher();
/*      */   
/*  124 */   private StrMatcher trimmerMatcher = StrMatcher.noneMatcher();
/*      */   
/*      */   private boolean emptyAsNull = false;
/*      */   
/*      */   private boolean ignoreEmptyTokens = true;
/*      */   
/*      */   private static StrTokenizer getCSVClone() {
/*  139 */     return (StrTokenizer)CSV_TOKENIZER_PROTOTYPE.clone();
/*      */   }
/*      */   
/*      */   public static StrTokenizer getCSVInstance() {
/*  152 */     return getCSVClone();
/*      */   }
/*      */   
/*      */   public static StrTokenizer getCSVInstance(String input) {
/*  165 */     StrTokenizer tok = getCSVClone();
/*  166 */     tok.reset(input);
/*  167 */     return tok;
/*      */   }
/*      */   
/*      */   public static StrTokenizer getCSVInstance(char[] input) {
/*  180 */     StrTokenizer tok = getCSVClone();
/*  181 */     tok.reset(input);
/*  182 */     return tok;
/*      */   }
/*      */   
/*      */   private static StrTokenizer getTSVClone() {
/*  191 */     return (StrTokenizer)TSV_TOKENIZER_PROTOTYPE.clone();
/*      */   }
/*      */   
/*      */   public static StrTokenizer getTSVInstance() {
/*  204 */     return getTSVClone();
/*      */   }
/*      */   
/*      */   public static StrTokenizer getTSVInstance(String input) {
/*  215 */     StrTokenizer tok = getTSVClone();
/*  216 */     tok.reset(input);
/*  217 */     return tok;
/*      */   }
/*      */   
/*      */   public static StrTokenizer getTSVInstance(char[] input) {
/*  228 */     StrTokenizer tok = getTSVClone();
/*  229 */     tok.reset(input);
/*  230 */     return tok;
/*      */   }
/*      */   
/*      */   public StrTokenizer() {
/*  242 */     this.chars = null;
/*      */   }
/*      */   
/*      */   public StrTokenizer(String input) {
/*  253 */     if (input != null) {
/*  254 */       this.chars = input.toCharArray();
/*      */     } else {
/*  256 */       this.chars = null;
/*      */     } 
/*      */   }
/*      */   
/*      */   public StrTokenizer(String input, char delim) {
/*  267 */     this(input);
/*  268 */     setDelimiterChar(delim);
/*      */   }
/*      */   
/*      */   public StrTokenizer(String input, String delim) {
/*  278 */     this(input);
/*  279 */     setDelimiterString(delim);
/*      */   }
/*      */   
/*      */   public StrTokenizer(String input, StrMatcher delim) {
/*  289 */     this(input);
/*  290 */     setDelimiterMatcher(delim);
/*      */   }
/*      */   
/*      */   public StrTokenizer(String input, char delim, char quote) {
/*  302 */     this(input, delim);
/*  303 */     setQuoteChar(quote);
/*      */   }
/*      */   
/*      */   public StrTokenizer(String input, StrMatcher delim, StrMatcher quote) {
/*  315 */     this(input, delim);
/*  316 */     setQuoteMatcher(quote);
/*      */   }
/*      */   
/*      */   public StrTokenizer(char[] input) {
/*  330 */     this.chars = input;
/*      */   }
/*      */   
/*      */   public StrTokenizer(char[] input, char delim) {
/*  343 */     this(input);
/*  344 */     setDelimiterChar(delim);
/*      */   }
/*      */   
/*      */   public StrTokenizer(char[] input, String delim) {
/*  357 */     this(input);
/*  358 */     setDelimiterString(delim);
/*      */   }
/*      */   
/*      */   public StrTokenizer(char[] input, StrMatcher delim) {
/*  371 */     this(input);
/*  372 */     setDelimiterMatcher(delim);
/*      */   }
/*      */   
/*      */   public StrTokenizer(char[] input, char delim, char quote) {
/*  387 */     this(input, delim);
/*  388 */     setQuoteChar(quote);
/*      */   }
/*      */   
/*      */   public StrTokenizer(char[] input, StrMatcher delim, StrMatcher quote) {
/*  403 */     this(input, delim);
/*  404 */     setQuoteMatcher(quote);
/*      */   }
/*      */   
/*      */   public int size() {
/*  415 */     checkTokenized();
/*  416 */     return this.tokens.length;
/*      */   }
/*      */   
/*      */   public String nextToken() {
/*  427 */     if (hasNext())
/*  428 */       return this.tokens[this.tokenPos++]; 
/*  430 */     return null;
/*      */   }
/*      */   
/*      */   public String previousToken() {
/*  439 */     if (hasPrevious())
/*  440 */       return this.tokens[--this.tokenPos]; 
/*  442 */     return null;
/*      */   }
/*      */   
/*      */   public String[] getTokenArray() {
/*  451 */     checkTokenized();
/*  452 */     return (String[])this.tokens.clone();
/*      */   }
/*      */   
/*      */   public List getTokenList() {
/*  461 */     checkTokenized();
/*  462 */     List list = new ArrayList(this.tokens.length);
/*  463 */     for (int i = 0; i < this.tokens.length; i++)
/*  464 */       list.add(this.tokens[i]); 
/*  466 */     return list;
/*      */   }
/*      */   
/*      */   public StrTokenizer reset() {
/*  477 */     this.tokenPos = 0;
/*  478 */     this.tokens = null;
/*  479 */     return this;
/*      */   }
/*      */   
/*      */   public StrTokenizer reset(String input) {
/*  491 */     reset();
/*  492 */     if (input != null) {
/*  493 */       this.chars = input.toCharArray();
/*      */     } else {
/*  495 */       this.chars = null;
/*      */     } 
/*  497 */     return this;
/*      */   }
/*      */   
/*      */   public StrTokenizer reset(char[] input) {
/*  512 */     reset();
/*  513 */     this.chars = input;
/*  514 */     return this;
/*      */   }
/*      */   
/*      */   public boolean hasNext() {
/*  525 */     checkTokenized();
/*  526 */     return (this.tokenPos < this.tokens.length);
/*      */   }
/*      */   
/*      */   public Object next() {
/*  536 */     if (hasNext())
/*  537 */       return this.tokens[this.tokenPos++]; 
/*  539 */     throw new NoSuchElementException();
/*      */   }
/*      */   
/*      */   public int nextIndex() {
/*  548 */     return this.tokenPos;
/*      */   }
/*      */   
/*      */   public boolean hasPrevious() {
/*  557 */     checkTokenized();
/*  558 */     return (this.tokenPos > 0);
/*      */   }
/*      */   
/*      */   public Object previous() {
/*  567 */     if (hasPrevious())
/*  568 */       return this.tokens[--this.tokenPos]; 
/*  570 */     throw new NoSuchElementException();
/*      */   }
/*      */   
/*      */   public int previousIndex() {
/*  579 */     return this.tokenPos - 1;
/*      */   }
/*      */   
/*      */   public void remove() {
/*  588 */     throw new UnsupportedOperationException("remove() is unsupported");
/*      */   }
/*      */   
/*      */   public void set(Object obj) {
/*  597 */     throw new UnsupportedOperationException("set() is unsupported");
/*      */   }
/*      */   
/*      */   public void add(Object obj) {
/*  606 */     throw new UnsupportedOperationException("add() is unsupported");
/*      */   }
/*      */   
/*      */   private void checkTokenized() {
/*  615 */     if (this.tokens == null)
/*  616 */       if (this.chars == null) {
/*  618 */         List split = tokenize(null, 0, 0);
/*  619 */         this.tokens = (String[])split.toArray((Object[])new String[split.size()]);
/*      */       } else {
/*  621 */         List split = tokenize(this.chars, 0, this.chars.length);
/*  622 */         this.tokens = (String[])split.toArray((Object[])new String[split.size()]);
/*      */       }  
/*      */   }
/*      */   
/*      */   protected List tokenize(char[] chars, int offset, int count) {
/*  648 */     if (chars == null || count == 0)
/*  649 */       return Collections.EMPTY_LIST; 
/*  651 */     StrBuilder buf = new StrBuilder();
/*  652 */     List tokens = new ArrayList();
/*  653 */     int pos = offset;
/*  656 */     while (pos >= 0 && pos < count) {
/*  658 */       pos = readNextToken(chars, pos, count, buf, tokens);
/*  661 */       if (pos >= count)
/*  662 */         addToken(tokens, ""); 
/*      */     } 
/*  665 */     return tokens;
/*      */   }
/*      */   
/*      */   private void addToken(List list, String tok) {
/*  675 */     if (tok == null || tok.length() == 0) {
/*  676 */       if (isIgnoreEmptyTokens())
/*      */         return; 
/*  679 */       if (isEmptyTokenAsNull())
/*  680 */         tok = null; 
/*      */     } 
/*  683 */     list.add(tok);
/*      */   }
/*      */   
/*      */   private int readNextToken(char[] chars, int start, int len, StrBuilder workArea, List tokens) {
/*  700 */     while (start < len) {
/*  701 */       int removeLen = Math.max(getIgnoredMatcher().isMatch(chars, start, start, len), getTrimmerMatcher().isMatch(chars, start, start, len));
/*  704 */       if (removeLen == 0 || getDelimiterMatcher().isMatch(chars, start, start, len) > 0 || getQuoteMatcher().isMatch(chars, start, start, len) > 0)
/*      */         break; 
/*  709 */       start += removeLen;
/*      */     } 
/*  713 */     if (start >= len) {
/*  714 */       addToken(tokens, "");
/*  715 */       return -1;
/*      */     } 
/*  719 */     int delimLen = getDelimiterMatcher().isMatch(chars, start, start, len);
/*  720 */     if (delimLen > 0) {
/*  721 */       addToken(tokens, "");
/*  722 */       return start + delimLen;
/*      */     } 
/*  726 */     int quoteLen = getQuoteMatcher().isMatch(chars, start, start, len);
/*  727 */     if (quoteLen > 0)
/*  728 */       return readWithQuotes(chars, start + quoteLen, len, workArea, tokens, start, quoteLen); 
/*  730 */     return readWithQuotes(chars, start, len, workArea, tokens, 0, 0);
/*      */   }
/*      */   
/*      */   private int readWithQuotes(char[] chars, int start, int len, StrBuilder workArea, List tokens, int quoteStart, int quoteLen) {
/*  752 */     workArea.clear();
/*  753 */     int pos = start;
/*  754 */     boolean quoting = (quoteLen > 0);
/*  755 */     int trimStart = 0;
/*  757 */     while (pos < len) {
/*  761 */       if (quoting) {
/*  768 */         if (isQuote(chars, pos, len, quoteStart, quoteLen)) {
/*  769 */           if (isQuote(chars, pos + quoteLen, len, quoteStart, quoteLen)) {
/*  771 */             workArea.append(chars, pos, quoteLen);
/*  772 */             pos += quoteLen * 2;
/*  773 */             trimStart = workArea.size();
/*      */             continue;
/*      */           } 
/*  778 */           quoting = false;
/*  779 */           pos += quoteLen;
/*      */           continue;
/*      */         } 
/*  784 */         workArea.append(chars[pos++]);
/*  785 */         trimStart = workArea.size();
/*      */         continue;
/*      */       } 
/*  791 */       int delimLen = getDelimiterMatcher().isMatch(chars, pos, start, len);
/*  792 */       if (delimLen > 0) {
/*  794 */         addToken(tokens, workArea.substring(0, trimStart));
/*  795 */         return pos + delimLen;
/*      */       } 
/*  799 */       if (quoteLen > 0 && 
/*  800 */         isQuote(chars, pos, len, quoteStart, quoteLen)) {
/*  801 */         quoting = true;
/*  802 */         pos += quoteLen;
/*      */         continue;
/*      */       } 
/*  808 */       int ignoredLen = getIgnoredMatcher().isMatch(chars, pos, start, len);
/*  809 */       if (ignoredLen > 0) {
/*  810 */         pos += ignoredLen;
/*      */         continue;
/*      */       } 
/*  817 */       int trimmedLen = getTrimmerMatcher().isMatch(chars, pos, start, len);
/*  818 */       if (trimmedLen > 0) {
/*  819 */         workArea.append(chars, pos, trimmedLen);
/*  820 */         pos += trimmedLen;
/*      */         continue;
/*      */       } 
/*  825 */       workArea.append(chars[pos++]);
/*  826 */       trimStart = workArea.size();
/*      */     } 
/*  831 */     addToken(tokens, workArea.substring(0, trimStart));
/*  832 */     return -1;
/*      */   }
/*      */   
/*      */   private boolean isQuote(char[] chars, int pos, int len, int quoteStart, int quoteLen) {
/*  847 */     for (int i = 0; i < quoteLen; i++) {
/*  848 */       if (pos + i >= len || chars[pos + i] != chars[quoteStart + i])
/*  849 */         return false; 
/*      */     } 
/*  852 */     return true;
/*      */   }
/*      */   
/*      */   public StrMatcher getDelimiterMatcher() {
/*  863 */     return this.delimMatcher;
/*      */   }
/*      */   
/*      */   public StrTokenizer setDelimiterMatcher(StrMatcher delim) {
/*  875 */     if (delim == null) {
/*  876 */       this.delimMatcher = StrMatcher.noneMatcher();
/*      */     } else {
/*  878 */       this.delimMatcher = delim;
/*      */     } 
/*  880 */     return this;
/*      */   }
/*      */   
/*      */   public StrTokenizer setDelimiterChar(char delim) {
/*  890 */     return setDelimiterMatcher(StrMatcher.charMatcher(delim));
/*      */   }
/*      */   
/*      */   public StrTokenizer setDelimiterString(String delim) {
/*  900 */     return setDelimiterMatcher(StrMatcher.stringMatcher(delim));
/*      */   }
/*      */   
/*      */   public StrMatcher getQuoteMatcher() {
/*  915 */     return this.quoteMatcher;
/*      */   }
/*      */   
/*      */   public StrTokenizer setQuoteMatcher(StrMatcher quote) {
/*  928 */     if (quote != null)
/*  929 */       this.quoteMatcher = quote; 
/*  931 */     return this;
/*      */   }
/*      */   
/*      */   public StrTokenizer setQuoteChar(char quote) {
/*  944 */     return setQuoteMatcher(StrMatcher.charMatcher(quote));
/*      */   }
/*      */   
/*      */   public StrMatcher getIgnoredMatcher() {
/*  959 */     return this.ignoredMatcher;
/*      */   }
/*      */   
/*      */   public StrTokenizer setIgnoredMatcher(StrMatcher ignored) {
/*  972 */     if (ignored != null)
/*  973 */       this.ignoredMatcher = ignored; 
/*  975 */     return this;
/*      */   }
/*      */   
/*      */   public StrTokenizer setIgnoredChar(char ignored) {
/*  988 */     return setIgnoredMatcher(StrMatcher.charMatcher(ignored));
/*      */   }
/*      */   
/*      */   public StrMatcher getTrimmerMatcher() {
/* 1003 */     return this.trimmerMatcher;
/*      */   }
/*      */   
/*      */   public StrTokenizer setTrimmerMatcher(StrMatcher trimmer) {
/* 1016 */     if (trimmer != null)
/* 1017 */       this.trimmerMatcher = trimmer; 
/* 1019 */     return this;
/*      */   }
/*      */   
/*      */   public boolean isEmptyTokenAsNull() {
/* 1030 */     return this.emptyAsNull;
/*      */   }
/*      */   
/*      */   public StrTokenizer setEmptyTokenAsNull(boolean emptyAsNull) {
/* 1041 */     this.emptyAsNull = emptyAsNull;
/* 1042 */     return this;
/*      */   }
/*      */   
/*      */   public boolean isIgnoreEmptyTokens() {
/* 1053 */     return this.ignoreEmptyTokens;
/*      */   }
/*      */   
/*      */   public StrTokenizer setIgnoreEmptyTokens(boolean ignoreEmptyTokens) {
/* 1064 */     this.ignoreEmptyTokens = ignoreEmptyTokens;
/* 1065 */     return this;
/*      */   }
/*      */   
/*      */   public String getContent() {
/* 1075 */     if (this.chars == null)
/* 1076 */       return null; 
/* 1078 */     return new String(this.chars);
/*      */   }
/*      */   
/*      */   public Object clone() {
/*      */     try {
/* 1091 */       return cloneReset();
/* 1092 */     } catch (CloneNotSupportedException ex) {
/* 1093 */       return null;
/*      */     } 
/*      */   }
/*      */   
/*      */   Object cloneReset() throws CloneNotSupportedException {
/* 1106 */     StrTokenizer cloned = (StrTokenizer)super.clone();
/* 1107 */     if (cloned.chars != null)
/* 1108 */       cloned.chars = (char[])cloned.chars.clone(); 
/* 1110 */     cloned.reset();
/* 1111 */     return cloned;
/*      */   }
/*      */   
/*      */   public String toString() {
/* 1121 */     if (this.tokens == null)
/* 1122 */       return "StrTokenizer[not tokenized yet]"; 
/* 1124 */     return "StrTokenizer" + getTokenList();
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\lang\text\StrTokenizer.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */