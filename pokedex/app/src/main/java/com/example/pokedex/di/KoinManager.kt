package com.example.pokeapiegsys.di

import com.example.pokeapiegsys.core.RetrofitConfig
import com.example.pokeapiegsys.core.service.PokemonService
import com.example.pokeapiegsys.core.service.PokemonServiceImpl
import com.example.pokeapiegsys.data.dataRepository.PokemonDataRepository
import com.example.pokeapiegsys.data.dataStore.PokemonCacheDataStore
import com.example.pokeapiegsys.data.dataStore.PokemonFactory
import com.example.pokeapiegsys.data.dataStore.PokemonRemoteDataStore
import com.example.pokeapiegsys.domain.interactor.GetRandomPokemonUseCase
import com.example.pokeapiegsys.domain.interactor.LoadAllPokemonUseCase
import com.example.pokeapiegsys.domain.repository.PokemonRepository
import com.example.pokeapiegsys.ui.viewModel.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

class KoinManager {
    companion object {
        fun getModulosAplicacao(): List<Module> {
            return listOf(
                getModuleCore(),
                getModuleDomain(),
                getModuleData(),
                getModulePresentation()
            )
        }

        private fun getModuleCore() = module {
            factory { RetrofitConfig.pokemonService() }
            factory<PokemonService> { PokemonServiceImpl(get()) }
        }

        private fun getModuleDomain() = module {
            factory { LoadAllPokemonUseCase(get()) }
            factory { GetRandomPokemonUseCase(get()) }
        }

        private fun getModuleData() = module {
            factory<PokemonRepository> {
                PokemonDataRepository(
                    PokemonFactory(
                        PokemonRemoteDataStore(get()),
                        PokemonCacheDataStore(),
                    )
                )
            }
        }

        private fun getModulePresentation() = module {
            viewModel { HomeViewModel(get(), get()) }
        }
    }
}
