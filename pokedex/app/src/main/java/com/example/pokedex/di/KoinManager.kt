package com.example.pokedex.di

import com.example.pokedex.core.RetrofitConfig
import com.example.pokedex.data.dataSource.PokemonRemoteService
import com.example.pokedex.remote.serviceImpl.PokemonServiceImpl
import com.example.pokedex.data.dataRepository.PokemonDataRepository
import com.example.pokedex.data.dataStore.PokemonCacheDataStore
import com.example.pokedex.data.dataStore.PokemonFactory
import com.example.pokedex.data.dataStore.PokemonRemoteDataStore
import com.example.pokedex.domain.interactor.GetRandomPokemonUseCase
import com.example.pokedex.domain.interactor.LoadAllPokemonUseCase
import com.example.pokedex.domain.repository.PokemonRepository
import com.example.pokedex.ui.viewModel.HomeViewModel
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
            factory<PokemonRemoteService> { PokemonServiceImpl(get()) }
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
