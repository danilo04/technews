package pa.ac.utp.components.technewsreader.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pa.ac.utp.components.technewsreader.data.services.InShortService
import pa.ac.utp.components.technewsreader.data.services.LobsterService
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    @Singleton
    @Provides
    @LobsterClient
    fun providesLobsterRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://lobste.rs/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    @InShortClient
    fun providesInShortRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://inshorts.deta.dev/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providesLobsterService(@LobsterClient retrofit: Retrofit): LobsterService {
        return retrofit.create(LobsterService::class.java)
    }

    @Singleton
    @Provides
    fun providesInShortService(@InShortClient retrofit: Retrofit): InShortService {
        return retrofit.create(InShortService::class.java)
    }
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class LobsterClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class InShortClient
