/**
 * Created by alessandra zullo on 24/05/17.
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import java.io.*;
import java.util.ArrayList;

import com.csvreader.CsvReader;


public class gen_charts extends Application {

    @Override public void start(Stage stage) {
        try {
            String filename = "insert file csv path";
            CsvReader reader = new CsvReader(new FileReader(filename));

            int sizeFile = 0;
            ArrayList<String> x_values = new ArrayList();
            ArrayList<String> first_values = new ArrayList();
            ArrayList<String> second_values = new ArrayList();


            while (reader.readRecord()) {
                x_values.add(reader.get(0));
                first_values.add(reader.get(1));
                second_values.add(reader.get(2));
                sizeFile++;
            }
            String graphName = "insert graph name";
            stage.setTitle(graphName);

            //defining the axes
            final NumberAxis xAxis = new NumberAxis();
            final NumberAxis yAxis = new NumberAxis();

            String xAxisName = "insert x axis name";
            xAxis.setLabel(xAxisName);
            String yAxisName = "insert y axis name";
            yAxis.setLabel(yAxisName);


            //creating the chart
            final LineChart<Number, Number> lineChart =
                    new LineChart<Number, Number>(xAxis, yAxis);

            lineChart.setTitle(filename.replaceFirst(".csv",""));

            //defining a series
            XYChart.Series first = new XYChart.Series();
            wtssn.setName(first_values.get(0));

            XYChart.Series second = new XYChart.Series();
            wtss.setName(second_values.get(0));

            //populating the series with data
            for(int i = 1; i<sizeFile; i++) {
                first.getData().add(new XYChart.Data(Float.parseFloat(x_values.get(i)), Float.parseFloat(first_values.get(i))));
                second.getData().add(new XYChart.Data(Float.parseFloat(x_values.get(i)), Float.parseFloat(second_values.get(i))));
            }

            Scene scene = new Scene(lineChart, 800, 600);
            lineChart.getData().add(first);
            lineChart.getData().add(second);


            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void main(String[] args) throws IOException{
        launch(args);



    }


}
