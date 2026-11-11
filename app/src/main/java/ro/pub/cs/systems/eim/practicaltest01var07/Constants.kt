package ro.pub.cs.systems.eim.practicaltest01var07
object Constants {

    // 1. Tag comun pentru filtrare în Logcat
    const val TAG = "PracticalTest01"

    // 2. Acțiunile de difuzare (Broadcast Actions) cerute în enunț (3 acțiuni diferite)
    const val ACTION_1 = "ro.pub.cs.systems.eim.practicaltest01.action1"
    const val ACTION_2 = "ro.pub.cs.systems.eim.practicaltest01.action2"
    const val ACTION_3 = "ro.pub.cs.systems.eim.practicaltest01.action3"

    // 3. Chei pentru transmiterea datelor prin Intent (Extras)
    const val KEY_NUMBER_1 = "NUMBER_1"
    const val KEY_NUMBER_2 = "NUMBER_2"
    const val KEY_NUMBER_3 = "NUMBER_3"
    const val KEY_NUMBER_4 = "NUMBER_4"
    const val KEY_TOTAL_CLICKS = "TOTAL_CLICKS"
    const val KEY_RESULT = "RESULT"

    // 4. Chei pentru datele transmise în mesajul cu difuzare (Broadcast Extras)
    const val KEY_DATE_TIME = "DATE_TIME"
    const val KEY_ARITHMETIC_MEAN = "ARITHMETIC_MEAN"
    const val KEY_GEOMETRIC_MEAN = "GEOMETRIC_MEAN"

    // 5. Configurare Serviciu & Praguri de declanșare
    const val NUMBER_OF_CLICKS_THRESHOLD = 10
    const val SLEEP_TIME_MS = 10000L // 10 secunde conform enunțului
    const val SERVICE_STOPPED = 0
    const val SERVICE_STARTED = 1

    // 6. Constante Notificări & Permisiuni (Android 8.0+ și Android 13+)
    const val CHANNEL_ID = "practical_test_01_channel"
    const val CHANNEL_NAME = "Practical Test 01 Channel"
    const val NOTIFICATION_ID = 1
    const val PERMISSION_REQUEST_POST_NOTIFICATIONS = 1
}
