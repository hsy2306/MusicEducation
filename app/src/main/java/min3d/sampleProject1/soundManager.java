package min3d.sampleProject1;

/**
 * Created by LeeDoBin on 2018-01-19.
 */
import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

import java.util.HashMap;

public class soundManager {
    private SoundPool m_soundpool;
    private HashMap m_soundpoolmap;
    private AudioManager m_Audiomanager;
    private Context m_Activity;
    private static soundManager m_instance;

    public static soundManager getInstance(){
        if(m_instance == null){
            m_instance = new soundManager();
        }

        return m_instance;
    }

    public void init(Context context){
        m_soundpool = new SoundPool(4,AudioManager.STREAM_MUSIC,0);
        m_soundpoolmap = new HashMap();
        m_Audiomanager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        m_Activity = context;
    }

    public void addSound(int index, int soundId){
        int id = m_soundpool.load(m_Activity, soundId, 1);
        m_soundpoolmap.put(index, id);
    }

    public void play(int index){
        float streamVolume = m_Audiomanager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        streamVolume = streamVolume / m_Audiomanager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        m_soundpool.play((Integer)m_soundpoolmap.get(index), streamVolume, streamVolume, 1, 0, 1f);
    }

    public void playLooped(int index){
        float streamVolume = m_Audiomanager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        streamVolume = streamVolume / m_Audiomanager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        m_soundpool.play((Integer)m_soundpoolmap.get(index), streamVolume, streamVolume, 1, -1, 1f);
    }
}
