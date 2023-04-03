/*     */ package org.jfree;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ import java.util.ResourceBundle;
/*     */ import org.jfree.base.BaseBoot;
/*     */ import org.jfree.base.Library;
/*     */ import org.jfree.ui.about.Contributor;
/*     */ import org.jfree.ui.about.Licences;
/*     */ import org.jfree.ui.about.ProjectInfo;
/*     */ 
/*     */ public class JCommonInfo extends ProjectInfo {
/*     */   private static JCommonInfo singleton;
/*     */   
/*     */   public static synchronized JCommonInfo getInstance() {
/*  71 */     if (singleton == null)
/*  72 */       singleton = new JCommonInfo(); 
/*  74 */     return singleton;
/*     */   }
/*     */   
/*     */   private JCommonInfo() {
/*  83 */     String baseResourceClass = "org.jfree.resources.JCommonResources";
/*  84 */     ResourceBundle resources = ResourceBundle.getBundle("org.jfree.resources.JCommonResources");
/*  87 */     setName(resources.getString("project.name"));
/*  88 */     setVersion(resources.getString("project.version"));
/*  89 */     setInfo(resources.getString("project.info"));
/*  90 */     setCopyright(resources.getString("project.copyright"));
/*  92 */     setLicenceName("LGPL");
/*  93 */     setLicenceText(Licences.getInstance().getLGPL());
/*  95 */     setContributors(Arrays.asList(new Contributor[] { 
/*  95 */             new Contributor("Anthony Boulestreau", "-"), new Contributor("Jeremy Bowman", "-"), new Contributor("J. David Eisenberg", "-"), new Contributor("Paul English", "-"), new Contributor("David Gilbert", "david.gilbert@object-refinery.com"), new Contributor("Hans-Jurgen Greiner", "-"), new Contributor("Arik Levin", "-"), new Contributor("Achilleus Mantzios", "-"), new Contributor("Thomas Meier", "-"), new Contributor("Aaron Metzger", "-"), 
/*  95 */             new Contributor("Thomas Morgner", "-"), new Contributor("Krzysztof Paz", "-"), new Contributor("Nabuo Tamemasa", "-"), new Contributor("Mark Watson", "-"), new Contributor("Matthew Wright", "-"), new Contributor("Hari", "-"), new Contributor("Sam (oldman)", "-") }));
/* 119 */     addLibrary(new Library("JUnit", "3.8", "IBM Public Licence", "http://www.junit.org/"));
/* 125 */     setBootClass(BaseBoot.class.getName());
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\JCommonInfo.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */