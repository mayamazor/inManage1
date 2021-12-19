package com.example.inmanage1;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.text.method.HideReturnsTransformationMethod;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.regex.Pattern;

public class RegisterFragment extends Fragment {
    View view;
    TextView Register;
    Button Continue;
    EditText fulLName, Email, Password,Password2;
    String pass1,pass2;
    int num;
    Dialog dialog;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_register, container, false);

        Register=view.findViewById(R.id.register_register);
        Continue=view.findViewById(R.id.register_continue);
        fulLName=view.findViewById(R.id.register_fullName);
        Email=view.findViewById(R.id.register_email);
        Password=view.findViewById(R.id.register_password);
        Password2=view.findViewById(R.id.register_password2);

        dialog=new Dialog(getContext());


        Continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pass1=Password.getText().toString();
                pass2=Password2.getText().toString();
                validateName();
                validateEmail();
                validatePassword();
                if(validateName() && validateEmail() && validatePassword() && SamePassword())
                showDialog();
            }
        });
        return view;
    }


   private void showDialog() {
   dialog.setContentView(R.layout.dialog);
   dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
       Random random=new Random();
       int low = 10;
       int high = 100;
        for(int i=10;i<=99;i++){
             num=random.nextInt(high-low)+low ;
        }
   String number=Integer.toString(num);
   TextView code=dialog.findViewById(R.id.dialog_text);
   code.setText(number);

   EditText setCode=dialog.findViewById(R.id.dialog_verify);
   Button finish=dialog.findViewById(R.id.dialog_button);
   ImageButton button=dialog.findViewById(R.id.dialog_close);

   finish.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {
           String FullName=fulLName.getText().toString();
           Bundle bundle=new Bundle();
           bundle.putString("name",FullName);
          String verify=setCode.getText().toString();
           if(verify.equals(number))
           {
               dialog.dismiss();
               Navigation.findNavController(view).navigate(R.id.action_registerFragment_to_secondFragment,bundle);
           }
           else
               setCode.setError("please try again");

       }
   });
   button.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {
           dialog.dismiss();
       }
   });
   dialog.show();
   }
//{0,}
    private Boolean validateName(){
        String name=fulLName.getText().toString();
        if (!name.matches( "^\\p{L}+[\\p{L}\\p{Z}\\p{P}]{0,}")) {
            fulLName.setError("please enter only letters");
            if(name.isEmpty())
                fulLName.setError("field can not be empty");
            return false;
        }
        return true;
    }
    private Boolean validateEmail(){
        String email = Email.getText().toString();
       if(email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
           Email.setError("Enter valid Email address!");
           return false;
       }
        return true;

    }
    private Boolean validatePassword()
    {
        String password=Password.getText().toString();
        if (password.isEmpty()) {
            Password.setError("Field can not be empty");
            return false;
        }
        return true;
    }
    private Boolean SamePassword(){
        if(!pass1.equals(pass2)){
            Password2.setError("The password does not match");
            return false;
        }
        return true;
    }
}