import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReference;

import javafx.concurrent.Task;
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
    void initialize() throws IOException {
        AtomicReference<Thread> JFS = new AtomicReference<>();
        final String[] FilePath = new String[1];
        obzor.setOnAction(event -> {
            Task<Void> task = new Task<Void>(){
                protected Void call() {
                    System.out.println("1");
                    JFrame frame = new JFrame();
                    JFileChooser JFC = new JFileChooser();
                    JFC.setFileSelectionMode(JFileChooser.FILES_ONLY);
                    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                    int result = JFC.showOpenDialog(frame);
                    if (result == JFileChooser.APPROVE_OPTION) {
                        try {
                            filePath.setText(new String(JFC.getSelectedFile().getAbsolutePath().getBytes(), "windows-1251"));
                            FilePath[0] = JFC.getSelectedFile().getAbsolutePath();
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }
                    return null;
                }
            };
            JFS.set(new Thread(task));
            JFS.get().start();
            });
        encode.setOnAction(event -> {
            Task<Void> task = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    encode encode = new encode(FilePath[0],"DOG");
                    encode.save();
                    return null;
                }
            };
            new Thread(task).start();
        });

        }
    }
