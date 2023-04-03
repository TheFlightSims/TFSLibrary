/*     */ package scala.util.parsing.combinator;
/*     */ 
/*     */ import java.io.Reader;
/*     */ import scala.Function0;
/*     */ import scala.MatchError;
/*     */ import scala.None$;
/*     */ import scala.Option;
/*     */ import scala.Predef$;
/*     */ import scala.Some;
/*     */ import scala.collection.immutable.PagedSeq$;
/*     */ import scala.collection.immutable.StringOps;
/*     */ import scala.util.matching.Regex;
/*     */ import scala.util.parsing.input.CharSequenceReader;
/*     */ import scala.util.parsing.input.PagedSeqReader;
/*     */ import scala.util.parsing.input.Positional;
/*     */ import scala.util.parsing.input.Reader;
/*     */ 
/*     */ public abstract class RegexParsers$class {
/*     */   public static void $init$(RegexParsers $this) {
/*  60 */     Predef$ predef$ = Predef$.MODULE$;
/*  60 */     $this.scala$util$parsing$combinator$RegexParsers$_setter_$whiteSpace_$eq((new StringOps("\\s+")).r());
/*     */   }
/*     */   
/*     */   public static boolean skipWhitespace(RegexParsers $this) {
/*  62 */     return ($this.whiteSpace().toString().length() > 0);
/*     */   }
/*     */   
/*     */   public static int handleWhiteSpace(RegexParsers $this, CharSequence source, int offset) {
/*     */     int i;
/*  74 */     if ($this.skipWhitespace()) {
/*  75 */       Option option = $this.whiteSpace().findPrefixMatchOf(source.subSequence(offset, source.length()));
/*  76 */       if (option instanceof Some) {
/*  76 */         Some some = (Some)option;
/*  76 */         int j = offset + ((Regex.Match)some.x()).end();
/*     */       } 
/*  77 */       if (None$.MODULE$ == null) {
/*  77 */         if (option != null)
/*     */           throw new MatchError(option); 
/*  77 */       } else if (!None$.MODULE$.equals(option)) {
/*     */         throw new MatchError(option);
/*     */       } 
/*  77 */       i = offset;
/*     */     } else {
/*  80 */       return offset;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static Parsers.Parser literal(RegexParsers $this, String s) {
/*  83 */     return new RegexParsers$$anon$1($this, s);
/*     */   }
/*     */   
/*     */   public static Parsers.Parser regex(RegexParsers $this, Regex r) {
/* 104 */     return new RegexParsers$$anon$2($this, r);
/*     */   }
/*     */   
/*     */   public static Parsers.Parser positioned(RegexParsers $this, Function0<Parsers.Parser<Positional>> p) {
/* 129 */     Parsers.Parser<Positional> pp = $this.scala$util$parsing$combinator$RegexParsers$$super$positioned(p);
/* 130 */     return new RegexParsers$$anon$3($this, pp);
/*     */   }
/*     */   
/*     */   public static Parsers.Parser phrase(RegexParsers $this, Parsers.Parser p) {
/* 140 */     return $this.scala$util$parsing$combinator$RegexParsers$$super$phrase(p.$less$tilde((Function0)new RegexParsers$$anonfun$phrase$1($this)));
/*     */   }
/*     */   
/*     */   public static Parsers.ParseResult parse(RegexParsers $this, Parsers.Parser p, Reader in) {
/* 144 */     return p.apply(in);
/*     */   }
/*     */   
/*     */   public static Parsers.ParseResult parse(RegexParsers $this, Parsers.Parser p, CharSequence in) {
/* 148 */     return p.apply((Reader)new CharSequenceReader(in));
/*     */   }
/*     */   
/*     */   public static Parsers.ParseResult parse(RegexParsers $this, Parsers.Parser p, Reader in) {
/* 152 */     return p.apply((Reader)new PagedSeqReader(PagedSeq$.MODULE$.fromReader(in)));
/*     */   }
/*     */   
/*     */   public static Parsers.ParseResult parseAll(RegexParsers $this, Parsers.Parser<?> p, Reader in) {
/* 156 */     return $this.parse($this.phrase(p), in);
/*     */   }
/*     */   
/*     */   public static Parsers.ParseResult parseAll(RegexParsers $this, Parsers.Parser<?> p, Reader in) {
/* 160 */     return $this.parse($this.phrase(p), in);
/*     */   }
/*     */   
/*     */   public static Parsers.ParseResult parseAll(RegexParsers $this, Parsers.Parser<?> p, CharSequence in) {
/* 164 */     return $this.parse($this.phrase(p), in);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\parsing\combinator\RegexParsers$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */