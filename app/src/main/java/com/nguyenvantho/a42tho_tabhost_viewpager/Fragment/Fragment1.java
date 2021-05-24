package com.nguyenvantho.a42tho_tabhost_viewpager.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
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

public class Fragment1 extends Fragment implements CompoundButton.OnCheckedChangeListener {
    TextView txtQuat,txtCua,txtRem,txtNhietDo,txtKhiGa;
    ToggleButton tgQuat,tgCua,tgRem,tgDenKhach,tgDenNgu,tgDenBep;

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

        tgQuat.setOnCheckedChangeListener(this);
        tgCua.setOnCheckedChangeListener(this);
        tgRem.setOnCheckedChangeListener(this);
        tgDenKhach.setOnCheckedChangeListener(this);
        tgDenNgu.setOnCheckedChangeListener(this);
        tgDenBep.setOnCheckedChangeListener(this);

        ///// đọc nhiệt độ và khí ga
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
                    Toast.makeText(getActivity(),"Quạt Bật !",Toast.LENGTH_SHORT).show();
                    txtQuat.setText("Quạt Bật");
                }
                else
                {
                    WriteDataToFirebase("Quat" ,  "OFF");
                    Toast.makeText(getActivity(),"Quạt Tắt !",Toast.LENGTH_SHORT).show();
                    txtQuat.setText("Quạt Tắt");
                }
                break;
            }
            case R.id.tgCua:
            {
                if(isChecked)
                {
                    WriteDataToFirebase("Cua" ,  "ON");
                    Toast.makeText(getActivity(),"Mở Cửa !",Toast.LENGTH_SHORT).show();
                    txtCua.setText("Cửa Mở    ");
                }
                else
                {
                    WriteDataToFirebase("Cua" ,  "OFF");
                    Toast.makeText(getActivity(),"Đóng Cửa !",Toast.LENGTH_SHORT).show();
                    txtCua.setText("Cửa Đóng");
                }
                break;
            }
            case R.id.tgrem:
            {
                if(isChecked)
                {
                    WriteDataToFirebase("Rem" ,  "ON");
                    Toast.makeText(getActivity(),"Mở Rèm !",Toast.LENGTH_SHORT).show();
                    txtRem.setText("Rèm Mở    ");
                }
                else
                {
                    WriteDataToFirebase("Rem" ,  "OFF");
                    Toast.makeText(getActivity(),"Đóng Rèm !",Toast.LENGTH_SHORT).show();
                    txtRem.setText("Rèm Đóng");
                }
                break;
            }
            case R.id.tgdenKhach:
            {
                if(isChecked)
                {
                    WriteDataToFirebase("DenKhach" ,  "ON");
                    Toast.makeText(getActivity(),"Đèn Phòng Khách Bật !",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    WriteDataToFirebase("DenKhach" ,  "OFF");
                    Toast.makeText(getActivity(),"Đèn Phòng Khách Tắt !",Toast.LENGTH_SHORT).show();
                }
                break;
            }
            case R.id.tgDenNgu:
            {
                if(isChecked)
                {
                    WriteDataToFirebase("DenNgu" ,  "ON");
                    Toast.makeText(getActivity(),"Đèn Phòng Ngủ Bật !",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    WriteDataToFirebase("DenNgu" ,  "OFF");
                    Toast.makeText(getActivity(),"Đèn Phòng Ngủ Tắt !",Toast.LENGTH_SHORT).show();
                }
                break;
            }
            case R.id.tgDenBep:
            {
                if(isChecked)
                {
                    WriteDataToFirebase("DenBep" ,  "ON");
                    Toast.makeText(getActivity(),"Đèn Phòng Bếp Bật !",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    WriteDataToFirebase("DenBep" ,  "OFF");
                    Toast.makeText(getActivity(),"Đèn Phòng Bếp Tắt !",Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }
    void WriteDataToFirebase(String Đuongdan, String Data)
    {
        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child(Đuongdan).setValue(Data);
    }
    void ReadDataToFirebase()
    {
        DatabaseReference NhietDoData,KhiGaData;
        ///////////////////////Nhiệt độ
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
        /////////////////////////// khí ga
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

}
