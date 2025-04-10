package nz.co.test.transactions

import android.app.Application
import nz.co.test.transactions.di.AppComponent
import nz.co.test.transactions.di.DaggerAppComponent

class TransactionApplication : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }
}