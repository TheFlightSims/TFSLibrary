/*     */ package ch.qos.logback.core.pattern.parser;
/*     */ 
/*     */ import ch.qos.logback.core.pattern.CompositeConverter;
/*     */ import ch.qos.logback.core.pattern.Converter;
/*     */ import ch.qos.logback.core.pattern.DynamicConverter;
/*     */ import ch.qos.logback.core.pattern.LiteralConverter;
/*     */ import ch.qos.logback.core.spi.ContextAwareBase;
/*     */ import ch.qos.logback.core.status.ErrorStatus;
/*     */ import ch.qos.logback.core.status.Status;
/*     */ import ch.qos.logback.core.util.OptionHelper;
/*     */ import java.util.Map;
/*     */ 
/*     */ class Compiler<E> extends ContextAwareBase {
/*     */   Converter<E> head;
/*     */   
/*     */   Converter<E> tail;
/*     */   
/*     */   final Node top;
/*     */   
/*     */   final Map converterMap;
/*     */   
/*     */   Compiler(Node top, Map converterMap) {
/*  34 */     this.top = top;
/*  35 */     this.converterMap = converterMap;
/*     */   }
/*     */   
/*     */   Converter<E> compile() {
/*  39 */     this.head = this.tail = null;
/*  40 */     for (Node n = this.top; n != null; n = n.next) {
/*     */       CompositeNode cn;
/*     */       CompositeConverter<E> compositeConverter;
/*     */       Compiler<E> childCompiler;
/*     */       Converter<E> childConverter;
/*     */       SimpleKeywordNode kn;
/*     */       DynamicConverter<E> dynaConverter;
/*     */       LiteralConverter literalConverter;
/*  41 */       switch (n.type) {
/*     */         case 0:
/*  43 */           addToList((Converter<E>)new LiteralConverter((String)n.getValue()));
/*     */           break;
/*     */         case 2:
/*  46 */           cn = (CompositeNode)n;
/*  47 */           compositeConverter = createCompositeConverter(cn);
/*  48 */           if (compositeConverter == null) {
/*  49 */             addError("Failed to create converter for [%" + cn.getValue() + "] keyword");
/*  50 */             addToList((Converter<E>)new LiteralConverter("%PARSER_ERROR[" + cn.getValue() + "]"));
/*     */             break;
/*     */           } 
/*  53 */           compositeConverter.setFormattingInfo(cn.getFormatInfo());
/*  54 */           compositeConverter.setOptionList(cn.getOptions());
/*  55 */           childCompiler = new Compiler(cn.getChildNode(), this.converterMap);
/*  57 */           childCompiler.setContext(this.context);
/*  58 */           childConverter = childCompiler.compile();
/*  59 */           compositeConverter.setChildConverter(childConverter);
/*  60 */           addToList((Converter<E>)compositeConverter);
/*     */           break;
/*     */         case 1:
/*  63 */           kn = (SimpleKeywordNode)n;
/*  64 */           dynaConverter = createConverter(kn);
/*  65 */           if (dynaConverter != null) {
/*  66 */             dynaConverter.setFormattingInfo(kn.getFormatInfo());
/*  67 */             dynaConverter.setOptionList(kn.getOptions());
/*  68 */             addToList((Converter<E>)dynaConverter);
/*     */             break;
/*     */           } 
/*  72 */           literalConverter = new LiteralConverter("%PARSER_ERROR[" + kn.getValue() + "]");
/*  74 */           addStatus((Status)new ErrorStatus("[" + kn.getValue() + "] is not a valid conversion word", this));
/*  76 */           addToList((Converter<E>)literalConverter);
/*     */           break;
/*     */       } 
/*     */     } 
/*  81 */     return this.head;
/*     */   }
/*     */   
/*     */   private void addToList(Converter<E> c) {
/*  85 */     if (this.head == null) {
/*  86 */       this.head = this.tail = c;
/*     */     } else {
/*  88 */       this.tail.setNext(c);
/*  89 */       this.tail = c;
/*     */     } 
/*     */   }
/*     */   
/*     */   DynamicConverter<E> createConverter(SimpleKeywordNode kn) {
/* 102 */     String keyword = (String)kn.getValue();
/* 103 */     String converterClassStr = (String)this.converterMap.get(keyword);
/* 105 */     if (converterClassStr != null)
/*     */       try {
/* 107 */         return (DynamicConverter<E>)OptionHelper.instantiateByClassName(converterClassStr, DynamicConverter.class, this.context);
/* 109 */       } catch (Exception e) {
/* 110 */         addError("Failed to instantiate converter class [" + converterClassStr + "] for keyword [" + keyword + "]", e);
/* 112 */         return null;
/*     */       }  
/* 115 */     addError("There is no conversion class registered for conversion word [" + keyword + "]");
/* 117 */     return null;
/*     */   }
/*     */   
/*     */   CompositeConverter<E> createCompositeConverter(CompositeNode cn) {
/* 130 */     String keyword = (String)cn.getValue();
/* 131 */     String converterClassStr = (String)this.converterMap.get(keyword);
/* 133 */     if (converterClassStr != null)
/*     */       try {
/* 135 */         return (CompositeConverter<E>)OptionHelper.instantiateByClassName(converterClassStr, CompositeConverter.class, this.context);
/* 137 */       } catch (Exception e) {
/* 138 */         addError("Failed to instantiate converter class [" + converterClassStr + "] as a composite converter for keyword [" + keyword + "]", e);
/* 140 */         return null;
/*     */       }  
/* 143 */     addError("There is no conversion class registered for composite conversion word [" + keyword + "]");
/* 145 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\core\pattern\parser\Compiler.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */