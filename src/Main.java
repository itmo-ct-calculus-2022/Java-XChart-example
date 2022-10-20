import org.knowm.xchart.*;
import org.knowm.xchart.style.lines.SeriesLines;
import org.knowm.xchart.style.markers.SeriesMarkers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    /* Функция, возвращающая элемент последовательности по его номеру */
    private static double function(int n) {
        // x_n = (3 * n^2 + 1) / n^2
        return (3.0 * n + 3) / (n);
    }

    /* Функция, добавляющая на график точки последовательности */
    private static void addPoints(XYChart chart, String seriesName, int from, int to) {
        // Заведем массивы для координат точек и заполним их
        List<Double> x = new ArrayList<>(to - from);
        List<Double> y = new ArrayList<>(to - from);
        for (int n = from; n < to; n++) {
            x.add((double) n);
            y.add(function(n));
        }

        XYSeries series = chart.addSeries(seriesName, x, y); // Добавим точки на график
        series.setLineStyle(SeriesLines.NONE);  // Не соединять точки линиями
        series.setMarker(SeriesMarkers.CIRCLE); // Формат точки - кружочек
    }

    /* Функция, добавляющая на график горизонтальный отрезок */
    private static void addHorizontalLine(XYChart chart, String name, double xFrom, double xTo, double yHeight) {
        // Отрезок задается двумя точками
        List<Double> x = List.of(xFrom, xTo);
        List<Double> y = List.of(yHeight, yHeight);

        XYSeries series = chart.addSeries(name, x, y); // Добавь точки отрезка на график
        series.setLineStyle(SeriesLines.SOLID); // Соедини точки сплошной линией
        series.setMarker(SeriesMarkers.NONE); // Не отображай сами точки
    }

    public static void main(String[] args) {
        XYChart chart = new XYChart(1000, 400); // Создадим пустой график
        addPoints(chart, "x_n", 1, 101); // Добавим на график первые 100 точек последовательности
        addHorizontalLine(chart, "inf", 1, 101, 3); // Добавим на график inf

        // Отобразим график в отдельном окошке
        new SwingWrapper<>(chart).displayChart();

        // Сохраним график в .png картинку
        try {
            BitmapEncoder.saveBitmap(chart, "./example", BitmapEncoder.BitmapFormat.PNG);
        } catch (IOException e) {
            System.out.println("Could not save chart: " + e.getMessage());
        }
    }
}
