package view.editor.test;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 *
 * @author Patrick Ott <Patrick.Ott@professional-webworkx.de>
 */
public class TakeSnapShoot extends Application {

    @Override
    public void start(Stage primaryStage) {
        AnchorPane root = new AnchorPane();
        Scene scene = new Scene(root, 1024, 768);
        ObservableList<String> observableArrayList = FXCollections.observableArrayList();
        observableArrayList.add("Kitchen");
        observableArrayList.add("Living Room");

        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        final BarChart barChart = new BarChart(xAxis, yAxis);

        xAxis.setLabel("Room");

        XYChart.Series series = new XYChart.Series();
        series.getData().add(new XYChart.Data<String, Number>("Kitchen", 1245));
        series.getData().add(new XYChart.Data<String, Number>("Living Room", 245));
        series.getData().add(new XYChart.Data<String, Number>("Child 1", 3445));

        barChart.getData().add(series);
        root.getChildren().add(barChart);

        Button snapShotBtn = new Button("Take a Snapshot");
        root.getChildren().add(snapShotBtn);
        snapShotBtn.setOnAction((ActionEvent t) -> {
            try {
                SnapshotParameters parameters = new SnapshotParameters();
                WritableImage wi = new WritableImage(100, 100);
                WritableImage snapshot = barChart.snapshot(new SnapshotParameters(), wi);

                File output = new File("snapshot" + new Date().getTime() + ".png");
                ImageIO.write(SwingFXUtils.fromFXImage(snapshot, null), "png", output);
            } catch (IOException ex) {
                Logger.getLogger(TakeSnapShoot.class.getName()).log(Level.SEVERE, null, ex);
            }
        });


        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}