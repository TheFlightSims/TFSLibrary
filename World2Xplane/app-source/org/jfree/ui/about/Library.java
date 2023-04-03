/*    */ package org.jfree.ui.about;
/*    */ 
/*    */ import org.jfree.base.Library;
/*    */ 
/*    */ public class Library extends Library {
/*    */   public Library(String name, String version, String licence, String info) {
/* 63 */     super(name, version, licence, info);
/*    */   }
/*    */   
/*    */   public Library(ProjectInfo project) {
/* 73 */     this(project.getName(), project.getVersion(), project.getLicenceName(), project.getInfo());
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfre\\ui\about\Library.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */