package tn.esprit.b4.esprit1718b4erp.app.client.gui;
//
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.InputStream;
//import java.sql.Date;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.naming.Context;
//import javax.naming.InitialContext;
//import javax.naming.NamingException;
//import org.apache.poi.hssf.usermodel.HSSFCell;
//import org.apache.poi.hssf.usermodel.HSSFCellStyle;
//import org.apache.poi.hssf.usermodel.HSSFDataFormat;
//import org.apache.poi.hssf.usermodel.HSSFRow;
//import org.apache.poi.hssf.usermodel.HSSFSheet;
//import org.apache.poi.poifs.filesystem.POIFSFileSystem;
//import org.apache.poi.xssf.usermodel.XSSFCell;
//import org.apache.poi.xssf.usermodel.XSSFRow;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//
//import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import org.apache.poi.hssf.usermodel.HSSFCell;
//import org.apache.poi.hssf.usermodel.HSSFCellStyle;
//import org.apache.poi.hssf.usermodel.HSSFDataFormat;
//import org.apache.poi.hssf.usermodel.HSSFRow;
//import org.apache.poi.hssf.usermodel.HSSFSheet;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.poifs.filesystem.POIFSFileSystem;
//import tn.esprit.b4.esprit1718b4erp.entities.Cellule_excel;;
//
//
//public class Test {
//
//    @SuppressWarnings("rawTypes")
//    public static List traitement() throws FileNotFoundException, IOException {
//
//        InputStream inp = new FileInputStream("ERP_pointage.xls");
//
//// recupère le fichier excel
//       
//XSSFWorkbook wb = new XSSFWorkbook(inp);
//
//// Recupére page 1 du fichier xls
//        XSSFSheet sheet = wb.getSheetAt(0);
//
//// nombre de ligne
//        int nbLigneFichier1 = sheet.getPhysicalNumberOfRows();
//        System.out.println("nbre ligne");
//        System.out.println(nbLigneFichier1);
//        int nombreDeCelluleMax = 0;
//
//        for (int ligne = 0; ligne < nbLigneFichier1; ligne++) {
//            // recuperation de chaque ligne
//            XSSFRow row = sheet.getRow(ligne);
//
//            // si la ligne contient au moins une cellule
//            if (row != null) {
//                if (row.getPhysicalNumberOfCells() > nombreDeCelluleMax) {
//                    nombreDeCelluleMax = row.getPhysicalNumberOfCells();
//                }
//            }
//
//        }
//        int nbColFichier1 = nombreDeCelluleMax;
//        System.out.println("nbre colonne");
//        System.out.println(nbColFichier1);
//        nombreDeCelluleMax = 0;
//
//// creation du tableau qui va contenir les differentes cellules
//        String tabFichier1[][] = new String[nbLigneFichier1][nbColFichier1];
//        List l = new ArrayList();
//        for (int ligne = 1; ligne < nbLigneFichier1; ligne++) {
//            Cellule_excel c = new Cellule_excel();
//
//            XSSFRow row = sheet.getRow(ligne);
//            if (row != null) {
//
//                for (int colonne = 0; colonne < nbColFichier1; colonne++) {
//                    if (colonne == 0) {
//                        XSSFCell cellule = row.getCell(colonne);
//                        Object valeur = ContenuCellule(cellule);
//                        tabFichier1[ligne][colonne] = (valeur.toString());
//                         System.out.println(tabFichier1[ligne][colonne]); 
//                        c.setId_emp(tabFichier1[ligne][colonne]);
//
//                    }
//                    if (colonne == 1) {
//                        XSSFCell cellule = row.getCell(colonne);
//                        Object valeur = ContenuCellule(cellule);
//                        tabFichier1[ligne][colonne] = (valeur.toString().substring(0,c.toString().indexOf(".") ));
//                         System.out.println(tabFichier1[ligne][colonne]); 
//                        c.setNbr_h(tabFichier1[ligne][colonne]);
//
//                    }
//         
//        }
//                l.add(c);
//                for (int i = 0; i < l.size(); i++) {
//                    Cellule_excel c1 = (Cellule_excel) l.get(i);
//                    // System.out.println( c1.toString());
//                    System.out.println(c1);
//                }
//
//            }
//
//        }
//
//        inp.close();
//
//        return l;
//    }
//    
//	public static void main(String[] args) throws NamingException, ParseException, FileNotFoundException, IOException {
//		traitement();
////	 
////			InitialContext ctx = null;
////			PrimaryMaterialsStockServicesRemote proxy = null;
////			ctx = new InitialContext();
////			String jndiName = "esprit1718b4erp-ear/esprit1718b4erp-service/PrimaryMaterialsStockServices!tn.esprit.b4.esprit1718b4erp.services.PrimaryMaterialsStockServicesRemote";
////			proxy = (PrimaryMaterialsStockServicesRemote) ctx.lookup(jndiName);
////			
////		
////		
////		String ProductJindi = "esprit1718b4erp-ear/esprit1718b4erp-service/ProductServices!tn.esprit.b4.esprit1718b4erp.services.ProductServicesRemote";
////		Context context = new InitialContext();
////		ProductServicesRemote u = (ProductServicesRemote) context.lookup(ProductJindi);
////	
////		String ElementJindi = "esprit1718b4erp-ear/esprit1718b4erp-service/ElementProductServices!tn.esprit.b4.esprit1718b4erp.services.ElementProductServicesRemote";
////		Context context1 = new InitialContext();
////        ElementProductServicesRemote m = (ElementProductServicesRemote) context1.lookup(ElementJindi);
////		
////		Product P=new Product();
////
////		
////		PrimaryMaterialsStock Pi=new PrimaryMaterialsStock();
////
////		
////		ElementProduct E =new ElementProduct();
////	
////		
////		
////		Pi=proxy.FindById((long) 2);
////	      P= u.FindById((long)4);
////
////	 
////
////
////	for (ElementProduct EE : P.getListElementProduct()) {
////
////		
////
////System.out.println(EE.getMaterials().getUnitPrice());
////		}
////	
////	
////	
////		
//
//		
//		
//	}
//	   private static Object ContenuCellule(XSSFCell cellule) {
//	        Object value = null;
//
//	        if (cellule == null) {
//	            value = "";
//	        } else if (cellule.getCellType() == XSSFCell.CELL_TYPE_BOOLEAN) {
//	            value = cellule.getBooleanCellValue();
//	        } else if (cellule.getCellType() == XSSFCell.CELL_TYPE_ERROR) {
//	            value = cellule.getErrorCellValue();
//	        } else if (cellule.getCellType() == XSSFCell.CELL_TYPE_FORMULA) {
//	            value = cellule.getCellFormula();
//	        } else if (cellule.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
//	            value = cellule.getNumericCellValue();
//	        } else if (cellule.getCellType() == XSSFCell.CELL_TYPE_STRING) {
//	            value = cellule.getRichStringCellValue();
//	        }
//	        return value;
//
//	    }
//
//}
 
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
 
