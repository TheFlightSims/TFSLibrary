/*     */ package com.google.common.net;
/*     */ 
/*     */ import com.google.common.annotations.Beta;
/*     */ import com.google.common.annotations.GwtCompatible;
/*     */ import com.google.common.base.Ascii;
/*     */ import com.google.common.base.CharMatcher;
/*     */ import com.google.common.base.Charsets;
/*     */ import com.google.common.base.Function;
/*     */ import com.google.common.base.Joiner;
/*     */ import com.google.common.base.Objects;
/*     */ import com.google.common.base.Optional;
/*     */ import com.google.common.base.Preconditions;
/*     */ import com.google.common.collect.ImmutableListMultimap;
/*     */ import com.google.common.collect.ImmutableMultiset;
/*     */ import com.google.common.collect.ImmutableSet;
/*     */ import com.google.common.collect.Iterables;
/*     */ import com.google.common.collect.ListMultimap;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.common.collect.Multimap;
/*     */ import com.google.common.collect.Multimaps;
/*     */ import java.nio.charset.Charset;
/*     */ import java.util.Collection;
/*     */ import java.util.Map;
/*     */ import javax.annotation.Nullable;
/*     */ import javax.annotation.concurrent.Immutable;
/*     */ 
/*     */ @Beta
/*     */ @GwtCompatible
/*     */ @Immutable
/*     */ public final class MediaType {
/*     */   private static final String CHARSET_ATTRIBUTE = "charset";
/*     */   
/*  84 */   private static final ImmutableListMultimap<String, String> UTF_8_CONSTANT_PARAMETERS = ImmutableListMultimap.of("charset", Ascii.toLowerCase(Charsets.UTF_8.name()));
/*     */   
/*  88 */   private static final CharMatcher TOKEN_MATCHER = CharMatcher.ASCII.and(CharMatcher.JAVA_ISO_CONTROL.negate()).and(CharMatcher.isNot(' ')).and(CharMatcher.noneOf("()<>@,;:\\\"/[]?="));
/*     */   
/*  91 */   private static final CharMatcher QUOTED_TEXT_MATCHER = CharMatcher.ASCII.and(CharMatcher.noneOf("\"\\\r"));
/*     */   
/*  97 */   private static final CharMatcher LINEAR_WHITE_SPACE = CharMatcher.anyOf(" \t\r\n");
/*     */   
/*     */   private static final String APPLICATION_TYPE = "application";
/*     */   
/*     */   private static final String AUDIO_TYPE = "audio";
/*     */   
/*     */   private static final String IMAGE_TYPE = "image";
/*     */   
/*     */   private static final String TEXT_TYPE = "text";
/*     */   
/*     */   private static final String VIDEO_TYPE = "video";
/*     */   
/*     */   private static final String WILDCARD = "*";
/*     */   
/* 108 */   private static final Map<MediaType, MediaType> KNOWN_TYPES = Maps.newHashMap();
/*     */   
/*     */   private static MediaType createConstant(String type, String subtype) {
/* 111 */     return addKnownType(new MediaType(type, subtype, ImmutableListMultimap.of()));
/*     */   }
/*     */   
/*     */   private static MediaType createConstantUtf8(String type, String subtype) {
/* 115 */     return addKnownType(new MediaType(type, subtype, UTF_8_CONSTANT_PARAMETERS));
/*     */   }
/*     */   
/*     */   private static MediaType addKnownType(MediaType mediaType) {
/* 119 */     KNOWN_TYPES.put(mediaType, mediaType);
/* 120 */     return mediaType;
/*     */   }
/*     */   
/* 133 */   public static final MediaType ANY_TYPE = createConstant("*", "*");
/*     */   
/* 134 */   public static final MediaType ANY_TEXT_TYPE = createConstant("text", "*");
/*     */   
/* 135 */   public static final MediaType ANY_IMAGE_TYPE = createConstant("image", "*");
/*     */   
/* 136 */   public static final MediaType ANY_AUDIO_TYPE = createConstant("audio", "*");
/*     */   
/* 137 */   public static final MediaType ANY_VIDEO_TYPE = createConstant("video", "*");
/*     */   
/* 138 */   public static final MediaType ANY_APPLICATION_TYPE = createConstant("application", "*");
/*     */   
/* 141 */   public static final MediaType CACHE_MANIFEST_UTF_8 = createConstantUtf8("text", "cache-manifest");
/*     */   
/* 143 */   public static final MediaType CSS_UTF_8 = createConstantUtf8("text", "css");
/*     */   
/* 144 */   public static final MediaType CSV_UTF_8 = createConstantUtf8("text", "csv");
/*     */   
/* 145 */   public static final MediaType HTML_UTF_8 = createConstantUtf8("text", "html");
/*     */   
/* 146 */   public static final MediaType I_CALENDAR_UTF_8 = createConstantUtf8("text", "calendar");
/*     */   
/* 147 */   public static final MediaType PLAIN_TEXT_UTF_8 = createConstantUtf8("text", "plain");
/*     */   
/* 153 */   public static final MediaType TEXT_JAVASCRIPT_UTF_8 = createConstantUtf8("text", "javascript");
/*     */   
/* 160 */   public static final MediaType TSV_UTF_8 = createConstantUtf8("text", "tab-separated-values");
/*     */   
/* 161 */   public static final MediaType VCARD_UTF_8 = createConstantUtf8("text", "vcard");
/*     */   
/* 162 */   public static final MediaType WML_UTF_8 = createConstantUtf8("text", "vnd.wap.wml");
/*     */   
/* 168 */   public static final MediaType XML_UTF_8 = createConstantUtf8("text", "xml");
/*     */   
/* 171 */   public static final MediaType BMP = createConstant("image", "bmp");
/*     */   
/* 181 */   public static final MediaType CRW = createConstant("image", "x-canon-crw");
/*     */   
/* 182 */   public static final MediaType GIF = createConstant("image", "gif");
/*     */   
/* 183 */   public static final MediaType ICO = createConstant("image", "vnd.microsoft.icon");
/*     */   
/* 184 */   public static final MediaType JPEG = createConstant("image", "jpeg");
/*     */   
/* 185 */   public static final MediaType PNG = createConstant("image", "png");
/*     */   
/* 202 */   public static final MediaType PSD = createConstant("image", "vnd.adobe.photoshop");
/*     */   
/* 203 */   public static final MediaType SVG_UTF_8 = createConstantUtf8("image", "svg+xml");
/*     */   
/* 204 */   public static final MediaType TIFF = createConstant("image", "tiff");
/*     */   
/* 205 */   public static final MediaType WEBP = createConstant("image", "webp");
/*     */   
/* 208 */   public static final MediaType MP4_AUDIO = createConstant("audio", "mp4");
/*     */   
/* 209 */   public static final MediaType MPEG_AUDIO = createConstant("audio", "mpeg");
/*     */   
/* 210 */   public static final MediaType OGG_AUDIO = createConstant("audio", "ogg");
/*     */   
/* 211 */   public static final MediaType WEBM_AUDIO = createConstant("audio", "webm");
/*     */   
/* 214 */   public static final MediaType MP4_VIDEO = createConstant("video", "mp4");
/*     */   
/* 215 */   public static final MediaType MPEG_VIDEO = createConstant("video", "mpeg");
/*     */   
/* 216 */   public static final MediaType OGG_VIDEO = createConstant("video", "ogg");
/*     */   
/* 217 */   public static final MediaType QUICKTIME = createConstant("video", "quicktime");
/*     */   
/* 218 */   public static final MediaType WEBM_VIDEO = createConstant("video", "webm");
/*     */   
/* 219 */   public static final MediaType WMV = createConstant("video", "x-ms-wmv");
/*     */   
/* 227 */   public static final MediaType APPLICATION_XML_UTF_8 = createConstantUtf8("application", "xml");
/*     */   
/* 228 */   public static final MediaType ATOM_UTF_8 = createConstantUtf8("application", "atom+xml");
/*     */   
/* 229 */   public static final MediaType BZIP2 = createConstant("application", "x-bzip2");
/*     */   
/* 238 */   public static final MediaType EOT = createConstant("application", "vnd.ms-fontobject");
/*     */   
/* 248 */   public static final MediaType EPUB = createConstant("application", "epub+zip");
/*     */   
/* 249 */   public static final MediaType FORM_DATA = createConstant("application", "x-www-form-urlencoded");
/*     */   
/* 258 */   public static final MediaType KEY_ARCHIVE = createConstant("application", "pkcs12");
/*     */   
/* 270 */   public static final MediaType APPLICATION_BINARY = createConstant("application", "binary");
/*     */   
/* 271 */   public static final MediaType GZIP = createConstant("application", "x-gzip");
/*     */   
/* 277 */   public static final MediaType JAVASCRIPT_UTF_8 = createConstantUtf8("application", "javascript");
/*     */   
/* 279 */   public static final MediaType JSON_UTF_8 = createConstantUtf8("application", "json");
/*     */   
/* 280 */   public static final MediaType KML = createConstant("application", "vnd.google-earth.kml+xml");
/*     */   
/* 281 */   public static final MediaType KMZ = createConstant("application", "vnd.google-earth.kmz");
/*     */   
/* 282 */   public static final MediaType MBOX = createConstant("application", "mbox");
/*     */   
/* 283 */   public static final MediaType MICROSOFT_EXCEL = createConstant("application", "vnd.ms-excel");
/*     */   
/* 284 */   public static final MediaType MICROSOFT_POWERPOINT = createConstant("application", "vnd.ms-powerpoint");
/*     */   
/* 286 */   public static final MediaType MICROSOFT_WORD = createConstant("application", "msword");
/*     */   
/* 287 */   public static final MediaType OCTET_STREAM = createConstant("application", "octet-stream");
/*     */   
/* 288 */   public static final MediaType OGG_CONTAINER = createConstant("application", "ogg");
/*     */   
/* 289 */   public static final MediaType OOXML_DOCUMENT = createConstant("application", "vnd.openxmlformats-officedocument.wordprocessingml.document");
/*     */   
/* 291 */   public static final MediaType OOXML_PRESENTATION = createConstant("application", "vnd.openxmlformats-officedocument.presentationml.presentation");
/*     */   
/* 293 */   public static final MediaType OOXML_SHEET = createConstant("application", "vnd.openxmlformats-officedocument.spreadsheetml.sheet");
/*     */   
/* 295 */   public static final MediaType OPENDOCUMENT_GRAPHICS = createConstant("application", "vnd.oasis.opendocument.graphics");
/*     */   
/* 297 */   public static final MediaType OPENDOCUMENT_PRESENTATION = createConstant("application", "vnd.oasis.opendocument.presentation");
/*     */   
/* 299 */   public static final MediaType OPENDOCUMENT_SPREADSHEET = createConstant("application", "vnd.oasis.opendocument.spreadsheet");
/*     */   
/* 301 */   public static final MediaType OPENDOCUMENT_TEXT = createConstant("application", "vnd.oasis.opendocument.text");
/*     */   
/* 303 */   public static final MediaType PDF = createConstant("application", "pdf");
/*     */   
/* 304 */   public static final MediaType POSTSCRIPT = createConstant("application", "postscript");
/*     */   
/* 310 */   public static final MediaType PROTOBUF = createConstant("application", "protobuf");
/*     */   
/* 311 */   public static final MediaType RDF_XML_UTF_8 = createConstantUtf8("application", "rdf+xml");
/*     */   
/* 312 */   public static final MediaType RTF_UTF_8 = createConstantUtf8("application", "rtf");
/*     */   
/* 322 */   public static final MediaType SFNT = createConstant("application", "font-sfnt");
/*     */   
/* 323 */   public static final MediaType SHOCKWAVE_FLASH = createConstant("application", "x-shockwave-flash");
/*     */   
/* 325 */   public static final MediaType SKETCHUP = createConstant("application", "vnd.sketchup.skp");
/*     */   
/* 326 */   public static final MediaType TAR = createConstant("application", "x-tar");
/*     */   
/* 336 */   public static final MediaType WOFF = createConstant("application", "font-woff");
/*     */   
/* 337 */   public static final MediaType XHTML_UTF_8 = createConstantUtf8("application", "xhtml+xml");
/*     */   
/* 345 */   public static final MediaType XRD_UTF_8 = createConstantUtf8("application", "xrd+xml");
/*     */   
/* 346 */   public static final MediaType ZIP = createConstant("application", "zip");
/*     */   
/*     */   private final String type;
/*     */   
/*     */   private final String subtype;
/*     */   
/*     */   private final ImmutableListMultimap<String, String> parameters;
/*     */   
/*     */   private MediaType(String type, String subtype, ImmutableListMultimap<String, String> parameters) {
/* 354 */     this.type = type;
/* 355 */     this.subtype = subtype;
/* 356 */     this.parameters = parameters;
/*     */   }
/*     */   
/*     */   public String type() {
/* 361 */     return this.type;
/*     */   }
/*     */   
/*     */   public String subtype() {
/* 366 */     return this.subtype;
/*     */   }
/*     */   
/*     */   public ImmutableListMultimap<String, String> parameters() {
/* 371 */     return this.parameters;
/*     */   }
/*     */   
/*     */   private Map<String, ImmutableMultiset<String>> parametersAsMap() {
/* 375 */     return Maps.transformValues((Map)this.parameters.asMap(), new Function<Collection<String>, ImmutableMultiset<String>>() {
/*     */           public ImmutableMultiset<String> apply(Collection<String> input) {
/* 378 */             return ImmutableMultiset.copyOf(input);
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   public Optional<Charset> charset() {
/* 392 */     ImmutableSet<String> charsetValues = ImmutableSet.copyOf((Collection)this.parameters.get("charset"));
/* 393 */     switch (charsetValues.size()) {
/*     */       case 0:
/* 395 */         return Optional.absent();
/*     */       case 1:
/* 397 */         return Optional.of(Charset.forName((String)Iterables.getOnlyElement((Iterable)charsetValues)));
/*     */     } 
/* 399 */     throw new IllegalStateException("Multiple charset values defined: " + charsetValues);
/*     */   }
/*     */   
/*     */   public MediaType withoutParameters() {
/* 408 */     return this.parameters.isEmpty() ? this : create(this.type, this.subtype);
/*     */   }
/*     */   
/*     */   public MediaType withParameters(Multimap<String, String> parameters) {
/* 417 */     return create(this.type, this.subtype, parameters);
/*     */   }
/*     */   
/*     */   public MediaType withParameter(String attribute, String value) {
/* 429 */     Preconditions.checkNotNull(attribute);
/* 430 */     Preconditions.checkNotNull(value);
/* 431 */     String normalizedAttribute = normalizeToken(attribute);
/* 432 */     ImmutableListMultimap.Builder<String, String> builder = ImmutableListMultimap.builder();
/* 433 */     for (Map.Entry<String, String> entry : (Iterable<Map.Entry<String, String>>)this.parameters.entries()) {
/* 434 */       String key = entry.getKey();
/* 435 */       if (!normalizedAttribute.equals(key))
/* 436 */         builder.put(key, entry.getValue()); 
/*     */     } 
/* 439 */     builder.put(normalizedAttribute, normalizeParameterValue(normalizedAttribute, value));
/* 440 */     MediaType mediaType = new MediaType(this.type, this.subtype, builder.build());
/* 442 */     return (MediaType)Objects.firstNonNull(KNOWN_TYPES.get(mediaType), mediaType);
/*     */   }
/*     */   
/*     */   public MediaType withCharset(Charset charset) {
/* 455 */     Preconditions.checkNotNull(charset);
/* 456 */     return withParameter("charset", charset.name());
/*     */   }
/*     */   
/*     */   public boolean hasWildcard() {
/* 461 */     return ("*".equals(this.type) || "*".equals(this.subtype));
/*     */   }
/*     */   
/*     */   public boolean is(MediaType mediaTypeRange) {
/* 491 */     return ((mediaTypeRange.type.equals("*") || mediaTypeRange.type.equals(this.type)) && (mediaTypeRange.subtype.equals("*") || mediaTypeRange.subtype.equals(this.subtype)) && this.parameters.entries().containsAll((Collection)mediaTypeRange.parameters.entries()));
/*     */   }
/*     */   
/*     */   public static MediaType create(String type, String subtype) {
/* 503 */     return create(type, subtype, (Multimap<String, String>)ImmutableListMultimap.of());
/*     */   }
/*     */   
/*     */   static MediaType createApplicationType(String subtype) {
/* 512 */     return create("application", subtype);
/*     */   }
/*     */   
/*     */   static MediaType createAudioType(String subtype) {
/* 521 */     return create("audio", subtype);
/*     */   }
/*     */   
/*     */   static MediaType createImageType(String subtype) {
/* 530 */     return create("image", subtype);
/*     */   }
/*     */   
/*     */   static MediaType createTextType(String subtype) {
/* 539 */     return create("text", subtype);
/*     */   }
/*     */   
/*     */   static MediaType createVideoType(String subtype) {
/* 548 */     return create("video", subtype);
/*     */   }
/*     */   
/*     */   private static MediaType create(String type, String subtype, Multimap<String, String> parameters) {
/* 553 */     Preconditions.checkNotNull(type);
/* 554 */     Preconditions.checkNotNull(subtype);
/* 555 */     Preconditions.checkNotNull(parameters);
/* 556 */     String normalizedType = normalizeToken(type);
/* 557 */     String normalizedSubtype = normalizeToken(subtype);
/* 558 */     Preconditions.checkArgument((!"*".equals(normalizedType) || "*".equals(normalizedSubtype)), "A wildcard type cannot be used with a non-wildcard subtype");
/* 560 */     ImmutableListMultimap.Builder<String, String> builder = ImmutableListMultimap.builder();
/* 561 */     for (Map.Entry<String, String> entry : (Iterable<Map.Entry<String, String>>)parameters.entries()) {
/* 562 */       String attribute = normalizeToken(entry.getKey());
/* 563 */       builder.put(attribute, normalizeParameterValue(attribute, entry.getValue()));
/*     */     } 
/* 565 */     MediaType mediaType = new MediaType(normalizedType, normalizedSubtype, builder.build());
/* 567 */     return (MediaType)Objects.firstNonNull(KNOWN_TYPES.get(mediaType), mediaType);
/*     */   }
/*     */   
/*     */   private static String normalizeToken(String token) {
/* 571 */     Preconditions.checkArgument(TOKEN_MATCHER.matchesAllOf(token));
/* 572 */     return Ascii.toLowerCase(token);
/*     */   }
/*     */   
/*     */   private static String normalizeParameterValue(String attribute, String value) {
/* 576 */     return "charset".equals(attribute) ? Ascii.toLowerCase(value) : value;
/*     */   }
/*     */   
/*     */   public static MediaType parse(String input) {
/* 585 */     Preconditions.checkNotNull(input);
/* 586 */     Tokenizer tokenizer = new Tokenizer(input);
/*     */     try {
/* 588 */       String type = tokenizer.consumeToken(TOKEN_MATCHER);
/* 589 */       tokenizer.consumeCharacter('/');
/* 590 */       String subtype = tokenizer.consumeToken(TOKEN_MATCHER);
/* 591 */       ImmutableListMultimap.Builder<String, String> parameters = ImmutableListMultimap.builder();
/* 592 */       while (tokenizer.hasMore()) {
/*     */         String value;
/* 593 */         tokenizer.consumeCharacter(';');
/* 594 */         tokenizer.consumeTokenIfPresent(LINEAR_WHITE_SPACE);
/* 595 */         String attribute = tokenizer.consumeToken(TOKEN_MATCHER);
/* 596 */         tokenizer.consumeCharacter('=');
/* 598 */         if ('"' == tokenizer.previewChar()) {
/* 599 */           tokenizer.consumeCharacter('"');
/* 600 */           StringBuilder valueBuilder = new StringBuilder();
/* 601 */           while ('"' != tokenizer.previewChar()) {
/* 602 */             if ('\\' == tokenizer.previewChar()) {
/* 603 */               tokenizer.consumeCharacter('\\');
/* 604 */               valueBuilder.append(tokenizer.consumeCharacter(CharMatcher.ASCII));
/*     */               continue;
/*     */             } 
/* 606 */             valueBuilder.append(tokenizer.consumeToken(QUOTED_TEXT_MATCHER));
/*     */           } 
/* 609 */           value = valueBuilder.toString();
/* 610 */           tokenizer.consumeCharacter('"');
/*     */         } else {
/* 612 */           value = tokenizer.consumeToken(TOKEN_MATCHER);
/*     */         } 
/* 614 */         parameters.put(attribute, value);
/*     */       } 
/* 616 */       return create(type, subtype, (Multimap<String, String>)parameters.build());
/* 617 */     } catch (IllegalStateException e) {
/* 618 */       throw new IllegalArgumentException("Could not parse '" + input + "'", e);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static final class Tokenizer {
/*     */     final String input;
/*     */     
/* 624 */     int position = 0;
/*     */     
/*     */     Tokenizer(String input) {
/* 627 */       this.input = input;
/*     */     }
/*     */     
/*     */     String consumeTokenIfPresent(CharMatcher matcher) {
/* 631 */       Preconditions.checkState(hasMore());
/* 632 */       int startPosition = this.position;
/* 633 */       this.position = matcher.negate().indexIn(this.input, startPosition);
/* 634 */       return hasMore() ? this.input.substring(startPosition, this.position) : this.input.substring(startPosition);
/*     */     }
/*     */     
/*     */     String consumeToken(CharMatcher matcher) {
/* 638 */       int startPosition = this.position;
/* 639 */       String token = consumeTokenIfPresent(matcher);
/* 640 */       Preconditions.checkState((this.position != startPosition));
/* 641 */       return token;
/*     */     }
/*     */     
/*     */     char consumeCharacter(CharMatcher matcher) {
/* 645 */       Preconditions.checkState(hasMore());
/* 646 */       char c = previewChar();
/* 647 */       Preconditions.checkState(matcher.matches(c));
/* 648 */       this.position++;
/* 649 */       return c;
/*     */     }
/*     */     
/*     */     char consumeCharacter(char c) {
/* 653 */       Preconditions.checkState(hasMore());
/* 654 */       Preconditions.checkState((previewChar() == c));
/* 655 */       this.position++;
/* 656 */       return c;
/*     */     }
/*     */     
/*     */     char previewChar() {
/* 660 */       Preconditions.checkState(hasMore());
/* 661 */       return this.input.charAt(this.position);
/*     */     }
/*     */     
/*     */     boolean hasMore() {
/* 665 */       return (this.position >= 0 && this.position < this.input.length());
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean equals(@Nullable Object obj) {
/* 670 */     if (obj == this)
/* 671 */       return true; 
/* 672 */     if (obj instanceof MediaType) {
/* 673 */       MediaType that = (MediaType)obj;
/* 674 */       return (this.type.equals(that.type) && this.subtype.equals(that.subtype) && parametersAsMap().equals(that.parametersAsMap()));
/*     */     } 
/* 679 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 684 */     return Objects.hashCode(new Object[] { this.type, this.subtype, parametersAsMap() });
/*     */   }
/*     */   
/* 687 */   private static final Joiner.MapJoiner PARAMETER_JOINER = Joiner.on("; ").withKeyValueSeparator("=");
/*     */   
/*     */   public String toString() {
/* 694 */     StringBuilder builder = (new StringBuilder()).append(this.type).append('/').append(this.subtype);
/* 695 */     if (!this.parameters.isEmpty()) {
/* 696 */       builder.append("; ");
/* 697 */       ListMultimap listMultimap = Multimaps.transformValues((ListMultimap)this.parameters, new Function<String, String>() {
/*     */             public String apply(String value) {
/* 700 */               return MediaType.TOKEN_MATCHER.matchesAllOf(value) ? value : MediaType.escapeAndQuote(value);
/*     */             }
/*     */           });
/* 703 */       PARAMETER_JOINER.appendTo(builder, listMultimap.entries());
/*     */     } 
/* 705 */     return builder.toString();
/*     */   }
/*     */   
/*     */   private static String escapeAndQuote(String value) {
/* 709 */     StringBuilder escaped = (new StringBuilder(value.length() + 16)).append('"');
/* 710 */     for (char ch : value.toCharArray()) {
/* 711 */       if (ch == '\r' || ch == '\\' || ch == '"')
/* 712 */         escaped.append('\\'); 
/* 714 */       escaped.append(ch);
/*     */     } 
/* 716 */     return escaped.append('"').toString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\google\common\net\MediaType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */