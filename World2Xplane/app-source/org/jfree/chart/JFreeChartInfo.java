/*      */ package org.jfree.chart;
/*      */ 
/*      */ import java.awt.Image;
/*      */ import java.net.URL;
/*      */ import java.util.Arrays;
/*      */ import java.util.ResourceBundle;
/*      */ import javax.swing.ImageIcon;
/*      */ import org.jfree.JCommon;
/*      */ import org.jfree.base.Library;
/*      */ import org.jfree.ui.about.Contributor;
/*      */ import org.jfree.ui.about.Licences;
/*      */ import org.jfree.ui.about.ProjectInfo;
/*      */ 
/*      */ class JFreeChartInfo extends ProjectInfo {
/*      */   public JFreeChartInfo() {
/* 1589 */     String baseResourceClass = "org.jfree.chart.resources.JFreeChartResources";
/* 1591 */     ResourceBundle resources = ResourceBundle.getBundle(baseResourceClass);
/* 1593 */     setName(resources.getString("project.name"));
/* 1594 */     setVersion(resources.getString("project.version"));
/* 1595 */     setInfo(resources.getString("project.info"));
/* 1596 */     setCopyright(resources.getString("project.copyright"));
/* 1597 */     setLogo(null);
/* 1598 */     setLicenceName("LGPL");
/* 1599 */     setLicenceText(Licences.getInstance().getLGPL());
/* 1601 */     setContributors(Arrays.asList(new Contributor[] { 
/* 1601 */             new Contributor("Eric Alexander", "-"), new Contributor("Richard Atkinson", "richard_c_atkinson@ntlworld.com"), new Contributor("David Basten", "-"), new Contributor("David Berry", "-"), new Contributor("Anthony Boulestreau", "-"), new Contributor("Jeremy Bowman", "-"), new Contributor("Nicolas Brodu", "-"), new Contributor("David Browning", "-"), new Contributor("S???ren Caspersen", "-"), new Contributor("Chuanhao Chiu", "-"), 
/* 1601 */             new Contributor("Brian Cole", "-"), new Contributor("Pascal Collet", "-"), new Contributor("Martin Cordova", "-"), new Contributor("Paolo Cova", "-"), new Contributor("Mike Duffy", "-"), new Contributor("Don Elliott", "-"), new Contributor("Jonathan Gabbai", "-"), new Contributor("David Gilbert", "david.gilbert@object-refinery.com"), new Contributor("Serge V. Grachov", "-"), new Contributor("Daniel Gredler", "-"), 
/* 1601 */             new Contributor("Hans-Jurgen Greiner", "-"), new Contributor("Joao Guilherme Del Valle", "-"), new Contributor("Aiman Han", "-"), new Contributor("Cameron Hayne", "-"), new Contributor("Jon Iles", "-"), new Contributor("Wolfgang Irler", "-"), new Contributor("Xun Kang", "-"), new Contributor("Bill Kelemen", "-"), new Contributor("Norbert Kiesel", "-"), new Contributor("Gideon Krause", "-"), 
/* 1601 */             new Contributor("Pierre-Marie Le Biot", "-"), new Contributor("Arnaud Lelievre", "-"), new Contributor("Wolfgang Lenhard", "-"), new Contributor("David Li", "-"), new Contributor("Yan Liu", "-"), new Contributor("Tin Luu", "-"), new Contributor("Craig MacFarlane", "-"), new Contributor("Achilleus Mantzios", "-"), new Contributor("Thomas Meier", "-"), new Contributor("Jim Moore", "-"), 
/* 1601 */             new Contributor("Jonathan Nash", "-"), new Contributor("Barak Naveh", "-"), new Contributor("David M. O'Donnell", "-"), new Contributor("Krzysztof Paz", "-"), new Contributor("Tomer Peretz", "-"), new Contributor("Andrzej Porebski", "-"), new Contributor("Xavier Poinsard", "-"), new Contributor("Viktor Rajewski", "-"), new Contributor("Eduardo Ramalho", "-"), new Contributor("Michael Rauch", "-"), 
/* 1601 */             new Contributor("Cameron Riley", "-"), new Contributor("Dan Rivett", "d.rivett@ukonline.co.uk"), new Contributor("Scott Sams", "-"), new Contributor("Michel Santos", "-"), new Contributor("Thierry Saura", "-"), new Contributor("Andreas Schneider", "-"), new Contributor("Jean-Luc SCHWAB", "-"), new Contributor("Bryan Scott", "-"), new Contributor("Tobias Selb", "-"), new Contributor("Mofeed Shahin", "-"), 
/* 1601 */             new Contributor("Pady Srinivasan", "-"), new Contributor("Greg Steckman", "-"), new Contributor("Roger Studner", "-"), new Contributor("Irv Thomae", "-"), new Contributor("Eric Thomas", "-"), new Contributor("Rich Unger", "-"), new Contributor("Daniel van Enckevort", "-"), new Contributor("Laurence Vanhelsuwe", "-"), new Contributor("Sylvain Vieujot", "-"), new Contributor("Mark Watson", "www.markwatson.com"), 
/* 1601 */             new Contributor("Alex Weber", "-"), new Contributor("Matthew Wright", "-"), new Contributor("Benoit Xhenseval", "-"), new Contributor("Christian W. Zuckschwerdt", "Christian.Zuckschwerdt@Informatik.Uni-Oldenburg.de"), new Contributor("Hari", "-"), new Contributor("Sam (oldman)", "-") }));
/* 1689 */     addLibrary((Library)JCommon.INFO);
/*      */   }
/*      */   
/*      */   public Image getLogo() {
/* 1700 */     Image logo = super.getLogo();
/* 1701 */     if (logo == null) {
/* 1702 */       URL imageURL = ClassLoader.getSystemResource("org/jfree/chart/gorilla.jpg");
/* 1705 */       if (imageURL != null) {
/* 1706 */         ImageIcon temp = new ImageIcon(imageURL);
/* 1708 */         logo = temp.getImage();
/* 1709 */         setLogo(logo);
/*      */       } 
/*      */     } 
/* 1712 */     return logo;
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\JFreeChartInfo.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */