/*    */ package com.world2xplane.GUI;
/*    */ 
/*    */ import java.awt.BorderLayout;
/*    */ import java.awt.Dimension;
/*    */ import java.awt.Toolkit;
/*    */ import javax.swing.BoxLayout;
/*    */ import javax.swing.JLabel;
/*    */ import javax.swing.JPanel;
/*    */ import javax.swing.border.BevelBorder;
/*    */ 
/*    */ public class MainWindow extends JPanel {
/* 31 */   JPanel statusPanel = new JPanel();
/*    */   
/*    */   public void MainWindow() {
/* 36 */     setLayout(new BorderLayout());
/* 37 */     Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
/* 38 */     Dimension frameSize = new Dimension(screenSize.width / 2, screenSize.height / 2);
/* 39 */     int x = frameSize.width / 2;
/* 40 */     int y = frameSize.height / 2;
/* 41 */     setBounds(x, y, frameSize.width, frameSize.height);
/* 44 */     this.statusPanel.setBorder(new BevelBorder(1));
/* 45 */     add(this.statusPanel, "South");
/* 46 */     this.statusPanel.setPreferredSize(new Dimension(getWidth(), 16));
/* 47 */     this.statusPanel.setLayout(new BoxLayout(this.statusPanel, 0));
/* 48 */     JLabel statusLabel = new JLabel("status");
/* 49 */     statusLabel.setHorizontalAlignment(2);
/* 50 */     this.statusPanel.add(statusLabel);
/* 52 */     setVisible(true);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\GUI\MainWindow.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */