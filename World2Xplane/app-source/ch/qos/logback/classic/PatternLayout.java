/*     */ package ch.qos.logback.classic;
/*     */ 
/*     */ import ch.qos.logback.classic.pattern.CallerDataConverter;
/*     */ import ch.qos.logback.classic.pattern.ClassOfCallerConverter;
/*     */ import ch.qos.logback.classic.pattern.ContextNameConverter;
/*     */ import ch.qos.logback.classic.pattern.DateConverter;
/*     */ import ch.qos.logback.classic.pattern.ExtendedThrowableProxyConverter;
/*     */ import ch.qos.logback.classic.pattern.FileOfCallerConverter;
/*     */ import ch.qos.logback.classic.pattern.LevelConverter;
/*     */ import ch.qos.logback.classic.pattern.LineOfCallerConverter;
/*     */ import ch.qos.logback.classic.pattern.LineSeparatorConverter;
/*     */ import ch.qos.logback.classic.pattern.LoggerConverter;
/*     */ import ch.qos.logback.classic.pattern.MDCConverter;
/*     */ import ch.qos.logback.classic.pattern.MarkerConverter;
/*     */ import ch.qos.logback.classic.pattern.MessageConverter;
/*     */ import ch.qos.logback.classic.pattern.MethodOfCallerConverter;
/*     */ import ch.qos.logback.classic.pattern.NopThrowableInformationConverter;
/*     */ import ch.qos.logback.classic.pattern.PropertyConverter;
/*     */ import ch.qos.logback.classic.pattern.RelativeTimeConverter;
/*     */ import ch.qos.logback.classic.pattern.RootCauseFirstThrowableProxyConverter;
/*     */ import ch.qos.logback.classic.pattern.ThreadConverter;
/*     */ import ch.qos.logback.classic.pattern.ThrowableProxyConverter;
/*     */ import ch.qos.logback.classic.pattern.color.HighlightingCompositeConverter;
/*     */ import ch.qos.logback.classic.spi.ILoggingEvent;
/*     */ import ch.qos.logback.core.pattern.PatternLayoutBase;
/*     */ import ch.qos.logback.core.pattern.color.BlackCompositeConverter;
/*     */ import ch.qos.logback.core.pattern.color.BlueCompositeConverter;
/*     */ import ch.qos.logback.core.pattern.color.BoldBlueCompositeConverter;
/*     */ import ch.qos.logback.core.pattern.color.BoldCyanCompositeConverter;
/*     */ import ch.qos.logback.core.pattern.color.BoldGreenCompositeConverter;
/*     */ import ch.qos.logback.core.pattern.color.BoldMagentaCompositeConverter;
/*     */ import ch.qos.logback.core.pattern.color.BoldRedCompositeConverter;
/*     */ import ch.qos.logback.core.pattern.color.BoldWhiteCompositeConverter;
/*     */ import ch.qos.logback.core.pattern.color.BoldYellowCompositeConverter;
/*     */ import ch.qos.logback.core.pattern.color.CyanCompositeConverter;
/*     */ import ch.qos.logback.core.pattern.color.GrayCompositeConverter;
/*     */ import ch.qos.logback.core.pattern.color.GreenCompositeConverter;
/*     */ import ch.qos.logback.core.pattern.color.MagentaCompositeConverter;
/*     */ import ch.qos.logback.core.pattern.color.RedCompositeConverter;
/*     */ import ch.qos.logback.core.pattern.color.WhiteCompositeConverter;
/*     */ import ch.qos.logback.core.pattern.color.YellowCompositeConverter;
/*     */ import ch.qos.logback.core.pattern.parser.Parser;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class PatternLayout extends PatternLayoutBase<ILoggingEvent> {
/*  61 */   public static final Map<String, String> defaultConverterMap = new HashMap<String, String>();
/*     */   
/*     */   public static final String HEADER_PREFIX = "#logback.classic pattern: ";
/*     */   
/*     */   static {
/*  65 */     defaultConverterMap.putAll(Parser.DEFAULT_COMPOSITE_CONVERTER_MAP);
/*  67 */     defaultConverterMap.put("d", DateConverter.class.getName());
/*  68 */     defaultConverterMap.put("date", DateConverter.class.getName());
/*  70 */     defaultConverterMap.put("r", RelativeTimeConverter.class.getName());
/*  71 */     defaultConverterMap.put("relative", RelativeTimeConverter.class.getName());
/*  73 */     defaultConverterMap.put("level", LevelConverter.class.getName());
/*  74 */     defaultConverterMap.put("le", LevelConverter.class.getName());
/*  75 */     defaultConverterMap.put("p", LevelConverter.class.getName());
/*  77 */     defaultConverterMap.put("t", ThreadConverter.class.getName());
/*  78 */     defaultConverterMap.put("thread", ThreadConverter.class.getName());
/*  80 */     defaultConverterMap.put("lo", LoggerConverter.class.getName());
/*  81 */     defaultConverterMap.put("logger", LoggerConverter.class.getName());
/*  82 */     defaultConverterMap.put("c", LoggerConverter.class.getName());
/*  84 */     defaultConverterMap.put("m", MessageConverter.class.getName());
/*  85 */     defaultConverterMap.put("msg", MessageConverter.class.getName());
/*  86 */     defaultConverterMap.put("message", MessageConverter.class.getName());
/*  88 */     defaultConverterMap.put("C", ClassOfCallerConverter.class.getName());
/*  89 */     defaultConverterMap.put("class", ClassOfCallerConverter.class.getName());
/*  91 */     defaultConverterMap.put("M", MethodOfCallerConverter.class.getName());
/*  92 */     defaultConverterMap.put("method", MethodOfCallerConverter.class.getName());
/*  94 */     defaultConverterMap.put("L", LineOfCallerConverter.class.getName());
/*  95 */     defaultConverterMap.put("line", LineOfCallerConverter.class.getName());
/*  97 */     defaultConverterMap.put("F", FileOfCallerConverter.class.getName());
/*  98 */     defaultConverterMap.put("file", FileOfCallerConverter.class.getName());
/* 100 */     defaultConverterMap.put("X", MDCConverter.class.getName());
/* 101 */     defaultConverterMap.put("mdc", MDCConverter.class.getName());
/* 103 */     defaultConverterMap.put("ex", ThrowableProxyConverter.class.getName());
/* 104 */     defaultConverterMap.put("exception", ThrowableProxyConverter.class.getName());
/* 106 */     defaultConverterMap.put("rEx", RootCauseFirstThrowableProxyConverter.class.getName());
/* 107 */     defaultConverterMap.put("rootException", RootCauseFirstThrowableProxyConverter.class.getName());
/* 109 */     defaultConverterMap.put("throwable", ThrowableProxyConverter.class.getName());
/* 112 */     defaultConverterMap.put("xEx", ExtendedThrowableProxyConverter.class.getName());
/* 113 */     defaultConverterMap.put("xException", ExtendedThrowableProxyConverter.class.getName());
/* 115 */     defaultConverterMap.put("xThrowable", ExtendedThrowableProxyConverter.class.getName());
/* 118 */     defaultConverterMap.put("nopex", NopThrowableInformationConverter.class.getName());
/* 120 */     defaultConverterMap.put("nopexception", NopThrowableInformationConverter.class.getName());
/* 123 */     defaultConverterMap.put("cn", ContextNameConverter.class.getName());
/* 124 */     defaultConverterMap.put("contextName", ContextNameConverter.class.getName());
/* 126 */     defaultConverterMap.put("caller", CallerDataConverter.class.getName());
/* 128 */     defaultConverterMap.put("marker", MarkerConverter.class.getName());
/* 130 */     defaultConverterMap.put("property", PropertyConverter.class.getName());
/* 132 */     defaultConverterMap.put("n", LineSeparatorConverter.class.getName());
/* 134 */     defaultConverterMap.put("black", BlackCompositeConverter.class.getName());
/* 135 */     defaultConverterMap.put("red", RedCompositeConverter.class.getName());
/* 136 */     defaultConverterMap.put("green", GreenCompositeConverter.class.getName());
/* 137 */     defaultConverterMap.put("yellow", YellowCompositeConverter.class.getName());
/* 138 */     defaultConverterMap.put("blue", BlueCompositeConverter.class.getName());
/* 139 */     defaultConverterMap.put("magenta", MagentaCompositeConverter.class.getName());
/* 140 */     defaultConverterMap.put("cyan", CyanCompositeConverter.class.getName());
/* 141 */     defaultConverterMap.put("white", WhiteCompositeConverter.class.getName());
/* 142 */     defaultConverterMap.put("gray", GrayCompositeConverter.class.getName());
/* 143 */     defaultConverterMap.put("boldRed", BoldRedCompositeConverter.class.getName());
/* 144 */     defaultConverterMap.put("boldGreen", BoldGreenCompositeConverter.class.getName());
/* 145 */     defaultConverterMap.put("boldYellow", BoldYellowCompositeConverter.class.getName());
/* 146 */     defaultConverterMap.put("boldBlue", BoldBlueCompositeConverter.class.getName());
/* 147 */     defaultConverterMap.put("boldMagenta", BoldMagentaCompositeConverter.class.getName());
/* 148 */     defaultConverterMap.put("boldCyan", BoldCyanCompositeConverter.class.getName());
/* 149 */     defaultConverterMap.put("boldWhite", BoldWhiteCompositeConverter.class.getName());
/* 150 */     defaultConverterMap.put("highlight", HighlightingCompositeConverter.class.getName());
/*     */   }
/*     */   
/*     */   public Map<String, String> getDefaultConverterMap() {
/* 161 */     return defaultConverterMap;
/*     */   }
/*     */   
/*     */   public String doLayout(ILoggingEvent event) {
/* 165 */     if (!isStarted())
/* 166 */       return ""; 
/* 168 */     return writeLoopOnConverters(event);
/*     */   }
/*     */   
/*     */   protected String getPresentationHeaderPrefix() {
/* 173 */     return "#logback.classic pattern: ";
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\classic\PatternLayout.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */