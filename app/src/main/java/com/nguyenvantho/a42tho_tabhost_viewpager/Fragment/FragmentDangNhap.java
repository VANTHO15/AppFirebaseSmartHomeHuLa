package com.nguyenvantho.a42tho_tabhost_viewpager.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.nguyenvantho.a42tho_tabhost_viewpager.MainActivity;
import com.nguyenvantho.a42tho_tabhost_viewpager.R;

import java.util.Arrays;

public class FragmentDangNhap extends Fragment implements View.OnClickListener {
    EditText edtenDN, edmatKhau;
    Button btnDangNhap;

    public FirebaseAuth firebaseAuth;
    public FirebaseAuth.AuthStateListener authStateListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_dangnhap, container, false);

        btnDangNhap = view.<Button>findViewById(R.id.btnDangNhap);
        edtenDN = view.<EditText>findViewById(R.id.edDiaChiEmailDangNhap);
        edmatKhau = view.<EditText>findViewById(R.id.edMatKhauDangNhap);

        btnDangNhap.setOnClickListener(this::onClick);

        // init instance of firebaseAuth
        firebaseAuth = FirebaseAuth.getInstance();    // dăng kí firebase
        // set up listener Firebase sign-in
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // Login thanh cong
//                    user.getEmail();
//                    user.getUid();

                } else {
                    // fail
                }
            }
        };


        return view;
    }

    @Override
    public void onClick(View v)
    {
                String TaiKhoan = edtenDN.getText().toString().trim();
                String MatKhau = edmatKhau.getText().toString().trim();
                boolean isvalid=validateForm();
                if(isvalid)
                {
                    // nhap day du roi
                    singIn( TaiKhoan, MatKhau);
                }
                else
                {
                    Toast.makeText(getActivity(),"Kiểm Tra Lại Thông Tin !",Toast.LENGTH_SHORT).show();
                }
    }
    private  void singIn(String email,String password)
    {
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(getActivity(),"Đăng Nhập Thành Công !",Toast.LENGTH_SHORT).show();
                    Intent iDangNhapToManHinhChinh= new Intent(getActivity(), MainActivity.class);
                    edtenDN.setText("");
                    edmatKhau.setText("") ;
                    startActivity(iDangNhapToManHinhChinh);
                }
                else
                {
                    Toast.makeText(getActivity(),"Đăng Nhập Thất Bại !",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    public void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if(authStateListener!=null)
        {
            firebaseAuth.removeAuthStateListener(authStateListener);
        }
    }

    private boolean validateForm()
    {
        String TaiKhoan=edtenDN.getText().toString().trim();
        String MatKhau=edmatKhau.getText().toString().trim();
        if(!TaiKhoan.isEmpty() && (!MatKhau.isEmpty()))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

}
