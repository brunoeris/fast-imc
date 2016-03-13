package com.example.android.fastimc;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.app.AlertDialog;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    double height = 0;
    double weight = 0;
    double h_imc[] = {20.7, 26.4};
    double m_imc[] = {19.1, 25.8};

    String gender = "";

    RadioGroup radioGroup;
    RadioButton male, female;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the imc button is clicked.
     */

    public void submitImc(View view) {

        EditText nameToCard = (EditText)findViewById(R.id.name);
        String inputName = nameToCard.getText().toString();

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

        male = (RadioButton) findViewById(R.id.radio_male);
        female = (RadioButton) findViewById(R.id.radio_female);

        int selectedId = radioGroup.getCheckedRadioButtonId();

        if(selectedId == male.getId()) {
            gender = "Masculino";
        } else if(selectedId == female.getId()) {
            gender = "Feminino";
        } else {
            Toast.makeText(this, "Nenhum selecionado", Toast.LENGTH_SHORT).show();
        }


        if ((inputName.length() < 1)) {

            nameToCard.setError("Nome é obrigatório");

        } else {

            EditText inputHeight = (EditText)findViewById(R.id.heightInput);

            EditText inputWeight = (EditText)findViewById(R.id.weightInput);

            if(inputHeight.getText().length() == 0 || inputWeight.getText().length() == 0) {

                inputHeight.setError("Altura é obrigatório");
                inputWeight.setError("Peso é obrigatório");

            } else {

                height = Double.parseDouble(inputHeight.getText().toString());
                weight = Double.parseDouble(inputWeight.getText().toString());

                String imcMessage = createIMCSumary(calculateIMC(height, weight), gender, height, weight, inputName);

                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                alertDialog.setTitle("Resultado IMC");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });

                alertDialog.setMessage(imcMessage);
                alertDialog.show();

            }

        }

    }

    /**
     * Calculates the imc
     * @param inputHeight
     * @param inputWeight
     * @return
     */

    private double calculateIMC(double inputHeight, double inputWeight) {
        return inputWeight / ((inputHeight)*(inputHeight));
    }

    /**
     * Create summary of the order.
     * @param imc
     * @param gender
     * @param height
     * @param weight
     * @param addName
     * @return
     */

    private String createIMCSumary(double imc, String gender, double height, double weight, String addName){

        String resultado = "";

        if (gender == "Masculino") {

            if (imc < h_imc[0]) {
                resultado = "\n- O seu IMC é: "+imc+" Você está abaixo do peso!";
            }

            if ((imc > h_imc[0]) && (imc < h_imc[1])) {
                resultado = "\n- O seu IMC é: "+imc+" Você está no peso ideal!";
            }

            if (imc > h_imc[1]) {
                resultado = "\n- O seu IMC é: "+imc+" Você está acima do peso!";
            }

        }

        if (gender == "Feminino") {

            if (imc < m_imc[0]) {
                resultado = "\n- O seu IMC é: "+imc+" Você está abaixo do peso!";
            }

            if ((imc > m_imc[0]) && (imc < m_imc[1])) {
                resultado = "\n- O seu IMC é: "+imc+" Você está no peso ideal!";
            }

            if (imc > m_imc[1]) {
                resultado = "\n- O seu IMC é: "+imc+" Você está acima do peso!";
            }

        }

        String imcMessage = "Nome: "+ addName;
        imcMessage += "\nSexo: " + gender;
        imcMessage += "\nAltura: " + height;
        imcMessage += "\nPeso: " + weight;
        imcMessage += "\n" + resultado;

        return imcMessage;
    }

}
