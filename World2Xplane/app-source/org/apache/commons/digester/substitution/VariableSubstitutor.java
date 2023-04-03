/*     */ package org.apache.commons.digester.substitution;
/*     */ 
/*     */ import org.apache.commons.digester.Substitutor;
/*     */ import org.xml.sax.Attributes;
/*     */ 
/*     */ public class VariableSubstitutor extends Substitutor {
/*     */   private VariableExpander attributesExpander;
/*     */   
/*     */   private VariableAttributes variableAttributes;
/*     */   
/*     */   private VariableExpander bodyTextExpander;
/*     */   
/*     */   public VariableSubstitutor(VariableExpander expander) {
/*  62 */     this(expander, expander);
/*     */   }
/*     */   
/*     */   public VariableSubstitutor(VariableExpander attributesExpander, VariableExpander bodyTextExpander) {
/*  73 */     this.attributesExpander = attributesExpander;
/*  74 */     this.bodyTextExpander = bodyTextExpander;
/*  75 */     this.variableAttributes = new VariableAttributes();
/*     */   }
/*     */   
/*     */   public Attributes substitute(Attributes attributes) {
/*  83 */     Attributes results = attributes;
/*  84 */     if (this.attributesExpander != null) {
/*  85 */       this.variableAttributes.init(attributes, this.attributesExpander);
/*  86 */       results = this.variableAttributes;
/*     */     } 
/*  88 */     return results;
/*     */   }
/*     */   
/*     */   public String substitute(String bodyText) {
/* 100 */     String result = bodyText;
/* 101 */     if (this.bodyTextExpander != null)
/* 102 */       result = this.bodyTextExpander.expand(bodyText); 
/* 104 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\digester\substitution\VariableSubstitutor.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */