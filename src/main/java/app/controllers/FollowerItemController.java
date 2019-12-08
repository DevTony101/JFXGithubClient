package app.controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import org.kohsuke.github.GHUser;

/**
 * FXML Controller class
 *
 * @author Tony Manjarres
 */
public class FollowerItemController implements Initializable {

    @FXML
    private ImageView imAvatar;

    @FXML
    private Label lblName, lblUsername;
    
    @FXML
    private Button btnFollow;

    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location The location used to resolve relative paths for the root
     * object, or
     * <tt>null</tt> if the location is not known.
     * @param resources The resources used to localize the root object, or
     * <tt>null</tt> if
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO
    }

    public void setFollower(GHUser user, GHUser follower) {
        try {
            String name = (follower.getName() != null && !follower.getName().isEmpty() ? follower.getName() : "No Name Available");
            lblName.setText(name);
            lblUsername.setText("@" + follower.getLogin());
            Image image = new Image(follower.getAvatarUrl());
            imAvatar.setImage(image);
            btnFollow.setVisible(!user.getFollows().contains(follower));
        } catch (IOException ex) {
            Logger.getLogger(FollowerItemController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void followUser(MouseEvent event) {
        // TODO: Implement this functionality
    }

}
