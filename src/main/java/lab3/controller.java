package lab3;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;


public class controller {

    @FXML
    private Button acept;

    @FXML
    private Button decode;

    @FXML
    private Button encode;

    @FXML
    private TextField filePath;

    @FXML
    private TextField keyField;

    @FXML
    private Button obzor;

    @FXML
    private TextArea text;

    @FXML
    void initialize(){
        final String[] FilePath = new String[1];
        final String[] key = new String[1];
        obzor.setOnAction(event -> {
            Task<Void> task = new Task<Void>(){
                protected Void call() {
                    JFrame frame = new JFrame();
                    JFileChooser JFC = new JFileChooser();
                    JFC.setFileSelectionMode(JFileChooser.FILES_ONLY);
                    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                    int result = JFC.showOpenDialog(frame);
                    if (result == JFileChooser.APPROVE_OPTION) {
                        try {
                            filePath.setText(new String(JFC.getSelectedFile().getAbsolutePath().getBytes(), StandardCharsets.UTF_8));
                            FilePath[0] = JFC.getSelectedFile().getAbsolutePath();
                            FileReader fr = new FileReader(JFC.getSelectedFile());
                            BufferedReader br = new BufferedReader(fr);
                            StringBuilder res = new StringBuilder();
                            String line;
                            while ((line = br.readLine())!=null){
                                res.append(line).append("\n");
                            }
                            text.setText(new String(res.toString().getBytes(), StandardCharsets.UTF_8));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    return null;
                }
            };
            new Thread(task).start();
        });

        acept.setOnAction(event -> {
            key[0] = keyField.getText();
        });

        encode.setOnAction(event -> {
            try {
                new GOST(Mode.ENCRYPT, new File(FilePath[0]),key[0]).rpz();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        decode.setOnAction(event -> {
            try {
                new GOST(Mode.DECRYPT, new File(FilePath[0]),key[0]).rpz();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }
}
