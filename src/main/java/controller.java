import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Random;
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
    private Button keyGen;

    @FXML
    private Button obzor;

    @FXML
    private TextArea text;

    @FXML
    void initialize(){
        final String[] FilePath = new String[1];
        final String[] key = new String[1];
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (keyField.getText().equals(""))key[0]="";
                System.out.println(key[0]);
            }
        },0,1000);
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
                            filePath.setText(new String(JFC.getSelectedFile().getAbsolutePath().getBytes(), "windows-1251"));
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
            keyGen.setOnAction(event -> {
                Task<Void> task = new Task<Void>(){
                    @Override
                    protected Void call(){
                        key[0] = KeyGeneration();
                        keyField.setText(key[0]);
                        return null;
                    }
                };
                new Thread(task).start();
            });

        encode.setOnAction(event -> {
            Task<Void> task = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    if (FilePath[0].equals("")){
                        String warning = "Íå âûáğàí ôàéë";
                        Alert alert = new Alert(Alert.AlertType.ERROR, new String(warning.getBytes(),StandardCharsets.UTF_8));
                        alert.showAndWait();
                    }else if (key[0].equals("")){
                        String warning = "Íå ñãåíåğèğîâàí êëş÷. \nÑãåíåğèğîâàòü êëş÷ àâòîìàòè÷åñêè?";
                        Alert alert = new Alert(Alert.AlertType.WARNING, new String(warning.getBytes(),StandardCharsets.UTF_8), ButtonType.YES, ButtonType.NO);
                        alert.showAndWait();
                        System.out.println("2");
                        if (alert.getResult()==ButtonType.YES){
                            encode encode = new encode(FilePath[0],KeyGeneration());
                            encode.save();
                        }
                    }else {
                        System.out.println("3");
                        encode encode = new encode(FilePath[0],key[0]);
                        encode.save();
                    }

                    return null;
                }
            };
            new Thread(task).start();
        });

        }

        private String KeyGeneration(){
            String alphabet = "ÀÁÂÃÄÅ¨ÈÆÇÈÉÊËÌÍÎÏĞÑÒÓÔÕÖ×ØÙÚÛÜİŞßABCDEFGHIJKLMNOPQRSTUVWXYZ 1234567890\"'?;,.:-!";
            StringBuilder sb = new StringBuilder();
            Random random = new Random();
            for (int i = 0; i < 10; i++) {
                sb.append(alphabet.charAt(random.nextInt(alphabet.length())));
            }
            return sb.toString();
        }
    }
