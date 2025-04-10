package nz.co.test.transactions.di

import dagger.Component
import nz.co.test.transactions.activities.MainActivity
import nz.co.test.transactions.di.activities.ActivitiesModule
import nz.co.test.transactions.di.network.NetworkModule
import nz.co.test.transactions.viewmodel.ViewModelFactory
import javax.inject.Singleton


@Component(
    modules = [
        NetworkModule::class,
        ActivitiesModule::class
    ]
)
@Singleton
interface AppComponent {
    fun inject(appComponent: DaggerAppComponentFactory)
    fun inject(activity: MainActivity)
    fun provideViewModelFactory(): ViewModelFactory
}