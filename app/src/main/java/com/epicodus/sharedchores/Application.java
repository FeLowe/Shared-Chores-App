package com.epicodus.sharedchores;


/**
 * Includes one-time initialization of Firebase related code
 */
public class Application extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();
//        /* Initialize Firebase */
//        Firebase.setAndroidContext(this);
//        /* Enable disk persistence  */
//        Firebase.getDefaultConfig().setPersistenceEnabled(true);
    }

}
