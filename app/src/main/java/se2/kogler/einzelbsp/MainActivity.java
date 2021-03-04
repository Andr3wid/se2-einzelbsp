package se2.kogler.einzelbsp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnSubmit;
    private Button btnCalculate;
    private EditText inpStudentNumber;
    private TextView txtResultDisplay;
    private ArrayList<Button> buttons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // find all view elements that are needed for interaction by their id
        this.btnSubmit = findViewById(R.id.btn_submit);
        this.btnCalculate = findViewById(R.id.btn_calculate);
        this.inpStudentNumber = findViewById(R.id.inp_studentNumber);
        this.txtResultDisplay = findViewById(R.id.txt_result);

        // initialize arraylist and add buttons so form validation can take place
        this.buttons = new ArrayList<>();
        this.buttons.add(btnSubmit);
        this.buttons.add(btnCalculate);

        // set buttons to disabled initially
        this.btnSubmit.setEnabled(false);
        this.btnCalculate.setEnabled(false);

        // associate event listeners
        this.btnSubmit.setOnClickListener(this);
        this.btnCalculate.setOnClickListener(this);
        this.inpStudentNumber.addTextChangedListener(new InputValidator(this.buttons));
    }

    // gets executed when the submit button gets clicked
    @Override
    public void onClick(View v) {
         if(v.getId() == R.id.btn_submit) {
            handleServerCommunication();
         }

         if(v.getId() == R.id.btn_calculate) {
            handleCalculation();
         }

    }

    // button handler functions
    private void handleServerCommunication() {
        // network communication in separate thread
        ServerCommunicator netcom = new ServerCommunicator(
                "se2-isys.aau.at",
                53212,
                getUserInput());

        Thread t = new Thread(netcom);
        t.start();

        try {
            t.join();
            this.txtResultDisplay.setText(netcom.getServerResponse());
        } catch (InterruptedException e) {
            Log.e("network-thread", "Error while waiting for network thread.");
            this.txtResultDisplay.setText("error");
            e.printStackTrace();
        }
    }

    private void handleCalculation() {
        Calculation calc = new Calculation(Integer.parseInt(getUserInput()));
        Thread t = new Thread(calc);
        t.start();

        try {
            t.join();
            this.txtResultDisplay.setText(calc.getResult());
        } catch (InterruptedException e) {
            Log.e("calc-thread", "Error while waiting for calculation thread.");
            this.txtResultDisplay.setText("error");
            e.printStackTrace();
        }
    }

    // getter & setter section
    public String getUserInput() {
        return inpStudentNumber.getText().toString();
    }
}
