/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controllers;

import de.jensd.fx.glyphs.octicons.OctIcon;
import de.jensd.fx.glyphs.octicons.OctIconView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.paint.Paint;
import org.kohsuke.github.GHRepository;

/**
 * FXML Controller class
 *
 * @author Tony Manjarres
 */
public class RepoItemController implements Initializable {

    @FXML
    private Label lblRepoName, lblRepoDesc, lblFork;

    @FXML
    private OctIconView icon;

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
            icon.setIcon(OctIcon.REPO_FORKED);
            icon.setFill(Paint.valueOf("#6A737D"));
            lblFork.setText("Forked");
        } else if (repo.isPrivate()) {
            icon.setIcon(OctIcon.LOCK);
            icon.setFill(Paint.valueOf("#DBAB09"));
            lblFork.setText("Private");
        } else {
            icon.setIcon(OctIcon.REPO);
            icon.setFill(Paint.valueOf("#6A737D"));
            lblFork.setText("Public");
        }

        lblRepoName.setText(repo.getName());
        lblRepoDesc.setText("No Description Available.");
        String desc = repo.getDescription();
        if (desc != null && !desc.isEmpty()) {
            lblRepoDesc.setText(desc);
        }
    }

    @FXML
    void setSelectedRepository(ActionEvent event) {
        HomeController.SELECTED = this.repo;
    }
}
