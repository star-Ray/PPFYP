package fypproject.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import fypproject.R;
import com.nxp.nfclib.classic.MFClassic;
import com.nxp.nfclib.exceptions.SmartCardException;
import com.nxp.nfclib.icode.ICodeSLI;
import com.nxp.nfclib.icode.ICodeSLIL;
import com.nxp.nfclib.icode.ICodeSLIS;
import com.nxp.nfclib.icode.ICodeSLIX;
import com.nxp.nfclib.icode.ICodeSLIX2;
import com.nxp.nfclib.icode.ICodeSLIXL;
import com.nxp.nfclib.icode.ICodeSLIXS;
import com.nxp.nfclib.ntag.NTag;
import com.nxp.nfclib.ntag.NTag203x;
import com.nxp.nfclib.ntag.NTag210;
import com.nxp.nfclib.ntag.NTag213215216;
import com.nxp.nfclib.ntag.NTag213F216F;
import com.nxp.nfclib.ntag.NTagI2C;
import com.nxp.nfclib.plus.PlusSL1;
import com.nxp.nfclib.ultralight.Ultralight;
import com.nxp.nfclib.ultralight.UltralightC;
import com.nxp.nfclib.ultralight.UltralightEV1;
import com.nxp.nfcliblite.Interface.Inxpnfcliblitecallback;
import com.nxp.nfcliblite.Interface.NxpNfcLibLite;
import com.nxp.nfcliblite.cards.DESFire;
import com.nxp.nfcliblite.cards.Plus;

import java.io.IOException;

public class WriteNFCActivity extends ActionBarActivity {

    private static final String MIME_PLAIN_TEXT = "text/plain";
    private NxpNfcLibLite libInstance = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_nfc);

        if(checkNFCEnabled()){
            libInstance = NxpNfcLibLite.getInstance();
            libInstance.registerActivity(this);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_write_nfc, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (libInstance != null){
            libInstance.startForeGroundDispatch();
        }
    }

    @Override
    protected void onPause() {
        if (libInstance != null){
            libInstance.stopForeGroundDispatch();
        }
        super.onPause();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        libInstance.filterIntent(intent, new Inxpnfcliblitecallback() {
            @Override
            public void onUltraLightCardDetected(Ultralight ultralight) {
            }

            @Override
            public void onClassicCardDetected(MFClassic mfClassic) {

            }

            @Override
            public void onDESFireCardDetected(DESFire desFire) {

            }

            @Override
            public void onUltraLightEV1CardDetected(UltralightEV1 ultralightEV1) {

            }

            @Override
            public void onUltraLightCCardDetected(UltralightC ultralightC) {

            }

            @Override
            public void onPlusCardDetected(Plus plus) {

            }

            @Override
            public void onNTag203xCardDetected(NTag203x nTag203x) {
            }

            @Override
            public void onNTag210CardDetected(NTag210 nTag210) {
            }

            @Override
            public void onNTag213215216CardDetected(NTag213215216 nTag213215216) {
                writeTag(nTag213215216);
            }

            @Override
            public void onNTag213F216FCardDetected(NTag213F216F nTag213F216F) {
            }

            @Override
            public void onNTagI2CCardDetected(NTagI2C nTagI2C) {
            }

            @Override
            public void onICodeSLIDetected(ICodeSLI iCodeSLI) {

            }

            @Override
            public void onICodeSLISDetected(ICodeSLIS iCodeSLIS) {

            }

            @Override
            public void onICodeSLILDetected(ICodeSLIL iCodeSLIL) {

            }

            @Override
            public void onICodeSLIXDetected(ICodeSLIX iCodeSLIX) {

            }

            @Override
            public void onICodeSLIXSDetected(ICodeSLIXS iCodeSLIXS) {

            }

            @Override
            public void onICodeSLIXLDetected(ICodeSLIXL iCodeSLIXL) {

            }

            @Override
            public void onPlusSL1CardDetected(PlusSL1 plusSL1) {

            }

            @Override
            public void onICodeSLIX2Detected(ICodeSLIX2 iCodeSLIX2) {

            }
        });
    }

    public void writeTag(NTag tag){
        try {
            String testmessage = "Test message for tag1";
            NdefRecord record = NdefRecord.createMime(MIME_PLAIN_TEXT, testmessage.getBytes("UTF-8"));
            NdefMessage message = new NdefMessage(record);

            Toast.makeText(this, "connecting...", Toast.LENGTH_SHORT).show();
            tag.connect();
            Toast.makeText(this, "writing...", Toast.LENGTH_SHORT).show();
            tag.writeNDEF(message);
            Toast.makeText(this, "closing...", Toast.LENGTH_SHORT).show();
            tag.close();
            Toast.makeText(this, "done", Toast.LENGTH_SHORT).show();

        } catch (IOException | SmartCardException e) {
            e.printStackTrace();
        }
    }

    public boolean checkNFCEnabled(){
        NfcAdapter nfcAdapter = NfcAdapter.getDefaultAdapter(this);

//        Stop here if device does not support NFC
        if (nfcAdapter == null){
            Toast.makeText(this, "This device does not support NFC.", Toast.LENGTH_LONG).show();
            finish(); //to end this activity and return
            return false;
        }

//        Check if NFC is enabled
        boolean isEnabled = nfcAdapter.isEnabled();
        if (!isEnabled) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.dialog_title_nfc_settings_fail).setMessage(R.string.dialog_message_nfc_fail);
            builder.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                    startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish(); //to end this activity and return
                }
            });
            builder.create().show();
        }
        return isEnabled;
    }


}
