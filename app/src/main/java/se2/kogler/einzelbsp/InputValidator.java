package se2.kogler.einzelbsp;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;

import java.util.ArrayList;

public class InputValidator implements TextWatcher {

    // array of dependent buttons that gets toggled based on the text input
    ArrayList<Button> toToggle;

    public InputValidator(ArrayList<Button> toToggle) {
        this.toToggle = toToggle;
    }

    // triggered on each change event - enables or disabled the buttons
    @Override
    public void onTextChanged(CharSequence s, int start, int count, int after) {
        String currentInput = s.toString();
        Log.i("validator", "Current input: " + currentInput +
                "; length: " + currentInput.trim().length());

        if(currentInput.trim().length() != 0) {
            this.setAllButtons(this.toToggle, true);
        } else {
            this.setAllButtons(this.toToggle, false);
        }
    }

    // sets all buttons to en- or disabled
    private void setAllButtons(ArrayList<Button> buttons, boolean enabled) {
        for(Button b : buttons) {
            b.setEnabled(enabled);
        }
    }

    // unused methods that have to be implemented due to the interface
    @Override
    public void beforeTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
    }

}
