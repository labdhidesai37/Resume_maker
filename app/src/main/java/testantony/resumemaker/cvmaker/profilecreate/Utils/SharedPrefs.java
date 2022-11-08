package testantony.resumemaker.cvmaker.profilecreate.Utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefs {
    public Context context;
    public SharedPreferences.Editor editor;
    public SharedPreferences prefs;


    private final String active_Weekly = "active_Weekly";
    private final String active_Monthly = "active_Monthly";
    private final String active_Yearly = "active_Yearly";

    public SharedPrefs(Context context2) {
        SharedPreferences sharedPreferences = context2.getSharedPreferences("officeMaster", 0);
        prefs = sharedPreferences;
        editor = sharedPreferences.edit();
        context = context2;
    }

    public void setCVData(String str) {
        editor.putString("SerializableObject", str);
        editor.commit();
    }

    public String getCVData() {
        return prefs.getString("SerializableObject", "");
    }

    public void setProfilePic(String str) {
        editor.putString("profilePic", str);
        editor.commit();
    }

    public String getProfilePic() {
        return prefs.getString("profilePic", null);
    }

    public boolean isOpenPdfSaveDialog() {
        return prefs.getBoolean("PdfSaveDialog", true);
    }

    public void setOpenPdfSaveDialog(boolean z) {
        editor.putBoolean("PdfSaveDialog", z);
        editor.commit();
    }


    public String getActive_Weekly() {
        return prefs.getString(active_Weekly, "");
    }

    public void setActive_Weekly(String value) {
        editor.putString(active_Weekly, value).apply();
    }

    public String getActive_Monthly() {
        return prefs.getString(active_Monthly, "");
    }

    public void setActive_Monthly(String value) {
        editor.putString(active_Monthly, value).apply();
    }

    public String getActive_Yearly() {
        return prefs.getString(active_Yearly, "");
    }

    public void setActive_Yearly(String value) {
        editor.putString(active_Yearly, value).apply();
    }
}
