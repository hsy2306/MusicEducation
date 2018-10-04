package testmode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

import min3d.sampleProject1.R;

/**
 * Created by LeeDoBin on 2018-06-18.
 */

public class SelectSong extends Activity implements MyRecyclerViewAdapter.ItemClickListener {

    private MyRecyclerViewAdapter adapter;
    ImageButton exit;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selectsong);
        exit = (ImageButton)findViewById(R.id.exit);

        ArrayList<Song> songs = new ArrayList<Song>();
        songs.add(new Song(R.drawable.songair, "비행기"));
        songs.add(new Song(R.drawable.littlestar, "작은 별"));
        songs.add(new Song(R.drawable.threebear, "곰 세마리"));
        songs.add(new Song(R.drawable.beatyfly, "나비야"));

        // set up the RecyclerView
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rvAnimals);
        LinearLayoutManager horizontalLayoutManagaer
                = new LinearLayoutManager(SelectSong.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(horizontalLayoutManagaer);
        adapter = new MyRecyclerViewAdapter(this, songs);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void onItemClick(View view, int position) {
        if (position == 0) {
            Intent intent = new Intent(getApplicationContext(), SelectedSong.class);
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(), "준비중입니다 ㅠㅠ", Toast.LENGTH_SHORT).show();
        }
    }
}
