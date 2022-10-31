package lab2;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import lab1.decode;
import lab1.encode;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Timer;
import java.util.TimerTask;

public class controller {

    @FXML
    private Button decode;

    @FXML
    private Button encode;

    @FXML
    private TextField filePath;

    @FXML
    private Button obzor;

    @FXML
    private TextArea text;

    @FXML
    private Button updateParam;

    @FXML
    private Button gen_save;

    @FXML
    void initialize(){
        final String[] FilePath = new String[1];
        final String[] key = new String[1];
        encode.setDisable(true);
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
            gen_save.setOnAction(event -> {
                try {
                    Gamma.saveParam();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            updateParam.setOnAction(event -> {
                try {
                    Gamma.saveParam();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            encode.setOnAction(event -> {
            try {
                Gamma.crypt(filePath.getText());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
            decode.setOnAction(event -> {
                try {
                    Gamma.crypt(filePath.getText());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }
