package com.daniel.farage.financeapp.di

import com.daniel.farage.financeapp.data.remote.Api
import com.daniel.farage.financeapp.data.remote.BackendClient
import org.koin.dsl.module

object CommonModule {
    val commomModule = module {
        single<Api> { BackendClient.client.create(Api::class.java) }
    }
}