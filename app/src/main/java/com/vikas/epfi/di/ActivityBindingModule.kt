package com.vikas.epfi.di

import com.vikas.epfi.di.scope.ActivityScoped

import com.vikas.epfi.ui.login.LoginActivity
import com.vikas.epfi.ui.splash.SplashActivity

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(
    includes = [ViewModelModule::class]
)
abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector
    abstract fun splashActivity(): SplashActivity

    @ActivityScoped
    @ContributesAndroidInjector
    abstract fun loginActivity(): LoginActivity

}