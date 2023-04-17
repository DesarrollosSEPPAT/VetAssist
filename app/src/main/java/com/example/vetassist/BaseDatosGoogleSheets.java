package com.example.vetassist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

public class BaseDatosGoogleSheets extends AppCompatActivity {
    private static final String URL_EXCEL = "https://drive.google.com/uc?export=download&id=1Ro0iPNTMchWm5ORsCwNKCMSSQD4CpeKX";
    private TextView mDataTextView;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_datos_google_sheets);
        mDataTextView = findViewById(R.id.datatextview);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        InputStream stream = new URL(URL_EXCEL).openStream();
                        XSSFWorkbook workbook = new XSSFWorkbook(stream);
                        XSSFSheet sheet = workbook.getSheetAt(0);
                        String value = sheet.getRow(0).getCell(0).getStringCellValue();

                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                mDataTextView.setText(value);
                            }
                        });

                        stream.close();
                        workbook.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    try {
                        Thread.sleep(8000); // Espera 5 segundos antes de leer el archivo de nuevo
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        thread.start();
    }
}