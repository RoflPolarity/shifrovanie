import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

import javax.swing.*;

public class controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button decode;

    @FXML
    private Button encode;

    @FXML
    private TextField filePath;

    @FXML
    private TextField key;

    @FXML
    private Button keyGen;

    @FXML
    private Button obzor;

    @FXML
    void initialize() {
        obzor.setOnAction(()->{JFrame frame = new JFrame();
        JFileChooser JFC = new JFileChooser();
        JFC.setFileSelectionMode(JFileChooser.FILES_ONLY);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        int result = JFC.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION ){
            try {
                prievue.setText(new String(JFC.getSelectedFile().getAbsolutePath().getBytes(), "windows-1251"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            files.set(JFC.getSelectedFile());
            run.setDisable(false);
            Original.setImage(new Image("file:///" + JFC.getSelectedFile().getAbsolutePath()));
        });

    }

}
