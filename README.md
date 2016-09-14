Ti.HeadphoneButtons
=================

This little Titanium module is for listening to headset buttons. Details you can find [on Android page](https://source.android.com/devices/accessories/headset/specification.html)

![](https://source.android.com/devices/accessories/headset/images/headset-circuit2.png)

<img src="http://theinstructional.s3.amazonaws.com/earphones/original-earphones.jpg" width=500 />

##Usage

```javascript
require("de.appwerft.headphonebuttons").registerListener();
Ti.App.addEventListener("mediaButton", function(e) {
    console.log(e.keyCode);
    console.log(e.keyName);  // media_play, media_next, media_previous
});
```
This you need in you Manifest:
```xml
<receiver android:name="de.appwerft.headphonebuttons.MediaButtonReceiver" android:enabled="true" android:exported="true">
<intent-filter android:priority="999">
    <action android:name="android.media.AUDIO_BECOMING_NOISY" />
    <action android:name="android.intent.action.MEDIA_BUTTON"/>
</intent-filter>
</receiver>
```

Add “android.permission.BLUETOOTH” permission (outside the application-"folder") if you want to support bluetooth headset. 

