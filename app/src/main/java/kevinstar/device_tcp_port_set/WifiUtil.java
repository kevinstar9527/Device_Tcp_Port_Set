package kevinstar.device_tcp_port_set;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

/**
 * 作者: KevinStar on 2017/10/9.16:12
 * 邮箱: wednesday9527@gmail.com
 */

public class WifiUtil {
    public static String getIp(Context contxext) {
        WifiManager wm = (WifiManager) contxext.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        // 检查Wifi状态
        if (!wm.isWifiEnabled())
            wm.setWifiEnabled(true);
        WifiInfo wi = wm.getConnectionInfo();
        int ipAdd = wi.getIpAddress();
        String ip = intToIp(ipAdd);
        return ip;
    }
    public static String getWifiName(Context context){
        WifiManager wm = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        // 检查Wifi状态
        if (!wm.isWifiEnabled())
            wm.setWifiEnabled(true);
        WifiInfo wi = wm.getConnectionInfo();
        return wi.getSSID();
    }
    public static String intToIp(int i) {
        return (i & 0xFF) + "." + ((i >> 8) & 0xFF) + "." + ((i >> 16) & 0xFF)
                + "." + (i >> 24 & 0xFF);
    }
}