/**
 *
 * @web http://java-buddy.blogspot.com
 */
public class Test extends Application {
 
    @Override
    public void start(Stage primaryStage) {
         
        final FileChooser fileChooser = new FileChooser();
        final Button openButton = new Button("Open Image");
 
        openButton.setOnAction(
            new EventHandler<ActionEvent>() {
                @Override
                public void handle(final ActionEvent e) {
                    setExtFilters(fileChooser);
                    File file = fileChooser.showOpenDialog(primaryStage);
                    if (file != null) {
                        openNewImageWindow(file);
                    }
                }
            });
 
         
        StackPane root = new StackPane();
        root.getChildren().add(openButton);
         
        Scene scene = new Scene(root, 400, 150);
         
        primaryStage.setTitle("java-buddy.blogspot.com");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
 
    public static void main(String[] args) {
        launch(args);
    }
     
    private void setExtFilters(FileChooser chooser){
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
    }
             
    private void openNewImageWindow(File file){
        Stage secondStage = new Stage();
         
        MenuBar menuBar = new MenuBar();
        Menu menuFile = new Menu("File");
        MenuItem menuItem_Save = new MenuItem("Save Image");
        menuFile.getItems().addAll(menuItem_Save);
        menuBar.getMenus().addAll(menuFile);
         
        Label name = new Label(file.getAbsolutePath());
        Image image = new Image(file.toURI().toString());
        ImageView imageView = new ImageView();
         
        menuItem_Save.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Save Image");
                 
                File file = fileChooser.showSaveDialog(secondStage);
                if (file != null) {
                    try {
                        ImageIO.write(SwingFXUtils.fromFXImage(imageView.getImage(),
                                null), "png", file);
                    } catch (IOException ex) {
                        Logger.getLogger(
                            Test.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
 
        final VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(0, 10, 0, 10));
        vbox.getChildren().addAll(name, imageView);
         
        imageView.setFitHeight(400);
        imageView.setPreserveRatio(true);
        imageView.setImage(image);
        imageView.setSmooth(true);
        imageView.setCache(true);
         
        Scene scene = new Scene(new VBox(), 400, 350);
        ((VBox)scene.getRoot()).getChildren().addAll(menuBar, vbox);
         
        secondStage.setTitle(file.getName());
        secondStage.setScene(scene);
        secondStage.show();
    }
     
}
