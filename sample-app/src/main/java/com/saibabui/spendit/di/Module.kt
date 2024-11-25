package com.saibabui.spendit.di

import android.app.Application
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.saibabui.spendit.auth.singup.data.FireBaseAuthRepositoryImplementation
import com.saibabui.spendit.auth.singup.domain.ValidateEmail
import com.saibabui.spendit.auth.singup.domain.ValidateName
import com.saibabui.spendit.auth.singup.domain.ValidatePassword
import com.saibabui.spendit.auth.singup.domain.ValidateRepeatedPassword
import com.saibabui.spendit.common.PreferenceUtils
import com.saibabui.spendit.home.ChatRepository
import com.saibabui.spendit.home.ChatRepositoryImpl
import com.saibabui.spendit.home.contactscreen.data.ContactRepository
import com.saibabui.spendit.home.contactscreen.data.ContactRepositoryImpl
import com.saibabui.spendit.home.homescreen.GetChatRoomListUseCase
import com.saibabui.spendit.home.profile.CreateChatRoomUseCase
import com.saibabui.spendit.auth.FireBaseAuthRepository
import com.saibabui.spendit.auth.login.data.LoginRepository
import com.saibabui.spendit.auth.login.data.LoginRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAppPreference(application: Application): PreferenceUtils =
        PreferenceUtils(application)


    @Provides
    @Singleton
    fun provideValidateEmail(): ValidateEmail = ValidateEmail()


    @Provides
    @Singleton
    fun provideValidatePassword(): ValidatePassword = ValidatePassword()


    @Provides
    @Singleton
    fun provideValidateConformPassword(): ValidateRepeatedPassword = ValidateRepeatedPassword()


    @Provides
    @Singleton
    fun provideValidateName(): ValidateName = ValidateName()

    @Provides
    @Singleton
    fun provideFireBaseAuthRepository(): FireBaseAuthRepository =
        FireBaseAuthRepositoryImplementation(auth = Firebase.auth)


    @Provides
    @Singleton
    fun provideFirebaseFireStore(): FirebaseFirestore = Firebase.firestore


    @Provides
    @Singleton
    fun provideContactDetailsRepository(firebaseFireStore: FirebaseFirestore): ContactRepository =
        ContactRepositoryImpl(firebaseFireStore)


    @Provides
    @Singleton
    fun provideChatRepository(firebaseFireStore: FirebaseFirestore): ChatRepository =
        ChatRepositoryImpl(firestore = firebaseFireStore)


    @Provides
    @Singleton
    fun provideCreateChatRoomUseCase(
        chatRepository: ChatRepository
    ): CreateChatRoomUseCase = CreateChatRoomUseCase(chatRepository)

    @Provides
    @Singleton
    fun provideGetAllChatRoomsUseCase(
        chatRepository: ChatRepository,
        firebaseFireStore: FirebaseFirestore
    ): GetChatRoomListUseCase = GetChatRoomListUseCase(chatRepository, firebaseFireStore)


    @Provides
    @Singleton
    fun provideLoginRepository(): LoginRepository = LoginRepositoryImpl(auth = Firebase.auth)

}