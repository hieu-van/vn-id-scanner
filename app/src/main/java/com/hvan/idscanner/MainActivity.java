/* This file is part of ID Scanner.

ID Scanner is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

ID Scanner is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with ID Scanner.  If not, see <https://www.gnu.org/licenses/>. */

package com.hvan.idscanner;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button scan_bhyt = findViewById(R.id.button_bhyt);
        Button scan_cccd = findViewById(R.id.button_cccd);
        Button about = findViewById(R.id.button_about);

        scan_bhyt.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent_scan = new Intent(MainActivity.this, ScanActivity.class);
                startActivity(intent_scan);
            }
        });

        scan_cccd.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage(getString(R.string.dialog_message))
                        .setTitle(getString(R.string.dialog_title))
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        about.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Intent intent_about = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent_about);
            }
        });
    }
}