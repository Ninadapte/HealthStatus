package com.project.healthstatus.ui.gather_insights;

import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import com.github.mikephil.charting.utils.MPPointF;
import com.project.healthstatus.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.project.healthstatus.summery_adapter;
import com.project.healthstatus.summery_item;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class graphdata extends Fragment {
    Spinner spinner_age ;
    GraphView bar_Graph,bar_Graph2;

    TextView agel1,agel2,agel3,pop1,pop2,pop3 ;

    PieChart pieChart,pieChart1,pieChart2;
    Spinner spinner_disease,spinner_education,spinner_nutrition;


    private static final String SET_LABEL = "Income";
    private BarChart chart;
    RecyclerView diseaserecycler,education_recycler,nutrition_recycler;
    RecyclerView.LayoutManager layoutManager,layoutManager1,layoutManager2;
    ArrayList<summery_item> diseases,education_list,nutrition_list;
    summery_adapter adapter_disease,adapter_education,adapter_nutrition;
    private static final String income[] = {"5L+","5L-","1L-","BPL","Not Given"};
    ArrayList<String> inc;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.graphdata_summery , container , false);


        inc = new ArrayList<>();
        inc.add("5L+");
        inc.add("5L-");
        inc.add("1L-");
        inc.add("BPL");
        inc.add("--");
        bar_Graph = view.findViewById(R.id.bar_graph);

        bar_Graph2 = view.findViewById(R.id.bar_graph_population);

        setBarGraph2(bar_Graph2);

        chart = view.findViewById(R.id.Barchart2);
        BarData data = createChartData();
        configureChartAppearance();
        prepareChartData(data);


        diseaserecycler = view.findViewById(R.id.diseaserecyclerview);
        education_recycler = view.findViewById(R.id.educationrecycler);
        nutrition_recycler = view.findViewById(R.id.nutritionrecycler);
        education_recycler.setHasFixedSize(true);
        diseaserecycler.setHasFixedSize(true);
        nutrition_recycler.setHasFixedSize(true);


        layoutManager = new LinearLayoutManager(getContext());
        layoutManager1= new LinearLayoutManager(getContext());
        layoutManager2 = new LinearLayoutManager(getContext());

        diseaserecycler.setLayoutManager(layoutManager);
        education_recycler.setLayoutManager(layoutManager1);
        nutrition_recycler.setLayoutManager(layoutManager2);

        diseases = new ArrayList<>();
        education_list = new ArrayList<>();
        nutrition_list = new ArrayList<>();

        adapter_disease = new summery_adapter(diseases,getContext());
        adapter_education = new summery_adapter(education_list , getContext());
        adapter_nutrition = new summery_adapter(nutrition_list , getContext());



        diseaserecycler.setAdapter(adapter_disease);
        education_recycler.setAdapter(adapter_education);
        nutrition_recycler.setAdapter(adapter_nutrition);

        setListwitheducation(education_list,adapter_education);
        setListwithdiseases(diseases,adapter_disease);
        setListwithnutrition(nutrition_list , adapter_nutrition);

        agel1  = view.findViewById(R.id.bargraphmaleLabel);
        agel2  = view.findViewById(R.id.bargraphfemaleLabel);
        agel3  = view.findViewById(R.id.bargraphothersLabel);

        pop1= view.findViewById(R.id.bargrap2hmaleLabel);
        pop2= view.findViewById(R.id.bargraph2femaleLabel);
        pop3= view.findViewById(R.id.bargraph2othersLabel);
        pop1.setText("Male");
        pop2.setText("Female");
        pop3.setText("Others");
        //spinnerage
        spinner_age = view.findViewById(R.id.agegraphspinner);
        ArrayAdapter<CharSequence> adapter_age = ArrayAdapter.createFromResource(getContext(),
                R.array.age, R.layout.summeryspinneritem);
        adapter_age.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_age.setAdapter(adapter_age);
        spinner_age.setOnItemSelectedListener(graph_spinner_age);


        spinner_disease = view.findViewById(R.id.diseasegraphspinner);
        ArrayAdapter<CharSequence> adapter_disease = ArrayAdapter.createFromResource(getContext(),
                R.array.summery_disease, R.layout.summeryspinneritem);
        adapter_disease.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_disease.setAdapter(adapter_disease);
        spinner_disease.setOnItemSelectedListener(graph_spinner_disease);




        spinner_education = view.findViewById(R.id.educationgraphspinner);
        ArrayAdapter<CharSequence> adapter_education = ArrayAdapter.createFromResource(getContext(),
                R.array.summery_literate, R.layout.summeryspinneritem);
        adapter_education.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_education.setAdapter(adapter_education);
        spinner_education.setOnItemSelectedListener(graph_spinner_education);
