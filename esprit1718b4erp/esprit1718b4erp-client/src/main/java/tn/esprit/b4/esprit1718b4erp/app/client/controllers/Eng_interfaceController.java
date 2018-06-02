/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b4.esprit1718b4erp.app.client.controllers;
//import jade.util.leap.Serializable;
//import jade.util.leap.Comparable;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.Transparency;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.ComponentColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import tn.esprit.b4.esprit1718b4erp.entities.User;
import tn.esprit.b4.esprit1718b4erp.services.UserServiceRemote;

/**
 * FXML Controller class
 *
 * @author firas saadaoui
 */
public class Eng_interfaceController implements Initializable {

    @FXML
    private Button btn_demandeCongé;
    @FXML
    private Button bntLogout;
    @FXML
    private ImageView imageViewPicture;
    @FXML
    private Button browsButton;
    final FileChooser fileChooser = new FileChooser();
    @FXML
    private Button viewButton;
    @FXML
    private Label LabelLastName;
    @FXML
    private Label labelFirstName;
    @FXML
    private Label LabelEmail;
    @FXML
    private Label LabelPhoneNumber;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            String jndiName = "esprit1718b4erp-ear/esprit1718b4erp-service/UserService!tn.esprit.b4.esprit1718b4erp.services.UserServiceRemote";
            Context context = new InitialContext();
            UserServiceRemote proxy = (UserServiceRemote) context.lookup(jndiName);
            User u = proxy.getUserById(LoginGUIController.getIdUserConnecte());
            System.out.println(u.getId());
            byte[] image1 = u.getPicture();            
            System.out.println(image1);            
            try (FileOutputStream fos = new FileOutputStream("picture")) {
                fos.write(image1);                
                System.out.println(fos.toString());  
                  labelFirstName.setText(u.getFirstname());
                  LabelLastName.setText(u.getLastname());
                  LabelEmail.setText(u.getEmail());
                  LabelPhoneNumber.setText(Integer.toString(u.getPhone_number()));
                try {

                    InputStream in = new ByteArrayInputStream(image1);
                    BufferedImage bImageFromConvert = ImageIO.read(in);
                    Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
                    BufferedImage capture = new Robot().createScreenCapture(screenRect);
                    Image image = SwingFXUtils.toFXImage(bImageFromConvert, null);
                    imageViewPicture.setImage(image);
                } catch (AWTException ex) {
                    Logger.getLogger(Eng_interfaceController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Eng_interfaceController.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        fos.close();
                    } catch (IOException ex) {
                        Logger.getLogger(Eng_interfaceController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
            } catch (IOException ex) {
                Logger.getLogger(Eng_interfaceController.class.getName()).log(Level.SEVERE, null, ex);
            }    } catch (NamingException ex) {
            Logger.getLogger(Eng_interfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }    
  
    
    }

    @FXML
    private void demandeCongéOnclick(ActionEvent event) throws IOException {
        Parent espaceResProjects = FXMLLoader.load(getClass().getResource("/fxml/Congé.fxml"));
        Scene espaceResProjectsScene = new Scene(espaceResProjects);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(espaceResProjectsScene);
        window.show();
    }

    @FXML
    private void OnCLickLogout(ActionEvent event) throws IOException {
        Parent espaceResProjects = FXMLLoader.load(getClass().getResource("/fxml/LoginGUI.fxml"));
        Scene espaceResProjectsScene = new Scene(espaceResProjects);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(espaceResProjectsScene);
        window.show();
    }

    @FXML
    private void OnClickBrowse(ActionEvent event) throws IOException, NamingException {
        String jndiName = "esprit1718b4erp-ear/esprit1718b4erp-service/UserService!tn.esprit.b4.esprit1718b4erp.services.UserServiceRemote";
        Context context = new InitialContext();
        UserServiceRemote proxy = (UserServiceRemote) context.lookup(jndiName);
        Stage primaryStage = null;
        setExtFilters(fileChooser);
        File file = fileChooser.showOpenDialog(primaryStage);
        System.out.println(file);
        User u = new User();
        byte[] data = Files.readAllBytes(file.toPath());
        u.setPicture((data));
        proxy.save(u);

    }

    private void setExtFilters(FileChooser chooser) {
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
    }
//        private static byte[] writtingImage(String fileLocation) {
//      System.out.println("file lication is"+fileLocation);
//     IOManager manager=new IOManager();
//        try {
//           return manager.getBytesFromFile(fileLocation);
//            
//        } catch (IOException e) {
//        }
//        return null;

    @FXML
    private void OnClickViewButton(ActionEvent event) throws IOException, NamingException, AWTException {
       
    }

    private static BufferedImage createRGBImage(byte[] bytes, int width, int height) {
        DataBufferByte buffer = new DataBufferByte(bytes, bytes.length);
        ColorModel cm = new ComponentColorModel(ColorSpace.getInstance(ColorSpace.CS_sRGB), new int[]{8, 8, 8}, false, false, Transparency.OPAQUE, DataBuffer.TYPE_BYTE);
        return new BufferedImage(cm, Raster.createInterleavedRaster(buffer, width, height, width * 3, 3, new int[]{0, 1, 2}, null), false, null);
    }

}
