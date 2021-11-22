package com.project.healthstatus.ui.gather_insights;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import com.project.healthstatus.R;

public class rawdata extends Fragment {

    RelativeLayout basicinforellayout,summeryrel1,summeryrel2,summeryagerel,summery_diseaserel,summery_incomerel,summery_nutritionrel;
    int isbasicinfolayopen = 0;
    ImageView basicinfoImg,summeryImg1,summeryImg2,summeryageimg,summery_diseaseimg,summery_incomeimg,summery_nutritionimg;
    CardView basicinfocard,summerycard1,summerycard2,summeryagecard,summery_diseasecard,summery_incomecard,summery_nutritioncard;
    Spinner spinner_literate,spinner_disease,spinner_income,spinner_nutrition;
    TextView male_literacy,female_literacy,others_literacy , male_disease,female_disease, others_disease,income_text,male_nutrition,female_nutrition,others_nutrition;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.rawdata_summery , container , false);


        male_literacy = view.findViewById(R.id.summeryliteratemaletext);
        female_literacy = view.findViewById(R.id.summeryliteratefemaletext);
        others_literacy = view.findViewById(R.id.summeryliterateotherstext);
        male_disease = view.findViewById(R.id.summerydiseasemaletext);
        female_disease = view.findViewById(R.id.summerydiseasefemaletext);
        others_disease = view.findViewById(R.id.summerydiseaseotherstext);
        income_text = view.findViewById(R.id.summeryincomemaletext);
        male_nutrition = view.findViewById(R.id.summerynutritionmaletext);
        female_nutrition = view.findViewById(R.id.summerynutritionfemaletext);
        others_nutrition = view.findViewById(R.id.summerynutritionotherstext);

        basicinforellayout  = view.findViewById(R.id.basicinforelative);
        basicinfoImg  = view.findViewById(R.id.basicinfoarrow);
        basicinfocard = view.findViewById(R.id.villagebasicinfocard);


        summeryrel1 = view.findViewById(R.id.summeryrelative);
        summeryrel2 = view.findViewById(R.id.literatepopulationrel);
        summeryagerel = view.findViewById(R.id.summeryagerel);
        summery_diseaserel = view.findViewById(R.id.summerydiseaserel);
        summery_incomerel = view.findViewById(R.id.summeryincomerel);
        summery_nutritionrel = view.findViewById(R.id.summerynutritionrel);

        summeryImg1 = view.findViewById(R.id.summeryimg1);
        summeryImg2 = view.findViewById(R.id.summeryimg2);
        summeryageimg = view.findViewById(R.id.summeryageimg);
        summery_diseaseimg = view.findViewById(R.id.summerydiseaseimg);
        summery_incomeimg = view.findViewById(R.id.summeryincomeimg);
        summery_nutritionimg = view.findViewById(R.id.summerynutritionimg);

        summerycard1 = view.findViewById(R.id.villagesummerycard);
        summerycard2 = view.findViewById(R.id.summerypopulationcard);
        summeryagecard = view.findViewById(R.id.summeryagecard);
        summery_diseasecard = view.findViewById(R.id.summerydiseasecard);
        summery_incomecard = view.findViewById(R.id.summeryincomecard);
        summery_nutritioncard = view.findViewById(R.id.summerynutritioncard);

        spinner_literate = view.findViewById(R.id.summeryliteratespinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.summery_literate, R.layout.summeryspinneritem);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_literate.setAdapter(adapter);
        spinner_literate.setOnItemSelectedListener(summery_spinner);

        spinner_disease = view.findViewById(R.id.summerydiseasespinner);
        ArrayAdapter<CharSequence> adapter_disease = ArrayAdapter.createFromResource(getContext(),
                R.array.summery_disease, R.layout.summeryspinneritem);
        adapter_disease.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_disease.setAdapter(adapter_disease);
        spinner_disease.setOnItemSelectedListener(summery_spinner_disease);

        spinner_income = view.findViewById(R.id.summeryincomespinner);
        ArrayAdapter<CharSequence> adapter_income = ArrayAdapter.createFromResource(getContext(),
                R.array.summery_income, R.layout.summeryspinneritem);
        adapter_income.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_income.setAdapter(adapter_income);
        spinner_income.setOnItemSelectedListener(summery_spinner_income);

        spinner_nutrition = view.findViewById(R.id.summerynutritionspinner);
        ArrayAdapter<CharSequence> adapter_nutrition = ArrayAdapter.createFromResource(getContext(),
                R.array.summery_nutrition, R.layout.summeryspinneritem);
        adapter_nutrition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_nutrition.setAdapter(adapter_nutrition);
        spinner_nutrition.setOnItemSelectedListener(summery_spinner_nutrition);


        basicinfoImg.setOnClickListener(listener);
        basicinfocard.setOnClickListener(listener);

        summerycard1.setOnClickListener(listener2);
        summeryImg1.setOnClickListener(listener2);

        summerycard2.setOnClickListener(listener3);
        summeryImg2.setOnClickListener(listener3);

        summeryagecard.setOnClickListener(listenerforsummeryage);
        summeryageimg.setOnClickListener(listenerforsummeryage);

        summery_diseaseimg.setOnClickListener(listenerforsummerydisease);
        summery_diseasecard.setOnClickListener(listenerforsummerydisease);

        summery_incomecard.setOnClickListener(listenerforsummeryincome);
        summery_incomeimg.setOnClickListener(listenerforsummeryincome);

        summery_nutritioncard.setOnClickListener(listenerforsummerynutrition);
        summery_incomeimg.setOnClickListener(listenerforsummerynutrition);

        return view;
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(basicinforellayout.getVisibility() == View.GONE)
            {
                basicinforellayout.setVisibility(View.VISIBLE);
                basicinfoImg.setImageResource(R.drawable.uparrow);
            }
            else
            {
                basicinforellayout.setVisibility(View.GONE);
                basicinfoImg.setImageResource(R.drawable.downarrow);
            }
        }
    };

    View.OnClickListener listenerforsummeryage = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(summeryagerel.getVisibility() == View.GONE)
            {
                summeryagerel.setVisibility(View.VISIBLE);
                summeryageimg.setImageResource(R.drawable.uparrow);
            }
            else
            {
                summeryagerel.setVisibility(View.GONE);
                summeryageimg.setImageResource(R.drawable.downarrow);
            }
        }
    };

    View.OnClickListener listenerforsummerynutrition = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(summery_nutritionrel.getVisibility() == View.GONE)
            {
                summery_nutritionrel.setVisibility(View.VISIBLE);
                summery_nutritionimg.setImageResource(R.drawable.uparrow);
            }
            else
            {
                summery_nutritionrel.setVisibility(View.GONE);
                summery_nutritionimg.setImageResource(R.drawable.downarrow);
            }
        }
    };

    View.OnClickListener listenerforsummerydisease = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(summery_diseaserel.getVisibility() == View.GONE)
            {
                summery_diseaserel.setVisibility(View.VISIBLE);
                summery_diseaseimg.setImageResource(R.drawable.uparrow);
            }
            else
            {
                summery_diseaserel.setVisibility(View.GONE);
                summery_diseaseimg.setImageResource(R.drawable.downarrow);
            }
        }
    };

    View.OnClickListener listenerforsummeryincome = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(summery_incomerel.getVisibility() == View.GONE)
            {
                summery_incomerel.setVisibility(View.VISIBLE);
                summery_incomeimg.setImageResource(R.drawable.uparrow);
            }
            else
            {
                summery_incomerel.setVisibility(View.GONE);
                summery_incomeimg.setImageResource(R.drawable.downarrow);
            }
        }
    };

    View.OnClickListener listener2 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(summeryrel1.getVisibility() == View.GONE)
            {
                summeryrel1.setVisibility(View.VISIBLE);
                summeryImg1.setImageResource(R.drawable.uparrow);
            }
            else
            {
                summeryrel1.setVisibility(View.GONE);
                summeryImg1.setImageResource(R.drawable.downarrow);
            }
        }
    };

    View.OnClickListener listener3 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(summeryrel2.getVisibility() == View.GONE)
            {
                summeryrel2.setVisibility(View.VISIBLE);
                summeryImg2.setImageResource(R.drawable.uparrow);
            }
            else
            {
                summeryrel2.setVisibility(View.GONE);
                summeryImg2.setImageResource(R.drawable.downarrow);
            }
        }
    };

    AdapterView.OnItemSelectedListener summery_spinner = new AdapterView.OnItemSelectedListener() {


        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if(position == 0)
            {
                male_literacy.setText("1234");
                female_literacy.setText("3234");
                others_literacy.setText("3848");
            }
            else if(position  == 1)
            {
                male_literacy.setText("1");
                female_literacy.setText("32");
                others_literacy.setText("384");
            }
            else if(position  == 2)
            {
                male_literacy.setText("23");
                female_literacy.setText("323");
                others_literacy.setText("38");
            }
            else if (position == 3)
            {
                male_literacy.setText("1");
                female_literacy.setText("3");
                others_literacy.setText("38");
            }
            else if(position  == 4)
            {
                male_literacy.setText("134234");
                female_literacy.setText("32434");
                others_literacy.setText("3844   8");
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            male_literacy.setText("1234");
            female_literacy.setText("3234");
            others_literacy.setText("3848");
        }
    };

    AdapterView.OnItemSelectedListener summery_spinner_nutrition = new AdapterView.OnItemSelectedListener() {


        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if(position == 0)
            {
                male_nutrition.setText("1234");
                female_nutrition.setText("3234");
                others_nutrition.setText("3848");
            }
            else if(position  == 1)
            {
                male_nutrition.setText("1");
                female_nutrition.setText("32");
                others_nutrition.setText("384");
            }
            else if(position  == 2)
            {
                male_nutrition.setText("23");
                female_nutrition.setText("323");
                others_nutrition.setText("38");
            }

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            male_nutrition.setText("1234");
            female_nutrition.setText("3234");
            others_nutrition.setText("3848");
        }
    };

    AdapterView.OnItemSelectedListener summery_spinner_disease = new AdapterView.OnItemSelectedListener() {


        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if(position == 0)
            {
                male_disease.setText("1234");
                female_disease.setText("3234");
                others_disease.setText("3848");
            }
            else if(position  == 1)
            {
                male_disease.setText("1");
                female_disease.setText("32");
                others_disease.setText("384");
            }
            else if(position  == 2)
            {
                male_disease.setText("23");
                female_disease.setText("323");
                others_disease.setText("38");
            }
            else if (position == 3)
            {
                male_disease.setText("1");
                female_disease.setText("3");
                others_disease.setText("38");
            }
            else if(position  == 4)
            {
                male_disease.setText("134234");
                female_disease.setText("32434");
                others_disease.setText("38448");
            }
            else if(position  == 4)
            {
                male_disease.setText("134234");
                female_disease.setText("32434");
                others_disease.setText("38448");
            }
            else if(position  == 4)
            {
                male_disease.setText("134234");
                female_disease.setText("32434");
                others_disease.setText("3848");
            }
            else if(position  == 4)
            {
                male_disease.setText("134234");
                female_disease.setText("32434");
                others_disease.setText("384 8");
            }
            else if(position  == 4)
            {
                male_disease.setText("134234");
                female_disease.setText("32434");
                others_disease.setText("38448");
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            male_disease.setText("1234");
            female_disease.setText("3234");
            others_disease.setText("3848");
        }
    };

    AdapterView.OnItemSelectedListener summery_spinner_income = new AdapterView.OnItemSelectedListener() {


        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if(position == 0)
            {
                income_text.setText("1234");

            }
            else if(position  == 1)
            {
                income_text.setText("1");

            }
            else if(position  == 2)
            {
                income_text.setText("23");

            }
            else if (position == 3)
            {
                income_text.setText("1");

            }
            else if(position  == 4)
            {
                income_text.setText("Unavailable");

            }

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            income_text.setText("1234");

        }
    };
}