//        linear1 = view.findViewById(R.id.linear1);
//        linear2 = view.findViewById(R.id.linear2);


        spinner_nutrition = view.findViewById(R.id.nutritiongraphspinner);
        ArrayAdapter<CharSequence> adapter_nutrition = ArrayAdapter.createFromResource(getContext(),
                R.array.summery_nutrition, R.layout.summeryspinneritem);
        adapter_nutrition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_nutrition.setAdapter(adapter_nutrition);
        spinner_nutrition.setOnItemSelectedListener(graph_spinner_nutrition);
        //Piechart
        pieChart = view.findViewById(R.id.piechart);
        pieChart1 = view.findViewById(R.id.piechart1);
        pieChart2 = view.findViewById(R.id.piechart2);


        return view;
    }
    private BarData createChartData() {
        ArrayList<BarEntry> values = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            float x = i;

            float y = x*20+10;
            values.add(new BarEntry(x, y));
        }

        BarDataSet set1 = new BarDataSet(values, SET_LABEL);

        ArrayList<IBarDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);

        BarData data = new BarData(dataSets);

        return data;
    }
    private void prepareChartData(BarData data) {
        data.setValueTextSize(12f);
        chart.setData(data);
        chart.invalidate();
    }
    private void configureChartAppearance() {
        chart.getDescription().setEnabled(false);
        chart.setDrawValueAboveBar(true);

        XAxis xAxis = chart.getXAxis();

        xAxis.setCenterAxisLabels(true);
        ValueFormatter valueFormatter = new ValueFormatter() {


            @Override
            public String getFormattedValue(float value) {
                if(value == (float)0.0)
                {
                    return income[0];
                }
                else if(value == (float)0.8)
                {
                    return income[1];
                }
                else if(value == (float)1.6)
                {
                    return income[2];
                }
                else if(value == (float)2.4)
                {
                    return income[3];
                }
                else if(value == (float)3.2)
                {
                    return income[4];
                }

                else return "";
            }
        };
//        xAxis.setValueFormatter(new ValueFormatter() {
//            @Override
//            public String getFormattedValue(float value) {
//
//              return income[(int)value];
//
////
//            }
//        });
        xAxis.setValueFormatter(valueFormatter);

        YAxis axisLeft = chart.getAxisLeft();
        axisLeft.setGranularity(10f);
        axisLeft.setAxisMinimum(0);

        YAxis axisRight = chart.getAxisRight();
        axisRight.setGranularity(10f);
        axisRight.setAxisMinimum(0);
    }//setListwithnutrition
    private void setListwithdiseases(ArrayList<summery_item> list,summery_adapter adapter)
        {
        list.clear();
        list.add(new summery_item("Asthma" ,"#FFA726","10" ,"#F3F5FF"));
        list.add(new summery_item("Cancer" ,"#66BB6A" ,"10","#F3F5FF"));
        list.add(new summery_item("Covid" ,"#EF5350","10","#F3F5FF" ));
        list.add(new summery_item("Dengue" ,"#29B6F6","10" ,"#F3F5FF"));
        list.add(new summery_item("Diabetes" ,"#D81B60","10" ,"#F3F5FF"));
        list.add(new summery_item("Malaria" ,"#E53935" ,"10","#F3F5FF"));
        list.add(new summery_item("Pneunomia" ,"#F4511E" ,"10","#F3F5FF"));
        list.add(new summery_item("TB" ,"#0088FF" ,"10","#F3F5FF"));
        list.add(new summery_item("Others" ,"#FDD835" ,"20","#F3F5FF"));//"#BBDEFD"
        adapter.notifyDataSetChanged();


        }
    private void setListwithnutrition(ArrayList<summery_item> list,summery_adapter adapter){
        list.clear();
        list.add(new summery_item("Healthy" ,"#FFA726","10" ,"#F3F5FF"));
        list.add(new summery_item("UnderWeight" ,"#66BB6A" ,"80","#F3F5FF"));//"#FFE9A4FB"
        list.add(new summery_item("Overweight" ,"#EF5350","10","#F3F5FF" ));

        adapter.notifyDataSetChanged();


    }
    private void setListwitheducation(ArrayList<summery_item> list,summery_adapter adapter)
    {
        list.clear();
        list.add(new summery_item("Below 10th" ,"#FFA726","10" ,"#F3F5FF"));
        list.add(new summery_item("Graduated" ,"#66BB6A" ,"10","#F3F5FF"));
        list.add(new summery_item("10th pass" ,"#EF5350","10","#F3F5FF"));//"#F8BCA9"
        list.add(new summery_item("12th pass" ,"#29B6F6","10" ,"#F3F5FF"));
        list.add(new summery_item("Uneducated" ,"#D81B60","10" ,"#F3F5FF"));

        adapter.notifyDataSetChanged();


    }
    private void setListwithgender(ArrayList<summery_item> list,summery_adapter adapter,int m,int f,int o,String color)
    {
        list.clear();//"#F8BCA9""#BBDEFD"
        list.add(new summery_item("Male" ,"#FFA726",String.valueOf(m) ,"#F3F5FF"));
        list.add(new summery_item("Female" ,"#66BB6A" ,String.valueOf(f),"#F3F5FF"));
        list.add(new summery_item("Others" ,"#EF5350",String.valueOf(o) ,"#F3F5FF"));

        adapter.notifyDataSetChanged();


    }

    private void setBarGraph2(GraphView bar_Graph)
    {
        bar_Graph.removeAllSeries();

        BarGraphSeries<Data> barGraph_Data = new BarGraphSeries<>(new Data[] {
                new Data(0, 10),
                new Data(1, 5),
                new Data(2, 6),

        });
        bar_Graph.addSeries(barGraph_Data);

        barGraph_Data.setValueDependentColor(new ValueDependentColor<Data>() {
            @Override
            public int get(Data info) {
                return Color.rgb((int) info.getX()*255/4, (int) Math.abs(info.getY()*255/6), 100);
            }
        });

        barGraph_Data.setSpacing(20);
        barGraph_Data.setAnimated(true);
        barGraph_Data.setValuesOnTopSize(30);
        barGraph_Data.setDrawValuesOnTop(true);
        barGraph_Data.setValuesOnTopColor(Color.RED);

    }
    private void setPieData(int m , int f , int o,PieChart pieChart)
    {
        pieChart.clearChart();
        pieChart.addPieSlice(
                new PieModel(
                        "Male",
                        Integer.parseInt(String.valueOf(m)),
                        Color.parseColor("#FFA726")));
        pieChart.addPieSlice(
                new PieModel(
                        "Female",
                        Integer.parseInt(String.valueOf(f)),
                        Color.parseColor("#66BB6A")));

        pieChart.addPieSlice(
                new PieModel(
                        "Others",
                        Integer.parseInt(String.valueOf(o)),
                        Color.parseColor("#EF5350")));

        // To animate the pie chart
        pieChart.startAnimation();
    }
    AdapterView.OnItemSelectedListener graph_spinner_age = new AdapterView.OnItemSelectedListener() {





        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            bar_Graph.removeAllSeries();
            agel1.setText("Male");
            agel2.setText("Female");
            agel3.setText("Others");
            if(position == 0)
            {
                BarGraphSeries<Data> barGraph_Data = new BarGraphSeries<>(new Data[] {
                        new Data(0, 3),
                        new Data(1, 5),
                        new Data(2, 6),

                });
                bar_Graph.addSeries(barGraph_Data);

                barGraph_Data.setValueDependentColor(new ValueDependentColor<Data>() {
                    @Override
                    public int get(Data info) {
                        return Color.rgb((int) info.getX()*255/4, (int) Math.abs(info.getY()*255/6), 100);
                    }
                });
                barGraph_Data.setAnimated(true);
                barGraph_Data.setValuesOnTopSize(30);
                barGraph_Data.setSpacing(20);
                barGraph_Data.setDrawValuesOnTop(true);
                barGraph_Data.setValuesOnTopColor(Color.RED);
            }
            else if(position  == 1)
            {
                BarGraphSeries<Data> barGraph_Data = new BarGraphSeries<>(new Data[] {
                        new Data(0, 16),
                        new Data(1, 56),
                        new Data(2, 36),

                });
                bar_Graph.addSeries(barGraph_Data);

                barGraph_Data.setValueDependentColor(new ValueDependentColor<Data>() {
                    @Override
                    public int get(Data info) {
                        return Color.rgb((int) info.getX()*255/4, (int) Math.abs(info.getY()*255/6), 100);
                    }
                });

                barGraph_Data.setSpacing(20);
                barGraph_Data.setAnimated(true);
                barGraph_Data.setValuesOnTopSize(30);
                barGraph_Data.setDrawValuesOnTop(true);
                barGraph_Data.setValuesOnTopColor(Color.RED);
            }
            else if(position  == 2)
            {
                BarGraphSeries<Data> barGraph_Data = new BarGraphSeries<>(new Data[] {
                        new Data(0, 1),
                        new Data(1, 2),
                        new Data(2, 33),

                });
                bar_Graph.addSeries(barGraph_Data);

                barGraph_Data.setValueDependentColor(new ValueDependentColor<Data>() {
                    @Override
                    public int get(Data info) {
                        return Color.rgb((int) info.getX()*255/4, (int) Math.abs(info.getY()*255/6), 100);
                    }
                });

                barGraph_Data.setSpacing(20);
                barGraph_Data.setDrawValuesOnTop(true);
                barGraph_Data.setAnimated(true);
                barGraph_Data.setValuesOnTopSize(30);
                barGraph_Data.setValuesOnTopColor(Color.RED);
            }
            else
            {
                //handle the all case here

                agel1.setText("Adult");
                agel2.setText("Senior");
                agel3.setText("Young");
                BarGraphSeries<Data> barGraph_Data = new BarGraphSeries<>(new Data[] {
                        new Data(0, 12),
                        new Data(1, 22),
                        new Data(2, 32),

                });
                bar_Graph.addSeries(barGraph_Data);

                barGraph_Data.setValueDependentColor(new ValueDependentColor<Data>() {
                    @Override
                    public int get(Data info) {
                        return Color.rgb((int) info.getX()*255/4, (int) Math.abs(info.getY()*255/6), 100);
                    }
                });
                barGraph_Data.setAnimated(true);
                barGraph_Data.setSpacing(20);
                barGraph_Data.setValuesOnTopSize(30);
                barGraph_Data.setDrawValuesOnTop(true);
                barGraph_Data.setValuesOnTopColor(Color.RED);

            }

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            bar_Graph.removeAllSeries();
            agel1.setText("Adult");
            agel2.setText("Senior");
            agel3.setText("Young");
            BarGraphSeries<Data> barGraph_Data = new BarGraphSeries<>(new Data[] {
                    new Data(0, 12),
                    new Data(1, 22),
                    new Data(2, 32),

            });
            bar_Graph.addSeries(barGraph_Data);

            barGraph_Data.setValueDependentColor(new ValueDependentColor<Data>() {
                @Override
                public int get(Data info) {
                    return Color.rgb((int) info.getX()*255/4, (int) Math.abs(info.getY()*255/6), 100);
                }
            });
            barGraph_Data.setAnimated(true);
            barGraph_Data.setSpacing(20);
            barGraph_Data.setValuesOnTopSize(30);
            barGraph_Data.setDrawValuesOnTop(true);
            barGraph_Data.setValuesOnTopColor(Color.RED);
        }
    };

    AdapterView.OnItemSelectedListener graph_spinner_disease = new AdapterView.OnItemSelectedListener() {





        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            if(position == 0)
            {
               //display all disease here

                setListwithdiseases(diseases,adapter_disease);
                pieChart.addPieSlice(
                        new PieModel(
                                "Asthma",
                                Integer.parseInt("10"),
                                Color.parseColor("#FFA726")));
                pieChart.addPieSlice(
                        new PieModel(
                                "Cancer",
                                Integer.parseInt("10"),
                                Color.parseColor("#66BB6A")));

                pieChart.addPieSlice(
                        new PieModel(
                                "Covid",
                                Integer.parseInt("10"),
                                Color.parseColor("#EF5350")));
                pieChart.addPieSlice(
                        new PieModel(
                                "Dengue",
                                Integer.parseInt("10"),
                                Color.parseColor("#29B6F6")));
                pieChart.addPieSlice(
                        new PieModel(
                                "Diabetes",
                                Integer.parseInt("10"),
                                Color.parseColor("#D81B60")));
                pieChart.addPieSlice(
                        new PieModel(
                                "Malaria",
                                Integer.parseInt("10"),
                                Color.parseColor("#E53935")));
                pieChart.addPieSlice(
                        new PieModel(
                                "Pneunomia",
                                Integer.parseInt("10"),
                                Color.parseColor("#F4511E")));
                pieChart.addPieSlice(
                        new PieModel(
                                "TB",
                                Integer.parseInt("10"),
                                Color.parseColor("#0088FF")));
                pieChart.addPieSlice(
                        new PieModel(
                                "Others",
                                Integer.parseInt("20"),
                                Color.parseColor("#FDD835")));



            }
            else if(position  == 1)
            {
                setListwithgender(diseases,adapter_disease,40,40,20,"#BBDEFD");
                setPieData(40,40,20,pieChart);
                //setPieData(40,40,20,linearLayout,linearlayout2,dis1,dis2,dis3,pieChart,disfemale,dismale,disother);
            }
            else if(position  == 2)
            {
                setListwithgender(diseases,adapter_disease,40,30,30,"#BBDEFD");
                setPieData(40,30,30,pieChart);
                //setPieData(40,30,30,linearLayout,linearlayout2,dis1,dis2,dis3,pieChart,disfemale,dismale,disother);
            }
            else if(position == 3)
            {
                setListwithgender(diseases,adapter_disease,70,10,20,"#BBDEFD");
                setPieData(70,10,20,pieChart);
                //setPieData(70,10,20,linearLayout,linearlayout2,dis1,dis2,dis3,pieChart,disfemale,dismale,disother);

            }
            else if(position == 4)
            {
                setListwithgender(diseases,adapter_disease,80,10,10,"#BBDEFD");
                setPieData(80,10,10,pieChart);
                //setPieData(80,10,10,linearLayout,linearlayout2,dis1,dis2,dis3,pieChart,disfemale,dismale,disother);

            }

            else if(position == 5)
            {
                setListwithgender(diseases,adapter_disease,20,50,30,"#BBDEFD");
                setPieData(20,50,30,pieChart);
                //setPieData(20,50,30,linearLayout,linearlayout2,dis1,dis2,dis3,pieChart,disfemale,dismale,disother);

            }

            else if(position == 6)
            {
                setListwithgender(diseases,adapter_disease,40,50,10,"#BBDEFD");
                setPieData(40,40,20,pieChart);
                //setPieData(40,50,10,linearLayout,linearlayout2,dis1,dis2,dis3,pieChart,disfemale,dismale,disother);

            }

            else if(position == 7)
            {
                //setPieData(30,30,40,linearLayout,linearlayout2,dis1,dis2,dis3,pieChart,disfemale,dismale,disother);
                setListwithgender(diseases,adapter_disease,30,30,40,"#BBDEFD");
                setPieData(30,30,40,pieChart);

            }

            else if(position == 8)
            {

                //setPieData(20,40,40,linearLayout,linearlayout2,dis1,dis2,dis3,pieChart,disfemale,dismale,disother);
                setListwithgender(diseases,adapter_disease,20,40,40,"#BBDEFD");
                setPieData(20,40,40,pieChart);
            }
            else if(position == 9)
            {

                //setPieData(50,40,10,linearLayout,linearlayout2,dis1,dis2,dis3,pieChart,disfemale,dismale,disother);
                setListwithgender(diseases,adapter_disease,50,40,10,"#BBDEFD");
                setPieData(50,40,10,pieChart);
            }



        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    AdapterView.OnItemSelectedListener graph_spinner_education = new AdapterView.OnItemSelectedListener() {





        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            if(position == 0)
            {

                setListwitheducation(education_list,adapter_education);
                pieChart1.clearChart();
                pieChart1.addPieSlice(
                        new PieModel(
                                "Below 10th",
                                Integer.parseInt("10"),
                                Color.parseColor("#FFA726")));
                pieChart1.addPieSlice(
                        new PieModel(
                                "Graduated",
                                Integer.parseInt("10"),
                                Color.parseColor("#66BB6A")));

                pieChart1.addPieSlice(
                        new PieModel(
                                "10th pass",
                                Integer.parseInt("10"),
                                Color.parseColor("#EF5350")));
                pieChart1.addPieSlice(
                        new PieModel(
                                "12th pass",
                                Integer.parseInt("10"),
                                Color.parseColor("#29B6F6")));
                pieChart1.addPieSlice(
                        new PieModel(
                                "Uneducated",
                                Integer.parseInt("50"),
                                Color.parseColor("#D81B60")));



            }
            else if(position  == 1)
            {
                //setPieData(40,40,20,linear1,linear2,edu1_label,edu2_label,edu3_label,pieChart1,edu2_text,edu1_text,edu3_text);
                setListwithgender(education_list,adapter_education,40,40,20,"#F8BCA9");
                setPieData(40,40,20,pieChart1);
            }
            else if(position  == 2)
            {
               // setPieData(40,30,30,linear1,linear2,edu1_label,edu2_label,edu3_label,pieChart1,edu2_text,edu1_text,edu3_text);
                setListwithgender(education_list,adapter_education,40,30,30,"#F8BCA9");
                setPieData(40,30,30,pieChart1);
            }
            else if(position == 3)
            {

                //setPieData(70,10,20,linear1,linear2,edu1_label,edu2_label,edu3_label,pieChart1,edu2_text,edu1_text,edu3_text);
                setListwithgender(education_list,adapter_education,70,10,20,"#F8BCA9");
                setPieData(70,10,20,pieChart1);
            }
            else if(position == 4)
            {

                //setPieData(80,10,10,linear1,linear2,edu1_label,edu2_label,edu3_label,pieChart1,edu2_text,edu1_text,edu3_text);
                setListwithgender(education_list,adapter_education,80,10,10,"#F8BCA9");
                setPieData(80,10,10,pieChart1);
            }

            else if(position == 5)
            {

                //setPieData(20,50,30,linear1,linear2,edu1_label,edu2_label,edu3_label,pieChart1,edu2_text,edu1_text,edu3_text);
                setListwithgender(education_list,adapter_education,20,50,30,"#F8BCA9");
                setPieData(20,50,30,pieChart1);
            }







        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    AdapterView.OnItemSelectedListener graph_spinner_nutrition= new AdapterView.OnItemSelectedListener() {





        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            if(position == 0)
            {

                setListwithnutrition(nutrition_list,adapter_nutrition);
                pieChart2.clearChart();
                pieChart2.addPieSlice(
                        new PieModel(
                                "Healthy",
                                Integer.parseInt("10"),
                                Color.parseColor("#FFA726")));
                pieChart2.addPieSlice(
                        new PieModel(
                                "Underweight",
                                Integer.parseInt("80"),
                                Color.parseColor("#66BB6A")));

                pieChart2.addPieSlice(
                        new PieModel(
                                "Overweight",
                                Integer.parseInt("10"),
                                Color.parseColor("#EF5350")));




            }
            else if(position  == 1)
            {
                //setPieData(40,40,20,linear1,linear2,edu1_label,edu2_label,edu3_label,pieChart1,edu2_text,edu1_text,edu3_text);
                setListwithgender(nutrition_list,adapter_nutrition,40,40,20,"#E9A4FB");
                setPieData(40,40,20,pieChart2);
            }
            else if(position  == 2)
            {
                // setPieData(40,30,30,linear1,linear2,edu1_label,edu2_label,edu3_label,pieChart1,edu2_text,edu1_text,edu3_text);
                setListwithgender(nutrition_list,adapter_nutrition,40,30,30,"#E9A4FB");
                setPieData(40,30,30,pieChart2);
            }
            else if(position == 3)
            {

                //setPieData(70,10,20,linear1,linear2,edu1_label,edu2_label,edu3_label,pieChart1,edu2_text,edu1_text,edu3_text);
                setListwithgender(nutrition_list,adapter_nutrition,70,10,20,"#E9A4FB");
                setPieData(70,10,20,pieChart2);
            }






        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };
}
