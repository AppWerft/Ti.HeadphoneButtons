Ti.HeadsetButtons
=================

This little Titanium module is for listening to headset buttons. Details you can find [on Android page](https://source.android.com/devices/accessories/headset/specification.html)

![](https://source.android.com/devices/accessories/headset/images/headset-circuit2.png)

<img src="http://theinstructional.s3.amazonaws.com/earphones/original-earphones.jpg" width=500 />

##Usage

```javascript
require("de.appwerft.headsetbuttons").addEventListener("keypressed",function(evt){
console.log(evt.keycode);
});
```
Add “android.permission.BLUETOOTH” permission if you want to support bluetooth headset.