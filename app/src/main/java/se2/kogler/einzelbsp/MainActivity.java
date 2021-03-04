package se2.kogler.einzelbsp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnSubmit;
    private EditText inpStudentNumber;
    private TextView txtServerResp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // find all view elements that are needed for interaction by their id
        this.btnSubmit = (Button) findViewById(R.id.btn_submit);
        this.inpStudentNumber = (EditText) findViewById(R.id.inp_studentNumber);
        this.txtServerResp = (TextView) findViewById(R.id.txt_serverResponse);

        // associate onclick listener
        this.btnSubmit.setOnClickListener(this);
    }

    // gets executed when the submit button gets clicked
    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btn_submit:
                this.txtServerResp
                        .setText("You entered: " + this.inpStudentNumber.getText().toString());
                break;
        }
    }

    // getter & setter section
    public Button getBtnSubmit() {
        return btnSubmit;
    }

    public EditText getInpStudentNumber() {
        return inpStudentNumber;
    }

    public TextView getTxtServerResp() {
        return txtServerResp;
    }
}