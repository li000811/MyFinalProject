package algonquin.cst2335.myfinalproject.aviation;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.room.Room;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import algonquin.cst2335.myfinalproject.R;

public class FightActivity extends AppCompatActivity {

    private ConstraintLayout mClRoot;
    private MaterialToolbar mTool;
    private TextView mTvDetail;

    private Button mBtnSave;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fight);

        initView();
        init();
    }

    private void initView() {
        mClRoot = findViewById(R.id.cl_root);
        mTool = findViewById(R.id.tool);
        mTvDetail = findViewById(R.id.tv_detail);
        mBtnSave = findViewById(R.id.btn_save);
    }

    private void init() {

        setSupportActionBar(mTool);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        String dataJson = getIntent().getStringExtra("data");
        if (dataJson != null) {
            Toast.makeText(this, getString(R.string.success), Toast.LENGTH_SHORT).show();
        } else {
            return;
        }

        FightsBean.DataDTO data = new Gson().fromJson(dataJson, FightsBean.DataDTO.class);

        String str = getString(R.string.iata) + ":" + data.getFlight().getIata() +
                "\n" + getString(R.string.status) + ":" + data.getFlight_status();

        if (data.getArrival() != null) {
            str = str +
                    "\n" + getString(R.string.destination) + ":" + data.getArrival().getAirport() +
                    "\n" + getString(R.string.terminal) + ":" + data.getArrival().getTerminal() +
                    "\n" + getString(R.string.gate) + ":" + data.getArrival().getGate() +
                    "\n" + getString(R.string.delay) + ":" + data.getArrival().getDelay();
        }

        mTvDetail.setText(str);

        mBtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog loadingDialog = new AlertDialog
                        .Builder(FightActivity.this)
                        .setTitle(getString(R.string.tips))
                        .setMessage(getString(R.string.save_data))
                        .setPositiveButton(getString(R.string.confirm), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        FightDatabase db = Room.databaseBuilder(getApplicationContext(),
                                                FightDatabase.class, "app.db").build();

                                        FightDao fightDao = db.fightDao();
                                        SaveFightBean bean = new SaveFightBean();
                                        bean.Delay = data.getArrival().getDelay();
                                        bean.Gate = data.getArrival().getGate();
                                        bean.Terminal = data.getArrival().getTerminal();
                                        bean.Destination = data.getArrival().getAirport();
                                        bean.status = data.getFlight_status();
                                        bean.iata = data.getFlight().getIata();
                                        fightDao.insert(bean);
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                Snackbar.make(mClRoot, getString(R.string.success), Snackbar.LENGTH_SHORT).show();
                                            }
                                        });
                                    }
                                }).start();
                            }
                        })
                        .setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .create();

                loadingDialog.show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}