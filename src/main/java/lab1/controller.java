package lab1;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Timer;
import java.util.TimerTask;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javax.swing.*;

public class controller {

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
        encode.setDisable(true);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (keyField.getText().equals(""))key[0]="";
                else key[0] = keyField.getText();
                if (!keyField.getText().equals("")&!filePath.getText().equals("")) encode.setDisable(false);
            }
        },0,100);
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

            encode.setOnAction(event -> {
            try {
                new encode(filePath.getText(),key[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
            decode.setOnAction(event -> {
                try {
                    new decode(filePath.getText(),key[0]);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }
