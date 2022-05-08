package ir.whoisAbel.roompractice

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import ir.whoisAbel.roompractice.db.UserDatabase
import ir.whoisAbel.roompractice.di.ViewModelFactory
import ir.whoisAbel.roompractice.di.bindViewModel
import ir.whoisAbel.roompractice.user.data.UserLocalDataSource
import ir.whoisAbel.roompractice.user.data.UserRepository
import ir.whoisAbel.roompractice.user.ui.viewModel.UserViewModel
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.direct
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class UserApplication : Application(), KodeinAware {

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
    override val kodein: Kodein = Kodein.lazy {

        import(androidXModule(this@UserApplication))
        bind() from singleton { UserDatabase(instance()) }
        bind() from singleton { UserLocalDataSource(instance()) }
        bind() from singleton { UserRepository(instance()) }
        bind<ViewModelProvider.Factory>() with singleton { ViewModelFactory(kodein.direct) }
        bindViewModel<UserViewModel>() with provider {
            UserViewModel(instance())
        }

    }


}