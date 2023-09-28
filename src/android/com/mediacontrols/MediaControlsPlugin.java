package com.mediacontrols;

import static androidx.core.content.ContextCompat.getSystemService;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import android.content.Context;
import android.media.AudioManager;


public class MediaControlsPlugin extends CordovaPlugin {
	public static final String ACTION_SET_AUDIO_MODE = "setAudioMode";

    @Override
    public boolean execute(String action, JSONArray args,
			CallbackContext callbackContext) throws JSONException {
        if(action.equals("setModeAudio")){
            String mode = args.getString(0);
            return this.setModeAudio(mode);
        }
        return false;
    }

	public boolean setModeAudio(String mode) {
        Context context = webView.getContext();
        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);

        if (mode.equals("normal")) {
          /* Mode Normal */
          audioManager.setMode(AudioManager.MODE_IN_COMMUNICATION);
          audioManager.setSpeakerphoneOn(false);
          return true;
        }
        if (mode.equals("speaker")) {
          /* Mode Speaker and Control Volume */
          audioManager.setMode(AudioManager.MODE_NORMAL);
          audioManager.setSpeakerphoneOn(true);
          return true;
        }
        return false;
    }
}
