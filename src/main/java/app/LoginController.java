package app;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import org.kohsuke.github.GHUser;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;

import utilities.*;

/**
 * FXML Controller class
 *
 * @author Tony Manjarres
 */
public class LoginController implements Initializable {

    @FXML
    private JFXTextField usernameField;

    @FXML
    private JFXPasswordField passwordField;

    //
    public static GHUser GH_USER = null;
    private double xPos, yPos;

    @FXML
    private void closeWindow(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    private void joinGithub(ActionEvent event) {
        openWebBrowser(Constants.GITHUB_JOIN_URL);
    }

    @FXML
    private void signIn(MouseEvent event) {
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();
        if (username.isEmpty() && password.isEmpty()) {
            Notifications nf = makeNotification("Error!", "Fields Can't Be Blank!");
            nf.showError();
        } else {
            try {
                GitHub github = new GitHubBuilder().withPassword(username, password).build();
                GH_USER = github.getMyself();
                
                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                stage.close();
                
                URL path = getClass().getResource(Constants.FXML_HOME);
                if (path != null) {
                    Parent root = FXMLLoader.load(path);
                    root.setOnMousePressed((value) -> {
                        xPos = value.getSceneX();
                        yPos = value.getSceneY();
                    });

                    root.setOnMouseDragged((value) -> {
                        stage.setX(value.getScreenX() - xPos);
                        stage.setY(value.getScreenY() - yPos);
                    });

                    Scene scene = new Scene(root);
                    scene.setFill(Color.TRANSPARENT);
                    
                    stage.setTitle(Constants.APP_TITLE);
                    stage.setScene(scene);
                    stage.show();
                } else {
                    throw new IOException("Error Loading Home FXML File.");
                }
            } catch (IOException e) {
                System.err.println("Error With Login: " + e.getMessage());
                Notifications nf = makeNotification("Error!", "Invalid User or Password.");
                nf.showError();
            }
        }
    }

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

    }

    private void openWebBrowser(String uri) {
        try {
            Desktop.getDesktop().browse(URI.create(uri));
        } catch (IOException e) {
            System.err.println("Error Open Web Browser: " + e.getMessage());
            String title = "Oh No! An Error Has Occurred!";
            String text = Constants.APP_TITLE + " is unable to open a web browser.";
            Notifications nf = makeNotification(title, text);
            nf.showError();
        }
    }

    private Notifications makeNotification(String title, String text) {
        Notifications builder = Notifications.create()
                .title(title)
                .text(text)
                .graphic(null)
                .hideAfter(Duration.seconds(3))
                .position(Pos.BOTTOM_RIGHT);
        return builder;
    }

}
