/*     */ package org.jdom;
/*     */ 
/*     */ public class IllegalAddException extends IllegalArgumentException {
/*     */   private static final String CVS_ID = "@(#) $RCSfile: IllegalAddException.java,v $ $Revision: 1.26 $ $Date: 2007/11/10 05:28:59 $ $Name:  $";
/*     */   
/*     */   IllegalAddException(Element base, Attribute added, String reason) {
/*  82 */     super("The attribute \"" + added.getQualifiedName() + "\" could not be added to the element \"" + base.getQualifiedName() + "\": " + reason);
/*     */   }
/*     */   
/*     */   IllegalAddException(Element base, Element added, String reason) {
/* 103 */     super("The element \"" + added.getQualifiedName() + "\" could not be added as a child of \"" + base.getQualifiedName() + "\": " + reason);
/*     */   }
/*     */   
/*     */   IllegalAddException(Element added, String reason) {
/* 122 */     super("The element \"" + added.getQualifiedName() + "\" could not be added as the root of the document: " + reason);
/*     */   }
/*     */   
/*     */   IllegalAddException(Element base, ProcessingInstruction added, String reason) {
/* 142 */     super("The PI \"" + added.getTarget() + "\" could not be added as content to \"" + base.getQualifiedName() + "\": " + reason);
/*     */   }
/*     */   
/*     */   IllegalAddException(ProcessingInstruction added, String reason) {
/* 162 */     super("The PI \"" + added.getTarget() + "\" could not be added to the top level of the document: " + reason);
/*     */   }
/*     */   
/*     */   IllegalAddException(Element base, Comment added, String reason) {
/* 181 */     super("The comment \"" + added.getText() + "\" could not be added as content to \"" + base.getQualifiedName() + "\": " + reason);
/*     */   }
/*     */   
/*     */   IllegalAddException(Element base, CDATA added, String reason) {
/* 202 */     super("The CDATA \"" + added.getText() + "\" could not be added as content to \"" + base.getQualifiedName() + "\": " + reason);
/*     */   }
/*     */   
/*     */   IllegalAddException(Element base, Text added, String reason) {
/* 224 */     super("The Text \"" + added.getText() + "\" could not be added as content to \"" + base.getQualifiedName() + "\": " + reason);
/*     */   }
/*     */   
/*     */   IllegalAddException(Comment added, String reason) {
/* 243 */     super("The comment \"" + added.getText() + "\" could not be added to the top level of the document: " + reason);
/*     */   }
/*     */   
/*     */   IllegalAddException(Element base, EntityRef added, String reason) {
/* 262 */     super("The entity reference\"" + added.getName() + "\" could not be added as content to \"" + base.getQualifiedName() + "\": " + reason);
/*     */   }
/*     */   
/*     */   IllegalAddException(Element base, Namespace added, String reason) {
/* 283 */     super("The namespace xmlns" + ((added.getPrefix() == null || added.getPrefix().equals("")) ? "=" : (":" + added.getPrefix() + "=")) + "\"" + added.getURI() + "\" could not be added as a namespace to \"" + base.getQualifiedName() + "\": " + reason);
/*     */   }
/*     */   
/*     */   IllegalAddException(DocType added, String reason) {
/* 306 */     super("The DOCTYPE " + added.toString() + " could not be added to the document: " + reason);
/*     */   }
/*     */   
/*     */   public IllegalAddException(String reason) {
/* 321 */     super(reason);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jdom\IllegalAddException.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */