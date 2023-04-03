/*    */ package org.postgresql.core;
/*    */ 
/*    */ import org.postgresql.PGNotification;
/*    */ 
/*    */ public class Notification implements PGNotification {
/*    */   private String m_name;
/*    */   
/*    */   private String m_parameter;
/*    */   
/*    */   private int m_pid;
/*    */   
/*    */   public Notification(String p_name, int p_pid) {
/* 18 */     this(p_name, p_pid, "");
/*    */   }
/*    */   
/*    */   public Notification(String p_name, int p_pid, String p_parameter) {
/* 23 */     this.m_name = p_name;
/* 24 */     this.m_pid = p_pid;
/* 25 */     this.m_parameter = p_parameter;
/*    */   }
/*    */   
/*    */   public String getName() {
/* 33 */     return this.m_name;
/*    */   }
/*    */   
/*    */   public int getPID() {
/* 41 */     return this.m_pid;
/*    */   }
/*    */   
/*    */   public String getParameter() {
/* 46 */     return this.m_parameter;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\core\Notification.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */