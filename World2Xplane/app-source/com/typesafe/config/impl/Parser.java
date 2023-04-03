/*      */ package com.typesafe.config.impl;
/*      */ 
/*      */ import com.typesafe.config.ConfigException;
/*      */ import com.typesafe.config.ConfigIncludeContext;
/*      */ import com.typesafe.config.ConfigOrigin;
/*      */ import com.typesafe.config.ConfigParseOptions;
/*      */ import com.typesafe.config.ConfigSyntax;
/*      */ import com.typesafe.config.ConfigValueType;
/*      */ import java.io.File;
/*      */ import java.io.StringReader;
/*      */ import java.net.MalformedURLException;
/*      */ import java.net.URL;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collections;
/*      */ import java.util.HashMap;
/*      */ import java.util.Iterator;
/*      */ import java.util.LinkedList;
/*      */ import java.util.List;
/*      */ import java.util.ListIterator;
/*      */ import java.util.Map;
/*      */ import java.util.Stack;
/*      */ 
/*      */ final class Parser {
/*      */   static AbstractConfigValue parse(Iterator<Token> tokens, ConfigOrigin origin, ConfigParseOptions options, ConfigIncludeContext includeContext) {
/*   32 */     ParseContext context = new ParseContext(options.getSyntax(), origin, tokens, SimpleIncluder.makeFull(options.getIncluder()), includeContext);
/*   34 */     return context.parse();
/*      */   }
/*      */   
/*      */   private static final class TokenWithComments {
/*      */     final Token token;
/*      */     
/*      */     final List<Token> comments;
/*      */     
/*      */     TokenWithComments(Token token, List<Token> comments) {
/*   42 */       this.token = token;
/*   43 */       this.comments = comments;
/*   45 */       if (Tokens.isComment(token))
/*   46 */         throw new ConfigException.BugOrBroken("tried to annotate a comment with a comment"); 
/*      */     }
/*      */     
/*      */     TokenWithComments(Token token) {
/*   50 */       this(token, Collections.emptyList());
/*      */     }
/*      */     
/*      */     TokenWithComments removeAll() {
/*   54 */       if (this.comments.isEmpty())
/*   55 */         return this; 
/*   57 */       return new TokenWithComments(this.token);
/*      */     }
/*      */     
/*      */     TokenWithComments prepend(List<Token> earlier) {
/*   61 */       if (earlier.isEmpty())
/*   62 */         return this; 
/*   63 */       if (this.comments.isEmpty())
/*   64 */         return new TokenWithComments(this.token, earlier); 
/*   66 */       List<Token> merged = new ArrayList<Token>();
/*   67 */       merged.addAll(earlier);
/*   68 */       merged.addAll(this.comments);
/*   69 */       return new TokenWithComments(this.token, merged);
/*      */     }
/*      */     
/*      */     TokenWithComments add(Token after) {
/*   74 */       if (this.comments.isEmpty())
/*   75 */         return new TokenWithComments(this.token, Collections.singletonList(after)); 
/*   77 */       List<Token> merged = new ArrayList<Token>();
/*   78 */       merged.addAll(this.comments);
/*   79 */       merged.add(after);
/*   80 */       return new TokenWithComments(this.token, merged);
/*      */     }
/*      */     
/*      */     SimpleConfigOrigin prependComments(SimpleConfigOrigin origin) {
/*   85 */       if (this.comments.isEmpty())
/*   86 */         return origin; 
/*   88 */       List<String> newComments = new ArrayList<String>();
/*   89 */       for (Token c : this.comments)
/*   90 */         newComments.add(Tokens.getCommentText(c)); 
/*   92 */       return origin.prependComments(newComments);
/*      */     }
/*      */     
/*      */     SimpleConfigOrigin appendComments(SimpleConfigOrigin origin) {
/*   97 */       if (this.comments.isEmpty())
/*   98 */         return origin; 
/*  100 */       List<String> newComments = new ArrayList<String>();
/*  101 */       for (Token c : this.comments)
/*  102 */         newComments.add(Tokens.getCommentText(c)); 
/*  104 */       return origin.appendComments(newComments);
/*      */     }
/*      */     
/*      */     public String toString() {
/*  112 */       return this.token.toString();
/*      */     }
/*      */   }
/*      */   
/*      */   private static final class ParseContext {
/*      */     private int lineNumber;
/*      */     
/*      */     private final Stack<Parser.TokenWithComments> buffer;
/*      */     
/*      */     private final Iterator<Token> tokens;
/*      */     
/*      */     private final FullIncluder includer;
/*      */     
/*      */     private final ConfigIncludeContext includeContext;
/*      */     
/*      */     private final ConfigSyntax flavor;
/*      */     
/*      */     private final ConfigOrigin baseOrigin;
/*      */     
/*      */     private final LinkedList<Path> pathStack;
/*      */     
/*      */     int equalsCount;
/*      */     
/*      */     int arrayCount;
/*      */     
/*      */     ParseContext(ConfigSyntax flavor, ConfigOrigin origin, Iterator<Token> tokens, FullIncluder includer, ConfigIncludeContext includeContext) {
/*  136 */       this.lineNumber = 1;
/*  137 */       this.buffer = new Stack<Parser.TokenWithComments>();
/*  138 */       this.tokens = tokens;
/*  139 */       this.flavor = flavor;
/*  140 */       this.baseOrigin = origin;
/*  141 */       this.includer = includer;
/*  142 */       this.includeContext = includeContext;
/*  143 */       this.pathStack = new LinkedList<Path>();
/*  144 */       this.equalsCount = 0;
/*  145 */       this.arrayCount = 0;
/*      */     }
/*      */     
/*      */     private static boolean attractsTrailingComments(Token token) {
/*  154 */       if (Tokens.isNewline(token) || token == Tokens.START || token == Tokens.OPEN_CURLY || token == Tokens.OPEN_SQUARE || token == Tokens.END)
/*  156 */         return false; 
/*  158 */       return true;
/*      */     }
/*      */     
/*      */     private static boolean attractsLeadingComments(Token token) {
/*  164 */       if (Tokens.isNewline(token) || token == Tokens.START || token == Tokens.CLOSE_CURLY || token == Tokens.CLOSE_SQUARE || token == Tokens.END)
/*  166 */         return false; 
/*  168 */       return true;
/*      */     }
/*      */     
/*      */     private void consolidateCommentBlock(Token commentToken) {
/*  180 */       List<Token> newlines = new ArrayList<Token>();
/*  181 */       List<Token> comments = new ArrayList<Token>();
/*  183 */       Token previous = null;
/*  184 */       Token next = commentToken;
/*      */       while (true) {
/*  186 */         if (Tokens.isNewline(next)) {
/*  187 */           if (previous != null && Tokens.isNewline(previous))
/*  190 */             comments.clear(); 
/*  192 */           newlines.add(next);
/*  193 */         } else if (Tokens.isComment(next)) {
/*  194 */           comments.add(next);
/*      */         } else {
/*  199 */           if (!attractsLeadingComments(next))
/*  200 */             comments.clear(); 
/*      */           break;
/*      */         } 
/*  205 */         previous = next;
/*  206 */         next = this.tokens.next();
/*      */       } 
/*  211 */       this.buffer.push(new Parser.TokenWithComments(next, comments));
/*  214 */       ListIterator<Token> li = newlines.listIterator(newlines.size());
/*  215 */       while (li.hasPrevious())
/*  216 */         this.buffer.push(new Parser.TokenWithComments(li.previous())); 
/*      */     }
/*      */     
/*      */     private Parser.TokenWithComments popTokenWithoutTrailingComment() {
/*  221 */       if (this.buffer.isEmpty()) {
/*  222 */         Token t = this.tokens.next();
/*  223 */         if (Tokens.isComment(t)) {
/*  224 */           consolidateCommentBlock(t);
/*  225 */           return this.buffer.pop();
/*      */         } 
/*  227 */         return new Parser.TokenWithComments(t);
/*      */       } 
/*  230 */       return this.buffer.pop();
/*      */     }
/*      */     
/*      */     private Parser.TokenWithComments popToken() {
/*  235 */       Parser.TokenWithComments withPrecedingComments = popTokenWithoutTrailingComment();
/*  243 */       if (!attractsTrailingComments(withPrecedingComments.token))
/*  244 */         return withPrecedingComments; 
/*  245 */       if (this.buffer.isEmpty()) {
/*  246 */         Token after = this.tokens.next();
/*  247 */         if (Tokens.isComment(after))
/*  248 */           return withPrecedingComments.add(after); 
/*  250 */         this.buffer.push(new Parser.TokenWithComments(after));
/*  251 */         return withPrecedingComments;
/*      */       } 
/*  256 */       if (Tokens.isComment(((Parser.TokenWithComments)this.buffer.peek()).token))
/*  257 */         throw new ConfigException.BugOrBroken("comment token should not have been in buffer: " + this.buffer); 
/*  259 */       return withPrecedingComments;
/*      */     }
/*      */     
/*      */     private Parser.TokenWithComments nextToken() {
/*  264 */       Parser.TokenWithComments withComments = null;
/*  266 */       withComments = popToken();
/*  267 */       Token t = withComments.token;
/*  269 */       if (Tokens.isProblem(t)) {
/*  270 */         ConfigOrigin origin = t.origin();
/*  271 */         String message = Tokens.getProblemMessage(t);
/*  272 */         Throwable cause = Tokens.getProblemCause(t);
/*  273 */         boolean suggestQuotes = Tokens.getProblemSuggestQuotes(t);
/*  274 */         if (suggestQuotes) {
/*  275 */           message = addQuoteSuggestion(t.toString(), message);
/*      */         } else {
/*  277 */           message = addKeyName(message);
/*      */         } 
/*  279 */         throw new ConfigException.Parse(origin, message, cause);
/*      */       } 
/*  281 */       if (this.flavor == ConfigSyntax.JSON) {
/*  282 */         if (Tokens.isUnquotedText(t))
/*  283 */           throw parseError(addKeyName("Token not allowed in valid JSON: '" + Tokens.getUnquotedText(t) + "'")); 
/*  285 */         if (Tokens.isSubstitution(t))
/*  286 */           throw parseError(addKeyName("Substitutions (${} syntax) not allowed in JSON")); 
/*      */       } 
/*  290 */       return withComments;
/*      */     }
/*      */     
/*      */     private void putBack(Parser.TokenWithComments token) {
/*  295 */       if (Tokens.isComment(token.token))
/*  296 */         throw new ConfigException.BugOrBroken("comment token should have been stripped before it was available to put back"); 
/*  298 */       this.buffer.push(token);
/*      */     }
/*      */     
/*      */     private Parser.TokenWithComments nextTokenIgnoringNewline() {
/*  302 */       Parser.TokenWithComments t = nextToken();
/*  304 */       while (Tokens.isNewline(t.token)) {
/*  309 */         this.lineNumber = t.token.lineNumber() + 1;
/*  311 */         t = nextToken();
/*      */       } 
/*  315 */       int newNumber = t.token.lineNumber();
/*  316 */       if (newNumber >= 0)
/*  317 */         this.lineNumber = newNumber; 
/*  319 */       return t;
/*      */     }
/*      */     
/*      */     private AbstractConfigValue addAnyCommentsAfterAnyComma(AbstractConfigValue v) {
/*  323 */       Parser.TokenWithComments t = nextToken();
/*  325 */       if (t.token == Tokens.COMMA) {
/*  327 */         putBack(t.removeAll());
/*  328 */         return v.withOrigin(t.appendComments(v.origin()));
/*      */       } 
/*  330 */       putBack(t);
/*  331 */       return v;
/*      */     }
/*      */     
/*      */     private boolean checkElementSeparator() {
/*  342 */       if (this.flavor == ConfigSyntax.JSON) {
/*  343 */         Parser.TokenWithComments tokenWithComments = nextTokenIgnoringNewline();
/*  344 */         if (tokenWithComments.token == Tokens.COMMA)
/*  345 */           return true; 
/*  347 */         putBack(tokenWithComments);
/*  348 */         return false;
/*      */       } 
/*  351 */       boolean sawSeparatorOrNewline = false;
/*  352 */       Parser.TokenWithComments t = nextToken();
/*      */       while (true) {
/*  354 */         if (Tokens.isNewline(t.token)) {
/*  356 */           this.lineNumber = t.token.lineNumber() + 1;
/*  357 */           sawSeparatorOrNewline = true;
/*      */         } else {
/*  361 */           if (t.token == Tokens.COMMA)
/*  362 */             return true; 
/*  365 */           putBack(t);
/*  366 */           return sawSeparatorOrNewline;
/*      */         } 
/*  368 */         t = nextToken();
/*      */       } 
/*      */     }
/*      */     
/*      */     private static SubstitutionExpression tokenToSubstitutionExpression(Token valueToken) {
/*  374 */       List<Token> expression = Tokens.getSubstitutionPathExpression(valueToken);
/*  375 */       Path path = Parser.parsePathExpression(expression.iterator(), valueToken.origin());
/*  376 */       boolean optional = Tokens.getSubstitutionOptional(valueToken);
/*  378 */       return new SubstitutionExpression(path, optional);
/*      */     }
/*      */     
/*      */     private void consolidateValueTokens() {
/*  386 */       if (this.flavor == ConfigSyntax.JSON)
/*      */         return; 
/*  390 */       List<AbstractConfigValue> values = null;
/*  393 */       Parser.TokenWithComments t = nextTokenIgnoringNewline();
/*      */       while (true) {
/*  395 */         AbstractConfigValue v = null;
/*  396 */         if (Tokens.isValue(t.token) || Tokens.isUnquotedText(t.token) || Tokens.isSubstitution(t.token) || t.token == Tokens.OPEN_CURLY || t.token == Tokens.OPEN_SQUARE) {
/*  400 */           v = parseValue(t);
/*  405 */           if (v == null)
/*  406 */             throw new ConfigException.BugOrBroken("no value"); 
/*  408 */           if (values == null)
/*  409 */             values = new ArrayList<AbstractConfigValue>(); 
/*  411 */           values.add(v);
/*  413 */           t = nextToken();
/*      */           continue;
/*      */         } 
/*      */         break;
/*      */       } 
/*  416 */       putBack(t);
/*  418 */       if (values == null)
/*      */         return; 
/*  421 */       AbstractConfigValue consolidated = ConfigConcatenation.concatenate(values);
/*  423 */       putBack(new Parser.TokenWithComments(Tokens.newValue(consolidated)));
/*      */     }
/*      */     
/*      */     private SimpleConfigOrigin lineOrigin() {
/*  427 */       return ((SimpleConfigOrigin)this.baseOrigin).setLineNumber(this.lineNumber);
/*      */     }
/*      */     
/*      */     private ConfigException parseError(String message) {
/*  431 */       return parseError(message, null);
/*      */     }
/*      */     
/*      */     private ConfigException parseError(String message, Throwable cause) {
/*  435 */       return (ConfigException)new ConfigException.Parse(lineOrigin(), message, cause);
/*      */     }
/*      */     
/*      */     private String previousFieldName(Path lastPath) {
/*  439 */       if (lastPath != null)
/*  440 */         return lastPath.render(); 
/*  441 */       if (this.pathStack.isEmpty())
/*  442 */         return null; 
/*  444 */       return ((Path)this.pathStack.peek()).render();
/*      */     }
/*      */     
/*      */     private Path fullCurrentPath() {
/*  449 */       if (this.pathStack.isEmpty())
/*  450 */         throw new ConfigException.BugOrBroken("Bug in parser; tried to get current path when at root"); 
/*  452 */       return new Path(this.pathStack.descendingIterator());
/*      */     }
/*      */     
/*      */     private String previousFieldName() {
/*  456 */       return previousFieldName(null);
/*      */     }
/*      */     
/*      */     private String addKeyName(String message) {
/*  460 */       String previousFieldName = previousFieldName();
/*  461 */       if (previousFieldName != null)
/*  462 */         return "in value for key '" + previousFieldName + "': " + message; 
/*  464 */       return message;
/*      */     }
/*      */     
/*      */     private String addQuoteSuggestion(String badToken, String message) {
/*  469 */       return addQuoteSuggestion(null, (this.equalsCount > 0), badToken, message);
/*      */     }
/*      */     
/*      */     private String addQuoteSuggestion(Path lastPath, boolean insideEquals, String badToken, String message) {
/*  474 */       String part, previousFieldName = previousFieldName(lastPath);
/*  477 */       if (badToken.equals(Tokens.END.toString())) {
/*  479 */         if (previousFieldName != null) {
/*  480 */           part = message + " (if you intended '" + previousFieldName + "' to be part of a value, instead of a key, " + "try adding double quotes around the whole value";
/*      */         } else {
/*  484 */           return message;
/*      */         } 
/*  486 */       } else if (previousFieldName != null) {
/*  487 */         part = message + " (if you intended " + badToken + " to be part of the value for '" + previousFieldName + "', " + "try enclosing the value in double quotes";
/*      */       } else {
/*  491 */         part = message + " (if you intended " + badToken + " to be part of a key or string value, " + "try enclosing the key or value in double quotes";
/*      */       } 
/*  497 */       if (insideEquals)
/*  498 */         return part + ", or you may be able to rename the file .properties rather than .conf)"; 
/*  501 */       return part + ")";
/*      */     }
/*      */     
/*      */     private AbstractConfigValue parseValue(Parser.TokenWithComments t) {
/*  507 */       int startingArrayCount = this.arrayCount;
/*  508 */       int startingEqualsCount = this.equalsCount;
/*  510 */       if (Tokens.isValue(t.token)) {
/*  514 */         v = Tokens.getValue(t.token);
/*  515 */       } else if (Tokens.isUnquotedText(t.token)) {
/*  516 */         v = new ConfigString(t.token.origin(), Tokens.getUnquotedText(t.token));
/*  517 */       } else if (Tokens.isSubstitution(t.token)) {
/*  518 */         v = new ConfigReference(t.token.origin(), tokenToSubstitutionExpression(t.token));
/*  519 */       } else if (t.token == Tokens.OPEN_CURLY) {
/*  520 */         v = parseObject(true);
/*  521 */       } else if (t.token == Tokens.OPEN_SQUARE) {
/*  522 */         v = parseArray();
/*      */       } else {
/*  524 */         throw parseError(addQuoteSuggestion(t.token.toString(), "Expecting a value but got wrong token: " + t.token));
/*      */       } 
/*  528 */       AbstractConfigValue v = v.withOrigin(t.prependComments(v.origin()));
/*  530 */       if (this.arrayCount != startingArrayCount)
/*  531 */         throw new ConfigException.BugOrBroken("Bug in config parser: unbalanced array count"); 
/*  532 */       if (this.equalsCount != startingEqualsCount)
/*  533 */         throw new ConfigException.BugOrBroken("Bug in config parser: unbalanced equals count"); 
/*  535 */       return v;
/*      */     }
/*      */     
/*      */     private static AbstractConfigObject createValueUnderPath(Path path, AbstractConfigValue value) {
/*  542 */       List<String> keys = new ArrayList<String>();
/*  544 */       String key = path.first();
/*  545 */       Path remaining = path.remainder();
/*  546 */       while (key != null) {
/*  547 */         keys.add(key);
/*  548 */         if (remaining == null)
/*      */           break; 
/*  551 */         key = remaining.first();
/*  552 */         remaining = remaining.remainder();
/*      */       } 
/*  560 */       ListIterator<String> i = keys.listIterator(keys.size());
/*  561 */       String deepest = i.previous();
/*  562 */       AbstractConfigObject o = new SimpleConfigObject(value.origin().setComments(null), Collections.singletonMap(deepest, value));
/*  565 */       while (i.hasPrevious()) {
/*  566 */         Map<String, AbstractConfigValue> m = Collections.singletonMap(i.previous(), o);
/*  568 */         o = new SimpleConfigObject(value.origin().setComments(null), m);
/*      */       } 
/*  571 */       return o;
/*      */     }
/*      */     
/*      */     private Path parseKey(Parser.TokenWithComments token) {
/*  575 */       if (this.flavor == ConfigSyntax.JSON) {
/*  576 */         if (Tokens.isValueWithType(token.token, ConfigValueType.STRING)) {
/*  577 */           String key = (String)Tokens.getValue(token.token).unwrapped();
/*  578 */           return Path.newKey(key);
/*      */         } 
/*  580 */         throw parseError(addKeyName("Expecting close brace } or a field name here, got " + token));
/*      */       } 
/*  584 */       List<Token> expression = new ArrayList<Token>();
/*  585 */       Parser.TokenWithComments t = token;
/*  586 */       while (Tokens.isValue(t.token) || Tokens.isUnquotedText(t.token)) {
/*  587 */         expression.add(t.token);
/*  588 */         t = nextToken();
/*      */       } 
/*  591 */       if (expression.isEmpty())
/*  592 */         throw parseError(addKeyName("expecting a close brace or a field name here, got " + t)); 
/*  596 */       putBack(t);
/*  597 */       return Parser.parsePathExpression(expression.iterator(), lineOrigin());
/*      */     }
/*      */     
/*      */     private static boolean isIncludeKeyword(Token t) {
/*  602 */       return (Tokens.isUnquotedText(t) && Tokens.getUnquotedText(t).equals("include"));
/*      */     }
/*      */     
/*      */     private static boolean isUnquotedWhitespace(Token t) {
/*  607 */       if (!Tokens.isUnquotedText(t))
/*  608 */         return false; 
/*  610 */       String s = Tokens.getUnquotedText(t);
/*  612 */       for (int i = 0; i < s.length(); i++) {
/*  613 */         char c = s.charAt(i);
/*  614 */         if (!ConfigImplUtil.isWhitespace(c))
/*  615 */           return false; 
/*      */       } 
/*  617 */       return true;
/*      */     }
/*      */     
/*      */     private void parseInclude(Map<String, AbstractConfigValue> values) {
/*      */       AbstractConfigObject obj;
/*  621 */       Parser.TokenWithComments t = nextTokenIgnoringNewline();
/*  622 */       while (isUnquotedWhitespace(t.token))
/*  623 */         t = nextTokenIgnoringNewline(); 
/*  629 */       if (Tokens.isUnquotedText(t.token)) {
/*  631 */         String name, kind = Tokens.getUnquotedText(t.token);
/*  633 */         if (!kind.equals("url("))
/*  635 */           if (!kind.equals("file("))
/*  637 */             if (!kind.equals("classpath("))
/*  640 */               throw parseError("expecting include parameter to be quoted filename, file(), classpath(), or url(). No spaces are allowed before the open paren. Not expecting: " + t);   
/*  645 */         t = nextTokenIgnoringNewline();
/*  646 */         while (isUnquotedWhitespace(t.token))
/*  647 */           t = nextTokenIgnoringNewline(); 
/*  652 */         if (Tokens.isValueWithType(t.token, ConfigValueType.STRING)) {
/*  653 */           name = (String)Tokens.getValue(t.token).unwrapped();
/*      */         } else {
/*  655 */           throw parseError("expecting a quoted string inside file(), classpath(), or url(), rather than: " + t);
/*      */         } 
/*  659 */         t = nextTokenIgnoringNewline();
/*  660 */         while (isUnquotedWhitespace(t.token))
/*  661 */           t = nextTokenIgnoringNewline(); 
/*  664 */         if (Tokens.isUnquotedText(t.token) && Tokens.getUnquotedText(t.token).equals(")")) {
/*  670 */           if (kind.equals("url(")) {
/*      */             URL url;
/*      */             try {
/*  673 */               url = new URL(name);
/*  674 */             } catch (MalformedURLException e) {
/*  675 */               throw parseError("include url() specifies an invalid URL: " + name, e);
/*      */             } 
/*  677 */             obj = (AbstractConfigObject)this.includer.includeURL(this.includeContext, url);
/*  678 */           } else if (kind.equals("file(")) {
/*  679 */             obj = (AbstractConfigObject)this.includer.includeFile(this.includeContext, new File(name));
/*  681 */           } else if (kind.equals("classpath(")) {
/*  682 */             obj = (AbstractConfigObject)this.includer.includeResources(this.includeContext, name);
/*      */           } else {
/*  684 */             throw new ConfigException.BugOrBroken("should not be reached");
/*      */           } 
/*      */         } else {
/*      */           throw parseError("expecting a close parentheses ')' here, not: " + t);
/*      */         } 
/*  686 */       } else if (Tokens.isValueWithType(t.token, ConfigValueType.STRING)) {
/*  687 */         String name = (String)Tokens.getValue(t.token).unwrapped();
/*  688 */         obj = (AbstractConfigObject)this.includer.include(this.includeContext, name);
/*      */       } else {
/*  691 */         throw parseError("include keyword is not followed by a quoted string, but by: " + t);
/*      */       } 
/*  697 */       if (this.arrayCount > 0 && obj.resolveStatus() != ResolveStatus.RESOLVED)
/*  698 */         throw parseError("Due to current limitations of the config parser, when an include statement is nested inside a list value, ${} substitutions inside the included file cannot be resolved correctly. Either move the include outside of the list value or remove the ${} statements from the included file."); 
/*  702 */       if (!this.pathStack.isEmpty()) {
/*  703 */         Path prefix = fullCurrentPath();
/*  704 */         obj = obj.relativized(prefix);
/*      */       } 
/*  707 */       for (String key : obj.keySet()) {
/*  708 */         AbstractConfigValue v = obj.get(key);
/*  709 */         AbstractConfigValue existing = values.get(key);
/*  710 */         if (existing != null) {
/*  711 */           values.put(key, v.withFallback(existing));
/*      */           continue;
/*      */         } 
/*  713 */         values.put(key, v);
/*      */       } 
/*      */     }
/*      */     
/*      */     private boolean isKeyValueSeparatorToken(Token t) {
/*  719 */       if (this.flavor == ConfigSyntax.JSON)
/*  720 */         return (t == Tokens.COLON); 
/*  722 */       return (t == Tokens.COLON || t == Tokens.EQUALS || t == Tokens.PLUS_EQUALS);
/*      */     }
/*      */     
/*      */     private AbstractConfigObject parseObject(boolean hadOpenCurly) {
/*  728 */       Map<String, AbstractConfigValue> values = new HashMap<String, AbstractConfigValue>();
/*  729 */       SimpleConfigOrigin objectOrigin = lineOrigin();
/*  730 */       boolean afterComma = false;
/*  731 */       Path lastPath = null;
/*  732 */       boolean lastInsideEquals = false;
/*      */       while (true) {
/*  735 */         Parser.TokenWithComments t = nextTokenIgnoringNewline();
/*  736 */         if (t.token == Tokens.CLOSE_CURLY) {
/*  737 */           if (this.flavor == ConfigSyntax.JSON && afterComma)
/*  738 */             throw parseError(addQuoteSuggestion(t.toString(), "expecting a field name after a comma, got a close brace } instead")); 
/*  740 */           if (!hadOpenCurly)
/*  741 */             throw parseError(addQuoteSuggestion(t.toString(), "unbalanced close brace '}' with no open brace")); 
/*  745 */           objectOrigin = t.appendComments(objectOrigin);
/*      */           break;
/*      */         } 
/*  748 */         if (t.token == Tokens.END && !hadOpenCurly) {
/*  749 */           putBack(t);
/*      */           break;
/*      */         } 
/*  751 */         if (this.flavor != ConfigSyntax.JSON && isIncludeKeyword(t.token)) {
/*  752 */           parseInclude(values);
/*  754 */           afterComma = false;
/*      */         } else {
/*  756 */           Parser.TokenWithComments valueToken, keyToken = t;
/*  757 */           Path path = parseKey(keyToken);
/*  758 */           Parser.TokenWithComments afterKey = nextTokenIgnoringNewline();
/*  759 */           boolean insideEquals = false;
/*  762 */           this.pathStack.push(path);
/*  763 */           if (afterKey.token == Tokens.PLUS_EQUALS) {
/*  768 */             if (this.arrayCount > 0)
/*  769 */               throw parseError("Due to current limitations of the config parser, += does not work nested inside a list. += expands to a ${} substitution and the path in ${} cannot currently refer to list elements. You might be able to move the += outside of the list and then refer to it from inside the list with ${}."); 
/*  776 */             this.arrayCount++;
/*      */           } 
/*  781 */           if (this.flavor == ConfigSyntax.CONF && afterKey.token == Tokens.OPEN_CURLY) {
/*  783 */             valueToken = afterKey;
/*      */           } else {
/*  785 */             if (!isKeyValueSeparatorToken(afterKey.token))
/*  786 */               throw parseError(addQuoteSuggestion(afterKey.toString(), "Key '" + path.render() + "' may not be followed by token: " + afterKey)); 
/*  791 */             if (afterKey.token == Tokens.EQUALS) {
/*  792 */               insideEquals = true;
/*  793 */               this.equalsCount++;
/*      */             } 
/*  796 */             consolidateValueTokens();
/*  797 */             valueToken = nextTokenIgnoringNewline();
/*  799 */             valueToken = valueToken.prepend(afterKey.comments);
/*      */           } 
/*  803 */           AbstractConfigValue newValue = parseValue(valueToken.prepend(keyToken.comments));
/*  805 */           if (afterKey.token == Tokens.PLUS_EQUALS) {
/*  806 */             this.arrayCount--;
/*  808 */             List<AbstractConfigValue> concat = new ArrayList<AbstractConfigValue>(2);
/*  809 */             AbstractConfigValue previousRef = new ConfigReference(newValue.origin(), new SubstitutionExpression(fullCurrentPath(), true));
/*  811 */             AbstractConfigValue list = new SimpleConfigList(newValue.origin(), Collections.singletonList(newValue));
/*  813 */             concat.add(previousRef);
/*  814 */             concat.add(list);
/*  815 */             newValue = ConfigConcatenation.concatenate(concat);
/*      */           } 
/*  818 */           newValue = addAnyCommentsAfterAnyComma(newValue);
/*  820 */           lastPath = this.pathStack.pop();
/*  821 */           if (insideEquals)
/*  822 */             this.equalsCount--; 
/*  824 */           lastInsideEquals = insideEquals;
/*  826 */           String key = path.first();
/*  827 */           Path remaining = path.remainder();
/*  829 */           if (remaining == null) {
/*  830 */             AbstractConfigValue existing = values.get(key);
/*  831 */             if (existing != null) {
/*  837 */               if (this.flavor == ConfigSyntax.JSON)
/*  838 */                 throw parseError("JSON does not allow duplicate fields: '" + key + "' was already seen at " + existing.origin().description()); 
/*  843 */               newValue = newValue.withFallback(existing);
/*      */             } 
/*  846 */             values.put(key, newValue);
/*      */           } else {
/*  848 */             if (this.flavor == ConfigSyntax.JSON)
/*  849 */               throw new ConfigException.BugOrBroken("somehow got multi-element path in JSON mode"); 
/*  853 */             AbstractConfigObject obj = createValueUnderPath(remaining, newValue);
/*  855 */             AbstractConfigValue existing = values.get(key);
/*  856 */             if (existing != null)
/*  857 */               obj = obj.withFallback(existing); 
/*  859 */             values.put(key, obj);
/*      */           } 
/*  862 */           afterComma = false;
/*      */         } 
/*  865 */         if (checkElementSeparator()) {
/*  867 */           afterComma = true;
/*      */           continue;
/*      */         } 
/*  869 */         t = nextTokenIgnoringNewline();
/*  870 */         if (t.token == Tokens.CLOSE_CURLY) {
/*  871 */           if (!hadOpenCurly)
/*  872 */             throw parseError(addQuoteSuggestion(lastPath, lastInsideEquals, t.toString(), "unbalanced close brace '}' with no open brace")); 
/*  876 */           objectOrigin = t.appendComments(objectOrigin);
/*      */           break;
/*      */         } 
/*  879 */         if (hadOpenCurly)
/*  880 */           throw parseError(addQuoteSuggestion(lastPath, lastInsideEquals, t.toString(), "Expecting close brace } or a comma, got " + t)); 
/*  883 */         if (t.token == Tokens.END) {
/*  884 */           putBack(t);
/*      */           break;
/*      */         } 
/*  887 */         throw parseError(addQuoteSuggestion(lastPath, lastInsideEquals, t.toString(), "Expecting end of input or a comma, got " + t));
/*      */       } 
/*  894 */       return new SimpleConfigObject(objectOrigin, values);
/*      */     }
/*      */     
/*      */     private SimpleConfigList parseArray() {
/*  899 */       this.arrayCount++;
/*  901 */       SimpleConfigOrigin arrayOrigin = lineOrigin();
/*  902 */       List<AbstractConfigValue> values = new ArrayList<AbstractConfigValue>();
/*  904 */       consolidateValueTokens();
/*  906 */       Parser.TokenWithComments t = nextTokenIgnoringNewline();
/*  909 */       if (t.token == Tokens.CLOSE_SQUARE) {
/*  910 */         this.arrayCount--;
/*  911 */         return new SimpleConfigList(t.appendComments(arrayOrigin), Collections.emptyList());
/*      */       } 
/*  913 */       if (Tokens.isValue(t.token) || t.token == Tokens.OPEN_CURLY || t.token == Tokens.OPEN_SQUARE) {
/*  915 */         AbstractConfigValue v = parseValue(t);
/*  916 */         v = addAnyCommentsAfterAnyComma(v);
/*  917 */         values.add(v);
/*      */       } else {
/*  919 */         throw parseError(addKeyName("List should have ] or a first element after the open [, instead had token: " + t + " (if you want " + t + " to be part of a string value, then double-quote it)"));
/*      */       } 
/*      */       while (true) {
/*  929 */         if (checkElementSeparator()) {
/*  946 */           consolidateValueTokens();
/*  948 */           t = nextTokenIgnoringNewline();
/*  949 */           if (Tokens.isValue(t.token) || t.token == Tokens.OPEN_CURLY || t.token == Tokens.OPEN_SQUARE) {
/*  951 */             AbstractConfigValue v = parseValue(t);
/*  952 */             v = addAnyCommentsAfterAnyComma(v);
/*  953 */             values.add(v);
/*      */             continue;
/*      */           } 
/*  954 */           if (this.flavor != ConfigSyntax.JSON && t.token == Tokens.CLOSE_SQUARE) {
/*  956 */             putBack(t);
/*      */             continue;
/*      */           } 
/*      */           break;
/*      */         } 
/*      */         t = nextTokenIgnoringNewline();
/*      */         if (t.token == Tokens.CLOSE_SQUARE) {
/*      */           this.arrayCount--;
/*      */           return new SimpleConfigList(t.appendComments(arrayOrigin), values);
/*      */         } 
/*      */         throw parseError(addKeyName("List should have ended with ] or had a comma, instead had token: " + t + " (if you want " + t + " to be part of a string value, then double-quote it)"));
/*      */       } 
/*  958 */       throw parseError(addKeyName("List should have had new element after a comma, instead had token: " + t + " (if you want the comma or " + t + " to be part of a string value, then double-quote it)"));
/*      */     }
/*      */     
/*      */     AbstractConfigValue parse() {
/*  968 */       Parser.TokenWithComments t = nextTokenIgnoringNewline();
/*  969 */       if (t.token == Tokens.START) {
/*  976 */         t = nextTokenIgnoringNewline();
/*  977 */         AbstractConfigValue result = null;
/*  978 */         if (t.token == Tokens.OPEN_CURLY || t.token == Tokens.OPEN_SQUARE) {
/*  979 */           result = parseValue(t);
/*      */         } else {
/*  981 */           if (this.flavor == ConfigSyntax.JSON) {
/*  982 */             if (t.token == Tokens.END)
/*  983 */               throw parseError("Empty document"); 
/*  985 */             throw parseError("Document must have an object or array at root, unexpected token: " + t);
/*      */           } 
/*  992 */           putBack(t);
/*  993 */           result = parseObject(false);
/*      */         } 
/* 1000 */         t = nextTokenIgnoringNewline();
/* 1001 */         if (t.token == Tokens.END)
/* 1002 */           return result; 
/* 1004 */         throw parseError("Document has trailing tokens after first object or array: " + t);
/*      */       } 
/*      */       throw new ConfigException.BugOrBroken("token stream did not begin with START, had " + t);
/*      */     }
/*      */   }
/*      */   
/*      */   static class Element {
/*      */     StringBuilder sb;
/*      */     
/*      */     boolean canBeEmpty;
/*      */     
/*      */     Element(String initial, boolean canBeEmpty) {
/* 1016 */       this.canBeEmpty = canBeEmpty;
/* 1017 */       this.sb = new StringBuilder(initial);
/*      */     }
/*      */     
/*      */     public String toString() {
/* 1022 */       return "Element(" + this.sb.toString() + "," + this.canBeEmpty + ")";
/*      */     }
/*      */   }
/*      */   
/*      */   private static void addPathText(List<Element> buf, boolean wasQuoted, String newText) {
/* 1028 */     int i = wasQuoted ? -1 : newText.indexOf('.');
/* 1029 */     Element current = buf.get(buf.size() - 1);
/* 1030 */     if (i < 0) {
/* 1032 */       current.sb.append(newText);
/* 1035 */       if (wasQuoted && current.sb.length() == 0)
/* 1036 */         current.canBeEmpty = true; 
/*      */     } else {
/* 1039 */       current.sb.append(newText.substring(0, i));
/* 1041 */       buf.add(new Element("", false));
/* 1043 */       addPathText(buf, false, newText.substring(i + 1));
/*      */     } 
/*      */   }
/*      */   
/*      */   private static Path parsePathExpression(Iterator<Token> expression, ConfigOrigin origin) {
/* 1049 */     return parsePathExpression(expression, origin, null);
/*      */   }
/*      */   
/*      */   private static Path parsePathExpression(Iterator<Token> expression, ConfigOrigin origin, String originalText) {
/* 1056 */     List<Element> buf = new ArrayList<Element>();
/* 1057 */     buf.add(new Element("", false));
/* 1059 */     if (!expression.hasNext())
/* 1060 */       throw new ConfigException.BadPath(origin, originalText, "Expecting a field name or path here, but got nothing"); 
/* 1064 */     while (expression.hasNext()) {
/*      */       String text;
/* 1065 */       Token t = expression.next();
/* 1066 */       if (Tokens.isValueWithType(t, ConfigValueType.STRING)) {
/* 1067 */         AbstractConfigValue v = Tokens.getValue(t);
/* 1070 */         String s = v.transformToString();
/* 1072 */         addPathText(buf, true, s);
/*      */         continue;
/*      */       } 
/* 1073 */       if (t == Tokens.END)
/*      */         continue; 
/* 1082 */       if (Tokens.isValue(t)) {
/* 1090 */         AbstractConfigValue v = Tokens.getValue(t);
/* 1091 */         text = v.transformToString();
/* 1092 */       } else if (Tokens.isUnquotedText(t)) {
/* 1093 */         text = Tokens.getUnquotedText(t);
/*      */       } else {
/* 1095 */         throw new ConfigException.BadPath(origin, originalText, "Token not allowed in path expression: " + t + " (you can double-quote this token if you really want it here)");
/*      */       } 
/* 1103 */       addPathText(buf, false, text);
/*      */     } 
/* 1107 */     PathBuilder pb = new PathBuilder();
/* 1108 */     for (Element e : buf) {
/* 1109 */       if (e.sb.length() == 0 && !e.canBeEmpty)
/* 1110 */         throw new ConfigException.BadPath(origin, originalText, "path has a leading, trailing, or two adjacent period '.' (use quoted \"\" empty string if you want an empty element)"); 
/* 1115 */       pb.appendKey(e.sb.toString());
/*      */     } 
/* 1119 */     return pb.result();
/*      */   }
/*      */   
/* 1122 */   static ConfigOrigin apiOrigin = SimpleConfigOrigin.newSimple("path parameter");
/*      */   
/*      */   static Path parsePath(String path) {
/* 1125 */     Path speculated = speculativeFastParsePath(path);
/* 1126 */     if (speculated != null)
/* 1127 */       return speculated; 
/* 1129 */     StringReader reader = new StringReader(path);
/*      */     try {
/* 1132 */       Iterator<Token> tokens = Tokenizer.tokenize(apiOrigin, reader, ConfigSyntax.CONF);
/* 1134 */       tokens.next();
/* 1135 */       return parsePathExpression(tokens, apiOrigin, path);
/*      */     } finally {
/* 1137 */       reader.close();
/*      */     } 
/*      */   }
/*      */   
/*      */   private static boolean hasUnsafeChars(String s) {
/* 1144 */     for (int i = 0; i < s.length(); ) {
/* 1145 */       char c = s.charAt(i);
/* 1146 */       if (Character.isLetter(c) || c == '.') {
/*      */         i++;
/*      */         continue;
/*      */       } 
/* 1149 */       return true;
/*      */     } 
/* 1151 */     return false;
/*      */   }
/*      */   
/*      */   private static void appendPathString(PathBuilder pb, String s) {
/* 1155 */     int splitAt = s.indexOf('.');
/* 1156 */     if (splitAt < 0) {
/* 1157 */       pb.appendKey(s);
/*      */     } else {
/* 1159 */       pb.appendKey(s.substring(0, splitAt));
/* 1160 */       appendPathString(pb, s.substring(splitAt + 1));
/*      */     } 
/*      */   }
/*      */   
/*      */   private static Path speculativeFastParsePath(String path) {
/* 1167 */     String s = ConfigImplUtil.unicodeTrim(path);
/* 1168 */     if (s.isEmpty())
/* 1169 */       return null; 
/* 1170 */     if (hasUnsafeChars(s))
/* 1171 */       return null; 
/* 1172 */     if (s.startsWith(".") || s.endsWith(".") || s.contains(".."))
/* 1173 */       return null; 
/* 1175 */     PathBuilder pb = new PathBuilder();
/* 1176 */     appendPathString(pb, s);
/* 1177 */     return pb.result();
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\typesafe\config\impl\Parser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */