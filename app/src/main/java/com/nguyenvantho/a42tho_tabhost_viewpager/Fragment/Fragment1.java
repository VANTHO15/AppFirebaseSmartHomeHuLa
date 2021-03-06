package com.nguyenvantho.a42tho_tabhost_viewpager.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nguyenvantho.a42tho_tabhost_viewpager.R;

public class Fragment1 extends Fragment implements CompoundButton.OnCheckedChangeListener, View.OnClickListener {
    TextView txtQuat,txtCua,txtRem,txtNhietDo,txtKhiGa;
    ToggleButton tgQuat,tgCua,tgRem,tgDenKhach,tgDenNgu,tgDenBep;
    RadioGroup rgchedo;
    RadioButton rbtudong,rbdieukhien;
    String TuDong="OFF";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.layout_fragment1,container,false);

        txtQuat= view.<TextView>findViewById(R.id.txtquat);
        txtCua = view.<TextView>findViewById(R.id.txtCua);
        txtRem = view.<TextView>findViewById(R.id.txtrem);
        tgQuat= view.<ToggleButton>findViewById(R.id.tgquat);
        tgCua= view.<ToggleButton>findViewById(R.id.tgCua);
        tgRem= view.<ToggleButton>findViewById(R.id.tgrem);
        tgDenKhach= view.<ToggleButton>findViewById(R.id.tgdenKhach);
        tgDenNgu= view.<ToggleButton>findViewById(R.id.tgDenNgu);
        tgDenBep= view.<ToggleButton>findViewById(R.id.tgDenBep);
        txtNhietDo= view.<TextView>findViewById(R.id.tvnhietdo);
        txtKhiGa= view.<TextView>findViewById(R.id.tvKhiGa);
        rgchedo= view.<RadioGroup>findViewById(R.id.rgchedo);
        rbtudong= view.<RadioButton>findViewById(R.id.rbtudong);
        rbdieukhien= view.<RadioButton>findViewById(R.id.rbdieukhien);


        tgQuat.setOnCheckedChangeListener(this);
        tgCua.setOnCheckedChangeListener(this);
        tgRem.setOnCheckedChangeListener(this);
        tgDenKhach.setOnCheckedChangeListener(this);
        tgDenNgu.setOnCheckedChangeListener(this);
        tgDenBep.setOnCheckedChangeListener(this);
        rbtudong.setOnClickListener(this);
        rbdieukhien.setOnClickListener(this);

        ///// ?????c nhi???t ????? v?? kh?? ga
        ReadDataToFirebase();


        return view;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId())
        {
            case R.id.tgquat:
            {
                if(isChecked)
                {
                    WriteDataToFirebase("Quat" ,  "ON");
                    Toast.makeText(getActivity(),"Qu???t B???t !",Toast.LENGTH_SHORT).show();
                    txtQuat.setText("Qu???t B???t");
                }
                else
                {
                    WriteDataToFirebase("Quat" ,  "OFF");
                    Toast.makeText(getActivity(),"Qu???t T???t !",Toast.LENGTH_SHORT).show();
                    txtQuat.setText("Qu???t T???t");
                }
                break;
            }
            case R.id.tgCua:
            {
                if(isChecked)
                {
                    WriteDataToFirebase("Cua" ,  "ON");
                    Toast.makeText(getActivity(),"M??? C???a !",Toast.LENGTH_SHORT).show();
                    txtCua.setText("C???a M???    ");
                }
                else
                {
                    WriteDataToFirebase("Cua" ,  "OFF");
                    Toast.makeText(getActivity(),"????ng C???a !",Toast.LENGTH_SHORT).show();
                    txtCua.setText("C???a ????ng");
                }
                break;
            }
            case R.id.tgrem:
            {
                if(isChecked)
                {
                    WriteDataToFirebase("Rem" ,  "ON");
                    Toast.makeText(getActivity(),"M??? R??m !",Toast.LENGTH_SHORT).show();
                    txtRem.setText("R??m M???    ");
                }
                else
                {
                    WriteDataToFirebase("Rem" ,  "OFF");
                    Toast.makeText(getActivity(),"????ng R??m !",Toast.LENGTH_SHORT).show();
                    txtRem.setText("R??m ????ng");
                }
                break;
            }
            case R.id.tgdenKhach:
            {
                if(isChecked)
                {
                    WriteDataToFirebase("DenKhach" ,  "ON");
                    Toast.makeText(getActivity(),"????n Ph??ng Kh??ch B???t !",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    WriteDataToFirebase("DenKhach" ,  "OFF");
                    Toast.makeText(getActivity(),"????n Ph??ng Kh??ch T???t !",Toast.LENGTH_SHORT).show();
                }
                break;
            }
            case R.id.tgDenNgu:
            {
                if(isChecked)
                {
                    WriteDataToFirebase("DenNgu" ,  "ON");
                    Toast.makeText(getActivity(),"????n Ph??ng Ng??? B???t !",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    WriteDataToFirebase("DenNgu" ,  "OFF");
                    Toast.makeText(getActivity(),"????n Ph??ng Ng??? T???t !",Toast.LENGTH_SHORT).show();
                }
                break;
            }
            case R.id.tgDenBep:
            {
                if(isChecked)
                {
                    WriteDataToFirebase("DenBep" ,  "ON");
                    Toast.makeText(getActivity(),"????n Ph??ng B???p B???t !",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    WriteDataToFirebase("DenBep" ,  "OFF");
                    Toast.makeText(getActivity(),"????n Ph??ng B???p T???t !",Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }
    void WriteDataToFirebase(String ??uongdan, String Data)
    {
        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child(??uongdan).setValue(Data);
    }
    void ReadDataToFirebase()
    {
        DatabaseReference NhietDoData,KhiGaData,CuaData,DenBepData,DenKhachData,DenNguData,QuatData,RemData;
        ///////////////////////DenNguData
        DenNguData = FirebaseDatabase.getInstance().getReference("DenNgu");
        DenNguData.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String dulieu= (String) snapshot.getValue();
                if(TuDong.equals("ON"))
                {
                    if(dulieu.equals("ON"))
                    {
                        tgDenNgu.setChecked(true);
                    }
                    else
                    {
                        tgDenNgu.setChecked(false);
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        ///////////////////////DenKhachData
        DenKhachData = FirebaseDatabase.getInstance().getReference("DenKhach");
        DenKhachData.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String dulieu= (String) snapshot.getValue();
                if(TuDong.equals("ON"))
                {
                    if(dulieu.equals("ON"))
                    {
                        tgDenKhach.setChecked(true);
                    }
                    else
                    {
                        tgDenKhach.setChecked(false);
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        ///////////////////////DenBepData
        DenBepData = FirebaseDatabase.getInstance().getReference("DenBep");
        DenBepData.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String dulieu= (String) snapshot.getValue();
                if(TuDong.equals("ON"))
                {
                    if(dulieu.equals("ON"))
                    {
                        tgDenBep.setChecked(true);
                    }
                    else
                    {
                        tgDenBep.setChecked(false);
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        ///////////////////////RemData
        RemData = FirebaseDatabase.getInstance().getReference("Rem");
        RemData.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String dulieu= (String) snapshot.getValue();
                if(TuDong.equals("ON"))
                {
                    if(dulieu.equals("ON"))
                    {
                        txtRem.setText("R??m M???    ");
                        tgRem.setChecked(true);
                    }
                    else
                    {
                        txtRem.setText("R??m ????ng");
                        tgRem.setChecked(false);
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        ///////////////////////QuatData
        QuatData = FirebaseDatabase.getInstance().getReference("Quat");
        QuatData.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String dulieu= (String) snapshot.getValue();
                if(TuDong.equals("ON"))
                {
                    if(dulieu.equals("ON"))
                    {
                        txtQuat.setText("Qu???t B???t");
                        tgQuat.setChecked(true);
                    }
                    else
                    {
                        txtQuat.setText("Qu???t T???t");
                        tgQuat.setChecked(false);
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        ///////////////////////CuaData
        CuaData = FirebaseDatabase.getInstance().getReference("Cua");
        CuaData.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String dulieu= (String) snapshot.getValue();
                if(TuDong.equals("ON"))
                {
                    if(dulieu.equals("ON"))
                    {
                        txtCua.setText("C???a M???    ");
                        tgCua.setChecked(true);
                    }
                    else
                    {
                        txtCua.setText("C???a ????ng");
                        tgCua.setChecked(false);
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        ///////////////////////Nhi???t ?????
        NhietDoData = FirebaseDatabase.getInstance().getReference("NhietDo");
        NhietDoData.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String dulieu= (String) snapshot.getValue();
                txtNhietDo.setText(dulieu+" C");

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        /////////////////////////// kh?? ga
        KhiGaData = FirebaseDatabase.getInstance().getReference("KhiGa");
        KhiGaData.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String dulieu= (String) snapshot.getValue();
                txtKhiGa.setText(dulieu+" %");
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (id)
        {
            case R.id.rbdieukhien:
            {
                TuDong="OFF";
                WriteDataToFirebase("TuDong" ,  "OFF");
                Toast.makeText(getActivity(),"??i???u khi???n !",Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.rbtudong:
            {
                TuDong="ON";
                WriteDataToFirebase("TuDong" ,  "ON");
                Toast.makeText(getActivity(),"T??? ?????ng!",Toast.LENGTH_SHORT).show();
                break;
            }
        }
    }
}
