/*    */ package org.jfree.ui;
/*    */ 
/*    */ import java.awt.event.WindowEvent;
/*    */ import java.awt.event.WindowListener;
/*    */ import javax.swing.JFrame;
/*    */ 
/*    */ public class ApplicationFrame extends JFrame implements WindowListener {
/*    */   public ApplicationFrame(String title) {
/* 66 */     super(title);
/* 67 */     addWindowListener(this);
/*    */   }
/*    */   
/*    */   public void windowClosing(WindowEvent event) {
/* 76 */     if (event.getWindow() == this) {
/* 77 */       dispose();
/* 78 */       System.exit(0);
/*    */     } 
/*    */   }
/*    */   
/*    */   public void windowClosed(WindowEvent event) {}
/*    */   
/*    */   public void windowActivated(WindowEvent event) {}
/*    */   
/*    */   public void windowDeactivated(WindowEvent event) {}
/*    */   
/*    */   public void windowDeiconified(WindowEvent event) {}
/*    */   
/*    */   public void windowIconified(WindowEvent event) {}
/*    */   
/*    */   public void windowOpened(WindowEvent event) {}
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfre\\ui\ApplicationFrame.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */