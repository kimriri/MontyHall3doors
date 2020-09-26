package ko.co.montyhall3doors

import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import androidx.appcompat.app.AppCompatActivity
import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        SystemClock.sleep(5000)
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
