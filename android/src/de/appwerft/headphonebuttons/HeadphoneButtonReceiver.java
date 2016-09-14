package de.appwerft.headphonebuttons;

import org.appcelerator.kroll.KrollDict;
import org.appcelerator.kroll.common.Log;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;

public class HeadphoneButtonReceiver extends BroadcastReceiver {
	public static final String LCAT = "HeadPhoneButtons  📢📢";
	int lastValue = 0;

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.d(LCAT + "🎧 🎧 🎧 Keycode from MediabuttonIntent", intent
				.getAction().toString());
		KrollDict dict = new KrollDict();
		if (intent.getAction().equals("android.media.VOLUME_CHANGED_ACTION")) {
			int newVolume = intent.getIntExtra(
					"android.media.EXTRA_VOLUME_STREAM_VALUE", 0);
			int oldVolume = intent.getIntExtra(
					"android.media.EXTRA_PREV_VOLUME_STREAM_VALUE", 0);
			if (newVolume == lastValue)
				return;
			lastValue = newVolume;
			if (newVolume > oldVolume) {
				dict.put("oldvolume", oldVolume);
				dict.put("volume", newVolume);
				dict.put("keyName", "volumeup");
				dict.put("keyCode", 24);
				HeadphonebuttonsModule.sendBack(dict);
			}
			if (newVolume < oldVolume) {
				dict.put("oldvolume", oldVolume);
				dict.put("volume", newVolume);
				dict.put("keyName", "volumedown");
				dict.put("keyCode", 25);
				HeadphonebuttonsModule.sendBack(dict);
			}
			KeyEvent keyEvent = (KeyEvent) intent
					.getParcelableExtra(Intent.EXTRA_KEY_EVENT);
			if (keyEvent != null) {
				int keyCode = keyEvent.getKeyCode();
				Log.d(LCAT, "KK=" + keyCode);
				dict.put("keyCode", keyCode);
				if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
					dict.put("keyName", "volumedown");
				}
				if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
					dict.put("keyName", "volumeup");
				}
			}
		}
		if (intent.getAction().equals("android.intent.action.MEDIA_BUTTON")) {
			KeyEvent event = (KeyEvent) intent
					.getParcelableExtra(Intent.EXTRA_KEY_EVENT);
			if (event != null) {
				dict.put("keyCode", event.getKeyCode());
				if (event.getKeyCode() == KeyEvent.KEYCODE_HEADSETHOOK)
					dict.put("keyName", "startstop");
				else if (event.getKeyCode() == KeyEvent.KEYCODE_MEDIA_PLAY)
					dict.put("keyName", "media_play");
				else if (event.getKeyCode() == KeyEvent.KEYCODE_MEDIA_NEXT)
					dict.put("keyName", "media_next");
				else if (event.getKeyCode() == KeyEvent.KEYCODE_MEDIA_PAUSE)
					dict.put("keyName", "media_pause");
				else if (event.getKeyCode() == KeyEvent.KEYCODE_MEDIA_PREVIOUS)
					dict.put("keyName", "media_previous");
				else
					dict.put("keyName", "unknown");
				HeadphonebuttonsModule.sendBack(dict);
			}
		}

	}

}