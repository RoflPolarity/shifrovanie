package lab2;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.ui.RectangleInsets;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;



import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class GSPCH {
    long m = (long) Math.pow(2,48),a,b,c0,c,cLast;
    GSPCH(){
        a = getA();
        b = getB();
        c0 = getC0();
    }

    private long getA() {

        long example =System.currentTimeMillis();
        while (example%4!=1){
            example += 1 ;
        }
        return example;
       }
    private long getB() {
        long example = System.currentTimeMillis();
        while (example%2==0&&!NOD(example,m)){
            example += 1;

        }
        return example;
        }
    private boolean NOD(long m, long n) {
        while (n > 0) {
            m = n;
            n = m % n;
        }
        return m == 1;
    }
    private long getC0() {
        return  System.currentTimeMillis();
    }


    public void updateParameters(){
        a = getA();
        b = getB();
        c0 = getC0();
    }
    public int[] getRand(int num){
        int[] arr = new int[num];
        cLast = (a*c0+b)%m;
        for (int i = 0; i < num; i++) {
            arr[i] = (int) ((a*cLast+b)%m);
            cLast = arr[i];
            }
        return arr;
    }
    public void getRandToFile(int num) throws IOException {
        List<Long> arr = new ArrayList<>();
        List<Long> counters = new ArrayList<>();
        List<Long> lst = new ArrayList<>();
        String username = System.getProperty("user.name");
        File file = new File("C:\\Users\\" + username +"\\Desktop\\nums.txt");
        FileWriter fw = new FileWriter(file);


        long z = 0;
        while (z<m){
            z+=m/100;
            arr.add(z);
            counters.add(0l);
        }

        cLast =(a*c0+b)%m;
        if (cLast<0) cLast*=-1;
        for (int i = 0; i < num; i++) {
            c = (a*cLast+b)%m;
            if (c<0) c *= -1;
            cLast = c;
            lst.add(c);
        }

        for (int i = 0; i < lst.size(); i++) {
            if (lst.get(i)<arr.get(0)) {
                long temp = counters.get(0)+1;
                counters.set(0,temp);

            }else if (lst.get(i)<arr.get(arr.size()-1)&&lst.get(i)>arr.get(arr.size()-2)){
                long temp = counters.get(counters.size()-1)+1;
                counters.set(counters.size()-1,temp);

            }else for (int j = 1; j < arr.size()-1; j++) {
                if (lst.get(i) <arr.get(j)&& lst.get(i) >arr.get(j-1)){
                    long temp = counters.get(j)+1;
                    counters.set(j,temp);
                }
            }
        }

        if (!file.exists()){
            file.createNewFile();
            for (Long aLong : lst) fw.write(aLong+" ");
        }else{
            for (Long aLong : lst) fw.write(aLong+" ");
        }

        fw.flush();
        fw.close();
        JFrame frame = new JFrame();
        JFreeChart chart = createChart(createDataset(counters,arr));
        chart.setPadding(new RectangleInsets(4, 8, 2, 2));
        ChartPanel panel = new ChartPanel(chart);
        panel.setFillZoomRectangle(true);
        panel.setMouseWheelEnabled(true);
        panel.setPreferredSize(new Dimension(600, 300));
        frame.add(panel);
        frame.setSize(600,300);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.show();
    }

    private JFreeChart createChart(CategoryDataset dataset){
        JFreeChart chart = ChartFactory.createBarChart(
                "Распределение чисел",
                "От 0 до (m-1)",
                "Кол-во попаданий",
                dataset);
        chart.setBackgroundPaint(Color.white);

        CategoryPlot plot = (CategoryPlot) chart.getPlot();

        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(false);
        chart.getLegend().setFrame(BlockBorder.NONE);

        return chart;
    }

    private CategoryDataset createDataset(List<Long> count, List<Long> arr){

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int i = 0; i < count.size(); i++) {
            dataset.addValue(count.get(i),"nums",String.valueOf(arr.get(i)));
        }
        return dataset;
    }

    public static void main(String[] args) throws IOException {
        GSPCH gspch = new GSPCH();
        gspch.getRandToFile(10000);
    }
}
