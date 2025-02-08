package eif.viko.lt.predictionappclient;

import eif.viko.lt.predictionappclient.Services.AuthService;
import eif.viko.lt.predictionappclient.Services.AuthServiceImpl;
import eif.viko.lt.predictionappclient.Services.LoginCallback;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private TextField password;

    @FXML
    private TextField username;

    @FXML
    private Button loginBtn;

    @FXML
    private Button logoutBtn;

    @FXML
    private VBox authPanelBox;


    @FXML
    private Text mainTabLabel;

    @FXML
    private Tab chatTab;

    @FXML
    private Tab predictionTab;


    private final AuthServiceImpl authService = new AuthServiceImpl();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        //SecureStorage.clearToken();
        boolean isAuthenticated = SecureStorage.getToken() == null;
        authPanelBox.setVisible(isAuthenticated);
        chatTab.setDisable(isAuthenticated);
        predictionTab.setDisable(isAuthenticated);
    }

    @FXML
    void login(ActionEvent event) {

        String user = username.getText();
        String pass = password.getText();

        if (user != null && pass != null)
            authService.login(user, pass, new LoginCallback() {
                @Override
                public void onLoginSuccess(String token) {
                    authPanelBox.setVisible(false);
                    mainTabLabel.setText("Sveiki prisijungę");
                    logoutBtn.setVisible(true);
                }

                @Override
                public void onLoginFailure(String errorMessage) {
                    chatTab.setDisable(false);
                    predictionTab.setDisable(false);
                }
            });

//        AuthServiceImpl authService = new AuthServiceImpl();
//        if (username != null && password != null)
//            authService.login(username.getText(), password.getText());
    }

    @FXML
    void logout(ActionEvent event) {
        SecureStorage.clearToken();
        authPanelBox.setVisible(true);
        logoutBtn.setVisible(false);
    }

}