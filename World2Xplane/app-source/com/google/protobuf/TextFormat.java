/*      */ package com.google.protobuf;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.math.BigInteger;
/*      */ import java.nio.CharBuffer;
/*      */ import java.util.ArrayList;
/*      */ import java.util.List;
/*      */ import java.util.Locale;
/*      */ import java.util.Map;
/*      */ import java.util.regex.Matcher;
/*      */ import java.util.regex.Pattern;
/*      */ 
/*      */ public final class TextFormat {
/*   58 */   private static final Printer DEFAULT_PRINTER = new Printer(false);
/*      */   
/*   59 */   private static final Printer SINGLE_LINE_PRINTER = new Printer(true);
/*      */   
/*      */   private static final int BUFFER_SIZE = 4096;
/*      */   
/*      */   public static void print(Message message, Appendable output) throws IOException {
/*   68 */     DEFAULT_PRINTER.print(message, new TextGenerator(output));
/*      */   }
/*      */   
/*      */   public static void print(UnknownFieldSet fields, Appendable output) throws IOException {
/*   75 */     DEFAULT_PRINTER.printUnknownFields(fields, new TextGenerator(output));
/*      */   }
/*      */   
/*      */   public static String shortDebugString(Message message) {
/*      */     try {
/*   84 */       StringBuilder sb = new StringBuilder();
/*   85 */       SINGLE_LINE_PRINTER.print(message, new TextGenerator(sb));
/*   87 */       return sb.toString().trim();
/*   88 */     } catch (IOException e) {
/*   89 */       throw new IllegalStateException(e);
/*      */     } 
/*      */   }
/*      */   
/*      */   public static String shortDebugString(UnknownFieldSet fields) {
/*      */     try {
/*   99 */       StringBuilder sb = new StringBuilder();
/*  100 */       SINGLE_LINE_PRINTER.printUnknownFields(fields, new TextGenerator(sb));
/*  102 */       return sb.toString().trim();
/*  103 */     } catch (IOException e) {
/*  104 */       throw new IllegalStateException(e);
/*      */     } 
/*      */   }
/*      */   
/*      */   public static String printToString(Message message) {
/*      */     try {
/*  114 */       StringBuilder text = new StringBuilder();
/*  115 */       print(message, text);
/*  116 */       return text.toString();
/*  117 */     } catch (IOException e) {
/*  118 */       throw new IllegalStateException(e);
/*      */     } 
/*      */   }
/*      */   
/*      */   public static String printToString(UnknownFieldSet fields) {
/*      */     try {
/*  128 */       StringBuilder text = new StringBuilder();
/*  129 */       print(fields, text);
/*  130 */       return text.toString();
/*  131 */     } catch (IOException e) {
/*  132 */       throw new IllegalStateException(e);
/*      */     } 
/*      */   }
/*      */   
/*      */   public static void printField(Descriptors.FieldDescriptor field, Object value, Appendable output) throws IOException {
/*  140 */     DEFAULT_PRINTER.printField(field, value, new TextGenerator(output));
/*      */   }
/*      */   
/*      */   public static String printFieldToString(Descriptors.FieldDescriptor field, Object value) {
/*      */     try {
/*  146 */       StringBuilder text = new StringBuilder();
/*  147 */       printField(field, value, text);
/*  148 */       return text.toString();
/*  149 */     } catch (IOException e) {
/*  150 */       throw new IllegalStateException(e);
/*      */     } 
/*      */   }
/*      */   
/*      */   public static void printFieldValue(Descriptors.FieldDescriptor field, Object value, Appendable output) throws IOException {
/*  168 */     DEFAULT_PRINTER.printFieldValue(field, value, new TextGenerator(output));
/*      */   }
/*      */   
/*      */   public static void printUnknownFieldValue(int tag, Object value, Appendable output) throws IOException {
/*  185 */     printUnknownFieldValue(tag, value, new TextGenerator(output));
/*      */   }
/*      */   
/*      */   private static void printUnknownFieldValue(int tag, Object value, TextGenerator generator) throws IOException {
/*  192 */     switch (WireFormat.getTagWireType(tag)) {
/*      */       case 0:
/*  194 */         generator.print(unsignedToString(((Long)value).longValue()));
/*      */         return;
/*      */       case 5:
/*  197 */         generator.print(String.format((Locale)null, "0x%08x", new Object[] { value }));
/*      */         return;
/*      */       case 1:
/*  201 */         generator.print(String.format((Locale)null, "0x%016x", new Object[] { value }));
/*      */         return;
/*      */       case 2:
/*  204 */         generator.print("\"");
/*  205 */         generator.print(escapeBytes((ByteString)value));
/*  206 */         generator.print("\"");
/*      */         return;
/*      */       case 3:
/*  209 */         DEFAULT_PRINTER.printUnknownFields((UnknownFieldSet)value, generator);
/*      */         return;
/*      */     } 
/*  212 */     throw new IllegalArgumentException("Bad tag: " + tag);
/*      */   }
/*      */   
/*      */   private static final class Printer {
/*      */     final boolean singleLineMode;
/*      */     
/*      */     private Printer(boolean singleLineMode) {
/*  222 */       this.singleLineMode = singleLineMode;
/*      */     }
/*      */     
/*      */     private void print(Message message, TextFormat.TextGenerator generator) throws IOException {
/*  228 */       for (Map.Entry<Descriptors.FieldDescriptor, Object> field : message.getAllFields().entrySet())
/*  229 */         printField(field.getKey(), field.getValue(), generator); 
/*  231 */       printUnknownFields(message.getUnknownFields(), generator);
/*      */     }
/*      */     
/*      */     private void printField(Descriptors.FieldDescriptor field, Object value, TextFormat.TextGenerator generator) throws IOException {
/*  236 */       if (field.isRepeated()) {
/*  238 */         for (Object element : value)
/*  239 */           printSingleField(field, element, generator); 
/*      */       } else {
/*  242 */         printSingleField(field, value, generator);
/*      */       } 
/*      */     }
/*      */     
/*      */     private void printSingleField(Descriptors.FieldDescriptor field, Object value, TextFormat.TextGenerator generator) throws IOException {
/*  250 */       if (field.isExtension()) {
/*  251 */         generator.print("[");
/*  253 */         if (field.getContainingType().getOptions().getMessageSetWireFormat() && field.getType() == Descriptors.FieldDescriptor.Type.MESSAGE && field.isOptional() && field.getExtensionScope() == field.getMessageType()) {
/*  258 */           generator.print(field.getMessageType().getFullName());
/*      */         } else {
/*  260 */           generator.print(field.getFullName());
/*      */         } 
/*  262 */         generator.print("]");
/*  264 */       } else if (field.getType() == Descriptors.FieldDescriptor.Type.GROUP) {
/*  266 */         generator.print(field.getMessageType().getName());
/*      */       } else {
/*  268 */         generator.print(field.getName());
/*      */       } 
/*  272 */       if (field.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
/*  273 */         if (this.singleLineMode) {
/*  274 */           generator.print(" { ");
/*      */         } else {
/*  276 */           generator.print(" {\n");
/*  277 */           generator.indent();
/*      */         } 
/*      */       } else {
/*  280 */         generator.print(": ");
/*      */       } 
/*  283 */       printFieldValue(field, value, generator);
/*  285 */       if (field.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
/*  286 */         if (this.singleLineMode) {
/*  287 */           generator.print("} ");
/*      */         } else {
/*  289 */           generator.outdent();
/*  290 */           generator.print("}\n");
/*      */         } 
/*  293 */       } else if (this.singleLineMode) {
/*  294 */         generator.print(" ");
/*      */       } else {
/*  296 */         generator.print("\n");
/*      */       } 
/*      */     }
/*      */     
/*      */     private void printFieldValue(Descriptors.FieldDescriptor field, Object value, TextFormat.TextGenerator generator) throws IOException {
/*  305 */       switch (field.getType()) {
/*      */         case INT32:
/*      */         case SINT32:
/*      */         case SFIXED32:
/*  309 */           generator.print(((Integer)value).toString());
/*      */           break;
/*      */         case INT64:
/*      */         case SINT64:
/*      */         case SFIXED64:
/*  315 */           generator.print(((Long)value).toString());
/*      */           break;
/*      */         case BOOL:
/*  319 */           generator.print(((Boolean)value).toString());
/*      */           break;
/*      */         case FLOAT:
/*  323 */           generator.print(((Float)value).toString());
/*      */           break;
/*      */         case DOUBLE:
/*  327 */           generator.print(((Double)value).toString());
/*      */           break;
/*      */         case UINT32:
/*      */         case FIXED32:
/*  332 */           generator.print(TextFormat.unsignedToString(((Integer)value).intValue()));
/*      */           break;
/*      */         case UINT64:
/*      */         case FIXED64:
/*  337 */           generator.print(TextFormat.unsignedToString(((Long)value).longValue()));
/*      */           break;
/*      */         case STRING:
/*  341 */           generator.print("\"");
/*  342 */           generator.print(TextFormat.escapeText((String)value));
/*  343 */           generator.print("\"");
/*      */           break;
/*      */         case BYTES:
/*  347 */           generator.print("\"");
/*  348 */           generator.print(TextFormat.escapeBytes((ByteString)value));
/*  349 */           generator.print("\"");
/*      */           break;
/*      */         case ENUM:
/*  353 */           generator.print(((Descriptors.EnumValueDescriptor)value).getName());
/*      */           break;
/*      */         case MESSAGE:
/*      */         case GROUP:
/*  358 */           print((Message)value, generator);
/*      */           break;
/*      */       } 
/*      */     }
/*      */     
/*      */     private void printUnknownFields(UnknownFieldSet unknownFields, TextFormat.TextGenerator generator) throws IOException {
/*  367 */       for (Map.Entry<Integer, UnknownFieldSet.Field> entry : unknownFields.asMap().entrySet()) {
/*  368 */         int number = ((Integer)entry.getKey()).intValue();
/*  369 */         UnknownFieldSet.Field field = entry.getValue();
/*  370 */         printUnknownField(number, 0, field.getVarintList(), generator);
/*  372 */         printUnknownField(number, 5, field.getFixed32List(), generator);
/*  374 */         printUnknownField(number, 1, field.getFixed64List(), generator);
/*  376 */         printUnknownField(number, 2, field.getLengthDelimitedList(), generator);
/*  378 */         for (UnknownFieldSet value : field.getGroupList()) {
/*  379 */           generator.print(((Integer)entry.getKey()).toString());
/*  380 */           if (this.singleLineMode) {
/*  381 */             generator.print(" { ");
/*      */           } else {
/*  383 */             generator.print(" {\n");
/*  384 */             generator.indent();
/*      */           } 
/*  386 */           printUnknownFields(value, generator);
/*  387 */           if (this.singleLineMode) {
/*  388 */             generator.print("} ");
/*      */             continue;
/*      */           } 
/*  390 */           generator.outdent();
/*  391 */           generator.print("}\n");
/*      */         } 
/*      */       } 
/*      */     }
/*      */     
/*      */     private void printUnknownField(int number, int wireType, List<?> values, TextFormat.TextGenerator generator) throws IOException {
/*  402 */       for (Object value : values) {
/*  403 */         generator.print(String.valueOf(number));
/*  404 */         generator.print(": ");
/*  405 */         TextFormat.printUnknownFieldValue(wireType, value, generator);
/*  406 */         generator.print(this.singleLineMode ? " " : "\n");
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   private static String unsignedToString(int value) {
/*  413 */     if (value >= 0)
/*  414 */       return Integer.toString(value); 
/*  416 */     return Long.toString(value & 0xFFFFFFFFL);
/*      */   }
/*      */   
/*      */   private static String unsignedToString(long value) {
/*  422 */     if (value >= 0L)
/*  423 */       return Long.toString(value); 
/*  427 */     return BigInteger.valueOf(value & Long.MAX_VALUE).setBit(63).toString();
/*      */   }
/*      */   
/*      */   private static final class TextGenerator {
/*      */     private final Appendable output;
/*      */     
/*  437 */     private final StringBuilder indent = new StringBuilder();
/*      */     
/*      */     private boolean atStartOfLine = true;
/*      */     
/*      */     private TextGenerator(Appendable output) {
/*  441 */       this.output = output;
/*      */     }
/*      */     
/*      */     public void indent() {
/*  450 */       this.indent.append("  ");
/*      */     }
/*      */     
/*      */     public void outdent() {
/*  458 */       int length = this.indent.length();
/*  459 */       if (length == 0)
/*  460 */         throw new IllegalArgumentException(" Outdent() without matching Indent()."); 
/*  463 */       this.indent.delete(length - 2, length);
/*      */     }
/*      */     
/*      */     public void print(CharSequence text) throws IOException {
/*  470 */       int size = text.length();
/*  471 */       int pos = 0;
/*  473 */       for (int i = 0; i < size; i++) {
/*  474 */         if (text.charAt(i) == '\n') {
/*  475 */           write(text.subSequence(pos, size), i - pos + 1);
/*  476 */           pos = i + 1;
/*  477 */           this.atStartOfLine = true;
/*      */         } 
/*      */       } 
/*  480 */       write(text.subSequence(pos, size), size - pos);
/*      */     }
/*      */     
/*      */     private void write(CharSequence data, int size) throws IOException {
/*  485 */       if (size == 0)
/*      */         return; 
/*  488 */       if (this.atStartOfLine) {
/*  489 */         this.atStartOfLine = false;
/*  490 */         this.output.append(this.indent);
/*      */       } 
/*  492 */       this.output.append(data);
/*      */     }
/*      */   }
/*      */   
/*      */   private static final class Tokenizer {
/*      */     private final CharSequence text;
/*      */     
/*      */     private final Matcher matcher;
/*      */     
/*      */     private String currentToken;
/*      */     
/*  533 */     private int pos = 0;
/*      */     
/*  536 */     private int line = 0;
/*      */     
/*  537 */     private int column = 0;
/*      */     
/*  541 */     private int previousLine = 0;
/*      */     
/*  542 */     private int previousColumn = 0;
/*      */     
/*  546 */     private static final Pattern WHITESPACE = Pattern.compile("(\\s|(#.*$))++", 8);
/*      */     
/*  548 */     private static final Pattern TOKEN = Pattern.compile("[a-zA-Z_][0-9a-zA-Z_+-]*+|[.]?[0-9+-][0-9a-zA-Z_.+-]*+|\"([^\"\n\\\\]|\\\\.)*+(\"|\\\\?$)|'([^'\n\\\\]|\\\\.)*+('|\\\\?$)", 8);
/*      */     
/*  555 */     private static final Pattern DOUBLE_INFINITY = Pattern.compile("-?inf(inity)?", 2);
/*      */     
/*  558 */     private static final Pattern FLOAT_INFINITY = Pattern.compile("-?inf(inity)?f?", 2);
/*      */     
/*  561 */     private static final Pattern FLOAT_NAN = Pattern.compile("nanf?", 2);
/*      */     
/*      */     private Tokenizer(CharSequence text) {
/*  567 */       this.text = text;
/*  568 */       this.matcher = WHITESPACE.matcher(text);
/*  569 */       skipWhitespace();
/*  570 */       nextToken();
/*      */     }
/*      */     
/*      */     public boolean atEnd() {
/*  575 */       return (this.currentToken.length() == 0);
/*      */     }
/*      */     
/*      */     public void nextToken() {
/*  580 */       this.previousLine = this.line;
/*  581 */       this.previousColumn = this.column;
/*  584 */       while (this.pos < this.matcher.regionStart()) {
/*  585 */         if (this.text.charAt(this.pos) == '\n') {
/*  586 */           this.line++;
/*  587 */           this.column = 0;
/*      */         } else {
/*  589 */           this.column++;
/*      */         } 
/*  591 */         this.pos++;
/*      */       } 
/*  595 */       if (this.matcher.regionStart() == this.matcher.regionEnd()) {
/*  597 */         this.currentToken = "";
/*      */       } else {
/*  599 */         this.matcher.usePattern(TOKEN);
/*  600 */         if (this.matcher.lookingAt()) {
/*  601 */           this.currentToken = this.matcher.group();
/*  602 */           this.matcher.region(this.matcher.end(), this.matcher.regionEnd());
/*      */         } else {
/*  605 */           this.currentToken = String.valueOf(this.text.charAt(this.pos));
/*  606 */           this.matcher.region(this.pos + 1, this.matcher.regionEnd());
/*      */         } 
/*  609 */         skipWhitespace();
/*      */       } 
/*      */     }
/*      */     
/*      */     private void skipWhitespace() {
/*  618 */       this.matcher.usePattern(WHITESPACE);
/*  619 */       if (this.matcher.lookingAt())
/*  620 */         this.matcher.region(this.matcher.end(), this.matcher.regionEnd()); 
/*      */     }
/*      */     
/*      */     public boolean tryConsume(String token) {
/*  629 */       if (this.currentToken.equals(token)) {
/*  630 */         nextToken();
/*  631 */         return true;
/*      */       } 
/*  633 */       return false;
/*      */     }
/*      */     
/*      */     public void consume(String token) throws TextFormat.ParseException {
/*  642 */       if (!tryConsume(token))
/*  643 */         throw parseException("Expected \"" + token + "\"."); 
/*      */     }
/*      */     
/*      */     public boolean lookingAtInteger() {
/*  652 */       if (this.currentToken.length() == 0)
/*  653 */         return false; 
/*  656 */       char c = this.currentToken.charAt(0);
/*  657 */       return (('0' <= c && c <= '9') || c == '-' || c == '+');
/*      */     }
/*      */     
/*      */     public String consumeIdentifier() throws TextFormat.ParseException {
/*  666 */       for (int i = 0; i < this.currentToken.length(); ) {
/*  667 */         char c = this.currentToken.charAt(i);
/*  668 */         if (('a' <= c && c <= 'z') || ('A' <= c && c <= 'Z') || ('0' <= c && c <= '9') || c == '_' || c == '.') {
/*      */           i++;
/*      */           continue;
/*      */         } 
/*  674 */         throw parseException("Expected identifier.");
/*      */       } 
/*  678 */       String result = this.currentToken;
/*  679 */       nextToken();
/*  680 */       return result;
/*      */     }
/*      */     
/*      */     public int consumeInt32() throws TextFormat.ParseException {
/*      */       try {
/*  689 */         int result = TextFormat.parseInt32(this.currentToken);
/*  690 */         nextToken();
/*  691 */         return result;
/*  692 */       } catch (NumberFormatException e) {
/*  693 */         throw integerParseException(e);
/*      */       } 
/*      */     }
/*      */     
/*      */     public int consumeUInt32() throws TextFormat.ParseException {
/*      */       try {
/*  703 */         int result = TextFormat.parseUInt32(this.currentToken);
/*  704 */         nextToken();
/*  705 */         return result;
/*  706 */       } catch (NumberFormatException e) {
/*  707 */         throw integerParseException(e);
/*      */       } 
/*      */     }
/*      */     
/*      */     public long consumeInt64() throws TextFormat.ParseException {
/*      */       try {
/*  717 */         long result = TextFormat.parseInt64(this.currentToken);
/*  718 */         nextToken();
/*  719 */         return result;
/*  720 */       } catch (NumberFormatException e) {
/*  721 */         throw integerParseException(e);
/*      */       } 
/*      */     }
/*      */     
/*      */     public long consumeUInt64() throws TextFormat.ParseException {
/*      */       try {
/*  731 */         long result = TextFormat.parseUInt64(this.currentToken);
/*  732 */         nextToken();
/*  733 */         return result;
/*  734 */       } catch (NumberFormatException e) {
/*  735 */         throw integerParseException(e);
/*      */       } 
/*      */     }
/*      */     
/*      */     public double consumeDouble() throws TextFormat.ParseException {
/*  746 */       if (DOUBLE_INFINITY.matcher(this.currentToken).matches()) {
/*  747 */         boolean negative = this.currentToken.startsWith("-");
/*  748 */         nextToken();
/*  749 */         return negative ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY;
/*      */       } 
/*  751 */       if (this.currentToken.equalsIgnoreCase("nan")) {
/*  752 */         nextToken();
/*  753 */         return Double.NaN;
/*      */       } 
/*      */       try {
/*  756 */         double result = Double.parseDouble(this.currentToken);
/*  757 */         nextToken();
/*  758 */         return result;
/*  759 */       } catch (NumberFormatException e) {
/*  760 */         throw floatParseException(e);
/*      */       } 
/*      */     }
/*      */     
/*      */     public float consumeFloat() throws TextFormat.ParseException {
/*  771 */       if (FLOAT_INFINITY.matcher(this.currentToken).matches()) {
/*  772 */         boolean negative = this.currentToken.startsWith("-");
/*  773 */         nextToken();
/*  774 */         return negative ? Float.NEGATIVE_INFINITY : Float.POSITIVE_INFINITY;
/*      */       } 
/*  776 */       if (FLOAT_NAN.matcher(this.currentToken).matches()) {
/*  777 */         nextToken();
/*  778 */         return Float.NaN;
/*      */       } 
/*      */       try {
/*  781 */         float result = Float.parseFloat(this.currentToken);
/*  782 */         nextToken();
/*  783 */         return result;
/*  784 */       } catch (NumberFormatException e) {
/*  785 */         throw floatParseException(e);
/*      */       } 
/*      */     }
/*      */     
/*      */     public boolean consumeBoolean() throws TextFormat.ParseException {
/*  794 */       if (this.currentToken.equals("true") || this.currentToken.equals("t") || this.currentToken.equals("1")) {
/*  797 */         nextToken();
/*  798 */         return true;
/*      */       } 
/*  799 */       if (this.currentToken.equals("false") || this.currentToken.equals("f") || this.currentToken.equals("0")) {
/*  802 */         nextToken();
/*  803 */         return false;
/*      */       } 
/*  805 */       throw parseException("Expected \"true\" or \"false\".");
/*      */     }
/*      */     
/*      */     public String consumeString() throws TextFormat.ParseException {
/*  814 */       return consumeByteString().toStringUtf8();
/*      */     }
/*      */     
/*      */     public ByteString consumeByteString() throws TextFormat.ParseException {
/*  823 */       List<ByteString> list = new ArrayList<ByteString>();
/*  824 */       consumeByteString(list);
/*  825 */       while (this.currentToken.startsWith("'") || this.currentToken.startsWith("\""))
/*  826 */         consumeByteString(list); 
/*  828 */       return ByteString.copyFrom(list);
/*      */     }
/*      */     
/*      */     private void consumeByteString(List<ByteString> list) throws TextFormat.ParseException {
/*  838 */       char quote = (this.currentToken.length() > 0) ? this.currentToken.charAt(0) : Character.MIN_VALUE;
/*  840 */       if (quote != '"' && quote != '\'')
/*  841 */         throw parseException("Expected string."); 
/*  844 */       if (this.currentToken.length() < 2 || this.currentToken.charAt(this.currentToken.length() - 1) != quote)
/*  846 */         throw parseException("String missing ending quote."); 
/*      */       try {
/*  850 */         String escaped = this.currentToken.substring(1, this.currentToken.length() - 1);
/*  852 */         ByteString result = TextFormat.unescapeBytes(escaped);
/*  853 */         nextToken();
/*  854 */         list.add(result);
/*  855 */       } catch (InvalidEscapeSequenceException e) {
/*  856 */         throw parseException(e.getMessage());
/*      */       } 
/*      */     }
/*      */     
/*      */     public TextFormat.ParseException parseException(String description) {
/*  866 */       return new TextFormat.ParseException((this.line + 1) + ":" + (this.column + 1) + ": " + description);
/*      */     }
/*      */     
/*      */     public TextFormat.ParseException parseExceptionPreviousToken(String description) {
/*  877 */       return new TextFormat.ParseException((this.previousLine + 1) + ":" + (this.previousColumn + 1) + ": " + description);
/*      */     }
/*      */     
/*      */     private TextFormat.ParseException integerParseException(NumberFormatException e) {
/*  887 */       return parseException("Couldn't parse integer: " + e.getMessage());
/*      */     }
/*      */     
/*      */     private TextFormat.ParseException floatParseException(NumberFormatException e) {
/*  895 */       return parseException("Couldn't parse number: " + e.getMessage());
/*      */     }
/*      */   }
/*      */   
/*      */   public static class ParseException extends IOException {
/*      */     private static final long serialVersionUID = 3196188060225107702L;
/*      */     
/*      */     public ParseException(String message) {
/*  904 */       super(message);
/*      */     }
/*      */   }
/*      */   
/*      */   public static void merge(Readable input, Message.Builder builder) throws IOException {
/*  915 */     merge(input, ExtensionRegistry.getEmptyRegistry(), builder);
/*      */   }
/*      */   
/*      */   public static void merge(CharSequence input, Message.Builder builder) throws ParseException {
/*  925 */     merge(input, ExtensionRegistry.getEmptyRegistry(), builder);
/*      */   }
/*      */   
/*      */   public static void merge(Readable input, ExtensionRegistry extensionRegistry, Message.Builder builder) throws IOException {
/*  945 */     merge(toStringBuilder(input), extensionRegistry, builder);
/*      */   }
/*      */   
/*      */   private static StringBuilder toStringBuilder(Readable input) throws IOException {
/*  954 */     StringBuilder text = new StringBuilder();
/*  955 */     CharBuffer buffer = CharBuffer.allocate(4096);
/*      */     while (true) {
/*  957 */       int n = input.read(buffer);
/*  958 */       if (n == -1)
/*      */         break; 
/*  961 */       buffer.flip();
/*  962 */       text.append(buffer, 0, n);
/*      */     } 
/*  964 */     return text;
/*      */   }
/*      */   
/*      */   public static void merge(CharSequence input, ExtensionRegistry extensionRegistry, Message.Builder builder) throws ParseException {
/*  976 */     Tokenizer tokenizer = new Tokenizer(input);
/*  978 */     while (!tokenizer.atEnd())
/*  979 */       mergeField(tokenizer, extensionRegistry, builder); 
/*      */   }
/*      */   
/*      */   private static void mergeField(Tokenizer tokenizer, ExtensionRegistry extensionRegistry, Message.Builder builder) throws ParseException {
/*      */     Descriptors.FieldDescriptor field;
/*  992 */     Descriptors.Descriptor type = builder.getDescriptorForType();
/*  993 */     ExtensionRegistry.ExtensionInfo extension = null;
/*  995 */     if (tokenizer.tryConsume("[")) {
/*  997 */       StringBuilder name = new StringBuilder(tokenizer.consumeIdentifier());
/*  999 */       while (tokenizer.tryConsume(".")) {
/* 1000 */         name.append('.');
/* 1001 */         name.append(tokenizer.consumeIdentifier());
/*      */       } 
/* 1004 */       extension = extensionRegistry.findExtensionByName(name.toString());
/* 1006 */       if (extension == null)
/* 1007 */         throw tokenizer.parseExceptionPreviousToken("Extension \"" + name + "\" not found in the ExtensionRegistry."); 
/* 1009 */       if (extension.descriptor.getContainingType() != type)
/* 1010 */         throw tokenizer.parseExceptionPreviousToken("Extension \"" + name + "\" does not extend message type \"" + type.getFullName() + "\"."); 
/* 1015 */       tokenizer.consume("]");
/* 1017 */       field = extension.descriptor;
/*      */     } else {
/* 1019 */       String name = tokenizer.consumeIdentifier();
/* 1020 */       field = type.findFieldByName(name);
/* 1025 */       if (field == null) {
/* 1028 */         String lowerName = name.toLowerCase(Locale.US);
/* 1029 */         field = type.findFieldByName(lowerName);
/* 1031 */         if (field != null && field.getType() != Descriptors.FieldDescriptor.Type.GROUP)
/* 1032 */           field = null; 
/*      */       } 
/* 1036 */       if (field != null && field.getType() == Descriptors.FieldDescriptor.Type.GROUP && !field.getMessageType().getName().equals(name))
/* 1038 */         field = null; 
/* 1041 */       if (field == null)
/* 1042 */         throw tokenizer.parseExceptionPreviousToken("Message type \"" + type.getFullName() + "\" has no field named \"" + name + "\"."); 
/*      */     } 
/* 1048 */     Object value = null;
/* 1050 */     if (field.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
/*      */       String endToken;
/*      */       Message.Builder subBuilder;
/* 1051 */       tokenizer.tryConsume(":");
/* 1054 */       if (tokenizer.tryConsume("<")) {
/* 1055 */         endToken = ">";
/*      */       } else {
/* 1057 */         tokenizer.consume("{");
/* 1058 */         endToken = "}";
/*      */       } 
/* 1062 */       if (extension == null) {
/* 1063 */         subBuilder = builder.newBuilderForField(field);
/*      */       } else {
/* 1065 */         subBuilder = extension.defaultInstance.newBuilderForType();
/*      */       } 
/* 1068 */       while (!tokenizer.tryConsume(endToken)) {
/* 1069 */         if (tokenizer.atEnd())
/* 1070 */           throw tokenizer.parseException("Expected \"" + endToken + "\"."); 
/* 1073 */         mergeField(tokenizer, extensionRegistry, subBuilder);
/*      */       } 
/* 1076 */       value = subBuilder.build();
/*      */     } else {
/*      */       Descriptors.EnumDescriptor enumType;
/*      */       String id;
/* 1079 */       tokenizer.consume(":");
/* 1081 */       switch (field.getType()) {
/*      */         case INT32:
/*      */         case SINT32:
/*      */         case SFIXED32:
/* 1085 */           value = Integer.valueOf(tokenizer.consumeInt32());
/*      */           break;
/*      */         case INT64:
/*      */         case SINT64:
/*      */         case SFIXED64:
/* 1091 */           value = Long.valueOf(tokenizer.consumeInt64());
/*      */           break;
/*      */         case UINT32:
/*      */         case FIXED32:
/* 1096 */           value = Integer.valueOf(tokenizer.consumeUInt32());
/*      */           break;
/*      */         case UINT64:
/*      */         case FIXED64:
/* 1101 */           value = Long.valueOf(tokenizer.consumeUInt64());
/*      */           break;
/*      */         case FLOAT:
/* 1105 */           value = Float.valueOf(tokenizer.consumeFloat());
/*      */           break;
/*      */         case DOUBLE:
/* 1109 */           value = Double.valueOf(tokenizer.consumeDouble());
/*      */           break;
/*      */         case BOOL:
/* 1113 */           value = Boolean.valueOf(tokenizer.consumeBoolean());
/*      */           break;
/*      */         case STRING:
/* 1117 */           value = tokenizer.consumeString();
/*      */           break;
/*      */         case BYTES:
/* 1121 */           value = tokenizer.consumeByteString();
/*      */           break;
/*      */         case ENUM:
/* 1125 */           enumType = field.getEnumType();
/* 1127 */           if (tokenizer.lookingAtInteger()) {
/* 1128 */             int number = tokenizer.consumeInt32();
/* 1129 */             value = enumType.findValueByNumber(number);
/* 1130 */             if (value == null)
/* 1131 */               throw tokenizer.parseExceptionPreviousToken("Enum type \"" + enumType.getFullName() + "\" has no value with number " + number + '.'); 
/*      */             break;
/*      */           } 
/* 1136 */           id = tokenizer.consumeIdentifier();
/* 1137 */           value = enumType.findValueByName(id);
/* 1138 */           if (value == null)
/* 1139 */             throw tokenizer.parseExceptionPreviousToken("Enum type \"" + enumType.getFullName() + "\" has no value named \"" + id + "\"."); 
/*      */           break;
/*      */         case MESSAGE:
/*      */         case GROUP:
/* 1149 */           throw new RuntimeException("Can't get here.");
/*      */       } 
/*      */     } 
/* 1153 */     if (field.isRepeated()) {
/* 1154 */       builder.addRepeatedField(field, value);
/*      */     } else {
/* 1156 */       builder.setField(field, value);
/*      */     } 
/*      */   }
/*      */   
/*      */   static String escapeBytes(ByteString input) {
/* 1175 */     StringBuilder builder = new StringBuilder(input.size());
/* 1176 */     for (int i = 0; i < input.size(); i++) {
/* 1177 */       byte b = input.byteAt(i);
/* 1178 */       switch (b) {
/*      */         case 7:
/* 1180 */           builder.append("\\a");
/*      */           break;
/*      */         case 8:
/* 1181 */           builder.append("\\b");
/*      */           break;
/*      */         case 12:
/* 1182 */           builder.append("\\f");
/*      */           break;
/*      */         case 10:
/* 1183 */           builder.append("\\n");
/*      */           break;
/*      */         case 13:
/* 1184 */           builder.append("\\r");
/*      */           break;
/*      */         case 9:
/* 1185 */           builder.append("\\t");
/*      */           break;
/*      */         case 11:
/* 1186 */           builder.append("\\v");
/*      */           break;
/*      */         case 92:
/* 1187 */           builder.append("\\\\");
/*      */           break;
/*      */         case 39:
/* 1188 */           builder.append("\\'");
/*      */           break;
/*      */         case 34:
/* 1189 */           builder.append("\\\"");
/*      */           break;
/*      */         default:
/* 1194 */           if (b >= 32) {
/* 1195 */             builder.append((char)b);
/*      */             break;
/*      */           } 
/* 1197 */           builder.append('\\');
/* 1198 */           builder.append((char)(48 + (b >>> 6 & 0x3)));
/* 1199 */           builder.append((char)(48 + (b >>> 3 & 0x7)));
/* 1200 */           builder.append((char)(48 + (b & 0x7)));
/*      */           break;
/*      */       } 
/*      */     } 
/* 1205 */     return builder.toString();
/*      */   }
/*      */   
/*      */   static ByteString unescapeBytes(CharSequence charString) throws InvalidEscapeSequenceException {
/* 1216 */     ByteString input = ByteString.copyFromUtf8(charString.toString());
/* 1224 */     byte[] result = new byte[input.size()];
/* 1225 */     int pos = 0;
/* 1226 */     for (int i = 0; i < input.size(); i++) {
/* 1227 */       byte c = input.byteAt(i);
/* 1228 */       if (c == 92) {
/* 1229 */         if (i + 1 < input.size()) {
/* 1230 */           i++;
/* 1231 */           c = input.byteAt(i);
/* 1232 */           if (isOctal(c)) {
/* 1234 */             int code = digitValue(c);
/* 1235 */             if (i + 1 < input.size() && isOctal(input.byteAt(i + 1))) {
/* 1236 */               i++;
/* 1237 */               code = code * 8 + digitValue(input.byteAt(i));
/*      */             } 
/* 1239 */             if (i + 1 < input.size() && isOctal(input.byteAt(i + 1))) {
/* 1240 */               i++;
/* 1241 */               code = code * 8 + digitValue(input.byteAt(i));
/*      */             } 
/* 1244 */             result[pos++] = (byte)code;
/*      */           } else {
/*      */             int code;
/* 1246 */             switch (c) {
/*      */               case 97:
/* 1247 */                 result[pos++] = 7;
/*      */                 break;
/*      */               case 98:
/* 1248 */                 result[pos++] = 8;
/*      */                 break;
/*      */               case 102:
/* 1249 */                 result[pos++] = 12;
/*      */                 break;
/*      */               case 110:
/* 1250 */                 result[pos++] = 10;
/*      */                 break;
/*      */               case 114:
/* 1251 */                 result[pos++] = 13;
/*      */                 break;
/*      */               case 116:
/* 1252 */                 result[pos++] = 9;
/*      */                 break;
/*      */               case 118:
/* 1253 */                 result[pos++] = 11;
/*      */                 break;
/*      */               case 92:
/* 1254 */                 result[pos++] = 92;
/*      */                 break;
/*      */               case 39:
/* 1255 */                 result[pos++] = 39;
/*      */                 break;
/*      */               case 34:
/* 1256 */                 result[pos++] = 34;
/*      */                 break;
/*      */               case 120:
/* 1260 */                 code = 0;
/* 1261 */                 if (i + 1 < input.size() && isHex(input.byteAt(i + 1))) {
/* 1262 */                   i++;
/* 1263 */                   code = digitValue(input.byteAt(i));
/*      */                 } else {
/* 1265 */                   throw new InvalidEscapeSequenceException("Invalid escape sequence: '\\x' with no digits");
/*      */                 } 
/* 1268 */                 if (i + 1 < input.size() && isHex(input.byteAt(i + 1))) {
/* 1269 */                   i++;
/* 1270 */                   code = code * 16 + digitValue(input.byteAt(i));
/*      */                 } 
/* 1272 */                 result[pos++] = (byte)code;
/*      */                 break;
/*      */               default:
/* 1276 */                 throw new InvalidEscapeSequenceException("Invalid escape sequence: '\\" + (char)c + '\'');
/*      */             } 
/*      */           } 
/*      */         } else {
/* 1281 */           throw new InvalidEscapeSequenceException("Invalid escape sequence: '\\' at end of string.");
/*      */         } 
/*      */       } else {
/* 1285 */         result[pos++] = c;
/*      */       } 
/*      */     } 
/* 1289 */     return ByteString.copyFrom(result, 0, pos);
/*      */   }
/*      */   
/*      */   static class InvalidEscapeSequenceException extends IOException {
/*      */     private static final long serialVersionUID = -8164033650142593304L;
/*      */     
/*      */     InvalidEscapeSequenceException(String description) {
/* 1300 */       super(description);
/*      */     }
/*      */   }
/*      */   
/*      */   static String escapeText(String input) {
/* 1310 */     return escapeBytes(ByteString.copyFromUtf8(input));
/*      */   }
/*      */   
/*      */   static String unescapeText(String input) throws InvalidEscapeSequenceException {
/* 1319 */     return unescapeBytes(input).toStringUtf8();
/*      */   }
/*      */   
/*      */   private static boolean isOctal(byte c) {
/* 1324 */     return (48 <= c && c <= 55);
/*      */   }
/*      */   
/*      */   private static boolean isHex(byte c) {
/* 1329 */     return ((48 <= c && c <= 57) || (97 <= c && c <= 102) || (65 <= c && c <= 70));
/*      */   }
/*      */   
/*      */   private static int digitValue(byte c) {
/* 1340 */     if (48 <= c && c <= 57)
/* 1341 */       return c - 48; 
/* 1342 */     if (97 <= c && c <= 122)
/* 1343 */       return c - 97 + 10; 
/* 1345 */     return c - 65 + 10;
/*      */   }
/*      */   
/*      */   static int parseInt32(String text) throws NumberFormatException {
/* 1355 */     return (int)parseInteger(text, true, false);
/*      */   }
/*      */   
/*      */   static int parseUInt32(String text) throws NumberFormatException {
/* 1366 */     return (int)parseInteger(text, false, false);
/*      */   }
/*      */   
/*      */   static long parseInt64(String text) throws NumberFormatException {
/* 1375 */     return parseInteger(text, true, true);
/*      */   }
/*      */   
/*      */   static long parseUInt64(String text) throws NumberFormatException {
/* 1386 */     return parseInteger(text, false, true);
/*      */   }
/*      */   
/*      */   private static long parseInteger(String text, boolean isSigned, boolean isLong) throws NumberFormatException {
/* 1393 */     int pos = 0;
/* 1395 */     boolean negative = false;
/* 1396 */     if (text.startsWith("-", pos)) {
/* 1397 */       if (!isSigned)
/* 1398 */         throw new NumberFormatException("Number must be positive: " + text); 
/* 1400 */       pos++;
/* 1401 */       negative = true;
/*      */     } 
/* 1404 */     int radix = 10;
/* 1405 */     if (text.startsWith("0x", pos)) {
/* 1406 */       pos += 2;
/* 1407 */       radix = 16;
/* 1408 */     } else if (text.startsWith("0", pos)) {
/* 1409 */       radix = 8;
/*      */     } 
/* 1412 */     String numberText = text.substring(pos);
/* 1414 */     long result = 0L;
/* 1415 */     if (numberText.length() < 16) {
/* 1417 */       result = Long.parseLong(numberText, radix);
/* 1418 */       if (negative)
/* 1419 */         result = -result; 
/* 1425 */       if (!isLong)
/* 1426 */         if (isSigned) {
/* 1427 */           if (result > 2147483647L || result < -2147483648L)
/* 1428 */             throw new NumberFormatException("Number out of range for 32-bit signed integer: " + text); 
/* 1432 */         } else if (result >= 4294967296L || result < 0L) {
/* 1433 */           throw new NumberFormatException("Number out of range for 32-bit unsigned integer: " + text);
/*      */         }  
/*      */     } else {
/* 1439 */       BigInteger bigValue = new BigInteger(numberText, radix);
/* 1440 */       if (negative)
/* 1441 */         bigValue = bigValue.negate(); 
/* 1445 */       if (!isLong) {
/* 1446 */         if (isSigned) {
/* 1447 */           if (bigValue.bitLength() > 31)
/* 1448 */             throw new NumberFormatException("Number out of range for 32-bit signed integer: " + text); 
/* 1452 */         } else if (bigValue.bitLength() > 32) {
/* 1453 */           throw new NumberFormatException("Number out of range for 32-bit unsigned integer: " + text);
/*      */         } 
/* 1458 */       } else if (isSigned) {
/* 1459 */         if (bigValue.bitLength() > 63)
/* 1460 */           throw new NumberFormatException("Number out of range for 64-bit signed integer: " + text); 
/* 1464 */       } else if (bigValue.bitLength() > 64) {
/* 1465 */         throw new NumberFormatException("Number out of range for 64-bit unsigned integer: " + text);
/*      */       } 
/* 1471 */       result = bigValue.longValue();
/*      */     } 
/* 1474 */     return result;
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\google\protobuf\TextFormat.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */