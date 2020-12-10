package github.sachin2dehury.runapp.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import github.sachin2dehury.runapp.data.RunningDatabase
import github.sachin2dehury.runapp.others.Constants
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, RunningDatabase::class.java, Constants.DATABASE_NAME)
        .fallbackToDestructiveMigration().build()

    @Singleton
    @Provides
    fun provideRunDao(runningDatabase: RunningDatabase) = runningDatabase.getRunDao()

    @Singleton
    @Provides
    fun provideSharedPreferences(
        @ApplicationContext context: Context
    ) = context.getSharedPreferences(Constants.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)

    @Singleton
    @Provides
    fun provideName(sharedPreferences: SharedPreferences) =
        sharedPreferences.getString(Constants.KEY_NAME, "") ?: ""

    @Singleton
    @Provides
    fun provideWeight(sharedPreferences: SharedPreferences) =
        sharedPreferences.getFloat(Constants.KEY_WEIGHT, 80f)

    @Singleton
    @Provides
    fun provideFirstTimeToggle(sharedPreferences: SharedPreferences) =
        sharedPreferences.getBoolean(Constants.KEY_FIRST_TIME_TOGGLE, true)
}