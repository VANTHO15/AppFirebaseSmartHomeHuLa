package com.nguyenvantho.a42tho_tabhost_viewpager.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.nguyenvantho.a42tho_tabhost_viewpager.R;

import static com.nguyenvantho.a42tho_tabhost_viewpager.R.id.btnDangKi;

public class FragmentDangKi extends Fragment implements View.OnClickListener {
    Button  btnDK;
    EditText edtenDK, edmatkhauDK,edmatkhaulaiDK;
    public FirebaseAuth firebaseAuth;
    public FirebaseAuth.AuthStateListener authStateListener;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.layout_fragment_dangky,container,false);

        btnDK= view.<Button>findViewById(btnDangKi);
        edtenDK = view.<EditText>findViewById(R.id.edDiaChiEmailDK);
        edmatkhauDK= view.<EditText>findViewById(R.id.edMatKhauDK);
        edmatkhaulaiDK= view.<EditText>findViewById(R.id.edNhapLaiMatKhauDK);

        btnDK.setOnClickListener(this);

        // init instance of firebaseAuth
        firebaseAuth= FirebaseAuth.getInstance();    // dăng kí firebase
        // set up listener Firebase sign-in

        return view;
    }

    @Override
    public void onClick(View v) {
        String TaiKhoan= edtenDK.getText().toString().trim();
        String MatKhau=edmatkhauDK.getText().toString().trim();
        String MatKhauNhaplai=edmatkhaulaiDK.getText().toString().trim();
        boolean isvalid=validateForm();
        if(isvalid)
        {
            // nhap day du roi
            signUp(TaiKhoan,MatKhau);
            edtenDK.setText("");
            edmatkhauDK.setText("");
            edmatkhaulaiDK.setText("");
        }
        else
        {
            Toast.makeText(getActivity(),"Xem Lại Thông Tin !",Toast.LENGTH_SHORT).show();
        }

    }
    private void signUp(String email,String password)
    {
        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(getActivity(),"Đăng Kí Thành Công !",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getActivity(),"Đăng Kí Lỗi Rồi !",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private boolean validateForm()
    {
        String TaiKhoan=edtenDK.getText().toString().trim();
        String MatKhau=edmatkhauDK.getText().toString().trim();
        String MatKhauNhaplai=edmatkhaulaiDK.getText().toString().trim();
       // Toast.makeText(getActivity(),MatKhau+ "xxx"+ MatKhauNhaplai,Toast.LENGTH_SHORT).show();
        if(!TaiKhoan.isEmpty() && (!MatKhau.isEmpty()) && MatKhau.equals(MatKhauNhaplai))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
