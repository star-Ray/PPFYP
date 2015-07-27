package com.example.arnold.fypproject.Activity;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.FormatException;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.tech.Ndef;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.arnold.fypproject.R;
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
import java.text.Format;

public class WriteNFCActivity extends ActionBarActivity {

    public static final String MIME_TEXT_PLAIN = "text/plain";
    private NxpNfcLibLite libInstance = null;
    private NfcAdapter nfcAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_nfc);

        libInstance = NxpNfcLibLite.getInstance();
        libInstance.registerActivity(this);

        //        initialization
//        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
//        //        Stop here if device does not support NFC
//        if (nfcAdapter == null){
//            Toast.makeText(this, "This device does not support NFC.", Toast.LENGTH_LONG).show();
//            finish();
//        }
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
        /**
         * It's important, that the activity is in the foreground (resumed). Otherwise
         * an IllegalStateException is thrown.
         */
//        setupForegroundDispatch(this, nfcAdapter);
        libInstance.startForeGroundDispatch();
    }

    @Override
    protected void onPause() {
        /**
         * Call this before onPause, otherwise an IllegalArgumentException is thrown as well.
         */
//        stopForegroundDispatch(this, nfcAdapter);
        libInstance.stopForeGroundDispatch();
        super.onPause();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        /**
         * This method gets called, when a new Intent gets associated with the current activity instance.
         * Instead of creating a new activity, onNewIntent will be called. For more information have a look
         * at the documentation.
         *
         * In our case this method gets called, when the user attaches a Tag to the device.
         */

//        String showString = "NFC Tag detected!\nIntent: " + intent.toString();
//        Toast.makeText(this, showString, Toast.LENGTH_LONG).show();


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
                System.out.println("onNTag213215216CardDetected");
                System.out.println("tagname: " + nTag213215216.getTagName());
                System.out.println("type: " + nTag213215216.getType().toString());
                readNTag(nTag213215216);
//                writeTag(nTag213215216);
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

//        System.out.println("here1");
//        Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
//        System.out.println(tag.toString());
//        System.out.println("here2");
//        System.out.println("here3");

//        try {
//            System.out.println("test1");
//            NTag tag = new NTag(){};
//            System.out.println("test2");
//            tag.connect();
//
//            System.out.println("test3");
//            System.out.println("Tagname: " + tag.getTagName());
//
//            NdefMessage message = tag.readNDEF();
//            System.out.println("ndefmessage: " + message.toString());
//
//        } catch (SmartCardException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (FormatException e) {
//            e.printStackTrace();
//        }
    }

    public void readNTag(NTag tag){
        try {
            Toast.makeText(this, "connecting...", Toast.LENGTH_SHORT).show();
            tag.connect();
            Toast.makeText(this, "connected", Toast.LENGTH_SHORT).show();
            NdefMessage message = tag.readNDEF();
            System.out.println("message: " + message.toString());
            Toast.makeText(this, "message: " + message.toString(), Toast.LENGTH_SHORT).show();
            NdefRecord[] records = message.getRecords();
            byte[] data = records[0].getPayload();
            String text = new String(data, "UTF-8");
            System.out.println("message: " + text);
            Toast.makeText(this, "message: " + text, Toast.LENGTH_SHORT).show();

            tag.close();
            Toast.makeText(this, "connection close", Toast.LENGTH_SHORT).show();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SmartCardException e) {
            e.printStackTrace();
        } catch (FormatException e) {
            e.printStackTrace();
        }
    }

    public void writeTag(NTag tag){
        try {
            String testmessage = "This is a testing message for tag1";
            System.out.println("here1");
//            byte[] data = testmessage.getBytes("UTF-8");
            System.out.println("here2");
            NdefRecord record = NdefRecord.createTextRecord("UTF-8", testmessage);
            NdefMessage message = new NdefMessage(record);
            System.out.println("here3");

            Toast.makeText(this, "connecting...", Toast.LENGTH_SHORT).show();
            tag.connect();
            Toast.makeText(this, "writing...", Toast.LENGTH_SHORT).show();
            tag.writeNDEF(message);
            Toast.makeText(this, "closing...", Toast.LENGTH_SHORT).show();
            tag.close();
            Toast.makeText(this, "done", Toast.LENGTH_SHORT).show();

        } catch (IOException e) {
            e.printStackTrace();
        }  catch (SmartCardException e){
            e.printStackTrace();
        }
    }

    /**
     * @param activity The corresponding {@link Activity} requesting the foreground dispatch.
     * @param adapter The {@link NfcAdapter} used for the foreground dispatch.
     */
//    protected static void setupForegroundDispatch(final Activity activity, NfcAdapter adapter) {
//        final Intent intent = new Intent(activity.getApplicationContext(), activity.getClass());
//        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
//
//        final PendingIntent pendingIntent = PendingIntent.getActivity(activity.getApplicationContext(), 0, intent, 0);
//        IntentFilter[] filters = new IntentFilter[1];
//
//        // Notice that this is the same filter as in our manifest.
//        filters[0] = new IntentFilter();
//        filters[0].addAction(NfcAdapter.ACTION_TAG_DISCOVERED);
//        filters[0].addCategory(Intent.CATEGORY_DEFAULT);
//        try {
//            filters[0].addDataType(MIME_TEXT_PLAIN);
//        } catch (IntentFilter.MalformedMimeTypeException e) {
//            throw new RuntimeException("Check your mime type.");
//        }
//        adapter.enableForegroundDispatch(activity, pendingIntent, null, null);
//    }

    /**
     * @param activity The corresponding {@link BaseActivity} requesting to stop the foreground dispatch.
     * @param adapter The {@link NfcAdapter} used for the foreground dispatch.
     */
//    protected static void stopForegroundDispatch(final Activity activity, NfcAdapter adapter) {
//        adapter.disableForegroundDispatch(activity);
//    }
}
