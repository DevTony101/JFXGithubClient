/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import org.kohsuke.github.GHRepository;

/**
 * FXML Controller class
 *
 * @author Tony Manjarres
 */
public class RepoItemController implements Initializable {

    @FXML
    private Circle shape;

    @FXML
    private Label lblOwn;

    @FXML
    private Label lblRepoName, lblRepoDesc, lblPrivate;

    //
    private GHRepository repo;

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

    public void setRepository(GHRepository repo) {
        this.repo = repo;
        if (repo.isFork()) {
            shape.setFill(Paint.valueOf("TOMATO"));
            lblOwn.setText("Forked Repo");
        }

        lblRepoName.setText(repo.getName());
        lblPrivate.setText(repo.isPrivate() ? "Private" : "Public");
        String desc = repo.getDescription();
        if (desc != null && !desc.isEmpty()) {
            lblRepoDesc.setText(desc);
        } else {
            lblRepoDesc.setText("No Description Available.");
        }
    }

    @FXML
    void setSelected(ActionEvent event) {
        HomeController.SELECTED = this.repo;
    }
}
