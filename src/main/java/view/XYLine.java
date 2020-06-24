package view;

import java.awt.Font;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import xiaowu.Jdbcs;
import xiaowu.Login;

public class XYLine {
    public XYLine(){
        StandardChartTheme mChartTheme = new StandardChartTheme("CN");
        mChartTheme.setLargeFont(new Font("����", Font.BOLD, 20));
        mChartTheme.setExtraLargeFont(new Font("����", Font.PLAIN, 15));
        mChartTheme.setRegularFont(new Font("����", Font.PLAIN, 15));
        ChartFactory.setChartTheme(mChartTheme);
        XYSeriesCollection mCollection = GetCollection();
        JFreeChart mChart = ChartFactory.createXYLineChart(
                "�����ٶ�(��/��) & ������ȷ��",
                "��  ��",
                "��  ��",
                mCollection,
                PlotOrientation.VERTICAL,
                true,
                true,
                false);
        ChartFrame mChartFrame = new ChartFrame("�ɼ�����ͼ", mChart);
        mChartFrame.pack();
        //mChartFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mChartFrame.setVisible(true);
    }
    public XYSeriesCollection GetCollection()
    {
        String username = Login.getNameTe();
        int[] speed = new Jdbcs().pushSpeed(username);
        double[] accuracy = new Jdbcs().pushAccuracy(username);
        XYSeriesCollection mCollection = new XYSeriesCollection();
        XYSeries mSeriesFirst = new XYSeries("�����ٶ�");
        for (int i = 0; i < speed.length; i++) {
            mSeriesFirst.add(i+1,speed[i]);
        }
        XYSeries mSeriesSecond = new XYSeries("������ȷ��");
        for (int i = 0; i < accuracy.length; i++) {
            mSeriesSecond.add(i+1,accuracy[i]);
        }
        mCollection.addSeries(mSeriesFirst);
        mCollection.addSeries(mSeriesSecond);
        return mCollection;
    }
}
