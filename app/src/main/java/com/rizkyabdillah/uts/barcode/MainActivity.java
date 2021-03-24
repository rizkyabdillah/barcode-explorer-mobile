package com.rizkyabdillah.uts.barcode;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class MainActivity extends AppCompatActivity {

    private Button btnMulai, btnCara, btnTentangA, btnTentangP, btnClose, btnCloseDesc;
    private ImageView imgBarcode;
    private TextView kode, nama, desc, judul, link;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMulai = (Button) this.findViewById(R.id.bukaCamera);
        btnCara = (Button) this.findViewById(R.id.caraPeng);
        btnTentangA = (Button) this.findViewById(R.id.tentangApp);
        btnTentangP = (Button) this.findViewById(R.id.tentangPeng);

        dialog = new Dialog(MainActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        btnMulai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnMulaiClicked(v);
            }
        });

        btnCara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCustomDialogDesc("", "CARA PENGGUNAAN","Untuk menggunakan aplikasi ini yaitu silahkan anda klik menu MULAI SCAN dan setelah itu anda akan di arahkan menuju kamera scanner, silahkan anda scan barcon yang tertera pada produk, dan secara otomatis sistem akan membaca dan mengenali produk yang di scan");
            }
        });

        btnTentangA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCustomDialogDesc("", "TENTANG APLIKASI", "Aplikasi Barcode Explorer adalah aplikasi untuk mencari nama produk dari barcode produk yang di scan");
            }
        });

        btnTentangP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCustomDialogDesc("www.stiki.ac.id", "TENTANG PENGEMBANG", "Nama : Rizky Abdillah \nNRP : 181111090 \nSupported By STIKI");
                link.setVisibility(View.VISIBLE);
            }
        });
    }

    private void onCustomDialogDesc(String linke, String judule, String description) {
        dialog.setContentView(R.layout.custom_dialog_desc);
        btnCloseDesc = (Button) dialog.findViewById(R.id.btn_close_desc);
        desc = (TextView) dialog.findViewById(R.id.desc);
        judul = (TextView) dialog.findViewById(R.id.judule);
        link = (TextView) dialog.findViewById(R.id.link);
        link.setText(linke);
        desc.setText(description);
        judul.setText(judule);
        btnCloseDesc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void btnMulaiClicked(View v) {
        IntentIntegrator integrator = new IntentIntegrator(MainActivity.this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.PRODUCT_CODE_TYPES);
        integrator.setPrompt("Scan a barcode");
        integrator.setBeepEnabled(false);
        integrator.setBarcodeImageEnabled(true);
        integrator.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                onCustomDialogScan(result.getContents());
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void onCustomDialogScan(String result) {
        Kode kodeClass = new Kode();
        dialog.setContentView(R.layout.custom_dialog);

        btnClose = dialog.findViewById(R.id.btn_close);

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        imgBarcode = (ImageView) dialog.findViewById(R.id.dataImg);
        kode = (TextView) dialog.findViewById(R.id.kode);
        nama = (TextView) dialog.findViewById(R.id.nama);

        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try {
            BitMatrix bitMatrix = multiFormatWriter.encode(result, BarcodeFormat.EAN_13, 500, 200);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            imgBarcode.setImageBitmap(bitmap);
            kode.setText(result);
            nama.setText(kodeClass.searchKode(result));
        } catch (WriterException e) {
            e.printStackTrace();
        }

        dialog.show();
    }
}